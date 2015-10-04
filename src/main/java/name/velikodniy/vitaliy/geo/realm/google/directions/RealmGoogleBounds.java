package name.velikodniy.vitaliy.geo.realm.google.directions;

public class RealmGoogleBounds {
    private RealmGoogleCord northeast;
    private RealmGoogleCord southwest;

    public RealmGoogleCord getNortheast() {
        return northeast;
    }

    public void setNortheast(RealmGoogleCord northeast) {
        this.northeast = northeast;
    }

    public RealmGoogleCord getSouthwest() {
        return southwest;
    }

    public void setSouthwest(RealmGoogleCord southwest) {
        this.southwest = southwest;
    }
}
