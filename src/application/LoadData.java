package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** Loads JSON data from saved file.
 * @author Mitchell Roberts
 * @since 1.0
 */
public class LoadData {
    
    /**
     * This method checks to see if a saved file exist 
     * if the file does exists than parse the JSON into.
     * Arraylist of type Press
     * @return ArrayList of Presses
     */
    public static ArrayList<Press> loadJSON() {
        JSONParser parser = new JSONParser();
        File tmpDir = new File("saved-data.json");
        boolean exists = tmpDir.exists();
        ArrayList<Press> presslist = new ArrayList<Press>();
        if (exists) { //check if saved file exists
            try {
                JSONArray pressList = (JSONArray) parser.parse(new FileReader("saved-data.json"));
                for (Iterator iterator = pressList.iterator(); iterator.hasNext();) {
                    JSONObject temp = (JSONObject) iterator.next();
                    String name = (String) temp.get("Name");
                    String id = String.valueOf(temp.get("Id")); //Get id
                    ArrayList<Locations> locations = getLocationsFromJSON((JSONArray) temp.get("Locations"));
                    ArrayList<TransferTime> times = getTimesFromJSON((JSONArray) temp.get("Times"));
                    Press press = new Press(name,times,locations,Integer.parseInt(id));
                    presslist.add(press);
                }
            } catch (IOException | ParseException e) {
                Logs.writeToException(e.toString());
                e.printStackTrace();
            }
        }
        return presslist;
    }
    
    /**
     * Parse jsonArray to ArrayList of Transfer Times.
     * @param jsonArray - JSON array of transfer times
     * @return ArrayList of type transfer times
     */
    private static ArrayList<TransferTime> getTimesFromJSON(JSONArray jsonArray) {
        ArrayList<TransferTime> timeList = new ArrayList<TransferTime>();
        if (!jsonArray.isEmpty()) {
            for (Object object : jsonArray) {
                JSONObject temp = (JSONObject) object;
                TransferTime time = new TransferTime((String) temp.get("Start"),(String) temp.get("Stop"));
                timeList.add(time);
            }
        }
        return timeList;
    }
    
    /**
     * Parse jsonArray to ArrayList of locations.
     * @param jsonArray - JSON array of locations
     * @return ArrayList of type locations
     */
    private static ArrayList<Locations> getLocationsFromJSON(JSONArray jsonArray) {
        ArrayList<Locations> locList = new ArrayList<Locations>();
        if (!jsonArray.isEmpty()) {
            for (Object object : jsonArray) {
                JSONObject temp = (JSONObject) object;
                Locations loc = new Locations((String) temp.get("Name"),(String) temp.get("From"),(String) temp.get("To"));
                locList.add(loc);
            }
        }
        return locList;
    }
}
