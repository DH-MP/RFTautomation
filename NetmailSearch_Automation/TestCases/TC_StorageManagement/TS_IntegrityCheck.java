package TestCases.TC_StorageManagement;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import resources.TestCases.TC_StorageManagement.TS_IntegrityCheckHelper;
import utilities.HelperClass;
import NetmailAdminUUI.Storage;
import NetmailAdminUUI.StorageLocation;
import NetmailAdminUUI.WebAdmin;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class TS_IntegrityCheck extends TS_IntegrityCheckHelper
{
	/**
	 * Script Name   : <b>TS_IntegrityCheck</b>
	 * Generated     : <b>Jan 6, 2015 1:26:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2015/01/06
	 * @author Administrator
	 */
	private boolean invalidDateRange = false;
	private WebAdmin webadmin;
	private String verificationFolder = remoteWorkSpace+"\\NetmailSearch_Automation\\assets\\TS_IntegrityCheck\\verificationPoints";
	private String accountName = "ProxyAdminTest@ProxyAdminTest.BASE2012.First Organization";
	String jobName = "";
	public TS_IntegrityCheck(){
		webadmin = new WebAdmin();
	}
	public void testMain(Object[] args) 
	{
		invalidDateRange = dpBoolean("invalidDateRange");
		java.util.Date now = new java.util.Date();
		jobName = "IC"+now.toString();
		Storage storage = new Storage();
		StorageLocation storageLocation = new StorageLocation();
		webadmin.loadWebadminUUI();
		webadmin.login();
		webadmin.navigateTree("Archive>Cluster.*");
		webadmin.selectPageTab("Storage");
		String storagePath = remoteWorkSpace+"\\NetmailSearch_Automation\\assets\\TS_IntegrityCheck\\"+dpString("dataFolder");
		storage.create("File System", jobName, storagePath, "administrator", "123Password");	
		storageLocation.create("Standard", jobName, "testing storage intregity", jobName, "archive", jobName, "attachment", storagePath+"\\audit", "administrator", "123Password", "Administrator@BASE2012@First Organization@User");
		webadmin.navigateTree("Archive>Cluster.*>Agents>Storage Management");
		sleep(5);
		webadmin.createJob(jobName, true);
		sleep(5);
		webadmin.navigateTree("Archive>Cluster.*>Agents>Storage Management>"+jobName);
		
		browser_htmlBrowser().inputKeys("{F12}");
		sleep(2);
		webadmin.selectPageTab("Criteria");

		radioButton_integrityCheck().click();
		logInfo("clicked integrity radio");
		browser_htmlBrowser().inputKeys("{F12}");
			
		text_dateRangeFrom().click();
		browser_htmlBrowser().inputKeys("^a");
		browser_htmlBrowser().inputChars(dpString("dateRangeFrom"));
		logInfo(String.format("Input < %s >", dpString("dateRangeFrom")));
		
		text_dateRangeTo().click();
		browser_htmlBrowser().inputKeys("^a");
		browser_htmlBrowser().inputChars(dpString("dateRangeTo"));
		logInfo(String.format("Input < %s >", dpString("dateRangeTo")));
		
		list_cSelect_ArchiveLocationLi().click();
		list_cSelect_ArchiveLocationLi().click(atText(jobName.replace(" ", "-")));
		webadmin.clickActionBarButton("Save");
		
		if(invalidDateRange){
			String errorMsg = "Invalid date format: 'asdsda'. Format accepted is dd/mm/yyyy.";
			vpManual("invalid_daterange", label_invalidDateFormat().getProperty(".text").toString().contentEquals(errorMsg)).performTest();
			button_htmlDialogButtonOK().click();
			return;
		}
		
		//Validate Integral
		if(dpBoolean("integralCheck")){
			checkBox_validateIntegralRef().click();
			checkBox_crcCheck().click();
			checkBox_repairAudits().click();
			webadmin.clickActionBarButton("Save");
			webadmin.runJob(accountName);
			sleep(30);
			waitUntilFinished();
			validateResult(dpString("vpName"));
		}		
		
		if(dpBoolean("indexCheck")){
			checkBox_validateIfMsgIndexed().click();
			checkBox_reIndexMissingMsg().click();
			webadmin.clickActionBarButton("Save");
			webadmin.runJob(accountName);
			sleep(600);
			waitUntilFinished();
			
			webadmin.navigateTree("Archive");
			validateResult(dpString("vpName"));
			webadmin.waitForJob(jobName);
			
			webadmin.navigateTree("Archive>Cluster.*>Agents>Storage Management>"+jobName);
			webadmin.runJob(accountName);
			sleep(30);
			waitUntilFinished();
			validateResult(dpString("vpName")+"_indexed");
		}

		
		if(dpBoolean("gapCheck")){
			checkBox_reportGaps().click();
			text_reportGapsDays().click();
			browser_htmlBrowser().inputChars(String.valueOf(dpInt("gapDays")));
			webadmin.clickActionBarButton("Save");
			webadmin.runJob(accountName);
			sleep(30);
			waitUntilFinished();
			validateResult(dpString("vpName"));
		}
	}
	
	private void validateResult(String vpName){
		
		File dir = new File("\\\\"+IP+"\\Messaging Architects\\IntegrityReport");
		File[] files = dir.listFiles();
		String lastFolderName = files[files.length-1].getName();
		 
		String actualPath = "\\\\"+IP+"\\Messaging Architects\\IntegrityReport\\"+lastFolderName+"\\P\\"+accountName+".htm";

		File actual = new File(actualPath);
		File expected = new File(verificationFolder+"\\"+vpName+".htm");
		try {
			vpManual("integrityCheck_"+dpString("vpName"), true, FileUtils.contentEquals(actual, expected)).performTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void waitUntilFinished(){
		webadmin.selectPageTab("Report");
		sleep(5);
		TestObject[] rows = table_reportTable().find(atDescendant(".class", "Html.TR", "class", "level_0"), false);
		TestObject[] columns = rows[0].getChildren();
		
		System.out.println(columns[2].getProperty(".text"));
		while(!columns[2].getProperty(".text").toString().contains("finished")){
			sleep(5);
			webadmin.selectPageTab("Report");
			sleep(5);
			rows = table_reportTable().find(atDescendant(".class", "Html.TR", "class", "level_0"), false);
			columns = rows[0].getChildren();
		}
	}
	
	@Override
	public void onTerminate(){
		Storage storage = new Storage();
		StorageLocation storageLocation = new StorageLocation();
		storage.delete(jobName);
		storageLocation.delete(jobName);
		super.onTerminate();
	}
}

