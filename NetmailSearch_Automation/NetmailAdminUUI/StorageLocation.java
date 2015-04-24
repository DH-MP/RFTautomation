package NetmailAdminUUI;
import java.util.Map;

import resources.NetmailAdminUUI.StorageLocationHelper;
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
public class StorageLocation extends StorageLocationHelper
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
	
//	private String locationType = "Standard";
//	private String name = "TS1680";
//	private String description = "automation";
//	private String storageDevice = "TS1680";
//	private String locationPath = "\\\\10.1.30.64\\TransendData\\TS1690";
//	private String superUser = "Administrator@DC@First Organization@User";
	public void testMain(Object[] args) 
	{
		
		
	}
	
	public void create(String locationType, String name, String description, String storageDevice, String locationPath, String superUser){
		WebAdmin webadmin = new WebAdmin();
		webadmin.navigateTree("Archive>Cluster.*");
		webadmin.selectPageTab("Storage");
		
		
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
		
		list_select_RetentionArchive().click();
		list_select_RetentionArchive().click(atText(storageDevice));
		logInfo("Selected < "+storageDevice+" > in archive secltion on new storage window");
		
		list_select_RetentionAttachmen().click();
		list_select_RetentionAttachmen().click(atText(storageDevice));
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
	
	
	
	
	public void create(String locationType, String name, String description, String archiveDevice, String archivePath, String attachmentDevice, String attachmentPath, String auditPath, String userID, String password, String superUser){
		WebAdmin webadmin = new WebAdmin();
		webadmin.navigateTree("Archive>Cluster.*");
		webadmin.selectPageTab("Storage");
		
		
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
		logInfo("Input < "+description+" >on new storage window");
		
		list_select_RetentionArchive().click();
		list_select_RetentionArchive().click(atText(archiveDevice));
		logInfo("Selected < "+archiveDevice+" > in archive secltion on new storage window");
		
		text_archivePath().click();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(archivePath);
		logInfo("Input < "+archivePath+" > archive Path");
		
		list_select_RetentionAttachmen().click();
		list_select_RetentionAttachmen().click(atText(attachmentDevice));
		logInfo("Selected < "+attachmentDevice+" > in attachement audit selection on new storage window");
		
		text_attachmentPath().click();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(attachmentPath);
		logInfo("Input < "+attachmentPath+" > attachment Path");
		
		text_auditPath().click();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(auditPath);
		logInfo("Input < "+auditPath+" >on audit path");
		
		text_userID().click();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(userID);
		text_password().click();
		browser_htmlBrowser(document_objectDetails(),DEFAULT_FLAGS).inputChars(password);
		
		//5.4.0 No More Super User
		//Adding Super User
//		button_addSuperUser().click();
//		logInfo("Click Storage Tab in new storage window");
//		button_listUsersbutton().click();
//		logInfo("Click list user in users window");
//		sleep(6);
//		
//		list_userListA().click(atText(superUser));
//		logInfo("Selected < "+superUser+" > in user window");
//		
//		button_listUserAdd().click();
//		logInfo("Click add in users window");
//		
//		button_oKbutton().click();
//		logInfo("Click ok in users window");
		
		button_savebutton().click();
		logInfo("Click save user in new storage window");
		
		button_systemSave().click();
		logInfo("Click save in storage tab");
	}
	
	
	public void delete(String storageLocationName){
		storageLocationName = storageLocationName.replace(" ", "-");
		WebAdmin webadmin = new WebAdmin();
		webadmin.navigateTree("Archive>Cluster.*");
		webadmin.selectPageTab("Storage");
		
		list_oArchiveLocations().click();
		browser_htmlBrowser().inputKeys(storageLocationName);
		button_removebutton().click();
		sleep(1);
		while(button_htmlDialogButtonOK().exists()){
			button_htmlDialogButtonOK().click();
			sleep(0.5);
		}
		System.out.println("Removed Storage: "+storageLocationName);
		webadmin.clickActionBarButton("Save");
	}
}

