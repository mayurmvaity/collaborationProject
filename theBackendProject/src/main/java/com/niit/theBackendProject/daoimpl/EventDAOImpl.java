package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.EventDAO;
import com.niit.theBackendProject.dto.Adminevent;

@Repository("eventDAO")
@Transactional
public class EventDAOImpl implements EventDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Adminevent get(int id) {
		return sessionFactory.getCurrentSession().get(Adminevent.class, Integer.valueOf(id));
	}

	@Override
	public List<Adminevent> list() {
		String selectActiveEvent = "FROM Adminevent WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveEvent);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Adminevent evt) {
		try {
			
			sessionFactory.getCurrentSession().save(evt);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Adminevent evt) {
		try {
			
			sessionFactory.getCurrentSession().update(evt);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Adminevent evt) {
		evt.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(evt);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
