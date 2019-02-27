package application_v2;

import java.util.HashMap;
import java.util.Map;
/*
 * Author: Mitchell Roberts
 */
public class PressManager {
	static HashMap<Integer, Press> pressManager = new HashMap<Integer, Press>();
	static Boolean recentAdded;
	static Press mostRecentPress;

	/*
	 * Add new press to collection
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
	
	/*
	 * Remove press from map
	 */
	public static void removePress(int key) {
		pressManager.remove(key);
	}
	
	 /*
     * Generate new key value
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
	

	public static Press getPress(int key) {
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
