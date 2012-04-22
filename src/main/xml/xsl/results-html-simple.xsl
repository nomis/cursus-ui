<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:d="urn:oid:1.3.6.1.4.1.39777.1.0.1.0.1"
		xmlns:r="urn:oid:1.3.6.1.4.1.39777.1.0.1.2.1"
		xmlns:s="urn:oid:1.3.6.1.4.1.39777.1.0.1.1.1"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="urn:oid:1.3.6.1.4.1.39777.1.0.1.0.1 https://xsd.s85.org/urn/oid/1.3.6.1.4.1.39777.1.0.1.0.1
			urn:oid:1.3.6.1.4.1.39777.1.0.1.1.1 https://xsd.s85.org/urn/oid/1.3.6.1.4.1.39777.1.0.1.1.1
			urn:oid:1.3.6.1.4.1.39777.1.0.1.2.1 https://xsd.s85.org/urn/oid/1.3.6.1.4.1.39777.1.0.1.2.1"
		version="1.0" xml:lang="en">

	<xsl:import href="results-html.xsl"/>

	<xsl:output method="html" version="5.0" encoding="UTF-8" indent="yes"/>

	<xsl:variable name="stylesheet" select="/r:cursus/r:stylesheet"/>

	<xsl:template match="/r:cursus">
		<xsl:apply-templates select="document(r:load/@href)/s:cursus" mode="r:page"/>
	</xsl:template>

	<xsl:template match="/s:cursus" mode="r:page">
		<html>
			<head>
				<title>Results for <xsl:value-of select="d:series/d:name"/></title>

				<xsl:for-each select="$stylesheet">
					<link rel="stylesheet" type="text/css">
						<xsl:attribute name="href">
							<xsl:value-of select="@href"/>
						</xsl:attribute>
					</link>
				</xsl:for-each>
			</head>
			<body>
				<xsl:apply-templates select="s:seriesResults|s:eventResults|s:raceResults" mode="r:body"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
