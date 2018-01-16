package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.User;
import edu.nsu.library.dao.UserDAO;
import edu.nsu.library.service.UserService;
import edu.nsu.library.util.MD5;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField NewPasswordField;
	private int changUserID;
	private JLabel UserNameLabel;
	private UserService userService;
	private MainFrame mainFrame;
	private String password;
	private UserDAO userDAO= new UserDAO();
	public int getChangUserID() {
		return changUserID;
	}

	public void setChangUserID(int changUserID) {
		this.changUserID = changUserID;
	}

	public UserService getUserService() {
		if(userService==null)
			userService=new UserService();
		return userService;
	}

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public ChangePassword(int id) {
		changUserID=id;
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u5BC6\u7801\u754C\u9762");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(210, 10, 164, 37);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528 \u6237 \u540D\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(93, 75, 114, 29);
		contentPanel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u539F\u59CB\u5BC6\u7801\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(93, 130, 114, 29);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u65B0 \u5BC6 \u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(93, 185, 114, 29);
		contentPanel.add(label_1);
		
		UserNameLabel = new JLabel("New label");
		UserNameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		UserNameLabel.setBounds(217, 75, 156, 37);
		contentPanel.add(UserNameLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(217, 130, 164, 29);
		contentPanel.add(passwordField);
		
		NewPasswordField = new JPasswordField();
		NewPasswordField.setBounds(217, 185, 164, 29);
		contentPanel.add(NewPasswordField);
		
		JLabel lblNewLabel_3 = new JLabel("*\u8BF7\u8F93\u5165\u539F\u59CB\u5BC6\u7801");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(391, 130, 146, 29);
		contentPanel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					changepassword(changUserID);
					JOptionPane.showMessageDialog
					(null, "密码修改完成！","错误信息",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setBounds(238, 265, 105, 29);
		contentPanel.add(btnNewButton);
		setInfo(getChangUserID());
	
	}
	public void setInfo(int id){
		User user = getUserService().getById(id);
		UserNameLabel.setText(user.getName());
		passwordField.setText(user.getPassword());
	}
	public void changepassword(int id) throws SQLException{
		User user = new User();
		password = new String(NewPasswordField.getPassword());
		password=MD5.get(password);
		user.setPassword(password);
		user.setId(id);
		userDAO.changPassWord(user);
	}
	
}
