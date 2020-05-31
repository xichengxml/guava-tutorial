package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataLoader;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description 显式清除
 *
 * @author xichengxml
 * @date 2020-05-30 19:02
 */
@Slf4j
public class T09_Cache {

    private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheDataLoader());

    public static void main(String[] args) {
        String key = CacheConstant.KEY;
        for (int i = 0; i < 10; i++) {
            List<String> stringList = cache.getUnchecked(key + i);
            log.info("stringList: {}", stringList);
        }

        // 可以通过removeListener日志看到key情况
        cache.invalidate(key + 0);
        cache.invalidateAll(Arrays.asList(key + 1, key + 2, key + 3));
        cache.invalidateAll();
    }
}
