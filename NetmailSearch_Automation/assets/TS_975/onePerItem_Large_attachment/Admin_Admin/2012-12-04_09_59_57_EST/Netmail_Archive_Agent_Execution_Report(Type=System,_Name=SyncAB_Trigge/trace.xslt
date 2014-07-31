<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:msxml="urn:schemas-microsoft-com:xslt">

  <xsl:key name="account-key" match="Table" use="account"/>
  <xsl:variable name="accounts" select="/NewDataSet/Table[generate-id(.)=generate-id(key('account-key', account))]/account"/>
  <!--
  //
  //  get displayname for account
  //  takes displayname@userid.po.domain and strips
  //  off everything after the @ symbol.
  //
  -->
  <xsl:template name="getDisplayName">
    <xsl:param name="account" select="."/>
    <xsl:variable name="displayname">
      <xsl:choose>
        <xsl:when test="contains($account, '@')=true()">
          <xsl:copy-of select="substring-before($account, '@')"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:copy-of select="$account"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:copy-of select="$displayname"/>
  </xsl:template>  
  <!--
  //
  //  print column along the left 
  //  to choose account to view
  //  
  -->
  <xsl:template name="printTableOfContents">
    <xsl:param name="accounts" select="."/>    
    <div class="left">
      <table>
        <xsl:for-each select="$accounts">
          <xsl:sort select="."/>
          <xsl:variable name="account" select="."/>
          <xsl:variable name="displayname">
            <xsl:call-template name="getDisplayName">
              <xsl:with-param name="account" select="$account"/>
            </xsl:call-template>
          </xsl:variable>
          <tr class="tab">
            <td>
              <a href="#{$account}">
                <xsl:value-of select="$displayname"/>
              </a>
            </td>
          </tr>
        </xsl:for-each>
      </table>
    </div>
  </xsl:template>
  <!--
  //
  //  print account summary
  //
  -->
  <xsl:template name="printAccount">
    <xsl:param name="account" select="."/>
    <xsl:param name="records" select="."/>
    <xsl:variable name="record" select="$records[1]"/>
    <table>
      <xsl:call-template name="printHeader">
        <xsl:with-param name="account" select="$account"/>
        <xsl:with-param name="record" select="$record"/>
      </xsl:call-template>
      <xsl:call-template name="printTrace">
        <xsl:with-param name="records" select="$records"/>
      </xsl:call-template>
    </table>
    <p></p>
  </xsl:template>
  <!--
  //
  //  print the header information for this account               
  //
  -->
  <xsl:template name="printHeader">        
    <xsl:param name="account" select="."/>
    <xsl:param name="record" select="."/>
    <xsl:variable name="displayname">
      <xsl:call-template name="getDisplayName">
        <xsl:with-param name="account" select="$account"/>
      </xsl:call-template>
    </xsl:variable>
    <caption>
      <a name="{$account}">
        <h1>
          <xsl:value-of select="$displayname"/> - Trace Summary
        </h1>
      </a>
      <a href="#Top">Home</a>
    </caption>
    <tr>
      <th>
        <small>Account: </small>
      </th>
      <td colspan="2">
        <small>
          <xsl:value-of select="$record/account"/>
        </small>
      </td>
    </tr>
    <tr>
      <th>
        <small>Trigger: </small>
      </th>
      <td colspan="2">
        <small>
          <xsl:value-of select="$record/triggerid"/>
        </small>
      </td>
    </tr>
    <tr colspan="2">
      <th>
        <small>DateTime: </small>
      </th>
      <td colspan="2">
        <small>
          <xsl:value-of select="$record/triggerdatetime"/>
        </small>
      </td>
    </tr>
    <tr>
      <th>
        <small>Job: </small>
      </th>
      <td colspan="2">
        <small>
          <xsl:value-of select="$record/jobid"/>
        </small>
      </td>
    </tr>
    <p></p>
  </xsl:template>
  <!-- 
  //
  //  print the trace information for this account.
  //
  -->
  <xsl:template name="printTrace">
    <xsl:param name="records" select="."/>    
      <xsl:for-each select="$records">
        <xsl:if test="position()=1">
          <tr>
            <th>
              <small>Log DateTime</small>
            </th>
            <th>
              <small>Entry</small>
            </th>
          </tr>
        </xsl:if>
        <tr>
          <xsl:if test="(position() mod 2 = 0)">
            <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
          </xsl:if>
          <xsl:attribute name="height">25</xsl:attribute>
          <td>
            <!---="125"-->
            <small>
              <xsl:value-of select="logdatetime" />
            </small>
          </td>
          <td>
            <small>
              <xsl:value-of select="logdata" />
            </small>
          </td>
        </tr>
      </xsl:for-each>    
    <p></p>
  </xsl:template>
  <!--
  //
  //  function Main()
  //
  -->
  <xsl:template match="/">
    <html>
      <head>
        <!--link rel="stylesheet" type="text/css" href="./report.css"></link-->
        <style >
          /* Common styles */
          body, p, tr, td, ol, ul
          {
          font-family: Arial, "Trebuchet MS" , Helvetica, Geneva, Swiss, SunSans-Regular;
          font-size: 95%;
          margin: 0;
          padding: 0;
          }
          th
          {
          color: black;
          font-weight: bold;
          letter-spacing: 0.1em;
          text-align: left;
          text-indent: 0.5em;
          background-color: lightgrey;
          }
          body a
          {
          text-decoration: none;
          }
          h1
          {
          font-size: 1.1em;
          color: black;
          font-weight: bold;
          letter-spacing: 0.1em;
          margin-top: 0.4em;
          margin-bottom: 0.4em;
          border-bottom: 2px solid #bf0000;
          }
          h2
          {
          font-size: 1.1em;
          color: white;
          font-weight: bold;
          letter-spacing: 0.1em;
          text-indent: 0.5em;
          background-color: #bf0000;
          }
          h3
          {
          font-size: 1em;
          color: black;
          font-weight: bold;
          letter-spacing: 0.1em;
          margin-top: 0.6em;
          margin-bottom: 0.2em;
          border-bottom: 1px solid #bf0000;
          }
          h4
          {
          font-size: 16px;
          color: white;
          font-weight: bold;
          letter-spacing: 0.1em;
          margin-top: 0em;
          margin-bottom: 0em;
          text-indent: 0.5em;
          background-color: #6B8899;
          }
          h5
          {
          font-size: 1em;
          color: black;
          font-weight: bold;
          letter-spacing: 0.1em;
          margin-bottom: 0.1em;
          text-indent: 0.5em;
          background-color: #cccc99;
          }
          small
          {
          font-size: 0.8em;
          }

          select
          {
          font-family: Arial, "Trebuchet MS" , Helvetica, Geneva, Swiss, SunSans-Regular;
          }

          /* WebAdmin specific styles */
          td
          {
          font-size: 0.9em;
          font-family: Arial, "Trebuchet MS" , Helvetica, Geneva, Swiss, SunSans-Regular;
          }
          tr.tab a
          {
          color: #5a5958;
          font-size: 0.8em;
          text-decoration: none;
          }
          tr.tab a:hover
          {
          color: #000000;
          }
          .DN
          {
          font-size: 1.1em;
          color: white;
          font-weight: bold;
          letter-spacing: 0.1em;
          }
          a.DN
          {
          text-decoration: none;
          color: white !important;
          }

          div.header
          {
          margin: 0;
          /*background-color: #bf0000;
          color: white;*/
          }
          div.footer		{ margin: bottom; }
          div.content
          {
          margin-left: 190px;
          border-left: 1px solid gray;
          padding: 1em;
          }
          div.left
          {
          float: left;
          width: 160px;
          margin: 0;
          padding: 1em;
          }
          div.container
          {
          width: 100%;
          margin: 0px;
          border: 1px solid gray;
          line-height: 150%;
          }
        </style>
      </head>
      <body>
        <!--
          This is an XSLT template file. Fill in this area with the
          XSL elements which will transform your XML to XHTML.
      -->
        <xsl:choose>
          <xsl:when test="count($accounts)>0">
            <div class="container">
              <xsl:call-template name="printTableOfContents">
                <xsl:with-param name="accounts" select="$accounts"/>
              </xsl:call-template>
              <div class="content">
                <a name="Top"></a>
                <xsl:for-each select="$accounts">
                  <xsl:sort select="."/>
                  <xsl:call-template name="printAccount">
                    <xsl:with-param name="account" select="."/>
                    <xsl:with-param name="records" select="/NewDataSet/Table[account=current()]"/>
                  </xsl:call-template>
                </xsl:for-each>
              </div>
            </div>
          </xsl:when>
          <xsl:otherwise>
            <h3>There was no trace information found for this trigger.</h3>
          </xsl:otherwise>
        </xsl:choose>        
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
