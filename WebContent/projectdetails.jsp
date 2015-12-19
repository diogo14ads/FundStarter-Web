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
	<div id="title"></div>
		<!-- Header -->
		<nav class="navbar navbar-inverse col-md-12">
		<div class="col-md-8">
			<ul class="nav navbar-nav">
				<li class="active"><a class="navbar-brand" href="main.action">FundStarter</a>
				</li>
			</ul>
		</div>
		<div class="col-md-2">
			<ul class="nav navbar-nav">
				<li><a href="myAccount.action">My Account</a></li>
			</ul>
		</div>
		</nav>

		<div>
			<h1>
				<s:property value="project.projectName" />
			</h1>
			<br>
			<h4>
				<s:property value="project.projectDescription" />
			</h4>
			<h5>
				Fund raiser will end in
				<s:property value="project.dateEnd" />
			</h5>
			<br>
			<div class="progress">

				<s:property value="project.moneyRaised" />
				$ /
				<s:property value="project.objective" />
				$
				<div class="progress-bar" role="progressbar" aria-valuenow="60"
					aria-valuemin="0" aria-valuemax="100"
					style="width: <s:property value="project.percentageComplete"/>%; ">
				</div>
			</div>

		</div>

		<hr>
		<h3>Available Rewards</h3>
		<s:iterator value="activeRewards">
			<div class="thumbnail">
				<div class="caption">
					<s:property value="description" />
					(
					<s:property value="value" />
					$)

					<!-- <a href="<s:url action="pledge"/>?rewardId=${rewardId}" class="">Pledge</a> -->

				</div>
			</div>

		</s:iterator>

		<hr>
		<h3>Extra Reward Levels</h3>
		<s:iterator value="extraLevels">
			<div class="thumbnail">
				<div class="caption">
					<h4>
						<s:property value="key.objective" />
					</h4>
					<s:iterator value="value">
						<s:property value="description" />
					(
					<s:property value="value" />
					$) <br>
					</s:iterator>
				</div>
			</div>
		</s:iterator>
	</div>
</body>
</html>
