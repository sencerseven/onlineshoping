package com.sencerseven.shoppingbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sencerseven.shoppingbackend.dao.UserDAO;
import com.sencerseven.shoppingbackend.dto.Address;
import com.sencerseven.shoppingbackend.dto.Cart;
import com.sencerseven.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addAdress(Address adress) {
		try {
			sessionFactory.getCurrentSession().persist(adress);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		try {
			Query<User> query = sessionFactory.getCurrentSession().createQuery(selectQuery,User.class);
			query.setParameter("email",email);
			
			User user = query.getSingleResult();
			return user;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "From Address where user = :user and billing = :billing";
		
		try {
			Query<Address> query = sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class);
			query.setParameter("user", user);
			query.setParameter("billing", true);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddresses(User user) {
		String selectQuery = "FROM Address where user = :user and shipping = :shipping";
		try {
			Query<Address> query = sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class);
			query.setParameter("user", user);
			query.setParameter("shipping", true);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
