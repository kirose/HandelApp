package com.handel.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Marco Antonio on 15/03/2018.
 */
public class Converters {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
