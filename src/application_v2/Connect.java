package application_v2;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import controllers.Controller;
import controllers.TimeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class Connect {
	File source, target;
	
    public String run(String sourceFolder, String targetFolder, boolean retryConnection, boolean continueChecking) throws InvalidPathException, IOException, InterruptedException {
    	try {
    		source = new File(sourceFolder);
        	target = new File(targetFolder);
    	} catch (InvalidPathException e) {
    		Logs.writeToException(e.toString());
			throw e;
		}
    	
    	if(source.exists() && target.exists()) {
	    	try {
				Transfer.copy(source, target);
				Date date = new Date();
				String strDateFormat = "EEE, MMM d, hh:mm a";
			    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			    String formattedDate = dateFormat.format(date);
				String msg = "Files from " + sourceFolder + " were succefully sent to " + targetFolder + " at " + formattedDate;
				return msg;
			} catch (IOException e) {
				Logs.writeToException(e.toString());
				throw e;
			}
    	}
    	else {
    		if (retryConnection) {
    			try {
        		//Wait 5 minutes before returning to retry the connection in CheckConnectivity.java
        		TimeUnit.MINUTES.sleep(5);
    			} catch (InterruptedException e) {
    				Logs.writeToException(e.toString());
    				throw e;
    			}
    		} else {
        		return null;
    		}
    	}
    	
    	if (continueChecking) {
    		try {
	        	//Wait 1 minute before returning to continue checking for files in CheckConnectivity.java
				TimeUnit.MINUTES.sleep(1);
    		} catch (InterruptedException e) {
    			Logs.writeToException(e.toString());
    			throw e;
    		}
    	} 
    	else {
    		return null;
    	}
		return null;
    }
}
