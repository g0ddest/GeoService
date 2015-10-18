package name.velikodniy.vitaliy.geo.api;

import name.velikodniy.vitaliy.geo.realm.yandex.route.RealmYandexRouteResponse;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import java.util.Map;

public interface YandexMapsApi {

    @GET("/services/route/2.0/")
    RealmYandexRouteResponse getRoute(@QueryMap Map<String, String> options);

}