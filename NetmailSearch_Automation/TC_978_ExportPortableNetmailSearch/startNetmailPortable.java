package TC_978_ExportPortableNetmailSearch;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import resources.TC_978_ExportPortableNetmailSearch.startNetmailPortableHelper;
import utilities.HelperClass;

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
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class startNetmailPortable extends startNetmailPortableHelper
{
	/**
	 * Script Name   : <b>startNetmailPortable</b>
	 * Generated     : <b>Oct 1, 2013 9:12:21 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/01
	 * @author Administrator
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
		file = args.length == 5 ?  (String) args[4] : dpString("fileToDownload");
		workspace = dpString("workSpaceLocation");
		winrarPath = dpString("winrarPath");
		fileLocation = dpString("fileLocation");
		extractLocation = dpString("extractLocation");
		exportName = dpString("exportName");
		
		//For Bug after downloading notification appear(relogin)
		Object[] ls = {null, null, false, true};
		Object[] al = {"ExportXML", "Super User", true};
	
		//NewExport
		Object[] nES = {args[0].toString(), 
						(int) args[1],
						(int) args[2],
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
		String file = this.file.trim();
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
			if(counter == 5){
				((GuiTestObject)exportFiles[0]).doubleClick();
			}
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
		try{
			downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
			while(!downloadFileText.matches(".*download has completed\\..*")){
				downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
				sleep(10);
			}
		}catch(NullPointerException e){
			//due to rare chances of not finding object, just wait very long for download to finish
			sleep(300);
		}
		HelperClass.ieNotificationElement("Close").click();
		logInfo("Clicked close button for finished download notification");
		
		//extractISO
		extractISO( this.file, "", extractLocation, fileLocation, workspace, winrarPath);

		
		//Delete Export
		HelperClass.CloseAllBrowsers();
		sleep(3);
		logInfo("Close all browsers"); //Bug bypass: There is a RFT bug when notification bar comes up no object can be found anymore.
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		sleep(1);
		deleteExport();
	
		//Verify NetmailSearchLite.exe
		try {
			HelperClass.CloseAllBrowsers();
			logInfo("Closer all browser");
			Process netmailLite = Runtime.getRuntime().exec(extractLocation+"\\NetmailSearchLite.exe");
			logInfo("Launched netmail search lite");
			browser_htmlBrowser().waitForExistence(240, DISABLED);
			sleep(5);
			waitForloading();
			waitForloading();
			waitForloading();
			
			netmailLite.destroy();
			sleep(20);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
//		//Delete Files
//		setUpAndTearDown(false);
		
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
				sleep(20);
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
	
	public void extractISO(String file, String password, String extractLocation, String fileLocation, String workspace, String winrarPath){
			HashMap<String, String> params = new HashMap<String, String> ();
			params.put("file", file);
			params.put("password", password);
			params.put("extractLocation", extractLocation);
			params.put("fileLocation", fileLocation);
			params.put("workspace", workspace);
			params.put("winrarPath", winrarPath);
			
			Object[] uHS = {"extractZip", params};
			callScript("utilities.HelperScript", uHS );
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



}

