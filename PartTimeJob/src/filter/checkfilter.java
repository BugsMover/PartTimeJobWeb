package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.nicecoder.util.BizConstant;
import cn.nicecoder.util.StringUtil;

public class checkfilter implements Filter{
	
	public static List<String> patternURL=new ArrayList<>();
	
	public void destroy() {
 
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpServletResponse resp=(HttpServletResponse)response;
        HttpServletRequest req=(HttpServletRequest)request;
        HttpSession session2=req.getSession();
        String path = req.getRequestURI();
        String name = (String)session2.getAttribute("name");
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
        String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = httpRequest.getSession();
//        String findname = (String)getAttribute("findname");
        for(String urlStr : patternURL) {
        if(path.indexOf(urlStr)>-1) {
        	chain.doFilter(request, response);
        	return;
           }
        }
        if(path.equals("/Login/index.jsp")) {
          if(name == null || "".equals(name)) {
        	resp.sendRedirect(req.getContextPath()+"/login2.jsp");
          }else {
        	chain.doFilter(request, response);
         }
        }
//        if(path.equals("/Login/changeSuc.jsp")||path.equals("/Login/changeword.jsp")) {
//         if(findname == null || "".equals(findname)) {
//        	resp.sendRedirect(req.getContextPath()+"/findword.jsp");
//         }else {
//        	chain.doFilter(request, response);
//         }
//        }
        if(path.equals("/Login/WEB-INF/admin.jsp")) {
          String bizCode = request.getParameter("bizCode");

          //简单的权限控制
          boolean flag = true;
  	
  		if(BizConstant.BIZ_NEWS_0.getpCode().equals(bizCode)||BizConstant.BIZ_NEWS_1.getpCode().equals(bizCode)||BizConstant.BIZ_NEWS_3.getpCode().equals(bizCode)) {
  			 
  			 if(StringUtil.isNotEmpty(username) && StringUtil.isNotEmpty(password)){
  				 if(!"admin".equalsIgnoreCase(password)){
  					 flag = false; 
  				 }else{
  					 //session.setAttribute("username", username);
  					 session.setAttribute("password", password);
  					 session.setMaxInactiveInterval(5 * 60);
  				 }
  			 }else{
  				 password = (String) session.getAttribute("password");
  				 if(!"admin".equalsIgnoreCase(password)){
  					 flag = false; 
  				 }
  			 }
  		}
  		
  		if(flag){
  			chain.doFilter(request,response);
  		}else{
  			request.getRequestDispatcher("forbidden.jsp").forward(request, response);
  		}
      }
	}
	
	private String getAttribute(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(FilterConfig config)throws ServletException{
		patternURL.add("login2.jsp");
		patternURL.add("register.jsp");
		patternURL.add("findword.jsp");
		patternURL.add("registerSuc.jsp");
	}

}
