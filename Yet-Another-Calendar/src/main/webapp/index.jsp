<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Yet Another Calendar</title>
<link rel="stylesheet" media="screen" href="Resources/structure.css" />
<script type="text/JavaScript" src="Resources/analog_clock.js"></script>
<script type="text/JavaScript" src="Resources/SmallCalendar.js"></script>
</head>
<body>
	<div id="main">
		<div id="info">
			<ul id="cl_clock">
				<li id="cl_sec"></li>
				<li id="cl_hour"></li>
				<li id="cl_min"></li>
			</ul>
			<table id="smallcalendar">
				<tr>
					<td>Mo</td>
					<td>Di</td>
					<td>Mi</td>
					<td>Do</td>
					<td>Fr</td>
					<td>Sa</td>
					<td>So</td>
				</tr>
			</table>
		</div>
		<div id="title" style="margin-left: 0px;">
			<div id="titleimage"></div>
		</div>
		<form action="userservlet" method="post" id="loginyac">
			<div>
				<img id="yacimage" src="Resources/Images/yac.png" alt="Logo" />
			</div>
			<fieldset id="login">
				<legend>Login</legend>
					<%
						if (pageContext.getErrorData().getStatusCode() == 401){
							%>
							<div id="errorbox">
							<% 
							out.println("<div>Ungültige Email-Adresse oder ungültiges Passwort.</div>");
							%>
							</div>
							<%
						}
					%>
				<div>
					<label for="email" style="float: left;">Email: &nbsp;</label> <input
						id="email" name="email" type="text" size="25" maxlength="25" />
				</div>
				<div>
					<label for="password" style="float: left;">Passwort: &nbsp;</label>
					<input id="password" name="password" type="password" size="25"
						maxlength="25" />
				</div>
				<div>
					<input type="hidden" name="action" value="login" />
						<button type="submit">Login</button>
				</div>
				
				<a href="Register.html" id="registerlink">Noch nicht
					registriert?</a>
			</fieldset>
		</form>
		<div id="footer">
			Impressum: &nbsp; Michael M&uuml;ller &nbsp; Tel: 10932048091284
			&nbsp; Email: <a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a>
			&nbsp; Mehr: <a href="About.html">About</a>
		</div>
	</div>
</body>
</html>
