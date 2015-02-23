package TC_441_ExportToPDF;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import resources.TC_441_ExportToPDF.TS_975_SuperUserExportOnePDFPerItemHelper;
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
public class TS_975_SuperUserExportOnePDFPerItem extends TS_975_SuperUserExportOnePDFPerItemHelper
{
	/**
	 * Script Name   : <b>TS_975_SuperUserExportOnePDFPerItem</b>
	 * Generated     : <b>Aug 19, 2013 11:16:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/08/19
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
		attachmentFileName = dpString("attachmentFileName");
		
		//Login
		NetmailLogin.login();
		//AdminLogin
		adminLogin.selectUserType(dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		sleep(5);
		waitForloading();
		
		Export_SuperUser esu = new Export_SuperUser();
		esu.setExportName(dpString("exportName"));
		esu.setSearchTabs(dpString("searchTabIndex"));
		esu.setItemOptions(dpInt("itemOption"));
		esu.setExportTypeOption(dpInt("exportTypeOption"));
		esu.setPassword(password);
		esu.setCustomGB(String.valueOf(dpFloat("customGB")));
		esu.setAdditionalOptions(dpString("additionalOptions"));
		esu.setIsLargeExport(dpBoolean("largeExport"));
		esu.create();
		
		//Setup
		for(String file : files){
			setUpAndTearDown(true, file.trim());
		}
		setUpAndTearDown(true, attachmentFileName);
		
		//Open export: Newest export are always first
		openExport();
		sleep(5);
		waitForloading();
		
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
				adminLogin.selectUserType(dpString("userType"));
				adminLogin.selectCase(dpString("caseName"));
				
				button_exportCasebutton().click();
				logInfo("Clicked export dropdown menu");
				link_exportManagement().click();
				logInfo("Clicked export management on dropdown");
				
				html_exportList().waitForExistence(120, DISABLED);
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
				extract(file);
			}
//			validateOnePdfPerItem(file);
		}
		
		
		//Download Attachment zip
		if(!attachmentFileName.isEmpty()){
			
			//RFT BUG when downloading: No object can be found after notification bar appears;
			HelperClass.CloseAllBrowsers();
			//Login
			NetmailLogin.login();
			//AdminLogin
			adminLogin.selectUserType(dpString("userType"));
			adminLogin.selectCase(dpString("caseName"));
			
			button_exportCasebutton().click();
			logInfo("Clicked export dropdown menu");
			link_exportManagement().click();
			logInfo("Clicked export management on dropdown");
			
			html_exportList().waitForExistence(120, DISABLED);
			sleep(2);
			exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
			columns = exports[0].find(atDescendant(".tag", "TD"), false);
			((GuiTestObject)columns[columns.length-1]).click();
			logInfo("Clicked detail button on export: "+exportName);
			sleep(5);
			waitForloading();
			
			downloadFile(attachmentFileName);
			
			//Validate Attachment
//			verifyAttachement(attachmentFileName);
			
		}
		
		//Delete Export
		HelperClass.CloseAllBrowsers();
		logInfo("Close all browsers"); //Bug bypass: There is a RFT bug when notification bar comes up no object can be found anymore.
		//Login
		NetmailLogin.login();
		//AdminLogin
		adminLogin.selectUserType(dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		deleteExport();
		
		//Delete Files
//		for(String file : files){
//			file = file.trim();
//			setUpAndTearDown(false, file);
//		}
//		setUpAndTearDown(false, attachmentFileName);

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
	
	private void extract(String file){
		HelperScript hs = new HelperScript();
		if(password!=null && !password.isEmpty()){
			logInfo("extracting < "+file+" > to < "+ extractLocation +" > using password < "+password+" >" );
			hs.extractZip(fileLocation+"\\"+file, extractLocation, workspace, password);
		}else{
			hs.extractZip(fileLocation+"\\"+file, extractLocation, workspace);
		}
		logInfo("ZIP Extraction Complete!");
	}
	
	
	
	private void downloadFile(String file){
		
		file = file.trim();
		html_exportFilesList().waitForExistence(120, DISABLED);
		sleep(2);
		
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
			sleep(10);
			
			//IE Notification Control
			TestObject downloadObject = findNotificationBar("Notification bar Text");
			if(downloadObject == null){
				((GuiTestObject)exportFiles[0]).doubleClick();
				downloadObject = findNotificationBar("Notification bar Text");
			}
			
			String downloadFileText = downloadObject.getProperty(".text").toString();
			String expectedDownloadMessage = "Do you want to open or save "+file+" from .*";
			logInfo("Verifying if < "+downloadFileText+" > mathces < "+expectedDownloadMessage+" >" );
			logTestResult("Correct_download_Message", downloadFileText.matches(expectedDownloadMessage) );
			HelperClass.ieNotificationElement("Save").click();
			logInfo("Clicked Save file on browser");
			sleep(10);
			
			
			//Wait for download to finish
			TestObject downloadingBar = findNotificationBar("Notification bar Text");
			downloadFileText = downloadingBar.getProperty(".text").toString();
			while(!downloadFileText.matches(".*download has completed\\..*")){
				downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
				sleep(8);
			}
			
			//Close IE Notification
			while(HelperClass.ieNotificationElement("Close") != null){
				HelperClass.ieNotificationElement("Close").click();
				sleep(1);
			}
			logInfo("Clicked close button for finished download notification");
			
		}else{
			logError("Could not find export file by the name < "+ file +" >");
			stop();
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
	
	private void validateOnePdfPerItem(String file){
		String folderName = file.replace(".zip", "");
		
		//Find Files
		File actualFolder = new File(extractLocation+"\\"+folderName);
		File expectedFolder = new File(workspace+"\\assets\\TS_975\\"+folderName);
		logInfo("Found both folders");
		
		//Verify PDFs
		verifyFolderPDF(actualFolder, expectedFolder);
	}
	
	public void verifyFolderPDF(File actualFolder, File expectedFolder){
		String hideDateExpr = "\\d{4}\\.\\d{1,2}\\.\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
		File[] actualFiles = actualFolder.listFiles();
		File[] expectedFiles = expectedFolder.listFiles();
		if(expectedFiles == null){
			logError("Folder Does not Match, cannot find: "+actualFolder.getName());
			return;
		}
		Arrays.sort(actualFiles);
		Arrays.sort(expectedFiles);
		
		if(actualFiles.length == expectedFiles.length){
			logInfo("Validating PDF(s)");
			boolean allEqual = true;
			for(int i = 0; i<actualFiles.length ; i++){
				if(actualFiles[i].isDirectory()){
					String directory = actualFiles[i].getPath().replace(actualFolder.getPath(), "");
					File newExpectedFolder = new File(expectedFolder.getPath()+directory);
					verifyFolderPDF(actualFiles[i], newExpectedFolder);
					continue;
				}

				
				//TODO Verify if front end automation needs to check pdf content
//				Object[] result = HelperClass.extractAndComparePDF(expectedFiles[i], actualFiles[i], hideDateExpr);
//				if(!(boolean)result[2]){
//					logInfo("Expected pdf < "+expectedFiles[i].getName()+" > does not match actual pdf < "+actualFiles[i].getName()+" >");
////					vpManual("VerifyText", result[0], result[1]).performTest();
//					allEqual = false;
//				}
			}			
			vpManual("Export_PDF_verification", true, allEqual).performTest();
		}else{
			logError("Actual zip contains: "+actualFiles.length+" Files where expected number of file is: "+expectedFiles.length);
		}
	}
	
	private void verifyAttachement(String attachmentName){
		String attachmentFolderPath = extractLocation+"\\"+exportName+"_attachment";
		File attachmentFolder = new File(attachmentFolderPath);
		if(attachmentFolder.mkdir()){
			
			//extract attachment
			logInfo("extracting < "+attachmentName+"> to < "+ attachmentFolderPath +" > using the password < "+password+" >" );
			HelperClass.extract(workspace, winrarPath, fileLocation+"\\"+attachmentName, attachmentFolderPath, password);
			while(winrarExtractingwindow().exists()){
				sleep(2);
			}
			logInfo("ZIP Extraction Complete!");
			
			//Verify
			vpManual("Export_Attachement_Verification", true, compareFiles(attachmentFolder)).performTest();
			
		}else{
			logError("cannot create folder < "+exportName+" > in "+ extractLocation);
		}
		
	}
	
	
	private boolean compareFiles(File folder){
		// verify using checksum if it is file.
		File[] folderFiles = folder.listFiles();
		File[] expectedFiles = new File(workspace+"\\assets\\TS_975"+folder.getPath().replace(extractLocation, "")).listFiles();
		
		if(folderFiles.length == expectedFiles.length){
			for(File file : folderFiles){
				if( file.isDirectory()){
					if(!compareFiles(file)){
						return false;
					}
				}else if(file.isFile()){
					File expectedFile = new File(workspace+"\\assets\\TS_975"+file.getPath().replace(extractLocation, ""));
					String expectedChkSum = HelperClass.getCheckSum(expectedFile, "SHA1");
					String actualChkSum = HelperClass.getCheckSum(file, "SHA1");
					if(!expectedChkSum.contentEquals(actualChkSum)){
						return false;
					}
				}else{
					logError("In oblivion");
					return false;
				}
			}
			return true;
		}else{
			logError("Actual attachment folder at < "+folder.getPath()+" > contains: "+folderFiles.length+" Files where expected number of file is: "+expectedFiles.length);
			return false;
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
			}else{
				logError("Export not found!");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			//Due to the object getting refreshed every 7 second, there is a 
			//small chance it try to find the object while refreshing causing object not found.
			openExport();
		}
	}
	
	public TestObject findNotificationBar(String name){
		TestObject downloadObject = HelperClass.ieNotificationElement(name);
		int counter = 0;
		while(downloadObject==null){
			sleep(3);
			downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
			if(counter++>15){
				logError("Could not find IE notification bar to save file");
				return null;
			}
		}
		return downloadObject;
	}
	
	@Override
	public void onTerminate(){
		browser_htmlBrowser().inputKeys("{F5}");		
	}
	
}

