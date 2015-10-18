package name.velikodniy.vitaliy.geo.realm.google.geocode;

import name.velikodniy.vitaliy.geo.dto.GeoObject;
import name.velikodniy.vitaliy.geo.dto.Point;

import java.util.ArrayList;
import java.util.List;

public class RealmGoogleGeocode {
    String status;
    List<RealmGoogleGeocodeResult> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RealmGoogleGeocodeResult> getResults() {
        return results;
    }

    public void setResults(List<RealmGoogleGeocodeResult> results) {
        this.results = results;
    }

    public List<GeoObject> getGeoObjects(){

        List<GeoObject> ret = new ArrayList<>();

        for(RealmGoogleGeocodeResult realmGoogleGeocodeResult : results){

            // The most relative object is in the top of list, better way to get only him.
            // After the first object goes higher level objects (etc country)

            ret.add(new GeoObject(
                    new Point(realmGoogleGeocodeResult.getGeometry().getLocation().getLat(),
                            realmGoogleGeocodeResult.getGeometry().getLocation().getLng()),
                    realmGoogleGeocodeResult.getFormatted_address(),
                    realmGoogleGeocodeResult.getFormatted_address()
            ));

        }

        return ret;
    }
}
