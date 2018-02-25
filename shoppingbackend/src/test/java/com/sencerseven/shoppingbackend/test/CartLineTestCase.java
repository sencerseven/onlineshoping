package com.sencerseven.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sencerseven.shoppingbackend.dao.CartLineDAO;
import com.sencerseven.shoppingbackend.dao.ProductDAO;
import com.sencerseven.shoppingbackend.dao.UserDAO;
import com.sencerseven.shoppingbackend.dto.Cart;
import com.sencerseven.shoppingbackend.dto.CartLine;
import com.sencerseven.shoppingbackend.dto.Product;
import com.sencerseven.shoppingbackend.dto.User;

public class CartLineTestCase {

	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sencerseven.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	@Test
	public void testAddNewCartLine() {
		// 1. get the user
		
		user = userDAO.getByEmail("hr@gmail.com");
		
		assertEquals("failed user", 1,user.getId());
		
		// 2. fetch the card
		
		cart = user.getCart();
		System.out.println(cart);
		assertEquals("failed cart", 1,cart.getId());
		
		// 3. get the product 
		
		product = productDAO.get(1);
		
		// 4. new cart line
		
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the cartLine", true,cartLineDAO.add(cartLine));
		
		// update the cart
		
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1 );
		
		assertEquals("Failed to update the cartLine", true,cartLineDAO.updateCart(cart));
	}
}
