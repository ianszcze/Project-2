package com.revature.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.dao.EmployeeDao;
import com.revature.dao.ReimbursementDao;
import com.revature.util.ConnectionUtil;
import org.apache.log4j.Logger;

public class Implementation implements EmployeeDao, ReimbursementDao {

final static Logger log = Logger.getLogger(Implementation.class);
private static ConnectionUtil cu = ConnectionUtil.getInstance();
Connection conn = null;
	
	public Employee login(String username, String password) {
		Employee login = null;
		conn = cu.getConnection();
		String query = "Select * from employee where eusername = ? and euserpass = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				login = new Employee(rs.getInt("eid"), rs.getString("etype"), rs.getString("eusername"),
						rs.getString("euserpass"), rs.getString("efirstname"), rs.getString("elastname"), rs.getString("eemail"));
			}
		} catch (SQLException e) {
			log.error("catch block in login - Implementation - occured");
		}
		return login;
	}
	
	public Employee updateEmployee(int id, String fname, String lname, String email, String password) {
		Employee update = null;
		conn = cu.getConnection();
		
		String query = "call updateInfo(?,?,?,?,?)";
		try {
			PreparedStatement sp = conn.prepareCall(query);
			sp.setInt(1, id);
			sp.setString(2, password);
			sp.setString(3, fname);
			sp.setString(4, lname);
			sp.setString(5, email);
			sp.executeQuery();
		} catch (SQLException e) {
			log.error("catch block for stored procedure in updateEmployee - Implementation - occured");
		}
		
		String query2 = "Select * from employee where eid = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1,id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				update = new Employee(rs.getInt("eid"), rs.getString("etype"), rs.getString("eusername"),
						rs.getString("euserpass"), rs.getString("efirstname"), rs.getString("elastname"), rs.getString("eemail"));
			}
		} catch (SQLException e) {
			log.error("catch block retrieving info in updateEmployee - Implementation - occured");
		}
		return update;
	}

	public List<Reimbursement> employeePendingRequests(Employee employee) {
		List<Reimbursement> pendingList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			//do a join with the employee table on eid to get the firstname/lastname and then
			//add resolved requests table to the modal
			//String query = "Select * from reimbursement where (reimbursereq = 'Pending' and eid=?) order by reimburseid";
			String query = "SELECT et.efirstname, et.elastname, r.reimbursereq, r.reimbursefor, r.reimburseamt, r.submittime, r.resolvetime, " + 
					"mt.efirstname as mfirstname, mt.elastname as mlastname " + 
					"FROM reimbursement r " + 
					"LEFT JOIN employee et " + 
					"ON et.eid = r.eid " + 
					"left join employee mt on mt.eid = r.mid " + 
					"where et.eid = ? order by r.reimburseid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employee.getId());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name= rs.getString("efirstname") +" "+ rs.getString("elastname");
				String mName= rs.getString("mfirstname") +" "+ rs.getString("mlastname");
				pendingList.add(new Reimbursement(name, rs.getString("reimbursefor"), rs.getFloat("reimburseamt"), rs.getString("submitTime"),
						rs.getString("reimbursereq"), mName, rs.getString("resolveTime")));
			}
			return pendingList;
		}
		catch (SQLException e){
			log.error("catch block in employeePendingRequests - Implementation - occured");
		}
		return pendingList;
	}
	
	public List<Employee> viewAllEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			String query = "Select * from employee";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				employeeList.add(new Employee(rs.getInt("eid"), rs.getString("etype"),
						rs.getString("eusername"), rs.getString("euserpass"),
						rs.getString("efirstname"), rs.getString("elastname"), rs.getString("eemail")));
			}
			return employeeList;
		}
		catch (SQLException e){
			log.error("catch block in viewAllEmployees - Implementation - occured");
		}
		return employeeList;
	}
	
	public Employee submitRequest(Employee employee, String purpose, float amount) {
		String submitTime = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		try {
			conn = cu.getConnection();
			String query = "call submit(?,?,?,?)";
			PreparedStatement sp = conn.prepareCall(query);
			sp.setInt(1, employee.getId());
			sp.setString(2, purpose);
			sp.setFloat(3, amount);
			sp.setString(4, submitTime);
			sp.executeQuery();
			
			return employee;
			
		} catch (SQLException e) {
			log.error("catch block in subtmitRequest - Implementation - occured");
		}
		return employee;
	}
	
	public void withdrawRequest(Reimbursement reimbursement) {
		String resolveTime = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		try {
			conn = cu.getConnection();
			String query = "call withdraw(?,?)";
			PreparedStatement sp = conn.prepareCall(query);
			sp.setInt(1, reimbursement.getId());
			sp.setString(2, resolveTime);
			sp.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void approveRequest(Reimbursement reimbursement, Employee employee) {
		String resolveTime = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		try {
			conn = cu.getConnection();
			String query = "call approve(?,?,?)";
			PreparedStatement sp = conn.prepareCall(query);
			sp.setInt(1, employee.getId());
			sp.setInt(2, reimbursement.getId());
			sp.setString(3, resolveTime);
			sp.executeQuery();
		} catch (SQLException e) {
			log.error("catch block in approveRequest - Implementation - occured");
		}
	}
	
	public void denyRequest(Reimbursement reimbursement, Employee employee) {
		String resolveTime = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		try {
			conn = cu.getConnection();
			String query = "call deny(?,?,?)";
			PreparedStatement sp = conn.prepareCall(query);
			sp.setInt(1, employee.getId());
			sp.setInt(2, reimbursement.getId());
			sp.setString(3, resolveTime);
			sp.executeQuery();
		} catch (SQLException e) {
			log.error("catch block in denyRequest - Implementation - occured");
		}
	}
	
	public List<Reimbursement> viewPendingRequests(Employee employee) {
		List<Reimbursement> pendingList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			String query = "Select * from reimbursement where reimbursereq = 'Pending' order by reimburseid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pendingList.add(new Reimbursement(rs.getInt("reimburseid"), rs.getInt("eid"), rs.getInt("mid"),
						rs.getString("reimbursereq"), rs.getString("reimbursefor"),
						rs.getFloat("reimburseamt"), rs.getString("submittime"), rs.getString("resolvetime")));
			}
			return pendingList;
		}
		catch (SQLException e){
			log.error("catch block in viewPendingRequests - Implementation - occured");
		}
		return pendingList;
	}
	
	public List<Reimbursement> viewResolvedRequests(Employee employee) {
		List<Reimbursement> pendingList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			String query = "Select * from reimbursement where (reimbursereq = 'Approved' or reimbursereq='Denied' or reimbursereq='Withdrawn') order by reimburseid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pendingList.add(new Reimbursement(rs.getInt("reimburseid"), rs.getInt("eid"), rs.getInt("mid"),
						rs.getString("reimbursereq"), rs.getString("reimbursefor"),
						rs.getFloat("reimburseamt"), rs.getString("submittime"), rs.getString("resolvetime")));
			}
			return pendingList;
		}
		catch (SQLException e){
			log.error("catch block in viewResolvedRequests - Implementation - occured");
		}
		return pendingList;
	}
	
	public List<Reimbursement> viewAllPendingRequests() {
		List<Reimbursement> pendingList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			String query = "SELECT reimbursement.reimburseid, employee.efirstname, employee.elastname, reimbursement.REIMBURSEFOR, reimbursement.SUBMITTIME, reimbursement.REIMBURSEAMT " + 
					"FROM employee INNER JOIN reimbursement ON employee.eid=reimbursement.eid where reimbursereq='Pending' order by reimbursement.reimburseid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name= rs.getString("efirstname") +" "+ rs.getString("elastname");
				pendingList.add(new Reimbursement(rs.getInt("reimburseid"), name, rs.getString("reimbursefor"), rs.getFloat("reimburseamt"),
						rs.getString("submittime")));
			}
			return pendingList;
		}
		catch (SQLException e){
			log.error("catch block in viewAllPendingRequests - Implementation - occured");
		}
		return pendingList;
	}
	
	public List<Reimbursement> viewAllResolvedRequests() {
		List<Reimbursement> pendingList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			String query = "SELECT et.efirstname, et.elastname, r.reimbursereq, r.reimbursefor, r.reimburseamt, r.submittime, r.resolvetime, " + 
					"mt.efirstname as mfirstname, mt.elastname as mlastname " + 
					"FROM reimbursement r " + 
					"LEFT JOIN employee et " + 
					"ON et.eid = r.eid " + 
					"left join employee mt on mt.eid = r.mid " + 
					"where (reimbursereq = 'Approved' or reimbursereq='Denied' or reimbursereq='Withdrawn') order by r.reimburseid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name= rs.getString("efirstname") +" "+ rs.getString("elastname");
				String mName= rs.getString("mfirstname") +" "+ rs.getString("mlastname");
				pendingList.add(new Reimbursement(name, rs.getString("reimbursefor"), rs.getFloat("reimburseamt"), rs.getString("submitTime"),
						rs.getString("reimbursereq"), mName, rs.getString("resolveTime")));
			}
			return pendingList;
		}
		catch (SQLException e){
			log.error("catch block in viewAllResolvedRequests - Implementation - occured");
		}
		return pendingList;
	}
	
	public List<Reimbursement> viewMyPendingRequests(Employee employee) {
		List<Reimbursement> pendingList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			String query = "SELECT reimbursement.reimburseid, employee.efirstname, employee.elastname, reimbursement.REIMBURSEFOR, reimbursement.SUBMITTIME, reimbursement.REIMBURSEAMT " +
					"FROM employee INNER JOIN reimbursement ON employee.eid=reimbursement.eid where reimbursereq='Pending' and employee.eid=? order by reimbursement.reimburseid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employee.getId());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name= rs.getString("efirstname") +" "+ rs.getString("elastname");
				pendingList.add(new Reimbursement(rs.getInt("reimburseid"), name, rs.getString("reimbursefor"), rs.getFloat("reimburseamt"),
						rs.getString("submittime")));
			}
			return pendingList;
		}
		catch (SQLException e){
			log.error("catch block in viewMyPendingRequests - Implementation - occured");
		}
		return pendingList;
	}
	
	public List<Reimbursement> viewMyResolvedRequests(Employee employee) {
		List<Reimbursement> resolvedList = new ArrayList<>();
		try {
			conn = cu.getConnection();
			String query = "SELECT et.efirstname, et.elastname, r.reimbursereq, r.reimbursefor, r.reimburseamt, r.submittime, r.resolvetime, " + 
					"mt.efirstname as mfirstname, mt.elastname as mlastname " + 
					"FROM reimbursement r " + 
					"LEFT JOIN employee et " + 
					"ON et.eid = r.eid " + 
					"left join employee mt on mt.eid = r.mid " + 
					"where et.eid = ? and (reimbursereq = 'Approved' or reimbursereq='Denied' or reimbursereq='Withdrawn') order by r.reimburseid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employee.getId());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name= rs.getString("efirstname") +" "+ rs.getString("elastname");
				String mName= rs.getString("mfirstname") +" "+ rs.getString("mlastname");
				resolvedList.add(new Reimbursement(name, rs.getString("reimbursefor"), rs.getFloat("reimburseamt"), rs.getString("submitTime"),
						rs.getString("reimbursereq"), mName, rs.getString("resolveTime")));
			}
			return resolvedList;
		}
		catch (SQLException e){
			log.error("catch block in viewMyResolvedRequests - Implementation - occured");
		}
		return resolvedList;
	}
}
