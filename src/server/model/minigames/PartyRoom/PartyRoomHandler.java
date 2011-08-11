package server.model.minigames.partyroom;

import server.model.players.PlayerHandler;
import server.Config;
import server.model.players.Client;
import server.model.players.Player;

/**
* PartyRoomHandler class
* Handles all the key features required for a REAL working party room.
* @author Adam Mackay a.k.a. Skiiii
*/

public class PartyRoomHandler {

	/**
	* The maximum amount of items the chest can hold
	* to be dropped during the party.
	*/
	public static final int MAXIMUM_DROP_ITEMS = 30;

	/**
	* The amount of items that has currently
	* been added to the chest.
	*/
	public static int itemsAdded = 0;

	/**
	* The drop list of PartyItem's waiting to be dropped.
	*/
	public static PartyItem[] dropItems = new PartyItem[MAXIMUM_DROP_ITEMS];


	/**
	* Opens the chest interface at the party room.
	* Displays items currently waiting to be dropped
	* and allows the player to add items to the chest
	* @param Client c : The client viewing the chest.
	*/
	public void openPartyChest(Client c) {
		/**
		* Changes the player inventory interface options
		* and resets the items shown on it.
		* Creates the chest interface.
		*/
		c.getItems().resetItems(2006);
		c.outStream.createFrame(248);
		c.outStream.writeWordA(2156);
		c.outStream.writeWord(2005);
		c.flushOutStream();

		/**
		* Sends the current items waiting to be dropped
		* to be displayed on the chest interface.
		*/
		int count = 0;
		for(PartyItem pi : dropItems) {
			c.getPA().sendItemArray(2273, pi.getItemId(), count++, pi.getItemAmount());
		}

		/**
		* Resets the items waiting to be added to chest
		* and clears the interface of previous items displayed.
		*/
		for(int i = 0; i <= 10; i++) {
			c.partyItems[i] = null;
			c.getPA().sendItemArray(2274, -1, i, 0);
		}
	}

	/**
	* updateChestItems method
	* Updates the items in the chest when a new item is added.
	* @param c The client for which the chest is being updated.
	*/
	public boolean updateChestItems(Client c) {
		/**
		* Start by clearing the current interface
		* of any items displayed using a loop.
		*/
		for(int i = 0; i < 30; i++) {
			c.getPA().sendItemArray(2273, -1, i, 0);
		}

		/**
		* Now display all items in the drop list on the interface.
		*/
		int count = 0;
		for(PartyItem pi : dropItems) {
			if(count > MAXIMUM_DROP_ITEMS) {
				return false;
			}
			c.getPA().sendItemArray(2273, pi.getItemId(), count++, pi.getItemAmount());
		}
		return true;
	}

	/**
	* updateDepositItems method
	* Updates the items being deposited by the player when a new item is added.
	* @param c The client for which the deposit list is being updated.
	*/
	public boolean updateDepositItems(Client c) {
		int count = 0;
		for(PartyItem pi : c.partyItems) {
			if(pi != null) {
				if(count > 7) {
					// Can not add more than 8 items at a time.
					c.sendMessage("You can not deposit any more items until the current items have been added.");
					return false;
				}
				// Displays items in the players partyItem array on the deposit interface.
				c.getPA().sendItemArray(2274, pi.getItemId(), count++, pi.getItemAmount());
			} else {
				// Leaves the space blank, but does not increase the count, allowing another item to replace the blank slot.
				c.getPA().sendItemArray(2274, -1, count, 0);
			}
		}
		return true;
	}

