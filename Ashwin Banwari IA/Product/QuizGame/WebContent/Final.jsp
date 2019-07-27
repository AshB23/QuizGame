<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QuizGame!</title>
</head>
<body>
<h1>
FINAL SCORE! 
</h1>
<h2>
	1.<%=request.getAttribute("name1") %> - <%=request.getAttribute("score1") %>
	<br>
	2.<%=request.getAttribute("name2") %> - <%=request.getAttribute("score2") %>
	<br>
	3.<%=request.getAttribute("name3") %> - <%=request.getAttribute("score3") %>
	<br>
</h2>
<br><br><br>
<form action = "CreateGame">
	
	<input id="return"type="Submit"value="Return">
	
</form>

</body>
</html>