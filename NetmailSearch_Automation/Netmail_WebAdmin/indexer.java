package Netmail_WebAdmin;
import java.util.HashMap;
import java.util.Map;

import resources.Netmail_WebAdmin.indexerHelper;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.object.map.MappedTestObject;
import com.rational.test.ft.object.map.SpyMappedTestObject;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class indexer extends indexerHelper
{
	/**
	 * Script Name   : <b>indexer</b>
	 * Generated     : <b>May 20, 2014 3:39:39 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/05/20
	 * @author Administrator
	 */
	private String indexLocation = "TS1680";
	private String user = "ati02@ex2010-03.com";
	private String indexName = "indexinator";
	public void testMain(Object[] args) 
	{		
		if(args != null && args.length > 0){
			Map<String, Object> data = (Map<String, Object>) args[0];
			indexLocation 	= data.get("indexLocation") == null 	? indexLocation : (String)data.get("indexLocation") ;
			user 			= data.get("user") 			== null 	? user 			: (String)data.get("user");
			indexName 		= data.get("indexName") 	== null 	? indexName 	: (String) data.get("indexName");
		}
		image_expandIndexAgent().click();
		logInfo("expand index agent tree");
		
		SpyMappedTestObject indexInstance = this.getMappedTestObject("html_indexInstance");
		indexInstance.setProperty(".text", "indexinator", 100);
		html_indexInstance().click();
		logInfo("click index");

		//Criteria
		link_criteria().click();
		logInfo("click critera tab");
		radioButton_reCreateIndex().click();
		logInfo("click recreate index radio");
		list_archiveLocationList().click();
		logInfo("click archive location list");
		list_archiveLocationList().click(atText(indexLocation));
		logInfo("selected < "+indexLocation+" >");
		button_savebutton().click();
		logInfo("click save button");
		
		
		//JobSetting
		link_jobSettings().click();
		logInfo("click job setting tab");
		button_selectUsers().click();
		logInfo("click select user");
		button_loadUsers().click();
		logInfo("click load user on pop up window");
		list_userListLeftBox().click(atText(user));
		logInfo("select < "+user+" >");
		button_addUser().click();
		logInfo("click add user");
		button_oKbutton().click();
		logInfo("click ok");
		button_savebutton().click();
		logInfo("click save index");
		
		button_runNowbutton().click();
		logInfo("click run now");
		
		button_htmlDialogButtonOK().click();
		logInfo("click ok on prompt run");
		
	}
	

}

