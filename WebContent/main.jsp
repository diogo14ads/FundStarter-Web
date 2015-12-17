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

<script type="text/javascript">
	var websocket = null;

	window.onload = function() { // URI = ws://10.16.0.165:8080/WebSocket/ws
		connect('ws://' + window.location.host + '/FundStarter-Web/websockets');
        document.getElementById("title").focus();
	}

	function connect(host) { // connect to the host websocket
		if ('WebSocket' in window)
			websocket = new WebSocket(host);
		else if ('MozWebSocket' in window)
			websocket = new MozWebSocket(host);
		else {
			writeToHistory('Get a real browser which supports WebSocket.');
			return;
		}

		websocket.onopen = onOpen; // set the event listeners below
		websocket.onclose = onClose;
		websocket.onmessage = onMessage;
		websocket.onerror = onError;
	}

	function onOpen(event) {
		writeToHistory('Connected to ' + window.location.host + '.');
		document.getElementById('title').onkeydown = function(key) {
			if (key.keyCode == 13)
				doSend(); // call doSend() on enter key
		};
	}

	function onClose(event) {
		writeToHistory('WebSocket closed.');
		document.getElementById('title').onkeydown = null;
	}

	function onMessage(message) { // print the received message
		msg = message.data;
		if(msg.split(" ").length==3)
			writeToHistory(message.data);
		else
			updatePercentage(message.data)
	}

	function onError(event) {
		writeToHistory('WebSocket error (' + event.data + ').');
		document.getElementById('title').onkeydown = null;
	}

	function doSend() {
		var message = document.getElementById('chat').value;
		if (message != '')
			websocket.send(message); // send the message
		document.getElementById('title').value = '';
	}

	function writeToHistory(text) {
		var list = text.split(" ");
		var title = document.getElementById('moneyRaised'+list[0]);
		title.innerHTML = "";
		var line = document.createElement('p');
		line.style.wordWrap = 'break-word';
		line.innerHTML = "Raised "+list[1]+" out of "+list[2]+".";
		title.appendChild(line);
		title.scrollTop = title.scrollHeight;
	}
	
	function updatePercentage(text){
		var list = text.split(" ");
		var title = document.getElementById('progressBar'+list[0]);
		var line = document.createElement('c:set');
		line.setAttribute("var","percent")
		line.setAttribute("value","75");
		title.insertBefore(line, title.children[0]);
		title.scrollTop = title.scrollHeight;
		
		
	}
	
	//NEW FROM WEB
	function addRow() {
	    var div = document.createElement('div');

	    div.className = 'row';

	    div.innerHTML = '<input type="text" name="name" value="" />\
	        <input type="text" name="value" value="" />\
	        <label> <input type="checkbox" name="check" value="1" /> Checked? </label>\
	        <input type="button" value="-" onclick="removeRow(this)">';

	     document.getElementById('content').appendChild(div);
	}

	function removeRow(input) {
	    document.getElementById('content').removeChild( input.parentNode );
	}
</script>

</head>
<body>
	<noscript>JavaScript must be enabled for WebSockets to work.</noscript>
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
			<h3 class="page-header" id="title">Project WebSockets</h3>
			<c:set var="currentProjects" value="${rmiBean.currentProjects}" />
			<c:forEach items="${currentProjects}" var="current">

				<div class="col-md-4">
					<div class="thumbnail">
						<a
							href="<s:url action="projectPage"/>?projectId=${current.projectId}"
							class=""> <img src="http://unsplash.it/300/300?random"
							alt="Imagem">
							<div class="caption">
								<h3>
									<c:out value="${current.projectName}" />
								</h3>
								<p>
									Objective:
									<c:out value="${current.objective}" />
								</p>
								<p id="moneyRaised${current.projectId}"> </p> <!-- Valor adicionado pelo websocket -->
								<div class="progress" id="progressBar${current.projectId}">
									<c:set var="percent" value="${current.percentageComplete}" />
									
									<div class="progress-bar" role="progressbar" aria-valuenow="60"
										aria-valuemin="0" aria-valuemax="100"
										style="width: ${percent}" />%; " ></div>
								</div>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>

		<div class="row">
			<h3 class="page-header">Current Projects</h3>
			<c:forEach items="${rmiBean.currentProjects}" var="current">

				<div class="col-md-4">
					<div class="thumbnail">
						<a
							href="<s:url action="projectPage"/>?projectId=${current.projectId}"
							class=""> <img src="http://unsplash.it/300/300?random"
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
						<a href="projectPage.action?project=${current}" class=""> <img
							src="http://lorempixel.com/300/300/technics" alt="Imagem">
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
