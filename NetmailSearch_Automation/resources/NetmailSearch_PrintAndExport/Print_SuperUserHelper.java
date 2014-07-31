// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.NetmailSearch_PrintAndExport;
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
 * Script Name   : <b>Print_SuperUser</b><br>
 * Generated     : <b>2014/07/14 11:17:02 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  July 14, 2014
 * @author Administrator
 */
public abstract class Print_SuperUserHelper extends utilities.MySuperHelper
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
	 * Backgroundbutton: with default state.
	 *		.text : Background
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_backgroundbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_backgroundbutton"));
	}
	/**
	 * Backgroundbutton: with specific test context and state.
	 *		.text : Background
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_backgroundbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_backgroundbutton"), anchor, flags);
	}
	
	/**
	 * deleteExport_Yesbutton: with default state.
	 *		.text : Yes
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_deleteExport_Yesbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_deleteExport_Yesbutton"));
	}
	/**
	 * deleteExport_Yesbutton: with specific test context and state.
	 *		.text : Yes
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_deleteExport_Yesbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_deleteExport_Yesbutton"), anchor, flags);
	}
	
	/**
	 * DeleteExportedFilesbutton: with default state.
	 *		.text : Delete Exported Files
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_deleteExportedFilesbutt() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_deleteExportedFilesbutt"));
	}
	/**
	 * DeleteExportedFilesbutton: with specific test context and state.
	 *		.text : Delete Exported Files
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_deleteExportedFilesbutt(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_deleteExportedFilesbutt"), anchor, flags);
	}
	
	/**
	 * exportCancelbutton: with default state.
	 *		.text : Cancel
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_exportCancelbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_exportCancelbutton"));
	}
	/**
	 * exportCancelbutton: with specific test context and state.
	 *		.text : Cancel
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_exportCancelbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_exportCancelbutton"), anchor, flags);
	}
	
	/**
	 * ExportCasebutton: with default state.
	 *		.text : Export Case
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_exportCasebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_exportCasebutton"));
	}
	/**
	 * ExportCasebutton: with specific test context and state.
	 *		.text : Export Case
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_exportCasebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_exportCasebutton"), anchor, flags);
	}
	
	/**
	 * exportPrintbutton: with default state.
	 *		.text : Print
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_exportPrintbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_exportPrintbutton"));
	}
	/**
	 * exportPrintbutton: with specific test context and state.
	 *		.text : Print
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_exportPrintbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_exportPrintbutton"), anchor, flags);
	}
	
	/**
	 * IEDownload_Close: with default state.
	 *		.text : Close
	 * 		.class : Html.HtmlBrowser.NotificationBarControl
	 * 		.name : Close
	 */
	protected GuiTestObject button_ieDownload_Close() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_ieDownload_Close"));
	}
	/**
	 * IEDownload_Close: with specific test context and state.
	 *		.text : Close
	 * 		.class : Html.HtmlBrowser.NotificationBarControl
	 * 		.name : Close
	 */
	protected GuiTestObject button_ieDownload_Close(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_ieDownload_Close"), anchor, flags);
	}
	
	/**
	 * IEDownload_Save: with default state.
	 *		.text : Save
	 * 		.class : Html.HtmlBrowser.NotificationBarControl
	 * 		.name : Save
	 */
	protected GuiTestObject button_ieDownload_Save() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_ieDownload_Save"));
	}
	/**
	 * IEDownload_Save: with specific test context and state.
	 *		.text : Save
	 * 		.class : Html.HtmlBrowser.NotificationBarControl
	 * 		.name : Save
	 */
	protected GuiTestObject button_ieDownload_Save(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_ieDownload_Save"), anchor, flags);
	}
	
	/**
	 * messagePrint: with default state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class : RegularExpression(.*x-btn-text print-tb-icon.*)
	 */
	protected GuiTestObject button_messagePrint() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_messagePrint"));
	}
	/**
	 * messagePrint: with specific test context and state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class : RegularExpression(.*x-btn-text print-tb-icon.*)
	 */
	protected GuiTestObject button_messagePrint(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_messagePrint"), anchor, flags);
	}
	
	/**
	 * messagePrintButton: with default state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text print-tb-icon
	 */
	protected GuiTestObject button_messagePrintButton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_messagePrintButton"));
	}
	/**
	 * messagePrintButton: with specific test context and state.
	 *		.text : 
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class :  x-btn-text print-tb-icon
	 */
	protected GuiTestObject button_messagePrintButton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_messagePrintButton"), anchor, flags);
	}
	
	/**
	 * OKbutton: with default state.
	 *		.text : OK
	 * 		type : button
	 * 		.screenLeft : NR:Range[0 .. 10000000000000]
	 * 		.hasFocus : true
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_oKbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_oKbutton"));
	}
	/**
	 * OKbutton: with specific test context and state.
	 *		.text : OK
	 * 		type : button
	 * 		.screenLeft : NR:Range[0 .. 10000000000000]
	 * 		.hasFocus : true
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_oKbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_oKbutton"), anchor, flags);
	}
	
	/**
	 * printButton: with default state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class : RegularExpression(.*x-btn-text print-tb-icon.*)
	 */
	protected GuiTestObject button_printButton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_printButton"));
	}
	/**
	 * printButton: with specific test context and state.
	 *		type : button
	 * 		.class : Html.BUTTON
	 * 		class : RegularExpression(.*x-btn-text print-tb-icon.*)
	 */
	protected GuiTestObject button_printButton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_printButton"), anchor, flags);
	}
	
	/**
	 * Cancelbutton: with default state.
	 *		.text : Cancel
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_printCancelbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_printCancelbutton"));
	}
	/**
	 * Cancelbutton: with specific test context and state.
	 *		.text : Cancel
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_printCancelbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_printCancelbutton"), anchor, flags);
	}
	
	/**
	 * windowExportFile_Closebutton: with default state.
	 *		.text : Close
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_windowExportFile_Closeb() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_windowExportFile_Closeb"));
	}
	/**
	 * windowExportFile_Closebutton: with specific test context and state.
	 *		.text : Close
	 * 		type : button
	 * 		.title : 
	 * 		.class : Html.BUTTON
	 * 		.name : 
	 * 		class :  x-btn-text
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_windowExportFile_Closeb(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_windowExportFile_Closeb"), anchor, flags);
	}
	
	/**
	 * attWindowCLose: with default state.
	 *		.className : x-tool x-tool-close
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_attWindowCLose() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_attWindowCLose"));
	}
	/**
	 * attWindowCLose: with specific test context and state.
	 *		.className : x-tool x-tool-close
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_attWindowCLose(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_attWindowCLose"), anchor, flags);
	}
	
	/**
	 * attWindowCloseButton: with default state.
	 *		.className : RegularExpression(x-tool x-tool-close.*)
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_attWindowCloseButton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_attWindowCloseButton"));
	}
	/**
	 * attWindowCloseButton: with specific test context and state.
	 *		.className : RegularExpression(x-tool x-tool-close.*)
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_attWindowCloseButton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_attWindowCloseButton"), anchor, flags);
	}
	
	/**
	 * dialogWindow: with default state.
	 *		.className :  x-window x-window-plain x-window-dlg
	 * 		.class : Html.DIV
	 * 		.classIndex : 31
	 */
	protected GuiTestObject html_dialogWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_dialogWindow"));
	}
	/**
	 * dialogWindow: with specific test context and state.
	 *		.className :  x-window x-window-plain x-window-dlg
	 * 		.class : Html.DIV
	 * 		.classIndex : 31
	 */
	protected GuiTestObject html_dialogWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_dialogWindow"), anchor, flags);
	}
	
	/**
	 * dialogWindowInner: with default state.
	 *		.className : x-window-bwrap
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_dialogWindowInner() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_dialogWindowInner"));
	}
	/**
	 * dialogWindowInner: with specific test context and state.
	 *		.className : x-window-bwrap
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_dialogWindowInner(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_dialogWindowInner"), anchor, flags);
	}
	
	/**
	 * exportFilesList: with default state.
	 *		.className : x-grid3-body
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_exportFilesList() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportFilesList"));
	}
	/**
	 * exportFilesList: with specific test context and state.
	 *		.className : x-grid3-body
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_exportFilesList(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportFilesList"), anchor, flags);
	}
	
	/**
	 * exportList: with default state.
	 *		.className : x-grid3-body
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_exportList() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportList"));
	}
	/**
	 * exportList: with specific test context and state.
	 *		.className : x-grid3-body
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_exportList(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportList"), anchor, flags);
	}
	
	/**
	 * exportStatusWindow: with default state.
	 *		.id : exportStatusWindow
	 * 		.className : x-window x-window-noborder x-window-plain x-resizable-pinned
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_exportStatusWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportStatusWindow"));
	}
	/**
	 * exportStatusWindow: with specific test context and state.
	 *		.id : exportStatusWindow
	 * 		.className : x-window x-window-noborder x-window-plain x-resizable-pinned
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_exportStatusWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportStatusWindow"), anchor, flags);
	}
	
	/**
	 * exportWindow: with default state.
	 *		.id : exportWindow
	 * 		.className :  x-window
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_exportWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportWindow"));
	}
	/**
	 * exportWindow: with specific test context and state.
	 *		.id : exportWindow
	 * 		.className :  x-window
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_exportWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportWindow"), anchor, flags);
	}
	
	/**
	 * exportWindow: with default state.
	 *		.className :  x-window
	 * 		.class : Html.DIV
	 * 		.classIndex : 31
	 */
	protected GuiTestObject html_exportWindow2() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportWindow2"));
	}
	/**
	 * exportWindow: with specific test context and state.
	 *		.className :  x-window
	 * 		.class : Html.DIV
	 * 		.classIndex : 31
	 */
	protected GuiTestObject html_exportWindow2(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_exportWindow2"), anchor, flags);
	}
	
	/**
	 * finishPrintWindowClose: with default state.
	 *		.className : x-tool x-tool-close
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_finishPrintWindowClose() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_finishPrintWindowClose"));
	}
	/**
	 * finishPrintWindowClose: with specific test context and state.
	 *		.className : x-tool x-tool-close
	 * 		.class : Html.DIV
	 * 		.classIndex : 0
	 */
	protected GuiTestObject html_finishPrintWindowClose(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_finishPrintWindowClose"), anchor, flags);
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
	 * printStatusWindow: with default state.
	 *		id : printStatusWindow
	 * 		.text : RegularExpression(Printing to PDF.*)
	 * 		.id : printStatusWindow
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_printStatusWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_printStatusWindow"));
	}
	/**
	 * printStatusWindow: with specific test context and state.
	 *		id : printStatusWindow
	 * 		.text : RegularExpression(Printing to PDF.*)
	 * 		.id : printStatusWindow
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_printStatusWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_printStatusWindow"), anchor, flags);
	}
	
	/**
	 * PrintWindow0: with default state.
	 *		.id : PrintWindow0
	 * 		.className : x-window x-window-noborder x-window-plain x-resizable-pinned
	 * 		.class : Html.DIV
	 * 		.classIndex : 29
	 */
	protected GuiTestObject html_printWindow0() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_printWindow0"));
	}
	/**
	 * PrintWindow0: with specific test context and state.
	 *		.id : PrintWindow0
	 * 		.className : x-window x-window-noborder x-window-plain x-resizable-pinned
	 * 		.class : Html.DIV
	 * 		.classIndex : 29
	 */
	protected GuiTestObject html_printWindow0(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_printWindow0"), anchor, flags);
	}
	
	/**
	 * exportManagement: with default state.
	 *		.text : Export Management
	 * 		.href : http://10.10.24.80:8888/index.html#
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class : x-menu-item
	 */
	protected GuiTestObject link_exportManagement() 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_exportManagement"));
	}
	/**
	 * exportManagement: with specific test context and state.
	 *		.text : Export Management
	 * 		.href : http://10.10.24.80:8888/index.html#
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 * 		class : x-menu-item
	 */
	protected GuiTestObject link_exportManagement(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_exportManagement"), anchor, flags);
	}
	
	/**
	 * normalUser_quickSearchField0: with default state.
	 *		.text : 
	 * 		type : text
	 * 		.id : quickSearchField0
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : quickSearchField0
	 * 		.classIndex : 0
	 * 		class : x-form-text x-form-field search_field_effect x-form-empty-field
	 */
	protected TextGuiTestObject text_normalUser_quickSearchFie() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_normalUser_quickSearchFie"));
	}
	/**
	 * normalUser_quickSearchField0: with specific test context and state.
	 *		.text : 
	 * 		type : text
	 * 		.id : quickSearchField0
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : quickSearchField0
	 * 		.classIndex : 0
	 * 		class : x-form-text x-form-field search_field_effect x-form-empty-field
	 */
	protected TextGuiTestObject text_normalUser_quickSearchFie(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_normalUser_quickSearchFie"), anchor, flags);
	}
	
	/**
	 * quickSearchField0: with default state.
	 *		.text : 
	 * 		type : text
	 * 		.id : quickSearchField0
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : quickSearchField0
	 * 		class : x-form-text x-form-field search_field_effect x-form-empty-field
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject text_quickSearchField0() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_quickSearchField0"));
	}
	/**
	 * quickSearchField0: with specific test context and state.
	 *		.text : 
	 * 		type : text
	 * 		.id : quickSearchField0
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		name : quickSearchField0
	 * 		class : x-form-text x-form-field search_field_effect x-form-empty-field
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject text_quickSearchField0(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_quickSearchField0"), anchor, flags);
	}
	
	/**
	 * Locate and return the verification point exportWindow_IE object in the SUT.
	 */
	protected IFtVerificationPoint exportWindow_IEVP() 
	{
		return vp("exportWindow_IE");
	}
	
	protected IFtVerificationPoint exportWindow_IEVP(TestObject anchor)
	{
		return vp("exportWindow_IE", anchor);
	}
	
	

	protected Print_SuperUserHelper()
	{
		setScriptName("NetmailSearch_PrintAndExport.Print_SuperUser");
	}
	
}

