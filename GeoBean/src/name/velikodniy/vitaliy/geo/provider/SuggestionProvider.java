package name.velikodniy.vitaliy.geo.provider;

import name.velikodniy.vitaliy.geo.api.SuggestionRequestBody;
import name.velikodniy.vitaliy.geo.realm.dadata.RealmDaDataSuggestion;

import java.io.IOException;

public interface SuggestionProvider {

    RealmDaDataSuggestion getSuggestions(SuggestionRequestBody query) throws IOException;

}
