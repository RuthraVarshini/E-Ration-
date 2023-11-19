package Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String dbUsername = "root";
        String dbPassword = "12345";
        
        // User input from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the database connection
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            
            // Create a SQL query to check if the user exists
            String sql = "SELECT * FROM login WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                // User exists, login successful
                out.println("<h2>Login Successful!</h2>");
            } else {
                // User doesn't exist, login failed
                out.println("<h2>Login Failed. Please check your credentials.</h2>");
            }
            
            // Close the database connection
            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e);
        }
    }
}
