package TestCases.TC_DeletingMessages;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import resources.TestCases.TC_DeletingMessages.DeleteMessageHelper;
import utilities.HelperClass;
import Case_Management.manageCase;
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
public class DeleteMessage extends DeleteMessageHelper
{
	/**
	 * Script Name   : <b>DeleteMessage</b>
	 * Generated     : <b>Jul 14, 2014 1:10:12 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/14
	 * @author Administrator
	 */
	private String workSpace = "\\\\10.10.23.61\\Data\\NetmailSearchGV\\NetmailSearch_Automation\\";
	
	public void testMain(Object[] args) 
	{
		String targetFolderLocation = "\\\\"+IP+"\\GVautomatoin";
		String baselineFolderLocation = workSpace+"assets\\AutoFixIndex";
		String webAdminUserName = "netmail";
		String webAdminPassword = "M3ss4g1ng";
		String indexAgentName = "indexinator";	
		String randomAccount = "Amy S@Amy.po1.dom1";
		String randomLocation = "RIF";
		
		//Restart open
		HelperClass.startstopRemoteService(false, IP, "MAOpen", workSpace);
		HelperClass.startstopRemoteService(true, IP, "MAOpen", workSpace);
		
		//Replace location with good baseline
		File targetLocation = new File(targetFolderLocation);
		File baselineLocation = new File(baselineFolderLocation+"\\GVautomatoin_Normal");
		if(targetLocation.exists()){
			try {
				FileUtils.cleanDirectory(targetLocation);
				FileUtils.copyDirectory(baselineLocation, targetLocation);
			} catch (IOException e1) {
				logError(e1.getMessage());
				e1.printStackTrace();
			}
		}
		
		//Index baseline data
		WebAdmin wa = new WebAdmin();
		wa.setIp(IP);
		wa.setUserName(webAdminUserName);
		wa.setPassword(webAdminPassword);
		wa.setJobName(indexAgentName);
		wa.loadWebadminUUI();
		wa.login();
		sleep(10);
		wa.navigateTree("Archive>Cluster.*>Agents>Index>"+indexAgentName);
		wa.indexAccount("ProxyAdminTest@ProxyAdminTest.BASE2012.First Organization", "GVautomation");
		wa.navigateTree("Archive");
		wa.waitForJob(indexAgentName);
		
		//Restart Netmail remote providers
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
		
		//Login
		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		Object[] al = {"", "Super User"}; 
		callScript("adminLogin", al);

		//Create Case
		manageCase mc = new manageCase();
		mc.setName("GVautomation");
		mc.setLocations("GVautomation");
		mc.newCase();
		waitForloading();
		HelperClass.navigateLocation("GVautomation>ProxyAdminTest");
		waitForloading();
		
		
		//Select first message
		TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		RegularExpression a = new RegularExpression(".*x-grid3-td-checker.*", false); 
		TestObject[] checkbox = results[0].find(atDescendant(".tag", "TD", "class", a), false);
		((GuiTestObject) checkbox[0]).click();
		
		//Delete
		button_deleteMessage().click();
		button_yesbutton().click();
		sleep(10);
		
		//Validate strikethrough
		results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		TestObject parent = results[0].getParent();
		logTestResult("Row is StrikeThrough", parent.getProperty("class").toString().matches(".*deletedrow.*"));
		
		//Run index on some other location to trigger commit
		wa.loadWebadminUUI();
		wa.login();
		sleep(10);
		wa.navigateTree("Archive>Cluster.*>Agents>Index>Indexinator");
		wa.indexAccount(randomAccount, randomLocation);
		wa.navigateTree("Archive");
		wa.waitForJob(indexAgentName);
		
		
		//Restart Netmail remote providers
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
		
		//Login
		callScript("loginScript", ls);
		Object[] al2 = {"GVautomation", "Super User"}; 
		callScript("adminLogin", al2);
		sleep(3);
		
		//Verify commited
		int oldResult = results.length;
		results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		logTestResult("message removed", results.length == oldResult-1);
		
		//Delete Case
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		mc.deleteCase("GVautomation");
	}
}

