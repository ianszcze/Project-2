package com.revature.daos;

import java.util.List;

import com.revature.beans.User;

public interface UserDao {
	
	public User getUser(String username, String password);
	public String getUserAddress(int user_id);
	public List<String> getBookmarks(int user_id);
	public boolean addUser(User obj);
	public boolean addBookmark(int user_id, String url);
	
	// optional
	//public boolean setUserInfo(String field, String newValue);
	//public boolean updateAddress(int user_id, String newAddress);
	
}
