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
@Table(name="forum")
public class Forum extends BaseDomain{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forforumid", allocationSize = 1)
	@Column
	private int forumid;
	
	@Column
	private String ftitle;
	
	@Column
	private String fdata;
	
	@Column
	private Date fdate;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private Usertable user;

	

	@Column(name="is_active")
	private char active = 'Y';

	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	public String getFtitle() {
		return ftitle;
	}

	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}

	public String getFdata() {
		return fdata;
	}

	public void setFdata(String fdata) {
		this.fdata = fdata;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Forum [forumid=" + forumid + ", ftitle=" + ftitle + ", fdata=" + fdata + ", fdate=" + fdate + ", user="
				+ user + ", active=" + active + "]";
	}
	
	
	
	
	
	
}
