package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.Book;
import edu.nsu.library.service.BookService;
import edu.nsu.library.util.ImageTool;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class BookInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel bookNameLabel;
	private JLabel authorLabel;
	private JLabel pressLabel;
	private JLabel presstimeLabel;
	private JLabel priceLabel;
	private JLabel bookcountsLabel;
	private JLabel isbnLabel;
	private JLabel borrowcountsLabel;
	private JLabel coverLabel;
	private BookService bookService=null;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;

	public BookService getBookService() {
		if(bookService==null)
			bookService=new BookService();
		return bookService;
	}

	/**
	 * Create the dialog.
	 */
	public BookInfoDialog(int id) {
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
			coverLabel.setBounds(524, 97, 264, 363);
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
			authorLabel = new JLabel("\u4F5C    \u8005    \u503C");
			authorLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			authorLabel.setBounds(209, 145, 331, 38);
			contentPanel.add(authorLabel);
		}
		{
			pressLabel = new JLabel("\u51FA \u7248 \u793E \u503C");
			pressLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			pressLabel.setBounds(209, 193, 331, 38);
			contentPanel.add(pressLabel);
		}
		{
			presstimeLabel = new JLabel("\u51FA\u7248\u65F6\u95F4\u503C");
			presstimeLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			presstimeLabel.setBounds(209, 241, 331, 38);
			contentPanel.add(presstimeLabel);
		}
		{
			priceLabel = new JLabel("\u4EF7 \u683C \u503C");
			priceLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			priceLabel.setBounds(209, 289, 331, 38);
			contentPanel.add(priceLabel);
		}
		{
			bookcountsLabel = new JLabel("\u6570 \u91CF \u503C");
			bookcountsLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			bookcountsLabel.setBounds(209, 340, 331, 38);
			contentPanel.add(bookcountsLabel);
		}
		{
			isbnLabel = new JLabel("ISBN\u503C");
			isbnLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			isbnLabel.setBounds(209, 388, 331, 38);
			contentPanel.add(isbnLabel);
		}
		{
			borrowcountsLabel = new JLabel("\u501F\u51FA\u6B21\u6570\u503C");
			borrowcountsLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			borrowcountsLabel.setBounds(209, 436, 331, 38);
			contentPanel.add(borrowcountsLabel);
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
				textArea.setLineWrap(true);
				textArea.setEditable(false);
				scrollPane.setViewportView(textArea);
			}
		}
		{
			JButton closeButton = new JButton("\u5173\u95ED");
			closeButton.setForeground(Color.BLUE);
			closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BookInfoDialog.this.dispose();
				}
			});
			closeButton.setFont(new Font("宋体", Font.PLAIN, 24));
			closeButton.setBounds(662, 538, 126, 42);
			contentPanel.add(closeButton); 
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
	authorLabel.setText(book.getAuthor());
	pressLabel.setText(book.getPress());
	presstimeLabel.setText(book.getPresstime());
	priceLabel.setText(Float.toString(book.getPrice()));
	isbnLabel.setText(book.getIsbn());
	bookcountsLabel.setText(
			Integer.toString(book.getBookcounts()));
	borrowcountsLabel.setText(
			Integer.toString(book.getBorrowcounts()));
	textArea.setText(book.getIntrouction());
	String cover="covers/"+book.getCover();
	File f=new File(cover);
	if(!f.exists())
		cover="covers/default.jpg";
	ImageTool.setLabelImage(coverLabel, cover);
}
}
