package TC_978_ExportPortableNetmailSearch;
import resources.TC_978_ExportPortableNetmailSearch.TS_992_AddTagsAndComments_NetmailLiteHelper;
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
public class TS_992_AddTagsAndComments_NetmailLite extends TS_992_AddTagsAndComments_NetmailLiteHelper
{
	/**
	 * Script Name   : <b>TS_992_AddTagsAndComments_NetmailLite</b>
	 * Generated     : <b>Oct 8, 2013 9:43:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/08
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		
		setup();

		
		/*********************************TEST ALL COLUMN******************************/
		headerColumns(true, true, true, true, true, true);
		unregisterAll();
		waitForloading();
		
		
		//Verify All Columns
		
		TestObject[] viewport = body().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-viewport"),true);	
		TestObject[] tableHeader = viewport[0].find(atDescendant(".class", "Html.TR", "class", "x-grid3-hd-row"), false);
		TestObject[] headerColumn = tableHeader[0].find(atDescendant(".class", "Html.TD", "class", new RegularExpression("x-grid3-hd.*",false)),false);
		TestObject[] expanders = body().find(atDescendant(".class", "Html.DIV", "class", "x-grid3-row-expander"), false);
		
		System.out.println(expanders.length);
		vpManual("ExpanderVisible", true, ((GuiTestObject)expanders[0]).ensureObjectIsVisible()).performTest();
		
