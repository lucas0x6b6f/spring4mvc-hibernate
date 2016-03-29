package com.lucasko.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.transaction.annotation.Transactional;

import com.lucasko.model.Count;
import com.lucasko.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@Transactional
	@SuppressWarnings("unchecked")
	public User getUser(String username) {
		List<User> users = new ArrayList<User>();
		users = getSessionFactory()
				.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username)
				.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@Transactional
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users = getSessionFactory().getCurrentSession().createQuery("from User").list();
		return users ;

	}
	
	@Transactional
	public void saveOrUpdateUser(User user) {
	        	getSessionFactory()
				.getCurrentSession().saveOrUpdate(user.getUsername(), user);
	    }
	
	
	@Transactional
	public int deleteUser(String username) {

		Session session = getSessionFactory().getCurrentSession() ;
		String command = "delete User where username = :username";

		int deletedEntities = session.createQuery( command )
		        .setString( "username", username )
		        .executeUpdate();
		System.out.println("deletedEntities="+deletedEntities);
		return deletedEntities ;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Count> getCount() {
		Session session = getSessionFactory().getCurrentSession() ;
		Criteria criteria = session.createCriteria(User.class)
				 .setProjection(Projections.projectionList()
						 .add(Projections.groupProperty("team"), "team")
						 .add(Projections.rowCount(), "count"))  ;
	             criteria.setResultTransformer(new AliasToBeanResultTransformer(
	     				Count.class));
	   List<Count> results = criteria.list() ;
		return results ;
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}