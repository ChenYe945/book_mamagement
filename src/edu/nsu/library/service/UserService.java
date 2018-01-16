package edu.nsu.library.service;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.UserDAO;
import edu.nsu.library.ui.MainFrame;
import edu.nsu.library.ui.RegistDialog;

public class UserService {
	private User user = null;
	private UserDAO userDAO = null;
	
	
	//---------------------
	public boolean check(int role,String name,String password) throws SQLException {
		boolean result = false;
		if(getUserDAO().get(role, name, password)==null)
			result=false;
		else {
			result=true;
		}
		
		return result;
	}
	public UserDAO getUserDAO() {
		if(userDAO==null)
			userDAO=new UserDAO();
		return userDAO;
	}
	public User getById(int id){
		return getUserDAO().getById(id);
	}

	public boolean loginProcess(int role,String name,String password) throws HeadlessException, SQLException{
		if(check(role, name, password)){
//			MainFrame mainFrame = new MainFrame();
//			mainFrame.setVisible(true);	
			return true;	
		}else{
			JOptionPane.showMessageDialog
			(null, "用户名或者密码错误！","错误信息",JOptionPane.ERROR_MESSAGE);
			return false;
			
		}
			
	}
	//-------------------检测数据库是否存在此用户
	public boolean checkRegist(int role,String name,String password) throws SQLException {
		boolean result = false;
		if(getUserDAO().get(role, name, password)==null)
			result=false;
		else {
			result=true;
		}
		
		return result;
	}
	
	public boolean registProcess(int role,String name,String password) throws SQLException {
		if(check(role, name, password)){
			System.out.println("2");
			
			return true;
		}else{		
			System.out.println("1");
			return false;	
		}
			
	}
	//---------------------------------------------------------
	//检测输入是否为空
	public boolean add(User user1){
		if(user1!=null){
			if(getUserDAO().add(user1)){
				JOptionPane.showMessageDialog
				(null, "注册成功！","错误信息",JOptionPane.INFORMATION_MESSAGE);
				
				return true;
				
			}
				
		else{
			JOptionPane.showMessageDialog
			(null, "重新注册！","错误信息",JOptionPane.ERROR_MESSAGE);
			
			}		
		}	
		return false;
	}
		
	public ArrayList<User> getByName(String userName){
		return getUserDAO().getByName(userName);
	}
	public ArrayList<User> getAllUser(){
		return getUserDAO().getAllUser();
	}

}
