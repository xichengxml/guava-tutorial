package com.xicheng.guava.t01_utilities;

import com.xicheng.guava.common.PreconditionsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * description 使用objects或assert实现preconditons
 *
 * @author xichengxml
 * @date 2020-05-30 15:01
 */
@Slf4j
public class T05_Preconditions {

    public static void main(String[] args) {
        try {
            Objects.requireNonNull(PreconditionsConstant.NULL_VALUE);
        } catch (Exception e) {
            log.error("requireNonNull error: ", e);
        }

        try {
            assert StringUtils.isNotBlank(PreconditionsConstant.NULL_VALUE);
        } catch (Exception e) {
            log.error("assert error: ", e);
        }
    }
}
