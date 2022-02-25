package app;
import lejos.ev3.*;
import app.ColorSensor;
import lejos.hardware.Button;
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
/**
 * @author 35840
 *
 */
public class LineFollow implements Runnable{
	long startTime;
	long stopTime;
	float[]		sample;
	int colorvalue;
	private int round = 0 ; 
	DataExc DEobj;
//	private EV3UltrasonicSensor us;
	private final int threshold = 70;
	 ColorSensor color = new ColorSensor(SensorPort.S4);
	 
	
     
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
	public LineFollow (DataExc DE) {
		DEobj = DE;
		
		 
	    
		

		//run();
	}
	

	@Override
	public void run() {
		//loputon 

		color.setRedMode();
	     color.setFloodLight(Color.RED);
	     color.setFloodLight(true);
	     
	     
	     Button.ENTER.waitForPress();
	     Delay.msDelay(1000);
	     long startTime = System.currentTimeMillis();
	     
	     
		while(true) {
			int colorvalue;
			colorvalue = (int)(color.getRed()*1000);
//			System.out.println(colorvalue);
			
			if (DEobj.getCMD() == 1) {
				

				
				if(colorvalue < threshold) {
					motorA.setPower(10);
					motorD.setPower(60);
//					System.out.println("jotain paskaa");
					LCD.drawInt(0, 0, 7);
				} else {
					motorA.setPower(60);
					motorD.setPower(10);
//					System.out.println("TEHO APPELSIINI PURKKI ");
				}
//				//LCD.drawInt(ss.readValue(), 3, 9, 0);
//				LCD.asyncRefresh();
			} else {
//				Delay.ms
				//Stop
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
				colorvalue = (int)(color.getRed()*1000);
				if (colorvalue > threshold && round < 1) {
//					System.out.println("");
				while(colorvalue > threshold) {
					
				round ++;	
				motorA.setPower(30);
				motorD.setPower(50);
//				System.out.println("Animesäilyke 69");
				colorvalue = (int)(color.getRed()*1000);
				}
//				LCD.drawString("stoppppppp", 0, 7);
			} else if (round >= 1){
				motorA.setPower(100);
				motorD.setPower(100);
				Delay.msDelay(300);
				motorA.setPower(100);
				motorD.setPower(-100);
				stopTime = System.currentTimeMillis();
				
				Delay.msDelay(500);
				motorA.stop();
				motorD.stop();
				long placeholder = (stopTime - startTime) / 1000;
				int timeElapsed = (int)placeholder;
				System.out.println(" \n \n \n \n \n \n \n \n \n Time: " + timeElapsed + " seconds");
				Delay.msDelay(10000);
				System.exit(0);
				
			}
				}
				
		}
	}
} 
