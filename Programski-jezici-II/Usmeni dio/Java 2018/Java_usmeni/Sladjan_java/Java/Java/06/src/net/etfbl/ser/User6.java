package net.etfbl.ser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User6 implements Serializable{
	private String username;
	private transient String password;
	
	public User6() {
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
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeObject(username);
		out.writeObject(password);
	}
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		username = (String) in.readObject();
		password = (String) in.readObject();		
	}

}
