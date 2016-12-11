package cn.crap.common.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 根据输入的字符串，日期，日期格式返回相应的日期字符串
 * @author TangTao
 *
 */
public class DateUtil {
	//yyyyMMddHHmmSS
	//S毫秒
	//s秒
	public static String YYYY_MM_DD = "yyyy-MM-dd";
	public static String DDHH="ddHH";
	public static String YYYY_MM_DD_HH_mm_ss="yyyy-MM-dd HH:mm:ss";
	public static String YYYY_MM_DD_HH_mm="yyyy-MM-dd HH:mm";
	public static String YYYYMMDDHHmmss="yyyyMMddHHmmss";
	public static String HHmmss="HHmmss";
	public static String CHINA_YYYY_MM_DD="yyyy年MM月dd日";
	public static SimpleDateFormat YYYY_MM_DD_HH_mm_SDF=new SimpleDateFormat(YYYY_MM_DD_HH_mm);
	public static boolean isWeekend(String date){
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(YYYY_MM_DD);
		Date myDate1=null;
		try {
			myDate1 = dateFormat1.parse(date);
		} catch (ParseException e) {
			return false;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(myDate1);
		if(Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)||Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)){
			return true;
		}else
			return false;
		
	}

	public static String getDateByFormat()
	{
		return getDateByFormat(new Date(),YYYY_MM_DD);
	}
	public static String getDateByFormatAddOneHour()
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		return getDateByFormat(c.getTime(),"DDHH");
	}
	public static String getDateByFormatAddOneDay(String date)
	{
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(YYYY_MM_DD);
		Date myDate1=null;
		try {
			myDate1 = dateFormat1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(myDate1);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return getDateByFormat(c.getTime(),YYYY_MM_DD);
	}
	public static long getCurrentTimeMillis(String date,String format)
	{
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(format);
		Date myDate1=null;
		try {
			myDate1 = dateFormat1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(myDate1);
		
		return c.getTimeInMillis();
	}
	public static long getCurrentTimeMillis(String date)
	{
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
		Date myDate1=null;
		try {
			myDate1 = dateFormat1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(myDate1);
		
		return c.getTimeInMillis();
	}
	
	public static String getDateByFormat(String date,String fromat)
	{
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
		Date myDate1=null;
		try {
			myDate1 = dateFormat1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getDateByFormat(myDate1,fromat);
	}
	public static String getDateByFormat(String date,String inFormat,String outFormat)
	{
		SimpleDateFormat dateInFormat = new SimpleDateFormat(inFormat);
		Date inDate=null;
		try {
			inDate = dateInFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getDateByFormat(inDate,outFormat);
	}

	
	public static String getDateByFormatDecOneDay(String date)
	{
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(YYYY_MM_DD);
		Date myDate1=null;
		try {
			myDate1 = dateFormat1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(myDate1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return getDateByFormat(c.getTime(),YYYY_MM_DD);
	}
	public static String getChinaDate(String date){
		date=date.substring(5,date.length());
		date=date.replace("-", "月");
		date=date+"日";
		return date;
	}
	public static String getDateByTimeMillis(String str,String sformat){
		try{
		  SimpleDateFormat format = new SimpleDateFormat(sformat);
	      Long time=Long.valueOf(str);
	      String d = format.format(time);
	      return d;
		}catch(Exception e){
			return "";
		}
	}
	public static String getDateByFormat(String daf){
		return getDateByFormat(new Date(),daf);
	}
	public static String getDateByFormat(SimpleDateFormat daf){
		return   getDateByFormat(new Date(),daf);
	}
	public static String getDateByFormat(Date date,String daf){
		SimpleDateFormat sdFormat = new SimpleDateFormat();
		return getDateByFormat(date,sdFormat);
	}
	public static String getDateByFormat(Date date,SimpleDateFormat daf){
		String dateStr=daf.format(date);
		return  dateStr;
	}
	public static String getDateByTimeMillis(Long str){
		return getDateByTimeMillis(str.toString(),YYYY_MM_DD_HH_mm);
	}
	
	
	/**
	 * @Description 缓存值第2天某时某分某秒所剩时间
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @return 时间（s）
	 */
	public static Long getActiveTime(int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);

		long milliseconds = calendar.getTimeInMillis() - System.currentTimeMillis();
		return milliseconds / 1000;
	}
	
	
	public static String getRelativeTime(int hour,int minute,int second) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }
	
}
