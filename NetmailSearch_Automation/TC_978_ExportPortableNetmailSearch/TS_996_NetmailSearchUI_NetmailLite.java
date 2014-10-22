package TC_978_ExportPortableNetmailSearch;
import resources.TC_978_ExportPortableNetmailSearch.TS_996_NetmailSearchUI_NetmailLiteHelper;
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
public class TS_996_NetmailSearchUI_NetmailLite extends TS_996_NetmailSearchUI_NetmailLiteHelper
{
	/**
	 * Script Name   : <b>TS_996_NetmailSearchUI_NetmailLite</b>
	 * Generated     : <b>Oct 11, 2013 11:11:51 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/11
	 * @author Administrator
	 * Prerequisite: Case must have more than 200 message
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
		
	//UserDropDown
		logInfo("Checking user dropdown menu if it has all button");
		button_adminbutton_preclick().click();
		vpManual("UserDropDown_Help", true, link_userDD_Help().exists() ).performTest();
		vpManual("UserDropDown_About", true, link_userDD_About().exists() ).performTest();
		vpManual("UserDropDown_Logout", true, link_userDD_Logout().exists() ).performTest();
		button_adminbutton_preclick().click();
		
		button_preferencesbutton().click();
		link_preference_Language().hover();
		vpManual("UserDropDown_English", true, link_preference_English().exists() ).performTest();
		vpManual("UserDropDown_French", true, link_preference_Francais().exists() ).performTest();
		vpManual("UserDropDown_FrenchCanadian", true, link_preference_FrenchCanadian().exists() ).performTest();
		
		link_userDD_Theme().hover();
		vpManual("UserDropDown_Default", true, link_userDD_Default().exists() ).performTest();
		vpManual("UserDropDown_French", true, link_userDD_Blue().exists() ).performTest();
		vpManual("UserDropDown_FrenchCanadian", true, link_userDD_Accessibility().exists() ).performTest();
	
	//Highlight
		logInfo("Search the term: "+dpString("quickSearchTerm"));
		sleep(3);
		String searchTerm = dpString("quickSearchTerm");
		text_quickSearchField0().click();	
		browser_htmlBrowser().inputKeys("^a{BKSP}"+searchTerm+"{ENTER}");
		sleep(2);
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
		logInfo("Clicked preference dropdown");
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
		logInfo("Clicked preference dropdown");
		link_highlightSearchTerm().click();
		logInfo("Clicked highlight");
		text_quickSearchField0().click();
		browser_htmlBrowser().inputKeys("^a{BKSP}{ENTER}");
		sleep(1);
		
	//Number of Display Result
		TestObject[] results;
		TestObject visibleResultContainer;
		button_preferencesbutton().click();
		link_preference_pageSize().hover();
		link_pageSize_100().click();
		sleep(3);
		visibleResultContainer= HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		vpManual("Result_Displayed_100", true, results.length<=100).performTest();
		
		button_preferencesbutton().click();
		link_preference_pageSize().hover();
		link_pageSize_200().click();
		sleep(5);
		visibleResultContainer= HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		vpManual("Result_Displayed_200", true, results.length<=200).performTest();
		
		button_preferencesbutton().click();
		link_preference_pageSize().hover();
		link_pageSize_50().click();
		sleep(2);
		visibleResultContainer= HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		vpManual("Result_Displayed_50", true, results.length<=50).performTest();
		
		button_preferencesbutton().click();
		link_preference_pageSize().hover();
		link_pageSize_20().click();
		sleep(2);
		visibleResultContainer= HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		vpManual("Result_Displayed_20", true, results.length<=20).performTest();
		unregisterAll();
	}
	
	
	
	@Override
	public void onTerminate(){
		table_optionsButton().click();
		link_userDD_Logout().click();
		button_yesbutton().click();
		yesbutton().click();
	}
}

