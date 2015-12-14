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
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
	function deleteOrderSetter(id){
		var element = document.getElementById("orderidtobedeleted");
		element.value = id;
	};
</script>

<title>Customer</title>
</head>

<body>

	<jsp:include page="logoutmodal.jsp" />

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" style="margin-left: 14%;">
			<li><a href="customer"><fmt:message key="customer" /></a></li>
			<li><a href="" data-toggle="modal" data-target="#myModal"><fmt:message
						key="new-order" /></a></li>
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
			<fmt:message key="customer-page" />
		</h2>
		<br>
	</div>
	<button type="button" style="margin-right: auto%; margin-left: 15%;"
		class="btn btn-primary" data-toggle="modal" data-target="#myModal">
		<fmt:message key="create-new-order" />
	</button>
	<br>
	<br>

	<br>
	<h4 style="margin-left: 15%; color: green;">
		<fmt:message key="Orders-in-progress" />
	</h4>
	<table class="table table-striped"
		style="width: 70%; height: 100%; text-align: left; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<td><fmt:message key="Project-Number" /></td>
				<td><fmt:message key="Order" /></td>
				<td><fmt:message key="Order-Description" /></td>
				<td><fmt:message key="Additional-Information" /></td>
				<td><fmt:message key="Status" /></td>
				<td><fmt:message key="Price" /></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="order" items="${orders}">
					<c:forEach var="project" items="${projects}">
						<c:choose>
							<c:when test="${order.id==project.orderId}">
								<tr>
									<td>${project.id}</td>
									<td>${order.name}</td>
									<td>${order.description}</td>
									<td>${order.additionalInformation}</td>
									<td>${project.status}</td>
									<td>${project.price}</td>
									<td><a href="orderdetails?orderid=${order.id}"><img
											src="img/icons/editicon.png"
											style="width: 50px; height: 50px;"></a></td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:forEach>
			</tr>

		</tbody>
	</table>
	<br>



	<h4 style="margin-left: 15%; color: red;">
		<fmt:message key="Unconsidered-orders" />
	</h4>
	<table class="table table-striped"
		style="width: 70%; height: 100%; text-align: left; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<td><fmt:message key="Project-Number" /></td>
				<td><fmt:message key="Order" /></td>
				<td><fmt:message key="Order-Description" /></td>
				<td><fmt:message key="Additional-Information" /></td>
				<td><fmt:message key="Status" /></td>
				<td><fmt:message key="Price" /></td>
			</tr>
		</thead>

		<tr>
			<c:forEach var="order" items="${unconsideredorders}">
				<tr>
					<td><fmt:message key="no-number" /></td>
					<td>${order.name}</td>
					<td>${order.description}</td>
					<td>${order.additionalInformation}</td>
					<td><fmt:message key="not-yet-considered" /></td>
					<td><fmt:message key="doesn't-have-price" /></td>
					<td><a href="orderdetails?orderid=${order.id}"><img
							src="img/icons/editicon.png" style="width: 50px; height: 50px;"></a></td>
					<td><a id="deleteorder" href="#" data-toggle="modal"
						data-target="#confirmDeleteOrder"
						onclick="deleteOrderSetter(${order.id});"><img
							src="img/icons/removeicon.png" style="width: 50px; height: 50px;">
							<%-- 							<c:set var="orderidtobedeleted" values="${order.id}"/> --%>
					</a></td>
				</tr>
			</c:forEach>
		</tr>
		</tbody>
	</table>


	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<form action="createneworder" method="get">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							<fmt:message key="create-new-order" />
						</h4>
					</div>
					<div class="modal-body"
						style="width: 80%; margin-right: auto; margin-left: auto">
						<input type="text" name="ordername" class="form-control"
							placeholder="<fmt:message key="Order-name"/>" /><br> <input
							type="text" name="orderdescription" class="form-control"
							placeholder="<fmt:message key="Order-Description"/>" /><br>
						<input type="text" name="orderadditionalinfo" class="form-control"
							placeholder="<fmt:message key="Additional-Information"/>" />
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary"
							value="<fmt:message key="Submit-creating"/>" />
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<fmt:message key="close" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<div class="modal fade" id="confirmDeleteOrder" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="margin-left: auto; margin-left: auto; margin-top: auto; margin-bottom: auto;">
		<div class="modal-dialog modal-sm">
			<form action="deleteorder" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<fmt:message key="order-delete-confirm" /> <input type="hidden"
							id="orderidtobedeleted" name="orderidtobedeleted">
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

