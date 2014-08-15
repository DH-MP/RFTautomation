package NetmailSearch_AdvanceSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import resources.NetmailSearch_AdvanceSearch.Audit_SuperUserHelper;
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
public class Audit_SuperUser extends Audit_SuperUserHelper
{
	/**
	 * Script Name   : <b>advanceSearchAudit_SuperUser</b>
	 * Generated     : <b>Sep 17, 2013 8:56:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/17
	 * @author Administrator
	 */
	
	private boolean created = false,
					viewed = false,
					exported = false,
					forwarded = false,
					republished = false,
					printed = false,
					deletedAdmin = false,
					deletedUser = false,
					commented = false,
					flagged = false,
					migrated = false,
					retained = false,
					sentForPrinting = false,
					sentTo = false,
					stubbed = false,
					relevant = false,
					privileged = false, 
					tagFlagged = false,
					workProduct = false,
					search = false,
					clear = false,
					cancel = false;
	
	
	private String auditor = "", comment = "";
	
					
	public void testMain(Object[] args) 
	{
		
		@SuppressWarnings("unchecked")
		HashMap<String, Boolean> checkboxes = (HashMap<String, Boolean>) args[0];
		created 		= checkboxes.get("created");
		viewed 			= checkboxes.get("viewed");
		exported 		= checkboxes.get("exported");
		forwarded 		= checkboxes.get("forwarded");
		republished 	= checkboxes.get("republished");
		printed 		= checkboxes.get("printed");
		deletedAdmin 	= checkboxes.get("deletedAdmin");
		deletedUser 	= checkboxes.get("deletedUser");
		commented 		= checkboxes.get("commented");
		flagged 		= checkboxes.get("flagged");
		migrated 		= checkboxes.get("migrated");
		retained 		= checkboxes.get("retained");
		sentForPrinting = checkboxes.get("sentForPrinting");
		sentTo 			= checkboxes.get("sentTo");
		stubbed			= checkboxes.get("stubbed");
		relevant 		= checkboxes.get("relevant");
		privileged 		= checkboxes.get("privileged");
		tagFlagged 		= checkboxes.get("tagFlagged");
		workProduct 	= checkboxes.get("workProduct");
		search 			= checkboxes.get("search");
		clear 			= checkboxes.get("clear");
		cancel 			= checkboxes.get("cancel");
		
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> inputField = (HashMap<String, String>) args[1];
		auditor 		= inputField.get("auditor");
		comment 		= inputField.get("comment");
		
		boolean checkActionList = created | viewed | exported | forwarded |
				republished | printed | deletedAdmin| deletedUser | commented |
				flagged | migrated | retained | sentForPrinting | sentTo | stubbed;
				
		boolean checkTags = relevant | privileged | tagFlagged | workProduct;
		
		
		
		button_advancedSearchbutton().click();
		logInfo("Clicked advance search button");
		sleep(2);
		link_advanceSearchAudit().click();
		logInfo("Click on audit in dropdown");
		
		TestObject[] emptycheckBoxes = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.checkbox", "CHECKED", "true"), true);
		TestObject[] emptyInputBoxes = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.text", ".value", ""), true);
		
		vpManual("ByDefault_checkboxes_empty", 0, emptycheckBoxes.length).performTest();
		vpManual("ByDefault_inputField_empty", 2, emptyInputBoxes.length).performTest();
		
		
		html_advancedSearchWindow().waitForExistence(123, DISABLED);
		if(checkActionList){
			checkBox_ifActionson().click();
			logInfo("checked action lists");
			sleep(1);
			
			if(created){
				checkBox_actionscreated().click();
				logInfo("checked created");
			}	
			if(viewed){
				checkBox_actionsviewed().click();
				logInfo("checked viewed");
			} 
			if(exported){
				checkBox_actionsexported().click();
				logInfo("checked exported");
			}
			if(forwarded){
				checkBox_actionsforwarded().click();
				logInfo("checked fowarded");
			}
			if(republished){
				checkBox_actionsrepublished().click();
				logInfo("checked republished");
			}
			if(printed){
				checkBox_actionsprinted().click();
				logInfo("checked printed");
			}
			if(deletedAdmin){
				checkBox_actionsdeleted().click();
				logInfo("checked deleted(admin)");
			}
			if(deletedUser){
				checkBox_actionsenduserdeleted().click();
				logInfo("checked deleted(suer)");
			}
			if(commented){
				checkBox_actionscommented().click();
				logInfo("checked commented");
			}
			if(flagged){
				checkBox_actionsflagged().click();
				logInfo("checked flagged");
			}
			if(migrated){
				checkBox_actionsmigrated().click();
				logInfo("checked migrated");
			}
			if(retained){
				checkBox_actionsretained().click();
				logInfo("checked retained");
			}
			if(sentForPrinting){
				checkBox_actionssentforprintin().click();
				logInfo("checked sentForPrinting");
			}
			if(sentTo){
				checkBox_actionssentto().click();
				logInfo("checked sentTo");
			}
			if(stubbed){
				checkBox_actionsstubbed().click();
				logInfo("checked stubbed");
			}
		}
		

		
		if(!auditor.isEmpty()){
			checkBox_ifAuditoron().click();
			sleep(2);
			text_auditor().unregister();
			text_auditor().click();
			logInfo("Checked and Clicked on auditor");
			browser_htmlBrowser().inputChars(auditor);
			logInfo("input < "+ auditor +" >");
		}
		
		
		if(!comment.isEmpty()){
			checkBox_ifCommenton().click();
			sleep(2);
			TestObject[] commentInput = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.text", "name", "comment"));
			((GuiTestObject)commentInput[0]).click();
			logInfo("Checked and Clicked on comment");
			browser_htmlBrowser().inputChars(comment);
			logInfo("input < "+ comment +" >");
		}
		
		if(checkTags){
			checkBox_ifTagson().click();
			if(relevant){
				checkBox_tagsRelevant().click();
				logInfo("checked relevant");
			}
			
			if(privileged){
				checkBox_tagsPrivilege().click();
				logInfo("checked privileged");
			}
			
			if(tagFlagged){
				checkBox_tagsFlagged().click();
				logInfo("checked tagFlagged");
			}
			
			if(workProduct){
				checkBox_tagsWorkProduct().click();
				logInfo("checked workProduct");
			}
		}
		
		
		
		if(search){
			button_searchsubmit().click();
			logInfo("Clicked search");
			waitForloading();
			waitForloading();//just in case
		}
		
		if(clear){
			button_clearsubmit().click();
			logInfo("Clicked clear search");
			vpManual("Clear_checkboxes_empty", 0, emptycheckBoxes.length).performTest();
			vpManual("Clear_inputField_empty", 2, emptyInputBoxes.length).performTest();
		}
		
		if(cancel){
			button_cancelsubmit().click();
			logInfo("Clicked cancel");
		}
	}
	public void openAdvanceSearchAudit(){
		button_advancedSearchbutton().click();
		logInfo("Clicked advance search button");
		sleep(2);
		link_advanceSearchAudit().click();
		logInfo("Click on audit in dropdown");
	}
	public void inputAudit(){		
		boolean checkActionList = created | viewed | exported | forwarded |
				republished | printed | deletedAdmin| deletedUser | commented |
				flagged | migrated | retained | sentForPrinting | sentTo | stubbed;
				
		boolean checkTags = relevant | privileged | tagFlagged | workProduct;
		
		html_advancedSearchWindow().waitForExistence(123, DISABLED);
				
		TestObject[] emptycheckBoxes = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.checkbox", "CHECKED", "true"), true);
		TestObject[] emptyInputBoxes = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.text", ".value", ""), true);
		
		vpManual("ByDefault_checkboxes_empty", 0, emptycheckBoxes.length).performTest();
		vpManual("ByDefault_inputField_empty", 2, emptyInputBoxes.length).performTest();
		
		

		if(checkActionList){
			checkBox_ifActionson().click();
			logInfo("checked action lists");
			sleep(1);
			
			if(created){
				checkBox_actionscreated().click();
				logInfo("checked created");
			}	
			if(viewed){
				checkBox_actionsviewed().click();
				logInfo("checked viewed");
			} 
			if(exported){
				checkBox_actionsexported().click();
				logInfo("checked exported");
			}
			if(forwarded){
				checkBox_actionsforwarded().click();
				logInfo("checked fowarded");
			}
			if(republished){
				checkBox_actionsrepublished().click();
				logInfo("checked republished");
			}
			if(printed){
				checkBox_actionsprinted().click();
				logInfo("checked printed");
			}
			if(deletedAdmin){
				checkBox_actionsdeleted().click();
				logInfo("checked deleted(admin)");
			}
			if(deletedUser){
				checkBox_actionsenduserdeleted().click();
				logInfo("checked deleted(suer)");
			}
			if(commented){
				checkBox_actionscommented().click();
				logInfo("checked commented");
			}
			if(flagged){
				checkBox_actionsflagged().click();
				logInfo("checked flagged");
			}
			if(migrated){
				checkBox_actionsmigrated().click();
				logInfo("checked migrated");
			}
			if(retained){
				checkBox_actionsretained().click();
				logInfo("checked retained");
			}
			if(sentForPrinting){
				checkBox_actionssentforprintin().click();
				logInfo("checked sentForPrinting");
			}
			if(sentTo){
				checkBox_actionssentto().click();
				logInfo("checked sentTo");
			}
			if(stubbed){
				checkBox_actionsstubbed().click();
				logInfo("checked stubbed");
			}
		}
		

		
		if(auditor!= null && !auditor.isEmpty()){
			checkBox_ifAuditoron().click();
			sleep(2);
			text_auditor().unregister();
			text_auditor().click();
			logInfo("Checked and Clicked on auditor");
			browser_htmlBrowser().inputChars(auditor);
			logInfo("input < "+ auditor +" >");
		}
		
		
		if(comment !=null && !comment.isEmpty()){
			checkBox_ifCommenton().click();
			sleep(2);
			TestObject[] commentInput = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.text", "name", "comment"));
			((GuiTestObject)commentInput[0]).click();
			logInfo("Checked and Clicked on comment");
			browser_htmlBrowser().inputChars(comment);
			logInfo("input < "+ comment +" >");
		}
		
		if(checkTags){
			checkBox_ifTagson().click();
			if(relevant){
				checkBox_tagsRelevant().click();
				logInfo("checked relevant");
			}
			
			if(privileged){
				checkBox_tagsPrivilege().click();
				logInfo("checked privileged");
			}
			
			if(tagFlagged){
				checkBox_tagsFlagged().click();
				logInfo("checked tagFlagged");
			}
			
			if(workProduct){
				checkBox_tagsWorkProduct().click();
				logInfo("checked workProduct");
			}
		}
	}

	
	public void validateResults(){
		TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);	
		
