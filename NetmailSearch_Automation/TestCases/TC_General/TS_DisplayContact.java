package TestCases.TC_General;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;

import resources.TestCases.TC_General.TS_DisplayContactHelper;
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
public class TS_DisplayContact extends TS_DisplayContactHelper
{
	/**
	 * Script Name   : <b>TS_DisplayContact</b>
	 * Generated     : <b>Aug 6, 2014 3:45:47 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/06
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		int numOfContact = 100;
		setupContact(numOfContact);
		
		HelperClass.startstopRemoteService(false, IP, "AWA_XMLV_SVC", remoteWorkSpace+"\\NetmailSearch_Automation");
		HelperClass.startstopRemoteService(true, IP, "AWA_XMLV_SVC", remoteWorkSpace+"\\NetmailSearch_Automation");
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("GVautomation");
		waitForloading();
		TestObject inboxSubFoldersUL;
		try{
			inboxSubFoldersUL = HelperClass.navigateLocation("GVautomation>rft>Contacts>Contacts");
		}catch(Exception e){
			//Archive account
			WebAdmin wa = new WebAdmin();
			wa.setIp(IP);
			wa.setUserName(adminUserName);
			wa.setPassword(adminPassword);
			wa.loadWebadminUUI();
			wa.login();
			
			wa.navigateTree("Archive>Cluster.*>Agents>Archive>ok");
			wa.archiveAccount("rft@BASE2012@First Organization@User", "GVautomation");
			wa.navigateTree("Archive");
			sleep(30);
			wa.waitForJob("ok");
			
			
			NetmailLogin.login();
			adminLogin.selectUserType("Super User");
			adminLogin.selectCase("GVautomation");
			waitForloading();
			inboxSubFoldersUL = HelperClass.navigateLocation("GVautomation>rft>Contacts>Contacts");
		}
		
		waitForloading();
		logTestResult("Correct number of contacts", Common.getNavigationPanelDisplayText().getProperty(".contentText").toString().matches("^Displaying \\d+ - \\d+ of "+(numOfContact-1)+"$"));
		
		TestObject[] results;
		TestObject visibleResultContainer;
		visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel\\s*$",false)))[0];
		results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", new RegularExpression("x-grid3-row-table", false)), true);	
		String email = results[0].find(atDescendant(".tag", "TD"), false)[2].getProperty(".contentText").toString();
		String userName = email.split("@")[0];
		((GuiTestObject)results[0]).click();
		((GuiTestObject)results[0]).doubleClick();
		checkDetailInfo(userName, email);
		html_closeContactWindow().click();

		Common.navigatePages(false, false, true, false);
		
		visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel\\s*$",false)))[0];
		results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", new RegularExpression("x-grid3-row-table", false)), true);			
		email = results[0].find(atDescendant(".tag", "TD"), false)[2].getProperty(".contentText").toString();
		userName = email.split("@")[0];
		((GuiTestObject)results[0]).click();
		((GuiTestObject)results[0]).doubleClick();
		checkDetailInfo(userName, email);
		html_closeContactWindow().click();

		
	}
	
	private void setupContact(int contactSize){
		String userFolderName = "rft@rft.BASE2012.First Organization";
		try {
				String contactFolder = "\\\\"+IP+"\\GVautomatoin\\archive\\accounts\\"+userFolderName+"\\pab\\Contacts";
				FileUtils.cleanDirectory(new File(contactFolder));
				for(int i = 1; i<contactSize; i++){
					FileWriter fstream2 = new FileWriter(contactFolder+"\\user"+i+"@fakefake.com.xml");
					BufferedWriter bw = new BufferedWriter(fstream2);
					String xml = dpString("a");
					bw.write(xml.replaceAll("mike", "user"+i));
					bw.close();
				}
	
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	
		try {
			String stubContactFolder = "\\\\"+IP+"\\GVautomatoin\\audit\\accounts\\"+userFolderName+"\\pabStub\\Contacts"; 
			FileUtils.cleanDirectory(new File(stubContactFolder));
			for(int i = 1; i<contactSize; i++){
				FileWriter fstream2 = new FileWriter(stubContactFolder+"\\user"+i+"@fakefake.com.stb");
				BufferedWriter bw = new BufferedWriter(fstream2);
				String xml = dpString("b");
				bw.write(xml.replaceAll("mikeNUMNUM", "user"+i));
				bw.close();
			}
	
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void checkDetailInfo(String userName, String email){

		TestObject[] TR = table_leftTable().getChildren()[0].getChildren();
		vpManual("LeftTable_TR1", "Name", 				TR[0].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR2", "First"+userName, 	TR[1].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR3", "Middle"+userName,	TR[2].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR4", "Last"+userName, 		TR[3].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR5", "Phone", 				TR[4].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR6", "Email"+email, 		TR[5].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR7", "Office Info", 		TR[6].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR8", "Title", 				TR[7].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR9", "Organization", 		TR[8].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR10","Website", 			TR[9].getProperty(".contentText")).performTest();
		vpManual("LeftTable_TR11", "Department", 		TR[10].getProperty(".contentText")).performTest();
		
		
		TR = table_rightTable().getChildren()[0].getChildren();
		vpManual("RightTable_TR1",  "Address", 			TR[0].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR2",  "Office", 			TR[1].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR3",  "Street",			TR[2].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR4",  "City", 			TR[3].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR5",  "State", 			TR[4].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR6",  "Postal Code", 		TR[5].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR7",  "Home", 			TR[6].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR8",  "Street", 			TR[7].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR9",  "City", 			TR[8].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR10", "State", 			TR[9].getProperty(".contentText")).performTest();
		vpManual("RightTable_TR11", "Postal Code", 		TR[10].getProperty(".contentText")).performTest();
	}
}

