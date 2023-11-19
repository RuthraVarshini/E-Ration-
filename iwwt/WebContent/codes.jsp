<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<style>
  table{
  width:100%;
  border-collapse:collapse;
  }
  th,td{
  border:1px solid #ddd;
  padding:8px;
  text-align:left;
  }
  th{
  background-color:#f2f2f2;
  
  }
  tr:nth-child(even){
  background-color:#f2f2f2;
  }
</style>
<table border="1">
<tr>
<th>sno</th>
<th>name</th>
</tr>
<%
 String url="jdbc:mysql://localhost:3307/db";
String username="root";
String password="12345";
try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection=DriverManager.getConnection(url,username,password);
	String sql="SELECT * from me";
	Statement statement=connection.createStatement();
	ResultSet resultSet=statement.executeQuery(sql);
	while(resultSet.next()){

%>
<tr>
<td><%=resultSet.getString("sno") %></td>
<td><%=resultSet.getString("name") %></td>
</tr>
<%
}
	resultSet.close();
	statement.close();
	connection.close();
}catch(Exception e){
	e.printStackTrace();
}
%>

</table>
</body>
</html>