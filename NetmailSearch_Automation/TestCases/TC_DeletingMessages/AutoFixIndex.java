package TestCases.TC_DeletingMessages;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import resources.TestCases.TC_DeletingMessages.AutoFixIndexHelper;
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
public class AutoFixIndex extends AutoFixIndexHelper
{
	/**
	 * Script Name   : <b>AutoFixIndex</b>
	 * Generated     : <b>Jul 14, 2014 2:24:16 PM</b>
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
		String webAminPassword = "M3ss4g1ng";
		String indexAgentName = "indexinator";
		
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
		wa.setPassword(webAminPassword);
		wa.setJobName(indexAgentName);
		wa.loadWebadminUUI();
		wa.login();
		sleep(10);
		wa.navigateTree("Archive>Cluster.*>Agents>Index>"+indexAgentName);
		wa.indexAccount("ProxyAdminTest@ProxyAdminTest.BASE2012.First Organization", "GVautomation");
		wa.navigateTree("Archive");
		wa.waitForJob(indexAgentName);
		
		//Restart
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
		
		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		Object[] al = {"", "Super User"}; 
		callScript("adminLogin", al);

		//Create Case
		manageCase mc = new manageCase();
		mc.setName("GVautomation");
		mc.setLocations("GVautomation");
		mc.newCase();
		
		//Restart Open
		HelperClass.startstopRemoteService(false, IP, "MAOpen", workSpace);
		HelperClass.startstopRemoteService(true, IP, "MAOpen", workSpace);
		
		//Replace with bad baseline
		File baselineDeletedLocation = new File(baselineFolderLocation+"\\GVautomatoin_Deleted");
		if(targetLocation.exists()){
			try {
				FileUtils.cleanDirectory(targetLocation);
				FileUtils.copyDirectory(baselineDeletedLocation, targetLocation);
			} catch (IOException e1) {
				logError(e1.getMessage());
				e1.printStackTrace();
			}
		}
		
		//Test
		TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		((GuiTestObject)results[0]).click();
		((GuiTestObject)results[0]).doubleClick();
		sleep(6);
		vpManual("ErrorHandling_Header", "Message Deleted", html_headerDiv().getProperty(".contentText")).performTest();
		vpManual("ErrorHandling_Header", "Sorry, this message was deleted and cannot be read.",html_bodyMessage().getProperty(".contentText")).performTest();
		button_deletedMessage_OKbutton().click();
		
		
		//Delete Case
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		mc.deleteCase("GVautomation");
	}
	
	@Override
	public void onTerminate(){
		
	}
}

