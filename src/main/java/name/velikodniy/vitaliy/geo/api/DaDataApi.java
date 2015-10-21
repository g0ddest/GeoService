package name.velikodniy.vitaliy.geo.api;

import name.velikodniy.vitaliy.geo.realm.dadata.RealmDaDataSuggestion;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface DaDataApi {
    @POST("/api/v2/suggest/address")
    RealmDaDataSuggestion getSuggestion(@Body SuggestionRequestBody body);

    @POST("/api/v2/suggest/address")
    void getSuggestionAsync(@Body SuggestionRequestBody body, Callback<RealmDaDataSuggestion> callback);
}
