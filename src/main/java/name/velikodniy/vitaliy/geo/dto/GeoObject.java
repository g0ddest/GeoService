package name.velikodniy.vitaliy.geo.dto;

public class GeoObject {

    Point point;
    String description;
    String name;

    public GeoObject(){}

    public GeoObject(Point point, String description, String name) {
        this.point = point;
        this.description = description;
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
