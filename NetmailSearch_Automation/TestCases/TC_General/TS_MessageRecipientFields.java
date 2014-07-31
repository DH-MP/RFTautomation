package TestCases.TC_General;
import java.util.HashMap;
import java.util.Map;

import resources.TestCases.TC_General.TS_MessageRecipientFieldsHelper;
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
public class TS_MessageRecipientFields extends TS_MessageRecipientFieldsHelper
{
	/**
	 * Script Name   : <b>TS_MessageRecipientFields</b>
	 * Generated     : <b>Jul 21, 2014 2:51:26 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/21
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		//Login
		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		//Admin Login
		Object[] al = {"post", "Super User"};
		callScript("adminLogin", al);
		waitForloading();
				
		TestObject[] messages = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		int i = 1;
		for(TestObject message : messages){
			((GuiTestObject) message).click();
			((GuiTestObject) message).doubleClick();
			logInfo("open message "+i);
			waitForloading();
			validateRecipientFields();
			html_closeMessage().click();
			i++;
		}
	}
	
	private void validateRecipientFields(){
		if(!(text_cc().ensureObjectIsVisible() | text_bc().ensureObjectIsVisible())){
			return;
		}
		
		Map<String, String>  toFieldRecipients = new HashMap<>();
		Map<String, String>  ccFieldRecipients = new HashMap<>();
		Map<String, String>  bccFieldRecipients = new HashMap<>();
		
		parse(text_to(), toFieldRecipients);
		parse(text_cc(), ccFieldRecipients);
		parse(text_bc(), bccFieldRecipients);
		
		link_propertiesTab().click();
		sleep(2);
		
		//Recipients folder
		Property[] properties = new Property[3];
		properties[0] = new Property(".tag", "DIV");
		properties[1] = new Property(".className", "x-tree-node-el folder x-tree-node-collapsed");
		properties[2] = new Property(".contentText", new RegularExpression(".*Recipients.*", false));
		GuiTestObject recipientFolder = (GuiTestObject) html_propertiesList().find(atDescendant(properties), false)[0];
		recipientFolder.hover();
		sleep(1);
		recipientFolder.click();
		recipientFolder.doubleClick();
		
		sleep(2);
		TestObject[] recipientsLI = recipientFolder.getParent().getChildren()[1].getChildren();
		
		for(TestObject r: recipientsLI){
			RegularExpression classRegexp = new RegularExpression(".*x-tree-col.*", false);
			String name = r.find(atDescendant(".tag", "DIV", "class", classRegexp), false)[1].getProperty(".text").toString().trim();
			if(toFieldRecipients.containsKey(name)){
				validateUserInfo(toFieldRecipients, r, name, "TO");	
				
			}else if(ccFieldRecipients.containsKey(name)){
				validateUserInfo(ccFieldRecipients, r, name, "CC");
				
			}else if(bccFieldRecipients.containsKey(name)){
				validateUserInfo(bccFieldRecipients, r, name, "BC");
				
			}else{
				logError("cannot find "+name+"in the field");
			}
		}	
	}
	
	private void parse(TextGuiTestObject field, Map<String, String> map){
		if(field.ensureObjectIsVisible()){
			//ex: "Archive Dedicated" <Archive@sqa.com>, "First, Last" <Last@sqa.com>
			String[] recipients = field.getText().split(">, ");
			for(String r : recipients){
				String[] nameAndEmail = r.replace("\"", "").replace(">", "").split("<");
				map.put(nameAndEmail[0].trim(), nameAndEmail[1].trim());
			}
		}
	}
	
	private void validateUserInfo(Map<String, String> map, TestObject recipient, String name, String distributionTypeExpected){
		RegularExpression classRegexp = new RegularExpression(".*x-tree-col.*", false);
		
		((GuiTestObject) recipient).doubleClick();
		sleep(2);
		TestObject recipientInfoList = recipient.getChildren()[1];
		
		//Email
		RegularExpression emailRegexp = new RegularExpression(".*Email.*", false);
		TestObject emailtreecol = recipientInfoList.find(atDescendant("class", classRegexp, ".contentText", emailRegexp), false)[0];
		String email = emailtreecol.getParent().getChildren()[1].getProperty(".text").toString().trim();
		logTestResult("Email is correct in property tab for "+name, email.contentEquals(map.get(name)));
		
		
		//Distribution Type
		RegularExpression distributionType = new RegularExpression(".*Distribution Type.*", false);
		TestObject distributiontreecol = recipientInfoList.find(atDescendant("class", classRegexp, ".contentText", distributionType), false)[0];
		String distributionTypeActual = distributiontreecol.getParent().getChildren()[1].getProperty(".text").toString().trim();
		logTestResult(email+" is under TO distribution type", distributionTypeActual.contentEquals(distributionTypeExpected));
	}
}

