package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Press2 {
	private final SimpleStringProperty windowTime2;
	
	
	public Press2(String windowTimes2) {
			this.windowTime2 = new SimpleStringProperty(windowTimes2);
		}

		public String getWindowTime2() {
			return windowTime2.get();
		}
		
		public void setWindowTime2(String newTime) {
			windowTime2.set(newTime);
		}
		public StringProperty windowTimeProperty() {
	        return windowTime2;
	    }
}
