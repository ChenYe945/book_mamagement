package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.nsu.library.bean.BorrowInfo;
import edu.nsu.library.bean.Category;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.BorrowInfoDAO;
import edu.nsu.library.service.BookService;
import edu.nsu.library.util.ImageTool;
import edu.nsu.library.util.Models;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.NonWritableChannelException;

public class StudentAndTeacher extends JFrame {
	private Models models = null;
	private BookService bookService = null;
	private ChangePassword changePassword;

	private BookService getBookService() {
		if(bookService==null)
			bookService = new BookService();
		return bookService;
	}

	public Models getModels() {
		if(models==null)
			models = new Models();
		return models;
	}

	private JPanel contentPane;
	private JTable table;
	public int loginId;

	
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StudentAndTeacher frame = new StudentAndTeacher();
//					frame.setVisible(true);
//					frame.setResizable(false);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public StudentAndTeacher() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.MAGENTA);
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7CFB\u7EDF\u8BBE\u7F6E");
		menuBar.add(menu);
		
		JMenuItem menuItem_13 = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePassword = new ChangePassword(getLoginId());
				changePassword.setVisible(true);
				StudentAndTeacher.this.dispose();
			}
		});
		menu.add(menuItem_13);
		
		JMenuItem menuItem_14 = new JMenuItem("\u501F\u9605\u4FE1\u606F");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowBorrowInfo showBorrowInfo = new ShowBorrowInfo(getLoginId());
				showBorrowInfo.setVisible(true);		
			}
		});
		menu.add(menuItem_14);
		
		JMenuItem menuItem_15 = new JMenuItem("\u7559\u8A00");
		menuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuggestionDialog suggestionDialog = new SuggestionDialog(getLoginId());
				suggestionDialog.setVisible(true);
			}
		});
		
		JMenuItem menuItem = new JMenuItem("\u4E2A\u4EBA\u4FE1\u606F");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserInfoDialog userInfoDialog = new UserInfoDialog(getLoginId());
				userInfoDialog.setVisible(true);
			}
		});
		menu.add(menuItem);
		menu.add(menuItem_15);
		
		JMenuItem menuItem_16 = new JMenuItem("\u9000\u51FA");
		menuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentAndTeacher.this.dispose();
			}
		});
		menu.add(menuItem_16);
		
		JMenu searchBookMenu = new JMenu("\u56FE\u4E66\u67E5\u8BE2");
		menuBar.add(searchBookMenu);
		
		JMenuItem fastSearchMenu = new JMenuItem("\u5FEB\u901F\u67E5\u8BE2");
		fastSearchMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 new SearchDialog(getLoginId());
			}
		});
		searchBookMenu.add(fastSearchMenu);
		
		JMenuItem fastSearchMenu1 = new JMenuItem("\u591A\u6761\u4EF6\u67E5\u8BE2");
		searchBookMenu.add(fastSearchMenu1);
		
		JMenu menu_4 = new JMenu("\u56FE\u4E66\u501F\u8FD8");
		menuBar.add(menu_4);
		
		JMenuItem menuItem_9 = new JMenuItem("\u501F\u4E66");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchDialog dialog = new SearchDialog(getLoginId());
				dialog.setVisible(true);
			}
		});
		menu_4.add(menuItem_9);
		
		JMenuItem returnBookMenuItem = new JMenuItem("\u8FD8\u4E66");
		returnBookMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchDialog dialog = new SearchDialog(getLoginId());
				dialog.setVisible(true);
			}
		});
		menu_4.add(returnBookMenuItem);
		
		JMenuItem menuItem_11 = new JMenuItem("\u7EED\u501F");
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RenewDialog renewDialog = new RenewDialog(getLoginId());
				renewDialog.setVisible(true);
				
			}
		});
		menu_4.add(menuItem_11);
		
		JMenuItem menuItem_12 = new JMenuItem("\u8D85\u671F\u5904\u7406");
		menu_4.add(menuItem_12);
		
		JMenu menu_6 = new JMenu("\u70ED\u95E8\u56FE\u4E66");
		menuBar.add(menu_6);
		
		JMenuItem menuItem_22 = new JMenuItem("\u501F\u51FA\u6700\u591A");
		menu_6.add(menuItem_22);
		
		JMenuItem menuItem_23 = new JMenuItem("\u8BC4\u8BBA\u6700\u591A");
		menu_6.add(menuItem_23);
		
		JMenuItem menuItem_24 = new JMenuItem("\u7279\u522B\u63A8\u8350");
		menu_6.add(menuItem_24);
		
		JMenu menu_7 = new JMenu("\u8BC4\u8BBA\u5EFA\u8BAE");
		menuBar.add(menu_7);
		
		JMenuItem menuItem_20 = new JMenuItem("\u6240\u6709\u8BC4\u8BBA");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AllCommentDialog allCommentDialog = new AllCommentDialog(getLoginId());
				allCommentDialog.setVisible(true);
			}
		});
		menu_7.add(menuItem_20);
		
		JMenuItem menuItem_21 = new JMenuItem("\u6240\u6709\u5EFA\u8BAE");
		menuItem_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllSuggestDialog allSuggestDialog = new AllSuggestDialog(getLoginId());
				allSuggestDialog.setVisible(true);
			}
		});
		menu_7.add(menuItem_21);
		
		JMenu menu_8 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_8);
		
		JMenuItem menuItem_17 = new JMenuItem("\u4F7F\u7528\u5E2E\u52A9");
		menu_8.add(menuItem_17);
		
		JMenuItem menuItem_18 = new JMenuItem("\u7248\u6743\u4FE1\u606F");
		menu_8.add(menuItem_18);
		
		JMenuItem menuItem_19 = new JMenuItem("\u8054\u7CFB\u6211\u4EEC");
		menu_8.add(menuItem_19);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 984, 50);
		contentPane.add(toolBar);
		
		JButton browseButton = new JButton("New button");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		//browseButton.setIcon(new ImageIcon("E:\\tushuguanlixitong\\eclipse2\\eclipse\\workspace\\library\\icons\\browse.png"));
		browseButton.setMaximumSize(new Dimension(48, 48));
		browseButton.setMinimumSize(new Dimension(48, 48));
		browseButton.setPreferredSize(new Dimension(48,48));
		ImageTool.setButtonImage(browseButton, "icons/browse.png", 48, 48);
		toolBar.add(browseButton);
		
		JButton settingsButton = new JButton("New button");
		settingsButton.setMaximumSize(new Dimension(48, 48));
		settingsButton.setMinimumSize(new Dimension(48, 48));
		ImageTool.setButtonImage(settingsButton, "icons/settings.png", 48, 48);
		toolBar.add(settingsButton);
		
		JButton searchButton = new JButton("New button");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchDialog(getLoginId());
			}
		});
		searchButton.setMinimumSize(new Dimension(48, 48));
		searchButton.setMaximumSize(new Dimension(48, 48));
		ImageTool.setButtonImage(searchButton, "icons/search.png", 48, 48);
		toolBar.add(searchButton);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.1);
		splitPane.setBounds(0, 60, 984, 621);
		contentPane.add(splitPane);
		
		JScrollPane leftScrollPane = new JScrollPane();
		splitPane.setLeftComponent(leftScrollPane);
		
		JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2){
					DefaultMutableTreeNode node = null;
					//获得用户最后双击的树节点
					node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
					Category cate = (Category)node.getUserObject();
					//重置表的数据模型
					table.setModel(getModels().getBooksTableModel(getBookService().getByCate(cate)));
					table.validate();
				}
					
			}
		});
		tree.setModel(getModels().getTreeModel());
		leftScrollPane.setViewportView(tree);
		JScrollPane rightScrollPane = new JScrollPane();
		splitPane.setRightComponent(rightScrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2){
					//获取双击的行
					int i = table.getSelectedRow();
					//获得第i行第0列的值，即id值
					int id = (Integer) table.getValueAt(i, 0);
					new BookInfoDialog(id);
				}
					
			}
		});
		table.setModel(getModels().getBooksTableModel(getBookService().getAll()));
		rightScrollPane.setViewportView(table);
		setLocationRelativeTo(null);
	}
}
