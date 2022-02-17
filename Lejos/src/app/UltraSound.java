package app;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class UltraSound {

    private static EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S1);

    public static void main(String[] args) {

        final SampleProvider sp = us1.getDistanceMode();
        int distanceValue = 0;
        
        while(true) {
            float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            distanceValue = (int)(sample[0]*100);

            System.out.println("Distance: " + distanceValue);
            Delay.msDelay(500);
            
            if(Button.getButtons()!=0) {
                break;
            }
            
        }

    }

}