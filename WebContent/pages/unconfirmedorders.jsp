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
<title>Uncosidered orders</title>
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
	<h3 style="margin-left: 15%">
		<fmt:message key="New-orders-from-Clients" />
	</h3>
	<br>
	<table class="table table-striped"
		style="width: 70%; height: 100%; text-align: left; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<td>Order</td>
				<td>Order Description</td>
				<td>Additional information</td>
				<td>Customer</td>
				<td>Registrate</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${orders}">
				<c:forEach var="user" items="${users}">
					<c:choose>
						<c:when test="${user.id==order.customerId}">
							<tr>
								<td>${order.name}</td>
								<td>${order.description}</td>
								<td>${order.additionalInformation}</td>
								<td>${user.firstName} ${user.lastName}</td>
								<td><a href="registrateproject?orderid=${order.id}"><img
										src="img/icons/registerproject.png"
										style="width: 50px; height: 50px;"></a></td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>