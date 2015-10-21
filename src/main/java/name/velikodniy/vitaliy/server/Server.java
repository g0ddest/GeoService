package name.velikodniy.vitaliy.server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import name.velikodniy.vitaliy.geo.api.SuggestionRequestBody;
import name.velikodniy.vitaliy.geo.cache.CachingProvider;
import name.velikodniy.vitaliy.geo.cache.Redis;
import name.velikodniy.vitaliy.geo.dto.ErrorObject;
import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.provider.*;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class Server {

    private Gson gson = new GsonBuilder().serializeNulls().create();
    private SuggestionProvider _suggestion;
    private GeoProvider _geoYandex;
    private GeoProvider _geoDadata;
    private GeoProvider _geoGoogle;

    public Server() {
        CachingProvider _cache = null;
        try{
            _cache = new Redis();
        }catch (Exception e){
            System.out.println("Cant connect to redis");
        }
        _suggestion = new DaDataService(_cache);
        _geoDadata = new DaDataService(_cache);
        _geoYandex = new YandexMapsService(_cache);
        _geoGoogle = new GoogleMapsService(_cache);
    }

    @GET
    public String apiMain() {
        return "Nothing here. Check the docs.";
    }

    @GET
    @Path("/suggest")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public String suggestions(
            @QueryParam("q") String q,
            @DefaultValue("10") @QueryParam("c") int count,
            @QueryParam("lat") float lat,
            @QueryParam("lng") float lng,
            @DefaultValue("city") @QueryParam("locations") String locations,
            @QueryParam("locations_value") String locationsValue

    ){
        try {
            SuggestionRequestBody request = new SuggestionRequestBody(q, count);
            return gson.toJson(
                    _suggestion.getSuggestions(request, _geoYandex, lat, lng, locations, locationsValue)
            );
        }catch(IOException ex){
            return gson.toJson(new ErrorObject("Network error"));
        }
    }

    @GET
    @Path("/geocode_reverse")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public String reverseGeo(
            @QueryParam("lat") float lat,
            @QueryParam("lng") float lng
    ){
        return gson.toJson(_geoGoogle.getObjects(lat, lng));
    }

    @GET
    @Path("/geocode")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public String reverseGeo(
            @QueryParam("name") String name
    ){
        boolean dadataAnswered = false;
        boolean yandexAnswered = false;
        List<GeoObject> dadataObjects = _geoDadata.getObjects(name);
        List<GeoObject> yandexObjects = null;

        if(Utils.geocoderResponseQuality(dadataObjects) > 2)
            yandexObjects = _geoYandex.getObjects(name);

        if(yandexObjects == null || Utils.geocoderResponseQuality(dadataObjects) < Utils.geocoderResponseQuality(yandexObjects))
            return gson.toJson(dadataObjects);
        else
            return gson.toJson(yandexObjects);
    }

    @GET
    @Path("/route")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public String route(
            @QueryParam("lat_start") float latStart,
            @QueryParam("lng_start") float lngStart,
            @QueryParam("lat_end") float latEnd,
            @QueryParam("lng_end") float lngEnd
    ){
        try {
            return gson.toJson(_geoYandex.getRoute(latStart, lngStart, latEnd, lngEnd));
        }catch(GeoProviderException e){
            return gson.toJson(new ErrorObject(e.getMessage()));
        }
    }

}