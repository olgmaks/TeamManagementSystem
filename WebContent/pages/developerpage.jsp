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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Developer page</title>
</head>
<body>

	<jsp:include page="logoutmodal.jsp" />

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" style="margin-left: 14%;">
			<li><a href="developer"><fmt:message key="developer" /></a></li>
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
			<a href="developer"><fmt:message key="developer" /> </a>
		</h2>
	</div>
	<table style="width: 70%; margin-left: auto; margin-right: auto;">
		<tbody>
			<tr>
				<td><h3>
						<fmt:message key="hello" />
						, ${firstname} ${lastname}!
					</h3> <br></td>
			</tr>
			<tr>
				<td><fmt:message key="Specialization" /> : ${specialization}</td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
		</tbody>
	</table>

	<br>
	<br>
	<form action="submithoursreport" method="get">
		<table class="table table-striped"
			style="width: 70%; text-align: left; margin-left: auto; margin-right: auto;">
			<thead>
				<tr>
					<td><fmt:message key="Project-Number" /></td>
					<td><fmt:message key="Order" /></td>
					<td><fmt:message key="Order-Description" /></td>
					<td><fmt:message key="Hours" /></td>
					<td><fmt:message key="Input-hours" /></td>
				</tr>
			</thead>

			<tbody>
				<tr>
					<c:forEach items="${projectDevelopers}" var="projectDeveloper">
						<c:forEach items="${projects}" var="project">
							<c:forEach items="${orders}" var="order">
								<c:choose>
									<c:when test="${order.id==project.orderId}">
										<c:choose>
											<c:when test="${projectDeveloper.projectId==project.id}">
												<tr>
													<td>${project.id}</td>
													<td>${order.name}</td>
													<td>${order.description}</td>
													<td>${projectDeveloper.hours}</td>
													<td style="width: 25%;"><input
														name="reportedhours${project.id}" class="form-control"
														placeholder="<fmt:message key="Worked-hours" />"></td>
												</tr>
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:forEach>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="<fmt:message key="Send-hours-report" />"
			class="btn btn-success" style="width: 200; margin-left: 15%;" />
	</form>

</body>
</html>