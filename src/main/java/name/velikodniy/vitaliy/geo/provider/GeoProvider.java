package name.velikodniy.vitaliy.geo.provider;

import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.dto.GeoRoute;

import java.io.IOException;
import java.util.ArrayList;

public interface GeoProvider {

    ArrayList<GeoObject> getObjects(String name) throws GeoProviderException, IOException;
    ArrayList<GeoObject> getObjects(float lat, float lng) throws GeoProviderException, IOException;

    ArrayList<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest);

}
