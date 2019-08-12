package entity;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String user_name;
	private String password;
	private String authoritative_code;
	private String explaination;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthoritative_code() {
		return authoritative_code;
	}
	public void setAuthoritative_code(String authoritative_code) {
		this.authoritative_code = authoritative_code;
	}
	public String getExplaination() {
		return explaination;
	}
	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password
				+ ", authoritative_code=" + authoritative_code + ", explaination=" + explaination + "]";
	}
	
}
