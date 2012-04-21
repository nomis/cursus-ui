<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:r="urn:oid:1.3.6.1.4.1.39777.1.0.1.2"
		xmlns:z="urn:oid:1.3.6.1.4.1.39777.1.0.1.1"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="urn:oid:1.3.6.1.4.1.39777.1.0.1.1 https://xsd.s85.org/urn/oid/1.3.6.1.4.1.39777.1.0.1.1
			urn:oid:1.3.6.1.4.1.39777.1.0.1.2 https://xsd.s85.org/urn/oid/1.3.6.1.4.1.39777.1.0.1.2"
		version="1.0" xml:lang="en">

	<xsl:output method="html" version="5.0" encoding="UTF-8" indent="yes"/>

	<xsl:variable name="flags" select="/r:cursus/r:flag"/>
	<xsl:variable name="classes" select="/r:cursus/r:class"/>

	<xsl:template match="z:seriesResults" mode="r:name"><xsl:value-of select="/z:cursus/z:series[@xml:id=current()/@series]/z:name"/></xsl:template>
	<xsl:template match="z:eventResults" mode="r:name"><xsl:value-of select="/z:cursus/z:series/z:events/z:event[@xml:id=current()/@event]/z:name"/></xsl:template>
	<xsl:template match="z:raceResults" mode="r:name"><xsl:value-of select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=current()/@race]/z:name"/></xsl:template>

	<xsl:template match="z:seriesResults" mode="r:desc"><xsl:value-of select="/z:cursus/z:series[@xml:id=current()/@series]/z:description"/></xsl:template>
	<xsl:template match="z:eventResults" mode="r:desc"><xsl:value-of select="/z:cursus/z:series/z:events/z:event[@xml:id=current()/@event]/z:description"/></xsl:template>
	<xsl:template match="z:raceResults" mode="r:desc"><xsl:value-of select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=current()/@race]/z:description"/></xsl:template>

	<xsl:template match="z:seriesResults" mode="r:class">series</xsl:template>
	<xsl:template match="z:eventResults" mode="r:class">event</xsl:template>
	<xsl:template match="z:raceResults" mode="r:class">race</xsl:template>

	<xsl:template match="z:seriesResults" mode="r:type">Overall</xsl:template>
	<xsl:template match="z:eventResults" mode="r:type">Event</xsl:template>
	<xsl:template match="z:raceResults" mode="r:type">Race</xsl:template>

	<xsl:template match="z:seriesResults" mode="r:index">series<xsl:value-of select="count(./preceding-sibling::z:seriesResults)+1"/></xsl:template>
	<xsl:template match="z:eventResults" mode="r:index">event<xsl:value-of select="count(./preceding-sibling::z:eventResults)+1"/></xsl:template>
	<xsl:template match="z:raceResults" mode="r:index">race<xsl:value-of select="count(./preceding-sibling::z:raceResults)+1"/></xsl:template>

	<xsl:template match="z:event|z:race" mode="r:th">
		<span>
			<xsl:if test="z:description != ''">
				<xsl:attribute name="title">
					<xsl:value-of select="z:description"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:value-of select="z:name"/>
		</span>
	</xsl:template>

	<xsl:template match="z:event|z:race" mode="r:penalty">
		<strong>
			<xsl:if test="z:description != ''">
				<xsl:attribute name="title">
					<xsl:value-of select="z:description"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:value-of select="z:name"/>
		</strong>
	</xsl:template>

	<xsl:template match="z:seriesResults" mode="r:body">
		<xsl:apply-templates select="." mode="r:internal">
			<xsl:with-param name="name"><xsl:apply-templates select="." mode="r:name"/></xsl:with-param>
			<xsl:with-param name="desc"><xsl:apply-templates select="." mode="r:desc"/></xsl:with-param>
			<xsl:with-param name="class">series</xsl:with-param>
			<xsl:with-param name="type"><xsl:apply-templates select="." mode="r:type"/></xsl:with-param>
			<xsl:with-param name="events" select="z:seriesEventResults"/>
			<xsl:with-param name="races" select="z:seriesEventResults/z:eventRaceResults"/>
			<xsl:with-param name="parent"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="z:eventResults" mode="r:body">
		<xsl:apply-templates select="." mode="r:internal">
			<xsl:with-param name="name"><xsl:apply-templates select="." mode="r:name"/></xsl:with-param>
			<xsl:with-param name="desc"><xsl:apply-templates select="." mode="r:desc"/></xsl:with-param>
			<xsl:with-param name="class">event</xsl:with-param>
			<xsl:with-param name="type"><xsl:apply-templates select="." mode="r:type"/></xsl:with-param>
			<xsl:with-param name="events" select="."/>
			<xsl:with-param name="races" select="z:eventRaceResults"/>
			<xsl:with-param name="parent"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="z:raceResults" mode="r:body">
		<xsl:variable name="parent" select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=current()/@race]/../.."/>
		<xsl:apply-templates select="." mode="r:internal">
			<xsl:with-param name="name"><xsl:apply-templates select="." mode="r:name"/></xsl:with-param>
			<xsl:with-param name="desc"><xsl:apply-templates select="." mode="r:desc"/></xsl:with-param>
			<xsl:with-param name="class">race</xsl:with-param>
			<xsl:with-param name="type"><xsl:apply-templates select="." mode="r:type"/></xsl:with-param>
			<xsl:with-param name="events" select="/.."/>
			<xsl:with-param name="races" select="."/>
			<xsl:with-param name="parent" select="$parent"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="z:seriesResults|z:eventResults|z:raceResults" mode="r:internal">
		<!-- Name of results -->
		<xsl:param name="name"/>
		<!-- Description of results -->
		<xsl:param name="desc"/>
		<!-- Class of results -->
		<xsl:param name="class"/>
		<!-- Type of results -->
		<xsl:param name="type"/>
		<!-- Set of event results -->
		<xsl:param name="events"/>
		<!-- Set of event/race results -->
		<xsl:param name="races"/>
		<!-- Parent of these results -->
		<xsl:param name="parent"/>

		<!-- Output laps if these are race results -->
		<xsl:variable name="laps" select="count($events) = 0"/>
		<!-- Determine if there are any penalties -->
		<xsl:variable name="penalties" select="sum(z:overallOrder/z:overallScore/@penalties) > 0"/>
		<!-- Show did not participate column -->
		<xsl:variable name="dnp" select="$penalties and $flags[@name='show-dnp'] and $class = 'series'"/>
		<!-- Determine if there are any penalties -->
		<xsl:variable name="notes" select="($penalties and $class != 'series') or (not($dnp) and z:overallOrder/z:overallScore/z:penalty) or ($dnp and z:overallOrder/z:overallScore/z:penalty[@type != 'EVENT_NON_ATTENDANCE'])"/>

		<h1><xsl:value-of select="$name"/></h1>
		<table>
			<xsl:attribute name="class">results <xsl:value-of select="$class"/></xsl:attribute>
			<thead>
				<tr>
					<th class="type" colspan="2"><xsl:value-of select="$type"/></th>
					<xsl:if test="$classes">
						<th class="classes"><xsl:attribute name="colspan"><xsl:value-of select="count($classes)"/></xsl:attribute>Classes</th>
					</xsl:if>
					<td></td>

					<!-- Output all the events -->
					<xsl:for-each select="$events">
						<th class="event">
								<xsl:attribute name="colspan">
									<xsl:value-of select="count(z:eventRaceResults)"/>
								</xsl:attribute>
								<xsl:apply-templates select="/z:cursus/z:series/z:events/z:event[@xml:id=current()/@event]" mode="r:th"/>
						</th>
					</xsl:for-each>

					<!-- Padding on top row -->
					<th>
						<xsl:attribute name="class">
							<xsl:choose>
								<xsl:when test="$parent">parent</xsl:when>
								<xsl:otherwise>pad</xsl:otherwise>
							</xsl:choose>
						</xsl:attribute>
						<xsl:attribute name="colspan">
							<xsl:choose>
								<!-- If these are not race results, extra padding is required for the race/laps columns -->
								<xsl:when test="$laps"><xsl:value-of select="count($races) * 2 + number($penalties) + number($dnp) + number($notes) + 2"/></xsl:when>
								<xsl:otherwise><xsl:value-of select="@discards + number($penalties) + number($dnp) + number($notes) + 2"/></xsl:otherwise>
							</xsl:choose>
						</xsl:attribute>
						<xsl:if test="$parent">
							<xsl:apply-templates select="$parent" mode="r:th"/>
						</xsl:if>
					</th>
				</tr>
				<tr>
					<th class="pos left">Position</th>
					<th class="pilot name">Name</th>
					<xsl:for-each select="$classes">
						<th class="class"><xsl:value-of select="r:output"/></th>
					</xsl:for-each>
					<th class="pilot num">Race <abbr title="Number">No.</abbr></th>

					<!-- Output all the races -->
					<xsl:for-each select="$races">
						<th class="race">
							<xsl:apply-templates select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=current()/@race]" mode="r:th"/>
						</th>
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
						<th class="pen"><abbr title="Penalties">Pen</abbr></th>
						<xsl:if test="$dnp">
							<th class="dnp"><abbr title="Did not participate">DNP</abbr></th>
						</xsl:if>
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
						<xsl:variable name="zPilotClasses" select="/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/@pilot]/z:member"/>
						<!-- Count the people with the same position and use it to add a "=" -->
						<xsl:variable name="joint" select="count(../z:overallScore[@position=current()/@position]) > 1"/>

						<tr>
							<th class="pos left"><xsl:value-of select="@position"/><xsl:if test="$joint">=</xsl:if></th>
							<td class="pilot name"><xsl:value-of select="/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/@pilot]/z:name"/></td>
							<xsl:for-each select="$classes">
								<td class="pilot class">
									<xsl:if test="$zPilotClasses[@class=/z:cursus/z:series/z:classes/z:class[z:name=current()/r:name]/@xml:id]">*</xsl:if>
								</td>
							</xsl:for-each>
							<td class="pilot num"><xsl:value-of select="/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/@pilot]/z:raceNumber/z:organisation"/><xsl:text> </xsl:text><xsl:value-of select="format-number(number(/z:cursus/z:series/z:pilots/z:pilot[@xml:id=current()/@pilot]/z:raceNumber/z:number), '000')"/></td>

							<!-- For each race score for this pilot -->
							<xsl:for-each select="$races/z:raceOrder/z:raceScore[@pilot=current()/@pilot]">
								<td>
									<xsl:attribute name="class" xml:space="preserve">race pts <xsl:if test="@simulated = 'true'">sim</xsl:if> <xsl:if test="@discarded = 'true'">dis</xsl:if></xsl:attribute>
									<xsl:value-of select="@points"/>
								</td>
								<xsl:if test="$laps">
									<td class="race laps"><xsl:value-of select="@laps"/></td>
								</xsl:if>
							</xsl:for-each>

							<!-- For each discarded race -->
							<xsl:for-each select="z:discard">
								<!-- Find the results for the race referenced by the discard and then find the points for that pilot -->
								<td class="dis"><xsl:value-of select="../../..//z:eventRaceResults[@race=current()/@race]/z:raceOrder/z:raceScore[@pilot=current()/../@pilot]/@points"/></td>
							</xsl:for-each>

							<!-- Output penalties column if there are any penalties -->
							<xsl:if test="$penalties">
								<xsl:choose>
									<xsl:when test="$dnp">
										<!-- Count of DNP penalties -->
										<xsl:variable name="dnpCount" select="sum(z:penalty[@type = 'EVENT_NON_ATTENDANCE']/@value)"/>

										<td class="over pen"><xsl:value-of select="@penalties - $dnpCount"/></td>
										<td class="over dnp"><xsl:value-of select="$dnpCount"/></td>
									</xsl:when>
									<xsl:otherwise><td class="over pen"><xsl:value-of select="@penalties"/></td></xsl:otherwise>
								</xsl:choose>
							</xsl:if>

							<td class="over pts"><xsl:value-of select="@points"/></td>
							<!-- Count the people with the same position and use it to add a "=" -->
							<th class="pos right"><xsl:value-of select="@position"/><xsl:if test="$joint">=</xsl:if></th>

							<xsl:if test="$notes">
								<td class="notes">
									<xsl:choose>
										<xsl:when test="$dnp">
											<xsl:variable name="simuPenalties" select="z:penalty[@type != 'EVENT_NON_ATTENDANCE']"/>
											<!-- If DNP then this is a series, so we don't show real penalties -->
											<xsl:if test="$simuPenalties">
												<ul class="pen">
													<xsl:for-each select="$simuPenalties">
														<xsl:apply-templates select="." mode="r:internal">
															<xsl:with-param name="name"/>
														</xsl:apply-templates>
													</xsl:for-each>
												</ul>
											</xsl:if>
										</xsl:when>
										<xsl:otherwise>
											<xsl:variable name="realPenalties" select="/z:cursus/z:series/z:events/z:event/z:races/z:race[@xml:id=$races/@race]/z:raceAttendee[@pilot=current()/@pilot]/z:penalty"/>
											<xsl:variable name="simuPenalties" select="z:penalty"/>
											<xsl:if test="($realPenalties and $class != 'series') or $simuPenalties">
												<ul class="pen">
													<xsl:if test="$class != 'series'">
														<xsl:for-each select="$realPenalties">
															<xsl:apply-templates select="." mode="r:internal">
																<xsl:with-param name="race" select="current()/../.."/>
															</xsl:apply-templates>
														</xsl:for-each>
													</xsl:if>
													<xsl:for-each select="$simuPenalties">
														<xsl:apply-templates select="." mode="r:internal">
															<xsl:with-param name="race"/>
														</xsl:apply-templates>
													</xsl:for-each>
												</ul>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>
								</td>
							</xsl:if>
						</tr>
					</xsl:for-each>
			</tbody>
			<xsl:if test="$desc != ''">
				<caption><xsl:value-of select="$desc"/></caption>
			</xsl:if>
		</table>
	</xsl:template>

	<xsl:template match="z:penalty" mode="r:internal">
		<xsl:param name="race"/>
		<xsl:variable name="absvalue" select="@value * (@value >= 0) - @value * (@value &lt; 0)"/>

		<li>
			<xsl:if test="$race"><xsl:apply-templates select="$race" mode="r:penalty"/>: </xsl:if>
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
					<xsl:value-of select="@value"/> point<xsl:if test="$absvalue != 1">s</xsl:if>
				</xsl:when>
				<xsl:when test="@type = 'LAPS'">
					<xsl:value-of select="$absvalue"/> lap<xsl:if test="$absvalue != 1">s</xsl:if><xsl:choose><xsl:when test="@value > 0"> added</xsl:when><xsl:otherwise> removed</xsl:otherwise></xsl:choose>
				</xsl:when>
				<xsl:when test="@type = 'EVENT_NON_ATTENDANCE'">
					<xsl:value-of select="@value"/> point<xsl:if test="$absvalue != 1">s</xsl:if>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="@value"/> <xsl:value-of select="@type"/>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:text>)</xsl:text>
		</li>
	</xsl:template>
</xsl:stylesheet>
