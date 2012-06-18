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
			<form action="userservlet" method="post"  id="loginyac">
			<img id="yacimage" src="Resources/BabyGnu.png" alt="Logo" />
				<fieldset id="login">
					<legend>Login</legend>
					<div>
					<label for="email" style="float:left;">Email: &nbsp;</label> <input id="email" name="email" type="email" size="25" maxlength="25"/>
					</div>
					<div>
					<label for="password" style="float:left;">Passwort: &nbsp;</label> <input id="password" name="password" type="password" size="25" maxlength="25"/>
					</div>
					<div>
					<input type="hidden" name="action" value="login">
					<button type="submit">Login</button>
					</div>
					<a href="register.html">Noch nicht registriert?</a>
				</fieldset>
			</form>
			<div id="footer">Impressum: &nbsp; Michael M&uuml;ller &nbsp; Tel: 10932048091284 &nbsp; Email: <a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a> &nbsp; Mehr: <a href="about.html">About</a></div>
		</div>
	</body>
</html>
