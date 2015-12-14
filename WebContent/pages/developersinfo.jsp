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
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>

	<jsp:include page="logoutmodal.jsp"></jsp:include>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" style="margin-left: 14%; width: 400px;">
			<li style="margin-left: auto; width: 100px;"><a href="manager"><fmt:message
						key="manager" /></a></li>
			<li style="margin-left: auto; width: 100px;"><a
				href="developersinfo"><fmt:message key="Developers" /></a></li>
			<li style="margin-left: auto; width: 150px;"><a
				href="unconfirmedorders"><fmt:message key="Orders" /></a></li>
		</ul>
		<ul class="nav navbar-nav"
			style="margin-left: 50%; margin-right: 50%; position: absolute;">
			<li><a href="index"><img src="img/icons/homeicon.png"
					style="width: 30px; height: 20px;"></a></li>

		</ul>
		<ul class="nav navbar-nav"
			style="width: 150px; margin-right: 15%; float: right;">
			<li><div style="margin-top: 10px;">
					<a href="#" data-toggle="modal" data-target="#logoutModal"><img
						style="width: 30px; height: 30px;" src="${loggingimage}"> <font
						color="white">${user.email}</font></a>
				</div></li>

		</ul>
	</div>
	</nav>
	<br>
	<br>
	<br>
	<div style="margin-left: 15%;">
		<h2>
			<fmt:message key="Developers-info-page" />
		</h2>
		<br> <br>
	</div>

	<table class="table table-striped"
		style="width: 70%; text-align: left; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<td>ID</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Specialization</td>
				<td>Hours</td>
				<td>Rate</td>
				<td>Set on project</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="developer" items="${developers}">
				<tr>
					<td>${developer.id}</td>
					<td>${developer.firstName}</td>
					<td>${developer.lastName}</td>
					<td>${developer.task.specialization}</td>
					<td>${developer.freeHours}</td>
					<td>${developer.rate}</td>
					<td>Edit</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>