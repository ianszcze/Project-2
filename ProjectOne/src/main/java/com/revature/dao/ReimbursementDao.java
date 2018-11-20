package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;

public interface ReimbursementDao {
	public void approveRequest(Reimbursement reimbursement, Employee employee);
	public void denyRequest(Reimbursement reimbursement, Employee employee);
	public void withdrawRequest(Reimbursement reimbursement);
	public List<Reimbursement> viewPendingRequests(Employee employee);
	public List<Reimbursement> viewResolvedRequests(Employee employee);
	public List<Reimbursement> viewMyPendingRequests(Employee employee);
	public List<Reimbursement> viewMyResolvedRequests(Employee employee);
	public List<Reimbursement> viewAllPendingRequests();
	public List<Reimbursement> viewAllResolvedRequests();
}
