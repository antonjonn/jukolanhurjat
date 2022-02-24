package app;

public class DataExc implements Runnable{

	private boolean obstacleDetected = false;
	
	private int CMD = 1;
	
	public DataExc() {
		
	}
	public void setObstacleDetected (boolean status) {
		obstacleDetected = status;
	}
	public boolean getObstacleDetected() {
		return obstacleDetected;
	}
	public void setCMD(int command) {
		CMD = command;
	}
	public int getCMD() {
		return CMD;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
