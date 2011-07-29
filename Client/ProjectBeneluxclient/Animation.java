
// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class Animation {
	public static byte[] getData(String s) {
			return FileOperations.ReadFile(s);
	}
    public static void unpackConfig(NamedArchive NamedArchive)
    {
           ByteBuffer ByteBuffer = new ByteBuffer(getData("C:/projectfatalityv6/data/animation/seq.dat"));
        int length = ByteBuffer.readUnsignedWord();
        if(anims == null)
            anims = new Animation[length];
        for(int j = 0; j < length; j++)
        {
            if(anims[j] == null)
                anims[j] = new Animation();
            anims[j].readValues(ByteBuffer);
        }
    }

   public int method258(int i)
    {
        int j = anIntArray355[i];
        if(j == 0)
        {
            FrameReader class36 = FrameReader.method531(anIntArray353[i]);
            if(class36 != null)
                j = anIntArray355[i] = class36.anInt636;
        }
        if(j == 0)
            j = 1;
        return j;
    }

     private void readValues(ByteBuffer ByteBuffer)
    {
                        anInt360 = ByteBuffer.readUnsignedWord();
                            anInt361 = ByteBuffer.readUnsignedWord();
                        anInt359 = ByteBuffer.readUnsignedByte();
                        anInt356 = ByteBuffer.readUnsignedWord();
                        anInt352 = ByteBuffer.readUnsignedWord();
                        anIntArray353 = new int[anInt352];
                        anIntArray354 = new int[anInt352];
                        anIntArray355 = new int[anInt352];
                            if (anInt360 == 65535)
                                anInt360 = -1;
                        if (anInt360 > 0)
                                    anInt360 += 512;
                        if (anInt361 == 65535)
                                    anInt361 = -1;
                            if (anInt361 > 0)
                                anInt361 += 512;
                        for(int i=0; i < anInt352; i++)
                                anIntArray353[i] = ByteBuffer.readDWord();
                        for(int i=0; i < anInt352; i++)
                                anIntArray354[i] = -1;
                        for(int i=0; i < anInt352; i++)
                                    anIntArray355[i] = ByteBuffer.readUnsignedByte();
    }

    private Animation()
    {
        anInt356 = -1;
        aBoolean358 = false;
        anInt359 = 5;
        anInt360 = -1;
        anInt361 = -1;
        anInt362 = 99;
        anInt363 = -1;
        anInt364 = -1;
        anInt365 = 2;
    }

    public static Animation anims[];
    public int anInt352;
    public int anIntArray353[];
    public int anIntArray354[];
    private int[] anIntArray355;
    public int anInt356;
    public int anIntArray357[];
    public boolean aBoolean358;
    public int anInt359;
    public int anInt360;
    public int anInt361;
    public int anInt362;
    public int anInt363;
    public int anInt364;
    public int anInt365;
    public static int anInt367;
}