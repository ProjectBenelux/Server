import java.io.*;

public class ItemDef_2  {
	///Uncomment if you disable the 600+ items
	/*public static void Items(int i) {
		ItemDef itemDef = ItemDef.forID(i);
		switch(i) {
			case 15000:   
				itemDef.name = "Arcane Sigil";
				itemDef.actions = new String[5];
				itemDef.modelID = 40914;
				itemDef.modelZoom = 789;
				itemDef.modelRotationY = 225;
				itemDef.modelRotationX = 186;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffset2 = -10;
				itemDef.anInt165 = -1;
				itemDef.anInt200 = -1;
				itemDef.anInt188 = -1;
			itemDef.description = "A sigil in the shape of an arcane rune".getBytes();
			break;
			case 15001:
				itemDef.name = "Divine Sigil";
				itemDef.actions = new String[5];
				itemDef.modelID = 40916;
				itemDef.modelZoom = 848;
				itemDef.modelRotationY = 267;
				itemDef.modelRotationX = 138;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffset2 = 0;
				itemDef.anInt165 = -1;
				itemDef.anInt200 = -1;
				itemDef.anInt188 = -1;
			itemDef.description = "A sigil in the shape of an Divine symbol.".getBytes();
			break;
			case 15002:
				itemDef.name = "Elysian Sigil";
				itemDef.actions = new String[5];
				itemDef.modelID = 40917;
				itemDef.modelZoom = 976;
				itemDef.modelRotationY = 288;
				itemDef.modelRotationX = 225;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffset2 = 0;
				itemDef.anInt165 = -1;
				itemDef.anInt200 = -1;
				itemDef.anInt188 = -1;
			itemDef.description = "A sigil marked with elysian signs".getBytes();
			break;
			case 15003:
				itemDef.name = "Spectral Sigil";
				itemDef.actions = new String[5];
				itemDef.modelID = 40918;
				itemDef.modelZoom = 976;
				itemDef.modelRotationY = 267;
				itemDef.modelRotationX = 1304;
				itemDef.modelOffset1 = -5;
				itemDef.modelOffset2 = 0;
				itemDef.anInt165 = -1;
				itemDef.anInt200 = -1;
				itemDef.anInt188 = -1;
			itemDef.description = "A sigil tempered with special powers.".getBytes();
			break;
			case 15004:
				itemDef.name = "Vesta's Chainbody";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42593;
				itemDef.modelZoom = 1440;
				itemDef.modelRotationY = 545;
				itemDef.modelRotationX = 2;
				itemDef.modelOffset2 = 5;
				itemDef.modelOffset1 = 4;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42624;
				itemDef.anInt200 = 42644;
			itemDef.description = "An ancient chainbody once worn by Vesta.".getBytes();
			break;
			case 15005:
				itemDef.name = "Vesta's Plateskirt";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42581;
				itemDef.modelZoom = 1753;
				itemDef.modelRotationY = 562;
				itemDef.modelRotationX = 1;
				itemDef.modelOffset2 = 11;
				itemDef.modelOffset1 = -3;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42633;
				itemDef.anInt200 = 42647;
			itemDef.description = "An ancient plateskirt once worn by Vesta.".getBytes();
			break;
			case 15006:
				itemDef.name = "Vesta's Longsword";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Equip";
				itemDef.modelID = 42597;
				itemDef.modelZoom = 1744;
				itemDef.modelRotationY = 738;
				itemDef.modelRotationX = 1985;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42615;
				itemDef.anInt200 = 42615;
			itemDef.description = "An ancient longsword once worn by Vesta.".getBytes();
			break;
			case 15007:
				itemDef.name = "Vesta's Spear";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Equip";
				itemDef.modelID = 42599;
				itemDef.modelZoom = 2022;
				itemDef.modelRotationY = 480;
				itemDef.modelRotationX = 15;
				itemDef.modelOffset2 = 5;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42614;
				itemDef.anInt200 = 42614;
			itemDef.description = "An ancient spear once worn by Vesta.".getBytes();
			break;
			case 15008:
				itemDef.name = "Zuriel's Robe Top";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42591;
				itemDef.modelZoom = 1373;
				itemDef.modelRotationY = 373;
				itemDef.modelRotationX = 0;
				itemDef.modelOffset2 = -7;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42627;
				itemDef.anInt200 = 42642;
			itemDef.description = "An ancient robe once worn by Zuriel.".getBytes();
			break;
			case 15009:
				itemDef.name = "Zuriel's Robe Bottom";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42588;
				itemDef.modelZoom = 1697;
				itemDef.modelRotationY = 512;
				itemDef.modelRotationX = 0;
				itemDef.modelOffset2 = -9;
				itemDef.modelOffset1 = 2;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42634;
				itemDef.anInt200 = 42645;
			itemDef.description = "An ancient robe once worn by Zuriel.".getBytes();
			break;
			case 15010:
				itemDef.name = "Zuriel's Staff";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Equip";
				itemDef.modelID = 42595;
				itemDef.modelZoom = 2000;
				itemDef.modelRotationY = 366;
				itemDef.modelRotationX = 3;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42617;
				itemDef.anInt200 = 42617;
			itemDef.description = "An ancient staff once worn by Zuriel.".getBytes();
			break;
			case 15011:
				itemDef.name = "Zuriel's Hood";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42604;
				itemDef.modelZoom = 720;
				itemDef.modelRotationY = 28;
				itemDef.modelRotationX = 0;
				itemDef.modelOffset2 = 1;
				itemDef.modelOffset1 = 1;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42638;
				itemDef.anInt200 = 42653;
			itemDef.description = "An ancient hood once worn by Zuriel.".getBytes();
			break;
			case 15012:
				itemDef.name = "Morrigan's leather body";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42578;
				itemDef.modelZoom = 1184;
				itemDef.modelRotationY = 545;
				itemDef.modelRotationX = 2;
				itemDef.modelOffset2 = 5;
				itemDef.modelOffset1 = 4;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42626;
				itemDef.anInt200 = 42643;
			itemDef.description = "An ancient leather body once used by Morrigan.".getBytes();
			break;
			case 15013:
				itemDef.name = "Morrigan's Leather Chaps";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42603;
				itemDef.modelZoom = 1753;
				itemDef.modelRotationY = 482;
				itemDef.modelRotationX = 1;
				itemDef.modelOffset2 = 11;
				itemDef.modelOffset1 = -3;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42631;
				itemDef.anInt200 = 42646;
			itemDef.description = "A pair of ancient leather chaps once used by Morrigan.".getBytes();
			break;
			case 15014:
				itemDef.name = "Morrigan's Coif";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42583;
				itemDef.modelZoom = 592;
				itemDef.modelRotationY = 537;
				itemDef.modelRotationX = 5;
				itemDef.modelOffset2 = 6;
				itemDef.modelOffset1 = -3;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42636;
				itemDef.anInt200 = 42652;
			itemDef.description = "An ancient coif once used by Morrigan.".getBytes();
			break;
			case 15015:
				itemDef.name = "Morrigan's Javelin";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 42592;
				itemDef.modelZoom = 1872;
				itemDef.modelRotationY = 282;
				itemDef.modelRotationX = 2009;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42613;
				itemDef.anInt200 = 42613;
			itemDef.description = "An ancient javelin once used by Morrigan.".getBytes();
			break;
			case 15016:
				itemDef.name = "Morrigan's Throwing Axe";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 42582;
				itemDef.modelZoom = 976;
				itemDef.modelRotationY = 672;
				itemDef.modelRotationX = 2024;
				itemDef.modelOffset2 = 4;
				itemDef.modelOffset1 = -5;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42611;
				itemDef.anInt200 = 42611;
			itemDef.description = "An ancient throwing axe once used by Morrigan.".getBytes();
			break;
			case 15017:
				itemDef.name = "Statius's Platebody";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42602;
				itemDef.modelZoom = 1312;
				itemDef.modelRotationY = 272;
				itemDef.modelRotationX = 2047;
				itemDef.modelOffset2 = 39;
				itemDef.modelOffset1 = -2;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42625;
				itemDef.anInt200 = 42641;
			itemDef.description = "An ancient platebody once worn by Statius.".getBytes();
			break;
			case 15018:
				itemDef.name = "Statius's Platelegs";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42590;
				itemDef.modelZoom = 1625;
				itemDef.modelRotationY = 355;
				itemDef.modelRotationX = 2046;
				itemDef.modelOffset2 = -11;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42632;
				itemDef.anInt200 = 42649;
			itemDef.description = "An ancient platelegs once worn by Statius.".getBytes();
			break;
			case 15019:
				itemDef.name = "Statius's Full Helm";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 42596;
				itemDef.modelZoom = 789;
				itemDef.modelRotationY = 96;
				itemDef.modelRotationX = 2039;
				itemDef.modelOffset2 = -7;
				itemDef.modelOffset1 = 2;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42639;
				itemDef.anInt200 = 42655;
			itemDef.description = "An ancient full helm once worn by Statius.".getBytes();
			break;
			case 15020:
				itemDef.name = "Statius's Warhammer";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 42577;
				itemDef.modelZoom = 1360;
				itemDef.modelRotationY = 507;
				itemDef.modelRotationX = 27;
				itemDef.modelOffset2 = 6;
				itemDef.modelOffset1 = 7;
				itemDef.anInt204 = 0;
				itemDef.anInt165 = 42623;
				itemDef.anInt200 = 42623;
			itemDef.description = "An ancient warhammer once used by Statius.".getBytes();
			break;
			case 15021:
				itemDef.name = "Arcane spirit shield";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 40922;//50000
				itemDef.modelZoom = 1540;
				itemDef.anInt204 = 97;
				itemDef.modelRotationX = 1020;
				itemDef.modelRotationY = 336;
				itemDef.modelOffset2 = 15;
				itemDef.modelOffset1 = -1;
				itemDef.anInt165 = 40944;
				itemDef.anInt200 = 40944;
			itemDef.description = "An ethereal shield with an arcane sigil attached to it.".getBytes();
			break;
			case 15022:
				itemDef.name = "Spectral spirit shield";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 40920;
				itemDef.modelZoom = 1540;
				itemDef.anInt204 = 97;
				itemDef.modelRotationX = 1020;
				itemDef.modelRotationY = 336;
				itemDef.modelOffset2 = 15;
				itemDef.modelOffset1 = -1;
				itemDef.anInt165 = 40940;
				itemDef.anInt200 = 40940;
			itemDef.description = "An ethereal shield with a spectral sigil attached to it.".getBytes();
			break;
			case 15023:
				itemDef.name = "Divine spirit shield";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 40921;
				itemDef.modelZoom = 1540;
				itemDef.anInt204 = 97;
				itemDef.modelRotationX = 1020;
				itemDef.modelRotationY = 336;
				itemDef.modelOffset2 = 15;
				itemDef.modelOffset1 = -1;
				itemDef.anInt165 = 40939;
				itemDef.anInt200 = 40939;
			itemDef.description = "An ethereal shield with a divine sigil attached to it.".getBytes();
			break;
			case 15024:
				itemDef.name = "Spirit shield";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 40919;
				itemDef.modelZoom = 1540;
				itemDef.anInt204 = 97;
				itemDef.modelRotationX = 1020;
				itemDef.modelRotationY = 336;
				itemDef.modelOffset2 = 15;
				itemDef.modelOffset1 = -1;
				itemDef.anInt165 = 40943;
				itemDef.anInt200 = 40943;
			itemDef.description = "An ethereal shield.".getBytes();
			break;
			case 15025:
				itemDef.name = "Blessed spirit shield";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 40913;
				itemDef.modelZoom = 1540;
				itemDef.anInt204 = 97;
				itemDef.modelRotationX = 1020;
				itemDef.modelRotationY = 336;
				itemDef.modelOffset2 = 15;
				itemDef.modelOffset1 = -1;
				itemDef.anInt165 = 40;
				itemDef.anInt200 = 40941;
			itemDef.description = "An ethereal shield that has been blessed with holy powers.".getBytes();
			break;
			case 15026:
				itemDef.name = "Elysian spirit shield";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 40915;
				itemDef.modelZoom = 1540;
				itemDef.anInt204 = 97;
				itemDef.modelRotationX = 1020;
				itemDef.modelRotationY = 336;
				itemDef.modelOffset2 = 15;
				itemDef.modelOffset1 = -1;
				itemDef.anInt165 = 40942;
				itemDef.anInt200 = 40942;
			itemDef.description = "An ethereal shield with an elysian sigil attached to it.".getBytes();
			break;
			case 15027:
				itemDef.name = "Dragon claws";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 44590;
				itemDef.anInt165 = 43660;
				itemDef.anInt200 = 43660;
				itemDef.modelZoom = 789;
				itemDef.modelRotationY = 240;
				itemDef.modelRotationX = 60;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffset2 = -23;
			itemDef.description = "A set of fighting claws.".getBytes();
			break;
			case 15028:
				itemDef.name = "Ruined dragon armour lump";
				itemDef.actions = new String[5];
				itemDef.modelID = 50012;
				itemDef.modelZoom = 2000;
				itemDef.modelRotationX = 100;
				itemDef.modelRotationY = 2000;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffset2 = 1;
				itemDef.anInt165 = -1;
				itemDef.anInt200 = -1;
				itemDef.anInt188 = -1;
			itemDef.description = "Its a broken piece of armour.".getBytes();
			break;
			case 15029:
				itemDef.name = "Ruined armour slice";
				itemDef.actions = new String[5];
				itemDef.modelID = 50013;
				itemDef.modelZoom = 2000;
				itemDef.modelRotationX = 304;
				itemDef.modelRotationY = 41;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffset2 = 0;
				itemDef.anInt165 = -1;
				itemDef.anInt200 = -1;
				itemDef.anInt188 = -1;
			itemDef.description = "Its a broken piece of armour.".getBytes();
			break;
			case 15030:
				itemDef.name = "Ruined dragon armour shard";
				itemDef.actions = new String[5];
				itemDef.modelID = 50014;
				itemDef.modelZoom = 2000;
				itemDef.modelRotationX = 228;
				itemDef.modelRotationY = 36;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffset2 = 0;
				itemDef.anInt165 = -1;
				itemDef.anInt200 = -1;
				itemDef.anInt188 = -1;
			itemDef.description = "Its a broken piece of armour.".getBytes();
			break;
			case 15031:
				itemDef.name = "Dragon platebody";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.originalModelColors = new int[2];
				itemDef.modifiedModelColors = new int[2];
				itemDef.originalModelColors[0] = 5662;
				itemDef.modifiedModelColors[0] = 910;
				itemDef.originalModelColors[1] = 3608;
				itemDef.modifiedModelColors[1] = 910;
				itemDef.modelID = 40208;
				itemDef.modelZoom = 1600;
				itemDef.modelRotationY = 488;
				itemDef.modelRotationX = 50;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffset2 = 2;
				itemDef.anInt165 = 40207; 
				itemDef.anInt200 = 40427;
				itemDef.certID = 15032;
			itemDef.description = "Provides excellent protection.".getBytes();
			break;
			
			case 15272:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Eat";
				itemDef.modelID = 48728;
				itemDef.modelZoom = 1460;
				itemDef.modelRotationY = 499;
				itemDef.modelRotationX = 1926;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffset2 = 0;
				itemDef.name = "Rocktail";
				itemDef.description = "Some nicely cooked rocktail.".getBytes();
			break;
			case 15273:
				itemDef.modelID = 48728;
				itemDef.modelZoom = 1460;
				itemDef.modelRotationY = 499;
				itemDef.modelRotationX = 1926;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffset2 = 0;
				itemDef.name = "Rocktail";
				itemDef.stackable = true;
				itemDef.description = "Exchange this at any bank for Rocktail.".getBytes();
			break;
			case 15271:
				itemDef.actions = new String[5];
				itemDef.modelID = 48722;
				itemDef.modelZoom = 1460;
				itemDef.modelRotationY = 499;
				itemDef.modelRotationX = 1926;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffset2 = 0;
				itemDef.name = "Raw rocktail";
				itemDef.description = "I should try cooking this.".getBytes();
			break;
			case 15274:
				itemDef.actions = new String[5];
				itemDef.modelID = 48725;
				itemDef.modelZoom = 1460;
				itemDef.modelRotationY = 499;
				itemDef.modelRotationX = 1926;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffset2 = 0;
				itemDef.name = "Burnt rocktail";
				itemDef.description = "Oops! Maybe a little less heat next time.".getBytes();
			break;
			case 14876:
				itemDef.actions = new String[5];
				itemDef.modelID = 47258;
				itemDef.modelZoom = 1513;
				itemDef.modelRotationY = 0;
				itemDef.modelRotationX = 202;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 43;
				itemDef.stackable = false;
				itemDef.name = "Ancient statuette";
				itemDef.description = "A mysterious statuette of ancient times.".getBytes();
			break;
			case 14877:
				itemDef.actions = new String[5];
				itemDef.modelID = 47257;
				itemDef.modelZoom = 1360;
				itemDef.modelRotationY = 81;
				itemDef.modelRotationX = 337;
				itemDef.modelOffset2 = -27;
				itemDef.modelOffset1 = 0;
				itemDef.stackable = false;
				itemDef.name = "Seren statuette";
				itemDef.description = "A small statuette that appears to be entirely made of crystal.".getBytes();
			break;
			case 14878:
				itemDef.actions = new String[5];
				itemDef.modelID = 47256;
				itemDef.modelZoom = 1360;
				itemDef.modelRotationY = 0;
				itemDef.modelRotationX = 148;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = -30;
				itemDef.stackable = false;
				itemDef.name = "Armadyl statuette";
				itemDef.description = "A dedication to Armadyl, carved from the wing bones of his fallen warriors.".getBytes();
			break;
			case 14879:
				itemDef.actions = new String[5];
				itemDef.modelID = 47250;
				itemDef.modelZoom = 976;
				itemDef.modelRotationY = 0;
				itemDef.modelRotationX = 75;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 33;
				itemDef.stackable = false;
				itemDef.name = "Zamorak statuette";
				itemDef.description = "A small obsidian statuette in the shape of a black demon. ".getBytes();
			break;
			case 14880:
				itemDef.actions = new String[5];
				itemDef.modelID = 47248;
				itemDef.modelZoom = 1488;
				itemDef.modelRotationY = 75;
				itemDef.modelRotationX = 94;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 43;
				itemDef.stackable = false;
				itemDef.name = "Saradomin statuette";
				itemDef.description = "An angel statuette dedicated to Saradomin.".getBytes();
			break;
			case 14881:
				itemDef.actions = new String[5];
				itemDef.modelID = 47244;
				itemDef.modelZoom = 1360;
				itemDef.modelRotationY = 153;
				itemDef.modelRotationX = 1841;
				itemDef.modelOffset2 = -32;
				itemDef.modelOffset1 = 0;
				itemDef.stackable = false;
				itemDef.name = "Bandos statuette";
				itemDef.description = "A statuette resembling an ork-like creature.".getBytes();
			break;
			case 14882:
				itemDef.actions = new String[5];
				itemDef.modelID = 47247;
				itemDef.modelZoom = 720;
				itemDef.modelRotationY = 105;
				itemDef.modelRotationX = 1653;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 57;
				itemDef.stackable = false;
				itemDef.name = "Ruby chalice";
				itemDef.description = "A small obsidian challice with a finely cut ruby in it.".getBytes();
			break;
			case 14883:
				itemDef.actions = new String[5];
				itemDef.modelID = 47252;
				itemDef.modelZoom = 1300;
				itemDef.modelRotationY = 141;
				itemDef.modelRotationX = 1949;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.stackable = false;
				itemDef.name = "Guthixian brazier";
				itemDef.description = "A bronze ritual brazier, trimmed with jade and emeralds.".getBytes();
			break;
			case 14884:
				itemDef.actions = new String[5];
				itemDef.modelID = 47251;
				itemDef.modelZoom = 1032;
				itemDef.modelRotationY = 364;
				itemDef.modelRotationX = 1872;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 5;
				itemDef.stackable = false;
				itemDef.name = "Armadyl totem";
				itemDef.description = "A painted, wooden eagle in fine condition.".getBytes();
			break;
			case 14885:
				itemDef.actions = new String[5];
				itemDef.modelID = 47259;
				itemDef.modelZoom = 724;
				itemDef.modelRotationY = 377;
				itemDef.modelRotationX = 916;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = -1;
				itemDef.stackable = false;
				itemDef.name = "Zamorak medallion";
				itemDef.description = "A black, metal symbol decorated with three blood red rubies.".getBytes();
			break;
			case 14886:
				itemDef.actions = new String[5];
				itemDef.modelID = 47246;
				itemDef.modelZoom = 1744;
				itemDef.modelRotationY = 0;
				itemDef.modelRotationX = 0;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = -32;
				itemDef.stackable = false;
				itemDef.name = "Saradomin carving";
				itemDef.description = "A wooden angel icon dedicated to Saradomin. ".getBytes();
			break;
			case 14887:
				itemDef.actions = new String[5];
				itemDef.modelID = 47245;
				itemDef.modelZoom = 921;
				itemDef.modelRotationY = 552;
				itemDef.modelRotationX = 94;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 1;
				itemDef.stackable = false;
				itemDef.name = "Bandos scrimshaw";
				itemDef.description = "A stylised ogre face, crafted out of bone.".getBytes();
			break;
			case 14888:
				itemDef.actions = new String[5];
				itemDef.modelID = 47254;
				itemDef.modelZoom = 1347;
				itemDef.modelRotationY = 81;
				itemDef.modelRotationX = 1670;
				itemDef.modelOffset2 = 15;
				itemDef.modelOffset1 = 3;
				itemDef.stackable = false;
				itemDef.name = "Saradomin amphora";
				itemDef.description = "A ceramic vase with a Saradomin symbol painted on it.".getBytes();
			break;
			case 14889:
				itemDef.actions = new String[5];
				itemDef.modelID = 47249;
				itemDef.modelZoom = 848;
				itemDef.modelRotationY = 111;
				itemDef.modelRotationX = 1347;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = -5;
				itemDef.stackable = false;
				itemDef.name = "Ancient psaltery bridge";
				itemDef.description = "A part of an old instrument.".getBytes();
			break;
			case 14890:
				itemDef.actions = new String[5];
				itemDef.modelID = 47255;
				itemDef.modelZoom = 835;
				itemDef.modelRotationY = 512;
				itemDef.modelRotationX = 13;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = -1;
				itemDef.stackable = false;
				itemDef.name = "Bronzed dragon claw";
				itemDef.description = "This big claw was once part of a ceremonial necklace.".getBytes();
			break;
			case 14891:
				itemDef.actions = new String[5];
				itemDef.modelID = 47243;
				itemDef.modelZoom = 1104;
				itemDef.modelRotationY = 130;
				itemDef.modelRotationX = 1820;
				itemDef.modelOffset2 = 20;
				itemDef.modelOffset1 = 0;
				itemDef.stackable = false;
				itemDef.name = "Third age carafe";
				itemDef.description = "A very old clay carafe.".getBytes();
			break;
			case 14892:
				itemDef.actions = new String[5];
				itemDef.modelID = 47253;
				itemDef.modelZoom = 1360;
				itemDef.modelRotationY = 512;
				itemDef.modelRotationX = 417;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = -9;
				itemDef.stackable = false;
				itemDef.name = "Broken statue headdress";
				itemDef.description = "This was once part of a big statue.".getBytes();
			break;
			case 15049:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.anInt165 = 56505;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffset2 = -12;
				itemDef.modelZoom = 976;
				itemDef.modelRotationX = 51;
				itemDef.modelRotationY = 510;
				itemDef.anInt200 = 55825;
				itemDef.modelID = 56779;
				itemDef.name = "Arcane stream necklace";
				itemDef.description = "The energy from this necklace is unlike anything you have ever felt.".getBytes();
			break;
			case 15050:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.anInt165 = 51802;
				itemDef.modelOffset1 = -5;
				itemDef.modelOffset2 = 2;
				itemDef.modelZoom = 1495;
				itemDef.modelRotationX = 1400;
				itemDef.modelRotationY = 148;
				itemDef.anInt200 = 51800;
				itemDef.modelID = 51799;
				itemDef.name = "Staff of light";
				itemDef.description = "Humming with power.".getBytes();
			break;
		}
	}*/
	
