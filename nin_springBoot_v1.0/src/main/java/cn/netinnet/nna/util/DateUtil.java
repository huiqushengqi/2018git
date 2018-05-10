package cn.netinnet.nna.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @ClassName:    DateUtil
 * @Description:  时间工具类
 *
 * @author         chen.wb
 * @version        V1.0
 * @Date           2017-07-24
 *
 **/
public class DateUtil {

	/**
	 * 生成ISO-8601 规范的时间格式
	 * @param date
	 * @return
	 */
	public static String formatISO8601DateString(Date date){
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		return  DateFormatUtils.format(date, pattern);
	}

    public static String yyyymmddHHMMssSSS_23(Date rightNow)
    {
        //Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyymmddHHMMssSSS_23()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyymmddHHMMss_14()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyy_mm_dd_chinese_14()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyy_mm_dd_10(Date rightNow)
    {
        //Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyy_mm_dd_10()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyymmdd_8(Date rightNow)
    {
        //Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyymmdd_8()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");//年月日
        return (String) formatter.format(rightNow);
    }
    public static String yyyy()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");//年天，秒，年，小时，毫秒，月
        return (String) formatter.format(rightNow);
    }

    public static String mm()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");//年天，秒，年，小时，毫秒，月
        return (String) formatter.format(rightNow);
    }
    public static String dd()
    {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");//年天，秒，年，小时，毫秒，月
        return (String) formatter.format(rightNow);
    }
    /**
     * 判断当前日期是否在某个日期之后
     * @param arg_date_yyyymmdd
     * @return
     */
    public static boolean isDateAfter_yyyymmdd8(String arg_date_yyyymmdd)
    {
        String v_today = DateUtil.yyyymmdd_8();
        if (v_today.compareTo(arg_date_yyyymmdd)>=0)
            return true;
        else
            return false;
    }
    public static boolean isDateBefore_yyyymmdd8(String arg_date_yyyymmdd)
    {
        String v_today = DateUtil.yyyymmdd_8();
        if (v_today.compareTo(arg_date_yyyymmdd)<=0)
            return true;
        else
            return false;
    }

    /**
     * 返回 今天 日期 加 几天 的日期
     * @param arg_date
     * @return
     */
    public static Date today_add(int arg_date)
    {
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.DATE ,  arg_date);
        return cal.getTime();
    }
    /**
     * 返回 今天 日期 加 几天 的日期
     * @param arg_date
     * @return
     */
    public static String yyyy_mm_dd_10_after_today(int arg_date)
    {
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.DATE ,  arg_date);
        return DateUtil.yyyy_mm_dd_10(cal.getTime());
    }
    /**
     * 返回 今天 日期 加 几天 的日期
     * @param arg_date
     * @return
     */
    public static String yyyymmdd_8_after_today(int arg_date)
    {
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.DATE ,  arg_date);
        return DateUtil.yyyymmdd_8(cal.getTime());
    }

}
