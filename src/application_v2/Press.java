package application_v2;

public class Press {
	private String name; //name of press
	private TransferTime[] TransferTimes; //Transfer times
	private Locations[] locations; //Location from/to that files will be moved
	
	public Press(String name, TransferTime[] transferTimes, Locations[] locations) {
		super();
		this.name = name;
		TransferTimes = transferTimes;
		this.locations = locations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TransferTime[] getTransferTimes() {
		return TransferTimes;
	}

	public void setTransferTimes(TransferTime[] transferTimes) {
		TransferTimes = transferTimes;
	}

	public Locations[] getLocations() {
		return locations;
	}

	public void setLocations(Locations[] locations) {
		this.locations = locations;
	}
}
