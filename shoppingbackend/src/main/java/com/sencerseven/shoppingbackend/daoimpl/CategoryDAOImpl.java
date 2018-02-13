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
import com.sencerseven.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	@Qualifier(value="sessionFactory")
	private SessionFactory sessionFactory;
	

	@Override
	
	public boolean add(Category category) {
		
		try {
			sessionFactory.getCurrentSession().persist(category);
			
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
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);		
		try {
			sessionFactory.getCurrentSession().update(category);
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Category get(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Category.class,Integer.valueOf(id));
	}

	@Override
	public List<Category> list() {
		String selectActiveCategory = "From Category WHERE active = :active";
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		
		
		return query.getResultList();
	}

}
