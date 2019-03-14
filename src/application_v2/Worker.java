package application_v2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.LocalTime;

import application.CheckPresses;
import controllers.Controller;
import javafx.concurrent.Task;

/*
 * Author: Mitchell
 */
public class Worker extends Task<Press> {
	 
    private Press press;
    private int interval = 2000;
    SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
 
    public Worker(Press press) {
        this.press = press;
    }
  

	@Override
	protected Press call() throws Exception {
		while (!isCancelled()) { //keep running until task is canceled
            try {       	
                Thread.sleep(interval); 
            } catch (InterruptedException interrupted) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
                    break;
                }
            }
            
            ArrayList<TransferTime> times = this.press.getTransferTimes(); //Get times
            for (int i = 0; i < times.size(); i++) { //Loop through times 
				
				Date date = new Date();
			    String strDateFormat = "hh:mm a";
			    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			    String formattedDate= dateFormat.format(date);
			    
			    SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
			    SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm"); 
			    LocalTime time24 = LocalTime.parse(outFormat.format(inFormat.parse(formattedDate)));
			    
			    
			    LocalTime from = LocalTime.parse(outFormat.format(inFormat.parse(times.get(i).getStartTime()))); 
			    LocalTime to = LocalTime.parse(outFormat.format(inFormat.parse(times.get(i).getStopTime())));
				
				// call CheckPresses class if actual time is between From and to
				if((time24.isAfter(from)) && (time24.isBefore(to))){
					 if (!this.press.locationsEmpty()) { //Check is locations exist
						 ArrayList<Locations> locations = this.press.getLocations(); //Get times
						 for (Locations location : locations) {
							 updateMessage(new Connect().run(location.getFromLocation(), location.getToLocation(), true, true)); //setup transfer and update gui log
						 }
					}
				 }
             }
			
        } 
		return null;
	} 
}

