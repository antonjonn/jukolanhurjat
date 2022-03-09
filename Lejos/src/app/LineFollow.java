package app;

import lejos.ev3.*;
import app.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.motor.*;
import lejos.robotics.Color;
import lejos.utility.Delay;
import app.*;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import java.io.File;

/**
 * @author 35840
 *
 */
public class LineFollow implements Runnable {
	long startTime;
	long stopTime;
	// Used for calculating elapsed time during course

	float[] sample; // Sample from ColorSensor class, returns reflected light values in array

	int colorvalue; // Compared to threshold
	private int round = 0;
	DataExc DEobj;
	private final int threshold = 70;

	// create sensor and motor objects and set the used port
	ColorSensor color = new ColorSensor(SensorPort.S4);
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);

	// used for communication with other classes
	public LineFollow(DataExc DE) {
		DEobj = DE;

		// run();
	}


	
	
	@Override
	public void run() {
		
		//makes the correct settings to the color sensor
		color.setRedMode();
		color.setFloodLight(Color.RED);
		color.setFloodLight(true);

		//Button.ENTER.waitForPress();
		Delay.msDelay(5000); // waits for enter press, then waits for 5 seconds before starting
		long startTime = System.currentTimeMillis();
		
		while (true) {
			int colorvalue;
			colorvalue = (int) (color.getRed() * 1000);
//			System.out.println(colorvalue);

			//Sets the motors power based on if the colorvalue is more or less than the threshold
			if (DEobj.getCMD() == 1) {
				//getCMD is based on the distance of an obstacle. If less than 25cm it returns 1, else 0
				if (colorvalue < threshold) {
					motorA.setPower(10);
					motorD.setPower(60);
//					System.out.println("");
					//LCD.drawInt(0, 0, 7);
				} else {
					motorA.setPower(60);
					motorD.setPower(10);
//					System.out.println("");
				}

			} else {
//				Delay.ms
				// Makes an evasive manouver if an obstacle is found
				motorA.setPower(0);
				motorD.setPower(0);
				Delay.msDelay(500);
				motorA.setPower(60);
				motorD.setPower(-60);
				Delay.msDelay(150);
				motorA.setPower(100);
				motorD.setPower(100);
				Delay.msDelay(850);
				motorA.setPower(-60);
				motorD.setPower(60);
				Delay.msDelay(150);
				colorvalue = (int) (color.getRed() * 1000);
				if (colorvalue > threshold && round < 1) {
//					System.out.println("");
					while (colorvalue > threshold) {
						// Keeps going until it finds the correct value, to indicate it has returned to the course
						round++; // Used to know when the robot should stop, it should only do 1 round on the course
						motorA.setPower(30);
						motorD.setPower(50);
//				System.out.println("");
						colorvalue = (int) (color.getRed() * 1000);
					}
//				LCD.drawString("stop", 0, 7);
				} else if (round >= 1) {
					//When the round counter hits 1, the robot instead of evading, will do a sick maneuver and stop
					motorA.setPower(100);
					motorD.setPower(100);
					Delay.msDelay(300);
					motorA.setPower(100);
					motorD.setPower(-100);
					stopTime = System.currentTimeMillis();

					Delay.msDelay(500);
					motorA.stop();
					motorD.stop();
					// calculates the elapsed time in milliseconds, then multiplies by 1000 to get time in seconds
					long placeholder = (stopTime - startTime) / 1000;
					int timeElapsed = (int) placeholder; // converts time value in long to int, for printing on lcd
					System.out.println(" \n \n \n \n \n \n \n \n \n Time: " + timeElapsed + " seconds");
					Delay.msDelay(10000);
					System.exit(0);

				}
			}

		}
	}
}
