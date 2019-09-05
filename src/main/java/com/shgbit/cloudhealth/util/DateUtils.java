package com.shgbit.cloudhealth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat();


    public static SimpleDateFormat getDateFormat(String pattern) {
        //"yyyy-MM-dd HH:mm:ss"
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    // 2018-06-15T05:55:00.000Z
    public static Date getDate(String timeString) {
        SimpleDateFormat format;
        if (timeString.contains("Z")) {
            timeString = timeString.replace("Z", " UTC");
            dateFormat.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        } else if(timeString.contains(":")) {
            dateFormat.applyPattern("yyyy-MM-dd HH:mm");
        }else{
            dateFormat.applyPattern("yyyy-MM-dd");
        }
        Date date = null;
        try {
            date = dateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
