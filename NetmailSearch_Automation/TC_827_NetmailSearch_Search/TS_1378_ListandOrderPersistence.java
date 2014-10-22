package TC_827_NetmailSearch_Search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.regex.Matcher;

import resources.TC_827_NetmailSearch_Search.TS_1378_ListandOrderPersistenceHelper;
import utilities.HelperClass;

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
 * Description : Functional Test Script
 * 
 * @author Administrator
 */
public class TS_1378_ListandOrderPersistence extends
		TS_1378_ListandOrderPersistenceHelper {
	/**
	 * Script Name : <b>TS_1378_ListandOrderPersistence</b> Generated : <b>Oct
	 * 15, 2013 1:14:27 PM</b> Description : Functional Test Script Original
	 * Host : WinNT Version 6.1 Build 7601 (S)
	 * 
	 * @since 2013/10/15
	 * @author Administrator
	 */
	
	
	public void testMain(Object[] args) {
		//Login
		HelperClass.CloseAllBrowsers();
		sleep(4);
		HelperClass.oneBrowserSetup();
		browser_htmlBrowser().waitForExistence(30, DISABLED);
		browser_htmlBrowser().deleteCookies();
		
		NetmailLogin.login();
		
		adminLogin.selectUserType("Normal User");
		
		TS_992_AddTagsAndComments atac = new TS_992_AddTagsAndComments();
		atac.headerColumns(true, true, true, false, false, false);
		
		
		//default
		TestObject visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		TestObject firstResult = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true)[0];
		TestObject[] rows = firstResult.find(atDescendant(".class", "Html.TR"), false);
		TestObject[] oldcolumns = rows[0].find(atDescendant(".class", "Html.TD"), false);

		String status = oldcolumns[3].getProperty("class").toString();
		String from = oldcolumns[5].getProperty(".text").toString();
		String to = oldcolumns[6].getProperty(".text").toString();
		String subject = oldcolumns[7].getProperty(".text").toString();
		String startDate = oldcolumns[8].getProperty(".text").toString();
		String receiveDate = oldcolumns[9].getProperty(".text").toString();
		
		unregister(rows);
		changeColumn();
			
		//Login
		HelperClass.CloseAllBrowsers();
		sleep(4);
		HelperClass.oneBrowserSetup();
		browser_htmlBrowser().waitForExistence(30, DISABLED);
		browser_htmlBrowser().deleteCookies();

		NetmailLogin.login();
		adminLogin.selectUserType("Normal User");
		
		String columnName = dpString("columnOrder").replace("'", "");
		String[] columnOrderSplit = columnName.split(",");
		
		visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		firstResult = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true)[0];
		rows = firstResult.find(atDescendant(".class", "Html.TR"), false);
		TestObject[] columns = rows[0].find(atDescendant(".class", "Html.TD"), false);
		
		int columnLength = columns.length -1; //Temporary Fixed for new folder column in 5.3
		for(int i=0; i<columnLength; i++){
			GuiTestObject column = (GuiTestObject) columns[i];
			String columnValue;
			switch (columnOrderSplit[i]) {
			case "expander":
				TestObject[] expander = column.find(atDescendant(".class", "Html.DIV", "class", "x-grid3-row-expander"), false);
				vpManual("ColumnNumber_"+i+"_Expander", true, expander.length>0).performTest();
				break;
			case "checkbox":
				vpManual("ColumnNumber_"+i+"_checkbox", "x-grid3-col x-grid3-cell x-grid3-td-checker" , column.getProperty("class").toString().trim()).performTest();
				break;
			case "status":
				columnValue = column.getProperty("class").toString().trim().replaceAll("\\d", "#");
				vpManual("ColumnNumber_"+i+"_status", status.trim().replaceAll("\\d", "#"), columnValue).performTest();
				break;
			case "to":
				columnValue = column.getProperty(".text").toString();
				vpManual("ColumnNumber_"+i+"_to", to , columnValue).performTest();
				break;
			case "startDate":
				columnValue = column.getProperty(".text").toString();
				vpManual("ColumnNumber_"+i+"_startDate", startDate , columnValue).performTest();
				break;
			case "receivedDate":
				columnValue = column.getProperty(".text").toString();
				vpManual("ColumnNumber_"+i+"_sentReceive", receiveDate , columnValue).performTest();
				break;
			case "from":
				columnValue = column.getProperty(".text").toString();
				vpManual("ColumnNumber_"+i+"_from", from , columnValue).performTest();
				break;
			case "subject":
				columnValue = column.getProperty(".text").toString();
				vpManual("ColumnNumber_"+i+"_subject", subject , columnValue).performTest();
				break;
			case "attachmentCount":
				vpManual("ColumnNumber_"+i+"_attachmentCount", "x-grid3-col x-grid3-cell x-grid3-td-"+i+" attachment" , column.getProperty("class").toString().trim()).performTest();
				break;
			default:
				break;
			}
		}


	}
	
	private void changeColumn(){
		String cfgFileDirectory = "\\\\"+IP+dpString("remoteProviderDirectory");
		String backupName = cfgFileDirectory+"\\xgwxmlv_BACKUP.cfg";
		String oldFileName = cfgFileDirectory+"\\xgwxmlv.cfg";
		String newFileName = cfgFileDirectory+"\\xgwxmlv_NEW.cfg";
		
		//Stop Services to make changes
		startOrStopNetmailServices(false);
		sleep(5);
		
		//Change CFG
		try {
			FileReader inputStream = new FileReader(oldFileName);
			BufferedReader br = new BufferedReader(inputStream);

			FileWriter fstream2 = new FileWriter(newFileName);
			BufferedWriter bw = new BufferedWriter(fstream2);

			String strLine;
			while ((strLine = br.readLine()) != null) {
				//analytics.columns_order
				if (strLine.matches("^analytics.columns_order.*") | strLine.matches("^#analytics.columns_order.*")) {
					strLine = "analytics.columns_order=["
							+ dpString("columnOrder") + "]";
				}
				if (strLine.matches("^#analytics.columns_visible.*")) {
					strLine = strLine.substring(1);
				}
				
				//analytics.columns_visible
				if (strLine.matches("^analytics.columns_visible.*") | strLine.matches("^#analytics.columns_visible.*")) {
					strLine = "analytics.columns_visible=["
							+ dpString("columnOrder") + "]";
				}
				if (strLine.matches("^#analytics.columns_visible.*")) {
					strLine = strLine.substring(1);
				}
				

				bw.write(strLine);
				bw.newLine();

			}
			bw.close();
			br.close();

			File bkFile = new File(backupName);
			if(bkFile.exists()){
				bkFile.delete();
				bkFile = new File(backupName);
			}
			File oldFile = new File(oldFileName);
			File newFile = new File(newFileName);
			oldFile.renameTo(bkFile);
			newFile.renameTo(oldFile);
			newFile.delete();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		
		//start all services
		startOrStopNetmailServices(true);
		sleep(10);
	}
	
	@Override
	public void onTerminate(){
		String cfgFileDirectory = "\\\\"+IP+dpString("remoteProviderDirectory");
		String backupName = cfgFileDirectory+"\\xgwxmlv_BACKUP.cfg";
		String oldFileName = cfgFileDirectory+"\\xgwxmlv.cfg";
		
		//stop services
		startOrStopNetmailServices(false);
		
		File bkFile = new File(backupName);
		if(bkFile.exists()){
			File oldFile = new File(oldFileName);
			oldFile.delete();
			oldFile = new File(oldFileName);
			bkFile.renameTo(oldFile);
		}
		
		
		//start services
		startOrStopNetmailServices(true);
	}
	
	private void startOrStopNetmailServices(boolean start){
		String netmailIP = IP;
		
		String workSpace = dpString("workSpace");
		
		String[] a;
		Process b;
		if(start){
			// start all services
			a = new String[] {
					"cmd.exe",
					"/C start " + workSpace + "\\assets\\startAllNetmailServices.bat "
							+ "\"\\\\"+netmailIP };
			try {
				b = Runtime.getRuntime().exec(a);
				sleep(3);
				b.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//stop all services
			a = new String[] {
					"cmd.exe",
					"/C start " + workSpace + "\\assets\\stopAllNetmailServices.bat " + "\"\\\\" + netmailIP};
			
			try {
				b = Runtime.getRuntime().exec(a);
				sleep(3);
				b.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		sleep(50);
	}
}
