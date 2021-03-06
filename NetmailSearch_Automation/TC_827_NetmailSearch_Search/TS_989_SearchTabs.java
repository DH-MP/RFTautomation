package TC_827_NetmailSearch_Search;
import java.util.HashMap;

import resources.TC_827_NetmailSearch_Search.TS_989_SearchTabsHelper;
import utilities.HelperClass;
import utilities.HelperScript;

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
public class TS_989_SearchTabs extends TS_989_SearchTabsHelper
{
	/**
	 * Script Name   : <b>TS_985_Superuser</b>
	 * Generated     : <b>Jun 25, 2013 9:33:47 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/25
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		GuiTestObject activeTab, previousTab;
		TestObject[] activeTabBody, inactiveTabs, bottomToolBar, cells;
		int totalNumOfTabs = 0, 
			oldNumOfTabs = -1;
		String 	tabName = "Acorn", quickSearchTerm = "BCC";

		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		Object[] al = {"intern_mike", "Super User"};
		callScript("adminLogin", al);
		
/******* Advance Search + Quick search on result  *****************************************************************/	
	//Advance Search
		HashMap<String, String> query = new HashMap<String, String> ();
		query.put("subject", "secret"); 
		HashMap<String, Boolean> booleanQuery = new HashMap<String, Boolean> ();
		booleanQuery.put("expectedResults", true);
		booleanQuery.put("performVerification", false);
		Object[] asmnu = {query, booleanQuery};
		callScript("NetmailSearch_AdvanceSearch.Message_SuperUser", asmnu);
		
	//Test sub search
		TestObject[] results = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		String[] contents = new String[results.length];
		for(int i = 0; i < results.length; i++){
			contents[i] = results[i].getProperty(".text").toString().trim().toLowerCase();
		}
		
		activeTabBody = getActiveTabBody();
		((GuiTestObject)activeTabBody[0].find(atDescendant(".tag", "INPUT", "id", new RegularExpression("quickSearchField\\d", false)), true)[0]).click();
		browser_htmlBrowser().inputKeys("dave{ENTER}");
		logInfo("inputed [DAVE] in quick search field and pressed enter");
		sleep(6);
		
		TestObject[] subResults = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		vpManual("Expected_number_of_Results", 1, subResults.length).performTest();
		boolean exists = true;
		for(TestObject subResult : subResults){
			exists = false;
			for(String content: contents){
				String a = subResult.getProperty(".text").toString().trim().toLowerCase();
				if(a.contentEquals(content)){
					exists = true;
					break;
				}
			}
			if(!exists){
				logError("Sub result is not in advance search results");
				break;
			}
		}
		if(exists){
			logTestResult("Searching on results", true);
		}
		
		button_advancedSearchbutton().click();
		link_advClearSearch().click();
		sleep(3);
		waitForloading();
			
		
/******* Add Tab *****************************************************************/
		activeTab = findActiveTab();
		totalNumOfTabs = findAllTabs().length;
		
		link_addTab().click();
		sleep(1);
		waitForloading();
		logInfo("Clicked + for new tab");
		html_extGen3().click(atPoint(1,1));
		activeTab = findActiveTab();
		
		oldNumOfTabs = totalNumOfTabs;
		totalNumOfTabs = findAllTabs().length;
		vpManual("TabCountIncreased", oldNumOfTabs, totalNumOfTabs-1 ).performTest();
		

/******* Rename Tab *****************************************************************/
		activeTab = findActiveTab();
		activeTab.click(RIGHT);
		logInfo("Right clicked active tab");
		sleep(0.5);	
		link_tabRename().click();
		logInfo("Clicked rename on dropdown");
		sleep(0.5);
		text_renameInput().click();
		logInfo("Clicked rename input field");
		browser_htmlBrowser().inputKeys(tabName);
		logInfo("inputed tab name: "+tabName);
		button_renameCancelbutton().click();
		logInfo("Clicked cancel");
		sleep(0.5);
		vpManual("Tab_name_is_unchanged_on_cancel", activeTab.getProperty(".text").toString(), "Search").performTest();
		
