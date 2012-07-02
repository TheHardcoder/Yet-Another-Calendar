<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
	<xsl:output method="xml" encoding="utf-8" indent="yes"
		doctype-public="-//W3C//DTD XHTML 1.1//EN" doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" />


	<xsl:variable name="cur">
	</xsl:variable>
	<xsl:variable name="no" select='count(//appointment)' />

	<xsl:template match="/calendar">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<title>Yet Another Calendar</title>
				<link type="text/css" rel="stylesheet" media="screen"
					href="Resources/structure.css"></link>
					<link type="text/css" rel="stylesheet" media="screen"
					href="Resources/monthview.css"></link>
				<script type="text/JavaScript" src="Resources/analog_clock.js"></script>
				<script type="text/JavaScript" src="Resources/DateChooser.js"></script>
				<script type="text/JavaScript" src="Resources/SmallCalendar.js"></script>
			</head>
			<body>
				<div id="main">
					<div id="logo">
						<img src="Resources/Images/yac_logo.png" width="100px" height="100px"
							alt="logo" />
					</div>
					<div id="info">
						<form action="userservlet?action=logout" method="post">
							<div>
								<input type="submit" id="logout" value="Logout"></input>
							</div>
						</form>
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
					<div id="title">Yet Another Calendar</div>
					<div id="menubar">
						<div class="button">&lt;&lt;</div>
						<div class="button">Neu</div>
						<div class="button">Heute</div>
						<div class="menuitem">
							<form action="calendarservlet" method="get">
							<input type="hidden" name="view" value="yearview"></input>
							<select id="day" name="selectedday" size="1">
							</select>
							<select id="month" name="selectedmonth" size="1" onchange="update();">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
								<option>6</option>
								<option>7</option>
								<option>8</option>
								<option>9</option>
								<option>10</option>
								<option>11</option>
								<option>12</option>
							</select>
							<select id="year" name="selectedyear" size="1" onchange="update();">
								<option>1990</option>
								<option>1991</option>
								<option>1992</option>
								<option>1993</option>
								<option>1994</option>
								<option>1995</option>
								<option>1996</option>
								<option>1997</option>
								<option>1998</option>
								<option>1999</option>
								<option>2000</option>
								<option>2001</option>
								<option>2002</option>
								<option>2003</option>
								<option>2004</option>
								<option>2005</option>
								<option>2006</option>
								<option>2007</option>
								<option>2008</option>
								<option>2009</option>
								<option>2010</option>
								<option>2011</option>
								<option>2012</option>
								<option>2013</option>
								<option>2014</option>
								<option>2015</option>
								<option>2016</option>
								<option>2017</option>
								<option>2018</option>
								<option>2019</option>
							</select>
							<input id="week" name="selectedweek" type="hidden" value=""></input>
							<input type="submit" value="Go"></input>
							</form>
						</div>
						<div class="button">Imp</div>
						<div class="button">Exp</div>
						<div class="button">&gt;&gt;</div>
					</div>
					<div id="calendar">
						<div id="tabbar">
							<xsl:variable name="yearviewlink">
								<xsl:text>calendarservlet?view=yearview&amp;selectedyear=</xsl:text>
								<xsl:value-of select="@selectedyear"></xsl:value-of>
								<xsl:text>&amp;selectedweek=</xsl:text>
								<xsl:value-of select="@selectedweek"></xsl:value-of>
								<xsl:text>&amp;selectedmonth=</xsl:text>
								<xsl:value-of select="@selectedmonth"></xsl:value-of>
								<xsl:text>&amp;selectedday=</xsl:text>
								<xsl:value-of select="@selectedday"></xsl:value-of>
							</xsl:variable>
							<div class="tab" onclick="window.location='{$yearviewlink}'">Jahresansicht</div>
							<div class="tab selected">Monatsansicht</div>
							<xsl:variable name="weekviewlink">
								<xsl:text>calendarservlet?view=weekview&amp;selectedyear=</xsl:text>
								<xsl:value-of select="@selectedyear"></xsl:value-of>
								<xsl:text>&amp;selectedmonth=</xsl:text>
								<xsl:value-of select="@selectedmonth"></xsl:value-of>
								<xsl:text>&amp;selectedweek=</xsl:text>
								<xsl:value-of select="@selectedweek"></xsl:value-of>
								<xsl:text>&amp;selectedday=</xsl:text>
								<xsl:value-of select="@selectedday"></xsl:value-of>
							</xsl:variable>
							<div class="tab" onclick="window.location='{$weekviewlink}'">Wochenansicht</div>
						</div>
						<xsl:apply-templates />
					</div>
					<div id="footer">
						Impressum: &#160; Michael Müller &#160; Tel: 10932048091284 &#160;
						Email:
						<a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a>
						&#160; Mehr:
						<a href="About.html">About</a>
					</div>
				</div>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="year">
		<div id="cyear">

			<div class="titlerow">
				<div class="daytitle">
					<xsl:text>Montag</xsl:text>
				</div>
			</div>
			<div class="titlerow">
				<div class="daytitle">
					<xsl:text>Dienstag</xsl:text>
				</div>
			</div>
			<div class="titlerow">
				<div class="daytitle">
					<xsl:text>Mittwoch</xsl:text>
				</div>
			</div>
			<div class="titlerow">
				<div class="daytitle">
					<xsl:text>Donnerstag</xsl:text>
				</div>
			</div>
			<div class="titlerow">
				<div class="daytitle">
					<xsl:text>Freitag</xsl:text>
				</div>
			</div>
			<div class="titlerow">
				<div class="daytitle">
					<xsl:text>Samstag</xsl:text>
				</div>
			</div>
			<div class="titlerow">
				<div class="daytitle">
					<xsl:text>Sonntag</xsl:text>
				</div>
			</div>
			
			<xsl:variable name="nrmonths" select="count(//month)" />	<!--Anzahl von Monaten im XML-File-->
			<!--<xsl:value-of select="$nrmonths" />-->
			
			<xsl:variable name="nrdays" select="count(//day)" />	<!--Anzahl von Tagen im XML-File-->
			<xsl:variable name="dayheight" select="450 div ($nrdays div 7) - 3" />
			<!--<xsl:value-of select="$dayheight" />-->
			
			
			
			<xsl:if test="$nrmonths = 3">	<!--Wenn 3 Monatseinträge im XML-File-->
				<xsl:for-each select="//month[1]/week/day">
					<div class="weekdayback">
						<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
						<div class="weekdayothermonth">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<xsl:value-of select="@number" />		<!--VORIGER MONAT-->
						</div>
					</div>
				</xsl:for-each>
				<xsl:for-each select="//month[2]/week/day">
					<div class="weekdayback">
						<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
						<div class="weekday">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<xsl:value-of select="@number" />		<!--AKTUELLER MONAT-->
						</div>
					</div>
				</xsl:for-each>
				<xsl:for-each select="//month[3]/week/day">
					<div class="weekdayback">
						<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
						<div class="weekdayothermonth">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<xsl:value-of select="@number" />		<!--FOLGENDER MONAT-->
						</div>
					</div>
				</xsl:for-each>	
			</xsl:if>
			
			<xsl:if test="$nrmonths = 2">	<!--Wenn 2 Monatseinträge im XML-File-->
				<xsl:variable name="nrdays1" select="count(//month[1]/week/day)" />
				<xsl:variable name="nrdays2" select="count(//month[2]/week/day)" />
				<xsl:if test="$nrdays1 &lt; $nrdays2">
					<xsl:for-each select="//month[1]/week/day">
						<div class="weekdayback">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<div class="weekdayothermonth">
								<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
								<xsl:value-of select="@number" />		<!--VORIGER MONAT-->
							</div>
						</div>
					</xsl:for-each>
					<xsl:for-each select="//month[2]/week/day">
						<div class="weekdayback">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<div class="weekday">
								<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
								<xsl:value-of select="@number" />		<!--AKTUELLER MONAT-->
							</div>
						</div>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="$nrdays2 &lt; $nrdays1">
					<xsl:for-each select="//month[1]/week/day">
						<div class="weekdayback">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<div class="weekday">
								<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
								<xsl:value-of select="@number" />		<!--AKTUELLER MONAT-->
							</div>
						</div>
					</xsl:for-each>
					<xsl:for-each select="//month[2]/week/day">
						<div class="weekdayback">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<div class="weekdayothermonth">
								<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
								<xsl:value-of select="@number" />		<!--FOLGENDER MONAT-->
							</div>
						</div>
					</xsl:for-each>
				</xsl:if>
			</xsl:if>
			
			<xsl:if test="$nrmonths = 1">	<!--Wenn 1 Monatseintrag im XML-File (Nur wenn Februar, Schaltjahr, 1. Februar = Montag)-->
				<xsl:for-each select="//day">
					<div class="weekdayback">
						<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
						<div class="weekday">
							<xsl:attribute name="style">height:<xsl:value-of select="$dayheight" />px</xsl:attribute>
							<xsl:value-of select="@number" />		<!--FEBRUAR, 28 Tage, Montag der erste-->
						</div>
					</div>
				</xsl:for-each>
			</xsl:if>
			
		</div>
		
	</xsl:template>
	

</xsl:stylesheet>