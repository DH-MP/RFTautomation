// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.TC_1328_FolderStructure;
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
 * Script Name   : <b>TS_1444_1445_Normal_And_Super_User</b><br>
 * Generated     : <b>2013/09/26 8:46:44 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  September 26, 2013
 * @author Administrator
 */
public abstract class TS_1444_1445_Normal_And_Super_UserHelper extends utilities.MySuperHelper
{
	/**
	 * folderContainer_Normal: with default state.
	 *		.className : x-panel-body x-panel-body-noheader
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_folderContainer_Normal() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderContainer_Normal"));
	}
	/**
	 * folderContainer_Normal: with specific test context and state.
	 *		.className : x-panel-body x-panel-body-noheader
	 * 		.class : Html.DIV
	 * 		.classIndex : 1
	 */
	protected GuiTestObject html_folderContainer_Normal(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderContainer_Normal"), anchor, flags);
	}
	
	/**
	 * folderUL_Admin: with default state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_folderUL_Admin() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderUL_Admin"));
	}
	/**
	 * folderUL_Admin: with specific test context and state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_folderUL_Admin(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderUL_Admin"), anchor, flags);
	}
	
	/**
	 * folderUL_Normal: with default state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_folderUL_Normal() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderUL_Normal"));
	}
	/**
	 * folderUL_Normal: with specific test context and state.
	 *		.class : Html.UL
	 * 		class : x-tree-root-ct x-tree-arrows
	 */
	protected GuiTestObject html_folderUL_Normal(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_folderUL_Normal"), anchor, flags);
	}
	
	

	protected TS_1444_1445_Normal_And_Super_UserHelper()
	{
		setScriptName("TC_1328_FolderStructure.TS_1444_1445_Normal_And_Super_User");
	}
	
}

