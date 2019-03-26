package application;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonElement;
/** This Class is the Press object that contains information like transfer times, locations and key
 * @author Mitchell Roberts
 * @since 1.0
 */
public class Press {
    private String name; //name of press
    private int key; //key for hashtable
    private ArrayList<TransferTime> TransferTimes; //Transfer times
    private ArrayList<Locations> locations; //Location from/to that files will be moved
    /**
     * Constructor
     * @param name - name of the press
     * @param transferTimes - ArrayList of Transfer Times
     * @param locations - ArrayList of Locations
     * @param id - key
     */
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
    /**
     * Update the times in press
     * @param times - ArrayList of new Tranfer Times
     */
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
    /**
     * This method returns true if the locations ArrayList is empty
     * @return locations - boolean
     */
    public boolean locationsEmpty() {
        if (this.locations != null) {
            return this.locations.isEmpty();
        }
        else {
            return false;
        }
    }
    /**
     * This method returns true if the TranferTimes ArrayList is empty
     * @return TransferTimes - boolean
     */
    public boolean timesEmpty() {
        if (this.TransferTimes != null) {
            return this.TransferTimes.isEmpty();
        }
        else {
            return false;
        }
    }
}
