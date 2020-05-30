package com.xicheng.guava.t01_utilities;

import com.google.common.base.Joiner;
import com.xicheng.guava.common.JoinerConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * description guava joiner用法
 *
 * @author xichengxml
 * @date 2020-05-30 06:12
 */
@Slf4j
public class T01_Joiner {

    private static final String FILE_NAME = "files\\joiner.txt";



    public static void main(String[] args) {
        // 使用自定义分割符连接元素
        String joiner01 = Joiner.on("|").join(JoinerConstant.VALUE_ARRAY);
        log.info("joiner01: {}", joiner01);
        // 支持空值的用法
        String joiner02 = Joiner.on("|").skipNulls().join(JoinerConstant.VALUE_ARRAY_WITH_NULL);
        log.info("joiner02: {}", joiner02);
        // 空值替换为默认值
        String joiner03 = Joiner.on("|").useForNull(JoinerConstant.DEFAULT_VALUE).join(JoinerConstant.VALUE_ARRAY_WITH_NULL);
        log.info("joiner03: {}", joiner03);
        // 将数据append到StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder joiner04 = Joiner.on("|").appendTo(stringBuilder, JoinerConstant.VALUE_ARRAY);
        log.info("equals: {}", joiner04.toString().equals(stringBuilder.toString()));
        // 将数据append到FileWriter
        try (FileWriter fileWriter = new FileWriter(new File(FILE_NAME))) {
            FileWriter joiner05 = Joiner.on("|").appendTo(fileWriter, JoinerConstant.VALUE_ARRAY);
            log.info("equals: {}", joiner05.equals(fileWriter));
        } catch (IOException e) {
            log.error("append to file error: ", e);
        }
        // 拼接map的key-value
        String joiner06 = Joiner.on("|").withKeyValueSeparator("=").join(JoinerConstant.VALUE_MAP);
        log.info("joiner06: {}", joiner06);
    }
}
