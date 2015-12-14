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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">zs
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
		var zIndexModal = $(modal).css('z-index');
		$(datePicker).css('z-index', zIndexModal + 1);
	});
</script>

<title>Order details</title>
</head>
<body style="color: FFF8DC">

	<jsp:include page="logoutmodal.jsp"/>
	
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" style="margin-left: 14%;">
			<li><a href="orderdetails?orderid=${orderid}"><fmt:message
						key="Order" /></a></li>
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


	<a href="customer" style="margin-left: 90%"><img
		src="img/icons/projectmodificationdone.png"
		style="width: 50px; height: 50px;"></a>
	<br>



	<table class="table table-striped"
		style="font-size: 13px; width: 70%; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<td></td>
				<td><fmt:message key="Specialization" /></td>
				<td style="width: 15%;"><fmt:message key="Developers-quantity" /></td>
				<td><fmt:message key="Start" /></td>
				<td><fmt:message key="End" /></td>
				<td><fmt:message key="Uncovered-hours" /></td>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="taskload" items="${taskloads}">
				<tr>
					<td><img src="img/icons/spec/spec${taskload.task.id}.png"
						style="width: 50px; height: 50px;"></td>
					<%-- 					<td>${taskload.task.id}</td> --%>
					<td>${taskload.task.specialization}</td>
					<td>${taskload.developerQuantity}</td>
					<td>${taskload.startDate}</td>
					<td>${taskload.endDate}</td>
					<td>${taskload.loadHours}</td>
					<td><a href="removeordertask?taskloadid=${taskload.id}"><img
							src="img/icons/removeicon.png" style="width: 40px; height: 40px;"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="" data-toggle="modal" data-target="#myModal"><img
		src="img/icons/addtask.png"
		style="width: 60px; height: 60px; margin-left: 15%;"></a>







	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<form action="addordertask" method="get">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							<fmt:message key="New-task-for-current-order" />
						</h4>
					</div>
					<div class="modal-body"
						style="width: 80%; margin-right: auto; margin-left: auto">



						<table class="table table-striped"
							style="width: 100%; text-align: left; margin-left: auto; margin-right: auto;">
							<thead>
								<tr>
									<td><fmt:message key="Task" /></td>
									<td><fmt:message key="Specialization" /></td>
									<td></td>
									<td><fmt:message key="Developers-quantity" /></td>
									<td><fmt:message key="Start" /></td>
									<td><fmt:message key="End" /></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="task" items="${tasks}">
									<tr>
										<td style="width: 20%;""><img
											src="img/icons/spec/spec${task.id}.png"
											style="width: 40px; height: 40px;"></td>
										<td>${task.specialization}</td>
										<td><input name="taskid" type="hidden" value=${task.id } /></td>
										<td style="width: 25%;"><input name="developers"
											class="form-control"
											placeholder="<fmt:message key="Developers-quantity"/>"></td>
										<td style="width: 25%;"><input title="begindate"
											name="startdate" class="form-control"
											placeholder="<fmt:message key="Begin-date"/>"></td>
										<td style="width: 25%; z-index: 10000;"><input
											title="enddate" name="enddate" class="form-control"
											placeholder="<fmt:message key="End-date"/>"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>



					</div>
					<div class="modal-footer">
						<input type="image" src="img/icons/done.png"
							style="margin-left: 50%; margin-right: 50%; width: 50px; height: 50px;"
							alt="Submit Form" />
					</div>
				</div>
			</form>
		</div>
	</div>














</body>
</html>