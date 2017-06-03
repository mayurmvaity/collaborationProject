package com.niit.theBackendProject.dao;

import java.util.List;

import com.niit.theBackendProject.dto.Friend;

public interface FriendDAO {

	Friend get(int id);
	List<Friend> list();
	boolean add(Friend friend);
	boolean update(Friend friend);
	boolean delete(Friend friend);
	
}
