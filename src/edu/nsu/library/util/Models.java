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
	//获得树的数据模型
	public CategoryService getCategoryService() {
		if(categoryService==null)
			categoryService = new CategoryService();
		return categoryService;
	}
	public DefaultTreeModel getTreeModel(){
		DefaultMutableTreeNode node = null;
		Category category = new Category();
		category.setCode("0");
		category.setName("全部分类");
		//创建树的根节点
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(category);
//		ArrayList<Category> categories = getCategoryService().get(category);
//		for(int i =0;i<categories.size();i++){
//			node = new DefaultMutableTreeNode(categories.get(i));
//			root.add(node);
//		}
		ArrayList<DefaultMutableTreeNode> nodes1 = getNodes("c1");
		ArrayList<DefaultMutableTreeNode> nodes2 = getNodes("c2");
		ArrayList<DefaultMutableTreeNode> nodes3 = getNodes("c3");
		//将三级节点集合加到二级节点下面
		addSubNodes(nodes2, nodes3);
		addSubNodes(nodes1, nodes2);
		//将一级节点集合加入到根节点下面
		addSubNodes(root, nodes1);
		//创建树的数据模型
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		return treeModel;
	}
	//获得树的某一级的节点集合
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
	//将子节点集合加入父节点下面
	private void addSubNodes(DefaultMutableTreeNode fatherNode,ArrayList<DefaultMutableTreeNode> nodes){
		//获得父节点所有储存的分类对象
		Category category = (Category) fatherNode.getUserObject();
		Category subCategory = null;
		for(int i =0;i<nodes.size();i++){
			//获得子节点所有储存的分类对象
			subCategory = (Category) nodes.get(i).getUserObject();
			if(category.getCode().equals(subCategory.getFathercode()))
				fatherNode.add(nodes.get(i));
		}
	}
	//将子节点结合加入到父节点集合下
	private void addSubNodes(ArrayList<DefaultMutableTreeNode> fatherNodes,
			ArrayList<DefaultMutableTreeNode> subNodes){
		for(int i =0;i<fatherNodes.size();i++){
			addSubNodes(fatherNodes.get(i), subNodes);
		}
	}
	//获得数据模型
	public DefaultTableModel getBooksTableModel(ArrayList<Book> books){
		String title[] = new String[]{
				"序号","书名","作者","出版社","出版时间","价格"
		};
		Object[][] data = null;
		Object[] row = null;
		//创建树的数据模型。data提供数据，title提供表头
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//让表格不可编辑
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
				"序号","图书编号","用户编号","借书日期","是否续借"
		};
		Object[][] data = null;
		Object[] row = null;
		//创建树的数据模型。data提供数据，title提供表头
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//让表格不可编辑
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
				"用户编号","建议/留言","留言时间"
		};
		Object[][] data = null;
		Object[] row = null;
		//创建树的数据模型。data提供数据，title提供表头
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//让表格不可编辑
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
				"序号","书籍编号","用户编号","评论时间","书籍评论"
		};
		Object[][] data = null;
		Object[] row = null;
		//创建树的数据模型。data提供数据，title提供表头
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//让表格不可编辑
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
				"序号","用户名","密码","性别","role","真实姓名","工作"
		};
		Object[][] data = null;
		Object[] row = null;
		//创建树的数据模型。data提供数据，title提供表头
		DefaultTableModel tableModel = new DefaultTableModel(data, title){
			//让表格不可编辑
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
