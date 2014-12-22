package NetmailSearch_AdvanceSearch;

import java.util.ArrayList;
import java.util.Arrays;

import resources.NetmailSearch_AdvanceSearch.MessageNetmailTab_SuperUserHelper;
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
public class MessageNetmailTab_SuperUser extends MessageNetmailTab_SuperUserHelper
{
	/**
	 * Script Name   : <b>advanceSearchMessageNetmailTab_SuperUser</b>
	 * Generated     : <b>Jul 9, 2013 1:33:04 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/07/09
	 * @author Administrator
	 */
	//case name: all
	private String 	field1 = "", //"exact", 
					field2 = "", //"forbidden", 
					field3 = "", // "approximate", //"wordsStarting",
					value1 = "", //"Dean John",
					value2 = "", //"Kraft",
					value3 = "", //"revized",
					listOfApproximateWords= "", // "revised revise revises",
					logic = "", // "OR",
					sql = "",
					customSearch = "",
					customSearchResultTitles = "";
	private int increment = 0,
				customSearchNumberOfResults = 0;
	
	
	public void testMain(Object[] args) 
	{
		if(args.length>0){
			field1 = (String) args[0];
			field2 = (String) args[1];
			field3 = (String) args[2];
			value1 = (String) args[3];
			value2 = (String) args[4];
			value3 = (String) args[5];
			logic = (String) args[6];
			listOfApproximateWords = (String) args[7];
			customSearch = (String) args[8];
			customSearchNumberOfResults = (int) args[9];
			customSearchResultTitles = (String) args[10];
		}
		button_advancedSearchbutton().click();
		logInfo("Clicked advance search");
		sleep(0.5);

		link_clearSearch().click();
		logInfo("clicked clear search on dropdown");
		sleep(3);
		waitForloading();
		button_advancedSearchbutton().click();
		logInfo("click advance search");
		sleep(0.5);
		link_advanceSearchMessage().click();
		logInfo("clicked messages on advance search dropdown");
		sleep(1);
		link_advSearchNetmailTab().click();
		logInfo("Clicked Netmail Search tab");
		
		if(customSearch.isEmpty()){
			enterSearch(field1, value1);
			validateSQL(field1, value1);
			
			enterSearch(field2, value2);
			validateSQL(field2, value2);
			
			enterSearch(field3, value3);
			validateSQL(field3, value3);
		}else{
			//custom search
			text_sql().click();
			browser_htmlBrowser().inputChars(customSearch);
		}

		button_searchsubmit().click();
		sleep(10);
		if(customSearch.isEmpty()){
			validateSearch();
		}else{
			//validate custom mysql
			validateCustomSearch();
		}
		
	}
	
	
	private void enterSearch(String field, String value){
		if(!field.isEmpty() | field != null){
			switch (field.trim()) {
				case "exact":
					table_exactPhrase().click();
					logInfo("clicked exact phrase row");
					sleep(0.5);
					break;
					
				case "forbidden":
					table_forbiddenTerm().click();
					logInfo("clicked forbidden phrase row");
					sleep(0.5);
					break;
					
				case "wordsStarting":
					table_wordStartingWith().click();
					logInfo("clicked Words Starting With row");
					sleep(0.5);
					break;
					
				case "approximate":
					table_approximateSpelling().click();
					logInfo("clicking Approximate spelling row");
					sleep(0.5);
					break;
					
				default:
					return;
			}
			text_netmailSearch_Input().click();
			browser_htmlBrowser().inputChars(value);
			logInfo("Entered < "+value+" >");
			clickButton();
		}
	}
	
	private void clickButton(){
		if(sql.isEmpty()){
			logInfo("clicked APPLY button");
			button_applybutton().click();
			sleep(1);
		}else if(logic.toLowerCase().trim().contentEquals("and")){
			button_anDbutton().click();
			logInfo("clicked AND button");
			sleep(1);
		}else if(logic.toLowerCase().trim().contentEquals("or")){
			button_oRbutton().click();
			logInfo("clicked OR button");
			sleep(1);
		}
	}
	
