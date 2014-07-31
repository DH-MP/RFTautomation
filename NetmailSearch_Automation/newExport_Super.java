
import resources.newExport_SuperHelper;
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
public class newExport_Super extends newExport_SuperHelper
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
	private int		
					itemOptions = 2,
					exportTypeOption = 1,
					packagingOption = 1;
	
	private String 	exportName = "actual",
					email = null,
					password= null,
					customGB = "",
					additionalOptions;
	
	private String[] searchTabIndexes = null;
	
	private Boolean isLargeExport = false;
	public void testMain(Object[] args) 
	{
		if(args.length>0){
			searchTabIndexes = args[0].getClass().getCanonicalName() == "java.lang.Integer" ? new String[]{String.valueOf(args[0])}: ((String) args[0]).split(",");
			itemOptions = (int) args[1];
			exportTypeOption = (int) args[2];
			exportName = (String) args[3];
			email = (String) args[4];
			password = (String) args[5];
			
			//optional
			customGB = args.length > 6 ? args[6].toString() : "";
			additionalOptions = args.length > 7 ? args[7].toString() : "";
			isLargeExport = args.length > 8 ? Boolean.parseBoolean(args[8].toString()) : false;
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
		
		if(Integer.parseInt(searchTabIndexes[0]) != 0){
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
		sleep(1);
		button_nextButton().click();
		logInfo("Clicked next button");
		sleep(2);
		
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
		sleep(1);
		button_nextButton().click();
		logInfo("Clicked next button");
		sleep(2);
		
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
		sleep(1);
		button_nextButton().click();
		logInfo("Clicked next button");
		
		if(!additionalOptions.isEmpty()){
			String[] options = additionalOptions.split(",");
			TestObject[] guiOptions = html_additionalOptionContainer().find(atDescendant(".class", "Html.INPUT.checkbox"), true);
			
			for(String option : options){
				GuiTestObject GuiObject = (GuiTestObject)guiOptions[Integer.parseInt(option.trim()) - 1];
				GuiObject.click();
			}
		}
		
		
		sleep(1);
		button_nextButton().click();
		logInfo("Clicked next button");
		try{
			if(button_hideThisReminderNextTim().exists() && button_hideThisReminderNextTim().ensureObjectIsVisible()){
				button_hideThisReminderNextTim().click();
			}
			sleep(2);
		}catch(AmbiguousRecognitionException e){
			//do nothing
		}

//Feature not in 5.3
//		switch (packagingOption) {
//			case 1:
//				logInfo("Picking Single package");
//				while(!Boolean.parseBoolean(radioButton_step5_package_Sing().getProperty(".checked").toString())){
//						radioButton_step5_package_Sing().click();
//				}
//				break;
//			case 2:
//				logInfo("Picking CD Package");
//				while(!Boolean.parseBoolean(radioButton_step5_package_CD().getProperty(".checked").toString())){
//					radioButton_step5_package_CD().click();
//				}
//				break;
//			case 3:
//				logInfo("Picking DVD package");
//				while(!Boolean.parseBoolean(radioButton_step5_package_DVD().getProperty(".checked").toString())){
//					radioButton_step5_package_DVD().click();
//				}
//				break;
//			case 4:
//				logInfo("Picking Custom package");
//				while(!Boolean.parseBoolean(radioButton_step5_package_Cust().getProperty(".checked").toString())){
//					radioButton_step5_package_Cust().click();
//
//				}
//				sleep(1);
//				text_step5_custom_size().click();
//				browser_htmlBrowser().inputChars(customGB);
//				break;
//			case 5:
//				logInfo("Picking CD ISO");
//				while(!Boolean.parseBoolean(radioButton_step5_cd_iso().getProperty(".checked").toString())){
//					radioButton_step5_cd_iso().click();
//				}
//				break;
//			default:	
//				logInfo("Defaulted to Single package");
//				while(!Boolean.parseBoolean(radioButton_step5_package_Sing().getProperty(".checked").toString())){
//					radioButton_step5_package_Sing().click();
//				}
//				break;
//		}
		
		sleep(1);
		button_nextButton().click();
		logInfo("Clicked next button");
		sleep(2);
		
		logInfo("Entering export name:"+exportName);
		text_step5_export_name().click();
		browser_htmlBrowser().inputChars(exportName);
		
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
	
	
	
}

