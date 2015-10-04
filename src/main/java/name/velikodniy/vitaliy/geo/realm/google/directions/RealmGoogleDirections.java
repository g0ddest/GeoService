package name.velikodniy.vitaliy.geo.realm.google.directions;

import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.dto.GeoRoute;
import name.velikodniy.vitaliy.geo.dto.Point;

import java.util.ArrayList;
import java.util.List;

public class RealmGoogleDirections {
    private List<RealmGoogleWaypoint> geocoded_waypoints;
    private List<RealmGoogleRoute> routes;
    private String status;

    public List<RealmGoogleWaypoint> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public void setGeocoded_waypoints(List<RealmGoogleWaypoint> geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public List<RealmGoogleRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RealmGoogleRoute> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeoRoute> getGeoRoutes(){

        ArrayList<GeoRoute> routes = new ArrayList<>();

        for(RealmGoogleRoute realmGoogleRoute : getRoutes())
            for (RealmGoogleLeg leg : realmGoogleRoute.getLegs())
                routes.add(new GeoRoute() {{
                    setDistance(leg.getDistance().getValue());
                    setDuration(leg.getDuration().getValue());
                    setOrigin(new GeoObject(
                            new Point(leg.getStart_location().getLat(), leg.getStart_location().getLng()),
                            leg.getStart_address(),
                            leg.getStart_address()
                    ));
                    setDestination(new GeoObject(
                            new Point(leg.getEnd_location().getLat(), leg.getEnd_location().getLng()),
                            leg.getEnd_address(),
                            leg.getEnd_address()
                    ));
                }});

        return routes;

    }
}