	private void validateSQL(String field, String value){
		String condition = " "+ logic.toUpperCase().trim()+" ";
		switch (field.trim()) {
			case "exact":
				sql += sql.isEmpty() ? "\""+value+"\"" : condition+"\""+value+"\"";
				break;
				
			case "forbidden":
				sql += sql.isEmpty() ? "-"+value : condition+"-"+value;
				break;
				
			case "wordsStarting":
				sql += sql.isEmpty() ? value+"*" :  condition+value+"*";
				break;
					
			case "approximate":
				sql += sql.isEmpty() ? "spellslike:"+value :  condition+"spellslike:"+value;
				break;
				
			default:
				return;
		}
		//Unique VP name. Else it will override each other results
		vpManual("CorrectSQL_"+increment++, sql.trim(), text_sql().getProperty(".contentText").toString().trim()).performTest();
		text_sql().unregister();
	}
	
	private void validateSearch(){
		TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		String [] 	fields = {field1, field2, field3},
					values = {value1, value2, value3};
		
		ArrayList<Boolean> bool = new ArrayList<Boolean>(3);
		boolean isFound;
		
		for(int i = 0; i<results.length ; i++){
			((GuiTestObject)results[i]).hover();
			((GuiTestObject)results[i]).click();
			((GuiTestObject)results[i]).doubleClick();
			logInfo("opening message "+i);
			sleep(2);
			waitForloading();
			
			
			//Just in case first time doublecliing doesn't open message
			try{
				text_mbSubject().getProperty(".value").toString();
			}catch(ObjectNotFoundException e){
				((GuiTestObject)results[i]).hover();
				((GuiTestObject)results[i]).click();
				((GuiTestObject)results[i]).doubleClick();
				sleep(2);
				waitForloading();
			}
			
			
			for(int k = 0; k <fields.length ; k++){
				if(!fields[k].isEmpty()){
					String regexp = "";
					String[] splitted;
					
					switch (fields[k].trim()){
						case "exact":
							regexp = "(?i).*\\b"+values[k].trim().replace(" ", "\\b[^a-zA-Z\\d]\\b")+"\\b.*";
							isFound = findWordInMessage(regexp);
							bool.add(isFound);
							vpManual("NetmailSearch_exactPhrase", true, isFound).performTest();
							break;
							
						case "forbidden":
							regexp = "(?i).*(\\b"+values[k].trim().replace(" ", "\\b|\\b")+"\\b).*";
							isFound =forbiddenWordInMessage(regexp);
							bool.add(!isFound);
							vpManual("NetmailSearch_forbidden", true, !isFound).performTest();
							break;
							
						case "wordsStarting":
							boolean allWordsFound = true;
							splitted = values[k].split(" ");
							for(String split : splitted){
								regexp = "(?i)"+split;
								isFound=findWordInMessage(regexp);
								if(!isFound){
									//Fails if finds one word that cannot be found in all highlighted text
									allWordsFound = false;
									break;
								}
							}
							bool.add(allWordsFound);
							vpManual("NetmailSearch_wordsStarting", true, allWordsFound).performTest();
							break;
							
						case "approximate":
							isFound = approximateWordInMessage();
							bool.add(isFound);
							vpManual("NetmailSearch_approximate", true, isFound).performTest();
							break;
					}
				}
			}

			if(logic.toLowerCase().trim().contentEquals("or")){
				logInfo("validating logic conditions: OR");
				vpManual("Msg"+i+"_OR_correct_content", true , bool.contains(true)).performTest();
			}else{
				logInfo("validating logic conditions: AND");
				vpManual("Msg"+i+"_AND_correct_content", true , !bool.contains(false)).performTest();
			}
			html_mbClose().click();
			logInfo("closing message "+i);
			bool.clear();
			sleep(1);
		}
		
		
	}
	
