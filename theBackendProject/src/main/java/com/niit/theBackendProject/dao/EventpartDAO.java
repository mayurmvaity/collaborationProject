package com.niit.theBackendProject.dao;

import java.util.List;

import com.niit.theBackendProject.dto.Eventpart;

public interface EventpartDAO {

	Eventpart get(int id);
	List<Eventpart> list();
	boolean add(Eventpart eventpart);
	boolean update(Eventpart eventpart);
	boolean delete(Eventpart eventpart);
	
	List<Eventpart> listByEpNumber(int evtid);
	List<Eventpart> participatedEvents(int userid);
}
