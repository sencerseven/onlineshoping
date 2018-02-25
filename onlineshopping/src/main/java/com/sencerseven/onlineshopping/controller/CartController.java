package com.sencerseven.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sencerseven.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result",required = false)String result) {
		ModelAndView mv = new ModelAndView("/page");
		if(cartService.getCartLines() != null)
			mv.addObject("cartLines",cartService.getCartLines());
		else
			mv.addObject("message","No card Found!");
		
			
		if(result != null) {
			switch(result) {
			case "updated":
				mv.addObject("message","CartLine has been updated successfully");
				break;
			case "added":
				mv.addObject("deleted", "CartLine has been added successfully");
				break;
			case "deleted":
				mv.addObject("deleted", "CartLine has been deleted successfully");
				break;
			case "error":
				mv.addObject("message", "Something went wrong!");
				break;
			}
		}
		mv.addObject("title","User Cart");
		mv.addObject("userClickShowCart",true);
		
		
		return mv;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable("cartLineId") int cartLineId,@RequestParam(name="count") int count) {
		
		String response = cartService.updateCartLine(cartLineId,count);
		
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String deleteCart(@PathVariable("cartLineId")int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable("productId")int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
	
}
