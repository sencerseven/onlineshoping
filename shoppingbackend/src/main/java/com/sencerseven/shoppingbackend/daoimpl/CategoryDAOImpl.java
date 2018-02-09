package com.sencerseven.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.RespectBinding;

import org.springframework.stereotype.Repository;

import com.sencerseven.shoppingbackend.dao.CategoryDAO;
import com.sencerseven.shoppingbackend.dto.Category;

@Repository("categoryDAO")
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
		return categories;
	}

}
