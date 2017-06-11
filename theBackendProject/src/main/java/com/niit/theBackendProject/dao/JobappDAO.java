package com.niit.theBackendProject.dao;

import java.util.List;

import com.niit.theBackendProject.dto.Jobapp;

public interface JobappDAO {

	Jobapp get(int id);
	List<Jobapp> list();
	boolean add(Jobapp jobapp);
	boolean update(Jobapp jobapp);
	boolean delete(Jobapp jobapp);
	
	List<Jobapp> listByJobid(int jobid);
}
