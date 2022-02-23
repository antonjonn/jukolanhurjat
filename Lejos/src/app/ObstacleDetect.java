package app;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class ObstacleDetect extends Thread {
	private DataExc DEobj;
	
	private EV3UltrasonicSensor us;
	private final int securityDistance = 25;
	
	public ObstacleDetect(DataExc DE) {
		DEobj = DE;
		us = new EV3UltrasonicSensor(SensorPort.S1);
	}
	
//	public void run() {
//		while (true) {
//			if(us.getDistance() > securityDistance) {
//				DEobj.setCMD(1) ; 
//			} else {
//				DEobj.setCMD(0);
//			}
//			LCD.drawString("Object detectedd", 0, 1);
//			LCD.refresh();
//			Sound.twoBeeps();
//			Sound.twoBeeps();
//		}
//	}
}
