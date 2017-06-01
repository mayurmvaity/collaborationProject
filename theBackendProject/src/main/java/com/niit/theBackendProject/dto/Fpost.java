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
@Table(name="fpost")
public class Fpost extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forfpostid", allocationSize = 1)
	@Column
	private int fpostid;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private Usertable user;
	
	@OneToOne
	@JoinColumn(name = "forumid")
	private Forum forum;
	
	@Column
	private String fpdata;
	
	@Column
	private Date fpdate;
	
	@Column(name="is_active")
	private char active = 'Y';

	public int getFpostid() {
		return fpostid;
	}

	public void setFpostid(int fpostid) {
		this.fpostid = fpostid;
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

	public String getFpdata() {
		return fpdata;
	}

	public void setFpdata(String fpdata) {
		this.fpdata = fpdata;
	}

	public Date getFpdate() {
		return fpdate;
	}

	public void setFpdate(Date fpdate) {
		this.fpdate = fpdate;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Fpost [fpostid=" + fpostid + ", user=" + user + ", forum=" + forum + ", fpdata=" + fpdata + ", fpdate="
				+ fpdate + ", active=" + active + "]";
	}
	
	
	
}
