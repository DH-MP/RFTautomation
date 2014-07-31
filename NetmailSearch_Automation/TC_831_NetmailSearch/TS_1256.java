package TC_831_NetmailSearch;
import resources.TC_831_NetmailSearch.TS_1256Helper;
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
public class TS_1256 extends TS_1256Helper
{
	/**
	 * Script Name   : <b>TS_1256</b>
	 * Generated     : <b>May 31, 2013 5:08:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/31
	 * @author Administrator
	 * 
	 * !!!Only works on IE because FireFox html.input has no value
	 */
	public void testMain(Object[] args) 
	{
		//Login
				Object[] ls = {null, null, dpBoolean("failToLogin")};
				callScript("loginScript", ls);
				if(dpBoolean("failToLogin"))
					return;

		//Admin Login
				Object[] al = {dpString("caseListName"), "Super User"};
				callScript("adminLogin", al);
		
		sleep(3);
		
		logInfo("By Default, panel are not shown");
		vpManual("bottom_pain_notVisible", false, html_bottomPanel_extComp1307().exists()).performTest();
		vpManual("side_pain_notVisible", false, html_topPanel_extComp1309().exists()).performTest();
		
		
		//Choose display side panel, 
		button_preferencesbutton().click();
		link_previewMenu().hover();
		link_sidePane().click();
		sleep(2);
		
		//open a message 
		TestObject[] messages = find(atDescendant("class", "x-grid3-row-table", ".class", "Html.TABLE"), true);
		((GuiTestObject)messages[4]).doubleClick();
		sleep(3);
		
		//Get Messagebox Data
		String mbFrom = (String) text_mbFrom().getProperty(".value");
		String mbSubject = (String) text_mbSubject().getProperty(".value");
		String mbTo = (String) text_mbTO().getProperty(".value");
		String mbMessage = (String) document_mbMessage().getProperty(".text");
		html_messageBoxClose().click();
		
		//Validate visibility and data of side panel against messages
		logInfo("Validate visibility and data of side panel against messages");
		vpManual("side_pain_visible", true, html_topPanel_extComp1309().ensureObjectIsVisible()).performTest();
		vpManual("Side_Panel_From_Match", mbFrom, (String) text_spFrom().getProperty(".value")).performTest();
		vpManual("Side_Panel_To_Match", mbTo, (String) text_spTO().getProperty(".value")).performTest();
		vpManual("Side_Panel_Subject_Match", mbSubject, (String) text_spSubject().getProperty(".value")).performTest();
		vpManual("Side_Panel_Message_Match", mbMessage, (String) document_spMessage().getProperty(".text")).performTest();
		html_extGen3().hover(atPoint(1,1));
		sleep(3);
//		html_topPanel_extComp1309().performTest( sidePanel_IEVP() );
		
		//Click bottom pannel and validate visibility & data of side panel
		logInfo("Validate visibility and data of bottom panel against messages");
		button_preferencesbutton().click();
		link_previewMenu().hover();
		link_bottomPane().click();
		sleep(3);
		vpManual("bottom_pain_visible", true, html_bottomPanel_extComp1307().ensureObjectIsVisible()).performTest();
		vpManual("Bottom_Panel_From_Match", mbFrom, (String) text_bpFROM().getProperty(".value")).performTest();
		vpManual("Bottom_Panel_To_Match", mbTo, (String) text_bpTO().getProperty(".value")).performTest();
		vpManual("Bottom_Panel_Subject_Match", mbSubject, (String) text_bpSubject().getProperty(".value")).performTest();
		vpManual("Bottom_Panel_Message_Match", mbMessage, (String) document_bpMessage().getProperty(".text")).performTest();
		
		//Validate both panel are not visible when chosen no preview
		logInfo("Validate that no panel are visible when choosing no preview");
		button_preferencesbutton().click();
		link_previewMenu().hover();
		link_noPreview().click();
		sleep(2);
		
		vpManual("bottom_pain_notVisible", false, html_bottomPanel_extComp1307().exists()).performTest();
		vpManual("side_pain_notVisible", false, html_topPanel_extComp1309().exists()).performTest();
		unregisterAll();
	}
}

