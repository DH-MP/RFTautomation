package NetmailSearch_PrintAndExport;

import java.io.IOException;
import java.util.ArrayList;

import resources.NetmailSearch_PrintAndExport.Export_SuperUserHelper;
import utilities.HelperClass;
import utilities.HelperScript;

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
public class Export_SuperUser extends Export_SuperUserHelper
{
	/**
	 * Script Name   : <b>newExport_Super</b>
	 * Generated     : <b>Jun 19, 2013 1:25:20 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/19
	 * @author Administrator
	 */
	private int		itemOptions,
					exportTypeOption,
					packagingOption;
	
	private String 	searchTabs = "",
					exportName = "",
					email = "",
					password= "",
					customGB = "",
					additionalOptions ="";
	
	private String[] searchTabIndexes = {};
	private Boolean isLargeExport = false;
	
	public void testMain(Object[] args) 
	{
	}
	
	public void create(){
		if(!searchTabs.isEmpty()){
			searchTabIndexes = searchTabs.split(",");
		}
		try{
			//Super user export case button
			button_exportCasebutton().click();
		}catch(ObjectNotFoundException e){
			java.awt.Rectangle bounds = (java.awt.Rectangle) table_netmailLite_exportButton().getProperty(".bounds");
			table_netmailLite_exportButton().click(atPoint(bounds.width-3,bounds.height/2));
		}
		logInfo("Clicked export case dropdown");
		sleep(1);
		link_newExport().click();
		waitForloading();
		waitForloading();
		logInfo("Clicked new export");
		html_exportWindow().waitForExistence();
		
		//Choose Search tabs to export
		if(searchTabIndexes.length > 0){
			clickRadio(radioButton_include_tabsselect());
			logInfo("Clicked include selected tab");
			TestObject[] searchTabs = html_step1_list_search_tabs().find(atDescendant(".tag", "TABLE", "class", new RegularExpression("^x-grid3-row-table$", false)));
			for(String searchTabIndex : searchTabIndexes ){
				logInfo("Checked tab at index "+ searchTabIndex);
				int index = Integer.parseInt(searchTabIndex.trim()); 

				TestObject tableParent = ((GuiTestObject)searchTabs[index-1]).getParent();
				
				//x-grid3-row-selected (DIV ACTS AS CHECKBOX)
				if(!tableParent.getProperty("class").toString().contains("selected"))
					((GuiTestObject)searchTabs[index-1].find(atDescendant(".tag", "DIV", "class", "x-grid3-row-checker"), false)[0]).click();
			}
		}else{
			clickRadio(radioButton_include_tabsevery_());
			logInfo("Clicked select all tabs");
		}
		sleep(0.5);
		button_nextButton().click();
		logInfo("Clicked next button");
		sleep(1);
		
		
		//Which item to exports (selected or all)
		if(itemOptions == 1){
			logInfo("Picking All Mail");
			clickRadio(radioButton_step2_everMail());
		}else{
			logInfo("Picking Selected Mail");
			clickRadio(radioButton_step2_selectedMail());
		}
		sleep(0.5);
		button_nextButton().click();
		logInfo("Clicked next button");
		sleep(1);
		
		//Type of Export
		switch (exportTypeOption) {
			case 1:
				logInfo("Picking ONE PDF");
				clickRadio(radioButton_step3_ONE_PDF());
				isLarge();
				break;
			case 2:
				logInfo("Picking ONE PDF PER ITEM");
				clickRadio(radioButton_step3_PDF_PER_ITEM());
				isLarge();
				break;
			case 3:
				logInfo("Picking Portable Netmail Search");
				clickRadio(radioButton_step3_Portable());
				break;
			case 4:
					logInfo("Picking PST");
					clickRadio(radioButton_step3_PST());
				break;
			default:
					clickRadio(radioButton_step3_ONE_PDF());
				break;
		}
		sleep(0.5);
		button_nextButton().click();
		sleep(2);
		logInfo("Clicked next button");
		
		//Optional Information by default it's checked if specified will be unchecked
		if(!additionalOptions.isEmpty()){
			String[] options = additionalOptions.split(",");
			TestObject[] guiOptions = html_additionalOptionContainer().find(atDescendant(".class", "Html.INPUT.checkbox"), true);
			
			//HACK: 5.3.1 changed the default option
			for(TestObject option : guiOptions){
				if(!Boolean.parseBoolean(option.getProperty("CHECKED").toString())){
					((GuiTestObject)option).click();
				}
			}
			
			for(String option : options){
				GuiTestObject GuiObject = (GuiTestObject)guiOptions[Integer.parseInt(option.trim()) - 1];
				GuiObject.click();
			}
		}	
		sleep(0.5);
		button_nextButton().click();
		logInfo("Clicked next button");
		
		try{		
			//Export Name and etc
			logInfo("Entering export name:"+exportName);
			text_step5_export_name().click();	
			browser_htmlBrowser().inputKeys("^a{BKSP}");
			browser_htmlBrowser().inputChars(exportName);
			
		}catch(Exception e){
			if(button_hideThisReminderNextTim().exists()){
				button_hideThisReminderNextTim().click();
			}
			sleep(1);
			
			sleep(0.5);
			button_nextButton().click();
			logInfo("Clicked next button");
			sleep(1);
		}

		//HACK put this to default pre-5.3.1
		if(Boolean.parseBoolean(checkBox_step5_send_mailstep5_().getProperty(".checked").toString())){
			checkBox_step5_send_mailstep5_().click();
		}
		if(Boolean.parseBoolean(checkBox_step5_with_passwordst().getProperty(".checked").toString())){
			checkBox_step5_with_passwordst().click();
		}
		

		if(email !=null && !email.isEmpty()){
			logInfo("entering email:"+email);
			checkBox_step5_send_mailstep5_().click();
			text_step5_export_email().click();
			browser_htmlBrowser().inputKeys("ioajdioajdioajoda");
			vpManual("finishButton_isDisabledOnIncorrectEmail", true, table_cardFinish().getProperty("class").toString().matches(".*x-item-disabled$")).performTest();
			browser_htmlBrowser().inputKeys("^a{DELETE}"+email);
			vpManual("finishButton_isEnabledOnCorrectEmail", false, table_cardFinish().getProperty("class").toString().matches(".*x-item-disabled$")).performTest();
		}
		
		if(password !=null &&!password.isEmpty()){
			logInfo("entering password:"+password);
			checkBox_step5_with_passwordst().click();
			vpManual("finishButton_isDisabledEmptyPasswords", true, table_cardFinish().getProperty("class").toString().matches(".*x-item-disabled$")).performTest();
			text_step5_password1().click();
			browser_htmlBrowser().inputChars(password);
			text_step5_password2().click();
			browser_htmlBrowser().inputChars(password+"10101010");
			vpManual("finishButton_isDisabledOnNoMatchPassword", true, table_cardFinish().getProperty("class").toString().matches(".*x-item-disabled$")).performTest();
			browser_htmlBrowser().inputKeys("^a{DELETE}"+password);
			sleep(2.5);
			vpManual("finishButton_isEnabledOnCorrectPassword", false, table_cardFinish().getProperty("class").toString().matches(".*x-item-disabled$")).performTest();
		}
		sleep(1);
		button_finishbutton().hover();
		button_finishbutton().click();
		logInfo("Clicked finish button");
		waitForloading();
		
		//Validate creation
		html_exportStatusWindow().waitForExistence(240, DISABLED);
		sleep(6);
		waitForloading();
		vpManual("ExportStatusWindow_Visible", true, html_exportStatusWindow().ensureObjectIsVisible()).performTest();
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		vpManual("Export_Created", true, columns[0].getProperty(".text").toString().matches(exportName)).performTest();
	}
	
