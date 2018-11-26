package com.revature.util;

import com.revature.beans.Address;
import com.revature.beans.Bookmark;
import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;

public class Driver {

	public static void main(String[] args) {
		//Session sess = HibernateUtil.getSession();
		//sess.close();
		
		UserDao ud = UserDaoImpl.getDao();
		/*
		Address testAdr = new Address("Testville", "Test State", "123 Test St", 55555);
		User testUser = new User("testUser", "testPW", "First", "Last", 
				"firstlast@testmail.com", testAdr, 0);
		System.out.println(ud.addUser(testUser)); 
		*/
		
		//System.out.println(ud.getUser("testUser", "testPW"));
		
		/*
		Address someAdr = new Address("Some City", "Some State", "456 Some Rd", 77777);
		User someUser = new User("someUser", "somePW", "Sam", "Sung", 
				"samsung@somemail.com", someAdr, 0);
		System.out.println(ud.addUser(someUser));
		*/
		
		User someUser = ud.getUser("someUser", "somePW");
		System.out.println(someUser);
		
		//Bookmark someBkmk = new Bookmark(someUser, "https://www.allsides.com/unbiased-balanced-news");
		//System.out.println(ud.addBookmark(someBkmk));
		System.out.println(ud.getBookmarks(someUser));
	}

}
