<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="date" uri="http://tms.com/custom"%>
<c:set var="language" value="${language}" />
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>General Engineering Home</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/business-frontpage.css" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>

	<c:choose>
		<c:when test="${languageimageref==null}">
			<c:set var="languageimageref"
				value="img/icons/change-language-ua.png" />
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${indexhomeref==null}">
			<c:set var="indexhomeref" value="index" />
		</c:when>
	</c:choose>



	<c:choose>
		<c:when test="${user==null}">
			<c:set var="signinmessage">
				<fmt:message key="index.SignIn" />
			</c:set>
			<c:set var="loggingimage" value="img/icons/loginicon.png" />
		</c:when>
		<c:otherwise>
			<c:set var="signinmessage" value="${user.email}" />
			<c:set var="loggingimage" value="img/icons/loginlocked.png" />
		</c:otherwise>
	</c:choose>


	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="${indexhomeref}"><fmt:message
					key="index.Home" /> </a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1"
			style="margin-right: auto; float: right;">
			<ul class="nav navbar-nav"">
				<li><div style="margin-top: 10px;">
						<a href="#" data-toggle="modal" data-target="#myModal"><img
							style="width: 30px; height: 30px;" src="${loggingimage}"> <font
							color="white">${signinmessage}</font></a>
					</div></li>

			</ul>
			<ul class="nav navbar-nav" style="margin-left: 20px;">
				<li>
					<form action="changelanguage" method="post">
						<input type="image" src="${languageimageref}"
							style="width: 40px; height: 40px; margin-top: 5px;"
							alt="Submit Form" />

					</form>
				</li>
				<li
					style="color: white; margin-left: 20px; margin-top: 15px; width: 130px;"><date:currentDate /></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<!-- Image Background Page Header -->
	<!-- Note: The background image is set within the business-casual.css file. -->
	<header class="business-header">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="tagline">General Engineering</h1>
			</div>
		</div>
	</div>
	</header>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="margin-left: 60%;">
		<c:choose>
			<c:when test="${user==null}">
				<form action="login" method="post">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									<fmt:message key="index.SignIn" />
								</h4>
							</div>
							<div class="modal-body">
								<table
									style="width: 100%; text-align: left; margin-left: auto; margin-right: auto;">
									<tr>
										<td><input type="text" name="e-mail" class="form-control"
											placeholder="<fmt:message key="email"/>" " /></td>
									</tr>
									<tr>
										<td><input type="password" name="password"
											class="form-control"
											placeholder="<fmt:message key="password"/>" " /></td>
									</tr>
								</table>
								<a href="registratenewuser"><fmt:message
										key="index.registrate" /></a>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<fmt:message key="close" />
								</button>
								<input type="submit" value="<fmt:message key="index.SignIn"/>"
									class="btn btn-primary" />
							</div>
						</div>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<fmt:message key="hello" />
								, ${user.firstName} ${user.lastName} !
							</h4>
						</div>
						<div class="modal-body">
							<a href="logout" class="btn btn-warning"
								style="margin-left: 40%; margin-right: auto;"><fmt:message
									key="logout" /></a>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<fmt:message key="close" />
							</button>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>


	<!-- Page Content -->
	<div class="container">

		<hr>

		<div class="row">
			<div class="col-sm-8">
				<h2>
					<fmt:message key="index.What-We-Do" />
				</h2>
				<p>
					<fmt:message key="index.What-We-Do-Description" />
				</p>
				<p>
					<a class="btn btn-default btn-lg" href="registratenewuser"> <fmt:message
							key="Registrate-now" /> &raquo;
					</a>
				</p>
			</div>
			<div class="col-sm-4">
				<h2>
					<fmt:message key="index.Contact-Us" />
				</h2>
				<address>
					<strong>Start Bootstrap</strong> <br>3481 Melrose Place <br>Beverly
					Hills, CA 90210 <br>
				</address>
				<address>
					<abbr title="Phone">P:</abbr>(123) 456-7890 <br> <abbr
						title="Email">E:</abbr> <a href="mailto:#">name@example.com</a>
				</address>
			</div>
		</div>
		<!-- /.row -->

		<hr>

		<div class="row">
			<div class="col-sm-4">
				<img class="img-circle img-responsive img-center" src="img/game.jpg"
					alt="">
				<h2>
					<fmt:message key="index.Structural-&-Architectural" />
				</h2>
				<p>
					<fmt:message key="index.Structural-&-Architectural-Description" />
				</p>
			</div>
			<div class="col-sm-4">
				<img class="img-circle img-responsive img-center"
					src="img/circus.jpg" alt="">
				<h2>
					<fmt:message key="index.Mechanical-&-Process" />
				</h2>
				<p>
					<fmt:message key="index.Mechanical-&-Process-Description" />
				</p>
			</div>
			<div class="col-sm-4">
				<img class="img-circle img-responsive img-center" src="img/cake.jpg"
					alt="">
				<h2>
					<fmt:message key="index.Engineering-networks" />
				</h2>
				<p>
					<fmt:message key="index.Engineering-networks-Description" />
				</p>
			</div>
		</div>
		<!-- /.row -->

		<hr>

		<!-- Footer -->
		<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>Copyright &copy; General engineering 2015</p>
			</div>
		</div>
		<!-- /.row --> </footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
