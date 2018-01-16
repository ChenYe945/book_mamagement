package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import com.mysql.jdbc.PreparedStatement;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.UserDAO;
import edu.nsu.library.service.UserService;
import edu.nsu.library.util.DB;
import edu.nsu.library.util.MD5;

import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JPasswordField passwordField;
	private UserService userService = null;
	private JPasswordField rePasswordField;
	private JTextField realNameTextField_1;
	private JTextField jobTextField_2;
	PreparedStatement pst = null;
	private int id;
	private String name;
	private String password;
	private String password2;
	private String sex;
	private int role;//用户类型
	private String realname;
	private String job;
	private JRadioButton manRdbtnNewRadioButton;
	private JRadioButton womenRdbtnNewRadioButton;
	private JComboBox roleComboBox;

	private void getInputRigest(){
		name = nameTextField.getText().trim();
		password = new String(passwordField.getPassword());
		password=MD5.get(password);
		role = roleComboBox.getSelectedIndex();
		if(manRdbtnNewRadioButton.isSelected()){
			sex = manRdbtnNewRadioButton.getText();
		}else {
			sex = womenRdbtnNewRadioButton.getText();
		}
		job = jobTextField_2.getText();
		password2 = new String(rePasswordField.getPassword());
		password2 =MD5.get(password2);
		realname = realNameTextField_1.getText().trim();
		
	}
	
	public JTextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JPasswordField getPasswordField_1() {
		return rePasswordField;
	}

	public void setPasswordField_1(JPasswordField passwordField_1) {
		this.rePasswordField = passwordField_1;
	}

	public JTextField getRealNameTextField_1() {
		return realNameTextField_1;
	}

	public void setRealNameTextField_1(JTextField realNameTextField_1) {
		this.realNameTextField_1 = realNameTextField_1;
	}

	public JTextField getJobTextField_2() {
		return jobTextField_2;
	}

	public void setJobTextField_2(JTextField jobTextField_2) {
		this.jobTextField_2 = jobTextField_2;
	}

	public PreparedStatement getPst() {
		return pst;
	}

	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public JRadioButton getManRdbtnNewRadioButton() {
		return manRdbtnNewRadioButton;
	}

	public void setManRdbtnNewRadioButton(JRadioButton manRdbtnNewRadioButton) {
		this.manRdbtnNewRadioButton = manRdbtnNewRadioButton;
	}

	public JRadioButton getWomenRdbtnNewRadioButton() {
		return womenRdbtnNewRadioButton;
	}

	public void setWomenRdbtnNewRadioButton(JRadioButton womenRdbtnNewRadioButton) {
		this.womenRdbtnNewRadioButton = womenRdbtnNewRadioButton;
	}

	public JComboBox getRoleComboBox() {
		return roleComboBox;
	}

	public void setRoleComboBox(JComboBox roleComboBox) {
		this.roleComboBox = roleComboBox;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void openRegist() {
		try {
			RegistDialog dialog = new RegistDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserService getUserService() {
		if(userService==null)
			userService=new UserService();
		
		return userService;
	}
	/**
	 * Create the dialog.
	 */
	public RegistDialog() {
		setBounds(100, 100, 569, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel label = new JLabel("用户注册");
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		label.setForeground(Color.BLUE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(156, 10, 257, 37);
		contentPanel.add(label);
		
		JLabel lblNewLabel = new JLabel("姓    名：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(105, 52, 76, 30);
		contentPanel.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("密    码：");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(105, 99, 76, 30);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("确认密码：");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(105, 139, 76, 30);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("性    别：");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(105, 171, 76, 30);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("用户类型：");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(105, 211, 76, 30);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("真实姓名：");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(105, 249, 76, 30);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("职    业：");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(105, 290, 76, 30);
		contentPanel.add(label_6);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		nameTextField.setBounds(191, 52, 171, 30);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordField.setBounds(191, 99, 171, 30);
		contentPanel.add(passwordField);
		
		rePasswordField = new JPasswordField();
		rePasswordField.setToolTipText("\u8BF7\u518D\u6B21\u8F93\u5165\u5BC6\u7801");
		rePasswordField.setFont(new Font("宋体", Font.PLAIN, 14));
		rePasswordField.setBounds(191, 139, 171, 30);
		contentPanel.add(rePasswordField);
		
		ButtonGroup bg = new ButtonGroup();
		
		manRdbtnNewRadioButton = new JRadioButton("\u7537",true);
		manRdbtnNewRadioButton.setBounds(210, 175, 56, 22);
		contentPanel.add(manRdbtnNewRadioButton);
		
		womenRdbtnNewRadioButton = new JRadioButton("\u5973");
		womenRdbtnNewRadioButton.setBounds(268, 175, 44, 23);
		contentPanel.add(womenRdbtnNewRadioButton);
		
		bg.add(manRdbtnNewRadioButton);
		bg.add(womenRdbtnNewRadioButton);
		
		roleComboBox = new JComboBox();
		roleComboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u7BA1\u7406\u5458", "\u6559\u5E08\u7528\u6237", "\u5B66\u751F\u7528\u6237"}));
		roleComboBox.setBounds(195, 211, 142, 30);
		contentPanel.add(roleComboBox);
		
		realNameTextField_1 = new JTextField();
		realNameTextField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		realNameTextField_1.setColumns(10);
		realNameTextField_1.setBounds(191, 249, 171, 30);
		contentPanel.add(realNameTextField_1);
		
		jobTextField_2 = new JTextField();
		jobTextField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jobTextField_2.setColumns(10);
		jobTextField_2.setBounds(191, 290, 171, 30);
		contentPanel.add(jobTextField_2);
		
		
		
		
		JButton registButton = new JButton("\u6CE8\u518C");
		registButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				getInputRigest();
				try {
					if(getUserService().checkRegist(role, name, password)){
						JOptionPane.showMessageDialog
						(null, "该用户已经存在！","错误信息",JOptionPane.ERROR_MESSAGE);
					}else if(getUserService().add(getInput())){
						LoginDialog dialog = new LoginDialog();
						dialog.setVisible(true);
						dispose();
						
					}else {
						
					}
						
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			
			}
		);
//		registButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {	
//				
//				try {
//					getInputRigest();
//					if(getUserService().registProcess(role, name, password)){
//						
//					}
//					UserService us = new UserService();
//					us.add(getInput());
//				
//					
//					
//				} catch (Exception e2) {
//					e2.printStackTrace();	
//				}	
//			}
//		});
		registButton.setBounds(191, 347, 88, 30);
		contentPanel.add(registButton);
		
		JButton outButton = new JButton("\u53D6\u6D88");
		outButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		outButton.setBounds(289, 347, 88, 30);
		contentPanel.add(outButton);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(369, 48, 54, 34);
		contentPanel.add(lblNewLabel_1);
		
		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.ORANGE);
		label_7.setFont(new Font("宋体", Font.PLAIN, 30));
		label_7.setBounds(369, 95, 54, 34);
		contentPanel.add(label_7);
		
		JLabel label_8 = new JLabel("*");
		label_8.setForeground(Color.ORANGE);
		label_8.setFont(new Font("宋体", Font.PLAIN, 30));
		label_8.setBounds(369, 139, 54, 34);
		contentPanel.add(label_8);
	}
//	public void fillData() throws SQLException{
//		User user = new User();
//		String sql = "insert into user(name,password,sex,role,realname,job) values(?,?,?,?,?,?)";
//		pst = (PreparedStatement)new DB().getPstmt(sql);
//		getInputRigest();
//		pst.setString(1, name);
//		pst.setString(2, password);
//		if(manRdbtnNewRadioButton.isSelected()){
//			pst.setString(3, manRdbtnNewRadioButton.getText());
//		}else if(womenRdbtnNewRadioButton.isSelected()){
//			pst.setString(3, womenRdbtnNewRadioButton.getText());
//		}
//		
//		pst.setInt(4, role);
//		pst.setString(5,realNameTextField_1.getText());
//		pst.setString(6, jobTextField_2.getText());
//		pst.executeUpdate();
//		JOptionPane.showMessageDialog
//		(null, "注册成功","zhengquexinxi",JOptionPane.NO_OPTION);
//		dispose();
//	}
	public User getInput(){
		User user = new User();
		//getInputRigest();
		user.setName(name);
		user.setPassword(password);
		user.setSex(sex);
		user.setRole(role);
		user.setRealname(realname);
		user.setJob(job);
		if(name==null||name.equals("")){
			JOptionPane.showMessageDialog
			(null, "用户名不能为空！","错误信息",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(password.length()==0||password.equals("")||password.isEmpty()){
			JOptionPane.showMessageDialog
			(null, "密码不能为空！","错误信息",JOptionPane.ERROR_MESSAGE);
			return null; 
		}
		if(!password.equals(password2)){
			JOptionPane.showMessageDialog
			(null, "两次密码输入不一致！","错误信息",JOptionPane.ERROR_MESSAGE);
			return null; 
		}
		
		return user;
		
	}
}
