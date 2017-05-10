package application;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import org.joda.time.LocalTime;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class CheckConnectivity implements Runnable {
	
	public Thread t;
	private String threadName;
	private boolean suspended = false;
	public String source, target;
	TableViewController tvc;
	TableColumn<TransferTimeFrom, String> ttf;
	TableColumn<TransferTimeTo, String> ttt;
	ObservableList<TransferTimeFrom> data1;
	/*LocalTime morning1;
	LocalTime morning2;
	LocalTime afternoon1;
	LocalTime afternoon2;
	LocalTime afternoon3;
	LocalTime afternoon4;
	LocalTime evening1;
	LocalTime evening2;*/
	
	public CheckConnectivity(String name, String source, String target, TableColumn<TransferTimeFrom, String> TransferTimeFrom1, TableColumn<TransferTimeTo, String> TransferTimeTo1, ObservableList<TransferTimeFrom> data) {
		threadName = name;
		this.source = source;
		this.target = target;
		this.ttf = TransferTimeFrom1;
		this.ttt = TransferTimeTo1;
		this.data1 = data;
		/*this.morning1 = new LocalTime(morning1);
		this.morning2 = new LocalTime (morning2);
		this.afternoon1 = new LocalTime (afternoon1);
		this.afternoon2 = new LocalTime (afternoon2);
		this.afternoon3 = new LocalTime (afternoon3);
		this.afternoon4 = new LocalTime (afternoon4);
		this.evening1 = new LocalTime (evening1);
		this.evening2 = new LocalTime (evening2);*/
	}
	
	public void start() {
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		
		try {
			//new CheckPresses().run(this.source, this.target, true, true);
			System.out.println("Hello World");
			
			for (int i = 0; i < data1.size(); i++){
				 System.out.println(ttf.getCellData(i));
				 System.out.println(ttt.getCellData(i));
			 }
			
		} catch(InvalidPathException e1) {
			System.out.println("Source Path " + this.source + " may be invalid.");
			System.out.println("Target Path " + this.target + " may be invalid.");
			e1.printStackTrace();
		}/* catch(IOException e1) {
			System.out.println("Copying files failed.");
			e1.printStackTrace();
		} catch(InterruptedException e1) {
			System.out.println("Thread " +  threadName + " interrupted.");
			e1.printStackTrace();
		}*/
	}

	/*public void suspend() {
		suspended = true;
	}
	
	synchronized void resume() {
		suspended = false;
		notify();
	}*/

	@SuppressWarnings("deprecation")
	public void stop() {
		t.stop();
	}
}

