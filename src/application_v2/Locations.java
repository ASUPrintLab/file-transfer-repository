package application_v2;

public class Locations {
	private String name;
	private String fromLocation;
	private String toLocation;
	
	public Locations(String name, String fromLocation, String toLocation) {
		super();
		this.name = name;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFromLocation() {
		return fromLocation;
	}
	
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	
	public String getToLocation() {
		return toLocation;
	}
	
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
}
