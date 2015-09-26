package name.velikodniy.vitaliy.geo.cache;

import java.io.IOException;

public interface CachingProvider {

    void cache(String key, String value) throws CacheException, IOException;

}
