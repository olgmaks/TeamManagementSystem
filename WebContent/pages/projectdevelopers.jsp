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

<script type="text/javascript">
	$(function() {
		$("[title|='begindate']").datepicker(
				{
					defaultDate : "+1w",
					changeMonth : true,
					numberOfMonths : 1,
					dateFormat : 'dd.mm.yy',
					onClose : function(selectedDate) {
						$("[title|='enddate']").datepicker("option", "minDate",
								selectedDate);
					}
				});
		$("[title|='enddate']").datepicker(
				{
					defaultDate : "+1w",
					changeMonth : true,
					numberOfMonths : 1,
					dateFormat : 'dd.mm.yy',
					onClose : function(selectedDate) {
						$("[title|='begindate']").datepicker("option",
								"maxDate", selectedDate);
					}

				});
	});
</script>

<script>
	$(function() {
		$("#daterestriction").datepicker({
			dateFormat : 'dd.mm.yy',
		})

	});
</script>

<script type="text/javascript">
	$(function() {
		$("#specializationrestriction").on("focus", function() {
			$("#specializationrestriction").val("");
		});
		var availableTags = [ "General layout", "Architectural", "Structural",
				"HVAC", "WS&S", "Electrical", "Mechanical" ];
		$('#specializationrestriction').autocomplete(
				{
					minLength : 0,
					source : function(request, response) {
						var data = $.grep(availableTags, function(value) {
							return value.substring(0, request.term.length)
									.toLowerCase() == request.term
									.toLowerCase();
						});
						response(data);
					}
				}).focus(function() {
			$(this).autocomplete("search", "");
		});
	});
</script>
<title>Project developers</title>
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
			<li style="margin-left: 5%;"><a
				href="projectdetails?id=${projectid}"><fmt:message key="Project" /></a></li>
			<li><a href="projectdevelopers"><fmt:message
						key="Developers" /></a></li>

		</ul>
		<a href="manager" style="margin-left: 90%"><img
			src="img/icons/projectmodificationdone.png"
			style="width: 50px; height: 50px;"></a> <br>

		<h2 style="width: 70%; text-align: left; margin-left: 15%">
			<fmt:message key="Free-developers-list" />
		</h2>


		<form action="filterprojectdevelopers" method="get">
			<table class="table table-striped"
				style="width: 50%; text-align: left; margin-left: auto; margin-right: 10%;">
				<thead>
					<tr>

					</tr>
					<tr>
						<td style="margin-bottom: auto; margin-top: 70%"><fmt:message
								key="Filtered-result-set" />: ${fiteredsize} <br> <br>
							<br> <fmt:message key="Restrictions" /></td>
						<td><input type="image" src="img/icons/filter.png"
							style="width: 100px; height: auto;" alt="Submit Form" /></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input name="specializationrestriction"
							id="specializationrestriction" class="form-control"
							placeholder="<fmt:message key="Specialization" />"
							value="${specializationrestriction}"></td>
						<td><input id="daterestriction" name="daterestriction"
							class="form-control" placeholder="<fmt:message key="Date" />"
							value="${daterestriction}"></td>
					</tr>
				</tbody>
			</table>
		</form>



		<table class="table table-striped"
			style="width: 70%; text-align: left; margin-left: auto; margin-right: auto;">

			<thead>
				<tr>
					<td>ID</td>
					<td><fmt:message key="First-Name" /></td>
					<td><fmt:message key="Last-Name" /></td>
					<td><fmt:message key="Specialization" /></td>
					<td><fmt:message key="Rate" /></td>
					<td><fmt:message key="Specify-begin-Date" /></td>
					<td><fmt:message key="Specify-end-Date" /></td>
					<td></td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="developer" items="${developers}">
					<form action="addprojectdeveloper" method="get">
						<tr>
							<td>${developer.id}</td>
							<td>${developer.firstName}</td>
							<td>${developer.lastName}</td>
							<td>${developer.task.specialization}</td>
							<td>${developer.rate}</td>
							<td style="width: 25%;"><input title="begindate"
								name="startdate${developer.id}" class="form-control"
								placeholder="<fmt:message key="Begin-date" />"></td>
							<td style="width: 25%;"><input title="enddate"
								name="enddate${developer.id}" class="form-control"
								placeholder="<fmt:message key="End-date" />"></td>
							<td><input type="hidden" name="developerid"
								value="${developer.id}"></td>
							<td><input type="image" src="img/icons/plusicon.png"
								style="width: 40px; height: 40px;" alt="Submit Form" /></td>
						</tr>
					</form>
				</c:forEach>
			</tbody>

		</table>
	</div>
</body>
</html>