	private void isLarge(){
		if(isLargeExport){
			html_typeExportContainer().click(atPoint(1,1));
			html_typeExportContainer().performTest( onePDFWarningVP() );
		}
	}
	
	public void openExportManagementWindow(){
		try{
			//Super user export case button
			button_exportCasebutton().click();
		}catch(ObjectNotFoundException e){
			java.awt.Rectangle bounds = (java.awt.Rectangle) table_netmailLite_exportButton().getProperty(".bounds");
			table_netmailLite_exportButton().click(atPoint(bounds.width-3,bounds.height/2));
		}
		logInfo("Clicked export case dropdown");
		
		link_exportManage().click();
		waitForloading();
	}
	
	public void closeExportManagementWindow(){
		if(html_exportStatusWindow().exists()){
			html_closeExportWindow().click();
		}else{
			logWarning("closeExport window called when export status window NOT visible");
		}
	}
	
	
	public TestObject getExportObject(String exportName){
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		for(TestObject export : exports){
			TestObject[] columns = export.find(atDescendant(".tag", "TD"), false);
			String name = columns[0].getProperty(".text").toString();
			if(exportName.contentEquals(name)){
				return export;
			}
		}
		return null;
	}
	
	public TestObject[] getExportObjects(String exportName){
		ArrayList<TestObject> matchedExports = new ArrayList<>();
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		for(TestObject export : exports){
			TestObject[] columns = export.find(atDescendant(".tag", "TD"), false);
			String name = columns[0].getProperty(".text").toString();
			if(exportName.contentEquals(name)){
				matchedExports.add(export);
			}
		}
		TestObject[] a = new TestObject[matchedExports.size()];
		return matchedExports.toArray(a);
	}
	
	
	public void cancelExport(String exportName){
		TestObject export = this.getExportObject(exportName);
		TestObject[] tds = export.find(atDescendant(".tag", "TD"), false);
		((GuiTestObject) tds[tds.length-1].find(atDescendant(".tag", "A", ".contentText", "Cancel"), false)[0]).click();
		sleep(1);
		button_yesbutton().click();	
	}
	
	
	
