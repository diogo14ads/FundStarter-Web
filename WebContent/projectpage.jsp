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
		writeToPage('Connected to ' + window.location.host + '.');
		document.getElementById('makePledge').onsubmit = function() {
				//doSend(); // call doSend() on enter key
				websocket.send("u "+document.getElementById('makePledge').children[0].value);
		};
	}

	function onClose(event) {
		writeToHistory('WebSocket closed.');
		document.getElementById('title').onkeydown = null;
	}

	function onMessage(message) { // print the received message
		msg = message.data;
		if (msg.split(" ").length == 3)
			writeToHistory(message.data);
		else
			updatePercentage(message.data)
	}

	function onError(event) {
		writeToHistory('WebSocket error (' + event.data + ').');
		document.getElementById('title').onkeydown = null;
	}

	function doSend() {
		var message = document.getElementById('makePledge').children[0].name;
		if (message != '')
			websocket.send(message); // send the message
		document.getElementById('makePledge').children[0].name = '';
	}
	
	 function writeToPage(text) {
         var history = document.getElementById('title');
         var line = document.createElement('p');
         line.style.wordWrap = 'break-word';
         line.innerHTML = text;
         history.appendChild(line);
         history.scrollTop = history.scrollHeight;
     }

	function writeToHistory(text) {
		var list = text.split(" ");
		var title = document.getElementById('moneyRaised' + list[0]);
		var line = document.createElement('p');
		line.style.wordWrap = 'break-word';
		line.innerHTML = "Raised " + list[1] + " out of " + list[2] + ".";
		title.appendChild(line);
		title.scrollTop = title.scrollHeight;
	}

	function updatePercentage(text) {
		var list = text.split(" ");
		var title = document.getElementById('progressBar' + list[0]);
		var line = document.createElement('c:set');
		line.setAttribute("var", "percent")
		line.setAttribute("value", "75");
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
		document.getElementById('content').removeChild(input.parentNode);
	}
</script>
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
		<div class="col-md-2">
			<ul class="nav navbar-nav">
				<li><a href="#">Messages</a></li>
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

					<s:form action="makePledge" id="makePledge" method="post">
						<s:hidden name="rewardId" />
						<s:submit value="Pledge" />
					</s:form>

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
		<hr>
		<h3>Chat</h3>
		<div class="thumbnail">
			<div class="caption">
				<s:iterator value="messages">
					<div class="thumbnail">
						<div class="caption">
							<s:property value="messageText" />
						</div>
					</div>
				</s:iterator>
				<hr>
				<s:form action="sendMessage" method="post">
					<s:textfield name="sendMessage"> Write Message:</s:textfield>
					<s:hidden name="projectId" />
					<s:submit key="Send Message" />
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>
