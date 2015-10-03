package name.velikodniy.vitaliy.geo.realm.yandex.geocode;

public class RealmYandexAdministrativeArea {
    private String AdministrativeAreaName;
    private RealmYandexSubAdministrativeArea SubAdministrativeArea;

    public String getAdministrativeAreaName() {
        return AdministrativeAreaName;
    }

    public void setAdministrativeAreaName(String administrativeAreaName) {
        AdministrativeAreaName = administrativeAreaName;
    }

    public RealmYandexSubAdministrativeArea getSubAdministrativeArea() {
        return SubAdministrativeArea;
    }

    public void setSubAdministrativeArea(RealmYandexSubAdministrativeArea subAdministrativeArea) {
        SubAdministrativeArea = subAdministrativeArea;
    }
}
