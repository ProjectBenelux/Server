package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.PlayerSave;
import server.model.players.Player;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;


import java.io.*;

/**
 * Commands
 **/
public class Commands implements PacketType 
{

    
    @Override
    public void processPacket(Client c, int packetType, int packetSize) 
    {
    String playerCommand = c.getInStream().readString();
		if (!playerCommand.startsWith("/"))
		{
			c.getPA().writeCommandLog(playerCommand);
		}
					if (playerCommand.startsWith("pure") && c.puremaster == 0) {
				int i = 0;
				c.getPA().addSkillXP((15000000), 0);
				c.getPA().addSkillXP((15000000), 2);
				c.getPA().addSkillXP((15000000), 3);
				c.getPA().addSkillXP((15000000), 4);
				c.getPA().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.puremaster = 1;
				}
				if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
   try {
   BufferedWriter report = new BufferedWriter(new FileWriter("./Data/bans/Reports.txt", true));
   String Report = playerCommand.substring(7);
   try {	
	report.newLine();
	report.write(c.playerName + ": " + Report);
	c.sendMessage("You have successfully submitted your report.");
	} finally {
	report.close();
	}
	} catch (IOException e) {
                e.printStackTrace();
	}
}
		if (playerCommand.startsWith("kdr")) {
				double KDR = ((double)c.KC)/((double)c.DC);
				c.forcedChat("My Kill/Death ratio is "+c.KC+"/"+c.DC+"; "+KDR+".");
			}
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
				c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;       
		}
    if (Config.SERVER_DEBUG)
        Misc.println(c.playerName+" playerCommand: "+playerCommand);
    
    if (c.playerRights >= 0)
        playerCommands(c, playerCommand);
    if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3) 
        moderatorCommands(c, playerCommand);
    if (c.playerRights == 2 || c.playerRights == 3) 
        administratorCommands(c, playerCommand);
    if (c.playerRights == 3) 
        ownerCommands(c, playerCommand);
        if (c.playerRights == 4) 
        DonatorCommands(c, playerCommand);
			
    }

    
    public void playerCommands(Client c, String playerCommand)
    {		 
			if (playerCommand.startsWith("resettask")) {
				c.taskAmount = -1;
				c.slayerTask = 0;
			}
			if (playerCommand.startsWith("resetdef")) {
				if (c.inWild())
				return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("Please take all your armour and weapons off before using this command.");
						return;
					}
				}
				try {
					int skill = 1;
					int level = 1;
					c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
					c.getPA().refreshSkill(skill);
				} catch (Exception e){}
			}
			
						if (playerCommand.startsWith("rules")) {
				c.sendMessage("Welcome to ErasedPkz");
				c.sendMessage("1.Do not ask staff for any stuff (mute).");
				c.sendMessage("2.No Pkpoint farming (Jail/Ban)");
				c.sendMessage("3.Do not use offensive Language. (Mild flaming aloud)");
				c.sendMessage("4.Do not Scam Passwords or Items (IPBAN)");
				c.sendMessage("5.Auto Clickers are NOT Allowed, Auto Typers are");
				c.sendMessage("if you set the timer to 5 + Seconds");
				c.sendMessage("Luring is aloud do not complain to a admin");
				c.sendMessage("If your a idiot and you got lured.");
			}
			
			if (playerCommand.startsWith("rest")) {
c.startAnimation(5713);
			}
				if (playerCommand.startsWith("EPP") || playerCommand.startsWith("epp") || playerCommand.startsWith("Epp")) {
				c.sendMessage("You have <col=1532693>" + c.pkPoints + "</col> EPP.");
				}
					if (playerCommand.equalsIgnoreCase("voted")) {
			      if(c.checkVotes(c.playerName)) {
                                c.getItems().addItem(995, 10000000);
                                c.sendMessage("<col=1532693>Thanks for voting!</col>");
                        } else {
		c.sendMessage("<col=1532693>You have voted already!</col>");
}
}
			if (playerCommand.equals("vote")) {
						c.getPA().sendFrame126("www.erased-pkz.comyr.com/Vote/vote.php", 12000);
						c.sendMessage("Please vote every day!");
					}
		    if (playerCommand.equalsIgnoreCase("players")) {
			c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
		    }
			if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}

			if (playerCommand.startsWith("ep") || playerCommand.startsWith("Ep") || playerCommand.startsWith("EP") || playerCommand.startsWith("eP")) {
			c.sendMessage("EP: "+ c.earningPotential+"");
			}
			if (playerCommand.startsWith("yell")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						
							
							if (c.isDonator == 1 && (c.playerRights < 1 || c.playerRights > 3)){
								c2.sendMessage("<shad=6081134>[Donator]</col><img=0>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 1){
								c2.sendMessage("<col=255>[Mod]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 2){
								c2.sendMessage("<col=255>[Admin]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerName.equalsIgnoreCase("rex")){
								c2.sendMessage("<shad=15695415>[Owner]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 3){
								c2.sendMessage("<shad=255>[Co-Owner]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 0 && c.isDonator == 0) {
								c.sendMessage("<shad=6081134><img=0>You must be a Donator to use Pure!<img=0></col>");
									
							}
						}
					}
				}
        
        
    }
    
    public void moderatorCommands(Client c, String playerCommand)
    {
			if(playerCommand.startsWith("jail")) {
				try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					c2.teleportToX = 3102;
					c2.teleportToY = 9516;
					c2.Jail = true;
					c2.sendMessage("You have been jailed by "+c.playerName+"");
					c.sendMessage("Successfully Jailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
}
if (playerCommand.startsWith("mute")) {
			try {	
				String playerToBan = playerCommand.substring(5);
				Connection.addNameToMuteList(playerToBan);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName);
							c.sendMessage("You have muted: " + c2.playerName);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}	
		
		if (playerCommand.startsWith("unmute")) {
			try {	
				String playerToBan = playerCommand.substring(7);
				Connection.unMuteUser(playerToBan);
				c.sendMessage("Unmuted.");
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}	

				if (playerCommand.startsWith("checkbank")) {
				String[] args = playerCommand.split(" ");
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c.getPA().otherBank(c, o);
						break;
						}
					}
				}
			}
			if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}
			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
						Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}
			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if(playerCommand.startsWith("unjail")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					c2.teleportToX = 3086;
                        		c2.teleportToY = 3493;
					c2.monkeyk0ed = 0;
					c2.Jail = false;
					c2.sendMessage("You have been unjailed by "+c.playerName+".");
					c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
        
    }
    
    public void administratorCommands(Client c, String playerCommand)
    {
			if (playerCommand.startsWith("alert")) {
				String msg = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						 Client c2 = (Client)Server.playerHandler.players[i];
						c2.sendMessage("Alert##Notification##" + msg + "##By: " + c.playerName);

					}
				}
			}
			if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								c2.sendMessage(" " +c2.playerName+ " Got IpMuted By " + c.playerName+ ".");
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}	
				}	



			if (playerCommand.startsWith("object")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}
			
			if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
			}

			if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}

			if (playerCommand.startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}

			if (playerCommand.startsWith("xteletome")) {
				try {	
					String playerToTele = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been teleported to " + c.playerName);
								c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		


			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
			if (playerCommand.equalsIgnoreCase("bank")) {
				c.getPA().openUpBank();
			}
			if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
						}			
					}
			if (playerCommand.startsWith("ipban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
						Client c2 = (Client)Server.playerHandler.players[i];
								Server.playerHandler.players[i].disconnected = true;
								c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

        
    }
    
    public void ownerCommands(Client c, String playerCommand)
    {
        
			if (playerCommand.startsWith("update") && c.playerName.equalsIgnoreCase("DarkSlayerz")) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			

			if(playerCommand.startsWith("npc")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}

			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}

			if (playerCommand.startsWith("spec")) {
				c.specAmount = 500.0;
			}

			if (playerCommand.startsWith("giveadmin") && c.playerName.equalsIgnoreCase("DarkSlayerz")) {
				try {	
					String playerToAdmin = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been given admin status by " + c.playerName);
								c2.playerRights = 2;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			if (playerCommand.startsWith("givemod") && c.playerName.equalsIgnoreCase("DarkSlayerz")) {
				try {	
					String playerToMod = playerCommand.substring(8);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been given mod status by " + c.playerName);
								c2.playerRights = 1;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

            if (playerCommand.startsWith("pnpc"))
                {
                try {
                    int newNPC = Integer.parseInt(playerCommand.substring(5));
                    if (newNPC <= 200000 && newNPC >= 0) {
                        c.npcId2 = newNPC;
                        c.isNpc = true;
                        c.updateRequired = true;
                        c.setAppearanceUpdateRequired(true);
                    } 
                    else {
                        c.sendMessage("No such P-NPC.");
                    }
                } catch(Exception e) {
                    c.sendMessage("Wrong Syntax! Use as ::pnpc #");
                }
            }

			
				if (playerCommand.startsWith("givedonor") && c.playerName.equalsIgnoreCase("DarkSlayerz")) {
				try {	
					String playerToMod = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been given donator status by " + c.playerName);
								c2.playerRights = 4;
								c2.isDonator = 1;
								c2.logout();
								
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			
			if (playerCommand.startsWith("demote") && c.playerName.equalsIgnoreCase("DarkSlayerz")) {
				try {	
					String playerToDemote = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been demoted by " + c.playerName);
								c2.playerRights = 0;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
						if (playerCommand.startsWith("reloadspawns")) {
				Server.npcHandler = null;
				Server.npcHandler = new server.model.npcs.NPCHandler();
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("<shad=15695415>[" + c.playerName + "] " + "NPC Spawns have been reloaded.</col>");
					}
				}

			}
						if(playerCommand.startsWith("restart") && (c.playerName.equalsIgnoreCase("DarkSlayerz"))) {
	for(Player p : PlayerHandler.players) {
		if(p == null)
		         continue;	
		PlayerSave.saveGame((Client)p);
	}
System.exit(0);
	}

			if (playerCommand.startsWith("item")) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("That item ID does not exist.");
						}
					} else {
						c.sendMessage("Wrong usage: (Ex:(::pickup_ID_Amount)(::item 995 1))");
					}
				} catch(Exception e) {
					
				} // HERE?
				} // HERE?
			if (playerCommand.equalsIgnoreCase("switch")) {
			for (int i = 0; i < 8 ; i++){
				c.getItems().wearItem(c.playerItems[i]-1,i);
			}
                        c.sendMessage("Switching Armor");
		}
					if (playerCommand.equalsIgnoreCase("brid")) {
				c.getItems().deleteAllItems();
				int itemsToAdd[] = { 4151, 6585, 10551, 20072, 11732, 11726, 15220, 7462,
					2440, 2436, 3024};
					for (int i = 0; i < itemsToAdd.length; i++) {
				c.getItems().addItem(itemsToAdd[i], 1);
			}
			int[] equip = { 10828, 2414, 18335, 15486, 4712, 6889, -1, 4714, -1,
				 6922, -1, 6920, 15018};
			for (int i = 0; i < equip.length; i++) {
				c.playerEquipment[i] = equip[i];
				c.playerEquipmentN[i] = 1;
				c.getItems().setEquipment(equip[i], 1, i);
			}
				c.getItems().addItem(555, 1200);
				c.getItems().addItem(560, 800);
				c.getItems().addItem(565, 400);
				c.getItems().addItem(5698, 1);
				c.getItems().addItem(391, 13);
                                c.playerMagicBook = 1;
                                c.setSidebarInterface(6, 12855);
				c.getItems().resetItems(3214);
				c.getItems().resetBonus();
				c.getItems().getBonus();
				c.getItems().writeBonus();
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
		}
				if (playerCommand.equals("alltome")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
			c2.teleportToX = c.absX;
                        c2.teleportToY = c.absY;
                        c2.heightLevel = c.heightLevel;
				c2.sendMessage("Mass teleport to: " + c.playerName + "");
					}
				}
			}
			if (playerCommand.startsWith("setlevel")) {
try {
String[] args = playerCommand.split(" ");
int skill = Integer.parseInt(args[1]);
int level = Integer.parseInt(args[2]);
String otherplayer = args[3];
Client target = null;
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
target = (Client)Server.playerHandler.players[i];
break;
}
}
}
if (target == null) {
c.sendMessage("Player doesn't exist.");
return;
}
c.sendMessage("You have just set one of "+ Misc.ucFirst(target.playerName) +"'s skills.");
target.sendMessage(""+ Misc.ucFirst(c.playerName) +" has just set one of your skills."); 
target.playerXP[skill] = target.getPA().getXPForLevel(level)+5;
target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
target.getPA().refreshSkill(skill);
} catch(Exception e) {
c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
}            
}		
		if (playerCommand.startsWith("heal") && c.playerRights > 0) {
			if (playerCommand.indexOf(" ") > -1 && c.playerRights > 1) {
				String name = playerCommand.substring(5);
				if (c.validClient(name)) {
					Client p = c.getClient(name);
					for (int i = 0; i < 20; i++) {
						p.playerLevel[i] = p.getLevelForXP(p.playerXP[i]);
						p.getPA().refreshSkill(i);
					}
					p.sendMessage("You have been healed by " + c.playerName + ".");
				} else {
					c.sendMessage("Player must be offline.");
				}
			} else {
				for (int i = 0; i < 20; i++) {
					c.playerLevel[i] = c.getLevelForXP(c.playerXP[i]);
					c.getPA().refreshSkill(i);
				}
				c.freezeTimer = -1;
				c.frozenBy = -1;
				c.sendMessage("You have been healed.");
			}
		}
		if (playerCommand.startsWith("shop")) {
			try {
				c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
			} catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::shop 1");
			}
		}
		if (playerCommand.startsWith("fhome") && c.playerRights > 2) {
			String name = playerCommand.substring(6);
			if (c.validClient(name)) {
				Client p = c.getClient(name);
				p.getPA().movePlayer(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0); // Replace these coords to your home location
				c.sendMessage("You have forced " + p.playerName + " home.");
				p.sendMessage("You have been forced home by:" + c.playerName + ".");
			}
		}
		if (playerCommand.startsWith("copy")) {
			String name = playerCommand.substring(5);
			if (c.validClient(name)) {
				Client p = c.getClient(name);
				for(int i = 0; i < c.playerEquipment.length; i++)
					c.playerEquipment[i] = p.playerEquipment[i];
				for(int i = 0; i < c.playerAppearance.length; i++)
					c.playerAppearance[i] = p.playerAppearance[i];
				c.sendMessage("You have copied " + p.playerName + ".");
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		}
			if (playerCommand.equals("govote") && c.playerRights == 3) {
				for (int j = 0; j < Server.playerHandler.players.length; j++)
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.getPA().sendFrame126("www.erased-pkz.comyr.com/Vote/vote.php", 12000);
						c2.sendMessage("Please vote every day!");
					}
			}
			if (playerCommand.equals("download") && c.playerRights == 3) {
				for (int j = 0; j < Server.playerHandler.players.length; j++)
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.getPA().sendFrame126("www.mediafire.com/?n2e8vw9nwt5ak36", 12001);
						c2.sendMessage("enjoy newest client");
					}
			}
		if (playerCommand.startsWith("xcopy")) {
			String name = playerCommand.substring(6);
			if (c.validClient(name)) {
				Client p = c.getClient(name);
				for(int i = 0; i < c.playerEquipment.length; i++)
					p.playerEquipment[i] = c.playerEquipment[i];
				for(int i = 0; i < c.playerAppearance.length; i++)
					p.playerAppearance[i] = c.playerAppearance[i];
				c.sendMessage("You have xcopied " + p.playerName + ".");
				p.sendMessage("You have been xcopied by " + c.playerName + ".");
				p.updateRequired = true;
				p.appearanceUpdateRequired = true;
			}
		}
		if (playerCommand.startsWith("god")) {
			if (c.playerStandIndex != 1501) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}
		if (playerCommand.startsWith("fall")) {
			if (c.playerStandIndex != 2048) {
				c.startAnimation(2046);
				c.playerStandIndex = 2048;
				c.playerTurnIndex = 2048;
				c.playerWalkIndex = 2048;
				c.playerTurn180Index = 2048;
				c.playerTurn90CWIndex = 2048;
				c.playerTurn90CCWIndex = 2048;
				c.playerRunIndex = 2048;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			} else {
				c.startAnimation(2047);
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		}
		if (playerCommand.startsWith("demon")) {
			int id = 82+Misc.random(2);
			c.npcId2 = id;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.playerStandIndex = 66;
			c.playerTurnIndex = 66;
			c.playerWalkIndex = 63;
			c.playerTurn180Index = 66;
			c.playerTurn90CWIndex = 66;
			c.playerTurn90CCWIndex = 63;
			c.playerRunIndex = 63;
		}
		if (playerCommand.startsWith("brute")) {
			int id = 6102+Misc.random(2);
			c.npcId2 = id;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
				if (playerCommand.startsWith("checkinv")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 						c.getPA().otherInv(c, o);
											break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}
						if (playerCommand.equalsIgnoreCase("master")) {
				for (int i = 0; i < 22; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);	
				}
				c.getPA().requestUpdates();
			}
    
    }

    public void DonatorCommands(Client c, String playerCommand)
    {
        
}
}
