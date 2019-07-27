<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
var timeStamp = <%=request.getAttribute("timer")%>;
var clicked;
function updateTime(){
  
  timeStamp=timeStamp-1000;
  $('#time').html(timeStamp);
  if (timeStamp<=-900){
	  window.open("JoinGame", "_self");  
  }
}
$(function(){
  setInterval(updateTime, 1000);
});
</script>
<meta charset="ISO-8859-1">

<title>!!!</title>
</head>
<body bgcolor=<%=request.getAttribute("color") %>>
<h1><%=request.getAttribute("name")%><%=request.getAttribute("result") %></h1>
</body>
</html>