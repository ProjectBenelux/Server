// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class RSInterface {

	/**Interfaces fixed:
	*	Prayer
	*	Curse
	*	Skill
	*	Emote
	*	Option
	*	Equipment / Items on Death
	*/
	public void swapInventoryItems(int i, int j) {
		int k = inventory[i];
		inventory[i] = inventory[j];
		inventory[j] = k;
		k = inventoryValue[i];
		inventoryValue[i] = inventoryValue[j];
		inventoryValue[j] = k;
	}

	public static void unpack(NamedArchive archive, RSFont rsFonts[], NamedArchive streamLoader_1) {
		spriteNodes = new MRUNodes(50000);
		ByteBuffer stream = new ByteBuffer(archive.getDataForName("data"));
		int i = -1;
		int j = stream.readUnsignedWord();
		interfaceCache = new RSInterface[j + 35000];
		while(stream.currentOffset < stream.buffer.length) {
			int index = stream.readUnsignedWord();
			if(index == 65535) {
				i = stream.readUnsignedWord();
				index = stream.readUnsignedWord();
			}
			RSInterface rsInterface = interfaceCache[index] = new RSInterface();
			rsInterface.id = index;
			rsInterface.parentID = i;
			rsInterface.type = stream.readUnsignedByte();
			rsInterface.atActionType = stream.readUnsignedByte();
			rsInterface.contentType = stream.readUnsignedWord();
			rsInterface.width = stream.readUnsignedWord();
			rsInterface.height = stream.readUnsignedWord();
			rsInterface.opacity = (byte) stream.readUnsignedByte();
			rsInterface.hoverType = stream.readUnsignedByte();
			if(rsInterface.hoverType != 0)
				rsInterface.hoverType = (rsInterface.hoverType - 1 << 8) + stream.readUnsignedByte();
			else
				rsInterface.hoverType = -1;
			int i1 = stream.readUnsignedByte();
			if(i1 > 0) {
				rsInterface.valueCompareType = new int[i1];
				rsInterface.requiredValues = new int[i1];
				for(int j1 = 0; j1 < i1; j1++) {
					rsInterface.valueCompareType[j1] = stream.readUnsignedByte();
					rsInterface.requiredValues[j1] = stream.readUnsignedWord();
				}
			}
			int k1 = stream.readUnsignedByte();
			if(k1 > 0) {
				rsInterface.valueIndexArray = new int[k1][];
				for(int l1 = 0; l1 < k1; l1++) {
					int i3 = stream.readUnsignedWord();
					rsInterface.valueIndexArray[l1] = new int[i3];
					for(int l4 = 0; l4 < i3; l4++)
						rsInterface.valueIndexArray[l1][l4] = stream.readUnsignedWord();
				}
			}
			if(rsInterface.type == 0) {
				rsInterface.drawsTransparent = false;
				rsInterface.scrollMax = stream.readUnsignedWord();
				rsInterface.interfaceShown = stream.readUnsignedByte() == 1;
				int i2 = stream.readUnsignedWord();
				rsInterface.children = new int[i2];
				rsInterface.childX = new int[i2];
				rsInterface.childY = new int[i2];
				for(int j3 = 0; j3 < i2; j3++) {
					rsInterface.children[j3] = stream.readUnsignedWord();
					rsInterface.childX[j3] = stream.readSignedWord();
					rsInterface.childY[j3] = stream.readSignedWord();
				}
			}
			if(rsInterface.type == 1) {
				stream.readUnsignedWord();
				stream.readUnsignedByte();
			}
			if(rsInterface.type == 2) {
				rsInterface.inventory = new int[rsInterface.width * rsInterface.height];
				rsInterface.inventoryValue = new int[rsInterface.width * rsInterface.height];
				rsInterface.allowSwapItems = stream.readUnsignedByte() == 1;
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.usableItemInterface = stream.readUnsignedByte() == 1;
				rsInterface.deletesTargetSlot = stream.readUnsignedByte() == 1;
				rsInterface.invSpritePadX = stream.readUnsignedByte();
				rsInterface.invSpritePadY = stream.readUnsignedByte();
				rsInterface.spritesX = new int[20];
				rsInterface.spritesY = new int[20];
				rsInterface.sprites = new Sprite[20];
				for(int j2 = 0; j2 < 20; j2++) {
					int k3 = stream.readUnsignedByte();
					if(k3 == 1) {
						rsInterface.spritesX[j2] = stream.readSignedWord();
						rsInterface.spritesY[j2] = stream.readSignedWord();
						String s1 = stream.readString();
						if(streamLoader_1 != null && s1.length() > 0) {
							int i5 = s1.lastIndexOf(",");
							rsInterface.sprites[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamLoader_1, s1.substring(0, i5));
						}
					}
				}
				rsInterface.actions = new String[5];
				for(int l3 = 0; l3 < 5; l3++) {
					rsInterface.actions[l3] = stream.readString();
					if(rsInterface.actions[l3].length() == 0)
						rsInterface.actions[l3] = null;
					if(rsInterface.parentID == 3824)
						rsInterface.actions[4] = "Buy 20";
					if(rsInterface.parentID == 1644)
						rsInterface.actions[2] = "Operate";
				}
			}
			if(rsInterface.type == 3)
				rsInterface.boxFilled = stream.readUnsignedByte() == 1;
			if(rsInterface.type == 4 || rsInterface.type == 1) {
				rsInterface.textCentered = stream.readUnsignedByte() == 1;
				int k2 = stream.readUnsignedByte();
				if(rsFonts != null)
					rsInterface.rsFonts = rsFonts[k2];
				rsInterface.textShadowed = stream.readUnsignedByte() == 1;
			}
			if(rsInterface.type == 4) {
				rsInterface.disabledMessage = stream.readString().replaceAll("RuneScape", Config.ClientName);
				rsInterface.enabledMessage = stream.readString();
			}
			if(rsInterface.type == 1 || rsInterface.type == 3 || rsInterface.type == 4)
				rsInterface.disabledColor = stream.readDWord();
			if(rsInterface.type == 3 || rsInterface.type == 4) {
				rsInterface.enabledColor = stream.readDWord();
				rsInterface.disabledHoverColor = stream.readDWord();
				rsInterface.enabledHoverColor = stream.readDWord();
			}
			if(rsInterface.type == 5) {
				rsInterface.drawsTransparent = false;
				String s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int i4 = s.lastIndexOf(",");
					rsInterface.disabledSprite = method207(Integer.parseInt(s.substring(i4 + 1)), streamLoader_1, s.substring(0, i4));
				}
				s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int j4 = s.lastIndexOf(",");
					rsInterface.enabledSprite = method207(Integer.parseInt(s.substring(j4 + 1)), streamLoader_1, s.substring(0, j4));
				}
			}
			if(rsInterface.type == 6) {
				int l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.disabledMediaType = 1;
					rsInterface.disabledMediaID = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.enabledMediaType = 1;
					rsInterface.enabledMediaID = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.disabledAnimation = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.disabledAnimation = -1;
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.enabledAnimation = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.enabledAnimation = -1;
				rsInterface.modelZoom = stream.readUnsignedWord();
				rsInterface.modelRotationY = stream.readUnsignedWord();
				rsInterface.modelRotationX = stream.readUnsignedWord();
			}
			if(rsInterface.type == 7) {
				rsInterface.inventory = new int[rsInterface.width * rsInterface.height];
				rsInterface.inventoryValue = new int[rsInterface.width * rsInterface.height];
				rsInterface.textCentered = stream.readUnsignedByte() == 1;
				int l2 = stream.readUnsignedByte();
				if(rsFonts != null)
					rsInterface.rsFonts = rsFonts[l2];
				rsInterface.textShadowed = stream.readUnsignedByte() == 1;
				rsInterface.disabledColor = stream.readDWord();
				rsInterface.invSpritePadX = stream.readSignedWord();
				rsInterface.invSpritePadY = stream.readSignedWord();
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.actions = new String[5];
				for(int k4 = 0; k4 < 5; k4++) {
					rsInterface.actions[k4] = stream.readString();
					if(rsInterface.actions[k4].length() == 0)
						rsInterface.actions[k4] = null;
				}

			}
			if(rsInterface.atActionType == 2 || rsInterface.type == 2) {
				rsInterface.selectedActionName = stream.readString();
				rsInterface.spellName = stream.readString();
				rsInterface.spellUsableOn = stream.readUnsignedWord();
			}

			if(rsInterface.type == 8)
				rsInterface.disabledMessage = stream.readString();

			if(rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
				rsInterface.tooltip = stream.readString();
				if(rsInterface.tooltip.length() == 0) {
					if(rsInterface.atActionType == 1)
						rsInterface.tooltip = "Ok";
					if(rsInterface.atActionType == 4)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 5)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 6)
						rsInterface.tooltip = "Continue";
				}
			}
		}
		aClass44 = archive;
			FriendList(rsFonts);
			IgnoreList(rsFonts);
			configureBank(rsFonts);
			configureCharDesign(rsFonts);
			skillInterface(rsFonts);
			addNewSkills(rsFonts);
			addOldSkills(rsFonts);
			equipmentScreen(rsFonts);
			musicTab(rsFonts);
			itemsOnDeathDATA(rsFonts);
			itemsOnDeath(rsFonts);
			EquipmentTab(rsFonts);
			questTab(rsFonts);
			Logout(rsFonts);
			emoteTab();
			optionTab(rsFonts);
			clanChatTab(rsFonts);
			Sidebar0(rsFonts);
			Pestpanel(rsFonts);
			Pestpanel2(rsFonts);
			magicTab(rsFonts);
			ancientMagicTab(rsFonts);
			configureLunar(rsFonts);
			constructLunar();
		PVPInterface(rsFonts);
		PVPInterface2(rsFonts);
			Curses(rsFonts);
			prayerTab(rsFonts);
			///My option interface
			extraOptions(rsFonts);
			addOnToSettings(rsFonts);
			addOnText(rsFonts);
			ColorChanger(rsFonts);
			
			GodWars(rsFonts);
newbosses(rsFonts);
 PbConfigsInterface(rsFonts);
 PFInfo(rsFonts);
achievement(rsFonts);
Rules(rsFonts);
			minigametele(rsFonts);
			monstertele(rsFonts);
			newbosses(rsFonts);
			traintele(rsFonts);
