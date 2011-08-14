package server.model.npcs.combat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.util.Misc;
import server.world.map.VirtualWorld;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;

public class NpcEmotes {
	
	//Block Emote for an npc
	public static int getBlockEmote(int id) {
		switch(Server.npcHandler.npcs[id].npcType) {		
			case 1158:
			case 3835:
				return 6232;
				case 2037:
				return 5489;

			case 10127:
			return 13170;
			case 10057:
				
						return 10818;


					
			case 1160:
			case 3836:
				return 6237;
			case 2783:
				return 2732;
			case 8133: // corp beast
				return 10058;
			case 10141: // corp beast
				return 13601;
			case 8349: // torm demon
				return 10923;
case 9947:
return 13771;
				/**
				* God Wars List
				**/
			//bandos
			case 6260: //boss
				return 7061;
			case 6261:
			case 6263:
			case 6265:
				return 6155;
			//armadyl
			case 6222: //boss
				return 6974;
			case 6223:
			case 6225:
			case 6227:
				return 6952;
			//zammy
			case 6203:
				return 6944;
			case 6204:
			case 6206:
			case 6208:
				return 65;
			//Sara
			case 6247:
				return 6966;
			case 6248:
				return 6375;
			case 6250:
				return 7017;
			case 6252:
				return 4311;
				
			//armadyl npcs
			//spiritual mages
			case 6229:
			case 6230:
			case 6231:
			//aviansies
			case 6232:
			case 6233:
			case 6234:
			case 6235:
			case 6236:
			case 6237:
			case 6238:
			case 6239:
			case 6240:
			case 6241:
			case 6242:
			case 6243:
			case 6244:
			case 6245:
			case 6246:
				return 6952;
				
			//bandos npcs
			case 6267:
				return 360;
			case 6268:
				return 2933;
			case 6269:
			case 6270:
				return 4651;
			case 6271:
			case 6272:
			case 6273:
			case 6274:
				return 4322;
			case 6275:
				return 165;
			case 6276:
			case 6277:
			case 6278:
				return 4322;
			case 6279:
			case 6280:
				return 6183;
			case 6281:
				return 6136;
			case 6282:
				return 6189;
			case 6283:
				return 6183;
				
			//zamorak npcs
			case 6210:
				return 6578;
			case 6211:
				return 170;
			case 6212:
			case 6213:
				return 6538;
			case 6215:
				return 1550;
			case 6216:
			case 6217:
				return 1581;
			case 6218:
				return 4301;
				
			//sara npcs
			case 6258:
				return 2561;
			
			case 655:
			return 129;
case 10775:
return 13154;
			
			default:
                return 1834;//1834
				
		}
	}
	//Emote for dieing from a npc
	public static int getDeadEmote(int id) {
		switch(Server.npcHandler.npcs[id].npcType) {
			case 8133: // corp beast
				return 10059;
case 10141:
return 13602;
case 10127:
return 13171;
case 10057:
return 10815;
			case 8349: // torm demon
				return 10924;
			//sara gwd
			case 6247:
			return 6965;
case 10775:
return 13153;
case 9947:
return 13772;
			case 6248:
			return 6377;
			case 6250:
			return 7016;
			case 6252:
			return 7011;
			//bandos gwd
			case 6261:
			case 6263:
			case 6265:
			return 6156;
			case 6260:
			return 7062;
			case 2892:
			case 2894:
			return 2865;
			case 1612: //banshee
			return 1524;
			case 6203: //zammy gwd
			return 6946;
			case 6204:
			case 6206:
			case 6208:
			return 67;
			case 6222:
			return 6975;
			case 6223:
			case 6225:
			case 6227:
			return 6956;
			case 2607:
			return 2607;
			case 2627:
			return 2620;
			case 2630:
			return 2627;
			case 2631:
			return 2630;
			case 2738:
			return 2627;
			case 2741:
			return 2638;
			case 2746:
			return 2638;
			case 2743:
			return 2646;
			case 2745:
			return 2654;
			
			case 6142:
			case 6143:
			case 6144:
			case 6145:
			return -1;
			
			case 3200:
			return 3147;
			
			case 3847:
			return 3993;
			
			case 2035: //spider
			return 146;
			
			case 2033: //rat
			return 141;
			
			case 2031: // bloodvel
			return 2073;
			
			case 101: //goblin
			return 313;
			
			case 81: // cow
			return 0x03E;
			
			case 41: // chicken
			return 57;
			
			case 1338: // dagannoth
			case 1340:
			case 1342:
			return 1342;
			
			case 2881:
			case 2882:
			case 2883:
			return 2856;
			
			case 111: // ice giant
			return 131;
			
			case 125: // ice warrior
			return 843;
			
			case 751://Zombies!!
			return 302;
			
			case 1626:
            case 1627:
            case 1628:
            case 1629:
            case 1630:
            case 1631:
            case 1632: //turoth!
            return 1597;
			
			case 1616: //basilisk
            return 1548;
			
			case 1653: //hand
            return 1590;
			
			case 82://demons
			case 83:
			case 84:
			return 67;
			
			case 1605://abby spec
			return 1508;
			
			case 51://baby drags
			case 52:
			case 1589:
			case 3376:
			return 28;
			
			case 1610:
			case 1611:
			return 1518;
			
			case 1618:
			case 1619:
			return 1553;
			
			case 1620: case 1621:
			return 1563;
			
			case 2783:
			return 2733;
			
			case 1615:
			return 1538;
			
			case 1624:
			return 1558;
			
			case 1613:
			return 1530;
			
			case 1633: 
			case 1634:
			case 1635: 
			case 1636:
			return 1580;
			
			case 1648:
			case 1649: 
			case 1650: 
			case 1651: 
			case 1652: 
			case 1654: 
			case 1655:
			case 1656: 
			case 1657:
			return 1590;
			
			case 100: case 102:
			return 313;
			
			case 105:
			case 106:
			return 44;
			
			case 412:
			case 78:
			return 36;
			
			case 122:
			case 123:
			return 167;
			
			case 58:
			case 59:
			case 60: 
			case 61: 
			case 62:
			case 63: 
			case 64: 
			case 134:
			return 146;
			
			case 1153: 
			case 1154: 
			case 1155: 
			case 1156:
			case 1157:
			return 1190;
			
			case 103: 
			case 104:
			return 123;
			
			case 118: 
			case 119:
			return 102;
			
			case 3340:
			return 3310;
			
			
			case 50://drags
			case 53:
			case 54:

			case 55:
			case 941:
			case 1590:
			case 1591:
			case 1592:
			return 92;
			
			
			case 1158:
			case 3835:
				return 6242;
			case 1160:
			case 3836:
				return 6233;
			
			
			default:
			return 2304;
		}
	}
	//Npc Attack Emote
	public static int getAttackEmote(int i) {
		switch(Server.npcHandler.npcs[i].npcType) {
	
case 6795:
return 1010;



case 10775:
return 13151;

case 2037:
				return 5485;
				
case 6797:
return 8104;
	
case 6799:
return 8069;

case 6801:
return 7853;

case 6803:
return 8159;

case 6805:
return 7786;

case 6807:
return 8148;

case 6810:
return 7970;

case 6812:
return 7935;

case 6814:
return 7741;

case 6816:
return 8288;

case 6819:
return 7667;

case 6821:
return 7680;


case 6823:
return 6376;


case 6826:
return 5387;


case 6828:
return 8208;


case 6830:
return 8292;
case 6832:
return 7795;
case 6834:
return 8248;
case 6836:
return 8275;
case 6838:
return 6254;
case 6856:
return 4921;
case 6858:
return 5327;

case 6860:
case 6862:
case 6864:
return 7656;

case 6868:
return 7896;

case 6870:
return 8303;


case 6872:
return 7769;

case 6874:
return 5782;

case 6890:
return 7260;

case 7330:
return 8223;

case 7332:
return 8032;

case 7338:
return 5228;

case 7352:
return 8234;

case 7354:
return 7755;

case 7355:
return 7834;

case 7358:
return 7844;

case 7359:
return 8183;

case 7362:
return 8257;

case 7364:
case 7366:
return 5228;

case 7368:
case 7369:

return 8130;

case 7371:
return 8093;

case 7374: 
return 7994;

case 7376:
return 7946;


			case 6260:
				if (Server.npcHandler.npcs[i].attackType == 0)
					return 7060;
				else
					return 7063;
			///Kq
			case 1158:
			case 3835:
				if (Server.npcHandler.npcs[i].attackType == 0)
						return 6241;
					else
						return 6240;
			case 1160:
			case 3836:
				if (Server.npcHandler.npcs[i].attackType == 0)
						return 6235;
					else
						return 6234;
			
			case 2892:

			case 2894:
			return 2868;
			case 2627:
			return 2621;
			case 2630:
			return 2625;
			case 2631:
			return 2633;
			case 2741:
			return 2637;
			case 2746:
			return 2637;
			case 2607:
			return 2611;
			case 2743://360
			return 2647;
			
			//bandos gwd
			case 6261:
			case 6263:
			case 6265:
			return 6154;
			case 6267:
			return 361;
			case 6268:
			return 2930;
			case 6269:
			case 6270:
			return 4652;
			case 6271:
			case 6272:
			case 6273:
			case 6274:
			return 4320;
			case 6275:
			return 164;
			case 6276:
			case 6277:
			case 6278:
			return 4320;
			case 6279:
			case 6280:
			return 6184;
			case 6281:
			return 6134;
			case 6282:
			return 6188;
			case 6283:
			return 6184;
			
			//end of gwd
			//zammy gwd
			case 6203:
			return 6945;
			case 6204:
			case 6206:
			case 6208:
			return 64;
			case 6210:
			return 6581;
			case 6211:
			return 169;
			case 6212:
			case 6213:
			return 6536;
			case 6215:
			return 1552;
			case 6216:
			case 6217:
			return 1582;
			case 6218:
			return 4300;
			//end of zammy gwd
			//arma gwd
			case 6222:
			return 6973;
			case 6225:
			return 6953;
			case 6223:
			return 6954;
			case 6227:
			return 6953;
			//spiritual mages
			case 6229:
			case 6230:
			case 6231:
				return 6954;
			//aviansies
			case 6232:
			case 6233:
			case 6234:
			case 6235:
			case 6236:
			case 6237:
			case 6238:
			case 6239:
			case 6240:
			case 6241:
			case 6242:
			case 6243:
			case 6244:
			case 6245:
			case 6246:
				return 6953;
			//end of arma gwd
			
			//sara gwd
			case 6247:
				if (Server.npcHandler.npcs[i].attackType == 2)
						return 6967;
					else
						return 6964;


			case 10057:
				if (Server.npcHandler.npcs[i].attackType == 1)
						return 10817;
					else
						return 10816;

					
			case 6248:
			return 6376;
			case 6250:
			return 7018;
			case 6252:
			return 7009;
			//end of sara gwd
			
			case 13: //wizards
			return 711;
			
			case 103:
			return 123;
			
			case 1624:
			return 1557;
			
			case 1648:
			return 1590;
			
			case 2783: //dark beast
			return 2731;
			
			case 1615: //abby demon
			return 1537;
			
			case 1613: //nech
			return 1528;
			
			case 1610: case 1611: //garg
			return 1519;
			
			case 1616: //basilisk
			return 1546;
			
			case 90: //skele
			return 260;
			
			case 50://drags
			case 53:

			case 54:
			case 55:
			case 941:
			case 1590:
			case 1591:
			case 1592:
			return 80;
			
			case 124: //earth warrior
			return 390;
			
			case 803: //monk
			return 422;
			
			case 52: //baby drag
			return 25;			

			case 58: //Shadow Spider
            case 59: //Giant Spider
            case 60: //Giant Spider
            case 61: //Spider
            case 62: //Jungle Spider
            case 63: //Deadly Red Spider
            case 64: //Ice Spider
            case 134:
			return 143;	
			
			case 105: //Bear
            case 106:  //Bear
			return 41;
			
			case 412:
			case 78:
			return 30;
			
			case 2033: //rat
			return 138;	
			
			case 2031: // bloodworm
			return 2070;
			
			case 101: // goblin
			return 309;	
			
			case 81: // cow
			return 0x03B;
			
			case 21: // hero
			return 451;	
			
			case 41: // chicken
			return 55;	
			
			case 9: // guard
			case 32: // guard
			case 20: // paladin
			return 451;	
			
			case 1338: // dagannoth
			case 1340:
			case 1342:
			return 1341;
		
			case 19: // white knight
			return 406;
			
			case 110:
			case 111: // ice giant
			case 112:
			case 117:
			return 128;
			
			case 2452:
			return 1312;
			
			case 2889:
			return 2859;
			
			case 118:
			case 119:
			return 99;
			
			case 82://Lesser Demon
            case 83://Greater Demon
            case 84://Black Demon
            case 1472://jungle demon
			return 64;
			
			case 1267:
			case 1265:
			return 1312;
			
			case 125: // ice warrior
			case 178:
			return 451;
			
			case 1153: //Kalphite Worker
            case 1154: //Kalphite Soldier
            case 1155: //Kalphite guardian
            case 1156: //Kalphite worker
            case 1157: //Kalphite guardian
			return 1184;
			
			case 123:
			case 122:
			return 164;




case 7334:
return 8172;
case 7336:
return 7871;
case 5228:
return 5228;

case 7340:
return 7879;

case 7342:
return 7879;

case 7344:
return 8183;

case 7346:
return 8048;

case 7348:
return 5989;


case 7350:
return 7693;

			
			case 2028: // karil
			return 2075;
					
			case 2025: // ahrim
			return 729;
			
			case 2026: // dharok
			return 2067;
			
			case 2027: // guthan
			return 2080;
			
			case 2029: // torag
			return 0x814;
			
			case 2030: // verac
			return 2062;
			
			case 2881: //supreme
			return 2855;
			
			case 2882: //prime
			return 2854;
			
			case 2883: //rex
			return 2851;
			
			case 3340: // giant mole test
			return 3312;
			
			case 3200:
			return 3146;
			
			case 3847:
			if (Server.npcHandler.npcs[i].attackType == 2)
			return 3992;
			if (Server.npcHandler.npcs[i].attackType == 1)
			return 3992;
			
			case 8349://tormented demon
				if (Server.npcHandler.npcs[i].attackType == 2)
					return 10917;
				else if (Server.npcHandler.npcs[i].attackType == 1)
					return 10918;
				else if (Server.npcHandler.npcs[i].attackType == 0)
					return 10922;



			
					
			case 8133://corp beast
				if (Server.npcHandler.npcs[i].attackType == 2)
					return 10053;
				else if (Server.npcHandler.npcs[i].attackType == 1)
					return 10059;
				else if (Server.npcHandler.npcs[i].attackType == 0)
					return 10057;
case 10127:
if (Server.npcHandler.npcs[i].attackType == 2)
					return 13176;
				else if (Server.npcHandler.npcs[i].attackType == 0)
					return 13169;

case 9947:
if (Server.npcHandler.npcs[i].attackType == 2)
					return 13770;
				else if (Server.npcHandler.npcs[i].attackType == 0)
					return 13771;
					
case 10141://corp beast
				if (Server.npcHandler.npcs[i].attackType == 2)
					return 10053;
				else if (Server.npcHandler.npcs[i].attackType == 0)
					return 13599;
				else if (Server.npcHandler.npcs[i].attackType == 1)
					return 13603;

			
			case 2745:
			if (Server.npcHandler.npcs[i].attackType == 2)
			return 9300;
			else if (Server.npcHandler.npcs[i].attackType == 1)
			return 9276;
			else if (Server.npcHandler.npcs[i].attackType == 0)
			return 9277;
			case 655:
			return 129;
			
			default:
			return 0x326;		
		}
	}
}

