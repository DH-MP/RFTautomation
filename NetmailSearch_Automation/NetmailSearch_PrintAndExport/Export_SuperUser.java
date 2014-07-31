package NetmailSearch_PrintAndExport;

import java.util.ArrayList;

import resources.NetmailSearch_PrintAndExport.Export_SuperUserHelper;
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
		sleep(0.5);
		link_newExport().click();
		logInfo("Clicked new export");
		html_exportWindow().waitForExistence();
		
		//Choose Search tabs to export
		if(searchTabIndexes.length > 0){
			while(!Boolean.parseBoolean(radioButton_include_tabsselect().getProperty(".checked").toString())){
				radioButton_include_tabsselect().click();
			}
			radioButton_include_tabsselect().click();
			logInfo("Clicked include selected tab");
			TestObject[] searchTabs = html_step1_list_search_tabs().find(atDescendant(".tag", "TABLE", "class", new RegularExpression("^x-grid3-row-table$", false)));
			for(String searchTabIndex : searchTabIndexes ){
				logInfo("Checked tab at index "+ searchTabIndex);
				int index = Integer.parseInt(searchTabIndex.trim()); 
				((GuiTestObject)searchTabs[index-1].find(atDescendant(".tag", "DIV", "class", "x-grid3-row-checker"), false)[0]).click();
			}
		}else{
			while(!Boolean.parseBoolean(radioButton_include_tabsevery_().getProperty(".checked").toString())){
				radioButton_include_tabsevery_().click();
				logInfo("Clicked select all tabs");
			}
		}
		sleep(0.5);
		button_nextButton().click();
		logInfo("Clicked next button");
		sleep(1);
		
		
		//Which item to exports (selected or all)
		if(itemOptions == 1){
			logInfo("Picking All Mail");
			while(!Boolean.parseBoolean(radioButton_step2_everMail().getProperty(".checked").toString())){
				radioButton_step2_everMail().click();
			}
		}else{
			logInfo("Picking Selected Mail");
			while(!Boolean.parseBoolean(radioButton_step2_selectedMail().getProperty(".checked").toString())){
				radioButton_step2_selectedMail().click();
			}
		}
		sleep(0.5);
		button_nextButton().click();
		logInfo("Clicked next button");
		sleep(1);
		
		//Type of Export
		switch (exportTypeOption) {
			case 1:
				logInfo("Picking ONE PDF");
				while(!Boolean.parseBoolean(radioButton_step3_ONE_PDF().getProperty(".checked").toString())){
					radioButton_step3_ONE_PDF().click();
				}
				isLarge();
				break;
			case 2:
				logInfo("Picking ONE PDF PER ITEM");
				while(!Boolean.parseBoolean(radioButton_step3_PDF_PER_ITEM().getProperty(".checked").toString())){
					radioButton_step3_PDF_PER_ITEM().click();
				}
				isLarge();
				break;
			case 3:
				logInfo("Picking Portable Netmail Search");
				while(!Boolean.parseBoolean(radioButton_step3_Portable().getProperty(".checked").toString())){
					radioButton_step3_Portable().click();
				}
				break;
			case 4:
				logInfo("Picking PST");
				while(!Boolean.parseBoolean(radioButton_step3_PST().getProperty(".checked").toString())){
					radioButton_step3_PST().click();
				}
				break;
			default:
				while(!Boolean.parseBoolean(radioButton_step3_ONE_PDF().getProperty(".checked").toString())){
					radioButton_step3_ONE_PDF().click();
				}
				break;
		}
		sleep(0.5);
		button_nextButton().click();
		logInfo("Clicked next button");
		
		//Optional Information
		if(!additionalOptions.isEmpty()){
			String[] options = additionalOptions.split(",");
			TestObject[] guiOptions = html_additionalOptionContainer().find(atDescendant(".class", "Html.INPUT.checkbox"), true);
			
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
		button_finishbutton().click();
		logInfo("Clicked finish button");
		waitForloading();
		
		//Validate creation
		html_exportStatusWindow().waitForExistence(240, DISABLED);
		sleep(3);
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
	
	
	public void deleteExport(TestObject export){
		TestObject[] tds = export.find(atDescendant(".tag", "TD"), false);
		((GuiTestObject) tds[tds.length-1].find(atDescendant(".tag", "A", ".contentText", "Details"), false)[0]).click();
		sleep(1);
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

