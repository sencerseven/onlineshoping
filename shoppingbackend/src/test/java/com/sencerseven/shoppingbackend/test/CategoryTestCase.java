package com.sencerseven.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sencerseven.shoppingbackend.config.HibernateConfig;
import com.sencerseven.shoppingbackend.dao.CategoryDAO;
import com.sencerseven.shoppingbackend.daoimpl.CategoryDAOImpl;
import com.sencerseven.shoppingbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sencerseven.shoppingbackend");
		
		context.refresh();
		
		categoryDAO =  (CategoryDAO) context.getBean("categoryDAO");
	}
	
	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Laptop");
		category.setDescription("this is some description for Laptop");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added a category inside the table", true,categoryDAO.add(category));
	}
}
