package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataLoader;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-31 11:24
 */@Slf4j
public class T11_Cache {

    private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .recordStats()
            .build(new CacheDataLoader());

    public static void main(String[] args) throws Exception {
        String key = CacheConstant.KEY;

        for (int i = 0; i < 10; i++) {
            cache.getUnchecked(key + i);
        }

        TimeUnit.SECONDS.sleep(10);

        for (int i = 0; i < 10; i++) {
            cache.getUnchecked(key + i);
        }

        CacheStats stats = cache.stats();
        double hitRate = stats.hitRate();
        double averageLoadPenalty = stats.averageLoadPenalty();
        long evictionCount = stats.evictionCount();
        log.info("stats, hitRate: {}, averageLoadPenalty: {}, evictionCount: {}", hitRate, averageLoadPenalty, evictionCount);
    }
}
