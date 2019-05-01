package application;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/** This Class creates a file and logs events and exceptions that occur in the program.
 * @author Mitchell Roberts
 * @since 1.0
 */
public class Logs {
    private static File logfolder;
    private static File parentfolder;
    private static File childfolder;
    private static Logger events = Logger.getLogger("Events");
    private static Logger exceptions = Logger.getLogger("Exceptions");
    private static FileHandler fh;
    private static FileHandler fh2;
    private static String year;
    private static String month;
    public static final int FILE_SIZE = 0;
    
    /**
     * This method checks to find the log file. 
     * If doesnt exist it will create one
     */
    private static void checkLogs() {
        Logs.year = getYear();
        Logs.month = getMonth();
        Logs.logfolder = new File("Logs");
        if (!Logs.logfolder.isDirectory()) {
            Logs.logfolder.mkdir();
        }
        Logs.parentfolder = new File("Logs\\" + Logs.year);
        Logs.childfolder = new File("Logs\\" + Logs.year + "\\" + Logs.month);
        //Check to see if folders exist before creating
        if (!Logs.parentfolder.exists() && !Logs.parentfolder.isDirectory()) {
            Logs.parentfolder.mkdir(); //Create directories
            Logs.childfolder.mkdir();
        } else if(!Logs.childfolder.exists() && !Logs.childfolder.isDirectory()) {
            Logs.childfolder.mkdir();
        }
        createEventLogs(); //Create files
    }

    /**
     * Create log files the program will write to.
     */
    private static void createEventLogs() {
        try {  
            // This block configure the logger with handler and formatter  
            String transfers = "Logs\\" + Logs.year + "\\" + Logs.month + "\\transfers.log";
            String exceptions = "Logs\\exceptions.log";
            Logs.fh = new FileHandler(transfers, FILE_SIZE, 1, true); 
            Logs.fh2 = new FileHandler(exceptions, FILE_SIZE, 1, true); //Add file handler that logs are written to
            Logs.events.addHandler(fh); // send log message to transfer.log
            Logs.exceptions.addHandler(fh2); // send log message to exceptions.log
            Logs.events.setLevel(Level.INFO); //Set the level
            Logs.exceptions.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter()); //Formatter for the logger
            fh2.setFormatter(new SimpleFormatter());
            fh.flush(); // forces any buffered bytes to be written to the appropriate output stream's destination
            fh2.flush();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
    
    /**
     * Gets the current month in the system.
     * @return formattedDate - formatted date (name of the month example: July)
     */
    private static String getMonth() {
        Date date = new Date();
        String strDateFormat = "MMMM";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
    
    /**
     * Gets the current year in the system.
     * @return formattedDate - formatted date (full year example: 2019)
     */
    private static String getYear() {
        Date date = new Date();
        String strDateFormat = "yyyy";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
    
    /**
     * Write the event (transfer info) to the log.
     * @param event - Events like a successful transfer
     */
    public static void writeToEvent(String event) {
        checkLogs(); //Check for the file if doesnt exist create a new one
        Logs.events.log(Level.INFO,event);
    }
    
    /**
     * Write the exception to the log.
     * @param event - Exception or error as a string that has occurred during runtime
     */
    public static void writeToException(String event) {
        checkLogs();
        Logs.exceptions.log(Level.SEVERE,event);
    }
    
    /**
     * Close Exceptions log and Events log files.
     */
    public static void closeFiles() {
        if (Logs.fh != null && Logs.fh2 != null) {
            Logs.fh.close();
            Logs.fh2.close();
            Logs.fh.flush();
            Logs.fh2.flush();
        }
    }
    
    /**
     * Gets the current date in the system.
     * @return formattedDate - formatted date example: Wed Oct 16 10:30 AM
     */
    public static String getCurrentDate() {
        Date date = new Date();
        String strDateFormat = "EEE, MMM d, hh:mm a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}
