package com.lizhenghome.bujicheck.jdo;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class BujiInfo {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.SEQUENCE)
	private Long id;
	
	@Persistent
	private String phoneNumber;

	@Persistent
	private String bujiStatus;
	
	@Persistent
	private String position;
	
	@Persistent
	private Date sendDate;
	
	public BujiInfo(String phoneNumber, String bujiStatus, String position) {
		this.phoneNumber = phoneNumber;
		this.bujiStatus = bujiStatus;
		this.position = position;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBujiStatus() {
		return bujiStatus;
	}

	public void setBujiStatus(String bujiStatus) {
		this.bujiStatus = bujiStatus;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

}
