package TC_978_ExportPortableNetmailSearch;
import java.util.HashMap;

import resources.TC_978_ExportPortableNetmailSearch.TS_987_AdvanceSearch_Messages_NetmailLiteHelper;
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
public class TS_987_AdvanceSearch_Messages_NetmailLite extends TS_987_AdvanceSearch_Messages_NetmailLiteHelper
{
	/**
	 * Script Name   : <b>TS_987_AdvanceSearch_Messages_NetmailLite</b>
	 * Generated     : <b>Oct 1, 2013 12:38:49 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/01
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(2);
		waitForloading();
		
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
		callScript("NetmailSearch_AdvanceSearch.Message_NormalUser", asmnu);
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(1);
		waitForloading();
		
	}
}

