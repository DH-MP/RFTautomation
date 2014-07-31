package TestCases.TS_2058_Extract_Inject_Archive_Search;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import resources.TestCases.TS_2058_Extract_Inject_Archive_Search.TS_Transend_GroupWiseHelper;
import utilities.HelperClass;

import Netmail_WebAdmin.indexer;
import Netmail_WebAdmin.webAdmin;
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
public class TS_Transend_GroupWise extends TS_Transend_GroupWiseHelper
{
	/**
	 * Script Name   : <b>TS_1691_Transend_GroupWise</b>
	 * Generated     : <b>Jun 12, 2014 5:00:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/06/12
	 * @author Administrator
	 */
	
	Process transend = null;
	private String transendExePath = "C:\\Program Files (x86)\\Transend Migrator\\tmship\\tm11.exe";
	private String sourceType = "GroupWise (via GW API)";
	private String targetType = "Netmail XML Archive";
	public void testMain(Object[] args) 
	{
		String sourceUserName = dpString("sourceUserName");
		String sourcePassword = dpString("sourcePassword");
		String targetUserName = dpString("targetUserName");
		String targetUserCN  = dpString("targetUserCN");
		String targetSharedDirectory = dpString("targetSharedDirectory");
		String selectedFolders = dpString("selectFolders");
		
		//Setup
		File directory = new File(targetSharedDirectory);
		if(directory.exists()){
			try {
				FileUtils.cleanDirectory(directory);
			} catch (IOException e1) {
				logError(e1.getMessage());
				e1.printStackTrace();
			}
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
		t.setSourceUserName(sourceUserName);
		t.setSourcePassword(sourcePassword);

		t.setTargetType(targetType);
		t.setTargetSharedDirectory(targetSharedDirectory);
		t.setTargetUserName(targetUserName);
		t.setTargetUserCN(targetUserCN);
		t.setSelectedFolders(selectedFolders);
		
		t.setCategory("email");
		t.setData();
		
		t.setCategory("addressBook");
		t.setData();

		t.setCategory("calendar");
		t.setData();
		
		t.setCategory("task");
		t.setData();
		
		t.startSingleMigration();	
		
		
	}
	
	@Override
	public void onTerminate(){
		transend.destroy();
	}
}

