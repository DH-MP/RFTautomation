package TestCases.TC_General;
import resources.TestCases.TC_General.TS_ItemCountsHelper;
import utilities.HelperClass;
import NetmailSearch_General.Common;
import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;

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
public class TS_ItemCounts extends TS_ItemCountsHelper
{
	/**
	 * Script Name   : <b>TS_ItemCounts</b>
	 * Generated     : <b>Aug 7, 2014 1:20:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/07
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("GVautomation");
		waitForloading();
		Common.preferencePageSize(200);
		TestObject folders = HelperClass.navigateLocation("GVautomation>rft");

		
	}
}

