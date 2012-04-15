<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet version="1.0" xml:lang="en" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:r="urn:oid:1.3.6.1.3.63193331266300908.1.0.1.2" xmlns:z="urn:oid:1.3.6.1.3.63193331266300908.1.0.1.1">
	<xsl:import href="results-html.xsl"/>

	<xsl:output method="html" version="5.0" encoding="UTF-8" indent="yes"/>

	<xsl:variable name="params" select="/r:cursus/r:param"/>

	<xsl:template match="/r:cursus">
		<xsl:apply-templates select="document(r:load/@href)/z:cursus" mode="r:page"/>
	</xsl:template>

	<xsl:template match="/z:cursus" mode="r:page">
		<html>
			<head>
				<title>Results for <xsl:value-of select="z:series/z:name"/></title>

				<xsl:for-each select="$params[@name='stylesheet']">
					<link rel="stylesheet" type="text/css">
						<xsl:attribute name="href">
							<xsl:value-of select="@href"/>
						</xsl:attribute>
					</link>
				</xsl:for-each>
			</head>
			<body>
				<xsl:apply-templates select="z:seriesResults|z:eventResults|z:raceResults" mode="r:body"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
