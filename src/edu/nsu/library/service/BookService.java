package edu.nsu.library.service;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.Category;
import edu.nsu.library.dao.BooKDAO;

public class BookService {
	private BooKDAO booKDAO = null;

	public BooKDAO getBooKDAO() {
		if(booKDAO==null)
			booKDAO = new BooKDAO();
		return booKDAO;
	}

	public void setBooKDAO(BooKDAO booKDAO) {
		this.booKDAO = booKDAO;
	}
	public ArrayList<Book> getAll(){
		return getBooKDAO().getAll();
	}
	public ArrayList<Book> getByCate(Category cate){
		return getBooKDAO().getByCode(cate.getCode());
	}
	public Book getById(int id){
		return getBooKDAO().getById(id);
	}
	public ArrayList<Book> getByName(String bookname){
		return getBooKDAO().getByName(bookname);
	}
	public boolean add(Book book){
		if(book!=null){
			if(getBooKDAO().add(book)){
				JOptionPane.showMessageDialog
				(null, "添加图书成功！","错误信息",JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
				
		else{
			JOptionPane.showMessageDialog
			(null, "添加图书失败！","错误信息",JOptionPane.ERROR_MESSAGE);
			}		
		}	
		return false;
	}
	

}
