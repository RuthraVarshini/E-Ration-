<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ration Table</title>
</head>
<body>
	<style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
    <h1><b>e-Ration</b></h1>
    <h2>Product Details</h2>
    <table border="1">
        <tr>
            <th>name</th>
            <th>age</th>
            <th>dob</th>
            <th>phone</th>
        </tr>
        <%
            // Database connection parameters
            String url = "jdbc:mysql://localhost:3307/db"; // Replace with your database name
            String username = "root";// Replacroote with your database username
            String password = "12345"; // Replace with your database password

            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Create a connection to the database
                Connection connection = DriverManager.getConnection(url, username, password);

                // Create a SQL statement
                String sql = "SELECT * FROM sample";
                Statement statement = connection.createStatement();

                // Execute the query and retrieve the result set
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
        %>
        <tr>
           
            <td><%= resultSet.getString("name") %></td>
            <td><%= resultSet.getString("age") %></td>
            <td><%= resultSet.getString("dob") %></td>
            <td><%= resultSet.getString("phone") %></td> <!-- Corrected column name -->
        </tr>
        <%
                }

                // Close the database resources
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>
