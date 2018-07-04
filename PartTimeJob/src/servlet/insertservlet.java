package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import factory.Daofactory;
import sha256.sha_256;
import vo.User;

public class insertservlet extends HttpServlet{
     public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	 String username =new String( request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
    	 String password = request.getParameter("password");
    	 String repassword = request.getParameter("repassword");
    	 String email = request.getParameter("email");
    	 String phonenumber = request.getParameter("phonenumber");
    	 String kaptcha = request.getParameter("kaptcha");
    	 String yzm = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//         String Checkusername=(String) request.getSession().getAttribute("Checkusername");
    	 if(username == null 
    	    || "".equals(username)
    	    || username.length()<=2
    	    || username.length()>=17
    	    
    	    ||password==null 
    		|| "".equals(password) 
    		|| password.length()<=5
    		|| password.length()>=17
    		
    		||repassword==null 
    	    || "".equals(repassword)
    	    || repassword.length()<=5
    	    || repassword.length()>=17
    	    || !repassword.equals(password)) {
    		 request.setAttribute("error", "注册失败，账号或密码输入有误，请重新输入！");
    		 request.getRequestDispatcher("register.jsp").forward(request, response);
    	 }else if( checkemail(email)== false
    			 || checkphonenumber(phonenumber)== false ) {
    		 request.setAttribute("error", "注册失败，邮箱或手机号码输入有误，请重新输入！");
    		 request.getRequestDispatcher("register.jsp").forward(request, response);
    	 }else if(kaptcha==null
    			 ||kaptcha.equals("")
    			 ||kaptcha.length()<=3
    			 ||kaptcha.length()>=5
    			 ||!yzm.equalsIgnoreCase(kaptcha)){
    		 request.setAttribute("error", "验证码错误，注册失败");
    		 request.getRequestDispatcher("register.jsp").forward(request, response);
    	 }
//    	 else if(Checkusername.equals(username)){
//    		 request.setAttribute("error", "用户名重复，注册失败");
//    		// System.out.println("用户名重复");
//    		 request.getRequestDispatcher("register.jsp").forward(request, response);
//    	 }
    	 else{
    		 User user = new User();
    		 sha_256 sha = new sha_256();
    		 user.setUsername(username);
    		 user.setPassword(sha.getSHA256Strjava(password));
    		 user.setPhonenumber(phonenumber);
    		 user.setEmali(email);
    		 try {
				if(Daofactory.getUserDaoInstance().doCreate(user)) {
                    request.setAttribute("username",username);
					request.getRequestDispatcher("registerSuc.jsp").forward(request, response);
				 }else {
					 request.setAttribute("error", "账号，密码，邮箱或手机号码有误");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    	 }
     }
     public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	 this.doGet(request, response);
     }
     
     public static boolean checkemail(String email) {
    	 boolean flag = false;
    	 String pattern = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
    	 Pattern regex = Pattern.compile(pattern);
    	 Matcher matcher = regex.matcher(email);
    	 flag =matcher.matches();
		return flag;
		}
     
     public static boolean checkphonenumber(String phonenumber) {
    	 boolean flag =false;
    	 String pattern = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
    	 Pattern regex = Pattern.compile(pattern);
    	 Matcher matcher = regex.matcher(phonenumber);
    	 flag = matcher.matches();
		return flag;
		}
}
