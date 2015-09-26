package name.velikodniy.vitaliy.geo.provider;

import name.velikodniy.vitaliy.geo.dto.SuggestionRequestBody;
import org.json.simple.JSONObject;
import retrofit.Callback;

import java.io.IOException;

public interface SuggestionProvider {

    JSONObject getSuggestions(SuggestionRequestBody query) throws IOException;
    void getSuggestionsAsync(SuggestionRequestBody query, Callback<JSONObject> callback) throws IOException;

}
