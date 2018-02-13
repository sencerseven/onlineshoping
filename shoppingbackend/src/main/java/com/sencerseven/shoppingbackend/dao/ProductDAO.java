package com.sencerseven.shoppingbackend.dao;

import java.util.List;

import com.sencerseven.shoppingbackend.dto.Product;

public interface ProductDAO {

	public Product get(int productId);
	public List<Product> list();
	public boolean add (Product product);
	public boolean delete(Product product);
	public boolean update(Product product);
	
	//business Methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
}
