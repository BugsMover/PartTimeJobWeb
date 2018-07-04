package cn.nicecoder.util;

/**
 * 自定义el表达式
 *-------------------------------
 * @author longtian
 * @date 2018年4月12日下午9:49:56
 * @description nicecoder.cn
 *-------------------------------
 */
public class UFunction {
	/**
	 * 转换格式"年-月-日"
	 * @param dateTime
	 * @return
	 */
	public static String formatDate(String dateTime){
		if(StringUtil.isNotEmpty(dateTime)){
			return dateTime.substring(0,4) + "-" +dateTime.substring(4,6) + "-" + dateTime.substring(6,8); 
		}
		return dateTime;
	}
	
	/**
	 * 转换格式"月日"
	 * @param dateTime
	 * @return
	 */
	public static String formatMD(String dateTime){
		if(StringUtil.isNotEmpty(dateTime)){
			String mon = dateTime.substring(4,5).equals("0") ? dateTime.substring(5,6) : dateTime.substring(4,6);
			String day = dateTime.substring(6,7).equals("0") ? dateTime.substring(7,8) : dateTime.substring(6,8);
			return mon + "月" + day + "日"; 
		}
		return dateTime;
	}
	
	/**
	 * 转换格式"年-月-日 时:分:秒"
	 * @param dateTime
	 * @return
	 */
	public static String formatDateTime(String dateTime){
		if(StringUtil.isNotEmpty(dateTime)){
			return dateTime.substring(0,4) + "-" +dateTime.substring(4,6) + "-" + dateTime.substring(6,8) + " " 
					+ dateTime.substring(8,10) + ":" + dateTime.substring(10,12) + ":" + dateTime.substring(12,14); 
		}
		return dateTime;
	}
}
