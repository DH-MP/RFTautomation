package TestCases.TC_842_SearchBoundaryStressTests;
import java.util.HashMap;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.TestCases.TC_842_SearchBoundaryStressTests.TS_1009_SearchWith20PlusTabsHelper;
import utilities.HelperScript;

import Case_Management.manageCase;
import NetmailSearch_AdvanceSearch.MessageWordListTab_SuperUser;
import NetmailSearch_AdvanceSearch.Message_SuperUser;
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
public class TS_1009_SearchWith20PlusTabs extends TS_1009_SearchWith20PlusTabsHelper
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
		String caseName = "TS1009_20Plus";
		int numberOfTabs = 20;  
		
		NetmailLogin.login();
		adminLogin.selectUserType(dpString("Super User"));
		
		//New Case
		manageCase mc = new manageCase();
		mc.setTestMode(false);
		mc.setName(caseName).setLocations("LargeUser;ALS").setTestMode(false).newCase();
		waitForloading();
		
		//Add tabs put default tab into account
		for(int i=0; i<numberOfTabs; i++){
			if(html_tabScrollRight().exists() ){
				html_tabScrollRight().click();
				sleep(1);
			}
			html_addTabLI().click();

			waitForloading();
			logInfo("Clicked + for new tab");
		}	
		
		mc.saveCurrentCase();
		sleep(20);
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(20);
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase(caseName);
		
		sleep(5);
		waitForloading();
		waitForloading();
		
		vpManual("numOfTabSaved", numberOfTabs+1, findAllTabs().length ).performTest();
		
		//Test search
		TestMessageAndWordList();
			
		
		//Relogin and delete case		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		mc.deleteCase("TS1009_20Plus");
		
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
		return html_tabPanelUL().find(atDescendant(".tag", "LI", "class", new RegularExpression("x-tab-strip-closable.*",false)));
	}
	
	
	
	private void TestMessageAndWordList() {
		
		

		// Point to the datapool location that was created
		java.io.File dpFile = new java.io.File(
				(String) getOption(IOptionName.DATASTORE), "/TestCases/TC_842_SearchBoundaryStressTests/TS_1009_MessageAndWordSearch.rftdp");
		// Load the datapool using RFT IDataPoolFactory
		IDatapool dataPool = dpFactory().load(dpFile, true);

		// Open the datapool using RFT IDataPoolFactory
		IDatapoolIterator dataPoolIter = dpFactory().open(dataPool, null);

		// After it is opened, initilize the datapool to access the data
		dataPoolIter.dpInitialize(dataPool);
		dataPoolIter.dpReset();
		
		//Advance Search
		while(!dataPoolIter.dpDone()){
			HashMap<String, String> query = new HashMap<String, String> ();
			query.put("subject", dataPoolIter.dpString("subject")); 
			query.put("sender", dataPoolIter.dpString("sender")); 
			query.put("recipient", dataPoolIter.dpString("recipient"));
			query.put("body", dataPoolIter.dpString("body"));
			query.put("sendDate1", dataPoolIter.dpString("sendDate1")); 
			query.put("sendDate2",dataPoolIter.dpString("sendDate2"));
			query.put("rcvDate1", dataPoolIter.dpString("rcvDate1"));
			query.put("rcvDate2", dataPoolIter.dpString("rcvDate2"));  
			query.put("personal",dataPoolIter.dpString("personal")); 
			query.put("category",dataPoolIter.dpString("category"));
			
			HashMap<String, Boolean> booleanQuery = new HashMap<String, Boolean> ();
			booleanQuery.put("typeMail", dataPoolIter.dpBoolean("checkTypeMail")); 
			booleanQuery.put("typeAppointment", dataPoolIter.dpBoolean("checkTypeAppointment"));
			booleanQuery.put("typeTask", dataPoolIter.dpBoolean("checkTypeTask"));			
			booleanQuery.put("typeNote", dataPoolIter.dpBoolean("checkTypeNote"));
			booleanQuery.put("searchEmbedded",dataPoolIter.dpBoolean("checkSearchEmbedded"));
			booleanQuery.put("invalidRcvDateRange", dataPoolIter.dpBoolean("invalidRcvDateRange"));
			booleanQuery.put("invalidSentDateRange", dataPoolIter.dpBoolean("invalidSendDateRange"));
			booleanQuery.put("expectedResults", dataPoolIter.dpBoolean("expectedResults"));
			Object[] asmnu = {query, booleanQuery};
			callScript("NetmailSearch_AdvanceSearch.Message_SuperUser", asmnu);
			


			if(dataPoolIter.dpString("wordList")!=null && !dataPoolIter.dpString("wordList").isEmpty()){
				Message_SuperUser msu = new Message_SuperUser();
				msu.clearSearchResult();
				msu.openAdvanceSearchMessage();
				
				MessageWordListTab_SuperUser mwltsu = new MessageWordListTab_SuperUser();
				mwltsu.openWordListTab();
				mwltsu.inputWordList(dataPoolIter.dpString("wordList"), ";");
				mwltsu.search();
				waitForloading();
				mwltsu.validateSearchResult(";");
			}
			
			dataPoolIter.dpNext();
		}
	}
}

 
