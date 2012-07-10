<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
	<xsl:output method="xml" encoding="utf-8" indent="yes"
		doctype-public="-//W3C//DTD XHTML 1.1//EN" doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" />

	<xsl:include href="Basic.xsl" />

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
				<script type="text/JavaScript" src="Resources/Import.js"></script>
			</head>
			<body>
				<div id="fileopen"></div>
				<div id="main">
					<div id="hiddeninfo">
						<div id="selectedyear">
							<xsl:value-of select="@selectedyear"></xsl:value-of>
						</div>
						<div id="selectedmonth">
							<xsl:value-of select="@selectedmonth"></xsl:value-of>
						</div>
						<div id="selectedweek">
							<xsl:value-of select="@selectedweek"></xsl:value-of>
						</div>
						<div id="selectedday">
							<xsl:value-of select="@selectedday"></xsl:value-of>
						</div>
					</div>
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
					<div id="title">
						<div id="titleimage"></div>
					</div>
					<div id="menubar">
						<div class="button"
							onclick="changeMonth('calendarservlet?view=monthview', false)">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/PfeilLinks_HP.png"></img>
							</div>
						<xsl:variable name="newentry">
							<xsl:text>Edit.html?view=monthview&amp;year=</xsl:text>
							<xsl:value-of select="@selectedyear"></xsl:value-of>
							<xsl:text>&amp;month=</xsl:text>
							<xsl:value-of select="@selectedmonth"></xsl:value-of>
							<xsl:text>&amp;week=</xsl:text>
							<xsl:value-of select="@selectedweek"></xsl:value-of>
							<xsl:text>&amp;day=</xsl:text>
							<xsl:value-of select="@selectedday"></xsl:value-of>
						</xsl:variable>
						<div class="button" onclick="window.location='{$newentry}'">
						<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/neuerTermin_HP.png"></img>
						</div>
						<div class="button" onclick="goToToday('calendarservlet?view=monthview')">Heute</div>
						<div class="menuitem">
							<form action="calendarservlet" method="get">
								<div>
									<input type="hidden" name="view" value="monthview"></input>
									<select id="day" name="selectedday" size="1" onchange="update();">
									</select>
									<select id="month" name="selectedmonth" size="1"
										onchange="update();">
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
								</div>
							</form>
						</div>
						<div class="button" onclick="showFileOpenDialog();">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/import-icon_HP.png"></img>
						</div>
						<div class="button" onclick="window.location='import?action=export'">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/export-icon_HP.png"></img>
						</div>
						<div class="button"
							onclick="changeMonth('calendarservlet?view=monthview', true)">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/PfeilRechts_HP.png"></img>
							</div>
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
							<div class="tab selected">
								Monatsansicht
								<xsl:value-of
									select="year/month[@number=//calendar/@selectedmonth]/@name"></xsl:value-of>
							</div>
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
		<div id="titlerow">

			<div class="daytitle">
				<div class="titlecontent">
					<xsl:text>Montag</xsl:text>
				</div>
			</div>
			<div class="daytitle">
				<div class="titlecontent">
					<xsl:text>Dienstag</xsl:text>
				</div>
			</div>
			<div class="daytitle">
				<div class="titlecontent">
					<xsl:text>Mittwoch</xsl:text>
				</div>
			</div>
			<div class="daytitle">
				<div class="titlecontent">
					<xsl:text>Donnerstag</xsl:text>
				</div>
			</div>
			<div class="daytitle">
				<div class="titlecontent">
					<xsl:text>Freitag</xsl:text>
				</div>
			</div>
			<div class="daytitle">
				<div class="titlecontent">
					<xsl:text>Samstag</xsl:text>
				</div>
			</div>
			<div class="daytitle">
				<div class="titlecontent">
					<xsl:text>Sonntag</xsl:text>
				</div>
			</div>
		</div>
		<div id="weekrows">
			<xsl:variable name="firstday"
				select="(//calendar/year[@number=//calendar/@selectedyear]/month[@number=//calendar/@selectedmonth]/week/day)[1]/@name"></xsl:variable>
			<xsl:variable name="lastday"
				select="(//calendar/year[@number=//calendar/@selectedyear]/month[@number=//calendar/@selectedmonth]/week/day)[last()]/@name"></xsl:variable>
			<xsl:variable name="rows"
				select="count(month[@number=//calendar/@selectedmonth]/week)"></xsl:variable>
			<xsl:variable name="height">
				<xsl:value-of select="100 div $rows"></xsl:value-of>
				<xsl:text>%</xsl:text>
			</xsl:variable>
			<xsl:variable name="width">
				<xsl:value-of select="100.0 div 7"></xsl:value-of>
				<xsl:text>%</xsl:text>
			</xsl:variable>
			<xsl:variable name="posfirst">
				<xsl:choose>
					<xsl:when test="$firstday='Di'">
						2
					</xsl:when>
					<xsl:when test="$firstday='Mi'">
						3
					</xsl:when>
					<xsl:when test="$firstday='Do'">
						4
					</xsl:when>
					<xsl:when test="$firstday='Fr'">
						5
					</xsl:when>
					<xsl:when test="$firstday='Sa'">
						6
					</xsl:when>
					<xsl:when test="$firstday='So'">
						7
					</xsl:when>
					<xsl:otherwise>
						1
					</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			<xsl:variable name="poslast">
				<xsl:choose>
					<xsl:when test="$lastday='Di'">
						2
					</xsl:when>
					<xsl:when test="$lastday='Mi'">
						3
					</xsl:when>
					<xsl:when test="$lastday='Do'">
						4
					</xsl:when>
					<xsl:when test="$lastday='Fr'">
						5
					</xsl:when>
					<xsl:when test="$lastday='Sa'">
						6
					</xsl:when>
					<xsl:when test="$lastday='So'">
						7
					</xsl:when>
					<xsl:otherwise>
						1
					</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			<xsl:variable name="daysmonthbefore"
				select="count(month[@number=(-1+//calendar/@selectedmonth)]/week/day)"></xsl:variable>
			<xsl:variable name="daysbefore" select="$daysmonthbefore - $posfirst + 1"></xsl:variable>

			<xsl:if test="//calendar/@selectedmonth &gt; 1">
				<xsl:for-each
					select="month[@number=(-1+//calendar/@selectedmonth)]/week/day[@number &gt; $daysbefore]">
					<div class="monthday" style="width: {$width}; height: {$height};">
						<div class="monthdaycontent">
							<div class="daynumber">
								<xsl:value-of select="@number"></xsl:value-of>
							</div>
							<xsl:apply-templates></xsl:apply-templates>
						</div>
					</div>
				</xsl:for-each>
			</xsl:if>
			<xsl:for-each select="month[@number=//calendar/@selectedmonth]/week/day">
				<div class="monthday" style="width: {$width}; height: {$height};">
					<div class="monthdaycontent">
						<div class="daynumber">
							<xsl:value-of select="@number"></xsl:value-of>
						</div>
						<xsl:apply-templates></xsl:apply-templates>
					</div>
				</div>
			</xsl:for-each>
			<xsl:if test="//calendar/@selectedmonth &lt; 12">
				<xsl:for-each
					select="month[@number=(1+//calendar/@selectedmonth)]/week/day[@number &lt;= 7 - $poslast]">
					<div class="monthday" style="width: {$width}; height: {$height};">
						<div class="monthdaycontent">
							<div class="daynumber">
								<xsl:value-of select="@number"></xsl:value-of>
							</div>
							<xsl:apply-templates></xsl:apply-templates>
						</div>
					</div>
				</xsl:for-each>
			</xsl:if>
		</div>
	</xsl:template>

	<xsl:template match="entry">
		<xsl:variable name="title">
			<xsl:value-of select="summary" />
			<xsl:text>&#xD;</xsl:text>
			<xsl:value-of select="starttime/@hours" />
			<xsl:text>:</xsl:text>
			<xsl:value-of select="starttime/@minutes" />
			<xsl:text> Uhr - </xsl:text>
			<xsl:value-of select="endtime/@hours" />
			<xsl:text>:</xsl:text>
			<xsl:value-of select="endtime/@minutes" />
			<xsl:text> Uhr&#xD;</xsl:text>
			<xsl:value-of select="description" />
			<xsl:text>&#xD;</xsl:text>
		</xsl:variable>
		<xsl:variable name="link">
			<xsl:text>Edit.html?view=weekview</xsl:text>
			<xsl:call-template name="entrycontent"></xsl:call-template>
		</xsl:variable>
		<xsl:variable name="time">
			<xsl:value-of select="starttime/@hours"></xsl:value-of>
			:
			<xsl:value-of select="starttime/@minutes"></xsl:value-of>
		</xsl:variable>
		<div class="entry" style="background-color: #{@color}" onclick="window.location='{$link}'"
			title="{$title}">
			<xsl:value-of select="translate(normalize-space($time), ' ', '')"></xsl:value-of>
			<xsl:text> Uhr </xsl:text>
			<xsl:value-of select="summary"></xsl:value-of>
		</div>
	</xsl:template>

</xsl:stylesheet>