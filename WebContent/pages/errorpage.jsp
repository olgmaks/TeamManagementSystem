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
<title>Error page</title>
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
	<div style="margin-left: auto; margin-right: auto; width: 300px;">
		<h4 style="margin-left: auto; margin-right: auto;">
			<fmt:message key="Dear" />
			<c:choose>
				<c:when test="${user==null}">
						<fmt:message key="user" />,
				</c:when>
				<c:otherwise>${user.firstName} ${user.lastName},</c:otherwise>
			</c:choose>
			<fmt:message key="errormessage" />
		</h4>
		<h4 style="margin-left: auto; margin-right: auto;"></h4>
		<div style="margin-left: auto; margin-right: auto; width: 220px;">
			<a href="index"><img src="img/icons/homeforerror.png" /></a><br>
			<br>
		</div>
	</div>
</body>
</html>