package TC_2057_Transend_Extract_Index_And_Search;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import resources.TC_2057_Transend_Extract_Index_And_Search.TS_1695_Transend_GoogleHelper;
import utilities.HelperClass;
import Netmail_WebAdmin.webAdmin;
import TC_827_NetmailSearch_Search.TS_1378_ListandOrderPersistence;

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
public class TS_1695_Transend_Google extends TS_1695_Transend_GoogleHelper
{
	/**
	 * Script Name   : <b>TS_1695_Transend_Google</b>
	 * Generated     : <b>Jun 13, 2014 11:30:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/06/13
	 * @author Administrator
	 */
	Process transend = null;
	private String transendExePath = "C:\\Program Files (x86)\\Transend Migrator\\tmship\\tm11.exe";
	private String workSpace = "\\\\10.10.23.61\\Data\\NetmailSearchGV\\NetmailSearch_Automation";
	private String webAdminIP = "http://"+IP+":89";
	private String webAdminUserName = "netmail";
	private String webAdminPassword = "M3ss4g1ng";
	
	private String sourceType = "Google Single User";
	private String targetType = "Netmail XML Archive";
	
	public TS_1695_Transend_Google(){
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
		String name = dpString("targetSharedDirectory");
		String sourceUserEmail = dpString("sourceUserEmail");
		String sourcePassword = dpString("sourcePassword");
		String targetUserName = dpString("targetUserName");
		String targetUserCN  = dpString("targetUserCN");
		String indexName = dpString("indexName");
		String targetSharedDirectory = "\\\\"+IP+"\\TransendData\\"+name;
		
		//Start Transend
		try {
			transend = Runtime.getRuntime().exec(transendExePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Evaluation message
		evaluationPopUp_OKbutton().waitForExistence(150, 3);	
		evaluationPopUp_OKbutton().click();
		logInfo("Click ok on evaluatioin version message");
		sleep(5);
		
		//Data
		Map<String, Object> map = new HashMap<>();	
		Object[] arg = {map};
		map.put("sourceType", sourceType);
		map.put("sourceUserEmail", sourceUserEmail);
		map.put("sourcePassword", sourcePassword);
		
		map.put("targetType", targetType);
		map.put("targetSharedDirectory", targetSharedDirectory);
		map.put("targetUserName", targetUserName);
		map.put("targetUserCN", targetUserCN);	
		
		map.put("category", "email");
		callScript("Transend.Transend", arg );
		
		map.put("category", "addressBook");
		callScript("Transend.Transend", arg );
		
		map.put("category", "calendar");
		callScript("Transend.Transend", arg );
		
		map.put("category", "task");
		callScript("Transend.Transend", arg );
			
		startSingleMigrationbutton().click();
		
		yesbutton().click();
		//okbutton().click();
			
//		HelperClass.oneBrowserSetup();
//		browser_htmlBrowser().loadUrl(webAdminIP);
//		browser_htmlBrowser().activate();
//	
//		//WebAdmin
//		webAdmin wa = new webAdmin();
//		wa.login(webAdminUserName, webAdminPassword);	
//		wa.createStorage("File System", name, "\\\\10.1.30.64\\TransendData\\"+name);	
//		wa.createStorageLocation( 
//				"Standard", 
//				name, 
//				"automation", 
//				name, "\\\\10.1.30.64\\TransendData\\"+name, 
//				"Administrator@BASE2012@First Organization@User"
//		);
//		wa.index(name, sourceUserEmail, indexName);
//		wa.waitForIndexing(indexName);
//		
//		
//		//Restart services
//		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
//		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
//		
//		//Login netmail search and new case
//		login("");
//		
//		/********************NEW CASE***************************/
//		button_newCasebutton().click();
//		logInfo("Clicked NewCase");
//		sleep(0.5);
//		//DATA
//		Object[] cmNewCase = {	name,
//								"nothing",
//								"New",
//								"Claim",
//								"06/13/14",
//								"06/13/14",
//								"General Liability",
//								"Workplace",
//								true,
//								name,
//								"netmail",
//								false,
//		};
//		
//		callScript("Case_Management.manageCase", cmNewCase);
//		login(name);
		
	}
	
	private void login(String caseName){
		//Login
			Object[] ls = {null,null, false};
			callScript("loginScript", ls);
			
		//Admin Login
			Object[] al = {caseName, "Super User"};
			callScript("adminLogin", al);
	}
	
	@Override
	public void onTerminate(){
//		transend.destroy();
	}
}

