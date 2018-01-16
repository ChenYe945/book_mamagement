package edu.nsu.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.Suggest;
import edu.nsu.library.util.DB;

public class SuggestDAO {
  private PreparedStatement pstmt=null;
  private String sql="";
  private ResultSet rs=null;
  public ArrayList<Suggest> getByRs(ResultSet rs){
	   ArrayList<Suggest> suggests=new ArrayList<Suggest>();
	   Suggest suggest=null;
	   try{
		   if(rs==null||!rs.next())
			   return null;
		   else
			   do{
					suggest=new Suggest();
					suggest.setId(rs.getInt("id"));
					suggest.setUserid(rs.getInt("userid"));
					suggest.setContent(rs.getString("content"));
					suggest.setSuggestTime(rs.getString("suggesttime"));
					suggests.add(suggest);
				}while(rs.next());
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	return suggests;	   
   }

	public void addSuggest(Suggest suggest) throws SQLException {
		sql = "insert into suggestion(userid,content,suggesttime) values(?,?,?)";
		pstmt=DB.getConn().prepareStatement(sql);
		pstmt.setInt(1, suggest.getUserid());
		pstmt.setString(2, suggest.getContent());
		pstmt.setString(3, suggest.getSuggestTime());
		pstmt.executeUpdate();
		
	}

	public ArrayList<Suggest> getAll() {
		 sql="select * from suggestion";
		   try {
			pstmt=DB.getConn().prepareStatement(sql);
			rs=pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return getByRs(rs);
		
	}

}
