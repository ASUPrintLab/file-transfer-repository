package application_v2;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/*
 * Author: Mitchell Roberts
 */
public class TransferTime {

	private String startTime;
	private String stopTime;
	private CheckBox edit;

	public TransferTime(String startTime, String stopTime) {
		super();
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.edit = new CheckBox();
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
	
	public CheckBox getEdit() {
		return edit;
	}

	public void setEdit(CheckBox edit) {
		this.edit = edit;
	}
	
	@Override
	public String toString() {
		return "TransferTime [startTime=" + startTime + ", stopTime=" + stopTime + "]";
	}
}

