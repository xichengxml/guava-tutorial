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
 * description 测试时间过期
 * 通过测试发现：只有显式去获取过期值的时候才会触发过期删除机制，所以移除操作是在第10秒触发的；如果显式获取的不是已过期的值，则不会触发过期删除
 *
 * @author xichengxml
 * @date 2020-05-30 16:54
 */
@Slf4j
public class T03_Cache {

    private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(new CacheDataRemovalListener())
            .build(new CacheDataLoader());

    public static void main(String[] args) throws Exception {
        List<String> stringList01 = cache.getUnchecked(CacheConstant.KEY + 1);
        cache.getUnchecked(CacheConstant.KEY + 2);
        log.info("stringList01: {}", stringList01);

        TimeUnit.SECONDS.sleep(10);

        // key不存在与cache中，因此不触发过期删除机制
        cache.getUnchecked(CacheConstant.KEY);
        log.info("stringList: {}", stringList01);
        // 触发删除机制，删除key1和key2
        stringList01 = cache.getUnchecked(CacheConstant.KEY + 1);
        log.info("stringList01: {}", stringList01);
    }
}
