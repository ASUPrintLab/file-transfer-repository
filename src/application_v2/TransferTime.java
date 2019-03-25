package application_v2;

import javafx.scene.control.CheckBox;

/** This Class is the TransferTime object that contains information like start and stop time 12hr format.
 * @author Mitchell Roberts
 * @since 1.0
 */
public class TransferTime {

    private String startTime;
    private String stopTime;
    private CheckBox edit; //Check box used for GUI table
    private String clear;
    
    /**
     * Constructor.
     * @param startTime - Start time that transfer time will start looking for files based on location
     * @param stopTime - Stop time 
     */
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

    public String getClear() {
        return clear;
    }

}

