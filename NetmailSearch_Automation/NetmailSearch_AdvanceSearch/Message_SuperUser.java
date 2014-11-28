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

import resources.NetmailSearch_AdvanceSearch.Message_SuperUserHelper;
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
public class Message_SuperUser extends Message_SuperUserHelper
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
//					category = "";
	
	private String 	subject = "", 
					sender = "", 
					recipient  = "", 
					body = "", 
					sentDate1 = "", 
					sentDate2 = "", 
					rcvDate1 = "", 
					rcvDate2 = "",  
					personal = "", 
					category = "";

	private Boolean typeMail = false, 
					typeAppointment = false,
					typeTask = false,			
					typeNote = false,
					searchEmbedded = false,
					invalidRcvDateRange = false,
					invalidSentDateRange = false,
					expectedResults = true,
					checkBox_Types_Checked = typeMail|typeAppointment|typeTask|typeNote,
					performVerification = true;

	
	public void testMain(Object[] args) 
	{
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
				performVerification = booleanQuery.get("performVerification")== null	? true 	: booleanQuery.get("performVerification");
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
								searchEmbedded			;

		if( performSearch ){
			//Open AdvanceSearch Messages
			clearSearchResult();
			waitForloading();
			openAdvanceSearchMessage();
			link_advanceSearch_MessageTab().click();
			
			//Input Advance Message
			inputAdvanceMessage();
			clearSearch();
			inputAdvanceMessage();
			button_searchsubmit().click();
			logInfo("Clicked search");
			sleep(6);
			waitForloading();
	
			
			
		//Validation Steps
			if(performVerification){
				TestObject[] results = html_activeTabBody().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
				if(!expectedResults && results.length > 0){
					//No Result Case
					logError("Expected no result from query");
					return;
				}else if(expectedResults && results.length > 0 && !(invalidRcvDateRange|invalidSentDateRange)){
						logInfo("Start validating the results");
						//Validate Queries
						int msgNum = 1;
						for(TestObject result : results){
							String msg = "Msg"+msgNum+": Checking if %s contain the word < %s > in the string < %s >";
							String failMsg = "Msg"+msgNum+": %s field does not contain the word of interest";
							String successMsg = "Msg"+msgNum+": %s field contains the word of interest";
							
							//TODO TYPE APPOINTMENT
							//TODO TYPE TASK
							//TODO TYPE NOTE
							//TYPE MAIL: If is an open message or new mail check if that query specified for message type mail.
							if(((GuiTestObject)result).compare( messageRow_IE_OPENEMAILVP()) | ((GuiTestObject)result).compare( messageRow_IE_NEWMAILVP())){
								if(checkBox_Types_Checked){
									if(!typeMail){
										logError("Results return message(s) of type mail when not specified too");
									}
									
								}
							}
			
							
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
									logError(String.format(failMsg, "Subject"));
								}else{
									logInfo(String.format(successMsg, "Subject"));
								}
							}
							
							//From|Sender VF
							if(!sender.isEmpty()){
								String resultString = text_mbFrom().getProperty(".value").toString();
								logInfo(String.format(msg, "FROM", sender, resultString));
								if(!resultString.toLowerCase().contains(sender.toLowerCase())){
									logError(String.format(failMsg, "FROM"));
								}else{
									logInfo(String.format(successMsg, "FROM"));
								}
							}
							
							//To|Recipient VF
							if(!recipient.isEmpty()){
								String resultString = text_mbTo().getProperty(".value").toString();
								logInfo(String.format(msg, "TO", recipient, resultString));
								if(!resultString.toLowerCase().contains(recipient.toLowerCase())){
									logError(String.format(failMsg, "TO"));
								}else{
									logInfo(String.format(successMsg, "TO"));
								}
								
							}
							
							//Body message VF
							if(!body.isEmpty()){
								String resultString = document_messageBody().getProperty(".text").toString();
								logInfo(String.format(msg, "MessageBody", body, resultString));
								if(!resultString.toLowerCase().contains(body.toLowerCase())){
									logError(String.format(failMsg, "Message Body"));
								}else{
									logInfo(String.format(successMsg, "Message Body"));
								}			
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
									logTestResult("Message_date_is_after_the_starting_received_date_contraint", messageDateObject.compareTo(receiveDateObject)>=0);
									logTestResult("Message_date_is_before_the_ending_receive_date_contraint", messageDateObject.compareTo(receive2DateObject)<=0);
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
									logTestResult("Message_date_is_after_the_beginnging_send_date_contraint", messageDateObject.compareTo(sentDateObject)>=0);
									logTestResult("Message_date_is_before_the_ending_send_date_contraint", messageDateObject.compareTo(sentDate2Object)<=0);
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
									logError(String.format(failMsg, "Personal Subject"));
								}else{
									logInfo(String.format(successMsg, "Pesonal Subject"));
								}
							}
								
							//Close Message box
							html_mbCloseButtonDiv().click();
							msgNum++;
							sleep(2);
						}
				}else if(expectedResults && results.length <= 0 && !(invalidRcvDateRange|invalidSentDateRange)){
					logError("Expected result but none returned");
					return;
				}
			}
		}
		
	}
	
	
	public void inputAdvanceMessage(){
		link_advanceSearch_MessageTab().click();
		
		String msg = "Entering < %s > in the %s input field";
		if(!subject.isEmpty()){
			logInfo(String.format(msg, subject, "Subject"));
			checkBox_ifSubjecton().click();
			text_subject().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(subject);
		}
		
		if(!sender.isEmpty()){
			logInfo(String.format(msg, sender, "Sender"));
			checkBox_ifFromon().click();
			text_from().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(sender);
		}
		
		if(!recipient.isEmpty()){
			logInfo(String.format(msg, recipient, "Recipient"));
			checkBox_ifRecipienton().click();
			text_recipient().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(recipient);
		}
		
		if(!body.isEmpty()){
			logInfo(String.format(msg, body, "Body"));
			checkBox_ifMessageon().click();
			text_message().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(body);
		}
		
		if(!sentDate1.isEmpty() && !sentDate2.isEmpty()){
			logInfo("Input the range of sent date from "+sentDate1+" TO "+sentDate2);
			checkBox_ifSentDateon().click();
			
			text_sentDate1().hover();
			text_sentDate1().doubleClick();
			sleep(1);
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(sentDate1);
			
			text_sentDate2().doubleClick();
			sleep(1);
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(sentDate2);
			
			if(invalidSentDateRange){
				logInfo("Validating Error message is displayed for invalid sent date value(s)");
				vpManual("InvalidSendDateFieldExists", true, text_sentDate1().compare( mbInvalidSentDate1VP() ) | text_sentDate2().performTest( mbInvalidSendDate2VP() ) ).performTest();
				if(text_sentDate1().compare( mbInvalidSentDate1VP() ) ){
					logInfo("Verifying visibility of tooltip on send date 1 invalid field");
					text_sentDate1().hover();
					sleep(2);
					html_toolTip().performTest( visibleToolTipVP());
				}
				if(text_sentDate2().compare( mbInvalidSendDate2VP() ) ){
					logInfo("Verifying visibility of tooltip on sent date 2 invalid field");
					text_sentDate2().hover();
					sleep(2);
					html_toolTip().performTest( visibleToolTipVP());
				}
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
			text_rcvDate1().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(rcvDate1);
			text_rcvDate2().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(rcvDate2);
			if(invalidRcvDateRange){
				logInfo("Validating Error message is displayed for invalid receive date value(s)");
				vpManual("InvalidSendDateFieldExists", true, text_rcvDate1().compare( mbInvalidSentDate1VP() ) | text_rcvDate2().performTest( InvalidRcvDate2VP() ) ).performTest();
				
				if(text_rcvDate1().compare( InvalidRcvDate1VP() ) ){
					logInfo("Verifying visibility of tooltip on send date 1 invalid field");
					text_rcvDate1().hover();
					sleep(3);
					html_toolTip().performTest( visibleToolTipVP());
				}
				if(text_rcvDate2().compare( InvalidRcvDate2VP() ) ){
					logInfo("Verifying visibility of tooltip on sent date 2 invalid field");
					text_rcvDate2().hover();
					sleep(3);
					html_toolTip().performTest( visibleToolTipVP());
				}
				
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
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(personal);
		}
		
		if(!category.isEmpty()){
			logInfo(String.format(msg, category, "Category"));
			checkBox_ifCategoryon().click();
			text_category().click();
			browser_htmlBrowser().inputKeys("^a{DELETE}");
			browser_htmlBrowser().inputChars(category);
		}
		
	}
	
	public void validateSearchResult(){	
		//Validation Steps
			if(performVerification){
				TestObject[] results = html_activeTabBody().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
				if(!expectedResults && results.length > 0){
					//No Result Case
					logError("Expected no result from query");
					return;
				}else if(expectedResults && results.length > 0 && !(invalidRcvDateRange|invalidSentDateRange)){
						logInfo("Start validating the results");
						//Validate Queries
						int msgNum = 1;
						for(TestObject result : results){
							String msg = "Msg"+msgNum+": Checking if %s contain the word < %s > in the string < %s >";
							String failMsg = "Msg"+msgNum+": %s field does not contain the word of interest";
							String successMsg = "Msg"+msgNum+": %s field contains the word of interest";
							
							//TODO TYPE APPOINTMENT
							//TODO TYPE TASK
							//TODO TYPE NOTE
							//TYPE MAIL: If is an open message or new mail check if that query specified for message type mail.
							if(((GuiTestObject)result).compare( messageRow_IE_OPENEMAILVP()) | ((GuiTestObject)result).compare( messageRow_IE_NEWMAILVP())){
								if(checkBox_Types_Checked){
									if(!typeMail){
										logError("Results return message(s) of type mail when not specified too");
									}
									
								}
							}
			
							
							//Open Message
							((GuiTestObject)result).hover();
							((GuiTestObject)result).doubleClick();
							sleep(2);
							waitForloading();
							
							//Subject VF
							if(!subject.isEmpty()){
								String resultString = text_mbSubject().getProperty(".value").toString();
								logInfo(String.format(msg, "Subject", subject, resultString));
								if(!resultString.toLowerCase().contains(subject.toLowerCase())){
									logError(String.format(failMsg, "Subject"));
								}else{
									logInfo(String.format(successMsg, "Subject"));
								}
							}
							
							//From|Sender VF
							if(!sender.isEmpty()){
								String resultString = text_mbFrom().getProperty(".value").toString();
								logInfo(String.format(msg, "FROM", sender, resultString));
								if(!resultString.toLowerCase().contains(sender.toLowerCase())){
									logError(String.format(failMsg, "FROM"));
								}else{
									logInfo(String.format(successMsg, "FROM"));
								}
							}
							
							//To|Recipient VF
							if(!recipient.isEmpty()){
								String resultString = text_mbTo().getProperty(".value").toString();
								logInfo(String.format(msg, "TO", recipient, resultString));
								if(!resultString.toLowerCase().contains(recipient.toLowerCase())){
									logError(String.format(failMsg, "TO"));
								}else{
									logInfo(String.format(successMsg, "TO"));
								}
								
							}
							
							//Body message VF
							if(!body.isEmpty()){
								String resultString = document_messageBody().getProperty(".text").toString();
								logInfo(String.format(msg, "MessageBody", body, resultString));
								if(!resultString.toLowerCase().contains(body.toLowerCase())){
									logError(String.format(failMsg, "Message Body"));
								}else{
									logInfo(String.format(successMsg, "Message Body"));
								}			
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
									logTestResult("Message_date_is_after_the_starting_received_date_contraint", messageDateObject.compareTo(receiveDateObject)>=0);
									logTestResult("Message_date_is_before_the_ending_receive_date_contraint", messageDateObject.compareTo(receive2DateObject)<=0);
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
									logTestResult("Message_date_is_after_the_beginnging_send_date_contraint", messageDateObject.compareTo(sentDateObject)>=0);
									logTestResult("Message_date_is_before_the_ending_send_date_contraint", messageDateObject.compareTo(sentDate2Object)<=0);
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
									logError(String.format(failMsg, "Personal Subject"));
								}else{
									logInfo(String.format(successMsg, "Pesonal Subject"));
								}
							}
								
							//Close Message box
							html_mbCloseButtonDiv().click();
							msgNum++;
							sleep(2);
						}
				}else if(expectedResults && results.length <= 0 && !(invalidRcvDateRange|invalidSentDateRange)){
					logError("Expected result but none returned");
					return;
				}
			}
		
	}

	public void search(){
		button_searchsubmit().click();
		logInfo("Clicked search");
		waitForloading();
	}
	
	public void clearSearchResult(){
		//Open AdvanceSearch Messages
		button_advancedSearchbutton().click();
		logInfo("Click advance search dropdown");
		link_clearSearch().click();
		logInfo("Click clear search in dropdown");
		sleep(8);
	}
	
	public void openAdvanceSearchMessage(){
		button_advancedSearchbutton().click();
		logInfo(" click advance search dropdown");
		link_messages().click();	
		logInfo("open message advance search from dropdown");
		sleep(1);
	}
	
	public void closeAdvanceSearchMessage(){
		html_closeAdvanceSearch().click();
		sleep(1);
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
					logError("Advance Search Clear button search did not clear all inputs", getRootTestObject().getScreenSnapshot() );
					break;
				}
			}
			for(Boolean isTrue : noValue){
				if(!isTrue){
					logError("Advance Window Clear button search did not clear all inputs", getRootTestObject().getScreenSnapshot() );
					break;
				}
			}
			button_clearsubmit().click();
			sleep(3);
			return;
	}

	
	/********************** SETTERS *************************/
	public void setSubject(String subject) {
		this.subject = subject == null ? "" : subject;
	}


	public void setSender(String sender) {
		this.sender = sender == null ? "" : sender;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient == null ? "" :recipient;
	}


	public void setBody(String body) {
		this.body = body == null ? "" : body;
	}


	public void setSentDate1(String sentDate1) {
		this.sentDate1 = sentDate1 == null ? "" : sentDate1;
	}


	public void setSentDate2(String sentDate2) {
		this.sentDate2 = sentDate2 == null ? "" : sentDate2;
	}


	public void setRcvDate1(String rcvDate1) {
		this.rcvDate1 = rcvDate1 == null ? "" : rcvDate1;
	}


	public void setRcvDate2(String rcvDate2) {
		this.rcvDate2 = rcvDate2 == null ? "" : rcvDate2;
	}


	public void setPersonal(String personal) {
		this.personal = personal == null ? "" : personal;
	}


	public void setCategory(String category) {
		this.category = category == null ? "" : category;
	}


	public void setTypeMail(Boolean typeMail) {
		this.typeMail = typeMail == null ? false : typeMail;
	}


	public void setTypeAppointment(Boolean typeAppointment) {
		this.typeAppointment = typeAppointment == null ? false : typeAppointment;
	}


	public void setTypeTask(Boolean typeTask) {
		this.typeTask = typeTask == null ? false : typeTask;
	}


	public void setTypeNote(Boolean typeNote) {
		this.typeNote = typeNote == null ? false : typeNote;
	}


	public void setSearchEmbedded(Boolean searchEmbedded) {
		this.searchEmbedded = searchEmbedded == null ? false : searchEmbedded;
	}


	public void setInvalidRcvDateRange(Boolean invalidRcvDateRange) {
		this.invalidRcvDateRange = invalidRcvDateRange == null ? false : invalidRcvDateRange;
	}


	public void setInvalidSentDateRange(Boolean invalidSentDateRange) {
		this.invalidSentDateRange = invalidSentDateRange == null ? false :invalidSentDateRange;
	}


	public void setExpectedResults(Boolean expectedResults) {
		this.expectedResults = expectedResults == null ? true : expectedResults;
	}


	public void setCheckBox_Types_Checked(Boolean checkBox_Types_Checked) {
		this.checkBox_Types_Checked = checkBox_Types_Checked == null ? false : checkBox_Types_Checked;
	}


	public void setPerformVerification(Boolean performVerification) {
		this.performVerification = performVerification == null ? true : performVerification;
	}
}

