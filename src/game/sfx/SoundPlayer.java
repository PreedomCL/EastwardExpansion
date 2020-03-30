package game.sfx;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
	
	public static void playSound(File sound) {
		startNewThread(sound);
	}
	
	public static void startSound(File sound) {
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			
			Thread.sleep((long) (clip.getFrameLength()/clip.getFormat().getFrameRate()));
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void startNewThread(File sound) {
		Runnable r = new Runnable() {
			public void run() {
				startSound(sound);
			}
		};
		
		Thread sThread = new Thread(r);
		sThread.start();
	}
}
