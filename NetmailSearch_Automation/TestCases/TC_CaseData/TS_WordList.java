package TestCases.TC_CaseData;
import org.apache.commons.lang3.StringEscapeUtils;

import resources.TestCases.TC_CaseData.TS_WordListHelper;
import Case_Management.manageCase;
import NetmailSearch_AdvanceSearch.MessageWordListTab_SuperUser;
import NetmailSearch_AdvanceSearch.Message_SuperUser;
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
public class TS_WordList extends TS_WordListHelper
{
	/**
	 * Script Name   : <b>TS_CaseInfoUnaffected</b>
	 * Generated     : <b>Jul 23, 2014 1:38:26 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/23
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		
		manageCase mc = new manageCase();
		mc.setName("TS_WordList");
		mc.setLocations("ALS");
		mc.newCase();
		
		
		MessageWordListTab_SuperUser awlse = new MessageWordListTab_SuperUser();
		Message_SuperUser msu = new Message_SuperUser();
		
		msu.openAdvanceSearchMessage();
		awlse.inputWordList("blubber blubberrer blubberring");
		awlse.search();
		
		msu.openAdvanceSearchMessage();
		msu.setSubject("nothing but void");
		msu.inputAdvanceMessage();
		msu.closeAdvanceSearchMessage();
		
		mc.saveCurrentCase();
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("TS_WordList");
		
		msu.openAdvanceSearchMessage();
		msu.setSubject("");
		msu.setBody("boooody");
		msu.inputAdvanceMessage();
		
		awlse.openWordListTab();
		
		StringEscapeUtils seu = new StringEscapeUtils();
		String source = "The less than sign (<) and ampersand (&) must be escaped before using them in HTML";
		String escaped = seu.escapeHtml4(awlse.text_wordlist().getText());
		vpManual("wordListUnaffected", "blubber\nblubberrer\nblubberring\n", escaped).performTest();
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		mc.deleteCase("TS_WordList");
	}
}

