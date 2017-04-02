<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Society</title>
</head>
<body>
	<nav>
	<div class="nav-wrapper blue darken-1">
		<a href="dashboard" class="brand-logo" style="margin-left: 30px;">{Dashboard}</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="dashboard">Events</a></li>
			<li><a href="society">Societies</a></li>
			<li><a href="forum">Forum</a></li>
			<li><a href="logout">Logout</a></li>
		</ul>
	</div>
	</nav>

	<div id="main">
		<div id="ques" class="z-depth-1">
			<div id="ques" class="z-depth-1">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>Title</b> : ${name} <br />
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>DEPARTMENT</b> :
					<br>
					 ${dept}
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>DETAILS</b> :
					<br>
					 ${det}
				</div>
			</div>
		</div>
	</div>

	<footer class="page-footer blue darken-1" style="padding-top: 0px;">
	<div class="footer-copyright">
		<div class="container">
			� 2017 Copyright <a target="_blank"
				class="grey-text text-lighten-4 right" href="http://gdgjss.in">GDG
				JSS Noida </a>
		</div>
	</div>
	</footer>
</body>
<style>
#ques {
	background: rgba(255, 255, 255, .3);
	padding: 10px;
	border-radius: 3px;
	width: 820px;
	margin: 30px auto;
}
</style>
</html>