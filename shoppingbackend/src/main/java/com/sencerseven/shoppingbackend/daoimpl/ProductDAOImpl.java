package com.sencerseven.shoppingbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sencerseven.shoppingbackend.dao.CategoryDAO;
import com.sencerseven.shoppingbackend.dao.ProductDAO;
import com.sencerseven.shoppingbackend.dto.Category;
import com.sencerseven.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	@Qualifier(value="sessionFactory")
	private SessionFactory sessionFactory;
	

	@Override
	
	public boolean add(Product product) {
		
		try {
			sessionFactory.getCurrentSession().persist(product);
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
/*
 * Updating a single category
 */
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		product.setActive(false);		
		try {
			sessionFactory.getCurrentSession().update(product);
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Product get(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			return session.get(Product.class,Integer.valueOf(id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> list() {
		String selectActiveCategory = "From Product";
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery(selectActiveCategory,Product.class);
		
		return query.getResultList();
	}
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "From Product WHERE active = :active";
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery(selectActiveProducts,Product.class);
		query.setParameter("active", true);
		return query.getResultList();
	}
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProducts = "From Product WHERE active = :active AND categoryId = :categoryID";
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery(selectActiveProducts,Product.class);
		query.setParameter("active", true);
		query.setParameter("categoryID", categoryId);
		return query.getResultList();
	}
	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectActiveProducts = "From Product WHERE active = :active ORDER BY id";
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery(selectActiveProducts,Product.class);
		query.setParameter("active", true);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}

}
