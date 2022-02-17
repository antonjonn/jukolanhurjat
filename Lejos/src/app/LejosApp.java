package app;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class LejosApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			System.out.println("It's Donkey Time");
		}
		
		Sound.playSample(new File("aasi.wav"), Sound.VOL_MAX);
		
		Button.waitForAnyPress();
	}

}
