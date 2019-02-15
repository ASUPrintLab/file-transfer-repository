package application_v2;

import java.util.HashMap;
import java.util.Map;

public class PressManager {
	static Map<String, Press> pressManager = new HashMap<String, Press>();

	/*
	 * Add new press to collection
	 */
	public static void addPress(String name, String id) {
		Press newPress = new Press(name, null, null);
		//If hash table contains key lets make another
		if (pressManager!=null && pressManager.containsKey(id)) {
			String newKey = getNewKey(); //Lets get a new key
			addPress(name,newKey); //Try again
		}
		else {
			pressManager.put(id, newPress); 
		}
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
}
