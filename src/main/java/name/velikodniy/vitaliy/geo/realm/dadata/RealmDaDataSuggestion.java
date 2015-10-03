package name.velikodniy.vitaliy.geo.realm.dadata;

import java.io.Serializable;
import java.util.List;

public class RealmDaDataSuggestion {
    private String uuid;

    private List<RealmDaDataAnswer> suggestions;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<RealmDaDataAnswer> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<RealmDaDataAnswer> suggestions) {
        this.suggestions = suggestions;
    }
}