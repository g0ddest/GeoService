package name.velikodniy.vitaliy.geo.provider;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import name.velikodniy.vitaliy.geo.Conf;
import name.velikodniy.vitaliy.geo.api.DaDataApi;
import name.velikodniy.vitaliy.geo.api.SuggestionRequestBody;
import name.velikodniy.vitaliy.geo.realm.dadata.RealmDaDataSuggestion;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.io.IOException;

public class DaDataService implements SuggestionProvider {

    private static volatile DaDataService instance;

    private DaDataApi apiService;

    private DaDataService() {
        Gson gson = new GsonBuilder()
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
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(DaDataApi.class);
    }


    @Override
    public RealmDaDataSuggestion getSuggestions(SuggestionRequestBody body) throws IOException {
        return apiService.getSuggestionSync(body);
    }
}
