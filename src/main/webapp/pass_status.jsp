<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	${ message }
	<p id="status"></p>
	<form action="pass_status" method="POST">
		Pass ID : <input type="text" name="passID">
		Aadhar number : <input type="text" name="aadharNumber">
		<input type="submit">
	</form>
</body>
</html>