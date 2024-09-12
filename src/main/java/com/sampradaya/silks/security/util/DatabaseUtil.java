package com.thespot.bookings.security.util;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Sort;

public final class DatabaseUtil {
    private DatabaseUtil() {}
    public static Sort buildSortObject(String column) {
        if (StringUtils.isNotBlank(column)) {
            return Sort.by(column);
        }
        return Sort.by(Sort.Direction.DESC, "createdOn");
    }
}
