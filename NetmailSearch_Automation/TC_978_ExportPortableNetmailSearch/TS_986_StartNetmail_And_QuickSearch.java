package TC_978_ExportPortableNetmailSearch;
import resources.TC_978_ExportPortableNetmailSearch.TS_986_StartNetmail_And_QuickSearchHelper;
import utilities.HelperClass;

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
public class TS_986_StartNetmail_And_QuickSearch extends TS_986_StartNetmail_And_QuickSearchHelper
{
	/**
	 * Script Name   : <b>TS_987_QuickSearch_NetmailLite</b>
	 * Generated     : <b>Oct 1, 2013 10:48:16 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/01
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		NetmailLogin.login();
		adminLogin.selectUserType(dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		
		Object[] startNetmail = {	dpString("searchTabIndex"), 
									dpInt("itemOption"),
									dpInt("exportTypeOption"),
		};
		callScript("TC_978_ExportPortableNetmailSearch.startNetmailPortable", startNetmail);

	//Highlight
		logInfo("Search the term: "+dpString("quickSearchTerm"));
		sleep(3);
		String searchTerm = dpString("quickSearchTerm");
		text_quickSearchField0().click();
		logInfo("Clicked quick search field");
		browser_htmlBrowser().inputKeys("^a{BKSP}"+searchTerm+"{ENTER}");
		logInfo("entered < "+searchTerm+" >");
		sleep(2);
		waitForloading();
		
		TestObject visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel(\\s)*$",false)))[0];
		TestObject[] results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		
		boolean vpNumResult = vpManual("vpNumOfResults", dpInt("expectedNumOfResults"), results.length).performTest();
		if(dpInt("expectedNumOfResults") == 0 | !vpNumResult){
		}else{
			TestObject[] highlightedTexts = find(atDescendant(".class", "Html.SPAN", "class", "tma-highlight"), false);
			if(vpManual("Text_isHighlighted", true, highlightedTexts.length>0).performTest()){
				for( TestObject highlighted : highlightedTexts){
					String text = ((String) highlighted.getProperty(".text")).trim();
					if(!text.matches("(?i)"+searchTerm)){
						vpManual("SearchTerm_Matches_HighlightedText", text, searchTerm).performTest();
					}
				}
			}
		
		
			button_preferencesbutton().click();
			logInfo("Clicked preference");
			link_highlightSearchTerm().click();
			logInfo("Clicked highlight");
			sleep(0.5);
			if(highlightedTexts.length !=0){
				for(TestObject highlighted : highlightedTexts){
					if(((GuiTestObject)highlighted).ensureObjectIsVisible()){
						vpManual("Expected_no_higlighted_term", false, ((GuiTestObject)highlighted).ensureObjectIsVisible()).performTest();
						break;
					}		
				}
				vpManual("Expected_no_higlighted_term", false, ((GuiTestObject)highlightedTexts[0]).ensureObjectIsVisible()).performTest();
			}
		
		
			button_preferencesbutton().click();
			logInfo("Clicked preference");
			link_highlightSearchTerm().click();
			logInfo("Clicked highlight");
			text_quickSearchField0().click();
			logInfo("Clicked quick search field");
			browser_htmlBrowser().inputKeys("^a{BKSP}{ENTER}");
			sleep(1);		
		}
	}
	
//	@Override
//	public void onTerminate(){
//		table_optionsButton().click();
//		link_logOut().click();
//		button_yesbutton().click();
//		yesbutton().click();
//	}
}

