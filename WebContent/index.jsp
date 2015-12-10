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
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/style.css">
</head>
<body>
	<div class="container">
		<!-- Header -->
		<div class="row">
			<nav class="navbar navbar-inverse col-md-12">
			<div class="col-md-10">
				<ul class="nav navbar-nav">
					<li class="active"><a class="navbar-brand" href="#">FundStarter</a>
					</li>
				</ul>
			</div>
			<div class="col-md-1">
				<ul class="nav navbar-nav">
					<li><a href="#">Login</a></li>
				</ul>
			</div>
			<div class="col-md-1">
				<ul class="nav navbar-nav">
					<li><a href="#">Register</a></li>
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
			<h3>Current Projects</h3>
			<div>
				<s:iterator value="current">

					<div class="row">
						<div class="col-sm-6 col-md-4">
							<div class="thumbnail">
								<img src="http://fillmurray.com/300/300" alt="Imagem">
								<div class="caption">
									<p>
										<h3>
											<a href="#" role="button"><s:property value="columns.get(2)" /></a>
										</h3>
									<p>
										Limit: <s:property value="columns.get(1)" />
									</p>
									<p>
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
												style="width: 20%;" />
										</div>
									</p>
									
								</div>
							</div>
						</div>
					</div>
				</s:iterator>
			</div>

		</div>
		<!-- Past Project List -->
		<div class="row">
			<h3>Past Projects</h3>
			<div>
				<s:iterator value="past">

					<div class="row">
						<div class="col-sm-6 col-md-4">
							<div class="thumbnail">
								<img src="http://fillmurray.com/300/300" alt="Imagem">
								<div class="caption">
									<p>
										<h3>
											<a href="#" role="button"><s:property value="columns.get(2)" /></a>
										</h3>
									<p>
										Limit: <s:property value="columns.get(1)" />
									</p>
									<p>
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
												style="width: 20%;" />
										</div>
									</p>
									
								</div>
							</div>
						</div>
					</div>
				</s:iterator>
			</div>
		</div>
	</div>
</body>
</html>
