package name.velikodniy.vitaliy.geo.api;

import name.velikodniy.vitaliy.geo.realm.google.directions.RealmGoogleDirections;
import name.velikodniy.vitaliy.geo.realm.google.geocode.RealmGoogleGeocode;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import java.util.Map;

public interface GoogleMapsApi {
    @GET("/maps/api/directions/json")
    RealmGoogleDirections getRoute(@QueryMap Map<String, String> options);

    @GET("/maps/api/directions/json")
    void getRouteAsync(@QueryMap Map<String, String> options, Callback<RealmGoogleDirections> callback);

    @GET("/maps/api/geocode/json")
    RealmGoogleGeocode geocode (@QueryMap Map<String, String> options);

    @GET("/maps/api/geocode/json")
    void geocode (@QueryMap Map<String, String> options, Callback<RealmGoogleGeocode> callback);
}