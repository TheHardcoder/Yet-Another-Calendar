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
				<script type="text/JavaScript" src="Resources/analog_clock.js"></script>
				<script type="text/JavaScript" src="Resources/DateChooser.js"></script>
				<script type="text/JavaScript" src="Resources/SmallCalendar.js"></script>
			</head>
			<body>
				<div id="main">
					<div id="logo">
						<img src="Resources/BabyGnu.png" width="100px" height="100px"
							alt="logo" />
					</div>
					<div id="info">
						<a href="index.jsp" id="logout">Logout</a>
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
					<div id="menubar">
						<div class="button">&lt;&lt;</div>
						<div class="button">Neu</div>
						<div class="button">Heute</div>
						<div class="menuitem">
							<select id="day" name="day" size="1">
							</select>
							<select id="month" name="month" size="1" onchange="update();">
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
							<select id="year" name="year" size="1" onchange="update();">
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
						</div>
						<div class="button">Imp</div>
						<div class="button">Exp</div>
						<div class="button">&gt;&gt;</div>
					</div>
					<div id="calendar">
						<div id="tabbar">
							<div class="tab selected">Jahresansicht</div>
							<div class="tab">Monatsansicht</div>
							<div class="tab">Wochenansicht</div>
						</div>
						<xsl:apply-templates />
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
		<div class="day">
			<xsl:variable name="curd" select="@number">
			</xsl:variable>
			<xsl:variable name="newentry">
			<xsl:text>Edit.html?</xsl:text>
			<xsl:text>day=</xsl:text><xsl:value-of select="@number"></xsl:value-of>
			<xsl:text>&amp;month=</xsl:text><xsl:value-of select="../../@number"></xsl:value-of>
			<xsl:text>&amp;year=</xsl:text><xsl:value-of select="../../../@number"></xsl:value-of>
			</xsl:variable>
			<div class="daylabel" ondblclick="window.location='{$newentry}'">
				<xsl:value-of select="$curd" />
				<xsl:text> </xsl:text>
				<xsl:value-of select="@name" />
			</div>
			<xsl:variable name="no" select='count(entry)' />
			<xsl:variable name="title">
				<xsl:for-each select="entry">
					<xsl:value-of select="summary" />
					<xsl:text> </xsl:text>
					<xsl:value-of select="starttime/@hours" />
					<xsl:text>:</xsl:text>
					<xsl:value-of select="starttime/@minutes" />
					<xsl:text> Uhr&#xD;</xsl:text>
					<xsl:value-of select="description" />
					<xsl:text>&#xD;</xsl:text>
					<xsl:if test="position() != last()">
						<xsl:text>&#xD;</xsl:text>
					</xsl:if>
				</xsl:for-each>
			</xsl:variable>
			<xsl:variable name="link">
				<xsl:text>Edit.html</xsl:text>
				<xsl:text>?description=</xsl:text>
				<xsl:value-of select="entry/description" />
				<xsl:text>&amp;summary=</xsl:text>
				<xsl:value-of select="entry/summary" />
				<xsl:text>&amp;starttime=</xsl:text>
				<xsl:value-of select="entry/starttime/@hours" />
				<xsl:text>:</xsl:text>
				<xsl:value-of select="entry/starttime/@minutes" />
				<xsl:text>&amp;endtime=</xsl:text>
				<xsl:value-of select="entry/endtime/@hours" />
				<xsl:text>:</xsl:text>
				<xsl:value-of select="entry/endtime/@minutes" />
			</xsl:variable>
			<xsl:if test="$no = 1">
				<a href="{$link}" class="entry" title="{$title}">
					<xsl:value-of select="$no" />
					<xsl:text> Termin</xsl:text>
				</a>
			</xsl:if>
			<xsl:if test="$no &gt; 1">
				<a href="{$link}" class="entry" title="{$title}">
					<xsl:value-of select="$no" />
					<xsl:text> Termine</xsl:text>
				</a>
			</xsl:if>

		</div>
	</xsl:template>

</xsl:stylesheet>