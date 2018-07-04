package cn.nicecoder.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 获取数据库连接
 *-------------------------------
 * @author longtian
 * @date 2018年4月12日下午9:49:56
 * @description nicecoder.cn
 *-------------------------------
 */
public class DBUtil {
	public static final String url = "jdbc:mysql://localhost:3306/parttime";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "admin";
	
	public Connection conn = null;
	public Connection getConnection() {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
