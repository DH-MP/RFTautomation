package TC_827_NetmailSearch_Search;
import resources.TC_827_NetmailSearch_Search.TS_QuickSearchHelper;
import NetmailSearch_AdvanceSearch.QuickSearch;

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
public class TS_QuickSearch extends TS_QuickSearchHelper
{
	/**
	 * Script Name   : <b>TS_QuickSearch</b>
	 * Generated     : <b>Jul 16, 2014 2:42:09 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/16
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		Object[] ls = {null, null, false};
		callScript("loginScript", ls);
		
		Object[] al = {"post", "Super User"};
		callScript("adminLogin", al);

		QuickSearch qs = new QuickSearch();
		qs.search(dpString("searchTerm"));
		sleep(1);
		waitForloading();
		qs.validateQuickSearch(dpString("searchTerm"), dpBoolean("expectedResults"));
	}
}

