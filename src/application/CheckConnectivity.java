package application;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.time.format.DateTimeFormatter;

import org.joda.time.LocalTime;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import application.CheckPresses;

public class CheckConnectivity implements Runnable {
	
	public Thread t;
	private String threadName;
	private boolean suspended = false;
	public String source, target;
	TableViewController tvc;
	TableColumn<TransferTimeFrom, String> ttf;
	TableColumn<TransferTimeTo, String> ttt;
	ObservableList<TransferTimeFrom> data1;
	
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
			
			while(!suspended){
				
				for (int i = 0; i < data1.size(); i++){
					 String from = ttf.getCellData(i);
					 from = from.replace(" AM", "");
					 from = from.replace(" PM", "");
					 
					 String to = ttt.getCellData(i);
					 to = to.replace(" AM", "");
					 to = to.replace(" PM", "");
					 
					 LocalTime timeFrom = LocalTime.parse(from);
					 LocalTime timeTo = LocalTime.parse(to);
					 LocalTime now = LocalTime.now();
					 
					 
					 
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

