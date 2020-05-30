package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import com.xicheng.guava.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * description 测试Callable
 * 这个方法简便地实现了模式"如果有缓存则返回；否则运算、缓存、然后返回"
 *
 * @author xichengxml
 * @date 2020-05-30 17:19
 */
@Slf4j
public class T05_Cache {

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
        String key = CacheConstant.KEY;
        List<String> stringList = cache.get(key, new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return CacheUtil.getListFromDb(key);
            }
        });
        log.info("stringList: {}", stringList);
    }
}
