package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Daofactory;
import sha256.sha_256;
import vo.User;


public class loginservlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	String name =new String( request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
    	String pass = request.getParameter("pass");
    	String kaptcha = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
    	String yzminput = request.getParameter("yzminput");
    	User user = new User();
 		sha_256 sha = new sha_256();

     	if(yzminput == null 
     			|| !yzminput.equalsIgnoreCase(kaptcha)
     			||yzminput.length()<=3
     			||yzminput.length()>=5) {
     		request.setAttribute("name", name);
     		request.setAttribute("error","登陆失败，验证码错误");
 			request.getRequestDispatcher("login2.jsp").forward(request,response);
     	}else if(name == null 
     			|| "".equals(name)
     			|| name.length()<=2
     			|| name.length()>=17
     			||pass == null 
     			|| "".equals(pass)
     			|| pass.length()<=4
     			||pass.length()>=17) {
     		request.setAttribute("name", name);
     		request.setAttribute("error","登录失败，密码或账号错误");
 			request.getRequestDispatcher("login2.jsp").forward(request,response);
     	}else {		
      		user.setUsername(name);
     		user.setPassword(sha.getSHA256Strjava(pass));
     		try {
 				if(Daofactory.getUserDaoInstance().findLogin(user)) {
 					request.getSession().setAttribute("name",name);
 					request.setAttribute("name", name);
 					request.getRequestDispatcher("coreServlet?bizCode=7").forward(request, response);
 				}else {
 					//登录失败
 					request.setAttribute("name", name);
 					request.setAttribute("error","登录失败，密码或账号错误");
 					request.getRequestDispatcher("login2.jsp").forward(request,response);
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
}
