package ie.jtc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import ie.jtc.model.GPLocation;
import ie.jtc.model.Location;

@Service
public class NearbyServiceImpl implements NearbyService {
	private static Logger log = Logger.getLogger(NearbyServiceImpl.class); 
	/* (non-Javadoc)
	 * @see ie.jtc.services.NearbyService#findWithinDrivingDistance(ie.jtc.model.Location, java.util.List, double, double)
	 */
	@Override
	public Map<Double, GPLocation> findWithinDrivingDistance(
			String gpId, 
			List<GPLocation> gps,
			double distanceKm ,
			double degreeBound){
		Map<Double, GPLocation> retval = new HashMap<Double, GPLocation>();
		/* todo:
		 * Design:
		 * use  degreeLatitude to prescreen the list
		 * of locations. Ideally in sql, but could be on the
		 * java list using lambda.
		 * firstly, find the gp in the list matching the passed id.
		 * use the location of that as the nexus, provided it is geocoded
		 * for each other location in gp list,
		 * 	if  gp.location.status == OK
		 * 		&& abs(gp.latitude-nexus.latitude) <= degreeBound
		 * 		&& abs(gp.longitude-nexus.longitude) <= degreeBound
		 * 			call DistanceService to determine the  driving
		 * 			distance between the 2 points
		 * 			if distance < distanceKm
		 * 				add the gp and distance to the map
		 * 
		 */
		log.error("NOT IMPLEMENTED!");
		throw new UnsupportedOperationException("NYI:Ran out of time");			
	}
}
