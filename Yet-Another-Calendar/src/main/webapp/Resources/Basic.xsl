<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
	<xsl:output method="xml" encoding="utf-8" indent="yes"
		doctype-public="-//W3C//DTD XHTML 1.1//EN" doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" />

	<xsl:template name="entrycontent">
		<xsl:text>&amp;id=</xsl:text>
		<xsl:value-of select="@id"></xsl:value-of>
		<xsl:text>&amp;priority=</xsl:text>
		<xsl:value-of select="@priority"></xsl:value-of>
		<xsl:text>&amp;color=</xsl:text>
		<xsl:value-of select="@color"></xsl:value-of>
		<xsl:text>&amp;summary=</xsl:text>
		<xsl:value-of select="summary"></xsl:value-of>

		<xsl:text>&amp;year=</xsl:text>
		<xsl:value-of select="startdate/@year" />
		<xsl:text>&amp;month=</xsl:text>
		<xsl:value-of select="startdate/@month" />
		<xsl:text>&amp;day=</xsl:text>
		<xsl:value-of select="startdate/@day" />
		<xsl:text>&amp;starttimehours=</xsl:text>
		<xsl:value-of select="startdate/@hours" />
		<xsl:text>&amp;starttimeminutes=</xsl:text>
		<xsl:value-of select="startdate/@minutes" />

		<xsl:text>&amp;endyear=</xsl:text>
		<xsl:value-of select="enddate/@year" />
		<xsl:text>&amp;endmonth=</xsl:text>
		<xsl:value-of select="enddate/@month" />
		<xsl:text>&amp;endday=</xsl:text>
		<xsl:value-of select="enddate/@day" />
		<xsl:text>&amp;endtimehours=</xsl:text>
		<xsl:value-of select="enddate/@hours" />
		<xsl:text>&amp;endtimeminutes=</xsl:text>
		<xsl:value-of select="enddate/@minutes" />
		<xsl:text>&amp;place=</xsl:text>
		<xsl:value-of select="location" />
		<xsl:text>&amp;description=</xsl:text>
		<xsl:value-of select="description" />
		<xsl:text>&amp;categories=</xsl:text>
		<xsl:apply-templates select="categories/category"></xsl:apply-templates>

		<xsl:text>&amp;createdYear=</xsl:text>
		<xsl:value-of select="created/@year" />
		<xsl:text>&amp;createdMonth=</xsl:text>
		<xsl:value-of select="created/@month" />
		<xsl:text>&amp;createdDay=</xsl:text>
		<xsl:value-of select="created/@day" />
		<xsl:text>&amp;createdHour=</xsl:text>
		<xsl:value-of select="created/@hours" />
		<xsl:text>&amp;createdMinutes=</xsl:text>
		<xsl:value-of select="created/@minutes" />

		<xsl:text>&amp;comment=</xsl:text>
		<xsl:value-of select="comment" />
	</xsl:template>
	
</xsl:stylesheet>