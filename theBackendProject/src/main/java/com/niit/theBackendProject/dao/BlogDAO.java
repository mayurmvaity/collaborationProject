package com.niit.theBackendProject.dao;

import java.util.List;

import com.niit.theBackendProject.dto.Blog;

public interface BlogDAO {

	Blog get(int id);
	List<Blog> list();
	boolean add(Blog blog);
	boolean update(Blog blog);
	boolean delete(Blog blog);

	public List<Blog> nabloglist();
	public List<Blog> userbloglist(int userid);
}
