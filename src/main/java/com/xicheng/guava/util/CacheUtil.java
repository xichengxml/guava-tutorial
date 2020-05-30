package com.xicheng.guava.util;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-30 16:40
 */
public class CacheUtil {

    /**
     * 模拟从数据库获取数据
     * @param key
     * @return
     */
    public static List<String> getListFromDb(String key) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(key + "_" + i);
        }
        return result;
    }
}
