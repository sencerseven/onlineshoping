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
	
	/*@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("test");
		category.setDescription("test");
		category.setImageURL("test");
		assertEquals("Successfully added a category inside the table", true,categoryDAO.add(category));
	}*/
	
	/*@Test
	public void testGetCategory() {
		category = categoryDAO.get(5);
		assertEquals("Successfully fetched a single a category from the table", "Television",category.getName());
		
	}*/
	
	/*@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(5);
		category.setName("TV");
		assertEquals("Successfully updated a single a category in the table", true,categoryDAO.update(category));
		
	}*/
	
/*	@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(5);
		assertEquals("Successfully deleted a single a category in the table", true,categoryDAO.delete(category));
		
	}*/
	
	/*@Test
	public void testListCategory() {
		assertEquals("Successfully fetched the list a category in the table", 3,categoryDAO.list().size());
		
	}*/
	
	@Test
	public void TestCRUDCategory() {
		category = new Category();
		category.setName("Television");
		category.setDescription("This is some television description");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added a category inside the table", true,categoryDAO.add(category));
		
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some laptop description");
		category.setImageURL("CAT_2.png");
		assertEquals("Successfully added a category inside the table", true,categoryDAO.add(category));
		
		category = categoryDAO.get(1);
		assertEquals("Successfully fetched a single a category from the table", "Television",category.getName());
		
		category = categoryDAO.get(1);
		category.setName("TV");
		assertEquals("Successfully updated a single a category in the table", true,categoryDAO.update(category));
		
		category = categoryDAO.get(2);
		assertEquals("Successfully deleted a single a category in the table", true,categoryDAO.delete(category));
		

		assertEquals("Successfully fetched the list a category in the table",1,categoryDAO.list().size());
	}
}
