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
<link href="css/userinfo.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
	function updateUser(id) {
		var element = document.getElementById("userid");
		element.value = id;
	};
</script>

<title>Admin page</title>
</head>
<body>


	<jsp:include page="logoutmodal.jsp" />

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" style="margin-left: 14%;">
			<li><a href="admin"><fmt:message key="admin" /></a></li>
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




	<h2 style="margin-left: 15%;"><fmt:message key="Admin-Home-page" /></h2>
	<table class="table table-striped"
		style="width: 70%; height: 100%; text-align: left; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<td>User ID</td>
				<td>First name</td>
				<td>Last name</td>
				<td>Sex</td>
				<td>e-mail</td>
				<td>Password</td>
				<td>Role</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${users}">
				<tr>
					<td>${item.id}</td>
					<td>${item.firstName}</td>
					<td>${item.lastName}</td>
					<td>${item.sex}</td>
					<td>${item.email}</td>
					<td>${item.password}</td>
					<c:choose>
						<c:when test="${item.role=='customer'}">
							<td><a href="#" data-toggle="modal"
								data-target="#changeUserRole" onclick="updateUser(${item.id});">${item.role}</a></td>
						</c:when>
						<c:otherwise>
							<td>${item.role}</td>
						</c:otherwise>
					</c:choose>

				</tr>
			</c:forEach>
		</tbody>
	</table>


	<div class="modal fade" id="changeUserRole" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="margin-left: auto; margin-left: auto; margin-top: auto; margin-bottom: auto;">
		<div class="modal-dialog modal-sm">
			<form action="registratedeveloper" method="get">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"><fmt:message key="Registrate-developer" /></h4>
					</div>

					<div class="modal-body">

						<input type="hidden" id="userid" name="userid"> <input
							type="text" id="sate" name="rate" placeholder="<fmt:message key="Rate" />"
							class="form-control"> <select name="specialization"
							class="btn btn-default dropdown-toggle" style="width: 268px;">
							<option value="0">General layout</option>
							<option value="11">Architectural</option>
							<option value="13">Structural</option>
							<option value="25">HVAC</option>
							<option value="34">WS&S</option>
							<option value="41">Electrical</option>
							<option value="56">Mechanical</option>
						</select>

					</div>

					<div class="modal-footer">
						<input type="image" src="img/icons/done.png"
							style="width: 20px; height: 20px; float: left; margin-left: 30px;"
							alt="Submit Form" />
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<fmt:message key="close" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>