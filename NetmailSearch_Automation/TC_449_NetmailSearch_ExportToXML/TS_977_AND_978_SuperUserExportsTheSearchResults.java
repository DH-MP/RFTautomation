package TC_449_NetmailSearch_ExportToXML;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import resources.TC_449_NetmailSearch_ExportToXML.TS_977_AND_978_SuperUserExportsTheSearchResultsHelper;
import NetmailSearch_PrintAndExport.Export_SuperUser;

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
import utilities.HelperClass;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class TS_977_AND_978_SuperUserExportsTheSearchResults extends TS_977_AND_978_SuperUserExportsTheSearchResultsHelper
{
	/**
	 * Script Name   : <b>TS_977_SuperUserExportsTheSearchResults</b>
	 * Generated     : <b>Aug 9, 2013 1:09:47 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/08/09
	 * @author Administrator
	 * 
	 */
	private String exportName = "",
				   file = "",
				   workspace = "", 
				   winrarPath = "",
				   fileLocation = "",
				   extractLocation = "";
	
	public void testMain(Object[] args) 
	{
		TestObject[] exports = null, columns = null;
		file = dpString("fileToDownload").trim();
		workspace = dpString("workSpaceLocation");
		winrarPath = dpString("winrarPath");
		fileLocation = dpString("fileLocation");
		extractLocation = dpString("extractLocation");
		exportName = dpString("exportName");
		
		//Login
		Object[] ls = {null,null, false, true};
		callScript("loginScript", ls);
		//AdminLogin
		Object[] al = {"ExportXML", "Super User", true};
		callScript("adminLogin", al);

		//NewExport
		Object[] nES = {dpString("searchTabIndex"), 
						dpInt("itemOption"),
						dpInt("exportTypeOption"),
						dpString("exportName"),
						dpString("email"),
						dpString("password"),
		};
		callScript("newExport_Super", nES);
		
		//Setup
		setUpAndTearDown(true);
		
		//Open export: Newest export are always first
		logTestResult("Export Management Window is open", html_exportList().ensureObjectIsVisible());
		exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		columns = exports[0].find(atDescendant(".tag", "TD"), false);
		if( columns[0].getProperty(".text").equals(exportName)){
			Export_SuperUser esu = new Export_SuperUser();
			esu.openWhenTopExportComplete();//wait for success Status
			sleep(10);
		}else{
			logError("Export not found!");
		}
		
		
		//Download Export
		String file = dpString("fileToDownload").trim();
		Property[] rowProperty = {	new Property(".tag", "TABLE"),
							new Property(".text", new RegularExpression("(?i).*"+file+".*", false)),
							new Property("class", "x-grid3-row-table"),
		};
		TestObject[] exportFiles = html_exportFilesList().find(atDescendant(rowProperty), true);
		if(exportFiles.length == 1){
			((GuiTestObject)exportFiles[0]).doubleClick();
			logInfo("Selected < "+ file +" > file to download");
			sleep(10);
		}else{
			logError("Could not find export file by the name < "+ file +">");
		}
		

		
		
		//IE Notification Control
		TestObject downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
		int counter = 0;
		while(downloadObject==null){
			sleep(2);//wait a bit
			downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
			if(counter++>10){
				logError("Could not find notification bar");
				break;
			}
		}
		
		String downloadFileText = downloadObject.getProperty(".text").toString();
		String expectedDownloadMessage = "Do you want to open or save "+file+" from .*";

		logInfo("Verifying if < "+downloadFileText+" > mathces < "+expectedDownloadMessage+" >" );
		logTestResult("Correct_download_Message", downloadFileText.matches(expectedDownloadMessage) );
		HelperClass.ieNotificationElement("Save").click();
		logInfo("Clicked Save file on browser");
		sleep(2);
		
		
		//Wait for download to finish
		waitForDownloadCompletion();

		HelperClass.ieNotificationElement("Close").click();
		logInfo("Clicked close button for finished download notification");
		
		//Extract ISO
		Thread  t= new Thread(){
			public void run(){
				extractISO();
			}
		};
		t.start();
		
		//Delete Export
		HelperClass.CloseAllBrowsers();
		logInfo("Close all browsers"); //Bug bypass: There is a RFT bug when notification bar comes up no object can be found anymore.

		
		callScript("loginScript", ls);
		callScript("adminLogin", al);	
		
		deleteExport();
		
		//Wait for extract to finished before proceeding
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		
		//Verify NetmailSearchLite.exe
		try {
			HelperClass.CloseAllBrowsers();
			logInfo("Closer all browser");
			Process netmailLite = Runtime.getRuntime().exec(extractLocation+"\\NetmailSearchLite.exe");
			logInfo("Launched netmail search lite");
			browser_htmlBrowser().waitForExistence(240, DISABLED);
			sleep(10);
			waitForloading();
			waitForloading();
			waitForloading();
			
			//Verify some small NetmailSearchLite.exe functionality
			testDisplaySelected();
			simpleNavigateTree();
			testSimpleAddTab();
			testSelectAllandClearSelection();
			
			
			//Logout
			button_userMenu().click();
			logInfo("Clicked User Menu");
			link_logout().click();
			logInfo("Clicked logout on dropdown");
			button_logoutNo().click();
			logInfo("Clicked No button");
			sleep(0.5);

			button_userMenu2().click();
			logInfo("Clicked User Menu");
			link_logout().click();
			logInfo("Clicked logout on dropdown");
			button_logoutYes().click();
			logInfo("Clicked Yes on logout");
			button_htmlDialogButtonYes().click();
			logInfo("Clicked Yes on IE dialog");
			sleep(0.5);
			
			
			netmailLite.destroy();
			sleep(20);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		//Delete Files
		setUpAndTearDown(false);
		
	}
	
	public void setUpAndTearDown(boolean isSetup){
		boolean setup = isSetup;
		
		//Delete ISO file if it exists
		File pdf = new File(fileLocation+"\\"+file);
		if(pdf.exists()){
			pdf.delete();
			logInfo("ISO file deleted");
		}
		
		//Delete ISO folder if it exists
		File isoFolder = new File(extractLocation);
		int count = 0;
		while(isoFolder.exists()){
			String[] a = new String[]{	"cmd.exe",
										"/C start "+workspace+"\\assets\\deleteFolder.bat "+extractLocation	
					  				};
			Process b;
			try {
				b = Runtime.getRuntime().exec(a);
				sleep(3);
				b.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
			isoFolder = new File(extractLocation);
			logInfo("ISO Folder deleted");
			
			if(count++>5){
				logError("Cannot Delete ISO folder attempted 5 times");
				break;
			}
		}

		//Create ISO folder if it doesn't exists for setup
		if(!isoFolder.exists() && setup){
			isoFolder.mkdir();
		}
	}
	
	public void extractISO(){
		try {
			//CMD unrar
			String[] a = new String[]{	"cmd.exe",
										"/C start "+workspace+"\\assets\\extractISO.bat \""+winrarPath+"\" "+fileLocation+"\\"+file+" "+extractLocation	
					  				};
			Process b = Runtime.getRuntime().exec(a);
			logInfo("Extracting the file at < "+fileLocation +"\\"+file+" > to < "+extractLocation+" >" );
			sleep(3);
			b.destroy();
		} catch (IOException e) {
			logError("ISO Extraction FAILED:"+e.toString());
			e.printStackTrace();
			stop();
		}
		while(winrarExtractingwindow().exists()){
			sleep(2);
		}
		logInfo("ISO Extraction Complete!");
	}
	
	
	public void deleteExport(){
		button_exportCasebutton().click();
		logInfo("Clicked export dropdown menu");
		link_exportManagement().click();
		logInfo("Clicked export management on dropdown");
		
		
		html_exportList().waitForExistence(120, DISABLED);
		sleep(2);
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		((GuiTestObject)columns[columns.length-1]).click();
		logInfo("Clicked detail button on export: "+exportName);
		button_deleteExportedFilesbutt().click();
		logInfo("Clicked delete export button");
		button_logoutYes().click();//apparently they are the same
		logInfo("Clicked yes to delete export");
		waitForloading();
	}
	
	
	public void simpleNavigateTree(){
		
		//Div->ul->div->li
		TestObject listOfDB = html_innerBodyLeftPanel().getChildren()[0].getChildren()[0].getChildren()[0];
		
		//ExpandExport
		((GuiTestObject)listOfDB.find(atChild(".class", "Html.DIV", ".text", "Export"), false)[0].find(atChild(".class", "Html.IMG"))[0]).click();
		logInfo("Expand on the top tree in left side folder");
		sleep(1);
		
		//Expand First User
		TestObject listOfUser = listOfDB.find(atChild(".class", "Html.UL"))[0];
		((GuiTestObject)listOfUser.find(atChild(".class", "Html.LI"), false)[0].getChildren()[0].find(atChild(".class", "Html.IMG"))[1]).click();
		logInfo("Expand first user");
	}
	
	public void testSelectAllandClearSelection(){
		//SelectAll
		button_selectionsbutton().click();
		logInfo("Selection button clicked");
		sleep(0.5);
		link_selectAll().click();
		logInfo("Clicked Select all on dropdown");
		
		TestObject[] results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		boolean allChecked = true;
		for(TestObject result : results){
			boolean checked = result.getParent().getProperty("class").toString().matches(".*x-grid3-row-selected.*");
			if(!checked){
				allChecked = false;
				logError("Not All Message Is Selected");
				break;
			}
		}
		if(allChecked){
			logTestResult("All message selected", true);
		}
		
		//ClearSelection
		button_selectionsbutton().click();
		logInfo("Selection button clicked");
		sleep(0.5);
		link_clearSelection().click();
		logInfo("Clicked clearSelection on dropdown");

		boolean nonChecked = true;
		results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		for(TestObject result : results){
			boolean checked = result.getParent().getProperty("class").toString().matches(".*x-grid3-row-selected.*");
			if(checked){
				nonChecked = false;
				logError("Expected no message selected");
				break;
			}
		}
		if(nonChecked){
			logTestResult("All message deselected", true);
		}
	}
	
	private void testSimpleAddTab(){
		int InitialTabSize = HelperClass.findAllTabs().length;
		link_addNewTab().click();
		logInfo("Clicked add new tab");
		sleep(1);
		vpManual("TabSizeIncrease", InitialTabSize+1,HelperClass.findAllTabs().length ).performTest();
		browser_htmlBrowser().activate();
		browser_htmlBrowser().click();
		HelperClass.findActiveTab().click(RIGHT);
		logInfo("right click  active tab");
		link_tabClose().click();
		logInfo("Click close tab");
		vpManual("TabSizeDecrease", InitialTabSize, HelperClass.findAllTabs().length ).performTest();
	}
	
	
	private void testDisplaySelected(){
		TestObject[] results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		int initialSizeOfResults = results.length;
		for(int i=0; i<results.length&& i<2; i++){
			TestObject[] checkbox = results[i].find(atDescendant(".tag", "TD", "class", new RegularExpression(".*x-grid3-td-checker.*", false)), false);
			((GuiTestObject)checkbox[0]).click();
		}
		
		button_selectionsbutton().click();
		button_selectionsbutton().unregister();
		logInfo("clicked selection");
		link_displaySelected().click();
		link_displaySelected().unregister();
		logInfo("clicked display all selected");
		sleep(2);
		waitForloading();
		results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		vpManual("Showing_Two_Selected", 2, results.length).performTest();
		
		button_selectionsbutton().click();
		logInfo("clicked selection");
		link_displaySelected().click();
		logInfo("clicked display all selection");
		sleep(2);
		results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		vpManual("All_Results_Displayed", initialSizeOfResults, results.length ).performTest();
	}
	
	private void waitForDownloadCompletion(){
		try{
		String downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
		while(!downloadFileText.matches(".*download has completed\\..*")){
			downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
			sleep(8);
		}
		}catch( java.lang.NullPointerException e){
			waitForDownloadCompletion();
			sleep(10);
		}
	}
}

