<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Creating a QuizSet</title>
<style>
input[type="submit"]{
	width:70%;
	height:4em;
	font-size:100%;
	margin-top: 3%;
	background:limegreen;
	
}

#return{
	width:20%;
	height:5em;
	margin-top:1%;
	background:orange;
}
.title{
	font-size:200%;
	display:inline-block;
	width:50%;
	height:80%;
}
input[name="title"]{
	font-size:100%;
	width:50%
}
table{
  width:20%;
  background-color: red;
}
input[type="text"]{
	width:80%;
	display:inline-block;
	margin-left:10%;
}
#titletext{
	width:55%;
}

</style>
</head>
<body>
	<h1>
	QuizSet can contain up to 20 words/phrases
	</h1>
	<form action="ActionResult">
	<table style="display:inline-block; vertical-align:top;">
		<tr>
			<th> 中文 </th>  <!-- Header: Chinese Words -->
			<th> 英文 </th>  <!-- Header: English Words -->
		</tr>
		<% for(int i =0;i<20;i++){ %>
		<tr> <!-- Create 20 rows to add chinese words and respective translation -->
			<td><input name="ch<%=i%>" type="text"> </td>
			<td><input name="en<%=i%>"type="text"> </td>
		</tr>
		<% } %>
			
	</table> <!-- Enter Quiz Set name/subject -->
	<div class="title">题目:  <input id= "titletext" name="title"type="text"> 
	<input type="Submit"value="Create QuizSet"> <!-- onClick of button to Create quizset -->
	</div> 
	</form>
	
	<form action = "CreateGame">
	
	<input id="return"type="Submit"value="Return">
	<!-- Button to return to previous page -->
	</form>
	
</body>
</html>