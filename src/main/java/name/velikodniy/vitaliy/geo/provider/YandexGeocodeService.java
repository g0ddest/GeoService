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
import name.velikodniy.vitaliy.geo.realm.dadata.RealmDaDataSuggestion;
import name.velikodniy.vitaliy.geo.realm.yandex.geocode.*;
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

        String cacheKey = String.format("%s%s", Conf.GEOCODE_CACHE_PREFIX, name);

        if(_cache != null && _cache.exists(cacheKey)) {
            return _gson.fromJson(_cache.get(cacheKey), ArrayList.class);
        }else{
            List<GeoObject> response = _geocodeApi.geocode(new HashMap<String, String>() {{
                put("geocode", name);
                put("format", "json");
            }}).getGeoObjects();

            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson.toJson(response),
                        Conf.GEOCODE_CACHE_SEC);

            return response;
        }
    }

    @Override
    public List<GeoObject> getObjects(float lat, float lng) {

        String cacheKey = String.format(Locale.ENGLISH, "%s%f,%f", Conf.GEOCODE_CACHE_PREFIX, lng, lat);

        if(_cache != null && _cache.exists(cacheKey)) {
            return _gson.fromJson(_cache.get(cacheKey), ArrayList.class);
        }else{
            List<GeoObject> response = _geocodeApi.geocode(new HashMap<String, String>() {{
                put("geocode", String.format(Locale.ENGLISH, "%f,%f", lng, lat));
                put("format", "json");
            }}).getGeoObjects();

            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson.toJson(response),
                        Conf.GEOCODE_CACHE_SEC);

            return response;
        }
    }

    @Override
    public List<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest) {
        return null;
    }

    @Override
    public String getLocationMeta(float lat, float lng, String locationType) {

        RealmYandexGeocode response = _geocodeApi.geocode(new HashMap<String, String>() {{
            put("geocode", String.format(Locale.ENGLISH, "%f,%f", lng, lat));
            put("format", "json");
        }});

        if(response != null){
            for(RealmYandexFeatureMember r : response.getResponse().getGeoObjectCollection().getFeatureMember()){
                try {
                    String meta = r.getGeoObject().getMetaDataProperty().getGeocoderMetaData().getKind();
                    if(Conf.YANDEX_DADATA_KINDS.containsKey(meta) &&
                       Conf.YANDEX_DADATA_KINDS.get(meta).equals(locationType))
                        return r.getGeoObject().getName();
                }catch (NullPointerException ex){}
            }
        }

        return null;

    }
}
