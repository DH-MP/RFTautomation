package NetmailSearch_AdvanceSearch;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Matcher.*;

import resources.NetmailSearch_AdvanceSearch.Message_NormalUserHelper;
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
public class Message_NormalUser extends Message_NormalUserHelper
{
	/**
	 * Script Name   : <b>advanceSearch</b>
	 * Generated     : <b>Jun 6, 2013 9:58:53 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/06
	 * @author Administrator
	 * Due to the hierarchy of the site being different in normal user login,
	 * will NOT work with admin advance search.
	 */
//	private String 	subject = "BCC", 
//					sender = "Amy", 
//					recipient  = "Admin", 
//					body = "test", 
//					sentDate1 = "05/05/13", 
//					sentDate2 = "09/05/13", 
//					rcvDate1 = "05/05/13", 
//					rcvDate2 = "09/05/13",  
//					personal = "test", 
//					category = null;
	
	private String 	subject = null, 
					sender = null, 
					recipient  = null, 
					body = null, 
					sentDate1 = null, 
					sentDate2 = null, 
					rcvDate1 = null, 
					rcvDate2 = null,  
					personal = "test", 
					category = null;

	private Boolean typeMail = false, 
					typeAppointment = false,
					typeTask = false,			
					typeNote = false,
					searchEmbedded = true,
					invalidRcvDateRange = false,
					invalidSentDateRange = false,
					expectedResults = true,
					checkBox_Types_Checked = typeMail|typeAppointment|typeTask|typeNote;

	
	public void testMain(Object[] args) 
	{
		boolean skipVerification = false;
		if(args.length > 0){		
					@SuppressWarnings("unchecked")
					HashMap<String, String> query = (HashMap<String, String>) args[0];
					@SuppressWarnings("unchecked")
					HashMap<String, Boolean> booleanQuery = (HashMap<String, Boolean>) args[1];
					subject 	= query.get("subject")	!= null ? query.get("subject") 	: ""; 
					sender 		= query.get("sender") 	!= null ? query.get("sender") 	: ""; 
					recipient 	= query.get("recipient")!= null ? query.get("recipient"): ""; 
					body 		= query.get("body")		!= null ? query.get("body") 	: ""; 
					sentDate1 	= query.get("sendDate1")!= null ? query.get("sendDate1"): ""; 
					sentDate2 	= query.get("sendDate2")!= null ? query.get("sendDate2"): ""; 
					rcvDate1 	= query.get("rcvDate1")	!= null ? query.get("rcvDate1") : ""; 
					rcvDate2 	= query.get("rcvDate2")	!= null ? query.get("rcvDate2") : "";  
					personal 	= query.get("personal")	!= null ? query.get("personal") : "";
					category 	= query.get("category")	!= null ? query.get("category") : "";
					
					typeMail 			= booleanQuery.get("typeMail") == null 				? false : booleanQuery.get("typeMail"); 
					typeAppointment 	= booleanQuery.get("typeAppointment")== null 		? false : booleanQuery.get("typeAppointment");
					typeTask 			= booleanQuery.get("typeTask")== null 				? false : booleanQuery.get("typeTask");			
					typeNote 			= booleanQuery.get("typeNote")== null 				? false : booleanQuery.get("typeNote");
					searchEmbedded 		= booleanQuery.get("searchEmbedded")== null 		? false : booleanQuery.get("searchEmbedded");
					invalidRcvDateRange = booleanQuery.get("invalidRcvDateRange")== null	? false : booleanQuery.get("invalidRcvDateRange");
					invalidSentDateRange= booleanQuery.get("invalidSentDateRange")== null 	? false : booleanQuery.get("invalidSentDateRange");
					expectedResults 	= booleanQuery.get("expectedResults")== null 		? false : booleanQuery.get("expectedResults");
					skipVerification    = booleanQuery.get("skipVerification")== null       ? false : booleanQuery.get("skipVerification");
		}
		boolean performSearch = !subject.isEmpty()		| 
								!sender.isEmpty()		| 
								!recipient.isEmpty()	| 
								!body.isEmpty()			| 
								!sentDate1.isEmpty()	| 
								!sentDate2.isEmpty()	| 
								!rcvDate1.isEmpty()		|
								!rcvDate2.isEmpty()		|
								!personal.isEmpty()		|
								!category.toString().isEmpty()		|
								checkBox_Types_Checked 	|
								searchEmbedded			|
								invalidRcvDateRange    	|
								invalidSentDateRange   	;

		if( performSearch ){
			//Open AdvanceSearch Messages
			button_advancedSearchbutton().hover();
			button_advancedSearchbutton().click();
			logInfo("Click advance search dropdown");
			sleep(1);
			link_clearSearch().click();
			logInfo("Click clear search in dropdown");
			sleep(2);
			waitForloading();
			button_advancedSearchbutton().click();
			logInfo("Click advance search dropdown");
			link_messages().click();	
			logInfo("Open message advance search from dropdown");
			
			//Enter Advance Critera 
			enterAdvanceCriteria();
			clearSearch();
			sleep(1);
			enterAdvanceCriteria();
			button_searchsubmit().click();
			logInfo("clicked search");
			sleep(10);
			waitForloading();
	
			if(skipVerification){
				return;
			}
			//Validation Steps
			TestObject visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
			TestObject[] results = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			System.out.println(results.length);
			if(!expectedResults && results.length > 0){
				//No Result Case
				logError("Expected no result from query");
				return;
			}else if(expectedResults && results.length > 0){
					logInfo("Start validating the results");
					//Validate Queries
					int msgNum = 1;
					for(TestObject result : results){
						String msg = "Msg"+msgNum+": Checking if %s contain the word < %s > in the string < %s >";
						String msg2 = "Msg"+msgNum+": %s field does not contain the word of interest";
						
						//TODO TYPE APPOINTMENT
						//TODO TYPE TASK
						//TODO TYPE NOTE
						//TYPE MAIL: If is an open message or new mail check if that query specified for message type mail.
//						if(((GuiTestObject)result).compare( messageRow_IE_OPENEMAILVP()) | ((GuiTestObject)result).compare( messageRow_IE_NEWMAILVP())){
//							if(checkBox_Types_Checked){
//								if(!typeMail){
//									logError("Results return message(s) of type mail when not specified too");
//								}
//								
//							}
//						}
		
						//Open Message
						((GuiTestObject)result).hover();
						((GuiTestObject)result).click();
						((GuiTestObject)result).doubleClick();
						sleep(4);
						waitForloading();
						
						//Just in case it doesn't open in first try
						try{
							text_mbSubject().getProperty(".value").toString();
						}catch(ObjectNotFoundException e){
							((GuiTestObject)result).hover();
							((GuiTestObject)result).click();
							((GuiTestObject)result).doubleClick();
							sleep(2);
							waitForloading();
						}
						
						//Subject VF
						if(!subject.isEmpty()){
							String resultString = text_mbSubject().getProperty(".value").toString();
							logInfo(String.format(msg, "Subject", subject, resultString));
							if(!resultString.toLowerCase().contains(subject.toLowerCase())){
								logError(String.format(msg2, "Subject"));
							};
						}
						
						//From|Sender VF
						if(!sender.isEmpty()){
							String resultString = text_mbFrom().getProperty(".value").toString();
							logInfo(String.format(msg, "FROM", sender, resultString));
							if(!resultString.toLowerCase().contains(sender.toLowerCase())){
								logError(String.format(msg2, "FROM"));
							};
						}
						
						//To|Recipient VF
						if(!recipient.isEmpty()){
							String resultString = text_mbTo().getProperty(".value").toString();
							logInfo(String.format(msg, "TO", recipient, resultString));
							if(!resultString.toLowerCase().contains(recipient.toLowerCase())){
								logError(String.format(msg2, "TO"));
							};
							
						}
						
						//Body message VF
						if(!body.isEmpty()){
							String resultString = document_messageBody().getProperty(".text").toString();
							logInfo(String.format(msg, "MessageBody", body, resultString));
							if(!resultString.toLowerCase().contains(body.toLowerCase())){
								logError(String.format(msg2, "Message Body"));
							};			
						}
						
						//Receive date verification
						if(!rcvDate1.isEmpty() && !rcvDate2.isEmpty() && !invalidRcvDateRange){
							logInfo("Checking if message's receive date respects the range: "+ rcvDate1 +" To "+ rcvDate2);
							String messageDate = text_mbSentReceivedDate().getProperty(".value").toString();
			
							DateFormat formatter = new SimpleDateFormat("M/d/yy");
							try {
								java.util.Date messageDateObject = formatter.parse(messageDate);
								java.util.Date receiveDateObject = formatter.parse(rcvDate1);
								java.util.Date receive2DateObject = formatter.parse(rcvDate2);
								vpManual("MSGDate_within_ReceiveDateConstraint", true ,messageDateObject.compareTo(receiveDateObject)>=0 && messageDateObject.compareTo(receive2DateObject)<=0).performTest();
							} catch (ParseException e) {
								e.printStackTrace();
							}
			
							
							
						}
						
						//Sent date verification
						if(!sentDate1.isEmpty() && !sentDate2.isEmpty() && !invalidSentDateRange){
							logInfo("Checking if message's send date respects the constraint: "+ sentDate1 +" To "+ sentDate2);
							String messageDate = text_mbSentReceivedDate().getProperty(".value").toString();
			
							DateFormat formatter = new SimpleDateFormat("M/d/yy");
							try {
								java.util.Date messageDateObject = formatter.parse(messageDate);
								java.util.Date sentDateObject = formatter.parse(sentDate1);
								java.util.Date sentDate2Object = formatter.parse(sentDate2);
								vpManual("MSGDate_within_SentDateConstraint", true, messageDateObject.compareTo(sentDateObject)>=0 && messageDateObject.compareTo(sentDate2Object)<=0).performTest();
							} catch (ParseException e) {
								e.printStackTrace();
							}	
						}				
						
						//TODO Category VP
						//Personal Subject VF
						if(!personal.isEmpty()){
							link_mbPropertyTab().click();
							TestObject[] property = html_mbPropertiesUL().find(atDescendant(".class", "Html.A", ".text", "Personal Subject"), true);
							
							//Get the DIV surrounding the whole row containing that property and find the only unmappable child by rft with that class
							TestObject[] propertyValue = property[0].getParent().getParent().find(atDescendant(".class", "Html.DIV", "class", "x-tree-col-text"), false);
							boolean contains = propertyValue[0].getProperty("title").toString().toLowerCase().contains(personal.toLowerCase());
							if(!contains){
								logError(String.format(msg2, "Personal Subject"));
							};
						}
							
						//Close Message box
						html_mbCloseButtonDiv().click();
						msgNum++;
						sleep(2);
					}
			}
			else if(!expectedResults && results.length == 0){
				logTestResult("Expected no Results", true);
			}else{
				logError("Expected result but none returned");
				return;
			}
		}	
	}
	
	
	private void enterAdvanceCriteria(){
		String msg = "Entering < %s > in the %s input field";
		if(!subject.isEmpty()){
			logInfo(String.format(msg, subject, "Subject"));
			checkBox_ifSubjecton().click();
			text_subject().click();
			browser_htmlBrowser().inputChars(subject);
		}
		
		if(!sender.isEmpty()){
			logInfo(String.format(msg, sender, "Sender"));
			checkBox_ifFromon().click();
			text_from().click();
			browser_htmlBrowser().inputChars(sender);
		}
		
		if(!recipient.isEmpty()){
			logInfo(String.format(msg, recipient, "Recipient"));
			checkBox_ifRecipienton().click();
			text_recipient().click();
			browser_htmlBrowser().inputChars(recipient);
		}
		
		if(!body.isEmpty()){
			logInfo(String.format(msg, body, "Body"));
			checkBox_ifMessageon().click();
			text_message().click();
			browser_htmlBrowser().inputChars(body);
		}
		
		if(!sentDate1.isEmpty() && !sentDate2.isEmpty()){
			logInfo("Input the range of sent date from "+sentDate1+" TO "+sentDate2);
			checkBox_ifSentDateon().click();
			text_sentDate1().hover();
			text_sentDate1().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(sentDate1);
			text_sentDate2().hover();
			text_sentDate2().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(sentDate2);
			if(invalidSentDateRange){
				logInfo("Validating Error message is displayed for invalid sent date value(s)");
				button_searchsubmit().click();
				sleep(1);
				vpManual("SentErrorMessage", true, html_invalidFormatRCV().ensureObjectIsVisible()).performTest();
				button_errorOkayButton().click();
				checkBox_ifSentDateon().click();
			}
		}
		
		if(!rcvDate1.isEmpty() && !rcvDate2.isEmpty()){
			logInfo("Input the range of receive date from "+rcvDate1+" TO "+rcvDate2);
			checkBox_ifRcvDateon().click();
			text_rcvDate1().hover();
			text_rcvDate1().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(rcvDate1);
			text_rcvDate2().hover();
			text_rcvDate2().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(rcvDate2);
			if(invalidRcvDateRange){
				logInfo("Validating Error message is displayed for invalid receive date value(s)");
				button_searchsubmit().click();
				sleep(1);
				vpManual("RcvErrorMessage", true, html_invalidFormatRCV().ensureObjectIsVisible()).performTest();
				button_errorOkayButton().click();
				checkBox_ifRcvDateon().click();
			}
		}
		
		if(checkBox_Types_Checked ){
			checkBox_ifItemTypeson().click();
			if(typeMail){
				logInfo("Checking Item Type Mail");
				checkBox_itemTypesmail().click();
			}
			
			if(typeAppointment){
				logInfo("Checking Item Type Appointment");
				checkBox_itemTypesappointment().click();
			}
			
			if(typeTask){
				logInfo("Checking Item Type Task");
				checkBox_itemTypestask().click();
			}
			
			if(typeNote){
				logInfo("Checking Item Type Note");
				checkBox_itemTypesnote().click();
			}
		}
		
		
		if(searchEmbedded){
			logInfo("Checking Search Embedded");
			checkBox_searchEmbeddedMessage().click();
		}
		
		if(!personal.isEmpty()){
			logInfo(String.format(msg, personal, "Personal"));
			checkBox_ifpersonalsubjecton().click();
			text_personalsubject().click();
			browser_htmlBrowser().inputChars(personal);
		}
		
		if(!category.isEmpty()){
			logInfo(String.format(msg, category, "Category"));
			checkBox_ifCategoryon().click();
			text_category().click();
			browser_htmlBrowser().inputChars(category);
		}
		
	}
	