		for(int i = 0; i < results.length; i++){
			((GuiTestObject)results[i]).hover();
			((GuiTestObject)results[i]).click();
			((GuiTestObject)results[i]).doubleClick();
			logInfo("open message: "+i);
			html_messageWindow0().waitForExistence(123, DISABLED);
			waitForloading();
			link_messageAuditTab().click();
			logInfo("Clicked Audit Tab");
			waitForloading();
			
			
			logInfo("Validating Results:"+i);
			
			//Action List
			ArrayList<Boolean> actionExists = new ArrayList<Boolean>();
			if(created){
				actionExists.add(messageHasAction("Created"));
			}
			if(viewed){
				actionExists.add(messageHasAction("Viewed"));
			}
			if(exported){
				actionExists.add(messageHasAction("Exported"));
			}
			if(forwarded){
				actionExists.add(messageHasAction("Forwarded"));
			}
			if(republished){
				actionExists.add(messageHasAction("Republished"));
			}
			if(printed){
				actionExists.add(messageHasAction("Printed"));
			}
			if(deletedAdmin){
				//TODO
			}
			if(deletedUser){
				//TODO
			}
			if(commented){
				actionExists.add(messageHasAction("Commented"));
			}
			if(flagged){
				actionExists.add(messageHasAction("Flagged"));
			}
			if(migrated){
				actionExists.add(messageHasAction("Migrated"));
			}
			if(retained){
				actionExists.add(messageHasAction("Retained"));
			}
			if(sentForPrinting){
				actionExists.add(messageHasAction("Sent for printing"));
			}
			if(sentTo){
				actionExists.add(messageHasAction("Sent To"));
			}
			if(stubbed){
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
			if(auditor != null && !auditor.isEmpty()){
				Property[] columnProperties = {	
						new Property(".class", "Html.TD"),
						new Property(".cellIndex", "2"),
						new Property(".text", new RegularExpression("(?i)"+auditor+".*", false))
				};
				TestObject[] messageBody = html_messageWindow0().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-body"),true);
				TestObject[] secondColumn = messageBody[0].find(atDescendant(columnProperties), false);
				logTestResult("Expected an Auditor by the name < "+auditor+" >", secondColumn.length>0);
			}
			
			//COMMENT aka note
			if(comment != null && !comment.isEmpty()){
				Property[] columnProperties = {	
						new Property(".class", "Html.TD"),
						new Property(".cellIndex", "3"),
						new Property(".text", new RegularExpression("(?i)"+comment+".*", false))
				};
				TestObject[] messageBody = html_messageWindow0().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-body"),true);
				TestObject[] secondColumn = messageBody[0].find(atDescendant(columnProperties), false);
				logTestResult("Expected a note with < "+comment+" >", secondColumn.length>0);
			}	
			
			
			
			//Tags VPs
			if(relevant | privileged| tagFlagged | tagFlagged | workProduct){
				HashMap<String, Boolean> tagResult = new HashMap<String, Boolean>();
				tagResult.put("relevant", null);
				tagResult.put("privileged", null);
				tagResult.put("tagFlagged", null);
				tagResult.put("workProduct", null);
				
				if(relevant){
					tagResult.put("relevant", messageHasTag("relevant", new RegularExpression("(?i)Relevant--.*", false)));
				}
				
				if(privileged){
					tagResult.put("privileged", messageHasTag("privileged", new RegularExpression("(?i)Privileged--.*", false)));
				}
				
				if(tagFlagged){
					tagResult.put("tagFlagged", messageHasTag("tagFlagged", new RegularExpression("(?i)Flagged--.*", false)));
				}
				
				if(workProduct){
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
							//Find atleast one of the tag
							tagTest = true;
							break;
						}
					}
				}

				logTestResult("Expected one the following tags to exists: "+tagsChecked, tagTest);
			}
			
