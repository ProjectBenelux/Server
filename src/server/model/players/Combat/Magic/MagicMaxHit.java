package server.model.players.combat.magic;

import server.model.players.CombatAssistant;
import server.model.players.Client;

public class MagicMaxHit {
	
	public static int finalMagicDamage(Client c) {
		double damage = c.MAGIC_SPELLS[c.oldSpellId][6];
		double damageMultiplier = 1;
		if (c.playerLevel[c.playerMagic] > c.getLevelForXP(c.playerXP[6])
				&& c.getLevelForXP(c.playerXP[6]) >= 95)
			damageMultiplier += .03 * (c.playerLevel[c.playerMagic] - 99);
		else
			damageMultiplier = 1;
		switch (c.playerEquipment[c.playerWeapon]) {
		case 18371: // Gravite Staff
			damageMultiplier += .05;
			break;
		case 4675: // Ancient Staff
		case 4710: // Ahrim's Staff
		case 4862: // Ahrim's Staff
		case 4864: // Ahrim's Staff
		case 4865: // Ahrim's Staff
		case 6914: // Master Wand
		case 8841: // Void Knight Mace
		case 13867: // Zuriel's Staff
		case 13869: // Zuriel's Staff (Deg)
			damageMultiplier += .10;
			break;
		case 15486: // Staff of Light
			damageMultiplier += .15;
			break;
		case 18355: // Chaotic Staff
			damageMultiplier += .20;
			break;
		}
		switch (c.playerEquipment[c.playerAmulet]) {
		case 18333: // Arcane Pulse
			damageMultiplier += .05;
			break;
		case 18334:// Arcane Blast
			damageMultiplier += .10;
			break;
		case 18335:// Arcane Stream
			damageMultiplier += .15;
			break;
		}
				switch (c.playerEquipment[c.playerHat]) {
		case 20159: // Virtus Mask
			damageMultiplier += .03;
			break;
		}
		switch (c.playerEquipment[c.playerChest]) {
		case 20159: // Virtus Body
			damageMultiplier += .06;
			break;
		}
		switch (c.playerEquipment[c.playerLegs]) {
		case 20159: // Virtus Legs
			damageMultiplier += .04;
			break;
		}
		damage *= damageMultiplier;
		return (int) damage;
	}

}
