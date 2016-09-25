package ie.jtc.services;

import ie.jtc.model.LatitudeAndLongitude;

public interface DistanceService {

	double drivingDistanceMeters(LatitudeAndLongitude locA, LatitudeAndLongitude locB);

}