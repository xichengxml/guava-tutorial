package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * description 测试load
 *
 * @author xichengxml
 * @date 2020-05-30 16:24
 */
@Slf4j
public class T01_Cache {

    private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
            .build(new CacheDataLoader());

    public static void main(String[] args) {
        List<String> keys = cache.getUnchecked(CacheConstant.KEY);
        log.info("keys: {}", keys);
    }
}
