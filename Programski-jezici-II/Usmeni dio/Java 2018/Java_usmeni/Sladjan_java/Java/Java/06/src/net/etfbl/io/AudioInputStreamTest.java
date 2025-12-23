package net.etfbl.io;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioInputStreamTest {
	public AudioInputStreamTest() {

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("D:\\Eclipse\\workspace\\PJ2_2015_06\\music.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			Thread.sleep(10000);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new AudioInputStreamTest();
	}
}