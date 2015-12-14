<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language}" />

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<title>Registration page</title>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="index"><fmt:message
					key="index.Home" /> </a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->

		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div style="margin-left: 40%;">
		<div style="margin-left: 0%;">
<!-- 			<h4 style="margin-left: -3%"> -->
				<fmt:message key="New-user-registration-page" />
<!-- 			</h4> -->
			<br> <br>
			<c:choose>
			<c:when test="${invalidinput==true}">
				<font color="red"><fmt:message key="Invalid-input" /></font>
			</c:when>
			</c:choose>
		</div>
		
		<form action="registratenewuser" method="post">
			<table style="width:">

				<tr>
					<td><input type="text" name="firstname" class="form-control"
						placeholder="<fmt:message key="First-name" />" /></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td><input type="text" name="lastname" class="form-control"
						placeholder="<fmt:message key="Last-name" />" /></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td><select name="sex" class="btn btn-default dropdown-toggle">
							<option value="male"><fmt:message key="Male" /></option>
							<option value="female"><fmt:message key="Female" /></option>
					</select></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td><input type="text" name="e-mail" class="form-control"
						placeholder="e-mail" /></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td><input type="password" name="password"
						class="form-control" placeholder="<fmt:message key="Password" />" /></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td><input type="image" src="img/icons/registrationicon.png"
						style="margin-left: 20%; 100 px; height: 100px;" alt="Submit Form" /></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>