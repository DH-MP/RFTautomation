package TestCases.TS_2058_Extract_Inject_Archive_Search;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import resources.TestCases.TS_2058_Extract_Inject_Archive_Search.TS_Transend_OutLookMSGHelper;
import utilities.HelperClass;
import Netmail_WebAdmin.webAdmin;
import TC_827_NetmailSearch_Search.TS_1378_ListandOrderPersistence;
import Transend.Transend;

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
public class TS_Transend_OutLookMSG extends TS_Transend_OutLookMSGHelper
{
	/**
	 * Script Name   : <b>TS_1695_Transend_Google</b>
	 * Generated     : <b>Jun 13, 2014 11:30:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/06/13
	 * @author Administrator
	 */
	Process transend = null;
	private String transendExePath = "C:\\Program Files (x86)\\Transend Migrator\\tmship\\tm11.exe";
	private String sourceType = "Outlook MSG";
	private String targetType = "Netmail XML Archive";
	public void testMain(Object[] args) 
	{	
		String sourceOutlookMSGDirectory = dpString("sourceOutlookMSGDirectory");
		String targetUserName = dpString("targetUserName");
		String targetUserCN  = dpString("targetUserCN");
		String targetSharedDirectory = dpString("targetSharedDirectory");
		
		//Setup
		File directory = new File(targetSharedDirectory);
		if(directory.exists()){
			try {
				FileUtils.cleanDirectory(directory);
			} catch (IOException e1) {
				logInfo(e1.getMessage());
				e1.printStackTrace();
			}
		}else{
			directory.mkdir();
		}
		
		//Start Transend
		try {
			transend = Runtime.getRuntime().exec(transendExePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Evaluation message
		evaluationPopUp_OKbutton().waitForExistence(150, 3);	
		evaluationPopUp_OKbutton().click();
		logInfo("Click ok on evaluatioin version message");
		sleep(5);
		
		//Data
		Transend t = new Transend();
		t.setSourceType(sourceType);
		t.setSourceOutlookMSGDirectory(sourceOutlookMSGDirectory);
		t.setTargetType(targetType);
		t.setTargetSharedDirectory(targetSharedDirectory);
		t.setTargetUserName(targetUserName);
		t.setTargetUserCN(targetUserCN);	
		t.setData();	
		t.startSingleMigration();
			
		
	}
	
	@Override
	public void onTerminate(){
		transend.destroy();
	}
}

