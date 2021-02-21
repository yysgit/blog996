package com.yys.szcp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间处理类
 * @author Mr Du
 *
 */
public class DateUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    /**
     * Format String : yyyy-MM-dd HH:mm:ss
     */
    public static final String DateFormat1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format String : yyyy-MM-dd
     */
    public static final String DateFormat2 = "yyyy-MM-dd";

    /**
     * Format String : yyyyMMdd
     */
    public static final String DateFormat3 = "yyyyMMdd";

    /**
     * Format String : yyyyMMdd HHmmss
     */
    public static final String DateFormat4 = "yyyyMMdd HHmmss";

    /**
     * Format String : yyyy-MM-dd HH:mm
     */
    public static final String DateFormat5 = "yyyy-MM-dd HH:mm";

    /**
     * Format String : yyyyMMdd HH:mm
     */
    public static final String DateFormat6 = "yyyyMMdd HH:mm";

    
    /**
     * Format String : yyyy年MM月dd日
     */
    public static final String DateFormat7 = "yyyy年MM月dd日";
    
    /**
     * 获取当前时间
     * 
     * @return Date对象
     */
    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 返回当前时间
     * 
     * @param format 时间格式
     * @return string 当前时间指定格式字符串
     */
    public static String getDate(String format) {
        return getStringDate(getDate(), format);
    }

    /**
     * 按照固定格式化
     * 
     * @param date Date
     * @param method 时间格式
     * @return 制定的时间格式
     */
    public static String getStringDate(Date date, String method) {
        SimpleDateFormat sdf = new SimpleDateFormat(method);
        String ret = null;
        try {
            ret = sdf.format(date);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return ret;
    }

    /**
     * 获取前几天或者后天时间
     * 
     * @param date Date
     * @param days 天数
     * @return Date时间
     */
    public static Date getDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 获取前几天或者后天时间
     * 
     * @param dateStr 'yyyyMMdd'
     * @param days 天数
     * @return Date时间
     */
    public static Date getDate(String dateStr, int days) {
        return getDate(getDate(dateStr, DateFormat3), days);
    }

    /**
     * 传入String类型时间返回Date
     * 
     * @param stringDate 时间
     * @param method 格式
     * @return 返回Date
     */
    public static Date getDate(String stringDate, String method) {
        SimpleDateFormat sdf = new SimpleDateFormat(method);
        Date ret = null;
        try {
            String integerDate = stringDate.replaceAll("-", "").replaceAll("/", "").replaceAll("年", "").replaceAll("月", "").replaceAll("日", "").replaceAll("：", ":");
            ret = sdf.parse(integerDate);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return ret;
    }

    /**
     * 获取两时间差的天数
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 天数
     */
    public static int getDayCount(Date beginDate, Date endDate) {
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        while (calendar.getTime().before(endDate)) {
            count++;
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return count;
    }

    /**
     * 当前日期的前一个月
     * 
     * @param data
     * @return
     */
    public static String getLastMonth(Date data) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        date = calendar.getTime();
        return DateUtil.getStringDate(date, DateUtil.DateFormat2);
    }

    /**
     * 当前日期的后一个月
     * 
     * @param data
     * @return
     */
    public static String getNextMonth(Date data) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        date = calendar.getTime();
        return DateUtil.getStringDate(date, DateUtil.DateFormat2);
    }
    
    /**
     * 传入LONG 返回 时分秒
     * @param diff
     * @return
     */
    public static String LongToString(long diff)
    {
        String showtime = "";
        long oneSecond = 1000;
        long oneMinute = oneSecond * 60;
        long oneHour = oneMinute * 60;
        long hours = diff / oneHour;
        diff -= hours * oneHour;
        long minutes = diff / oneMinute;
        diff -= minutes * oneMinute;
        long seconds = diff / oneSecond;
        if (hours > 0) showtime += hours + "时";
        if (minutes > 0) showtime += minutes + "分";
        if (seconds > 0) showtime += seconds + "秒";
        return showtime;
    }
    /**
     * 数据库时间格式化
     * @param date
     * @return
     */
   public static String DateFormat(String date) throws ParseException {
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
       Date d = formatter.parse(date);
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String sDate=sdf.format(date);
       return  sDate;
   }
    /**
     * @Author zhangxiaofeng
     * @Description //TODO 获取当前时间的0点
     * @Date  2019/7/16 17:27
     * @Param []
     * @return java.lang.String
     **/
    public static String getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date beginOfDate = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(beginOfDate);
    }
    /**
     * @Author zhangxiaofeng
     * @Description //TODO 获取当前时间的24点
     * @Date  2019/7/16 17:27
     * @Param []
     * @return java.lang.String
     **/
    public static String getTimesNight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        Date beginOfDate = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(beginOfDate);
    }

    /**
     * 判断选择的日期本月的第几天
     */
    public static int getWeeksByChooseDay(){
        Calendar cal = Calendar.getInstance();
        return cal.get( Calendar.DAY_OF_MONTH);
    }
    /**
     * 获取当前年份
     */
    public static Integer getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return Integer.valueOf( sdf.format(date));
    }

    /**
     * 获取当前月份
     */
    public static Integer getCurrentMonth(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

}
