package server.model.minigames.partyroom;

public class PartyItem {

	/**
	* The id and amount of the item created.
	*/
	private int itemId;
	private int itemAmount;

	/**
	* Constructor for creating a party item to be added to the chest.
	* @param id The id of the item being added to the chest.
	* @param amount The amount of the item being added to the chest.
	*/
	public PartyItem(int id, int amount) {
		this.itemId = id;
		this.itemAmount = amount;
	}

	/**
	* Returns the id of the item.
	*/
	public int getItemId() {
		return itemId;
	}

	/**
	* Returns the amount of the item.
	*/
	public int getItemAmount() {
		return itemAmount;
	}

	/**
	* Sets the amount of the item remaining.
	*/
	public void setItemAmount(int i) {
		itemAmount = i;
	}

}
