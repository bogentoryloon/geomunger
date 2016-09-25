package ie.jtc.services;

import ie.jtc.model.Location;

public interface GeoService {

	Location.Status getGeoCode(Location loc) throws Exception;

}