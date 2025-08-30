package com.text;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewStudent extends GenericServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Step-1: Establish connection
            con = DBConnection.getConnection();

            if (con == null) {
                out.println("Database connection failed!");
                return;
            }else {
            	out.println("Database connection failed!");
            }

           
            String selectQuery = "SELECT * FROM students";
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            
            while (rs.next()) {
                out.println("<tr>");
               
                	out.println("<td class='text-center'>" + rs.getString("stdName") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdRoll") + "</td>");
                	out.println("<td class='text-center'>" + rs.getInt("stdAge") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdEmail") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdBranch") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdAdd") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdCity") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdState") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdPin") + "</td>");
                	out.println("<td class='text-center'>" + rs.getString("stdCollege") + "</td>");

                
                out.println("</tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='11'>Error: " + e.getMessage() + "</td></tr>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
