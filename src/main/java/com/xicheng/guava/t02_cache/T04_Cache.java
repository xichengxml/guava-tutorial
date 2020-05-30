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
 * description 测试没有数据load的情况
 *
 * @author xichengxml
 * @date 2020-05-30 17:19
 */
@Slf4j
public class T04_Cache {

    private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheLoader<String, List<String>>() {
                @Override
                public List<String> load(String key) throws Exception {
                    return new ArrayList<>();
                }
            });

    public static void main(String[] args) throws Exception {
        List<String> stringList = cache.getUnchecked(CacheConstant.KEY);
        log.info("stringList: {}", stringList);
        cache.put(CacheConstant.KEY, Arrays.asList("value1", "value2"));

        TimeUnit.SECONDS.sleep(10);
        stringList = cache.getUnchecked(CacheConstant.KEY);
        log.info("stringList: {}", stringList);
    }
}
