package com.niit.theBackendProject.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="adminevent")
public class Adminevent extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="foreventid", allocationSize = 1)
	@Column
	private int evtid;
	
	@Column
	private String etitle;
	
	@Column
	private String edata;
	
	@Column
	private Date edate;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private Usertable user;
	
	

	@Column(name="is_active")
	private char active = 'Y';

	public int getEvtid() {
		return evtid;
	}

	public void setEvtid(int evtid) {
		this.evtid = evtid;
	}

	public String getEtitle() {
		return etitle;
	}

	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}

	public String getEdata() {
		return edata;
	}

	public void setEdata(String edata) {
		this.edata = edata;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Adminevent [evtid=" + evtid + ", etitle=" + etitle + ", edata=" + edata + ", edate=" + edate + ", user="
				+ user + ", active=" + active + "]";
	}
	
	
	
	
	
}
