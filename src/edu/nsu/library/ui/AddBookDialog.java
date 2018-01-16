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
import edu.nsu.library.service.BookService;
import edu.nsu.library.util.ImageTool;
import edu.nsu.library.util.Models;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField authorTextField;
	private JTextField priceTextField;
	private JTextField bookNameTextField;
	private JTextField pressTextField;
	private JTextField pressTimeTextField;
	private JTextField ISBNTextField;
	private JTextArea textArea;
	private JSpinner spinner;
	private JComboBox c1ComboBox;
	private JComboBox c2ComboBox;
	private JComboBox c3ComboBox;
	private Models models =null;
	private BookService bookService = null;
	private String filePath = "";
	
	public BookService getBookService() {
		if(bookService==null)
			bookService = new BookService();
		return bookService;
	}

	public Models getModels() {
		if(models==null)
			models = new Models();
		return models;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddBookDialog dialog = new AddBookDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddBookDialog() {
		setTitle("\u6DFB\u52A0\u56FE\u4E66");
		setBounds(100, 100, 960, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u4E66    \u540D\uFF1A");
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setBounds(185, 27, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u4F5C    \u8005\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 86, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u51FA \u7248 \u793E\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 145, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u4EF7    \u683C\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 204, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u51FA\u7248\u65F6\u95F4\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 263, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel lblISB = new JLabel("I S B N\uFF1A");
			lblISB.setHorizontalAlignment(SwingConstants.RIGHT);
			lblISB.setFont(new Font("宋体", Font.PLAIN, 20));
			lblISB.setBounds(185, 325, 147, 32);
			contentPanel.add(lblISB);
		}
		{
			JLabel label = new JLabel("\u6570    \u91CF\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 384, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u56FE\u4E66\u5206\u7C7B\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 440, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u4E00\u7EA7\u5206\u7C7B\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 498, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u4E8C\u7EA7\u5206\u7C7B\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 552, 147, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u4E09\u7EA7\u5206\u7C7B\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(185, 616, 147, 32);
			contentPanel.add(label);
		}
		{
			authorTextField = new JTextField();
			authorTextField.setColumns(10);
			authorTextField.setBounds(342, 86, 255, 32);
			contentPanel.add(authorTextField);
		}
		{
			priceTextField = new JTextField();
			priceTextField.setColumns(10);
			priceTextField.setBounds(342, 204, 255, 32);
			contentPanel.add(priceTextField);
		}
		{
			bookNameTextField = new JTextField();
			bookNameTextField.setColumns(10);
			bookNameTextField.setBounds(342, 27, 255, 32);
			contentPanel.add(bookNameTextField);
		}
		{
			pressTextField = new JTextField();
			pressTextField.setToolTipText("\u683C\u5F0F\u5982\uFF1A2015-08-22");
			pressTextField.setColumns(10);
			pressTextField.setBounds(342, 145, 255, 32);
			contentPanel.add(pressTextField);
		}
		{
			pressTimeTextField = new JTextField();
			pressTimeTextField.setFont(new Font("宋体", Font.PLAIN, 18));
			pressTimeTextField.setText("2015-08");
			pressTimeTextField.setColumns(10);
			pressTimeTextField.setBounds(342, 263, 255, 32);
			contentPanel.add(pressTimeTextField);
		}
		{
			ISBNTextField = new JTextField();
			ISBNTextField.setFont(new Font("宋体", Font.PLAIN, 18));
			ISBNTextField.setText("0.0");
			ISBNTextField.setColumns(10);
			ISBNTextField.setBounds(342, 325, 255, 32);
			contentPanel.add(ISBNTextField);
		}
		
		c1ComboBox = new JComboBox();
		Category category = new Category();
		category.setCode("0");
		c1ComboBox.setModel(getModels().getComboxBoxModel(category));
		//设置第一项为默认选项
		c1ComboBox.setSelectedIndex(0);
		category = (Category) c1ComboBox.getSelectedItem();
		
		
		c1ComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				c1ChangeAction();
			}
		});
		c1ComboBox.setFont(new Font("宋体", Font.BOLD, 17));
		c1ComboBox.setBounds(342, 498, 184, 38);
		contentPanel.add(c1ComboBox);
		
		c2ComboBox = new JComboBox();
		c2ComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				c2ChangeAction();
			}
		});
		c2ComboBox.setFont(new Font("宋体", Font.BOLD, 17));
		c2ComboBox.setBounds(342, 552, 184, 38);
		contentPanel.add(c2ComboBox);
		c2ComboBox.setModel(getModels().getComboxBoxModel(category));
		c2ComboBox.setSelectedIndex(0);
		category = (Category) c2ComboBox.getSelectedItem();
		
		c3ComboBox = new JComboBox();
		c3ComboBox.setFont(new Font("宋体", Font.BOLD, 17));
		c3ComboBox.setBounds(342, 616, 184, 38);
		contentPanel.add(c3ComboBox);
		c3ComboBox.setModel(getModels().getComboxBoxModel(category));
		c3ComboBox.setSelectedIndex(0);
		
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("宋体", Font.PLAIN, 29));
		label.setBounds(607, 27, 54, 32);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.ORANGE);
		label_1.setFont(new Font("宋体", Font.PLAIN, 29));
		label_1.setBounds(607, 86, 54, 32);
		contentPanel.add(label_1);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spinner.setFont(new Font("宋体", Font.PLAIN, 17));
		spinner.setBounds(342, 384, 87, 38);
		contentPanel.add(spinner);
		
		JLabel label_2 = new JLabel("\u672C");
		label_2.setFont(new Font("宋体", Font.PLAIN, 23));
		label_2.setBounds(435, 384, 54, 38);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("\u5143");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(607, 204, 54, 32);
		contentPanel.add(label_3);
		
		JLabel coverLabel = new JLabel("\u56FE\u7247");
		coverLabel.setBounds(724, 41, 210, 278);
		contentPanel.add(coverLabel);
		
		JLabel label_5 = new JLabel("\u7B80\u8981\u63CF\u8FF0\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(531, 384, 147, 32);
		contentPanel.add(label_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(556, 412, 380, 190);
		contentPanel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton submitButton = new JButton("\u63D0\u4EA4");
		submitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
			if(getBookService().add(getInput()))
				AddBookDialog.this.dispose();
			}
		});
		submitButton.setBounds(556, 623, 93, 32);
		contentPanel.add(submitButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setBounds(672, 623, 93, 32);
		contentPanel.add(resetButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(792, 623, 93, 32);
		contentPanel.add(cancelButton);
		
		JButton chooseCoverButton = new JButton("\u9009\u62E9\u5C01\u9762\u56FE\u7247......");
		chooseCoverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建文件选择器
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
		chooseCoverButton.setBounds(772, 332, 147, 23);
		contentPanel.add(chooseCoverButton);
		this.setLocationRelativeTo(null);
	}
	private Book getInput(){
		
		Book book = new Book();
		book.setBookname(bookNameTextField.getText().trim());
		book.setAuthor(authorTextField.getText().trim());
		if(book.getBookname()==null||book.getBookname().equals("")){
			JOptionPane.showMessageDialog
			(null, "书名不能为空！","错误信息",JOptionPane.ERROR_MESSAGE);
			return null;
			
		}
		if(book.getAuthor()==null||book.getAuthor().equals("")){
			JOptionPane.showMessageDialog
			(null, "作者不能为空！","错误信息",JOptionPane.ERROR_MESSAGE);
			return null;
			
		}
		book.setPress(pressTextField.getText().trim());
		book.setPresstime(pressTimeTextField.getText().trim());
		book.setIsbn(ISBNTextField.getText().trim());
		book.setPrice(Float.parseFloat(priceTextField.getText().trim()));
		book.setIntrouction(textArea.getText());
		book.setBookcounts((int) spinner.getValue());
		//获取用户选择的三级分类
		Category cate = (Category) c3ComboBox.getSelectedItem();
		book.setC3code(cate.getCode());
		book.setCover(filePath);
		if(!InputChecker.isPrice(priceTextField.getText().trim())){
			JOptionPane.showMessageDialog
			(null, "价格非法！","错误信息",JOptionPane.ERROR_MESSAGE);
			return null;
		}
			if(!InputChecker.isDate(pressTimeTextField.getText().trim())){
				JOptionPane.showMessageDialog
				(null, "出版时间非法！","错误信息",JOptionPane.ERROR_MESSAGE);
				return null;
			}
		return book;
	}
	//二级分类选项发生改变时所执行的操作
	private void c2ChangeAction(){
		Category cate = (Category) c2ComboBox.getSelectedItem();
		//根据用户选择的二级分类重新设置三级分类的数据模型
		c3ComboBox.setModel(getModels().getComboxBoxModel(cate));
		c3ComboBox.validate();
	}
	private void c1ChangeAction(){
		Category cate = (Category) c1ComboBox.getSelectedItem();
		//根据用户选择的二级分类重新设置三级分类的数据模型
		c2ComboBox.setModel(getModels().getComboxBoxModel(cate));
		c2ComboBox.validate();
		c2ChangeAction();
	}
}
