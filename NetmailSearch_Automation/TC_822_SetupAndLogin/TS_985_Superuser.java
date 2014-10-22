package TC_822_SetupAndLogin;
import resources.TC_822_SetupAndLogin.TS_985_SuperuserHelper;
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
public class TS_985_Superuser extends TS_985_SuperuserHelper
{
	/**
	 * Script Name   : <b>testLanguage</b>
	 * Generated     : <b>Jun 27, 2013 11:36:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/27
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		languageFrenchTest();
		languageFrenchCanadianTest();
		languageDeutschTest();
		
		browser_htmlBrowser().loadUrl(URL);
		button_loginLanguagebutton().click();
		link_langEnglish().click();
		
		//Login
		NetmailLogin.login();
		//AdminLogin
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("themeAndLanguage");
		
		button_adminbutton().click();
		sleep(0.5);
		link_theme().hover();
		link_theme_Blue().click();
		themeBlueTest();
		
		button_adminbutton().click();
		sleep(0.5);
		link_theme().hover();
		link_theme_Access().click();
		themeAccessibilityTest();
		
		button_adminbutton().click();
		sleep(0.5);
		link_theme().hover();
		link_theme_Default().click();

	}
	
	
	private TestObject[] getActiveTabBody(){
		TestObject[] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", "x-tab-panel-body x-tab-panel-body-top"), true);
		return tabPanelBody[0].find(atChild(".tag", "DIV", "class", "x-panel x-panel-noborder"));
	}
	
	
	private void themeAccessibilityTest(){
		browser_htmlBrowser().loadUrl("http://10.10.24.80:8888");
		logInfo("Redirect to login screen");
		html_extGen3().click(atPoint(1,1));
		html_extGen3().click(atPoint(1,1));
		sleep(4);
		html_extGen3().performTest( accessbilityLoginScreen_IEVP() );
		
		Object[] ls = {"admin", "password", false, true};
		callScript("loginScript", ls);
		
		html_extGen3().click(atPoint(1,1));
		sleep(2);
		html_roleSelectionWindow().performTest( accessbilityRoleSelection_IEVP() );
		
		TestObject[] user = html_roleSelectionWindow().find(atDescendant(".class", "Html.TABLE", ".text", "Super User"), true);
		((GuiTestObject)user[0]).doubleClick();	
		logInfo("clicked super user");
		sleep(4);
		TestObject caseListWindow = html_extGen3().find(atDescendant(".tag", "DIV", "id", "caseListWindow" ), true)[0];
		TestObject[] searchCase = caseListWindow.find(atDescendant(".class", "Html.DIV", ".text", "themeAndLanguage"), false);
		((GuiTestObject)searchCase[0]).doubleClick();
		logInfo("clicked themeAndLanguage");
		sleep(4);
		
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_extGen83().performTest( accessibilityMainScreen_IEVP() );
		
		button_newCasebutton().click();
		logInfo("clicked new case");
		sleep(1);
		text_openDate().click();
		logInfo("clicked date input field");
		browser_htmlBrowser().inputKeys("^a{DELETE}05/12/91");
		logInfo("enter 05/12/91");	
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( accessibilityNewCase_IEVP() );	
		button_cancelbutton().click();
		logInfo("clicked cancel");
		sleep(1);
		
		button_editCasebutton().click();
		logInfo("clicked cancel button");
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( accessibilityEditCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		sleep(1);
		
		TestObject[] results = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		((GuiTestObject)results[0]).click();
		logInfo("clicked first result");
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Buttom().click();
		logInfo("clicked bottom panel on dropdown");
		sleep(2);
		getTabBody().performTest( accessibilityBottomPanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button"); 
		sleep(0.5);
		link_preview_Side().click();
		logInfo("clicked side pannel on dropdown");
		sleep(2);
		getTabBody().performTest( accessibilitySidePanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(1);
		link_preview_No().click();
		logInfo("clicked no preview");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance searcg button");
		sleep(0.5);
		link_advanceSearchMesssage().click();
		logInfo("clicked message in advance search dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( accessibilityAdvancedSearchWindow_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAttachment().click();
		logInfo("clicked attachement in advance search dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( accessibilityAdvancedSearchWindowAttachment_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAudit().click();
		logInfo("clicked audit in advance search dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( accesibilityAdvancedSearchWindowAudit_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");

	}
	
	
	
	private void themeBlueTest(){
		browser_htmlBrowser().loadUrl("http://10.10.24.80:8888");
		html_extGen3().click(atPoint(1,1));
		html_extGen3().click(atPoint(1,1));
		sleep(4);
		html_extGen3().performTest( blueLoginScreen_IEVP() );
		
		Object[] ls = {"admin", "password", false, true};
		callScript("loginScript", ls);
		
		html_extGen3().click(atPoint(1,1));
		sleep(2);
		html_roleSelectionWindow().performTest( blueRoleSelectionWindow_IEVP() );
		
		TestObject[] user = html_roleSelectionWindow().find(atDescendant(".class", "Html.TABLE", ".text", "Super User"), true);
		((GuiTestObject)user[0]).doubleClick();
		logInfo("double clicked super user");
		sleep(4);
		TestObject caseListWindow = html_extGen3().find(atDescendant(".tag", "DIV", "id", "caseListWindow" ), true)[0];
		TestObject[] searchCase = caseListWindow.find(atDescendant(".class", "Html.DIV", ".text", "themeAndLanguage"), false);
		((GuiTestObject)searchCase[0]).doubleClick();
		logInfo("double clicked themeAndLanguage");
		sleep(4);

		html_extGen83().performTest( blueMainScreen_IEVP() );
		
		button_newCasebutton().click();
		logInfo("clicked new case button");
		sleep(1);
		text_openDate().click();
		logInfo("clicked date input field");
		browser_htmlBrowser().inputKeys("^a{DELETE}05/12/91");
		logInfo("entered 05/12/91");
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( blueCaseDetailWindowNewCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		sleep(1);
		
		button_editCasebutton().click();
		logInfo("clicked edit case button");
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( blueCaseDetailWindowEditCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel");
		sleep(1);
		
		TestObject[] results = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		((GuiTestObject)results[0]).click();
		logInfo("clicked first results");
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Buttom().click();
		logInfo("clicked buttom panel in dropdown");
		sleep(3);
		getTabBody().performTest( blueBottomPanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Side().click();
		logInfo("clicked side panel in dropdown");
		sleep(2);
		getTabBody().performTest( blueSidePanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(1);
		link_preview_No().click();
		logInfo("clicked no preview");
		
		button_advancedSearchbutton().click();
		logInfo("click adv search");
		sleep(0.5);
		link_advanceSearchMesssage().click();
		logInfo("click message in adv dropdown");
		sleep(1);
		html_advancedSearchWindow().performTest( blueAdvancedSearchWindowMessage_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked adv search");
		sleep(0.5);
		link_advanceSearchAttachment().click();
		logInfo("clicked attachment in adv dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( blueAdvancedSearchWindowAttachment_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked adv search");
		sleep(0.5);
		link_advanceSearchAudit().click();
		logInfo("clicked audit in adv dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( blueAdvancedSearchWindowAudit_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
	}
	
	
	private void languageFrenchTest(){
		logInfo("Verifying French Language");
		browser_htmlBrowser().loadUrl("http://10.10.24.80:8888");
		button_loginLanguagebutton().click();
		logInfo("clicked language button in login page");
		link_langFrench().click();
		logInfo("clicked french");
		sleep(5);
		html_extGen3().click(atPoint(1,1));
		sleep(3);
		html_extGen3().performTest( frenchLoginScreen_IEVP() );
		
		Object[] ls = {"admin", "password", false, true};
		callScript("loginScript", ls);
		
		html_extGen3().click(atPoint(1,1));
		sleep(2);
		html_roleSelectionWindow().performTest( frenchRoleSelectionWindow_IEVP() );
		
		
		TestObject[] user = html_roleSelectionWindow().find(atDescendant(".class", "Html.TABLE", ".text", "Super-utilisateur"), true);
		((GuiTestObject)user[0]).doubleClick();	
		logInfo("Double clicked super-utilisateur");
		sleep(4);
		TestObject caseListWindow = html_extGen3().find(atDescendant(".tag", "DIV", "id", "caseListWindow" ), true)[0];
		TestObject[] searchCase = caseListWindow.find(atDescendant(".class", "Html.DIV", ".text", "themeAndLanguage"), false);
		((GuiTestObject)searchCase[0]).doubleClick();
		logInfo("clicked case: themeAndLanguage");
		sleep(4);
		
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_extGen3().performTest( frenchMainWindow_IEVP() );
		
		button_newCasebutton().click();
		logInfo("clicked new case button");
		sleep(1);
		text_openDate().click();
		browser_htmlBrowser().inputKeys("^a{DELETE}05/12/91");
		logInfo("input 05/12/91 into date field");
		
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( frenchCaseDetailWindowNewCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		sleep(1);
		
		button_editCasebutton().click();
		logInfo("clicked edit case button");
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( frenchCaseDetailWindowEditCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		
		TestObject[] results = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		((GuiTestObject)results[0]).click();
		logInfo("clicked first result");
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Buttom().click();
		logInfo("clicked on bottom panel on preview dropdown");
		sleep(2);
		getTabBody().performTest( frenchBottomPanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Side().click();
		logInfo("clicked side panel on preview dropdown");
		sleep(2);
		getTabBody().performTest( frenchSidePanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_No().click();
		logInfo("clicked no preview on preview dropdown");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchMesssage().click();
		logInfo("clicked message on advance search dropdown");
		sleep(2);
		html_advancedSearchWindow().performTest( frenchAdvancedSearchWindowMessages_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAttachment().click();
		logInfo("clicked attachement on advance search dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( frenchAdvancedSearchWindowAttachments_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAudit().click();
		logInfo("clicked Audit on advance search dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( frenchAdvancedSearchWindowAudit_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
	
	}
	
	private void languageFrenchCanadianTest(){
		logInfo("Verifying French Canadian Language");
		browser_htmlBrowser().loadUrl("http://10.10.24.80:8888");
		button_loginLanguagebutton().click();
		logInfo("clicked language in login page");
		link_langFrenchCanadian().click();
		logInfo("clicked french canadian");
		sleep(5);
		html_extGen3().click(atPoint(1,1));
		sleep(3);
		html_extGen3().performTest( frenchCanadianLoginScreen_IEVP() );
		
		Object[] ls = {"admin", "password", false, true};
		callScript("loginScript", ls);
		
		html_extGen3().click(atPoint(1,1));
		sleep(2);
		html_roleSelectionWindow().performTest( frenchCanadianRoleSelectionWindow_IEVP() );
		
		
		TestObject[] user = html_roleSelectionWindow().find(atDescendant(".class", "Html.TABLE", ".text", "Super-utilisateur"), true);
		((GuiTestObject)user[0]).doubleClick();	
		logInfo("Double clicked super-utilisateur");
		sleep(4);
		TestObject caseListWindow = html_extGen3().find(atDescendant(".tag", "DIV", "id", "caseListWindow" ), true)[0];
		TestObject[] searchCase = caseListWindow.find(atDescendant(".class", "Html.DIV", ".text", "themeAndLanguage"), false);
		((GuiTestObject)searchCase[0]).doubleClick();
		logInfo("clicked case: themeAndLanguage");
		sleep(4);
		
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_extGen3().performTest( frenchCanadianMainWindow_IEVP() );
		
		button_newCasebutton().click();
		logInfo("clicked new case button");
		sleep(1);
		text_openDate().click();
		browser_htmlBrowser().inputKeys("^a{DELETE}05/12/91");
		logInfo("input 05/12/91 into date field");
		
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( frenchCandianCaseDetailWindowNewCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		sleep(1);
		
		button_editCasebutton().click();
		logInfo("clicked edit case button");
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( frenchCandianCaseDetailWindowEditCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		
		TestObject[] results = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		((GuiTestObject)results[0]).click();
		logInfo("clicked first result");
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Buttom().click();
		logInfo("clicked on bottom panel on preview dropdown");
		sleep(2);
		getTabBody().performTest( frenchCandianBottomPanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Side().click();
		logInfo("clicked side panel on preview dropdown");
		sleep(2);
		getTabBody().performTest( frenchCandianSidePanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_No().click();
		logInfo("clicked no preview on preview dropdown");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchMesssage().click();
		logInfo("clicked message on advance search dropdown");
		sleep(2);
		html_advancedSearchWindow().performTest( frenchCandianAdvancedSearchWindowMessages_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAttachment().click();
		logInfo("clicked attachement on advance search dropdown");
		sleep(0.5);
		html_advancedSearchWindow().performTest( frenchCandianAdvancedSearchWindowAttachment_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAudit().click();
		logInfo("clicked Audit on advance search dropdown");
		sleep(1);
		html_advancedSearchWindow().performTest( frenchCandianAdvancedSearchWindowAudit_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
	}
	
	
	
	
	private void languageDeutschTest(){
		logInfo("Verifying French Canadian Language");
		browser_htmlBrowser().loadUrl("http://10.10.24.80:8888");
		button_loginLanguagebutton().click();
		logInfo("clicked language in login page");
		link_langDeutsch().click();
		logInfo("clicked deutsch");
		sleep(5);
		html_extGen3().click(atPoint(1,1));
		sleep(3);
		html_extGen3().performTest( deutschLoginPage_IEVP() );
		
		Object[] ls = {"admin", "password", false, true};
		callScript("loginScript", ls);
		
		html_extGen3().click(atPoint(1,1));
		sleep(2);
		html_roleSelectionWindow().performTest( deutschRoleSelectionWindow_IEVP() );
		
		
		TestObject[] user = html_roleSelectionWindow().find(atDescendant(".class", "Html.TABLE", ".text", "Super-Benutzer"), true);
		((GuiTestObject)user[0]).doubleClick();	
		logInfo("Double clicked super-utilisateur");
		sleep(4);
		TestObject caseListWindow = html_extGen3().find(atDescendant(".tag", "DIV", "id", "caseListWindow" ), true)[0];
		TestObject[] searchCase = caseListWindow.find(atDescendant(".class", "Html.DIV", ".text", "themeAndLanguage"), false);
		((GuiTestObject)searchCase[0]).doubleClick();
		logInfo("clicked case: themeAndLanguage");
		sleep(4);
		
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_extGen3().performTest( deutschMainPage_IEVP() );
		
		button_newCasebutton().click();
		logInfo("clicked new case button");
		sleep(1);
		text_openDate().click();
		browser_htmlBrowser().inputKeys("^a{DELETE}05/12/91");
		logInfo("input 05/12/91 into date field");
		
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( deutschCaseDetailWindowNewCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		sleep(1);
		
		button_editCasebutton().click();
		logInfo("clicked edit case button");
		html_extGen83().click(atPoint(1,1));
		sleep(0.5);
		html_caseDetailWindow().performTest( deutschCaseDetailWindowEditCase_IEVP() );
		button_cancelbutton().click();
		logInfo("clicked cancel button");
		
		TestObject[] results = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		((GuiTestObject)results[0]).click();
		logInfo("clicked first result");
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Buttom().click();
		logInfo("clicked on bottom panel on preview dropdown");
		sleep(2);
		getTabBody().performTest( deutschBottomPanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_Side().click();
		logInfo("clicked side panel on preview dropdown");
		sleep(2);
		getTabBody().performTest( deutschSidePanel_IEVP() );
		
		button_previewbutton().click();
		logInfo("clicked preview button");
		sleep(0.5);
		link_preview_No().click();
		logInfo("clicked no preview on preview dropdown");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchMesssage().click();
		logInfo("clicked message on advance search dropdown");
		sleep(2);
		html_advancedSearchWindow().performTest( deutschAdvancedSearchWindowMessage_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAttachment().click();
		logInfo("clicked attachement on advance search dropdown");
		sleep(0.5);
		html_advancedSearchWindow2().performTest( deutschAdvancedSearchWindowAttachment_DeutschVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
		
		button_advancedSearchbutton().click();
		logInfo("clicked advance search button");
		sleep(0.5);
		link_advanceSearchAudit().click();
		logInfo("clicked Audit on advance search dropdown");
		sleep(1);
		html_advancedSearchWindow2().performTest( deustchAdvancedSearchWindowAudit_IEVP() );
		button_advSearchCancel().click();
		logInfo("clicked cancel");
	}
	
	private GuiTestObject getTabBody(){
		TestObject [] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", new RegularExpression("^(\\s)*x-tab-panel-body x-tab-panel-body-top(\\s)*$", false)), true);
		return (GuiTestObject) tabPanelBody[0].find(atChild(".tag", "DIV", "class", new RegularExpression("^(\\s)*x-panel x-panel-noborder(\\s)*$", false)))[0];
	}
}

