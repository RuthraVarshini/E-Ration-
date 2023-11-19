package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUpServlet")
public class signUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3307/db"; // Change to your database URL
        String dbUsername = "root"; // Change to your database username
        String dbPassword = "12345"; // Change to your database password

        // User input from the sign-up form
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String aadhar = request.getParameter("aadhar");
        String ration = request.getParameter("ration");
        String gender = request.getParameter("gender");
        String district = request.getParameter("district");

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            // Create a SQL query to insert user data into a table (e.g., users)
            String sql = "INSERT INTO signup (name, phone, aadhar, ration, gender, district) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, aadhar);
            preparedStatement.setString(4, ration);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, district);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Sign-up successful
                out.println("<h2>Sign-up successful!</h2>");
            } else {
                // Sign-up failed
                out.println("<h2>Sign-up failed. Please try again.</h2>");
            }

            // Close the database connection
            conn.close();
        } 
        catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
