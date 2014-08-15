package TestCases.TC_Load;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

import resources.TestCases.TC_Load.TS_SearchingLargeUserHelper;
import utilities.HelperClass;
import Case_Management.manageCase;
import NetmailSearch_AdvanceSearch.Audit_SuperUser;
import NetmailSearch_AdvanceSearch.MessageWordListTab_SuperUser;
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
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		
		manageCase mc = new manageCase();
		mc.setName("TS_SearchLargeUser");
		mc.setLocations("LargeUser;LargeUser2");
		mc.setTestMode(false);
		mc.newCase();
		
		if(dpBoolean("testQuickSearch")){
			//quickSearch test
			QuickSearch qs = new QuickSearch();
			qs.search(dpString("quickSearchTerm"));
			waitForloading();
			qs.validateQuickSearch(dpString("quickSearchTerm"), true);
		}
		
		//Advance Message Search
		Message_SuperUser msu = new Message_SuperUser();
		msu.clearSearchResult();
		waitForloading();
		msu.openAdvanceSearchMessage();
		
		if(dpBoolean("testAdvanceMessage")){
			msu.setSubject(dpString("subject"));
			msu.setSender(dpString("sender"));
			msu.setRecipient(dpString("recipient"));
			msu.setBody(dpString("body"));
			msu.setSentDate1(dpString("sentDate1"));
			msu.setSentDate2(dpString("sentDate2"));
			msu.setRcvDate1(dpString("rcvDate1"));
			msu.setRcvDate2(dpString("rcvDate2"));
			msu.setPersonal(dpString("personal"));
			msu.setCategory(dpString("category"));
			msu.inputAdvanceMessage();
			msu.search();
			msu.validateSearchResult();
		}
		
		
		//WordList
		msu.clearSearchResult();
		msu.openAdvanceSearchMessage();
		
		MessageWordListTab_SuperUser mwltsu = new MessageWordListTab_SuperUser();
		mwltsu.openWordListTab();
		String a = dpString("wordList");
		mwltsu.inputWordList(a, ";");
		mwltsu.search();
		waitForloading();
		mwltsu.validateSearchResult(";");
		
//		Audit
		msu.clearSearchResult();
		Audit_SuperUser asu = new Audit_SuperUser();
		for(String checkBoxName : dpString("auditCheckBoxesName").split(";")){
			switch (checkBoxName) {
			case "created":
				asu.setCreated(true);
				break;
			case "viewed":
				asu.setViewed(true);
				break;
			case "exported":
				asu.setExported(true);
				break;
			case "forwarded":
				asu.setForwarded(true);
				break;
			case "republished":
				asu.setRepublished(true);
				break;
			case "printed":
				asu.setPrinted(true);
				break;
			case "deletedAdmin":
				asu.setDeletedAdmin(true);
				break;
			case "deletedUser":
				asu.setDeletedUser(true);
				break;
			case "commented":
				asu.setCommented(true);
				break;
			case "flagged":
				asu.setFlagged(true);
				break;
			case "retained":
				asu.setRetained(true);
				break;
			case "sentForPrinting":
				asu.setSentForPrinting(true);
				break;
			case "sentTo":
				asu.setSentTo(true);
				break;
			case "migrated":
				asu.setMigrated(true);
				break;
			case "stubbed":
				asu.setStubbed(true);
				break;
			case "relevant":
				asu.setRelevant(true);
				break;
			case "privileged":
				asu.setPrivileged(true);
				break;
			case "workProduct":
				asu.setWorkProduct(true);
				break;	
			case "tagFlagged":
				asu.setTagFlagged(true);
				break;
			default:
				break;
			}
		}	
		asu.openAdvanceSearchAudit();
		asu.setAuditor(dpString("auditAuditor"));
		asu.setComment(dpString("auditComment"));
		asu.inputAudit();
		asu.search();
		waitForloading();
		asu.validateResults();
		
		if(dpString("expectedAuditResults")!=null && !dpString("expectedAuditResults").isEmpty()){
			TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);	
			vpManual("expectedAuditResult", dpString("expectedAuditResults"), results.length+"").performTest();
		}
		
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		mc.deleteCase("TS_SearchLargeUser");
	}
}

