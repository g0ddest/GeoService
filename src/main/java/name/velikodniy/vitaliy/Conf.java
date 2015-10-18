package name.velikodniy.vitaliy;

import java.util.HashMap;

// Todo replace conf to etc
public class Conf {

    public static boolean DEBUG = true;
    public static String DADATA_BASE_URL = "https://dadata.ru";
    public static String DADATA_API_KEY = "";
    public static String REDIS_HOST = "188.226.168.36";
    public static String SUGGESTIONS_CACHE_PREFIX = "SG@";
    public static int SUGGESTIONS_CACHE_SEC = 86400; // 1 day
    public static String YANDEX_GEOCODE_BASE_URL = "https://geocode-maps.yandex.ru/";
    public static String YANDEX_GEOCODE_CACHE_PREFIX = "GCY@";
    public static String GOOGLE_GEOCODE_CACHE_PREFIX = "GCG@";
    public static int GEOCODE_CACHE_SEC = 86400; // 1 day
    public static String GOOGLE_API_KEY = "";
    public static String GOOGLE_DIRECTIONS_BASE_URL = "https://maps.googleapis.com/";
    public static int ROUTE_CACHE_SEC = 30; // 30 sec
    public static String GOOGLE_LANG = "ru";
    public static HashMap<String, String> YANDEX_DADATA_KINDS = new HashMap<String, String>(){{
        put("street", "street");
        put("locality", "city");
        put("area", "area");
        put("province", "region");
    }};

}
