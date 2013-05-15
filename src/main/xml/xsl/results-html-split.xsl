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
	<xsl:variable name="split" select="/r:cursus/r:split"/>
	<xsl:variable name="this" select="concat($split/@type, $split/@index)"/>

	<xsl:template match="/r:cursus">
		<xsl:apply-templates select="document(r:load/@href)/s:cursus" mode="r:page"/>
	</xsl:template>

	<xsl:template match="/s:cursus" mode="r:page">
		<xsl:for-each select="s:seriesResults|s:eventResults|s:raceResults">
			<xsl:variable name="index"><xsl:apply-templates select="." mode="r:index"/></xsl:variable>
			<xsl:if test="$index = $this">
				<xsl:apply-templates select="/s:cursus" mode="r:split">
					<xsl:with-param name="results" select="."/>
				</xsl:apply-templates>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="/s:cursus" mode="r:split">
		<xsl:param name="results"/>

		<html>
			<head>
				<title>Results for <xsl:value-of select="d:series/d:name"/> – <xsl:apply-templates select="$results" mode="r:name"/></title>

				<xsl:for-each select="$stylesheet">
					<link rel="stylesheet" type="text/css">
						<xsl:attribute name="href">
							<xsl:value-of select="@href"/>
						</xsl:attribute>
					</link>
				</xsl:for-each>
			</head>
			<body>
				<!-- Menu for series/event/race results -->
				<ul class="menu">
					<xsl:for-each select="s:seriesResults|s:eventResults|s:raceResults">
						<xsl:variable name="index"><xsl:apply-templates select="." mode="r:index"/></xsl:variable>
						<li>
							<xsl:attribute name="class"><xsl:value-of select="$index"/><xsl:text> </xsl:text><xsl:if test="$index = $this">current</xsl:if></xsl:attribute>
							<xsl:text> </xsl:text>
							<xsl:choose>
								<xsl:when test="$index = $this"><xsl:apply-templates select="." mode="r:name"/></xsl:when>
								<xsl:otherwise>
									<a>
										<xsl:variable name="desc"><xsl:apply-templates select="." mode="r:desc"/></xsl:variable>
										<xsl:attribute name="href"><xsl:value-of select="$split/@prefix"/><xsl:value-of select="$index"/><xsl:value-of select="$split/@suffix"/></xsl:attribute>
										<xsl:if test="$desc != ''">
											<xsl:attribute name="title">
												<xsl:value-of select="$desc"/>
											</xsl:attribute>
										</xsl:if>
										<xsl:apply-templates select="." mode="r:name"/>
									</a>
								</xsl:otherwise>
							</xsl:choose>
							<xsl:text> </xsl:text>
						</li>
					</xsl:for-each>
				</ul>
				<xsl:apply-templates select="$results" mode="r:body"/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="d:event" mode="r:th">
		<xsl:apply-templates select="." mode="th">
			<xsl:with-param name="results" select="/s:cursus/s:eventResults[@event=current()/@xml:id]"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="d:race" mode="r:th">
		<xsl:apply-templates select="." mode="th">
			<xsl:with-param name="results" select="/s:cursus/s:raceResults[@race=current()/@xml:id]"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="d:event|d:race" mode="th">
		<xsl:param name="results"/>
		<xsl:variable name="index"><xsl:apply-templates select="$results" mode="r:index"/></xsl:variable>
		<xsl:choose>
			<xsl:when test="$results and $index != $this">
				<a>
					<xsl:attribute name="href"><xsl:value-of select="$split/@prefix"/><xsl:apply-templates select="$results" mode="r:index"/><xsl:value-of select="$split/@suffix"/></xsl:attribute>
					<xsl:if test="d:description != ''">
						<xsl:attribute name="title">
							<xsl:apply-templates select="." mode="r:description"/>
						</xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="r:name"/>
				</a>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates select="." mode="r:name"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
