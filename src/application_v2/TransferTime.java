package application_v2;

/*
 * Author: Mitchell Roberts
 */
public class TransferTime {
	private String startTime;
	private String stopTime;
	
	public TransferTime(String startTime, String stopTime) {
		super();
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
}

