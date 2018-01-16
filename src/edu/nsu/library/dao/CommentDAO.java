package edu.nsu.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nsu.library.bean.BorrowInfo;
import edu.nsu.library.bean.Comment;
import edu.nsu.library.bean.Suggest;
import edu.nsu.library.util.DB;

public class CommentDAO {
	private PreparedStatement pstmt=null;
	private String sql="";
	private ResultSet rs=null;
	private ArrayList<Comment> getByRs(ResultSet rs){
		   ArrayList<Comment> comments=new ArrayList<Comment>();
		   Comment comment=null;
		   try{
			   if(rs==null||!rs.next())
				   return null;
			   else
				   do{
					   comment = new Comment();
					   comment.setId(rs.getInt("id"));
					   comment.setBookid(rs.getInt("bookid"));
					   comment.setUserid(rs.getInt("userid"));
					   comment.setCommenttime(rs.getString("commenttime"));
					   comment.setContent(rs.getString("content"));
					   comments.add(comment);
					}while(rs.next());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		return comments;	   
	 }
	public void addComment(Comment comment) throws SQLException {
		sql = "insert into comment(bookid,userid,commenttime,content) values(?,?,?,?)";
		pstmt=DB.getConn().prepareStatement(sql);
		pstmt.setInt(1, comment.getBookid());
		pstmt.setInt(2, comment.getUserid());
		pstmt.setString(3, comment.getCommenttime());
		pstmt.setString(4, comment.getContent());
		pstmt.executeUpdate();
	}
	public ArrayList<Comment> getAll() {
		 sql="select * from comment";
		   try {
			pstmt=DB.getConn().prepareStatement(sql);
			rs=pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return getByRs(rs);
		
	}
	public Comment getById(int id) throws SQLException {
		 sql="select * from comment where id=?";
		   Comment comment=null;
		   try{
			   pstmt=DB.getConn().prepareStatement(sql);
			   pstmt.setInt(1, id);
			   rs=pstmt.executeQuery();
			   ArrayList<Comment> comments=getByRs(rs);
			   if(comments==null||comments.size()<1)
				   return null;
			   else
				   comment=comments.get(0);
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return comment;
		
	}

}
