package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckServlet extends HttpServlet{
	private static final String URL="jdbc:mysql://localhost:3306/parttime";
	private static final String NAME="root";
	private static final String PASSWORD="admin";
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		PreparedStatement pstmt;
		ResultSet rs;
		String username =new String( request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
	//	System.out.println("网页回传用户名"+username);
		if(username.length()>=3 && username.length()<=16) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序");
			e.printStackTrace();
		 }
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			System.out.println("获取数据库连接成功");
		} catch (SQLException e) {
			System.out.println("获取数据库连接失败");
			e.printStackTrace();
		}
		String sql = "select count(username) from user where username=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			System.out.println("来自数据库"+rs);
			if(rs.next()) {
				if(rs.getInt(1) >= 1) {
				   //System.out.println("!"+rs.getString(1));
				   response.getWriter().print("true");
				   System.out.println("true");
//				   request.getSession().setAttribute("Checkusername", username);
				}else {
					response.getWriter().print("false");
					System.out.println("false");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn!=null)  
        {  
            try {  
                conn.close();  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
                conn=null;  
            }  
        }  
	 }else {
		 response.getWriter().print("length");
			//System.out.println("length");
	 }
	}
}
