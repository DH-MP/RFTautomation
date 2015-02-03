package TC_2057_Transend_Extract_Index_And_Search;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import resources.TC_2057_Transend_Extract_Index_And_Search.TS_1690_Transend_Exchange2013Helper;
import utilities.HelperClass;

import Case_Management.manageCase;
import NetmailAdminUUI.Storage;
import NetmailAdminUUI.StorageLocation;
import NetmailAdminUUI.WebAdmin;
import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;
import Netmail_WebAdmin.webAdmin;
import Transend.Transend;

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
public class TS_1690_Transend_Exchange2013 extends TS_1690_Transend_Exchange2013Helper
{
	/**
	 * Script Name   : <b>TS_1690_Transend_Exchange2013</b>
	 * Generated     : <b>May 12, 2014 2:52:51 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/05/12
	 * @author Administrator
	 */
	
	Process transend = null;
	private String transendExePath = "C:\\Program Files (x86)\\Transend Migrator\\tmship\\tm11.exe";
	private String workSpace = remoteWorkSpace+"\\NetmailSearch_Automation";
	private String webAdminIP = "http://"+IP+":89";
	private String webAdminUserName = "netmail";
	private String webAdminPassword = "M3ss4g1ng";
	private String sourceType = "Exchange Server 2013";
	private String targetType = "Netmail XML Archive";
	
	public TS_1690_Transend_Exchange2013(){
		//Constructor	
		IVariablesManager vm = getVariablesManager();
		IParameter rqmIP = vm.getInputParameter("ip");
		if(rqmIP != null){
			IP = rqmIP.getValue();
			webAdminIP = "http://"+IP+":89";
		}
	}
	
	public void testMain(Object[] args) 
	{
		
		String name = dpString("name");
		String sourceAddress = dpString("sourceAddress");
		String sourceUserEmail = dpString("sourceUserEmail");
		String sourcePassword = dpString("sourcePassword");
		String targetUserName = dpString("targetUserName");
		String targetUserCN  = dpString("targetUserCN");
		String indexName = dpString("indexName");
		String targetSharedDirectory = "\\\\10.10.23.61\\Data\\TransendData\\"+name;
		
		
		//Start Transend
		try {
			transend = Runtime.getRuntime().exec(transendExePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Setup
		File directory = new File(targetSharedDirectory);
		if(directory.exists()){
			try {
				FileUtils.cleanDirectory(directory);
			} catch (IOException e1) {
				logError(e1.getMessage());
				e1.printStackTrace();
			}
		}
		
		//Evaluation message
		evaluationPopUp_OKbutton().waitForExistence(150, 3);	
		evaluationPopUp_OKbutton().click();
		logInfo("Click ok on evaluatioin version message");
		sleep(5);
		
		//Data
		Transend t = new Transend();
		Map<String, Object> map = new HashMap<>();	
		Object[] arg = {map};
		t.setSourceType(sourceType);
		t.setSourceAddress(sourceAddress);
		t.setSourceUserEmail(sourceUserEmail);
		t.setSourcePassword(sourcePassword);
		t.setTargetType(targetType);
		t.setTargetSharedDirectory(targetSharedDirectory);
		t.setTargetUserName(targetUserName);
		t.setTargetUserCN(targetUserCN);
		t.setSelectedFoldersNoRegexp("Inbox,Drafts,Sent Items");
		t.setCategory("email");
		t.setData();
		t.setCategory("addressBook");
		t.setData();
		t.setCategory("calendar");
		t.setData();
		t.setCategory("task");
		t.setData();	
		t.startSingleMigration();
		
		//Start Browser
		HelperClass.oneBrowserSetup();
		browser_htmlBrowser().loadUrl(webAdminIP);
		browser_htmlBrowser().activate();
		
		//Webadmin
		Storage storage = new Storage();
		StorageLocation storageLocation = new StorageLocation();
		WebAdmin webadmin = new WebAdmin();
		webadmin.loadWebadminUUI();
		webadmin.login();
		storage.create("File System", name, targetSharedDirectory, "administrator", "123Password");
		storageLocation.create(
				"Standard", 
				name, 
				"testing storage intregity", 
				name, 
				"", 
				name, 
				"", 
				targetSharedDirectory, 
				"administrator", 
				"123Password", 
				"Administrator@BASE2012@First Organization@User"
		);
		webadmin.navigateTree("Archive>Cluster.*>Agents>Index>"+indexName);
		webadmin.indexAccount(targetUserCN, name);
		webadmin.navigateTree("Archive");
		webadmin.waitForJob(indexName);
		
		
//		//Restart services
//		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
//		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
//		
		//Login netmail search and new case
		login("");
		
		/********************NEW CASE***************************/
		manageCase mc = new manageCase();
		mc.setName(name);
		mc.setDescription("nothing");
		mc.setStatus("New");
		mc.setCaseClass("Claim");
		mc.setOpenDate("06/13/14");
		mc.setCloseDate("06/13/14");
		mc.setCaseType("General Liability");
		mc.setCaseSubType("Workplace");
		mc.setCheckLoadAllCase(true);
		mc.setLocations(name);
		mc.setFilterWord("a");
		mc.setCancelOperation(false);
		mc.newCase();
		login(name);
		
		webadmin.loadWebadminUUI();
		webadmin.login();
		storage.delete(name);
		storageLocation.delete(name);
	}
	
	private void login(String caseName){
		//Login
		NetmailLogin.login();
			
		//Admin Login
		adminLogin.selectUserType("Super User");

		if(caseName != null && !caseName.isEmpty()){
			adminLogin.selectCase(caseName);
		}else{
			manageCase.clickOkButtonReviewCase();
		}		
	}
	@Override
	public void onTerminate(){
		transend.destroy();
	}
}

