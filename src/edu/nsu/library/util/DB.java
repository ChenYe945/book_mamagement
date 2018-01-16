package edu.nsu.library.util;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	private Properties p = null;//读取胚子文件的属性类
	private String driver;//驱动程序类名
	private String url;
	private String username;
	private String password;
	private static Connection conn = null;//连接对象
	private ResultSet rs = null;//结果集对象
	private static PreparedStatement pstmt = null;//欲编译语句对象
	public DB(){
			p = new Properties();
			try {
				p.load(new FileInputStream("config.ini"));
				driver = p.getProperty("driver");
				url = p.getProperty("url");
				username = p.getProperty("username");
				password = p.getProperty("password");
				Class.forName(driver);
				conn = DriverManager.getConnection(url,username,password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
	}
	public static PreparedStatement getPstmt(String sql){
		try {
			pstmt=getConn().prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return pstmt;
		
	}
	public static Connection getConn(){
		
			try {
				if(conn==null||conn.isClosed())
					new DB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return conn;
	}
	public void close(){
		try {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			rs=null;
			pstmt=null;
			conn=null;
		}
	}
}

