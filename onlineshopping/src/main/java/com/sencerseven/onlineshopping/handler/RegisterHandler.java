package com.sencerseven.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.sencerseven.onlineshopping.model.RegisterModel;
import com.sencerseven.shoppingbackend.dao.UserDAO;
import com.sencerseven.shoppingbackend.dto.Address;
import com.sencerseven.shoppingbackend.dto.Cart;
import com.sencerseven.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing) {
		registerModel.setBilling(billing);
	}

	public String saveAll(RegisterModel model) {
		String transactionValue="success";
		
		User user = model.getUser();
		if(user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		// Encode the Password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userDAO.addUser(user);
		
		Address address = model.getBilling();
		address.setBilling(true);
		address.setUser(user);
		
		userDAO.addAdress(address);	
		
		
		return transactionValue;
	}
	
	public String validateUser(User user,MessageContext error) {
		String transactionValue = "success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Password does not match the Confirm Password").build());
			transactionValue = "failure";
		}
		
		if(userDAO.getByEmail(user.getEmail()) != null) {
			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email address is already used!").build());
			transactionValue = "failure";
			
		}
		
		return transactionValue;
	}
}
