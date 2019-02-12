package application_v2;

public class Locations {
	private String fromLocation;
	private String toLocation;
	
	public Locations(String fromLocation, String toLocation) {
		super();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
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
