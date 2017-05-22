package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailJobs {
	
	public Thread t;
	// Folder to be read from
	static File folder = new File("C:\\Users\\ASUprint\\Desktop\\Enfocus\\Switch\\ASU Print Online\\Output\\Hold Jobs");
	String ret = "";	
	
	public void run() {
		File[] list = folder.listFiles();
		FileWriter fw = null;  
		DateFormat df = new SimpleDateFormat("MM_dd_yy HH;mm");
		Date dateobj = new Date();
	
		if (folder.isDirectory() && list.length > 0){
			// Folder to put the txt file in
			File file = new File("C:\\Users\\ASUprint\\Desktop\\Enfocus\\Switch\\ASU Print Online\\Output\\Single email\\Jobs" + df.format(dateobj) + ".txt");
			
			try {
				// If new file was created put return this
				if (file.createNewFile()){
					ret =  "File Created (" + df.format(dateobj) + ")";
				}
					
				// If a new file was not created return this
				else {
					ret =  "File '" + file + "' already exists!";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				// Get absolute path 
				fw = new FileWriter(file.getAbsoluteFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedWriter writer = new BufferedWriter(fw);
			
			try {
				// lin seperator is equivalent to \r\n
				writer.write("Files:" + System.lineSeparator());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Counts how many files are in the folder.
			for (int i = 0; i < list.length; i++){
				// If it is a file, it writes to the txt file
				if (list[i].isFile()){
					try {
						writer.write("\t" + list[i].getName() + System.lineSeparator());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				// if it is a directory write this to txt
				else if (list[i].isDirectory()){
					try {
						writer.write("\t" + "This is a directory: " + list[i].getName() + System.lineSeparator());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			try {
				writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		// If target folder is empty aka no jobs are available
		else {
			ret = "Folder does not exist or is empty! (" + df.format(dateobj) + ")" ;
		}
	}
}
