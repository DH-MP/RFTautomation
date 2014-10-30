package TC_827_NetmailSearch_Search;
import resources.TC_827_NetmailSearch_Search.TS_990_AdvanceSearch_AttachementsHelper;
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
public class TS_990_AdvanceSearch_Attachements extends TS_990_AdvanceSearch_AttachementsHelper
{
	/**
	 * Script Name   : <b>TS_990_AdvanceSearch_Attachements</b>
	 * Generated     : <b>Jul 18, 2013 1:16:58 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/07/18
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		NetmailLogin.login();
		adminLogin.selectUserType(dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		
		Object[] aSASU = {dpString("fileName"), dpString("fileSize1"), dpString("fileSize2"), dpBoolean("testBody"), dpBoolean("expectedResults")};
		callScript("NetmailSearch_AdvanceSearch.Attachment_SuperUser", aSASU);
	}
}

