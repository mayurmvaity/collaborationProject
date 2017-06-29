package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.UserDAO;
import com.niit.theBackendProject.dto.Usertable;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Usertable get(int id) {
		return sessionFactory.getCurrentSession().get(Usertable.class, Integer.valueOf(id));
	}

	@Override
	public List<Usertable> list() {
		String selectActiveUser = "FROM Usertable WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveUser);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Usertable user) {
		try {
			
			sessionFactory.getCurrentSession().save(user);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Usertable user) {
		try {
			
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Usertable user) {
		user.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletePermanently(Usertable user) {
		try {
			
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Usertable validateUser(Usertable user) {
		
		String username = user.getEmail();
		String password = user.getPw();
		
		String hql = "FROM Usertable where email = '" + username + "' and pw = '" + password + "'";
		
		try{
			System.out.println("before q");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
			System.out.println("after q");
			if(query == null)
			{
				return null;
			}
			else {
				user = (Usertable) query.getSingleResult();
				System.out.println("In the new data");
				return user;
			}
		}
		catch(Exception e){
			System.out.println("some Exception 2.3" +e);
			//e.printStackTrace();
			return null;
		}
		
		/*try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			user = (Usertable) query.getSingleResult();
			return user;
		} catch (Exception e) {
			System.out.println("some Exception");
			//e.printStackTrace();
			return null;
		}*/
	}
	
	@Override
	public Usertable getUserByEmail(String email) {
		String username = email;
		
		String hql = "FROM Usertable where email = '" + username + "'";
		
		try {
			
			System.out.println("before q");
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			System.out.println("after q");
			if(query == null)
			{
				System.out.println("Query is null");
				return null;
			}
			else {
				Usertable user3 = (Usertable) query.getSingleResult();
				System.out.println("In the new data");
				return user3;
			}
			
			
		}
		catch(Exception ex) {
			System.out.println("EMIAL NOT FOUND"+ email);
			System.out.println(ex);
		}
		return null;
	}
	
	// list of not approved users for admin
	@Override
	public List<Usertable> nalist() {
		String selectActiveUser = "FROM Usertable WHERE active = :active and isApproved = :approv and role =:role order by fname";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveUser);
		
		query.setParameter("active", 'Y');
		query.setParameter("approv", 'N');
		query.setParameter("role", "User");
		
		return query.getResultList();
	}
	
	// list of approved users for users
		@Override
		public List<Usertable> approvlist() {
			String selectActiveUser = "FROM Usertable WHERE active = :active and isApproved = :approv order by fname";
			
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveUser);
			
			query.setParameter("active", 'Y');
			query.setParameter("approv", 'Y');
			
			
			return query.getResultList();
		}
		
		// list of approved users for users
		@Override
		public List<Usertable> rejectedUsersList() {
			String selectActiveUser = "FROM Usertable WHERE active = :active and isApproved = :approv order by fname";
					
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveUser);
					
			query.setParameter("active", 'Y');
			query.setParameter("approv", 'R');
					
					
			return query.getResultList();
		}
		
		

		@Override
		@Transactional
		public boolean updateUserProfile(String fileName, int id) {
			String updateQuery = "UPDATE Usertable SET profile = :fileName WHERE userid = :id";
			Query query = sessionFactory.getCurrentSession().createQuery(updateQuery);
			query.setParameter("id", id);
			query.setParameter("fileName", fileName);
			try {
				query.executeUpdate();
				return true;
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}	
			return false;
		}

		@Override
		@Transactional
		public List<Usertable> getOnlineFrnds(int userid) {
			String selectQuery = "SELECT * FROM Usertable WHERE userid IN (SELECT userid1 FROM Friend WHERE userid2 = :userid and is_friend = 'Y' UNION SELECT userid2 FROM Friend WHERE userid1 = :userid and is_friend = 'Y') AND is_approved = 'Y' AND is_online = 'Y' AND is_active = 'Y' order by fname";

			return sessionFactory
					.getCurrentSession()
						.createNativeQuery(selectQuery,Usertable.class)
							.setParameter("userid", userid)
								.getResultList();
		}

		

		
}
