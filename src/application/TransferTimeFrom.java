package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TransferTimeFrom {
	private final SimpleStringProperty TransferTimeFrom;
	private final SimpleStringProperty TransferTimeTo;
//	private final SimpleStringProperty windowTime3;
//	private final SimpleStringProperty emailTime;
	
	
	public TransferTimeFrom(String TransferTimeFrom, String TransferTimeTo) {
		this.TransferTimeFrom = new SimpleStringProperty(TransferTimeFrom);
//		this.windowTime2 = new SimpleStringProperty(windowTimes2);
//		this.windowTime3 = new SimpleStringProperty(windowTimes3);
		this.TransferTimeTo = new SimpleStringProperty(TransferTimeTo);
	}
	
//	public TransferTimeFrom() {
//		this.TransferTimeFrom = "";
//		
//	}

	public String getTransferTimeFrom() {
		return TransferTimeFrom.get();
	}
	
	public String getTransferTimeTo() {
		return TransferTimeTo.get();
	}
	
	public void setTransferTimeFrom(String newTime) {
		TransferTimeFrom.set(newTime);
	}
	
	public void setTransferTimeTo(String newTime) {
		TransferTimeTo.set(newTime);
	}
	
	public StringProperty TransferTimeFromProperty() {
        return TransferTimeFrom;
    }
	
	public StringProperty TransferTimeToProperty() {
        return TransferTimeTo;
    }
//	public String getEmailTime() {
//		return emailTime.get();
//	}
//	
//	public void setEmailTime(String newTime) {
//		emailTime.set(newTime);
//	}	
	
}