	/**
	* addItemsToChest method
	* Add items waiting to be deposited to the chest.
	* @param c The client who is depositing the items.
	*/
	public void addItemsToChest(Client c) {
		/**
		* Checks the players partyItems array to see if any items
		* are being deposited. If not send a message and return.
		*/
		int itemCount = 0;
		for(PartyItem pi : c.partyItems) {
			if(pi != null) {
				itemCount++;
			}
		}
		if(itemCount < 1) {
			c.sendMessage("There is no items waiting to be deposited.");
			return;
		}

		/**
		* Adds the item to the chest and
		* checks if the item is previously in the chest.
		* If so adds the new item amount to the old item amount.
		*/
		boolean isNewItem = true;
		for(PartyItem pi : c.partyItems) {
			for(PartyItem di : dropItems) {
				if(pi.getItemId() == di.getItemId()) {
					di.setItemAmount(pi.getItemAmount() + di.getItemAmount());
					isNewItem = false;
				}
			}
			if(isNewItem) {
				dropItems[itemsAdded++] = pi;
			}
		}

		/**
		* Updates the chest items displayed on the interface for all players
		* and clears the players partyItems array to avoid duplications.
		*/
		for(Player p : PlayerHandler.players) {
			Client c1 = (Client)p;
			updateChestItems(c1);
		}
		for(int i = 0; i < 8; i++) {
			c.partyItems[i] = null;
			c.getPA().sendItemArray(2274, -1, i, 0);
		}
	}

	/**
	* addItemToDeposit method.
	* Adds an item to the waiting to be deposited list
	* and displays it on the interface.
	* @param c The player depositing the item.
	* @param itemId The id of the item being added.
	* @param itemSlot The slot from which the item is being deposited in the players inventory.
	* @param itemAmount The amount of the item being deposited.
	*/
	public void addItemToDeposit(Client c, int itemId, int itemSlot, int itemAmount) {
		/**
		* Checks if the item being added is tradeable.
		* If not sends a message and returns.
		*/
		for(int i : Config.ITEM_TRADEABLE) {
			if(itemId == i) {
				c.sendMessage("You can not deposit this item.");
				return;
			}
		}

		/**
		* Checks if the player is trying to add more of the item
		* then they have in their inventory.
		*/
		if(!c.getItems().playerHasItem(itemId, itemAmount)) {
			itemAmount = c.getItems().getItemAmount(itemId);
		}
		if(itemAmount < 1) {
			c.sendMessage("You do not have any of this item to deposit.");
			return;
		}

		/**
		* Adds the item to the waiting to be deposited list (partyItems array).
		* Checks if the item previously exists in the array
		* if so increases the item amount.
		*/
		boolean itemExists = false;
		for(PartyItem pi : c.partyItems) {
			if(pi.getItemId() == itemId) {
				pi.setItemAmount(pi.getItemAmount() + itemAmount);
				c.getItems().deleteItem(itemId, itemSlot, itemAmount);
				itemExists = true;
			}
		}

		/**
		* Attempts to update the items waiting to be deposited.
		* If unsuccessful, returns and does not add the item.
		*/
		if(updateDepositItems(c)) {
			if(!itemExists) {
				PartyItem newItem = new PartyItem(itemId, itemAmount);
				c.partyItems[getFreeDepositSlot(c)] = newItem;
				c.getItems().deleteItem(itemId, itemSlot, itemAmount);
			}
		}
		c.getItems().resetItems(2006);
	}

	private int getFreeDepositSlot(Client c) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* withdrawItem method.
	* Withdraws an item from the waiting to be deposited section of the chest.
	* @param c The player who is withdrawing the item.
	* @param itemId The id of the item being withdrawn.
	* @param itemSlot The slot in the chest the item is being withdrawn from.
	* @param itemAmount The amount of the item being added.
	*/
	public void withdrawItem(Client c, int itemId, int itemSlot, int itemAmount) {
		/**
		* Finds the item in the players partyItems array.
		*/
		PartyItem pi = null;
		for(PartyItem di : c.partyItems) {
			if(di.getItemId() == itemId) {
				pi = di;
			}
		}

		/**
		* Checks if the player is trying to withdraw more of the item
		* then currently exists in the waiting to be deposited items.
		*/
		if(itemAmount > pi.getItemAmount()) {
			itemAmount = pi.getItemAmount();
		}

		/**
		* Adds the item to the players inventory
		* and removes it from the list.
		*/
		c.getItems().addItem(itemId, itemAmount);
		c.getItems().resetItems(2006);
		pi.setItemAmount(pi.getItemAmount() - itemAmount);
		if(pi.getItemAmount() <= 0) {
			c.partyItems[itemSlot] = null;
		}
		updateDepositItems(c);
	}

}