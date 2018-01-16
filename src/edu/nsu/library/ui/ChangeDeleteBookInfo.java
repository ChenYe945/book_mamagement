package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.Category;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.BooKDAO;
import edu.nsu.library.dao.UserDAO;
import edu.nsu.library.service.BookService;
import edu.nsu.library.util.ImageTool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class ChangeDeleteBookInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel bookNameLabel;
	private JLabel isbnLabel;
	private JLabel coverLabel;
	private BookService bookService=null;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JTextField authorTextField;
	private JTextField pressTextField;
	private JTextField pressTimeTextField;
	private JTextField priceTextField;
	private JTextField bookCountsTextField;
	private JTextField borrowCountsTextFiled;
	private String filePath = "";
	private JButton DeleteButton;
	private JButton UpdateButton;
//	private String bookName;
//	private String bookAuthor;
//	private String bookPress;
//	private String bookPressTime;
//	private float bookPrice;
//	private int bookCounts;
//	private String bookIsbn;
//	private int bookBorrowCounts;

	public BookService getBookService() {
		if(bookService==null)
			bookService=new BookService();
		return bookService;
	}

	/**
	 * Create the dialog.
	 */
	public ChangeDeleteBookInfo(int id) {
		setTitle("\u56FE\u4E66\u8BE6\u7EC6\u4FE1\u606F");
		setBounds(100, 100, 960, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u4FE1\u606F");
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
			lblNewLabel.setBounds(353, 22, 119, 47);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("\u4E66    \u540D\uFF1A");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
			lblNewLabel_1.setBounds(36, 97, 137, 38);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel label = new JLabel("\u4F5C    \u8005\uFF1A");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(36, 145, 137, 38);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u51FA \u7248 \u793E\uFF1A");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(36, 193, 137, 38);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u51FA\u7248\u65F6\u95F4\uFF1A");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(36, 241, 137, 38);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u4EF7\u683C(\u5143)\uFF1A");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(36, 289, 137, 38);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u6570\u91CF(\u672C)\uFF1A");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(36, 340, 137, 38);
			contentPanel.add(label);
		}
		{
			JLabel lblISbN = new JLabel("I S B N \uFF1A");
			lblISbN.setHorizontalAlignment(SwingConstants.TRAILING);
			lblISbN.setFont(new Font("宋体", Font.BOLD, 20));
			lblISbN.setBounds(36, 388, 137, 38);
			contentPanel.add(lblISbN);
		}
		{
			bookNameLabel = new JLabel("\u4E66    \u540D    \u503C");
			bookNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			bookNameLabel.setBounds(209, 97, 331, 38);
			contentPanel.add(bookNameLabel);
		}
		{
			coverLabel = new JLabel("\u5C01\u9762\u56FE\u7247");
			coverLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			coverLabel.setBounds(524, 97, 264, 251);
			contentPanel.add(coverLabel);
		}
		{
			JLabel label = new JLabel("\u501F\u51FA\u6B21\u6570\uFF1A");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(36, 436, 137, 38);
			contentPanel.add(label);
		}
		{
			isbnLabel = new JLabel("ISBN\u503C");
			isbnLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			isbnLabel.setBounds(209, 388, 331, 38);
			contentPanel.add(isbnLabel);
		}
		{
			JLabel label = new JLabel("\u56FE\u4E66\u7B80\u4ECB\uFF1A");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(36, 484, 137, 38);
			contentPanel.add(label);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(209, 495, 334, 180);
			contentPanel.add(scrollPane);
			{
				textArea = new JTextArea();
				textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
				textArea.setLineWrap(true);
				textArea.setEditable(false);
				scrollPane.setViewportView(textArea);
			}
		}
		{
			JButton closeButton = new JButton("\u5173\u95ED");
			closeButton.setForeground(Color.CYAN);
			closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ChangeDeleteBookInfo.this.dispose();
				}
			});
			closeButton.setFont(new Font("宋体", Font.PLAIN, 24));
			closeButton.setBounds(763, 638, 119, 37);
			contentPanel.add(closeButton); 
		}
		
		authorTextField = new JTextField();
		authorTextField.setFont(new Font("宋体", Font.PLAIN, 16));
		authorTextField.setBounds(193, 145, 210, 38);
		contentPanel.add(authorTextField);
		authorTextField.setColumns(10);
		
		pressTextField = new JTextField();
		pressTextField.setFont(new Font("宋体", Font.PLAIN, 16));
		pressTextField.setColumns(10);
		pressTextField.setBounds(193, 193, 210, 38);
		contentPanel.add(pressTextField);
		
		pressTimeTextField = new JTextField();
		pressTimeTextField.setFont(new Font("宋体", Font.PLAIN, 16));
		pressTimeTextField.setColumns(10);
		pressTimeTextField.setBounds(193, 241, 210, 38);
		contentPanel.add(pressTimeTextField);
		
		priceTextField = new JTextField();
		priceTextField.setFont(new Font("宋体", Font.PLAIN, 16));
		priceTextField.setColumns(10);
		priceTextField.setBounds(193, 289, 210, 38);
		contentPanel.add(priceTextField);
		
		bookCountsTextField = new JTextField();
		bookCountsTextField.setFont(new Font("宋体", Font.PLAIN, 16));
		bookCountsTextField.setColumns(10);
		bookCountsTextField.setBounds(193, 340, 210, 38);
		contentPanel.add(bookCountsTextField);
		
		borrowCountsTextFiled = new JTextField();
		borrowCountsTextFiled.setFont(new Font("宋体", Font.PLAIN, 16));
		borrowCountsTextFiled.setColumns(10);
		borrowCountsTextFiled.setBounds(193, 436, 210, 38);
		contentPanel.add(borrowCountsTextFiled);
		
		JButton chooseCoverButten = new JButton("\u9009\u62E9\u56FE\u7247");
		chooseCoverButten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				//显示文件选择框并获得返回值
				int result = fileChooser.showOpenDialog(null);
				if(result==fileChooser.APPROVE_OPTION){
					//获得用户选择的文件
					File f = fileChooser.getSelectedFile();
					filePath = f.getPath();
					if(!f.exists()){
						JOptionPane.showMessageDialog
						(null, "未选择图片！","错误信息",JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(!InputChecker.isPicture(filePath)){
						JOptionPane.showMessageDialog
						(null, "请选择图片文件！","错误信息",JOptionPane.ERROR_MESSAGE);
						return;
					}
					ImageTool.setLabelImage(coverLabel, filePath);
				}
			}
		});
		chooseCoverButten.setBounds(544, 358, 191, 38);
		contentPanel.add(chooseCoverButten);
		{
			DeleteButton = new JButton("\u786E\u8BA4\u5220\u9664");
			DeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						DeleteBook(id);
						JOptionPane.showMessageDialog
						(null, "删除完成！","错误信息",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			DeleteButton.setFont(new Font("宋体", Font.PLAIN, 18));
			DeleteButton.setForeground(Color.RED);
			DeleteButton.setBounds(763, 574, 119, 38);
			contentPanel.add(DeleteButton);
		}
		{
			UpdateButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
			UpdateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getInfoAndUpdate(id);
					JOptionPane.showMessageDialog
					(null, "修改成功！","提示信息",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			});
			UpdateButton.setForeground(Color.BLUE);
			UpdateButton.setFont(new Font("宋体", Font.PLAIN, 18));
			UpdateButton.setBounds(763, 518, 119, 38);
			contentPanel.add(UpdateButton);
		}
		setBookInfo(id);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setResizable(false);
	}
	public void setBookInfo(int id){
		Book book=getBookService().getById(id);
		bookNameLabel.setText(book.getBookname());
		authorTextField.setText(book.getAuthor());
		pressTextField.setText(book.getPress());
		pressTimeTextField.setText(book.getPresstime());
		priceTextField.setText(Float.toString(book.getPrice()));
		bookCountsTextField.setText(Integer.toString(book.getBookcounts()));
		isbnLabel.setText(book.getIsbn());
		borrowCountsTextFiled.setText(Integer.toString(book.getBorrowcounts()));
		textArea.setText(book.getIntrouction());
		String cover="covers/"+book.getCover();
		File f=new File(cover);
		if(!f.exists())
			cover="covers/default.jpg";
		ImageTool.setLabelImage(coverLabel, cover);
	}
	public void getInfoAndUpdate(int id){
		Book book = new Book();
		book.setBookname(bookNameLabel.getText().trim());
		book.setAuthor(authorTextField.getText().trim());
		if(book.getBookname()==null||book.getBookname().equals("")){
			JOptionPane.showMessageDialog
			(null, "书名不能为空！","错误信息",JOptionPane.ERROR_MESSAGE);	
		}
		if(book.getAuthor()==null||book.getAuthor().equals("")){
			JOptionPane.showMessageDialog
			(null, "作者不能为空！","错误信息",JOptionPane.ERROR_MESSAGE);
		}
		book.setPress(pressTextField.getText().trim());
		book.setPresstime(pressTimeTextField.getText().trim());
		book.setIsbn(isbnLabel.getText().trim());
		book.setPrice(Float.parseFloat(priceTextField.getText().trim()));
		book.setIntrouction(textArea.getText());
		book.setBookcounts(Integer.parseInt(bookCountsTextField.getText().trim()));
		book.setBorrowcounts(Integer.parseInt(borrowCountsTextFiled.getText().trim()));
		//获取用户选择的三级分类
		book.setCover(filePath);
		if(!InputChecker.isPrice(priceTextField.getText().trim())){
			JOptionPane.showMessageDialog
			(null, "价格非法！","错误信息",JOptionPane.ERROR_MESSAGE);
			
		}
			if(!InputChecker.isDate(pressTimeTextField.getText().trim())){
				JOptionPane.showMessageDialog
				(null, "出版时间非法！","错误信息",JOptionPane.ERROR_MESSAGE);
				
			}
			book.setId(id);
		BooKDAO bKdao = new BooKDAO();
		try {
			
			bKdao.modify(book);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public void DeleteBook(int id) throws SQLException{
		Book b = new Book();
		BooKDAO booKDAO = new BooKDAO();
		b.setId(id);
		booKDAO.delete(b);
		
	}
	
}
