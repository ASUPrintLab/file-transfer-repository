package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TransferTimeFrom {
	private final SimpleStringProperty TransferTimeFrom;
	private final SimpleStringProperty TransferTimeTo;
	
	public TransferTimeFrom(String TransferTimeFrom, String TransferTimeTo) {
		this.TransferTimeFrom = new SimpleStringProperty(TransferTimeFrom);
		this.TransferTimeTo = new SimpleStringProperty(TransferTimeTo);
	}

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

	
}
