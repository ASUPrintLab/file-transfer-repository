package application_v2;

/** Saved hashmap of PressManager as JSON 
 * @author Mitchell Roberts
 * @since 1.0
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SaveData {
	public static void saveToJSON() {
		Gson gson = new Gson();
		//Get all JSON array
		JsonArray jsonTree = mapToJson(PressManager.getAllPresses());
		
		String jsonData = gson.toJson(jsonTree); // to json string
		try (FileWriter file = new FileWriter("saved-data.json")) {
			 //Lets write it to a file
            file.write(jsonData);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Method of converting arraylist of presses to JSON
	 * @param pressList - Arraylist of presses
	 * @return converted JSON Array
	 */
	private static JsonArray mapToJson(ArrayList<Press> pressList) {
	  Gson gson = new Gson();
	  JsonArray pressJSONArray = new JsonArray();
	  
	  for (Press press : pressList) {
		  JsonObject pressObj = new JsonObject();
		  JsonElement name = gson.fromJson (press.getName(), JsonElement.class);
		  JsonArray locationList = getLocationsAsJSON(press.getLocations());
		  JsonArray timeList = geTimesAsJSON(press.getTransferTimes());
		  pressObj.add("Name", name);
		  pressObj.add("Locations", locationList);
		  pressObj.add("Times", timeList);
		  pressJSONArray.add(pressObj);
	  }


	  return pressJSONArray;
	}
	/**
	 * Converts an arraylist of tranfer times to JSON array
	 * @param timeList - arraylist of transfer times
	 * @return JSON array of transfer times
	 */
	private static JsonArray geTimesAsJSON(ArrayList<TransferTime> timeList) {
		JsonArray timesJSONArray = new JsonArray();
		
		for (TransferTime time : timeList) {
			JsonObject timeObj = new JsonObject();
			timeObj.addProperty("Start", time.getStartTime());
			timeObj.addProperty("Stop", time.getStopTime());
			timesJSONArray.add(timeObj);
		}
		return timesJSONArray;
	}
	/**
	 * Converts an arraylist of location times to JSON array
	 * @param locList - arraylist of location times
	 * @return JSON array of location times
	 */
	private static JsonArray getLocationsAsJSON(ArrayList<Locations> locList) {
		JsonArray locationsJSONArray = new JsonArray();
		
		for (Locations loc : locList) {
			JsonObject locObj = new JsonObject();
			locObj.addProperty("To", loc.getToLocation());
			locObj.addProperty("From", loc.getFromLocation());
			locationsJSONArray.add(locObj);
		}
		return locationsJSONArray;
	}
}