		System.out.println(headerColumn[8].getProperties());
		boolean allColumnFlag = true;
		for(int i=0; i<headerColumn.length; i++){
			java.awt.Rectangle dimension = (java.awt.Rectangle)headerColumn[i].getProperty(".bounds");
			if(!(dimension.width>0 && dimension.height>0)){
				allColumnFlag = false;
				logError("Header columns number "+i+" is not visible");
				vpManual("AllColumnsVisible", true, false).performTest();
				break;
			}
		}
		if(allColumnFlag){
			vpManual("AllColumnsVisible", true, true).performTest();
		}
		
/********************************TEST ADDING TAGGING AND COMMENT ***************************************************************/
		TestObject[] results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		
	//Relevant
		((GuiTestObject)results[0].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Checked first message");
		button_relevantbutton().click();
		logInfo("Clicked relevant");
		waitForloading();
		vpManual("TagSuccessMessage", true, html_tagAppliedMSG().ensureObjectIsVisible());
		button_taggOK().click();
		logInfo("Clicked ok tag successful");
		((GuiTestObject)results[0].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Uncheck first message");
		
	//Privilege
		((GuiTestObject)results[1].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Checked second message");
		button_privilegebutton().click();
		logInfo("Clicked privilege");
		waitForloading();
		vpManual("TagSuccessMessage", true, html_tagAppliedMSG().ensureObjectIsVisible());
		button_taggOK().click();
		logInfo("Clicked ok on tag successful");
		((GuiTestObject)results[1].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Unchecked second message");
		
	//Flagged
		((GuiTestObject)results[2].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Checked third message");
		button_flaggedbutton().click();
		logInfo("Clicked flagged");
		waitForloading();
		vpManual("TagSuccessMessage", true, html_tagAppliedMSG().ensureObjectIsVisible());
		button_taggOK().click();
		logInfo("Clicked OK on successful tag");
		((GuiTestObject)results[2].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Unchecked third message");
		
	//Work Product
		((GuiTestObject)results[3].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Checked fourth message");
		button_workProductbutton().click();
		logInfo("Clicked work product");
		waitForloading();
		vpManual("TagSuccessMessage", true, html_tagAppliedMSG().ensureObjectIsVisible());
		button_taggOK().click();
		logInfo("Clicked OK on successful tag");
		((GuiTestObject)results[3].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Unchecked fourth message");
		
		
	//Comment
		((GuiTestObject)results[4].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Checked fifth message");
		text_comment().click();
		browser_htmlBrowser().inputChars("autoCommented");
		logInfo("Input autoCommented in comment input field");
		button_addComment().click();
		logInfo("Clicked add comment");
		waitForloading();
		button_taggOK().click();
		logInfo("Clicked OK on successful comment");
		((GuiTestObject)results[4].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		logInfo("Unchecked fifth message");
		
		setup();
		
	//Verify Tagging and Commenting
		headerColumns(false, false, false, false, true, true);
		
		results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		
		//Msg1
		TestObject[] resultsColumn = results[0].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg1TaggedRelevant", "Relevant;", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
		//Msg2
		resultsColumn = results[1].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg2TaggedPrivilege", "Privileged;", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
		//Msg3
		resultsColumn = results[2].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg3TaggedFlagged", "Flagged;", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
		//Msg4
		resultsColumn = results[3].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg4TaggedWorkProduct", "Work Product;", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
		
		//Msg5
		resultsColumn = results[4].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-13.*", false)), false);
		vpManual("Msg5Comment", "autoCommented", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
		
/*********************************REMOVE TAGS AND COMMENT**************************************/		
	//Remove Relevant
		results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		for(int i =0; i<5; i++){
			((GuiTestObject)results[i].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		}
		
		button_removeTagbutton().click();
		removeTag("Relevant", link_rmvRelevant());
		
		reloadWithTagAndComment();
		results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		
		//Msg1 VP
		resultsColumn = results[0].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg1TaggedRemoved", "", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
	//Remove Privilege
		for(int i =0; i<5; i++){
			((GuiTestObject)results[i].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		}
		
		button_removeTagbutton().click();
		removeTag("Privilege", link_rmvPrivilege());

		reloadWithTagAndComment();
		results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);	
		//Msg2 VP
		resultsColumn = results[1].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg2TaggedRemoved", "", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
				
	//Remove Flagged
		for(int i =0; i<5; i++){
			((GuiTestObject)results[i].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		}
		
		button_removeTagbutton().click();
		removeTag("Flagged", link_rmvFlagged());
		
		reloadWithTagAndComment();
		results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		//Msg3 VP
		resultsColumn = results[2].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg3TaggedRemoved", "", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
	// Remove Work Product
		for(int i =0; i<5; i++){
			((GuiTestObject)results[i].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		}
		
		button_removeTagbutton().click();
		removeTag("Work Product", link_rmvWorkProduct());
		
		reloadWithTagAndComment();
		results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		//Msg4VP
		resultsColumn = results[3].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-12.*", false)), false);
		vpManual("Msg3TaggedRemoved", "", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
		
	//Remove Comment
		for(int i =0; i<5; i++){
			((GuiTestObject)results[i].find(atDescendant(".class", "Html.TD"), false)[1]).click();
		}
		
		text_comment().click();
		logInfo("clicked on comment input");
		button_addComment().click();
		logInfo("clicked add comment");
		waitForloading();
		button_taggOK().click();
		logInfo("clicked ok button");
		
		reloadWithTagAndComment();
		results = body().find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		//Msg5
		resultsColumn = results[4].find(atDescendant(".class", "Html.TD", "class", new RegularExpression(".*x-grid3-td-13.*", false)), false);
		vpManual("Msg4CommentRemoved", "", resultsColumn[0].getProperty(".contentText").toString().trim()).performTest();
		
	}
	
	
	private void removeTagDropdown(){
		java.awt.Rectangle bounds = (java.awt.Rectangle) html_removeTagEM().getProperty(".bounds");
		html_removeTagEM().click(atPoint(bounds.width-1,bounds.height/2));
	}
	
	
	private void removeTag(String tagName, GuiTestObject tagLink){
		removeTagDropdown();
		logInfo("Clicked remove tag dropdown arrow");
		tagLink.click();
		logInfo("Clicked on "+ tagName +" on remove tag dropdown");
		waitForloading();
		button_taggOK().click();
		logInfo("Clicked on ok button on successful removal");
	}
	
	
	private void headerColumns(boolean expander, boolean to, boolean date, boolean tampered, boolean tags, boolean comment){
		TestObject[] body = HelperClass.getActiveTabBody();
		TestObject[] viewport = body[0].find(atDescendant(".class", "Html.DIV", "class", "x-grid3-viewport"),true);	
		TestObject[] tableHeader = viewport[0].find(atDescendant(".class", "Html.TR", "class", "x-grid3-hd-row"), false);
		TestObject[] headerColumn = tableHeader[0].find(atDescendant(".class", "Html.TD", "class", new RegularExpression("x-grid3-hd.*",false)),false);
		java.awt.Rectangle columnBounds = (java.awt.Rectangle) ((GuiTestObject)headerColumn[5]).getProperty(".bounds");
		((GuiTestObject)headerColumn[5]).hover(atPoint(columnBounds.width-5,columnBounds.height/2));
		sleep(2);
		((GuiTestObject)headerColumn[5]).click(atPoint(columnBounds.width-5,columnBounds.height/2));
		logInfo("Clicked at the corner of a header column");

		
		link_columns().hover();
		logInfo("clicked column on dropdown");
		if(expander){
			link_expander().click();
			logInfo("clicked expander");
		}
		if(to){
			link_to().click();
			logInfo("clicked to");
		}
		
		if(date){
			link_date().click();
			logInfo("clicked date");
		}
		
		if(tampered){
			link_tampered().click();
			logInfo("clicked tampered");
			waitForloading();
			sleep(2);
		}
		
		if(tags){
			link_tags().click();
			logInfo("clicked tags");
		}
		
		if(comment){
			link_comment().click();
			logInfo("clicked comment");
		}
		
		sleep(2);
		waitForloading();
		html_extGen3().click(atPoint(1,1));
	}
	
	private TestObject body(){
		return HelperClass.getActiveTabBody()[0];
	}
	
	
	private void reloadWithTagAndComment(){
		setup();
		
		//Verify Tagging and Commenting
		headerColumns(false, false, false, false, true, true);
	}
	
	
	public void setup(){
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(2);
		waitForloading();
		
		//QuickSearch term should contain more than 4 message result
		text_quickSearchField0().click();
		logInfo("clicked quick search field");
		browser_htmlBrowser().inputChars("bcc");
		browser_htmlBrowser().inputKeys("{ENTER}");
		logInfo(" entered < bcc >");
		sleep(6);
	}
}