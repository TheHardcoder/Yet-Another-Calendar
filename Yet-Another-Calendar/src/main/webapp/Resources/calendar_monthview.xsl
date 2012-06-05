<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/loose.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" />

<xsl:variable name="cur"> </xsl:variable>
<xsl:variable name="no" select='count(//appointment)' />

<xsl:template match="/">
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>Yet Another Calendar</title>
			<link rel="stylesheet" media="screen" href="Resources/structure.css"></link>
			<script type="text/JavaScript" src="Resources/analog_clock.js"></script> 
		</head>
		<body>
			<div id="main">
				<div id="logo"><img src="Resources/BabyGnu.png" width="100px" height="100px" alt="logo"/></div>
				<div id="info"><canvas width="150" height="150" id="analog_clock"></canvas></div>
				<div id="title">Yet Another Calendar</div>
				<div id="menubar"></div>
				<div id="calendar">
					<xsl:apply-templates />
				</div>
				<div id="footer">Impressum: &#160; Michael Müller &#160; Tel: 10932048091284 &#160; Email: <a href="mailto:ofsdfjo@swfonm.net">ofsdfjo@swfonm.net</a> &#160; Mehr: <a href="about.html">About</a></div>
			</div>
		</body>
	</html>
</xsl:template>

<xsl:template match="calendar">
	<ul id="year">
      <xsl:for-each select="year/month">
		<li class="month">
		<ul class="daylist">
			<xsl:variable name="cur" select="@name"></xsl:variable>
			<li class="monthtitle"><xsl:value-of select="$cur"/></li>
			<xsl:apply-templates/>
		</ul>
		</li>
      </xsl:for-each>
    </ul>
</xsl:template>

<xsl:template match="day">
	<li class="day">
	<xsl:variable name="curd" select="@number"> </xsl:variable>
	<div class="daylabel"><xsl:value-of select="$curd"/> <xsl:text> </xsl:text> <xsl:value-of select="@name"/></div>
	
	<xsl:variable name="no" select='count(entry)' />
	<xsl:variable name="height" select='days * 23 - 3' /> 
	<xsl:if test="$no = 1">
	<div class="entry" title="{desc}" ><xsl:value-of select="$no"/><xsl:text> Term.</xsl:text></div>
	</xsl:if>
	<xsl:if test="$no &gt; 1">
	<div class="entry" title="{desc}"><xsl:value-of select="$no"/><xsl:text> Term</xsl:text></div>
	</xsl:if>
		
	</li>
</xsl:template>

<xsl:template match="entry">
</xsl:template>

</xsl:stylesheet>