teleport(rsFonts);
newteleport(rsFonts); 
		spriteNodes = null;
	}


	

	public static void skillToolTip(int ID, int CT) {
		RSInterface rsi = addInterface(ID);
		rsi.id = ID;
		rsi.parentID = ID;
		rsi.type = 8;
		rsi.disabledMessage = null;
		rsi.contentType = CT;
		rsi.height = 32;
		rsi.width = 62;
		rsi.inventoryhover = true;
	}
	public static void addSkillText(int id, String text, RSFont tda[], boolean center, int idx, int color, boolean shadow) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 0;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.textCentered = center;
		rsinterface.textShadowed = shadow;
		rsinterface.rsFonts = tda[idx];
		rsinterface.disabledMessage = text;
		rsinterface.enabledMessage = "";
		rsinterface.disabledColor = color;
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = 0;
		rsinterface.enabledHoverColor = 0;		
	}
	public static void skillInterfaceButton(int i, int j, String s) {
        RSInterface Tab = interfaceCache[i] = new RSInterface();
        Tab.id = i;
        Tab.parentID = i;
        Tab.type = 5;
        Tab.atActionType = 1;
        Tab.contentType = 0;
        Tab.width = 62;
        Tab.height = 32;
        Tab.opacity = 0;
        Tab.hoverType = 0;
		Tab.tooltip = s;
        Tab.disabledSprite = imageLoader(j, "Interfaces/Skill/Skill");
        Tab.enabledSprite = imageLoader(j, "Interfaces/Skill/Skill");
    }
	public static void addPixels(int id, int color, int width, int height, int alpha, boolean filled) {
		RSInterface rsi = addInterface(id);
		rsi.type = 3;
		rsi.opacity = (byte)alpha;
		rsi.disabledColor = color;
		rsi.disabledHoverColor = color;
		rsi.enabledColor = color;
		rsi.enabledHoverColor = color;
		rsi.boxFilled = filled;
		rsi.width = width;
		rsi.height = height;
	}
	private static Sprite LoadLunarSprite(int i, String s) {
		Sprite sprite = imageLoader(i,"Interfaces/Lunar/" + s);
		return sprite;
	}
	private static Sprite CustomSpriteLoader(int id, String s) {
		long l = (TextClass.method585(s) << 8) + (long)id;
		Sprite sprite = (Sprite)spriteNodes.insertFromCache(l);
		if(sprite != null) { return sprite; }
		try {
			sprite = new Sprite("/Interfaces/Attack/"+id + s);
			spriteNodes.removeFromCache(sprite, l);
		} catch(Exception exception) { 
			return null; }
		return sprite;
	}
	private static Sprite imageLoader(int i, String s) {
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) spriteNodes.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s+" "+i);
			spriteNodes.removeFromCache(sprite, l);
		} catch(Exception exception) {
			return null;
		}
		return sprite;
	}
	public static void drawBlackBox(int xPos, int yPos) {
		DrawingArea.drawPixels(71, yPos - 1, xPos - 2, 0x726451, 1);
		DrawingArea.drawPixels(69, yPos, xPos + 174, 0x726451, 1);
		DrawingArea.drawPixels(1, yPos - 2, xPos - 2, 0x726451, 178);
		DrawingArea.drawPixels(1, yPos + 68, xPos, 0x726451, 174);
		DrawingArea.drawPixels(71, yPos - 1, xPos - 1, 0x2E2B23, 1);
		DrawingArea.drawPixels(71, yPos - 1, xPos + 175, 0x2E2B23, 1);
		DrawingArea.drawPixels(1, yPos - 1, xPos, 0x2E2B23, 175);
		DrawingArea.drawPixels(1, yPos + 69, xPos, 0x2E2B23, 175);
		DrawingArea.method335(0x000000, yPos, 174, 68, 220, xPos);
	}
	public void child(int id, int interID, int x, int y) {
		children[id] = interID;
		childX[id] = x;
		childY[id] = y;
	}
	public void totalChildren(int t) {
		children = new int[t];
		childX = new int[t];
		childY = new int[t];
	}
	public void totalChildren(int id, int x, int y) {
		children = new int[id];
		childX = new int[x];
		childY = new int[y];
	}
	public static void setChildren(int total, RSInterface rsinterface) {
		rsinterface.children = new int[total];
		rsinterface.childX = new int[total];
		rsinterface.childY = new int[total];
	}
	public static void setBounds(int ID, int X, int Y, int frame, RSInterface RSinterface){
		RSinterface.children[frame] = ID;
		RSinterface.childX[frame] = X;
		RSinterface.childY[frame] = Y;
	}
	public static void setBoundry(int frame, int ID, int X, int Y, RSInterface RSInterface) {
		RSInterface.children[frame] = ID;
		RSInterface.childX[frame] = X;
		RSInterface.childY[frame] = Y;
	}
	public static void removeSomething(int id) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
	}
	public static void disabledSprite(int id, int sprite) {
		RSInterface class9 = interfaceCache[id];
		class9.disabledSprite = CustomSpriteLoader(sprite, "");
	}
	public static void disabledColor(int id, int color) {
		RSInterface rsi = interfaceCache[id];
		rsi.disabledColor = color;
	}
	public static void textSize(int id, RSFont tda[], int idx) {
		RSInterface rsi = interfaceCache[id]; rsi.rsFonts = tda[idx];
	}
	public static void addGenericTooltip(int id, int width, int height, String message) {
		RSInterface hover = addInterface(id);
		hover.type = 8;
		hover.disabledMessage = message;
		hover.height = height;
		hover.width = width;
		hover.inventoryhover = false;
	}
	public static void addGenericTabTooltip(int id, int width, int height, String message) {
		RSInterface hover = addInterface(id);
		hover.type = 8;
		hover.disabledMessage = message;
		hover.height = height;
		hover.width = width;
		hover.inventoryhover = true;
	}
	public static void addTooltipBox(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.parentID = id;
		rsi.type = 9;
		rsi.disabledMessage = text;
		rsi.inventoryhover = true;
	}
	public static void addTooltip(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.interfaceShown = true;
		rsi.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
	}
	public static void drawTooltip(int id, String text) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.parentID = id;
		rsinterface.type = 0;
		rsinterface.interfaceShown = true;
		rsinterface.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsinterface.totalChildren(1);
		rsinterface.child(0, id + 1, 0, 0);
	}
	
  private static void addHButton(int paramInt1, String paramString1, int paramInt2, int paramInt3, int paramInt4, String paramString2, int paramInt5, int paramInt6, int paramInt7)
  {
    addHoverButton(paramInt1, paramString1, paramInt2, paramInt3, paramInt4, paramString2, 0, paramInt5, paramInt6);
    addHoveredButton(paramInt5, paramString1, paramInt7, paramInt3, paramInt4, paramInt5 + 1);
  }
  
  public static void PFInfo(RSFont[] rsFonts) {		
    RSInterface localRSInterface = addTabInterface(24000);
    localRSInterface.children = new int[9];
    localRSInterface.childX = new int[9];
    localRSInterface.childY = new int[9];

    localRSInterface.children[0] = 24001;
    localRSInterface.childX[0] = 4;
    localRSInterface.childY[0] = 16;

    addSprite(24001, 0, "PFINFO");

    localRSInterface.children[1] = 24002;
    localRSInterface.childX[1] = 90;
    localRSInterface.childY[1] = 22;

    addText(24002, "Project Fatality Achievements", 16753920, false, true, 1, rsFonts, 0);

    localRSInterface.children[2] = 24003;
    localRSInterface.childX[2] = 25;
    localRSInterface.childY[2] = 66;
    addText(24003, "Unbelievable", 16753920, false, true, 1, rsFonts, 0);

    localRSInterface.children[3] = 24004;
    localRSInterface.childX[3] = 25;
    localRSInterface.childY[3] = 101;

    addText(24004, "Kill 3 people in PVP", 16753920, false, true, 1, rsFonts, 0);

    localRSInterface.children[4] = 24005;
    localRSInterface.childX[4] = 136;
    localRSInterface.childY[4] = 156;

    addText(24005, "@red@Completed", 16753920, false, true, 2, rsFonts, 0);

    localRSInterface.children[5] = 24006;
    localRSInterface.childX[5] = 136;
    localRSInterface.childY[5] = 176;

    addText(24006, "KBF - 0 / 100", 16753920, false, true, 1, rsFonts, 0);

    localRSInterface.children[6] = 24007;
    localRSInterface.childX[6] = 136;
    localRSInterface.childY[6] = 196;

    addText(24007, "Red - 0 / 100", 16753920, false, true, 1, rsFonts, 0);

    localRSInterface.children[7] = 24008;
    localRSInterface.childX[7] = 136;
    localRSInterface.childY[7] = 216;

    addText(24008, "test - 0 / 100", 16753920, false, true, 1, rsFonts, 0);
    localRSInterface.children[8] = 24009;
    localRSInterface.childX[8] = 6;
    localRSInterface.childY[8] = 20;
    addHButton(24009, "Close", 0, 16, 16, "Close Button", 14513, 1, 1); }	
	
  public static void achievement(RSFont[] rsFonts){ 
     RSInterface localRSInterface1 = addTabInterface(26600);
    RSInterface localRSInterface2 = addTabInterface(26601);
    RSInterface localRSInterface3 = addTabInterface(26602);
    RSInterface localRSInterface4 = addTabInterface(26603);
    localRSInterface1.totalChildren(5);

    localRSInterface1.child(1, 26604, 4, 16);
    localRSInterface1.child(2, 26601, 7, 9);
    localRSInterface1.child(3, 26602, 7, 84);
    localRSInterface1.child(4, 26603, 157, 90);

    addSprite(26604, 0, "BLOOD");
    localRSInterface2.totalChildren(2);
    localRSInterface2.child(0, 26605, 7, 9);
    localRSInterface2.child(1, 25999, 4, 10);
    localRSInterface2.width = 485;
    localRSInterface2.height = 75;
    addSprite(26605, 0, "TITLE");
    addHButton(25999, "Close", 0, 16, 16, "Close Button", 14513, 4, 10);

    int i2 = 26612;
    localRSInterface3.totalChildren(5);
    localRSInterface3.child(0, 26606, 4, 102);
    localRSInterface3.child(1, 26607, 4, 59);
    localRSInterface3.child(2, 26608, 4, 139);
    localRSInterface3.child(3, 26609, 4, 170);
    localRSInterface3.child(4, 26610, 4, 16);
    localRSInterface3.width = 150;
    localRSInterface3.height = 210;
    addButton(26606, 1, "monsterk", "Yes");
    addButton(26607, 1, "misc", "Yes");
    addButton(26608, 1, "pking", "Yes");
    addButton(26609, 1, "skilling", "Yes");
    addButton(26610, 1, "combat", "Yes");
int i = 26612;
    for(i = 26612; i <= 26712; ++i)
    {
      addButton(i, 5, "combat", "Yes");
    }
    for (i = 26713; i <= 26813; ++i)
    {
      addText(i, "", rsFonts, 2, 16750623, false, false);
    }
    for (i = 26814; i <= 26914; ++i)
    {
      addText(i, "", rsFonts, 1, 16750623, false, false);
    }

    for (i = 27115; i <= 27215; ++i)
    {
      addText(i, "", rsFonts, 2, 16750623, false, false);
    }
    localRSInterface4.totalChildren(404);
    i = 0; int j = 2; int k = 15; int l = 30; int i1 = 30;
    for (i2 = 26612; i2 <= 26712; ++i2) {
      localRSInterface4.child(i, i2, 0, j);
      ++i;
      j += 53;
    }

    for (i2 = 26713; i2 <= 26813; ++i2) {
      localRSInterface4.child(i, i2, 120, k);
      ++i;
      k += 53;
    }
    for (i2 = 26814; i2 <= 26914; ++i2) {
      localRSInterface4.child(i, i2, 115, l);
      ++i;
      l += 53;
    }
    for (i2 = 27115; i2 <= 27215; ++i2) {
      localRSInterface4.child(i, i2, 305, i1);
      ++i;
      i1 += 53;
    }
    localRSInterface4.scrollMax = 1165;
    localRSInterface4.width = 325;
    localRSInterface4.height = 210;
  }	

  public static void PbConfigsInterface(RSFont[] rsFonts) {
    RSInterface Interface = addTabInterface(38000);

    int i = 0;
    String str = "Interfaces/PbConfigs/";
    addSprite(38001, str + "Border");
    addSprite(38002, str + "Background");
    addSprite(38003, str + "TextBox");
    addSprite(38004, str + "SmallerTextBox");
    addText(38005, "Gameframe", 16777215, true, true, -1, rsFonts, 1);
    addText(38006, "Sky", 16777215, true, true, -1, rsFonts, 1);
    addText(38007, "Hitmarks", 16777215, true, true, -1, rsFonts, 1);
    addText(38008, "Hitpoint Bar", 16777215, true, true, -1, rsFonts, 1);
    addText(38009, "Names", 16777215, true, true, -1, rsFonts, 1);
    addText(38010, "10x Damage", 16777215, true, true, -1, rsFonts, 1);
    addText(38011, "Exp Lock", 16777215, true, true, -1, rsFonts, 1);
    addText(38012, "525", 16777215, true, true, -1, rsFonts, 1);
    addText(38019, "Client Configs", 16751360, true, true, -1, rsFonts, 2);
    addText(38020, "Back", 16777215, true, true, -1, rsFonts, 1);

    addText(38050, "0", 16777215, false, true, -1, rsFonts, 1);

    addHoverButton(38013, str + "SmallButton", 0, 36, 18, "Change", -1, 38014, 1);
    addHoveredButton(38014, str + "SmallButton", 1, 36, 18, 38015);

    addHoverButton(38016, str + "ModernButton", 0, 49, 18, "Back", -1, 38017, 1);
    addHoveredButton(38017, str + "ModernButton", 1, 49, 18, 38018);

    addButton(38021, 4, 38022, 0, 1, str + "SelectButton", 18, 18, "Toggle new Menus", 350, 0);
    addButton(38023, 4, 38024, 0, 1, str + "SelectButton", 18, 18, "Toggle new Hitmarks", 351, 0);
    addButton(38025, 4, 38026, 0, 1, str + "SelectButton", 18, 18, "Toggle new Hp Bar", 352, 0);
    addButton(38027, 4, 38028, 0, 1, str + "SelectButton", 18, 18, "Toggle names above head", 353, 0);
    addButton(38029, 4, 38030, 0, 1, str + "SelectButton", 18, 18, "Toggle 10X damage", 354, 0);
    addButton(38031, 4, 38032, 0, 1, str + "SelectButton", 18, 18, "Toggle Exp Lock", 355, 0);

    setChildren(30, Interface);
    setBounds(38001, 3, 22, i, Interface); ++i;
    setBounds(38002, 3, 24, i, Interface); ++i;
    setBounds(38001, 3, 249, i, Interface); ++i;

    setBounds(38004, 13, 35, i, Interface); ++i;
    setBounds(38003, 13, 62, i, Interface); ++i;
    setBounds(38003, 13, 89, i, Interface); ++i;
    setBounds(38003, 13, 116, i, Interface); ++i;
    setBounds(38003, 13, 143, i, Interface); ++i;
    setBounds(38003, 13, 170, i, Interface); ++i;
    setBounds(38003, 13, 197, i, Interface); ++i;
    setBounds(38016, 64, 224, i, Interface); ++i;
    setBounds(38017, 64, 224, i, Interface); ++i;
    setBounds(38013, 143, 35, i, Interface); ++i;
    setBounds(38014, 143, 35, i, Interface); ++i;

    setBounds(38005, 74, 36, i, Interface); ++i;
    setBounds(38006, 83, 63, i, Interface); ++i;
    setBounds(38007, 83, 90, i, Interface); ++i;
    setBounds(38008, 83, 117, i, Interface); ++i;
    setBounds(38009, 83, 144, i, Interface); ++i;
    setBounds(38010, 83, 171, i, Interface); ++i;
    setBounds(38011, 83, 198, i, Interface); ++i;
    setBounds(38020, 88, 225, i, Interface); ++i;
    setBounds(38012, 161, 36, i, Interface); ++i;
    setBounds(38021, 161, 62, i, Interface); ++i;
    setBounds(38023, 161, 89, i, Interface); ++i;
    setBounds(38025, 161, 116, i, Interface); ++i;
    setBounds(38027, 161, 143, i, Interface); ++i;
    setBounds(38029, 161, 170, i, Interface); ++i;
    setBounds(38031, 161, 197, i, Interface); ++i;
    setBounds(38019, 95, 4, i, Interface); ++i;
  }

		   

  public static void minigametele(RSFont[] rsFonts) {
    RSInterface Interface = addInterface(45200);

    addText(45201, "Minigames Teleport", 16751360, false, true, 52, rsFonts, 2);

    addHoverButton(45202, "Interfaces/Minigame/Hover", 0, 172, 24, "Duel Arena", -1, 45203, 1);
    addHoveredButton(45203, "Interfaces/Minigame/Hover", 1, 172, 24, 45204);

    addHoverButton(45218, "Interfaces/Minigame/Hover", 0, 172, 24, "Barrows", -1, 45219, 1);
    addHoveredButton(45219, "Interfaces/Minigame/Hover", 1, 172, 24, 45220);

    addHoverButton(45221, "Interfaces/Minigame/Hover", 0, 172, 24, "Pest Control", -1, 45222, 1);
    addHoveredButton(45222, "Interfaces/Minigame/Hover", 1, 172, 24, 45223);

    addHoverButton(45224, "Interfaces/Minigame/Hover", 0, 172, 24, "Warriors Guild", -1, 45225, 1);
    addHoveredButton(45225, "Interfaces/Minigame/Hover", 1, 172, 24, 45226);

    addHoverButton(45227, "Interfaces/Minigame/Hover", 0, 172, 24, "God Wars", -1, 45228, 1);
    addHoveredButton(45228, "Interfaces/Minigame/Hover", 1, 172, 24, 45229);

    addHoverButton(45230, "Interfaces/Minigame/Hover", 0, 172, 24, "Fight Cave", -1, 45231, 1);
    addHoveredButton(45231, "Interfaces/Minigame/Hover", 1, 172, 24, 45232);

    addHoverButton(45233, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 45234, 1);
    addHoveredButton(45234, "Interfaces/Minigame/Back", 1, 16, 16, 45235);

    addSprite(45205, 1, "Interfaces/Minigame/DuelArena");
    addSprite(45206, 1, "Interfaces/Minigame/Barrows");
    addSprite(45207, 1, "Interfaces/Minigame/PestControl");
    addSprite(45208, 1, "Interfaces/Minigame/Warriors");
    addSprite(45209, 1, "Interfaces/Minigame/GodWars");
    addSprite(45210, 1, "Interfaces/Minigame/FightCave");
    addSprite(45211, 1, "Interfaces/Minigame/Background");

    addText(45212, "Duel Arena", 14056233, true, true, 52, rsFonts, 2);
    addText(45213, "Barrows", 14056233, true, true, 52, rsFonts, 2);
    addText(45214, "Pest Control", 14056233, true, true, 52, rsFonts, 2);
    addText(45215, "Warriors Guild", 14056233, true, true, 52, rsFonts, 2);
    addText(45216, "God Wars", 14056233, true, true, 52, rsFonts, 2);
    addText(45217, "Fight Caves", 14056233, true, true, 52, rsFonts, 2);

    int i = 28;
    int j = 0;
    setChildren(i, Interface);

    setBounds(45211, -1, 26, j, Interface); ++j;

    setBounds(45201, 33, 7, j, Interface); ++j;

    setBounds(45202, 8, 35, j, Interface); ++j;
    setBounds(45203, 8, 35, j, Interface); ++j;
    setBounds(45212, 80, 39, j, Interface); ++j;

    setBounds(45218, 8, 72, j, Interface); ++j;
    setBounds(45219, 8, 72, j, Interface); ++j;
    setBounds(45213, 80, 76, j, Interface); ++j;

    setBounds(45221, 8, 109, j, Interface); ++j;
    setBounds(45222, 8, 109, j, Interface); ++j;
    setBounds(45214, 80, 113, j, Interface); ++j;

    setBounds(45224, 8, 146, j, Interface); ++j;
    setBounds(45225, 8, 146, j, Interface); ++j;
    setBounds(45215, 80, 150, j, Interface); ++j;

    setBounds(45227, 8, 183, j, Interface); ++j;
    setBounds(45228, 8, 183, j, Interface); ++j;
    setBounds(45216, 80, 187, j, Interface); ++j;

    setBounds(45230, 8, 220, j, Interface); ++j;
    setBounds(45231, 8, 220, j, Interface); ++j;
    setBounds(45217, 80, 224, j, Interface); ++j;

    setBounds(45205, 148, 33, j, Interface); ++j;

    setBounds(45206, 148, 70, j, Interface); ++j;
    setBounds(45207, 148, 104, j, Interface); ++j;
    setBounds(45208, 148, 140, j, Interface); ++j;
    setBounds(45209, 148, 179, j, Interface); ++j;
    setBounds(45210, 148, 219, j, Interface); ++j;

    setBounds(45233, 10, 6, j, Interface); ++j;
    setBounds(45234, 10, 6, j, Interface); ++j;
  }

  public static void traintele(RSFont[] rsFonts)
  {
    RSInterface Interface = addInterface(45600);

    addText(45601, "Monster Teleport", 16751360, false, true, 52, rsFonts, 2);

    addHoverButton(45602, "Interfaces/Minigame/Hover", 0, 172, 24, "Rock Crabs", -1, 45603, 1);
    addHoveredButton(45603, "Interfaces/Minigame/Hover", 1, 172, 24, 45604);

    addHoverButton(45618, "Interfaces/Minigame/Hover", 0, 172, 24, "Taverly Dungeon", -1, 45619, 1);
    addHoveredButton(45619, "Interfaces/Minigame/Hover", 1, 172, 24, 45620);

    addHoverButton(45621, "Interfaces/Minigame/Hover", 0, 172, 24, "Slayer Tower", -1, 45622, 1);
    addHoveredButton(45622, "Interfaces/Minigame/Hover", 1, 172, 24, 45623);

    addHoverButton(45624, "Interfaces/Minigame/Hover", 0, 172, 24, "Brimhaven Dungeon", -1, 45625, 1);
    addHoveredButton(45625, "Interfaces/Minigame/Hover", 1, 172, 24, 45626);

    addHoverButton(45633, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 45634, 1);
    addHoveredButton(45634, "Interfaces/Minigame/Back", 1, 16, 16, 45635);

    addSprite(45605, 1, "Interfaces/Minigame/Blank");
    addSprite(45606, 1, "Interfaces/Minigame/Blank");
    addSprite(45607, 1, "Interfaces/Minigame/Blank");
    addSprite(45608, 1, "Interfaces/Minigame/Blank");

    addSprite(45611, 1, "Interfaces/Minigame/Background");

    addText(45612, "Rock Crabs", 14056233, true, true, 52, rsFonts, 2);
    addText(45613, "Taverly Dungeon", 14056233, true, true, 52, rsFonts, 2);
    addText(45614, "Slayer Tower", 14056233, true, true, 52, rsFonts, 2);
    addText(45615, "Brimhaven Dungeon", 14056233, true, true, 52, rsFonts, 2);

    int i = 20;
    int j = 0;
    setChildren(i, Interface);

    setBounds(45611, -1, 26, j, Interface); ++j;

    setBounds(45601, 33, 7, j, Interface); ++j;

    setBounds(45602, 8, 35, j, Interface); ++j;
    setBounds(45603, 8, 35, j, Interface); ++j;
    setBounds(45612, 80, 39, j, Interface); ++j;

    setBounds(45618, 8, 72, j, Interface); ++j;
    setBounds(45619, 8, 72, j, Interface); ++j;
    setBounds(45613, 80, 76, j, Interface); ++j;

    setBounds(45621, 8, 109, j, Interface); ++j;
    setBounds(45622, 8, 109, j, Interface); ++j;
    setBounds(45614, 80, 113, j, Interface); ++j;

    setBounds(45624, 8, 146, j, Interface); ++j;
    setBounds(45625, 8, 146, j, Interface); ++j;
    setBounds(45615, 80, 150, j, Interface); ++j;

    setBounds(45605, 148, 33, j, Interface); ++j;

    setBounds(45606, 148, 70, j, Interface); ++j;
    setBounds(45607, 148, 104, j, Interface); ++j;
    setBounds(45608, 148, 140, j, Interface); ++j;

    setBounds(45633, 10, 6, j, Interface); ++j;
    setBounds(45634, 10, 6, j, Interface); ++j;
  }

  public static void monstertele(RSFont[] rsFonts)
  {
    RSInterface Interface = addInterface(45500);

    addText(45501, "Monster Teleport", 16751360, false, true, 52, rsFonts, 2);

    addHoverButton(45502, "Interfaces/Minigame/Hover", 0, 172, 24, "Godwars", -1, 45503, 1);
    addHoveredButton(45503, "Interfaces/Minigame/Hover", 1, 172, 24, 45504);

    addHoverButton(45518, "Interfaces/Minigame/Hover", 0, 172, 24, "King Black Dragon", -1, 45519, 1);
    addHoveredButton(45519, "Interfaces/Minigame/Hover", 1, 172, 24, 45520);

    addHoverButton(45521, "Interfaces/Minigame/Hover", 0, 172, 24, "Dagannoth Kings", -1, 45522, 1);
    addHoveredButton(45522, "Interfaces/Minigame/Hover", 1, 172, 24, 45523);

    addHoverButton(45524, "Interfaces/Minigame/Hover", 0, 172, 24, "Chaos Elemental", -1, 45525, 1);
    addHoveredButton(45525, "Interfaces/Minigame/Hover", 1, 172, 24, 45526);

    addHoverButton(45527, "Interfaces/Minigame/Hover", 0, 172, 24, "Kalphite Queen", -1, 45528, 1);
    addHoveredButton(45528, "Interfaces/Minigame/Hover", 1, 172, 24, 45529);

    addHoverButton(45530, "Interfaces/Minigame/Hover", 0, 172, 24, "New Bosses", -1, 45531, 1);
    addHoveredButton(45531, "Interfaces/Minigame/Hover", 1, 172, 24, 45532);

    addHoverButton(45533, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 45534, 1);
    addHoveredButton(45534, "Interfaces/Minigame/Back", 1, 16, 16, 45535);

    addSprite(45505, 1, "Interfaces/Minigame/Blank");
    addSprite(45506, 1, "Interfaces/Minigame/Blank");
    addSprite(45507, 1, "Interfaces/Minigame/Blank");
    addSprite(45508, 1, "Interfaces/Minigame/Blank");
    addSprite(45509, 1, "Interfaces/Minigame/Blank");
    addSprite(45510, 1, "Interfaces/Minigame/Blank");
    addSprite(45511, 1, "Interfaces/Minigame/Background");

    addText(45512, "Godwars", 14056233, true, true, 52, rsFonts, 2);
    addText(45513, "King Black Dragon", 14056233, true, true, 52, rsFonts, 2);
    addText(45514, "Dagannoth Kings", 14056233, true, true, 52, rsFonts, 2);
    addText(45515, "Chaos Elemental", 14056233, true, true, 52, rsFonts, 2);
    addText(45516, "Kalphite Queen", 14056233, true, true, 52, rsFonts, 2);
    addText(45517, "New Bosses", 14056233, true, true, 52, rsFonts, 2);

    int i = 28;
    int j = 0;
    setChildren(i, Interface);

    setBounds(45511, -1, 26, j, Interface); ++j;

    setBounds(45501, 33, 7, j, Interface); ++j;

    setBounds(45502, 8, 35, j, Interface); ++j;
    setBounds(45503, 8, 35, j, Interface); ++j;
    setBounds(45512, 80, 39, j, Interface); ++j;

    setBounds(45518, 8, 72, j, Interface); ++j;
    setBounds(45519, 8, 72, j, Interface); ++j;
    setBounds(45513, 80, 76, j, Interface); ++j;

    setBounds(45521, 8, 109, j, Interface); ++j;
    setBounds(45522, 8, 109, j, Interface); ++j;
    setBounds(45514, 80, 113, j, Interface); ++j;

    setBounds(45524, 8, 146, j, Interface); ++j;
    setBounds(45525, 8, 146, j, Interface); ++j;
    setBounds(45515, 80, 150, j, Interface); ++j;

    setBounds(45527, 8, 183, j, Interface); ++j;
    setBounds(45528, 8, 183, j, Interface); ++j;
    setBounds(45516, 80, 187, j, Interface); ++j;

    setBounds(45530, 8, 220, j, Interface); ++j;
    setBounds(45531, 8, 220, j, Interface); ++j;
    setBounds(45517, 80, 224, j, Interface); ++j;

    setBounds(45505, 148, 33, j, Interface); ++j;

    setBounds(45506, 148, 70, j, Interface); ++j;
    setBounds(45507, 148, 104, j, Interface); ++j;
    setBounds(45508, 148, 140, j, Interface); ++j;
    setBounds(45509, 148, 179, j, Interface); ++j;
    setBounds(45510, 148, 219, j, Interface); ++j;

    setBounds(45533, 10, 6, j, Interface); ++j;
    setBounds(45534, 10, 6, j, Interface); ++j;
  }  
  
   public static void teleport(RSFont[] rsFonts) {
      RSInterface localRSInterface = addInterface(17650);
    addSprite(17651, 10, "CLICK");
    addHoverButton(17652, "CLICK", 2, 200, 30, "Which Zone?", -1, 17653, 1);
    addHoveredButton(17653, "CLICK", 2, 200, 30, 17654);
    addHoverButton(17655, "CLICK", 3, 200, 30, "Which Zone?", -1, 17656, 1);
    addHoveredButton(17656, "CLICK", 3, 200, 30, 17657);
    addHoverButton(17658, "CLICK", 3, 200, 30, "Which Zone?", -1, 17659, 1);
    addHoveredButton(17659, "CLICK", 3, 200, 30, 17660);
    addHoverButton(17661, "CLICK", 3, 200, 30, "Which Zone?", -1, 17662, 1);
    addHoveredButton(17662, "CLICK", 3, 200, 30, 17663);
    addHoverButton(17664, "CLICK", 3, 200, 30, "Which Zone?", -1, 17665, 1);
    addHoveredButton(17665, "CLICK", 3, 200, 30, 17666);
    addHoverButton(17667, "CLICK", 3, 200, 30, "Which Zone?", -1, 17668, 1);
    addHoveredButton(17668, "CLICK", 3, 200, 30, 17669);
    addHoverButton(17670, "CLICK", 3, 200, 30, "Which Zone?", -1, 17671, 1);
    addHoveredButton(17671, "CLICK", 3, 200, 30, 17672);
    addHoverButton(17673, "CLICK", 1, 200, 30, "Stop Viewing", -1, 17674, 1);
    addHoveredButton(17674, "CLICK", 1, 200, 30, 17675);
    addText(22804, "God Wars", rsFonts, 0, 16750623, false, true);
    addText(22808, "King Black Dragon", rsFonts, 0, 16750623, false, true);
    addText(22812, "Dagannoth Kings", rsFonts, 0, 16750623, false, true);
    addText(22816, "Chaos Elemental", rsFonts, 0, 16750623, false, true);
    addText(22820, "Kalphite Queen", rsFonts, 0, 16750623, false, true);
    addText(22824, "Unholy Cursebearer", rsFonts, 0, 16750623, false, true);
    addText(22828, "More Bosses", rsFonts, 0, 16750623, false, true);
    addText(22800, "", rsFonts, 0, 16750623, false, true);
    localRSInterface.totalChildren(25);
    localRSInterface.child(0, 17651, 0, 0);
    localRSInterface.child(1, 17652, 12, 40);
    localRSInterface.child(2, 17653, 11, 40);
    localRSInterface.child(3, 17655, 12, 65);
    localRSInterface.child(4, 17656, 11, 65);
    localRSInterface.child(5, 22800, 68, 78);
    localRSInterface.child(6, 17658, 12, 90);
    localRSInterface.child(7, 17659, 11, 90);
    localRSInterface.child(8, 17661, 12, 115);
    localRSInterface.child(9, 17662, 11, 115);
    localRSInterface.child(10, 17664, 12, 143);
    localRSInterface.child(11, 17665, 11, 143);
    localRSInterface.child(12, 17667, 12, 168);
    localRSInterface.child(13, 17668, 11, 168);
    localRSInterface.child(14, 17670, 12, 193);
    localRSInterface.child(15, 17671, 11, 193);
    localRSInterface.child(16, 17673, 38, 236);
    localRSInterface.child(17, 17674, 38, 236);
    localRSInterface.child(18, 22804, 38, 45);
    localRSInterface.child(19, 22808, 38, 70);
    localRSInterface.child(20, 22812, 38, 95);
    localRSInterface.child(21, 22816, 38, 120);
    localRSInterface.child(22, 22820, 38, 147);
    localRSInterface.child(23, 22824, 38, 174);
    localRSInterface.child(24, 22828, 38, 201);
    localRSInterface = addTabInterface(14000);
    localRSInterface.width = 474;
    localRSInterface.height = 213;
    localRSInterface.scrollMax = 305;
    for (int i = 14001; i <= 14030; ++i) {
      addText(i, "", rsFonts, 1, 16777215, false, true);
    }
    localRSInterface.totalChildren(30);
    int i = 0;
    int j = 5;
    for (int k = 14001; k <= 14030; ++k) {
      localRSInterface.child(i, k, 248, j);
      ++i;
      j += 13;
    }
  } 


  
   public static void newbosses(RSFont[] rsFonts) {
    RSInterface Interface = addInterface(46000);

    addText(46001, "New Bosses Teleport", 16751360, false, true, 52, rsFonts, 2);

    addHoverButton(46002, "Interfaces/Minigame/Hover", 0, 172, 24, "Unholy cursebearer", -1, 46003, 1);
    addHoveredButton(46003, "Interfaces/Minigame/Hover", 1, 172, 24, 46004);

    addHoverButton(46018, "Interfaces/Minigame/Hover", 0, 172, 24, "Tormented Demon", -1, 46019, 1);
    addHoveredButton(46019, "Interfaces/Minigame/Hover", 1, 172, 24, 46020);

    addHoverButton(46021, "Interfaces/Minigame/Hover", 0, 172, 24, "freezer Lakhrahnaz", -1, 46022, 1);
    addHoveredButton(46022, "Interfaces/Minigame/Hover", 1, 172, 24, 46023);

    addHoverButton(46024, "Interfaces/Minigame/Hover", 0, 172, 24, "Bal'lak the Pummeller", -1, 46025, 1);
    addHoveredButton(46025, "Interfaces/Minigame/Hover", 1, 172, 24, 46026);

    addHoverButton(46027, "Interfaces/Minigame/Hover", 0, 172, 24, "Corporeal Beast", -1, 46028, 1);
    addHoveredButton(46028, "Interfaces/Minigame/Hover", 1, 172, 24, 46029);

    addHoverButton(46030, "Interfaces/Minigame/Hover", 0, 172, 24, "More Soon", -1, 46031, 1);
    addHoveredButton(46031, "Interfaces/Minigame/Hover", 1, 172, 24, 46032);

    addHoverButton(46033, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 46034, 1);
    addHoveredButton(46034, "Interfaces/Minigame/Back", 1, 16, 16, 46035);

    addSprite(46005, 1, "Interfaces/Minigame/Blank");
    addSprite(46006, 1, "Interfaces/Minigame/Blank");
    addSprite(46007, 1, "Interfaces/Minigame/Blank");
    addSprite(46008, 1, "Interfaces/Minigame/Blank");
    addSprite(46009, 1, "Interfaces/Minigame/Blank");
    addSprite(46010, 1, "Interfaces/Minigame/Blank");
    addSprite(46011, 1, "Interfaces/Minigame/Background");

    addText(46012, "Unholy cursebearer", 14056233, true, true, 52, rsFonts, 2);
    addText(46013, "Tormented Deamon", 14056233, true, true, 52, rsFonts, 2);
    addText(46014, "Plane-freezer Lakhrahnaz", 14056233, true, true, 52, rsFonts, 2);
    addText(46015, "Bal'lak the Pummeller", 14056233, true, true, 52, rsFonts, 2);
    addText(46016, "Corporeal Beast", 14056233, true, true, 52, rsFonts, 2);
    addText(46017, "More Soon", 14056233, true, true, 52, rsFonts, 2);

    int i = 28;
    int j = 0;
    setChildren(i, Interface);

    setBounds(46011, -1, 26, j, Interface); ++j;

    setBounds(46001, 33, 7, j, Interface); ++j;

    setBounds(46002, 8, 35, j, Interface); ++j;
    setBounds(46003, 8, 35, j, Interface); ++j;
    setBounds(46012, 80, 39, j, Interface); ++j;

    setBounds(46018, 8, 72, j, Interface); ++j;
    setBounds(46019, 8, 72, j, Interface); ++j;
    setBounds(46013, 80, 76, j, Interface); ++j;

    setBounds(46021, 8, 109, j, Interface); ++j;
    setBounds(46022, 8, 109, j, Interface); ++j;
    setBounds(46014, 80, 113, j, Interface); ++j;

    setBounds(46024, 8, 146, j, Interface); ++j;
    setBounds(46025, 8, 146, j, Interface); ++j;
    setBounds(46015, 80, 150, j, Interface); ++j;

    setBounds(46027, 8, 183, j, Interface); ++j;
    setBounds(46028, 8, 183, j, Interface); ++j;
    setBounds(46016, 80, 187, j, Interface); ++j;

    setBounds(46030, 8, 220, j, Interface); ++j;
    setBounds(46031, 8, 220, j, Interface); ++j;
    setBounds(46017, 80, 224, j, Interface); ++j;

    setBounds(46005, 148, 33, j, Interface); ++j;

    setBounds(46006, 148, 70, j, Interface); ++j;
    setBounds(46007, 148, 104, j, Interface); ++j;
    setBounds(46008, 148, 140, j, Interface); ++j;
    setBounds(46009, 148, 179, j, Interface); ++j;
    setBounds(46010, 148, 219, j, Interface); ++j;

    setBounds(46033, 10, 6, j, Interface); ++j;
    setBounds(46034, 10, 6, j, Interface); ++j;
  }  
	public static void addTooltip(int id, int frame, int X, int Y, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.interfaceShown = true;
		rsi.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
		rsi.children[frame] = id;
		rsi.childX[frame] = X;
		rsi.childY[frame] = Y;
	}
	public static RSInterface addInterface(int id) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.id = id;
		rsi.parentID = id;
		rsi.width = 512;
		rsi.height = 334;
		return rsi;
	}
	public static RSInterface addTabInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 700;
		tab.opacity = (byte)0;
		tab.hoverType = -1;
		return tab;
	}
	public static RSInterface addScreenInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = (byte)0;
		tab.hoverType = 0;
		return tab;
	}
	public static RSInterface addTab(int i) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 512;
		rsinterface.height = 334;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 0;
		return rsinterface;
	}
	public static void addButton(int id, int sid, String spriteName, String tooltip) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = tab.disabledSprite.myWidth;
		tab.height = tab.enabledSprite.myHeight;
		tab.tooltip = tooltip;
	}

  public static void Rules(RSFont[] tda)
  {
		RSInterface tab = addTabInterface(17250);
    addSprite(17251, 0, "Interfaces/Rules/WELCOME");
    addHoverButton(17252, "Interfaces/Rules/SPRITE", 6, 78, 39, "Accept", -1, 17253, 1);
    addHoveredButton(17253, "Interfaces/Rules/SPRITE", 7, 78, 39, 17254);
    addHoverButton(17255, "Interfaces/Rules/SPRITE", 6, 78, 39, "Decline", -1, 17256, 1);
    addHoveredButton(17256, "Interfaces/Rules/SPRITE", 7, 78, 39, 17257);
        	addText(17800, "Accept", tda, 0, 0x33ff00, false, true);
       		addText(17801, "Decline", tda, 0, 0xff0000, false, true);
		addText(17802, "Rules of Your server:", tda, 2, 0xff981f, false, true);
        	addText(17803, "Failure to agree with the rules will get you banned", tda, 0, 0xff981f, false, true);
		addText(17804, "", tda, 2, 0xff981f, false, true);
       		addText(17805, "", tda, 0, 0xff981f, false, true);
		addText(17806, "", tda, 2, 0xff981f, false, true);
       		addText(17807, "", tda, 0, 0xff981f, false, true);
		addText(17808, "", tda, 2, 0xff981f, false, true);
       		addText(17809, "", tda, 0, 0xff981f, false, true);
		addText(17810, "", tda, 2, 0xff981f, false, true);
       		addText(17811, "", tda, 0, 0xff981f, false, true);
		tab.totalChildren(18);
		tab.child(0, 17251, 0, 0);
		tab.child(1, 17252, 140, 276);
		tab.child(2, 17253, 140, 276);
		tab.child(3, 17255, 280, 276);
		tab.child(4, 17256, 280, 276);
		tab.child(5, 17800, 159, 285);
		tab.child(6, 17801, 297, 285);
		tab.child(7, 17802, 22, 37);
		tab.child(8, 17803, 210, 40);
		tab.child(9, 17804, 26, 64);
		tab.child(10, 17805, 26, 79);
		tab.child(11, 17806, 26, 91);
		tab.child(12, 17807, 26, 106);
		tab.child(13, 17808, 26, 118);
		tab.child(14, 17809, 26, 133);
		tab.child(15, 17810, 26, 145);
		tab.child(16, 17811, 26, 160);
		tab.child(17, 14000, 0, 58);
		tab = addTabInterface(14000);
		tab.width = 476;
		tab.height = 213;
		tab.scrollMax = 405;
		for(int i = 14001; i <= 14030; i++){
		addText(i, "", tda, 1, 0xffffff, false, true);
		addText(14002, "Offensive Language:", tda, 2, 0xff981f, true, true);
       		addText(14003, "Offensive language including racism, sexism is not tolerated.", tda, 0, 0xff981f, true, true);
		addText(14005, "Staff Impersonation:", tda, 2, 0xff981f, true, true);
       		addText(14006, "Pretending to act like a member of the staff is not allowed.", tda, 0, 0xff981f, true, true);
       		addText(14008, "Scamming", tda, 2, 0xff981f, true, true);
       		addText(14009, "Scamming player's items is not allowed.", tda, 0, 0xff981f, true, true);
		addText(14011, "Bug Abuse:", tda, 2, 0xff981f, true, true);
       		addText(14012, "Abusing a glitch is not allowed, you must report it immediately", tda, 0, 0xff981f, true, true);
		addText(14014, "Advertising/Spamming:", tda, 2, 0xff981f, true, true);
       		addText(14015, "Advertising servers and spamming will not be tolerated.", tda, 0, 0xff981f, true, true);
		addText(14017, "Encouraging Rule Breaking:", tda, 2, 0xff981f, true, true);
       		addText(14018, "Encouraging others to break rules is strictly forbidden", tda, 0, 0xff981f, true, true);
		addText(14020, "Personal Information:", tda, 2, 0xff981f, true, true);
       		addText(14021, "Do not give out any personal information, keep it to yourself and the people you know.", tda, 0, 0xff981f, true, true);
		}
		tab.totalChildren(30);
		int Child = 0;
		int Y = 5;
		for(int i = 14001; i <= 14030; i++){
		tab.child(Child, i, 248, Y);
		Child++;
		Y += 13;
 }
  }
	public static void addButton(int i, int j, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.disabledSprite = imageLoader(j,name);
		RSInterface.enabledSprite = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	public static void addButton(int id, int sid, String spriteName, String tooltip, int w, int h) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = tooltip;
	}
	public static void addButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.opacity = 0;
		RSInterface.hoverType = hoverId;
		RSInterface.disabledSprite = imageLoader(j,name);
		RSInterface.enabledSprite = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	public static void addButton(int id, int sid, String spriteName, String tooltip, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		tab.inventoryhover = true;
	}
	public static void addButton(int id, int sid, String spriteName, String tooltip, int mOver, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.hoverType = mOver;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		tab.inventoryhover = true;
	}
	private static void addButton(int ID, int type, int hoverID, int dS, int eS, String NAME, int W, int H, String text, int configFrame, int configId) {
		RSInterface rsinterface = addInterface(ID);
		rsinterface.id = ID;
		rsinterface.parentID = ID;
		rsinterface.type = 5;
		rsinterface.atActionType = type;
		rsinterface.opacity = 0;
		rsinterface.hoverType = hoverID;
		rsinterface.disabledSprite = imageLoader(dS, NAME);
		rsinterface.enabledSprite = imageLoader(eS, NAME);
		rsinterface.width = W;
		rsinterface.height = H;
		rsinterface.valueCompareType = new int[1];
		rsinterface.requiredValues = new int[1];
		rsinterface.valueCompareType[0] = 1;
		rsinterface.requiredValues[0] = configId;
		rsinterface.valueIndexArray = new int[1][3];
		rsinterface.valueIndexArray[0][0] = 5;
		rsinterface.valueIndexArray[0][1] = configFrame;
		rsinterface.valueIndexArray[0][2] = 0;
		rsinterface.tooltip = text;
	}
	public static void addButtons(int id, int sid, String spriteName, String tooltip, int mOver, int atAction) {
		RSInterface rsinterface = interfaceCache[id] = new RSInterface();
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 5;
		rsinterface.atActionType = atAction;
		rsinterface.contentType = 0;
		rsinterface.opacity = (byte)0;
		rsinterface.hoverType = mOver;
		rsinterface.disabledSprite = imageLoader(sid, spriteName);
		rsinterface.enabledSprite = imageLoader(sid, spriteName);
		rsinterface.width = rsinterface.disabledSprite.myWidth;
		rsinterface.height = rsinterface.enabledSprite.myHeight;
		rsinterface.tooltip = tooltip;
		rsinterface.inventoryhover = true;
	}
	public static void addToggleButton(int id, int sprite, int setconfig, int width, int height, String tooltip, int mOver) {
		RSInterface rsi = addInterface(id);
		rsi.disabledSprite = CustomSpriteLoader(sprite, "");
		rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
		rsi.requiredValues = new int[1];
		rsi.requiredValues[0] = 1;
		rsi.valueCompareType = new int[1];
		rsi.valueCompareType[0] = 1;
		rsi.valueIndexArray = new int[1][3];
		rsi.valueIndexArray[0][0] = 5;
		rsi.valueIndexArray[0][1] = setconfig;
		rsi.valueIndexArray[0][2] = 0;
		rsi.atActionType = 4;
		rsi.width = width;
		rsi.hoverType = mOver;
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
		rsi.height = height;
		rsi.tooltip = tooltip;
	}
	public static void addToggleButton(int id, int sprite, int setconfig, int width, int height, String s) {
		RSInterface rsi = addInterface(id);
		rsi.disabledSprite = CustomSpriteLoader(sprite, "");
		rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
		rsi.requiredValues = new int[1];
		rsi.requiredValues[0] = 1;
		rsi.valueCompareType = new int[1];
		rsi.valueCompareType[0] = 1;
		rsi.valueIndexArray = new int[1][3];
		rsi.valueIndexArray[0][0] = 5;
		rsi.valueIndexArray[0][1] = setconfig;
		rsi.valueIndexArray[0][2] = 0;
		rsi.atActionType = 4;
		rsi.width = width;
		rsi.hoverType = -1;
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
		rsi.height = height;
		rsi.tooltip = s;
	}
	public static void addActionButton(int id, int sprite, int enabledSprite, int width, int height, String s) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.disabledSprite = CustomSpriteLoader(sprite, "");
		if (enabledSprite == sprite)
		  rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
		else
		  rsi.enabledSprite = CustomSpriteLoader(enabledSprite, "");
		rsi.tooltip = s;
		rsi.contentType = 0;
		rsi.atActionType = 1;
		rsi.width = width;
		rsi.hoverType = 52;
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
		rsi.height = height;
	}

