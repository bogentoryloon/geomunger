package ie.jtc.model;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.maps.errors.*;
import com.google.maps.model.GeocodingResult;



public  class Location {
	private String address;
	private LatitudeAndLongitude longitudeAndLatitude=new LatitudeAndLongitude();
	// things from google api, may be useful
	private String placeId;
	private static Logger log=Logger.getLogger(Location.class);
	/*
	 * "OK" indicates that no errors occurred; the address was successfully parsed and at least one geocode was returned.
"ZERO_RESULTS" indicates that the geocode was successful but returned no results. This may occur if the geocoder was passed a non-existent address.
"OVER_QUERY_LIMIT" indicates that you are over your quota.
"REQUEST_DENIED" indicates that your request was denied.
"INVALID_REQUEST" generally indicates that the query (address, components or latlng) is missing.
"UNKNOWN_ERROR" indicates that the request could not be processed due to a server error. The request may succeed if you try again.
	 */
	public enum Status{
		OK("OK"),
		ZERO_RESULTS("ZERO_RESULTS"),
		OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"),
		REQUEST_DENIED("REQUEST_DENIED"),
		INVALID_REQUEST("INVALID_REQUEST"),
		UNKNOWN_ERROR("UNKNOWN_ERROR"),
		MAX_ELEMENTS_EXCEEDED("MAX_ELEMENTS_EXCEEDED"),
		NOT_FOUND("NOT_FOUND"),
		ACCESS_NOT_CONFIGURED("ACCESS_NOT_CONFIGURED"),
		INVALID_ARGUMENT("INVALID_ARGUMENT"),
		RESOURCE_EXHAUSTED("RESOURCE_EXHAUSTED"),
		PERMISSION_DENIED("PERMISSION_DENIED");
		private final String value;
		Status(String value){
			this.value=value;
		}
		
	}
	/*
	 * well that's the json, but have 
	 * reverse engineer ApiException:
	 * ...
	 *     // Classic Geo API error formats
    if ("OK".equals(status)) {
      return null;
    } else if ("INVALID_REQUEST".equals(status)) {
      return new InvalidRequestException(errorMessage);
    } else if ("MAX_ELEMENTS_EXCEEDED".equals(status)) {
      return new MaxElementsExceededException(errorMessage);
    } else if ("NOT_FOUND".equals(status)) {
      return new NotFoundException(errorMessage);
    } else if ("OVER_QUERY_LIMIT".equals(status)) {
      return new OverQueryLimitException(errorMessage);
    } else if ("REQUEST_DENIED".equals(status)) {
      return new RequestDeniedException(errorMessage);
    } else if ("UNKNOWN_ERROR".equals(status)) {
      return new UnknownErrorException(errorMessage);
    } else if ("ZERO_RESULTS".equals(status)) {
      return new ZeroResultsException(errorMessage);
    }

    // New-style Geo API error formats
    if ("ACCESS_NOT_CONFIGURED".equals(status)) {
      return new AccessNotConfiguredException(errorMessage);
    } else if ("INVALID_ARGUMENT".equals(status)) {
      return new InvalidRequestException(errorMessage);
    } else if ("RESOURCE_EXHAUSTED".equals(status)) {
      return new OverQueryLimitException(errorMessage);
    } else if ("PERMISSION_DENIED".equals(status)) {
      return new RequestDeniedException(errorMessage);
    }

	 */
	@JsonIgnore
	public Status setStatus(ApiException e) {
		if( e instanceof AccessNotConfiguredException ){
			status=Status.ACCESS_NOT_CONFIGURED;
		}else if( e instanceof InvalidRequestException){
			status=Status.INVALID_REQUEST;
		}else if( e instanceof MaxElementsExceededException){
			status=Status.MAX_ELEMENTS_EXCEEDED;
		}else if( e instanceof NotFoundException ){
			status=Status.NOT_FOUND;
		}else if( e instanceof OverQueryLimitException){
			status=Status.OVER_QUERY_LIMIT;
		}else if( e instanceof RequestDeniedException ){
			status=Status.REQUEST_DENIED;
		}else if( e instanceof UnknownErrorException){
			status=Status.UNKNOWN_ERROR;
		}else if( e instanceof  ZeroResultsException){
			status=Status.ZERO_RESULTS;
		}else if( e instanceof AccessNotConfiguredException){
			status=Status.ACCESS_NOT_CONFIGURED;
		}else if( e instanceof InvalidRequestException){
			status=Status.INVALID_REQUEST;
		}else if( e instanceof OverQueryLimitException ){
			status=Status.OVER_QUERY_LIMIT;
		}else if( e instanceof RequestDeniedException){
			status=Status.PERMISSION_DENIED;
		}
		return status;
	}
	private Status status;
	private Boolean partialMatch;
	private GeocodingResult[] georesults;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LatitudeAndLongitude getLongitudeAndLatitude() {
		return longitudeAndLatitude;
	}
	public void setLongitudeAndLatitude(LatitudeAndLongitude longitudeAndLatitude) {
		this.longitudeAndLatitude = longitudeAndLatitude;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public Boolean getPartialMatch() {
		return partialMatch;
	}
	public void setPartialMatch(Boolean partialMatch) {
		this.partialMatch = partialMatch;
	}
	public Status getStatus() {
		return status;
	}

	public Location(){
		
	}
	public Location(String address){
		setAddress(address);
	}
	/*
	 *  the result set is quite big, don't know what we will find useful
	 *  or how best to handle multiple results till we say bigger data,
	 *  so save it off for now
	 */
	public void assimilateGeoResults(GeocodingResult[] results) {
		georesults=results;
		if( results!=null){
			this.longitudeAndLatitude.setLongitude(results[0].geometry.location.lng);
			this.longitudeAndLatitude.setLatitude(results[0].geometry.location.lat);
			this.placeId=results[0].placeId;
			this.partialMatch=results[0].partialMatch;
			
			// for curiosity...
			for(int i=0;i<results.length;++i){
				log.debug( dump(results[i]) );
			}
		}		
	}
	private String dump(GeocodingResult geo) {
		StringBuilder sb=new StringBuilder();
		sb.append(geo.formattedAddress+geo.formattedAddress )
		.append(geo.placeId);
		if( geo.types!=null){
			sb.append("Types:");
			for(int i=0;i<geo.types.length;++i){
				sb.append(geo.types[i].toString() );
			}
		}
		// see how eircode fares
		if( geo.postcodeLocalities!=null){
			sb.append("PostCodes:");
			for(int i=0;i<geo.postcodeLocalities.length;++i){
				sb.append(geo.postcodeLocalities[i].toString() );
			}
		}		
		return sb.toString();
	}
		

}
