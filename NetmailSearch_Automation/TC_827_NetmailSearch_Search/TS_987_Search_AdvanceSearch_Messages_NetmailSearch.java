package TC_827_NetmailSearch_Search;
import resources.TC_827_NetmailSearch_Search.TS_987_Search_AdvanceSearch_Messages_NetmailSearchHelper;
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
public class TS_987_Search_AdvanceSearch_Messages_NetmailSearch extends TS_987_Search_AdvanceSearch_Messages_NetmailSearchHelper
{
	/**
	 * Script Name   : <b>TS_987_Search_AdvanceSearch_Messages_NetmailSearch</b>
	 * Generated     : <b>Jul 15, 2013 9:51:21 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/07/15
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
//		NetmailLogin.login();
//		adminLogin.selectUserType(dpString("userType"));
//		adminLogin.selectCase(dpString("caseName"));
//				
		Object[] aSMN = {	dpString("field1"),
							dpString("field2"), 
							dpString("field3"), 
							dpString("value1"),
							dpString("value2"), 
							dpString("value3"), 
							dpString("logic"), 
							dpString("listOfApproximateWords"),
							dpString("customSearch"),
							dpInt("customSearchNumberOfResults"),
							dpString("customSearchResultTitles"),
						};
		
		callScript("NetmailSearch_AdvanceSearch.MessageNetmailTab_SuperUser", aSMN);
			
	}
}

