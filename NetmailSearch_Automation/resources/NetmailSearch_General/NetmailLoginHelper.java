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
 * Script Name   : <b>NetmailLogin</b><br>
 * Generated     : <b>2014/10/22 1:56:43 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  October 22, 2014
 * @author Administrator
 */
public abstract class NetmailLoginHelper extends utilities.MySuperHelper
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
	 * Languagebutton: with default state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_languagebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_languagebutton"));
	}
	/**
	 * Languagebutton: with specific test context and state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_languagebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_languagebutton"), anchor, flags);
	}
	
	/**
	 * Loginbutton: with default state.
	 *		.type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_loginbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_loginbutton"));
	}
	/**
	 * Loginbutton: with specific test context and state.
	 *		.type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_loginbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_loginbutton"), anchor, flags);
	}
	
	/**
	 * WrongPassword_OKbutton: with default state.
	 *		.text : OK
	 * 		.type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_wrongPassword_OKbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_wrongPassword_OKbutton"));
	}
	/**
	 * WrongPassword_OKbutton: with specific test context and state.
	 *		.text : OK
	 * 		.type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_wrongPassword_OKbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_wrongPassword_OKbutton"), anchor, flags);
	}
	
	/**
	 * body: with default state.
	 *		.text : RegularExpression(.*)
	 * 		.title : 
	 * 		.class : Html.BODY
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_body() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_body"));
	}
	/**
	 * body: with specific test context and state.
	 *		.text : RegularExpression(.*)
	 * 		.title : 
	 * 		.class : Html.BODY
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_body(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_body"), anchor, flags);
	}
	
	/**
	 * extGen13: with default state.
	 *		.className : x-panel-body x-panel-body-noheader x-panel-body-noborder
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_extGen13() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_extGen13"));
	}
	/**
	 * extGen13: with specific test context and state.
	 *		.className : x-panel-body x-panel-body-noheader x-panel-body-noborder
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_extGen13(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_extGen13"), anchor, flags);
	}
	
	/**
	 * languageUL: with default state.
	 *		.class : Html.UL
	 * 		class : x-menu-list
	 */
	protected GuiTestObject html_languageUL() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_languageUL"));
	}
	/**
	 * languageUL: with specific test context and state.
	 *		.class : Html.UL
	 * 		class : x-menu-list
	 */
	protected GuiTestObject html_languageUL(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_languageUL"), anchor, flags);
	}
	
	/**
	 * loginWindow: with default state.
	 *		.id : loginWindow
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_loginWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_loginWindow"));
	}
	/**
	 * loginWindow: with specific test context and state.
	 *		.id : loginWindow
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_loginWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_loginWindow"), anchor, flags);
	}
	
	/**
	 * loginWindowDynamic: with default state.
	 *		.offsetTop : RegularExpression(^[1-9][0-9]*$)
	 * 		.screenLeft : RegularExpression(^[1-9][0-9]*$)
	 * 		.id : loginWindow
	 * 		.screenTop : RegularExpression(^[1-9][0-9]*$)
	 * 		.class : Html.DIV
	 * 		.offsetLeft : RegularExpression(^[1-9][0-9]*$)
	 */
	protected GuiTestObject html_loginWindowDynamic() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_loginWindowDynamic"));
	}
	/**
	 * loginWindowDynamic: with specific test context and state.
	 *		.offsetTop : RegularExpression(^[1-9][0-9]*$)
	 * 		.screenLeft : RegularExpression(^[1-9][0-9]*$)
	 * 		.id : loginWindow
	 * 		.screenTop : RegularExpression(^[1-9][0-9]*$)
	 * 		.class : Html.DIV
	 * 		.offsetLeft : RegularExpression(^[1-9][0-9]*$)
	 */
	protected GuiTestObject html_loginWindowDynamic(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_loginWindowDynamic"), anchor, flags);
	}
	
	/**
	 * WrongPasswordWindow: with default state.
	 *		.text : RegularExpression(Login Failed.*)
	 * 		.className :  x-window x-window-plain x-window-dlg
	 * 		.class : Html.DIV
	 * 		.contentText : RegularExpression((\s)*Login Failed.*)
	 */
	protected GuiTestObject html_wrongPasswordWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_wrongPasswordWindow"));
	}
	/**
	 * WrongPasswordWindow: with specific test context and state.
	 *		.text : RegularExpression(Login Failed.*)
	 * 		.className :  x-window x-window-plain x-window-dlg
	 * 		.class : Html.DIV
	 * 		.contentText : RegularExpression((\s)*Login Failed.*)
	 */
	protected GuiTestObject html_wrongPasswordWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_wrongPasswordWindow"), anchor, flags);
	}
	
	/**
	 * pwd: with default state.
	 *		.id : tmaLoginPassword
	 * 		.class : RegularExpression(Html\.INPUT\.password|Html\.INPUT)
	 */
	protected TextGuiTestObject text_pwd() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_pwd"));
	}
	/**
	 * pwd: with specific test context and state.
	 *		.id : tmaLoginPassword
	 * 		.class : RegularExpression(Html\.INPUT\.password|Html\.INPUT)
	 */
	protected TextGuiTestObject text_pwd(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_pwd"), anchor, flags);
	}
	
	/**
	 * uid: with default state.
	 *		.id : tmaLoginUsername
	 * 		.class : RegularExpression(Html\.INPUT\.text|Html\.INPUT)
	 * 		.name : uid
	 */
	protected TextGuiTestObject text_username() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_username"));
	}
	/**
	 * uid: with specific test context and state.
	 *		.id : tmaLoginUsername
	 * 		.class : RegularExpression(Html\.INPUT\.text|Html\.INPUT)
	 * 		.name : uid
	 */
	protected TextGuiTestObject text_username(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_username"), anchor, flags);
	}
	
	/**
	 * Locate and return the verification point loginWindow_Firefox object in the SUT.
	 */
	protected IFtVerificationPoint loginWindow_FirefoxVP() 
	{
		return vp("loginWindow_Firefox");
	}
	protected IFtVerificationPoint loginWindow_FirefoxVP(TestObject anchor)
	{
		return vp("loginWindow_Firefox", anchor);
	}
	
	/**
	 * Locate and return the verification point loginWindow_IE object in the SUT.
	 */
	protected IFtVerificationPoint loginWindow_IEVP() 
	{
		return vp("loginWindow_IE");
	}
	protected IFtVerificationPoint loginWindow_IEVP(TestObject anchor)
	{
		return vp("loginWindow_IE", anchor);
	}
	
	

	protected NetmailLoginHelper()
	{
		setScriptName("NetmailSearch_General.NetmailLogin");
	}
	
}

