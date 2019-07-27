<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QuizGame</title>
<%@ page import="pslayer.GameManager" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
var timeStamp = <%=request.getAttribute("timer")%>;
function updateTime(){
  
  timeStamp=timeStamp-1000;
   //$('#time').html(timeStamp); 
  if (timeStamp<=0){
	  window.open("JoinGame", "_self");  
  }
}
$(function(){
  setInterval(updateTime, 1000);
});

</script>
<style>
h1{
	color: orange;
}
</style>
</head>
<body>
<h1 style="text-align:center;" id="time"> Game will begin shortly! </h1>

</body>
</html>