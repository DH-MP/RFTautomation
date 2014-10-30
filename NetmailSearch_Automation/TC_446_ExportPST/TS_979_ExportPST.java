package TC_446_ExportPST;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import resources.TC_446_ExportPST.TS_979_ExportPSTHelper;
import utilities.HelperClass;
import utilities.HelperScript;

import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;
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
public class TS_979_ExportPST extends TS_979_ExportPSTHelper
{
	/**
	 * Script Name   : <b>TS_979_ExportPST</b>
	 * Generated     : <b>Aug 27, 2013 4:55:45 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/08/27
	 * @author Administrator
	 */
	
	private String 	exportName = "",			
			workspace = "", 
			winrarPath = "",
			fileLocation = "",
			extractLocation = "",
			password = "",
			attachmentFileName= "";

	private String[] files;
	
	
	
	public void testMain(Object[] args) 
	{
		TestObject[] exports = null, columns = null;
		
		files = dpString("filesToDownload").split(",");
		workspace = dpString("workSpaceLocation");
		winrarPath = dpString("winrarPath");
		fileLocation = dpString("fileLocation");
		extractLocation = dpString("extractLocation");
		exportName = dpString("exportName");
		password = dpString("password");
		
		//Login
		NetmailLogin.login();
		//AdminLogin
		adminLogin.selectUserType( dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		
		
		Export_SuperUser esu = new Export_SuperUser();
		esu.setExportName(dpString("exportName"));
		esu.setSearchTabs(dpString("searchTabIndex"));
		esu.setItemOptions(dpInt("itemOption"));
		esu.setExportTypeOption(dpInt("exportTypeOption"));
		esu.setPassword(password);
		esu.setCustomGB(String.valueOf(dpFloat("customGB")));
		esu.setAdditionalOptions("");
		esu.setIsLargeExport(dpBoolean("largeExport"));
		esu.create();
		
		//Setup
		for(String file : files){
			setUpAndTearDown(true, file.trim());
		}
		
		//Open export: Newest export are always first
		openExport();
		
		
		//Download Export zip file(s)
		for(String file : files){
			file = file.trim();

			downloadFile(file);
			if(files.length>1){
				//RFT BUG when downloading: No object can be found after notification bar appears;
				HelperClass.CloseAllBrowsers();
				
				//Login
				NetmailLogin.login();
				//AdminLogin
				adminLogin.selectUserType( dpString("userType"));
				adminLogin.selectCase(dpString("caseName"));
				
				button_exportCasebutton().click();
				logInfo("Clicked export dropdown menu");
				link_exportManagement().click();
				logInfo("Clicked export management on dropdown");
				
				exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
				columns = exports[0].find(atDescendant(".tag", "TD"), false);
				((GuiTestObject)columns[columns.length-1]).click();
				logInfo("Clicked detail button on export: "+exportName);
			}
		}
				

		//Check all the Files
		for(String file : files){
			file = file.trim();
			//Extract ISO
			if(file.contains(".zip")){				
				HelperScript hs = new HelperScript();
				if(password!=null && !password.isEmpty()){
					hs.extractZip(fileLocation+"\\"+file, extractLocation, workspace);
				}else{
					logInfo("extracting < "+file+" > to < "+ extractLocation +" > using password < "+password+" >" );
					hs.extractZip(fileLocation+"\\"+file, extractLocation, workspace, password);
				}
				logInfo("ZIP Extraction Complete!");
			}
		}
				
		//Delete Export
		HelperClass.CloseAllBrowsers();
		logInfo("Close all browsers"); //Bug bypass: There is a RFT bug when notification bar comes up no object can be found anymore.
		
		//Login
		NetmailLogin.login();
		//AdminLogin
		adminLogin.selectUserType( dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		
		deleteExport();
		
		//Delete Files
		for(String file : files){
			file = file.trim();
			setUpAndTearDown(false, file);
		}
		
		logInfo("TODO: Verify PST using the tool develop by netmail when it is complete");
	}
	
	
	public void setUpAndTearDown(boolean isSetup, String file){
		boolean setup = isSetup;
		
		//Delete zip file if it exists
		File pdf = new File(fileLocation+"\\"+file);
		if(pdf.exists()){
			pdf.delete();
			logInfo("zip file deleted");
		}
		
		//Delete zip folder if it exists
		File zipFolder = new File(extractLocation);
		int count = 0;
		while(zipFolder.exists()){
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
			zipFolder = new File(extractLocation);
			logInfo("zip Folder deleted");
			
			if(count++>5){
				logError("Cannot Delete zip folder attempted 5 times");
				break;
			}
		}
	
		//Create ISO folder if it doesn't exists for setup
		if(!zipFolder.exists() && setup){
			zipFolder.mkdir();
		}
	}
	
	private void openExport(){
		logTestResult("Export Management Window is open", html_exportList().ensureObjectIsVisible());
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		
		try{

			if( columns[0].getProperty(".text").equals(exportName)){
				Export_SuperUser esu = new Export_SuperUser();
				esu.openWhenTopExportComplete(dpString("exportStatus"));//wait for success Status
				sleep(10);
			}else{
				logError("Export not found!");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			//Due to the object getting refreshed every 7 second, there is a 
			//small chance it try to find the object while refreshing causing object not found.
			openExport();
		}
	}
	
	private void deleteExport(){
		Export_SuperUser esu = new Export_SuperUser();
		esu.openExportManagementWindow();
		waitForloading();
		
		html_exportList().waitForExistence(120, DISABLED);
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		esu.deleteExport(exports[0]);
		waitForloading();
	}
	
	
	
	
	private void downloadFile(String file){
		file = file.trim();
		Property[] rowProperty = {	new Property(".tag", "TABLE"),
				new Property(".text", new RegularExpression("(?i).*"+file+".*", false)),
				new Property("class", "x-grid3-row-table"),
		};
		
		TestObject[] exportFiles = html_exportFilesList().find(atDescendant(rowProperty), true);
		if(exportFiles.length >= 1){
			
			//Double Click file
			((GuiTestObject)exportFiles[0]).click();
			((GuiTestObject)exportFiles[0]).doubleClick();
			logInfo("Selected < "+ file +" > file to download");
			sleep(5);
			
			//IE Notification Control
			TestObject downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
			int counter = 0;
			while(downloadObject==null){
				((GuiTestObject)exportFiles[0]).doubleClick();
				sleep(5);
				downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
				if(counter++>10){
					logError("Could not find IE notification bar to save file");
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
			downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
			while(!downloadFileText.matches(".*download has completed\\..*")){
				downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
				sleep(8);
			}
			HelperClass.ieNotificationElement("Close").click();
			sleep(1);
			logInfo("Clicked close button for finished download notification");
			
		}else{
			logError("Could not find export file by the name < "+ file +" >");
		}
	}
}

