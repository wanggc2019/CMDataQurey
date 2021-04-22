package com.wgc.CMDataQurey.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
  public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'+08:00'");
  
  public static Integer SS = 1000;
  
  public static Integer MI = SS * 60;
  
  public static Integer HH = MI * 60;
  
  public static Integer DD = HH * 24;
  
  public static Date getDayOffsetDate(Date protoDate, int dayOffset) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(protoDate);
    cal.add(6, -dayOffset);
    return cal.getTime();
  }
  
  public static Date getHourOffsetDate(Date protoDate, int hourOffset) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(protoDate);
    cal.add(11, -hourOffset);
    return cal.getTime();
  }
  
  public static String formatDate(Date date) {
    return (date == null) ? null : SDF.format(date);
  }
  
  public static String calculatDurationByms(Long ms) {
    Long day = ms / DD;
    Long hour = (ms - day * DD) / HH;
    Long minute = (ms - day * DD - hour * HH) / MI;
    Long second = (ms - day * DD - hour * HH - minute * MI) / SS;
    Long milliSecond = ms - day * DD - hour * HH - minute * MI - second * SS;
    StringBuffer sb = new StringBuffer();
    if (day > 0L)
      sb.append(day).append("天");
    if (hour > 0L)
      sb.append(hour).append("小时");
    if (minute > 0L)
      sb.append(minute).append("分");
    if (second > 0L)
      sb.append(second).append("秒");
    if (milliSecond > 0L)
      sb.append(milliSecond).append("毫秒");
    return sb.toString();
  }
}
