package ie.jtc.services;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.*;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import ie.jtc.model.LatitudeAndLongitude;
import ie.jtc.model.Location;

@Service
public class GoogleGeoService implements GeoService {
	private String jtcApiKey = "AIzaSyBnnOcSKaX3E4vGHUD7UQTC5_TRmotpG7w";
	private GeoApiContext context;
	private Logger log = Logger.getLogger(GoogleGeoService.class);

	GoogleGeoService() {
		context = new GeoApiContext();
		context.setApiKey(jtcApiKey);
		// parameters lifted from google tests
		this.context = context.setQueryRateLimit(3).setConnectTimeout(1, TimeUnit.SECONDS)
				.setReadTimeout(1, TimeUnit.SECONDS).setWriteTimeout(1, TimeUnit.SECONDS);
	}

	/* (non-Javadoc)
	 * @see ie.jtc.services.GeoServiceI#getGeoCode(ie.jtc.model.Location)
	 */
	@Override
	public Location.Status getGeoCode(Location loc) throws Exception {
		long start = System.currentTimeMillis();
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.newRequest(context).address(loc.getAddress()).await();
		} catch (ApiException e) {
			return loc.setStatus(e);
		}
		log.debug(loc.getAddress() + " took " + (System.currentTimeMillis() - start) + " to return"
				+ (results == null ? " null " : Integer.toString(results.length)));
		if (results != null) {
			loc.assimilateGeoResults(results);
			return Location.Status.OK;
		} else {
			return Location.Status.ZERO_RESULTS;
		}
	}


}
