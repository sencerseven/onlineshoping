package com.sencerseven.shoppingbackend.dao;

import com.sencerseven.shoppingbackend.dto.Address;
import com.sencerseven.shoppingbackend.dto.Cart;
import com.sencerseven.shoppingbackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getByEmail(String email);
	
	boolean addAdress(Address adress);
	
	boolean updateCart(Cart cart);
}
