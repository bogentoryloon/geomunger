package ie.jtc.model;

public abstract class Location {
	private String address;
	private LongitudeAndLatitude longitudeAndLatitude;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LongitudeAndLatitude getLongitudeAndLatitude() {
		return longitudeAndLatitude;
	}
	public void setLongitudeAndLatitude(LongitudeAndLatitude longitudeAndLatitude) {
		this.longitudeAndLatitude = longitudeAndLatitude;
	}
}
