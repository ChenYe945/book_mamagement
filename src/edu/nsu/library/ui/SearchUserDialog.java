package edu.nsu.library.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.User;
import edu.nsu.library.service.BookService;
import edu.nsu.library.service.UserService;
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

public class SearchUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField keywordTextField;
	private JTable table;
	private JScrollPane scrollPane;
	private UserService userService=null;
	private Models models=null;
	private JLabel lblNewLabel_1;

	public Models getModels() {
		if(models==null)
			models=new Models();
		return models;
	}


	public UserService getUserService(){
		if(userService==null)
			userService = new UserService();
		return userService;
	}

	/**
	 * Launch the application.
	 */
	public void getData(){
		String keyword=keywordTextField.getText().trim();
		ArrayList<User> users=getUserService().
				getByName(keyword);
		table.setModel(getModels().getUserTableModel(users));		
		table.validate();
		
	}
	public static void main(String[] args) {
		try {
			SearchUserDialog dialog = new SearchUserDialog();
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
	public SearchUserDialog() {
		setTitle("\u7528\u6237\u67E5\u8BE2");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u9700\u8981\u4FEE\u6539\u7684\u7528\u6237");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(330, 10, 193, 62);
		contentPanel.add(lblNewLabel);
		
		keywordTextField = new JTextField();
		keywordTextField.setToolTipText("\u8BF7\u8F93\u5165\u9700\u8981\u67E5\u8BE2\u7684\u7528\u6237\u540D");
		keywordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					getData();
				}
			}
		});
		keywordTextField.setFont(new Font("宋体", Font.PLAIN, 24));
		keywordTextField.setBounds(101, 82, 316, 46);
		contentPanel.add(keywordTextField);
		keywordTextField.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getData();
				
			}
		});
		
		searchButton.setFont(new Font("宋体", Font.PLAIN, 28));
		searchButton.setBounds(420, 82, 93, 46);
		contentPanel.add(searchButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 138, 726, 295);
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
					new changeUserInfoDialog(id);
					table.setModel(getModels().getUserTableModel(getUserService().getAllUser()));		
					table.validate();
					
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel("*\u8BF7\u8F93\u5165\u9700\u8981\u4FEE\u6539\u7684\u7528\u6237\u540D");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(523, 82, 166, 51);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("*\u8BF7\u53CC\u51FB\u8981\u4FEE\u6539\u6216\u8005\u5220\u9664\u7684\u7528\u6237");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(66, 461, 181, 46);
		contentPanel.add(lblNewLabel_2);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