	//parameter is the container of the export
	public void deleteExport(TestObject export){
		TestObject[] tds = export.find(atDescendant(".tag", "TD"), false);
		((GuiTestObject) tds[tds.length/2]).click();
		((GuiTestObject) tds[tds.length-1].find(atDescendant(".tag", "A", ".contentText", "Details"), false)[0]).click();
		sleep(2);
		waitForloading();
		button_deleteExportedFilesbutt().click();
		button_yesbutton().click();
		
	}
	
	

	
	
	public void waitForExport(String exportName){
		waitForExport(exportName, "success");
	}
	
	public void waitForExport(String exportName, String expectedStatus){
		TestObject export = getExportObject(exportName);
		TestObject[] columns = export.find(atDescendant(".tag", "TD"), false);
		String status = columns[3].getProperty(".text").toString().trim().toLowerCase();
		while(!(status.contentEquals(expectedStatus)|status.contentEquals("warning"))){
			sleep(3);
			export = getExportObject(exportName);
			columns = export.find(atDescendant(".tag", "TD"), false);
			status = columns[3].getProperty(".text").toString().trim().toLowerCase();
			if(status.contentEquals("error")){
				logError("exportFailed");
				stop();
			}
		}
	} 
	
	public void waitForExport(String exportName, String expectedStatus, int recheckInterval){
		TestObject export = getExportObject(exportName);
		TestObject[] columns = export.find(atDescendant(".tag", "TD"), false);
		String status = columns[3].getProperty(".text").toString().trim().toLowerCase();
		while(!(status.contentEquals(expectedStatus)|status.contentEquals("warning"))){
			sleep(recheckInterval);
			export = getExportObject(exportName);
			columns = export.find(atDescendant(".tag", "TD"), false);
			status = columns[3].getProperty(".text").toString().trim().toLowerCase();
			if(status.contentEquals("error")){
				logError("exportFailed");
				stop();
			}
		}
	} 
	
	public void waitForTopExport(){
		waitForTopExport("success");
	}
	
	public void waitForTopExport(String expectedStatus){
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		String status = columns[3].getProperty(".text").toString().trim().toLowerCase();
		while(!(status.contentEquals(expectedStatus)|status.contentEquals("warning"))){
			sleep(3);
			exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
			columns = exports[0].find(atDescendant(".tag", "TD"), false);
			status = columns[3].getProperty(".text").toString().trim().toLowerCase();
			if(status.contentEquals("error")){
				logError("exportFailed");
				stop();
			}
		}
	}
	
