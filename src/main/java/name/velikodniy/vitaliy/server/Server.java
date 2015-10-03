package name.velikodniy.vitaliy.server;
import com.google.gson.Gson;
import name.velikodniy.vitaliy.geo.api.SuggestionRequestBody;
import name.velikodniy.vitaliy.geo.cache.CachingProvider;
import name.velikodniy.vitaliy.geo.cache.Redis;
import name.velikodniy.vitaliy.geo.provider.DaDataService;
import name.velikodniy.vitaliy.geo.provider.GeoProvider;
import name.velikodniy.vitaliy.geo.provider.SuggestionProvider;
import name.velikodniy.vitaliy.geo.provider.YandexGeocodeService;
import name.velikodniy.vitaliy.geo.realm.dadata.RealmDaDataSuggestion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
public class Server {

    private Gson gson = new Gson();
    private SuggestionProvider _suggestion;
    private GeoProvider _geo;

    public Server() {
        CachingProvider _cache = new Redis();
        _suggestion = new DaDataService(_cache);
        _geo = new YandexGeocodeService(_cache);
    }

    @GET
    public String apiMain() {
        return "Nothing here. Check the docs.";
    }

    @GET
    @Path("/suggest")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String suggestions(
            @QueryParam("q") String q,
            @DefaultValue("10") @QueryParam("count") int count
    ){
        try {
            return gson.toJson(
                    _suggestion.getSuggestions(new SuggestionRequestBody(q, count))
            );
        }catch(IOException ex){
            //TODO: return 403
            return "Error";
        }
    }

    @GET
    @Path("/geocode_reverse")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String reverseGeo(
            @QueryParam("lat") float lat,
            @QueryParam("lng") float lng
    ){
        return gson.toJson(_geo.getObjects(lat, lng));
    }

    @GET
    @Path("/geocode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String reverseGeo(
            @QueryParam("name") String name
    ){
        return gson.toJson(_geo.getObjects(name));
    }

}