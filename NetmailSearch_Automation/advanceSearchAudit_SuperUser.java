
import java.util.HashMap;

import resources.advanceSearchAudit_SuperUserHelper;
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
public class advanceSearchAudit_SuperUser extends advanceSearchAudit_SuperUserHelper
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
}

