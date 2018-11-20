package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.delegate.HomeDelegate;
import com.revature.delegate.LoginDelegate;
import com.revature.implementation.Implementation;

public class RequestHelper {
	
	final static Logger log = Logger.getLogger(RequestHelper.class);
	private HomeDelegate hd = new HomeDelegate();
	private LoginDelegate ld = new LoginDelegate();
	private Implementation implement = new Implementation();
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);
		while(switchString.indexOf("/")>0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		switch(switchString) {
		case "login": 
			if("POST".equals(req.getMethod())) {
			ld.login(req, resp);
		} break;
		
		case "home": hd.goHome(req, resp); break;
		
		case "info": 
			Employee info = (Employee) req.getSession().getAttribute("user");
			ObjectMapper mapperInfo = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			mapperInfo.writeValue(resp.getOutputStream(), info);
			break;
			
		case "update":
			ld.update(req, resp);
			break;
			
		case "viewMyPendingRequests":
			Employee employeePending = (Employee) req.getSession().getAttribute("user");
			List<Reimbursement> myPendingRequests = implement.viewMyPendingRequests(employeePending);
			ObjectMapper myPendingRequestsObject = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			myPendingRequestsObject.writeValue(resp.getOutputStream(), myPendingRequests);
			break;
			
		case "viewMyResolvedRequests":
			Employee employeeResolved = (Employee) req.getSession().getAttribute("user");
			List<Reimbursement> myResolvedRequests = implement.viewMyResolvedRequests(employeeResolved);
			ObjectMapper myResolvedRequestsObject = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			myResolvedRequestsObject.writeValue(resp.getOutputStream(), myResolvedRequests);
			break;
			
		case "submitRequest":
			Employee employee = (Employee) req.getSession().getAttribute("user");
			String purpose = req.getParameter("purpose");
			String amountInput = req.getParameter("amount");
			float amount = Float.parseFloat(amountInput);
			implement.submitRequest(employee, purpose, amount);
			log.info(employee.getUsername()+" has submitted a reimbursement request.");
			req.getSession().invalidate();
			HttpSession Session = req.getSession();
			Session.setAttribute("user", employee);
			resp.sendRedirect("html/employee.html");
			break;
			
		case "withdrawRequest":
			Employee employee2 = (Employee) req.getSession().getAttribute("user");
			ObjectMapper withdrawObject = new ObjectMapper();
			Reimbursement reimbursement = withdrawObject.readValue(req.getReader(), Reimbursement.class);
			implement.withdrawRequest(reimbursement);
			log.warn(employee2.getUsername()+" has withdrawn their reimbursement request.");
			ld.login(req, resp);
			break;
			
		case "approveRequest":
			Employee employeeA = (Employee) req.getSession().getAttribute("user");
			ObjectMapper approveObject = new ObjectMapper();
			Reimbursement reimbursement2 = approveObject.readValue(req.getReader(), Reimbursement.class);
			implement.approveRequest(reimbursement2, employeeA);
			log.info(employeeA.getUsername()+" has had their request approved.");
			ld.login(req, resp);
			break;
			
		case "denyRequest":
			Employee employeeD = (Employee) req.getSession().getAttribute("user");
			ObjectMapper denyObject = new ObjectMapper();
			Reimbursement reimbursement3 = denyObject.readValue(req.getReader(), Reimbursement.class);
			implement.denyRequest(reimbursement3, employeeD);
			log.info(employeeD.getUsername()+" has had their request denied.");
			ld.login(req, resp);
			break;
			
		case "viewAllPendingRequests": 
			List<Reimbursement> allPendingRequests = implement.viewAllPendingRequests();
			ObjectMapper allPendingRequestsObject = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			allPendingRequestsObject.writeValue(resp.getOutputStream(), allPendingRequests);
			break;
			
		case "viewAllResolvedRequests":
			List<Reimbursement> allResolvedRequests = implement.viewAllResolvedRequests();
			ObjectMapper allResolvedRequestsObject = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			allResolvedRequestsObject.writeValue(resp.getOutputStream(), allResolvedRequests);
			break;
			
		case "viewAllEmployees":
			List<Employee> allEmployees = implement.viewAllEmployees();
			ObjectMapper allEmployeesObject = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			allEmployeesObject.writeValue(resp.getOutputStream(), allEmployees);
			break;
			
		case "employeePendingRequests":
			ObjectMapper pendingRequestsObject = new ObjectMapper();
			resp.setHeader("Content-Type", "Application/json");
			Employee employeePR = pendingRequestsObject.readValue(req.getReader(), Employee.class);
			List<Reimbursement> employeePendingRequests = implement.employeePendingRequests(employeePR);
			System.out.println(employeePendingRequests);
			ObjectMapper sendEmployeePendingRequests = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			sendEmployeePendingRequests.writeValue(resp.getOutputStream(), employeePendingRequests);
			break;
			
		case "logout": ld.logout(req, resp); break;
		
		default: break;
		}
	}
}