	///Nintendo's fix for hand models - Cuaes a stackedOVerFlow with 620 items.
	public static void fix525Items(int i) {
		ItemDef itemDef = ItemDef.forID(i);///This
		switch(i) {
			case 7452:
			case 7453:
			case 7454:
			case 7455:
			case 7456:
			case 7457:
			case 7458:
			case 7459:
			case 7460:
			case 7461:
			case 7462:
				itemDef.aByte205 = 13;
			break;
			case 4151:
			case 4153:
			//add all pvp items
				itemDef.aByte205 = 13;
				itemDef.aByte154 = 6;
			break;
			// FEMALE WEPAONS MODELS
			case 1215:
			case 1231:
			case 1249:
			case 1263:
			case 1305:
			case 1377:
			case 1434:
			case 4718:
			case 3205:
			case 4587:
			case 5680:
			case 5698:
			case 5716:
			case 5730:
			case 3204:
			case 6739:
			case 7158:
			case 1323:
			case 1333:
			case 1309:
			case 1319:
			//case 4153:
			case 6528:
			case 1349:
			case 1359:
			case 1267:
			case 1275:
			case 861:
			case 9185:
			case 4212:
			case 868:
			case 1381:
			case 1383:
			case 1385:
			case 1387:
			case 4170:
			case 2415:
			case 4755:
			case 2416:
			case 2417:
			case 6914:
			case 4756:
			case 4711:
			case 4719:
			case 4747:
			case 4735:
			case 11235:
			case 1213:
			case 1289:
			case 1303:
			case 1373:
			case 4675:
			case 11694:
			case 11696:
			case 11698:
			case 11700:
			case 841:
			case 11730:
			case 6908:
			case 6910:
			case 6912:
				itemDef.aByte154 = -15;
			break;
			case 8844:
			case 8845:
			case 8846:
			case 8847:
			case 8848:
			case 8849:
			case 8850:
				itemDef.aByte205 = 13;
				itemDef.aByte154 = -1;
			break;
			//FEMALE SHIELD FIT FIX
			case 1171:
			case 1173:
			case 1175:
			case 1177:
			case 1179:
			case 1181:
			case 1183:
			case 1187:
			case 1189:
			case 1191:
			case 1193:
			case 1195:
			case 1197:
			case 1199:
			case 1201:
			case 1540:
			case 2589:
			case 2597:
			case 2603:
			case 2611:
			case 2621:
			case 2629:
			case 2659:
			case 2667:
			case 2675:
			case 2890:
			case 3122:
			case 3488:
			case 3758:
			case 4072:
			case 4156:
			case 4224:
			case 4225:
			case 4507:
			case 6215:
			case 6524:
			case 6631:
			case 6894:
			case 7332:
			case 7334:
			case 11283:
			case 7336:
				itemDef.aByte205 = 14;
				itemDef.aByte154 = -14;
			break;
		}
	}
}