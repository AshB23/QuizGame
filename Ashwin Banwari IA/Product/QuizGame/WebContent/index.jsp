<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Joining a QuizGame!</title>
<style>
table, th {
    border: 1px solid black;
    border-collapse: collapse;
    padding:5px;
    text-align:center;
}
h4  {
	padding:0px;
	margin:0px;
}
body {
	background-color:Orange;
}
</style>
</head>
<body>
<%--forward to JoinGame Servlet --%>
<h1 style="text-align:center;"> Joining QuizGame </h1>
<div style="text-align:center;">

<form action = "JoinGame">
Your Name:
<input type="text" name="playerName">
<br><br>
<input type="Submit" value="Join Game">

</form>
<br>
Note: Wait for teacher to start game before trying to join! 

</div>


	
</body>
</html>