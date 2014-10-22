package TC_483_LoginAsRegularUser;
import java.util.HashMap;
import java.util.regex.Matcher;

import resources.TC_483_LoginAsRegularUser.TS_981_NormalUserHelper;
import utilities.HelperClass;

import NetmailSearch_General.NetmailLogin;

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
public class TS_981_NormalUser extends TS_981_NormalUserHelper
{
	/**
	 * Script Name   : <b>TS_981_NormalUser</b>
	 * Generated     : <b>Jun 12, 2013 10:07:21 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/12
	 * @author Administrator
	 */
	private boolean performForwardingMessage = true;
	
	private String GWusername = "amy", GWpassword = "password";
	public void testMain(Object[] args) 
	{
		performForwardingMessage = dpBoolean("testForwardingMessage");
		
		//Login
		NetmailLogin.login(dpString("userName"), dpString("password"), dpBoolean("failToLogin"));
		sleep(5);
		if(dpBoolean("failToLogin"))
			return;
		
		//ALS MailBox Default
		Property[] ps = new Property[3];
		ps[0] = new Property(".class", "Html.A");
		ps[1] = new Property("class", "x-tree-node-anchor");
		ps[2] = new Property(".text", "ALS");
		GuiTestObject alsLink = (GuiTestObject) html_folderTree0().find(atDescendant(ps), true)[0];
		GuiTestObject expander = (GuiTestObject) alsLink.getParent().getChildren()[1];
		expander.click();
		sleep(3);	

		ps[0] = new Property(".class", "Html.A");
		ps[1] = new Property("class", "x-tree-node-anchor");
		ps[2] = new Property(".text", "Amy S");
		GuiTestObject AmySLink = (GuiTestObject) alsLink.getParent().getParent().find(atDescendant(ps), true)[0];
		expander = (GuiTestObject) AmySLink.getParent().getChildren()[1];
		expander.click();
		sleep(3);
		
		ps[0] = new Property(".class", "Html.A");
		ps[1] = new Property("class", "x-tree-node-anchor");
		ps[2] = new Property(".text", "Mailbox");
		GuiTestObject mailboxLink = (GuiTestObject) AmySLink.getParent().getParent().find(atDescendant(ps), true)[0];
		mailboxLink.click();
		
		
		//Current Message
		TestObject visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		TestObject[] messages = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		String[] messageText = new String[messages.length];
		for(int i = 0; i<messages.length; i++){
			messageText[i] = messages[i].getProperty(".contentText").toString();
		}
		
		//Advance Search
		HashMap<String, String> query = new HashMap<String, String> ();
		query.put("subject", dpString("subject")); 
		query.put("sender", dpString("sender")); 
		query.put("recipient", dpString("recipient"));
		query.put("body", dpString("body"));
		query.put("sendDate1", dpString("sendDate1")); 
		query.put("sendDate2",dpString("sendDate2"));
		query.put("rcvDate1", dpString("rcvDate1"));
		query.put("rcvDate2", dpString("rcvDate2"));  
		query.put("personal",dpString("personal")); 
		query.put("category",dpString("category"));
		
		HashMap<String, Boolean> booleanQuery = new HashMap<String, Boolean> ();
		booleanQuery.put("typeMail", dpBoolean("checkTypeMail")); 
		booleanQuery.put("typeAppointment", dpBoolean("checkTypeAppointment"));
		booleanQuery.put("typeTask", dpBoolean("checkTypeTask"));			
		booleanQuery.put("typeNote", dpBoolean("checkTypeNote"));
		booleanQuery.put("searchEmbedded",dpBoolean("checkSearchEmbedded"));
		booleanQuery.put("invalidRcvDateRange", dpBoolean("invalidRcvDateRange"));
		booleanQuery.put("invalidSentDateRange", dpBoolean("invalidSendDateRange"));
		booleanQuery.put("expectedResults", dpBoolean("expectedResults"));
		Object[] asmnu = {query, booleanQuery};
		callScript("NetmailSearch_AdvanceSearch.Message_NormalUser", asmnu);
		
		clearSearch();
		
		//Attachement Search
		if(!(dpString("filename").isEmpty() && dpString("fileSize1").isEmpty() && dpString("fileSize2").isEmpty())){
			Object[] asanu = {dpString("filename"), dpString("fileSize1"), dpString("fileSize2")};
			callScript("NetmailSearch_AdvanceSearch.Attachment_NormalUser", asanu);
		}

		
		//Validate clear search
		clearSearch();
		logInfo("clear search clicked under advance search dropdown");
		visibleResultContainer = HelperClass.getActiveTabBody()[0].find(atDescendant(".class", "Html.DIV", "class", new RegularExpression(".*x-panel x-panel-noborder x-grid-panel",false)))[0];
		TestObject[] messages2 = visibleResultContainer.find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		String[] messageText2 = new String[messages.length];
		for(int i = 0; i<messages2.length; i++){
			messageText2[i] = messages2[i].getProperty(".contentText").toString();
		}
		boolean clearSearch = true;
		if(messageText2.length == messageText.length){
			for(int i = 0; i<messageText2.length; i++){
				if(!messageText2[i].contentEquals(messageText[i])){
					clearSearch = false;
					logError("Message number "+i, getRootTestObject().getScreenSnapshot());
				}
			}
		}
		logTestResult("Advance Clear Search working", clearSearch);
		
		if(performForwardingMessage){
			//Forward Message Tests
//			TestMessageWithNoAttachment();
			//TODO: BROKEN missing messages NOT GROUPWISE
//			NetmailLogin.login(dpString("userName"), dpString("password"), dpBoolean("failToLogin"));
//			TestMessageWithAttachment();
//			NetmailLogin.login(dpString("userName"), dpString("password"), dpBoolean("failToLogin"));		
//			TestMessageWithNestedMessage();
		}
		
		unregisterAll();
	}
	
	
	private void clearSearch(){	
		sleep(1);
		button_advancedSearchbutton().click();
		link_clearSearch().click();
		sleep(3);
	}
	
