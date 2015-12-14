<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="customer">Customer</a></li>
		<li><a href="createneworder">New Order</a></li>
		<li class="pull-right"><a href="logout">Logout</a></li>
	</ul>
	<h2>New Order Page</h2>
	<br>
	<form action="createneworder" method="post">
		<table
			style="width: 100%; text-align: left; margin-left: auto; margin-right: auto;"
			border="0" cellpadding="2" cellspacing="2">
			<tbody>
				<tr>
					<td><input name="ordername" class="form-control"
						placeholder="Order name"><br> <input
						name="orderdescription" class="form-control"
						placeholder="Order description"></td>
					<td style="width: 30%;"></td>
					<td>Select tasks and specify hours</td>
				</tr>
			</tbody>
		</table>
		<br> <br>
		<table class="table table-striped"
			style="width: 70%; text-align: left; margin-left: auto; margin-right: auto;">
			<thead>
				<tr>
					<td>Task</td>
					<td>Specialization</td>
					<td>Developer quantity</td>
					<td>Begin Date</td>
					<td>End Date</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="task" items="${tasks}">
					<tr>
						<td style="width: 20%;""><img
							src="img/icons/spec/spec${task.task.id}.png"
							style="width: 40px; height: 40px;"></td>
						<td>${task.specialization}</td>
						<td style="width: 25%;"><input name="${task.id}developers"
							class="form-control" placeholder="Developers"></td>
						<td style="width: 25%;"><input title="begindate"
							name="${task.id}startdate" class="form-control"
							placeholder="Begin date"></td>
						<td style="width: 25%;"><input title="enddate"
							name="${task.id}enddate" class="form-control"
							placeholder="End Date"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<table style="width: 70%; margin-left: auto; margin-right: auto;">
			<tbody>
				<tr>
					<td style="width: 100%;"><input name="additionalinfo"
						class="form-control" placeholder="Set additional information"></td>
					<td><input type="submit"
						value="Submit order and send on server" class="btn btn-success" />

					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>