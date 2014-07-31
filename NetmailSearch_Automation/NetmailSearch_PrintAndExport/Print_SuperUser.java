package NetmailSearch_PrintAndExport;


import org.apache.commons.logging.*;
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.rational.test.ft.script.Property;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.IFtVerificationPoint;



import resources.NetmailSearch_PrintAndExport.Print_SuperUserHelper;
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
public class Print_SuperUser extends Print_SuperUserHelper
{
	/**
	 * Script Name   : <b>TS_125_Print</b>
	 * Generated     : <b>Jun 18, 2013 10:31:30 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/18
	 * @author Administrator
	 */
	
	private boolean testExport = false;
	private String uncheckPrintOption = "";
	public void testMain(Object[] args) 
	{
//		String homeDir = null;
//		File pdf = null;
//		
///*********PRINT BUTTON METHOD ******************************************************************/
//				
//		//Make sure file does not exists
//		homeDir = System.getProperty("user.home");
//		pdf = new File(homeDir+"\\Downloads\\"+dpString("pdfName_PrintMethod"));	
//		if(pdf.exists()){
//			pdf.delete();
//		}
//		
//		logTestResult("Multiple print button does not exists", !button_printButton().exists());
//		
//		if( !html_exportWindow2().compare( exportWindow_IEVP())){
//			logInfo("Print Status Window Image Verification failed!", html_exportWindow2().getScreenSnapshot() );
//		}
//		
//		if(!dpString("uncheckPrintOption").isEmpty()){
//			TestObject[] printOptions = html_exportWindow2().find(atDescendant(".class", "Html.INPUT.checkbox"),true);
//			for(String printOption: dpString("uncheckPrintOption").split(",")){
//				((GuiTestObject)printOptions[Integer.parseInt(printOption.trim())-1]).click();
//			}
//		}
//		
//		//Print button
//		button_exportPrintbutton().click();
//		logInfo("Clicked Print");
//		
//		
//		if(dpBoolean("cancelPrint")){
//			cancelPrint();
//			return;//Exit Test
//		}
//			
//
//		logInfo("Checking for Print Status Window");
//		html_printStatusWindow().waitForExistence(100, DISABLED);
//		if(html_printStatusWindow().ensureObjectIsVisible()){
//			logTestResult("Print Status Window visible", true);
//			
//			if(!dpBoolean("printInBackground")){
//				int timeout = 0;
//				//Wait for print status window to finish
//				while(html_printStatusWindow().exists()){
//					sleep(2);
//					timeout += 2;
//					if(timeout == 200){
//						logError("Print Status timeout, too long");
//						break;
//					}
//				}	
//			}else{
//				//Print in background
//				downloadBackgroundPrint();
//			}	
//		}else{
//			logError("Print status never appeared", getRootTestObject().getScreenSnapshot());
//		}
//		
//		if(dpBoolean("printInBackground")){
//			html_attWindowCloseButton().click();
//			logInfo("Finished print window closed");
//			
//			//Delete background print
//			button_deleteExportedFilesbutt().click();
//			sleep(0.5);
//			button_deleteExport_Yesbutton().click();
//			sleep(3);
//			button_windowExportFile_Closeb().click();
//			logInfo("background print export deleted");
//			
//		}else{
//			html_finishPrintWindowClose().click();
//		}
//		
//		//Save file, DEFAULT DOWNLOAD LOCATION SHOULD BE DOWNLOADS
//		button_ieDownload_Save().click();
//		sleep(3);
//		button_ieDownload_Close().click();
//		sleep(1);
//		logInfo("Download Saved");		
	}
	
	public void printMessage(GuiTestObject tableMessageObject, boolean cancelPrint){
		tableMessageObject.click();
		tableMessageObject.doubleClick();
		waitForloading();
		
		button_messagePrint().click();
		
		if( !html_exportWindow2().compare( exportWindow_IEVP())){
			logInfo("Print Status Window Image Verification failed!", html_exportWindow2().getScreenSnapshot() );
		}
		
		if(!uncheckPrintOption.isEmpty()){
			TestObject[] printOptions = html_exportWindow2().find(atDescendant(".class", "Html.INPUT.checkbox"),true);
			for(String printOption:uncheckPrintOption.split(",")){
				((GuiTestObject)printOptions[Integer.parseInt(printOption.trim())-1]).click();
			}
		}
		
		//Print button
		button_exportPrintbutton().click();
		logInfo("Clicked Print");
		
		if(cancelPrint){
			cancelPrint();
			return;//Exit Test
		}
		
		
		logInfo("Checking for Print Status Window");
		html_printStatusWindow().waitForExistence(15, DISABLED);
		if(html_printStatusWindow().ensureObjectIsVisible()){
			logTestResult("Print Status Window visible", true);
			
				int timeout = 0;
				//Wait for print status window to finish
				while(html_printStatusWindow().exists()){
					sleep(2);
					timeout += 2;
					if(timeout == 200){
						logError("Print Status timeout, too long");
						break;
					}
				}		
		}else{
			logError("Print status never appeared", getRootTestObject().getScreenSnapshot());
		}
		
		//Save file, DEFAULT DOWNLOAD LOCATION SHOULD BE DOWNLOADS
		button_ieDownload_Save().click();
		sleep(3);
		button_ieDownload_Close().click();
		sleep(1);
		logInfo("Download Saved");
	}
	
	
	
