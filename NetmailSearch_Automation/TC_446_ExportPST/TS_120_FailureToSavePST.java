package TC_446_ExportPST;
import java.io.File;
import java.io.IOException;

import resources.TC_446_ExportPST.TS_120_FailureToSavePSTHelper;
import utilities.HelperClass;

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
public class TS_120_FailureToSavePST extends TS_120_FailureToSavePSTHelper
{
	/**
	 * Script Name   : <b>TS_120_FailureToSavePST</b>
	 * Generated     : <b>Aug 27, 2013 11:49:13 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/08/27
	 * @author Administrator
	 */
	
	private String exportName = "";
	public void testMain(Object[] args) 
	{		
		
		exportName = dpString("exportName");
		HelperClass.CloseAllBrowsers();
		//Login
		Object[] ls = {dpString("username"), dpString("password"), false, true};
		callScript("loginScript", ls);
		//AdminLogin
		Object[] al = {dpString("caseName"), dpString("userType"), true};
		callScript("adminLogin", al);
		
		
		button_exportCasebutton().click();
		sleep(1);
		link_exportManagement().click();
		sleep(2);
		
		//Open export: Newest export are always first
		logTestResult("Export Management Window is open", html_exportList().ensureObjectIsVisible());
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", ".text", new RegularExpression(".*"+exportName+".*", false)), true);
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		((GuiTestObject)columns[columns.length-1]).click();
		
		
		//Download
		String file = dpString("PSTFileName");
		Property[] rowProperty = {	new Property(".tag", "TABLE"),
				new Property(".text", new RegularExpression("(?i).*"+file+".*", false)),
				new Property("class", "x-grid3-row-table"),
		};
		TestObject[] exportFiles = html_exportFileList().find(atDescendant(rowProperty), true);
		if(exportFiles.length >= 1){
			((GuiTestObject)exportFiles[0]).click();
			((GuiTestObject)exportFiles[0]).doubleClick();
			logInfo("Selected < "+ file +" > file to download");
			sleep(10);
		}else{
			logError("Could not find export file by the name < "+ file +" >");
		}
		
		HelperClass.ieNotificationElement("Save").click(atPoint(65,10));
		logInfo("click browser's save dropdown");
		sleep(1);
		saveAsmenuItem().click();	
		logInfo("clicked on save as on dropdown");
		
		addressCUsersAdministratorDown().doubleClick(atPoint(10,10));
		sleep(1);
		saveAswindow().inputKeys("C:\\Users\\Administrator\\Downloads\\readOnly"+"{ENTER}");
		readOnlyContinuebutton().click();
		logInfo("Clicked continue on read only error message");
		readOnlyClosebutton().click();
		logInfo("Clicked close read only error message");
		saveAsCancelbutton().click();
		logInfo("Clicked cancel on save as window");
		
		HelperClass.CloseAllBrowsers();
		unregisterAll();
	}
}

