package com.niit.theBackendProject.daoimpl;

import java.util.List;

import javax.transaction.Transactional;
import javax.xml.registry.infomodel.User;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.theBackendProject.dao.FriendDAO;
import com.niit.theBackendProject.dto.Friend;
import com.niit.theBackendProject.dto.Usertable;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Friend get(int id) {
		return sessionFactory.getCurrentSession().get(Friend.class, Integer.valueOf(id));
	}

	@Override
	public List<Friend> list() {
		String selectActiveFriend = "FROM Friend WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveFriend);
		
		query.setParameter("active", 'Y');
		
		return query.getResultList();
	}

	@Override
	public boolean add(Friend friend) {
		try {
			
			sessionFactory.getCurrentSession().save(friend);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Friend friend) {
		try {
			
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Friend friend) {
		friend.setActive('N');
		 
		try {
	
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// friend requests sent
	@Override
	public List<Usertable> frlist(int userid) {
		// String selectActiveFriend = "FROM Friend WHERE user1.userid = :userid or user2.userid = :userid and isFriend = :isFriend and active = :active";
		
		String selectQuery = "SELECT * FROM Usertable WHERE userid IN (SELECT userid2 FROM Friend WHERE userid1 = :userid and is_friend = 'N') AND is_approved = 'Y' AND is_active = 'Y'";
		
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(selectQuery,Usertable.class)
						.setParameter("userid", userid)
							.getResultList();
	}
	
	// friend requests received
		@Override
		public List<Usertable> frReqrcvlist(int userid) {
			// String selectActiveFriend = "FROM Friend WHERE user1.userid = :userid or user2.userid = :userid and isFriend = :isFriend and active = :active";
			
			String selectQuery = "SELECT * FROM Usertable WHERE userid IN (SELECT userid1 FROM Friend WHERE userid2 = :userid and is_friend = 'N') AND is_approved = 'Y' AND is_active = 'Y'";
			
			return sessionFactory
					.getCurrentSession()
						.createNativeQuery(selectQuery,Usertable.class)
							.setParameter("userid", userid)
								.getResultList();
		}
	
	
	// other users who aren not friends
	@Override
	public List<Usertable> notfrlist(int userid) {
		// String selectActiveFriend = "FROM Friend WHERE user1.userid = :userid or user2.userid = :userid and isFriend = :isFriend and active = :active";
		
		String selectQuery = "SELECT * FROM Usertable WHERE userid NOT IN (SELECT userid1 FROM Friend WHERE userid2 = :userid and is_active = 'Y' UNION SELECT userid2 FROM Friend WHERE userid1 = :userid and is_active = 'Y') and userid != :userid AND is_approved = 'Y' AND is_active = 'Y'";
		
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(selectQuery,Usertable.class)
						.setParameter("userid", userid)
							.getResultList();
	}
	
	// my friends
	@Override
	public List<Usertable> myfrlist(int userid) {
		// String selectActiveFriend = "FROM Friend WHERE user1.userid = :userid
		// or user2.userid = :userid and isFriend = :isFriend and active =
		// :active";

		String selectQuery = "SELECT * FROM Usertable WHERE userid IN (SELECT userid1 FROM Friend WHERE userid2 = :userid and is_friend = 'Y' UNION SELECT userid2 FROM Friend WHERE userid1 = :userid and is_friend = 'Y') AND is_approved = 'Y' AND is_active = 'Y'";

		return sessionFactory.getCurrentSession().createNativeQuery(selectQuery, Usertable.class)
				.setParameter("userid", userid).getResultList();
	}

	@Override
	public Friend getByUsers(int userid1, int userid2) {
		String selectActiveFriend = "FROM Friend WHERE userid1 = :userid1 and userid2 = :userid2 and active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveFriend);
		
		query.setParameter("active", 'Y');
		query.setParameter("userid1", userid1);
		query.setParameter("userid2", userid2);
		
		return (Friend) query.getSingleResult();
	}
	
	/**********************************/
}
