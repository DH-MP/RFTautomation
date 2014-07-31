package TC_978_ExportPortableNetmailSearch;
import resources.TC_978_ExportPortableNetmailSearch.TS_990_AdvanceSearch_Attachment_NetmailLiteHelper;
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
public class TS_990_AdvanceSearch_Attachment_NetmailLite extends TS_990_AdvanceSearch_Attachment_NetmailLiteHelper
{
	/**
	 * Script Name   : <b>TS_990_AdvanceSearch_Attachment_NetmailLite</b>
	 * Generated     : <b>Oct 1, 2013 2:33:03 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/01
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(2);
		waitForloading();
		
		clearSearch();
		waitForloading();
		Object[] aANM = {dpString("fileName"), dpInt("fileSize1"), dpInt("fileSize2")};
		callScript("NetmailSearch_AdvanceSearch.Attachment_NormalUser", aANM);
		clearSearch();
		waitForloading();
	}
	
	
	private void clearSearch(){	
		sleep(1);
		button_advancedSearchbutton().click();
		link_clearSearch().click();
		sleep(3);
	}
	
}

