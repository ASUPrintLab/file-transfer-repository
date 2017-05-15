package application;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.joda.time.LocalTime;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import application.CheckPresses;

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
		t.start();
	}

	@Override
	public void run() {
		
		try {
			//Victor Varela VV
			while(!suspended){
				System.out.println(LocalTime.now());
				
				for (int i = 0; i < data1.size(); i++){
					
					LocalTime timeFrom;
					LocalTime timeTo;
					
					String from = ttf.getCellData(i); 
					String to = ttt.getCellData(i);			 
					 
					 
					LocalTime now = LocalTime.now();
					 
					if (from.contains("PM")){
						Date dateFrom = parseFormat.parse(from);
						timeFrom = LocalTime.parse(displayFormat.format(dateFrom));
					}
					else {
						from = from.replace(" AM", "");
						timeFrom = LocalTime.parse(from);
					}
					
					if(to.contains("PM")){
						Date dateTo = parseFormat.parse(to);
						timeTo = LocalTime.parse(displayFormat.format(dateTo));
					}
					
					else{
						to = to.replace(" AM", "");
						timeTo = LocalTime.parse(to);
					}
					 
					 if((now.isAfter(timeFrom)) && (now.isBefore(timeTo))){
						 //System.out.println("ITS TIME!");
						 new CheckPresses().run(this.source, this.target, true, true);
					 }
					 
					 Thread.sleep(300);
				}
			 }
			
		} catch(InvalidPathException e1) {
			System.out.println("Source Path " + this.source + " may be invalid.");
			System.out.println("Target Path " + this.target + " may be invalid.");
			e1.printStackTrace();
		} catch(IOException e1) {
			System.out.println("Copying files failed.");
			e1.printStackTrace();
		} catch(InterruptedException e1) {
			System.out.println("Thread " +  threadName + " interrupted.");
			e1.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void suspend() {
		suspended = true;
	}
	
	synchronized void resume() {
		suspended = false;
		notify();
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		t.stop();
	}
}

