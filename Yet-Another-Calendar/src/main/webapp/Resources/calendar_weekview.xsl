<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
	<xsl:output method="xml" encoding="utf-8" indent="yes"
		doctype-public="-//W3C//DTD XHTML 1.1//EN" doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" />


	<xsl:variable name="no" select='count(//appointment)' />

	<xsl:template match="/calendar">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<title>Yet Another Calendar</title>
				<link type="text/css" rel="stylesheet" media="screen"
					href="Resources/structure.css"></link>
				<script type="text/JavaScript" src="Resources/analog_clock.js"></script>
				<script type="text/JavaScript" src="Resources/DateChooser.js"></script>
				<script type="text/JavaScript" src="Resources/SmallCalendar.js"></script>
			</head>
			<body>
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
						<img src="Resources/BabyGnu.png" width="100px" height="100px"
							alt="logo" />
					</div>
					<div id="info">
						<form action="userservlet?action=logout" method="post">
							<input type="submit" id="logout" value="Logout"></input>
						</form>
						<canvas width="150" height="150" id="analog_clock"></canvas>
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
					<xsl:variable name="calendarback">
						<xsl:text>calendarservlet?view=weekview</xsl:text>
						<xsl:text>&amp;selectedyear=</xsl:text>
						<xsl:value-of select="@selectedyear"></xsl:value-of>
						<xsl:text>&amp;selectedweek=</xsl:text>
						<xsl:value-of select="@selectedweek - 1"></xsl:value-of>
					</xsl:variable>
					<xsl:variable name="calendarforward">
						<xsl:text>calendarservlet?view=weekview</xsl:text>
						<xsl:text>&amp;selectedyear=</xsl:text>
						<xsl:value-of select="@selectedyear"></xsl:value-of>
						<xsl:text>&amp;selectedweek=</xsl:text>
						<xsl:value-of select="@selectedweek + 1"></xsl:value-of>
					</xsl:variable>
					<div id="menubar">
						<div class="button" onclick="window.location='{$calendarback}'">&lt;&lt;</div>
						<div class="button">Neu</div>
						<div class="button" onclick="goToToday('calendarservlet?view=weekview')">Heute</div>
						<div class="menuitem">
							<form action="calendarservlet" method="get">
								<input type="hidden" name="view" value="yearview"></input>
								<select id="day" name="selectedday" size="1">
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
								<input type="submit" value="Go"></input>
							</form>
						</div>
						<div class="button">Imp</div>
						<div class="button">Exp</div>
						<div class="button" onclick="window.location='{$calendarforward}'">&gt;&gt;</div>
					</div>
					<div id="calendar">
						<div id="tabbar">
							<xsl:variable name="yearviewlink">
								<xsl:text>calendarservlet?view=yearview&amp;selectedyear=</xsl:text>
								<xsl:value-of select="@selectedyear"></xsl:value-of>
								<xsl:text>&amp;selectedweek=</xsl:text>
								<xsl:value-of select="@selectedweek"></xsl:value-of>
							</xsl:variable>
							<div class="tab" onclick="window.location='{$yearviewlink}'">Jahresansicht</div>
							<div class="tab">Monatsansicht</div>
							<div class="tab selected">Wochenansicht <xsl:value-of select="@selectedweek"></xsl:value-of>
							</div>
						</div>
						<xsl:apply-templates select="year[1]/month[1]/week[2]"/>
					</div>
					<div id="footer">
						Impressum: &#160; Michael Müller &#160; Tel: 10932048091284 &#160;
						Email:
						<a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a>
						&#160; Mehr:
						<a href="about.html">About</a>
					</div>
				</div>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="year[1]/month[1]/week[2]">
		<div id="weekview">
			<div id="times">
			<div class="hourlabel"> </div>
			<xsl:call-template name="selects">
               			<xsl:with-param name="i">1</xsl:with-param>
               			<xsl:with-param name="count">24</xsl:with-param>
               			<xsl:with-param name="print">1</xsl:with-param>
            </xsl:call-template>
            </div>
            <div id="week">
			<xsl:for-each select="day">
				<div class="daycolumn">
					<div class="daycolumntitle">
						<xsl:value-of select="@name"></xsl:value-of>
					</div>
					<xsl:call-template name="selects">
               			<xsl:with-param name="i">1</xsl:with-param>
               			<xsl:with-param name="count">24</xsl:with-param>
               			<xsl:with-param name="print">0</xsl:with-param>
            		</xsl:call-template> 
				</div>
			</xsl:for-each>
			</div>
		</div>
	</xsl:template>


	<xsl:template name="selects">
		<xsl:param name="i" />
		<xsl:param name="count" />
		<xsl:param name="print" />
		
		<xsl:variable name="time">
				<xsl:if test="$i &lt; 10">
					<xsl:text>0</xsl:text>
				</xsl:if>
				<xsl:value-of select="$i"></xsl:value-of>
				<xsl:text>:00</xsl:text>
		</xsl:variable>

		<xsl:if test="$i &lt;= $count">
			
			<xsl:if test="$print = 1">
				<div class="hourlabel">
				<xsl:value-of select="$time"></xsl:value-of>
				</div>
			</xsl:if>
			<xsl:if test="$print != 1">
			
				<div class="hour" title="{$time}">
				</div>
			</xsl:if>
		</xsl:if>

		<!--begin_: RepeatTheLoopUntilFinished -->
		<xsl:if test="$i &lt;= $count">
			<xsl:call-template name="selects">
				<xsl:with-param name="i">
					<xsl:value-of select="$i + 1" />
				</xsl:with-param>
				<xsl:with-param name="count">
					<xsl:value-of select="$count" />
				</xsl:with-param>
				<xsl:with-param name="print"><xsl:value-of select="$print" /></xsl:with-param>
			</xsl:call-template>
		</xsl:if>

	</xsl:template>


</xsl:stylesheet>