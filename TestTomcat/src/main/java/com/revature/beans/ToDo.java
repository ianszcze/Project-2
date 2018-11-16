package com.revature.beans;

import java.io.Serializable;

public class ToDo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4062299971724650525L;
	
	public ToDo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToDo(String name, Integer priority) {
		super();
		this.name = name;
		this.priority = priority;
	}
	private String name;
	private Integer priority;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
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
		ToDo other = (ToDo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ToDo [name=" + name + ", priority=" + priority + "]";
	}
	

}
