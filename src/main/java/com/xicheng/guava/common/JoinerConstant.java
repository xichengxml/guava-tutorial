package com.xicheng.guava.common;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-30 06:40
 */
public interface JoinerConstant {

    List<String> VALUE_ARRAY = Arrays.asList("value1", "value2", "value3", "value4");

    List<String> VALUE_ARRAY_WITH_NULL = Arrays.asList("value1", "value2", null, null);

    String DEFAULT_VALUE = "value0";

    Map<String, String> VALUE_MAP = ImmutableMap.of("key01", "value01", "key02", "value02");
}
