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
					<li class="active"><a class="navbar-brand" href="#">FundStarter</a>
					</li>
				</ul>
			</div>
			<div class="col-md-2">
				<ul class="nav navbar-nav">
					<li><a href="myAccount.action">My Account</a></li>
				</ul>
			</div>
			<div class="col-md-2">
				<ul class="nav navbar-nav">
					<li><a href="#">Messages</a></li>
				</ul>
			</div>
			</nav>
		</div>
		<!-- Current Project List -->

		<!--  
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="..." alt="...">
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>...</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Button</a> <a
								href="#" class="btn btn-default" role="button">Button</a>
						</p>
					</div>
				</div>
			</div>
		</div>
-->

		<div class="row">
			<h3 class="page-header">Current Projects</h3>
			<c:forEach items="${rmiBean.currentProjects}" var="current">

				<div class="col-md-4">
					<div class="thumbnail">
						<a href="#" class=""> <img src="http://placecage.com/300/300"
							alt="Imagem">
							<div class="caption">
								<h3>
									<c:out value="${current.projectName}" />
								</h3>
								<p>
									Objective:
									<c:out value="${current.objective}" />
								</p>
								<p>
									Raised
									<c:out value="${current.moneyRaised}" />
									out of
									<c:out value="${current.objective}" />
									.
								</p>
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="60"
										aria-valuemin="0" aria-valuemax="100"
										style="width: <c:out value="${current.percentageComplete}" />%; "></div>
								</div>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- Past Project List -->
		<div class="row">
			<h3 class="page-header">Past Projects</h3>
			<c:forEach items="${rmiBean.pastProjects}" var="current">

				<div class="col-md-4">
					<div class="thumbnail">
						<a href="#" class=""> <img src="http://placecage.com/300/300"
							alt="Imagem">
							<div class="caption">
								<h3>
									<c:out value="${current.projectName}" />
								</h3>
								<p>
									Objective:
									<c:out value="${current.objective}" />
								</p>
								<p>
									Raised
									<c:out value="${current.moneyRaised}" />
									out of
									<c:out value="${current.objective}" />
									.
								</p>
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="60"
										aria-valuemin="0" aria-valuemax="100"
										style="width: <c:out value="${current.percentageComplete}" />%; "></div>
								</div>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
