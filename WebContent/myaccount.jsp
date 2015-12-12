<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="widt = device-width, initial-scale=1">
<title>FundStarter</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/style.css">
</head>
<body>
	<div class="container">
		<!-- Header -->
		<div class="row">
			<nav class="navbar navbar-inverse col-md-12">
			<div class="col-md-8">
				<ul class="nav navbar-nav">
					<li class="active"><a class="navbar-brand mark" href="#">FundStarter</a>
					</li>
				</ul>
			</div>
			<div class="col-md-2">
				<ul class="nav navbar-nav">
					<li class="active"><a class="navbar-brand mark"
						href="myAccount.action">My Account</a></li>
					<!-- <li><a href="#">My Account</a></li> -->
				</ul>
			</div>
			<div class="col-md-2">
				<ul class="nav navbar-nav">
					<li><a href="#">Messages</a></li>
				</ul>
			</div>
			</nav>
		</div>

		<div class="row">
			<a href="createproject.jsp">New Project</a> <br>
			<a href="checkBalance.action">Check Balance</a> <br> 
			Balance:
			<h2>
				<s:property value="balance" /> $
			</h2>
			<hr>
			<a href="myRewards.action">Check Rewards</a> <br>
			<s:iterator value="myRewards">

				<div class="col-md-4">
					<div class="thumbnail">
						<div class="caption">
							<h3>
								<s:property value="name" />
							</h3>
						</div>
					</div>
				</div>
			</s:iterator>



		</div>
	</div>
</body>
</html>
