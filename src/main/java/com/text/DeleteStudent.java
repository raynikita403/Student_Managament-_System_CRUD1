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


public class DeleteStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteStudent() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String roll = request.getParameter("roll"); 

        if (roll == null || roll.trim().isEmpty()) {
            response.getWriter().println("Student roll number is required!");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            if (con == null) {
                response.getWriter().println("Database connection failed!");
                return;
            }else {
            	 response.getWriter().println("Database connected Successfully!");
            }

            String deleteQuery = "DELETE FROM students WHERE stdRoll = ?";
            ps = con.prepareStatement(deleteQuery);
            ps.setString(1, roll);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.getWriter().println("Student deleted successfully!");
            } else {
                response.getWriter().println("Student with this roll number not found.");
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
