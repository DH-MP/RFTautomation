package TestCases.TC_CaseData;
import resources.TestCases.TC_CaseData.TS_ExportDataHelper;
import Case_Management.manageCase;
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
public class TS_ExportData extends TS_ExportDataHelper
{
	/**
	 * Script Name   : <b>TS_ExportData</b>
	 * Generated     : <b>Jul 18, 2014 4:19:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/18
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		String exportName = "Hodor";
		String caseName = "TS_ExportData";
		//Login
		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		//Admin Login
		Object[] al = {null, "Super User"};
		callScript("adminLogin", al);
		
		manageCase mc = new manageCase();
		mc.setTestMode(false);
		mc.setName(caseName);
		mc.setLocations("ALS");
		mc.setUsers("First, Last@Last.LongDom-po1.Test Domain with 32 char name lo");
		mc.newCase();
		
		
		Export_SuperUser esu = new Export_SuperUser();
		esu.setItemOptions(1);
		esu.setExportTypeOption(2);
		esu.setExportName(exportName);
		esu.create();
		waitForloading();
		
		TestObject export = esu.getExportObject(exportName);
		TestObject[] columns = export.find(atDescendant(".tag", "TD"), false);
		vpManual("correct_export_caseName", caseName, columns[1].getProperty(".text")).performTest();
		esu.closeExportManagementWindow();
		
		mc.setName(caseName+"EDITED");
		mc.editCase();
		
		esu.openExportManagementWindow();
		sleep(5);
		export = esu.getExportObject(exportName);
		columns = export.find(atDescendant(".tag", "TD"), false);
		vpManual("correct_export_caseName", caseName+"EDITED", columns[1].getProperty(".text")).performTest();
		esu.closeExportManagementWindow();
		
		
		
		//Login
		callScript("loginScript", ls);
		
		//Admin Login
		callScript("adminLogin", al);
		mc.deleteCase("TS_ExportDataEDITED");
		mc.openCase("intern_mike");
		waitForloading();
		sleep(2);
		
		esu.openExportManagementWindow();
		TestObject a = esu.getExportObject(exportName);
		logTestResult("Export removed on delete case", a==null);
		
	}
}

