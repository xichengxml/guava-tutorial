package com.xicheng.guava.helper;

import com.google.common.cache.CacheLoader;
import com.xicheng.guava.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-30 16:45
 */
@Slf4j
public class CacheDataLoader extends CacheLoader<String, List<String>> {
    @Override
    public List<String> load(String key) throws Exception {
        log.info("load from db, key: {}", key);
        return CacheUtil.getListFromDb(key);
    }
}
