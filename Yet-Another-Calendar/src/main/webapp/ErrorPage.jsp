<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Yet Another Calendar</title>
<link rel="stylesheet" media="screen" href="Resources/structure.css"/>
<script type="text/JavaScript" src="Resources/analog_clock.js"></script>
</head>
<body>
	<div id="main">
		<div id="info">
			<ul id="cl_clock">
				<li id="cl_sec"></li>
				<li id="cl_hour"></li>
				<li id="cl_min"></li>
			</ul>
		</div>
		<div id="title" style="margin-left: 0px;">
			<div id="titleimage"></div>
		</div>
		<div id="messagecontent">
			<div id="messagewindow">


				Statuscode:
				<%=pageContext.getErrorData().getStatusCode()%><br /> Request-URI:
				<%=pageContext.getErrorData().getRequestURI()%><br /> Exception:
				<%=exception%>
				<br /> <a href="index.jsp">Startseite</a>
			</div>
		</div>
		<div id="footer">
			Impressum: &nbsp; Michael M&uuml;ller &nbsp; Tel: 10932048091284
			&nbsp; Email: <a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a>
			&nbsp; Mehr: <a href="About.html">About</a>
		</div>
	</div>
</body>
</html>