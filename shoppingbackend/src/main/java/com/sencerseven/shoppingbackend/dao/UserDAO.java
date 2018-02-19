package com.sencerseven.shoppingbackend.dao;

import java.util.List;

import com.sencerseven.shoppingbackend.dto.Address;
import com.sencerseven.shoppingbackend.dto.Cart;
import com.sencerseven.shoppingbackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getByEmail(String email);
	
	boolean addAdress(Address adress);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	
	boolean updateCart(Cart cart);
}
