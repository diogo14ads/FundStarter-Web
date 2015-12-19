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

<!-- TODO arranjar isto, estou a importar destes endereções em vez dos ficheiros locais -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
</head>
<body>
	<div class="container">
		<!-- Header -->
		<div class="row">
			<nav class="navbar navbar-inverse col-md-12">
			<div class="col-md-10">
				<ul class="nav navbar-nav">
					<li class="active"><a class="navbar-brand" href="main.action">FundStarter</a>
					</li>
				</ul>
			</div>
			<div class="col-md-1">
				<ul class="nav navbar-nav">
					<!--  <li><a href="#">Login</a></li>-->
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary btn-lg"
						data-toggle="modal" data-target="#myModal">Login</button>

					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Login</h4>
								</div>
								<s:form action="login" method="post">
									<div class="modal-body">
										<s:text name="Email" />
										<br>
										<s:textfield name="user.email" />
										<br>
										<s:text name="Password" />
										<br>
										<s:password name="user.password" />
										<br>
									</div>
									<div class="modal-footer">
										<s:submit cssClass="btn btn-primary" value="Login"/>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</s:form>
							</div>
						</div>
					</div>
				</ul>
			</div>
			<div class="col-md-1">
				<ul class="nav navbar-nav">
					<li><a href="register.jsp">Register</a></li>
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
						<a href="<s:url action="projectDetails"/>?projectId=${current.projectId}" class=""> <img src="http://placecage.com/300/300"
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