	private void clearSearch(){
			button_clearsubmit().click();
			logInfo("Clear search clicked in advance search window");
			sleep(1);
			boolean[] unChecked = {					
					Boolean.valueOf(checkBox_ifSubjecton().getProperty(".checked").toString()) ,
					Boolean.valueOf(checkBox_ifCategoryon().getProperty(".checked").toString()),
					Boolean.valueOf(checkBox_ifFromon().getProperty(".checked").toString()),
					Boolean.valueOf(checkBox_ifItemTypeson().getProperty(".checked").toString()),
					Boolean.valueOf(checkBox_ifMessageon().getProperty(".checked").toString()),
					Boolean.valueOf(checkBox_ifRcvDateon().getProperty(".checked").toString()),
					Boolean.valueOf(checkBox_ifRecipienton().getProperty(".checked").toString()),
					Boolean.valueOf(checkBox_ifSentDateon().getProperty(".checked").toString()),
					Boolean.valueOf(checkBox_ifpersonalsubjecton().getProperty(".checked").toString()),
			};
			checkBox_ifSubjecton().click();
			checkBox_ifCategoryon().click();
			checkBox_ifFromon().click();
			checkBox_ifItemTypeson().click();
			checkBox_ifMessageon().click();
			checkBox_ifRcvDateon().click();
			checkBox_ifRecipienton().click();
			checkBox_ifSentDateon().click();
			checkBox_ifpersonalsubjecton().click();
			boolean[] noValue = {
				!Boolean.valueOf(checkBox_itemTypesappointment().getProperty(".checked").toString()),
				!Boolean.valueOf(checkBox_itemTypesmail().getProperty(".checked").toString()),
				!Boolean.valueOf(checkBox_itemTypesnote().getProperty(".checked").toString()),
				!Boolean.valueOf(checkBox_itemTypestask().getProperty(".checked").toString()),
				!Boolean.valueOf(checkBox_searchEmbeddedMessage().getProperty(".checked").toString()),
				text_category().getProperty(".value").toString().trim().isEmpty(),
				text_from().getProperty(".value").toString().trim().isEmpty(),
				text_message().getProperty(".value").toString().trim().isEmpty(),
				text_personalsubject().getProperty(".value").toString().trim().isEmpty(),
				text_rcvDate1().getProperty(".value").toString().trim().isEmpty(),
				text_rcvDate2().getProperty(".value").toString().trim().isEmpty(),
				text_sentDate1().getProperty(".value").toString().trim().isEmpty(),
				text_sentDate2().getProperty(".value").toString().trim().isEmpty(),
				text_subject().getProperty(".value").toString().trim().isEmpty(),
			};
			for(Boolean isChecked : unChecked){
				if(isChecked){
					logError("Advance Window Clear search did not clear all inputs", getRootTestObject().getScreenSnapshot() );
					break;
				}
			}
			for(Boolean isTrue : noValue){
				if(!isTrue){
					logError("Advance Window Clear search did not clear all inputs", getRootTestObject().getScreenSnapshot() );
					break;
				}
			}
			button_clearsubmit().click();
			return;
	}
}

