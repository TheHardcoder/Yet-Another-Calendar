<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
       "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Yet Another Calendar</title>
		<link rel="stylesheet" media="screen" href="Resources/structure.css"></link>
		<script type="text/JavaScript" src="Resources/analog_clock.js"></script> 
	</head>
	<body>
		<div id="main">
			<div id="info"><canvas width="150" height="150" id="analog_clock"></canvas></div>
			<div id="title" style="margin-left: 0px;">Yet Another Calendar</div>
			<form action="calendar_monthview.xml" method="post"  id="loginyac">
			<img id="yacimage" src="Resources/BabyGnu.png"/>
				<fieldset id="login">
					<legend>Login</legend>
					<label for="email" style="float:left;">Email: &nbsp;</label> <input id="email" name="email" type="email" size="25" maxlength="25"/>
					<br clear="all"/>
					<label for="password" style="float:left;">Passwort: &nbsp;</label> <input id="password" name="password" type="password" size="25" maxlength="25"/>
					<br clear ="all"/>
					<button>Login</button>
					<br/>
					<a href="register.html">Noch nicht registriert?</a>
				</fieldset>
			</form>
			<div id="footer">Impressum: &nbsp; Michael Müller &nbsp; Tel: 10932048091284 &nbsp; Email: <a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a> &nbsp; Mehr: <a href="about.html">About</a></div>
		</div>
	</body>
</html>