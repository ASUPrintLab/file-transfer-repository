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

import org.joda.time.LocalTime;

import application.TransferTimeFrom;
import application.TransferTimeTo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;


public class MainApp implements Runnable {

	EmailJobs email = new EmailJobs();
	
	TableViewController tvc;
	
	public volatile boolean running = true;
	static File folder = new File("C:\\Users\\vevarela\\Desktop\\New folder");
	DateFormat df = new SimpleDateFormat("MM_dd_yy");
	Date dateobj = new Date();
    String endTime = "16:15";

	TableColumn<NewEmailTransferTime, String> ttf;
	ObservableList<NewEmailTransferTime> data1;
	SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
	
	public MainApp(TableColumn<NewEmailTransferTime, String> emailTransferTime, ObservableList<NewEmailTransferTime> emailData){
		this.ttf = emailTransferTime;
		this.data1 = emailData;
	}
	
    @Override
    public void run(){
    	tvc.appendALine("Fuck Thomass");
		
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
		    	tvc.emailText.appendText("Its the weekend!");
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
		        		tvc.emailText.appendText(email.ret + "\n");
		        		tvc.emailText.selectPositionCaret(tvc.emailText.getText().length());
			        }
		        }
		           
		        try{
		        	Thread.sleep(55000);
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
							String content = tvc.emailText.getText();
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
						tvc.emailText.setText(null);
			    	}
			    }
			}
		}
	}
    
    public void terminate() {
        running = false;
    }
}