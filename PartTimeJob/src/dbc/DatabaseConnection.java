package dbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
      private static final String DBDRIVER = "com.mysql.jdbc.Driver";
      private static final String DBURL = "jdbc:mysql://localhost:3306/parttime";
      private static final String DBUSER = "root";
      private static final String DBPASSWORD = "admin";
      private Connection conn = null ;
      
      public DatabaseConnection() throws Exception{
    	  Class.forName(DBDRIVER);
    	  this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
      }
      
      public Connection getConnection() {
    	  return this.conn;
      } 
      
      public void close() throws Exception{
    	  if(this.conn !=null ) {
    		  this.conn.close();
    	  }
      }
}
