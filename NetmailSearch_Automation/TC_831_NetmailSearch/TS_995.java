package TC_831_NetmailSearch;
import java.util.Arrays;

import resources.TC_831_NetmailSearch.TS_995Helper;
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
public class TS_995 extends TS_995Helper
{
	/**
	 * Script Name   : <b>TS_995</b>
	 * Generated     : <b>May 29, 2013 3:15:30 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/29
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
//Login
		HelperClass.CloseAllBrowsers();
		sleep(2);
		HelperClass.oneBrowserSetup();
		sleep(5);
		browser_htmlBrowser().deleteCookies();
		HelperClass.CloseAllBrowsers();
		sleep(2);
		HelperClass.oneBrowserSetup();
		sleep(5);

		NetmailLogin.login(dpString("userName"), dpString("password"), dpBoolean("failToLogin"));
		if(dpBoolean("failToLogin"))
			return;

//Admin Login
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase(dpString("caseListName"));

//Check user dropdown
		logInfo("Check User dropdown menu");
		button_preferencesbutton().click();
		link_userDropDown_Language().hover();
		sleep(0.5);
		//html_extGen95().performTest(IE_userDropDown_languageDropDownVP());
		link_userDropDown_Theme().hover();
		sleep(0.5);
		//html_extGen95_2().performTest(userdropdown_THEMEVP());
		
//CaseManagementToolBar
		logInfo("Check if edit case button works");
		button_editCasebutton().click();
		sleep(1);
		button_newCase_Cancelbutton().click();
		
		logInfo("Check if new case button works");		
		button_newCasebutton().click();
		sleep(1);
		button_newCase_Cancelbutton().waitForExistence(1000, DISABLED);
		button_newCase_Cancelbutton().click();
		
		logInfo("Check if casemanagement button works");
		button_caseManagementbutton().click();
		button_caseList_Cancelbutton().click();
	
		logInfo("Select the case name <"+dpString("quickCaseListName")+"> under quick case list dropdown");
		text_quickCaseComboBox().click();
		html_quickCaseList2().hover();
		sleep(5);
		
		RegularExpression regexp = new RegularExpression("x-combo-list-item( x-combo-selected)*", false);
		TestObject[] quickCase = html_quickCaseList2().find(atDescendant(".className", regexp, ".contentText" , dpString("quickCaseListName")), false);
		((GuiTestObject) quickCase[0]).hover();
		quickCase = html_quickCaseList2().find(atDescendant(".className", regexp, ".contentText" , dpString("quickCaseListName")), false);
		((GuiTestObject) quickCase[0]).click();
		sleep(3);
		waitForloading();

		
//Testing Highlight and quicksearch
		testHighlightAndQuickSearch(dpString("quickSearchTerm"), dpInt("expectedNumberOfResults"));
		
//Test Pagination: Number of Display Result for 3 pages
		testPagination();

	}
	
	public void testPagination(){
		logInfo("Checking for 3 pages if page size are correct for 20, 50, 100, 200");
		int numberOfPages = 3;
		
		testNumberOfResult(numberOfPages, 20);
		
		button_preferencesbutton().click();
		link_pageSize().hover();
		link_page100().click();
		sleep(6);
		waitForloading();
		testNumberOfResult(numberOfPages, 100);

		button_preferencesbutton().click();
		link_pageSize().hover();
		link_page200().click();
		sleep(6);
		waitForloading();
		testNumberOfResult(numberOfPages, 200);

		button_preferencesbutton().click();
		link_pageSize().hover();
		link_page50().click();
		sleep(6);
		waitForloading();
		testNumberOfResult(numberOfPages, 50);
		
		//Put back default.
		button_preferencesbutton().click();
		link_pageSize().hover();
		link_page20().click();
	}
	public void testHighlightAndQuickSearch(String quickSearchTerm, int expectedNumberOfResults){
		logInfo("Search the term: "+quickSearchTerm);
		sleep(5);
		String searchTerm = quickSearchTerm;
		text_quickSearchField0().click();
		browser_htmlBrowser().inputKeys(searchTerm+"{ENTER}");
		sleep(6);
		
		if(expectedNumberOfResults>0){
			
			String[] numOfResults = ((String)html_displayingXofX().getProperty(".text")).split("of");
			vpManual("Expected_NumberOfResults", expectedNumberOfResults , Integer.parseInt(numOfResults[1].toString().trim())).performTest();
			
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
			link_highlight_extComp1510().click();
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
			link_highlight_extComp1510().click();
			sleep(1);
		}else{
			TestObject[] results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			vpManual("NoMessages", 0, results.length).performTest();
		}
		
		text_quickSearchField0().click();
		text_quickSearchField0().doubleClick();
		browser_htmlBrowser().inputKeys("^a{DELETE}{ENTER}");
		sleep(2);
		waitForloading();
	}
	private void testNumberOfResult(int numberOfPage, int pageSize){

		TestObject[] results;
		boolean valid;
		int count = 1, From = 1, To = 0;
		while(count < numberOfPage){
			logInfo("Checking page size on Page"+count);
			To+=pageSize;		
			results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			vpManual("Result_Displayed_"+pageSize, pageSize, results.length).performTest();
			valid = ((String)html_displayingXofX().getProperty(".text")).matches(".*Displaying "+From+" - "+To+" of (\\d)*");
			vpManual("Pagination_DisplayedCorrectly", true, valid).performTest();
			From+=pageSize;
			button_paginationNextButton().click();
			sleep(18);
			++count;
		}
		button_paginationFirstPageButt().click();
		sleep(7);
		waitForloading();
	}
}



