package TestCases.TC_General;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import resources.TestCases.TC_General.TS_DisablingDeletionHelper;
import utilities.HelperClass;

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
public class TS_DisablingDeletion extends TS_DisablingDeletionHelper
{
	/**
	 * Script Name   : <b>TS_DisablingDeletion</b>
	 * Generated     : <b>Jul 22, 2014 11:31:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/22
	 * @author Administrator
	 */
	
	String cfgFileDirectory = "\\\\"+IP+"\\Messaging Architects\\RemoteProvider";
	private String backupName = cfgFileDirectory+"\\xgwxmlv_BACKUP.cfg";
	private String oldFileName = cfgFileDirectory+"\\xgwxmlv.cfg";
	private String workSpace = remoteWorkSpace+"\\NetmailSearch_Automation";
	
	public void testMain(Object[] args) 
	{
		
		Object[] ls = {null,null, false};
		Object[] al = {"post", "Super User"}; 
		Object[] al2 = {"", "Normal User"}; 
		
		//Both have delete flag off
		setDeletion(false, false);
		
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		logTestResult("Delete does NOT exist for super user", !button_deleteMessage().exists());
		
		callScript("loginScript", ls);
		callScript("adminLogin", al2);
		logTestResult("Delete does NOT exist for normal user", !button_normalUserDeleteMessage().exists());
		
		
		//Both have delete flag on
		revert();
		setDeletion(true, true);
		
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		logTestResult("Delete does exist for super user", button_deleteMessage().exists());
		
		callScript("loginScript", ls);
		callScript("adminLogin", al2);
		logTestResult("Delete does exist for normal user", button_normalUserDeleteMessage().exists());
		
		//both have delete fla
		revert();
		setDeletion(true, false);
		
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		logTestResult("Delete does exist for super user", button_deleteMessage().exists());
		
		callScript("loginScript", ls);
		callScript("adminLogin", al2);
		logTestResult("Delete does NOT exist for normal user", !button_normalUserDeleteMessage().exists());
		
		//both have delete fla
		revert();
		setDeletion(false, true);
		
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		logTestResult("Delete does NOT exist for super user", !button_deleteMessage().exists());
		
		callScript("loginScript", ls);
		callScript("adminLogin", al2);
		logTestResult("Delete does exist for normal user", button_normalUserDeleteMessage().exists());
		
		
	}
	
	private void setDeletion(Boolean adminflag, Boolean normalUserFlag){
		String newFileName = cfgFileDirectory+"\\xgwxmlv_NEW.cfg";
		
		//stop NetmailSearch
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		
		//Change CFG
		try {
			FileReader inputStream = new FileReader(oldFileName);
			BufferedReader br = new BufferedReader(inputStream);

			FileWriter fstream2 = new FileWriter(newFileName);
			BufferedWriter bw = new BufferedWriter(fstream2);

			String strLine;
			while ((strLine = br.readLine()) != null) {
				//analytics.columns_order
				if (strLine.matches("^Message\\.deletable.*") | strLine.matches("^#Message\\.deletable.*")) {
					strLine = "Message.deletable="+normalUserFlag;
				}
				if (strLine.matches("^Message\\.superuserdelete.*") | strLine.matches("^#Message\\.superuserdelete.*")) {
					strLine = "Message.superuserdelete="+adminflag;
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
		
		//start NetmailSearch
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
	}
	
	public void revert(){
		//Stop NetmailSearch
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
				
		File bkFile = new File(backupName);
		if(bkFile.exists()){
			File oldFile = new File(oldFileName);
			oldFile.delete();
			oldFile = new File(oldFileName);
			bkFile.renameTo(oldFile);
		}
		
		//Start NetmailSearch
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
	}
	
	@Override
	public void onTerminate(){	
		revert();
	}
}

