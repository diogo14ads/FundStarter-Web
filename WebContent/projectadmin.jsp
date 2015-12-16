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
					<li class="active"><a class="navbar-brand" href="main.action">FundStarter</a>
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


		<c:forEach items="${rmiBean.getProjectLevels(projectId)}" var="level">
			<div class="thumbnail">
				<div class="caption">
					<h3>
						<c:out value="${level.objective}"></c:out>
					</h3>
					<br>
					<c:forEach
						items="${rmiBean.getLevelRewards(projectId, level.levelId)}"
						var="reward">
						<div class="thumbnail">
							<div class="caption">
								<a><c:out value="${reward.description}"></c:out><br></a>
								<s:form action="removeReward" method="post">
									<input type="hidden" name="rewardId" value="${reward.rewardId}" />
									<s:submit key="Remove" />
								</s:form>
							</div>

						</div>

					</c:forEach>

					<c:if test="${level.levelId != 0}">
						<s:form action="removeLevel" method="post">
							<input type="hidden" name="levelId" value="${level.levelId}" />
							<s:submit key="Remove" />
						</s:form>
					</c:if>
				</div>
			</div>
		</c:forEach>
		<br>
		<hr>
		
		<s:form action="addLevel" method="post">
			<s:text name="Amount needed to reach level:" />
			<s:textfield name="objective"/>
			<s:hidden name="projectId" />
			<s:submit key="Add Level" />
		</s:form>
		
		<hr>
		
		<s:form action="addAdministrator" method="post">
			<s:text name="New administrator email:" />
			<s:textfield name="newAdmin" />
			<s:hidden name="projectId" />
			<s:submit key="Add Admin" />
		</s:form>
		
		<hr>
		<a href="cancelProject.action?projectId=<s:property value="projectId"/>" > Cancel Project</a>
		
		<hr>
		
		<h3>New Reward</h3>
		<s:form action="addReward" method="post">
			<s:text name="Description:" />
			<s:textfield name="description" />
			<s:text name="value:" />
			<s:textfield name="value" />
			
			
			<select name="levelId">
				<c:forEach items="${rmiBean.getProjectLevels(projectId)}" var="level">
						
						<option value="${level.levelId}" >${level.objective}</option>					
				</c:forEach>
			</select>
			
			<s:hidden name="projectId" />
			<s:submit key="Add Reward" />
		</s:form>
		
		<hr>
		
		<h3>Your Messages</h3>
		<select name="levelId">
			<c:forEach items="${rmiBean.getProjectLevels(projectId)}" var="level">

				<option value="${level.levelId}">${level.objective}</option>
			</c:forEach>
		</select>

	</div>
</body>
</html>
