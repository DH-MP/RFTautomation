package NetmailSearch_AdvanceSearch;

import java.util.ArrayList;
import java.util.Arrays;

import resources.NetmailSearch_AdvanceSearch.MessageWordListTab_SuperUserHelper;
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
import com.ibm.jsse2.i;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

import de.tu_darmstadt.informatik.rbg.hatlak.rockridge.impl.SUSPFactory;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class MessageWordListTab_SuperUser extends MessageWordListTab_SuperUserHelper
{
	/**
	 * Script Name   : <b>advanceSearchMessageWordListTab_SuperUser</b>
	 * Generated     : <b>Jul 8, 2013 4:12:07 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/07/08
	 * @author Administrator
	 */
	
	private String wordList = "test importance";
	public void testMain(Object[] args) 
	{
		if(args.length>0){
			wordList = (String) args[0];
		}
		if(wordList != null && !wordList.isEmpty()){
			button_advancedSearchbutton().click();
			logInfo("Clicked advance search");
			sleep(0.5);
			
			link_clearSearch().click();
			logInfo("clicked clear search on dropdown");
			sleep(3);
			
			button_advancedSearchbutton().click();
			logInfo("click advance search");
			sleep(0.5);
			
			link_advanceSearchMessages().click();
			logInfo("clicked messages on advance search dropdown");
			sleep(0.8);
			
			openWordListTab();
			
			for(String word: wordList.toLowerCase().split(" ")){
				browser_htmlBrowser().inputChars(word);
				browser_htmlBrowser().inputKeys("~");
				logInfo("input < "+word+" > in wordlist text box");
			}
			button_clearsubmit().click();
			logInfo("clicked clear");
			vpManual("wordListBoxCleared", true, text_wordlist().getProperty(".value").toString().isEmpty()).performTest();
			text_wordlist().click();
			sleep(0.3);
			for(String word: wordList.split(" ")){
				browser_htmlBrowser().inputChars(word);
				browser_htmlBrowser().inputKeys("~");
				logInfo("input < "+word+" > in wordlist text box");
			}
			button_searchsubmit().click();
			logInfo("clicked search");
			waitForloading();
			
			
			boolean matchesBody = false, matchesHeader = false;
			String[] words = wordList.toLowerCase().split(" ");
			TestObject[] results = html_activeTabBody().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
			
			for(int i = 0; i<results.length ; i++){
				GuiTestObject msg = (GuiTestObject) results[i];
				msg.hover();
				msg.click();
				msg.doubleClick();
				sleep(2);
				waitForloading();
				logInfo("Opened message number "+i);
				logInfo("Validating if word list matches this msg information");
				text_mbSubject().waitForExistence(120, DISABLED);
				
				
				//Word List condition: all the words must exists in atleast one of the following fields: from, cc, subject message body, etc.
				TestObject[] highlightedTexts = frame_mbMessageBody().find(atDescendant(".class", "Html.SPAN", "style", "BACKGROUND-COLOR: #feff80"), false);
				System.out.println("NUMBER OF HIGHLIGHTED TEXT IN BODY:"+highlightedTexts.length);
				Boolean oneOfWordFound = false;
				for (String word : words){
					
					//Find the word in To,From,CC,Subject field 
					Boolean[] hit = {
										text_mbTo().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word.trim()+".*"),
										text_mbSentReceivedDate().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word.trim()+".*"),
										text_mbSubject().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word.trim()+".*"),
										text_mbFrom().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word.trim()+".*"),							
									};
					matchesHeader = Arrays.asList(hit).contains(true);
					
					//Match highlighted text against word, if word exists in text but not highlighted it is also a fail.
					for(TestObject higlightedTextObject : highlightedTexts){
						String higlightedText = higlightedTextObject.getProperty(".text").toString().toLowerCase().trim();
						if(higlightedText.matches(".*"+word+".*")){
							matchesBody = true;
							break;//Found a highlighted text term that matches word in message body
						}
					}
					
					System.out.println(word+matchesHeader+" "+matchesBody);
					if(!(matchesHeader|matchesBody)){
						logInfo("The word < "+word+" >was not found in msg"+i, browser_htmlBrowser().getScreenSnapshot());
						break;
					}else{
						oneOfWordFound = true;
					}
					
				}
				highlightedTexts=null;
				html_mbClose().click();
				logInfo("closed message box");
				
				if(!oneOfWordFound){
					logError("no word found in message["+i+"]", browser_htmlBrowser().getScreenSnapshot());
				}
			}

		
		
		}
		
		
	}
	
	public void inputWordList(){
		inputWordList(wordList);
	}
	
	public void inputWordList(String wordList){
		inputWordList(wordList, " ");
	}
	public void inputWordList(String wordList, String splitBy){
		setWordList(wordList);
		openWordListTab();
		if(wordList != null && !wordList.isEmpty()){
			text_wordlist().click();
			for(String word: wordList.toLowerCase().split(splitBy)){
				try{
					browser_htmlBrowser().inputChars(word);
					browser_htmlBrowser().inputKeys("~");
				}catch(StringNotInCodePageException e){
					String currentText = text_wordlist().getProperty(".value").toString();
					text_wordlist().setProperty(".text", currentText+"\\n"+word);
				}
				logInfo("input < "+word+" > in wordlist text box");
			}
		}
	}
		
	public void search(){
		button_searchsubmit().click();
		logInfo("clicked search");
		waitForloading();	
	}
	
	public void openWordListTab(){
		link_advSearchWordListTab().click();
		logInfo("Clicked on WordList tab");
		text_wordlist().click();
	}
	
	public TextGuiTestObject text_wordlist(){
		//make it public
		return super.text_wordlist();
	}
	
	public MessageWordListTab_SuperUser setWordList(String wordList){
		this.wordList = wordList == null ? "" : wordList;
		return this;
	}
	
	public void validateSearchResult(){
		performValidation(" ");
	}
	
	
	public void validateSearchResult(String splitBy){
		performValidation(splitBy);
	}
	
	private void performValidation(String splitBy){
		if(wordList == null && wordList.isEmpty()){
			return;
		}
		boolean matchesBody = false, matchesHeader = false;
		String[] lines = wordList.toLowerCase().split(splitBy);
		TestObject[] results = html_activeTabBody().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		
		
		
		for(int i = 0; i<results.length ; i++){
			GuiTestObject msg = (GuiTestObject) results[i];
			msg.hover();
			msg.click();
			msg.doubleClick();
			sleep(2);
			waitForloading();
			logInfo("Opened message number "+i);
			logInfo("Validating if word list matches this msg information");
			text_mbSubject().waitForExistence(120, DISABLED);
			
			
			//Word List condition: all the words must exists in atleast one of the following fields: from, cc, subject message body, etc.
			TestObject[] highlightedTexts = frame_mbMessageBody().find(atDescendant(".class", "Html.SPAN", "style", "BACKGROUND-COLOR: #feff80"), false);
			System.out.println("NUMBER OF HIGHLIGHTED TEXT IN BODY:"+highlightedTexts.length);
			
			Boolean oneOfLinePasses = false;
			for (String line : lines){	
				line = line.toLowerCase().trim();
				//Find the word in To,From,CC,Subject field 
					
				String[] words = line.split(" ");
				if(words.length<=1){
								Boolean[] hit = {
										text_mbTo().getProperty(".value").toString().trim().toLowerCase().matches(".*"+line+".*"),
										text_mbSentReceivedDate().getProperty(".value").toString().trim().toLowerCase().matches(".*"+line+".*"),
										text_mbSubject().getProperty(".value").toString().trim().toLowerCase().matches(".*"+line+".*"),
										text_mbFrom().getProperty(".value").toString().trim().toLowerCase().matches(".*"+line+".*"),							
									};
					matchesHeader = Arrays.asList(hit).contains(true);
					
					//Match highlighted text against word, if word exists in text but not highlighted it is also a fail.
					for(TestObject higlightedTextObject : highlightedTexts){
						String higlightedText = higlightedTextObject.getProperty(".text").toString().toLowerCase().trim();
						if(higlightedText.matches(".*"+line+".*")){
							matchesBody = true;
							break;//Found a highlighted text term that matches word in message body
						}
					}
					
					if(!(matchesHeader|matchesBody)){
						logInfo("The word < "+line+" >was not found in msg"+i, browser_htmlBrowser().getScreenSnapshot());
					}else{
						oneOfLinePasses = true;
					}			
				}else{		
					ArrayList<Boolean> andConditionMet = new ArrayList<Boolean>();
					for(String word : words){//One line condition: all must exists , treat multiple words as one word
						Boolean[] hit = {
								text_mbTo().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word+".*"),
								text_mbSentReceivedDate().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word+".*"),
								text_mbSubject().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word+".*"),
								text_mbFrom().getProperty(".value").toString().trim().toLowerCase().matches(".*"+word+".*"),							
							};
						matchesHeader = Arrays.asList(hit).contains(true);
						
						//Match highlighted text against word, if word exists in text but not highlighted it is also a fail.
						for(TestObject higlightedTextObject : highlightedTexts){
							String higlightedText = higlightedTextObject.getProperty(".text").toString().toLowerCase().trim();
							if(higlightedText.matches(".*"+word+".*")){
								matchesBody = true;
								break;//Found a highlighted text term that matches word in message body
							}
						}
						
						if(!(matchesHeader|matchesBody)){
							logInfo("The word < "+word+" >was not found in msg"+i, browser_htmlBrowser().getScreenSnapshot());
							andConditionMet.add(false);
						}else{
							andConditionMet.add(true);
						}			
	
					}
					
					if(!andConditionMet.contains(false)){
						oneOfLinePasses = true;
					}
				}				
			}
			
			
			highlightedTexts=null;
			html_mbClose().click();
			logInfo("closed message box");
			
			if(!oneOfLinePasses){
				logError("no lines/words found in message["+i+"]", browser_htmlBrowser().getScreenSnapshot());
			}
		}
	}
	
	public void validateExpectedResults(int numOfResults){
		TestObject[] results = html_activeTabBody().find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		vpManual("WordList_ExpectedResults", numOfResults, results.length).performTest();
	}
}

