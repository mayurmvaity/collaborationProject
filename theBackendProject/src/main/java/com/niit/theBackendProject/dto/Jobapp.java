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
@Table(name="jobapp")
public class Jobapp extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forjobappid", allocationSize = 1)
	@Column
	private int jobappid;
	
	@OneToOne
	@JoinColumn(name = "jobid")
	private Job job;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private Usertable user;
	
	@Column
	private Date appdate;
	
	@Column(name="is_active")
	private char active = 'Y';

	public int getJobappid() {
		return jobappid;
	}

	public void setJobappid(int jobappid) {
		this.jobappid = jobappid;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	public Date getAppdate() {
		return appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Jobapp [jobappid=" + jobappid + ", job=" + job + ", user=" + user + ", appdate=" + appdate + ", active="
				+ active + "]";
	}
	
	
}
