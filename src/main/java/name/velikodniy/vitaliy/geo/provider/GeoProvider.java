package name.velikodniy.vitaliy.geo.provider;

import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.dto.GeoRoute;

import java.util.List;

public interface GeoProvider {

    List<GeoObject> getObjects(String name);
    List<GeoObject> getObjects(float lat, float lng);
    List<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest);

}