//newteleport(rsFonts); 
  
   public static void newteleport(RSFont[] rsFonts) {
    RSInterface localRSInterface = addInterface(27000);
    addSprite(27951, 10, "CLICK");
    addHoverButton(27952, "CLICK", 2, 200, 30, "Which Zone?", -1, 27953, 1);
    addHoveredButton(27953, "CLICK", 2, 200, 30, 27954);
    addHoverButton(27955, "CLICK", 3, 200, 30, "Which Zone?", -1, 27956, 1);
    addHoveredButton(27956, "CLICK", 3, 200, 30, 27957);
    addHoverButton(27958, "CLICK", 3, 200, 30, "Which Zone?", -1, 27959, 1);
    addHoveredButton(27959, "CLICK", 3, 200, 30, 27960);
    addHoverButton(27961, "CLICK", 3, 200, 30, "Which Zone?", -1, 27962, 1);
    addHoveredButton(27962, "CLICK", 3, 200, 30, 27963);
    addHoverButton(27964, "CLICK", 3, 200, 30, "Which Zone?", -1, 27965, 1);
    addHoveredButton(27965, "CLICK", 3, 200, 30, 27966);
    addHoverButton(27967, "CLICK", 3, 200, 30, "Which Zone?", -1, 27968, 1);
    addHoveredButton(27968, "CLICK", 3, 200, 30, 27969);
    addHoverButton(27970, "CLICK", 3, 200, 30, "Which Zone?", -1, 27971, 1);
    addHoveredButton(27971, "CLICK", 3, 200, 30, 27972);
    addHoverButton(27973, "CLICK", 1, 200, 30, "Stop Viewing", -1, 27974, 1);
    addHoveredButton(27974, "CLICK", 1, 200, 30, 27975);
    addText(32804, "Tormented Deamon", rsFonts, 0, 16750623, false, true);
    addText(32808, "Plane-freezer Lakhrahnaz", rsFonts, 0, 16750623, false, true);
    addText(32812, "Bal'lak the Pummeller", rsFonts, 0, 16750623, false, true);
    addText(32816, "Corporeal Beast", rsFonts, 0, 16750623, false, true);
    addText(32820, "More", rsFonts, 0, 16750623, false, true);
    addText(32824, "Coming", rsFonts, 0, 16750623, false, true);
    addText(32828, "Soon!", rsFonts, 0, 16750623, false, true);
    addText(32800, "", rsFonts, 0, 16750623, false, true);
    localRSInterface.totalChildren(25);
    localRSInterface.child(0, 37951, 0, 0);
    localRSInterface.child(1, 37952, 12, 40);
    localRSInterface.child(2, 37953, 11, 40);
    localRSInterface.child(3, 37955, 12, 65);
    localRSInterface.child(4, 37956, 11, 65);
    localRSInterface.child(5, 32800, 68, 78);
    localRSInterface.child(6, 37958, 12, 90);
    localRSInterface.child(7, 37959, 11, 90);
    localRSInterface.child(8, 37961, 12, 115);
    localRSInterface.child(9, 37962, 11, 115);
    localRSInterface.child(10, 37964, 12, 143);
    localRSInterface.child(11, 37965, 11, 143);
    localRSInterface.child(12, 37967, 12, 168);
    localRSInterface.child(13, 37968, 11, 168);
    localRSInterface.child(14, 37970, 12, 193);
    localRSInterface.child(15, 37971, 11, 193);
    localRSInterface.child(16, 37973, 38, 236);
    localRSInterface.child(17, 37974, 38, 236);
    localRSInterface.child(18, 32804, 38, 45);
    localRSInterface.child(19, 32808, 38, 70);
    localRSInterface.child(20, 32812, 38, 95);
    localRSInterface.child(21, 32816, 38, 120);
    localRSInterface.child(22, 32820, 38, 147);
    localRSInterface.child(23, 32824, 38, 174);
    localRSInterface.child(24, 32828, 38, 201);
    localRSInterface = addTabInterface(49000);
    localRSInterface.width = 474;
    localRSInterface.height = 213;
    localRSInterface.scrollMax = 305;
    for (int i = 14001; i <= 14030; ++i) {
      addText(i, "", rsFonts, 1, 16777215, false, true);
    }
    localRSInterface.totalChildren(30);
    int i = 0;
    int j = 5;
    for (int k = 14001; k <= 14030; ++k) {
      localRSInterface.child(i, k, 248, j);
      ++i;
      j += 13;
    }
  } 
	public static void addActionButton(int id, int sprite, int enabledSprite, int width, int height, String s, int mOver) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.disabledSprite = CustomSpriteLoader(sprite, "");
		if (enabledSprite == sprite)
		  rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
		else
		  rsi.enabledSprite = CustomSpriteLoader(enabledSprite, "");
		rsi.tooltip = s;
		rsi.contentType = 0;
		rsi.atActionType = 1;
		rsi.width = width;
		rsi.hoverType = mOver;
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
		rsi.height = height;
	}
	public static void addActionButton1(int i, int j, int k, int width, int height, String s, int mOver) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 5;
		rsinterface.atActionType = 1;
		rsinterface.contentType = 0;
		rsinterface.width = width;
		rsinterface.height = height;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 52;
		rsinterface.disabledSprite = imageLoader(j, "Interfaces/Equipment/BOX");
		rsinterface.enabledSprite = imageLoader(k, "Interfaces/Equipment/BOX");
		rsinterface.tooltip = s;
	}
	public static void addConfigButton(int ID, int pID, int bID, int bID2, String bName, int width, int height, String tT, int configID, int aT, int configFrame) {
		RSInterface Tab = addTabInterface(ID);
		Tab.parentID = pID;
		Tab.id = ID;
		Tab.type = 5;
		Tab.atActionType = aT;
		Tab.contentType = 0;
		Tab.width = width;
		Tab.height = height;
		Tab.opacity = 0;
		Tab.hoverType = -1;
		Tab.valueCompareType = new int[1];
		Tab.requiredValues = new int[1];
		Tab.valueCompareType[0] = 1;
		Tab.requiredValues[0] = configID;
		Tab.valueIndexArray = new int[1][3];
		Tab.valueIndexArray[0][0] = 5;
		Tab.valueIndexArray[0][1] = configFrame;
		Tab.valueIndexArray[0][2] = 0;
		Tab.disabledSprite = imageLoader(bID, bName);
		Tab.enabledSprite = imageLoader(bID2, bName);
		Tab.tooltip = tT;
	    }
	public static void addHoverButton(int i, String imageName, int j, int width, int height, String text, int contentType, int hoverOver, int aT) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.opacity = 0;
		tab.hoverType = hoverOver;
		tab.disabledSprite = imageLoader(j, imageName);
		tab.enabledSprite = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
	public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.type = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.interfaceShown = true;
		tab.opacity = 0;
		tab.hoverType = -1;
		tab.scrollMax = 0;
		addHoverImage(IMAGEID, j, j, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}
	public static void addHover(int i, int aT, int cT, int hoverid,int sId, String NAME, int W, int H, String tip) { 
		RSInterface rsinterfaceHover = addInterface(i);
		rsinterfaceHover.id = i;
		rsinterfaceHover.parentID = i;
		rsinterfaceHover.type = 5;
		rsinterfaceHover.atActionType = aT;
		rsinterfaceHover.contentType = cT;
		rsinterfaceHover.hoverType = hoverid;
		rsinterfaceHover.disabledSprite = imageLoader(sId, NAME);
		rsinterfaceHover.enabledSprite = imageLoader(sId, NAME);
		rsinterfaceHover.width = W;
		rsinterfaceHover.height = H;
		rsinterfaceHover.tooltip = tip;
	}
	public static void addHovered(int i, int j, String imageName, int w, int h, int IMAGEID) {
		RSInterface rsinterfaceHover = addInterface(i);
		rsinterfaceHover.parentID = i;
		rsinterfaceHover.id = i;
		rsinterfaceHover.type = 0;
		rsinterfaceHover.atActionType = 0;
		rsinterfaceHover.width = w;
		rsinterfaceHover.height = h;
		rsinterfaceHover.interfaceShown = true;
		rsinterfaceHover.hoverType = -1;
		addSprite(IMAGEID, j, imageName);
		setChildren(1, rsinterfaceHover);
		setBounds(IMAGEID, 0, 0, 0, rsinterfaceHover);
	}
	public static void addHoverNoTooltip(int i, int aT, int cT, int hoverid,int sId, String NAME, int W, int H) { 
		RSInterface rsinterfaceHover = addInterface(i);
		rsinterfaceHover.id = i;
		rsinterfaceHover.parentID = i;
		rsinterfaceHover.type = 5;
		rsinterfaceHover.atActionType = aT;
		rsinterfaceHover.contentType = cT;
		rsinterfaceHover.hoverType = hoverid;
		rsinterfaceHover.disabledSprite = imageLoader(sId, NAME);
		rsinterfaceHover.enabledSprite = imageLoader(sId, NAME);
		rsinterfaceHover.width = W;
		rsinterfaceHover.height = H;
		rsinterfaceHover.tooltip = null;
	}
	public static void addText(int i, String s, int k, boolean l, boolean m, int a, int j) {
		RSInterface rsinterface = addTabInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 0;
		rsinterface.height = 0;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = a;
		rsinterface.textCentered = l;
		rsinterface.textShadowed = m;
		rsinterface.rsFonts = RSInterface.fonts[j];
		rsinterface.disabledMessage = s;
		rsinterface.disabledColor = k;
	}
	public static void addText(int id, String text, RSFont wid[], int idx, int color) {
		RSInterface rsinterface = addTab(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.textCentered = false;
		rsinterface.textShadowed = true;
		rsinterface.rsFonts = wid[idx];
		rsinterface.disabledMessage = text;
		rsinterface.enabledMessage = "";
		rsinterface.disabledColor = color;
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = 0;
		rsinterface.enabledHoverColor = 0;	
	}
	public static void addText(int id, String text, RSFont tda[], int idx, int color, boolean center) {
		RSInterface rsinterface = addInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 0;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.textCentered = center;
		rsinterface.textShadowed = true;
		rsinterface.rsFonts = tda[idx];
		rsinterface.disabledMessage = text;
		rsinterface.enabledMessage = "";
		rsinterface.disabledColor = color;
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = 0;
		rsinterface.enabledHoverColor = 0;	
	}
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,RSFont[] TDA, int j) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.parentID = i;
		RSInterface.id = i;
		RSInterface.type = 4;
		RSInterface.atActionType = 0;
		RSInterface.width = 0;
		RSInterface.height = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = a;
		RSInterface.textCentered = l;
		RSInterface.textShadowed = m;
		RSInterface.rsFonts = TDA[j];
		RSInterface.disabledMessage = s;
		RSInterface.enabledMessage = "";
		RSInterface.disabledColor = k;
	}
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,RSFont[] TDA, int j, int dsc) {
		RSInterface rsinterface = addInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		rsinterface.type = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = a;
		rsinterface.textCentered = l;
		rsinterface.textShadowed = m;
		rsinterface.rsFonts = TDA[j];
		rsinterface.disabledMessage = s;
		rsinterface.enabledMessage = "";
		rsinterface.enabledColor = 0;
		rsinterface.disabledColor = k;
		rsinterface.enabledHoverColor = dsc;
		rsinterface.tooltip = s;
	}
	public static void addText(int id, String text, RSFont tda[], int idx, int color, boolean centered, boolean textShadowed) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		if(centered)
			rsi.textCentered = true;
			rsi.textShadowed = textShadowed;
			rsi.rsFonts = tda[idx];
			rsi.disabledMessage = text;
			rsi.disabledColor = color;
			rsi.id = id;
			rsi.type = 4;
	}
	public static void addText(int i, String s, String tooltip, int k, boolean l, boolean m, int a,RSFont[] TDA, int j, int dsc) {
		RSInterface rsinterface = addInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		rsinterface.type = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = a;
		rsinterface.textCentered = l;
		rsinterface.textShadowed = m;
		rsinterface.rsFonts = TDA[j];
		rsinterface.disabledMessage = s;
		rsinterface.enabledMessage = "";
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = k;
		rsinterface.enabledHoverColor = dsc;
		rsinterface.tooltip = tooltip;
	}
	public static void addHoverText(int id, String text, String tooltip, RSFont tda[], int idx, int color, boolean center, boolean textShadowed, int width) {
		RSInterface rsinterface = addInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = width;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.textCentered = center;
		rsinterface.textShadowed = textShadowed;
		rsinterface.rsFonts = tda[idx];
		rsinterface.disabledMessage = text;
		rsinterface.enabledMessage = "";
		rsinterface.disabledColor = color;
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = 0xffffff;
		rsinterface.enabledHoverColor = 0;
		rsinterface.tooltip = tooltip;
	}
public static void addSprite(int id, int spriteId, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(spriteId, spriteName);
		tab.enabledSprite = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
	}
	
