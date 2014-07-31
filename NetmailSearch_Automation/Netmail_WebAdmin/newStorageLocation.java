package Netmail_WebAdmin;
import java.util.Map;

import resources.Netmail_WebAdmin.newStorageLocationHelper;
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
public class newStorageLocation extends newStorageLocationHelper
{
	/**
	 * Script Name   : <b>newStorageLocation</b>
	 * Generated     : <b>May 16, 2014 9:35:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/05/16
	 * @author Administrator
	 */
	
	/* 
	 * PRECONDITION: User is logged into system
	 * POSTCONDITION: Storage Location is created.
	 */
	
	private String locationType = "Standard";
	private String name = "TS1680";
	private String description = "automation";
	private String storageDevice = "TS1680";
	private String locationPath = "\\\\10.1.30.64\\TransendData\\TS1690";
	private String superUser = "Administrator@DC@First Organization@User";
	public void testMain(Object[] args) 
	{
		if(args != null && args.length > 0){
			Map<String, Object> data = (Map<String, Object>) args[0];
			locationType = (String) data.get("locationType");
			name = (String) data.get("name");
			description = (String) data.get("description");
			storageDevice = (String) data.get("storageDevice");
			locationPath = (String) data.get("locationPath");
			superUser = (String) data.get("superUser");
		}
		
		link_system().click();
		logInfo("Click System link");
		link_storage().click();
		logInfo("Click Storage Tab");
		button_addButton().click();
		logInfo("Click add button in storage tab");
		
		if(locationType.contentEquals("Standard")){
			TestObject[] radios = html_divLocationTypes().find(atDescendant(".type", "radio", ".classIndex", "0"));
			((GuiTestObject) radios[0]).click();
		}	
		
		button_nextbutton().click();
		logInfo("Click Next button on new storage window");
		
		text_name().click();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(name);
		logInfo("Input name on new storage window");
		
		text_description().doubleClick();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(description);
		logInfo("Input <"+description+">on new storage window");
		
		list_select_RetentionDevices_A2().click();
		list_select_RetentionDevices_A2().click(atText(storageDevice));
		logInfo("Selected < "+storageDevice+" > in archive secltion on new storage window");
		
		list_select_RetentionDevices_A().click();
		list_select_RetentionDevices_A().click(atText(storageDevice));
		logInfo("Selected < "+storageDevice+" > in attachement audit selection on new storage window");
		
		text_auditPath().click();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(locationPath);
		logInfo("Input <"+locationPath+">on new storage window");
		
		
		//Adding Super User
		button_addSuperUser().click();
		logInfo("Click Storage Tab in new storage window");
		button_listUsersbutton().click();
		logInfo("Click list user in users window");
		sleep(6);
		
		list_userListA().click(atText(superUser));
		logInfo("Selected < "+superUser+" > in user window");
		
		button_listUserAdd().click();
		logInfo("Click add in users window");
		
		button_oKbutton().click();
		logInfo("Click ok in users window");
		
		button_savebutton().click();
		logInfo("Click save user in new storage window");
		
		button_systemSave().click();
		logInfo("Click save in storage tab");
	}
}

