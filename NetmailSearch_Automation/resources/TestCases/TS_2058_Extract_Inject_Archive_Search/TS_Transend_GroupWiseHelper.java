// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.TestCases.TS_2058_Extract_Inject_Archive_Search;
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
 * Script Name   : <b>TS_Transend_GroupWise</b><br>
 * Generated     : <b>2014/07/02 9:54:58 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  July 02, 2014
 * @author Administrator
 */
public abstract class TS_Transend_GroupWiseHelper extends utilities.MySuperHelper
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
	 * NewCasebutton: with default state.
	 *		.text : New Case
	 * 		type : button
	 * 		.class : Html.BUTTON
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_newCasebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_newCasebutton"));
	}
	/**
	 * NewCasebutton: with specific test context and state.
	 *		.text : New Case
	 * 		type : button
	 * 		.class : Html.BUTTON
	 * 		.classIndex : 0
	 */
	protected GuiTestObject button_newCasebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_newCasebutton"), anchor, flags);
	}
	
	/**
	 * evaluationPopUp_OK: with default state.
	 *		.text : OK
	 * 		.class : .Pushbutton
	 * 		.name : OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject evaluationPopUp_OKbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("evaluationPopUp_OKbutton"));
	}
	/**
	 * evaluationPopUp_OK: with specific test context and state.
	 *		.text : OK
	 * 		.class : .Pushbutton
	 * 		.name : OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject evaluationPopUp_OKbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("evaluationPopUp_OKbutton"), anchor, flags);
	}
	
	/**
	 * MigrationCompletedReport: with default state.
	 *		.text : Migration Completed Report
	 * 		.class : TForm10
	 * 		.processName : tm11.exe
	 * 		.name : Migration Completed Report
	 */
	protected TopLevelSubitemTestObject migrationCompletedReportwindow() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("migrationCompletedReportwindow"));
	}
	/**
	 * MigrationCompletedReport: with specific test context and state.
	 *		.text : Migration Completed Report
	 * 		.class : TForm10
	 * 		.processName : tm11.exe
	 * 		.name : Migration Completed Report
	 */
	protected TopLevelSubitemTestObject migrationCompletedReportwindow(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("migrationCompletedReportwindow"), anchor, flags);
	}
	
	/**
	 * OK: with default state.
	 *		.text : OK
	 * 		.class : .Pushbutton
	 * 		.name : OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject okbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("okbutton"));
	}
	/**
	 * OK: with specific test context and state.
	 *		.text : OK
	 * 		.class : .Pushbutton
	 * 		.name : OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject okbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("okbutton"), anchor, flags);
	}
	
	/**
	 * StartSingleMigration: with default state.
	 *		.text : Start Single Migration...
	 * 		.class : .Pushbutton
	 * 		.name : Start Single Migration...
	 * 		.classIndex : 0
	 */
	protected GuiTestObject startSingleMigrationbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("startSingleMigrationbutton"));
	}
	/**
	 * StartSingleMigration: with specific test context and state.
	 *		.text : Start Single Migration...
	 * 		.class : .Pushbutton
	 * 		.name : Start Single Migration...
	 * 		.classIndex : 0
	 */
	protected GuiTestObject startSingleMigrationbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("startSingleMigrationbutton"), anchor, flags);
	}
	
	/**
	 * TransendMigrationMonitor: with default state.
	 *		.text : Transend Migration Monitor
	 * 		.class : TForm8
	 * 		.processName : tm11.exe
	 * 		.name : Transend Migration Monitor
	 */
	protected TopLevelSubitemTestObject transendMigrationMonitorwindow() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigrationMonitorwindow"));
	}
	/**
	 * TransendMigrationMonitor: with specific test context and state.
	 *		.text : Transend Migration Monitor
	 * 		.class : TForm8
	 * 		.processName : tm11.exe
	 * 		.name : Transend Migration Monitor
	 */
	protected TopLevelSubitemTestObject transendMigrationMonitorwindow(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigrationMonitorwindow"), anchor, flags);
	}
	
	/**
	 * Yes: with default state.
	 *		.text : Yes
	 * 		.class : .Pushbutton
	 * 		.name : Yes
	 * 		.classIndex : 0
	 */
	protected GuiTestObject yesbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("yesbutton"));
	}
	/**
	 * Yes: with specific test context and state.
	 *		.text : Yes
	 * 		.class : .Pushbutton
	 * 		.name : Yes
	 * 		.classIndex : 0
	 */
	protected GuiTestObject yesbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("yesbutton"), anchor, flags);
	}
	
	

	protected TS_Transend_GroupWiseHelper()
	{
		setScriptName("TestCases.TS_2058_Extract_Inject_Archive_Search.TS_Transend_GroupWise");
	}
	
}

