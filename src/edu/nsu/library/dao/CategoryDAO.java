package edu.nsu.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import edu.nsu.library.bean.Category;
import edu.nsu.library.util.DB;

public class CategoryDAO {
	private PreparedStatement pstmt  = null;
	private ResultSet rs = null;
	private ArrayList<Category> categories = new ArrayList<Category>();
	private String sql="";
	private Category category = null;
	public ArrayList<Category> get(String fathercode){
		sql = "select * from category where fathercode = ?";
		categories.clear();
		try {
			pstmt = DB.getConn().prepareStatement(sql);
			pstmt.setString(1, fathercode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				category = new Category();
				category.setCode(rs.getString("code"));
				category.setName(rs.getString("name"));
				category.setFathercode(rs.getString("fathercode"));
				categories.add(category);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return categories;
		
		
	}
	public ArrayList<Category> getCategorise(String level){
   	 categories.clear(); 
   	 switch(level){
   	 case "c1":sql="select * from category where length(code)=2";break;//获取一级分类
   	 case "c2":sql="select * from category where length(code)=4";break;//获取二级分类
   	 case "c3":sql="select * from category where length(code)=6";break;//获取三级分类
   	 }
   	try{
   		  pstmt=DB.getConn().prepareStatement(sql);
   		  rs=pstmt.executeQuery();	   
   	}catch(Exception e){
   		e.printStackTrace();
   	}
   	return getByRs(rs);
   }
	public ArrayList<Category> getChildren(Category cate){
		String code = cate.getCode();
		sql = "select * from category where fathercode=?";
		try {
			pstmt = DB.getConn().prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return getByRs(rs);
	}
	private ArrayList<Category> getByRs(ResultSet rs){
		try {
			if(rs==null||!rs.next()){
				return null;
			}
			else{
				categories.clear();
				 do{
					   category=new Category();
					   category.setCode(rs.getString("code"));
					   category.setName(rs.getString("name"));
					   category.setFathercode(rs.getString("fathercode"));
					   categories.add(category);
				   }while(rs.next());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}


}
