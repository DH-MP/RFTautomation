<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet 
  version="1.1" 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  xmlns:msxml="urn:schemas-microsoft-com:xslt">

  <xsl:key name="account-key" match="Table" use="account"/>
  <xsl:variable name="totalAccountsNumber" select="/NewDataSet/total"></xsl:variable>
  <xsl:variable name="totalProcessedNumber" select="/NewDataSet/processed"></xsl:variable>
  <xsl:variable name="totalFailedNumber" select="/NewDataSet/failed"></xsl:variable>
  <xsl:variable name="accounts" select="/NewDataSet/Table[generate-id(.)=generate-id(key('account-key', account))]/account"/>
  <xsl:variable name="titles.tf">
    <!-- declare title options for each function -->
    <title name="log-purge-summary">Purge Log Summary</title>
    <title name="folder-summary">Folder Management Summary</title>
    <title name="utility-summary">Mail Maintainance Summary</title>
    <title name="purge-summary">Mail Purge Summary</title>
    <title name="index-summary">Mail Index Summary</title>
    <title name="migrate-summary">Mail Migrate Summary</title>
    <title name="groom-summary">Mail Groom Summary</title>
    <title name="export-summary">Mail Export Summary</title>
    <title name="archive-summary">Mail Archive Summary</title>
    <title name="address-book-summary">Addressbook Synchronization Summary</title>
    <title name="job-summary">Job Summary</title>
  </xsl:variable>
  <xsl:variable name="titles" select="msxml:node-set($titles.tf)"/>
  <!--
  //
  //  print the header information for this account
  //
  -->
  <xsl:template name="printHeader">
    <xsl:param name="record" select="."/>
    <xsl:param name="span" select="."/>
    <tr>
      <th>
        Agent:
      </th>
      <td>
        <xsl:attribute name="colspan">
          <xsl:value-of select="$span"/>
        </xsl:attribute>
        <xsl:value-of select="normalize-space($record/jobid)"/>
      </td>
    </tr>
    <!--<tr>
      <th>
        DateTime:
      </th>
      <td>
        <xsl:attribute name="colspan">
          <xsl:value-of select="$span"/>
        </xsl:attribute>        
        <xsl:value-of select="$record/jobstarttime"/>        
      </td>
    </tr>
    <tr>
      <th>
        Job:
      </th>
      <td>
        <xsl:attribute name="colspan">
          <xsl:value-of select="$span"/>
        </xsl:attribute>        
        <xsl:value-of select="normalize-space($record/jobid)"/>        
      </td>
    </tr>-->
  </xsl:template>
  <!--
  //
  //  output the actual summary infor
  //  for the summary indicated by the
  //  name attribute from the summary tag
  -->
  <xsl:template name="printSummary">
    <xsl:param name="name" select="."/>
    <xsl:param name="account" select="."/>
    <xsl:param name="record" select="."/>
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
    <xsl:choose>
      <xsl:when test="$name='folder-summary'">
        <xsl:call-template name="folder-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='log-purge-summary'">
        <xsl:call-template name="log-purge-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='index-summary'">
        <xsl:call-template name="index-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='purge-summary'">
        <xsl:call-template name="archive-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="record" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='migrate-summary'">
        <xsl:call-template name="migrate-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='groom-summary'">
        <xsl:call-template name="export-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='export-summary'">
        <xsl:call-template name="export-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='archive-summary'">
        <xsl:call-template name="archive-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="record" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='utility-summary'">
        <xsl:call-template name="utility-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='address-book-summary'">
        <xsl:call-template name="address-book-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="summary" select="$record"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$name='job-summary'">
        <xsl:call-template name="job-summary">
          <xsl:with-param name="account" select="$displayname"/>
          <xsl:with-param name="record" select="$record"/>
        </xsl:call-template>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  <!--
  //
  //  general job summary print.
  //
  -->
  <xsl:template name="job-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="record" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th >
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Status</xsl:text>
        </th>
        <th >
          <xsl:text>Eligible</xsl:text>
        </th>
        <th >
          <xsl:text>Succeed</xsl:text>
        </th>
        <th >
          <xsl:text>Errors</xsl:text>
        </th>
        <th >
          <xsl:text>Warnings</xsl:text>
        </th>
        <th >
          <xsl:text>Indexed</xsl:text>
        </th>
        <th >
          <xsl:text>Indexed Errors</xsl:text>
        </th>
        <th >
          <xsl:text>Transacted</xsl:text>
        </th>
        <th >
          <xsl:text>Transacted Errors</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td >
        <xsl:value-of select="$account"/>
      </td>
      <td >
        <xsl:value-of select="$record/jobstarttime"/>
      </td>
      <td >
        <xsl:value-of select="$record/logtimestamp"/>
      </td>
      <td>
        <xsl:choose>
          <xsl:when test="$record/status">
            <xsl:value-of select="$record/status"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>Undefined</xsl:text> 
          </xsl:otherwise>
        </xsl:choose>
      </td>
      <td >
        <xsl:value-of select="$record/nb_eligible"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_succeed"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_errors"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_warnings"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_indexed"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_index_errors"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_transacted"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_transact_errors"/>
      </td>
    </tr>
  </xsl:template>

  <!--
  //
  //  print folder summary for utility job
  //
  -->
  <xsl:template name="folder-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Folder</xsl:text>
        </th>
        <th>
          <xsl:text>Index</xsl:text>
        </th>
        <th>
          <xsl:text>Created</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <xsl:for-each select="$summary/folder">
      <tr>
        <xsl:if test="(position() mod 2 = 0)">
          <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
        </xsl:if>
        <xsl:attribute name="height">25</xsl:attribute>
        <td>
          <xsl:value-of select="$account"/>
        </td>
        <td>
          <xsl:value-of select="start_time"/>
        </td>
        <td>
          <xsl:value-of select="end_time"/>
        </td>
        <td>
          <xsl:value-of select="."/>
        </td>
        <td align="right">
          <xsl:value-of select="@index"/>
        </td>
        <td align="right">
          <xsl:value-of select="@created"/>
        </td>
      </tr>
    </xsl:for-each>
  </xsl:template>
  <!--
  //
  //  print expire summary for utility job
  //
  -->
  <xsl:template name="utility-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Processed</xsl:text>
        </th>
        <th>
          <xsl:text>Removed</xsl:text>
        </th>
        <th>
          <xsl:text>Errors</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td>
        <xsl:value-of select="$account"/>
      </td>
      <td>
        <xsl:value-of select="$summary/start_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/end_time"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/messages_processed"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/messages_removed"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/errors_encountered"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  print summary from log purge job
  //
  -->
  <xsl:template name="log-purge-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Status Entries</xsl:text>
        </th>
        <th>
          <xsl:text>Trace Entries</xsl:text>
        </th>
        <th>
          <xsl:text>Error Entries</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td>
        <xsl:value-of select="$account"/>
      </td>
      <td>
        <xsl:value-of select="$summary/start_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/end_time"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/status_rows_purged"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/trace_rows_purged"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/error_rows_purged"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  print summary for index job
  //
  -->
  <xsl:template name="index-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Bytes</xsl:text>
        </th>
        <th>
          <xsl:text>Documents</xsl:text>
        </th>
        <th>
          <xsl:text>Words</xsl:text>
        </th>
        <th>
          <xsl:text>Size</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td>
        <xsl:value-of select="$account"/>
      </td>
      <td>
        <xsl:value-of select="$summary/start_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/end_time"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/bytes_indexed"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/documents_indexed"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/words_indexed"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/index_size"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  print purge summary for ILM job
  //
  -->
  <xsl:template name="purge-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Messages</xsl:text>
        </th>
        <th>
          <xsl:text>Purged</xsl:text>
        </th>
        <th>
          <xsl:text>Scheduled</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td>
        <xsl:value-of select="$account"/>
      </td>
      <td>
        <xsl:value-of select="$summary/start_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/end_time"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/message_count"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/purge_count"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/scheduled_count"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  print migration summary for ILM job
  //
  -->
  <xsl:template name="migrate-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Messages</xsl:text>
        </th>
        <th>
          <xsl:text>Policy</xsl:text>
        </th>
        <th>
          <xsl:text>Archive Path</xsl:text>
        </th>
        <th>
          <xsl:text>Audit Path</xsl:text>
        </th>
        <th>
          <xsl:text>Result</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td>
        <xsl:value-of select="$account"/>
      </td>
      <td>
        <xsl:value-of select="$summary/start_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/end_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/message_count"/>
      </td>
      <td>
        <xsl:value-of select="$summary/policy_name"/>
      </td>
      <td>
        <xsl:value-of select="$summary/archive_path"/>
      </td>
      <td>
        <xsl:value-of select="$summary/audit_path"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/result"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  print groom and export summary for ILM and Export job
  //
  -->
  <xsl:template name="export-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Messages</xsl:text>
        </th>
        <th>
          <xsl:text>Disks</xsl:text>
        </th>
        <th>
          <xsl:text>Bytes</xsl:text>
        </th>
        <th>
          <xsl:text>Omitted</xsl:text>
        </th>
        <th>
          <xsl:text>Oversized</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td>
        <xsl:value-of select="$account"/>
      </td>
      <td>
        <xsl:value-of select="$summary/start_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/end_time"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/message_count"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/disk_count"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/bytes_written"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/omitted_count"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/oversized_count"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  print archive summary for archive job
  //
  -->
  <xsl:template name="archive-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="record" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th >
          <xsl:text>End Time</xsl:text>
        </th>
        <th >
          <xsl:text>Scanned</xsl:text>
        </th>
        <th >
          <xsl:text>Eligible</xsl:text>
        </th>
        <th >
          <xsl:text>Succeed</xsl:text>
        </th>
        <th >
          <xsl:text>Errors</xsl:text>
        </th>
        <th >
          <xsl:text>Warnings</xsl:text>
        </th>
        <th >
          <xsl:text>Indexed</xsl:text>
        </th>
        <th >
          <xsl:text>Indexed Errors</xsl:text>
        </th>
        <th >
          <xsl:text>Transacted</xsl:text>
        </th>
        <th >
          <xsl:text>Transacted Errors</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td >
        <xsl:value-of select="$account"/>
      </td>
      <td >
        <xsl:value-of select="$record/jobstarttime"/>
      </td>
      <td >
        <xsl:value-of select="$record/logtimestamp"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_scanned"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_eligible"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_succeed"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_errors"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_warnings"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_indexed"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_index_errors"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_transacted"/>
      </td>
      <td >
        <xsl:value-of select="$record/nb_transact_errors"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  print address book sync job summary
  //
  -->
  <xsl:template name="address-book-summary">
    <xsl:param name="account" select="."/>
    <xsl:param name="summary" select="."/>
    <xsl:if test="position()=1">
      <tr>
        <th colspan="4">Contacts</th>
      </tr>
      <tr>
        <th>
          <xsl:text>Account</xsl:text>
        </th>
        <th>
          <xsl:text>Start Time</xsl:text>
        </th>
        <th>
          <xsl:text>End Time</xsl:text>
        </th>
        <th>
          <xsl:text>Added</xsl:text>
        </th>
        <th>
          <xsl:text>Updated</xsl:text>
        </th>
        <th>
          <xsl:text>Deleted</xsl:text>
        </th>
        <th>
          <xsl:text>Errors</xsl:text>
        </th>
        <th>
          <xsl:text>PO Added</xsl:text>
        </th>
        <th>
          <xsl:text>PO Errors</xsl:text>
        </th>
      </tr>
    </xsl:if>
    <tr>
      <xsl:if test="(position() mod 2 = 0)">
        <xsl:attribute name="bgcolor">#dddddd</xsl:attribute>
      </xsl:if>
      <xsl:attribute name="height">25</xsl:attribute>
      <td>
        <xsl:value-of select="$account"/>
      </td>
      <td>
        <xsl:value-of select="$summary/start_time"/>
      </td>
      <td>
        <xsl:value-of select="$summary/end_time"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/contacts_added"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/contacts_updated"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/contacts_deleted"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/contact_errors"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/post_offices_added"/>
      </td>
      <td align="right">
        <xsl:value-of select="$summary/post_office_errors"/>
      </td>
    </tr>
  </xsl:template>
  <!--
  //
  //  function Main()
  //
  -->
  <xsl:template match="/">
    <html>
      <title></title>
      <head>

        <!--link rel="stylesheet" type="text/css" href="./report.css"></link-->
        <style >
          /* Common styles */
          body, p, tr, td, ol, ul
          {
          font-family: Arial, "Trebuchet MS" , Helvetica, Geneva, Swiss, SunSans-Regular;
          font-size: 95%;
          <!--margin: 0;
          padding: 0;-->
          }
          th
          {
          color: black;
          font-weight: bold;
          letter-spacing: 0.1em;
          text-align: center;
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
          text-align: center;
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
          <xsl:when test="$totalAccountsNumber">
            <caption>
              <h1 align="Center">
                Job Global Counters
              </h1>
            </caption>
            <table border="2" bordercolor="navy" cellspacing="1" WIDTH="100%">
              <tr>
                <th>Total accounts</th>
                <th>Processed accounts</th>
                <th>Failed accounts</th>
              </tr>
              <tr>
                <td>
                  <xsl:value-of select="$totalAccountsNumber"></xsl:value-of>
                </td>
                <td>
                  <xsl:value-of select="$totalProcessedNumber"></xsl:value-of>
                </td>
                <td>
                  <xsl:value-of select="$totalFailedNumber"></xsl:value-of>
                </td>
              </tr>
            </table>
          </xsl:when>
        </xsl:choose>
        <xsl:choose>
          <xsl:when test="count($accounts)>0">
            <table border="2"  bordercolor="navy" cellspacing="1">
              <xsl:for-each select="$accounts">
                <xsl:sort select="."/>
                <xsl:variable name="record" select="/NewDataSet/Table[account=current()]"/>
                <xsl:variable name="name" select="'job-summary'"/>
                <xsl:if test="position()=1">
                  <xsl:variable name="span" select="count($record/*)"/>
                  <xsl:variable name="title" select="$titles/title[@name=$name]"/>
                  <caption>
                    <h1>
                      <xsl:value-of select="$title"/>
                    </h1>
                  </caption>
                  <xsl:call-template name="printHeader">
                    <xsl:with-param name="record" select="$record"/>
                    <xsl:with-param name="span" select="$span"/>
                  </xsl:call-template>
                </xsl:if>
                <xsl:call-template name="printSummary">
                  <xsl:with-param name="name" select="$name"/>
                  <xsl:with-param name="account" select="$record/account"/>
                  <xsl:with-param name="record" select="$record"/>
                </xsl:call-template>
              </xsl:for-each>
            </table>
          </xsl:when>
          <xsl:otherwise>
            <h3>There was no summary information found for this trigger.</h3>
          </xsl:otherwise>
        </xsl:choose>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
