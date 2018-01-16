package edu.nsu.library.util;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import edu.nsu.library.bean.Book;
import edu.nsu.library.bean.BorrowInfo;
import edu.nsu.library.bean.Category;
import edu.nsu.library.bean.Comment;
import edu.nsu.library.bean.Suggest;
import edu.nsu.library.bean.User;
import edu.nsu.library.service.CategoryService;

public class Models {
	private CategoryService categoryService=null;
	//�����������ģ��
	public CategoryService getCategoryService() {
		if(categoryService==null)
			categoryService = new CategoryService();
		return categoryService;
	}
	public DefaultTreeModel getTreeModel(){
		DefaultMutableTreeNode node = null;
		Category category = new Category();
		category.setCode("0");
		category.setName("ȫ������");
		//�������ĸ��ڵ�
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(category);
//		ArrayList<Category> categories = getCategoryService().get(category);
//		for(int i =0;i<categories.size();i++){
//			node = new DefaultMutableTreeNode(categories.get(i));
//			root.add(node);
//		}
		ArrayList<DefaultMutableTreeNode> nodes1 = getNodes("c1");
		ArrayList<DefaultMutableTreeNode> nodes2 = getNodes("c2");
		ArrayList<DefaultMutableTreeNode> nodes3 = getNodes("c3");
		//�������ڵ㼯�ϼӵ������ڵ�����
		addSubNodes(nodes2, nodes3);
		addSubNodes(nodes1, nodes2);
		//��һ���ڵ㼯�ϼ��뵽���ڵ�����
		addSubNodes(root, nodes1);
		//������������ģ��
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		return treeModel;
	}
	//�������ĳһ���Ľڵ㼯��
	private ArrayList<DefaultMutableTreeNode> getNodes(String level){
		ArrayList<Category> categories = getCategoryService().getCategories(level);
		ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
		DefaultMutableTreeNode node = null;
		for(int i =0;i<categories.size();i++){
			node=new DefaultMutableTreeNode(categories.get(i));
			nodes.add(node);
			
		}
		return nodes;
	}
	//���ӽڵ㼯�ϼ��븸�ڵ�����
	private void addSubNodes(DefaultMutableTreeNode fatherNode,ArrayList<DefaultMutableTreeNode> nodes){
		//��ø��ڵ����д���ķ������
		Category category = (Category) fatherNode.getUserObject();
		Category subCategory = null;
		for(int i =0;i<nodes.size();i++){
			//����ӽڵ����д���ķ������
			subCategory = (Category) nodes.get(i).getUserObject();
			if(category.getCode().equals(subCategory.getFathercode()))
				fatherNode.add(nodes.get(i));
		}
	}
	//���ӽڵ��ϼ��뵽���ڵ㼯����
	private void addSubNodes(ArrayList<DefaultMutableTreeNode> fatherNodes,
			ArrayList<DefaultMutableTreeNode> subNodes){
		for(int i =0;i<fatherNodes.size();i++){
			addSubNodes(fatherNodes.get(i), subNodes);
		}
	}
	//�������ģ��
	public DefaultTableModel getBooksTableModel(ArrayList<Book> books){
		String title[] = new String[]{
				"���","����","����","������","����ʱ��","�۸�"
		};
		Object[][] data = null;
		Object[] row = null;
		//������������ģ�͡�data�ṩ���ݣ�title�ṩ��ͷ
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//�ñ�񲻿ɱ༭
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		if(books==null||books.size()<1)
			return tableModel;
		for(int i =0;i<books.size();i++){
			row = new Object[6];
			row[0] = books.get(i).getId();
			row[1] = books.get(i).getBookname();
			row[2] = books.get(i).getAuthor();
			row[3] = books.get(i).getPress();
			row[4] = books.get(i).getPresstime();
			row[5] = books.get(i).getPrice();
			tableModel.addRow(row);
		}
		return tableModel;
		
	}
	public DefaultTableModel getBorrowInfoTableModel(ArrayList<BorrowInfo> books){
		String title[] = new String[]{
				"���","ͼ����","�û����","��������","�Ƿ�����"
		};
		Object[][] data = null;
		Object[] row = null;
		//������������ģ�͡�data�ṩ���ݣ�title�ṩ��ͷ
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//�ñ�񲻿ɱ༭
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		if(books==null||books.size()<1)
			return tableModel;
		for(int i =0;i<books.size();i++){
			row = new Object[5];
			row[0] = books.get(i).getId();
			row[1] = books.get(i).getBookId();
			row[2] = books.get(i).getUserId();
			row[3] = books.get(i).getBorrowTime();
			row[4] = books.get(i).getRenew();
			tableModel.addRow(row);
		}
		return tableModel;
		
	}
	public DefaultTableModel getSuggestTableModel(ArrayList<Suggest> suggests){
		String title[] = new String[]{
				"�û����","����/����","����ʱ��"
		};
		Object[][] data = null;
		Object[] row = null;
		//������������ģ�͡�data�ṩ���ݣ�title�ṩ��ͷ
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//�ñ�񲻿ɱ༭
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		if(suggests==null||suggests.size()<1)
			return tableModel;
		for(int i =0;i<suggests.size();i++){
			row = new Object[3];
			row[0] = suggests.get(i).getUserid();
			row[1] = suggests.get(i).getContent();
			row[2] = suggests.get(i).getSuggestTime();
			tableModel.addRow(row);
		}
		return tableModel;
		
	}
	public DefaultTableModel getCommentTableModel(ArrayList<Comment> comments){
		String title[] = new String[]{
				"���","�鼮���","�û����","����ʱ��","�鼮����"
		};
		Object[][] data = null;
		Object[] row = null;
		//������������ģ�͡�data�ṩ���ݣ�title�ṩ��ͷ
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//�ñ�񲻿ɱ༭
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		if(comments==null||comments.size()<1)
			return tableModel;
		for(int i =0;i<comments.size();i++){
			row = new Object[5];
			row[0] = comments.get(i).getId();
			row[1] = comments.get(i).getBookid();
			row[2] = comments.get(i).getUserid();
			row[3] = comments.get(i).getCommenttime();
			row[4] = comments.get(i).getContent();
			tableModel.addRow(row);
		}
		return tableModel;
		
	}
	public DefaultComboBoxModel<Category> getComboxBoxModel(Category cate){
		ArrayList<Category> categories = getCategoryService().getChildren(cate);
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<Category>();
		for(int i = 0;i<categories.size();i++){
			comboBoxModel.addElement(categories.get(i));
		}
		return comboBoxModel;
	}
	
	
	
	
	public DefaultTableModel getUserTableModel(ArrayList<User> books){
		String title[] = new String[]{
				"���","�û���","����","�Ա�","role","��ʵ����","����"
		};
		Object[][] data = null;
		Object[] row = null;
		//������������ģ�͡�data�ṩ���ݣ�title�ṩ��ͷ
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//�ñ�񲻿ɱ༭
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		if(books==null||books.size()<1)
			return tableModel;
		for(int i =0;i<books.size();i++){
			row = new Object[7];
			row[0] = books.get(i).getId();
			row[1] = books.get(i).getName();
			row[2] = books.get(i).getPassword();
			row[3] = books.get(i).getSex();
			row[4] = books.get(i).getRole();
			row[5] = books.get(i).getRealname();
			row[6] = books.get(i).getJob();
			tableModel.addRow(row);
		}
		return tableModel;
		
	}
	
}
