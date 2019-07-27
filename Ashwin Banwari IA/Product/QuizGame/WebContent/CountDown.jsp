<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1{
font-size:13vw;
padding:0;
margin:0;
color:red;
}
#main{
height:100%;
width:100%;
font-size:18vw;
border-color:black;
text-align:center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
var timestamp = 6;
function updateTime(){
  
  timestamp--;
  if (timestamp==2){
	  window.open("DisplayGame", "_self");  
  }  
  if (timestamp>=0){
	  $('#time').html(timestamp);  //    JQuery/AJAX sets element with id "time" to the value of var timestamp
  }
}
$(function(){
  setInterval(updateTime, 1000);  
});
</script>
<meta charset="ISO-8859-1">
<title>Count down</title>

</head>
<body>
<h1 id="time"style="text-align:center;">6</h1>
<div id="main"><%=request.getAttribute("question") %></div>
</body>





</html>