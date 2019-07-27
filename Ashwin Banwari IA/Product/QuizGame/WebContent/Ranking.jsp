<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
var timeStamp = 3;
var clicked;
function updateTime(){
  
  timeStamp--;
  $('#time').html(timeStamp);
  if (timeStamp==0){
	  window.open("DisplayGame", "_self");  
  }
}
$(function(){
  setInterval(updateTime, 1000);
});
</script>
<style>
h1{
	text-align:center;
	color:orange;
}
h2{
	margin-left:15%;
}
</style>
<meta charset="ISO-8859-1">
<title>QuizGame!</title>
</head>
<body>
<h1>
RANKINGS
</h1>
<h2>
	1.<%=request.getAttribute("name1") %> - <%=request.getAttribute("score1") %>
	<br>
	2. George - 0
	<br>
	3. Emma - 0
	<br>
	
	
</h2>
</body>
</html>