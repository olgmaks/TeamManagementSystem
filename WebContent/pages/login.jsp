<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	<font size="2" color="red">${errormessage}</font>
	<c:choose>
		<c:when test="${usernamevalidationcondition}">
			<form action="login" method="post">
				<table>
					<tr>
						<td><input type="text" name="e-mail" class="form-control"
							placeholder="e-mail" value="${emailfield}" /></td>
					</tr>
					<tr>
						<td><input type="password" name="password"
							class="form-control" placeholder="Password" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit and send on server"
							class="btn btn-success" /></td>
					</tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<h2>Hello, ${username}</h2>
			<form action="logout" method="get">
				<button type="submit" class="btn btn-warning">Logout</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>