package com.xicheng.guava.t01_utilities;

import com.google.common.base.Splitter;
import com.xicheng.guava.common.SplitterConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-30 07:00
 */
@Slf4j
public class T03_Splitter {

    public static void main(String[] args) {
        // 根据特定分隔符分割字符串
        List<String> splitter01 = Splitter.on("#").splitToList(SplitterConstant.VALUE_STRING);
        log.info("splitter01: {}", splitter01);
        // 忽略空元素
        List<String> splitter02 = Splitter.on("#").omitEmptyStrings().splitToList(SplitterConstant.VALUE_STRING_WITH_NULL);
        log.info("splitter02: {}", splitter02);
        // 去除首尾空格
        List<String> splitter03 = Splitter.on("#").trimResults().splitToList(SplitterConstant.VALUE_STRING_WITH_SPACE);
        log.info("splitter03: {}", splitter03);
        // 按固定长度分割
        List<String> splitter04 = Splitter.fixedLength(4).splitToList(SplitterConstant.VALUE_STRING_FIX_LENGTH);
        log.info("splitter04", splitter04);
        // 截取分割结果
        List<String> splitter05 = Splitter.on("#").limit(2).splitToList(SplitterConstant.VALUE_STRING);
        log.info("splitter01: {}", splitter05);
        // 通过正则表达式分割
        List<String> splitter06 = Splitter.onPattern("\\#").splitToList(SplitterConstant.VALUE_STRING);
        log.info("splitter06: {}", splitter06);
        // 分割key-value格式
        Map<String, String> splitter07 = Splitter.on("#").withKeyValueSeparator("=").split(SplitterConstant.KEY_VALUE_STRING);
        log.info("splitter07: {}", splitter07);
    }
}
