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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class ChangeAndDeleteBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField keywordTextField;
	private JTable table;
	private JScrollPane scrollPane;
	private BookService bookService=null;
	private Models models=null;

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
	public static void main(String[] args) {
		try {
			ChangeAndDeleteBook dialog = new ChangeAndDeleteBook();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChangeAndDeleteBook() {
		setTitle("\u56FE\u4E66\u67E5\u8BE2");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u9700\u8981\u4FEE\u6539\u548C\u5220\u9664\u7684\u56FE\u4E66");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setBounds(295, 10, 310, 62);
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
		keywordTextField.setBounds(119, 82, 343, 46);
		contentPanel.add(keywordTextField);
		keywordTextField.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getData();
				
			}
		});
		
		searchButton.setFont(new Font("宋体", Font.PLAIN, 28));
		searchButton.setBounds(472, 82, 93, 46);
		contentPanel.add(searchButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 138, 726, 413);
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
					new ChangeDeleteBookInfo(id);
					table.setModel(getModels().getBooksTableModel(getBookService().getAll()));
					table.validate();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("*\u8F93\u5165\u9700\u8981\u4FEE\u6539\u7684\u56FE\u4E66");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(575, 82, 151, 46);
		contentPanel.add(lblNewLabel_1);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
