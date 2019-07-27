<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>KahootGame!</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> <!-- import needed jQuery files -->
<script>
var timeStamp = <%=request.getAttribute("timer")%>; //Get timer time from servlet (in milliseconds)
var clicked;
function updateTime(){
  
  timeStamp=timeStamp-1000;  //Decrease the timer time by 100 every 100 milliseconds.
  if (timeStamp<=1000){
	  window.open("JoinGame?selected="+clicked, "_self");   //Open other window on timer end.
  }
}
$(function(){
  setInterval(updateTime, 1000);  //Set the interval of updateTime() to 100 milliseconds
});

function setBorders(elmnt){      //Update the borders of the possible options on click of an option to indicate selected option.
	document.getElementById("a1").style.border = "2px solid black";
	document.getElementById("a2").style.border = "2px solid black";
	document.getElementById("a3").style.border = "2px solid black";
	document.getElementById("a4").style.border = "2px solid black";
	elmnt.style.border="0.2em solid black";
	clicked=elmnt.value;  //remember which element is currently clicked to send in GET request to JoinGame servlet after time ends.
}
</script>
<style>
html{
height:100%;}
tr{
height:50%;

}
input{
height:100%;
width:100%;
font-size:14vw;
border-color:black;}
table{
table-layout:fixed;
position:fixed;
    width:100%;
    height:100%;
    top:0px;
    left:0px;}
#a1{
background-color:lightblue;
color:blue;

}
#a2{
	background-color:lightgreen;
	color:green;
}
#a3{
background-color:lightpink;
color:red;
}
#a4{
background-color:gold;
color:darkorange;
}
td{
width:50%;
}

</style>
</head>
<body>

<form action="ActionResult">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
		<tr>
			 
			<td><input id="a1" type="button"onclick="setBorders(this)" value="<%=request.getAttribute("Answer1") %>"> </td>
			<td> <input id="a2" type="button"onclick="setBorders(this)" value="<%=request.getAttribute("Answer2") %>"> </td>
		</tr>
		<tr>
			<td> <input id="a3" type="button"onclick="setBorders(this)" value="<%=request.getAttribute("Answer3") %>">  </td>
			<td> <input id="a4" type="button"onclick="setBorders(this)" value="<%=request.getAttribute("Answer4") %>">  </td>
		</tr>
	</table> 
</form>
</body>
</html>