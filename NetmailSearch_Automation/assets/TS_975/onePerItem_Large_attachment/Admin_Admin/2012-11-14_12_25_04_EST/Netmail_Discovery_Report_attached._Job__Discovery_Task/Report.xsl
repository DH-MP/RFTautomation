<?xml version="1.0" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/Report">
		<HTML>
			<HEAD>
				<TITLE>Messaging Architects Discovery Report</TITLE>
			</HEAD>
			<BODY topmargin="0" leftmargin="0" marginwidth="0" marginheight="0" link="#004400" vlink="#004400"
				alink="#004400">
				<div id="menu" style="position:absolute; top:5; left:10;">
					<table border="0" style="color:#3F5092; font-size:11px;text-align:left; font-family:Arial,Verdana,Times New Roman;"
						cellpadding="2" cellspacing="1" width="1100" bgcolor="#a9b7db">
						<tr/>
						<tr>
							<td bgcolor="#F3F3F3">
								<b>Discovery started at:</b>
								<A>									
									<xsl:attribute name="name">
										<xsl:text>#top</xsl:text>
									</xsl:attribute>
								</A>
							</td>
							<td bgcolor="#F3F3F3">
								<b>
									<xsl:text>&#160;&#160;</xsl:text>
									<xsl:value-of select="/Report/@date"/>
								</b>
							</td>
						</tr>
						<tr>
							<td bgcolor="#F3F3F3">
								<b>Users Processed:</b>								
							</td>
							<td bgcolor="#F3F3F3">
								<b>
									<xsl:text>&#160;&#160;</xsl:text>
									<xsl:value-of select="count(/Report/User)"/>
								</b>
							</td>
						</tr>
						<tr>
							<td bgcolor="#F3F3F3">
								<b>Old messages threshold (days):</b>
							</td>
							<td bgcolor="#F3F3F3">
								<b>
									<xsl:text>&#160;&#160;</xsl:text>
									<xsl:value-of select="/Report/Misc/Threshold"/>
								</b>
							</td>
						</tr>
						<tr/>
						<tr>
							<td bgcolor="#F3F3F3" align="center" colspan="2">
								<b>System</b>
							</td>
						</tr>
						<tr/>
						<tr>
							<td bgcolor="#F3F3F3" colspan="2">
								<b>
									<xsl:text>System Totals</xsl:text>
								</b>
							</td>
						</tr>
						<xsl:variable name="sort" select="/Report/Misc/Sort"/>
						<!-- Totals -->
						<xsl:for-each select="/Report/Totals">
							<xsl:for-each select="./*">
								<xsl:choose>
									<xsl:when test="contains(@name,'Size')">
										<tr>
											<td bgcolor="#F3F3F3">
												<xsl:text>&#160;&#160;</xsl:text><xsl:value-of select="@name"/>
											</td>
											<xsl:variable name="size" select="."/>
											<td bgcolor="#F3F3F3">
												<xsl:choose>
													<xsl:when test="$size!=0">
														<xsl:text>&#160;&#160;</xsl:text>
														<xsl:value-of select="$size"/>
														<xsl:text>&#160;Kb</xsl:text>
													</xsl:when>
													<xsl:otherwise>
														<xsl:text>&#160;&#160;</xsl:text>
														<xsl:text>0&#160;Kb</xsl:text>
													</xsl:otherwise>
												</xsl:choose>
											</td>
										</tr>
									</xsl:when>
									<xsl:otherwise>
										<tr>
											<td bgcolor="#F3F3F3">
												<xsl:text>&#160;&#160;</xsl:text>
												<xsl:value-of select="@name"/>
											</td>
											<td bgcolor="#F3F3F3">
												<xsl:text>&#160;&#160;</xsl:text>
												<xsl:value-of select="."/>
											</td>
										</tr>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:for-each>
						<tr>
							<td bgcolor="#F3F3F3" colspan="2">
								<b>
									<xsl:text>System Load</xsl:text>
								</b>
							</td>
						</tr>
						<!-- System Load -->
						<xsl:for-each select="/Report/SysLoad">
							<xsl:for-each select="./*">
								<xsl:choose>
									<xsl:when test="contains(@name,'Size')">
										<tr>
											<td bgcolor="#F3F3F3">
												<xsl:text>&#160;&#160;</xsl:text>
												<xsl:value-of select="@name"/>
											</td>
											<xsl:variable name="size" select="."/>
											<td bgcolor="#F3F3F3">
												<xsl:choose>
													<xsl:when test="$size!=0">
														<xsl:text>&#160;&#160;</xsl:text>
														<xsl:value-of select="$size"/>														
														<xsl:text>&#160;Kb</xsl:text>
													</xsl:when>
													<xsl:otherwise>
														<xsl:text>&#160;&#160;</xsl:text>
														<xsl:text>0&#160;Kb</xsl:text>
													</xsl:otherwise>
												</xsl:choose>
											</td>
										</tr>
									</xsl:when>
									<xsl:otherwise>
										<tr>
											<td bgcolor="#F3F3F3">
												<xsl:text>&#160;&#160;</xsl:text>
												<xsl:value-of select="@name"/>
											</td>
											<td bgcolor="#F3F3F3" align="left">
												<xsl:text>&#160;&#160;</xsl:text>
												<xsl:value-of select="."/>
											</td>
										</tr>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:for-each>
						<!-- USERS -->
						<tr/>
					</table>					
					<table border="0" style="color:#3F5092; font-size:11px;text-align:left; font-family:Arial,Verdana,Times New Roman;"
						cellpadding="3" cellspacing="1" width="1100" bgcolor="#a9b7db">
						<tr>
							<td bgcolor="#F3F3F3" align="center" colspan="18">
								<b>Users</b>
							</td>
						</tr>
						<tr/>
						<!-- Headers - Users table -->
						<tr valign="top" align="center">
							<td bgcolor="#E0E0F0" rowspan="1">Name<br/>Email</td>
							<td bgcolor="#E0E0F0" nowrap="">Msg #</td>
							<td bgcolor="#E0E0F0" nowrap="">Att #</td>
							<td bgcolor="#E0E0F0" nowrap="">Msg Size<br/>(Kb)</td>
							<td bgcolor="#E0E0F0" nowrap="">Att Size<br/>(Kb)</td>
							<td bgcolor="#E0E0F0" nowrap="">Total Size<br/>(Kb)</td>
							<td bgcolor="#E0E0F0" nowrap="">Old Msg #</td>
							<td bgcolor="#E0E0F0" nowrap="">Old Att #</td>
							<td bgcolor="#E0E0F0" nowrap="">Old<br/>Msg Size<br/>(Kb)</td>
							<td bgcolor="#E0E0F0" nowrap="">Old<br/>Att Size<br/>(Kb)</td>
							<td bgcolor="#E0E0F0" nowrap="">Total Size <br/>Old Items<br/>(Kb)</td>
							<td bgcolor="#E0E0F0" nowrap="">Old/Total <br/>Ratio (%)</td>
							<td bgcolor="#E0E0F0" nowrap="">Forbidden <br/>Att. #</td>
							<td bgcolor="#E0E0F0" nowrap="">Forbidden <br/>Att. Size<br/>(Kb)</td>
							<td bgcolor="#E0E0F0" nowrap="">Last Sent</td>
							<td bgcolor="#E0E0F0" nowrap="">Last Rcvd</td>
							<td bgcolor="#E0E0F0" colspan="2">Archive Path</td>
						</tr>
						<tr/>
						<tr/>
						<!-- Select Sorting Order - Users (first table) -->
						<xsl:choose>
							<xsl:when test="/Report/Misc/Sort=('./MC')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./AC')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./AC" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./MS')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MS" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./AS')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./MCO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./ACO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./MSO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./ASO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./LMS')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./LMS" order="descending" data-type="number"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('@id')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="@id" order="ascending" data-type="text"/>
									<xsl:call-template name="table1"/>
								</xsl:for-each>
							</xsl:when>
						</xsl:choose>
					</table>
					<table border="0" style="color:#3F5092; font-size:11px;text-align:left; font-family:Arial,Verdana,Times New Roman;"
						cellpadding="3" cellspacing="1" width="1100" bgcolor="#a9b7db">
						<tr>
							<td bgcolor="#F3F3F3" align="center" colspan="11">
								<b>Users Details</b>
							</td>
						</tr>
						<tr/>
						<!-- Select Sorting Order - Users Details -->
						<xsl:choose>
							<xsl:when test="/Report/Misc/Sort=('./MC')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./AC')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./AC" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./MS')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MS" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./AS')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./MCO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./ACO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./MSO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./ASO')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./MC" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('./LMS')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="./LMS" order="descending" data-type="number"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="/Report/Misc/Sort=('@id')">
								<xsl:for-each select="/Report/User">
									<xsl:sort select="@id" order="ascending" data-type="text"/>
									<xsl:call-template name="table2"/>
								</xsl:for-each>
							</xsl:when>
						</xsl:choose>
					</table>
				</div>
			</BODY>
		</HTML>
	</xsl:template>
	
	<!-- Start Template 'Users' -->
	<xsl:template name="table1">
		<tr align="center">
			<td bgcolor="#F3F3F3" align="left">
				<A><xsl:attribute name="name">
					<xsl:text>#back</xsl:text>
					<xsl:value-of select="./Email"/>
					</xsl:attribute>
				</A>
				<A><xsl:attribute name="href">
					<xsl:text>#</xsl:text>
					<xsl:value-of select="./Email"/>
					</xsl:attribute>
					<b><xsl:value-of select="@id"/></b>
				</A>
				<br/>
				<xsl:value-of select="./Email"/>
			</td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./MC"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./AC"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./MS"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./AS"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="number(./AS) + number(./MS)"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./MCO"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./ACO"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./MSO"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./ASO"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="number(./ASO) + number(./MSO)"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./OTR"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./BAC"/></td>
			<td bgcolor="#F3F3F3"><xsl:value-of select="./BAS"/></td>
			<td bgcolor="#F3F3F3" nowrap=""><xsl:value-of select="./LMS"/></td>
			<td bgcolor="#F3F3F3" nowrap=""><xsl:value-of select="./LMR"/></td>
			<td bgcolor="#F3F3F3" colspan="2"><xsl:value-of select="./ARC"/></td>
		</tr>
		<tr/>
	</xsl:template>
	
	<!-- Start Template 'Table2' - User details -->
	<xsl:template name="table2">
		<tr align="center" valign="top">
			<!-- Headers Row - for each User-->
			<td bgcolor="#F3F3F3" align="left">
				<A>
					<xsl:attribute name="name">
						<xsl:value-of select="./Email"/>
					</xsl:attribute>
				</A>
				<A>
					<xsl:attribute name="href">
						<xsl:text>#back</xsl:text>
						<xsl:value-of select="./Email"/>
					</xsl:attribute>
					<b>
						<xsl:value-of select="@id"/>
					</b>
				</A>
				<xsl:text>&#160;</xsl:text>
				<xsl:text>&#160;</xsl:text>
				<A>
        		<xsl:attribute name="href">
						<xsl:text>#top</xsl:text>
					</xsl:attribute>(top)<br/>        		        		
        	</A>
				<xsl:value-of select="./Email"/>
			</td>
			<td bgcolor="#E0E0F0">Msg #</td>
			<td bgcolor="#E0E0F0">Att #</td>
			<td bgcolor="#E0E0F0">Msg Size<br/>(Kb)</td>
			<td bgcolor="#E0E0F0">Att Size<br/>(Kb)</td>
			<td bgcolor="#E0E0F0">Total Size<br/>(Kb)</td>
			<td bgcolor="#E0E0F0">Old Msg #</td>
			<td bgcolor="#E0E0F0">Old Att #</td>
			<td bgcolor="#E0E0F0">Old Msg Size<br/>(Kb)</td>
			<td bgcolor="#E0E0F0">Old Att Size<br/>(Kb)</td>
			<td bgcolor="#E0E0F0">Total Size - Old<br/>(Kb)</td>
		</tr>
		<!-- Incoming Items -->
		<tr align="center" bgcolor="#F9F9F9">
			<td align="left">
				<b>Incoming</b><br/>
				<xsl:text>&#160;&#160;</xsl:text>Mail		<br/>
				<xsl:text>&#160;&#160;</xsl:text>Appointment	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Task		<br/>
				<xsl:text>&#160;&#160;</xsl:text>Note		<br/>
				<xsl:text>&#160;&#160;</xsl:text>Other
			</td>
			<td>
				<xsl:value-of select="./IMC"/><br/>
				<xsl:value-of select="./IMMC"/><br/>
				<xsl:value-of select="./IAMC"/><br/>
				<xsl:value-of select="./ITMC"/><br/>
				<xsl:value-of select="./INMC"/><br/>
				<xsl:value-of select="./IOMC"/>
			</td>
			<td>
				<xsl:value-of select="./IAC"/><br/>
				<xsl:value-of select="./IMAC"/><br/>
				<xsl:value-of select="./IAAC"/><br/>
				<xsl:value-of select="./ITAC"/><br/>
				<xsl:value-of select="./INAC"/><br/>
				<xsl:value-of select="./IOAC"/>
			</td>
			<td>
				<xsl:value-of select="./IMS"/><br/>
				<xsl:value-of select="./IMMS"/><br/>
				<xsl:value-of select="./IAMS"/><br/>
				<xsl:value-of select="./ITMS"/><br/>
				<xsl:value-of select="./INMS"/><br/>
				<xsl:value-of select="./IOMS"/>
			</td>
			<td>
				<xsl:value-of select="./IAS"/><br/>
				<xsl:value-of select="./IMAS"/><br/>
				<xsl:value-of select="./IAAS"/><br/>
				<xsl:value-of select="./ITAS"/><br/>
				<xsl:value-of select="./INAS"/><br/>
				<xsl:value-of select="./IOAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./IAS) + number(./IMS)"/><br/>
				<xsl:value-of select="number(./IMAS) + number(./IMMS)"/><br/>
				<xsl:value-of select="number(./IAAS) + number(./IAMS)"/><br/>
				<xsl:value-of select="number(./ITAS) + number(./ITMS)"/><br/>
				<xsl:value-of select="number(./INAS) + number(./INMS)"/><br/>
				<xsl:value-of select="number(./IOAS) + number(./IOMS)"/>
			</td>
			<td>
				<xsl:value-of select="./OIMC"/><br/>
				<xsl:value-of select="./OIMMC"/><br/>
				<xsl:value-of select="./OIAMC"/><br/>
				<xsl:value-of select="./OITMC"/><br/>
				<xsl:value-of select="./OINMC"/><br/>
				<xsl:value-of select="./OIOMC"/>
			</td>
			<td>
				<xsl:value-of select="./OIAC"/><br/>
				<xsl:value-of select="./OIMAC"/><br/>
				<xsl:value-of select="./OIAAC"/><br/>
				<xsl:value-of select="./OITAC"/><br/>
				<xsl:value-of select="./OINAC"/><br/>
				<xsl:value-of select="./OIOAC"/>
			</td>
			<td>
				<xsl:value-of select="./OIMS"/><br/>
				<xsl:value-of select="./OIMMS"/><br/>
				<xsl:value-of select="./OIAMS"/><br/>
				<xsl:value-of select="./OITMS"/><br/>
				<xsl:value-of select="./OINMS"/><br/>
				<xsl:value-of select="./OIOMS"/>
			</td>
			<td>
				<xsl:value-of select="./OIAS"/><br/>
				<xsl:value-of select="./OIMAS"/><br/>
				<xsl:value-of select="./OIAAS"/><br/>
				<xsl:value-of select="./OITAS"/><br/>
				<xsl:value-of select="./OINAS"/><br/>
				<xsl:value-of select="./OIOAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./OIAS) + number(./OIMS)"/><br/>
				<xsl:value-of select="number(./OIMAS) + number(./OIMMS)"/><br/>
				<xsl:value-of select="number(./OIAAS) + number(./OIAMS)"/><br/>
				<xsl:value-of select="number(./OITAS) + number(./OITMS)"/><br/>
				<xsl:value-of select="number(./OINAS) + number(./OINMS)"/><br/>
				<xsl:value-of select="number(./OIOAS) + number(./OIOMS)"/>
			</td>
		</tr>
		<!-- Outgoing Items -->
		<tr align="center" bgcolor="#FDFDFD">
			<td align="left">
				<b>Outgoing</b><br/>
				<xsl:text>&#160;&#160;</xsl:text>Mail	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Appointment	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Task	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Note	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Other	<br/>
			</td>
			<td>
				<xsl:value-of select="./OMC"/><br/>
				<xsl:value-of select="./OMMC"/><br/>
				<xsl:value-of select="./OAMC"/><br/>
				<xsl:value-of select="./OTMC"/><br/>
				<xsl:value-of select="./ONMC"/><br/>
				<xsl:value-of select="./OUMC"/>
			</td>
			<td>
				<xsl:value-of select="./OAC"/><br/>
				<xsl:value-of select="./OMAC"/><br/>
				<xsl:value-of select="./OAAC"/><br/>
				<xsl:value-of select="./OTAC"/><br/>
				<xsl:value-of select="./ONAC"/><br/>
				<xsl:value-of select="./OUAC"/>
			</td>
			<td>
				<xsl:value-of select="./OMS"/><br/>
				<xsl:value-of select="./OMMS"/><br/>
				<xsl:value-of select="./OAMS"/><br/>
				<xsl:value-of select="./OTMS"/><br/>
				<xsl:value-of select="./ONMS"/><br/>
				<xsl:value-of select="./OUMS"/>
			</td>
			<td>
				<xsl:value-of select="./OAS"/><br/>
				<xsl:value-of select="./OMAS"/><br/>
				<xsl:value-of select="./OAAS"/><br/>
				<xsl:value-of select="./OTAS"/><br/>
				<xsl:value-of select="./ONAS"/><br/>
				<xsl:value-of select="./OUAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./OAS) + number(./OMS)"/><br/>
				<xsl:value-of select="number(./OMAS) + number(./OMMS)"/><br/>
				<xsl:value-of select="number(./OAAS) + number(./OAMS)"/><br/>
				<xsl:value-of select="number(./OTAS) + number(./OTMS)"/><br/>
				<xsl:value-of select="number(./ONAS) + number(./ONMS)"/><br/>
				<xsl:value-of select="number(./OUAS) + number(./OUMS)"/>
			</td>
			<td>
				<xsl:value-of select="./OOMC"/><br/>
				<xsl:value-of select="./OOMMC"/><br/>
				<xsl:value-of select="./OOAMC"/><br/>
				<xsl:value-of select="./OOTMC"/><br/>
				<xsl:value-of select="./OONMC"/><br/>
				<xsl:value-of select="./OOOMC"/>
			</td>
			<td>
				<xsl:value-of select="./OOAC"/><br/>
				<xsl:value-of select="./OOMAC"/><br/>
				<xsl:value-of select="./OOAAC"/><br/>
				<xsl:value-of select="./OOTAC"/><br/>
				<xsl:value-of select="./OONAC"/><br/>
				<xsl:value-of select="./OOOAC"/>
			</td>
			<td>
				<xsl:value-of select="./OOMS"/><br/>
				<xsl:value-of select="./OOMMS"/><br/>
				<xsl:value-of select="./OOAMS"/><br/>
				<xsl:value-of select="./OOTMS"/><br/>
				<xsl:value-of select="./OONMS"/><br/>
				<xsl:value-of select="./OOOMS"/>
			</td>
			<td>
				<xsl:value-of select="./OOAS"/><br/>
				<xsl:value-of select="./OOMAS"/><br/>
				<xsl:value-of select="./OOAAS"/><br/>
				<xsl:value-of select="./OOTAS"/><br/>
				<xsl:value-of select="./OONAS"/><br/>
				<xsl:value-of select="./OOOAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./OOAS) + number(./OOMS)"/><br/>
				<xsl:value-of select="number(./OOMAS) + number(./OOMMS)"/><br/>
				<xsl:value-of select="number(./OOAAS) + number(./OOAMS)"/><br/>
				<xsl:value-of select="number(./OOTAS) + number(./OOTMS)"/><br/>
				<xsl:value-of select="number(./OONAS) + number(./OONMS)"/><br/>
				<xsl:value-of select="number(./OOOAS) + number(./OOOMS)"/>
			</td>
		</tr>
		<!-- Trash Items -->
		<tr align="center" bgcolor="#F9F9F9">
			<td align="left">
				<b>Trash</b><br/>
				<xsl:text>&#160;&#160;</xsl:text>Mail	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Appointment	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Task	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Note	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Other	<br/>
			</td>
			<td>
				<xsl:value-of select="./RMC"/><br/>
				<xsl:value-of select="./RMMC"/><br/>
				<xsl:value-of select="./RAMC"/><br/>
				<xsl:value-of select="./RTMC"/><br/>
				<xsl:value-of select="./RNMC"/><br/>
				<xsl:value-of select="./ROMC"/>
			</td>
			<td>
				<xsl:value-of select="./RAC"/><br/>
				<xsl:value-of select="./RMAC"/><br/>
				<xsl:value-of select="./RAAC"/><br/>
				<xsl:value-of select="./RTAC"/><br/>
				<xsl:value-of select="./RNAC"/><br/>
				<xsl:value-of select="./ROAC"/>
			</td>
			<td>
				<xsl:value-of select="./RMS"/><br/>
				<xsl:value-of select="./RMMS"/><br/>
				<xsl:value-of select="./RAMS"/><br/>
				<xsl:value-of select="./RTMS"/><br/>
				<xsl:value-of select="./RNMS"/><br/>
				<xsl:value-of select="./ROMS"/>
			</td>
			<td>
				<xsl:value-of select="./RAS"/><br/>
				<xsl:value-of select="./RMAS"/><br/>
				<xsl:value-of select="./RAAS"/><br/>
				<xsl:value-of select="./RTAS"/><br/>
				<xsl:value-of select="./RNAS"/><br/>
				<xsl:value-of select="./ROAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./RAS) + number(./RMS)"/><br/>
				<xsl:value-of select="number(./RMAS) + number(./RMMS)"/><br/>
				<xsl:value-of select="number(./RAAS) + number(./RAMS)"/><br/>
				<xsl:value-of select="number(./RTAS) + number(./RTMS)"/><br/>
				<xsl:value-of select="number(./RNAS) + number(./RNMS)"/><br/>
				<xsl:value-of select="number(./ROAS) + number(./ROMS)"/>
			</td>
			<td>
				<xsl:value-of select="./ORMC"/><br/>
				<xsl:value-of select="./ORMMC"/><br/>
				<xsl:value-of select="./ORAMC"/><br/>
				<xsl:value-of select="./ORTMC"/><br/>
				<xsl:value-of select="./ORNMC"/><br/>
				<xsl:value-of select="./OROMC"/>
			</td>
			<td>
				<xsl:value-of select="./ORAC"/><br/>
				<xsl:value-of select="./ORMAC"/><br/>
				<xsl:value-of select="./ORAAC"/><br/>
				<xsl:value-of select="./ORTAC"/><br/>
				<xsl:value-of select="./ORNAC"/><br/>
				<xsl:value-of select="./OROAC"/>
			</td>
			<td>
				<xsl:value-of select="./ORMS"/><br/>
				<xsl:value-of select="./ORMMS"/><br/>
				<xsl:value-of select="./ORAMS"/><br/>
				<xsl:value-of select="./ORTMS"/><br/>
				<xsl:value-of select="./ORNMS"/><br/>
				<xsl:value-of select="./OROMS"/>
			</td>
			<td>
				<xsl:value-of select="./ORAS"/><br/>
				<xsl:value-of select="./ORMAS"/><br/>
				<xsl:value-of select="./ORAAS"/><br/>
				<xsl:value-of select="./ORTAS"/><br/>
				<xsl:value-of select="./ORNAS"/><br/>
				<xsl:value-of select="./OROAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./ORAS) + number(./ORMS)"/><br/>
				<xsl:value-of select="number(./ORMAS) + number(./ORMMS)"/><br/>
				<xsl:value-of select="number(./ORAAS) + number(./ORAMS)"/><br/>
				<xsl:value-of select="number(./ORTAS) + number(./ORTMS)"/><br/>
				<xsl:value-of select="number(./ORNAS) + number(./ORNMS)"/><br/>
				<xsl:value-of select="number(./OROAS) + number(./OROMS)"/>
			</td>
		</tr>
		<!-- Draft Items -->
		<tr bgcolor="#FDFDFD" align="center">
			<td align="left">
				<b>Draft</b><br/>
				<xsl:text>&#160;&#160;</xsl:text>Mail	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Appointment	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Task	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Note	<br/>
				<xsl:text>&#160;&#160;</xsl:text>Other	<br/>
			</td>
			<td>
				<xsl:value-of select="./DMC"/><br/>
				<xsl:value-of select="./DMMC"/><br/>
				<xsl:value-of select="./DAMC"/><br/>
				<xsl:value-of select="./DTMC"/><br/>
				<xsl:value-of select="./DNMC"/><br/>
				<xsl:value-of select="./DOMC"/>
			</td>
			<td>
				<xsl:value-of select="./DAC"/><br/>
				<xsl:value-of select="./DMAC"/><br/>
				<xsl:value-of select="./DAAC"/><br/>
				<xsl:value-of select="./DTAC"/><br/>
				<xsl:value-of select="./DNAC"/><br/>
				<xsl:value-of select="./DOAC"/>
			</td>
			<td>
				<xsl:value-of select="./DMS"/><br/>
				<xsl:value-of select="./DMMS"/><br/>
				<xsl:value-of select="./DAMS"/><br/>
				<xsl:value-of select="./DTMS"/><br/>
				<xsl:value-of select="./DNMS"/><br/>
				<xsl:value-of select="./DOMS"/>
			</td>
			<td>
				<xsl:value-of select="./DAS"/><br/>
				<xsl:value-of select="./DMAS"/><br/>
				<xsl:value-of select="./DAAS"/><br/>
				<xsl:value-of select="./DTAS"/><br/>
				<xsl:value-of select="./DNAS"/><br/>
				<xsl:value-of select="./DOAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./DAS) + number(./DMS)"/><br/>
				<xsl:value-of select="number(./DMAS) + number(./DMMS)"/><br/>
				<xsl:value-of select="number(./DAAS) + number(./DAMS)"/><br/>
				<xsl:value-of select="number(./DTAS) + number(./DTMS)"/><br/>
				<xsl:value-of select="number(./DNAS) + number(./DNMS)"/><br/>
				<xsl:value-of select="number(./DOAS) + number(./DOMS)"/>
			</td>
			<td>
				<xsl:value-of select="./ODMC"/><br/>
				<xsl:value-of select="./ODMMC"/><br/>
				<xsl:value-of select="./ODAMC"/><br/>
				<xsl:value-of select="./ODTMC"/><br/>
				<xsl:value-of select="./ODNMC"/><br/>
				<xsl:value-of select="./ODOMC"/>
			</td>
			<td>
				<xsl:value-of select="./ODAC"/><br/>
				<xsl:value-of select="./ODMAC"/><br/>
				<xsl:value-of select="./ODAAC"/><br/>
				<xsl:value-of select="./ODTAC"/><br/>
				<xsl:value-of select="./ODNAC"/><br/>
				<xsl:value-of select="./ODOAC"/><br/>
			</td>
			<td>
				<xsl:value-of select="./ODMS"/><br/>
				<xsl:value-of select="./ODMMS"/><br/>
				<xsl:value-of select="./ODAMS"/><br/>
				<xsl:value-of select="./ODTMS"/><br/>
				<xsl:value-of select="./ODNMS"/><br/>
				<xsl:value-of select="./ODOMS"/>
			</td>
			<td>
				<xsl:value-of select="./ODAS"/><br/>
				<xsl:value-of select="./ODMAS"/><br/>
				<xsl:value-of select="./ODAAS"/><br/>
				<xsl:value-of select="./ODTAS"/><br/>
				<xsl:value-of select="./ODNAS"/><br/>
				<xsl:value-of select="./ODOAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./ODAS) + number(./ODMS)"/><br/>
				<xsl:value-of select="number(./ODMAS) + number(./ODMMS)"/><br/>
				<xsl:value-of select="number(./ODAAS) + number(./ODAMS)"/><br/>
				<xsl:value-of select="number(./ODTAS) + number(./ODTMS)"/><br/>
				<xsl:value-of select="number(./ODNAS) + number(./ODNMS)"/><br/>
				<xsl:value-of select="number(./ODOAS) + number(./ODOMS)"/>
			</td>
		</tr>
		<!-- Personal Items -->
		<tr align="center" bgcolor="#F9F9F9">
			<td align="left">
			<b>Personal</b><br/>
			<xsl:text>&#160;&#160;</xsl:text>Mail	<br/>
			<xsl:text>&#160;&#160;</xsl:text>Appointment	<br/>
			<xsl:text>&#160;&#160;</xsl:text>Task	<br/>
			<xsl:text>&#160;&#160;</xsl:text>Note	<br/>
			<xsl:text>&#160;&#160;</xsl:text>Other	<br/>
		</td>
			<td>
				<xsl:value-of select="./PMC"/><br/>
				<xsl:value-of select="./PMMC"/><br/>
				<xsl:value-of select="./PAMC"/><br/>
				<xsl:value-of select="./PTMC"/><br/>
				<xsl:value-of select="./PNMC"/><br/>
				<xsl:value-of select="./POMC"/>
			</td>
			<td>
				<xsl:value-of select="./PAC"/><br/>
				<xsl:value-of select="./PMAC"/><br/>
				<xsl:value-of select="./PAAC"/><br/>
				<xsl:value-of select="./PTAC"/><br/>
				<xsl:value-of select="./PNAC"/><br/>
				<xsl:value-of select="./POAC"/>
			</td>
			<td>
				<xsl:value-of select="./PMS"/><br/>
				<xsl:value-of select="./PMMS"/><br/>
				<xsl:value-of select="./PAMS"/><br/>
				<xsl:value-of select="./PTMS"/><br/>
				<xsl:value-of select="./PNMS"/><br/>
				<xsl:value-of select="./POMS"/>
			</td>
			<td>
				<xsl:value-of select="./PAS"/><br/>
				<xsl:value-of select="./PMAS"/><br/>
				<xsl:value-of select="./PAAS"/><br/>
				<xsl:value-of select="./PTAS"/><br/>
				<xsl:value-of select="./PNAS"/><br/>
				<xsl:value-of select="./POAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./PAS) + number(./PMS)"/><br/>
				<xsl:value-of select="number(./PMAS) + number(./PMMS)"/><br/>
				<xsl:value-of select="number(./PAAS) + number(./PAMS)"/><br/>
				<xsl:value-of select="number(./PTAS) + number(./PTMS)"/><br/>
				<xsl:value-of select="number(./PNAS) + number(./PNMS)"/><br/>
				<xsl:value-of select="number(./POAS) + number(./POMS)"/>
			</td>
			<td>
				<xsl:value-of select="./OPMC"/><br/>
				<xsl:value-of select="./OPMMC"/><br/>
				<xsl:value-of select="./OPAMC"/><br/>
				<xsl:value-of select="./OPTMC"/><br/>
				<xsl:value-of select="./OPNMC"/><br/>
				<xsl:value-of select="./OPOMC"/>
			</td>
			<td>
				<xsl:value-of select="./OPAC"/><br/>
				<xsl:value-of select="./OPMAC"/><br/>
				<xsl:value-of select="./OPAAC"/><br/>
				<xsl:value-of select="./OPTAC"/><br/>
				<xsl:value-of select="./OPNAC"/><br/>
				<xsl:value-of select="./OPOAC"/>
				<br/>
			</td>
			<td>
				<xsl:value-of select="./OPMS"/><br/>
				<xsl:value-of select="./OPMMS"/><br/>
				<xsl:value-of select="./OPAMS"/><br/>
				<xsl:value-of select="./OPTMS"/><br/>
				<xsl:value-of select="./OPNMS"/><br/>
				<xsl:value-of select="./OPOMS"/>
			</td>
			<td>
				<xsl:value-of select="./OPAS"/><br/>
				<xsl:value-of select="./OPMAS"/><br/>
				<xsl:value-of select="./OPAAS"/><br/>
				<xsl:value-of select="./OPTAS"/><br/>
				<xsl:value-of select="./OPNAS"/><br/>
				<xsl:value-of select="./OPOAS"/>
			</td>
			<td>
				<xsl:value-of select="number(./OPAS) + number(./OPMS)"/><br/>
				<xsl:value-of select="number(./OPMAS) + number(./OPMMS)"/><br/>
				<xsl:value-of select="number(./OPAAS) + number(./OPAMS)"/><br/>
				<xsl:value-of select="number(./OPTAS) + number(./OPTMS)"/><br/>
				<xsl:value-of select="number(./OPNAS) + number(./OPNMS)"/><br/>
				<xsl:value-of select="number(./OPOAS) + number(./OPOMS)"/>
			</td>
		</tr>
		<!-- Calculates and displays totals -->
		<tr align="center" bgcolor="#F3F3F3">
			<td align="left"><b>Total</b></td>
			<td><xsl:value-of select="./MC"/></td>
			<td><xsl:value-of select="./AC"/></td>
			<td><xsl:value-of select="./MS"/></td>
			<td><xsl:value-of select="./AS"/></td>
			<td>
				<xsl:value-of select="number(./IAS) + number(./IMS) + number(./OMS) + number(./OAS) + number(./DMS) + number(./DAS) + number(./PMS) + number(./PAS) + number(./RMS) + number(./RAS)"/>
				<br/>
			</td><td><xsl:value-of select="./MCO"/></td>
			<td><xsl:value-of select="./ACO"/></td>
			<td><xsl:value-of select="./MSO"/></td>
			<td><xsl:value-of select="./ASO"/></td>
			<td>
				<xsl:value-of select="number(./OIAS) + number(./OIMS) + number(./OOMS) + number(./OOAS) + number(./ODMS) + number(./ODAS) + number(./OPMS) + number(./OPAS) + number(./ORMS) + number(./ORAS)"/>
				<br/>
			</td>
		</tr>
		<tr/>
		<!-- End template 'Table2' -->
	</xsl:template>
	<!-- Template to show sizes in Kb. Checks if value is greater than 1 -->
	<xsl:template name="showsize">
		<xsl:param name="value"/>
		<xsl:choose>
			<xsl:when test="number($value) &gt;  number(1)">
				<xsl:value-of select="$value"/>
				<xsl:text>&#160;Kb</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>&lt;1&#160;Kb</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