		activeTab.click(RIGHT);
		logInfo("Right clicked active tab");
		sleep(0.5);		
		link_tabRename().click();
		logInfo("Clicked rename on dropdown");
		sleep(0.5);
		text_renameInput().click();
		logInfo("Clicked rename input field");
		browser_htmlBrowser().inputKeys(tabName);
		logInfo("inputed tab name: "+tabName);
		button_renameOKbutton().click();
		logInfo("clicked OK"+tabName);
		sleep(3);
		waitForloading();
		vpManual("Tab_renamed", tabName, activeTab.getProperty(".text")).performTest();
		
/******* Clone Tab *****************************************************************/
	//Prepare results
		activeTab = findActiveTab();
		String traversePath = "ALS>Amy S>Mailbox";
		HelperClass.navigateLocation(traversePath);
		activeTabBody = getActiveTabBody();
		((GuiTestObject)activeTabBody[0].find(atDescendant(".tag", "INPUT", "id", new RegularExpression("quickSearchField\\d", false)), true)[0]).click();
		browser_htmlBrowser().inputKeys(quickSearchTerm+"{ENTER}");
		logInfo("inputed <"+quickSearchTerm+"> in quick search field and pressed enter");
		sleep(6);
		

		
	//Clone a Tab	
		activeTab.click(RIGHT);
		logInfo("Right click active tab");
		link_tabClone().click();
		logInfo("Clicked clone tab in dropdown");
		activeTab = findActiveTab();
		sleep(2);
		waitForloading();
		oldNumOfTabs = totalNumOfTabs;
		totalNumOfTabs = findAllTabs().length;
		vpManual("TabCountIncreased", oldNumOfTabs, totalNumOfTabs-1 ).performTest();
		
	//Verify Cloned Tab
	//Verify the search query is cloned
		GuiTestObject quickSearch = ((GuiTestObject)activeTabBody[0].find(atDescendant(".tag", "INPUT", "id", new RegularExpression("quickSearchField\\d", false)), true)[0]);
		logInfo("Verifying quick search field of cloned tab");
		vpManual("searchQueryCloned", quickSearchTerm, quickSearch.getProperty("value") ).performTest();
		
	//Validate the search query is highlighted in clone
		logInfo("Verifying if search term is highlighted in cloned tab");
		TestObject[] highlightedTexts = find(atDescendant(".class", "Html.SPAN", "class", "tma-highlight"), false);
		if(vpManual("clonedTabHighlightTexTFound", true, highlightedTexts.length>0).performTest()){
			for( TestObject highlighted : highlightedTexts){
				String text = ((String) highlighted.getProperty(".text")).trim();
				if(!quickSearchTerm.contains(text)){
					vpManual("SearchTerm_Matches_HighlightedText", quickSearchTerm, text).performTest();
				}
			}
		}
		
		
	//Validate the same number of results in clone
		bottomToolBar = activeTabBody[0].find(atDescendant(".tag", "DIV", "class", "x-panel-bbar x-panel-bbar-noborder"), true);
		cells = bottomToolBar[0].find(atDescendant(".tag", "TD", "class", "x-toolbar-cell"), false);
		String clonedTabNumberOfResults = cells[7].getProperty(".text").toString();
		

		activeTab = (GuiTestObject)findAllTabs()[1];;
		activeTab.click();

		bottomToolBar = activeTabBody[0].find(atDescendant(".tag", "DIV", "class", "x-panel-bbar x-panel-bbar-noborder"), true);
		cells = bottomToolBar[0].find(atDescendant(".tag", "TD", "class", "x-toolbar-cell"), false);
		String expectedNumberOfResults = cells[7].getProperty(".text").toString();
		logInfo("Verifying if cloned tab has same number of result");
		vpManual("CloneTabHasSameNumberOfResults", true, clonedTabNumberOfResults == expectedNumberOfResults);
		
	
	//Validate Cloned Tab Name
		previousTab = (GuiTestObject)findAllTabs()[1];
		activeTab = (GuiTestObject)findAllTabs()[2];
		activeTab.click();
		logInfo("Verifying clone tab name");
		vpManual("Tab_Name_Cloned", activeTab.getProperty(".text").toString(), previousTab.getProperty(".text").toString()).performTest();
		
	//Validate Results are same
		TestObject[] resultsCloned = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		activeTab = (GuiTestObject)findAllTabs()[1];
		activeTab.click();
		sleep(2);
		TestObject[] previousResults = getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		logInfo("Verifying if results are exactly the same");
		boolean equals = true;
		for(int i = 0; i<resultsCloned.length|i<resultsCloned.length; i++){
			TestObject[] clonedColumns = resultsCloned[i].find(atDescendant(".tag", "TD"), false);
			TestObject[] previousColumns = previousResults[i].find(atDescendant(".tag", "TD"), false);
			for(int k = 0; k<clonedColumns.length|k<previousColumns.length; k++){
				String a = previousColumns[k].getProperty(".text").toString().trim().toLowerCase();
				String b = clonedColumns[k].getProperty(".text").toString().trim().toLowerCase();
				if(!a.contentEquals(b)){
					logWarning("Message in row "+i+" do not match");
					break;
				}
			}
		}
		if(equals){
			logTestResult("Results are cloned (they are all the same)", true);
		}