public static void addSprite(int i, int j, int k) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 5;
		rsinterface.atActionType = 1;
		rsinterface.contentType = 0;
		rsinterface.width = 20;
		rsinterface.height = 20;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 52;
		rsinterface.disabledSprite = imageLoader(j, "Interfaces/Equipment/SPRITE");
		rsinterface.enabledSprite = imageLoader(k, "Interfaces/Equipment/SPRITE");
	}


	public static void addSprite(int paramInt, String paramString) {
    RSInterface rsinterface = interfaceCache[paramInt] = new RSInterface();
    rsinterface.id = paramInt;
    rsinterface.parentID = paramInt;
    rsinterface.type = 5;
    rsinterface.atActionType = 0;
    rsinterface.contentType = 0;
    rsinterface.opacity = 0;
    rsinterface.hoverType = 52;
    rsinterface.disabledSprite = imageLoader2(paramString);
    rsinterface.enabledSprite = imageLoader2(paramString);
    rsinterface.width = 512;
    rsinterface.height = 334; 

}
	public static void addCacheSprite(int id, int disabledSprite, int enabledSprite, String sprites) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.disabledSprite = method207(disabledSprite, aClass44, sprites);
		rsi.enabledSprite = method207(enabledSprite, aClass44, sprites);
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
	}
	public static void addTransparentSprite(int id, int spriteId, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(spriteId, spriteName);
		tab.enabledSprite = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}

  private static Sprite imageLoader2(String paramString) {
    long l = TextClass.method585(paramString) << 8;
    Sprite localSprite = (Sprite)spriteNodes.insertFromCache(l);
    if (localSprite != null)
      return localSprite;
    try {
      localSprite = new Sprite(paramString);
      spriteNodes.removeFromCache(localSprite, l);
    } catch (Exception localException) {
      return null;
    }
    return localSprite; 
}
	public static void addPrayerWithTooltip(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, int Hover, String tooltip) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 5608;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.hoverType = Hover;
		Interface.disabledSprite = imageLoader(0, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.enabledSprite = imageLoader(1, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 1;
		Interface.requiredValues[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = tooltip;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 5608;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.opacity = 0;
		Interface.disabledSprite = imageLoader(prayerSpriteID, "Interfaces/PrayerTab/PRAYERON");
		Interface.enabledSprite = imageLoader(prayerSpriteID, "Interfaces/PrayerTab/PRAYEROFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 2;
		Interface.requiredValues[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}

 
	public static void addPrayer(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, String PrayerName, int Hover) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.hoverType = Hover;
		Interface.disabledSprite = imageLoader(0, "Interfaces/CurseTab/GLOW");
		Interface.enabledSprite = imageLoader(1, "Interfaces/CurseTab/GLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 1;
		Interface.requiredValues[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = "Select@lre@ " + PrayerName;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.opacity = 0;
		Interface.disabledSprite = imageLoader(prayerSpriteID, "Interfaces/CurseTab/PRAYON");
		Interface.enabledSprite = imageLoader(prayerSpriteID, "Interfaces/CurseTab/PRAYOFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 2;
		Interface.requiredValues[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}
	public static void addHoverImage(int i, int j, int k, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = 0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(j, name);
		tab.enabledSprite = imageLoader(k, name);
	}
	public static void addChar(int ID) { 
		RSInterface t = interfaceCache[ID] = new RSInterface(); 
		t.id = ID; 
		t.parentID = ID; 
		t.type = 6;
		t.atActionType = 0; 
		t.contentType = 328; 
		t.width = 136; 
		t.height = 168; 
		t.opacity = 0;
		t.hoverType = 0;
		t.modelZoom = 560;
		t.modelRotationY = 150;
		t.modelRotationX = 0; 
		t.disabledAnimation = -1; 
		t.enabledAnimation = -1; 
	}
	public static void addLunarSprite(int i, int j, String name) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.disabledSprite = imageLoader(j, name);
		RSInterface.width = 500;
		RSInterface.height = 500;
		RSInterface.tooltip = "";
	}
	public static void drawRune(int i, int id, String runeName) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.type = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.disabledSprite = imageLoader(id, "Interfaces/Lunar/Rune");
		RSInterface.width = 500;
		RSInterface.height = 500;
	}
	public static void addRuneText(int ID, int runeAmount, int RuneID, RSFont[] tda) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 4;
		rsInterface.atActionType = 0;
		rsInterface.contentType = 0;
		rsInterface.width = 0;
		rsInterface.height = 14;
		rsInterface.opacity = 0;
		rsInterface.hoverType = -1;
		rsInterface.valueCompareType = new int[1];
		rsInterface.requiredValues = new int[1];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = runeAmount;
		rsInterface.valueIndexArray = new int[1][4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = RuneID;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.textCentered = true;
		rsInterface.rsFonts = tda[0];
		rsInterface.textShadowed = true;
		rsInterface.disabledMessage = "%1/" + runeAmount + "";
		rsInterface.enabledMessage = "";
		rsInterface.disabledColor = 12582912;
		rsInterface.enabledColor = 49152;
	}
	public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1,
			int ra2, int rune1, int lvl, String name, String descr,
			RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast On";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[3];
		rsInterface.requiredValues = new int[3];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = lvl;
		rsInterface.valueIndexArray = new int[3][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[3];
		rsInterface.valueIndexArray[2][0] = 1;
		rsInterface.valueIndexArray[2][1] = 6;
		rsInterface.valueIndexArray[2][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(7, hover);
		addLunarSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 37, 35, 3, hover);// Rune
		setBounds(rune1, 112, 35, 4, hover);// Rune
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 50, 66, 5, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 123, 66, 6, hover);
	}
	
	public static void addLunar3RunesSmallBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 14, 35, 3, hover);
		setBounds(rune1, 74, 35, 4, hover);
		setBounds(rune2, 130, 35, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 66, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 66, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 66, 8, hover);
	}
	
	public static void addLunar3RunesBigBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 1, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 21, 2, hover);
		setBounds(30016, 14, 48, 3, hover);
		setBounds(rune1, 74, 48, 4, hover);
		setBounds(rune2, 130, 48, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 79, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 79, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 79, 8, hover);
	}
	
	public static void addLunar3RunesLargeBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.interfaceShown = true;
		hover.hoverType = -1;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 2, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 34, 2, hover);
		setBounds(30016, 14, 61, 3, hover);
		setBounds(rune1, 74, 61, 4, hover);
		setBounds(rune2, 130, 61, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 92, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 92, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 92, 8, hover);
	}
	
	public static void homeTeleport() {
		RSInterface RSInterface = addTabInterface(30000);
		RSInterface.tooltip = "Cast @gre@Lunar Home Teleport";
		RSInterface.id = 30000;
		RSInterface.parentID = 30000;
		RSInterface.type = 5;
		RSInterface.atActionType = 5;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 30001;
		RSInterface.disabledSprite = imageLoader(1, "Interfaces/Lunar/SPRITE");
		RSInterface.width = 20;
		RSInterface.height = 20;
		RSInterface hover = addTabInterface(30001);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(1, hover);
		addLunarSprite(30002, 0, "Interfaces/Lunar/SPRITE");
		setBounds(30002, 0, 0, 0, hover);
	}
	
	public static void GodWars(RSFont[] TDA) {
		RSInterface rsinterface = addTab(16210);
		addText(16211, "NPC killcount", TDA, 0, 0xff9040);
		addText(16212, "Armadyl kills", TDA, 0, 0xff9040);
		addText(16213, "Bandos kills", TDA, 0, 0xff9040);
		addText(16214, "Saradomin kills", TDA, 0, 0xff9040);
		addText(16215, "Zamorak kills", TDA, 0, 0xff9040);
		addText(16216, "0", TDA, 0, 0x66FFFF);//armadyl
		addText(16217, "0", TDA, 0, 0x66FFFF);//bandos
		addText(16218, "0", TDA, 0, 0x66FFFF);//saradomin
		addText(16219, "0", TDA, 0, 0x66FFFF);//zamorak
		rsinterface.scrollMax = 0;
		rsinterface.children = new int[9];
		rsinterface.childX = new int[9];
		rsinterface.childY = new int[9];
		rsinterface.children[0] = 16211;
		rsinterface.childX[0] = -52+375+30;
		rsinterface.childY[0] = 7;
		rsinterface.children[1] = 16212;
		rsinterface.childX[1] = -52+375+30;
		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 16213;
		rsinterface.childX[2] = -52+375+30;
		rsinterface.childY[2] = 44;
		rsinterface.children[3] = 16214;
		rsinterface.childX[3] = -52+375+30;
		rsinterface.childY[3] = 58;
		rsinterface.children[4] = 16215;
		rsinterface.childX[4] = -52+375+30;
		rsinterface.childY[4] = 73;
		rsinterface.children[5] = 16216;
		rsinterface.childX[5] = -52+460+60;
		rsinterface.childY[5] = 31;
		rsinterface.children[6] = 16217;
		rsinterface.childX[6] = -52+460+60;
		rsinterface.childY[6] = 45;
		rsinterface.children[7] = 16218;
		rsinterface.childX[7] = -52+460+60;
		rsinterface.childY[7] = 59;
		rsinterface.children[8] = 16219;
		rsinterface.childX[8] = -52+460+60;
		rsinterface.childY[8] = 74;
	}
	
	public static void magicTab(RSFont[] tda) {
		RSInterface tab = addTabInterface(1151);
		RSInterface homeHover = addTabInterface(1196);
		RSInterface spellButtons = interfaceCache[12424];
		spellButtons.scrollMax = 0; spellButtons.height = 260; spellButtons.width = 190;
		int[] spellButton = {
			1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
			1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
			1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
			1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
			1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471
		};
		tab.totalChildren(63);
		tab.child(0, 12424, 13, 24);
		for(int i1 = 0; i1 < spellButton.length; i1++) {
			int yPos = i1 > 34 ? 8 : 183;
			tab.child(1, 1195, 13, 24);
			tab.child(i1 + 2, spellButton[i1], 5, yPos);
			addButton(1195, 1, "Interfaces/Magic/Home", "Cast @gre@Home Teleport");
			RSInterface homeButton = interfaceCache[1195];
			homeButton.hoverType = 1196;
		}
		for(int i2 = 0; i2 < spellButton.length; i2++) {
			if(i2 < 60)
				spellButtons.childX[i2] = spellButtons.childX[i2] + 24;
			if(i2 == 6 || i2 == 12 || i2 == 19 || i2 == 35 || i2 == 41 || i2 == 44 || i2 == 49 || i2 == 51)
				spellButtons.childX[i2] = 0;
			spellButtons.childY[6] = 24; spellButtons.childY[12] = 48;
			spellButtons.childY[19] = 72; spellButtons.childY[49] = 96;
			spellButtons.childY[44] = 120; spellButtons.childY[51] = 144;
			spellButtons.childY[35] = 170; spellButtons.childY[41] = 192;
		}
		homeHover.interfaceShown = true;
		addText(1197, "Level 0: Home Teleport", tda, 1, 0xFE981F, true, true);
		RSInterface homeLevel = interfaceCache[1197]; homeLevel.width = 174; homeLevel.height = 68;
		addText(1198, "Requires no runes - recharge time", 0xAF6A1A, true, true, 52, 0);
		addText(18998, "30mins. Warning: This spell takes a", 0xAF6A1A, true, true, 52, 0);
		addText(18999, "long time to cast and will be", 0xAF6A1A, true, true, 52, 0);
		addText(19000, "interupted by combat.", 0xAF6A1A, true, true, 52, 0);
		homeHover.totalChildren(5);
		homeHover.child(0, 1197, 3, 4);
		homeHover.child(1, 1198, 91, 23);
		homeHover.child(2, 18998, 90, 34);
		homeHover.child(3, 18999, 91, 45);
		homeHover.child(4, 19000, 91, 56);
	}
	
	public static void ancientMagicTab(RSFont[] tda) {
		RSInterface tab = addInterface(12855);
		miasmicsSpells(tda);

		addButton(12856, 1, "Interfaces/Magic/Home", "Cast @gre@Home Teleport");
		RSInterface homeButton = interfaceCache[12856]; 
		homeButton.hoverType = 1196;

		int[] itfChildren = {
			12856, 12939, 12987, 13035, 12901,
			12861, 13045, 32833, 12963, 13011,
			13053, 12919, 12881, 13061, 32843,
			12951, 12999, 13069, 12911, 12871,
			13079, 32853, 12975, 13023, 13087, 
			12929, 12891, 13095, 32863,

			1196,  12940, 12988, 13036, 12902, 
			12862, 13046, 32834, 12964, 13012, 
			13054, 12920, 12882, 13062, 32844,
			12952, 13000, 13070, 12912, 12872, 
			13080, 32854, 12976, 13024, 13088, 
			12930, 12892, 13096, 32864
		};
		tab.totalChildren(itfChildren.length);

		for(int i1 = 0, xPos = 13, yPos = 10; i1 < itfChildren.length; i1++, xPos += 37) {
			if(xPos > 175) {
				xPos = 13; yPos += 30;
			}
			if(i1 < 29)
				tab.child(i1, itfChildren[i1], xPos, yPos);
			if(i1 > 28) {
				yPos = i1 < 41 ? 181 : 1;
				tab.child(i1, itfChildren[i1], 4, yPos);
			}
		}
	}

	public static void addMiasmicSpell(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune0, int rune1, int rune2, int lvl,
			String name, String descr, RSFont[] TDA, int sid) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = 2;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = 10;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 23;
		rsInterface.height = 23;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Magic/Miasmicon");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Magic/Miasmicoff");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;

		setChildren(9, hover);
		addLunarSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(rune0, 14, 35, 3, hover);
		setBounds(rune1, 74, 35, 4, hover);
		setBounds(rune2, 130, 35, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 66, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 66, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 66, 8, hover);
	}

	public static void miasmicsSpells(RSFont[] TDA) {
		int[][] data = {
			{60, 30015, 0, 30011, 1, 30006, 0, 560, 562, 557, 1}, // RUSH
			{72, 30015, 1, 30011, 3, 30006, 1, 560, 562, 557, 2}, // BURST
			{84, 30015, 2, 30014, 1, 30006, 2, 560, 565, 557, 3}, // BLITZ
			{96, 30015, 3, 30014, 3, 30006, 3, 560, 565, 557, 0}, // BARRAGE
		};
		String[] names = {
			"Rush", "Burst", "Blitz", "Barrage",
		};
		int id = 32833;
		for(int i = 0; i < data.length; i++) {
			addMiasmicSpell(id,data[i][7],data[i][8],data[i][9],data[i][2],data[i][4],data[i][6], data[i][1],data[i][3],data[i][5],data[i][0],
			"Miasmic "+ names[i], "Spell Discription", TDA, data[i][10]);
			id += 10;
		}
	}
	public static void prayerTab(RSFont[] TDA) {
		RSInterface RSI = addInterface(5608);
		int index = 0;
		addText(687, "", 0xff981f, false, true, -1, TDA, 1);
		addSprite(25105, 0, "Interfaces/PrayerTab/PRAYERICON");
		addPrayerWithTooltip(25000, 0, 83, 0, 0, 25052, "Select @lre@Thick Skin");
		addPrayerWithTooltip(25002, 0, 84, 3, 1, 25054, "Select @lre@Burst of Strength");
		addPrayerWithTooltip(25004, 0, 85, 6, 2, 25056, "Select @lre@Clarity of Thought");
		addPrayerWithTooltip(25006, 0, 601, 7, 3, 25058, "Select @lre@Sharp Eye");
		addPrayerWithTooltip(25008, 0, 602, 8, 4, 25060, "Select @lre@Mystic Will");
		addPrayerWithTooltip(25010, 0, 86, 9, 5, 25062, "Select @lre@Rock Skin");
		addPrayerWithTooltip(25012, 0, 87, 12, 6, 25064, "Select @lre@Superhuman Strength");
		addPrayerWithTooltip(25014, 0, 88, 15, 7, 25066, "Select @lre@Improved Reflexes");
		addPrayerWithTooltip(25016, 0, 89, 18, 8, 25068, "Select @lre@Rapid Restore");
		addPrayerWithTooltip(25018, 0, 90, 21, 9, 25070, "Select @lre@Rapid Heal");
		addPrayerWithTooltip(25020, 0, 91, 24, 10, 25072, "Select @lre@Protect Item");
		addPrayerWithTooltip(25022, 0, 603, 25, 11, 25074, "Select @lre@Hawk Eye");
		addPrayerWithTooltip(25024, 0, 604, 26, 12, 25076, "Select @lre@Mystic Lore");
		addPrayerWithTooltip(25026, 0, 92, 27, 13, 25078, "Select @lre@Steel Skin");
		addPrayerWithTooltip(25028, 0, 93, 30, 14, 25080, "Select @lre@Ultimate Strength");
		addPrayerWithTooltip(25030, 0, 94, 33, 15, 25082, "Select @lre@Incredible Reflexes");
		addPrayerWithTooltip(25032, 0, 95, 36, 16, 25084, "Select @lre@Protect from Magic");		
		addPrayerWithTooltip(25034, 0, 96, 39, 17, 25086, "Select @lre@Protect from Missles");
		addPrayerWithTooltip(25036, 0, 97, 42, 18, 25088, "Select @lre@Protect from Melee");	
		addPrayerWithTooltip(25038, 0, 605, 43, 19, 25090, "Select @lre@Eagle Eye");
		addPrayerWithTooltip(25040, 0, 606, 44, 20, 25092, "Select @lre@Mystic Might");
		addPrayerWithTooltip(25042, 0, 98, 45, 21, 25094, "Select @lre@Retribution");
		addPrayerWithTooltip(25044, 0, 99, 48, 22, 25096, "Select @lre@Redemption");
		addPrayerWithTooltip(25046, 0, 100, 51, 23, 25098, "Select @lre@Smite");
		addPrayerWithTooltip(25048, 0, 607, 59, 24, 25100, "Select @lre@Chivalry");
		addPrayerWithTooltip(25050, 0, 608, 69, 25, 25102, "Select @lre@Piety");
		addGenericTabTooltip(25052, 34, 34, "Level 01\\nThick Skin\\nIncreases your Defence by 5%");
		addGenericTabTooltip(25054, 34, 34, "Level 04\\nBurst of Strength\\nIncreases your Strength by 5%");
		addGenericTabTooltip(25056, 34, 34, "Level 07\\nClarity of Thought\\nIncreases your Attack by 5%");
		addGenericTabTooltip(25058, 34, 34, "Level 08\\nSharp Eye\\nIncreases your Ranged by 5%");
		addGenericTabTooltip(25060, 34, 34, "Level 09\\nMystic Will\\nIncreases your Magic by 5%");
		addGenericTabTooltip(25062, 34, 34, "Level 10\\nRock Skin\\nIncreases your Defence by 10%");
		addGenericTabTooltip(25064, 34, 34, "Level 13\\nSuperhuman Strength\\nIncreases your Strength by 10%");
		addGenericTabTooltip(25066, 34, 34, "Level 16\\nImproved Reflexes\\nIncreases your Attack by 10%");
		addGenericTabTooltip(25068, 34, 34, "Level 19\\nRapid Restore\\n2x restore rate for all stats\\nexcept Hitpoints and Prayer");
		addGenericTabTooltip(25070, 34, 34, "Level 22\\nRapid Heal\\n2x restore rate for the\\nHitpoints stat");
		addGenericTabTooltip(25072, 34, 34, "Level 25\\nProtect Item\\nKeep 1 extra item if you die");
		addGenericTabTooltip(25074, 34, 34, "Level 26\\nHawk Eye\\nIncreases your Ranged by 10%");
		addGenericTabTooltip(25076, 34, 34, "Level 27\\nMystic Lore\\nIncreases your Magic by 10%");
		addGenericTabTooltip(25078, 34, 34, "Level 28\\nSteel Skin\\nIncreases your Defence by 15%");
		addGenericTabTooltip(25080, 34, 34, "Level 31\\nUltimate Strength\\nIncreases your Strength by 15%");
		addGenericTabTooltip(25082, 34, 34, "Level 34\\nIncredible Reflexes\\nIncreases your Attack by 15%");
		addGenericTabTooltip(25084, 34, 34, "Level 37\\nProtect from Magic\\nProtection from magical attacks");
		addGenericTabTooltip(25086, 34, 34, "Level 40\\nProtect from Missles\\nProtection from ranged attacks");
		addGenericTabTooltip(25088, 34, 34, "Level 43\\nProtect from Melee\\nProtection from melee attacks");
		addGenericTabTooltip(25090, 34, 34, "Level 44\\nEagle Eye\\nIncreases your Ranged by 15%");
		addGenericTabTooltip(25092, 34, 34, "Level 45\\nMystic Might\\nIncreases your Magic by 15%");
		addGenericTabTooltip(25094, 34, 34, "Level 46\\nRetribution\\nInflicts damage to nearby\\ntargets if you die");
		addGenericTabTooltip(25096, 34, 34, "Level 49\\nRedemption\\nHeals you when damaged\\nand Hitpoints falls\\nbelow 10%");
		addGenericTabTooltip(25098, 34, 34, "Level 52\\nSmite\\n1/4 of damage dealt is\\nalso removed from\\nopponent's Prayer");
		addGenericTabTooltip(25100, 34, 34, "Level 60\\nChivalry\\nIncreases your Defence by\\n20%,Strength by 18%, and\\nAttack by 15%");
		addGenericTabTooltip(25102, 34, 34, "Level 70\\nPiety\\nIncreases your Defence by 25%,\\nStrength by 23%, and Attack by\\n20%");
		setChildren(80, RSI);
		setBounds(687, 85, 241, index, RSI); index++;
		setBounds(25105, 65, 241, index, RSI); index++;
		setBounds(25000, 2, 5, index, RSI); index++;
		setBounds(25001, 5, 8, index, RSI); index++;
		setBounds(25002, 40, 5, index, RSI); index++;
		setBounds(25003, 44, 8, index, RSI); index++;
		setBounds(25004, 76, 5, index, RSI); index++;
		setBounds(25005, 79, 11, index, RSI); index++;
		setBounds(25006, 113, 5, index, RSI); index++;
		setBounds(25007, 116, 10, index, RSI); index++;
		setBounds(25008, 150, 5, index, RSI); index++;
		setBounds(25009, 153, 9, index, RSI); index++;
		setBounds(25010, 2, 45, index, RSI); index++;
		setBounds(25011, 5, 48, index, RSI); index++;
		setBounds(25012, 39, 45, index, RSI); index++;	
		setBounds(25013, 44, 47, index, RSI); index++;
		setBounds(25014, 76, 45, index, RSI); index++;			
		setBounds(25015, 79, 49, index, RSI); index++;
		setBounds(25016, 113, 45, index, RSI); index++;		
		setBounds(25017, 116, 50, index, RSI); index++;
		setBounds(25018, 151, 45, index, RSI); index++;		
		setBounds(25019, 154, 50, index, RSI); index++;
		setBounds(25020, 2, 82, index, RSI); index++;		
		setBounds(25021, 4, 84, index, RSI); index++;
		setBounds(25022, 40, 82, index, RSI); index++;			
		setBounds(25023, 44, 87, index, RSI); index++;
		setBounds(25024, 77, 82, index, RSI); index++;			
		setBounds(25025, 81, 85, index, RSI); index++;
		setBounds(25026, 114, 83, index, RSI); index++;			
		setBounds(25027, 117, 85, index, RSI); index++;			
		setBounds(25028, 153, 83, index, RSI); index++;			
		setBounds(25029, 156, 87, index, RSI); index++;			
		setBounds(25030, 2, 120, index, RSI); index++;			
		setBounds(25031, 5, 125, index, RSI); index++;			
		setBounds(25032, 40, 120, index, RSI); index++;			
		setBounds(25033, 43, 124, index, RSI); index++;	
		setBounds(25034, 78, 120, index, RSI); index++;			
		setBounds(25035, 83, 124, index, RSI); index++;								
		setBounds(25036, 114, 120, index, RSI); index++;			
		setBounds(25037, 115, 121, index, RSI); index++;	
		setBounds(25038, 151, 120, index, RSI); index++;			
		setBounds(25039, 154, 124, index, RSI); index++;			
		setBounds(25040, 2, 158, index, RSI); index++;			
		setBounds(25041, 5, 160, index, RSI); index++;			
		setBounds(25042, 39, 158, index, RSI); index++;			
		setBounds(25043, 41, 158, index, RSI); index++;			
		setBounds(25044, 76, 158, index, RSI); index++;			
		setBounds(25045, 79, 163, index, RSI); index++;			
		setBounds(25046, 114, 158, index, RSI); index++;			
		setBounds(25047, 116, 158, index, RSI); index++;						
		setBounds(25048, 153, 158, index, RSI); index++;			
		setBounds(25049, 161, 160, index, RSI); index++;				
		setBounds(25050, 2, 196, index, RSI); index++;			
		setBounds(25051, 4, 207, index, RSI); index++;
		setBounds(25052, 2, 5, index, RSI); index++;
		setBounds(25054, 40, 5, index, RSI); index++;
		setBounds(25056, 76, 5, index, RSI); index++;
		setBounds(25058, 113, 5, index, RSI); index++;
		setBounds(25060, 150, 5, index, RSI); index++;
		setBounds(25062, 2, 45, index, RSI); index++;
		setBounds(25064, 39, 45, index, RSI); index++;
		setBounds(25066, 76, 45, index, RSI); index++;
		setBounds(25068, 113, 45, index, RSI); index++;
		setBounds(25070, 151, 45, index, RSI); index++;
		setBounds(25072, 2, 82, index, RSI); index++;
		setBounds(25074, 40, 82, index, RSI); index++;
		setBounds(25076, 77, 82, index, RSI); index++;
		setBounds(25078, 114, 83, index, RSI); index++;
		setBounds(25080, 153, 83, index, RSI); index++;
		setBounds(25082, 2, 120, index, RSI); index++;
		setBounds(25084, 40, 120, index, RSI); index++;
		setBounds(25086, 78, 120, index, RSI); index++;
		setBounds(25088, 114, 120, index, RSI); index++;
		setBounds(25090, 151, 120, index, RSI); index++;
		setBounds(25092, 2, 158, index, RSI); index++;
		setBounds(25094, 39, 158, index, RSI); index++;
		setBounds(25096, 76, 158, index, RSI); index++;
		setBounds(25098, 114, 158, index, RSI); index++;
		setBounds(25100, 153, 158, index, RSI); index++;
		setBounds(25102, 2, 196, index, RSI); index++;
	}
	
	public static void extraOptions(RSFont[] TDA) {
		RSInterface rsi = addInterface(27500);
		int color = 0xFF981F;
		int width = 16, height = 16, width2 = 18, height2 = 18, idx = 0;
		String dir = "Interfaces/OptionTab/Settings/";
		addSprite(27501, 0, "Interfaces/OptionTab/Settings/Backing");
		RSInterface background = interfaceCache[27501];
		background.spriteShadowed = true;
		addHover(27529, 3, 0, 27568, 1, dir+"Close", width, height, "Close");
		addHovered(27568, 0, dir+"Close", width, height, 27569);
		addText(27549, "Preferences", color, false, true, -1, TDA, 3);
		setChildren(5, rsi);
		setBounds(27501, 12, 17, idx, rsi); idx++;	//1 Main Sprite backing
		setBounds(27610, 290, 105, idx, rsi); idx++; ///AddOns - Private message colors
		setBounds(27600, 0, 0, idx, rsi); idx++;	///41 AddOns Interface
		setBounds(27529, 474, 20, idx, rsi); idx++;	//2 Close 
		setBounds(27568, 474, 20, idx, rsi); idx++;	//3 Close Hover
	}
	
	public static void addOnToSettings(RSFont[] TDA) {
		RSInterface rsi = addInterface(27600);
		int color = 0xFF981F, width = 16, height = 16, width2 = 18, height2 = 18, idx = 0, total = 53;
		int[] Id = {27502, 27505, 27508, 27511, 27514, 27517, 27520, 27523, 27526, 27601};
		int[] hId = {27503, 27506, 27509, 27512, 27515, 27518, 27521, 27524, 27527, 27602};
		int[] endHId = {27504, 27507, 27510, 27513, 27516, 27519, 27522, 27525, 27528, 27607};
		int[] checkId = {27531, 27533, 27535, 27537, 27539, 27541, 27543, 27545, 27547, 27605};
		int[] tooltipId = {27532, 27534, 27536, 27538, 27540, 27542, 27544, 27546, 27548, 27604};
		int[] textId = {27550, 27551, 27552, 27553, 27554, 27555, 27556, 27557, 27558, 27603};
		int[] x = {25, 25, 45, 225, 225, 210, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
			25, 25, 25,25, 25, 225, 225, 225, 225, 225, 225, 225, 225, 225, 225, 225, 225, 225, 225,
			225, 225, 225, 225, 45, 45, 45, 45, 45, 45, 45, 45, 45
		};
		int[] y = {275, 275, 275, 275, 275, 18, 50, 50, 75, 75, 100, 100, 125, 125, 150, 150, 175, 175,
			200, 200, 225, 225, 250, 250, 50, 75, 100, 125, 150, 175, 200, 225, 250, 50, 75, 100, 125,
			150, 175, 200, 225, 250, 50, 75, 100, 125, 150, 175, 200, 225, 250
		};
		String[] text = {
			"Change Gameframe:", "Display Sky:", "Display HP above head:", 
			"Display Names above head:", "Display 10x Damage:", "Change Hitmarks:",
			"Change HP Bar:", "Change Menu:", "Enable use of HotKeys:", "Save all settings:"
		};
		String[] tooltip = {
			"Change the current Gameframe\\n to: 474, 508", "Display the new/old Sky", "Display your current HP above\\n your head",
			"Display your Users username\\n above your head", "Display 10x Damage when you hit\\nExample: 10 = 100", 
			"Display the new Hitmarks when\\nyour character is in\\n combat", "Display the new Hitpoints bar\\nwhen your character is in\\n combat", 
			"Change the game menu", "Enable the use of hotkeys\\nF1-F12"
		};
		String hoverClick = "Click to enable/disable\\nthis feature";
		String dir = "Interfaces/OptionTab/Settings/";
		String info = "Info";
		String check = "Check";
		addText(25630, "Information:", color, true, true, -1, TDA, 3);
		addHover(Id[0], 1, 0, hId[0], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[0], 0, dir+"InfoIcon", width, height, endHId[0]);
		addHover(Id[1], 1, 0, hId[1], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[1], 0, dir+"InfoIcon", width, height, endHId[1]);
		addHover(Id[2], 1, 0, hId[2], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[2], 0, dir+"InfoIcon", width, height, endHId[2]);
		addHover(Id[3], 1, 0, hId[3], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[3], 0, dir+"InfoIcon", width, height, endHId[3]);
		addHover(Id[4], 1, 0, hId[4], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[4], 0, dir+"InfoIcon", width, height, endHId[4]);
		addHover(Id[5], 1, 0, hId[5], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[5], 0, dir+"InfoIcon", width, height, endHId[5]);
		addHover(Id[6], 1, 0, hId[6], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[6], 0, dir+"InfoIcon", width, height, endHId[6]);
		addHover(Id[7], 1, 0, hId[7], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[7], 0, dir+"InfoIcon", width, height, endHId[7]);
		addHover(Id[8], 1, 0, hId[8], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[8], 0, dir+"InfoIcon", width, height, endHId[8]);
		addHover(Id[9], 1, 0, hId[9], 1, dir+"InfoIcon", width, height, info);
		addHovered(hId[9], 0, dir+"InfoIcon", width, height, endHId[9]);
		addButton(checkId[0], 1, tooltipId[0], 2, 3, dir+"CheckBox", width2+33, height2, check, 0, 1);
		addGenericTooltip(tooltipId[0], width2+33, height2, "Click to change the\\nGameframe to 474/508");
		addButton(checkId[1], 1, tooltipId[1], 0, 1, dir+"CheckBox", width2, height2, check, 1, 1);
		addGenericTooltip(tooltipId[1], width2, height2, hoverClick);
		addButton(checkId[2], 1, tooltipId[2], 0, 1, dir+"CheckBox", width2, height2, check, 2, 1);
		addGenericTooltip(tooltipId[2], width2, height2, hoverClick);
		addButton(checkId[3], 1, tooltipId[3], 0, 1, dir+"CheckBox", width2, height2, check, 3, 1);
		addGenericTooltip(tooltipId[3], width2, height2, hoverClick);
		addButton(checkId[4], 1, tooltipId[4], 0, 1, dir+"CheckBox", width2, height2, check, 4, 1);
		addGenericTooltip(tooltipId[4], width2, height2, hoverClick);
		addButton(checkId[5], 1, tooltipId[5], 0, 1, dir+"CheckBox", width2, height2, check, 5, 1);
		addGenericTooltip(tooltipId[5], width2, height2, hoverClick);
		addButton(checkId[6], 1, tooltipId[6], 0, 1, dir+"CheckBox", width2, height2, check, 6, 1);
		addGenericTooltip(tooltipId[6], width2, height2, hoverClick);
		addButton(checkId[7], 1, tooltipId[7], 0, 1, dir+"CheckBox", width2, height2, check, 7, 1);
		addGenericTooltip(tooltipId[7], width2, height2, hoverClick);
		addButton(checkId[8], 1, tooltipId[8], 0, 1, dir+"CheckBox", width2, height2, check, 8, 1);
		addGenericTooltip(tooltipId[8], width2, height2, hoverClick);
		addButton(checkId[9], 1, tooltipId[9], 0, 1, dir+"CheckBox", width2, height2, check, 9, 1);
		addGenericTooltip(tooltipId[9], width2, height2, hoverClick);
		addText(textId[0], text[0], color, false, true, -1, TDA, 1);
		addText(textId[1], text[1], color, false, true, -1, TDA, 1);
		addText(textId[2], text[2], color, false, true, -1, TDA, 1);
		addText(textId[3], text[3], color, false, true, -1, TDA, 1);
		addText(textId[4], text[4], color, false, true, -1, TDA, 1);
		addText(textId[5], text[5], color, false, true, -1, TDA, 1);
		addText(textId[6], text[6], color, false, true, -1, TDA, 1);
		addText(textId[7], text[7], color, false, true, -1, TDA, 1);
		addText(textId[8], text[8], color, false, true, -1, TDA, 1);
		addText(textId[9], text[9], color, false, true, -1, TDA, 1);
		setChildren(total, rsi);
		setBounds(27609, 290, 60, idx, rsi); idx++;
		setBounds(25630, 385, 40, idx, rsi); idx++;
		setBounds(27601, x[0], y[0], idx, rsi); idx++;
		setBounds(27602, x[1], y[1], idx, rsi); idx++;
		setBounds(27603, x[2], y[2], idx, rsi); idx++;
		setBounds(27604, x[3], y[3], idx, rsi); idx++;
		setBounds(27605, x[4], y[4], idx, rsi); idx++;
		setBounds(27549, x[5], y[5], idx, rsi); idx++;	///4 Text: Settings
		setBounds(27502, x[6], y[6], idx, rsi); idx++;	//5 Icon 1
		setBounds(27503, x[7], y[7], idx, rsi); idx++;	//6 HoverOnIcon 1
		setBounds(27505, x[8], y[8], idx, rsi); idx++;	//7 Icon 2
		setBounds(27506, x[9], y[9], idx, rsi); idx++;	//8 HoverOnIcon 2
		setBounds(27508, x[10], y[10], idx, rsi); idx++;//9 Icon 3
		setBounds(27509, x[11], y[11], idx, rsi); idx++;//10 HoverOnIcon 3
		setBounds(27511, x[12], y[12], idx, rsi); idx++;//11 Icon 4
		setBounds(27512, x[13], y[13], idx, rsi); idx++;//12 HoverOnIcon 4
		setBounds(27514, x[14], y[14], idx, rsi); idx++;//13 Icon 5
		setBounds(27515, x[15], y[15], idx, rsi); idx++;//14 HoverOnIcon 5
		setBounds(27517, x[16], y[16], idx, rsi); idx++;//15 Icon 6
		setBounds(27518, x[17], y[17], idx, rsi); idx++;//16 HoverOnIcon 6
		setBounds(27520, x[18], y[18], idx, rsi); idx++;//17 Icon 7
		setBounds(27521, x[19], y[19], idx, rsi); idx++;//18 HoverOnIcon 7
		setBounds(27523, x[20], y[20], idx, rsi); idx++;//19 Icon 8
		setBounds(27524, x[21], y[21], idx, rsi); idx++;//20 HoverOnIcon 8
		setBounds(27526, x[22], y[22], idx, rsi); idx++;//21 Icon 9
		setBounds(27527, x[23], y[23], idx, rsi); idx++;//22 HoverOnIcon 9
		setBounds(27531, x[24], y[24], idx, rsi); idx++;//23 Checkbox 1
		setBounds(27533, x[25], y[25], idx, rsi); idx++;//25 Checkbox 2
		setBounds(27535, x[26], y[26], idx, rsi); idx++;//27 Checkbox 3
		setBounds(27537, x[27], y[27], idx, rsi); idx++;//29 Checkbox 4
		setBounds(27539, x[28], y[28], idx, rsi); idx++;//31 Checkbox 5
		setBounds(27541, x[29], y[29], idx, rsi); idx++;//33 Checkbox 6
		setBounds(27543, x[30], y[30], idx, rsi); idx++;//35 Checkbox 7
		setBounds(27545, x[31], y[31], idx, rsi); idx++;//37 Checkbox 8
		setBounds(27547, x[32], y[32], idx, rsi); idx++;//39 Checkbox 9
		setBounds(27532, x[33], y[33], idx, rsi); idx++;//24 HoverOnCheckbox 1
		setBounds(27534, x[34], y[34], idx, rsi); idx++;//26 HoverOnCheckbox 2
		setBounds(27536, x[35], y[35], idx, rsi); idx++;//28 HoverOnCheckbox 3
		setBounds(27538, x[36], y[36], idx, rsi); idx++;//30 HoverOnCheckbox 4
		setBounds(27540, x[37], y[37], idx, rsi); idx++;//32 HoverOnCheckbox 5
		setBounds(27542, x[38], y[38], idx, rsi); idx++;//34 HoverOnCheckbox 6
		setBounds(27544, x[39], y[39], idx, rsi); idx++;//36 HoverOnCheckbox 7
		setBounds(27546, x[40], y[40], idx, rsi); idx++;//38 HoverOnCheckbox 8
		setBounds(27548, x[41], y[41], idx, rsi); idx++;//40 HoverOnCheckbox 9
		setBounds(27550, x[42], y[42], idx, rsi); idx++;///42 Text: Gameframe
		setBounds(27551, x[43], y[43], idx, rsi); idx++;///43 Text: Remeber me
		setBounds(27552, x[44], y[44], idx, rsi); idx++;///44 Text: Display HP
		setBounds(27553, x[45], y[45], idx, rsi); idx++;///45 Text: Display Names
		setBounds(27554, x[46], y[46], idx, rsi); idx++;///46 Text: Display 10x
		setBounds(27555, x[47], y[47], idx, rsi); idx++;///47 Text: Change Hitmarks
		setBounds(27556, x[48], y[48], idx, rsi); idx++;///48 Text: Change HPBar
		setBounds(27557, x[49], y[49], idx, rsi); idx++;///49 Text: Change Menu
		setBounds(27558, x[50], y[50], idx, rsi); idx++;///50 Text: Enable HotKeys
	}
	
	public static void ColorChanger(RSFont[] TDA) {
		RSInterface rsi = addInterface(27610);
		String dir = "Interfaces/OptionTab/Settings/ChatColor/C";
		String col = "Select colour";
		String gBox = "Interfaces/optionTab/Settings/ChatColor/H";
		int[] realId = {
			31002, 31005, 31008, 31011, 31014, 31017, 31020, 31023, 31026, 31029, 31032, 31035, 31038, 31041, 31044};
		int[] hovId = {
			31003, 31006, 31009, 31012, 31015, 31018, 31021, 31024, 31027, 31030, 31033, 31036, 31039, 31042, 31045};
		int[] nullId = {
			31004, 31007, 31010, 31013, 31016, 31019, 31022, 31025, 31028, 31031, 31034, 31037, 31040, 31043, 31046};
		int w1 = 20, h1 = 20;
		int w2 = 19, h2 = 20;
		int index = 0, color = 0xFF981F;
		addText(31051, "Split-Chat Colour", color, false, true, -1, TDA, 1);
		addText(31052, "Sample Text", client.getChatColor(), false, true, -1, TDA, 0);
		addPixels(31053, 0x000000, 141, 15, 65, true);
		//addPixels(31054, 0x000000, 151, 117, 100, false);
		setChildren(33, rsi);
		setBounds(31053, 26, 190, index, rsi); index++;
		//setBounds(31054, 21, 90, index, rsi); index++;
		setBounds(31051, 50, 95, index, rsi); index++;
		setBounds(31052, 65, 192, index, rsi); index++;
		addHover(realId[0], 1, 0, hovId[0], 0, dir, w1, h1, col);
		addHover(realId[1], 1, 0, hovId[1], 1, dir, w1, h1, col);
		addHover(realId[2], 1, 0, hovId[2], 2, dir, w1, h1, col);
		addHover(realId[3], 1, 0, hovId[3], 3, dir, w1, h1, col);
		addHover(realId[4], 1, 0, hovId[4], 4, dir, w1, h1, col);
		addHover(realId[5], 1, 0, hovId[5], 5, dir, w1, h1, col);
		addHover(realId[6], 1, 0, hovId[6], 6, dir, w1, h1, col);
		addHover(realId[7], 1, 0, hovId[7], 7, dir, w1, h1, col);
		addHover(realId[8], 1, 0, hovId[8], 8, dir, w1, h1, col);
		addHover(realId[9], 1, 0, hovId[9], 9, dir, w1, h1, col);
		addHover(realId[10], 1, 0, hovId[10], 10, dir, w1, h1, col);
		addHover(realId[11], 1, 0, hovId[11], 11, dir, w1, h1, col);
		addHover(realId[12], 1, 0, hovId[12], 12, dir, w1, h1, col);
		addHover(realId[13], 1, 0, hovId[13], 13, dir, w1, h1, col);
		addHover(realId[14], 1, 0, hovId[14], 14, dir, w1, h1, col);
		addHovered(hovId[0], 0, gBox, w2, h2, nullId[0]);
		addHovered(hovId[1], 0, gBox, w2, h2, nullId[1]);
		addHovered(hovId[2], 0, gBox, w2, h2, nullId[2]);
		addHovered(hovId[3], 0, gBox, w2, h2, nullId[3]);
		addHovered(hovId[4], 0, gBox, w2, h2, nullId[4]);
		addHovered(hovId[5], 0, gBox, w2, h2, nullId[5]);
		addHovered(hovId[6], 0, gBox, w2, h2, nullId[6]);
		addHovered(hovId[7], 0, gBox, w2, h2, nullId[7]);
		addHovered(hovId[8], 0, gBox, w2, h2, nullId[8]);
		addHovered(hovId[9], 0, gBox, w2, h2, nullId[9]);
		addHovered(hovId[10], 0, gBox, w2, h2, nullId[10]);
		addHovered(hovId[11], 0, gBox, w2, h2, nullId[11]);
		addHovered(hovId[12], 0, gBox, w2, h2, nullId[12]);
		addHovered(hovId[13], 0, gBox, w2, h2, nullId[13]);
		addHovered(hovId[14], 0, gBox, w2, h2, nullId[14]);
		setBounds(realId[0], 26, 119, index, rsi); index++;
		setBounds(realId[1], 56, 119, index, rsi); index++;
		setBounds(realId[2], 86, 119, index, rsi); index++;
		setBounds(realId[3], 116, 119, index, rsi); index++;
		setBounds(realId[4], 146, 119, index, rsi); index++;
		setBounds(realId[5], 26, 139+3, index, rsi); index++;
		setBounds(realId[6], 56, 139+3, index, rsi); index++;
		setBounds(realId[7], 86, 139+3, index, rsi); index++;
		setBounds(realId[8], 116, 139+3, index, rsi); index++;
		setBounds(realId[9], 146, 139+3, index, rsi); index++;
		setBounds(realId[10], 26, 159+6, index, rsi); index++;
		setBounds(realId[11], 56, 159+6, index, rsi); index++;
		setBounds(realId[12], 86, 159+6, index, rsi); index++;
		setBounds(realId[13], 116, 159+6, index, rsi); index++;
		setBounds(realId[14], 146, 159+6, index, rsi); index++;
		setBounds(hovId[0], 26, 119, index, rsi); index++;
		setBounds(hovId[1], 56, 119, index, rsi); index++;
		setBounds(hovId[2], 86, 119, index, rsi); index++;
		setBounds(hovId[3], 116, 119, index, rsi); index++;
		setBounds(hovId[4], 146, 119, index, rsi); index++;
		setBounds(hovId[5], 26, 139+3, index, rsi); index++;
		setBounds(hovId[6], 56, 139+3, index, rsi); index++;
		setBounds(hovId[7], 86, 139+3, index, rsi); index++;
		setBounds(hovId[8], 116, 139+3, index, rsi); index++;
		setBounds(hovId[9], 146, 139+3, index, rsi); index++;
		setBounds(hovId[10], 26, 159+6, index, rsi); index++;
		setBounds(hovId[11], 56, 159+6, index, rsi); index++;
		setBounds(hovId[12], 86, 159+6, index, rsi); index++;
		setBounds(hovId[13], 116, 159+6, index, rsi); index++;
		setBounds(hovId[14], 146, 159+6, index, rsi); index++;
	}
	
	public static void addOnText(RSFont[] TDA) {
		RSInterface rsi = addInterface(27609);
		rsi.parentID = 27609;
		rsi.id = 27609;
		rsi.type = 0;
		rsi.atActionType = 0;
		rsi.contentType = 0;
		rsi.opacity = 0;
		rsi.hoverType = -1;
		int y = 0;
		int index = 0;
		setChildren(11, rsi);
		for(int idx = 25600; idx <= 25610;idx++) {
			addText(idx, "", TDA, 0, 0xff981f);
			setBounds(idx, 0, y, index, rsi);
			index++;
			y += 12;
		}
	}
	
	public static void Curses(RSFont[] TDA) {
		RSInterface Interface = addTabInterface(22500);
		int index = 0;
		addText(687, "99 / 99", 0xFF981F, false, false, -1, TDA, 1);
		addSprite(22502, 0, "Interfaces/CurseTab/ICON");
		addPrayer(22503, 0, 610, 49, 7, "Protect Item", 22582);//1
		addPrayer(22505, 0, 611, 49, 4, "Sap Warrior", 22544);//2
		addPrayer(22507, 0, 612, 51, 5, "Sap Ranger", 22546);//3
		addPrayer(22509, 0, 613, 53, 3, "Sap Mage", 22548);//4
		addPrayer(22511, 0, 614, 55, 2, "Sap Spirit", 22550);//5
		addPrayer(22513, 0, 615, 58, 18, "Berserker", 22552);//6
		addPrayer(22515, 0, 616, 61, 15, "Deflect Summoning", 22554);///7
		addPrayer(22517, 0, 617, 64, 17, "Deflect Magic", 22556);///8
		addPrayer(22519, 0, 618, 67, 16, "Deflect Missiles", 22558);///9
		addPrayer(22521, 0, 619, 70, 6, "Deflect Melee", 22560);///10
		addPrayer(22523, 0, 620, 73, 9, "Leech Attack", 22562);//11
		addPrayer(22525, 0, 621, 75, 10, "Leech Ranged", 22564);//12
		addPrayer(22527, 0, 622, 77, 11, "Leech Magic", 22566);//13
		addPrayer(22529, 0, 623, 79, 12, "Leech Defence", 22568);//14
		addPrayer(22531, 0, 624, 81, 13, "Leech Strength", 22570);//15
		addPrayer(22533, 0, 625, 83, 14, "Leech Energy", 22572);//16
		addPrayer(22535, 0, 626, 85, 19, "Leech Special Attack", 22574);//17
		addPrayer(22537, 0, 627, 88, 1, "Wrath", 22576);///18
		addPrayer(22539, 0, 628, 91, 8, "Soul Split", 22578);///19
		addPrayer(22541, 0, 629, 94, 20, "Turmoil", 22580);//20
		addGenericTabTooltip(22582, 34, 34,  "Level 50\\nProtect Item\\nKeep 1 extra item if you die");
		addGenericTabTooltip(22544, 34, 34,  "Level 50\\nSap Warrior\\nDrains 10% of enemy Attack,\\nStrength and Defence,\\nincreasing to 20% over time");
		addGenericTabTooltip(22546, 34, 34,  "Level 52\\nSap Ranger\\nDrains 10% of enemy Ranged\\nand Defence, increasing to 20%\\nover time");
		addGenericTabTooltip(22548, 34, 34,  "Level 54\\nSap Mage\\nDrains 10% of enemy Magic\\nand Defence, increasing to 20%\\nover time");
		addGenericTabTooltip(22550, 34, 34,  "Level 56\\nSap Spirit\\nDrains enenmy special attack\\nenergy");
		addGenericTabTooltip(22552, 34, 34,  "Level 59\\nBerserker\\nBoosted stats last 15% longer");
		addGenericTabTooltip(22554, 34, 34,  "Level 62\\nDeflect Summoning\\nReduces damage dealt from\\nSummoning scrolls, prevents the\\nuse of a familiar's special\\nattack, and can deflect some of\\ndamage back to the attacker");
		addGenericTabTooltip(22556, 34, 34,  "Level 65\\nDeflect Magic\\nProtects against magic attacks\\nand can deflect some of the\\ndamage back to the attacker");
		addGenericTabTooltip(22558, 34, 34,  "Level 68\\nDeflect Missiles\\nProtects against ranged attacks\\nand can deflect some of the\\ndamage back to the attacker");
		addGenericTabTooltip(22560, 34, 34,  "Level 71\\nDeflect Melee\\nProtects against melee attacks\\nand can deflect some of the\\ndamage back to the attacker");
		addGenericTabTooltip(22562, 34, 34,  "Level 74\\nLeech Attack\\nBoosts Attack by 5%, increasing\\nto 10% over time, while draining\\nenemy Attack by 10%, increasing\\nto 25% over time");
		addGenericTabTooltip(22564, 34, 34,  "Level 76\\nLeech Ranged\\nBoosts Ranged by 5%,\\nincreasing to 10% over time,\\n while draining enemy Ranged\\n by 10%, increasing to\\n25% over time");
		addGenericTabTooltip(22566, 34, 34,  "Level 78\\nLeech Magic\\nBoosts Magic by 5%, increasing\\nto 10% over time, while draining\\nenemy Magic by 10%, increasing\\nto 25% over time");
		addGenericTabTooltip(22568, 34, 34,  "Level 80\\nLeech Defence\\nBoosts Defence by 5%,\\nincreasing to 10% over time,\\n while draining enemy Defence\\n by 10%, increasing to\\n25% over time");
		addGenericTabTooltip(22570, 34, 34,  "Level 82\\nLeech Strength\\nBoosts Strength by 5%,\\nincreasing to 10% over time,\\n while draining enemy Strength\\n by 10%, increasing to\\n25% over time");
		addGenericTabTooltip(22572, 34, 34,  "Level 84\\nLeech Energy\\nDrains enemy run energy, while\\nincreasing your own");
		addGenericTabTooltip(22574, 34, 34,  "Level 86\\nLeech Special Attack\\nDrains enemy special attack\\nenergy, while increasing your\\nown");
		addGenericTabTooltip(22576, 34, 34,  "Level 89\\nWrath\\nInflicts damage to nearby\\ntargets if you die");
		addGenericTabTooltip(22578, 34, 34,  "Level 92\\nSoul Split\\n1/4 of damage dealt is\\nalso removed from opponent's\\nPrayer and added to your\\nHitpoints");
		addGenericTabTooltip(22580, 34, 34,  "Level 95\\nTurmoil\\nIncreases Attack and Defence\\nby 15%, plus 15% of enemy's\\nlevel, and Strength by 23% plus\\n10% of enemy's level");
		setChildren(62, Interface);

		setBounds(687, 85, 241, index, Interface);index++;//Text
		setBounds(22502, 65, 241, index, Interface);index++;//Icon
		setBounds(22503, 2, 5, index, Interface);index++;
		setBounds(22505, 40, 5, index, Interface);index++;
		setBounds(22507, 76, 5, index, Interface);index++;
		setBounds(22509, 113, 5, index, Interface);index++;
		setBounds(22511, 150, 5, index, Interface);index++;
		setBounds(22513, 2, 45, index, Interface);index++;
		setBounds(22515, 39, 45, index, Interface);index++;
		setBounds(22517, 76, 45, index, Interface);index++;
		setBounds(22519, 113, 45, index, Interface);index++;
		setBounds(22521, 151, 45, index, Interface);index++;
		setBounds(22523, 2, 82, index, Interface);index++;
		setBounds(22525, 40, 82, index, Interface);index++;
		setBounds(22527, 77, 82, index, Interface);index++;
		setBounds(22529, 114, 83, index, Interface);index++;
		setBounds(22531, 153, 83, index, Interface);index++;
		setBounds(22533, 2, 120, index, Interface);index++;
		setBounds(22535, 40, 120, index, Interface);index++;
		setBounds(22537, 78, 120, index, Interface);index++;
		setBounds(22539, 114, 120, index, Interface);index++;
		setBounds(22541, 151, 120, index, Interface);index++;
		
		setBounds(22504, 8, 8, index, Interface);index++;
		setBounds(22506, 47, 12, index, Interface);index++;
		setBounds(22508, 82, 11, index, Interface);index++;
		setBounds(22510, 116, 8, index, Interface);index++;
		setBounds(22512, 155, 10, index, Interface);index++;
		setBounds(22514, 9, 48, index, Interface);index++;
		setBounds(22516, 42, 47, index, Interface);index++;
		setBounds(22518, 79, 48, index, Interface);index++;
		setBounds(22520, 116, 48, index, Interface);index++;
		setBounds(22522, 154, 48, index, Interface);index++;
		setBounds(22524, 6, 86, index, Interface);index++;
		setBounds(22526, 42, 86, index, Interface);index++;
		setBounds(22528, 79, 86, index, Interface);index++;
		setBounds(22530, 119, 87, index, Interface);index++;
		setBounds(22532, 156, 86, index, Interface);index++;
		setBounds(22534, 7, 125, index, Interface);index++;
		setBounds(22536, 45, 124, index, Interface);index++;
		setBounds(22538, 86, 124, index, Interface);index++;
		setBounds(22540, 120, 125, index, Interface);index++;
		setBounds(22542, 153, 127, index, Interface);index++;
		
		setBounds(22582, 2, 5, index, Interface);index++;
		setBounds(22544, 37, 5, index, Interface);index++;
		setBounds(22546, 76, 5, index, Interface);index++;
		setBounds(22548, 113, 5, index, Interface);index++;
		setBounds(22550, 150, 5, index, Interface);index++;
		setBounds(22552, 2, 45, index, Interface);index++;
		setBounds(22554, 39, 45, index, Interface);index++;
		setBounds(22556, 76, 45, index, Interface);index++;
		setBounds(22558, 113, 45, index, Interface);index++;
		setBounds(22560, 151, 45, index, Interface);index++;
		setBounds(22562, 5, 82, index, Interface);index++;
		setBounds(22564, 40, 82, index, Interface);index++;
		setBounds(22566, 77, 82, index, Interface);index++;
		setBounds(22568, 114, 83, index, Interface);index++;
		setBounds(22570, 153, 83, index, Interface);index++;
		setBounds(22572, 2, 120, index, Interface);index++;
		setBounds(22574, 40, 120, index, Interface);index++;
		setBounds(22576, 78, 120, index, Interface);index++;
		setBounds(22578, 114, 120, index, Interface);index++;
		setBounds(22580, 151, 120, index, Interface);index++;
	}
	
	
	public static void Logout(RSFont[] wid) {
		RSInterface rsinterface = interfaceCache[2449];
		rsinterface.childY[0] = 68;
		rsinterface.childY[1] = 86;
		rsinterface.childX[2] = 12;
		rsinterface.childY[2] = 104;
		rsinterface.childX[8] = 31;
		rsinterface.childY[8] = 164;
		rsinterface = interfaceCache[2450];
		rsinterface.disabledColor = 0xff981f;
		rsinterface = interfaceCache[2451];
		rsinterface.disabledColor = 0xff981f;
		rsinterface = interfaceCache[2452];
		rsinterface.disabledColor = 0xff981f;
	}
	
	public void specialBar(int id) {//7599
        /*addActionButton(ID, SpriteOFF, SpriteON, Width, Height, "SpriteText");*/
		addActionButton(id-12, 7587, -1, 150, 26, "Use @gre@Special Attack", 20003);
        /*removeSomething(ID);*/
        for (int i = id-11; i < id; i++)
			removeSomething(i);
        RSInterface rsi = interfaceCache[id-12];
        rsi.width = 150;
        rsi.height = 26;
        rsi = interfaceCache[id];
		rsi.width = 150;
		rsi.height = 26;
		rsi.child(0, id-12, 0, 0);
		rsi.child(12, id+1, 3, 7);
		rsi.child(23, id+12, 16, 8);
        for (int i = 13; i < 23; i++) {
            rsi.childY[i] -= 1;
        }
        rsi = interfaceCache[id+1];
		rsi.type = 5;
		rsi.disabledSprite = CustomSpriteLoader(7600, "");
        for (int i = id+2; i < id+12; i++) {
			rsi = interfaceCache[i];
            rsi.type = 5;
        }
        disabledSprite(id+2, 7601);disabledSprite(id+3, 7602);
        disabledSprite(id+4, 7603);disabledSprite(id+5, 7604);
        disabledSprite(id+6, 7605);disabledSprite(id+7, 7606);
        disabledSprite(id+8, 7607);disabledSprite(id+9, 7608);
        disabledSprite(id+10, 7609);disabledSprite(id+11, 7610);
    }

    public static void Sidebar0(RSFont[] tda)
    {
		RSInterface rsi = addInterface(19300);
		addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate", 20001);
		addGenericTabTooltip(20001, 150, 44, "When active your player\\n will automatically\\n fight back if attacked.");
		//addTooltip(20001, "When active your player\n will automatically\n fight back if attacked.");
		//addText(3983, "", tda, 0, 0xff981f, false, true);
		rsi.totalChildren(3, 3, 3);
		rsi.child(0, 19999, 52, 25);//combat
		rsi.child(1, 150, 21, 153);
		rsi.child(2, 20001, 21, 153);//23+4, 203
		/*
		addFourSelect(id, id2, id3, "text1", "text2", "text3", "text4",
							str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y,
							img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y,
							tda);
		*/
		addFourSelect(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block",
							42, 75, 127, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block",
							43, 75, 124, 75, 41, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block",
							42, 75, 125, 75, 40, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 20007, 20009, 20011, 20013);
		addFourSelect(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block",
							39, 75, 121, 75, 41, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block",
							40, 75, 124, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 
							42, 75, 125, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block",
							41, 75, 123, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block",
							42, 75, 125, 75, 40, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		/*
		addFourSelectNoSpec(id, id2, "text1", "text2", "text3", "text4",
							str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y,
							img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y,
							tda);
		*/
		addFourSelectNoSpec(776, 779, "Reap", "Chop", "Jab", "Block", 42,
							75, 126, 75, 46, 128, 125, 128, 122,
							103, 122, 50, 40, 103, 40, 50,
							tda);
		/*
		addThreeSelect(id, id2, id3, "text1", "text2", "text3",
							str1x, str1y, str2x, str2y, str3x, str3y,
							img1x, img1y, img2x, img2y, img3x, img3y,
							tda);
		*/
		addThreeSelect(425, 428, 7474, "Pound", "Pummel", "Block",
							39, 75, 121, 75, 42, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(1749, 1752, 7524, "Accurate", "Rapid", "Longrange",
							33, 75, 125, 75, 29, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(1764, 1767, 7549, "Accurate", "Rapid", "Longrange",
							33, 75, 125, 75, 29, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(4446, 4449, 7649, "Accurate", "Rapid", "Longrange",
							33, 75, 125, 75, 29, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(5855, 5857, 7749, "Punch", "Kick", "Block",
							40, 75, 129, 75, 42, 128,
							40, 50, 122, 50, 40, 103,
							tda);
		addThreeSelect(6103, 6132, 6117, "Bash", "Pound", "Block",
							43, 75, 124, 75, 42, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(8460, 8463, 8493, "Jab", "Swipe", "Fend",
							46, 75, 124, 75, 43, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(12290, 12293, 12323, "Flick", "Lash", "Deflect",
							44, 75, 127, 75, 36, 128,
							40, 50, 40, 103, 122, 50,
							tda);
		/*
		addThreeSelectNoSpec(id, id2, "text1", "text2", "text3",
								str1x, str1y, str2x, str2y, str3x, str3y,
								img1x, img1y, img2x, img2y, img3x, img3y,
								tda);
		*/
		addThreeSelectNoSpec(328, 331, "Bash", "Pound", "Focus",
								42, 66, 39, 101, 41, 136,
								40, 120, 40, 50, 40, 85,
								tda);
    }

    public static void addFourSelect
		(int id, int id2, int id3, String text1, String text2, String text3, String text4,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y,
		RSFont[] tda, int hover, int hover2, int hover3, int hover4) //4button spec
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);
			
			addTooltip(hover, "(Accurate)\n(Slash)\n(Attack XP)");
			addTooltip(hover2, "(Agressive)\n(Slash)\n(Strength XP)");
			addTooltip(hover3, "(Agressive)\n(Crush)\n(Strength XP)");
			addTooltip(hover4, "(Defensive)\n(Slash)\n(Defence XP)");
			/*specialBar(ID);*/
            rsi.specialBar(id3); //7599
            rsi.width = 190;
            rsi.height = 261;
        int last = 19; int frame = 0;
        rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432
            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436
            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599
			
			rsi.child(frame, hover, img1x, img1y); frame++; //Retaliate+cb lvl
			rsi.child(frame, hover2, img2x, img2y); frame++; //Retaliate+cb lvl
			rsi.child(frame, hover3, img3x, img3y); frame++; //Retaliate+cb lvl
			rsi.child(frame, hover4, img4x, img4y); frame++; //Retaliate+cb lvl

		
			rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
			
			
        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
			rsi = interfaceCache[i];
				rsi.disabledSprite = CustomSpriteLoader(19301, "");
				rsi.enabledSprite = CustomSpriteLoader(19301, "a");
				rsi.width = 68; rsi.height = 44;
        }
    }

    public static void addFourSelectNoSpec
		(int id, int id2, String text1, String text2, String text3, String text4,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y,
		RSFont[] tda) //4button nospec
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);
            rsi.width = 190;
            rsi.height = 261;
        int last = 14; int frame = 0;
        rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432
            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436
            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426
			rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
			rsi = interfaceCache[i];
				rsi.disabledSprite = CustomSpriteLoader(19301, "");
				rsi.enabledSprite = CustomSpriteLoader(19301, "a");
				rsi.width = 68; rsi.height = 44;
        }
    }

    public static void addThreeSelect
		(int id, int id2, int id3, String text1, String text2, String text3,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y,
		RSFont[] tda) //3button spec
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false); // spec
            addText(id2+11, text3, tda, 0, 0xff981f, false);
			/*specialBar(ID);*/
            rsi.specialBar(id3); //7599
            rsi.width = 190;
            rsi.height = 261;
        int last = 12; int frame = 0;
        rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 21, 99); frame++;
            rsi.child(frame, id2+4, 105, 46); frame++;
            rsi.child(frame, id2+5, 21, 46); frame++;
            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright
            rsi.child(frame, id2+9, str1x, str1y); frame++; //chop
            rsi.child(frame, id2+10, str2x, str2y); frame++; //slash
            rsi.child(frame, id2+11, str3x, str3y); frame++; //lunge
            rsi.child(frame, id2, 94, 4); frame++; //weapon
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599
			rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
        for (int i = id2+3; i < id2+6; i++) {
			rsi = interfaceCache[i];
				rsi.disabledSprite = CustomSpriteLoader(19301, "");
				rsi.enabledSprite = CustomSpriteLoader(19301, "a");
				rsi.width = 68; rsi.height = 44;
        }
    }

    public static void addThreeSelectNoSpec
		(int id, int id2, String text1, String text2, String text3,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y,
		RSFont[] tda) //3button nospec (magic intf)
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);
            //addText(353, "Spell", tda, 0, 0xff981f, false);
			removeSomething(353);
            addText(354, "Spell", tda, 0, 0xff981f, false);
            addCacheSprite(337, 19, 0, "combaticons");
            addCacheSprite(338, 13, 0, "combaticons2");
            addCacheSprite(339, 14, 0, "combaticons2");
			/*addToggleButton(id, sprite, config, width, height, tooltip);*/
            //addToggleButton(349, 349, 108, 68, 44, "Select");
			removeSomething(349);
            addToggleButton(350, 350, 108, 68, 44, "Select");
            rsi.width = 190;
            rsi.height = 261;
			int last = 15; int frame = 0;
			rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 20, 115); frame++;
            rsi.child(frame, id2+4, 20, 80); frame++;
            rsi.child(frame, id2+5, 20, 45); frame++;
            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright
            rsi.child(frame, id2+9, str1x, str1y); frame++; //bash
            rsi.child(frame, id2+10, str2x, str2y); frame++; //pound
            rsi.child(frame, id2+11, str3x, str3y); frame++; //focus
            rsi.child(frame, 349, 105, 46); frame++; //spell1
            rsi.child(frame, 350, 104, 106); frame++; //spell2
            rsi.child(frame, 353, 125, 74); frame++; //spell
            rsi.child(frame, 354, 125, 134); frame++; //spell
            rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
            rsi.child(frame, id2, 94, 4); frame++; //weapon
    }
	
	public static void emoteTab() {
        RSInterface tab = addTabInterface(147);
        RSInterface scroll = addTabInterface(148);
        tab.totalChildren(1);
        tab.child(0, 148, 0, 3);
		int w = 41, h = 47;
		addButton(168, 0, "Interfaces/Emotes/EMOTE", "Yes", 1, w, h);
		addButton(169, 1, "Interfaces/Emotes/EMOTE", "No", 1, w, h);
		addButton(164, 2, "Interfaces/Emotes/EMOTE", "Bow", 1, w, h);
		addButton(165, 3, "Interfaces/Emotes/EMOTE", "Angry", 1, w, h);
		addButton(162, 4, "Interfaces/Emotes/EMOTE", "Think", 1, w, h);
		addButton(163, 5, "Interfaces/Emotes/EMOTE", "Wave", 1, w, h);
		addButton(13370, 6, "Interfaces/Emotes/EMOTE", "Shrug", 1, w, h);
		addButton(171, 7, "Interfaces/Emotes/EMOTE", "Cheer", 1, w, h);
		addButton(167, 8, "Interfaces/Emotes/EMOTE", "Beckon", 1, w, h);
		addButton(170, 9, "Interfaces/Emotes/EMOTE", "Laugh", 1, w, h);
		addButton(13366, 10, "Interfaces/Emotes/EMOTE", "Jump for Joy", 1, w, h);
		addButton(13368, 11, "Interfaces/Emotes/EMOTE", "Yawn", 1, w, h);
		addButton(166, 12, "Interfaces/Emotes/EMOTE", "Dance", 1, w, h);
		addButton(13363, 13, "Interfaces/Emotes/EMOTE", "Jig", 1, w, h);
		addButton(13364, 14, "Interfaces/Emotes/EMOTE", "Spin", 1, w, h);
		addButton(13365, 15, "Interfaces/Emotes/EMOTE", "Headbang", 1, w, h);
		addButton(161, 16, "Interfaces/Emotes/EMOTE", "Cry", 1, w, h);
		addButton(11100, 17, "Interfaces/Emotes/EMOTE", "Blow kiss", 1, w, h);
		addButton(13362, 18, "Interfaces/Emotes/EMOTE", "Panic", 1, w, h);
		addButton(13367, 19, "Interfaces/Emotes/EMOTE", "Raspberry", 1, w, h);
		addButton(172, 20, "Interfaces/Emotes/EMOTE", "Clap", 1, w, h);
		addButton(13369, 21, "Interfaces/Emotes/EMOTE", "Salute", 1, w, h);
		addButton(13383, 22, "Interfaces/Emotes/EMOTE", "Goblin Bow", 1, w, h);
		addButton(13384, 23, "Interfaces/Emotes/EMOTE", "Goblin Salute", 1, w, h);
		addButton(667, 24, "Interfaces/Emotes/EMOTE", "Glass Box", 1, w, h);
		addButton(6503, 25, "Interfaces/Emotes/EMOTE", "Climb Rope", 1, w, h);
		addButton(6506, 26, "Interfaces/Emotes/EMOTE", "Lean On Air", 1, w, h);
		addButton(666, 27, "Interfaces/Emotes/EMOTE", "Glass Wall", 1, w, h);
		addButton(18464, 33, "Interfaces/Emotes/EMOTE", "Idea", 1, w, h);
		addButton(18465, 34, "Interfaces/Emotes/EMOTE", "Stomp", 1, w, h);
		addButton(15166, 35, "Interfaces/Emotes/EMOTE", "Flap", 1, w, h);
		addButton(18686, 36, "Interfaces/Emotes/EMOTE", "Slap head", 1, 43, h);
		addButton(18689, 28, "Interfaces/Emotes/EMOTE", "Zombie Walk", 1, w, h);
		addButton(18688, 29, "Interfaces/Emotes/EMOTE", "Zombie Dance", 1, w, h);
		addButton(18691, 30, "Interfaces/Emotes/EMOTE", "Scared", 1, w, h);
		addButton(18692, 37, "Interfaces/Emotes/EMOTE", "Zombie Hand", 1, w, h);
		addButton(18687, 31, "Interfaces/Emotes/EMOTE", "Bunny Hop", 1, w, h);
		addButton(154, 32, "Interfaces/Emotes/EMOTE", "SkillCape", 1, w, h);
		scroll.totalChildren(38);
		//ids
		scroll.width = 173;
		scroll.height = 258;
		scroll.scrollMax = 500;
		scroll.child(0, 168, 10, 6);
		scroll.child(1, 169, 54, 6);
		scroll.child(2, 164, 98, 13);
		scroll.child(3, 165, 137, 6);//Row 1
		scroll.child(4, 162, 9, 55);
		scroll.child(5, 163, 48, 55);
		scroll.child(6, 13370, 95, 55);
		scroll.child(7, 171, 137, 55);//Row 2
		scroll.child(8, 167, 7, 104);
		scroll.child(9, 170, 51, 104);
		scroll.child(10, 13366, 95, 103);
		scroll.child(11, 13368, 139, 104);//Row 3
		scroll.child(12, 166, 6, 153);
		scroll.child(13, 13363, 50, 153);
		scroll.child(14, 13364, 90, 153);
		scroll.child(15, 13365, 135, 153);//Row 4
		scroll.child(16, 161, 8, 203);
		scroll.child(17, 11100, 51, 202);
		scroll.child(18, 13362, 99, 203);
		scroll.child(19, 13367, 137, 202);//Row 5
		scroll.child(20, 172, 10, 250);
		scroll.child(21, 13369, 53, 250);
		scroll.child(22, 13383, 88, 255);
		scroll.child(23, 13384, 138, 249);//Row 6
		scroll.child(24, 667, 3, 300);
		scroll.child(25, 6503, 52, 299);
		scroll.child(26, 6506, 96, 299);
		scroll.child(27, 666, 141, 299);//Row 7
		scroll.child(28, 18464, 5, 349);
		scroll.child(29, 18465, 53, 350);
		scroll.child(30, 15166, 88, 352);
		scroll.child(31, 18686, 142, 350);//Row 8
		scroll.child(32, 18687, 12, 452);
		scroll.child(33, 18689, 10, 401);
		scroll.child(34, 18688, 51, 402);
		scroll.child(35, 18692, 90, 402);//Row 9
		scroll.child(36, 18691, 139, 406);
		scroll.child(37, 154, 49, 450);//Row 10
    }

	public static void optionTab(RSFont[] TDA) {
        RSInterface Interface = addTabInterface(904);
		setChildren(46,Interface);
		addSprite(25801, 0,"Interfaces/OptionTab/OPTION");
		addSprite(25802, 1,"Interfaces/OptionTab/OPTION");
		addSprite(25803, 1,"Interfaces/OptionTab/OPTION");
		addSprite(25804, 1,"Interfaces/OptionTab/OPTION");
		setBounds(25801, 49, 17, 0, Interface);
		setBounds(25802, 49, 54, 1, Interface);
		setBounds(25803, 49, 90, 2, Interface);
		setBounds(25804, 49, 127, 3, Interface);
		addButton(25805, 5, -1, 2, 2, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Brightness", 166, 1);
		setBounds(25805, 9, 8, 4, Interface);
		addButton(25806, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 1);
		addButton(25807, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 2);
		addButton(25808, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 3);
		addButton(25809, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 4);
		setBounds(25806, 57, 16, 5, Interface);
		setBounds(25807, 88, 16, 6, Interface);
		setBounds(25808, 119, 16, 7, Interface);
		setBounds(25809, 153, 16, 8, Interface);
		addButton(25810, 5, -1, 3, 4, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Music Level", 168, 4);
		setBounds(25810, 9, 45, 9, Interface);
		addButton(25811, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 4);
		addButton(25812, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 3);
		addButton(25813, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 2);
		addButton(25814, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 1);
		addButton(25815, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 0);
		setBounds(25811, 54, 53, 10, Interface);
		setBounds(25812, 78, 53, 11, Interface);
		setBounds(25813, 105, 53, 12, Interface);
		setBounds(25814, 131, 53, 13, Interface);
		setBounds(25815, 156, 53, 14, Interface);
		addButton(25816, 5, -1, 5, 6, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Sounds", 169, 4);
		setBounds(25816, 9, 81, 15, Interface);
		addButton(25817, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 4);
		addButton(25818, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 3);
		addButton(25819, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 2);
		addButton(25820, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 1);
		addButton(25821, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 0);
		setBounds(25817, 54, 89, 16, Interface);
		setBounds(25818, 78, 89, 17, Interface);
		setBounds(25819, 105, 89, 18, Interface);
		setBounds(25820, 131, 89, 19, Interface);
		setBounds(25821, 156, 89, 20, Interface);
		addButton(25822, 5, -1, 7, 8, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Sound Effects", 400, 0);
		setBounds(25822, 10, 119, 21, Interface);
		addButton(25823, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 0);
		addButton(25824, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 1);
		addButton(25825, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 2);
		addButton(25826, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 3);
		addButton(25827, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 4);
		setBounds(25823, 54, 126, 22, Interface);
		setBounds(25824, 78, 126, 23, Interface);
		setBounds(25825, 105, 126, 24, Interface);
		setBounds(25826, 131, 126, 25, Interface);
		setBounds(25827, 156, 126, 26, Interface);		
		addButton(25828, 4, 25829, 9, 10, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Mouse Buttons", 170, 0);
		addGenericTabTooltip(25829, 40, 40, "Toggle Mouse Buttons");
		addButton(25831, 4, 25832, 9, 10, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Chat Effects", 171, 0);
		addGenericTabTooltip(25832, 40, 40, "Toggle Chat Effects");
		addButton(25834, 4, 25835, 9, 10, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Split-Level Chat", 287, 0);
		addGenericTabTooltip(25835, 40, 40, "Toggle Split-Level Chat");
		addButton(25837, 4, 25838, 9, 10, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Accept Aid", 427, 1);
		addGenericTabTooltip(25838, 40, 40, "Toggle Accept Aid");
		addButton(152, 4, 25841, 9, 10, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Run Mode", 173, 0);
		addGenericTabTooltip(25841, 40, 40, "Toggle Run");
		addButton(25843, 9, 25844, "Interfaces/OptionTab/OPTION", 40, 40, "Show Settings", 1);
		addGenericTabTooltip(25844, 40, 40, "More");
		setBounds(25828, 19, 152, 27, Interface);
		setBounds(25831, 75, 152, 28, Interface);
		setBounds(25834, 131, 152, 29, Interface);
		setBounds(25837, 19, 206, 30, Interface);
		setBounds(152, 75, 206, 31, Interface);
		setBounds(25843, 131, 206, 32, Interface);
		setBounds(25857, 78, 159, 33, Interface);
		setBounds(25858, 136, 158, 34, Interface);
		setBounds(25859, 23, 212, 35, Interface);
		setBounds(25860, 86, 210, 36, Interface);
		setBounds(25861, 136, 211, 37, Interface);
		setBounds(25856, 23, 159, 38, Interface);
		setBounds(25829, 19, 152, 40, Interface);//tooltip
		setBounds(25832, 75, 152, 41, Interface);//tooltip
		setBounds(25835, 131, 152, 42, Interface);//tooltip
		setBounds(25838, 19, 206, 43, Interface);//tooltip
		setBounds(25841, 75, 206, 44, Interface);//tooltip
		setBounds(25844, 131, 206, 39, Interface);//tooltip
		addSprite(25856, 11, "Interfaces/OptionTab/OPTION");
		addSprite(25857, 12, "Interfaces/OptionTab/OPTION"); 
		addSprite(25858, 13, "Interfaces/OptionTab/OPTION"); 
		addSprite(25859, 14, "Interfaces/OptionTab/OPTION");
		addSprite(25860, 15, "Interfaces/OptionTab/OPTION");
		addSprite(25861, 17, "Interfaces/OptionTab/OPTION");
		addText(149, "100%", 0xFF9800, true, true, 52, TDA, 1);
		setBounds(149, 94, 230, 45, Interface);
    }
	
	public static void questTab(RSFont[] TDA){
		RSInterface Interface = addInterface(638);
		setChildren(4, Interface);
		addText(29155, "", 0xFF981F, false, true, 52, TDA, 2);
		addButton(29156, 1, "Interfaces/QuestTab/QUEST", 18, 18, "Swap to Achievement Diary", 1);
		addSprite(29157, 0, "Interfaces/QuestTab/QUEST");
		setBounds(29155, 10, 5, 0, Interface);
		setBounds(29156, 165, 5, 1, Interface);
		setBounds(29157, 3, 24, 2, Interface);
		setBounds(29160, 5, 29, 3, Interface);
		Interface = addInterface(29160);
		Interface.height = 214;
		Interface.width = 165;
		Interface.scrollMax = 1700;
		Interface.newScroller = false;
		setChildren(105, Interface);
		addText(29161, "Players Online", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29162, "Uptime", "Read Players Online", TDA, 0, 0xff0000, false, true, 150);
		addText(29163, "Special Quest", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29164, "Quest", "Read Special Quest Journal", TDA, 0, 0xff0000, false, true, 150);
		setBounds(29161, 4, 4, 0, Interface);
		setBounds(29162, 8, 22, 1, Interface);
		setBounds(29163, 4, 35, 2, Interface);
		setBounds(29164, 8, 53, 3, Interface);
		setBounds(663, 4, 67, 4, Interface);
		int Ypos = 83;
		int frameID = 5;
		for(int iD = 29165; iD <= 29264;iD++){
			addHoverText(iD, "", "View Quest"/*"View Quest Journal, "+iD*/, TDA, 0, 0xff0000, false, true, 150);
			setBounds(iD, 8, Ypos, frameID, Interface);
			frameID++;
			Ypos += 15;
			Ypos++;
		}
		Interface = addInterface(29265);
		try {
			setChildren(4, Interface);
			addText(29266, "Achievement Diary", 0xFF981F, false, true, -1, TDA, 2);
			addButton(29267, 2, "Interfaces/QuestTab/QUEST", 18, 18, "Swap to Quest Diary", 1);
			addSprite(29269, 0, "Interfaces/QuestTab/QUEST");
			setBounds(29266, 10, 5, 0, Interface);
			setBounds(29267, 165, 5, 1, Interface);
			setBounds(29269, 3, 24, 2, Interface);
			setBounds(29268, 5, 29, 3, Interface);
			Interface = addInterface(29268);
			Interface.height = 214;
			Interface.width = 165;
			Interface.scrollMax = 215;
			Interface.newScroller = false;
			setChildren(3, Interface);
			setBounds(29295, 8, 6, 0, Interface);
			setBounds(29287, 8, 21, 1, Interface);
			setBounds(29305, 8, 36, 2, Interface);
			addHoverText(29295, "Karamja", "Read Journal", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29287, "Varrock", "Read Journal", TDA, 0, 0xff0000, false, true, 150);
			addText(29305, "More coming soon...", 0xFF0000, false, true, -1, TDA, 0, 0xFFFFFF);
			} catch(Exception e){
				e.printStackTrace();
		}	
	}

	public static void clanChatTab(RSFont[] tda) {
        RSInterface tab = addTabInterface(18128);
        addHoverButton(18129, "Interfaces/ClanChat/BUTTON", 1, 72, 32, "Join Chat", 550, 18130, 1);
		addHoveredButton(18130, "Interfaces/ClanChat/BUTTON", 2, 72, 32, 18131);
        addHoverButton(18132, "Interfaces/ClanChat/BUTTON", 1, 72, 32, "Leave Chat", -1, 18133, 5);
        addHoveredButton(18133, "Interfaces/ClanChat/BUTTON", 2, 72, 32, 18134);
		addButtons(18250, 0, "Interfaces/ClanChat/LS", "Toggle lootshare", 18251, 1);
		addGenericTabTooltip(18251, 21, 26, "Toggle-Lootshare");
		addText(18135, "Join Chat", tda, 0, 0xff9b00, true, true);
        addText(18136, "Leave Chat", tda, 0, 0xff9b00, true, true);
        addSprite(18137, 1, "Interfaces/ClanChat/BACK");
        addText(18138, "Clan Chat", tda, 1, 0xff9b00, true, true);
        addText(18139, "Talking in: Not in chat", tda, 0, 0xff9b00, false, true);
        addText(18140, "Owner: None", tda, 0, 0xff9b00, false, true);
        tab.totalChildren(13);
        tab.child(0, 18137, 3, 57);
        tab.child(1, 18143, 7, 62);
        tab.child(2, 18129, 15, 227);
        tab.child(3, 18130, 15, 227);
        tab.child(4, 18132, 103, 226);
        tab.child(5, 18133, 103, 226);
        tab.child(6, 18135, 51, 238);
        tab.child(7, 18136, 139, 238);
        tab.child(8, 18138, 95, 3);
        tab.child(9, 18139, 10, 23);
        tab.child(10, 18140, 25, 38);
		tab.child(11, 18250, 148, 24);
		tab.child(12, 18251, 148, 24);
        RSInterface list = addTabInterface(18143);
        list.totalChildren(100);
        for(int i = 18144; i <= 18244; i++) {
            addText(i, "", tda, 0, 0xffffff, false, true);
        }
        for(int id = 18144, i = 0; id <= 18243 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 5;
            for(int id2 = 18144, i2 = 1; id2 <= 18243 && i2<= 99; id2++, i2++) {
                list.childY[0] = 2;
                list.childY[i2] = list.childY[i2 - 1] + 14;
            }
        }
        list.height = 152;
		list.width = 164;
        list.scrollMax = 1405;
    }
	
	public static void Pestpanel(RSFont[] tda) {
		RSInterface RSinterface = addTab(21005);
		addText(21006, "Next Departure:", 0xCCCBCB, false, true, 52, tda, 1);
		addText(21007, "Players Ready:", 0x5BD230, false, true, 52, tda, 1);
		addText(21008, "(Need 5 to 25 players)", 0xDED36A, false, true, 52, tda, 1);
		addText(21009, "Pest Points:", 0x99FFFF, false, true, 52, tda, 1);
		int last = 4;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21006, 15, 12, 0,RSinterface);
		setBounds(21007, 15, 30, 1,RSinterface);
		setBounds(21008, 15, 48, 2,RSinterface);
		setBounds(21009, 15, 66, 3,RSinterface);
	}
		
	public static void Pestpanel2(RSFont[] tda) {
		RSInterface RSinterface = addInterface(21100);
		addSprite(21101, 0, "Interfaces/PestControl/PEST1");
		addSprite(21102, 1, "Interfaces/PestControl/PEST1");
		addSprite(21103, 2, "Interfaces/PestControl/PEST1");
		addSprite(21104, 3, "Interfaces/PestControl/PEST1");
		addSprite(21105, 4, "Interfaces/PestControl/PEST1");
		addSprite(21106, 5, "Interfaces/PestControl/PEST1");
		addText(21107, "W", 0xCC00CC, false, true, 52, tda, 1);
		addText(21108, "E", 0x0000FF, false, true, 52, tda, 1);
		addText(21109, "SE", 0xFFFF44, false, true, 52, tda, 1);
		addText(21110, "SW", 0xCC0000, false, true, 52, tda, 1);
		addText(21111, "", 0x99FF33, false, true, 52, tda, 1);//w purp
		addText(21112, "", 0x99FF33, false, true, 52, tda, 1);//e blue
		addText(21113, "", 0x99FF33, false, true, 52, tda, 1);//se yel
		addText(21114, "", 0x99FF33, false, true, 52, tda, 1);//sw red
		addText(21115, "200", 0x99FF33, false, true, 52, tda, 1);//attacks
		addText(21116, "--", 0x99FF33, false, true, 52, tda, 1);//knights hp
		addText(21117, "Time Remaining:", 0xFFFFFF, false, true, 52, tda, 0);
		addText(21118, "-", 0xFFFFFF, false, true, 52, tda, 0);
		int last = 18;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21101, 361, 26, 0,RSinterface);
		setBounds(21102, 396, 26, 1,RSinterface);
		setBounds(21103, 436, 26, 2,RSinterface);
		setBounds(21104, 474, 26, 3,RSinterface);
		setBounds(21105, 3, 21, 4,RSinterface);
		setBounds(21106, 3, 50, 5,RSinterface);
		setBounds(21107, 371, 60, 6,RSinterface);
		setBounds(21108, 409, 60, 7,RSinterface);
		setBounds(21109, 443, 60, 8,RSinterface);
		setBounds(21110, 479, 60, 9,RSinterface);
		setBounds(21111, 362, 10, 10,RSinterface);
		setBounds(21112, 398, 10, 11,RSinterface);
		setBounds(21113, 436, 10, 12,RSinterface);
		setBounds(21114, 475, 10, 13,RSinterface);
		setBounds(21115, 32, 32, 14,RSinterface);
		setBounds(21116, 32, 62, 15,RSinterface);
		setBounds(21117, 8, 88, 16,RSinterface);
		setBounds(21118, 87, 88, 17,RSinterface);
	}
	
	public static void musicTab(RSFont[] TDA) {
		RSInterface Interface = addInterface(29500);
		addText(29501, "Playing:", 0xFF981F, false, true, 52,TDA, 1);
		addText(29502, "Attack", 0x33ff00, false, true, 52,TDA, 1);
		setChildren(3, Interface);
		setBounds(29501, 8, 13, 0, Interface);
		setBounds(29502, 8, 33, 1, Interface);
		setBounds(962, 0, 0, 2, Interface);
		Interface = interfaceCache[962];
		addText(963, "", 0xff9b00, false, true, 52,TDA, 1);
		addText(8934, "", 0xFF981F, false, true, 52,TDA, 1);
		addText(6272, "AUTO", 0xFF981F, false, true, 52,TDA, 1);
		addText(6271, "MAN", 0xFF981F, false, true, 52,TDA, 1);
		addText(9926, "LOOP", 0xFF981F, false, true, 52,TDA, 1);
		addText(5450, "", 0x33ff00, false, true, 52,TDA, 1);
		addText(4439, "", 0xFF981F, false, true, 52,TDA, 1);
		addText(3206, "Click the tune to play", 0xFF981F, false, true, 52,TDA, 1);
	}
	
	public static void skillInterface(RSFont tda[]) {
		RSInterface rsi = addTab(24200);
		rsi.totalChildren(24);
		rsi.child(0, 24199, 0, 0);
		//7 per row
		int[] ID = {
			4041, 4077, 4113, 4047, 4083, 4119, 4053,
			4089, 4125, 4059, 4095,4131, 4065, 4101,
			4137, 4071, 4107, 4143, 4154, 12168, 13918,
			13919, 13920
		};
		int[] content = {
			204,225,206,207,208,209,210,
			211,212,213,214,215,216,217,
			218,219,220,221,222,223,224,
			226,227
		};
		for(int i = 0; i < ID.length; i++) {
			skillToolTip(ID[i], content[i]);
		}
		int[] X = {
			0,0,0,0,0,0,64,
			64,64,64,64,64,128,128,
			128,128,128,128,0,64,128,
			0,64
		};
		int[] Y = {
			2,33,64,95,126,157,2,
			34,64,95,126,157,2,33,
			64,95,126,157,188,188,188,
			219,219
		};
		for(int i = 0; i < ID.length; i++) {
			setBounds(ID[i], X[i], Y[i], i + 1, rsi);
		}
	}
		
	public static void addNewSkills(RSFont tda[]) {
		RSInterface tab = addTab(24199);
		tab.totalChildren(7);
		tab.child(0, 3917, 0, 0);
		setBounds(24201, 40, 222, 2, tab);
		setBounds(24202, 102, 222, 5, tab);
		setBounds(24203, 52, 234, 3, tab);
		setBounds(24204, 114, 234, 6, tab);
		setBounds(24205, 64, 219, 4, tab);
		setBounds(24206, 1, 219, 1, tab);
		addSkillText(24201, "1", tda, true, 0, 0xFFFF00, true);
		addSkillText(24202, "1", tda, true, 0, 0xFFFF00, true);
		addSkillText(24203, "1", tda, true, 0, 0xFFFF00, true);
		addSkillText(24204, "1", tda, true, 0, 0xFFFF00, true);
		skillInterfaceButton(24205, 1, "Veiw @or1@Hunter@whi@ Guide");
		skillInterfaceButton(24206, 0, "Veiw @or1@Summoning@whi@ Guide");
	}

	public static void addOldSkills(RSFont tda[]) {
		RSInterface rsi = interfaceCache[3917];
		rsi.child(6, 3960, 124, 213);
		rsi.child(7, 3972, 68, 68);
		RSInterface Tab = interfaceCache[3960];
		Tab.child(5, 3984, 6, 15);
		Tab.child(4, 3983, 200, 200);
		Tab = interfaceCache[3961];
		Tab.width = 63;
		Tab.height = 32;
		Tab = interfaceCache[3962];
		Tab.width = 61;
		Tab.height = 30;
		Tab = interfaceCache[3963];
		Tab.width = 62;
		Tab.height = 31;
		Tab = interfaceCache[3964];
		Tab.width = 59;
		Tab.height = 28;
		Tab = interfaceCache[3984];
		Tab.rsFonts = tda[0];
		Tab.disabledMessage = "     Total\\nLevel: " + "%1";
		int[] ID = {
			4040,4046,4052,4058,4064,4070,4076,
			4082,4088,4094,4100,4106,4112,4118,
			4124,4130,4136,4142,4160,2832,13917,
			3985
		};
		for(int i = 0; i < ID.length; i++) {
			rsi.removeSomething(ID[i]);
		}
	}
	
	public static void EquipmentTab(RSFont[] wid) {
		RSInterface Interface = interfaceCache[1644];
		addSprite(15101, 0, "");//cheap hax
		addSprite(15102, 1, "");//cheap hax
		addSprite(15109, 2, "");//cheap hax
		removeSomething(15103);
		removeSomething(15104);
		setBounds(15101, 40, 205, 23, Interface);
		setBounds(15102, 110, 205, 24, Interface);
		setBounds(15109, 39, 240, 25, Interface);
		setBounds(27650, 0, 0, 26, Interface);
		Interface = addInterface(27650);
		addButton(27651, 6, "Interfaces/Equipment/BOX", "More", 27659, 1, 26, 33);
		addGenericTabTooltip(27659, 26, 18, "More");
		addButton(27653, 1, "Interfaces/Equipment/BOX", "Show Equipment Stats", 27655, 1, 40, 39);
		addGenericTabTooltip(27655, 40, 39, "Show Equipment Stats");
		addButton(27654, 2, "Interfaces/Equipment/BOX", "Show Items Kept on Death", 27657, 1, 40, 39);
		addGenericTabTooltip(27657, 40, 39, "Show Items Kept on Death");
		setChildren(6, Interface);
		setBounds(27651, 160, 5, 0, Interface);
		setBounds(27653, 40, 205, 1, Interface);
		setBounds(27654, 114, 205, 2, Interface);
		setBounds(27659, 160, 5, 3, Interface);
		setBounds(27655, 40, 205, 4, Interface);
		setBounds(27657, 114, 205, 5, Interface);
	}

	public static void PVPInterface(RSFont[] TDA) {
		RSInterface RSinterface = addInterface(21200);
		addSprite(21201, 0, "Other/NOTINWILD1");
		addText(21202, "126 - 138", TDA, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21201, 400, 285, 0,RSinterface);
		setBounds(21202, 444, 318, 1,RSinterface);
	}
	
	public static void PVPInterface2(RSFont[] TDA) {
		RSInterface RSinterface = addInterface(21300);
		addSprite(21301, 0, "Other/INWILD1");
		addText(21302, "126 - 138", TDA, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21301, 400, 285, 0,RSinterface);
		setBounds(21302, 444, 318, 1,RSinterface);
	}


	
	public static void equipmentScreen(RSFont[] TDA) {
		RSInterface Interface = addInterface(19148);
		addSprite(19149, 0, "Interfaces/Equipment/CHAR");
		addHover(19150, 3, 0, 19151, 3, "Interfaces/Equipment/CHAR", 21, 21, "Close");
		addHovered(19151, 2, "Interfaces/Equipment/CHAR", 21, 21, 19152);
		addText(19154, "Equip Your Character...", 0xFF981F, false, true, 52, TDA, 2);
		addText(1673, "Attack bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1674, "Defense bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1685, "Other bonuses", 0xFF981F, false, true, 52, TDA, 2);	
		addText(1675, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1676, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1677, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1678, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1679, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1680, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1681, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1682, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1683, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1684, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1686, "Strength:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1687, "Prayer:", 0xFF981F, false, true, 52, TDA, 1);
		addText(19155, "0%", 0xFF981F, false, true, 52, TDA, 1);
		addChar(19153);
		int last = 45, frame = 0;
		setChildren(last, Interface);
		setBounds(19149, 12, 20, frame, Interface);frame++;
		setBounds(19150, 472, 27, frame, Interface);frame++;
		setBounds(19151, 472, 27, frame, Interface);frame++;
		setBounds(19153, 193, 190, frame, Interface);frame++;
		setBounds(19154, 23, 29, frame, Interface);frame++;
		setBounds(1673, 365, 71, frame, Interface); frame++;
		setBounds(1674, 365, 163, frame, Interface); frame++;
		setBounds(1675, 372, 85, frame, Interface); frame++;
		setBounds(1676, 372, 99, frame, Interface); frame++;
		setBounds(1677, 372, 113, frame, Interface); frame++;
		setBounds(1678, 372, 127, frame, Interface); frame++;
		setBounds(1679, 372, 141, frame, Interface); frame++;
		setBounds(1680, 372, 177, frame, Interface); frame++;
		setBounds(1681, 372, 191, frame, Interface); frame++;
		setBounds(1682, 372, 205, frame, Interface); frame++;
		setBounds(1683, 372, 219, frame, Interface); frame++;
		setBounds(1684, 372, 233, frame, Interface); frame++;
		setBounds(1685, 365, 253, frame, Interface); frame++;
		setBounds(1686, 372, 267, frame, Interface); frame++;
		setBounds(1687, 372, 281, frame, Interface); frame++;
		setBounds(19155, 94, 286, frame, Interface); frame++;
		setBounds(1645, 83, 106, frame, Interface); frame++;
		setBounds(1646, 83, 135, frame, Interface); frame++;
		setBounds(1647, 83, 172, frame, Interface); frame++;
		setBounds(1648, 83, 213, frame, Interface); frame++;
		setBounds(1649, 27, 185, frame, Interface); frame++;
		setBounds(1650, 27, 221, frame, Interface); frame++;
		setBounds(1651, 139, 185, frame, Interface); frame++;
		setBounds(1652, 139, 221, frame, Interface); frame++;
		setBounds(1653, 53, 148, frame, Interface); frame++;
		setBounds(1654, 112, 148, frame, Interface); frame++;
		setBounds(1655, 63, 109, frame, Interface); frame++;
		setBounds(1656, 117, 108, frame, Interface); frame++;
		setBounds(1657, 83, 71, frame, Interface); frame++;
		setBounds(1658, 42, 110, frame, Interface); frame++;
		setBounds(1659, 83, 110, frame, Interface); frame++;
		setBounds(1660, 124, 110, frame, Interface); frame++;
		setBounds(1661, 27, 149, frame, Interface); frame++;
		setBounds(1662, 83, 149, frame, Interface); frame++;
		setBounds(1663, 139, 149, frame, Interface); frame++;
		setBounds(1664, 83, 189, frame, Interface); frame++;
		setBounds(1665, 83, 229, frame, Interface); frame++;
		setBounds(1666, 27, 229, frame, Interface); frame++;
		setBounds(1667, 139, 229, frame, Interface); frame++;
		setBounds(1688, 29, 111, frame, Interface); frame++;
	}
		
	public static void itemsOnDeath(RSFont[] wid) {
		RSInterface rsinterface = addInterface(17100);
		rsinterface.scrollMax = 0;
		rsinterface.interfaceShown = false;
		addSprite(17101, 2, 2);
		addText(17103, "", wid, 2, 0xff981f);
		addText(17104, "", wid, 1, 0xff981f);
		addText(17105, "", wid, 1, 0xff981f);
		addText(17106, "", wid, 1, 0xff981f);
		addText(17107, "", wid, 1, 0xffcc33);
		addText(17108, "", wid, 1, 0xffcc33);
		addText(17124, "", wid, 0, 0xff981f);
		addHover(17102, 3, 0, 17131, 1, "Interfaces/Equipment/SPRITE", 17, 17, "Close Window");
		addHovered(17131, 3, "Interfaces/Equipment/SPRITE", 17, 17, 17132);
		RSInterface background = interfaceCache[17101];
		background.spriteShadowed = true;
		rsinterface.totalChildren(13);
		setBounds(17101, 7, 8, 0, rsinterface);
		setBounds(17102, 480, 17, 1, rsinterface);
		setBounds(17103, 185, 18, 2, rsinterface);
		setBounds(17104, 22, 50, 3, rsinterface);
		setBounds(17105, 22, 110, 4, rsinterface);
		setBounds(17106, 347, 47, 5, rsinterface);
		setBounds(17107, 349, 270, 6, rsinterface);
		setBounds(17108, 398, 298, 7, rsinterface);
		setBounds(17129, 348, 64, 8, rsinterface);
		setBounds(10494, 26, 74, 9, rsinterface);
		setBounds(10600, 26, 133, 10, rsinterface);
		setBounds(17131, 480, 17, 11, rsinterface);
		
		setBounds(17124, 349, 255, 12, rsinterface);///Carried Wealth
		
	}
	
	public static void itemsOnDeathDATA(RSFont[] wid) {
		RSInterface rsinterface = addInterface(17129);
		rsinterface.parentID = 17129;
		rsinterface.id = 17129;
		rsinterface.type = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 130;
		rsinterface.height = 180;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.scrollMax = 280;
		int y = 0;
		int index = 0;
		setChildren(15, rsinterface);
		for(int idx = 17109; idx <= 17123;idx++) {
			addText(idx, "", wid, 0, 0xff981f);
			setBounds(idx, 0, y, index, rsinterface);
			index++;
			y += 12;
		}
	}
	
	public static void FriendList(RSFont[] wid) {
		RSInterface i = interfaceCache[5065];
		addText(5070, "Add Friend", wid, 0, 0xff9933);
		addText(5071, "Del Friend", wid, 0, 0xff9933);
		i.childX[0] = 2;
		i.childX[1] = 53;//friends list - world xxx X
		i.childX[4] = 25;//add friend X
		i.childX[5] = 106;//del friend X
		i.childY[0] = 25;
		i.childY[4] = 236;//add friend Y
		i.childY[5] = 236;//del friend Y
		i = i.interfaceCache[5066];
		i.height = 194;
		for(int idx = 0; idx < 400; idx++)
			i.childY[idx] -= 8;
		i = i.interfaceCache[5067];
		i.disabledMessage = "Friend List";
		i.textCentered = true;
		i.disabledColor = 0xff9933;
	}
	
	public static void IgnoreList(RSFont[] wid) {
		RSInterface i = interfaceCache[5715];
		addText(5720, "Add Name", wid, 0, 0xff9933);
		addText(5721, "Del Name", wid, 0, 0xff9933);
		i.childX[0] = 2;
		i.childX[1] = 54;//ignore list
		i.childX[4] = 25;//add name X
		i.childX[5] = 106;//del name X
		i.childY[0] = 25;
		i.childY[4] = 236;//add name Y
		i.childY[5] = 236;//del name Y
		i = i.interfaceCache[5716];
		i.height = 194;
		for(int idx = 0; idx < 100; idx++)
			i.childY[idx] -= 8;
		i = i.interfaceCache[5717];
		i.disabledMessage = "Ignore List";
		i.textCentered = true;
		i.disabledColor = 0xff9933;
	}
	
	public static void configureLunar(RSFont[] TDA) {
		homeTeleport();
		drawRune(30003, 1, "Fire");
		drawRune(30004, 2, "Water");
		drawRune(30005, 3, "Air");
		drawRune(30006, 4, "Earth");
		drawRune(30007, 5, "Mind");
		drawRune(30008, 6, "Body");
		drawRune(30009, 7, "Death");
		drawRune(30010, 8, "Nature");
		drawRune(30011, 9, "Chaos");
		drawRune(30012, 10, "Law");
		drawRune(30013, 11, "Cosmic");
		drawRune(30014, 12, "Blood");
		drawRune(30015, 13, "Soul");
		drawRune(30016, 14, "Astral");
		addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004, 64, 
			"Bake Pie",
			"Bake pies without a stove",
			TDA, 0, 16, 2);
		addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65,
			"Cure Plant",
			"Cure disease on farming patch",
			TDA, 1, 4, 2);
		addLunar3RunesBigBox(30032, 9075, 564, 558, 0, 0, 0, 30013, 30007, 65,
			"Monster Examine",
			"Detect the combat statistics of a\\nmonster",
			TDA, 2, 2, 2);
		addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005, 66,
			"NPC Contact",
			"Speak with varied NPCs",
			TDA, 3, 0, 2);
		addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006, 67,
			"Cure Other",
			"Cure poisoned players",
			TDA, 4, 8, 2);
		addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003, 67,
			"Humidify",
			"Fills certain vessels with water",
			TDA, 5, 0, 5);
		addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006, 68,
			"Moonclan Teleport",
			"Teleports you to moonclan island",
			TDA, 6, 0, 5);
		addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012, 30006, 69,
			"Tele Group Moonclan",
			"Teleports players to Moonclan\\nisland",
			TDA, 7, 0, 5);
		addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006, 70,
			"Ourania Teleport",
			"Teleports you to ourania rune altar",
			TDA, 8, 0, 5);
		addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012, 70,
			"Cure Me",
			"Cures Poison",
			TDA, 9, 0, 5);
		addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70,
			"Hunter Kit",
			"Get a kit of hunting gear",
			TDA, 10, 0, 5);
		addLunar3RunesSmallBox(30106, 9075, 563, 555, 1, 0, 0, 30012, 30004, 71,
			"Waterbirth Teleport",
			"Teleports you to Waterbirth island",
			TDA, 11, 0, 5);
		addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72,
			"Tele Group Waterbirth",
			"Teleports players to Waterbirth\\nisland",
			TDA, 12, 0, 5);
		addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012, 73,
			"Cure Group",
			"Cures Poison on players",
			TDA, 13, 0, 5);
		addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74,
			"Stat Spy",
			"Cast on another player to see their\\nskill levels",
			TDA, 14, 8, 2);
		addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74,
			"Barbarian Teleport",
			"Teleports you to the Barbarian\\noutpost",
			TDA, 15, 0, 5);
		addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75,
			"Tele Group Barbarian",
			"Teleports players to the Barbarian\\noutpost",
			TDA, 16, 0, 5);
		addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005, 76,
			"Superglass Make",
			"Make glass without a furnace",
			TDA, 17, 16, 2);
		addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004, 77,
			"Khazard Teleport",
			"Teleports you to Port khazard",
			TDA, 18, 0, 5);
		addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004, 78,
			"Tele Group Khazard",
			"Teleports players to Port khazard",
			TDA, 19, 0, 5);
		addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78,
			"Dream",
			"Take a rest and restore hitpoints 3\\n times faster",
			TDA, 20, 0, 5);
		addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004, 79,
			"String Jewellery",
			"String amulets without wool",
			TDA, 21, 0, 5);
		addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004, 80,
			"Stat Restore Pot\\nShare",
			"Share a potion with up to 4 nearby\\nplayers",
			TDA, 22, 0, 5);
		addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004, 81,
			"Magic Imbue",
			"Combine runes without a talisman",
			TDA, 23, 0, 5);
		addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82,
			"Fertile Soil",
			"Fertilise a farming patch with super\\ncompost",
			TDA, 24, 4, 2);
		addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83,
			"Boost Potion Share",
			"Shares a potion with up to 4 nearby\\nplayers",
			TDA, 25, 0, 5);
		addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004, 84,
			"Fishing Guild Teleport",
			"Teleports you to the fishing guild",
			TDA, 26, 0, 5);
		addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004, 85, 
			"Tele Group Fishing Guild",
			"Teleports players to the Fishing\\nGuild",
			TDA, 27, 0, 5);
		addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010, 85,
			"Plank Make",
			"Turn Logs into planks",
			TDA, 28, 16, 5);
		addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004, 86, 
			"Catherby Teleport",
			"Teleports you to Catherby",
			TDA, 29, 0, 5);
		addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004, 87, 
			"Tele Group Catherby",
			"Teleports players to Catherby",
			TDA, 30, 0, 5);
		addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004, 88,
			"Ice Plateau Teleport",
			"Teleports you to Ice Plateau",
			TDA, 31, 0, 5);
		addLunar3RunesLargeBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89, 
			"Tele Group Ice Plateau",
			"Teleports players to Ice Plateau",
			TDA, 32, 0, 5);
		addLunar3RunesBigBox(30282, 9075, 563, 561, 2, 1, 0, 30012, 30010, 90,
			"Energy Transfer",
			"Spend HP and SA energy to\\n give another SA and run energy",
			TDA, 33, 8, 2);
		addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91,
			"Heal Other",
			"Transfer up to 75% of hitpoints\\n to another player",
			TDA, 34, 8, 2);
		addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92,
			"Vengeance Other",
			"Allows another player to rebound\\ndamage to an opponent",
			TDA, 35, 8, 2);
		addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9, 30009, 30006, 93,
			"Vengeance",
			"Rebound damage to an opponent", 
			TDA, 36, 0, 5);
		addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94,
			"Heal Group",
			"Transfer up to 75% of hitpoints\\n to a group",
			TDA, 37, 0, 5);
		addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95,
			"Spellbook Swap",
			"Change to another spellbook for 1\\nspell cast", 
			TDA, 38, 0, 5);
	}
	
	public static void constructLunar() {
		RSInterface Interface = addTabInterface(16640);
		setChildren(80, Interface);
		setBounds(30000, 11, 10, 0, Interface);
		setBounds(30017, 40, 9, 1, Interface);
		setBounds(30025, 71, 12, 2, Interface);
		setBounds(30032, 103, 10, 3, Interface);
		setBounds(30040, 135, 12, 4, Interface);
		setBounds(30048, 165, 10, 5, Interface);
		setBounds(30056, 8, 38, 6, Interface);
		setBounds(30064, 39, 39, 7, Interface);
		setBounds(30075, 71, 39, 8, Interface);
		setBounds(30083, 103, 39, 9, Interface);
		setBounds(30091, 135, 39, 10, Interface);
		setBounds(30099, 165, 37, 11, Interface);
		setBounds(30106, 12, 68, 12, Interface);
		setBounds(30114, 42, 68, 13, Interface);
		setBounds(30122, 71, 68, 14, Interface);
		setBounds(30130, 103, 68, 15, Interface);
		setBounds(30138, 135, 68, 16, Interface);
		setBounds(30146, 165, 68, 17, Interface);
		setBounds(30154, 14, 97, 18, Interface);
		setBounds(30162, 42, 97, 19, Interface);
		setBounds(30170, 71, 97, 20, Interface);
		setBounds(30178, 101, 97, 21, Interface);
		setBounds(30186, 135, 98, 22, Interface);
		setBounds(30194, 168, 98, 23, Interface);
		setBounds(30202, 11, 125, 24, Interface);
		setBounds(30210, 42, 124, 25, Interface);
		setBounds(30218, 74, 125, 26, Interface);
		setBounds(30226, 103, 125, 27, Interface);
		setBounds(30234, 135, 125, 28, Interface);
		setBounds(30242, 164, 126, 29, Interface);
		setBounds(30250, 10, 155, 30, Interface);
		setBounds(30258, 42, 155, 31, Interface);
		setBounds(30266, 71, 155, 32, Interface);
		setBounds(30274, 103, 155, 33, Interface);
		setBounds(30282, 136, 155, 34, Interface);
		setBounds(30290, 165, 155, 35, Interface);
		setBounds(30298, 13, 185, 36, Interface);
		setBounds(30306, 42, 185, 37, Interface);
		setBounds(30314, 71, 184, 38, Interface);
		setBounds(30322, 104, 184, 39, Interface);
		setBounds(30001, 6, 184, 40, Interface);// hover
		setBounds(30018, 5, 176, 41, Interface);// hover
		setBounds(30026, 5, 176, 42, Interface);// hover
		setBounds(30033, 5, 163, 43, Interface);// hover
		setBounds(30041, 5, 176, 44, Interface);// hover
		setBounds(30049, 5, 176, 45, Interface);// hover
		setBounds(30057, 5, 176, 46, Interface);// hover
		setBounds(30065, 5, 176, 47, Interface);// hover
		setBounds(30076, 5, 163, 48, Interface);// hover
		setBounds(30084, 5, 176, 49, Interface);// hover
		setBounds(30092, 5, 176, 50, Interface);// hover
		setBounds(30100, 5, 176, 51, Interface);// hover
		setBounds(30107, 5, 176, 52, Interface);// hover
		setBounds(30115, 5, 163, 53, Interface);// hover
		setBounds(30123, 5, 176, 54, Interface);// hover
		setBounds(30131, 5, 163, 55, Interface);// hover
		setBounds(30139, 5, 163, 56, Interface);// hover
		setBounds(30147, 5, 163, 57, Interface);// hover
		setBounds(30155, 5, 176, 58, Interface);// hover
		setBounds(30163, 5, 176, 59, Interface);// hover
		setBounds(30171, 5, 176, 60, Interface);// hover
		setBounds(30179, 5, 163, 61, Interface);// hover
		setBounds(30187, 5, 176, 62, Interface);// hover
		setBounds(30195, 5, 149, 63, Interface);// hover
		setBounds(30203, 5, 176, 64, Interface);// hover
		setBounds(30211, 5, 163, 65, Interface);// hover
		setBounds(30219, 5, 163, 66, Interface);// hover
		setBounds(30227, 5, 176, 67, Interface);// hover
		setBounds(30235, 5, 149, 68, Interface);// hover
		setBounds(30243, 5, 176, 69, Interface);// hover
		setBounds(30251, 5, 5, 70, Interface);// hover
		setBounds(30259, 5, 5, 71, Interface);// hover
		setBounds(30267, 5, 5, 72, Interface);// hover
		setBounds(30275, 5, 5, 73, Interface);// hover
		setBounds(30283, 5, 5, 74, Interface);// hover
		setBounds(30291, 5, 5, 75, Interface);// hover
		setBounds(30299, 5, 5, 76, Interface);// hover
		setBounds(30307, 5, 5, 77, Interface);// hover
		setBounds(30323, 5, 5, 78, Interface);// hover
		setBounds(30315, 5, 5, 79, Interface);// hover
	}
	
	public static void configureBank(RSFont[] TDA) {
		RSInterface Tab = addTabInterface(23000);
		addText(23003, "item amount", TDA, 0, 0xFFB000);
		addHover(23004, 3, 0, 23005, 3, "Interfaces/Equipment/CHAR", 21, 21, "Close");
		addHovered(23005, 2, "Interfaces/Equipment/CHAR", 21, 21, 23006);
		addHover(23007, 4, 0, 23008, 5, "Interfaces/Bank/BANK", 35, 25, "Deposit carried items");
		addHovered(23008, 6, "Interfaces/Bank/BANK", 35, 25, 23009);
		addText(19999, "Combat Level: ", TDA, 0, 0xFF981F);
		setChildren(6, Tab);
		setBounds(5292, 0, 0, 0, Tab);
		setBounds(23003, 410, 30, 1, Tab);
		setBounds(23004, 472, 27, 2, Tab);
		setBounds(23005, 472, 27, 3, Tab);
		setBounds(23007, 450, 288, 4, Tab);
		setBounds(23008, 450, 288, 5, Tab);
		RSInterface rsi = interfaceCache[5292];
		addText(5384, "", TDA, 0, 0xFFB000);//cheap hax for 'close window'
		rsi.childX[90] = 410;
		rsi.childY[90] = 288;
	}

	public static void configureCharDesign(RSFont[] TDA) {
		addText(3649, "Welcome to "+Config.ClientName+" - Use the buttons below to design your player", TDA, 2, 0xFF9442);
		addText(3658, "Torso", TDA, 1, 0xFF9442);
		addText(3673, "Arms", TDA, 1, 0xFF9442);
		addText(3674, "Legs", TDA, 1, 0xFF9442);
		addText(3675, "Head", TDA, 1, 0xFF9442);
		addText(3676, "Hands", TDA, 1, 0xFF9442);
		addText(3677, "Feet", TDA, 1, 0xFF9442);
		addText(3678, "Jaw", TDA, 1, 0xFF9442);
		addText(3690, "Hair", TDA, 1, 0xFF9442);
		addText(3691, "Torso", TDA, 1, 0xFF9442);
		addText(3692, "Legs", TDA, 1, 0xFF9442);
		addText(3693, "Skin", TDA, 1, 0xFF9442);
		addText(3694, "Feet", TDA, 1, 0xFF9442);
		addText(3700, "Male", TDA, 1, 0xFF9442);
		addText(3701, "Female", TDA, 1, 0xFF9442);
	}
	
	private Model method206(int i, int j) {
		Model model = (Model) modelNodes.insertFromCache((i << 16) + j);
		if(model != null)
			return model;
		if(i == 1)
			model = Model.method462(j);
		if(i == 2)
			model = EntityDef.forID(j).method160();
		if(i == 3)
			model = client.myPlayer.method453();
		if(i == 4)
			model = ItemDef.forID(j).method202(50);
		if(i == 5)
			model = null;
		if(model != null)
			modelNodes.removeFromCache(model, (i << 16) + j);
		return model;
	}

	static int f = 0;
	static int f2 = 0;

	private static Sprite method207(int i, NamedArchive archive, String s) {
		long l = (TextClass.method585(s) << 8) + (long) i;
		if (s.equals("combatboxes")) {
			if (++ f2 == 2) {
				f2 = 3;
				return null;
			}
		}
		if (s.contains("prayer")) {
			if (++ f > 93)
				return null;
		}
		Sprite sprite = (Sprite) spriteNodes.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(archive, s, i);
			if (sprite == null)
				return null;
			spriteNodes.removeFromCache(sprite, l);
		} catch(Exception _ex) {
			return null;
		}
		return sprite;
	}

	public static void method208(boolean flag, Model model) {
		int i = 0;//was parameter
		int j = 5;//was parameter
		if(flag)
			return;
		modelNodes.unlinkAll();
		if(model != null && j != 4)
			modelNodes.removeFromCache(model, (j << 16) + i);
	}

	public Model method209(int j, int k, boolean flag) {
		Model model;
		if(flag)
			model = method206(enabledMediaType, enabledMediaID);
		else
			model = method206(disabledMediaType, disabledMediaID);
		if(model == null)
			return null;
		if(k == -1 && j == -1 && model.anIntArray1640 == null)
			return model;
		Model model_1 = new Model(true, FrameReader.method532(k) & FrameReader.method532(j), false, model);
		if(k != -1 || j != -1)
			model_1.method469();
		if(k != -1)
			model_1.method470(k);
		if(j != -1)
			model_1.method470(j);
		model_1.method479(64, 768, -50, -10, -50, true);
			return model_1;
	}

	public RSInterface() {}
	
	public static NamedArchive aClass44;
	/* Custom added */
	public boolean drawsTransparent;
	public String hoverText;
	public String popupString;
	public boolean newScroller;
	public boolean inventoryhover;
	public boolean spriteShadowed;
	public static RSFont[] fonts;
	
	/* Class */
	public Sprite disabledSprite;
	public int animationDelay;
	public Sprite sprites[];
	public static RSInterface interfaceCache[];
	public int requiredValues[];
	public int contentType;
	public int spritesX[];
	public int disabledHoverColor;
	public int atActionType;
	public String spellName;
	public int enabledColor;
	public int width;
	public String tooltip;
	public String selectedActionName;
	public boolean textCentered;
	public int scrollPosition;
	public String actions[];
	public int valueIndexArray[][];
	public boolean boxFilled;
	public String enabledMessage;
	public int hoverType;
	public int invSpritePadX;
	public int disabledColor;
	public int disabledMediaType;
	public int disabledMediaID;
	public boolean deletesTargetSlot;
	public int parentID;
	public int spellUsableOn;
	private static MRUNodes spriteNodes;
	public int enabledHoverColor;
	public int children[];
	public int childX[];
	public boolean usableItemInterface;
	public RSFont rsFonts;
	public int invSpritePadY;
	public int valueCompareType[];
	public int animationLength;
	public int spritesY[];
	public String disabledMessage;
	public boolean isInventoryInterface;
	public int id;
	public int inventoryValue[];
	public int inventory[];
	public byte opacity;
	private int enabledMediaType;
	private int enabledMediaID;
	public int disabledAnimation;
	public int enabledAnimation;
	public boolean allowSwapItems;
	public Sprite enabledSprite;
	public int scrollMax;
	public int type;
	boolean aBoolean266;
	public int xOffset;
	private static final MRUNodes modelNodes = new MRUNodes(30);
	public int yOffset;
	public boolean interfaceShown;
	public int height;
	public boolean textShadowed;
	public int modelZoom;
	public int modelRotationY;
	public int modelRotationX;
	public int childY[];
}
