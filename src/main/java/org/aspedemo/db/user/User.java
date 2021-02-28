package org.aspedemo.db.user;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String userName;
	
	@Column(nullable = false)
	private String realName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private Date lastLogin;
	
	private String passwordHash;
	
	@Column(nullable = false)
	private boolean isActive = false;
	
	public HashMap<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<>();
		if (id != null) {
			map.put("id", id);
		}
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("email", email);
		map.put("lastLogin", lastLogin);
		map.put("passwordHash", passwordHash);
		map.put("isActive", isActive);
		
		return map;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
