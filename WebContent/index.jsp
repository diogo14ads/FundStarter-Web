<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="widt = device-width, initial-scale=1">
	<title>FundStarter</title>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/style.css">
</head>
<body>
	<div class="container">
		<!-- Header -->
		<div class="row">
			<nav class="navbar navbar-inverse col-md-12">
				<div class="col-md-10">
					<ul class="nav navbar-nav">
						<li class="active">
							<a class="navbar-brand" href="#">FundStarter</a>
						</li>
					</ul>
				</div>
				<div class="col-md-1">
					<ul class="nav navbar-nav">
						<li>
							<a href="#">Login</a>
						</li>
					</ul>
				</div>
				<div class="col-md-1">
					<ul class="nav navbar-nav">
						<li>
							<a href="#">Register</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>
		<!-- Current Project List -->
		<div class="row">
			<h3>Current Projects</h3>
			<div>
				<s:iterator value="current">
					<s:property value="columns.get(1)" />
					<s:property value="columns.get(2)" /><br>
				</s:iterator>
			</div>
			
		</div>
		<!-- Past Project List -->
		<div class="row">
			<h3>Past Projects</h3>
			<div>
				<div>
				<s:iterator value="past">
						<s:property value="columns" /><br>
				</s:iterator>
			</div>
			</div>
		</div>
	</div>
</body>
</html>
