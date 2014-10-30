package TC_831_NetmailSearch;
import resources.TC_831_NetmailSearch.TS_996Helper;
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
public class TS_996 extends TS_996Helper
{
	/**
	 * Script Name   : <b>TS_996</b>
	 * Generated     : <b>Jun 4, 2013 4:12:11 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/04
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
	//Login
		NetmailLogin.login(dpString("userName"), dpString("password"), dpBoolean("failToLogin"));
		if(dpBoolean("failToLogin"))
			return;

	//Admin Login
		if(dpBoolean("hasPrivilege")){
			adminLogin.selectUserType("Normal User");
//			adminLogin.selectCase(dpString("caseListName"));
		}else{				
//				//ALS MailBox Default
//				Property[] ps = new Property[3];
//				ps[0] = new Property(".class", "Html.A");
//				ps[1] = new Property("class", "x-tree-node-anchor");
//				ps[2] = new Property(".text", "ALS");
//				TestObject folderTree = browser_htmlBrowser().find(atDescendant(".tag", "DIV", "id", "folderTree0"), true)[0];
//				GuiTestObject alsLink = (GuiTestObject) folderTree.find(atDescendant(ps), true)[0];
//				GuiTestObject expander = (GuiTestObject) alsLink.getParent().getChildren()[1];
//				expander.click();
//				sleep(3);	
		}
		
	//UserDropDown
			logInfo("Checking preference menu if it has all button");
			button_adminbutton().click();
			vpManual("UserDropDown_Help", true, link_userDD_Help().exists() ).performTest();
			vpManual("UserDropDown_About", true, link_userDD_About().exists() ).performTest();
			vpManual("UserDropDown_Logout", true, link_userDD_Logout().exists() ).performTest();
			
			browser_htmlBrowser().click();
			button_preferencesbutton().click();
			vpManual("UserDropDown_Language", true, link_userDD_Language().exists() ).performTest();
			vpManual("UserDropDown_Theme", true, link_userDD_Theme().exists() ).performTest();
			
			link_userDD_Language().hover();
			vpManual("UserDropDown_English", true, link_userDD_English().exists() ).performTest();
			vpManual("UserDropDown_French", true, link_userDD_Francais().exists() ).performTest();
			vpManual("UserDropDown_FrenchCanadian", true, link_userDD_FrenchCanadian().exists() ).performTest();
			
			link_userDD_Theme().hover();
			vpManual("UserDropDown_Default", true, link_userDD_Default().exists() ).performTest();
			vpManual("UserDropDown_French", true, link_userDD_Blue().exists() ).performTest();
			vpManual("UserDropDown_FrenchCanadian", true, link_userDD_Accessibility().exists() ).performTest();
		
	//Highlight
			logInfo("Search the term: "+dpString("quickSearchTerm"));
			sleep(2);
			String searchTerm = dpString("quickSearchTerm");
			text_quickSearchField0().click();	
			browser_htmlBrowser().inputKeys("^a{BKSP}"+searchTerm+"{ENTER}");
			sleep(4);
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
			link_highlightSearchTerm().click();
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
			sleep(1);
			link_highlightSearchTerm().click();
			text_quickSearchField0().click();
			browser_htmlBrowser().inputKeys("^a{BKSP}{ENTER}");
			sleep(5);
			
	//Number of Display Result
			TestObject[] results;			
			button_preferencesbutton().click();
			link_preference_pageSize().hover();
			link_pageSize_100().click();
			sleep(2);
			waitForloading();
			results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			vpManual("Result_Displayed_100", true, results.length<=100).performTest();
			
			button_preferencesbutton().click();
			link_preference_pageSize().hover();
			link_pageSize_200().click();
			sleep(2);
			waitForloading();
			results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			vpManual("Result_Displayed_200", true, results.length<=200).performTest();
			
			button_preferencesbutton().click();
			link_preference_pageSize().hover();
			link_pageSize_50().click();
			sleep(2);
			waitForloading();
			results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			vpManual("Result_Displayed_50", true, results.length<=50).performTest();
			
			button_preferencesbutton().click();
			link_preference_pageSize().hover();
			link_pageSize_20().click();
			sleep(2);
			waitForloading();
			results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			vpManual("Result_Displayed_20", true, results.length<=20).performTest();
			unregisterAll();
			
	}
}

