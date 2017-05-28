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
@Table(name="blogcomment")
public class BlogComment extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forblogcommid", allocationSize = 1)
	@Column
	private int blogcommid;	
	
	@Column
	private String commdata;
	
	@Column
	private int blogid;
	
	@Column
	private Date commdate;
	
	@OneToOne
	@JoinColumn(name="userid")
	private Usertable user;
	
	@Column(name="is_active")
	private char active='Y';
	
	public int getBlogcommid() {
		return blogcommid;
	}

	public void setBlogcommid(int blogcommid) {
		this.blogcommid = blogcommid;
	}

	public String getCommdata() {
		return commdata;
	}

	public void setCommdata(String commdata) {
		this.commdata = commdata;
	}

	public Date getCommdate() {
		return commdate;
	}

	public void setCommdate(Date commdate) {
		this.commdate = commdate;
	}

	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	
	
	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "BlogComment [blogcommid=" + blogcommid + ", commdata=" + commdata + ", blogid=" + blogid + ", commdate="
				+ commdate + ", user=" + user + ", active=" + active + "]";
	}

	

	
	
}
