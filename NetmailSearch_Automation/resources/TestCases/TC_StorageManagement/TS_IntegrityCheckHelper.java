// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.TestCases.TC_StorageManagement;
import utilities.MySuperHelper;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Script Name   : <b>TS_IntegrityCheck</b><br>
 * Generated     : <b>2015/01/12 12:46:53 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  January 12, 2015
 * @author Administrator
 */
public abstract class TS_IntegrityCheckHelper extends utilities.MySuperHelper
{
	/**
	 * htmlBrowser: with default state.
	 *		.class : Html.HtmlBrowser
	 */
	protected BrowserTestObject browser_htmlBrowser() 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_htmlBrowser"));
	}
	/**
	 * htmlBrowser: with specific test context and state.
	 *		.class : Html.HtmlBrowser
	 */
	protected BrowserTestObject browser_htmlBrowser(TestObject anchor, long flags) 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_htmlBrowser"), anchor, flags);
	}
	
	/**
	 * HtmlDialogButtonOK: with default state.
	 *		.text : OK
	 * 		.class : Html.DialogButton
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_htmlDialogButtonOK() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_htmlDialogButtonOK"));
	}
	/**
	 * HtmlDialogButtonOK: with specific test context and state.
	 *		.text : OK
	 * 		.class : Html.DialogButton
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_htmlDialogButtonOK(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_htmlDialogButtonOK"), anchor, flags);
	}
	
	/**
	 * CRCCheck: with default state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_CRC
	 * 		.classIndex : 1
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_crcCheck() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_crcCheck"));
	}
	/**
	 * CRCCheck: with specific test context and state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_CRC
	 * 		.classIndex : 1
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_crcCheck(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_crcCheck"), anchor, flags);
	}
	
	/**
	 * ReIndexMissingMsg: with default state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ReIndexMissingMsg
	 * 		value : on
	 * 		.classIndex : 4
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_reIndexMissingMsg() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_reIndexMissingMsg"));
	}
	/**
	 * ReIndexMissingMsg: with specific test context and state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ReIndexMissingMsg
	 * 		value : on
	 * 		.classIndex : 4
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_reIndexMissingMsg(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_reIndexMissingMsg"), anchor, flags);
	}
	
	/**
	 * RepairAudits: with default state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_RepairAudits
	 * 		.classIndex : 2
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_repairAudits() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_repairAudits"));
	}
	/**
	 * RepairAudits: with specific test context and state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_RepairAudits
	 * 		.classIndex : 2
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_repairAudits(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_repairAudits"), anchor, flags);
	}
	
	/**
	 * ReportGaps: with default state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ReportGaps
	 * 		value : on
	 * 		.classIndex : 5
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_reportGaps() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_reportGaps"));
	}
	/**
	 * ReportGaps: with specific test context and state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ReportGaps
	 * 		value : on
	 * 		.classIndex : 5
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_reportGaps(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_reportGaps"), anchor, flags);
	}
	
	/**
	 * validateIfMsgIndexed: with default state.
	 *		type : checkbox
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ValidateIfMsgIndexed
	 * 		.classIndex : 3
	 */
	protected ToggleGUITestObject checkBox_validateIfMsgIndexed() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_validateIfMsgIndexed"));
	}
	/**
	 * validateIfMsgIndexed: with specific test context and state.
	 *		type : checkbox
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ValidateIfMsgIndexed
	 * 		.classIndex : 3
	 */
	protected ToggleGUITestObject checkBox_validateIfMsgIndexed(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_validateIfMsgIndexed"), anchor, flags);
	}
	
	/**
	 * validateIntegralRef: with default state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ValidateIntegralRef
	 * 		value : on
	 * 		.classIndex : 0
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_validateIntegralRef() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_validateIntegralRef"));
	}
	/**
	 * validateIntegralRef: with specific test context and state.
	 *		type : checkbox
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.checkbox
	 * 		name : CB_IC_ValidateIntegralRef
	 * 		value : on
	 * 		.classIndex : 0
	 * 		class : 
	 */
	protected ToggleGUITestObject checkBox_validateIntegralRef(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("checkBox_validateIntegralRef"), anchor, flags);
	}
	
	/**
	 * netmail: with default state.
	 *		.title : netmail
	 * 		.class : Html.HtmlDocument
	 */
	protected DocumentTestObject document_netmail() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_netmail"));
	}
	/**
	 * netmail: with specific test context and state.
	 *		.title : netmail
	 * 		.class : Html.HtmlDocument
	 */
	protected DocumentTestObject document_netmail(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_netmail"), anchor, flags);
	}
	
	/**
	 * Details: with default state.
	 *		.text : Integrity Check Date range: From: To: ( Note: the dates are file's creation date ...
	 * 		.title : 
	 * 		.class : Html.FORM
	 * 		.name : Details
	 * 		class : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject form_details() 
	{
		return new GuiTestObject(
                        getMappedTestObject("form_details"));
	}
	/**
	 * Details: with specific test context and state.
	 *		.text : Integrity Check Date range: From: To: ( Note: the dates are file's creation date ...
	 * 		.title : 
	 * 		.class : Html.FORM
	 * 		.name : Details
	 * 		class : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject form_details(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("form_details"), anchor, flags);
	}
	
	/**
	 * InvalidDateFormat: with default state.
	 *		.text : Invalid date format: 'asdsda'. Format accepted is dd/mm/yyyy.
	 * 		.class : Html.DialogStatic
	 * 		.classIndex : 1
	 */
	protected GuiTestObject label_invalidDateFormat() 
	{
		return new GuiTestObject(
                        getMappedTestObject("label_invalidDateFormat"));
	}
	/**
	 * InvalidDateFormat: with specific test context and state.
	 *		.text : Invalid date format: 'asdsda'. Format accepted is dd/mm/yyyy.
	 * 		.class : Html.DialogStatic
	 * 		.classIndex : 1
	 */
	protected GuiTestObject label_invalidDateFormat(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("label_invalidDateFormat"), anchor, flags);
	}
	
	/**
	 * CSelect_ArchiveLocationList: with default state.
	 *		.text : ALS RIF local Export loc1 ATI strip LargeUser LargeUser2 test GVautomation
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : CSelect_ArchiveLocationList
	 * 		class : 
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject list_cSelect_ArchiveLocationLi() 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("list_cSelect_ArchiveLocationLi"));
	}
	/**
	 * CSelect_ArchiveLocationList: with specific test context and state.
	 *		.text : ALS RIF local Export loc1 ATI strip LargeUser LargeUser2 test GVautomation
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : CSelect_ArchiveLocationList
	 * 		class : 
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject list_cSelect_ArchiveLocationLi(TestObject anchor, long flags) 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("list_cSelect_ArchiveLocationLi"), anchor, flags);
	}
	
	/**
	 * IntegrityCheck: with default state.
	 *		.type : radio
	 * 		.class : Html.INPUT.radio
	 * 		.name : CRG_Mode
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject radioButton_integrityCheck() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("radioButton_integrityCheck"));
	}
	/**
	 * IntegrityCheck: with specific test context and state.
	 *		.type : radio
	 * 		.class : Html.INPUT.radio
	 * 		.name : CRG_Mode
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject radioButton_integrityCheck(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("radioButton_integrityCheck"), anchor, flags);
	}
	
	/**
	 * idIntegrity: with default state.
	 *		.class : Html.TABLE
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_idIntegrity() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_idIntegrity"));
	}
	/**
	 * idIntegrity: with specific test context and state.
	 *		.class : Html.TABLE
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_idIntegrity(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_idIntegrity"), anchor, flags);
	}
	
	/**
	 * reportTable: with default state.
	 *		.class : Html.TABLE
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_reportTable() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_reportTable"));
	}
	/**
	 * reportTable: with specific test context and state.
	 *		.class : Html.TABLE
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_reportTable(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_reportTable"), anchor, flags);
	}
	
	/**
	 * dateRangeFrom: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : CText_IC_DRFrom
	 * 		.classIndex : 0
	 * 		class : clsText
	 */
	protected TextGuiTestObject text_dateRangeFrom() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_dateRangeFrom"));
	}
	/**
	 * dateRangeFrom: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : CText_IC_DRFrom
	 * 		.classIndex : 0
	 * 		class : clsText
	 */
	protected TextGuiTestObject text_dateRangeFrom(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_dateRangeFrom"), anchor, flags);
	}
	
	/**
	 * dateRangeTo: with default state.
	 *		.text : 
	 * 		type : text
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : CText_IC_DRTo
	 * 		.classIndex : 1
	 * 		class : clsText
	 */
	protected TextGuiTestObject text_dateRangeTo() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_dateRangeTo"));
	}
	/**
	 * dateRangeTo: with specific test context and state.
	 *		.text : 
	 * 		type : text
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : CText_IC_DRTo
	 * 		.classIndex : 1
	 * 		class : clsText
	 */
	protected TextGuiTestObject text_dateRangeTo(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_dateRangeTo"), anchor, flags);
	}
	
	/**
	 * reportGapsDays: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : CText_IC_ReportGapsDays
	 * 		.classIndex : 2
	 */
	protected TextGuiTestObject text_reportGapsDays() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_reportGapsDays"));
	}
	/**
	 * reportGapsDays: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : CText_IC_ReportGapsDays
	 * 		.classIndex : 2
	 */
	protected TextGuiTestObject text_reportGapsDays(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_reportGapsDays"), anchor, flags);
	}
	
	

	protected TS_IntegrityCheckHelper()
	{
		setScriptName("TestCases.TC_StorageManagement.TS_IntegrityCheck");
	}
	
}
