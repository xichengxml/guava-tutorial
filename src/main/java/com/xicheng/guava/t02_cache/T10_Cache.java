package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataLoader;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * description 自定义线程清理过期key
 * 清理没好使，不知道为啥; 都是走的自动过期
 *
 * @author xichengxml
 * @date 2020-05-31 10:05
 */
@Slf4j
public class T10_Cache {

    private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheDataLoader());

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws Exception {
        Runnable cacheCleanThread = cache::cleanUp;

        String key = CacheConstant.KEY;
        List<String> stringList = cache.getUnchecked(key);
        log.info("stringList: {}", stringList);

        // 启动一次清理线程，5秒后启动
        scheduler.schedule(cacheCleanThread, 5, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);

        cache.getUnchecked(key);
        log.info("finish");
    }

}
