package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Bookmark;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	
	private static UserDaoImpl udi;

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserDaoImpl getDao() {
		if (udi == null) {
			udi = new UserDaoImpl();
		}
		return udi;
	}

	@Override
	public User getUser(String username, String password) {
		Session hiSess = HibernateUtil.getSession();
		// HQL uses bean name, NOT table name
		String hql = "FROM User WHERE username = :userVal AND pswd = :pwVal";
		Query<User> selectUser = hiSess.createQuery(hql, User.class);
		selectUser.setParameter("userVal", username);
		selectUser.setParameter("pwVal", password);
		User user = (User) selectUser.getSingleResult();
		hiSess.close();
		return user;
	}

	/*
	@Override
	public String getUserAddress(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public List<Bookmark> getBookmarks(User user) {
		Session hiSess = HibernateUtil.getSession();
		String hql = "FROM Bookmark WHERE user_id = :uIdVal";
		Query<Bookmark> selectBkmks = hiSess.createQuery(hql, Bookmark.class);
		selectBkmks.setParameter("uIdVal", user.getUser_id());
		List<Bookmark> bkmks = selectBkmks.list();
		hiSess.close();
		return bkmks;
	}

	@Override
	public int addUser(User user) {
		Session hiSess = HibernateUtil.getSession();
		Transaction tx = hiSess.beginTransaction();
		hiSess.save(user.getAddress()); // must add address first to avoid exception
		// but what if address already exists? how to prevent duplicate addresses?
		int userPK = (int) hiSess.save(user);
		tx.commit();
		hiSess.close();
 		return userPK;
	}

	@Override
	public int addBookmark(Bookmark bkmk) { // add condition: user must already exist in db 
		Session hiSess = HibernateUtil.getSession();
		Transaction tx = hiSess.beginTransaction();
		int bkmkPK = (int) hiSess.save(bkmk);
		tx.commit();
		hiSess.close();
 		return bkmkPK;
	}

}
