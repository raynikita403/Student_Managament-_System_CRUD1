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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class GetStudent
 */

public class GetStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8"); // Always set encoding for JSON
//		  PrintWriter out = response.getWriter();
		  String roll = request.getParameter("stdRollFetch");
		    Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try (PrintWriter out = response.getWriter()) {
	            con = DBConnection.getConnection();
	            if (con == null) {
	                out.println("{}"); // return empty JSON 
	                					//before we response.getWriter().println("Database connection failed!"); it is breaking json flow so issue occure
	                return;
	            }
	            
	            //we want get data from db 
	            String selectQuery = "SELECT * FROM students WHERE stdRoll=?";
	            ps =con.prepareStatement(selectQuery);
	            ps.setString(1, roll);
	            //excute Query 
	           rs= ps.executeQuery();
	           
	           if (rs.next()) {
	        	    out.println("{");
	        	    
	        	    out.println("\"stdName\":\"" + rs.getString("stdName") + "\",");
	        	    out.println("\"stdRoll\":\"" + rs.getString("stdRoll") + "\",");
	        	    out.println("\"stdAge\":\"" + rs.getInt("stdAge") + "\",");
	        	    out.println("\"stdEmail\":\"" + rs.getString("stdEmail") + "\",");
	        	    out.println("\"stdBranch\":\"" + rs.getString("stdBranch") + "\",");
	        	    out.println("\"stdAdd\":\"" + rs.getString("stdAdd") + "\",");
	        	    out.println("\"stdCity\":\"" + rs.getString("stdCity") + "\",");
	        	    out.println("\"stdState\":\"" + rs.getString("stdState") + "\",");
	        	    out.println("\"stdPin\":\"" + rs.getString("stdPin") + "\",");
	        	    out.println("\"stdCollege\":\"" + rs.getString("stdCollege") + "\"");
	        	    out.println("}");
	        	} else {
	        	    out.println("{}"); 
	        	}
	}catch (Exception e) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
