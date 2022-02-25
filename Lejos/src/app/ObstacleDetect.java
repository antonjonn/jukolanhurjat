package app;
import lejos.ev3.*;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class ObstacleDetect implements Runnable {
	private DataExc DEobj;
	int distanceValue = 0;
	 private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
	private final int securityDistance = 20;
	final SampleProvider sp = us.getDistanceMode();
	public ObstacleDetect(DataExc DE) {
		DEobj = DE;
		
		
		
		//run();
	}
	@Override
	public void run() {
		while (true) {
			float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            distanceValue = (int)(sample[0]*100);
			if(distanceValue < securityDistance) {
				DEobj.setCMD(0) ; 
				Sound.twoBeeps();
			} else {
				DEobj.setCMD(1);
				LCD.drawString("safe", 0, 1);
				LCD.refresh();
			}
//			LCD.drawString("Object detectedd", 0, 1);
//			LCD.refresh();
//			Sound.twoBeeps();
//			Sound.twoBeeps();
		}
	}
}
