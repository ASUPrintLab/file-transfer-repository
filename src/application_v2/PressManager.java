package application_v2;

import java.util.ArrayList;
import java.util.HashMap;
/** This Class serves as a manager (hashmap) for the presses added 
 * @author Mitchell Roberts
 * @since 1.0
*/
public class PressManager {
	static HashMap<Integer, Press> pressManager = new HashMap<Integer, Press>(); //Collection of presses
	static Boolean recentAdded;
	static Press mostRecentPress; //Used to identify the most recent press added to collection

	/**
	 * Add new press to collection
	 * @param name - name of press
	 * @param id - key used for the hashmap
	 */
	public static void addPress(String name, int id) {
		//If hash table contains key lets make another
		if (pressManager!=null && pressManager.containsKey(id)) { //This could better optimized better for memory purposes. Remove recursion
			int newKey = getNewKey(id); //Lets get a new key
			Press newPress = new Press(name, null, null, newKey);
			mostRecentPress = newPress; //Used to find the most recent press added to map
			pressManager.put(newKey, newPress);
		}
		else {
			Press newPress = new Press(name, null, null, id);
			mostRecentPress = newPress; //Used to find the most recent press added to map
			pressManager.put(id, newPress); 
		}
	}
	/**
	 * Adds Press object to collection
	 * @param press - Press object that can contain transfer times, locations, key
	 */
	public static void addPress(Press press) {
		//If hash table contains key lets make another
		if (pressManager!=null && pressManager.containsKey(press.getKey())) { //This could better optimized better for memory purposes. Remove recursion
			int newKey = getNewKey(press.getKey()); //Lets get a new key
			Press newPress = new Press(press.getName(), press.getTransferTimes(), press.getLocations(), newKey);
			mostRecentPress = newPress; //Used to find the most recent press added to map
			pressManager.put(newKey, newPress);
		}
		else {
			Press newPress = new Press(press.getName(), press.getTransferTimes(), press.getLocations(), press.getKey());
			mostRecentPress = newPress; //Used to find the most recent press added to map
			pressManager.put(press.getKey(), newPress); 
		}
	}
	
	/**
	 * Removes Press from hashmap
	 * @param key
	 */
	public static void removePress(int key) {
		pressManager.remove(key);
	}
	
	 /**
	  * Generate new key value
	  * @param n - key
	  * @return n - new key
	  */
    private static int getNewKey(int n) {

        n = n + 1; //increment hashcode

        if (pressManager.containsKey(n)) {
            n = getNewKey(n);
        }
        //Return new key
        return n;
    }
	//Gets the most recently added press
	public static Press getRecentPress() {
		return mostRecentPress;
		
	}
	/**
	 * Gets a press based off key
	 * @param key
	 * @return Press object
	 */
	public static Press getPress(int key) {
	
		return pressManager.get(key);
	}
	
	public static void setRecentAdded(Boolean recentAdded) {
		PressManager.recentAdded = recentAdded;
	}
	
	public static Boolean getRecentAdded() {
		return recentAdded;
	}
	/**
	 * Update press in hash map
	 * @param press
	 */
	public static void updatePress(Press press) {
		mostRecentPress = press;
		pressManager.put(press.getKey(), press);
	}
	/**
	 * Return a list of press objects in hashmap
	 * @return list - ArrayList of Press objects
	 */
	public static ArrayList<Press> getAllPresses() {
		ArrayList<Press> list = new ArrayList<>();
		for (Press press : pressManager.values()) {
			list.add(press);
		}
		return list;
	}

	public static boolean isEmpty() {
		return pressManager.isEmpty();
	}
	/**
	 * Returns the hashmap
	 * @return pressManager - hashmap of Press collection
	 */
	public static HashMap<Integer, Press> getMap() {
		return pressManager;
	}

}
