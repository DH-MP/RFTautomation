// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.Transend;

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
 * Script Name   : <b>Transend</b><br>
 * Generated     : <b>2014/07/02 11:53:41 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  July 02, 2014
 * @author Administrator
 */
public abstract class TransendHelper extends RationalTestScript
{
	/**
	 * Close: with default state.
	 *		.text : Close
	 * 		.class : .Pushbutton
	 * 		.name : Close
	 * 		.classIndex : 0
	 */
	protected GuiTestObject closebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("closebutton"));
	}
	/**
	 * Close: with specific test context and state.
	 *		.text : Close
	 * 		.class : .Pushbutton
	 * 		.name : Close
	 * 		.classIndex : 0
	 */
	protected GuiTestObject closebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("closebutton"), anchor, flags);
	}
	
	/**
	 * Context: with default state.
	 *		.text : Context
	 * 		.class : .Menupopup
	 * 		.name : Context
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject contextpopupMenu() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("contextpopupMenu"));
	}
	/**
	 * Context: with specific test context and state.
	 *		.text : Context
	 * 		.class : .Menupopup
	 * 		.name : Context
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject contextpopupMenu(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("contextpopupMenu"), anchor, flags);
	}
	
	/**
	 * EnableMigration: with default state.
	 *		.text : Enable Migration
	 * 		.class : .Checkbutton
	 * 		.name : Enable Migration
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject enableMigrationcheckBox() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("enableMigrationcheckBox"));
	}
	/**
	 * EnableMigration: with specific test context and state.
	 *		.text : Enable Migration
	 * 		.class : .Checkbutton
	 * 		.name : Enable Migration
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject enableMigrationcheckBox(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("enableMigrationcheckBox"), anchor, flags);
	}
	
	/**
	 * folder_OK: with default state.
	 *		.text : &OK
	 * 		.class : .Pushbutton
	 * 		.name : &OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject folder_OKbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("folder_OKbutton"));
	}
	/**
	 * folder_OK: with specific test context and state.
	 *		.text : &OK
	 * 		.class : .Pushbutton
	 * 		.name : &OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject folder_OKbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("folder_OKbutton"), anchor, flags);
	}
	
	/**
	 * Folders: with default state.
	 *		.text : Folders...
	 * 		.class : .Pushbutton
	 * 		.name : Folders...
	 * 		.classIndex : 0
	 */
	protected GuiTestObject foldersbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("foldersbutton"));
	}
	/**
	 * Folders: with specific test context and state.
	 *		.text : Folders...
	 * 		.class : .Pushbutton
	 * 		.name : Folders...
	 * 		.classIndex : 0
	 */
	protected GuiTestObject foldersbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("foldersbutton"), anchor, flags);
	}
	
	/**
	 * GWpassword: with default state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject gWpasswordtext() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("gWpasswordtext"));
	}
	/**
	 * GWpassword: with specific test context and state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject gWpasswordtext(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("gWpasswordtext"), anchor, flags);
	}
	
	/**
	 * GWuserName: with default state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject gWuserNametext() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("gWuserNametext"));
	}
	/**
	 * GWuserName: with specific test context and state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject gWuserNametext(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("gWuserNametext"), anchor, flags);
	}
	
	/**
	 * List: with default state.
	 *		.class : .List
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject listlist() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("listlist"));
	}
	/**
	 * List: with specific test context and state.
	 *		.class : .List
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject listlist(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("listlist"), anchor, flags);
	}
	
	/**
	 * LoadFromEMailSource: with default state.
	 *		.text : Load from E-Mail Source
	 * 		.class : .Pushbutton
	 * 		.name : Load from E-Mail Source
	 * 		.classIndex : 0
	 */
	protected GuiTestObject loadFromEMailSourcebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("loadFromEMailSourcebutton"));
	}
	/**
	 * LoadFromEMailSource: with specific test context and state.
	 *		.text : Load from E-Mail Source
	 * 		.class : .Pushbutton
	 * 		.name : Load from E-Mail Source
	 * 		.classIndex : 0
	 */
	protected GuiTestObject loadFromEMailSourcebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("loadFromEMailSourcebutton"), anchor, flags);
	}
	
	/**
	 * LogonToTargetSystemWasSuccessfulSeeMasterLogFileForDetails: with default state.
	 *		.class : .Statictext
	 * 		.name : Logon to target system was successful, see master log file for details
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject logonToTargetSystemWasSuccessf() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("logonToTargetSystemWasSuccessf"));
	}
	/**
	 * LogonToTargetSystemWasSuccessfulSeeMasterLogFileForDetails: with specific test context and state.
	 *		.class : .Statictext
	 * 		.name : Logon to target system was successful, see master log file for details
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject logonToTargetSystemWasSuccessf(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("logonToTargetSystemWasSuccessf"), anchor, flags);
	}
	
	/**
	 * MigrationCompletedReport: with default state.
	 *		.text : Migration Completed Report
	 * 		.class : RegularExpression(TForm.*)
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
	 * 		.class : RegularExpression(TForm.*)
	 * 		.processName : tm11.exe
	 * 		.name : Migration Completed Report
	 */
	protected TopLevelSubitemTestObject migrationCompletedReportwindow(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("migrationCompletedReportwindow"), anchor, flags);
	}
	
	/**
	 * migrationOptions: with default state.
	 *		.class : .List
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject migrationOptionslist() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("migrationOptionslist"));
	}
	/**
	 * migrationOptions: with specific test context and state.
	 *		.class : .List
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject migrationOptionslist(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("migrationOptionslist"), anchor, flags);
	}
	
	/**
	 * netmailXML_userCN: with default state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject netmailXML_userCNtext() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("netmailXML_userCNtext"));
	}
	/**
	 * netmailXML_userCN: with specific test context and state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject netmailXML_userCNtext(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("netmailXML_userCNtext"), anchor, flags);
	}
	
	/**
	 * netmailXML_userName: with default state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject netmailXML_userNametext() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("netmailXML_userNametext"));
	}
	/**
	 * netmailXML_userName: with specific test context and state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject netmailXML_userNametext(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("netmailXML_userNametext"), anchor, flags);
	}
	
	/**
	 * OKMigrationComplete: with default state.
	 *		.text : OK
	 * 		.class : .Pushbutton
	 * 		.name : OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject okMigrationCompletebutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("okMigrationCompletebutton"));
	}
	/**
	 * OKMigrationComplete: with specific test context and state.
	 *		.text : OK
	 * 		.class : .Pushbutton
	 * 		.name : OK
	 * 		.classIndex : 0
	 */
	protected GuiTestObject okMigrationCompletebutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("okMigrationCompletebutton"), anchor, flags);
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
	 * Open: with default state.
	 *		.text : Open
	 * 		.class : .Pushbutton
	 * 		.name : Open
	 * 		.classIndex : 0
	 */
	protected GuiTestObject openbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("openbutton"));
	}
	/**
	 * Open: with specific test context and state.
	 *		.text : Open
	 * 		.class : .Pushbutton
	 * 		.name : Open
	 * 		.classIndex : 0
	 */
	protected GuiTestObject openbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("openbutton"), anchor, flags);
	}
	
	/**
	 * outlookMSGDirectory: with default state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject outlookMSGDirectorytext() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("outlookMSGDirectorytext"));
	}
	/**
	 * outlookMSGDirectory: with specific test context and state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject outlookMSGDirectorytext(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("outlookMSGDirectorytext"), anchor, flags);
	}
	
	/**
	 * outputDirectory: with default state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject outputDirectorytext() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("outputDirectorytext"));
	}
	/**
	 * outputDirectory: with specific test context and state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject outputDirectorytext(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("outputDirectorytext"), anchor, flags);
	}
	
	/**
	 * serverDomainComOrIPAddress: with default state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject serverDomainComOrIPAddresstext() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("serverDomainComOrIPAddresstext"));
	}
	/**
	 * serverDomainComOrIPAddress: with specific test context and state.
	 *		.text : null
	 * 		.class : Edit
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject serverDomainComOrIPAddresstext(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("serverDomainComOrIPAddresstext"), anchor, flags);
	}
	
	/**
	 * sourceEmail: with default state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject sourceEmailtext() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourceEmailtext"));
	}
	/**
	 * sourceEmail: with specific test context and state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject sourceEmailtext(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourceEmailtext"), anchor, flags);
	}
	
	/**
	 * SourceFrom_Dropdown: with default state.
	 *		.class : TDropDownEditButton
	 */
	protected GuiSubitemTestObject sourceFrom_Dropdownwindow() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourceFrom_Dropdownwindow"));
	}
	/**
	 * SourceFrom_Dropdown: with specific test context and state.
	 *		.class : TDropDownEditButton
	 */
	protected GuiSubitemTestObject sourceFrom_Dropdownwindow(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourceFrom_Dropdownwindow"), anchor, flags);
	}
	
	/**
	 * sourceList: with default state.
	 *		.hasFocus : true
	 * 		.class : .List
	 * 		.visible : true
	 */
	protected GuiSubitemTestObject sourceListlist() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourceListlist"));
	}
	/**
	 * sourceList: with specific test context and state.
	 *		.hasFocus : true
	 * 		.class : .List
	 * 		.visible : true
	 */
	protected GuiSubitemTestObject sourceListlist(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourceListlist"), anchor, flags);
	}
	
	/**
	 * sourcePassword: with default state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject sourcePasswordtext() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourcePasswordtext"));
	}
	/**
	 * sourcePassword: with specific test context and state.
	 *		.class : .Text
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject sourcePasswordtext(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("sourcePasswordtext"), anchor, flags);
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
	 * TCoolBar: with default state.
	 *		.text : null
	 * 		.class : TCoolBar
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tCoolBarwindow() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tCoolBarwindow"));
	}
	/**
	 * TCoolBar: with specific test context and state.
	 *		.text : null
	 * 		.class : TCoolBar
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tCoolBarwindow(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tCoolBarwindow"), anchor, flags);
	}
	
	/**
	 * TDropDownEditButton: with default state.
	 *		.text : null
	 * 		.class : TDropDownEditButton
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tDropDownEditButtonwindow() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tDropDownEditButtonwindow"));
	}
	/**
	 * TDropDownEditButton: with specific test context and state.
	 *		.text : null
	 * 		.class : TDropDownEditButton
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tDropDownEditButtonwindow(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tDropDownEditButtonwindow"), anchor, flags);
	}
	
	/**
	 * TDropDownForm: with default state.
	 *		.text : null
	 * 		.class : TDropDownForm
	 * 		.processName : tm11.exe
	 * 		.name : null
	 */
	protected TopLevelSubitemTestObject tDropDownFormwindow() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("tDropDownFormwindow"));
	}
	/**
	 * TDropDownForm: with specific test context and state.
	 *		.text : null
	 * 		.class : TDropDownForm
	 * 		.processName : tm11.exe
	 * 		.name : null
	 */
	protected TopLevelSubitemTestObject tDropDownFormwindow(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("tDropDownFormwindow"), anchor, flags);
	}
	
	/**
	 * targetList: with default state.
	 *		.hasFocus : true
	 * 		.class : .List
	 * 		.visible : true
	 */
	protected GuiSubitemTestObject targetListlist() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("targetListlist"));
	}
	/**
	 * targetList: with specific test context and state.
	 *		.hasFocus : true
	 * 		.class : .List
	 * 		.visible : true
	 */
	protected GuiSubitemTestObject targetListlist(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("targetListlist"), anchor, flags);
	}
	
	/**
	 * TargetTo_DropDown: with default state.
	 *		.text : null
	 * 		.class : TDropDownEditButton
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject targetTo_DropDownwindow() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("targetTo_DropDownwindow"));
	}
	/**
	 * TargetTo_DropDown: with specific test context and state.
	 *		.text : null
	 * 		.class : TDropDownEditButton
	 * 		.name : null
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject targetTo_DropDownwindow(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("targetTo_DropDownwindow"), anchor, flags);
	}
	
	/**
	 * TestLogon: with default state.
	 *		.text : Test Logon
	 * 		.class : .Pushbutton
	 * 		.name : Test Logon
	 * 		.classIndex : 0
	 */
	protected GuiTestObject testLogonbutton() 
	{
		return new GuiTestObject(
                        getMappedTestObject("testLogonbutton"));
	}
	/**
	 * TestLogon: with specific test context and state.
	 *		.text : Test Logon
	 * 		.class : .Pushbutton
	 * 		.name : Test Logon
	 * 		.classIndex : 0
	 */
	protected GuiTestObject testLogonbutton(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("testLogonbutton"), anchor, flags);
	}
	
	/**
	 * TransendMigratorCProgramDataTransendDefaultTmd: with default state.
	 *		.text : Transend Migrator .*
	 * 		.class : TForm3
	 * 		.processName : tm11.exe
	 * 		.name : RegularExpression(Transend Migrator.*)
	 */
	protected TopLevelSubitemTestObject transendMigratorCProgramDataTr() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorCProgramDataTr"));
	}
	/**
	 * TransendMigratorCProgramDataTransendDefaultTmd: with specific test context and state.
	 *		.text : Transend Migrator .*
	 * 		.class : TForm3
	 * 		.processName : tm11.exe
	 * 		.name : RegularExpression(Transend Migrator.*)
	 */
	protected TopLevelSubitemTestObject transendMigratorCProgramDataTr(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorCProgramDataTr"), anchor, flags);
	}
	
	/**
	 * TransendMigratorCProgramDataTransendDefaultTmd: with default state.
	 *		.text : RegularExpression(Transend Migrator.*)
	 * 		.class : TForm3
	 * 		.processName : tm11.exe
	 * 		.name : RegularExpression(Transend Migrator.*)
	 */
	protected TopLevelSubitemTestObject transendMigratorCProgramDataTr2() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorCProgramDataTr2"));
	}
	/**
	 * TransendMigratorCProgramDataTransendDefaultTmd: with specific test context and state.
	 *		.text : RegularExpression(Transend Migrator.*)
	 * 		.class : TForm3
	 * 		.processName : tm11.exe
	 * 		.name : RegularExpression(Transend Migrator.*)
	 */
	protected TopLevelSubitemTestObject transendMigratorCProgramDataTr2(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorCProgramDataTr2"), anchor, flags);
	}
	
	/**
	 * TransendMigratorMigrationOptions: with default state.
	 *		.text : Transend Migrator - Migration Options
	 * 		.class : TForm1
	 * 		.processName : tm11.exe
	 * 		.name : Transend Migrator - Migration Options
	 */
	protected TopLevelSubitemTestObject transendMigratorMigrationOptio() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorMigrationOptio"));
	}
	/**
	 * TransendMigratorMigrationOptions: with specific test context and state.
	 *		.text : Transend Migrator - Migration Options
	 * 		.class : TForm1
	 * 		.processName : tm11.exe
	 * 		.name : Transend Migrator - Migration Options
	 */
	protected TopLevelSubitemTestObject transendMigratorMigrationOptio(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorMigrationOptio"), anchor, flags);
	}
	
	/**
	 * TransendMigrator: with default state.
	 *		.text : Transend Migrator
	 * 		.class : #32770
	 * 		.processName : tm11.exe
	 * 		.name : Transend Migrator
	 */
	protected TopLevelSubitemTestObject transendMigratorwindow() 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorwindow"));
	}
	/**
	 * TransendMigrator: with specific test context and state.
	 *		.text : Transend Migrator
	 * 		.class : #32770
	 * 		.processName : tm11.exe
	 * 		.name : Transend Migrator
	 */
	protected TopLevelSubitemTestObject transendMigratorwindow(TestObject anchor, long flags) 
	{
		return new TopLevelSubitemTestObject(
                        getMappedTestObject("transendMigratorwindow"), anchor, flags);
	}
	
	/**
	 * UseArchiveGWClient55OrGreater: with default state.
	 *		.text : Use Archive (GW client 5.5 or greater)
	 * 		.class : .Checkbutton
	 * 		.name : Use Archive (GW client 5.5 or greater)
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject useArchiveGWClient55OrGreaterc() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("useArchiveGWClient55OrGreaterc"));
	}
	/**
	 * UseArchiveGWClient55OrGreater: with specific test context and state.
	 *		.text : Use Archive (GW client 5.5 or greater)
	 * 		.class : .Checkbutton
	 * 		.name : Use Archive (GW client 5.5 or greater)
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject useArchiveGWClient55OrGreaterc(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("useArchiveGWClient55OrGreaterc"), anchor, flags);
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
	
	

	protected TransendHelper()
	{
		setScriptName("Transend.Transend");
	}
	
}

