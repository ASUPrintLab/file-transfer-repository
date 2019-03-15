package application_v2;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/*
 * Author: Mitchell
 * Class: Logs - Creates a file and logs events and exceptions that occur in the program.
 */
public class Logs {
	private static File parentfolder;
	private static File childfolder;
	private static Logger events = Logger.getLogger("Events");;
	private static Logger exceptions = Logger.getLogger("Exceptions");
	private static FileHandler fh;
	private static FileHandler fh2;
	private static String year;
	private static String month;

	private static void checkLogs() {
		Logs.year = getYear();
		Logs.month = getMonth();
		Logs.parentfolder = new File("C:\\Users\\msrober3\\Desktop\\Test File Transfer\\Logs\\" + Logs.year);
		Logs.childfolder = new File("C:\\Users\\msrober3\\Desktop\\Test File Transfer\\Logs\\" + Logs.year + "\\" + Logs.month);
		//Check to see if folders exist before creating
		if (!Logs.parentfolder.exists() && !Logs.parentfolder.isDirectory()) {
			Logs.parentfolder.mkdir();
			Logs.childfolder.mkdir();
		}
		else if(!Logs.childfolder.exists() && !Logs.childfolder.isDirectory()) {
			Logs.childfolder.mkdir();
		}
		createEventLogs();
	}

	/*
	 * Create log files the program will write to
	 */
	private static void createEventLogs() {
		try {  
	        // This block configure the logger with handler and formatter  
			String transfers = "C:\\Users\\msrober3\\Desktop\\Test File Transfer\\Logs\\" + Logs.year + "\\" + Logs.month + "\\transfers.log";
			String exceptions = "C:\\Users\\msrober3\\Desktop\\Test File Transfer\\Logs\\exceptions.log";
			Logs.fh = new FileHandler(transfers); 
	        Logs.fh2 = new FileHandler(exceptions); //Add file handler that logs are written to
	        Logs.events.addHandler(fh);
	        Logs.exceptions.addHandler(fh2);
	        Logs.events.setLevel(Level.INFO); //Set the level
	        Logs.exceptions.setLevel(Level.ALL);
	        fh.setFormatter(new SimpleFormatter()); //Formatter for the logger
	        fh2.setFormatter(new SimpleFormatter());
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}

	private static String getMonth() {
		Date date = new Date();
		String strDateFormat = "MMMM";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	private static String getYear() {
		Date date = new Date();
		String strDateFormat = "yyyy";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate = dateFormat.format(date);
		return formattedDate;
	}
	/*
	 * Write the event (transfer info) to the log
	 */
	public static void writeToEvent(String event) {
		checkLogs();
		Logs.events.log(Level.INFO,event);
	}
	/*
	 * Write the exception to the log
	 */
	public static void writeToException(String event) {
		checkLogs();
		Logs.exceptions.log(Level.SEVERE,event);
	}
	
	public static void closeFiles() {
		if (Logs.fh != null && Logs.fh2 != null) {
			Logs.fh.close();
			Logs.fh2.close();	
		}
	}
	
	public static String getCurrentDate() {
		Date date = new Date();
		String strDateFormat = "EEE, MMM d, hh:mm a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate = dateFormat.format(date);
	    return formattedDate;
	}
}
