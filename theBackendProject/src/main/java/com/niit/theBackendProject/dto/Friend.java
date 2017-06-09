package com.niit.theBackendProject.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="friend")
public class Friend extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forfriendid", allocationSize = 1)
	@Column
	private int friendid;
	
	@Column
	private int userid1;
	
	@Column
	private int userid2;
	
	@Column(name="is_friend")
	private char isFriend = 'N';
	
	@Column(name="is_active")
	private char active = 'Y';

	public int getFriendid() {
		return friendid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	

	public int getUserid1() {
		return userid1;
	}

	public void setUserid1(int userid1) {
		this.userid1 = userid1;
	}

	public int getUserid2() {
		return userid2;
	}

	public void setUserid2(int userid2) {
		this.userid2 = userid2;
	}

	public char getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(char isFriend) {
		this.isFriend = isFriend;
	}

	@Override
	public String toString() {
		return "Friend [friendid=" + friendid + ", userid1=" + userid1 + ", userid2=" + userid2 + ", isFriend="
				+ isFriend + ", active=" + active + "]";
	}

	
	
	
}
