package TestCases.TC_Load;
import resources.TestCases.TC_Load.TS_SearchingLargeUserHelper;
import Case_Management.manageCase;
import NetmailSearch_AdvanceSearch.Message_SuperUser;
import NetmailSearch_AdvanceSearch.QuickSearch;
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
public class TS_SearchingLargeUser extends TS_SearchingLargeUserHelper
{
	/**
	 * Script Name   : <b>TS_SearchingLargeUser</b>
	 * Generated     : <b>Aug 13, 2014 10:37:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/13
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
//		NetmailLogin.login();
//		adminLogin.selectUserType("Super User");
//		
//		manageCase mc = new manageCase();
//		mc.setName("TS_SearchLargeUser");
//		mc.setLocations("LargeUser;LargeUser2");
//		mc.setTestMode(false);
//		mc.newCase();
		
//		//quickSearch test
//		QuickSearch qs = new QuickSearch();
//		qs.search(dpString("quickSearchTerm"));
//		qs.validateQuickSearch(dpString("quickSearchTerm"), true);
		
		//Advance Message Search
		Message_SuperUser msu = new Message_SuperUser();
//		msu.clearSearchResult();
		
		msu.setSubject("Be on top of her");
		msu.setSender("cristina");
		msu.setRecipient("pqwcgcl@netmail.com");
		msu.setBody("selection");
		msu.setSentDate1("");
		msu.setSentDate2("");
		msu.setRcvDate1("10/08/08");
		msu.setRcvDate2("10/09/08");
		msu.setPersonal("");
		msu.setCategory("");
		msu.inputAdvanceMessage();
		msu.search();
		
		
		
	}
}

