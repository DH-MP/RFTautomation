package TestCases.TC_Load;
import resources.TestCases.TC_Load.TS_LargeParallelExportJobHelper;
import Case_Management.manageCase;
import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;
import NetmailSearch_PrintAndExport.Export_SuperUser;

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
public class TS_LargeParallelExportJob extends TS_LargeParallelExportJobHelper
{
	/**
	 * Script Name   : <b>TS_ParallelExportJob</b>
	 * Generated     : <b>Jul 16, 2014 4:03:15 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/16
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		Export_SuperUser esu = new Export_SuperUser();
		int parallel = 15;
		//Login
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		
		manageCase mc = new manageCase();
		mc.setName("LargeConcurrentExports");
		mc.setLocations("Export");
		mc.setTestMode(false);
		mc.newCase();
		waitForloading();
		
		for(int i=0; i<2; i++){
			html_addTab().click();
			waitForloading();
		}
		
		esu.setSearchTabs("1,2,3");
		esu.setItemOptions(1);
		esu.setExportTypeOption(2);
		
		for(int i = 0; i < parallel; i++){
			esu.closeExportManagementWindow();
			esu.setExportName("p"+i);
			esu.create();
			sleep(4);
		}
		
		int recheckInterval = 500;
		TestObject export = esu.getExportObject("p14");
		TestObject[] columns = export.find(atDescendant(".tag", "TD"), false);
		String status = columns[3].getProperty(".text").toString().trim().toLowerCase();
		while(!(status.contentEquals("success")|status.contentEquals("warning"))){
			try{
				esu.closeExportManagementWindow();
				sleep(recheckInterval);
				esu.openExportManagementWindow();
				waitForloading();
				export = esu.getExportObject("p14");
				columns = export.find(atDescendant(".tag", "TD"), false);
				status = columns[3].getProperty(".text").toString().trim().toLowerCase();
				if(status.contentEquals("error")){
					logError("exportFailed");
					stop();
				}
			}catch(Exception e){
				esu.closeExportManagementWindow();
				esu.openExportManagementWindow();
			}
		}
		
		for(int i = 2; i < 13; i++){
			try{		
				esu.deleteExport(esu.getExportObject("p"+i));
				waitForloading();
				esu.closeExportManagementWindow();
				sleep(0.5);
				esu.openExportManagementWindow();
			}catch(UnsupportedActionException e){
				esu.closeExportManagementWindow();
				esu.openExportManagementWindow();
			}
			waitForloading();
		}
	}
}

