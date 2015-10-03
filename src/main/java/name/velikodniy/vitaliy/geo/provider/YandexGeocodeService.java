package name.velikodniy.vitaliy.geo.provider;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import name.velikodniy.vitaliy.Conf;
import name.velikodniy.vitaliy.geo.api.YandexGeocodeApi;
import name.velikodniy.vitaliy.geo.cache.CachingProvider;
import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.dto.GeoRoute;
import name.velikodniy.vitaliy.geo.dto.Point;
import name.velikodniy.vitaliy.geo.realm.yandex.geocode.RealmYandexFeatureMember;
import name.velikodniy.vitaliy.geo.realm.yandex.geocode.RealmYandexGeocode;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class YandexGeocodeService implements GeoProvider {

    private CachingProvider _cache;
    private Gson _gson;
    private YandexGeocodeApi _geocodeApi;

    public YandexGeocodeService(CachingProvider cache) {

        _cache = cache;
        _gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).create();


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(Conf.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setEndpoint(Conf.YANDEX_GEOCODE_BASE_URL)
                .setConverter(new GsonConverter(_gson))
                .build();

        _geocodeApi = restAdapter.create(YandexGeocodeApi.class);

    }

    @Override
    public List<GeoObject> getObjects(String name) {
        return null;
    }

    @Override
    public List<GeoObject> getObjects(float lat, float lng) {
        RealmYandexGeocode response = _geocodeApi.geocode(new HashMap<String, String>() {{
            put("geocode", String.format(Locale.ENGLISH, "%f,%f", lng, lat));
            put("format", "json");
        }});

        return response.getGeoObjects();
    }

    @Override
    public List<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest) {
        return null;
    }

}
