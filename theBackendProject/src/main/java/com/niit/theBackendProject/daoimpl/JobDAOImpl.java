package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.JobDAO;
import com.niit.theBackendProject.dto.Job;


@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Job get(int id) {
		return sessionFactory.getCurrentSession().get(Job.class, Integer.valueOf(id));
	}

	@Override
	public List<Job> list() {
		String selectActiveJob = "FROM Job WHERE active = :active order by jdate desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveJob);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Job job) {
		try {
			
			sessionFactory.getCurrentSession().save(job);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Job job) {
		try {
			
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Job job) {
		job.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	

}
