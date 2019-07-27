<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
var timestamp = 15;
function updateTime(){
  timestamp--;
  $('#time').html(timestamp);
  if (timestamp==0){
	  window.open("DisplayGame", "_self");  
  }
  
}
$(function(){
  setInterval(updateTime, 1000);
});


</script>
<meta charset="ISO-8859-1">
<title>NEW QUIZGAME STARTED</title>
</head>
<body>

<H1> QUIZGAME WILL START IN: <label id="time">15</label> SECONDS</H1>
<h2>
Join Game Now !
</H2>

</body>
</html>