package application_v2;

import java.util.HashMap;
import java.util.Map;

public class PressManager {
	static Map<String, Press> pressManager = new HashMap<String, Press>();
	static Boolean recentAdded;
	static Press mostRecentPress;

	/*
	 * Add new press to collection
	 */
	public static void addPress(String name, String id) {
		//If hash table contains key lets make another
		if (pressManager!=null && pressManager.containsKey(id)) { //This could better optimized better for memory purposes. Remove recursion
			String newKey = getNewKey(); //Lets get a new key
			addPress(name,newKey); //Try again
		}
		else {
			Press newPress = new Press(name, null, null, id);
			mostRecentPress = newPress; //Used to find the most recent press added to map
			pressManager.put(id, newPress); 
		}
	}
	
	/*
	 * Remove press from map
	 */
	public static void removePress(String key) {
		pressManager.remove(key);
	}
	
	/*
	 * Generate new key value
	 */
	private static String getNewKey() {
		String keyValue = null;
		for (String key : pressManager.keySet()) {
		    keyValue = key;
		}
		int ketInt = Integer.parseInt(keyValue) + 1;
		keyValue = String.valueOf(ketInt);
		//Return new key
		return keyValue;
	}
	//Gets the most recently added press
	public static Press getRecentPress() {
		return mostRecentPress;
	}
	

	public static Press getPress(String key) {
		return (Press)pressManager.get(key);
	}
	
	public static void setRecentAdded(Boolean recentAdded) {
		PressManager.recentAdded = recentAdded;
	}
	
	public static Boolean getRecentAdded() {
		return recentAdded;
	}
	/*
	 * Update press in hash map
	 */
	public static void updatePress(Press press) {
		pressManager.put(press.getKey(), press);
	}

}
