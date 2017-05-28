package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.BlogCommentDAO;
import com.niit.theBackendProject.dto.BlogComment;

@Repository("blogcommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BlogComment get(int id) {
		return sessionFactory.getCurrentSession().get(BlogComment.class, Integer.valueOf(id));
	}

	@Override
	public List<BlogComment> list() {
		String selectActiveBlogComm = "FROM BlogComment WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveBlogComm);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(BlogComment bcomm) {
		try {
			
			sessionFactory.getCurrentSession().save(bcomm);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(BlogComment bcomm) {
		try {
			
			sessionFactory.getCurrentSession().update(bcomm);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(BlogComment bcomm) {
		bcomm.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(bcomm);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<BlogComment> listByBlogid(int blogid) {
		String selectActiveBlogComm = "FROM BlogComment WHERE active = :active and blogid = :blogid";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveBlogComm);
		
		query.setParameter("active", 'Y');
		query.setParameter("blogid", blogid);
		
		return query.getResultList();
	}

	
	/*********************/
}
