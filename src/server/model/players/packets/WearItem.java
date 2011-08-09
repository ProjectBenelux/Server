package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;


/**
 * Wear Item
 **/
 
public class WearItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		c.interfaceId = c.getInStream().readUnsignedWordA();
		if (c.wearId == 5509) {
			c.getPA().removeSmallPouch();
			return;
		}
		if (c.wearId == 5510) {
			c.getPA().removeMediumPouch();
			return;
		}
		if (c.wearId == 5511) {
			c.getPA().removeMediumPouch();
			return;
		}
		if (c.wearId == 5512) {
			c.getPA().removeLargePouch();
			return;
		}
		if (c.wearId == 5513) {
			c.getPA().removeLargePouch();
			return;
		}
		if (c.wearId == 5514) {
			c.getPA().removeGiantPouch();
			return;
		}
		if (c.wearId == 5515) {
			c.getPA().removeGiantPouch();
			return;
		}
		int oldCombatTimer = c.attackTimer;
		if (c.playerIndex > 0 || c.npcIndex > 0)
			c.getCombat().resetPlayerAttack();
		/*if (c.wearId >= 5509 && c.wearId <= 5515) {
			int pouch = -1;
			int a = c.wearId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().emptyPouch(pouch);
			return;
		}*/
			//c.attackTimer = oldCombatTimer;
		c.getItems().wearItem(c.wearId, c.wearSlot);
	}
}
