
import resources.advanceSearchAttachment_NormalUserHelper;
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
public class advanceSearchAttachment_NormalUser extends advanceSearchAttachment_NormalUserHelper
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
	private Integer	fileSize1 = 0, 
					fileSize2 = 2000;
	public void testMain(Object[] args) 
	{
		if(args.length>0){
			filename = (String) args[0];
			fileSize1 = args[1].toString().isEmpty()? null: Integer.parseInt(args[1].toString());
			fileSize2 = args[2].toString().isEmpty()? null: Integer.parseInt(args[2].toString());
		}
		String msg = "Check if %s contains the query < %s > in the string < %s >";
		

		button_advancedSearchbutton().click();
		logInfo("Open Advance Search dropdown");
		link_attachments().click();
		logInfo("Open Attachement from dropdown");
		sleep(1);

		//Enter Advance Search Attachment
		if(!filename.isEmpty()){
			logInfo("Input <"+ filename + "> in Filenameinput field");
			checkBox_ifFilenameon().click();
			text_filename().click();
			browser_htmlBrowser().inputChars(filename);
		}

		if(fileSize1 != null && fileSize2!= null){
			logInfo("Input file size range: <"+fileSize1.toString()+">-<"+fileSize2.toString()+">");
			checkBox_ifFilesizeon().click();
			text_filesize1().click();
			browser_htmlBrowser().inputChars(fileSize1.toString());
			text_filesize2().click();
			browser_htmlBrowser().inputChars(fileSize2.toString());
		}
		
		button_advAttach_Searchsubmit().click();
		logInfo("search clicked");
		sleep(15);
		
		//Validation Steps
		TestObject[] activeResultListDiv = find(atDescendant(".tag", "DIV", "class", "x-panel x-panel-noborder x-grid-panel"), true);
		TestObject[] results = activeResultListDiv[0].find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
		
		for(TestObject result: results){
			TestObject[] columns = result.find(atDescendant(".tag", "TD"), false);
			String actualFileName = ((GuiTestObject)columns[2]).getProperty(".text").toString();
			
			if(!filename.isEmpty()){
				logInfo(String.format(msg, "FILENAME", filename, actualFileName));
				vpManual("fileNameMatchesQuery", true, actualFileName.toLowerCase().contains(filename.toLowerCase())).performTest();
			}
			
			if(fileSize1!=null && fileSize2!=null){
				((GuiTestObject)columns[2]).click();
				sleep(0.5);
				((GuiTestObject)columns[2]).doubleClick();
				sleep(0.5);
				logInfo("open result");
				sleep(3);
				TestObject[] attachmentLink = null;
				try{
					attachmentLink = html_messageWindow0().find(atDescendant(".tag", "A", ".text", actualFileName), true);
				}catch(com.rational.test.ft.AmbiguousRecognitionException e){
					((GuiTestObject)columns[2]).click();
					sleep(0.5);
					((GuiTestObject)columns[2]).doubleClick();
					sleep(0.5);
					attachmentLink = html_messageWindow0().find(atDescendant(".tag", "A", ".text", actualFileName), true);
					
				}
				//vpManual("An_attachment_link_exist_in_message", 1, attachmentLink.length).performTest();
				if(attachmentLink.length == 1){
					link_mbPropertyTab().click();
					sleep(0.5);
					logInfo("property tab clicked");
					
					//Open Attachments dropdown
					link_propertiesAttachments().click();
					sleep(0.75);
					link_propertiesAttachments().doubleClick();
					sleep(0.75);
					logInfo("attachment(s) tree expanded");
					sleep(4);
					
					//Find the attachement
					TestObject[] attachmentValue = html_attachmentsUL().find(atDescendant(".tag", "DIV", ".title", actualFileName), false);			
					TestObject attachmentRow = attachmentValue[0].getParent().getParent();
					//Open the attachement
					((GuiTestObject)attachmentRow).click();
					sleep(0.75);
					((GuiTestObject)attachmentRow).doubleClick();
					sleep(0.75);
					logInfo("attachment expanded");
					sleep(1.5);
					
					//Find size of attachment && Validate
					TestObject[] sizeDiv = attachmentRow.getParent().find(atDescendant(".tag", "UL"),true)[0]
							  									 .find(atDescendant(".tag", "LI"), false)[4]
							  									 .find(atDescendant(".tag", "DIV", "title", new RegularExpression("\\d+", false)), false);
					Integer size = Integer.parseInt(sizeDiv[0].getProperty(".text").toString());
					logInfo("Is the size <"+ size +"> of attachment within specified range: "+ fileSize1+" To "+ fileSize2);
					vpManual("Attachment_size_range", true, fileSize1 <= size && size <= fileSize2 ).performTest();
					//TODO check nested messages
				}

				//TODO body search
				
				//Close MessageBox
				while(html_mbClose().exists()){
					logInfo("result window closed");
					html_mbClose().click();
					sleep(2);
				}
			}
		}
	}
}

