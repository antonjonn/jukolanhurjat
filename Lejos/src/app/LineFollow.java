package app;
import lejos.ev3.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.motor.*;
import lejos.robotics.Color;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
/**
 * @author 35840
 *
 */
public class LineFollow extends Thread {
	EV3ColorSensor	color;
	float[]		sample;

	DataExc DEobj;
	private EV3ColorSensor ss;
	private EV3UltrasonicSensor us;
	private final int colorPattern = 45;
	
	public LineFollow (DataExc DE) {
		DEobj = DE;
		UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	    UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
	    motorA.setPower(-50);
	    motorD.setPower(-50);
//		ss = new EV3ColorSensor(SensorPort.S4);
	    
		
		us = new EV3UltrasonicSensor(SensorPort.S1);
		
	}
	
	 public void EV3ColorSensor(Port port) {
	    EV3ColorSensor color = new EV3ColorSensor(SensorPort.S4);
		color.setCurrentMode(Color.RED);
		color.setFloodlight(true);
		color.getRedMode();
		double lightvalue = 
		ss.setFloodlight(true);	
	    }
		
	
//	
//	public float getRed() {
//		color.fetchSample(sample, 0);
//		return sample[0];
//	}
	public void run() {
		//loputon 
		
		while(true) {
			if (DEobj.getCMD() == 1) {
//				int lightlevel = color.fetchSample(Sample, 0);
				if(color.getRedMode() < colorPattern) {
					MotorPort.A.controlMotor(0, 3);
					MotorPort.D.controlMotor(80,1);
				} else {
					MotorPort.A.controlMotor(80, 1);
					MotorPort.D.controlMotor(0,3);
				}
				LCD.drawInt(ss.readValue(), 3, 9, 0);
				LCD.asyncRefresh();
			} else {
				//Stop
				MotorPort.A.controlMotor(0,3);
				MotorPort.D.controlMotor(0,3);
			}
				
		}
	}
}
