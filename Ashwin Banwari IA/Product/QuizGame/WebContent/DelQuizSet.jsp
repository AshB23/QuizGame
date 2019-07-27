<%@ page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#Delete{
margin-left:10%;
width:20%;
height:3em;
background:red;
}
#return{
	width:20%;
	height:5em;
	margin-top:1%;
	background:orange;
}
select{
margin-left:30%;
width:100px;
}
</style>
<title>Delete a QuizSet</title>
</head>
<body>
<form action ="ActionResult">  
<select name="selectedQuizSet"><!-- Create new form and drop down menu -->
<%for (int i=0;i<(int)request.getAttribute("num");i++){ %> 
<option><%=request.getAttribute("qs"+i) %></option>
<%} %> <!-- Use java loop to create drop down option for each existing quiz set -->
</select>
<br>
<h3>Click button to permanently delete selected QuizSet.</h3>
<br>
<input id="Delete"type="Submit"value="Delete"<%if ((boolean)request.getAttribute("empty")){ %>disabled<%}%>> 
</form>            <!-- Disable delete button if no existing quiz sets -->
<br>
<form action = "CreateGame">
<input id="return" type="Submit"value="Return">  <!-- Go back to home page -->
</form>

</body>
</html>