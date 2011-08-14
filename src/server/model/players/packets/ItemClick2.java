package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Item Click 2 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 * Proper Streams
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		
		if (!c.getItems().playerHasItem(itemId,1))
			return;

		switch (itemId) {
			case 11283:
			case 11284:
			case 11285:
			c.sendMessage("Your shield has "+c.dfsCount+" charges");
			break;

                case 11694:/*AGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11694, 1)) {
			c.getItems().deleteItem(11694,1);
			c.getItems().addItem(11702,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
			case 11696:/*BGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11696, 1)) {
			c.getItems().deleteItem(11696,1);
			c.getItems().addItem(11704,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
			case 11698:/*SGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11698, 1)) {
			c.getItems().deleteItem(11698,1);
			c.getItems().addItem(11706,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
			case 11700:/*ZGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11700, 1)) {
			c.getItems().deleteItem(11700,1);
			c.getItems().addItem(11708,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
		default:
			if (c.playerRights == 3)
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId);
			break;
		}

	}

}
