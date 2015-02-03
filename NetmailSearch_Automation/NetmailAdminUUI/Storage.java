package NetmailAdminUUI;
import java.util.Map;

import resources.NetmailAdminUUI.StorageHelper;
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
public class Storage extends StorageHelper
{
	/**
	 * Script Name   : <b>newStorage</b>
	 * Generated     : <b>May 15, 2014 12:25:36 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/05/15
	 * @author Administrator
	 * 
	 * PRECONDITION: User is logged into system
	 */
	
	
	/* 
	 * PRECONDITION: User is logged into system
	 * POSTCONDITION: Storage is created.
	 */
	
	public void testMain(Object[] args) 
	{	

	}
	
	public void create(String storageType, String name, String storagePath){
		WebAdmin webadmin = new WebAdmin();
		webadmin.navigateTree("Archive>Cluster.*");
		webadmin.selectPageTab("Storage");
		
		button_addButton().click();
		logInfo("click addButon");
		
		if(storageType.contentEquals("File System")){
			TestObject[] radios = html_divDeviceTypes().find(atDescendant(".type", "radio", ".classIndex", "3"));
			((GuiTestObject) radios[0]).click();
		}		
		button_nextbutton().click();

		text_name().click();
		browser_htmlBrowser(document_storageDeviceFileSyst(), DEFAULT).inputChars(name);
		
		text_path().click();
		browser_htmlBrowser(document_storageDeviceFileSyst(), DEFAULT).inputChars(storagePath);
		
		button_savebutton().click();
		
		webadmin.clickActionBarButton("Save");
	}
	
	public void create(String storageType, String name, String storagePath, String login, String password){
		WebAdmin webadmin = new WebAdmin();
		webadmin.navigateTree("Archive>Cluster.*");
		webadmin.selectPageTab("Storage");
		
		button_addButton().click();
		logInfo("click addButon");
		
		if(storageType.contentEquals("File System")){
			TestObject[] radios = html_divDeviceTypes().find(atDescendant(".type", "radio", ".classIndex", "3"));
			((GuiTestObject) radios[0]).click();
		}		
		button_nextbutton().click();

		text_name().click();
		browser_htmlBrowser(document_storageDeviceFileSyst(), DEFAULT).inputChars(name);
		
		text_path().click();
		browser_htmlBrowser(document_storageDeviceFileSyst(), DEFAULT).inputChars(storagePath);
		
		
		text_login().click();
		browser_htmlBrowser(document_storageDeviceFileSyst(), DEFAULT).inputChars(login);
		text_password().click();
		browser_htmlBrowser(document_storageDeviceFileSyst(), DEFAULT).inputChars(password);
		
		button_savebutton().click();
		webadmin.clickActionBarButton("Save");
	}
	
	public void delete(String storageName){
		WebAdmin webadmin = new WebAdmin();
		webadmin.navigateTree("Archive>Cluster.*");
		webadmin.selectPageTab("Storage");
		
		list_retentionDevicesList().click();
		browser_htmlBrowser().inputKeys(storageName);
		button_removebutton().click();
		button_htmlDialogButtonOK().click();
		System.out.println("Removed Storage: "+storageName);
		webadmin.clickActionBarButton("Save");
	}
}

