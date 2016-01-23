package com.framework.helix.util;

import java.text.SimpleDateFormat;

/**
 * Created by nuwan on 7/7/15.
 */
public class DateUtil {
    public final static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(Constant.DATE_FORMAT);
    }
}
