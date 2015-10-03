package name.velikodniy.vitaliy.geo.api;

import name.velikodniy.vitaliy.geo.realm.dadata.RealmDaDataSuggestion;
import retrofit.http.Body;
import retrofit.http.POST;

public interface DaDataApi {

    @POST("/api/v2/suggest/address")
    RealmDaDataSuggestion getSuggestionSync(@Body SuggestionRequestBody body);
}
