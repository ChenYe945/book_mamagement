package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.BorrowInfo;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.BooKDAO;
import edu.nsu.library.dao.BorrowInfoDAO;
import edu.nsu.library.dao.UserDAO;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;

public class GetBorrowInfo extends JDialog {
	private int borrowId;
	private JLabel userNameLabel;
	private JLabel bookNameLabel;
	private JLabel borrowTimeLabel;
	private JLabel renewLabel;
	private User user;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			GetBorrowInfo dialog = new GetBorrowInfo();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	/**
	 * Create the dialog.
	 */
	public GetBorrowInfo(int id) {
		borrowId = id;
		setBounds(100, 100, 513, 358);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528 \u6237 \u540D\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(78, 61, 120, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u4E66    \u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(78, 117, 120, 36);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u501F\u4E66\u65F6\u95F4\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(78, 163, 120, 36);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u7EED\u501F\u6B21\u6570\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(78, 218, 120, 36);
		getContentPane().add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u501F\u9605\u4FE1\u606F");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(178, 10, 162, 41);
		getContentPane().add(lblNewLabel_1);
		
		userNameLabel = new JLabel("New label");
		userNameLabel.setBounds(208, 61, 162, 36);
		getContentPane().add(userNameLabel);
		
		bookNameLabel = new JLabel("New label");
		bookNameLabel.setBounds(208, 117, 162, 36);
		getContentPane().add(bookNameLabel);
		
		borrowTimeLabel = new JLabel("New label");
		borrowTimeLabel.setBounds(208, 163, 162, 36);
		getContentPane().add(borrowTimeLabel);
		
		renewLabel = new JLabel("New label");
		renewLabel.setBounds(208, 218, 162, 36);
		getContentPane().add(renewLabel);
		try {
			setBorrowInfo(getBorrowId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setBorrowInfo(int id) throws SQLException{
		BorrowInfoDAO borrowInfoDAO = new BorrowInfoDAO();
		BorrowInfo borrowInfo = borrowInfoDAO.getById(id);
		UserDAO userDAO = new UserDAO();
		
		user = userDAO.getById(borrowInfo.getUserId());
		userNameLabel.setText(user.getName());
		BooKDAO bookDAO = new BooKDAO();
		Book book = bookDAO.getById(borrowInfo.getBookId());
		bookNameLabel.setText(book.getBookname());
		borrowTimeLabel.setText(borrowInfo.getBorrowTime());
		String panDing = "";
		if(borrowInfo.getRenew()==0){
			panDing="没有续借记录";
		}else{
			panDing="续借"+borrowInfo.getRenew()+"次";
		}
		renewLabel.setText(panDing);
		
	}
}
