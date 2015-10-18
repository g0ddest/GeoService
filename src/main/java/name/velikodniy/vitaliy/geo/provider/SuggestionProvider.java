package name.velikodniy.vitaliy.geo.provider;

import name.velikodniy.vitaliy.geo.api.SuggestionRequestBody;
import name.velikodniy.vitaliy.geo.dto.GeoSuggestion;

import java.io.IOException;
import java.util.List;

public interface SuggestionProvider {

    List<GeoSuggestion> getSuggestions(SuggestionRequestBody query, GeoProvider geoProvider, float lat, float lng, String locations, String locationsValue) throws IOException;

}
