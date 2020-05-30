package com.xicheng.guava.t01_utilities;

import com.xicheng.guava.common.JoinerConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;

/**
 * description 使用jdk8实现joiner功能
 *
 * @author xichengxml
 * @date 2020-05-30 06:39
 */
@Slf4j
public class T02_Joiner {

    public static void main(String[] args) {
        String streamJoiner01 = JoinerConstant.VALUE_ARRAY_WITH_NULL.stream()
                .filter(item -> StringUtils.isNotBlank(item))
                .collect(Collectors.joining("|"));
        log.info("streamJoiner01: {}", streamJoiner01);

        String steamJoiner02 = JoinerConstant.VALUE_ARRAY_WITH_NULL.stream()
                .map(item -> StringUtils.isBlank(item) ? JoinerConstant.DEFAULT_VALUE : item)
                .collect(Collectors.joining("|"));
        log.info("streamJoiner02: {}", steamJoiner02);
    }
}
