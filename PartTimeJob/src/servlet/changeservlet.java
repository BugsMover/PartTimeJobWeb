package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Daofactory;
import sha256.sha_256;
import vo.User;

public class changeservlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		User user = new User();
		String changepassword = request.getParameter("changepassword");
		String rechangepassword = request.getParameter("rechangepassword");
		String findname =  (String) request.getSession().getAttribute("findname");
		sha_256 sha= new sha_256();
//		System.out.println("change:"+changepassword+rechangepassword+findname);
		
		if(changepassword==null
				|| changepassword.equals("")
				|| changepassword.length()<=5
				|| changepassword.length()>=17
				
				|| rechangepassword ==null
				|| rechangepassword.equals("")
				|| rechangepassword.length()<=5
				|| rechangepassword.length()>=17
				
				|| !rechangepassword.equals(changepassword)) {
			request.setAttribute("changeerror", "输入的密码和确认密码不一致或者不符合条件，请重新输入！");
			request.getRequestDispatcher("change.jsp").forward(request, response);
		}else {
			user.setUsername(new String(findname.getBytes("UTF-8"),"ISO-8859-1"));
			user.setPassword(sha.getSHA256Strjava(changepassword));
			try {
				if(Daofactory.getUserDaoInstance().changeword(user)) {
				   request.setAttribute("findname",findname);
				   request.getRequestDispatcher("changeSuc.jsp").forward(request, response);
				   request.getSession().removeAttribute("findname");
				}else {
					request.setAttribute("changeerror", "新的密码修改失败，请稍后再次尝试！");
					request.getRequestDispatcher("change.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		this.doGet(request, response);
	}

}
