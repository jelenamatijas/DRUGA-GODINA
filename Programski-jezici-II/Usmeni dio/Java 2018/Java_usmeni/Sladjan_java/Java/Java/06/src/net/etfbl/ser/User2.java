package net.etfbl.ser;


public class User2 {
	private String username;
	private String password;
	
	public User2() {
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
