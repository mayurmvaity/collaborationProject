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
@Table(name="blog")
public class Blog extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forblogid", allocationSize = 1)
	@Column
	private int blogid;	
	
	@Column
	private String btitle;
	
	@Column
	private String bdata;
	
	@Column
	private int blikes = 0;
	
	@Column
	private int bcomments;
	
	@Column
	private Date bdate;
	
	
	@Column(name="is_active")
	private char active='Y';
	
	@Column(name="is_approved")
	private char isApproved = 'N';
	
	@OneToOne
	@JoinColumn(name="userid")
	private Usertable user;
	
	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	public char getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(char isApproved) {
		this.isApproved = isApproved;
	}

	public int getBlikes() {
		return blikes;
	}

	public void setBlikes(int blikes) {
		this.blikes = blikes;
	}

	public int getBcomments() {
		return bcomments;
	}

	public void setBcomments(int bcomments) {
		this.bcomments = bcomments;
	}

	

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBdata() {
		return bdata;
	}

	public void setBdata(String bdata) {
		this.bdata = bdata;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}
	
	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	@Override
	public String toString() {
		return "Blog [blogid=" + blogid + ", btitle=" + btitle + ", bdata=" + bdata + ", blikes=" + blikes
				+ ", bcomments=" + bcomments + ", bdate=" + bdate + ", active=" + active + ", isApproved=" + isApproved
				+ ", user=" + user + "]";
	}

	
	
}
