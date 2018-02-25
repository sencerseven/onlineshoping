package com.sencerseven.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sencerseven.onlineshopping.model.UserModel;
import com.sencerseven.shoppingbackend.dao.CartLineDAO;
import com.sencerseven.shoppingbackend.dao.ProductDAO;
import com.sencerseven.shoppingbackend.dto.Cart;
import com.sencerseven.shoppingbackend.dto.CartLine;
import com.sencerseven.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ProductDAO productDAO;
	
	private Cart getCart() {
		return ((UserModel)httpSession.getAttribute("userModel")).getCart();
	}
	
	public List<CartLine> getCartLines(){

		return cartLineDAO.list(this.getCart().getId());
	}

	public String updateCartLine(int cartLineId, int count) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null || count < 0) {
			return "result=error";
		}else {
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			
			if(product.getQuantity() <= count) {
				count = product.getQuantity();
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";
		}else {
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			cartLineDAO.updateCart(cart);
			
		//	remove the cart line
		cartLineDAO.delete(cartLine);
		return "result=deleted";
		}
		
	
	}

	public String addCartLine(int productId) {
		String response = null;
		
		Cart cart = this.getCart();
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null) {
			cartLine = new CartLine();
			
			Product product = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines() +1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response = "result=added";
			
		}
		
		
		return response;
	}
	
}