	public void printSelectedInBackground(boolean cancelPrint){
		waitForloading();
		button_printButton().click();
		
		if( !html_exportWindow2().compare( exportWindow_IEVP())){
			logInfo("Print Status Window Image Verification failed!", html_exportWindow2().getScreenSnapshot() );
		}
		
		if(!uncheckPrintOption.isEmpty()){
			TestObject[] printOptions = html_exportWindow2().find(atDescendant(".class", "Html.INPUT.checkbox"),true);
			for(String printOption:uncheckPrintOption.split(",")){
				((GuiTestObject)printOptions[Integer.parseInt(printOption.trim())-1]).click();
			}
		}
		
		//Print button
		button_exportPrintbutton().click();
		logInfo("Clicked Print");
		
		if(cancelPrint){
			cancelPrint();
			return;//Exit Test
		}
		
		
		logInfo("Checking for Print Status Window");
		html_printStatusWindow().waitForExistence(100, DISABLED);
		if(html_printStatusWindow().ensureObjectIsVisible()){
			logTestResult("Print Status Window visible", true);
			//Print in background
			
			button_backgroundbutton().click();
			logInfo("clicked print in background");
			waitForloading();
			sleep(3);

			TestObject[] printBackgroundMessage = html_dialogWindow().find(atDescendant(".class", "Html.SPAN", ".className", "ext-mb-text"));
			vpManual("Correct_printInBackground_message", true, printBackgroundMessage[0].getProperty(".contentText").toString().matches("(?i).*Are you sure you want to run this print as a background export to PDF job ?.*")).performTest();
			button_deleteExport_Yesbutton().click(); //Apparently same yes button for all dialog window
			logInfo("clicked yes to confirm background print");
			sleep(3);
			vpManual("printStatusWindow_is_in_background", false, html_printStatusWindow().exists()).performTest();
			
			//Click ok for reminder
			if(button_oKbutton().exists()){
				button_oKbutton().click();
			}
			
		}else{
			logError("Print status never appeared", getRootTestObject().getScreenSnapshot());
		}
	}
	
	public void verifyPDF(String expectedPDFPath, String actualPDFPath, String[] masks){
		String expectedText ="";
		String actualText = "";
		try{	
			
			logInfo("Looking for ["+expectedPDFPath+"]");
			File expectedFilename = new File(expectedPDFPath);
			
			logInfo("Looking for ["+actualPDFPath+"]");
			File actualFilename = new File(actualPDFPath);	
			logInfo("Both File Found");
			
			//Load the PDF files
			PDDocument expectedDoc = PDDocument.load(expectedFilename);
			PDDocument actualDoc = PDDocument.load(actualFilename);
			
			PDFTextStripper stripper = new PDFTextStripper();
			expectedText = stripper.getText(expectedDoc).toString();
			actualText = stripper.getText(actualDoc).toString();
			
			for(String mask : masks){
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
		}
		catch (Exception e){
			RationalTestScript.logException(e);
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
		vpManual("printStatusWindow_is_in_background", false, html_printStatusWindow().exists()).performTest();
		
		//Click ok for reminder
		if(button_oKbutton().exists()){
			button_oKbutton().click();
		}
		
		//Open export management
		button_exportCasebutton().click();
		logInfo("Clicked export case button");
		link_exportManagement().click();
		logInfo("Clicked export Management on dropdown");
		
		
		//Find print export
		openExport("Print");
		
		//Download print 
		doubleClickExportfile(dpString("pdfName_PrintMethod"));
		
		
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
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		if( columns[0].getProperty(".text").equals(exportName)){
			Export_SuperUser esu = new Export_SuperUser();
			esu.openWhenTopExportComplete();//wait for success Status
			sleep(10);
		}else{
			logError("Export not found!");
		}
	}
	
	private void cancelPrint(){
		html_printStatusWindow().waitForExistence(100, DISABLED);
		button_printCancelbutton().click();
		logInfo("Clicked Cancel print");
		
		TestObject[] cancelMessage = html_dialogWindow().find(atDescendant(".class", "Html.SPAN", ".className", "ext-mb-text"));
		vpManual("Correct_Cancel_MessagE", true, cancelMessage[0].getProperty(".contentText").toString().matches("(?i).*"+dpString("cancelMessage")+".*")).performTest();
		button_deleteExport_Yesbutton().click(); //Apparently same yes button for all dialog window
		logInfo("clicked yes to confirm cancel print");
		sleep(3);
		if(html_printStatusWindow().exists() | button_ieDownload_Save().exists()){
			logTestResult("Cancel Print Succeeded", false);
		}else{
			logTestResult("Cancel Print Succeeded", true);
		}
	}
}

