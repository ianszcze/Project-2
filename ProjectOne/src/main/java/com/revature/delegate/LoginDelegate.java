package com.revature.delegate;
import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.implementation.Implementation;

public class LoginDelegate {
	public EmployeeDao implement = new Implementation();
	final static Logger log = Logger.getLogger(LoginDelegate.class);

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Employee login = null;
		HttpSession var = req.getSession(false);
		if (var == null) {
			String username = req.getParameter("user");
			String password = req.getParameter("pass");
			login = implement.login(username,password);
			log.info(login.getUsername()+" has logged in.");
		} else {
			login = (Employee) req.getSession().getAttribute("user");
		}
		if(login == null) {
			resp.sendRedirect("html/login.html");
		} else if (login.getType().equals("Manager")) {
			HttpSession session = req.getSession();
			session.setAttribute("user", login);
			resp.sendRedirect("html/manager.html");
		} else if (login.getType().equals("Employee")) {
			HttpSession session = req.getSession();
			session.setAttribute("user", login);
			resp.sendRedirect("html/employee.html");
		}
	}
	
	public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Employee info = (Employee) req.getSession().getAttribute("user");
		String firstname = req.getParameter("fname");
		if (firstname.equals("")) {
			firstname = info.getFname();
		}
		String lastname = req.getParameter("lname");
		if (lastname.equals("")) {
			lastname = info.getLname();
		}
		String password = req.getParameter("password");
		if (password.equals("")) {
			password = info.getPassword();
		}
		String email = req.getParameter("email");
		if (email.equals("")) {
			email = info.getEmail();
		}
		int id = info.getId();
		Employee update = implement.updateEmployee(id, firstname, lastname, email, password);
		req.getSession().invalidate();
		HttpSession session = req.getSession();
		session.setAttribute("user", update);
		log.info(info.getUsername()+ " updated their information.");
		login(req,resp);
		/*resp.sendRedirect("html/manager.html");*/
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("The user has logged out.");
		req.getSession().invalidate();
		resp.sendRedirect("html/login.html");
	}
}