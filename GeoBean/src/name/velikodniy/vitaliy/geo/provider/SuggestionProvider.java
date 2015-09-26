package name.velikodniy.vitaliy.geo.provider;

import org.json.simple.JSONObject;

import java.io.IOException;

public interface SuggestionProvider {

    JSONObject getSuggestions(String query) throws IOException;

}
