package name.velikodniy.vitaliy.geo.realm.dadata;

import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.dto.Point;

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

    public GeoObject getGeoObject(){

        if(suggestions.size() > 0) {
            RealmDaDataAnswer answer = suggestions.get(0);
            return new GeoObject(
                    new Point(answer.getRealmData().getGeo_lat(),
                            answer.getRealmData().getGeo_lon()),
                    answer.getValue(),
                    answer.getValue()
            );
        }
        return null;
    }

}