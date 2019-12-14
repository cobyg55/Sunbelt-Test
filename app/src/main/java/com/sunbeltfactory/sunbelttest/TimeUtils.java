package com.sunbeltfactory.sunbelttest;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static Date stringToDate(String dateString) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault());
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            return formatter.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return new Date();
        }
    }

    public static String getTimeAgo(long time, Context ctx) {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        if (time < 1000000000000L) {
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }


        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return ctx.getString(R.string.timeFormatNow);
        } else if (diff < 2 * MINUTE_MILLIS) {
            return ctx.getString(R.string.timeFormatMinute);
        } else if (diff < 50 * MINUTE_MILLIS) {
            return ctx.getString(R.string.timeFormatMinutes, (diff / MINUTE_MILLIS));
        } else if (diff < 90 * MINUTE_MILLIS) {
            return ctx.getString(R.string.timeFormatHour);
        } else if (diff < 24 * HOUR_MILLIS) {
            return ctx.getString(R.string.timeFormatHours, (diff / HOUR_MILLIS));
        } else if (diff < 48 * HOUR_MILLIS) {
            return ctx.getString(R.string.timeFormatDay);
        } else if (diff < 96 * HOUR_MILLIS) {
            return ctx.getString(R.string.timeFormatDays, (diff / DAY_MILLIS));
        } else {
            return f.format(new Date(time));
        }
    }
}
