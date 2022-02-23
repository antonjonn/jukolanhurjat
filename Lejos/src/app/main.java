package app;
import lejos.hardware.*;
import lejos.hardware.lcd.LCD;

public class main {
	private static DataExc DE;
	private static LineFollow LFobj;
	private static ObstacleDetect ODobj;
	
	public static void main(String[] args) {
		DE = new DataExc();
		//ODobj = new ObstacleDetect(DE);
		LFobj = new LineFollow(DE);
		
		while(!Button.ESCAPE.isDown()) {
			
		}
		LCD.drawString("finished", 0, 7);
		LCD.refresh();
		System.exit(0);
		
	}
	
}
