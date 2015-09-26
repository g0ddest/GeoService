package name.velikodniy.vitaliy.geo.dto;

public class GeoRoute {

    GeoObject origin;
    GeoObject destination;

    Integer distance;
    Integer duration;

    public GeoObject getOrigin() {
        return origin;
    }

    public void setOrigin(GeoObject origin) {
        this.origin = origin;
    }

    public GeoObject getDestination() {
        return destination;
    }

    public void setDestination(GeoObject destination) {
        this.destination = destination;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
