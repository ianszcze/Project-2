package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.ToDo;

public class ToDoServlet extends HttpServlet {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8045469870423058987L;

		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			ToDo todo = new ToDo();
			todo.setName("Clean Room");
			todo.setPriority(0);
			
			/*int i =Integer.parseInt(req.getParameter("id"));
			
			if (i >=10 ) {
				res.setStatus(404);
			}*/
			
			//System.out.println(req.getParameter("id"));
			ObjectMapper mapper = new ObjectMapper();
			//res.setHeader("Content-Type","application/json" );
			mapper.writeValue(res.getOutputStream(), todo);
			//res.setHeader(name, value);
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			ToDo newTodo = new ObjectMapper().readValue(req.getReader(), ToDo.class);
			System.out.println(newTodo);
				
		}
}
