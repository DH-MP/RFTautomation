package Netmail_WebAdmin;
import java.util.Map;

import resources.Netmail_WebAdmin.newStorageHelper;
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
public class newStorage extends newStorageHelper
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
	
	private String storageType = "";
	private String name = "";
	private String storagePath = "";
	public void testMain(Object[] args) 
	{	
		
		if(args != null && args.length > 0){
			Map<String, Object> data = (Map<String, Object>) args[0];
			storageType = (String) data.get("storageType");
			name = (String) data.get("name");
			storagePath = (String) data.get("storagePath");
		}
		
		link_system().click();
		logInfo("Click System");
		
		link_storage().click();
		logInfo("Click Storage Tab");
		
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
		
		button_systemSave().click();
	}
}

