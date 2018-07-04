package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Daofactory;
import vo.User;

public class findservlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String findname =new String(request.getParameter("findname").getBytes("ISO-8859-1"),"UTF-8");
		String findemail = request.getParameter("findemail");
		String findphonenumber = request.getParameter("findphonenumber");
		String findyzm = request.getParameter("findyzm");
		String kaptcha = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//		System.out.println("find:"+findname+findemail+findphonenumber+findyzm);
		User user =new User();
		
		if(findname == null 
				||"".equals(findname) 
				|| findname.length()<=2
				|| findname.length()>=17
				|| checkemail(findemail)==false
				|| checkphonenumber(findphonenumber)==false
				) {
			request.setAttribute("finderror", "输入的信息有误，请重新输入！");
			request.getRequestDispatcher("findword.jsp").forward(request, response);
		}else if(findyzm ==null
				||findyzm.equals("")
				||findyzm.length()<=3
				||findyzm.length()>=5
				||!findyzm.equalsIgnoreCase(kaptcha)) {
			request.setAttribute("finderror", "输入的验证码有误，请重新输入！");
			request.getRequestDispatcher("findword.jsp").forward(request, response);
		}else {
			user.setUsername(findname);
			user.setEmali(findemail);
			user.setPhonenumber(findphonenumber);
			try {
				if(Daofactory.getUserDaoInstance().findword(user)) {
					request.getSession().setAttribute("findname",new String(findname.getBytes("ISO-8859-1"),"UTF-8"));
					request.getRequestDispatcher("change.jsp").forward(request, response);
				}else {
					request.setAttribute("finderror", "输入的信息有误，请重新输入！");
					request.getRequestDispatcher("findword.jsp").forward(request, response);
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
