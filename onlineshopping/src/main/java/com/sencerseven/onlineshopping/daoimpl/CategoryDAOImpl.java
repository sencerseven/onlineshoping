package com.sencerseven.onlineshopping.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.sencerseven.onlineshopping.dao.CategoryDAO;
import com.sencerseven.onlineshopping.dto.Category;

public class CategoryDAOImpl implements CategoryDAO{

	private static List<Category> categories = new ArrayList<>();
	
	
	
	static {
		Category category = new Category();
		
		category.setId(1);
		category.setName("Television");
		category.setDescription("this is some description for television");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);

		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("this is some description for mobile");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);
			
		category = new Category();
		category.setId(3);
		category.setName("laptop");
		category.setDescription("this is some description for laptop");
		category.setImageURL("CAT_3.png");
		
		categories.add(category);
	}
	
	@Override
	public List<Category> list() {
	
		// TODO Auto-generated method stub
		return null;
	}

}