	private String extractEmailOnly(String t){
		String expectedTo="";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<[A-Za-z0-9 _.]+@[A-Za-z0-9 _.]+(\\.com|\\.ca)>");
		Matcher matcher = pattern.matcher(t);
		while(matcher.find()) {
			expectedTo= ("; "+matcher.group(0)).replace("<", "").replace(">", "")+expectedTo; //prints /{item}/
		}
		return expectedTo.substring(1).trim();
	}
	
	
	private void TestMessageWithNoAttachment(){
		String 	messageName = "category : Urgent and Personal", 
				recipient = "S.Amy@sqa.com", 
				sender = "S.Amy@sqa.com",
				forwardMessage = "automation testing";
		
		//Navigate to Message
			
		//Find FIRST Message with that subject name
		text_quickSearchField0().click();
		browser_htmlBrowser().inputKeys(messageName+"{Enter}");
		sleep(1);
		waitForloading();
		findMessageAndOpen(messageName);
		sleep(2);
		text_mbFrom().waitForExistence(240, DISABLED);
		
		//The actual message Information
		String  MessageFromValue = text_mbFrom().getProperty(".value").toString(),
				MessageToValue = text_mbTO().getProperty(".value").toString(),
				MessageCCValue = text_mbCC().getProperty(".value").toString(),
				messageBodyValue = document_messageBody().getProperty(".text").toString();
		
		//Forward The Message
		forwardMessage(messageName, recipient, forwardMessage);
		sleep(2);
		
		//Log into Amy/Password in webaccess
		logInWebaccess(GWusername, GWpassword);
		
		//Find The message in webaccess
		TestObject theMessage = findMessageInWebAccess(messageName, false);
		if(theMessage != null){
			((GuiTestObject)theMessage).doubleClick();
			logInfo("open the message <"+messageName+"> in webaccess");
			sleep(2);
			TestObject[] rows = table_messageInformation().find(atDescendant(".tag","TR"), false);
			
			//ExtractInfo
			String 	FromValue = rows[1].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString(),
					ToValue = rows[2].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString(),
					SubjectValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			
			//Validate Message
			vpManual("FWD_Correct_Sender", "<"+sender+">", FromValue).performTest();
			vpManual("FWD_Correct_Recipient", recipient, ToValue).performTest();						
			String expectedSubject = ("Fwd: "+messageName).replace(" ", "").replace("", " ").trim();//
			vpManual("FWD_Correct_Subject", expectedSubject, SubjectValue).performTest();
			vpManual("CheckForwardedMessage", forwardMessage,document_gwMessageBody().getProperty(".text")).performTest();
			
			//Validate Actual Forwarded Message
			link_openThisMessage().click();
			logInfo("Open envelope");
			sleep(1);
			rows = table_messageInformation().find(atDescendant(".tag","TR"), false);
			FromValue = rows[1].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			ToValue = rows[2].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
//			String CCValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			SubjectValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			String messageBody = document_gwMessageBody().getProperty(".text").toString().trim();
			
			
			vpManual("Correct_From_Value", MessageFromValue.replace("\"", ""), FromValue).performTest();
			vpManual("Correct_Recipient_Value", extractEmailOnly(MessageToValue), ToValue).performTest();
//			vpManual("Correct_CC", extractEmailOnly(MessageCCValue), CCValue).performTest();
			expectedSubject = (messageName).replace(" ", "").replace("", " ").trim();
			vpManual("Correct_Subject", expectedSubject, SubjectValue).performTest();
			vpManual("CorrectOriginalMessage", messageBodyValue.trim(), messageBody).performTest();
		}
		html_close().click();
		logInfo("close message");
		sleep(0.5);
		html_gwDeleteMessage().click();
		logInfo("Delete message");
		sleep(2);
		if(html_close().exists()){
			html_close().click();
		}
		
	
	}
	
	
	private void TestMessageWithAttachment(){
		logInfo("Test Forwarded Message with Attachement");
		String 	messageName = "DHCP protocol with text attachment", 
				recipient = "S.Amy@sqa.com", 
				sender = "S.Amy@sqa.com",
				forwardMessage = "automation testing";;
		
		//Navigate to Message
		chooseDatabase();
		button_lastPageButton().click();
		sleep(1);
			
		//Find FIRST Message with that subject name	
		findMessageAndOpen(messageName);
		sleep(2);
		
		//The actual message Information
		TestObject[] mbInfoRows = form_extGen467().find(atDescendant(".tag", "DIV"));
		String  MessageFromValue = text_mbFrom().getProperty(".value").toString(),
				MessageToValue = text_mbTO().getProperty(".value").toString(),
				MessageCCValue = text_mbCC().getProperty(".value").toString(),
				messageBodyValue = document_messageBody().getProperty(".text").toString(),
				attachmentName = mbInfoRows[mbInfoRows.length-1].getProperty(".text").toString();
		
		//Forward The Message
		forwardMessage(messageName, recipient, forwardMessage);
		sleep(2);
		//Log into Amy/Password in webaccess
		logInWebaccess(GWusername, GWpassword);
		
		//Find The message in webaccess
		TestObject theMessage = findMessageInWebAccess(messageName, false);
		if(theMessage != null){
			((GuiTestObject)theMessage).doubleClick();
			logInfo("open the message <"+messageName+"> in webaccess");
			sleep(2);
			TestObject[] rows = table_messageInformation().find(atDescendant(".tag","TR"), false);
			
			//ExtractInfo
			String 	FromValue = rows[1].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString(),
					ToValue = rows[2].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString(),
					SubjectValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			
			//Validate Message
			vpManual("FWD_Correct_Sender", "<"+sender+">", FromValue).performTest();
			vpManual("FWD_Correct_Recipient", recipient, ToValue).performTest();						
			String expectedSubject = ("Fwd: "+messageName).replace(" ", "").replace("", " ").trim();//
			vpManual("FWD_Correct_Subject", expectedSubject, SubjectValue).performTest();
			vpManual("CheckForwardedMessage", forwardMessage,document_gwMessageBody().getProperty(".text")).performTest();
			
			//Validate Actual Forwarded Message
			link_openThisMessage().click();
			logInfo("Open Envelope");
			sleep(1);
			rows = table_messageInformation().find(atDescendant(".tag","TR"), false);
			FromValue = rows[1].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			ToValue = rows[2].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			String CCValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			SubjectValue = rows[4].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			String messageBody = document_gwMessageBody().find(atDescendant(".tag", "DIV", "class", "WordSection1"), false)[0].getProperty(".text").toString().trim();
			
			
			vpManual("Correct_From_Value", MessageFromValue.replace("\"", ""), FromValue).performTest();
			vpManual("Correct_Recipient_Value", extractEmailOnly(MessageToValue), ToValue).performTest();
			vpManual("Correct_CC", extractEmailOnly(MessageCCValue), CCValue).performTest();
			expectedSubject = (messageName).replace(" ", "").replace("", " ").trim();
			vpManual("Correct_Subject", expectedSubject, SubjectValue).performTest();
			vpManual("CorrectOriginalMessage", messageBodyValue.trim(), messageBody).performTest();
			
			TestObject[] attachmentLink = table_messageInformation().find(atDescendant(".tag", "A", ".text", attachmentName));
			vpManual("AttachmentExists", true, attachmentLink.length == 1 ).performTest();
		}
		html_close().click();
		logInfo("Close Message");
		sleep(0.5);
		html_gwDeleteMessage().click();
		logInfo("Delete Message");
		sleep(2);
		if(html_close().exists()){
			html_close().click();
		}
		
	
	}
	
	
	private void TestMessageWithNestedMessage(){
		logInfo("Test forwarded message with nested messages");
		//Forward Message Content
		String 	messageName = "Fwd: Marc-France Léger La Presse (TEXT) (test BCC)", 
				recipient = "S.Amy@sqa.com", 
				sender = "S.Amy@sqa.com",
				forwardMessage = "automation testing";
			
		//Find the FIRST Message with that subject name
		findMessageAndOpen(messageName);
		sleep(2);
		//The actual message Information
		TestObject[] mbInfoRows = form_extGen467().find(atDescendant(".tag", "DIV"));
		String  MessageFromValue = text_mbFrom().getProperty(".value").toString(),
				MessageToValue = text_mbTO().getProperty(".value").toString(),
				MessageSubjectValue = text_mbSubject().getProperty(".value").toString(),
				messageBodyValue = document_messageBody().getProperty(".text").toString();

		//Avoid ambigous object and move first message window to the side
		TestObject messageWindow0 = find(atDescendant(".tag", "DIV", "id", new RegularExpression("^messageWindow0$", false)))[0];
		((GuiTestObject)messageWindow0).hover(atPoint(100,10));
		((GuiTestObject)messageWindow0).drag(atPoint(100,10), atPoint(300,10));
		
		//Open nested Message MUST BE SECOND ATTACHMENT
		GuiTestObject nestedMessageLink =((GuiTestObject)mbInfoRows[mbInfoRows.length-1].find(atDescendant(".tag", "A"))[1]);
		nestedMessageLink.click();
		logInfo("Open Nested Message");
		
		//Close First Message for object recognition purposes
		((GuiTestObject)messageWindow0.find(atDescendant(".tag", "DIV", "class", "x-tool x-tool-close"))[0]).click();
		
		//Get Nested Message Information and close message window
		String  MessageFromValue2 = text_mbFrom().getProperty(".value").toString(),
				MessageToValue2 = text_mbTO().getProperty(".value").toString(),
				MessageSubjectValue2 = text_mbSubject().getProperty(".value").toString(),
				messageBodyValue2 = document_messageBody().getProperty(".text").toString();
		html_mbCLose().click();
		logInfo("Close First Message");
		
		//Reopen message
		findMessageAndOpen(messageName);
		
		//Forward The Message
		forwardMessage(messageName, recipient, forwardMessage);
		sleep(2);
		
		//Log into Amy/Password in webaccess
		logInWebaccess(GWusername, GWpassword);
		
		//Find The message in webaccess
		TestObject theMessage = findMessageInWebAccess(messageName, false);
		if(theMessage != null){
			((GuiTestObject)theMessage).doubleClick();
			logInfo("open the message <"+messageName+"> in webaccess");
			sleep(2);

			//Validate Message
			TestObject[] rows = table_messageInformation().find(atDescendant(".tag","TR"), false);
			String 	FromValue = rows[1].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString(),
					ToValue = rows[2].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString(),
					SubjectValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString(),
					expectedSubject = ("Fwd: "+MessageSubjectValue).replace(" ", "").replace("", " ").trim();
			
			vpManual("FWD_Correct_Sender", "<"+sender+">", FromValue).performTest();
			vpManual("FWD_Correct_Recipient", recipient, ToValue).performTest();						
			vpManual("FWD_Correct_Subject", expectedSubject, SubjectValue).performTest();
			vpManual("CheckForwardedMessage", forwardMessage,document_gwMessageBody().getProperty(".text")).performTest();
			
			//Validate Actual Forwarded Message
			link_openThisMessage().click();
			logInfo("Open envelope");
			sleep(1);
			rows = table_messageInformation().find(atDescendant(".tag","TR"), false);
			
			FromValue = rows[1].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			ToValue = rows[2].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			SubjectValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			String messageBody = html_idMsgBodyPlainText().getProperty(".text").toString().trim();
			expectedSubject = (MessageSubjectValue).replace(" ", "").replace("", " ").trim();
			
			vpManual("Correct_From_Value", MessageFromValue.replace("\"", ""), FromValue).performTest();
			vpManual("Correct_Recipient_Value", extractEmailOnly(MessageToValue), ToValue).performTest();
			vpManual("Correct_Subject", expectedSubject, SubjectValue).performTest();
			vpManual("CorrectOriginalMessage", messageBodyValue.trim(), messageBody).performTest();
			
			
			//Validate Nested Forwarded Message
			link_openThisMessage().click();
			logInfo("Open envelope");
			sleep(1);
			rows = table_messageInformation().find(atDescendant(".tag","TR"), false);
			
			FromValue = rows[1].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			ToValue = rows[2].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			//CCValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			SubjectValue = rows[3].find(atChild(".tag","TD"),false)[1].getProperty(".text").toString();
			messageBody = html_idMsgBodyPlainText().getProperty(".text").toString().trim();
			expectedSubject = (MessageSubjectValue2).replace(" ", "").replace("", " ").trim();
			
			vpManual("Nested_Correct_From_Value", MessageFromValue2.replace("\"", ""), FromValue).performTest();
			vpManual("Correct_Recipient_Value", "Milena&boo@sqa.com", ToValue).performTest();
			vpManual("Nested_Correct_Subject", expectedSubject, SubjectValue).performTest();
			vpManual("Nested_CorrectOriginalMessage", messageBodyValue2.trim(), messageBody).performTest();
			
			//Delete Message
			html_close().click();
			logInfo("close Message");
			sleep(0.5);
			html_close().click();
			logInfo("close Message");
			sleep(2);
			((GuiTestObject)findMessageInWebAccess(messageName, true)).doubleClick();
			sleep(0.5);
			html_gwDeleteMessage().click();
			logInfo("Delete Message");
			sleep(2);
			if(html_close().exists()){
				html_close().click();
			}
		}
	}
	
	
	
