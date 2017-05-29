package com.niit.theBackendProject.dto;

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
@Table(name="fmember")
public class Fmember extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forfmemberid", allocationSize = 1)
	@Column
	private int fmemberid;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private Usertable user;
	
	@OneToOne
	@JoinColumn(name = "forumid")
	private Forum forum;
	
	@Column(name="is_active")
	private char active = 'Y';
	
	@Column(name="is_approved")
	private char isApproved = 'N';

	public int getFmemberid() {
		return fmemberid;
	}

	public void setFmemberid(int fmemberid) {
		this.fmemberid = fmemberid;
	}

	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public char getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(char isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "Fmember [fmemberid=" + fmemberid + ", user=" + user + ", forum=" + forum + ", active=" + active
				+ ", isApproved=" + isApproved + "]";
	}
	
	
	
	
}
