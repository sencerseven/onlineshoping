package com.sencerseven.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sencerseven.onlineshopping.exception.ProductNotFoundException;
import com.sencerseven.shoppingbackend.dao.CategoryDAO;
import com.sencerseven.shoppingbackend.dao.ProductDAO;
import com.sencerseven.shoppingbackend.dto.Category;
import com.sencerseven.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		System.out.println(categoryDAO.list());
		mv.addObject("categories", categoryDAO.list());
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value = {"/about"})
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value = {"/contact"})
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	/*
	 *Metods to load all the products and based on category 
	 */
	
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		System.out.println(categoryDAO.list());
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	@RequestMapping(value = {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id")int id) {
		ModelAndView mv = new ModelAndView("page");
		
		// CategoryDAO to fetch single category
		
		Category category = null;
			
		category = categoryDAO.get(id);
		
		mv.addObject("title", category.getName());
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	
	/*
	@RequestMapping(value="/test")
	public ModelAndView test(@RequestParam(value="greeting",required=false)String greeting) {
		if(greeting == null)
			greeting = "Welcome";
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}
	*/
	
	@RequestMapping(value = {"/show/{id}/product"})
	public ModelAndView showSingleProduct(@PathVariable("id")int id)  throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		mv.addObject("title", product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickSingleProduct", true);
		
		return mv;
		
	}
	/* having similar mapping to our flow id*/
	@RequestMapping(value = {"/register"})
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value = {"/login"})
	public ModelAndView login(@RequestParam(name="error",required = false)String error,
			@RequestParam(name="logout",required = false)String logout) {
		ModelAndView mv = new ModelAndView("login");
		if(error != null) {
			mv.addObject("message", "Invalid Username and password");
		}
		if(logout != null) {
			mv.addObject("logout", "User has successfully logged out!");
		}
			
		logger.warn("Inside PageController login method - INFO");
		
		mv.addObject("title", "Login");
		return mv;
	}
	
	/* Access Denied Page */
	@RequestMapping(value = {"/access-denied"})
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "Access Denied!!");
		mv.addObject("errorTitle", "Aha! I Caught You !");
		mv.addObject("errorDescription", "You are not authorized to view this page");
		return mv;
	}
	
	@RequestMapping(value={"/perform-logout"})
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/login?logout";
	}
}
