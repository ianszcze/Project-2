package com.revature.beans;

public class Employee {
	private int id;
	private String type;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int id, String type, String username, String password, String fname, String lname, String email) {
		super();
		this.id = id;
		this.type = type;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	
	public Employee(String type, String username, String password, String fname, String lname, String email) {
		super();
		this.type = type;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	
	public Employee(int id, String fname, String lname, String password, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.email = email;
	}
	
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Employee(int id) {
		super();
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (id != other.id)
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", type=" + type + ", username=" + username + ", password=" + password
				+ ", fname=" + fname + ", lname=" + lname + ", email=" + email + "]";
	}
	
	

}
