package name.velikodniy.vitaliy.geo.realm.yandex.geocode;

import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.dto.Point;

import java.util.ArrayList;
import java.util.List;

public class RealmYandexGeocode {
    private RealmYandexResponse response;

    public RealmYandexResponse getResponse() {
        return response;
    }

    public void setResponse(RealmYandexResponse response) {
        this.response = response;
    }

    public List<GeoObject> getGeoObjects(){

        ArrayList<GeoObject> geoObjectList = new ArrayList<>();

        if(getResponse() != null &&
            getResponse().getGeoObjectCollection() != null &&
            getResponse().getGeoObjectCollection().getFeatureMember() != null) {
                getResponse().getGeoObjectCollection().getFeatureMember()
                        .stream()
                        .filter(featureMember -> featureMember != null && featureMember.getGeoObject() != null)
                        .forEach(featureMember -> {
                            String[] pos = featureMember.getGeoObject().getPoint().getPos().split(" ");
                            geoObjectList.add(new GeoObject(
                                    new Point(Float.parseFloat(pos[1]), Float.parseFloat(pos[0])),
                                    featureMember.getGeoObject().getName(),
                                    featureMember.getGeoObject().getDescription()
                    ));
            });
        }

        return geoObjectList;
    }
}
