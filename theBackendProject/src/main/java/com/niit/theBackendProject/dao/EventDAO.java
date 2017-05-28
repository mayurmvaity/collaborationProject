package com.niit.theBackendProject.dao;

import java.util.List;

import com.niit.theBackendProject.dto.Adminevent;

public interface EventDAO {

	Adminevent get(int id);
	List<Adminevent> list();
	boolean add(Adminevent evt);
	boolean update(Adminevent evt);
	boolean delete(Adminevent evt);
	
}
