package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
	// Target Folder
	static File folder = new File("C:\\Users\\ASUprint\\Desktop\\Enfocus\\Switch\\ASU Print Online\\Output\\Hold Jobs");
	DateFormat df = new SimpleDateFormat("MM_dd_yy");
	Date dateobj = new Date();
	// Hardcoded for end of the month archive
    String endTime = "18:15";

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
		// Used to kill all threads when window closes.
		t.setDaemon(true);
		t.start();
	}
	
    @Override
    public void run(){
    	if (!(data1.size() > 0)){
    		System.out.println("Done");
    	}
    	
    	else {
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
			       
			    // If it is the weekend do nothing but tell the user
			    // and let the thread sleep for 3 hours
			    if((day == week) || (day == end)){
			    	javafx.application.Platform.runLater( () -> textArea.appendText("Its the weekend!" + "\n"));
			        try{
			        	Thread.sleep(60000 * 60 * 3);
				    } catch(InterruptedException e) {
				    	e.printStackTrace();
				       }       	
			    }
			    
			    // Otherwise,  
			    else{
			    	// Count the number of items in the table data
			        for (int i = 0; i < data1.size(); i++){
			        	//Do this for each one...
			        	String eTime = ttf.getCellData(i);
			        	// If it is a PM time convert to 24 hour time
			        	if (eTime.contains("PM")){
			        		try {
								Date aTime = parseFormat.parse(eTime);
								eTime = displayFormat.format(aTime);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			        	}
			        	// If it id an AM time remove the space and AM
			        	else {
			        		eTime = eTime.replaceAll(" AM", "");
						}
			        	// If that time is equal to the user input time
			        	// call emailJobs class
			        	if (time.equals(eTime)){
			        		email.run();
			        		// runLater is used because we need to give javafx time to put the text into the textArea
			        		javafx.application.Platform.runLater( () -> textArea.appendText(email.ret + "\n"));
			        		javafx.application.Platform.runLater( () -> textArea.selectPositionCaret(textArea.getText().length()));
			        		try {
			        			// Thread sleeps for 59 sec otherwise you will get duplicate text files.
								Thread.sleep(59000);
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
			       
			    // If it is the last day of the month archive text in textArea, and clear text area
				if(dayOfMonth == a.getActualMaximum(Calendar.DAY_OF_MONTH) && time.equals(endTime)){
				    if (folder.isDirectory() && list.length > 0){
				    	// Creates a txt file
				    	String file = "C:\\Users\\ASUprint\\Desktop\\Enfocus\\Switch\\ASU Print Online\\Output\\OLD Sent\\EmailArchive_" + df.format(dateobj) + ".txt";
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
								// Saves all text from textArea to content variable
								String content = textArea.getText();
								content = content.replaceAll("(?!\\r)\\n", "\r\n"); //separates all info onto a new line 
								// Writes all content to txt file
								writer.write(content);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							  }
								
							try{
								// Closes bufferedWriter
								if (writer != null){
									writer.close();
								}
								// Closes fileWriter.
								if (fw != null){
									fw.close();
								}
							} catch (IOException e2) {
								e2.printStackTrace();
							  }		
							// Clears textArea
							javafx.application.Platform.runLater( () -> textArea.setText(null));
				    	}
				    }
				}
			}
    	}
	}
    // Stops threads
    @SuppressWarnings("deprecation")
	public void stop() {
		t.stop();
	}
}