	private void chooseDatabase(){
		//Navigate to message with no attachment
		TestObject[] databaseLink;
		GuiTestObject databaseCheckBox;
		
		databaseLink = html_leftPanelInnerContrainer().find(atDescendant(".tag", "UL"), true)[0].find(atDescendant(".tag", "A", ".text", "RIF"), false);
		databaseCheckBox = (GuiTestObject)databaseLink[0].getParent().find(atDescendant(".tag","INPUT"))[0];
		if(!Boolean.parseBoolean(databaseCheckBox.getProperty(".checked").toString())){
			databaseCheckBox.click();
		}
		
		databaseLink = html_leftPanelInnerContrainer().find(atDescendant(".tag", "UL"), true)[0].find(atDescendant(".tag", "A", ".text", "local"), false);
		databaseCheckBox = (GuiTestObject)databaseLink[0].getParent().find(atDescendant(".tag","INPUT"))[0];
		if(!Boolean.parseBoolean(databaseCheckBox.getProperty(".checked").toString())){
			databaseCheckBox.click();
		}
		
		
		databaseLink = html_leftPanelInnerContrainer().find(atDescendant(".tag", "UL"), true)[0].find(atDescendant(".tag", "A", ".text", "ALS"), false);
		databaseCheckBox = (GuiTestObject)databaseLink[0].getParent().find(atDescendant(".tag","INPUT"))[0];
		if(Boolean.parseBoolean(databaseCheckBox.getProperty(".checked").toString())){
			databaseCheckBox.click();
		}
		sleep(2);

	}
	
	
	
	
	private void findMessageAndOpen(String nameOfMessage){
		Property[] properties = new Property[3];
		properties[0] = new Property(".tag", "DIV");
		properties[1] = new Property(".text", nameOfMessage);
		properties[2] = new Property("class", new RegularExpression("x-grid3-cell-inner x-grid3-col-\\d",false));
		System.out.println("Looking for:"+nameOfMessage);
		GuiTestObject message = (GuiTestObject) HelperClass.getActiveTabBody()[0].find(atDescendant(properties),false)[0];
		message.doubleClick();
		logInfo("open message in netmail search with the name:"+nameOfMessage);
		sleep(2);
	}
	
