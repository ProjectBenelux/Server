// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)

public final class FrameReader
{
	public static void loadSkins()
	{
		//System.out.println("Preloading Skins...");
		ByteBuffer ByteBuffer = new ByteBuffer(FileOperations.ReadFile(signlink.findcachedir()+"data/Skins.dat"));

		/*
Format
2byte numData
loop
2byte - fileID - could be removed realy
4byte - fileSize
ByteArray of fileSize -- gziped
repeat
*/

		int numSkins = ByteBuffer.g2();
		skinData = new byte[numSkins][];

		for(int i = 0; i < numSkins; i++)
		{
			int fileID = ByteBuffer.g2();
			int compressedSize = ByteBuffer.g4();
			byte[] compressedData = ByteBuffer.getData(new byte[compressedSize]);
			byte[] decompressedData = JavaUncompress.decompress(compressedData);
			skinData[fileID] = decompressedData;
		}
		//System.out.println("preloaded "+numSkins+" skins");
	}

	public static void loadFrames()
	{
		//System.out.println("Preloading Frames...");
		ByteBuffer ByteBuffer = new ByteBuffer(FileOperations.ReadFile(signlink.findcachedir()+"data/Frames.dat"));

		/*
Format
2byte - fileID - could be removed realy
4byte - fileSize
ByteArray of fileSize -- gziped
repeat
*/

		int numFrames = ByteBuffer.g2();
		frameData = new byte[numFrames][];

		for(int i = 0; i < numFrames; i++)
		{
			int fileID = ByteBuffer.g2();
			int compressedSize = ByteBuffer.g4();
			byte[] compressedData = ByteBuffer.getData(new byte[compressedSize]);
			byte[] decompressedData = JavaUncompress.decompress(compressedData);
			frameData[fileID] = decompressedData;
		}
		//System.out.println("preloaded "+numFrames+" frames");

	}
	
	public static byte[] getData(int i1, int i2) {
		//if(frameData == null)
		//loadFrames();
		
		//if(skinData == null)
		//loadSkins();

		if (i1 == 0)
		{
			//return FileOperations.ReadFile("./extras/frames/" + i2 + ".dat");
			return frameData[i2];
		}
		else
		{
			//return FileOperations.ReadFile("./extras/skinlist/" + i2 + ".dat");
			return skinData[i2];
		}
	}
	
	public static void method528(int i)
	{
		animationlist = new FrameReader[4000][0];
	}

	public static void load(int file){
		try {
			// System.out.println(file);
			ByteBuffer ByteBuffer = new ByteBuffer(getData(0, file));
			ByteBuffer ByteBuffer1 = new ByteBuffer(getData(1, file));
			FrameList FrameList = new FrameList(ByteBuffer1, 0);
			int k1 = ByteBuffer.readUnsignedWord();
			animationlist[file] = new FrameReader[k1];
			int ai[] = new int[500];
			int ai1[] = new int[500];
			int ai2[] = new int[500];
			int ai3[] = new int[500];
			for(int l1 = 0; l1 < k1; l1++)
			{
				int i2 = ByteBuffer.readUnsignedWord();
				FrameReader FrameReader = animationlist[file][i2] = new FrameReader();
				FrameReader.aClass18_637 = FrameList;
				int j2 = ByteBuffer.readUnsignedByte();
				int l2 = 0;
				int k2 = -1;
				for(int i3 = 0; i3 < j2; i3++)
				{
					int j3 = ByteBuffer.readUnsignedByte();
					
					if(j3 > 0)
					{
						if(FrameList.anIntArray342[i3] != 0)
						{
							for(int l3 = i3 - 1; l3 > k2; l3--)
							{
								if(FrameList.anIntArray342[l3] != 0)
								continue;
								ai[l2] = l3;
								ai1[l2] = 0;
								ai2[l2] = 0;
								ai3[l2] = 0;
								l2++;
								break;
							}

						}
						ai[l2] = i3;
						short c = 0;
						if(FrameList.anIntArray342[i3] == 3)
						c = (short)128;

						if((j3 & 1) != 0)
						ai1[l2] = (short)ByteBuffer.readShort2();
						else
						ai1[l2] = c;
						if((j3 & 2) != 0)
						ai2[l2] = ByteBuffer.readShort2();
						else
						ai2[l2] = c;
						if((j3 & 4) != 0)
						ai3[l2] = ByteBuffer.readShort2();
						else
						ai3[l2] = c;
						k2 = i3;
						l2++;
					}
				}

				FrameReader.anInt638 = l2;
				FrameReader.anIntArray639 = new int[l2];
				FrameReader.anIntArray640 = new int[l2];
				FrameReader.anIntArray641 = new int[l2];
				FrameReader.anIntArray642 = new int[l2];
				for(int k3 = 0; k3 < l2; k3++)
				{
					FrameReader.anIntArray639[k3] = ai[k3];
					FrameReader.anIntArray640[k3] = ai1[k3];
					FrameReader.anIntArray641[k3] = ai2[k3];
					FrameReader.anIntArray642[k3] = ai3[k3];
				}

			}
		}catch(Exception exception) { }
	}
	public static void nullLoader()
	{
		animationlist = null;
	}

	public static FrameReader method531(int j)
	{
		if(animationlist == null)
		return null;
		String hex = Integer.toHexString(j);
		int file = Integer.parseInt(hex.substring(0,(hex.length()-4)), 16);
		int frame = Integer.parseInt(hex.substring((hex.length()-4)), 16);
		if(animationlist[file].length == 0)
		load(file);
		return animationlist[file][frame];

	}



	public static boolean method532(int i)
	{
		return i == -1;
	}

	private FrameReader()
	{
	}
	private static FrameReader animationlist[][];
	public int anInt636;
	public FrameList aClass18_637;
	public int anInt638;
	public static byte[][] frameData = null;
	public static byte[][] skinData = null;
	public int anIntArray639[];
	public int anIntArray640[];
	public int anIntArray641[];
	public int anIntArray642[];
	private static boolean[] aBooleanArray643;

}