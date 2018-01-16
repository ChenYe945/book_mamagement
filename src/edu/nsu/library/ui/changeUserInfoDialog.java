package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.UserDAO;
import edu.nsu.library.service.BookService;
import edu.nsu.library.service.UserService;
import edu.nsu.library.util.ImageTool;
import edu.nsu.library.util.MD5;

import java.awt.Font;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class changeUserInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField realNameTextFiled;
	private JTextField jobTextField;
	private JRadioButton manRadioButton;
	private JRadioButton womenRadioButton;
	private JLabel nameLable1;
	private UserService userService=null;
	private JComboBox comboBox;
	private ButtonGroup bg;
	private String name;
	private String password;
	private String sex;
	private int role;//用户类型
	private String realname;
	private String job;
	private JRadioButton manRdbtnNewRadioButton;
	private JRadioButton womenRdbtnNewRadioButton;
	private JPasswordField passwordField;
	private UserDAO userDAO;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public UserService getUserService() {
		if(userService==null)
			userService=new UserService();
		return userService;
	}
	public changeUserInfoDialog(int id) {
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setTitle("\u4FEE\u6539\u7528\u6237\u4FE1\u606F");
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("\u59D3    \u540D\uFF1A");
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(117, 26, 91, 29);
		contentPanel.add(nameLabel);
		
		JLabel passwordLabel = new JLabel("\u5BC6    \u7801\uFF1A");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordLabel.setBounds(117, 81, 91, 29);
		contentPanel.add(passwordLabel);
		
		JLabel sexLabel = new JLabel("\u6027    \u522B\uFF1A");
		sexLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sexLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		sexLabel.setBounds(117, 136, 91, 29);
		contentPanel.add(sexLabel);
		
		JLabel roleLabel = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roleLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		roleLabel.setBounds(117, 191, 91, 29);
		contentPanel.add(roleLabel);
		
		JLabel jobLabel = new JLabel("\u804C    \u4E1A\uFF1A");
		jobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jobLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		jobLabel.setBounds(117, 301, 91, 29);
		contentPanel.add(jobLabel);
		
		JLabel realNameLabel = new JLabel("\u771F\u662F\u59D3\u540D\uFF1A");
		realNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		realNameLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		realNameLabel.setBounds(117, 246, 91, 29);
		contentPanel.add(realNameLabel);
		
		nameLable1 = new JLabel("name");
		nameLable1.setBounds(218, 26, 163, 29);
		contentPanel.add(nameLable1);
		
		realNameTextFiled = new JTextField();
		realNameTextFiled.setColumns(10);
		realNameTextFiled.setBounds(218, 246, 172, 29);
		contentPanel.add(realNameTextFiled);
		
		jobTextField = new JTextField();
		jobTextField.setColumns(10);
		jobTextField.setBounds(218, 301, 172, 29);
		contentPanel.add(jobTextField);
		
		bg = new ButtonGroup();
		
		manRadioButton = new JRadioButton("\u7537",true);
		manRadioButton.setBounds(218, 136, 49, 29);
		contentPanel.add(manRadioButton);
		
		womenRadioButton = new JRadioButton("\u5973");
		womenRadioButton.setBounds(267, 137, 49, 29);
		contentPanel.add(womenRadioButton);
		bg.add(manRadioButton);
		bg.add(womenRadioButton);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u7BA1\u7406\u5458", "\u6559\u5E08\u7528\u6237", "\u5B66\u751F\u7528\u6237"}));
		comboBox.setBounds(218, 191, 139, 29);
		contentPanel.add(comboBox);
		
		JButton changeButton = new JButton("\u786E\u5B9A\u4FEE\u6539");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNewInfoAndDelete(id);
				JOptionPane.showMessageDialog
				(null, "修改成功！","错误信息",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				
			}
		});
		changeButton.setBounds(458, 241, 101, 34);
		contentPanel.add(changeButton);
		
		JButton outButton = new JButton("\u9000\u51FA");
		outButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		outButton.setBounds(458, 299, 101, 34);
		contentPanel.add(outButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(218, 81, 172, 29);
		contentPanel.add(passwordField);
		
		JButton btnNewButton = new JButton("\u5220\u9664\u7528\u6237");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteUser(id);
					JOptionPane.showMessageDialog
					(null, "删除完成！","错误信息",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(458, 174, 101, 34);
		contentPanel.add(btnNewButton);
		
		setUserInfo(id);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setResizable(false);
	}
	public void setUserInfo(int id){
		User user=getUserService().getById(id);
		nameLable1.setText(user.getName());
		passwordField.setText(user.getPassword());
		comboBox.setSelectedIndex(user.getRole());
		realNameTextFiled.setText(user.getRealname());
		jobTextField.setText(user.getJob());
	}
	public void getNewInfoAndDelete(int id){
		name = nameLable1.getText().trim();
		password = new String(passwordField.getPassword());
		password=MD5.get(password);
		if(manRadioButton.isSelected()){
			sex = manRadioButton.getText();
		}
		else{
			sex = womenRadioButton.getText();
			
		}
		role = comboBox.getSelectedIndex();
		realname = realNameTextFiled.getText();
		job = jobTextField.getText();
		userDAO = new UserDAO();
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		u.setSex(sex);
		u.setRole(role);
		u.setRealname(realname);
		u.setJob(job);
		u.setId(id);
		try {
			userDAO.modify(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void DeleteUser(int id) throws SQLException{
		User u = new User();
		userDAO = new UserDAO();
		u.setId(id);
		userDAO.delete(u);
		
	}
}
