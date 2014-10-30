package NetmailSearch_General;

import java.util.Arrays;

import resources.NetmailSearch_General.adminLoginHelper;
import Case_Management.manageCase;

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
public class adminLogin extends adminLoginHelper
{
	/**
	 * Script Name   : <b>adminLogin</b>
	 * Generated     : <b>May 30, 2013 5:14:07 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/30
	 * @author Administrator
	 */
	private String caseListName = "intern_mike", userType = "Super User";

	private static adminLogin al = null;
	
	public static void selectUserType(String userType){
		adminLogin al = getInstance();
		al.userType = userType;
		al.selectUserType();
	}
	public static void superUser(){
		adminLogin al = getInstance();
		al.userType = "Super User";
		al.selectUserType();
	}
	public static void normalUser(){
		adminLogin al = getInstance();
		al.userType = "Normal User";
		al.selectUserType();
	}
	
	private void selectUserType(){
		/******Click UserType*******/
		String info = "On admin login, selecting: %s";
		logInfo(String.format(info, userType));
		html_userListDIV_extGen80().waitForExistence(240, DISABLED);
		sleep(0.5);

		TestObject[] roles = html_userListDIV_extGen80().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"));
		vpManual("NumberOfRoles", 2, roles.length ).performTest();
		for(TestObject role: roles){
			String roleName = role.getProperty(".text").toString();
			
			String[] expectedRoleNames = {"super user", "normal user"};//role in lowercase
			
			boolean exists = Arrays.asList(expectedRoleNames).contains(roleName.trim().toLowerCase());
			if(exists){
				vpManual("Expected_Role_In_SelectionWindow", true, true).performTest();
			}else{
				vpManual("Role_not_Expected", false, false).performTest();
			}
		}

		TestObject[] user = html_userListDIV_extGen80().find(atDescendant(".class", "Html.TABLE", ".text", userType), true);
		((GuiTestObject)user[0]).click();	
		button_continuebutton().click();
		sleep(4);
		waitForloading();
	}
	
	
	
	public static void selectCase(String caseListName){
		adminLogin al = getInstance();
		al.caseListName = caseListName;
		al.selectCase();
	}
	
	private void selectCase(){
		manageCase.clickOkButtonReviewCase();
		
		/******Click Case*******/
		if( caseListName != null && !caseListName.isEmpty()){
			String info = "As super user login, selecting the case: %s ";
			logInfo(String.format(info, caseListName));
			html_caseListDIV_extGen166().waitForExistence(1000, DISABLED);
			sleep(0.5);
			TestObject[] searchCase = html_caseListDIV_extGen166().find(atDescendant(".class", "Html.DIV", ".text", caseListName), false);
			((GuiTestObject)searchCase[0]).click();
			button_openCasebutton().click();
		}
		waitForloading();
		waitForloading();
	}
	
	
	private static adminLogin getInstance(){
		if(al == null){
			al = new adminLogin();
		}
		return al;
	}
	

}

