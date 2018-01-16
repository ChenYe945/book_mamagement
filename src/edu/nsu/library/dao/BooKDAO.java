package edu.nsu.library.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.nsu.library.bean.Book;
import edu.nsu.library.util.DB;

public class BooKDAO {
   private PreparedStatement pstmt=null;
   private String sql="";
   private ResultSet rs=null;
   public ArrayList<Book> getAll(){
	   sql="select * from book";
	   try {
		pstmt=DB.getConn().prepareStatement(sql);
		rs=pstmt.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   return getByRs(rs);
   }
   public ArrayList<Book> getByCode(String code){
	   int length=code.length();
	   if(length==1)
          return getAll();
	   else
		   //left是mysql提供的函数，用来从字符串左边截取
		   //指定长度的字符串
		   sql="select * from book where left(c3code,?)=?";
	   try{pstmt=DB.getConn().prepareStatement(sql);
		   pstmt.setInt(1, length);
		   pstmt.setString(2, code);
		   rs=pstmt.executeQuery();
	   }catch(Exception e){
		   e.printStackTrace();
	   } 
	   return getByRs(rs);
   }
   public ArrayList<Book> getByRs(ResultSet rs){
	   ArrayList<Book> books=new ArrayList<Book>();
	   Book book=null;
	   try{
		   if(rs==null||!rs.next())
			   return null;
		   else
			   do{
					book=new Book();
					book.setId(rs.getInt("id"));
					book.setBookname(rs.getString("bookname"));
					book.setAuthor(rs.getString("author"));
					book.setPress(rs.getString("press"));
					book.setPresstime(rs.getString("presstime"));
					book.setPrice(rs.getFloat("price"));
					book.setC3code(rs.getString("c3code"));
					book.setIsbn(rs.getString("isbn"));
					book.setBookcounts(rs.getInt("bookcounts"));
					book.setBorrowcounts(rs.getInt("borrowcounts"));
					book.setRecommend(rs.getInt("recommend"));
					book.setIntrouction(rs.getString("introduction"));
					book.setCover(rs.getString("cover"));
					books.add(book);
				}while(rs.next());
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	return books;	   
   }
  
   public Book getById(int id){
	   sql="select * from book where id=?";
	   Book book=null;
	   try{
		   pstmt=DB.getConn().prepareStatement(sql);
		   pstmt.setInt(1, id);
		   rs=pstmt.executeQuery();
		   ArrayList<Book> books=getByRs(rs);
		   if(books==null||books.size()<1)
			   return null;
		   else
			   book=books.get(0);
		   
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return book;
   }
   public ArrayList<Book> getByName(String bookname){
	   
	   //以一个或多个空格作为关键字分隔符
	   String[] strs = bookname.split("\\s+");
	   sql = "select * from book where bookname like '%"
		   		 +strs[0]+"%'";
	   try {
		   if(strs.length>1){
			   for(int i=1;i<strs.length;i++)
				   sql+=" and bookname like '%"
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

   public boolean add(Book book){
	   int result = 0;
	   sql = "insert into book(bookname,author,press,presstime,price,isbn,"
	   		+ "c3code,bookcounts,introduction) values"
	   		+ "(?,?,?,?,?,?,?,?,?)";
	   try {
		   pstmt = DB.getConn().prepareStatement(sql);
		   pstmt.setString(1, book.getBookname());
		   pstmt.setString(2, book.getAuthor());
		   pstmt.setString(3, book.getPress());
		   pstmt.setString(4, book.getPresstime());
		   pstmt.setFloat(5, book.getPrice());
		   pstmt.setString(6, book.getIsbn());
		   pstmt.setString(7, book.getC3code());
		   pstmt.setInt(8, book.getBookcounts());
		   pstmt.setString(9, book.getIntrouction());
		   result = pstmt.executeUpdate();
		   //获得最大的id值，即刚插入记录的id值
		   sql = "select max(id) as maxid from book";
		   pstmt = DB.getConn().prepareStatement(sql);
		   rs = pstmt.executeQuery();
		   rs.next();
		   int id = rs.getInt("maxid");
		   String cover = book.getCover();
		   if(cover!=null||!cover.equals("")){
		   int pos = book.getCover().lastIndexOf(".");
		   if(pos==-1){
			   JOptionPane.showMessageDialog
				(null, "请选择图片文件！","错误信息",JOptionPane.ERROR_MESSAGE);
		   }else{
			   String ext = book.getCover().substring(pos);
			   sql = "update book set cover=? where id = ?";
			   pstmt = DB.getConn().prepareStatement(sql);
			   pstmt.setString(1, id+ext);
			   pstmt.setInt(2, id);
			   pstmt.executeUpdate();
			   //获得原文件
			   Path source = Paths.get(book.getCover());
			   Path target = Paths.get("covers/"+id+ext);
			  
			   Files.copy(source, target);
			   
		   }
		   
		   }
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	   if(result>0)
		   return true;
	   return false;
   }
	public void modify(Book book) throws SQLException {
		
		sql = "update book set bookname=?,author=?,press=?,presstime=?,price=?,isbn=?,bookcounts=?,borrowcounts=?,introduction=? where id=?";
		pstmt = DB.getConn().prepareStatement(sql);
		pstmt.setString(1,book.getBookname());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getPress());
		pstmt.setString(4, book.getPresstime());
		pstmt.setFloat(5, book.getPrice());
		pstmt.setString(6, book.getIsbn());
		pstmt.setInt(7, book.getBookcounts());
		pstmt.setInt(8, book.getBorrowcounts());
		pstmt.setString(9, book.getIntrouction());
		pstmt.setInt(10, book.getId());
		pstmt.executeUpdate();
		
	}
	public void delete(Book b) throws SQLException {
		sql ="delete from book where id=?";
		pstmt = DB.getConn().prepareStatement(sql);
		pstmt.setInt(1, b.getId());
		pstmt.executeUpdate();
		
	}
	public void changeBookInfo(Book book) throws SQLException {
		sql = "update book set bookcounts=?,borrowcounts=? where id=?";
		pstmt = DB.getConn().prepareStatement(sql);
		pstmt.setInt(1, book.getBookcounts());
		pstmt.setInt(2, book.getBorrowcounts());
		pstmt.setInt(3, book.getId());
		pstmt.executeUpdate();
	}
   
   
}
