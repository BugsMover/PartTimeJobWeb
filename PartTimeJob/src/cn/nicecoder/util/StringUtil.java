package cn.nicecoder.util;

/**
 * 字符串工具类
 *-------------------------------
 * @author longtian
 * @date 2018年4月12日下午9:49:56
 * @description nicecoder.cn
 *-------------------------------
 */
public class StringUtil {
	public static boolean isNotEmpty(String str){
		if(str == null || "".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
	//长度控制
	public static String lengthControl(String str, int len){
		String result = "";
		if(str.length() < len){
			result = str;
		}else{
			result = str.substring(0, len) + "...";
		}
		return result;
	}
	
	//方法一
	public String stripHtml(String content) { 
	    // <p>段落替换为换行 
	    content = content.replaceAll("<p .*?>", "\r\n"); 
	    // <br><br/>替换为换行 
	    content = content.replaceAll("<br\\s*/?>", "\r\n"); 
	    // 去掉其它的<>之间的东西 
	    content = content.replaceAll("\\<.*?>", ""); 
	    // 去掉空格 
	    content = content.replaceAll(" ", ""); 
	    return content;   
	}

	//方法二
	public static String delHtmlTag(String str){ 
	    String newstr = ""; 
	    newstr = str.replaceAll("<[.[^>]]*>","");
	    newstr = newstr.replaceAll(" ", ""); 
	    return newstr;
	}
}
