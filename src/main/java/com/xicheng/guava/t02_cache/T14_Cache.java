package com.xicheng.guava.t02_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.xicheng.guava.common.CacheConstant;
import com.xicheng.guava.helper.CacheDataRemovalListener;
import com.xicheng.guava.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description 测试刷新的时候调用的方法是什么，以及什么时候调用reload
 * 自动过期时，如果有reload, 调用reload，否则调用load
 *
 * @author xichengxml
 * @date 2020-06-11 18:03:04
 */
@Slf4j
public class T14_Cache {

	private static final ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));

	private static final LoadingCache<String, List<String>> cache = CacheBuilder.newBuilder()
			.refreshAfterWrite(5, TimeUnit.SECONDS)
			.removalListener(new CacheDataRemovalListener())
			.build(new CacheLoader<String, List<String>>() {
				@Override
				public List<String> load(String key) throws Exception {
					log.info("load from db, key: {}", key);
					return CacheUtil.getListFromDb(key);
				}

				@Override
				public ListenableFuture<List<String>> reload(String key, List<String> oldValue) throws Exception {
					return executor.submit(new Callable<List<String>>() {
						@Override
						public List<String> call() throws Exception {
							log.info("reload from db, key: {}", key);
							return CacheUtil.getListFromDb(key);
						}
					});
				}
			});

	public static void main(String[] args) throws Exception {
		String key = CacheConstant.KEY;
		List<String> stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);

		TimeUnit.SECONDS.sleep(10);

		stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);

		cache.refresh(key);
		stringList = cache.getUnchecked(key);
		log.info("stringList: {}", stringList);
	}
}
