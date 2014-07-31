package TC_827_NetmailSearch_Search;
import resources.TC_827_NetmailSearch_Search.TS_988_CaseManagementHelper;
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
public class TS_988_CaseManagement extends TS_988_CaseManagementHelper
{
	/**
	 * Script Name   : <b>TS_988_CaseManagement</b>
	 * Generated     : <b>Jul 23, 2013 1:20:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/07/23
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		login();
		
		/********************NEW CASE***************************/
		button_newCasebutton().click();
		logInfo("Clicked NewCase");
		sleep(0.5);
		//DATA
		Object[] cmNewCase = {	dpString("name"),
								dpString("description"),
								dpString("status"),
								dpString("caseClass"),
								dpString("openDate"),
								dpString("closeDate"),
								dpString("caseType"),
								dpString("caseSubType"),
								dpBoolean("checkLoadAllCase"),
								dpString("locations"),
								dpString("filterWord"),
								dpBoolean("cancelNewCase"),
		};
		callScript("Case_Management.manageCase", cmNewCase);
		validateCaseCreation(cmNewCase, dpString("name"));
		login();
		
		/*********************EDIT CASE**************************/
		Object[] cmEditCase = {	dpString("newName"),
								dpString("newDescription"),
								dpString("newStatus"),
								dpString("newCaseClass"),
								"", //No new open date variable
								dpString("newCloseDate"),
								dpString("newCaseType"),
								dpString("newCaseSubType"),
								dpBoolean("newCheckLoadAllCase"),
								dpString("newLocations"),
								"", //No new filter variable
								dpBoolean("cancelEditCase"),
								
								//OLD values for verification if data is saved correctly
								dpString("name"),
								dpString("description"),
								dpString("status"),
								dpString("caseClass"),
								dpString("closeDate"),
								dpString("caseType"),
								dpString("caseSubType"),
								dpBoolean("checkLoadAllCase"),
								dpString("locations"),						
		};
		EditCase(cmEditCase, dpString("name"));
		validateCaseCreation(cmEditCase, dpString("newName"));
		sleep(2);
		login();

		/*******VALIDATE: Edit, make changes and Cancel case *************/
		cmEditCase = new Object[]{	"VIVA LA FIESTA",
									"DANCE WIGGLE DANCE",
									"",
									"",
									"", //No new open date variable
									"",
									"",
									"",
									dpBoolean("newCheckLoadAllCase"),
									"",
									"", //No new filter variable
									true, //cancel
									
									//OldValues
									dpString("newName"),
									dpString("newDescription"),
									dpString("newStatus"),
									dpString("newCaseClass"),
									dpString("newCloseDate"),
									dpString("newCaseType"),
									dpString("newCaseSubType"),
									dpBoolean("newCheckLoadAllCase"),
									dpString("newLocations"),
		};
		EditCase(cmEditCase, dpString("newName"));
		sleep(2);

		/***********************VALIDATE: Cancel********************************/
		cmEditCase = new Object[]{	"",
									"",
									"",
									"",
									"", //No new open date variable
									"",
									"",
									"",
									dpBoolean("newCheckLoadAllCase"),
									"",
									"", //No new filter variable
									true, //Cancel
									
									//OldValues
									dpString("newName"),
									dpString("newDescription"),
									dpString("newStatus"),
									dpString("newCaseClass"),
									dpString("newCloseDate"),
									dpString("newCaseType"),
									dpString("newCaseSubType"),
									dpBoolean("newCheckLoadAllCase"),
									dpString("newLocations"),
		};
		EditCase(cmEditCase, dpString("newName"));
		
		
		/*********************DELETE CASE**************************/
		TestObject caseListBody = html_caseListWindow().find(atDescendant(".tag", "DIV", "class", "x-grid3-scroller"), true)[0].find(atChild(".tag", "DIV"))[0];
		TestObject newCase = caseListBody.find(atChild(".tag", "DIV", ".contentText", new RegularExpression(dpString("newName")+".*", false)), false)[0];
		
		//Delete + No/Cancel
		((GuiTestObject)newCase).click();
		button_deleteCasebutton().click();
		logInfo("Clicked delete case");
		button_deleteCase_Nobutton().click();
		logInfo("Clicked No on delete case window");
		
		//Delete case + Yes/Confirm
		((GuiTestObject)newCase).click();
		button_deleteCasebutton().click();
		logInfo("Clicked delete case");
		button_deleteCase_Yesbutton().click();
		logInfo("Clicked Yes on delete case window");
		sleep(3);
		
		//Verify case is deleted
		TestObject[] findCase = caseListBody.find(atChild(".tag", "DIV", ".contentText", new RegularExpression(dpString("newName")+".*", false)), false);
		vpManual("CaseDeleted", 0, findCase.length).performTest();
		
		
	}
	
	private void EditCase(Object[] args, String caseName){
		TestObject caseListBody = html_caseListWindow().find(atDescendant(".tag", "DIV", "class", "x-grid3-scroller"), true)[0].find(atChild(".tag", "DIV"))[0];
		TestObject newCase = caseListBody.find(atChild(".tag", "DIV", ".contentText", new RegularExpression(caseName+".*", false)), false)[0];
		((GuiTestObject)newCase).click();
		logInfo("Clicked on case < "+caseName+" >");
		button_editCasebutton().click();
		logInfo("Clicked edit case");
		callScript("Case_Management.manageCase", args);
	}
	
	private void validateCaseCreation(Object[] args, String caseName){
		TestObject caseListBody = html_caseListWindow().find(atDescendant(".tag", "DIV", "class", "x-grid3-scroller"), true)[0].find(atChild(".tag", "DIV"))[0];
		TestObject newCase = caseListBody.find(atChild(".tag", "DIV", ".contentText", new RegularExpression(caseName+".*", false)), false)[0];
		((GuiTestObject)newCase).doubleClick();
		sleep(20);
		logInfo(" doubleClicked on case < "+caseName+" >");
		
		logInfo("Validating case creations for: locations, loaded or not loaded all case");
		String[] locations = ((String)args[9]).split("\\s");

		TestObject activeBody = utilities.HelperClass.getActiveTabBody()[0];
		System.out.println(activeBody.getProperties());
		TestObject[] results = activeBody.find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"), false);
		if((Boolean)args[8]){
			vpManual("Expected_message_on_load", true, results.length>0).performTest();
		}else{
			vpManual("Expected_no_message_on_load", true, results.length==0).performTest();
		}
		
		for(String locationName : locations){
			TestObject folderSection = activeBody.find(atDescendant(".tag", "DIV", "id", "folderTree0"), false)[0];
			TestObject[] location = folderSection.find(atDescendant(".contentText", new RegularExpression(locationName+".*", false), "class", new RegularExpression("^x-tree-node$", false)), false);
			if(location.length != 1){
				logError("Case does not have location < "+locationName+" >");
			}else{
				String msg = (Boolean)args[8]? "Locations_loaded" : "Locations_NotLoaded";
				String checked = location[0].find(atDescendant(".class", "Html.INPUT.checkbox"))[0].getProperty(".checked").toString();
				vpManual(msg, (Boolean)args[8], Boolean.parseBoolean(checked)).performTest();
			}
		}
		
		
	}
	
	private void login(){
		//Login
			Object[] ls = {null,null, false};
			callScript("loginScript", ls);
			
		//Admin Login
			Object[] al = {"", dpString("userType")};
			callScript("adminLogin", al);
	}
}

