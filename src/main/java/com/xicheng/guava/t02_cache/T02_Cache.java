package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataLoader;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description 测试size移除
 *
 * @author xichengxml
 * @date 2020-05-30 16:42
 */
@Slf4j
public class T02_Cache {

    private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
            .maximumSize(5)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheDataLoader());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String key = CacheConstant.KEY + i;
            List<String> stringList = cache.getUnchecked(key);
            log.info("stringList: {}, {}", i, stringList);
        }
    }

}