	public void forwardMessage(String messageName, String recipient, String forwardMessage){
		button_mbForwardMessage().click();
		logInfo("forward button clicked");
		sleep(1);
		text_mbFwdTo().click();
		browser_htmlBrowser().inputKeys("^a{DELETE}"+recipient);
		logInfo("Entering < "+recipient+"> in the TO input field");
		vpManual("Subject_is_prepopulated_for_forwardMessage",("Fwd: "+messageName).toLowerCase(), text_mbFwdSubject().getProperty(".value").toString().toLowerCase()).performTest();
		document_mbFwdMessageBody().click();
		browser_htmlBrowser().inputChars(forwardMessage);
		logInfo("Entering < "+forwardMessage+"> in the message body");
		button_mbFwdForwardbutton().click();
		logInfo("forward button clicked");
		sleep(3);
		html_mbCLose().click();
		logInfo("close message");
	}
	
	
	public void logInWebaccess(String username, String password){
		logInfo("Navigate to webaccess: http://10.10.24.55/gw/");
		browser_htmlBrowser().loadUrl("http://10.10.24.55/gw/");
		html_idLogin().waitForExistence(10000, DISABLED);
		//Give the message time dance its way to the recipient
		sleep(60);
		
		//Enter Information
		logInfo("Enter the following credential username="+ username +" password="+password);
		text_gwUserId().click();
		browser_htmlBrowser().inputKeys(username);
		text_gwUserPassword().click();
		browser_htmlBrowser().inputKeys(password);
		button_gwLoginSubmit().click();
		logInfo("click login");
		sleep(5);

	}
	
	private TestObject findMessageInWebAccess(String messageName, Boolean isRead){
		html_msglist().waitForExistence(240, DISABLED);
		String status = isRead ? "read" : "unread";
		boolean foundMessage = false;
		TestObject[] unreadMessages = html_msglist().find(atDescendant(".tag", "TR", "class", status), false);
		for(TestObject unreadMessage: unreadMessages){
			
			Property[] properties = new Property[2];
			properties[0] = new Property(".tag", "TD");
			properties[1] = new Property(".text", "Fwd: "+messageName);
			TestObject[] theMessage = unreadMessage.find(atChild(properties),false);
			
			if(theMessage.length == 1){	
				foundMessage = true;
				return theMessage[0];
			}
		}
		if(!foundMessage){
			logError("Cannot find forwarded message");	
		}
		return null;
	}
}

