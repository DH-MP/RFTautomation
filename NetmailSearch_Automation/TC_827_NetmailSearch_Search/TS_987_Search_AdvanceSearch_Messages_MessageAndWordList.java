package TC_827_NetmailSearch_Search;
import java.util.HashMap;

import resources.TC_827_NetmailSearch_Search.TS_987_Search_AdvanceSearch_Messages_MessageAndWordListHelper;
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
public class TS_987_Search_AdvanceSearch_Messages_MessageAndWordList extends TS_987_Search_AdvanceSearch_Messages_MessageAndWordListHelper
{
	/**
	 * Script Name   : <b>TS_987_Search_AdvanceSearch_Messages</b>
	 * Generated     : <b>Jul 11, 2013 3:57:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/07/11
	 * @author Administrator
	 */
	
	private boolean skipLogin = false;

	public void testMain(Object[] args) 
	{
		
		if(!skipLogin){
			//Login
			NetmailLogin.login();
			adminLogin.selectUserType(dpString("userType"));
			adminLogin.selectCase(dpString("caseName"));
		}
				
		//Advance Search
		HashMap<String, String> query = new HashMap<String, String> ();
		query.put("subject", dpString("subject")); 
		query.put("sender", dpString("sender")); 
		query.put("recipient", dpString("recipient"));
		query.put("body", dpString("body"));
		query.put("sendDate1", dpString("sendDate1")); 
		query.put("sendDate2",dpString("sendDate2"));
		query.put("rcvDate1", dpString("rcvDate1"));
		query.put("rcvDate2", dpString("rcvDate2"));  
		query.put("personal",dpString("personal")); 
		query.put("category",dpString("category"));
		
		HashMap<String, Boolean> booleanQuery = new HashMap<String, Boolean> ();
		booleanQuery.put("typeMail", dpBoolean("checkTypeMail")); 
		booleanQuery.put("typeAppointment", dpBoolean("checkTypeAppointment"));
		booleanQuery.put("typeTask", dpBoolean("checkTypeTask"));			
		booleanQuery.put("typeNote", dpBoolean("checkTypeNote"));
		booleanQuery.put("searchEmbedded",dpBoolean("checkSearchEmbedded"));
		booleanQuery.put("invalidRcvDateRange", dpBoolean("invalidRcvDateRange"));
		booleanQuery.put("invalidSentDateRange", dpBoolean("invalidSendDateRange"));
		booleanQuery.put("expectedResults", dpBoolean("expectedResults"));
		Object[] asmnu = {query, booleanQuery};
		callScript("NetmailSearch_AdvanceSearch.Message_SuperUser", asmnu);
		
		if(dpString("wordList")!= null && !dpString("wordList").isEmpty() ){
			//Advance Message WordList
			Message_SuperUser msu = new Message_SuperUser();
			msu.openAdvanceSearchMessage();
			
			MessageWordListTab_SuperUser mwltsu = new MessageWordListTab_SuperUser();
			mwltsu.openWordListTab();
			
			String a = dpString("wordList");
			mwltsu.inputWordList(a, ";");
			mwltsu.search();
			waitForloading();
			if(dpInt("numOfExpectedResults")>-1){
				mwltsu.validateExpectedResults(dpInt("numOfExpectedResults"));
			}
			mwltsu.validateSearchResult(";");
		}	
	}
	
	public void setSkipLogin(boolean skipLogin) {
		this.skipLogin = skipLogin;
	}
}

