package app;
import lejos.ev3.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.motor.*;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
public class LineFollow extends Thread {
	DataExc DEobj;
	private EV3IRSensor ss;
	private EV3UltrasonicSensor us;
	private final int colorPattern = 45;
	
	public LineFollow (DataExc DE) {
		DEobj = DE;
		
		ss = new EV3IRSensor(SensorPort.S4);
		us = new EV3UltrasonicSensor(SensorPort.S1);
		//ss.setFloodlight(true);
	}
	public void run() {
		//loputon 
		while(true) {
			if (DEobj.getCMD() == 1) {
				if(ss.readValue()< colorPattern) {
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
