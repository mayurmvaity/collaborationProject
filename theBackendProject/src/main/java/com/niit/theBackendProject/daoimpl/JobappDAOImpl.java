package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.JobappDAO;
import com.niit.theBackendProject.dto.Jobapp;

@Repository("jobappDAO")
@Transactional
public class JobappDAOImpl implements JobappDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Jobapp get(int id) {
		return sessionFactory.getCurrentSession().get(Jobapp.class, Integer.valueOf(id));
	}

	@Override
	public List<Jobapp> list() {
		String selectActiveJobapp = "FROM Jobapp WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveJobapp);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Jobapp jobapp) {
		try {
			
			sessionFactory.getCurrentSession().save(jobapp);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Jobapp jobapp) {
		try {
			
			sessionFactory.getCurrentSession().update(jobapp);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Jobapp jobapp) {
		jobapp.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(jobapp);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Jobapp> listByJobid(int jobid) {
		String selectActiveJobappByJobid = "FROM Jobapp WHERE job.jobid = :jobid and active = :active order by appdate desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveJobappByJobid);
		
		query.setParameter("active", 'Y');
		query.setParameter("jobid", jobid);
		
		return query.getResultList();
	}
	
	// applied jobs list
	@Override
	public List<Jobapp> appliedJobs(int userid) {
		String selectActiveJobapp = "FROM Jobapp WHERE active = :active and user.userid = :userid order by appdate desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveJobapp);
		
		query.setParameter("active", 'Y');
		query.setParameter("userid", userid);
		
		return query.getResultList();
	}

}
