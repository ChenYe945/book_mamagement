package edu.nsu.library.ui;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.BorrowInfo;
import edu.nsu.library.dao.BooKDAO;
import edu.nsu.library.dao.BorrowInfoDAO;
import edu.nsu.library.service.BookService;
import edu.nsu.library.util.ImageTool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.channels.NonWritableChannelException;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BorrowBookInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int bookId;
	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	private JLabel bookNameLabel;
	private JLabel authorLabel;
	private JLabel pressLabel;
	private JLabel pressTimeLabel;
	private JLabel lastCountLabel;
	private JSpinner borrowSpinner;
	private JButton btnNewButton;
	private BookService bookService=null;
	private int lastCount;
	private int borrowCount;
	public BookService getBookService() {
		if(bookService==null)
			bookService=new BookService();
		return bookService;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public BorrowBookInfo(int Bid,int uId) {
		bookId = Bid;
		userId = uId;
		setTitle("\u501F\u4E66\u9875\u9762");
		setBounds(100, 100, 576, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u4E66    \u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(53, 33, 114, 31);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u4F5C    \u8005\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(53, 82, 114, 31);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u51FA \u7248 \u793E\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(53, 123, 114, 31);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("\u51FA\u7248\u65F6\u95F4\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(53, 175, 114, 31);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("\u5269\u4F59\u6570\u91CF\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		label_4.setBounds(53, 226, 114, 31);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("\u501F\u51FA\u6570\u91CF\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 18));
		label_5.setBounds(53, 281, 114, 31);
		contentPanel.add(label_5);
		
		borrowSpinner = new JSpinner();
		borrowSpinner.setFont(new Font("宋体", Font.PLAIN, 16));
		borrowSpinner.setBounds(177, 281, 77, 31);
		contentPanel.add(borrowSpinner);
		
		bookNameLabel = new JLabel("New label");
		bookNameLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		bookNameLabel.setBounds(177, 33, 160, 31);
		contentPanel.add(bookNameLabel);
		
		authorLabel = new JLabel("New label");
		authorLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		authorLabel.setBounds(177, 82, 160, 31);
		contentPanel.add(authorLabel);
		
		pressLabel = new JLabel("New label");
		pressLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		pressLabel.setBounds(177, 123, 160, 31);
		contentPanel.add(pressLabel);
		
		pressTimeLabel = new JLabel("New label");
		pressTimeLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		pressTimeLabel.setBounds(177, 175, 160, 31);
		contentPanel.add(pressTimeLabel);
		
		lastCountLabel = new JLabel("New label");
		lastCountLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lastCountLabel.setBounds(177, 226, 160, 31);
		contentPanel.add(lastCountLabel);
		
		btnNewButton = new JButton("\u786E\u8BA4\u501F\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					changeBookLastCounts(getBookId());									
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(364, 246, 121, 40);
		contentPanel.add(btnNewButton);
		setBookInfo(getBookId());
	}
	public void setBookInfo(int id){
		Book book=getBookService().getById(id);
		bookNameLabel.setText(book.getBookname());
		authorLabel.setText(book.getAuthor());
		pressLabel.setText(book.getPress());
		pressTimeLabel.setText(book.getPresstime());
		lastCountLabel.setText(Integer.toString(book.getBookcounts()));
	}
	public void changeBookLastCounts(int id) throws SQLException{
		Book book2 = new Book();
		Book book=getBookService().getById(id);
		BooKDAO booKDAO = new BooKDAO();
		lastCount =(int)book.getBookcounts()-(int)borrowSpinner.getValue();
		borrowCount = book.getBorrowcounts()+(int)borrowSpinner.getValue();
		if(lastCount<0){
			JOptionPane.showMessageDialog
			(null, "所借图书数量不足！","错误信息",JOptionPane.ERROR_MESSAGE);
		}else {
			book2.setBookcounts(lastCount);
			book2.setBorrowcounts(borrowCount);
			book2.setId(id);
			booKDAO.changeBookInfo(book2);
			BorrowInfo borrowInfo = new BorrowInfo();
			borrowInfo.setBookId(getBookId());
			borrowInfo.setUserId(getUserId());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date(System.currentTimeMillis());
			String str = formatter.format(now);
			borrowInfo.setBorrowTime(str);
			borrowInfo.setRenew(0);
			BorrowInfoDAO borrowInfoDAO = new BorrowInfoDAO();
			borrowInfoDAO.addBorrowInfo(borrowInfo);
			JOptionPane.showMessageDialog
			(null, "借书成功完成！","错误信息",JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}

	}
}
