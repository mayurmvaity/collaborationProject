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
@Table(name="job")
public class Job extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forjobid", allocationSize = 1)
	@Column
	private int jobid;
	
	@Column
	private String jtitle;
	
	@Column
	private String jdata;
	
	@Column
	private Date jdate;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private Usertable user;
	
	

	@Column(name="is_active")
	private char active = 'Y';

	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	public String getJtitle() {
		return jtitle;
	}

	public void setJtitle(String jtitle) {
		this.jtitle = jtitle;
	}

	public String getJdata() {
		return jdata;
	}

	public void setJdata(String jdata) {
		this.jdata = jdata;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public Date getJdate() {
		return jdate;
	}

	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}

	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Job [jobid=" + jobid + ", jtitle=" + jtitle + ", jdata=" + jdata + ", jdate=" + jdate + ", user=" + user
				+ ", active=" + active + "]";
	}
	
	
	
	
	
}