			//CloseMessage
			html_messageClose().click();
			logInfo("closed message");
		}
	}
	
	private boolean messageHasAction(String actionName){
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
	
	private boolean messageHasTag(String tag, RegularExpression tagRegexp){
		Property[] columnProperties = {	
				new Property(".class", "Html.TD"),
				new Property(".cellIndex", "3"),
				new Property(".text", tagRegexp)
		};
		TestObject[] messageBody = html_messageWindow0().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-body"),true);
		TestObject[] secondColumn = messageBody[0].find(atDescendant(columnProperties), false);
		return secondColumn.length>0;
	}

	public void search(){
		button_searchsubmit().click();
		logInfo("Clicked search");
		waitForloading();
		waitForloading();//just in case

		

	}
	
	public void clear(){
		TestObject[] emptycheckBoxes = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.checkbox", "CHECKED", "true"), true);
		TestObject[] emptyInputBoxes = html_advancedSearchWindow().find(atDescendant(".class", "Html.INPUT.text", ".value", ""), true);
		
		button_clearsubmit().click();
		logInfo("Clicked clear search");
		vpManual("Clear_checkboxes_empty", 0, emptycheckBoxes.length).performTest();
		vpManual("Clear_inputField_empty", 2, emptyInputBoxes.length).performTest();
	}
	
	public void cancel(){
		button_cancelsubmit().click();
		logInfo("Clicked cancel");
	}
	/************ SETTER ***************/
	public void setCreated(boolean created) {
		this.created = created;
	}


	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}


	public void setExported(boolean exported) {
		this.exported = exported;
	}


	public void setForwarded(boolean forwarded) {
		this.forwarded = forwarded;
	}


	public void setRepublished(boolean republished) {
		this.republished = republished;
	}


	public void setPrinted(boolean printed) {
		this.printed = printed;
	}


	public void setDeletedAdmin(boolean deletedAdmin) {
		this.deletedAdmin = deletedAdmin;
	}


	public void setDeletedUser(boolean deletedUser) {
		this.deletedUser = deletedUser;
	}


	public void setCommented(boolean commented) {
		this.commented = commented;
	}


	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}


	public void setMigrated(boolean migrated) {
		this.migrated = migrated;
	}


	public void setRetained(boolean retained) {
		this.retained = retained;
	}


	public void setSentForPrinting(boolean sentForPrinting) {
		this.sentForPrinting = sentForPrinting;
	}


	public void setSentTo(boolean sentTo) {
		this.sentTo = sentTo;
	}


	public void setStubbed(boolean stubbed) {
		this.stubbed = stubbed;
	}


	public void setRelevant(boolean relevant) {
		this.relevant = relevant;
	}


	public void setPrivileged(boolean privileged) {
		this.privileged = privileged;
	}


	public void setTagFlagged(boolean tagFlagged) {
		this.tagFlagged = tagFlagged;
	}


	public void setWorkProduct(boolean workProduct) {
		this.workProduct = workProduct;
	}


	public void setSearch(boolean search) {
		this.search = search;
	}


	public void setClear(boolean clear) {
		this.clear = clear;
	}


	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}


	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
}

