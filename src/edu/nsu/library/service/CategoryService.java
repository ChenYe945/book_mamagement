package edu.nsu.library.service;

import java.util.ArrayList;

import edu.nsu.library.bean.Category;
import edu.nsu.library.dao.CategoryDAO;

public class CategoryService {
	private CategoryDAO categoryDAO;
	private ArrayList<Category> categories = null;
	public CategoryDAO getCategoryDAO() {
		if(categoryDAO==null)
			categoryDAO = new CategoryDAO();
 		return categoryDAO;
	}
	public ArrayList<Category> get(Category category){
		return getCategoryDAO().get(category.getCode());
	}
	//���ĳһ���������з��༯��
	public ArrayList<Category> getCategories(String level){
		return getCategoryDAO().getCategorise(level);
	}
	//��ȡ����������༯��
	public ArrayList<Category> getChildren(Category category){
		return getCategoryDAO().getChildren(category);
	}
	

}
