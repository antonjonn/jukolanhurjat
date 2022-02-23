package app;
import lejos.ev3.*;
import app.ColorSensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.motor.*;
import lejos.robotics.Color;
import app.*;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
/**
 * @author 35840
 *
 */
public class LineFollow {
	
	float[]		sample;
	
	
	DataExc DEobj;
//	private EV3UltrasonicSensor us;
	private final int threshold = 45;
	 ColorSensor color = new ColorSensor(SensorPort.S4);
	 
	
     
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
	public LineFollow (DataExc DE) {
		DEobj = DE;
		
		 color.setRGBMode();
	        color.setFloodLight(Color.WHITE);
	        
	        Color rgb; 
	        rgb = color.getColor();
	        
				
//	    motorA.setPower(-50);
//	    motorD.setPower(-50);
//		ss = new EV3ColorSensor(SensorPort.S4);
	    
		
//		us = new EV3UltrasonicSensor(SensorPort.S1);
		run();
	}
	


	
	public void run() {
		//EV3ColorSensor color = new EV3ColorSensor(SensorPort.S4);
		//loputon 
//		color.setCurrentMode(Color.RED);
//		color.setFloodlight(true);
//		color.getRedMode();
		color.setRedMode();
	     color.setFloodLight(Color.RED);
	     color.setFloodLight(true);
		
		while(true) {
			int colorvalue;
			colorvalue = (int)(color.getRed()*100);
			System.out.println(colorvalue);
			
//			if (DEobj.getCMD() == 1) {
				//getRed();
//				int lightlevel = color.fetchSample(Sample, 0);
//				color.fetchSample(sample, 0);
//				int colorValue;
//				colorValue = (int)(sample[0]*100);
				
//				if(colorValue < threshold) {
//					motorA.setPower(0);
//					motorD.setPower(80);
//					//LCD.drawInt(, 0, 7);
//				} else {
//					motorA.setPower(80);
//					motorD.setPower(0);
//				}
//				//LCD.drawInt(ss.readValue(), 3, 9, 0);
//				LCD.asyncRefresh();
//			} else {
//				//Stop
//				motorA.setPower(0);
//				motorD.setPower(0);
//				LCD.drawString("stoppppppp", 0, 7);
//			}
				
		}
	}
} //}
