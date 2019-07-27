<%@ page language="java" contentType="text/html;"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Creating new QuizGame</title>
<style>
input{
	width:200px;
}
select{
	width:100px;
}
#Create{
	background:limegreen;
}
#Delete{
	background:red;
}
#Start{
	background: linear-gradient(124deg, #ff2400, #e81d1d, #e8b71d, #e3e81d, #1de840, #1ddde8, #2b1de8, #dd00f3, #dd00f3);
}

</style>
</head>
<body>
<h3>
Warning: Starting a new QuizGame will stop any ongoing QuizGames.
<br>
Specify the quiz set to use:
</h3>
<form action ="DisplayGame">
<select name="selectedQuizSet">
<%for (int i=0;i<(int)request.getAttribute("num");i++){ %>
<option><%=request.getAttribute("qs"+i) %></option>
<%} %>
</select>
<input id="Start" type="Submit"value="Start Game"<%if ((boolean)request.getAttribute("empty")){ %>disabled<%}%>> 
</form> <br>
<form action="QuizGameManagement">
<input id="Create"type="Submit"name="action"value="Create New QuizSet">
<input id="Delete"type="Submit"name="action"value="Delete A QuizSet">
</form>

</body>
</html>