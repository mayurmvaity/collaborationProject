package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.ForumDAO;
import com.niit.theBackendProject.dto.Blog;
import com.niit.theBackendProject.dto.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Forum get(int id) {
		return sessionFactory.getCurrentSession().get(Forum.class, Integer.valueOf(id));
	}

	@Override
	public List<Forum> list() {
		String selectActiveForum = "FROM Forum WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveForum);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Forum forum) {
		try {
			
			sessionFactory.getCurrentSession().save(forum);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Forum forum) {
		try {
			
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Forum forum) {
		forum.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
