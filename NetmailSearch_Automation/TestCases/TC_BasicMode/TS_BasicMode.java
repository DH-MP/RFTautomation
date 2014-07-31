package TestCases.TC_BasicMode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.TestCases.TC_BasicMode.TS_BasicModeHelper;
import utilities.HelperClass;

import NetmailSearch_PrintAndExport.Print_SuperUser;
import TC_831_NetmailSearch.TS_995;

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
public class TS_BasicMode extends TS_BasicModeHelper
{
	/**
	 * Script Name   : <b>BasicMode</b>
	 * Generated     : <b>Jul 11, 2014 10:37:13 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/11
	 * @author Administrator
	 */
	String cfgFileDirectory = "\\\\"+IP+"\\Messaging Architects\\RemoteProvider";
	private String backupName = cfgFileDirectory+"\\xgwxmlv_BACKUP.cfg";
	private String oldFileName = cfgFileDirectory+"\\xgwxmlv.cfg";
	private String workSpace = "\\\\10.10.23.61\\Data\\NetmailSearchGV\\NetmailSearch_Automation";
	public void testMain(Object[] args) 
	{	
		enableBasicMode();
		
		//Login
		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		//Use old script to test highlight and quick search
		TS_995 ts995 = new TS_995();
		HelperClass.navigateLocation("ALS>Amy S");
		sleep(2);
		if(button_basicModeArchive_OK().exists())
			button_basicModeArchive_OK().click();
		sleep(2);
		if(html_indexingProgressWindow().exists()){
			while(html_indexingProgressWindow().getProperty("style").toString().matches(".*VISIBILITY: visible.*"));
		}
		
		try {
			ts995.testHighlightAndQuickSearch("bcc", 6);
		} catch (Exception e) {
			logError("HighlightAndQuickSearchException: "+ e);
		}
		
		try {
			TestNetmailSearch();
		} catch (Exception e) {
			logError("NetmailSearch: "+ e);
		}
		
		
		try {
			TestMessageAndWordList();
		} catch (Exception e) {
			logError("TestMessageAndWordListException: "+ e);
		}
		
		
		//Login
		callScript("loginScript", ls);
		HelperClass.navigateLocation("ALS>F user1dom2po1");
		sleep(2);
		if(button_basicModeArchive_OK().exists())
			button_basicModeArchive_OK().click();
		sleep(2);
		while(html_indexingProgressWindow().getProperty("style").toString().matches(".*VISIBILITY: visible.*"));
		
		
		try {
			ts995.testPagination();
		} catch (Exception e) {
			logError("ts995Exception: "+ e);
		}
		
		try{
			testDisplayAllChecked();
		}catch(Exception e){
			logError("DisplayAllCheckException: "+ e);
		}
		
		try{
			TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			Print_SuperUser psu = new Print_SuperUser();	
			psu.printMessage((GuiTestObject)results[0], false);
		}catch(Exception e){
			logError("PrintMessageTestException: "+ e);
		}
	}
	
	private void enableBasicMode(){
		String newFileName = cfgFileDirectory+"\\xgwxmlv_NEW.cfg";
		
		//stop NetmailSearch
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		
		//Change CFG
		try {
			FileReader inputStream = new FileReader(oldFileName);
			BufferedReader br = new BufferedReader(inputStream);

			FileWriter fstream2 = new FileWriter(newFileName);
			BufferedWriter bw = new BufferedWriter(fstream2);

			String strLine;
			while ((strLine = br.readLine()) != null) {
				//analytics.columns_order
				if (strLine.matches("^basicMode.*") | strLine.matches("^#basicMode.*")) {
					strLine = "basicMode=true";
				}
				bw.write(strLine);
				bw.newLine();
			}
			bw.close();
			br.close();

			File bkFile = new File(backupName);
			if(bkFile.exists()){
				bkFile.delete();
				bkFile = new File(backupName);
			}
			File oldFile = new File(oldFileName);
			File newFile = new File(newFileName);
			oldFile.renameTo(bkFile);
			newFile.renameTo(oldFile);
			newFile.delete();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		//start NetmailSearch
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
	}
	
	private void testDisplayAllChecked(){
		TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		for(int i = 0; i < 3; i++){
			RegularExpression a = new RegularExpression(".*x-grid3-td-checker.*", false); 
			TestObject[] checkbox = results[i].find(atDescendant(".tag", "TD", "class", a), false);
			((GuiTestObject) checkbox[0]).click();
		}
		
		table_selectionsButton0().click();
		image_selectedOnlyCheckBox().click();
		sleep(1);
		waitForloading();
		
		results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		vpManual("DisplaySelectedTest", 3, results.length).performTest();
		
		table_selectionsButton0().click();
		image_selectedOnlyCheckBox().click();
		sleep(1);
		waitForloading();
		
	}
	
	private void TestMessageAndWordList() {
		
		// Point to the datapool location that was created
		java.io.File dpFile = new java.io.File(
				(String) getOption(IOptionName.DATASTORE), "/TestCases/TC_BasicMode/MessageAndWordList.rftdp");
		// Load the datapool using RFT IDataPoolFactory
		IDatapool dataPool = dpFactory().load(dpFile, true);

		// Open the datapool using RFT IDataPoolFactory
		IDatapoolIterator dataPoolIter = dpFactory().open(dataPool, null);

		// After it is opened, initilize the datapool to access the data
		dataPoolIter.dpInitialize(dataPool);
		
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
			
			Object[] wordList = {dataPoolIter.dpString("wordList")};
			callScript("NetmailSearch_AdvanceSearch.MessageWordListTab_SuperUser", wordList);
			
			dataPoolIter.dpNext();
		}
	}
	
	
	private void TestNetmailSearch() {
		// Point to the datapool location that was created
		java.io.File dpFile = new java.io.File(
				(String) getOption(IOptionName.DATASTORE), "/TestCases/TC_BasicMode/Search_NetmailSearchTab.rftdp");
		// Load the datapool using RFT IDataPoolFactory
		IDatapool dataPool = dpFactory().load(dpFile, true);

		// Open the datapool using RFT IDataPoolFactory
		IDatapoolIterator dataPoolIter = dpFactory().open(dataPool, null);

		// After it is opened, initilize the datapool to access the data
		dataPoolIter.dpInitialize(dataPool);
		
		//Advance Search
		while(!dataPoolIter.dpDone()){
			Object[] aSMN = {	
					dataPoolIter.dpString("field1"),
					dataPoolIter.dpString("field2"), 
					dataPoolIter.dpString("field3"), 
					dataPoolIter.dpString("value1"),
					dataPoolIter.dpString("value2"), 
					dataPoolIter.dpString("value3"), 
					dataPoolIter.dpString("logic"), 
					dataPoolIter.dpString("listOfApproximateWords"),
					dataPoolIter.dpString("customSearch"),
					dataPoolIter.dpInt("customSearchNumberOfResults"),
					dataPoolIter.dpString("customSearchResultTitles"),
				};

			callScript("NetmailSearch_AdvanceSearch.MessageNetmailTab_SuperUser", aSMN);
			dataPoolIter.dpNext();
		}
	}
	
	
	
	@Override
	public void onTerminate(){	
		//Stop NetmailSearch
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		
		File bkFile = new File(backupName);
		if(bkFile.exists()){
			File oldFile = new File(oldFileName);
			oldFile.delete();
			oldFile = new File(oldFileName);
			bkFile.renameTo(oldFile);
		}
		
		//Start NetmailSearch
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
	}
}

