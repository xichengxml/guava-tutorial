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
 * description 对比定时回收机制expireAfterAccess
 *
 * @author xichengxml
 * @date 2020-05-30 18:48
 */
@Slf4j
public class T07_Cache {

    /**
     * 缓存项在给定时间内没有被写访问（创建或覆盖），则回收
     * once a fixed duration has elapsed after the entry's creation, the most recent replacement of its value,
     * or its last access
     */
    private static final LoadingCache<String, List<String>> expireAfterAccessCache = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheDataLoader());

    public static void main(String[] args) throws Exception {
        String key = CacheConstant.KEY;

        // 每秒读取一次，不会触发回收
        for (int i = 0; i < 10; i++) {
            List<String> stringList = expireAfterAccessCache.getUnchecked(key);
            log.info("stringList: {}, {}", i, stringList);
            TimeUnit.SECONDS.sleep(1);
        }

        // 触发超时回收，因为每次读取时候accessTime重置，因此4秒后（加上上面的1秒实际是5秒)后触发回收
        TimeUnit.SECONDS.sleep(4);
        List<String> stringList = expireAfterAccessCache.getUnchecked(key);
        log.info("stringList: {}", stringList);
    }
}
