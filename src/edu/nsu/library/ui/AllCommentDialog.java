package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.BorrowInfo;
import edu.nsu.library.bean.Comment;
import edu.nsu.library.bean.Suggest;
import edu.nsu.library.dao.BorrowInfoDAO;
import edu.nsu.library.dao.CommentDAO;
import edu.nsu.library.dao.SuggestDAO;
import edu.nsu.library.service.BookService;
import edu.nsu.library.util.Models;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectableChannel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class AllCommentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private BookService bookService=null;
	private Models models=null;
	private int selectRow = 0;
	public int borrowId;
	private int userId;
	private JButton btnNewButton;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Models getModels() {
		if(models==null)
			models=new Models();
		return models;
	}

	public BookService getBookService() {
		if(bookService==null)
			bookService=new BookService();
		return bookService;
	}

	/**
	 * Launch the application.
	 */
	public void getData(){
		CommentDAO commentDAO = new CommentDAO();
		ArrayList<Comment> comments= commentDAO.getAll();
		table.setModel(getModels().getCommentTableModel(comments));
		table.validate();
		
	}
	/**
	 * Create the dialog.
	 */
	public AllCommentDialog(int id) {
		userId = id;
		setTitle("\u501F\u4E66\u4FE1\u606F");
		setBounds(100, 100, 600, 424);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4E66\u7C4D\u8BC4\u8BBA");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 26));
		lblNewLabel.setBounds(231, 10, 146, 62);
		contentPanel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
	
		scrollPane.setBounds(24, 91, 539, 231);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					//获得用户双击的行
					int i=table.getSelectedRow();
					//获得第i行第0列的值，即id值
					int bid=(Integer)table.getValueAt(i, 0);
					CommentInfo commentInfo = new CommentInfo(bid);
					commentInfo.setVisible(true);
				}
				if(e.getClickCount()==1){
					int j = table.getSelectedRow();
					borrowId = (Integer)table.getValueAt(j, 0);	
				}
			}
		});
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("\u67E5\u770B\u8BC4\u8BBA\u4FE1\u606F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CommentInfo commentInfo = new CommentInfo(borrowId);
				commentInfo.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(190, 332, 157, 44);
		contentPanel.add(btnNewButton);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		getData();
	}
}
