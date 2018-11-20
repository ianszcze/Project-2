package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDao {
	public Employee login(String username, String password);
	public List<Employee> viewAllEmployees();
	public Employee updateEmployee(int id, String firstname, String lastname, String email, String password);
	public Employee submitRequest(Employee employee, String reason, float amount);
}
