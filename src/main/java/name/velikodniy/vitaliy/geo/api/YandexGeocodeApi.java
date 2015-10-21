package name.velikodniy.vitaliy.geo.api;

import name.velikodniy.vitaliy.geo.realm.yandex.geocode.RealmYandexGeocode;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import java.util.Map;

public interface YandexGeocodeApi {
    @GET("/1.x/")
    RealmYandexGeocode geocode(@QueryMap Map<String, String> options);

    @GET("/1.x/")
    void geocodeAsync(@QueryMap Map<String, String> options, Callback<RealmYandexGeocode> callback);
}