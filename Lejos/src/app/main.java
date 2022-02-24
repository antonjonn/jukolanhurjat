package app;
import lejos.hardware.*;
import lejos.hardware.lcd.LCD;

public class main {
	private static DataExc DE;
	private static LineFollow LFobj;
	private static ObstacleDetect ODobj;
	
	public static void main(String[] args) {
		DataExc DE = new DataExc();
		LineFollow LFobj = new LineFollow(DE);
		ObstacleDetect ODobj = new ObstacleDetect(DE);
		
		Thread thread_a = new Thread(DE);
		Thread thread_b = new Thread(LFobj);
		Thread thread_c = new Thread(ODobj);
		
		thread_a.start();
		thread_b.start();
		thread_c.start();
		
		while(!Button.ESCAPE.isDown()) {
		
			
		}
		LCD.drawString("finished", 0, 7);
		LCD.refresh();
		System.exit(0);
		
	}
	
}
