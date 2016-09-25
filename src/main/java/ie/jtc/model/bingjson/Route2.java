package ie.jtc.model.bingjson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Route2 {
	public final String authenticationResultCode;
	public final String brandLogoUri;
	public final String copyright;
	public final ResourceSet resourceSets[];
	public final long statusCode;
	public final String statusDescription;
	public final String traceId;

	@JsonCreator
    public Route2(@JsonProperty("authenticationResultCode") String authenticationResultCode, 
    		@JsonProperty("brandLogoUri") String brandLogoUri, 
    		@JsonProperty("copyright") String copyright, 
    		@JsonProperty("resourceSets") ResourceSet[] resourceSets, 
    		@JsonProperty("statusCode") long statusCode, 
    		@JsonProperty("statusDescription") String statusDescription, 
    		@JsonProperty("traceId") String traceId){
        this.authenticationResultCode = authenticationResultCode;
        this.brandLogoUri = brandLogoUri;
        this.copyright = copyright;
        this.resourceSets = resourceSets;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.traceId = traceId;
    }

	public static final class ResourceSet {
		public final long estimatedTotal;
		public final Resource resources[];

		@JsonCreator
		public ResourceSet(@JsonProperty("estimatedTotal") long estimatedTotal,
				@JsonProperty("resources") Resource[] resources) {
			this.estimatedTotal = estimatedTotal;
			this.resources = resources;
		}

		public static final class Resource {
			public final String __type;
			public final double[] bbox;
			public final String id;
			public final String distanceUnit;
			public final String durationUnit;
			public final RouteLeg routeLegs[];
			public final String trafficCongestion;
			public final String trafficDataUsed;
			public final double travelDistance;
			public final long travelDuration;
			public final long travelDurationTraffic;

			@JsonCreator
			public Resource(@JsonProperty("__type") String __type, @JsonProperty("bbox") double[] bbox,
					@JsonProperty("id") String id, @JsonProperty("distanceUnit") String distanceUnit,
					@JsonProperty("durationUnit") String durationUnit, @JsonProperty("routeLegs") RouteLeg[] routeLegs,
					@JsonProperty("trafficCongestion") String trafficCongestion,
					@JsonProperty("trafficDataUsed") String trafficDataUsed,
					@JsonProperty("travelDistance") double travelDistance,
					@JsonProperty("travelDuration") long travelDuration,
					@JsonProperty("travelDurationTraffic") long travelDurationTraffic) {
				this.__type = __type;
				this.bbox = bbox;
				this.id = id;
				this.distanceUnit = distanceUnit;
				this.durationUnit = durationUnit;
				this.routeLegs = routeLegs;
				this.trafficCongestion = trafficCongestion;
				this.trafficDataUsed = trafficDataUsed;
				this.travelDistance = travelDistance;
				this.travelDuration = travelDuration;
				this.travelDurationTraffic = travelDurationTraffic;
			}

			public static final class RouteLeg {
				public final ActualEnd actualEnd;
				public final ActualStart actualStart;
				public final AlternateVia alternateVias[];
				public final long cost;
				public final String description;
				public final ItineraryItem itineraryItems[];
				public final String routeRegion;
				public final RouteSubLeg routeSubLegs[];
				public final double travelDistance;
				public final long travelDuration;

				@JsonCreator
				public RouteLeg(@JsonProperty("actualEnd") ActualEnd actualEnd,
						@JsonProperty("actualStart") ActualStart actualStart,
						@JsonProperty("alternateVias") AlternateVia[] alternateVias, @JsonProperty("cost") long cost,
						@JsonProperty("description") String description,
						@JsonProperty("itineraryItems") ItineraryItem[] itineraryItems,
						@JsonProperty("routeRegion") String routeRegion,
						@JsonProperty("routeSubLegs") RouteSubLeg[] routeSubLegs,
						@JsonProperty("travelDistance") double travelDistance,
						@JsonProperty("travelDuration") long travelDuration) {
					this.actualEnd = actualEnd;
					this.actualStart = actualStart;
					this.alternateVias = alternateVias;
					this.cost = cost;
					this.description = description;
					this.itineraryItems = itineraryItems;
					this.routeRegion = routeRegion;
					this.routeSubLegs = routeSubLegs;
					this.travelDistance = travelDistance;
					this.travelDuration = travelDuration;
				}

				public static final class ActualEnd {
					public final String type;
					public final double[] coordinates;

					@JsonCreator
					public ActualEnd(@JsonProperty("type") String type,
							@JsonProperty("coordinates") double[] coordinates) {
						this.type = type;
						this.coordinates = coordinates;
					}
				}

				public static final class ActualStart {
					public final String type;
					public final double[] coordinates;

					@JsonCreator
					public ActualStart(@JsonProperty("type") String type,
							@JsonProperty("coordinates") double[] coordinates) {
						this.type = type;
						this.coordinates = coordinates;
					}
				}

				public static final class AlternateVia {

					@JsonCreator
					public AlternateVia() {
					}
				}

				public static final class ItineraryItem {
					public final String compassDirection;
					public final Detail details[];
					public final String exit;
					public final String iconType;
					public final Instruction instruction;
					public final ManeuverPoint maneuverPoint;
					public final String sideOfStreet;
					public final String tollZone;
					public final String towardsRoadName;
					public final String transitTerminus;
					public final long travelDistance;
					public final long travelDuration;
					public final String travelMode;
					public final Warning warnings[];
					public final Hint hints[];

					@JsonCreator
					public ItineraryItem(@JsonProperty("compassDirection") String compassDirection,
							@JsonProperty("details") Detail[] details, @JsonProperty("exit") String exit,
							@JsonProperty("iconType") String iconType,
							@JsonProperty("instruction") Instruction instruction,
							@JsonProperty("maneuverPoint") ManeuverPoint maneuverPoint,
							@JsonProperty("sideOfStreet") String sideOfStreet,
							@JsonProperty("tollZone") String tollZone,
							@JsonProperty("towardsRoadName") String towardsRoadName,
							@JsonProperty("transitTerminus") String transitTerminus,
							@JsonProperty("travelDistance") long travelDistance,
							@JsonProperty("travelDuration") long travelDuration,
							@JsonProperty("travelMode") String travelMode, @JsonProperty("warnings") Warning[] warnings,
							@JsonProperty("hints") Hint[] hints) {
						this.compassDirection = compassDirection;
						this.details = details;
						this.exit = exit;
						this.iconType = iconType;
						this.instruction = instruction;
						this.maneuverPoint = maneuverPoint;
						this.sideOfStreet = sideOfStreet;
						this.tollZone = tollZone;
						this.towardsRoadName = towardsRoadName;
						this.transitTerminus = transitTerminus;
						this.travelDistance = travelDistance;
						this.travelDuration = travelDuration;
						this.travelMode = travelMode;
						this.warnings = warnings;
						this.hints = hints;
					}

					public static final class Detail {
						public final long compassDegrees;
						public final int[] endPathIndices;
						public final String maneuverType;
						public final String mode;
						public final String[] names;
						public final String roadType;
						public final int[] startPathIndices;

						@JsonCreator
						public Detail(@JsonProperty("compassDegrees") long compassDegrees,
								@JsonProperty("endPathIndices") int[] endPathIndices,
								@JsonProperty("maneuverType") String maneuverType, @JsonProperty("mode") String mode,
								@JsonProperty("names") String[] names, @JsonProperty("roadType") String roadType,
								@JsonProperty("startPathIndices") int[] startPathIndices) {
							this.compassDegrees = compassDegrees;
							this.endPathIndices = endPathIndices;
							this.maneuverType = maneuverType;
							this.mode = mode;
							this.names = names;
							this.roadType = roadType;
							this.startPathIndices = startPathIndices;
						}
					}

					public static final class Instruction {
						public final FormattedText formattedText;
						public final String maneuverType;
						public final String text;

						@JsonCreator
						public Instruction(@JsonProperty("formattedText") FormattedText formattedText,
								@JsonProperty("maneuverType") String maneuverType, @JsonProperty("text") String text) {
							this.formattedText = formattedText;
							this.maneuverType = maneuverType;
							this.text = text;
						}

						public static final class FormattedText {

							@JsonCreator
							public FormattedText() {
							}
						}
					}

					public static final class ManeuverPoint {
						public final String type;
						public final double[] coordinates;

						@JsonCreator
						public ManeuverPoint(@JsonProperty("type") String type,
								@JsonProperty("coordinates") double[] coordinates) {
							this.type = type;
							this.coordinates = coordinates;
						}
					}

					public static final class Warning {
						public final String origin;
						public final String severity;
						public final String text;
						public final String to;
						public final String warningType;

						@JsonCreator
						public Warning(@JsonProperty("origin") String origin, @JsonProperty("severity") String severity,
								@JsonProperty("text") String text, @JsonProperty("to") String to,
								@JsonProperty("warningType") String warningType) {
							this.origin = origin;
							this.severity = severity;
							this.text = text;
							this.to = to;
							this.warningType = warningType;
						}
					}

					public static final class Hint {
						public final String hintType;
						public final String text;

						@JsonCreator
						public Hint(@JsonProperty("hintType") String hintType, @JsonProperty("text") String text) {
							this.hintType = hintType;
							this.text = text;
						}
					}
				}

				public static final class RouteSubLeg {
					public final EndWaypoint endWaypoint;
					public final StartWaypoint startWaypoint;
					public final double travelDistance;
					public final long travelDuration;

					@JsonCreator
					public RouteSubLeg(@JsonProperty("endWaypoint") EndWaypoint endWaypoint,
							@JsonProperty("startWaypoint") StartWaypoint startWaypoint,
							@JsonProperty("travelDistance") double travelDistance,
							@JsonProperty("travelDuration") long travelDuration) {
						this.endWaypoint = endWaypoint;
						this.startWaypoint = startWaypoint;
						this.travelDistance = travelDistance;
						this.travelDuration = travelDuration;
					}

					public static final class EndWaypoint {
						public final String type;
						public final double[] coordinates;
						public final String description;
						public final boolean isVia;
						public final String locationIdentifier;
						public final long routePathIndex;

						@JsonCreator
						public EndWaypoint(@JsonProperty("type") String type,
								@JsonProperty("coordinates") double[] coordinates,
								@JsonProperty("description") String description, @JsonProperty("isVia") boolean isVia,
								@JsonProperty("locationIdentifier") String locationIdentifier,
								@JsonProperty("routePathIndex") long routePathIndex) {
							this.type = type;
							this.coordinates = coordinates;
							this.description = description;
							this.isVia = isVia;
							this.locationIdentifier = locationIdentifier;
							this.routePathIndex = routePathIndex;
						}
					}

					public static final class StartWaypoint {
						public final String type;
						public final double[] coordinates;
						public final String description;
						public final boolean isVia;
						public final String locationIdentifier;
						public final long routePathIndex;

						@JsonCreator
						public StartWaypoint(@JsonProperty("type") String type,
								@JsonProperty("coordinates") double[] coordinates,
								@JsonProperty("description") String description, @JsonProperty("isVia") boolean isVia,
								@JsonProperty("locationIdentifier") String locationIdentifier,
								@JsonProperty("routePathIndex") long routePathIndex) {
							this.type = type;
							this.coordinates = coordinates;
							this.description = description;
							this.isVia = isVia;
							this.locationIdentifier = locationIdentifier;
							this.routePathIndex = routePathIndex;
						}
					}
				}
			}
		}
	}
}