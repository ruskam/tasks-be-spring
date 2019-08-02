package com.lublin.groceriesjavaspringbe.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static Date getCorrectTime(){
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Warsaw");
        //TimeZone timeZone = TimeZone.getDefault();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        return calendar.getTime();
    }

}
