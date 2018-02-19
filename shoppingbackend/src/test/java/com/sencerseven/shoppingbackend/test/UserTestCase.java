package com.sencerseven.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sencerseven.shoppingbackend.dao.UserDAO;
import com.sencerseven.shoppingbackend.dto.Address;
import com.sencerseven.shoppingbackend.dto.Cart;
import com.sencerseven.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sencerseven.shoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
		
	}
/*	
	@Test
	public void testAdd() {
		user = new User();
		
		user.setFirstName("hrithik");
		user.setLastName("roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("123123");
		user.setRole("USER");
		user.setPassword("123456");
		
		assertEquals("Fialed to add user!",true, userDAO.addUser(user));
		
		address = new Address();
		
		address.setAddressLineOne("Kartal tepe mah");
		address.setAdressLineTwo("terakki cad.");
		address.setCity("istanbul");
		address.setState("bakırköy");
		address.setCountry("Turkey");
		address.setPostalCode("34471");
		address.setBilling(true);
		
		address.setUserId(user.getId());
		
		assertEquals("Failed to add address", true,userDAO.addAdress(address));
		
		if(user.getRole().equals("USER")) {
			cart = new Cart();
			cart.setUser(user);
			
			assertEquals("Failed to add cart", true,userDAO.addCart(cart));
		
			address = new Address();
			address.setAddressLineOne("Kartal tepe mah");
			address.setAdressLineTwo("terakki cad.");
			address.setCity("istanbul");
			address.setState("bakırköy");
			address.setCountry("Turkey");
			address.setPostalCode("34471");
			
			address.setShipping(true);
			
			address.setUserId(user.getId());
			
			assertEquals("Failed to add shipping", true,userDAO.addAdress(address));
		}
	}
	*/
	/*
	@Test
	public void testAdd() {
		user = new User();
		
		user.setFirstName("hrithik");
		user.setLastName("roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("123123");
		user.setRole("USER");
		user.setPassword("123456");
	
		
		if(user.getRole().equals("USER")) {
			cart = new Cart();
			cart.setUser(user);
			
			user.setCart(cart);
			
			assertEquals("Failed to add address", true,userDAO.addUser(user));
			
			
		}
	}
	*/
	
	@Test
	public void testUpdateCart() {
		
		User user = userDAO.getByEmail("hr@gmail.com");
		
		Cart cart = user.getCart();
		
		cart.setGrandTotal(1.00);
		cart.setCartLines(6000);
		
		assertEquals("Failed to update cart", true,userDAO.updateCart(cart));
	}
	
}
