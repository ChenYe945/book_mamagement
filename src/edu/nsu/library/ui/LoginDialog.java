package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import edu.nsu.library.ui.MainFrame;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import edu.nsu.library.bean.User;
import edu.nsu.library.dao.UserDAO;
import edu.nsu.library.service.UserService;
import edu.nsu.library.util.MD5;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.channels.NonWritableChannelException;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JPasswordField passwordField;
	private JComboBox roleComboBox;
	private UserService userService = null;
	private String name;
	private String password;
	private int role;
	private MainFrame mainFrame; 
	private StudentAndTeacher sAndTeacher;
	
	private ChangePassword changePassword;

	public UserService getUserService() {
		if(userService==null)
			userService=new UserService();
		
		return userService;
	}
	private void getInput(){
		name=nameTextField.getText().trim();//去空格
		password=new String(passwordField.getPassword());
		password=MD5.get(password);
		role=roleComboBox.getSelectedIndex();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			String style="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);//使窗口居于屏幕中间
			dialog.setVisible(true);
			dialog.nameTextField.grabFocus();
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	private void getUserId(String name,String password,int role) throws SQLException{
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		u.setRole(role);
		int id =0;
		u.setId(id);
		UserDAO userDAO = new UserDAO();
		u=userDAO.get(role, name, password);
		mainFrame.setLoginId(u.getId());
	}
	private void getUserId2(String name,String password,int role) throws SQLException{
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		u.setRole(role);
		int id =0;
		u.setId(id);
		UserDAO userDAO = new UserDAO();
		u=userDAO.get(role, name, password);
		sAndTeacher.setLoginId(u.getId());
	}
	public LoginDialog() {
		setBounds(200, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("用户登录");
		label.setForeground(Color.MAGENTA);
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(103, 30, 245, 29);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("用户类型：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(61, 69, 96, 29);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("用 户 名：");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(61, 111, 96, 29);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("密    码：");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(61, 146, 96, 29);
		contentPanel.add(label_3);
		
		roleComboBox = new JComboBox();
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u7BA1\u7406\u5458", "\u6559\u5E08\u7528\u6237", "\u5B66\u751F\u7528\u6237"}));
		roleComboBox.setBounds(167, 69, 158, 29);
		contentPanel.add(roleComboBox);
		
		nameTextField = new JTextField();
		nameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==arg0.VK_ENTER){
					getInput();
					try {
						if(getUserService().loginProcess(role, name, password))
							LoginDialog.this.dispose();
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					}
			}
		});
		
		nameTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		nameTextField.setBounds(167, 111, 158, 29);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==arg0.VK_ENTER){
					getInput();
					
					try {if(role==0){
						
						if(getUserService().loginProcess(role, name, password)){
							mainFrame = new MainFrame();
							mainFrame.setVisible(true);
							getUserId(name, password, role);
							LoginDialog.this.dispose();
						}
							
					}else{
						if(getUserService().loginProcess(role, name, password)){
							sAndTeacher = new StudentAndTeacher();
							sAndTeacher.setVisible(true);
							getUserId2(name, password, role);
							LoginDialog.this.dispose();
						}
						
					}
						
						
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
			}
		});
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordField.setBounds(167, 150, 158, 29);
		contentPanel.add(passwordField);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInput();
				try {if(role==0){
					
					if(getUserService().loginProcess(role, name, password)){
						mainFrame = new MainFrame();
						mainFrame.setVisible(true);
						getUserId(name, password, role);
						LoginDialog.this.dispose();
					}
						 
				}else {
					if(getUserService().loginProcess(role, name, password)){
						sAndTeacher = new StudentAndTeacher();
						sAndTeacher.setVisible(true);
						getUserId2(name, password, role);
						LoginDialog.this.dispose();
					}
					
				}
					
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		loginButton.setBounds(116, 210, 93, 23);
		contentPanel.add(loginButton);
		
		JButton exitButton = new JButton("\u9000\u51FA");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	
		exitButton.setBounds(229, 210, 93, 23);
		contentPanel.add(exitButton);
		
		JButton registButton = new JButton("\u6CE8\u518C");
		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RegistDialog().openRegist();
				dispose();
			}
		});
		registButton.setBounds(331, 10, 93, 23);
		contentPanel.add(registButton);
	}
}
