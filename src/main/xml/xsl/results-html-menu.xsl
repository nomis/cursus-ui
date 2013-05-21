<?xml version="1.0" encoding="UTF-8"?>
<!--
	cursus - Race series management program
	Copyright 2012-2013  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
-->
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
				<style type="text/css">
					<!-- Turn the sections on and off based on the current target -->
					<xsl:for-each select="s:seriesResults|s:eventResults|s:raceResults">
						#<xsl:apply-templates select="." mode="r:index"/>:not(:target) { display: none; }
						#<xsl:apply-templates select="." mode="r:index"/>:target { display: block; }
					</xsl:for-each>
				</style>
			</head>
			<body>
				<!-- Menu for series/event/race results -->
				<ul class="menu">
					<xsl:for-each select="s:seriesResults|s:eventResults|s:raceResults">
						<xsl:variable name="index"><xsl:apply-templates select="." mode="r:index"/></xsl:variable>
						<li>
							<xsl:attribute name="class"><xsl:value-of select="$index"/></xsl:attribute>
							<xsl:text> </xsl:text>
							<a>
								<xsl:variable name="desc"><xsl:apply-templates select="." mode="r:desc"/></xsl:variable>
								<xsl:attribute name="href">#<xsl:value-of select="$index"/></xsl:attribute>
								<xsl:if test="$desc != ''">
									<xsl:attribute name="title">
										<xsl:value-of select="$desc"/>
									</xsl:attribute>
								</xsl:if>
								<xsl:apply-templates select="." mode="r:name"/>
							</a>
							<xsl:text> </xsl:text>
						</li>
					</xsl:for-each>
				</ul>
				<!-- Tables for series/event/race results -->
				<xsl:for-each select="s:seriesResults|s:eventResults|s:raceResults">
					<div>
						<xsl:attribute name="id"><xsl:apply-templates select="." mode="r:index"/></xsl:attribute>
						<xsl:apply-templates select="." mode="r:body"/>
					</div>
				</xsl:for-each>
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
		<xsl:choose>
			<xsl:when test="$results">
				<a>
					<xsl:attribute name="href">#<xsl:apply-templates select="$results" mode="r:index"/></xsl:attribute>
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
