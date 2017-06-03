package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.FriendDAO;
import com.niit.theBackendProject.dto.Friend;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Friend get(int id) {
		return sessionFactory.getCurrentSession().get(Friend.class, Integer.valueOf(id));
	}

	@Override
	public List<Friend> list() {
		String selectActiveFriend = "FROM Friend WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveFriend);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Friend friend) {
		try {
			
			sessionFactory.getCurrentSession().save(friend);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Friend friend) {
		try {
			
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Friend friend) {
		friend.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	
	
	/**********************************/
}
