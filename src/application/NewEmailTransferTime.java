package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewEmailTransferTime {
	private final SimpleStringProperty NewEmailTransferTime;
	
	public NewEmailTransferTime(String NewEmailTransferTime) {
		this.NewEmailTransferTime = new SimpleStringProperty(NewEmailTransferTime);;
	}

	public String getNewEmailTransferTime() {
		return NewEmailTransferTime.get();
	}
	
	public void setNewEmailTransferTime(String newTime) {
		NewEmailTransferTime.set(newTime);
	}
	
	public StringProperty NewEmailTransferTimeProperty() {
        return NewEmailTransferTime;
    }
	
}
