package TestCases.TC_Load;
import resources.TestCases.TC_Load.TS_ParallelExportJobHelper;
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
public class TS_ParallelExportJob extends TS_ParallelExportJobHelper
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
		
		//Login
		NetmailLogin.login();
		
		//Admin Login
		adminLogin.superUser();
		adminLogin.selectCase("ExportXML");
		
		int parallel = 3;
		
		Export_SuperUser esu = new Export_SuperUser();
		esu.setSearchTabs("9");
		esu.setItemOptions(2);
		esu.setExportTypeOption(2);
		
		for(int i = 0; i < parallel; i++){
			esu.closeExportManagementWindow();
			esu.setExportName("p"+i);
			esu.create();
			sleep(4);
		}
		

		while(true){
			String statuses = "";
			String expectedStatuses = "";
			for(int i = 0; i < parallel; i++){
				TestObject export = esu.getExportObject("p"+i);
				statuses += export.find(atDescendant(".tag", "TD"), false)[3].getProperty(".contentText").toString()+" ";
				expectedStatuses += "exporting messages "; //Mid point
			}
//			System.out.println(expectedStatuses);
//			System.out.println(statuses);
			if(statuses.contentEquals(expectedStatuses)){
				logTestResult("All export are in parrallel", true);
				break;
			}else if(statuses.contains("success")){
				logTestResult("One or more export finished before all export reach status 'export messages' ", false);
				break;
			} 
		}
		
		for(int i = 0; i < parallel; i++){
			esu.cancelExport("p"+i);
		}
	}
}

