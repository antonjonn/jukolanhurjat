package app;
import java.io.File;

import lejos.hardware.*;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;


/**
 * @author Anton Jonninen, Veeti Polsa, Aleksander Kuivila
 * Date 9.3. 2022
 * This is an application for an Lejos robot to follow a line and detecting and  avoiding obstacles
 */ 

public class main {
	
	private static DataExc DE;
	
	private static LineFollow LFobj;
	
	private static ObstacleDetect ODobj;
	
	/** main method starts threads and waits for press to start the app
	 * @param args
	 */
	public static void main(String[] args) {
		//music();
		
		
		DataExc DE = new DataExc();
		LineFollow LFobj = new LineFollow(DE);
		ObstacleDetect ODobj = new ObstacleDetect(DE);
		PlaySound Sobj = new PlaySound();
		
		Thread thread_a = new Thread(DE);
		Thread thread_b = new Thread(LFobj);
		Thread thread_c = new Thread(ODobj);
		Thread thread_d = new Thread(Sobj);
		System.out.println("Gentlemen, Start your Engines \n Press Enter");
		Button.ENTER.waitForPress();
		
		// Enter press is in LineFollow class
		
//		if (Button.ENTER.isDown()) 
//		Button.ENTER.waitForPress();
		
//		Delay.msDelay(1000);
		thread_d.start();
		thread_a.start();
		thread_b.start();
		thread_c.start();
		


		
	}
	
	
}
