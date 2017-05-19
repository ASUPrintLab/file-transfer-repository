package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;


public class EmailHead implements Runnable {

	EmailJobs email = new EmailJobs();
	public Thread t;
	
	private volatile boolean running = true;
	static File folder = new File("C:\\Users\\vevarela\\Desktop\\New folder");
	DateFormat df = new SimpleDateFormat("MM_dd_yy");
	Date dateobj = new Date();
    String endTime = "16:15";

	TableColumn<NewEmailTransferTime, String> ttf;
	ObservableList<NewEmailTransferTime> data1;
	TextArea textArea;
	SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
	
	public EmailHead(TableColumn<NewEmailTransferTime, String> emailTransferTime, ObservableList<NewEmailTransferTime> emailData, TextArea textArea){
		this.ttf = emailTransferTime;
		this.data1 = emailData;
		this.textArea = textArea;
	}
	
	public void start() {
		t = new Thread(this);
		t.start();
	}
	
    @Override
    public void run(){
    	
		while(running){
			Calendar a = Calendar.getInstance();
			File[] list = folder.listFiles();
			
		    String min = Integer.toString(a.get(Calendar.MINUTE));
		    String hour = Integer.toString(a.get(Calendar.HOUR_OF_DAY));
		    String time = hour + ":" + min;
		        
		    int day = a.get(Calendar.DAY_OF_WEEK);
		    int dayOfMonth = a.get(Calendar.DAY_OF_MONTH);
		    int week = Calendar.SATURDAY;
		    int end = Calendar.SUNDAY;
		        
		    if((day == week) || (day == end)){
		    	javafx.application.Platform.runLater( () -> textArea.appendText("Its the weekend!"));
		        try{
		        	Thread.sleep(60000 * 60);
			    } catch(InterruptedException e) {
			    	e.printStackTrace();
			       }       	
		    }
		        
		    else{
		        for (int i = 0; i < data1.size(); i++){
		        		
		        	String eTime = ttf.getCellData(i);
		        		
		        	if (eTime.contains("PM")){
		        		eTime = eTime.replaceAll(" PM", "");
		        	}
		        		
		        	else {
		        		eTime = eTime.replaceAll(" AM", "");
					}
		        		
		        	if (time.equals(eTime)){
		        		email.run();
		        		javafx.application.Platform.runLater( () -> textArea.appendText(email.ret + "\n"));
		        		javafx.application.Platform.runLater( () -> textArea.selectPositionCaret(textArea.getText().length()));
		        		try {
							Thread.sleep(60000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
		        }
		           
		        try{
		        	Thread.sleep(2000);
		        } catch(InterruptedException e) {
		           	e.printStackTrace();
		        }
		    }
		       
			if(dayOfMonth == a.getActualMaximum(Calendar.DAY_OF_MONTH) && time.equals(endTime)){
			    if (folder.isDirectory() && list.length > 0){
			    	String file = "C:\\Users\\vevarela\\Desktop\\Test\\EmailArchive_" + df.format(dateobj) + ".txt";
			    	File fPath = new File(file);
			    		
			    	if (!fPath.exists()){
			    			
				    	FileWriter fw = null;
						try {
							fw = new FileWriter(file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						BufferedWriter writer = null;
						try {
							writer = new BufferedWriter(fw);
							String content = textArea.getText();
							content = content.replaceAll("(?!\\r)\\n", "\r\n"); //separates all info onto a new line 
							writer.write(content);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						  }
							
						try{
							if (writer != null){
								writer.close();
							}
							if (fw != null){
								fw.close();
							}
						} catch (IOException e2) {
							e2.printStackTrace();
						  }							
						javafx.application.Platform.runLater( () -> textArea.setText(null));
			    	}
			    }
			}
		}
	}
    
    @SuppressWarnings("deprecation")
	public void stop() {
		t.stop();
	}
}