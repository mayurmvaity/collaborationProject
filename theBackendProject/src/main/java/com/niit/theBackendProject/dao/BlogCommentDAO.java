package com.niit.theBackendProject.dao;

import java.util.List;

import com.niit.theBackendProject.dto.BlogComment;

public interface BlogCommentDAO {

	BlogComment get(int id);
	List<BlogComment> list();
	boolean add(BlogComment bcomm);
	boolean update(BlogComment bcomm);
	boolean delete(BlogComment bcomm);
	
	public List<BlogComment> listByBlogid(int blogid);
}
