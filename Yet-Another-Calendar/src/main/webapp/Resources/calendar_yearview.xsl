<?xml version="1.0" encoding="UTF-8"?>
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
					href="Resources/yearview.css"></link>
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
					<xsl:variable name="calendarback">
						<xsl:text>calendarservlet?view=yearview</xsl:text>
						<xsl:text>&amp;selectedyear=</xsl:text>
						<xsl:value-of select="@selectedyear - 1"></xsl:value-of>
						<xsl:text>&amp;selectedweek=</xsl:text>
						<xsl:value-of select="@selectedweek"></xsl:value-of>
						<xsl:text>&amp;selectedmonth=</xsl:text>
						<xsl:value-of select="@selectedmonth"></xsl:value-of>
						<xsl:text>&amp;selectedday=</xsl:text>
						<xsl:value-of select="@selectedday"></xsl:value-of>
					</xsl:variable>
					<xsl:variable name="calendarforward">
						<xsl:text>calendarservlet?view=yearview</xsl:text>
						<xsl:text>&amp;selectedyear=</xsl:text>
						<xsl:value-of select="@selectedyear + 1"></xsl:value-of>
						<xsl:text>&amp;selectedweek=</xsl:text>
						<xsl:value-of select="@selectedweek"></xsl:value-of>
						<xsl:text>&amp;selectedmonth=</xsl:text>
						<xsl:value-of select="@selectedmonth"></xsl:value-of>
						<xsl:text>&amp;selectedday=</xsl:text>
						<xsl:value-of select="@selectedday"></xsl:value-of>
					</xsl:variable>
					<div id="menubar">
						<div class="button" onclick="window.location='{$calendarback}'">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/PfeilLinks_HP.png"></img>
						</div>
						<xsl:variable name="newentry">
							<xsl:text>Edit.html?view=weekview&amp;year=</xsl:text>
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
						<div class="button" onclick="goToToday('calendarservlet?view=yearview')">Heute</div>
						<div class="menuitem">
							<form action="calendarservlet" method="get">
								<div>
									<input type="hidden" name="view" value="yearview"></input>
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
						<div class="button"
							onclick="showFileOpenDialog('yearview', {@selectedyear}, {@selectedmonth}, {@selectedweek}, {@selectedday});">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/import-icon_HP.png"></img>
						</div>
						<div class="button" onclick="window.location='import?action=export'">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/export-icon_HP.png"></img>
						</div>
						<div class="button" onclick="window.location='{$calendarforward}'">
							<img width="32px" height="32px" style="margin-top: -5px;"
								src="Resources/Images/PfeilRechts_HP.png"></img>
						</div>
					</div>
					<div id="calendar">
						<div id="tabbar">
							<div class="tab selected">
								Jahresansicht
								<xsl:value-of select="@selectedyear"></xsl:value-of>
							</div>
							<xsl:variable name="monthviewlink">
								<xsl:text>calendarservlet?view=monthview&amp;selectedyear=</xsl:text>
								<xsl:value-of select="@selectedyear"></xsl:value-of>
								<xsl:text>&amp;selectedmonth=</xsl:text>
								<xsl:value-of select="@selectedmonth"></xsl:value-of>
								<xsl:text>&amp;selectedweek=</xsl:text>
								<xsl:value-of select="@selectedweek"></xsl:value-of>
								<xsl:text>&amp;selectedday=</xsl:text>
								<xsl:value-of select="@selectedday"></xsl:value-of>
							</xsl:variable>
							<div class="tab" onclick="window.location='{$monthviewlink}'">Monatsansicht</div>
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
						Impressum: &#160; Michael Müller &#160;
						Email:
						<a href="mailto:yac@iteabag.org">yac@iteabag.org</a>
						&#160; Mehr:
						<a href="About.html">About</a>
					</div>
				</div>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="year">
		<div id="cyear">
			<xsl:for-each select="month">
				<div class="month">
					<div class="daylist">
						<xsl:variable name="cur" select="@name"></xsl:variable>
						<div class="monthtitle">
							<xsl:value-of select="$cur" />
						</div>
						<xsl:apply-templates select="week/day" />
					</div>
				</div>
			</xsl:for-each>
		</div>
	</xsl:template>

	<xsl:template match="day">
		<xsl:if
			test="not(position() &lt;= 15 and @number &gt; 15 and ../../@number = 1) and not(position() &gt;= 15 and @number &lt; 15 and ../../@number = 12)">
			<div class="day">
				<xsl:variable name="curday" select="@number"></xsl:variable>
				<xsl:variable name="newentry">
					<xsl:text>Edit.html?view=yearview</xsl:text>
					<xsl:text>&amp;day=</xsl:text>
					<xsl:choose>
						<xsl:when test="@number &lt; 10">
							<xsl:text>0</xsl:text>
							<xsl:value-of select="@number"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="@number"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:text>&amp;month=</xsl:text>
					<xsl:choose>
						<xsl:when test="../../@number &lt; 10">
							<xsl:text>0</xsl:text>
							<xsl:value-of select="../../@number"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="../../@number"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:text>&amp;year=</xsl:text>
					<xsl:value-of select="../../../@number"></xsl:value-of>
				</xsl:variable>
				<div class="daylabel" ondblclick="window.location='{$newentry}'; return false;">
					<xsl:value-of select="$curday" />
					<xsl:text> </xsl:text>
					<xsl:value-of select="@name" />
				</div>
				<xsl:variable name="no" select='count(entry)' />
				<xsl:variable name="title">
					<xsl:for-each select="entry">
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
						<xsl:if test="position() != last()">
							<xsl:text>---------------------------&#xD;</xsl:text>
						</xsl:if>
					</xsl:for-each>
				</xsl:variable>
				<xsl:if test="$no = 1">
					<a
						href="javascript:goTo('calendarservlet?view=weekview',{../../../@number},{../../@number},{@number});"
						class="entry" title="{$title}">
						<xsl:value-of select="$no" />
						<xsl:text> Termin</xsl:text>
					</a>
				</xsl:if>
				<xsl:if test="$no &gt; 1">
					<a
						href="javascript:goTo('calendarservlet?view=weekview',{../../../@number},{../../@number},{@number});"
						class="entry" title="{$title}">
						<xsl:value-of select="$no" />
						<xsl:text> Termine</xsl:text>
					</a>
				</xsl:if>

			</div>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>