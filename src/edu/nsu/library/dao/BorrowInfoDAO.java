package edu.nsu.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.BorrowInfo;
import edu.nsu.library.bean.User;
import edu.nsu.library.util.DB;

public class BorrowInfoDAO {
   private PreparedStatement pstmt=null;
   private String sql="";
   private ResultSet rs=null;
   private ArrayList<BorrowInfo> getByRs(ResultSet rs){
	   ArrayList<BorrowInfo> borrowInfos=new ArrayList<BorrowInfo>();
	   BorrowInfo borrowInfo=null;
	   try{
		   if(rs==null||!rs.next())
			   return null;
		   else
			   do{
				   borrowInfo = new BorrowInfo();
				   borrowInfo.setId(rs.getInt("id"));
				   borrowInfo.setBookId(rs.getInt("bookid"));
				   borrowInfo.setUserId(rs.getInt("userid"));
				   borrowInfo.setBorrowTime(rs.getString("borrowtime"));
				   borrowInfo.setRenew(rs.getInt("renew"));
				   borrowInfos.add(borrowInfo);
				}while(rs.next());
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	return borrowInfos;	   
   }
	public void addBorrowInfo(BorrowInfo borrowInfo) throws SQLException {
		sql = "insert into borrowinfo(bookid,userid,borrowtime,renew) values(?,?,?,?)";
		pstmt=DB.getConn().prepareStatement(sql);
		pstmt.setInt(1, borrowInfo.getBookId());
		pstmt.setInt(2, borrowInfo.getUserId());
		pstmt.setString(3, borrowInfo.getBorrowTime());
		pstmt.setInt(4, borrowInfo.getRenew());
		pstmt.executeUpdate();
	}

	public BorrowInfo getById(int id) throws SQLException {
		 sql="select * from borrowinfo where id=?";
		   BorrowInfo borrowInfo=null;
		   try{
			   pstmt=DB.getConn().prepareStatement(sql);
			   pstmt.setInt(1, id);
			   rs=pstmt.executeQuery();
			   ArrayList<BorrowInfo> borrowInfos=getByRs(rs);
			   if(borrowInfos==null||borrowInfos.size()<1)
				   return null;
			   else
				   borrowInfo=borrowInfos.get(0);
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return borrowInfo;
		
	}
	 public ArrayList<BorrowInfo> getByUserId2(int id){
		   //以一个或多个空格作为关键字分隔符
		   sql = "select * from borrowinfo where userid=?";
		   try {
			pstmt = DB.getConn().prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return getByRs(rs);
	   }
	 public ArrayList<BorrowInfo> getAll(){
		   sql="select * from borrowinfo";
		   try {
			pstmt=DB.getConn().prepareStatement(sql);
			rs=pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			   return getByRs(rs);
	 }
	public void changeRenew(BorrowInfo borrowInfo) throws SQLException {
		sql = "update borrowinfo set renew=? where id=?";
		pstmt = DB.getConn().prepareStatement(sql);
		pstmt.setInt(1, borrowInfo.getRenew());
		pstmt.setInt(2, borrowInfo.getId());
		pstmt.executeUpdate();
	}
	
}
