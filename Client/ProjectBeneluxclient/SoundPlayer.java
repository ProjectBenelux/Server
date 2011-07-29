/*
 * SoundPlayer.java
 * 
 * Version 1.0
 *
 * Create by bluurr on 28 Sep 2009
 * 
 * Educational Purpose / Free for use.
 */

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundPlayer implements Runnable{
	
	private AudioInputStream stream;
	private DataLine.Info info;
	private Clip soundeffect;
	
	private InputStream musicfile;
	private Thread player;

	public SoundPlayer(InputStream musicfile) {
		this.musicfile = musicfile;
		player = new Thread(this);
		player.start();
	}
	
	@Override
	public void run() {
		  try {
			 stream = AudioSystem.getAudioInputStream(musicfile);  //Creates the stream from the stored sound file in the client
      		 info = new DataLine.Info(Clip.class, stream.getFormat());   
      		 
      		 soundeffect = (Clip) AudioSystem.getLine(info);  
      		 soundeffect.open(stream);  //Reads the stream
      		 soundeffect.start();		//Starts playing sound
      		 
      		 while(soundeffect.isActive()){
      			Thread.sleep(250);      		 
      		}
      		Thread.sleep(10000); // allow sound to fully finsh
      		
     		soundeffect.close(); // Closes SoundPlayer
     		stream.close(); 	 // Closes Stream
     		player.interrupt();  // Interrupts this thread to exit.
     		
		  }catch(Exception e){
			  player.interrupt();
			  System.out.println("Error player sounds");
		  }
	}
}