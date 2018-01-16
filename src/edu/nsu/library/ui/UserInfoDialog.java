package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.User;
import edu.nsu.library.dao.UserDAO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UserInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel userNameLabel;
	private JLabel sexLabel;
	private JLabel roleLabel;
	private JLabel reanNameLabel;
	private JLabel jobLable;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			UserInfoDialog dialog = new UserInfoDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public UserInfoDialog(int id) {
		setTitle("\u7528\u6237\u4FE1\u606F");
		setBounds(100, 100, 492, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u7528 \u6237 \u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 18));
		label.setBounds(76, 33, 121, 37);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u6027     \u522B\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_1.setBounds(76, 82, 121, 37);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_2.setBounds(76, 134, 121, 37);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("\u771F\u662F\u59D3\u540D\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_3.setBounds(76, 184, 121, 37);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("\u804C    \u4E1A\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_4.setBounds(76, 226, 121, 37);
		contentPanel.add(label_4);
		
		userNameLabel = new JLabel("New label");
		userNameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		userNameLabel.setBounds(204, 33, 167, 37);
		contentPanel.add(userNameLabel);
		
		sexLabel = new JLabel("New label");
		sexLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		sexLabel.setBounds(204, 82, 167, 37);
		contentPanel.add(sexLabel);
		
		roleLabel = new JLabel("New label");
		roleLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		roleLabel.setBounds(204, 134, 167, 37);
		contentPanel.add(roleLabel);
		
		reanNameLabel = new JLabel("New label");
		reanNameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		reanNameLabel.setBounds(204, 184, 167, 37);
		contentPanel.add(reanNameLabel);
		
		jobLable = new JLabel("New label");
		jobLable.setFont(new Font("宋体", Font.PLAIN, 18));
		jobLable.setBounds(204, 226, 167, 37);
		contentPanel.add(jobLable);
		setUserInfo(id);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	public void setUserInfo(int id){
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getById(id);
		userNameLabel.setText(user.getName());
		sexLabel.setText(user.getSex());
		String role="";
		if(user.getRole()==0){
			role = "管理员";
		}if(user.getRole()==1){
			role = "教师用户";
		}if(user.getRole()==2){
			role = "学生用户";
		}
		roleLabel.setText(role);
		reanNameLabel.setText(user.getRealname());
		jobLable.setText(user.getJob());
	}
}