	private void validateCustomSearch(){
		String[] resultTitleCollection = customSearchResultTitles.split(",");
		for(int i=0; i<resultTitleCollection.length; i++){
			resultTitleCollection[i] = resultTitleCollection[i].replace("\"", "").trim();
		}
		
		java.util.List<String> resultTitleList =  Arrays.asList(resultTitleCollection);
		TestObject[] results = HelperClass.getActiveTabBody()[0].find(atDescendant(".tag", "TABLE", "class", "x-grid3-row-table"));
		vpManual("SearchExpectNumResult", customSearchNumberOfResults, results.length).performTest();
		
		for(int i = 0; i<results.length ; i++){
			Property p1 = new Property(".tag", "TD" );
			Property[] properties ={p1};
			TestObject[] subjectColumn = findAtDescendant(results[i], properties, false);
			System.out.println(resultTitleList.toString());
			System.out.println(subjectColumn[7].getProperty(".contentText").toString());
			vpManual("NameInResultTitleSet", true, resultTitleList.contains(subjectColumn[7].getProperty(".text").toString())).performTest();
		}
	}
	
	
	private boolean findWordInMessage(String regexp){
		text_mbTo().waitForExistence(60, DISABLED);
		TestObject[] highlightedTexts = frame_mbMessageBody().find(atDescendant(".class", "Html.SPAN", "style", "BACKGROUND-COLOR: #feff80"), false);
		boolean matchesBody = false, matchesHeader = false;
		Boolean[] hit = {
				text_mbTo().getProperty(".value").toString().trim().toLowerCase().matches(regexp),
				text_mbSubject().getProperty(".value").toString().trim().toLowerCase().matches(regexp),
				text_mbFrom().getProperty(".value").toString().trim().toLowerCase().matches(regexp),							
			};
		matchesHeader =Arrays.asList(hit).contains(true);
		
		//Match highlighted text against word, NOTE: if word exists in text but not highlighted it is also a fail.
		for(TestObject higlightedTextObject : highlightedTexts){
			String highlightedText = higlightedTextObject.getProperty(".text").toString().toLowerCase().trim();
			
			if(highlightedText.matches(regexp)){
				matchesBody = true;
				break;//Found a highlighted text term that matches word in message body
			}
		}
		
		//Check
		String contentOfBody = frame_mbMessageBody().find(atDescendant(".class", "Html.BODY"), false)[0].getProperty(".text").toString();
		regexp = "(?i).*\\b"+regexp.replace(" ", "\\b[^a-zA-Z0-9]*\\b")+"\\b.*";
		if(contentOfBody.matches(regexp) ){
			matchesBody = true;
		}
		
		return matchesHeader|matchesBody;
	}
	
	
	
	private boolean forbiddenWordInMessage(String regexp){
		text_mbTo().waitForExistence(60, DISABLED);
		boolean matchesBody = false, matchesHeader = false;
		Boolean[] hit = {
				text_mbTo().getProperty(".value").toString().trim().toLowerCase().matches(regexp),
				text_mbSubject().getProperty(".value").toString().trim().toLowerCase().matches(regexp),
				text_mbFrom().getProperty(".value").toString().trim().toLowerCase().matches(regexp),							
			};
		matchesHeader =Arrays.asList(hit).contains(true);
		matchesBody = document_mbMessageBody().getProperty(".text").toString().matches(regexp);

		return matchesHeader|matchesBody;
	}
	
	
	private boolean approximateWordInMessage(){
		text_mbTo().waitForExistence(60, DISABLED);
		TestObject[] highlightedTexts = frame_mbMessageBody().find(atDescendant(".class", "Html.SPAN", "style", "BACKGROUND-COLOR: #feff80"), false);
		String[] approximateWords = listOfApproximateWords.split(" ");
		boolean matchesBody = false, matchesHeader = false;
		
		String 	toField = text_mbTo().getProperty(".value").toString().trim().toLowerCase(),
				subjectField = text_mbSubject().getProperty(".value").toString().trim().toLowerCase(),
				fromField = text_mbFrom().getProperty(".value").toString().trim().toLowerCase(),
				regexp = "";
		
		for(String approxWord : approximateWords){
			regexp = ".*"+approxWord+".*";
			Boolean[] hit = {
					toField.matches(regexp),
					subjectField.matches(regexp),
					fromField.matches(regexp),							
				};
			matchesHeader = Arrays.asList(hit).contains(true);
			if(matchesHeader){
				break;
			}
		}
		

		for(TestObject higlightedTextObject : highlightedTexts){
			String highlightedText = higlightedTextObject.getProperty(".text").toString().toLowerCase().trim();
			matchesBody = Arrays.asList(approximateWords).contains(highlightedText);
			if(matchesBody){
				break;
			}
		}

		return matchesHeader|matchesBody;
	}
}

