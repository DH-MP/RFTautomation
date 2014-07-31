package TC_978_ExportPortableNetmailSearch;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import resources.TC_978_ExportPortableNetmailSearch.TS_124_Export_NetmailLiteHelper;
import utilities.HelperClass;

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
public class TS_124_Export_NetmailLite extends TS_124_Export_NetmailLiteHelper
{
	/**
	 * Script Name   : <b>TS_124_Export_NetmailLite</b>
	 * Generated     : <b>Nov 5, 2013 9:57:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/11/05
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
		password = dpString("zipPassword");
		attachmentFileName = dpString("attachmentFileName");
		
		//Login
		Object[] ls = {dpString("username"), dpString("password"), false, true};
		callScript("loginScript", ls);
		//AdminLogin
		Object[] al = {dpString("caseName"), dpString("userType"), true};
		callScript("adminLogin", al);
		
		Object[] startNetmail = {	1, 
									2,
									3,
									1,
									"ISO.zip"
		};
		callScript("TC_978_ExportPortableNetmailSearch.startNetmailPortable", startNetmail);
		
		
		//NetmailLite NewExport
		Object[] nES = {dpString("searchTabIndex"), 
				dpInt("itemOption"),
				dpInt("exportTypeOption"),
				dpInt("packagingOption"),
				dpString("exportName"),
				"", //no email
				password,
				dpFloat("customGB"),
				dpString("additionalOptions"),
				false,
		};
		callScript("newExport_Super", nES);
		sleep(10);
		
		//Setup
		for(String file : files){
			setUpAndTearDown(true, file.trim());
		}
		setUpAndTearDown(true, attachmentFileName);
		
		//Open export: Newest export are always first
		openExport();
		
		//Download Export zip file(s)
		for(String file : files){
			file = file.trim();

			downloadFile(file);
			if(files.length>1){
				
				//RFT BUG when downloading: No object can be found after notification bar appears;
				((BrowserTestObject)HelperClass.findBrowser()).inputKeys("{F5}");
				waitForloading();
				waitForloading();
				waitForloading();
				
				java.awt.Rectangle bounds = (java.awt.Rectangle) table_exportButton().getProperty(".bounds");
				table_exportButton().click(atPoint(bounds.width-3,bounds.height/2));
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
			validateOnePdfPerItem(file);
		}
		
		//Some attachments are over 1gb in size and it better to test a smaller scale
		if(dpBoolean("validateAttachment")){
			//Download Attachment zip
			if(!attachmentFileName.isEmpty()){
				
				//RFT BUG when downloading: No object can be found after notification bar appears;
				browser_htmlBrowser().inputKeys("{F5}");
				
				
				java.awt.Rectangle bounds = (java.awt.Rectangle) table_exportButton().getProperty(".bounds");
				table_exportButton().click(atPoint(bounds.width-3,bounds.height/2));
				logInfo("Clicked export dropdown menu");
				link_exportManagement().click();
				logInfo("Clicked export management on dropdown");
				
				html_exportList().waitForExistence(120, DISABLED);
				sleep(2);
				exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
				columns = exports[0].find(atDescendant(".tag", "TD"), false);
				((GuiTestObject)columns[columns.length-1]).click();
				logInfo("Clicked detail button on export: "+exportName);
				
				downloadFile(attachmentFileName);
				
				//Validate Attachment
				verifyAttachement(attachmentFileName);
				
			}
		}
		
		
		browser_htmlBrowser().inputKeys("{F5}");
		waitForloading();
		callScript("TC_978_ExportPortableNetmailSearch.TC_978_ExitNetmail");

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
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("file", file);
		map.put("password", password);
		map.put("extractLocation", extractLocation);
		map.put("fileLocation", fileLocation);
		map.put("workspace", workspace);
		map.put("winrarPath", winrarPath);
		Object[] a = {"extractZip", map};
		callScript("utilities.HelperScript", a);
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
			sleep(5);
			
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
			sleep(4);
			
			
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
	
	
	private void waitUntilComplete(){
			try{
				TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
				TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
				while(!(columns[3].getProperty(".text").toString().trim().toLowerCase().contentEquals(dpString("exportStatus")) | columns[3].getProperty(".text").toString().trim().toLowerCase().contentEquals("success"))){
					exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
					columns = exports[0].find(atDescendant(".tag", "TD"), false);
					sleep(3);
				}
				logInfo("Selecting the export with name: "+exportName);
				((GuiTestObject)columns[columns.length-1]).click();
				return;
			}catch(ArrayIndexOutOfBoundsException | ObjectNotFoundException e){
				waitUntilComplete();
				return;
			}
	}
	
	private void validateOnePdfPerItem(String file){
		String folderName = file.replace(".zip", "");
		
		//Find Files
		File[] actualFiles = new File(extractLocation+"\\"+folderName).listFiles();
		File[] expectedFiles = new File(workspace+"\\assets\\TS_124_Lite\\"+folderName).listFiles();
		Arrays.sort(actualFiles);
		Arrays.sort(expectedFiles);
		logInfo("Found both folders");
		
		//Verify PDFs
		String hideDateExpr = "\\d{4}\\.\\d{1,2}\\.\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
		if(actualFiles.length == expectedFiles.length){
			logInfo("Validating PDF(s)");
			boolean allEqual = true;
			for(int i = 0; i<actualFiles.length ; i++){
				
				//Some export have large amount of pdf therefore the export is not always consistent.
				//Better to validate pdf's when it is a small scale export
				if(dpBoolean("validatePDF")){
					Object[] result = HelperClass.extractAndComparePDF(expectedFiles[i], actualFiles[i], hideDateExpr);
					if(!(boolean)result[2]){
						logInfo("Expected pdf < "+expectedFiles[i].getName()+" > does not match actual pdf < "+actualFiles[i].getName()+" >");
						//vpManual("VerifyText", result[0], result[1]).performTest();
						allEqual = false;
					}
				}
			}
			vpManual("Export_PDF_verification", true, allEqual).performTest();
		}else{
			logError("Actual zip contains: "+actualFiles.length+" Files where expected number of file is: "+expectedFiles.length);
		}
	}
	
	private boolean compareFiles(File folder){
		// verify using checksum if it is file.
		File[] folderFiles = folder.listFiles();
		File[] expectedFiles = new File(workspace+"\\assets\\TS_124_Lite"+folder.getPath().replace(extractLocation, "")).listFiles();
		
		if(folderFiles.length == expectedFiles.length){
			for(File file : folderFiles){
				if( file.isDirectory()){
					if(!compareFiles(file)){
						return false;
					}
				}else if(file.isFile()){
					File expectedFile = new File(workspace+"\\assets\\TS_124_Lite"+file.getPath().replace(extractLocation, ""));
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
				waitUntilComplete();//wait for success Status
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
	
	public TestObject findNotificationBar(String name){
		TestObject downloadObject = HelperClass.ieNotificationElement(name);
		int counter = 0;
		while(downloadObject==null){
			sleep(3);
			downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
			if(counter++>10){
				logError("Could not find IE notification bar to save file");
				return null;
			}
		}
		return downloadObject;
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
	
	
	

}

