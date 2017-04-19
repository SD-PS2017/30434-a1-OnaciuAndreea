package com.example.dto;

public class UserDTO {
	private String username;
	private String password;
	private String role;
	private String name;
	private float salary;
	

	public UserDTO(String username, String password, String role, String name, float money) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.salary = money;
	}

	public UserDTO(){
		this("","","EMPLOYEE","",0);
	}
	
	public UserDTO(String name){
		this("","","EMPLOYEE",name,0);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float money) {
		this.salary = money;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", role=" + role + ", name=" + name
				+ ", salary=" + salary + "]";
	}

}
