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
	static File folder = new File("C:\\Users\\vevarela\\Desktop\\New folder");
	String ret = "";	
	
	public void run() {
		File[] list = folder.listFiles();
		FileWriter fw = null;  
		DateFormat df = new SimpleDateFormat("MM_dd_yy HH;mm");
		Date dateobj = new Date();
	
		if (folder.isDirectory() && list.length > 0){
			File file = new File("C:\\Users\\vevarela\\Desktop\\Test\\Jobs" + df.format(dateobj) + ".txt");
			
			try {
				if (file.createNewFile()){
					ret =  "File Created (" + df.format(dateobj) + ")";
				}
					
				else {
					ret =  "File '" + file + "' already exists!";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				fw = new FileWriter(file.getAbsoluteFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedWriter writer = new BufferedWriter(fw);
			
			try {
				writer.write("Files:" + System.lineSeparator());
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < list.length; i++){
				if (list[i].isFile()){
					try {
						writer.write("\t" + list[i].getName() + System.lineSeparator());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
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
		
		else {
			ret = "Folder does not exist or is empty!";
		}
	}
}
