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
				<style type="text/css">
					<!-- Turn the sections on and off based on the current target -->
					<xsl:for-each select="z:seriesResults|z:eventResults|z:raceResults">
						#<xsl:apply-templates select="." mode="r:index"/>:not(:target) { display: none; }
						#<xsl:apply-templates select="." mode="r:index"/>:target { display: block; }
					</xsl:for-each>
				</style>
			</head>
			<body>
				<!-- Menu for series/event/race results -->
				<ul class="menu">
					<xsl:for-each select="z:seriesResults|z:eventResults|z:raceResults">
						<li>
							<xsl:attribute name="class"><xsl:apply-templates select="." mode="r:index"/></xsl:attribute>
							<xsl:text> </xsl:text>
							<a>
								<xsl:attribute name="href">#<xsl:apply-templates select="." mode="r:index"/></xsl:attribute>
								<xsl:apply-templates select="." mode="r:name"/>
							</a>
							<xsl:text> </xsl:text>
						</li>
					</xsl:for-each>
				</ul>
				<!-- Tables for series/event/race results -->
				<xsl:for-each select="z:seriesResults|z:eventResults|z:raceResults">
					<div>
						<xsl:attribute name="id"><xsl:apply-templates select="." mode="r:index"/></xsl:attribute>
						<xsl:apply-templates select="." mode="r:body"/>
					</div>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="z:event" mode="r:th">
		<xsl:apply-templates select="." mode="th">
			<xsl:with-param name="results" select="/z:cursus/z:eventResults/z:event[@ref=current()/@xml:id]/.."/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="z:race" mode="r:th">
		<xsl:apply-templates select="." mode="th">
			<xsl:with-param name="results" select="/z:cursus/z:raceResults/z:race[@ref=current()/@xml:id]/.."/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="z:event|z:race" mode="th">
		<xsl:param name="results"/>
		<xsl:choose>
			<xsl:when test="$results">
				<a>
					<xsl:attribute name="href">#<xsl:apply-templates select="$results" mode="r:index"/></xsl:attribute>
					<xsl:value-of select="z:name"/>
				</a>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="z:name"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
