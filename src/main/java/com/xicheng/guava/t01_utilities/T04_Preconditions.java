package com.xicheng.guava.t01_utilities;

import com.google.common.base.Preconditions;
import com.xicheng.guava.common.PreconditionsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-05-30 07:30
 */
@Slf4j
public class T04_Preconditions {

    public static void main(String[] args) {
        try {
            Preconditions.checkNotNull(PreconditionsConstant.NULL_VALUE);
        } catch (Exception e) {
            log.error("value null: ", e);
        }

        try {
            Preconditions.checkNotNull(PreconditionsConstant.NULL_VALUE, "value should not be null");
        } catch (Exception e) {
            log.error("value null: ", e);
        }

        try {
            Preconditions.checkArgument(StringUtils.isBlank(PreconditionsConstant.NULL_VALUE));
        } catch (Exception e) {
            log.error("checkArgument error: ", e);
        }

        try {
            Preconditions.checkElementIndex(2, PreconditionsConstant.VALUE_LIST.size(), "list size should be 3");
        } catch (Exception e) {
            log.error("checkElementIndex error: ", e);
        }

        try {
            Preconditions.checkState(PreconditionsConstant.STATUS.matches("ON"), "state should be on");
        } catch (Exception e) {
            log.error("checkState error: ", e);
        }

    }
}
