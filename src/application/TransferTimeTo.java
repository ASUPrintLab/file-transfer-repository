package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TransferTimeTo {
	private final SimpleStringProperty windowTime3;
	
	
	public TransferTimeTo(String windowTimes3) {
			this.windowTime3 = new SimpleStringProperty(windowTimes3);
		}

		public String getWindowTime3() {
			return windowTime3.get();
		}
		
		public void setWindowTime3(String newTime) {
			windowTime3.set(newTime);
		}
		public StringProperty windowTimeProperty() {
	        return windowTime3;
	    }
}
