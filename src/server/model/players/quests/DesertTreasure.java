package server.model.players.quests;

import server.Server;
import server.model.players.Client;
import server.model.players.Player;

/**
* Desert treasure
* @author Ian
*/

public class DesertTreasure {

	public Client c;
	
	public DesertTreasure(Client client) {
		c = client;
	}
	
	public final int ARCH_ID = 1918;
	public final int ARCH_X = 3128;
	public final int ARCH_Y = 3245;
	
	public int getDtX(int lastKill) {
		switch(lastKill) {
			case 1977:
			return 2739;
			case 1974:
			case 1975:
			return 3570;
			case 1914:
			return 2836;
		}
		return 0;
	}
	
	public int getDtY(int lastKill) {
		switch(lastKill) {
			case 1977:
			return 5092;
			case 1974:
			case 1975:
			return 3404;
			case 1914:
			return 3810;
		}
		return 0;
	}
	
	public int getDtH(int lastKill) {
		switch(lastKill) {
			case 1977:
			case 1974:
			case 1975:
			return 4;
			case 1914:
			return 4;
		}
		return 0;
	}
	
	public int playerX(int lastKill) {
		switch(lastKill) {
			case 1977:
			return 2739;
			case 1975:
			return 3570;
			case 1914:
			return 2848;
		}
		return 0;
	}
	
	public int playerY(int lastKill) {
		switch(lastKill) {
			case 1977:
			return 5086;
			case 1975:
			return 3406;
			case 1914:
			return 3809;
		}
		return 0;
	}
	
	public String nextDtKill(String lastKill) {
		if(Server.npcHandler.getNpcName(1914).equalsIgnoreCase(lastKill)) {
			return Server.npcHandler.getNpcName(1913);
		} 
		else if(Server.npcHandler.getNpcName(1974).equalsIgnoreCase(lastKill)) {
			return Server.npcHandler.getNpcName(1914);
		}
		else if(Server.npcHandler.getNpcName(1977).equalsIgnoreCase(lastKill)) {
			return Server.npcHandler.getNpcName(1974);
		}
		return "";
	}
	
	public int nextDtNpcId(String lastKill) {
		if(Server.npcHandler.getNpcName(1974).equalsIgnoreCase(lastKill)) {
			return 1914;
		} 
		else if(Server.npcHandler.getNpcName(1914).equalsIgnoreCase(lastKill)) {
			return 1913;
		}
		else if(Server.npcHandler.getNpcName(1977).equalsIgnoreCase(lastKill)) {
			return 1974;
		}
		return -1;
	}
	
	public int[][] dtNpcInfo = {
		{1915, 200, 20, 80, 80}, // NPCID, HP, MAX, ATK, DEF
		{1974, 90, 29, 70, 70},
		{1975, 200, 29, 85, 80},
		{1913, 130, 22, 80, 80},
		{1914, 200, 19, 80, 80}
	};
	
	public void getDtNpcInfo(int npcId) {
		for(int i = 0; i < dtNpcInfo.length; i++) {
			if(dtNpcInfo[i][0] == npcId) {
				c.dtHp = dtNpcInfo[i][1];
				c.dtMax = dtNpcInfo[i][2];
				c.dtAtk = dtNpcInfo[i][3];
				c.dtDef = dtNpcInfo[i][4];
			}
		}
	}
	
	public void questInformation() {
		for(int i = 8144; i < 8195; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("@dre@Desert Treasure", 8144);
		c.getPA().sendFrame126("", 8145);
		if(c.desertT == 0) {
			c.getPA().sendFrame126("Talk to the Archaeologist in the jail", 8147);
			c.getPA().sendFrame126("east of Draynor to start this quest.", 8148);
		} else if(c.desertT == 1) {
			c.getPA().sendFrame126("@str@Talk to the Archaeologist in the jail", 8147);
			c.getPA().sendFrame126("@str@east of Draynor to start this quest.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Talk to the Archaeologist when you", 8150);
			c.getPA().sendFrame126(c.lastDtKill == 0 ? "are ready to fight Fareed." : "are ready to fight "+nextDtKill(Server.npcHandler.getNpcName(c.lastDtKill))+".", 8151);
		} else if(c.desertT == 2) {
			c.getPA().sendFrame126("@str@Talk to the Archaeologist in the jail", 8147);
			c.getPA().sendFrame126("@str@east of Draynor to start this quest.", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("@str@Talk to the Archaeologist when you", 8150);
			c.getPA().sendFrame126(c.lastDtKill == 0 ? "@str@are ready to fight Fareed." : "@str@are ready to fight "+nextDtKill(Server.npcHandler.getNpcName(c.lastDtKill))+".", 8151);
			c.getPA().sendFrame126("", 8152);
			c.getPA().sendFrame126("You have completed this quest!", 8153);
		}
		c.getPA().showInterface(8134);
	}
	
	public void handleDtKills(int i) {
		if (Server.npcHandler.npcs[i].npcType == 1974) {
			getDtNpcInfo(1975);
			Server.npcHandler.spawnNpc(c, 1975, 2739, 5092, c.height, 0, c.dtHp, c.dtMax, c.dtAtk, c.dtDef, true, true);
			return;
		}
		int dtNpcs[] = {
			1975, 1914, 1977, 1913
		};
		for(int j = 0; j < dtNpcs.length; j++) {
			if(Server.npcHandler.npcs[i].npcType == dtNpcs[j]) {
				c.inDt = false;
				c.getPA().movePlayer(ARCH_X, ARCH_Y, 0);
				if(dtNpcs[j] != 1913) {
					c.getDH().sendDialogues(406, ARCH_ID);
				} else {
					c.getDH().sendDialogues(61, ARCH_ID);
				}
			}
		}
	}

}
