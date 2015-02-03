package TestCases.TC_DiagnosticUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import resources.TestCases.TC_DiagnosticUI.DiagnosticUI_PlatformStatusWithHelper;
import NetmailAdminUUI.WebAdmin;

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
public class DiagnosticUI_PlatformStatusWith extends DiagnosticUI_PlatformStatusWithHelper
{
	/**
	 * Script Name   : <b>DiagnosticUI</b>
	 * Generated     : <b>Dec 12, 2014 2:23:12 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/12/12
	 * @author Administrator
	 */
	/*** REQUIRE NETMAIL FOLDER TO BE SHARED AND ACCESSIBLE****/
	private String statusJsonFilePath = "\\\\10.1.30.64\\netmail\\var\\monitor\\default\\default\\status.json";
	private String statusJsonBackupFilePath = "\\\\10.1.30.64\\netmail\\var\\monitor\\default\\default\\status_bk.json";
	
	private JSONObject statusTemplate;
	public void testMain(Object[] args) throws IOException 
	{
	
//		WebAdmin wa = new WebAdmin();
//		wa.loadWebadminUUI();
//		wa.login();
//		wa.selectPageTab("Diagnostics");
			
//		getTemplate();
	   //Create backup
	   createBackup();

		
		
		testExchange();
		
		System.out.println("asdads");


		
	}
	
	private void testExchange() throws IOException{
		
		FileWriter fw = null;
		String color = "Red";
		JSONParser parser=new JSONParser();
		BufferedReader br = null;
		try{

			   br = new BufferedReader(new InputStreamReader(DiagnosticUI_PlatformStatusWith.class.getResourceAsStream("DiagStatusTextFiles/exchangeStatus"+color))); 
			   JSONObject exObj = (JSONObject) parser.parse(br);
			   
			   br = new BufferedReader(new InputStreamReader(DiagnosticUI_PlatformStatusWith.class.getResourceAsStream("DiagStatusTextFiles/configurationStatus"+color)));
			   JSONObject configObj = (JSONObject) parser.parse(br);
			   
			   br = new BufferedReader(new InputStreamReader(DiagnosticUI_PlatformStatusWith.class.getResourceAsStream("DiagStatusTextFiles/authenticationStatus"+color)));
			   JSONObject authObj = (JSONObject) parser.parse(br);
			   
			   br.close();
			   
			   JSONObject jobject = new JSONObject();
			   jobject.put("Exchange", exObj.get("Exchange"));
			   jobject.put("Configuration Repository", configObj.get("Configuration Repository"));
			   jobject.put("Authentication Source", authObj.get("Authentication Source"));
			   
			   fw = new FileWriter(statusJsonFilePath);
			   fw.write(jobject.toJSONString());
			   fw.flush();
			   fw.close();
			   
		       System.out.println("Successfully Copied to File...");
		       
		       WebAdmin wa = new WebAdmin();
		       wa.selectPageTab("Diagnostics");
		       sleep(2);
				
		       testStatus(color);
		       
			 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void testStatus(String color){
		String status = "";
		IFtVerificationPoint vp;
		if(color.contentEquals("Red")){
			status = "Bad";
		}
		if(color.contentEquals("Green")){
			status = "Good";
		}
		if(color.contentEquals("Yellow")){
			status = "Degraded";
		}
		
		
		//infra is the DIV container fo the three status of Exchange, config, authentication source
		TestObject infra = html_framework().find(atDescendant(".tag", "DIV", "class", "infra"), false)[0];
		TestObject[] statusObjects = infra.find(atChild("title", "Status: "+status), false);
		vpManual("ExpectedNumOfStatuses"+status, 3, statusObjects.length).performTest();
		
		int count = 0;
		for(TestObject statusObject : statusObjects){
			count++;
			((GuiTestObject)statusObject.find(atDescendant(".tag", "IMG"), true)[0]).performTest(getStatusVP(color));
			
			((GuiTestObject)statusObject).click();
			sleep(1);
			
			RegularExpression styleRegexp = new RegularExpression(".*display: block;.*visibility: visible;.*opacity: 1;.*", false);
			TestObject lightBox = html_framework().find(atDescendant("class", "view", "style", styleRegexp), false)[0];
			
			if(count ==1){
				TestObject[] innerStatusObjects = lightBox.find(atDescendant("title", "Status: "+status), false);
				vpManual("ExpectedNumOfInnerStatuses"+status, 3, innerStatusObjects.length).performTest();
				
				for(TestObject innerStatusObject : innerStatusObjects){
					((GuiTestObject)innerStatusObject.find(atDescendant(".tag", "IMG"), true)[0]).performTest(getStatusVP(color));
				}
			}else{
				TestObject[] innerStatusObjects = lightBox.find(atDescendant("title", "Status: "+status), false);
				vpManual("ExpectedNumOfInnerStatuses"+status, 2, innerStatusObjects.length).performTest();
				
				for(TestObject innerStatusObject : innerStatusObjects){
					((GuiTestObject)innerStatusObject.find(atDescendant(".tag", "IMG"), true)[0]).performTest(getStatusVP(color));
				}
			}
			((GuiTestObject)lightBox.find(atDescendant(".class", "Html.INPUT.button", "value", "Close"))[0]).click();
			sleep(1);
		}
		
//		image_firstStatusGreen().performTest( statusGreen_statusGreenVP() );
	}
	
	public IFtVerificationPoint getStatusVP(String color){
		IFtVerificationPoint vp = null;
		if(color.contentEquals("Red")){
			 vp = statusRed_statusRedVP();
		}
		if(color.contentEquals("Green")){
			 vp = statusGreen_statusGreenVP();
		}
		if(color.contentEquals("Yellow")){
			 vp = statusYellow_statusYellowVP();
		}
		return vp;
	}
	
	private void createBackup(){
		File bkFile = new File(statusJsonBackupFilePath);
		File statusFile = new File(statusJsonFilePath);
		statusFile.renameTo(bkFile);
	}
	
	private void getTemplate() throws IOException{
		JSONParser parser=new JSONParser();
		try{
		   BufferedReader br = new BufferedReader(new InputStreamReader(DiagnosticUI_PlatformStatusWith.class.getResourceAsStream("DiagStatusTextFiles/JSONstatusTemplate")));
		   Object obj = parser.parse(br);
		   statusTemplate = (JSONObject) obj;
		   br.close();
		 
		}catch(ParseException pe){
		   System.out.println("position: " + pe.getPosition());
		   System.out.println(pe);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTerminate(){
		File bkFile = new File(statusJsonBackupFilePath);
		File newFile = new File(statusJsonFilePath);
		if(bkFile.exists()){
			newFile.delete();
			bkFile.renameTo(newFile);
		}
	}
	
}

