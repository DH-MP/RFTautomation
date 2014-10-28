package TC_483_LoginAsRegularUser;


import org.apache.commons.logging.*;
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;

import com.rational.test.ft.script.Property;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.IFtVerificationPoint;



import resources.TC_483_LoginAsRegularUser.TS_125_PrintHelper;
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
public class TS_125_Print extends TS_125_PrintHelper
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
	public void testMain(Object[] args) 
	{
		String homeDir = null;
		File pdf = null;
		boolean isAdmin = dpBoolean("isAdmin"),
				testExport = dpBoolean("testExport");
		
		//Login
		NetmailLogin.login();
		//AdminLogin
		adminLogin.selectUserType(dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		
		GuiTestObject quickSearch = isAdmin ? text_quickSearchField0(): text_normalUser_quickSearchFie();

/*********SETUP ****************************************************************************/
		if(!isAdmin){
			//ALS MailBox Default
			Property[] ps = new Property[3];
			ps[0] = new Property(".class", "Html.A");
			ps[1] = new Property("class", "x-tree-node-anchor");
			ps[2] = new Property(".text", "ALS");
			GuiTestObject alsLink = (GuiTestObject) html_folderTree0().find(atDescendant(ps), true)[0];
			GuiTestObject expander = (GuiTestObject) alsLink.getParent().getChildren()[1];
			expander.click();
			sleep(3);
			
			ps[0] = new Property(".class", "Html.A");
			ps[1] = new Property("class", "x-tree-node-anchor");
			ps[2] = new Property(".text", "Mailbox");
			GuiTestObject mailboxLink = (GuiTestObject) alsLink.getParent().getParent().find(atDescendant(ps), true)[0];
			mailboxLink.click();
		}
		
		quickSearch.click();
		logInfo("clicked on quickSearch");
		browser_htmlBrowser().inputKeys("^a{DELETE}BCC{ENTER}");
		sleep(20);
		
/*********PRINT BUTTON METHOD ******************************************************************/
				
		//Make sure file does not exists
		homeDir = System.getProperty("user.home");
		pdf = new File(homeDir+"\\Downloads\\"+dpString("pdfName_PrintMethod"));
		if(pdf.exists()){
			pdf.delete();
		}
		
		//Print to pdf
		logInfo("Validate the PDF generated using the print button");
		if(isAdmin){//Being admin doesn't have to do anything. Admin have no choice but to select last four message.
			logInfo("Selecting the LAST four message in the current and using the print button");
			CheckFour();
			button_printButton().click();
			sleep(4);
			
		}else{//Non admin have to quick search a single message first
			quickSearch.click();
			logInfo("clicked on quickSearch");
			browser_htmlBrowser().inputKeys("^a{DELETE}"+dpString("quickSearch")+"{ENTER}");
			logInfo("searched < "+dpString("quickSearch")+" >");
			
			TestObject[] activeResultListDiv = find(atDescendant( ".tag", "DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel", false)), true);
			Property[] properties = new Property[2];
			properties[0] = new Property(".tag", "TABLE");
			properties[1] = new Property("class", new RegularExpression("^x-grid3-row-table$", false));

			TestObject[] messages = activeResultListDiv[0].find(atDescendant(properties),false);
			((GuiTestObject)messages[0]).doubleClick();
			logInfo("double clicked to open first message in list");
			button_messagePrintButton().click();
			logInfo("clicked print button in message box");
			sleep(6);
		}

		if( !html_exportWindow2().compare( exportWindow_IEVP())){
			logInfo("Print Status Window Image Verification failed!", html_exportWindow2().getScreenSnapshot() );
		}
		
		if(!dpString("uncheckPrintOption").isEmpty()){
			TestObject[] printOptions = html_exportWindow2().find(atDescendant(".class", "Html.INPUT.checkbox"),true);
			for(String printOption: dpString("uncheckPrintOption").split(",")){
				((GuiTestObject)printOptions[Integer.parseInt(printOption.trim())-1]).click();
			}
		}
		
		//Print button
		button_exportPrintbutton().click();
		logInfo("Clicked Print");
		
		
		if(dpBoolean("cancelPrint")){
			cancelPrint();
			return;//Exit Test
		}
			
		if(isAdmin){
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
						if(timeout == 200){
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
		}
		
		if(dpBoolean("printInBackground")){
			html_attWindowCloseButton().click();
			logInfo("Finished print window closed");
			
			//Delete background print
			button_deleteExportedFilesbutt().click();
			sleep(0.5);
			button_deleteExport_Yesbutton().click();
			sleep(3);
			button_windowExportFile_Closeb().click();
			logInfo("background print export deleted");
			
		}else{
			html_finishPrintWindowClose().click();
		}
		
		//Save file, DEFAULT DOWNLOAD LOCATION SHOULD BE DOWNLOADS
		button_ieDownload_Save().click();
		sleep(10);
		button_ieDownload_Close().click();
		sleep(1);
		logInfo("Download Saved");
		
		//Verify pdf
		String[] masks = {};
		verifyPDF(dpString("expectedFileName_PrintMethod"), dpString("pdfName_PrintMethod"), masks);
		
		
		//Test Simple New Export
		if(testExport){
			testExport();
		}
	}
	
	private void testExport(){
		String exportName = dpString("newExportFileName");
		//Make sure file does not exists
		String homeDir = System.getProperty("user.home");
		File pdf = new File(homeDir+"\\Downloads\\"+exportName+".pdf");
		if(pdf.exists()){
			pdf.delete();
		}
		
		Object[] nws = {1, 2, 1, exportName, "", "", "", "3"};
		callScript("newExport_Super", nws);
		

		logInfo("Validate the PDF using EXPORT method");
		
		//Open Export
		openExport(exportName);
		
		//Download export pdf	
		doubleClickExportfile(dpString("pdfName_PrintMethod"));
		
		//Save file
		button_ieDownload_Save().waitForExistence(60, DISABLED);
		button_ieDownload_Save().click();
		sleep(3);
		button_ieDownload_Close().click();
		sleep(1);
		html_attWindowCLose().click();
		sleep(0.5);
		logInfo("File saved");
		
		//Delete Export
		button_deleteExportedFilesbutt().click();
		sleep(0.5);
		button_deleteExport_Yesbutton().click();
		sleep(3);
		button_windowExportFile_Closeb().click();
		logInfo("Delete Export");
		
		//Verify Downloaded PDF
		String hideDateExpr = "\\d{4}\\.\\d{1,2}\\.\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}(\\sEST)*(\\sEDT)*";
		String caseName = "Case Name \\d+";
		String[] masks = {hideDateExpr, caseName};
		verifyPDF(dpString("expectedExportFileName"), exportName+".pdf", masks);
	}
	
	private void verifyPDF(String expectedName, String actualName, String[] masks){
		String expectedText ="";
		String actualText = "";
		try{	
			//String workspace = System.getProperty("user.dir");
			String workspace = dpString("workSpaceLocation");
			String homeDir = System.getProperty("user.home");
			
			logInfo("Looking for ["+expectedName+"] in "+workspace+"\\assets");
			File expectedFilename = new File(workspace+"\\assets\\"+expectedName);
			
			logInfo("Looking for ["+actualName+"] in "+homeDir+"\\Downloads");
			File actualFilename = new File(homeDir+"\\Downloads\\"+actualName);	
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
				while(!columns[3].getProperty(".text").toString().trim().toLowerCase().contentEquals("success")){
					if(columns[3].getProperty(".text").toString().trim().toLowerCase().contentEquals("error")){
						logTestResult("Print job failed", false);
						return;
					}
					exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
					columns = exports[0].find(atDescendant(".tag", "TD"), false);
					sleep(3);
				}
				logInfo("Selecting the export with name: "+exportName);
				((GuiTestObject)columns[columns.length-1]).click();
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
		vpManual("printStatusWindow_is_in_background", false, html_printStatusWindow().exists()).performTest();
		
		//Click ok for reminder
		if(button_oKbutton().exists()){
			button_oKbutton().click();
		}
		
		sleep(10);
		
		//Open export management
		button_exportCasebutton().click();
		logInfo("Clicked export case button");
		link_exportManagement().click();
		logInfo("Clicked export Management on dropdown");
		sleep(3);
		waitForloading();
		
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
			waitUntilComplete(0, exportName);//wait for success Status
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

