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
import edu.nsu.library.bean.Comment;
import edu.nsu.library.dao.CommentDAO;
import edu.nsu.library.service.BookService;
import edu.nsu.library.util.Models;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

public class SearchDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField keywordTextField;
	private JTable table;
	private JScrollPane scrollPane;
	private BookService bookService=null;
	private Models models=null;
	private int selectRow = 0;
	public int bookId;
	private int userId;
	private JButton BorrowButton;
	private JButton returnBookButton;

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
		String keyword=keywordTextField.getText().trim();
		ArrayList<Book> books=getBookService().
				getByName(keyword);
		table.setModel(getModels().
				getBooksTableModel(books));
		table.validate();
		
	}
//	public static void main(String[] args) {
//		try {
//			SearchDialog dialog = new SearchDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			dialog.setResizable(false);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public SearchDialog(int id) {
		userId = id;
		
		setTitle("\u56FE\u4E66\u67E5\u8BE2");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u67E5\u8BE2");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 32));
		lblNewLabel.setBounds(330, 10, 146, 62);
		contentPanel.add(lblNewLabel);
		
		keywordTextField = new JTextField();
		keywordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					getData();
				}
			}
		});
		keywordTextField.setFont(new Font("宋体", Font.PLAIN, 24));
		keywordTextField.setBounds(110, 82, 427, 46);
		contentPanel.add(keywordTextField);
		keywordTextField.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getData();
				
			}
		});
		
		searchButton.setFont(new Font("宋体", Font.PLAIN, 28));
		searchButton.setBounds(548, 80, 93, 46);
		contentPanel.add(searchButton);
		
		scrollPane = new JScrollPane();
	
		scrollPane.setBounds(32, 138, 715, 296);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					//获得用户双击的行
					int i=table.getSelectedRow();
					//获得第i行第0列的值，即id值
					int id=(Integer)table.getValueAt(i, 0);
					new BookInfoDialog(id);
					
				}
				if(e.getClickCount()==1){
					int j = table.getSelectedRow();
					bookId = (Integer)table.getValueAt(j, 0);
					
				}
					
				
			}
		});
		scrollPane.setViewportView(table);
		
		BorrowButton = new JButton("\u501F\u6B64\u4E66");
		BorrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bookId==0||bookId<0){
					JOptionPane.showMessageDialog
					(null, "请先选择图书！","错误信息",JOptionPane.ERROR_MESSAGE);
				}else {
					BorrowBookInfo bookInfo = new BorrowBookInfo(bookId,userId);
					bookInfo.setVisible(true);
				}
				
			}
		});
		BorrowButton.setForeground(Color.BLUE);
		BorrowButton.setFont(new Font("宋体", Font.BOLD, 16));
		BorrowButton.setBounds(238, 467, 115, 39);
		contentPanel.add(BorrowButton);
		
		returnBookButton = new JButton("\u8FD8\u4E66");
		returnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bookId==0||bookId<0){
					JOptionPane.showMessageDialog
					(null, "请先选择图书！","错误信息",JOptionPane.ERROR_MESSAGE);
				}else {
					ReturnBookDialog returnBookDialog = new ReturnBookDialog(bookId, userId);
					returnBookDialog.setVisible(true);
				}
				
			}
		});
		returnBookButton.setForeground(Color.CYAN);
		returnBookButton.setFont(new Font("宋体", Font.PLAIN, 20));
		returnBookButton.setBounds(383, 467, 115, 39);
		contentPanel.add(returnBookButton);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
