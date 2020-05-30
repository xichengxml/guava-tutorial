package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-30 18:56
 */
@Slf4j
public class T08_Cache {

    /**
     * 是在指定时间内没有被创建/覆盖，则指定时间过后，再次访问时，会去刷新该缓存，在新值没有到来之前，始终返回旧值
     */
    private static final LoadingCache<String, List<String>> refreshAfterWriteCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheLoader<String, List<String>>() {
                @Override
                public List<String> load(String key) throws Exception {
                    return new ArrayList<>();
                }
            });

    public static void main(String[] args) throws Exception {
        String key = CacheConstant.KEY;

        List<String> stringList = refreshAfterWriteCache.getUnchecked(key);
        log.info("stringList: {}", stringList);

        // 10秒后，会触发超时刷新，但是没有新值刷新，因此返回旧值
        TimeUnit.SECONDS.sleep(10);
        stringList = refreshAfterWriteCache.getUnchecked(key);
        log.info("stringList: {}", stringList);

        // 刷新新值
        refreshAfterWriteCache.put(key, Arrays.asList("value1", "value2", "value3"));
        stringList = refreshAfterWriteCache.getUnchecked(key);
        log.info("stringList: {}", stringList);
    }
}
