package TC_827_NetmailSearch_Search;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import resources.TC_827_NetmailSearch_Search.TS_991_AdvanceSearch_AuditHelper;
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
public class TS_991_AdvanceSearch_Audit extends TS_991_AdvanceSearch_AuditHelper
{
	/**
	 * Script Name   : <b>TS_991_AdvanceSearch_Audit</b>
	 * Generated     : <b>Sep 17, 2013 10:22:05 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/17
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		//Login
		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		//Admin Login
		Object[] al = {dpString("caseName"), dpString("userType")};
		callScript("adminLogin", al);
		
		
		HashMap<String, Boolean> query = new HashMap<String, Boolean> ();
		//Actions
		query.put("created" 		,	dpBoolean("created"));
		query.put("viewed" 			,	dpBoolean("viewed"));
		query.put("exported" 		,	dpBoolean("exported"));
		query.put("forwarded" 		,	dpBoolean("forwarded"));
		query.put("republished" 	,	dpBoolean("republished"));
		query.put("printed" 		,	dpBoolean("printed"));
		query.put("deletedAdmin" 	,	dpBoolean("deletedAdmin"));
		query.put("deletedUser" 	,	dpBoolean("deletedUser"));
		query.put("commented"		,	dpBoolean("commented"));
		query.put("flagged" 		,	dpBoolean("flagged"));
		query.put("migrated" 		,	dpBoolean("migrated"));
		query.put("retained" 		,	dpBoolean("retained"));
		query.put("sentForPrinting" ,	dpBoolean("sentForPrinting"));	
		query.put("sentTo"	 		,	dpBoolean("sentTo"));
		query.put("stubbed" 		,	dpBoolean("stubbed"));
		
		//Tags
		query.put("relevant" 		,	dpBoolean("relevant"));
		query.put("privileged" 		,	dpBoolean("privileged"));
		query.put("tagFlagged" 		,	dpBoolean("tagFlagged"));
		query.put("workProduct" 	,	dpBoolean("workProduct"));
		
		//Search, Cancel, Clear
		query.put("search" 			,	true);
		query.put("cancel" 			,	false);
		query.put("clear" 			,	false);
		
		HashMap<String, String> query2 = new HashMap<String, String> ();
		query2.put("auditor" 		,	dpString("auditor"));
		query2.put("comment" 		,	dpString("comment"));
		
		
		Object[] aSAS = {query, query2};
		callScript("NetmailSearch_AdvanceSearch.Audit_SuperUser", aSAS);
		sleep(4);
		waitForloading();

		TestObject[] results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		if(dpBoolean("expectedResults") && results.length == 0){
			logTestResult("No results when expected to have results", false);
			return;
		}else if(!dpBoolean("expectedResults") && results.length > 0){
			logError("Expected no results");
			return;
		}else{
			if(dpInt("expectedNumberOfResults") != -1)
				vpManual("ExpectedNumberOfResults", dpInt("expectedNumberOfResults"), results.length).performTest();
		}
		
		
		for(int i = 0; i < results.length; i++){
			((GuiTestObject)results[i]).hover();
			((GuiTestObject)results[i]).doubleClick();
			logInfo("open message: "+i);
			html_messageWindow0().waitForExistence(123, DISABLED);
			link_messageAuditTab().click();
			logInfo("Clicked Audit Tab");
			waitForloading();
			
			
			logInfo("Validating Results:"+i);
			
			//Action List
			ArrayList<Boolean> actionExists = new ArrayList<Boolean>();
			if(dpBoolean("created")){
				actionExists.add(messageHasAction("Created"));
			}
			if(dpBoolean("viewed")){
				actionExists.add(messageHasAction("Viewed"));
			}
			if(dpBoolean("exported")){
				actionExists.add(messageHasAction("Exported"));
			}
			if(dpBoolean("forwarded")){
				actionExists.add(messageHasAction("Forwarded"));
			}
			if(dpBoolean("republished")){
				actionExists.add(messageHasAction("Republished"));
			}
			if(dpBoolean("printed")){
				actionExists.add(messageHasAction("Printed"));
			}
			if(dpBoolean("deletedAdmin")){
				//TODO
			}
			if(dpBoolean("deletedUser")){
				//TODO
			}
			if(dpBoolean("commented")){
				actionExists.add(messageHasAction("Commented"));
			}
			if(dpBoolean("flagged")){
				actionExists.add(messageHasAction("Flagged"));
			}
			if(dpBoolean("migrated")){
				actionExists.add(messageHasAction("Migrated"));
			}
			if(dpBoolean("retained")){
				actionExists.add(messageHasAction("Retained"));
			}
			if(dpBoolean("sentForPrinting")){
				actionExists.add(messageHasAction("Sent for printing"));
			}
			if(dpBoolean("sentTo")){
				actionExists.add(messageHasAction("Sent To"));
			}
			if(dpBoolean("stubbed")){
				actionExists.add(messageHasAction("Stubbbed"));
			}
			
			if(actionExists!=null && !actionExists.isEmpty()){
				System.out.println(actionExists.toString());
				if(actionExists.contains(true) | actionExists.contains("true")){
					logTestResult("audit ActionList Verification", true);
				}else{
					logError("advance Search Audit Action list Verifiction Failed");
				}
			}
			
			//AUDITOR
			if(!dpString("auditor").isEmpty()){
				Property[] columnProperties = {	
						new Property(".class", "Html.TD"),
						new Property(".cellIndex", "2"),
						new Property(".text", new RegularExpression("(?i)"+dpString("auditor")+".*", false))
				};
				TestObject[] messageBody = html_messageWindow0().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-body"),true);
				TestObject[] secondColumn = messageBody[0].find(atDescendant(columnProperties), false);
				logTestResult("Expected an Auditor by the name < "+dpString("auditor")+" >", secondColumn.length>0);
			}
			
			//COMMENT aka note
			if(!dpString("comment").isEmpty()){
				Property[] columnProperties = {	
						new Property(".class", "Html.TD"),
						new Property(".cellIndex", "3"),
						new Property(".text", new RegularExpression("(?i)"+dpString("comment")+".*", false))
				};
				TestObject[] messageBody = html_messageWindow0().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-body"),true);
				TestObject[] secondColumn = messageBody[0].find(atDescendant(columnProperties), false);
				logTestResult("Expected a note with < "+dpString("comment")+" >", secondColumn.length>0);
			}	
			
			
			
			//Tags VPs
			if(dpBoolean("relevant") | dpBoolean("privileged")| dpBoolean("tagFlagged") |dpBoolean("workProduct")){
				HashMap<String, Boolean> tagResult = new HashMap<String, Boolean>();
				tagResult.put("relevant", null);
				tagResult.put("privileged", null);
				tagResult.put("tagFlagged", null);
				tagResult.put("workProduct", null);
				
	
				if(dpBoolean("relevant") | dpBoolean("privileged")| dpBoolean("tagFlagged") | dpBoolean("tagFlagged") |dpBoolean("workProduct")){
					if(dpBoolean("relevant")){
						tagResult.put("relevant", messageHasTag("relevant", new RegularExpression("(?i)Relevant--.*", false)));
					}
					
					if(dpBoolean("privileged")){
						tagResult.put("privileged", messageHasTag("privileged", new RegularExpression("(?i)Privileged--.*", false)));
					}
					
					if(dpBoolean("tagFlagged")){
						tagResult.put("tagFlagged", messageHasTag("tagFlagged", new RegularExpression("(?i)Flagged--.*", false)));
					}
					
					if(dpBoolean("workProduct")){
						tagResult.put("workProduct", messageHasTag("workProduct", new RegularExpression("(?i)Work Product--.*", false)));
					}
		
					Iterator it = tagResult.entrySet().iterator();
					boolean tagTest = false;
					String tagsChecked = "";
					while(it.hasNext()){
						@SuppressWarnings("unchecked")
						Map.Entry<String, Boolean> pair = (Entry<String, Boolean>) it.next();
						if(pair.getValue() == null){
							continue;
						}else{
							tagsChecked += pair.getKey()+" ";
							if(pair.getValue().booleanValue()){
								tagTest = true;
								vpManual("tagVerification", true,true).performTest();
								break;
							}
						}
					}
					if(!tagTest){
						logTestResult("Expected one the following tags: "+tagsChecked, false);
					}
				}
			}
			
			
			//CloseMessage
			html_messageClose().click();
			logInfo("closed message");
		}

	}
	
	
	public boolean messageHasAction(String actionName){
			Property[] columnProperties = {	
					new Property(".class", "Html.TD"),
					new Property(".cellIndex", "1"),
					new Property(".text", actionName),
			};
			TestObject[] messageBody = html_messageWindow0().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-body"),true);
			TestObject[] secondColumn = messageBody[0].find(atDescendant(columnProperties), false);
			if(secondColumn.length>0){
				return true;
			}else{
				return false;
			}
	}
	
	
	public boolean messageHasTag(String tag, RegularExpression tagRegexp){
		Property[] columnProperties = {	
				new Property(".class", "Html.TD"),
				new Property(".cellIndex", "3"),
				new Property(".text", tagRegexp)
		};
		TestObject[] messageBody = html_messageWindow0().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-body"),true);
		TestObject[] secondColumn = messageBody[0].find(atDescendant(columnProperties), false);
		return secondColumn.length>0;
	}
}

