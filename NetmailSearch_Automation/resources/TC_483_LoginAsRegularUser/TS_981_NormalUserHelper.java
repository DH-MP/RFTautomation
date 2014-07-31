// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.TC_483_LoginAsRegularUser;
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
 * Script Name   : <b>TS_981_NormalUser</b><br>
 * Generated     : <b>2014/03/18 4:15:41 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  March 18, 2014
 * @author Administrator
 */
public abstract class TS_981_NormalUserHelper extends utilities.MySuperHelper
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
	 * AdvancedSearchbutton: with default state.
	 *		.text : Advanced Search
	 * 		type : button
	 * 		.class : Html.BUTTON
	 * 		class : RegularExpression(.*x-btn-text)
	 */
	protected GuiTestObject button_advancedSearchbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_advancedSearchbutton"));
	}
	/**
	 * AdvancedSearchbutton: with specific test context and state.
	 *		.text : Advanced Search
	 * 		type : button
	 * 		.class : Html.BUTTON
	 * 		class : RegularExpression(.*x-btn-text)
	 */
	protected GuiTestObject button_advancedSearchbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_advancedSearchbutton"), anchor, flags);
	}
	
	/**
	 * firstPageButton: with default state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text x-tbar-page-first
	 */
	protected GuiTestObject button_firstPageButton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_firstPageButton"));
	}
	/**
	 * firstPageButton: with specific test context and state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text x-tbar-page-first
	 */
	protected GuiTestObject button_firstPageButton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_firstPageButton"), anchor, flags);
	}
	
	/**
	 * gwLoginSubmit: with default state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Login    
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : submit
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_gwLoginSubmit() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_gwLoginSubmit"));
	}
	/**
	 * gwLoginSubmit: with specific test context and state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Login    
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : submit
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_gwLoginSubmit(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_gwLoginSubmit"), anchor, flags);
	}
	
	/**
	 * lastPageButton: with default state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text x-tbar-page-last
	 */
	protected GuiTestObject button_lastPageButton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_lastPageButton"));
	}
	/**
	 * lastPageButton: with specific test context and state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text x-tbar-page-last
	 */
	protected GuiTestObject button_lastPageButton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_lastPageButton"), anchor, flags);
	}
	
	/**
	 * mbForwardMessage: with default state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text forward-tb-icon
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_mbForwardMessage() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_mbForwardMessage"));
	}
	/**
	 * mbForwardMessage: with specific test context and state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text forward-tb-icon
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_mbForwardMessage(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_mbForwardMessage"), anchor, flags);
	}
	
	/**
	 * mbFwdCancelbutton: with default state.
	 *		.text : Cancel
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text cancel-tb-icon
	 */
	protected GuiTestObject button_mbFwdCancelbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_mbFwdCancelbutton"));
	}
	/**
	 * mbFwdCancelbutton: with specific test context and state.
	 *		.text : Cancel
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text cancel-tb-icon
	 */
	protected GuiTestObject button_mbFwdCancelbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_mbFwdCancelbutton"), anchor, flags);
	}
	
	/**
	 * mbFwdForwardbutton: with default state.
	 *		.text : Forward
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text send-tb-icon
	 */
	protected GuiTestObject button_mbFwdForwardbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_mbFwdForwardbutton"));
	}
	/**
	 * mbFwdForwardbutton: with specific test context and state.
	 *		.text : Forward
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text send-tb-icon
	 */
	protected GuiTestObject button_mbFwdForwardbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_mbFwdForwardbutton"), anchor, flags);
	}
	
	/**
	 * nextPageButton: with default state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text x-tbar-page-next
	 */
	protected GuiTestObject button_nextPageButton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_nextPageButton"));
	}
	/**
	 * nextPageButton: with specific test context and state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text x-tbar-page-next
	 */
	protected GuiTestObject button_nextPageButton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_nextPageButton"), anchor, flags);
	}
	
	/**
	 * prevPageButton: with default state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text x-tbar-page-prev
	 */
	protected GuiTestObject button_prevPageButton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_prevPageButton"));
	}
	/**
	 * prevPageButton: with specific test context and state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text x-tbar-page-prev
	 */
	protected GuiTestObject button_prevPageButton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_prevPageButton"), anchor, flags);
	}
	
	/**
	 * gwMessageBody: with default state.
	 *		.class : Html.HtmlDocument
	 */
	protected DocumentTestObject document_gwMessageBody() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_gwMessageBody"));
	}
	/**
	 * gwMessageBody: with specific test context and state.
	 *		.class : Html.HtmlDocument
	 */
	protected DocumentTestObject document_gwMessageBody(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_gwMessageBody"), anchor, flags);
	}
	
	/**
	 * mbFwdMessageBody: with default state.
	 *		.title : 
	 * 		.class : Html.HtmlDocument
	 * 		.url : http://10.10.24.80:8888/index.html
	 */
	protected DocumentTestObject document_mbFwdMessageBody() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_mbFwdMessageBody"));
	}
	/**
	 * mbFwdMessageBody: with specific test context and state.
	 *		.title : 
	 * 		.class : Html.HtmlDocument
	 * 		.url : http://10.10.24.80:8888/index.html
	 */
	protected DocumentTestObject document_mbFwdMessageBody(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_mbFwdMessageBody"), anchor, flags);
	}
	
	/**
	 * messageBody: with default state.
	 *		.class : Html.HtmlDocument
	 * 		.url : about:blank
	 */
	protected DocumentTestObject document_messageBody() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_messageBody"));
	}
	/**
	 * messageBody: with specific test context and state.
	 *		.class : Html.HtmlDocument
	 * 		.url : about:blank
	 */
	protected DocumentTestObject document_messageBody(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_messageBody"), anchor, flags);
	}
	
	/**
	 * extGen467: with default state.
	 *		.class : Html.FORM
	 * 		class : x-panel-body x-panel-body-noheader x-panel-body-noborder x-form
	 */
	protected GuiTestObject form_extGen467() 
	{
		return new GuiTestObject(
                        getMappedTestObject("form_extGen467"));
	}
	/**
	 * extGen467: with specific test context and state.
	 *		.class : Html.FORM
	 * 		class : x-panel-body x-panel-body-noheader x-panel-body-noborder x-form
	 */
	protected GuiTestObject form_extGen467(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("form_extGen467"), anchor, flags);
	}
	
	/**
	 * Close: with default state.
	 *		.text : 
	 * 		.title : Close
	 * 		.class : Html.LI
	 * 		class : tbbutton tbnotext
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_close() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_close"));
	}
	/**
	 * Close: with specific test context and state.
	 *		.text : 
	 * 		.title : Close
	 * 		.class : Html.LI
	 * 		class : tbbutton tbnotext
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_close(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_close"), anchor, flags);
	}
	
	/**
	 * extGen164: with default state.
	 *		.className : x-grid3-scroller
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_extGen164() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_extGen164"));
	}
	/**
	 * extGen164: with specific test context and state.
	 *		.className : x-grid3-scroller
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_extGen164(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_extGen164"), anchor, flags);
	}
	
	/**
	 * folderTree0: with default state.
	 *		.id : folderTree0
	 * 		.className :  x-panel folder x-tree x-border-panel
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_folderTree0() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderTree0"));
	}
	/**
	 * folderTree0: with specific test context and state.
	 *		.id : folderTree0
	 * 		.className :  x-panel folder x-tree x-border-panel
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_folderTree0(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderTree0"), anchor, flags);
	}
	
	/**
	 * gwDeleteMessage: with default state.
	 *		.text : Delete
	 * 		.title : Delete
	 * 		.class : Html.LI
	 * 		.classIndex : 8
	 * 		class : tbbutton
	 */
	protected GuiTestObject html_gwDeleteMessage() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_gwDeleteMessage"));
	}
	/**
	 * gwDeleteMessage: with specific test context and state.
	 *		.text : Delete
	 * 		.title : Delete
	 * 		.class : Html.LI
	 * 		.classIndex : 8
	 * 		class : tbbutton
	 */
	protected GuiTestObject html_gwDeleteMessage(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_gwDeleteMessage"), anchor, flags);
	}
	
	/**
	 * idLogin: with default state.
	 *		.id : idLogin
	 * 		.className : loginRootContainer phoneWidth
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_idLogin() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_idLogin"));
	}
	/**
	 * idLogin: with specific test context and state.
	 *		.id : idLogin
	 * 		.className : loginRootContainer phoneWidth
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_idLogin(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_idLogin"), anchor, flags);
	}
	
	/**
	 * idMsgBodyPlainText: with default state.
	 *		.className : msgbody_inner
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_idMsgBodyPlainText() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_idMsgBodyPlainText"));
	}
	/**
	 * idMsgBodyPlainText: with specific test context and state.
	 *		.className : msgbody_inner
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_idMsgBodyPlainText(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_idMsgBodyPlainText"), anchor, flags);
	}
	
	/**
	 * leftPanelInnerContrainer: with default state.
	 *		.className : x-panel-body x-panel-body-noheader
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_leftPanelInnerContrainer() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_leftPanelInnerContrainer"));
	}
	/**
	 * leftPanelInnerContrainer: with specific test context and state.
	 *		.className : x-panel-body x-panel-body-noheader
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_leftPanelInnerContrainer(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_leftPanelInnerContrainer"), anchor, flags);
	}
	
	/**
	 * leftPanelUL: with default state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_leftPanelUL() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_leftPanelUL"));
	}
	/**
	 * leftPanelUL: with specific test context and state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_leftPanelUL(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_leftPanelUL"), anchor, flags);
	}
	
	/**
	 * location: with default state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_location() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_location"));
	}
	/**
	 * location: with specific test context and state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_location(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_location"), anchor, flags);
	}
	
	/**
	 * mbCLose: with default state.
	 *		.className : x-tool x-tool-close
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_mbCLose() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_mbCLose"));
	}
	/**
	 * mbCLose: with specific test context and state.
	 *		.className : x-tool x-tool-close
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_mbCLose(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_mbCLose"), anchor, flags);
	}
	
	/**
	 * msglist: with default state.
	 *		.id : msglist
	 * 		.className : 
	 * 		.class : Html.DIV
	 * 		.classIndex : 2
	 */
	protected GuiTestObject html_msglist() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_msglist"));
	}
	/**
	 * msglist: with specific test context and state.
	 *		.id : msglist
	 * 		.className : 
	 * 		.class : Html.DIV
	 * 		.classIndex : 2
	 */
	protected GuiTestObject html_msglist(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_msglist"), anchor, flags);
	}
	
	/**
	 * clearSearch: with default state.
	 *		.text : (clear search)
	 * 		.class : Html.A
	 * 		class : x-menu-item
	 */
	protected GuiTestObject link_clearSearch() 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_clearSearch"));
	}
	/**
	 * clearSearch: with specific test context and state.
	 *		.text : (clear search)
	 * 		.class : Html.A
	 * 		class : x-menu-item
	 */
	protected GuiTestObject link_clearSearch(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_clearSearch"), anchor, flags);
	}
	
	/**
	 * OpenThisMessage: with default state.
	 *		.text : Envelope
	 * 		.href : http://10.10.24.55/gw/webacc?User.context=5e8f34fbf533b5a2c94de467bc0f846b895c01 ...
	 * 		.title : Open this message
	 * 		.class : Html.A
	 * 		.name : 
	 * 		class : item-link
	 * 		.classIndex : 0
	 */
	protected GuiTestObject link_openThisMessage() 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_openThisMessage"));
	}
	/**
	 * OpenThisMessage: with specific test context and state.
	 *		.text : Envelope
	 * 		.href : http://10.10.24.55/gw/webacc?User.context=5e8f34fbf533b5a2c94de467bc0f846b895c01 ...
	 * 		.title : Open this message
	 * 		.class : Html.A
	 * 		.name : 
	 * 		class : item-link
	 * 		.classIndex : 0
	 */
	protected GuiTestObject link_openThisMessage(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_openThisMessage"), anchor, flags);
	}
	
	/**
	 * messageInformation: with default state.
	 *		.class : Html.TABLE
	 */
	protected StatelessGuiSubitemTestObject table_messageInformation() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_messageInformation"));
	}
	/**
	 * messageInformation: with specific test context and state.
	 *		.class : Html.TABLE
	 */
	protected StatelessGuiSubitemTestObject table_messageInformation(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_messageInformation"), anchor, flags);
	}
	
	/**
	 * currentPage: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		class : x-form-text x-form-field x-form-num-field x-tbar-page-number
	 */
	protected TextGuiTestObject text_currentPage() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_currentPage"));
	}
	/**
	 * currentPage: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		class : x-form-text x-form-field x-form-num-field x-tbar-page-number
	 */
	protected TextGuiTestObject text_currentPage(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_currentPage"), anchor, flags);
	}
	
	/**
	 * gwUserId: with default state.
	 *		.text : 
	 * 		type : text
	 * 		.id : username
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : User.id
	 * 		.classIndex : 0
	 * 		class : gray_bg_9
	 */
	protected TextGuiTestObject text_gwUserId() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_gwUserId"));
	}
	/**
	 * gwUserId: with specific test context and state.
	 *		.text : 
	 * 		type : text
	 * 		.id : username
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : User.id
	 * 		.classIndex : 0
	 * 		class : gray_bg_9
	 */
	protected TextGuiTestObject text_gwUserId(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_gwUserId"), anchor, flags);
	}
	
	/**
	 * gwUserPassword: with default state.
	 *		.id : password
	 * 		.type : password
	 * 		.title : 
	 * 		.class : Html.INPUT.password
	 * 		.name : User.password
	 * 		.classIndex : 0
	 * 		class : gray_bg_9
	 */
	protected TextGuiTestObject text_gwUserPassword() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_gwUserPassword"));
	}
	/**
	 * gwUserPassword: with specific test context and state.
	 *		.id : password
	 * 		.type : password
	 * 		.title : 
	 * 		.class : Html.INPUT.password
	 * 		.name : User.password
	 * 		.classIndex : 0
	 * 		class : gray_bg_9
	 */
	protected TextGuiTestObject text_gwUserPassword(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_gwUserPassword"), anchor, flags);
	}
	
	/**
	 * mbCC: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : cc
	 * 		class : x-form-text x-form-field x-trigger-noedit x-form-empty-field
	 */
	protected TextGuiTestObject text_mbCC() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbCC"));
	}
	/**
	 * mbCC: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : cc
	 * 		class : x-form-text x-form-field x-trigger-noedit x-form-empty-field
	 */
	protected TextGuiTestObject text_mbCC(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbCC"), anchor, flags);
	}
	
	/**
	 * mbFrom: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : from
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFrom() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFrom"));
	}
	/**
	 * mbFrom: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : from
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFrom(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFrom"), anchor, flags);
	}
	
	/**
	 * mbFwdCC: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : cc
	 * 		class :  x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFwdCC() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFwdCC"));
	}
	/**
	 * mbFwdCC: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : cc
	 * 		class :  x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFwdCC(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFwdCC"), anchor, flags);
	}
	
	/**
	 * mbFwdSubject: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : subject
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFwdSubject() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFwdSubject"));
	}
	/**
	 * mbFwdSubject: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : subject
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFwdSubject(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFwdSubject"), anchor, flags);
	}
	
	/**
	 * mbFwdTo: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : to
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFwdTo() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFwdTo"));
	}
	/**
	 * mbFwdTo: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : to
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbFwdTo(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbFwdTo"), anchor, flags);
	}
	
	/**
	 * mbSubject: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : subject
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbSubject() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbSubject"));
	}
	/**
	 * mbSubject: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : subject
	 * 		class : x-form-text x-form-field
	 */
	protected TextGuiTestObject text_mbSubject(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbSubject"), anchor, flags);
	}
	
	/**
	 * mbTO: with default state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : to
	 * 		class : x-form-text x-form-field x-trigger-noedit x-form-empty-field
	 */
	protected TextGuiTestObject text_mbTO() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbTO"));
	}
	/**
	 * mbTO: with specific test context and state.
	 *		type : text
	 * 		.class : Html.INPUT.text
	 * 		name : to
	 * 		class : x-form-text x-form-field x-trigger-noedit x-form-empty-field
	 */
	protected TextGuiTestObject text_mbTO(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_mbTO"), anchor, flags);
	}
	
	/**
	 * quickSearchField0: with default state.
	 *		type : text
	 * 		.id : quickSearchField0
	 * 		.class : Html.INPUT.text
	 * 		name : quickSearchField0
	 */
	protected TextGuiTestObject text_quickSearchField0() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_quickSearchField0"));
	}
	/**
	 * quickSearchField0: with specific test context and state.
	 *		type : text
	 * 		.id : quickSearchField0
	 * 		.class : Html.INPUT.text
	 * 		name : quickSearchField0
	 */
	protected TextGuiTestObject text_quickSearchField0(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_quickSearchField0"), anchor, flags);
	}
	
	/**
	 * Locate and return the verification point validateSearchCleared object in the SUT.
	 */
	protected IFtVerificationPoint validateSearchClearedVP() 
	{
		return vp("validateSearchCleared");
	}
	protected IFtVerificationPoint validateSearchClearedVP(TestObject anchor)
	{
		return vp("validateSearchCleared", anchor);
	}
	
	

	protected TS_981_NormalUserHelper()
	{
		setScriptName("TC_483_LoginAsRegularUser.TS_981_NormalUser");
	}
	
}

