package TestCases.TC_Load;
import resources.TestCases.TC_Load.TS_MultiplePrintThousandItemsHelper;
import utilities.HelperClass;
import Case_Management.manageCase;
import NetmailSearch_General.Common;
import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;
import NetmailSearch_PrintAndExport.Export_SuperUser;
import NetmailSearch_PrintAndExport.Print_SuperUser;

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
public class TS_MultiplePrintThousandItems extends TS_MultiplePrintThousandItemsHelper
{
	/**
	 * Script Name   : <b>TS_MultiplePrintThousandItems</b>
	 * Generated     : <b>Jul 29, 2014 9:36:11 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/29
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		final int numberOfPrint = 3;
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		
		manageCase mc = new manageCase();
		mc.setName("TS_MultiplePrintThousandItems");
		mc.setLocations("ALS");
		mc.setUsers("smith@greg.dom2po1.dom2");
		mc.newCase();
		
		HelperClass.navigateLocation("ALS>smith>Mailbox");
		
		int messageCount = 1000;
			
		Common.preferencePageSize(200);
		
		while(messageCount > 0){
			TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			messageCount =  messageCount - results.length;
	
			TestObject[] headerColumns = HelperClass.getActiveTabBody()[0].find(atDescendant(".tag", "DIV", ".className", "x-grid3-header"), false);
			TestObject[] SelectAllcheckbox = headerColumns[0].find(atDescendant(".tag", "TD"), false);
			((GuiTestObject) SelectAllcheckbox[1]).click();
			
			Common.navigatePages(false, false, true, false);
		}
		
		int count = numberOfPrint; 
		while(count > 0){
			Print_SuperUser psu = new Print_SuperUser();
			psu.printSelectedInBackground(false);
			count--;
		}
		
		Export_SuperUser esu = new Export_SuperUser();
		esu.openExportManagementWindow();
		waitForloading();
		esu.waitForExport("Print");
		
		TestObject[] prints = esu.getExportObjects("Print");
		count = numberOfPrint;
		while(count>0){
			TestObject[] columns = prints[count-1].find(atDescendant(".tag", "TD"), false);
			String status = columns[3].getProperty(".text").toString().trim().toLowerCase();
			if(!status.contentEquals("success") | !status.contentEquals("success")){
				logError("one of the multiple prints failed");
			}
			count--;
		}
		
		count = numberOfPrint;
		while(count>0){
			prints = esu.getExportObjects("Print");
			esu.deleteExport(prints[0]);
			count--;
			waitForloading();
			esu.closeExportManagementWindow();
			esu.openExportManagementWindow();
			waitForloading();
		}

		

		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		mc.deleteCase("TS_MultiplePrintThousandItems");
		
		
	}
}

