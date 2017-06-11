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
@Table(name="eventpart")
public class Eventpart {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="foreventpartid", allocationSize = 1)
	@Column
	private int eventpartid;
	
	@OneToOne
	@JoinColumn(name = "evtid")
	private Adminevent evt;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private Usertable user;
	
	@Column
	private Date epdate;
	
	@Column(name="is_active")
	private char active = 'Y';

	public int getEventpartid() {
		return eventpartid;
	}

	public void setEventpartid(int eventpartid) {
		this.eventpartid = eventpartid;
	}

	public Adminevent getEvt() {
		return evt;
	}

	public void setEvt(Adminevent evt) {
		this.evt = evt;
	}

	public Usertable getUser() {
		return user;
	}

	public void setUser(Usertable user) {
		this.user = user;
	}

	public Date getEpdate() {
		return epdate;
	}

	public void setEpdate(Date epdate) {
		this.epdate = epdate;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Eventpart [eventpartid=" + eventpartid + ", evt=" + evt + ", user=" + user + ", epdate=" + epdate
				+ ", active=" + active + "]";
	}
	
	
}
