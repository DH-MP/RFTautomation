package TestCases.TC_General;
import resources.TestCases.TC_General.TS_FolderSortingHelper;
import utilities.HelperClass;

import NetmailAdminUUI.WebAdmin;
import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;

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
public class TS_FolderSorting extends TS_FolderSortingHelper
{
	/**
	 * Script Name   : <b>TS_FolderSorting</b>
	 * Generated     : <b>Aug 6, 2014 10:42:17 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/06
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		String expectedOrderOfFolders = "Ab, ac, be, bee, bez, bz, z, za, zaa, Zv";
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("GVautomation");
		waitForloading();
	
		TestObject inboxSubFoldersUL;
		try{
			inboxSubFoldersUL = HelperClass.navigateLocation("GVautomation>rft>Inbox>__caseSensitive");
		}catch(Exception e){
			//Archive account
			WebAdmin wa = new WebAdmin();
			wa.setIp(IP);
			wa.setUserName(adminUserName);
			wa.setPassword(adminPassword);
			
			wa.navigateTree("Archive>Cluster.*>Agents>Archive>ok");
			wa.archiveAccount("rft@BASE2012@First Organization@User", "GVautomation");
			wa.navigateTree("Archive");
			sleep(30);
			wa.waitForJob("ok");
			
			
			NetmailLogin.login();
			adminLogin.selectUserType("Super User");
			adminLogin.selectCase("GVautomation");
			waitForloading();
			inboxSubFoldersUL = HelperClass.navigateLocation("GVautomation>rft>Inbox>__caseSensitive");
		}
		
		TestObject[] subFolders = inboxSubFoldersUL.find(atDescendant(".tag", "LI"), false);
		String[] expectedSubFoldersOrder = expectedOrderOfFolders.split(",");
		for(int i = 0; i<subFolders.length; i++){
			TestObject link = subFolders[i].getChildren()[0].find(atDescendant(".tag", "A"))[0];
			vpManual("SubFolderName"+i, expectedSubFoldersOrder[i].trim(), link.getProperty(".contentText")).performTest();
		}
	}
	 
}

