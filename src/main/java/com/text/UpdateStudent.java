package com.text;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class UpdateStudent
 */

public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudent() {
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
		// TODO Auto-generated method stub
		 response.setContentType("text/plain");//here we getting text so text/plain in other somehow chunks of html we getting so..text/html
		  String roll = request.getParameter("stdRoll");
		  if (roll == null || roll.isEmpty()) {
	            response.getWriter().println("Roll number is required!");
	            return;
	        }
		  String name = request.getParameter("stdName");
		  String studentAge=request.getParameter("stdAge");
		  int age=Integer.parseInt(studentAge);
		  
		   String email = request.getParameter("stdEmail");
	        String branch = request.getParameter("stdBranch");
	        String address = request.getParameter("stdAdd");
	        String city = request.getParameter("stdCity");
	        String state = request.getParameter("stdState");
	        String pin = request.getParameter("stdPin");
	        String college = request.getParameter("stdCollege");
	        
	        Connection con = null;
	        PreparedStatement ps = null;

	        try (PrintWriter out = response.getWriter()) {
	            con = DBConnection.getConnection();
	            if (con == null) {
	                out.println("DataBase Not connected "); // return empty JSON 
	                					//before we response.getWriter().println("Database connection failed!"); it is breaking json flow so issue occure
	                return;
	            }else {
	            	out.println("DataBase connected successfully.. "); 
	            }
	            
	            String updateQuery = "UPDATE students SET stdName=?, stdAge=?, stdEmail=?, stdBranch=?, stdAdd=?, stdCity=?, stdState=?, stdPin=?, stdCollege=? WHERE stdRoll=?";
	            	ps=con.prepareStatement(updateQuery);
	            	
	            	ps.setString(1, name);
	                ps.setInt(2, age);
	                ps.setString(3, email);
	                ps.setString(4, branch);
	                ps.setString(5, address);
	                ps.setString(6, city);
	                ps.setString(7, state);
	                ps.setString(8, pin);
	                ps.setString(9, college);
	                ps.setString(10, roll);
	                
	                int result =ps.executeUpdate();
	                if (result > 0) {
	                    response.getWriter().println("Student updated successfully!");
	                } else {
	                    response.getWriter().println("Student not found or no changes made.");
	                }

	} catch (Exception e) {
        e.printStackTrace();
        response.getWriter().println("Error: " + e.getMessage());
    }finally {
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	}

}
