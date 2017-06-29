package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.BlogDAO;
import com.niit.theBackendProject.dao.UserDAO;
import com.niit.theBackendProject.dto.Blog;
import com.niit.theBackendProject.dto.Usertable;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public Blog get(int id) {
		return sessionFactory.getCurrentSession().get(Blog.class, Integer.valueOf(id));
	}

	@Override
	public List<Blog> list() {
		String selectActiveBlog = "FROM Blog WHERE active = :active and isApproved = :approv order by bdate desc";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveBlog);

		query.setParameter("active", 'Y');
		query.setParameter("approv", 'Y');

		return query.getResultList();
	}

	@Override
	public boolean add(Blog blog) {
		try {

			sessionFactory.getCurrentSession().save(blog);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Blog blog) {
		try {

			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Blog blog) {
		blog.setActive('N');

		try {

			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// list of not approved blogs for admin
	@Override
	public List<Blog> nabloglist() {
		String selectActiveUser = "FROM Blog WHERE active = :active and isApproved = :approv order by bdate desc";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveUser);

		query.setParameter("active", 'Y');
		query.setParameter("approv", 'N');

		return query.getResultList();
	}

	// list of not approved blogs for admin
	@Override
	public List<Blog> userbloglist(int userid) {
		String selectActiveUser = "FROM Blog WHERE active = :active and user = :user order by bdate desc";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveUser);

		query.setParameter("active", 'Y');
		Usertable user = userDAO.get(userid);
		query.setParameter("user", user);

		return query.getResultList();
	}

}
