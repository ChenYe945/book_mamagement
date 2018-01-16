package edu.nsu.library.dao;


import java.nio.channels.SelectableChannel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.User;
import edu.nsu.library.util.DB;

public class UserDAO {
	private PreparedStatement pstmt = null;
	private String sql = "";
	private ResultSet rs = null;
	private User user = null;
	public User get(int role,String name,String password) throws SQLException {
		sql = "select * from user where role =? and name=? and password=?";
		pstmt = DB.getPstmt(sql);
		pstmt.setInt(1, role);
		pstmt.setString(2, name);
		pstmt.setString(3, password);
		rs=pstmt.executeQuery();
		if(rs!=null&&rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setSex(rs.getString("sex"));
			user.setRole(rs.getInt("role"));
			user.setRealname(rs.getString("realname"));
			user.setJob(rs.getString("job"));
			
		}
		else{
			return null;
		}	
		return user;
	}
	public boolean add(User user1){
		   int result = 0;
		   sql = "insert into user(name,password,sex,role,realname,job) values(?,?,?,?,?,?)";
		   try {
			   pstmt = DB.getConn().prepareStatement(sql);
			   pstmt.setString(1, user1.getName());
			   pstmt.setString(2, user1.getPassword());
			   pstmt.setString(3, user1.getSex());
			   pstmt.setInt(4, user1.getRole());
			   pstmt.setString(5, user1.getRealname());
			   pstmt.setString(6, user1.getJob());
			   result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		   if(result>0)
			   return true;
		   return false;
	   }
	public ArrayList<User> getByName(String userName) {
		 //以一个或多个空格作为关键字分隔符
		   String[] strs = userName.split("\\s+");
		   sql = "select * from user where name like '%"
			   		 +strs[0]+"%'";
		   try {
			   if(strs.length>1){
				   for(int i=1;i<strs.length;i++)
					   sql+=" and name like '%"
					   		+ strs[i]+"%'";
			   }
			   pstmt = DB.getConn().prepareStatement(sql);
			 
			   rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		  return getByRs(rs);	
	}
	public ArrayList<User> getAllUser(){
		sql = "select * from user";
		try {
			pstmt =DB.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getByRs(rs);
	}
	 private ArrayList<User> getByRs(ResultSet rs){
		   ArrayList<User> users=new ArrayList<User>();
		   try{
			   if(rs==null||!rs.next())
				   return null;
			   else
				   do{
						user = new User();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setSex(rs.getString("sex"));
						user.setRole(rs.getInt("role"));
						user.setRealname(rs.getString("realname"));
						user.setJob(rs.getString("job"));
						users.add(user);
					}while(rs.next());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		return users;	   
	   }
	 public User getById(int id){
		   sql="select * from user where id=?"; 
		   try{
			   pstmt=DB.getConn().prepareStatement(sql);
			   pstmt.setInt(1, id);
			   rs=pstmt.executeQuery();
			   ArrayList<User> users=getByRs(rs);
			   if(users==null||users.size()<1)
				   return null;
			   else
				   user=users.get(0);
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return user;
	   }
	public void modify(User u) throws SQLException {
		sql = "update user set password=?,sex=?,role=?,realname=?,job=? where id=?";
		pstmt = DB.getConn().prepareStatement(sql);
		pstmt.setString(1, u.getPassword());
		pstmt.setString(2, u.getSex());
		pstmt.setInt(3, u.getRole());
		pstmt.setString(4, u.getRealname());
		pstmt.setString(5, u.getJob());
		pstmt.setInt(6, u.getId());
		pstmt.executeUpdate();
		
	}
	public void changPassWord(User user2) throws SQLException {
		sql = "update user set password=? where id=?";
		pstmt = DB.getConn().prepareStatement(sql);
		pstmt.setString(1, user2.getPassword());
		pstmt.setInt(2, user2.getId());
		pstmt.executeUpdate();
	}
	public void delete(User u) throws SQLException{
		sql ="delete from user where id=?";
		pstmt = DB.getConn().prepareStatement(sql);
		pstmt.setInt(1, u.getId());
		pstmt.executeUpdate();
		
		
	}
	
	
}