	public void openExport(TestObject export){
		TestObject[] columns = export.find(atDescendant(".tag", "TD"), false);
		((GuiTestObject)columns[columns.length-1]).click();
		logInfo("open export: "+columns[0].getProperty(".contentText"));
	}
	
	public void openWhenTopExportComplete(){
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		try{
			waitForTopExport();
			logInfo("Selecting the export with name: "+exportName);
			((GuiTestObject)columns[columns.length-1]).click();
			return;
		}catch(ArrayIndexOutOfBoundsException | ObjectNotFoundException e){
			openWhenTopExportComplete();
			return;
		}
	}
	
	public void downloadExportFile(String fileName){
		//Download Export
		Property[] rowProperty = {	new Property(".tag", "TABLE"),
							new Property(".text", new RegularExpression("(?i).*"+fileName+".*", false)),
							new Property("class", "x-grid3-row-table"),
		};
		TestObject[] exportFiles = html_exportFilesList().find(atDescendant(rowProperty), true);
		if(exportFiles.length == 1){
			((GuiTestObject)exportFiles[0]).doubleClick();
			logInfo("Selected < "+ fileName +" > file to download");
			sleep(10);
		}else{
			logError("Could not find export file by the name < "+ fileName +">");
		}
		
		//IE Notification Control
		TestObject downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
		int counter = 0;
		while(downloadObject==null){
			sleep(2);//wait a bit
			downloadObject = HelperClass.ieNotificationElement("Notification bar Text");
			if(counter == 5){
				((GuiTestObject)exportFiles[0]).click();
				((GuiTestObject)exportFiles[0]).doubleClick();
			}
			if(counter++>10){
				logError("Could not find notification bar");
				break;
			}
		}
		
		//Click Save button
		HelperClass.ieNotificationElement("Save").click();
		logInfo("Clicked Save file on browser");
		
		
		//Wait for download to finish
		try{
			String downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
			while(!downloadFileText.matches(".*download has completed\\..*")){
				downloadFileText = HelperClass.ieNotificationElement("Notification bar Text").getProperty(".text").toString();
				sleep(10);
			}
		}catch(NullPointerException e){
			//due to rare chances of not finding object, just wait very long for download to finish
			sleep(400);
		}
		//Close ieNotifcation
		HelperClass.ieNotificationElement("Close").click();
		logInfo("Clicked close button for finished download notification");	
	}
	
	public void openWhenTopExportComplete(String expectedStatus){
		TestObject[] exports = html_exportList().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
		TestObject[] columns = exports[0].find(atDescendant(".tag", "TD"), false);
		try{
			waitForTopExport(expectedStatus);
			logInfo("Selecting the export with name: "+exportName);
			((GuiTestObject)columns[columns.length-1]).click();
			return;
		}catch(ArrayIndexOutOfBoundsException | ObjectNotFoundException e){
			openWhenTopExportComplete(expectedStatus);
			return;
		}
	}
	
	public void clickRadio(GuiTestObject radio){
		int posX = 6;
		while(!Boolean.parseBoolean(radio.getProperty(".checked").toString())){
			radio.click(atPoint(6,posX));
			sleep(0.5);
			posX++;
		}
	}
	
	/***********************SETTERS******************************/
	public void setItemOptions(int itemOptions) {
		this.itemOptions = itemOptions;
	}

	public void setExportTypeOption(int exportTypeOption) {
		this.exportTypeOption = exportTypeOption;
	}

	public void setPackagingOption(int packagingOption) {
		this.packagingOption = packagingOption;
	}

	public void setSearchTabs(String searchTabs) {
		this.searchTabs = searchTabs;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCustomGB(String customGB) {
		this.customGB = customGB;
	}

	public void setAdditionalOptions(String additionalOptions) {
		this.additionalOptions = additionalOptions;
	}

	public void setIsLargeExport(Boolean isLargeExport) {
		this.isLargeExport = isLargeExport;
	}
	
}

