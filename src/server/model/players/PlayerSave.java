package server.model.players;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import server.Server;
import server.util.Misc;

public class PlayerSave
{

	
	
	/**
	*Loading
	**/
	public static int loadGame(Client p, String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		boolean File1 = false;
		
		try {
			characterfile = new BufferedReader(new FileReader("./Data/characters/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		
		if (File1) {
			//new File ("./characters/"+playerName+".txt");
		} else {
			Misc.println(playerName+": character file not found.");
			p.newPlayer = false;
			return 0;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					 if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2) || Misc.basicEncrypt(playerPass).equals(token2)) {
							playerPass = token2;
						} else {
							return 3;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						p.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						p.teleportToX = (Integer.parseInt(token2) <= 0 ? 3210 : Integer.parseInt(token2));
					} else if (token.equals("character-posy")) {
						p.teleportToY = (Integer.parseInt(token2) <= 0 ? 3424 : Integer.parseInt(token2));
					} else if (token.equals("character-rights")) {
						p.playerRights = Integer.parseInt(token2);
                    			} else if (token.equals("Used-Puremaster")) {
                        			p.pure = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						p.playerEnergy = Integer.parseInt(token2);
					} else if(token.equals("dt-kill")) {
						p.lastDtKill = Integer.parseInt(token2);
					} else if(token.equals("desert-treasure")) {
						p.desertT = Integer.parseInt(token2);
					} else if (token.equals("quest-points")) {
						p.questPoints = Integer.parseInt(token2);
					} else if (token.equals("tutorial-progress")) {
						p.tutorial = Integer.parseInt(token2);	
					} else if (token.equals("crystal-bow-shots")) {
						p.crystalBowArrowCount = Integer.parseInt(token2);
					} else if (token.equals("skull-timer")) {
						p.skullTimer = Integer.parseInt(token2);
					} else if (token.equals("EP")) {
						p.earningPotential = Integer.parseInt(token2);
                   } else if (token.equals("character-longsword")) {
                        p.vlsLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-warhammer")) {
                        p.statLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-spear")) {
                        p.vSpearLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-chainbody")) {
                        p.vTopLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-chainskirt")) {
                        p.vLegsLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-full helm")) {
                        p.sHelmLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-platebody")) {
                        p.sTopLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-platelegs")) {
                        p.sLegsLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-hood")) {
                        p.zHoodLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-staff")) {
                        p.zStaffLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-robe top")) {
                        p.zTopLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-robe bottom")) {
                        p.zBottomLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-leather body")) {
                        p.mBodyLeft = Integer.parseInt(token2);
                    } else if (token.equals("character-chaps")) {
                        p.mChapsLeft = Integer.parseInt(token2);
					} else if (token.equals("magic-book")) {
						p.playerMagicBook = Integer.parseInt(token2);
					} else if (token.equals("xpLock")) {
						p.xpLock = Boolean.parseBoolean(token2);
					} else if (token.equals("Jailed")) {
						p.Jail = Boolean.parseBoolean(token2);
					} else if (token.equals("Agrith")) {
						p.Agrith = Boolean.parseBoolean(token2);
					} else if (token.equals("degrade")) {
					        p.degradeTime = Integer.parseInt(token2); 
					} else if (token.equals("Flambeed")) {
						p.Flambeed = Boolean.parseBoolean(token2);
					} else if (token.equals("Karamel")) {
						p.Karamel = Boolean.parseBoolean(token2);
					} else if (token.equals("Dessourt")) {
						p.Dessourt = Boolean.parseBoolean(token2);
					} else if (token.equals("culin")) {
						p.Culin = Boolean.parseBoolean(token2);
					} else if (token.equals("Monkey-Kc")) {
						p.monkeyk0ed = Integer.parseInt(token2);
					} else if (token.equals("cw-games")) {
                            			p.cwGames = Integer.parseInt(token2);
					} else if (token.equals("brother-info")) {
						p.barrowsNpcs[Integer.parseInt(token3[0])][1] = Integer.parseInt(token3[1]);
					 } else if (token.equals("special-amount")) {
						p.specAmount = Double.parseDouble(token2);	
					 } else if (token.equals("selected-coffin")) {
						p.randomCoffin = Integer.parseInt(token2);	
					} else if (token.equals("barrows-killcount")) {
						p.pkPoints = Integer.parseInt(token2);							
					} else if (token.equals("teleblock-length")) {
						p.teleBlockDelay = System.currentTimeMillis();
						p.teleBlockLength = Integer.parseInt(token2);							
					} else if (token.equals("pc-points")) {
						p.pcPoints = Integer.parseInt(token2);	
					} else if (token.equals("gwdelay")) {
						p.gwdelay = Integer.parseInt(token2);
					} else if (token.equals("Altar")) {
						p.altarPrayed = Integer.parseInt(token2);
					} else if (token.equals("Arma-KC")) {
						p.Arma = Integer.parseInt(token2);	
					} else if (token.equals("Band-KC")) {
						p.Band = Integer.parseInt(token2);	
					} else if (token.equals("Zammy-KC")) {
						p.Zammy = Integer.parseInt(token2);	
					} else if (token.equals("Sara-KC")) {
						p.Sara = Integer.parseInt(token2);	
					} else if (token.equals("pk-points")) {
						p.pkPoints = Integer.parseInt(token2);	
					} else if (token.equals("isDonator")) {
						p.isDonator = Integer.parseInt(token2);
					} else if (token.equals("donatorChest")) {
						p.donatorChest = Integer.parseInt(token2);	
					} else if (token.equals("slayerPoints")) {
						p.slayerPoints = Integer.parseInt(token2);						
					} else if (token.equals("slayerTask")) {
						p.slayerTask = Integer.parseInt(token2);					
					} else if (token.equals("taskAmount")) {
						p.taskAmount = Integer.parseInt(token2);					
					} else if (token.equals("magePoints")) {
						p.magePoints = Integer.parseInt(token2);
} else if (line.startsWith("KC")) {
						p.KC = Integer.parseInt(token2);
					} else if (line.startsWith("DC")) {
						p.DC = Integer.parseInt(token2);
					} else if (line.startsWith("totalstored")) {
						p.totalstored = Integer.parseInt(token2);
					} else if (token.equals("autoRet")) {
						p.autoRet = Integer.parseInt(token2);
					} else if (token.equals("trade11")) {
						p.trade11 = Integer.parseInt(token2);
					} else if (token.equals("SpeDelay")) {
						p.SpecialDelay = Integer.parseInt(token2);
					} else if (token.equals("barrowskillcount")) {
						p.barrowsKillCount = Integer.parseInt(token2);
					} else if (token.equals("flagged")) {
						p.accountFlagged = Boolean.parseBoolean(token2);
					} else if (token.equals("Rules")) {
						p.readRules = Boolean.parseBoolean(token2);
											} else if (token.equals("isShopping")) {
						p.isShopping = Boolean.parseBoolean(token2);
							} else if (token.equals("SPoints")) {
						p.SPoints = Integer.parseInt(token2);
					} else if (token.equals("wave")) {
						p.waveId = Integer.parseInt(token2);
} else if (token.equals("dfs-charges")) {
						p.dfsCount = Integer.parseInt(token2);
					} else if (token.equals("lastsummon")) {
						p.lastsummon = Integer.parseInt(token2);

					} else if (token.equals("summoningnpcid")) {
						p.summoningnpcid = Integer.parseInt(token2);

					} else if (token.equals("void")) {
						for (int j = 0; j < token3.length; j++) {
							p.voidStatus[j] = Integer.parseInt(token3[j]);						
						}
					} else if (token.equals("fightMode")) {
						p.fightMode = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						p.playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						p.playerAppearance[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					} 
					break;
				case 5:
					if (token.equals("character-skill")) {
						p.playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						p.playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						p.bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					 if (token.equals("character-friend")) {
						p.friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					} 
					break;
				case 9:
					/* if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					} */
					break;
				case 20:
					if (token.equals("stored")) {
						p.storeditems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;

				case 21:
					 if (token.equals("occupy")) {
						p.occupied[Integer.parseInt(token3[0])] = Boolean.parseBoolean(token3[1]);
					} 
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
} else if (line.equals("[STORED]")) {		ReadMode = 20;
} else if (line.equals("[OCCUPY]")) {		ReadMode = 21;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 13;
	}
	
	
	
	
	/**
	*Saving
	**/
	public static boolean saveGame(Client p) {
		if(!p.saveFile || p.newPlayer || !p.saveCharacter) {
			//System.out.println("first");
			return false;
		}
		if(p.playerName == null || Server.playerHandler.players[p.playerId] == null) {
			//System.out.println("second");
			return false;
		}
		p.playerName = p.playerName2;
		int tbTime = (int)(p.teleBlockDelay - System.currentTimeMillis() + p.teleBlockLength);
		if(tbTime > 300000 || tbTime < 0){
			tbTime = 0;
		}
		
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./Data/characters/"+p.playerName+".txt"));
			
			/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(p.playerName, 0, p.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(p.playerPass, 0, p.playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			
			/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(p.heightLevel), 0, Integer.toString(p.heightLevel).length());
			characterfile.newLine();

			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(p.absX), 0, Integer.toString(p.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(p.absY), 0, Integer.toString(p.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(p.playerRights), 0, Integer.toString(p.playerRights).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(p.playerEnergy), 0, Integer.toString(p.playerEnergy).length());
			characterfile.newLine();
			characterfile.write("crystal-bow-shots = ", 0, 20);
			characterfile.write(Integer.toString(p.crystalBowArrowCount), 0, Integer.toString(p.crystalBowArrowCount).length());
			characterfile.newLine();
			characterfile.write("VLS-hits = ", 0, 11);
			characterfile.write(Integer.toString(p.degradeTime), 0, Integer.toString(p.degradeTime).length());
			characterfile.newLine(); 
			characterfile.write("skull-timer = ", 0, 14);
			characterfile.write(Integer.toString(p.skullTimer), 0, Integer.toString(p.skullTimer).length());
			characterfile.newLine();
			characterfile.write("EP = ", 0, 5);
			characterfile.write(Integer.toString(p.earningPotential), 0, Integer.toString(p.earningPotential).length());
			characterfile.newLine();
                 characterfile.write("character-longsword = ", 0, 22);
                  characterfile.write(Integer.toString(p.vlsLeft), 0, Integer.toString(p.vlsLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-warhammer = ", 0, 22);
                  characterfile.write(Integer.toString(p.statLeft), 0, Integer.toString(p.statLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-spear = ", 0, 18);
                  characterfile.write(Integer.toString(p.vSpearLeft), 0, Integer.toString(p.vSpearLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-chainbody = ", 0, 22);
                  characterfile.write(Integer.toString(p.vTopLeft), 0, Integer.toString(p.vTopLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-chainskirt = ", 0, 23);
                  characterfile.write(Integer.toString(p.vLegsLeft), 0, Integer.toString(p.vLegsLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-full helm = ", 0, 22);
                  characterfile.write(Integer.toString(p.sHelmLeft), 0, Integer.toString(p.sHelmLeft).length());
                  characterfile.newLine();
			characterfile.write("desert-treasure = ", 0, 18);
			characterfile.write(Integer.toString(p.desertT), 0, Integer.toString(p.desertT).length());
			characterfile.newLine();
			characterfile.write("dt-kill = ", 0, 10);
			characterfile.write(Integer.toString(p.lastDtKill), 0, Integer.toString(p.lastDtKill).length());
			characterfile.newLine();
			characterfile.write("quest-points = ", 0, 15);
			characterfile.write(Integer.toString(p.questPoints), 0, Integer.toString(p.questPoints).length());
			characterfile.newLine();
                  characterfile.write("character-platebody = ", 0, 22);
                  characterfile.write(Integer.toString(p.sTopLeft), 0, Integer.toString(p.sTopLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-platelegs = ", 0, 22);
                  characterfile.write(Integer.toString(p.sLegsLeft), 0, Integer.toString(p.sLegsLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-hood = ", 0, 17);
                  characterfile.write(Integer.toString(p.zHoodLeft), 0, Integer.toString(p.zHoodLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-staff = ", 0, 18);
                  characterfile.write(Integer.toString(p.zStaffLeft), 0, Integer.toString(p.zStaffLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-robe top = ", 0, 21);
                  characterfile.write(Integer.toString(p.zTopLeft), 0, Integer.toString(p.zTopLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-robe bottom = ", 0, 24);
                  characterfile.write(Integer.toString(p.zBottomLeft), 0, Integer.toString(p.zBottomLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-leather body = ", 0, 25);
                  characterfile.write(Integer.toString(p.mBodyLeft), 0, Integer.toString(p.mBodyLeft).length());
                  characterfile.newLine();
                  characterfile.write("character-chaps = ", 0, 18);
                  characterfile.write(Integer.toString(p.mChapsLeft), 0, Integer.toString(p.mChapsLeft).length());
                  characterfile.newLine();
			characterfile.write("magic-book = ", 0, 13);
			characterfile.write(Integer.toString(p.playerMagicBook), 0, Integer.toString(p.playerMagicBook).length());
			characterfile.newLine();
			for (int b = 0; b < p.barrowsNpcs.length; b++) {
				characterfile.write("brother-info = ", 0, 15);
				characterfile.write(Integer.toString(b), 0, Integer.toString(b).length());
				characterfile.write("	", 0, 1);
				characterfile.write(p.barrowsNpcs[b][1] <= 1 ? Integer.toString(0) : Integer.toString(p.barrowsNpcs[b][1]), 0, Integer.toString(p.barrowsNpcs[b][1]).length());
				characterfile.newLine();
			}	
			characterfile.write("special-amount = ", 0, 17);
			characterfile.write(Double.toString(p.specAmount), 0, Double.toString(p.specAmount).length());
			characterfile.newLine();
			characterfile.write("selected-coffin = ", 0, 18);
			characterfile.write(Integer.toString(p.randomCoffin), 0, Integer.toString(p.randomCoffin).length());
			characterfile.newLine();
			characterfile.write("barrows-killcount = ", 0, 20);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0, Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			characterfile.write("teleblock-length = ", 0, 19);
			characterfile.write(Integer.toString(tbTime), 0, Integer.toString(tbTime).length());
			characterfile.newLine();
			characterfile.write("pc-points = ", 0, 12);
			characterfile.write(Integer.toString(p.pcPoints), 0, Integer.toString(p.pcPoints).length());
			characterfile.newLine();
			characterfile.write("gwdelay = ", 0, 10);
			characterfile.write(Integer.toString(p.gwdelay), 0, Integer.toString(p.gwdelay).length());
			characterfile.newLine();
			characterfile.write("Altar = ", 0, 8);
			characterfile.write(Integer.toString(p.altarPrayed), 0, Integer.toString(p.altarPrayed).length());
			characterfile.newLine();
			characterfile.write("Arma-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Arma), 0, Integer.toString(p.Arma).length());
			characterfile.newLine();
			characterfile.write("Band-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Band), 0, Integer.toString(p.Band).length());
			characterfile.newLine();
			characterfile.write("Zammy-KC = ", 0, 11);
			characterfile.write(Integer.toString(p.Zammy), 0, Integer.toString(p.Zammy).length());
			characterfile.newLine();
			characterfile.write("Sara-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Sara), 0, Integer.toString(p.Sara).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("pk-points = ", 0, 12);
			characterfile.write(Integer.toString(p.pkPoints), 0, Integer.toString(p.pkPoints).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("isDonator = ", 0, 12);
			characterfile.write(Integer.toString(p.isDonator), 0, Integer.toString(p.isDonator).length());
			characterfile.newLine();
			characterfile.write("donatorChest = ", 0, 15);
			characterfile.write(Integer.toString(p.donatorChest), 0, Integer.toString(p.donatorChest).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("slayerPoints = ", 0, 15);
			characterfile.write(Integer.toString(p.slayerPoints), 0, Integer.toString(p.slayerPoints).length());
			characterfile.newLine();
			characterfile.write("slayerTask = ", 0, 13);
			characterfile.write(Integer.toString(p.slayerTask), 0, Integer.toString(p.slayerTask).length());
			characterfile.newLine();
			characterfile.write("xpLock = ", 0, 9);
			characterfile.write(Boolean.toString(p.xpLock), 0, Boolean.toString(p.xpLock).length());
			characterfile.newLine();
			characterfile.write("Agrith = ", 0, 9);
			characterfile.write(Boolean.toString(p.Agrith), 0, Boolean.toString(p.Agrith).length());
			characterfile.newLine();
			characterfile.write("Flambeed = ", 0, 11);
			characterfile.write(Boolean.toString(p.Flambeed), 0, Boolean.toString(p.Flambeed).length());
			characterfile.newLine();
			characterfile.write("Karamel = ", 0, 10);
			characterfile.write(Boolean.toString(p.Karamel), 0, Boolean.toString(p.Karamel).length());
			characterfile.newLine();
			characterfile.write("Dessourt = ", 0, 11);
			characterfile.write(Boolean.toString(p.Dessourt), 0, Boolean.toString(p.Dessourt).length());
			characterfile.newLine();
			characterfile.write("culin = ", 0, 8);
			characterfile.write(Boolean.toString(p.Culin), 0, Boolean.toString(p.Culin).length());
			characterfile.newLine();
			characterfile.write("taskAmount = ", 0, 13);
			characterfile.write(Integer.toString(p.taskAmount), 0, Integer.toString(p.taskAmount).length());
			characterfile.newLine();
			characterfile.write("magePoints = ", 0, 13);
			characterfile.write(Integer.toString(p.magePoints), 0, Integer.toString(p.magePoints).length());
			characterfile.newLine();
			characterfile.write("KC = ", 0, 4);
			characterfile.write(Integer.toString(p.KC), 0, Integer.toString(p.KC).length());
			characterfile.newLine();
			characterfile.write("DC = ", 0, 4);
			characterfile.write(Integer.toString(p.DC), 0, Integer.toString(p.DC).length());
			characterfile.newLine();
			characterfile.write("totalstored = ", 0, 14);
			characterfile.write(Integer.toString(p.totalstored), 0, Integer.toString(p.totalstored).length());
			characterfile.newLine();
			characterfile.write("autoRet = ", 0, 10);
			characterfile.write(Integer.toString(p.autoRet), 0, Integer.toString(p.autoRet).length());
			characterfile.newLine();
			characterfile.write("trade11 = ", 0, 10);
			characterfile.write(Integer.toString(p.trade11), 0, Integer.toString(p.trade11).length());
			characterfile.newLine();
			characterfile.write("SpeDelay = ", 0, 11);
			characterfile.write(Long.toString(p.SpecialDelay), 0, Long.toString(p.SpecialDelay).length());
			characterfile.newLine();
			characterfile.write("barrowskillcount = ", 0, 19);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0, Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			characterfile.write("flagged = ", 0, 10);
			characterfile.write(Boolean.toString(p.accountFlagged), 0, Boolean.toString(p.accountFlagged).length());
			characterfile.newLine();
			characterfile.write("Rules = ", 0, 8);
			characterfile.write(Boolean.toString(p.readRules), 0, Boolean.toString(p.readRules).length());
			characterfile.newLine();
			characterfile.write("shopping = ", 0, 11);
			characterfile.write(Boolean.toString(p.isShopping), 0, Boolean.toString(p.isShopping).length());
			characterfile.newLine();
			characterfile.write("Jailed = ", 0, 9);
			characterfile.write(Boolean.toString(p.Jail), 0, Boolean.toString(p.Jail).length());
			characterfile.newLine();
			characterfile.write("wave = ", 0, 7);
			characterfile.write(Integer.toString(p.waveId), 0, Integer.toString(p.waveId).length());
			characterfile.newLine();
			characterfile.write("dfs-charges = ", 0, 14);
			characterfile.write(Integer.toString(p.dfsCount), 0, Integer.toString(p.dfsCount).length());
			characterfile.newLine();
			characterfile.write("lastsummon = ", 0, 13);
			characterfile.write(Integer.toString(p.lastsummon), 0, Integer.toString(p.lastsummon).length());
			characterfile.newLine();
			characterfile.write("summoningnpcid = ", 0, 17);
			characterfile.write(Integer.toString(p.summoningnpcid), 0, Integer.toString(p.summoningnpcid).length());
			characterfile.newLine();
			characterfile.write("fightMode = ", 0, 12);
			characterfile.write(Integer.toString(p.fightMode), 0, Integer.toString(p.fightMode).length());
			characterfile.newLine();
			characterfile.write("SPoints = ", 0, 10);
			characterfile.write(Integer.toString(p.SPoints), 0, Integer.toString(p.SPoints).length());
			characterfile.newLine();
			characterfile.write("Monkey-Kc = ", 0, 12);
			characterfile.write(Integer.toString(p.monkeyk0ed), 0, Integer.toString(p.monkeyk0ed).length());
			characterfile.newLine();
            		characterfile.write("cw-games = ", 0, 11);
            		characterfile.write(Integer.toString(p.cwGames), 0, Integer.toString(p.cwGames).length());
            		characterfile.newLine();
			characterfile.write("void = ", 0, 7);
			String toWrite = p.voidStatus[0] + "\t" + p.voidStatus[1] + "\t" + p.voidStatus[2] + "\t" + p.voidStatus[3] + "\t" + p.voidStatus[4];
			characterfile.write(toWrite);
			characterfile.newLine();
			characterfile.newLine();
			
			/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < p.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipment[i]), 0, Integer.toString(p.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipmentN[i]), 0, Integer.toString(p.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			
			/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.playerAppearance.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerAppearance[i]), 0, Integer.toString(p.playerAppearance[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			
			/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerLevel[i]), 0, Integer.toString(p.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerXP[i]), 0, Integer.toString(p.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			
			/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < p.playerItems.length; i++) {
				if (p.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItems[i]), 0, Integer.toString(p.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItemsN[i]), 0, Integer.toString(p.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			
		/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.bankItems.length; i++) {
				if (p.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems[i]), 0, Integer.toString(p.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItemsN[i]), 0, Integer.toString(p.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			
		/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < p.friends.length; i++) {
				if (p.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write("" + p.friends[i]);
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			
		/*Storeditems*/
			characterfile.write("[STORED]", 0, 8);
			characterfile.newLine();
for (int i = 0; i < p.storeditems.length; i++) {
					characterfile.write("stored = ", 0, 9);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
characterfile.write(Integer.toString(p.storeditems[i]), 0, Integer.toString(p.storeditems[i]).length());
					characterfile.newLine();
			}
characterfile.newLine();

/*Storeditems*/
			characterfile.write("[OCCUPY]", 0, 8);
			characterfile.newLine();
for (int i = 0; i < p.occupied.length; i++) {
					characterfile.write("occupy = ", 0, 9);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
characterfile.write(Boolean.toString(p.occupied[i]), 0, Boolean.toString(p.occupied[i]).length());
					characterfile.newLine();
			}
characterfile.newLine();
			
		/*IGNORES*/
			/*characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();*/
		/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			Misc.println(p.playerName+": error writing file.");
			return false;
		}
		return true;
	}	
	

}