package net.etfbl.ser;

import java.io.Serializable;

public class User implements Serializable{
	private String username;
	private String password;
	
	public User() {
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
