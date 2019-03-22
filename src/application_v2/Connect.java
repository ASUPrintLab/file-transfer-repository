package application_v2;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/** This Class will check the locations of the transfer before moving (copying) files over to new location.
 * 	Will sleep for 5 minutes if path does not exist.
 * @author Mitchell Roberts & Victor Verela
 * @since 1.0
 */
public class Connect {
    File source, target;
    
    /**
     * This method will check the locations of the transfer before moving (copying) files over to new location.
     * @param sourceFolder - The location at which the file will need to be moved from
     * @param targetFolder - The location at which the file will need to be moved to
     * @param retryConnection - boolean that will return true if it needs to sleep the thread for 5 minutes
     * @param continueChecking - boolean that will return true if it needs to sleep the thread for 1 minute
     * @return null
     * @throws InvalidPathException
     * @throws IOException
     * @throws InterruptedException
     */
    public String run(String sourceFolder, String targetFolder, boolean retryConnection, boolean continueChecking) throws InvalidPathException, IOException, InterruptedException {
        try {
            source = new File(sourceFolder);
            target = new File(targetFolder);
        } catch (InvalidPathException e) {
            Logs.writeToException(e.toString());
            throw e;
        }
        //If the folder exists try and move the files
        if(source.exists() && target.exists()) {
            try {
                Transfer.copy(source, target);
                Date date = new Date();
                String strDateFormat = "EEE, MMM d, hh:mm a";
                DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
                String formattedDate = dateFormat.format(date);
                String msg = "Files from " + sourceFolder + " were succefully sent to " + targetFolder + " at " + formattedDate;
                return msg; //Return the message after transfer
            } catch (IOException e) {
                Logs.writeToException(e.toString());
                throw e;
            }
        }
        else {
            if (retryConnection) {
                try {
                    //Wait 5 minutes before returning to retry the connection in CheckConnectivity.java
                    TimeUnit.MINUTES.sleep(5);
                } catch (InterruptedException e) {
                    Logs.writeToException(e.toString());
                    throw e;
                }
            } else {
                return null;
            }
        }

        if (continueChecking) {
            try {
                //Wait 1 minute before returning to continue checking for files in CheckConnectivity.java
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                Logs.writeToException(e.toString());
                throw e;
            }
        }  else {
            return null;
        }
        return null;
    }
}
