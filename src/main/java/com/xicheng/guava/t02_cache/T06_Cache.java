package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataLoader;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import com.xicheng.guava.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description 对比定时回收的机制: expireAfterWrite
 *
 * @author xichengxml
 * @date 2020-05-30 17:56
 */
@Slf4j
public class T06_Cache {

    /**
     * 缓存项在给定时间内没有被写访问，则回收; 即使有读访问也会回收
     * once a fixed duration has elapsed after the entry's creation, or the most recent replacement of its value
     */
    private static final LoadingCache<String, List<String>> expireAfterWriteCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheDataLoader());

    public static void main(String[] args) throws Exception {
        String key = CacheConstant.KEY;

        // 每过一秒读取一次，也会回收
        for (int i = 0; i < 6; i++) {
            List<String> stringList = expireAfterWriteCache.getUnchecked(key);
            log.info("stringList: {}, {}", i, stringList);
            TimeUnit.SECONDS.sleep(1);
        }

        // 第3秒的时候写入一次, 加上上面的1秒超时，相当于在缓存重载后第4秒覆盖; 第7秒去读，不会回收
        TimeUnit.SECONDS.sleep(3);
        expireAfterWriteCache.put(key, CacheUtil.getListFromDb(key));
        TimeUnit.SECONDS.sleep(4);
        List<String> stringList = expireAfterWriteCache.getUnchecked(key);
        log.info("stringList: {}", stringList);
    }
}
