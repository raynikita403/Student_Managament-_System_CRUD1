package com.text;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class AddStudent
 */
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");

	    String name = request.getParameter("stdName");
	    String roll = request.getParameter("stdRoll");
	    int age = Integer.parseInt(request.getParameter("stdAge"));
	    String email = request.getParameter("stdEmail");
	    String branch = request.getParameter("stdBranch");
	    String address = request.getParameter("stdAdd");
	    String city = request.getParameter("stdCity");
	    String state = request.getParameter("stdState");
	    String pin = request.getParameter("stdPin");
	    String college = request.getParameter("stdCollege");

	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = DBConnection.getConnection();
	        if (con == null) {
	            response.getWriter().println("Database connection failed!");
	            return;
	        }else {
	        	 response.getWriter().println("Database connection Successfully!");
	        }

	        String InsertQuery = "INSERT INTO students(stdName, stdRoll, stdAge, stdEmail, stdBranch, stdAdd, stdCity, stdState, stdPin, stdCollege) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        ps = con.prepareStatement(InsertQuery);
	        
	        ps.setString(1, name);
	        ps.setString(2, roll);
	        ps.setInt(3, age);
	        ps.setString(4, email);
	        ps.setString(5, branch);
	        ps.setString(6, address);
	        ps.setString(7, city);
	        ps.setString(8, state);
	        ps.setString(9, pin);
	        ps.setString(10, college);

	        int result = ps.executeUpdate();
	        if (result > 0) {
	            response.getWriter().println("Student added successfully!");
	        } else {
	            response.getWriter().println("Failed to add student.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        response.getWriter().println("SQL Error: " + e.getMessage());
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	}
