package TC_978_ExportPortableNetmailSearch;
import java.io.File;
import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import resources.TC_978_ExportPortableNetmailSearch.TS_1015_Print_NetmailLiteHelper;
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
public class TS_1015_Print_NetmailLite extends TS_1015_Print_NetmailLiteHelper
{
	/**
	 * Script Name   : <b>TS_1015_Print_NetmailLite</b>
	 * Generated     : <b>Oct 8, 2013 11:13:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/08
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(2);
		waitForloading();
		/*********PRINT BUTTON METHOD ******************************************************************/
		
		//Make sure file does not exists
		String homeDir = System.getProperty("user.home");
		File pdf = new File(homeDir+"\\Downloads\\"+dpString("pdfName_PrintMethod"));
		if(pdf.exists()){
			pdf.delete();
		}
		
		//Print to pdf
		logInfo("Selecting the LAST four message in the current and using the print button");
		CheckFour();
		sleep(1);
		button_printButton().click();
		sleep(1);
		
		if(!dpString("uncheckPrintOption").isEmpty()){
			TestObject[] printOptions = html_exportWindow2().find(atDescendant(".class", "Html.INPUT.checkbox"),true);
			for(String printOption: dpString("uncheckPrintOption").split(",")){
				((GuiTestObject)printOptions[Integer.parseInt(printOption.trim())-1]).click();
			}
		}
		
		//Print button
		button_exportPrintbutton().click();
		logInfo("Clicked Print");
			

		logInfo("Checking for Print Status Window");
		html_printStatusWindow().waitForExistence(100, DISABLED);
		if(html_printStatusWindow().ensureObjectIsVisible()){
			logTestResult("Print Status Window visible", true);
			
			if(!dpBoolean("printInBackground")){
				int timeout = 0;
				//Wait for print status window to finish
				while(html_printStatusWindow().exists()){
					sleep(2);
					timeout += 2;
					if(timeout == 120){
						logError("Print Status timeout, too long");
						break;
					}
				}	
			}else{
				//Print in background
				downloadBackgroundPrint();
			}	
		}else{
			logError("Print status never appeared", getRootTestObject().getScreenSnapshot());
		}
		
		
		if(!dpBoolean("printInBackground")){
			html_finishPrintWindowClose().click();
			if(browserVersion.contains("IE")){
				button_ieDownloadSave().click();
				sleep(3);
				button_ieDownloadClose().click();
				sleep(1);
				logInfo("Download Saved");
			}
		}
		
