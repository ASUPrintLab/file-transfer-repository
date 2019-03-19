package application_v2;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonElement;
/*
 * Author: Mitchell Roberts
 */
public class Press {
	private String name; //name of press
	private int key; //key for hashtable
	private ArrayList<TransferTime> TransferTimes; //Transfer times
	private ArrayList<Locations> locations; //Location from/to that files will be moved
	
	public Press(String name, ArrayList<TransferTime> transferTimes, ArrayList<Locations> locations, int id) {
		super();
		this.name = name;
		TransferTimes = transferTimes;
		this.locations = locations;
		this.key = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<TransferTime> getTransferTimes() {
		return TransferTimes;
	}

	public void setTransferTimes(ArrayList<TransferTime> transferTimes) {
		TransferTimes = transferTimes;
	}

	public ArrayList<Locations> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Locations> locations) {
		this.locations = locations;
	}

	public void updateTimes(ArrayList<TransferTime> times) {
		if (this.TransferTimes == null) {
			setTransferTimes(times);
		}
		else {
			for(TransferTime time : times) { //Iterate through an add times to existing times
				this.TransferTimes.add(time);
			}
		}
	}

	public boolean locationsEmpty() {
		if (this.locations != null) {
			return this.locations.isEmpty();
		}
		else {
			return false;
		}
	}

	public boolean timesEmpty() {
		if (this.TransferTimes != null) {
			return this.locations.isEmpty();
		}
		else {
			return false;
		}
	}
}
