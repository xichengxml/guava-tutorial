package com.xicheng.guava.helper;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-30 16:42
 */
@Slf4j
public class CacheDataRemovalListener implements RemovalListener<String, List<String>> {
    @Override
    public void onRemoval(RemovalNotification<String, List<String>> notification) {
        log.info("remove, key: {}, value: {}, cause: {}", notification.getKey(), notification.getValue(), notification.getCause());
    }
}
