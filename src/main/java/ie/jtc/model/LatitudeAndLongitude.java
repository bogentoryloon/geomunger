package ie.jtc.model;


/**
 *	representation of co-ordinates, specified in fractional degrees 
 * @author John
 *
 */
public class LatitudeAndLongitude {
	private double longitude;
	private double latitude;
	public LatitudeAndLongitude(double longitude,double latitude){
		setLongitude(longitude);
		setLatitude(latitude);		
	}
	public LatitudeAndLongitude(String coords) {
		String pair[]=coords.split(",");
		setLongitudeString(pair[0]);
		setLatitudeString(pair[1]);

	}
	public LatitudeAndLongitude() {
		// TODO Auto-generated constructor stub
	}
	public void setLatitudeString(String latitude2) {
		this.setLatitude(Double.parseDouble(latitude2));
		
	}
	public void setLongitudeString(String longitude2) {
		this.setLongitude(Double.parseDouble(longitude2));		
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
