package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.FpostDAO;
import com.niit.theBackendProject.dto.Fpost;

@Repository("fpostDAO")
@Transactional
public class FpostDAOImpl implements FpostDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Fpost get(int id) {
		return sessionFactory.getCurrentSession().get(Fpost.class, Integer.valueOf(id));
	}

	@Override
	public List<Fpost> list() {
		String selectActiveFpost = "FROM Fpost WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveFpost);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Fpost fpost) {
		try {
			
			sessionFactory.getCurrentSession().save(fpost);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Fpost fpost) {
		try {
			
			sessionFactory.getCurrentSession().update(fpost);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Fpost fpost) {
		fpost.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(fpost);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Fpost> fplistByforumid(int forumid) {
		String selectActiveFpost = "FROM Fpost WHERE active = :active and forum.forumid = :forumid";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveFpost);
		
		query.setParameter("active", 'Y');
		query.setParameter("forumid", forumid);
		
		return query.getResultList();
	}

}
