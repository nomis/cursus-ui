<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet version="1.0" xml:lang="en" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:r="urn:oid:1.3.6.1.3.63193331266300908.1.0.1.2" xmlns:z="urn:oid:1.3.6.1.3.63193331266300908.1.0.1.1">
	<xsl:output method="html" version="5.0" encoding="UTF-8" indent="yes"/>

	<xsl:variable name="params" select="/r:cursus/r:param"/>
	<xsl:variable name="classes" select="/r:cursus/r:class"/>

	<xsl:template match="/r:cursus">
		<xsl:apply-templates select="document(r:load/@href)/z:cursus"/>
	</xsl:template>

	<xsl:template match="/z:cursus">
		<html>
			<head>
				<title>Scores for <xsl:value-of select="z:series/z:name"/></title>

				<xsl:for-each select="$params[@name='stylesheet']">
					<link rel="stylesheet" type="text/css">
						<xsl:attribute name="href">
							<xsl:value-of select="@value"/>
						</xsl:attribute>
					</link>
				</xsl:for-each>
			</head>
			<body>
				<xsl:for-each select="z:seriesResults">
					<xsl:apply-templates select=".">
						<xsl:with-param name="name" select="/z:cursus/z:series[@xml:id=current()/z:series/@ref]/z:name"/>
						<xsl:with-param name="class">series</xsl:with-param>
						<xsl:with-param name="type">Overall</xsl:with-param>
						<xsl:with-param name="events" select="z:seriesEventResults"/>
						<xsl:with-param name="races" select="z:seriesEventResults/z:eventRaceResults"/>
					</xsl:apply-templates>
				</xsl:for-each>

				<xsl:for-each select="z:eventResults">
					<xsl:apply-templates select=".">
						<xsl:with-param name="name" select="/z:cursus/z:series/z:events/z:event[@xml:id=current()/z:event/@ref]/z:name"/>
						<xsl:with-param name="class">event</xsl:with-param>
						<xsl:with-param name="type">Event</xsl:with-param>
						<xsl:with-param name="events" select="."/>
						<xsl:with-param name="races" select="z:eventRaceResults"/>
					</xsl:apply-templates>
				</xsl:for-each>

				<xsl:for-each select="z:raceResults">
					<xsl:apply-templates select=".">
						<xsl:with-param name="name" select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=current()/z:race/@ref]/z:name"/>
						<xsl:with-param name="class">race</xsl:with-param>
						<xsl:with-param name="type">Race</xsl:with-param>
						<xsl:with-param name="events" select="/.."/>
						<xsl:with-param name="races" select="."/>
					</xsl:apply-templates>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="z:seriesResults|z:eventResults|z:raceResults">
		<!-- Name of results -->
		<xsl:param name="name"/>
		<!-- Class of results -->
		<xsl:param name="class"/>
		<!-- Type of results -->
		<xsl:param name="type"/>
		<!-- Set of event results -->
		<xsl:param name="events"/>
		<!-- Set of event/race results -->
		<xsl:param name="races"/>

		<!-- Output laps if these are race results -->
		<xsl:variable name="laps" select="count($events) = 0"/>
		<!-- Determine if there are any penalties -->
		<xsl:variable name="penalties" select="sum(z:overallOrder/z:overallScore/@penalties) > 0"/>
		<!-- Determine if there are any penalties -->
		<xsl:variable name="notes" select="($penalties and $class != 'series') or z:overallOrder/z:overallScore/z:penalty"/>

		<h1><xsl:value-of select="$name"/></h1>
		<table border="1">
			<xsl:attribute name="class">results <xsl:value-of select="$class"/></xsl:attribute>
			<thead>
				<tr>
					<th class="type" colspan="2"><xsl:value-of select="$type"/></th>
					<xsl:if test="count($classes) > 0">
						<th class="classes"><xsl:attribute name="colspan"><xsl:value-of select="count($classes)"/></xsl:attribute>Classes</th>
					</xsl:if>
					<td></td>

					<!-- Output all the events -->
					<xsl:for-each select="$events">
						<th class="event">
								<xsl:attribute name="colspan">
									<xsl:value-of select="count(z:eventRaceResults)"/>
								</xsl:attribute>
								<xsl:value-of select="/z:cursus/z:series/z:events/z:event[@xml:id=current()/z:event/@ref]/z:name"/>
						</th>
					</xsl:for-each>

					<!-- Padding on top row -->
					<td class="pad">
						<xsl:attribute name="colspan">
							<xsl:choose>
								<!-- If these are not race results, extra padding is required for the race/laps columns -->
								<xsl:when test="$laps"><xsl:value-of select="count($races) * 2 + number($penalties) + number($notes) + 2"/></xsl:when>
								<xsl:otherwise><xsl:value-of select="@discards + number($penalties) + number($notes) + 2"/></xsl:otherwise>
							</xsl:choose>
						</xsl:attribute>
					</td>
				</tr>
				<tr>
					<th class="pos left">Position</th>
					<th class="pilot name">Name</th>
					<xsl:for-each select="$classes">
						<th class="class"><xsl:value-of select="r:output"/></th>
					</xsl:for-each>
					<th class="pilot num">Race <abbr name="Number">No.</abbr></th>

					<!-- Output all the races -->
					<xsl:for-each select="$races">
						<th class="race"><xsl:value-of select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=current()/z:race/@ref]/z:name"/></th>
						<xsl:if test="$laps">
							<th class="laps">Laps</th>
						</xsl:if>
					</xsl:for-each>

					<!-- Output discards columns if there are any discards -->
					<xsl:if test="not($laps) and @discards > 0">
						<th class="dis"><xsl:attribute name="colspan"><xsl:value-of select="@discards"/></xsl:attribute>Discards</th>
					</xsl:if>

					<!-- Output penalties column if there are any penalties -->
					<xsl:if test="$penalties">
						<th class="pen"><abbr name="Penalties">Pen</abbr></th>
					</xsl:if>

					<th class="pts">Points</th>
					<th class="pos right">Position</th>

					<xsl:if test="$notes">
						<th class="notes"></th>
					</xsl:if>
				</tr>
			</thead>
			<tbody>
				<xsl:for-each select="z:overallOrder/z:overallScore">
						<!-- Keep a reference to the current pilot's classes while looping through $classes -->
						<xsl:variable name="zPilotClasses" select="/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/z:pilot/@ref]/z:class"/>
						<!-- Count the people with the same position and use it to add a "=" -->
						<xsl:variable name="joint" select="count(../z:overallScore[@position=current()/@position]) > 1"/>

						<tr>
							<th class="pos left"><xsl:value-of select="@position"/><xsl:if test="$joint">=</xsl:if></th>
							<td class="pilot name"><xsl:value-of select="/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/z:pilot/@ref]/z:name"/></td>
							<xsl:for-each select="$classes">
								<td class="pilot class">
									<xsl:if test="$zPilotClasses[@ref=/z:cursus/z:series/z:classes/z:class[z:name=current()/r:name]/@xml:id]">*</xsl:if>
								</td>
							</xsl:for-each>
							<td class="pilot num"><xsl:value-of select="/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/z:pilot/@ref]/z:raceNumber/z:organisation"/><xsl:text> </xsl:text><xsl:value-of select="format-number(number(/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/z:pilot/@ref]/z:raceNumber/z:number), '000')"/></td>

							<!-- For each race score for this pilot -->
							<xsl:for-each select="$races/z:raceOrder/z:raceScore/z:pilot[@ref=current()/z:pilot/@ref]/..">
								<td class="race pts">
									<!-- Output the race points with emphasis if simulated -->
									<xsl:choose>
										<xsl:when test="@simulated = 'true'"><em><xsl:value-of select="@points"/></em></xsl:when>
										<xsl:otherwise><xsl:value-of select="@points"/></xsl:otherwise>
									</xsl:choose>
								</td>
								<xsl:if test="$laps">
									<td class="race laps"><xsl:value-of select="@laps"/></td>
								</xsl:if>
							</xsl:for-each>

							<!-- For each discarded race -->
							<xsl:for-each select="z:discards/z:race">
								<!-- Find the results for the race referenced by the discard and then find the points for that pilot -->
								<td class="dis"><xsl:value-of select="../../../..//z:eventRaceResults/z:race[@ref=current()/@ref]/../z:raceOrder/z:raceScore/z:pilot[@ref=current()/../../z:pilot/@ref]/../@points"/></td>
							</xsl:for-each>

							<!-- Output penalties column if there are any penalties -->
							<xsl:if test="$penalties">
								<td class="over pen"><xsl:value-of select="@penalties"/></td>
							</xsl:if>

							<td class="over pts"><xsl:value-of select="@points"/></td>
							<!-- Count the people with the same position and use it to add a "=" -->
							<th class="pos right"><xsl:value-of select="@position"/><xsl:if test="$joint">=</xsl:if></th>

							<xsl:if test="$notes">
								<td class="notes">
									<xsl:variable name="realPenalties" select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=$races/z:race/@ref]/z:attendee/z:pilot[@ref=current()/z:pilot/@ref]/../z:penalty"/>
									<xsl:variable name="simuPenalties" select="z:penalty"/>
									<xsl:if test="($realPenalties and $class != 'series') or $simuPenalties">
										<ul class="pen">
											<xsl:if test="$class != 'series'">
												<xsl:for-each select="$realPenalties">
													<xsl:apply-templates select=".">
														<xsl:with-param name="name" select="current()/../../z:name"/>
													</xsl:apply-templates>
												</xsl:for-each>
											</xsl:if>
											<xsl:for-each select="$simuPenalties">
												<xsl:apply-templates select=".">
													<xsl:with-param name="name"/>
												</xsl:apply-templates>
											</xsl:for-each>
										</ul>
									</xsl:if>
								</td>
							</xsl:if>
						</tr>
					</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="z:penalty">
		<xsl:param name="name"/>
		<xsl:variable name="absvalue" select="@value * (@value >= 0) - @value * (@value &lt; 0)"/>

		<li>
			<xsl:if test="$name != ''"><strong><xsl:value-of select="$name"/></strong>: </xsl:if>
			<xsl:choose>
				<xsl:when test="@type = 'EVENT_NON_ATTENDANCE'">Did not attend <strong><xsl:value-of select="z:reason"/></strong></xsl:when>
				<xsl:otherwise><xsl:value-of select="z:reason"/></xsl:otherwise>
			</xsl:choose>
			<xsl:text> (</xsl:text>
			<xsl:choose>
				<xsl:when test="@type = 'AUTOMATIC'">
					<xsl:value-of select="@value"/> penalt<xsl:choose><xsl:when test="$absvalue = 1">y</xsl:when><xsl:otherwise>s</xsl:otherwise></xsl:choose>
				</xsl:when>
				<xsl:when test="@type = 'FIXED'">
					<xsl:value-of select="@value"/> penalty point<xsl:if test="$absvalue != 1">s</xsl:if>
				</xsl:when>
				<xsl:when test="@type = 'LAPS'">
					<xsl:value-of select="$absvalue"/> lap<xsl:if test="$absvalue != 1">s</xsl:if><xsl:choose><xsl:when test="@value > 0"> added</xsl:when><xsl:otherwise> removed</xsl:otherwise></xsl:choose>
				</xsl:when>
				<xsl:when test="@type = 'EVENT_NON_ATTENDANCE'">
					<xsl:value-of select="@value"/> penalty point<xsl:if test="$absvalue != 1">s</xsl:if>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="@value"/> <xsl:value-of select="@type"/>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:text>)</xsl:text>
		</li>
	</xsl:template>
</xsl:stylesheet>
