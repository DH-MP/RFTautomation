package TestCases.TC_General;
import resources.TestCases.TC_General.TS_ItemCountsHelper;
import utilities.HelperClass;
import NetmailAdminUUI.WebAdmin;
import NetmailSearch_General.Common;
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
public class TS_ItemCounts extends TS_ItemCountsHelper
{
	/**
	 * Script Name   : <b>TS_ItemCounts</b>
	 * Generated     : <b>Aug 7, 2014 1:20:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/07
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		test();
	}
	
	public void test(){
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("GVautomation");
		waitForloading();
		Common.preferencePageSize(200);
		waitForloading();
		
		TestObject[] folders = HelperClass.navigateLocation("GVautomation>rft").getChildren();
		try{
			folders = HelperClass.navigateLocation("GVautomation>rft").getChildren();
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
			Common.preferencePageSize(200);
			waitForloading();
			folders = HelperClass.navigateLocation("GVautomation>rft").getChildren();
		}

		waitForloading();
		for(TestObject folder : folders){
			TestObject[] folderCount = folder.find(atDescendant(".tag", "SPAN", "class", "folderCounts"), false);
			if(folderCount.length>0){
				String countString = folderCount[0].getProperty(".contentText").toString();
				try{
					Integer countInteger =  Integer.parseInt(countString);
					((GuiTestObject)folder).click();
					waitForloading();
					waitForloading();
					
					TestObject[] results;
					TestObject visibleResultContainer;
					visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel\\s*$",false)))[0];
					results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", new RegularExpression("x-grid3-row-table", false)), true);	
						
					vpManual("ItemCount_Matches_Results"+results.length, results.length, countInteger).performTest();
					
					
					((GuiTestObject)folderCount[0]).click();
					waitForloading();
					html_helpInfo().performTest( itemCountHelpInfoVP() );
					button_oKbutton().click();

				}catch (NumberFormatException nfe){
					logError("Item count is only available after archiving");
				}
			}else{
				continue;
			}

		}
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("GVautomation");
		Common.preferencePageSize(20);
	}
}

