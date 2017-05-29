package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.FmemberDAO;
import com.niit.theBackendProject.dto.Fmember;

@Repository("fmemberDAO")
@Transactional
public class FmemberDAOImpl implements FmemberDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Fmember get(int id) {
		return sessionFactory.getCurrentSession().get(Fmember.class, Integer.valueOf(id));
	}

	@Override
	public List<Fmember> list() {
		String selectActiveFmember = "FROM Fmember WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveFmember);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Fmember fmember) {
		try {
			
			sessionFactory.getCurrentSession().save(fmember);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Fmember fmember) {
		try {
			
			sessionFactory.getCurrentSession().update(fmember);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Fmember fmember) {
		fmember.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(fmember);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
