<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Score</title>
</head>
<body>
Good job! You got a score of:
<h1>
<%=request.getAttribute("studentScore") %></h1>
</body>
<form action = "/QuizGame">
	
	<input id="return"type="Submit"value="Return">
	
</form>
</html>