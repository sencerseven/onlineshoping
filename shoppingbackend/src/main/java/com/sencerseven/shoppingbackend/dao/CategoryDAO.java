package com.sencerseven.shoppingbackend.dao;

import java.util.List;

import com.sencerseven.shoppingbackend.dto.Category;

public interface CategoryDAO {

	public List<Category> list();

	public Category get(int id);
	
}
