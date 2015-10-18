package name.velikodniy.vitaliy.server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import name.velikodniy.vitaliy.geo.api.SuggestionRequestBody;
import name.velikodniy.vitaliy.geo.cache.CachingProvider;
import name.velikodniy.vitaliy.geo.cache.Redis;
import name.velikodniy.vitaliy.geo.provider.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;

@Path("/")
public class Server {

    private Gson gson = new GsonBuilder().serializeNulls().create();
    private SuggestionProvider _suggestion;
    private GeoProvider _geoYandex;
    private GeoProvider _geoGoogle;

    public Server() {
        CachingProvider _cache = null;
        try{
            _cache = new Redis();
        }catch (Exception e){
            System.out.println("Cant connect to redis");
        }
        _suggestion = new DaDataService(_cache);
        _geoYandex = new YandexGeocodeService(_cache);
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
            //TODO: return 403
            return "Error";
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
        return gson.toJson(_geoYandex.getObjects(name));
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
        return gson.toJson(_geoGoogle.getRoute(latStart, lngStart, latEnd, lngEnd));
    }

}