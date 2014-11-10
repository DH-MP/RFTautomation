package NetmailSearch_AdvanceSearch;

import resources.NetmailSearch_AdvanceSearch.Attachment_SuperUserHelper;
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
public class Attachment_SuperUser extends Attachment_SuperUserHelper
{
	/**
	 * Script Name   : <b>advanceSearchAttachment_NormalUser</b>
	 * Generated     : <b>Jun 12, 2013 1:43:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/12
	 * @author Administrator
	 */
	
	private String  filename = "";
	private String	fileSize1 = "", 
					fileSize2 = "";
	private boolean testBody = true,
					expectedResults = true;
	public void testMain(Object[] args) 
	{
		if(args.length>0){
			filename = (String) args[0];
			fileSize1 = args[1].toString().isEmpty()? "": args[1].toString();
			fileSize2 = args[2].toString().isEmpty()? "": args[2].toString();
			testBody = (boolean) args[3];
			expectedResults = (boolean) args[4];
		}
		

		button_advancedSearchbutton().click();
		logInfo("Open Advance Search dropdown");
		link_attachments().click();
		logInfo("Open Attachement from dropdown");
		sleep(1);

		//Enter Advance Search Attachment
		if(!filename.isEmpty()){
			logInfo("Input < "+filename+ " > in Filename input field");
			checkBox_ifFilenameon().click();
			text_filename().click();
			browser_htmlBrowser().inputChars(filename);
		}

		if(fileSize1 != null && fileSize2!= null){
			logInfo("Input file size range: < "+fileSize1.toString()+" >-< "+fileSize2.toString()+" >");
			checkBox_ifFilesizeon().click();
			text_filesize1().click();
			browser_htmlBrowser().inputChars(fileSize1.toString());
			text_filesize2().click();
			browser_htmlBrowser().inputChars(fileSize2.toString());
		}
		
		button_advAttach_Searchsubmit().click();
		logInfo("search clicked");
		sleep(5);
		waitForloading();
		
		//Validation Steps
		TestObject[] activeResultListDiv = find(atDescendant(".tag", "DIV", "class", "x-panel x-panel-noborder x-grid-panel"), true);
		TestObject[] results = activeResultListDiv[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		if(expectedResults && results.length>0){
			validateFileNameAndFileSize(results);
		}else if(expectedResults && results.length == 0){
			logError("Expected results, no results returned from query");
		}else if(!expectedResults && results.length == 0){
			logTestResult("Expected no results", true);
		}
			
		
		if(testBody){
			//Body input test: Simple expected result check
			button_advancedSearchbutton().click();
			logInfo("Open Advance Search dropdown");
			link_clearSearch().click();
			waitForloading();
			waitForloading();
			
			button_advancedSearchbutton().click();
			logInfo("Open Advance Search dropdown");
			link_attachments().click();
			logInfo("Open Attachement from dropdown");
			sleep(1);
			
			checkBox_ifFilenameon().click();
			text_filename().click();
			browser_htmlBrowser().inputChars("22");
//			checkBox_ifFilesizeon().click();
//			text_filesize1().click();
//			browser_htmlBrowser().inputChars("110000");
//			text_filesize2().click();
//			browser_htmlBrowser().inputChars("155000");
			
			checkBox_ifAttBodyon().click();
			text_body().click();
			browser_htmlBrowser().inputChars("file");
			text_nextto().click();
			browser_htmlBrowser().inputChars("xfilter");
			text_within().click();
			browser_htmlBrowser().inputChars("4");
			
			button_advAttach_Searchsubmit().click();
			logInfo("search clicked");
			
			sleep(4);
			waitForloading();
			waitForloading();
			
			//Validation Steps
			activeResultListDiv = find(atDescendant(".tag", "DIV", "class", "x-panel x-panel-noborder x-grid-panel"), true);
			results = activeResultListDiv[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
			vpManual("Only_two_results_returned", 2, results.length).performTest();
			for(TestObject result: results){
				TestObject[] columns = result.find(atDescendant(".tag", "TD"), false);
				String actualFileName = ((GuiTestObject)columns[2]).getProperty(".text").toString();
				vpManual("Correct_File_Returned", "Gastro Final File.xls", actualFileName).performTest();
			}
		}
	}
		
		
	private void validateFileNameAndFileSize(TestObject[] results){
		String msg = "Check if %s contains the query < %s > in the string < %s >";
		for(TestObject result: results){
			TestObject[] columns = result.find(atDescendant(".tag", "TD"), false);
			String actualFileName = ((GuiTestObject)columns[2]).getProperty(".text").toString();
			logInfo("Testing the file: "+actualFileName);
			if(!filename.isEmpty()){
				String[] words = filename.split(" ");
				for(String word: words){
					logInfo(String.format(msg, "FILENAME", word, actualFileName));
					if(!actualFileName.toLowerCase().contains(word.toLowerCase())){
						logError("Result does not contain the word<"+word+"> in the file name. FAILED");
						break;
					}else{
						logTestResult("Result does contains the word< "+word+" > in the file name. ", true);
					}
				}
			}
			
			if(fileSize1!=null && fileSize2!=null){
				TestObject[] attachmentLink = null;
				try{
					((GuiTestObject)columns[2]).hover();
					((GuiTestObject)columns[2]).click();
					((GuiTestObject)columns[2]).doubleClick();
					logInfo("doubled click result");
					sleep(3);
					attachmentLink = html_messageWindow0().find(atDescendant(".tag", "A", ".text", actualFileName), true);
				}catch(ObjectNotFoundException e){
					((GuiTestObject)columns[2]).hover();
					((GuiTestObject)columns[2]).click();
					((GuiTestObject)columns[2]).doubleClick();
					logInfo("doubled click result");
					sleep(3);
					attachmentLink = html_messageWindow0().find(atDescendant(".tag", "A", ".text", actualFileName), true);
				}
				
				//vpManual("An_attachment_link_exist_in_message", 1, attachmentLink.length).performTest();
				if(attachmentLink.length == 1){
					link_mbPropertyTab().click();
					logInfo("property tab clicked");
					
					//Open Attachments dropdown
					link_propertiesAttachments().click();
					link_propertiesAttachments().doubleClick();
					logInfo("attachment(s) tree expanded");
					sleep(2);
					
					//Find the attachement
					logInfo("Finding <"+actualFileName+"> in the property tree");
					RegularExpression expr = new RegularExpression(actualFileName.replace(" ", "\\s*"), false);
 					TestObject[] attachmentValue = html_attachmentsUL().find(atDescendant(".tag", "DIV", ".title", expr), false);			
					TestObject attachmentRow = attachmentValue[0].getParent().getParent();
					
					//Open the attachement
					((GuiTestObject)attachmentRow).click();
					((GuiTestObject)attachmentRow).doubleClick();
					logInfo("attachment expanded");
					sleep(1);
					
					//Find size of attachment && Validate
					TestObject[] sizeDiv = attachmentRow.getParent().find(atDescendant(".tag", "UL"),true)[0]
							  									 .find(atDescendant(".tag", "LI"), false)[4]
							  									 .find(atDescendant(".tag", "DIV", "title", new RegularExpression("\\d+", false)), false);
					Integer size = Integer.parseInt(sizeDiv[0].getProperty(".text").toString());
					logInfo("Is the size <"+ size +"> of attachment within specified range: "+ fileSize1+" To "+ fileSize2);
					Integer fs1 = fileSizeStringToInteger(fileSize1);
					Integer fs2 = fileSizeStringToInteger(fileSize2);
					vpManual("Size_of_Attachment_is_within_size_range", true, fs1 <= size && size <= fs2 ).performTest();
					//TODO check nested messages
				}
				//Close MessageBox
				while(html_mbClose().exists()){
					logInfo("result window closed");
					html_mbClose().click();
					sleep(2);
				}
			}
		}
	}
	
	
	private  Integer fileSizeStringToInteger (String size){
		if(size.matches(".*kb|.*KB")){
			return (int) (Float.parseFloat(size.replace("kb", "").replace("KB", "").trim())*1000);
		}
		if(size.matches(".*mb|.*MB")){
			return (int) (Float.parseFloat(size.replace("mb", "").replace("MB", "").trim())*1000*1000);
		}
		return Integer.parseInt(size);
	}
}

