package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.EventpartDAO;
import com.niit.theBackendProject.dto.Eventpart;

@Repository("eventpartDAO")
@Transactional
public class EventpartDAOImpl implements EventpartDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Eventpart get(int id) {
		return sessionFactory.getCurrentSession().get(Eventpart.class, Integer.valueOf(id));
	}

	@Override
	public List<Eventpart> list() {
		String selectActiveEp = "FROM Eventpart WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveEp);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Eventpart eventpart) {
		try {
			
			sessionFactory.getCurrentSession().save(eventpart);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Eventpart eventpart) {
		try {
			
			sessionFactory.getCurrentSession().update(eventpart);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Eventpart eventpart) {
		eventpart.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(eventpart);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Eventpart> listByEpNumber(int evtid) {
		String selectActiveEpbynum = "FROM Eventpart WHERE evt.evtid = :evtid and active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveEpbynum);
		
		query.setParameter("active", 'Y');
		query.setParameter("evtid", evtid);
		
		return query.getResultList();
	}

}
