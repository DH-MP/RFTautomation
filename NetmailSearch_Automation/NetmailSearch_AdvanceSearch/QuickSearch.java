package NetmailSearch_AdvanceSearch;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Matcher.*;

import resources.NetmailSearch_AdvanceSearch.QuickSearchHelper;
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
public class QuickSearch extends QuickSearchHelper
{
	/**
	 * Script Name   : <b>advanceSearch</b>
	 * Generated     : <b>Jun 6, 2013 9:58:53 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/06
	 * @author Administrator
	 * Due to the hierarchy of the site being different in normal user login,
	 * will NOT work with admin advance search.
	 */

	private String 	searchTerm; 

	public void testMain(Object[] args) {
		validateQuickSearch("test categories", true);
	}
	
	
	
	public void search(String searchTerm){
		text_quickSearchField0().click();
		browser_htmlBrowser().inputChars(searchTerm);
		browser_htmlBrowser().inputKeys("{ENTER}");
	}
	public void validateQuickSearch(String searchTerm, Boolean expectedResults)
	{
		TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		if(!expectedResults && results.length > 0){
			//No Result Case
			logError("Expected no result from query");
			return;
		}else if(expectedResults && results.length > 0){
				logInfo("Start validating the results");
				
			//Validate Queries
			int msgNum = 1;
			boolean allMatching = false;
			
			for(TestObject result : results){	
				//Open Message
				((GuiTestObject)result).hover();
				((GuiTestObject)result).doubleClick();
				sleep(2);
				waitForloading();
				
				//Subject VF
				String subject = text_mbSubject().getProperty(".value").toString().toLowerCase();

				//From|Sender VF
				String sender = text_mbFrom().getProperty(".value").toString().toLowerCase();

				//To|Recipient VF
				String recipient = text_mbTo().getProperty(".value").toString().toLowerCase();
				
				//Body message VF
				String messageBody = document_messageBody().getProperty(".text").toString().toLowerCase();
					
				String cc = text_cc().ensureObjectIsVisible() ? text_cc().getText().toLowerCase() : "";
				
				String bc = text_bc().ensureObjectIsVisible() ? text_bc().getText().toLowerCase() : "";
				
				searchTerm = searchTerm.toLowerCase();
				allMatching = 		subject.contains(searchTerm) 	| 
									sender.contains(searchTerm) 	|
									recipient.contains(searchTerm) 	|
									messageBody.contains(searchTerm)|
									cc.contains(searchTerm) 		|
									bc.contains(searchTerm);
								
				//Close Message box
				html_mbCloseButtonDiv().click();
				msgNum++;
				sleep(2);		
				
				if(!allMatching){
					break;
				}
			}
			logTestResult("All message relevant is relevant to  quick Search Term", allMatching);
			
				
		
		}else if(expectedResults && results.length <= 0){
			logError("Expected result but none returned");
			return;
		}
	}
		
	
}

