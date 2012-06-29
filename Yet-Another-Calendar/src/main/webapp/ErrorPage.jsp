<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<title>Yet Another Calendar</title>
		<link rel="stylesheet" media="screen" href="Resources/structure.css">
		<script type="text/JavaScript" src="Resources/analog_clock.js"></script> 
	</head>
	<body>
		<div id="main">
			<div id="info"><canvas width="150" height="150" id="analog_clock"></canvas></div>
			<div id="title" style="margin-left: 0px;">Yet Another Calendar</div>
			<div id="messagecontent"><div id="messagewindow">
			
			
			Statuscode: <%= pageContext.getErrorData().getStatusCode() %><br/>
			Request-URI: <%= pageContext.getErrorData().getRequestURI() %><br/>
			Exception: <%= exception %> <br/>
			<a href="index.jsp">Startseite</a>
			</div></div>
			<div id="footer">Impressum: &nbsp; Michael M&uuml;ller &nbsp; Tel: 10932048091284 &nbsp; Email: <a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a> &nbsp; Mehr: <a href="about.html">About</a></div>
		</div>
	</body>
</html>