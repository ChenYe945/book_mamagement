package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.Comment;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.BooKDAO;
import edu.nsu.library.dao.CommentDAO;
import edu.nsu.library.dao.UserDAO;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CommentInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int commentId;
	private JLabel bookNameLabel;
	private JLabel userNameLabel;
	private JLabel commentTimeLabel;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			CommentInfo dialog = new CommentInfo();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	/**
	 * Create the dialog.
	 */
	public CommentInfo(int id) {
		commentId = id;
		setTitle("\u8BC4\u8BBA\u4FE1\u606F");
		setBounds(100, 100, 596, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4E66    \u540D\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(122, 53, 105, 31);
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u7528 \u6237 \u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(122, 105, 105, 31);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u8BC4\u8BBA\u65F6\u95F4\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(122, 159, 105, 31);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u8BC4    \u8BBA\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(122, 221, 105, 31);
		contentPanel.add(label_2);
		
		bookNameLabel = new JLabel("New label");
		bookNameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		bookNameLabel.setBounds(237, 53, 151, 31);
		contentPanel.add(bookNameLabel);
		
		userNameLabel = new JLabel("New label");
		userNameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		userNameLabel.setBounds(237, 105, 151, 31);
		contentPanel.add(userNameLabel);
		
		commentTimeLabel = new JLabel("New label");
		commentTimeLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		commentTimeLabel.setBounds(237, 159, 151, 31);
		contentPanel.add(commentTimeLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 232, 308, 112);
		contentPanel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane.setViewportView(textArea);
		try {
			setCommentInfo(commentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setCommentInfo(int id) throws SQLException{
		CommentDAO commentDAO = new CommentDAO();
		Comment comment = commentDAO.getById(id);
		BooKDAO bKdao = new BooKDAO();
		Book book = bKdao.getById(comment.getBookid());
		bookNameLabel.setText(book.getBookname());
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getById(comment.getUserid());
		userNameLabel.setText(user.getName());
		commentTimeLabel.setText(comment.getCommenttime());
		textArea.setText(comment.getContent());
	}
}
