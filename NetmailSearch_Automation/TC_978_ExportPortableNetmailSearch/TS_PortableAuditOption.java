package TC_978_ExportPortableNetmailSearch;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import resources.TC_978_ExportPortableNetmailSearch.TS_PortableAuditOptionHelper;
import utilities.HelperClass;
import utilities.HelperScript;
import NetmailSearch_General.NetmailLogin;
import NetmailSearch_General.adminLogin;
import NetmailSearch_PrintAndExport.Export_SuperUser;

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
public class TS_PortableAuditOption extends TS_PortableAuditOptionHelper
{
	/**
	 * Script Name   : <b>TS_PortableAuditOption</b>
	 * Generated     : <b>Aug 5, 2014 12:42:51 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/05
	 * @author Administrator
	 */
	private String extractLocation = "C:\\Users\\Administrator\\Downloads\\ISO";
	private String fileLocation = "C:\\Users\\Administrator\\Downloads\\";
	private String workSpace = "\\\\10.10.23.61\\Data\\NetmailSearchGV\\NetmailSearch_Automation";
	public void testMain(Object[] args) 
	{
		setup();
		
		NetmailLogin.login();
		waitForloading();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("rif");
		waitForloading();
		testExportAudit(true);

		NetmailLogin.login();
		waitForloading();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("rif");
		waitForloading();
		testExportAudit(false);
		
		tearDown();
	}
	
	private void setup(){
		try {
			File isoFolder = new File("extractLocation");
			if(!isoFolder.exists()){
				isoFolder.mkdir();
			}else{
				FileUtils.cleanDirectory(isoFolder);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void tearDown(){
		NetmailLogin.login();
		waitForloading();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("rif");
		waitForloading();
		
		Export_SuperUser esu = new Export_SuperUser();
		TestObject yesaudit = esu.getExportObject("yesAudit");
		if(yesaudit !=null){
			esu.deleteExport(yesaudit);
		}
		
		try{		
			waitForloading();
			esu.closeExportManagementWindow();
			sleep(0.5);
			esu.openExportManagementWindow();
		}catch(UnsupportedActionException e){
			esu.closeExportManagementWindow();
			esu.openExportManagementWindow();
		}
		
		TestObject noaudit = esu.getExportObject("noAudit");
		if(noaudit !=null){
			esu.deleteExport(noaudit);
		}
	}
	
	private void testExportAudit(Boolean includeAudit){
		String hasAudit = includeAudit ? "yes" : "no";
		String exportName = hasAudit+"Audit";
		String exportZipName = exportName+".zip";
		
		Export_SuperUser esu = new Export_SuperUser();
		esu.setItemOptions(1);
		esu.setExportTypeOption(3);
		
		if(!includeAudit){
			esu.setAdditionalOptions("3");
		}
		
		esu.setExportName(exportName);
		esu.create();
		esu.waitForExport(exportName);
		
		esu.openExport(esu.getExportObject(exportName));
		esu.downloadExportFile(exportZipName);
		HelperClass.CloseAllBrowsers();
		sleep(2);
		
		HelperScript hs = new HelperScript();
		hs.extractZip(fileLocation+exportZipName, extractLocation, workSpace);
	
//		Verify NetmailSearchLite.exe
		try {
			HelperClass.CloseAllBrowsers();
			logInfo("Closer all browser");
			Process netmailLite = Runtime.getRuntime().exec(extractLocation+"\\NetmailSearchLite.exe");
			logInfo("Launched netmail search lite");
			browser_htmlBrowser().waitForExistence(240, DISABLED);
			sleep(20);
			waitForloading();
			
		
			TestObject[] results;
			TestObject visibleResultContainer;
			visibleResultContainer= HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
			results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			
			for(TestObject result : results){
				((GuiTestObject) result).click();
				((GuiTestObject) result).doubleClick();
				waitForloading();
				link_auditTab().click();
				waitForloading();
				TestObject[] audits = html_messageWindow0().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), true);
				TestObject firstAudit = audits[0];
				TestObject[] tds = firstAudit.find(atDescendant(".tag", "TD"), false);
				
				if(!includeAudit){
					vpManual("first_audit_action", "Exported",  tds[1].getProperty(".contentText").toString().trim()).performTest();
					logTestResult("first_audit_auditor", tds[2].getProperty(".contentText").toString().toLowerCase().contains(adminUserName.toLowerCase()));
				}else{
					vpManual("first_audit_action", "Created",  tds[1].getProperty(".contentText").toString().trim()).performTest();
					logTestResult("first_audit_auditor", tds[3].getProperty(".contentText").toString().contains("Generated by"));
				}
				html_messageClose().click();
			}
			
			HelperClass.CloseAllBrowsers();
			sleep(20);
			netmailLite.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

