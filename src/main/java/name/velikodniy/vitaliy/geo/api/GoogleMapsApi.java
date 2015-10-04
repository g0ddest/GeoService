package name.velikodniy.vitaliy.geo.api;

import name.velikodniy.vitaliy.geo.realm.google.directions.RealmGoogleDirections;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import java.util.Map;

public interface GoogleMapsApi {
    @GET("/maps/api/directions/json")
    RealmGoogleDirections getRoute(@QueryMap Map<String, String> options);
}