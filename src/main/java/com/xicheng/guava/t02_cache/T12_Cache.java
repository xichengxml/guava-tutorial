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
 * description 测试同时使用expireAfterWrite和refreshAfterWrite
 *
 * @author xichengxml
 * @date 2020-06-02 15:52:03
 */
@Slf4j
public class T12_Cache {

	private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(5, TimeUnit.SECONDS)
			.refreshAfterWrite(3, TimeUnit.SECONDS)
			.removalListener(new CacheDataRemovalListener())
			.build(new CacheDataLoader());

	public static void main(String[] args) throws Exception{
		String key = CacheConstant.KEY;
		List<String> stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);

		// 等待4秒，看看refresh是否执行
		TimeUnit.SECONDS.sleep(4);

		stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);

		// 再等待2秒，总共6秒，看看expire是否执行; 因为第4秒触发了refresh, 所以此处不会触发过期
		TimeUnit.SECONDS.sleep(2);
		stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);

		// 再等待4秒，相当于写之后总共等待6秒, 此时再读取, 会触发expire
		TimeUnit.SECONDS.sleep(4);
		stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);
	}
}
