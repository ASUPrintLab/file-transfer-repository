package application;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalTime;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class CheckConnectivity implements Runnable {
	
	public Thread t;
	private String threadName;
	private boolean suspended = false;
	public String source, target;
	//TableViewController tvc;
	TableColumn<TransferTimeFrom, String> ttf;
	TableColumn<TransferTimeTo, String> ttt;
	ObservableList<TransferTimeFrom> data1;
	SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
	
	public CheckConnectivity(String name, String source, String target, TableColumn<TransferTimeFrom, String> TransferTimeFrom1, TableColumn<TransferTimeTo, String> TransferTimeTo1, ObservableList<TransferTimeFrom> data) {
		threadName = name;
		this.source = source;
		this.target = target;
		this.ttf = TransferTimeFrom1;
		this.ttt = TransferTimeTo1;
		this.data1 = data;
	}
	
	public void start() {
		t = new Thread(this);
		//Closes thread when program is closed.
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		
		if (source.isEmpty() || target.isEmpty() || !(data1.size() > 0)){
			t.stop();
		}
		
		else {
			try {
				//Victor Varela VV & Luis Quintanilla LQ
				while(!suspended){
					
					for (int i = 0; i < data1.size(); i++){
						
						LocalTime timeFrom;
						LocalTime timeTo;
						
						String from = ttf.getCellData(i); 
						String to = ttt.getCellData(i);			 
						 
						 
						LocalTime now = LocalTime.now();
						 
						// If the time is PM convert to 24 hour.
						if (from.contains("PM")){
							Date dateFrom = parseFormat.parse(from);
							timeFrom = LocalTime.parse(displayFormat.format(dateFrom));
						}
						
						// If the time has AM remove it with the extra space
						else {
							from = from.replace(" AM", "");
							timeFrom = LocalTime.parse(from);
						}
						
						// If the time is PM convert to 24 hour.
						if(to.contains("PM")){
							Date dateTo = parseFormat.parse(to);
							timeTo = LocalTime.parse(displayFormat.format(dateTo));
						}
						
						// If the time has AM remove it with the extra space
						else{
							to = to.replace(" AM", "");
							timeTo = LocalTime.parse(to);
						}
						 
						// call CheckPresses class if actual time is between From and to
						if((now.isAfter(timeFrom)) && (now.isBefore(timeTo))){
							 //System.out.println("ITS TIME!");
							 new CheckPresses().run(source, target, true, true);
						 }
						 
						Thread.sleep(300);
					}
				 }
				// if source or target is invalid catch it
			} catch(InvalidPathException e1) {
				System.out.println("Source Path " + this.source + " may be invalid.");
				System.out.println("Target Path " + this.target + " may be invalid.");
				e1.printStackTrace();
				
				// if copying files doesnt workout
			} catch(IOException e1) {
				System.out.println("Copying files failed.");
				e1.printStackTrace();
				
				//If the thread gets interupted
			} catch(InterruptedException e1) {
				System.out.println("Thread " +  threadName + " interrupted.");
				e1.printStackTrace();
				
				// If parseing throws an error
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Used for pause button, currently not implemented
	public void suspend() {
		suspended = true;
	}
	
	//Used for pause button, currently not implemented 
	synchronized void resume() {
		suspended = false;
		// notifies threads that it needs to accept something
		notify();
	}

	//Stops thread
	@SuppressWarnings("deprecation")
	public void stop() {
		t.stop();
	}
}

