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

<title>Project details</title>
</head>
<body>

	<jsp:include page="logoutmodal.jsp" />

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
	<div style="width: 80%; margin-left: auto; margin-right: auto;">
		<ul class="nav nav-tabs">
			<%-- 	 --%>
			<li style="margin-left: 5%;"><a
				href="projectdetails?id=${projectid}"><fmt:message key="Project" /></a></li>
			<li><a href="projectdevelopers"><fmt:message
						key="Developers" /></a></li>
		</ul>
		<a href="manager" style="margin-left: 90%"><img
			src="img/icons/projectmodificationdone.png"
			style="width: 50px; height: 50px;"></a>

		<h3 style="margin-left: 5%">
			<fmt:message key="Project" />
			"${projectname}"
		</h3>
		<table
			style="width: 80%; text-align: left; margin-left: auto; margin-right: 15%;"
			border="0" cellpadding="2" cellspacing="2">
			<tbody>
				<tr>
					<td>
						<table style="">
							<tr>
								<td><fmt:message key="Order-Description" />:</td>
								<td style="width: 10%"></td>
								<td>${description}</td>
							</tr>
							<tr>
								<td><fmt:message key="Additional-Information" />:</td>
								<td></td>
								<td>${additionalinfo}</td>
							</tr>
							<tr>
								<td><fmt:message key="Price" />:</td>
								<td></td>
								<td>${projectprice}$</td>
							</tr>
							<tr>
								<td><fmt:message key="Status" />:</td>
								<td></td>
								<td>${status}</td>
							</tr>

						</table>
					</td>
					<td width="60%">
						<h3 style="text-align: right;">
							<fmt:message key="To-do-list" />
						</h3>
						<table class="table table-striped" style="font-size: 13px;">
							<thead>
								<tr>
									<td><fmt:message key="Task" /></td>
									<td><fmt:message key="Specialization" /></td>
									<td><fmt:message key="Developers-quantity" /></td>
									<td><fmt:message key="Start" /></td>
									<td><fmt:message key="End" /></td>
									<td><fmt:message key="Uncovered-hours" /></td>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="task" items="${tasks}">
									<tr>
										<td style="width: 15%;""><img
											src="img/icons/spec/spec${task.task.id}.png"
											style="width: 40px; height: 40px;"></td>
										<%-- 									<td>${task.task.id}</td> --%>
										<td>${task.task.specialization}</td>
										<td>${task.developerQuantity}</td>
										<td>${task.startDate}</td>
										<td>${task.endDate}</td>
										<td>${task.loadHours}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<br>


		<table
			style="width: 70%; text-align: left; margin-left: auto; margin-right: auto;">
			<tr>
				<td></td>
				<td width="30%"></td>
				<td><h4>
						<fmt:message key="Developers-team" />
					</h4></td>
			</tr>
		</table>
		<a href="projectdevelopers" style="margin-left: 15%"><img
			src="img/icons/adddeveloper2.png"
			style="width: 100px; height: 100px;"></a>
		<table class="table table-striped"
			style="width: 70%; text-align: left; margin-left: auto; margin-right: auto;">
			<thead>
				<tr>
					<td><fmt:message key="First-Name" /></td>
					<td><fmt:message key="Last-Name" /></td>
					<td><fmt:message key="Specialization" /></td>
					<td><fmt:message key="Hours" /></td>
					<td><fmt:message key="Start" /></td>
					<td><fmt:message key="End" /></td>
					<td><fmt:message key="Rate" /></td>
					<td><fmt:message key="Price" /></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="developer" items="${developers}">
					<c:forEach var="projectdeveloper" items="${projectdevelopers}">
						<c:choose>
							<c:when test="${projectdeveloper.developerId==developer.id}">
								<tr>
									<td>${developer.firstName}</td>
									<td>${developer.lastName}</td>
									<td>${developer.task.specialization}</td>
									<td>${projectdeveloper.hours}</td>
									<td>${projectdeveloper.startDate}</td>
									<td>${projectdeveloper.endDate}</td>
									<td>${developer.rate}</td>
									<td>${projectdeveloper.price}</td>
									<td><a
										href="removeprojectdeveloper?projectid=${projectid}
									&developerid=${developer.id}"><img
											src="img/icons/removedeveloper.png"
											style="width: 30px; height: 30px;"></a></td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>