	//Validate Folder Selection are the same
		HelperClass.navigateLocation(traversePath);
		activeTabBody = getActiveTabBody();
		TestObject locationTree = activeTabBody[0].find(atDescendant(".tag", "DIV", "class", "x-tree-root-node"), false)[0];
		String[] paths = traversePath.split(">");
		TestObject root =  locationTree;
		TestObject[] link = null;
		TestObject lastCheckbox = null;
		for(int i = 0; i<paths.length; i++){
			String path = paths[i];
			RegularExpression pathRegexp = new RegularExpression(path, false);
			link = root.find(atDescendant(".tag", "A", ".contentText", pathRegexp), false);
			if(link.length>0){
				root = link[0].getParent().getParent().getChildren()[1];
				TestObject[] checkbox = link[0].getParent().find(atDescendant(".class", "Html.INPUT.checkbox"), false);
				if(i != paths.length-1){
					if(Boolean.parseBoolean(checkbox[0].getProperty(".checked").toString())){
						logError("<"+path+"> in the "+paths+" is not supposed to be checked, cloning folder selection failed");
						break;
					}
				}
				lastCheckbox = checkbox[0];

			}else{
				logError("CAN'T FIND < "+path+" > in tree" );
			}	
		}
		
		System.out.println(lastCheckbox.getProperty(".checked").toString());
		logTestResult("Last path in the "+paths+" is checked", Boolean.parseBoolean(lastCheckbox.getProperty(".checked").toString()));
		

/******* Close Tab *****************************************************************/
	//Close 3rd Tab
		activeTab = (GuiTestObject)findAllTabs()[2];
		activeTab.click();
		logInfo("Clicked 3rd tab");
		totalNumOfTabs = findAllTabs().length;
		activeTab.click(RIGHT);
		logInfo("Right clicked the tab");
		link_tabClose().click();
		logInfo("Clicked close on dropdown");
		sleep(0.5);
		activeTab = findActiveTab();
		if(activeTab!= null){
			oldNumOfTabs = totalNumOfTabs;
			totalNumOfTabs = findAllTabs().length;
			vpManual("TabCountDecreased", oldNumOfTabs-1, totalNumOfTabs ).performTest();
		}
		
		
/******* Close All Other Tabs *****************************************************************/
	//Close all other tab except the second tab
		totalNumOfTabs = findAllTabs().length;
		link_addTab().click();
		logInfo("Clicked + for new tab");
		sleep(5);
		link_addTab().click();
		logInfo("Clicked + again for new tab");
		sleep(2);
		waitForloading();
		
		activeTab = ((GuiTestObject)findAllTabs()[1]);
		activeTab.click();
		logInfo("Clicked 2nd tab");
		
		if(activeTab!= null){
			oldNumOfTabs = totalNumOfTabs;
			totalNumOfTabs = findAllTabs().length;
			vpManual("TabCountIncreased", oldNumOfTabs+2, totalNumOfTabs ).performTest();
		}
		
		activeTab.click(RIGHT);
		logInfo("Right clicked current tab");
		link_tabCloseOther().click();
		logInfo("Clicked close all other tabs on dropdown");
		
		activeTab = findActiveTab();
		totalNumOfTabs = findAllTabs().length;
		vpManual("OnlyOneTabLeft", 1, totalNumOfTabs).performTest();
		vpManual("VerifyTheCurrentTabName", tabName, activeTab.getProperty(".text").toString()).performTest();	

	}
	
	private GuiTestObject findActiveTab(){
		TestObject[] tab = find(atDescendant(".tag", "LI", "class", "x-tab-strip-closable x-tab-strip-active"), true);
		while( tab.length != 1){
			tab = find(atDescendant(".tag", "LI", "class", "x-tab-strip-closable x-tab-strip-active"), true);
		}
		return (GuiTestObject)tab[0];
	}
	
	private TestObject[] getActiveTabBody(){
		TestObject [] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", new RegularExpression("^(\\s)*x-tab-panel-body x-tab-panel-body-top(\\s)*$", false)), true);
		return tabPanelBody[0].find(atChild(".tag", "DIV", "class", new RegularExpression("^(\\s)*x-panel x-panel-noborder(\\s)*$", false)));
	}
	
	
	private TestObject[] findAllTabs(){
		return html_tabPanelHeader().find(atDescendant(".tag", "LI", "class", new RegularExpression("x-tab-strip-closable.*",false)));
	}
}

