package com.text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/studentinfo";
        String user = "root";     
        String password = "root";  

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");//this is important otherwise Sql connection issue
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is established");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }
}
