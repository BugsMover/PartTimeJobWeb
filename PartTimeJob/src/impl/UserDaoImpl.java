package impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.UserDao;
import vo.User;

public class UserDaoImpl implements UserDao{
	
	private Connection conn = null;
	private PreparedStatement pstmt =null;
	
	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean findLogin(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "select username from user where username = ? and password = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getUsername());
		this.pstmt.setString(2, user.getPassword());
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			user.setUsername(rs.getString(1));
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public boolean doCreate(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "insert into user(username,password,phonenumber,email) values (?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getUsername());
		this.pstmt.setString(2, user.getPassword());
		this.pstmt.setString(3, user.getPhonenumber());
		this.pstmt.setString(4, user.getEmali());
		if(this.pstmt.executeUpdate()>0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}
	
	@Override
	public boolean findword(User user)throws Exception{
		boolean flag = false;
		String sql = "select username from user where username = ? and email = ? and phonenumber = ?";
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getUsername());
		this.pstmt.setString(2, user.getEmali());
		this.pstmt.setString(3, user.getPhonenumber());
		ResultSet rs=this.pstmt.executeQuery();
		if(rs.next()) {
			user.setUsername(rs.getString(1));
			flag=true;
		}
		this.pstmt.close();
		return flag;
	}
	
	@Override
	public boolean changeword(User user)throws Exception{
		boolean flag = false;
		String sql = "update user set password = ? where username=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getPassword());
		this.pstmt.setString(2, user.getUsername());
		if(this.pstmt.executeUpdate()>0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
		
	}

}
