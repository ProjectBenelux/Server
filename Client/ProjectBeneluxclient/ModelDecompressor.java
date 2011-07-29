import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;


/** Author: Ben */

public class ModelDecompressor {
	
public ModelDecompressor() 
{
	decompressors = new FileStore[5];
}
	public static String loc = signlink.findcachedir()+"Data/Model/";
	public static String loadMDL() {
		if(Config.detail525)
			return loc+"525/";
		else
			return loc+"474/";
	}
	static FileStore[] decompressors;


//			DataInputStream indexFile = new DataInputStream(new FileInputStream("C:/ProjectFatalityV5/Data/Model/525/MDL_cache.idx"));
			//DataInputStream dataFile = new DataInputStream(new FileInputStream(loadMDL()+"MDL_cache.dat"));

	public static byte[] data;
	public static void loadModelDataFile() {
		try {
			DataInputStream indexFile = new DataInputStream(new FileInputStream("C:/MAP_CACHE.idx"));
			DataInputStream dataFile = new DataInputStream(new FileInputStream("C:/MAP_CACHE.dat"));


			
			int length = indexFile.readInt();
			//data = new byte[length];

			for(int i = 0; i < length; i++) {
				int id = indexFile.readInt();
				int invlength = indexFile.readInt();
				byte[] data = new byte[invlength];
				dataFile.readFully(data);

				Model.method460(data, id);
	FileOperations.WriteFile("./maps/"+id+".dat", data);
	System.err.println("MODEL DUMPED "+id+"");



			}
			System.out.println("Loaded the Model cache.");
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void loadModelDataFile2() {
		try {
			DataInputStream indexFile = new DataInputStream(new FileInputStream("C:/ProjectFatalityV6/Data/Model/525/MDL_cache.idx"));
			DataInputStream dataFile = new DataInputStream(new FileInputStream("C:/ProjectFatalityV6/Data/Model/525/MDL_cache.dat"));
			int length = indexFile.readInt();
			//data = new byte[length];
			for(int i = 0; i < length; i++) {
				int id = indexFile.readInt();
				int invlength = indexFile.readInt();
				byte[] data = new byte[invlength];


				dataFile.readFully(data);
		if(i == 1570 || i == 1637 || i == 8157 || i == 8131 || i == 8037 || i == 8023 || i == 1699 || i == 1667 || i == 1688)
{
FileOperations.WriteFile("C:/OBJMODELS/"+i+".dat", data);
	System.err.println("MODEL DUMPED "+i+"");
}
			}
			//System.out.println("Loaded the Model cache.");
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}