		//Save file, DEFAULT DOWNLOAD LOCATION SHOULD BE DOWNLOADS

		
		//Verify pdf
//		verifyPDF(dpString("expectedFileName_PrintMethod"), dpString("pdfName_PrintMethod"), null);
		
	}
	
	
	
	private void verifyPDF(String expectedName, String actualName, String mask){
		String expectedText ="";
		String actualText = "";
		try{	
			//String workspace = System.getProperty("user.dir");
			String workspace = dpString("workSpaceLocation");
			String homeDir = System.getProperty("user.home");
			
			logInfo("Looking for "+expectedName+" in "+workspace+"\\assets");
			File expectedFilename = new File(workspace+"\\assets\\TS_1015_Print_NetmailLite\\"+expectedName);
			
			logInfo("Looking for"+actualName+" in "+homeDir+"\\Downloads");
			File actualFilename = new File(homeDir+"\\Downloads\\"+actualName);	
			logInfo("Both File Found");
			
			//Load the PDF files
			PDDocument expectedDoc = PDDocument.load(expectedFilename);
			PDDocument actualDoc = PDDocument.load(actualFilename);
			
			PDFTextStripper stripper = new PDFTextStripper();
			expectedText = stripper.getText(expectedDoc).toString();
			actualText = stripper.getText(actualDoc).toString();
			
			if(mask!=null){
				expectedText = expectedText.replaceAll(mask, "HIDDEN");
				actualText = actualText.replaceAll(mask, "HIDDEN");
			}
			diff_match_patch dmp = new diff_match_patch();
			LinkedList<Diff> diffList = dmp.diff_main(expectedText, actualText,true);
			dmp.diff_cleanupSemantic(diffList);
			
			logInfo("Using PDFBOX Libary, verify the extracted pdf texts against expected pdf");
			if(expectedText.equals(actualText)){
				logTestResult("PDF texts are EQUAL!", true);
			}else{
				logTestResult("The PDF texts are different...", false, dmp.diff_prettyHtml(diffList));
				vpManual("Check_The_Difference", expectedText, actualText).performTest();
			}
				
			//Close the PDF files.
			expectedDoc.close();
			actualDoc.close();
			
			//Delete Files 
//			if(actualFilename.delete()){
//				logInfo("File is Deleted");
//			}else{
//				logError("File is not deleted! DELETE MANUALLY");
//			}
		}
		catch (Exception e){
			RationalTestScript.logException(e);
		}
	}
	
	private void CheckFour(){
		TestObject[] activeResultListDiv = find(atDescendant( ".tag", "DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel", false)), true);
		Property[] properties = new Property[2];
		properties[0] = new Property(".tag", "TABLE");
		properties[1] = new Property("class", new RegularExpression("^x-grid3-row-table$", false));

		TestObject[] messages = activeResultListDiv[0].find(atDescendant(properties),false);
		for(int i=0; i<4; i++){
			 TestObject[] checker = messages[i].find(atDescendant(".tag", "DIV", "class", new RegularExpression("^x-grid3-row-checker.*",false)),false);
			 ((GuiTestObject)checker[0]).click();
		}

	}
	
	private void waitUntilComplete(int depth, String exportName){
		if(depth++<10){ //Prevent forever calling
			try{
				TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
				TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
				while(!columns[2].getProperty(".text").toString().trim().toLowerCase().contentEquals("success")){
					exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
					columns = exports[0].find(atDescendant(".tag", "TD"), false);
					sleep(3);
				}
				logInfo("Selecting the export with name: "+exportName);
				((GuiTestObject)columns[columns.length-1].find(atDescendant(".tag", "A"))[0]).click();
				sleep(1);
				waitForloading();
				return;
			}catch(ArrayIndexOutOfBoundsException | ObjectNotFoundException e){
				waitUntilComplete(depth, exportName);
				return;
			}
		}else{
			logError("Timeout: waited too long for export to complete!");
			return;
		}
	}
	
	private void downloadBackgroundPrint(){
		button_backgroundbutton().click();
		logInfo("clicked print in background");

		TestObject[] printBackgroundMessage = html_dialogWindow().find(atDescendant(".class", "Html.SPAN", ".className", "ext-mb-text"));
		vpManual("Correct_printInBackground_message", true, printBackgroundMessage[0].getProperty(".contentText").toString().matches("(?i).*"+dpString("backgroundMessage")+".*")).performTest();
		button_deleteExport_Yesbutton().click(); //Apparently same yes button for all dialog window
		logInfo("clicked yes to confirm background print");
		sleep(3);
		
		//Ok to user help message that print is in export management
		button_oKbutton_backgroundprin().click();
		
		vpManual("printStatusWindow_is_in_background", false, html_printStatusWindow().exists()).performTest();

		
		//Open export management
		java.awt.Rectangle exportBounds = (java.awt.Rectangle) table_exportButton().getProperty(".bounds");
		table_exportButton().click(atPoint(exportBounds.width-5,exportBounds.height/2));
		logInfo("Clicked export case button");
		html_exportManagement().click();
		logInfo("Clicked export Management on dropdown");
		sleep(1);
		waitForloading();
		html_exportList().waitForExistence(120, DISABLED);
		
		//Find print export
		openExport("Print");
		
		//Download print 
		doubleClickExportfile(dpString("pdfName_PrintMethod"));
		if(browserVersion.contains("FF")){
			Export_SuperUser esu = new Export_SuperUser();
			esu.setExportName(dpString("pdfName_PrintMethod"));
			esu.downloadExportFile(dpString("pdfName_PrintMethod"));
		}	
	}
	
	
	private void doubleClickExportfile(String file){
		Property[] rowProperty = {	new Property(".tag", "TABLE"),
				new Property(".text", new RegularExpression("(?i).*"+file+".*", false)),
				new Property("class", "x-grid3-row-table"),
		};
		TestObject[] exportFiles = html_exportFilesList().find(atDescendant(rowProperty), true);
		if(exportFiles.length >= 1){
			((GuiTestObject)exportFiles[0]).click();
			((GuiTestObject)exportFiles[0]).doubleClick();
			logInfo("Selected < "+ file +" > file to download");
			sleep(3);
		}else{
			logError("Could not find export file by the name < "+ file +" >");
		}
	}
	
	
	private void openExport(String exportName){
		logTestResult("Export Management Window is open", html_exportList().ensureObjectIsVisible());
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		try{
			TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
			if( columns[0].getProperty(".text").equals(exportName)){
				waitUntilComplete(0, exportName);//wait for success Status
				sleep(10);
			}else{
				logError("Export not found!");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			logError("No export found!");
		}
	}
	
}

