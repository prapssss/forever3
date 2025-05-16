package com.forever3.model;

public class UserModel {

	private int id;
	private String first_name;
	private String last_name;
	private String user_name;
	private String email;
	private String number;
	private String password;
	private String image_url;
	private String role;

	public UserModel() {
	}

	public UserModel(String username, String password) {
		this.user_name = username;
		this.password = password;
	}

	public UserModel(int id, String first_name, String last_name, String user_name, 
			String email, String number, String password, String image_url, String role) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.email = email;
		this.number = number;
		this.password = password;
		this.image_url = image_url;
		this.role = role;
	
		
	}

	public UserModel(String first_name, String last_name, String user_name, String email,
			String number, String password, String image_url) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.email = email;
		this.number = number;
		this.password = password;
		this.image_url = image_url;
		}

	public UserModel(int id, String first_name, String last_name, String email, String number) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getImageUrl() {
		return image_url;
	}

	public void setImageUrl(String image_url) {
		this.image_url = image_url;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}