package com.zli.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
  public static Date stringToDate(String time) {
    time = time.replace("12", "00");
    Date date = null;
    SimpleDateFormat format = new SimpleDateFormat("K:mma");
    try {
      date = format.parse(time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public static String dateToString(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(date);
  }

  public static Date addHour(Date origin, int hour) {
    return new Date(origin.getTime() + hour * Value.HOUR);
  }

  public static int substractMinute(Date start, Date end) {
    return (int) ((end.getTime() - start.getTime()) / Value.MINUTE);
  }

  private Time() {}
}
