package main;
import java.io.Serializable;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
public class Sound{
	
   Clip clip;
   URL soundURL[] = new URL[30];
   FloatControl fc;
	int volumeScale = 3;
	float volume;
   
   public Sound() {
	   soundURL[0] = getClass().getResource("/sound/vamp9.wav");
	   soundURL[1] = getClass().getResource("/sound/bossfight.wav");
	   soundURL[2] = getClass().getResource("/sound/Coffin.wav");
	   soundURL[3] = getClass().getResource("/sound/Damage1.wav");
	   soundURL[4] = getClass().getResource("/sound/Explosion5.wav");
	   soundURL[5] = getClass().getResource("/sound/Kill2.wav");
	   soundURL[6] = getClass().getResource("/sound/Monster3.wav");
	   soundURL[7] = getClass().getResource("/sound/Recovery1.wav");
	   soundURL[8] = getClass().getResource("/sound/Sword3.wav");
	   soundURL[9] = getClass().getResource("/sound/Teleport2.wav");
	   soundURL[10] = getClass().getResource("/sound/heal7.wav");
	   soundURL[11] = getClass().getResource("/sound/tortured_male2.wav");
	   
   }
   public void setFile(int i) {
	   try {
		   AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
		   clip = AudioSystem.getClip();
		   clip.open(ais);
		   fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
	   }catch( Exception e) {
		   
	   }
   }
   public void play() {
	   clip.start();
   }
   public void loop() {
	   clip.loop(Clip.LOOP_CONTINUOUSLY);
   }
   public void stop() {
	   clip.stop();
   }
   
   public void checkVolume() {
		
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		
		fc.setValue(volume);
	}
}
