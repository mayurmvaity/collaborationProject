package com.niit.theBackendProject.dao;

import java.util.List;

import com.niit.theBackendProject.dto.Forum;

public interface ForumDAO {

	Forum get(int id);
	List<Forum> list();
	boolean add(Forum forum);
	boolean update(Forum forum);
	boolean delete(Forum forum);

	
}
