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
 * description 测试不配置过期时间的情况
 *
 * @author xichengxml
 * @date 2020-06-11 17:53:56
 */
@Slf4j
public class T13_Cache {

	private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
			.removalListener(new CacheDataRemovalListener())
			.build(new CacheDataLoader());

	public static void main(String[] args) throws Exception {
		String key = CacheConstant.KEY;
		List<String> stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);

		TimeUnit.SECONDS.sleep(100);
		stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);
	}
}
