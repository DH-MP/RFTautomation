// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.NetmailSearch_General;
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
 * Script Name   : <b>adminLogin</b><br>
 * Generated     : <b>2014/07/28 4:18:57 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  July 28, 2014
 * @author Administrator
 */
public abstract class adminLoginHelper extends utilities.MySuperHelper
{
	/**
	 * htmlBrowser: with default state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject browser_htmlBrowser() 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_htmlBrowser"));
	}
	/**
	 * htmlBrowser: with specific test context and state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject browser_htmlBrowser(TestObject anchor, long flags) 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_htmlBrowser"), anchor, flags);
	}
	
	/**
	 * Continuebutton: with default state.
	 *		.text : RegularExpression(Continue|Continuer|Fortsetzen)
	 * 		.type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_continuebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_continuebutton"));
	}
	/**
	 * Continuebutton: with specific test context and state.
	 *		.text : RegularExpression(Continue|Continuer|Fortsetzen)
	 * 		.type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_continuebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_continuebutton"), anchor, flags);
	}
	
	/**
	 * OpenCasebutton: with default state.
	 *		.text : Open Case
	 * 		.type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text load-tb-icon
	 */
	protected GuiTestObject button_openCasebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_openCasebutton"));
	}
	/**
	 * OpenCasebutton: with specific test context and state.
	 *		.text : Open Case
	 * 		.type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text load-tb-icon
	 */
	protected GuiTestObject button_openCasebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_openCasebutton"), anchor, flags);
	}
	
	/**
	 * CaseListDIV_extGen166: with default state.
	 *		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_caseListDIV_extGen166() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_caseListDIV_extGen166"));
	}
	/**
	 * CaseListDIV_extGen166: with specific test context and state.
	 *		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_caseListDIV_extGen166(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_caseListDIV_extGen166"), anchor, flags);
	}
	
	/**
	 * roleSelectionWindow: with default state.
	 *		.id : roleSelectionWindow
	 * 		.className :  x-window x-window-noborder
	 * 		.class : Html.DIV
	 * 		.classIndex : 7
	 */
	protected GuiTestObject html_roleSelectionWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_roleSelectionWindow"));
	}
	/**
	 * roleSelectionWindow: with specific test context and state.
	 *		.id : roleSelectionWindow
	 * 		.className :  x-window x-window-noborder
	 * 		.class : Html.DIV
	 * 		.classIndex : 7
	 */
	protected GuiTestObject html_roleSelectionWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_roleSelectionWindow"), anchor, flags);
	}
	
	/**
	 * extComp1036: with default state.
	 *		.class : Html.DIV
	 * 		.classIndex : 17
	 */
	protected GuiTestObject html_roleSelectionWindow2() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_roleSelectionWindow2"));
	}
	/**
	 * extComp1036: with specific test context and state.
	 *		.class : Html.DIV
	 * 		.classIndex : 17
	 */
	protected GuiTestObject html_roleSelectionWindow2(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_roleSelectionWindow2"), anchor, flags);
	}
	
	/**
	 * UserListDIV_extGen80: with default state.
	 *		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_userListDIV_extGen80() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_userListDIV_extGen80"));
	}
	/**
	 * UserListDIV_extGen80: with specific test context and state.
	 *		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_userListDIV_extGen80(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_userListDIV_extGen80"), anchor, flags);
	}
	
	/**
	 * Locate and return the verification point internetE_roleSelectionWindow object in the SUT.
	 */
	protected IFtVerificationPoint internetE_roleSelectionWindowVP() 
	{
		return vp("internetE_roleSelectionWindow");
	}
	protected IFtVerificationPoint internetE_roleSelectionWindowVP(TestObject anchor)
	{
		return vp("internetE_roleSelectionWindow", anchor);
	}
	
	/**
	 * Locate and return the verification point roleSelectionWindow_roleSelectionWindow object in the SUT.
	 */
	protected IFtVerificationPoint roleSelectionWindow_roleSelectionWindowVP() 
	{
		return vp("roleSelectionWindow_roleSelectionWindow");
	}
	protected IFtVerificationPoint roleSelectionWindow_roleSelectionWindowVP(TestObject anchor)
	{
		return vp("roleSelectionWindow_roleSelectionWindow", anchor);
	}
	
	

	protected adminLoginHelper()
	{
		setScriptName("NetmailSearch_General.adminLogin");
	}
	
}

