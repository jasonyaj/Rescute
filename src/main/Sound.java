package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		// collect bunny SE
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		// level completion
		soundURL[2] = getClass().getResource("/sound/fanfare.wav");
		// drop bunny in den
		soundURL[3] = getClass().getResource("/sound/drop.wav");
		// title screen chosen menu SE
		soundURL[4] = getClass().getResource("/sound/powerup.wav");
		// title screen cursor SE
		soundURL[5] = getClass().getResource("/sound/speak.wav");
		// Map 1 song
		soundURL[6] = getClass().getResource("/sound/Merchant.wav");
		// Map 2 song
		soundURL[7] = getClass().getResource("/sound/Dungeon.wav");
	}
	//method to open audio files in java
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e){
			
		}
	}
	//start sound
	public void play() {
		clip.start();
	}
	
	// loop the sound
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// stop the sound
	public void stop() {
		clip.stop();
	}
}
