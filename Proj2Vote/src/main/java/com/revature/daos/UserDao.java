package com.revature.daos;

import java.util.List;

import com.revature.beans.Bookmark;
import com.revature.beans.User;

public interface UserDao {
	
	public User getUser(String username, String password);
	//public String getUserAddress(int user_id);
	public List<Bookmark> getBookmarks(User user);
	public int addUser(User user);
	public int addBookmark(Bookmark bkmk);
	
	// optional
	//public boolean setUserInfo(String field, String newValue);
	//public boolean updateAddress(int user_id, String newAddress);
	
}
