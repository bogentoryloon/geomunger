package ie.jtc.services;

import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ie.jtc.model.LatitudeAndLongitude;
import ie.jtc.model.bingjson.Route;

@Service
public class BingDistanceService implements DistanceService {
	private final static String key="&key=AuVg_A9RO6mo3IUgeLvozGKR8pHhOX2ClPnPhKlz-SRAoXwUP8TYdg1KhKC7q3HP";
	private final static String baseUrl="http://dev.virtualearth.net/REST/V1/Routes/Driving?o=json&";
	@Override
	public double drivingDistanceMeters(LatitudeAndLongitude locA, LatitudeAndLongitude locB) {
		StringBuilder url=new StringBuilder(baseUrl)
				.append(codeWayPoint(locA,0))
				.append("&")
				.append(codeWayPoint(locB,1))
				.append(key);
		RestTemplate restTemplate = new RestTemplate();
		Route route = restTemplate.getForObject(url.toString(),Route.class);
		if( route != null
			&& route.resourceSets != null
			&& route.resourceSets[0].resources != null ){
			assert "Kilometer".equals(route.resourceSets[0].resources[0].distanceUnit);
			// convert km to metre
			return 	route.resourceSets[0].resources[0].travelDistance*1000;		
		} 
		return 0;
	}

	private String codeWayPoint(LatitudeAndLongitude loc,int number ){
		return String.format("wp.%d=%f,%f",number,loc.getLatitude(),loc.getLongitude());
	}
}
