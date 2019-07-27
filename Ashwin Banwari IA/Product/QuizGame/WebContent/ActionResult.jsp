<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#return{
	width:20%;
	height:5em;
	margin-top:1%;
	background:orange;
}
</style>
<title>Result</title>
</head>
<body>
<h4>
<%=request.getAttribute("result") %>
</h4>
<form action = <%=request.getAttribute("destination") %>>
	
	<input id="return"type="Submit"value="Return">
	
</form>
</body>
</html>