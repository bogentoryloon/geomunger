package ie.jtc.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ie.jtc.model.GPLocation;
import ie.jtc.model.Location;
@Service
public interface NearbyService {

	Map<Double, GPLocation> findWithinDrivingDistance(
			String gpId, 
			List<GPLocation> gps, 
			double distanceKm,
			double degreeBound);
}