<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Project</title>
</head>
<body>
	<s:form action="createProject" method="post">
		<s:text name="Name:" />
		<s:textfield name="newProject.projectName"/> <br>
		<s:text name="Description:" />
		<s:textfield name="newProject.projectDescription"/> <br>
		<s:text name="End Date (dd/mm/yyyy hh:mm):" />
		<s:textfield name="newProject.dateEnd"/> <br>
		<s:text name="Money Goal:" />
		<s:textfield name="newProject.objective"/> <br>
		<s:submit />
	</s:form>
</body>
</html>