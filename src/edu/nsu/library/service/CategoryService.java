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
	//获得某一级分类所有分类集合
	public ArrayList<Category> getCategories(String level){
		return getCategoryDAO().getCategorise(level);
	}
	//获取父分类的子类集合
	public ArrayList<Category> getChildren(Category category){
		return getCategoryDAO().getChildren(category);
	}
	

}
