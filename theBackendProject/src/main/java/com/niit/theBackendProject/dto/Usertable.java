package com.niit.theBackendProject.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="usertable")
public class Usertable extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="foruserid", allocationSize = 1)
	@Column
	private int userid;
	
	@Column
	private String fname;

	@Column
	private String lname;
	
	@Column
	private String email;
	
	@Column
	private String profile;
	
	@Column
	private String gender;
	
	@Column
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	//	Address fields
	@Column
	private String add1;
	@Column
	private String add2;
	@Column
	private String add3;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String pincode;
	
	
	@Column
	private String pw;
	
	@Transient
	private String repw;
	
	@Column
	private String role = "User";

	@Column
	private String pno;

	@Column(name="is_active")
	private char active = 'Y';
	
	@Column(name="is_online")
	private char isOnline = 'N';
	
	@Column(name="is_approved")
	private char isApproved = 'N';

	
	
	
	public String getRepw() {
		return repw;
	}

	public void setRepw(String repw) {
		this.repw = repw;
	}

	public char getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(char isApproved) {
		this.isApproved = isApproved;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Usertable [userid=" + userid + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", profile=" + profile + ", gender=" + gender + ", status=" + status + ", add1=" + add1 + ", add2="
				+ add2 + ", add3=" + add3 + ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", pw="
				+ pw + ", role=" + role + ", pno=" + pno + ", active=" + active + ", isOnline=" + isOnline
				+ ", isApproved=" + isApproved + "]";
	}

	

	
}
