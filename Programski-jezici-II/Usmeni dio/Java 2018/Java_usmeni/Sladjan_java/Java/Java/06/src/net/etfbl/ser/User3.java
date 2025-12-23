package net.etfbl.ser;

import java.io.Serializable;

public class User3 implements Serializable{
	private String username;
	private transient String password;
	
	public User3() {
		username = password = "default";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
