package name.velikodniy.vitaliy.geo.provider;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import name.velikodniy.vitaliy.geo.Conf;
import name.velikodniy.vitaliy.geo.api.DaDataApi;
import name.velikodniy.vitaliy.geo.api.SuggestionRequestBody;
import name.velikodniy.vitaliy.geo.cache.CachingProvider;
import name.velikodniy.vitaliy.geo.realm.dadata.RealmDaDataSuggestion;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.io.IOException;

public class DaDataService implements SuggestionProvider {

    private DaDataApi apiService;
    private CachingProvider _cache;
    private Gson _gson_encoder = new Gson();
    private Gson _gson_builder;

    public DaDataService(CachingProvider cache) {
        _cache = cache;
        _gson_builder = new GsonBuilder()
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
                .setEndpoint(Conf.DADATA_BASE_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", "application/json");
                        request.addHeader("Accept", "application/json");
                        request.addHeader("Authorization", "Token ".concat(Conf.DADATA_API_KEY));
                    }
                })
                .setConverter(new GsonConverter(_gson_builder))
                .build();

        apiService = restAdapter.create(DaDataApi.class);
    }


    @Override
    public RealmDaDataSuggestion getSuggestions(SuggestionRequestBody body) throws IOException {
        String cacheKey = String.format("%s%d%s", Conf.SUGGESTIONS_CACHE_PREFIX, body.getCount(), body.getQuery());
        if(_cache != null && _cache.exists(cacheKey)){
            return _gson_builder.fromJson(_cache.get(cacheKey), RealmDaDataSuggestion.class);
        }else {
            RealmDaDataSuggestion response = apiService.getSuggestionSync(body);
            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson_encoder.toJson(response),
                        Conf.SUGGESTIONS_CACHE_SEC);
            return response;
        }
    }
}
