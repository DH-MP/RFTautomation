// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.TestCases.TC_BasicMode;
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
 * Script Name   : <b>TS_BasicMode</b><br>
 * Generated     : <b>2014/07/23 1:23:00 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  July 23, 2014
 * @author Administrator
 */
public abstract class TS_BasicModeHelper extends utilities.MySuperHelper
{
	/**
	 * basicModeArchive_OK: with default state.
	 *		.text : OK
	 * 		type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_basicModeArchive_OK() 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_basicModeArchive_OK"));
	}
	/**
	 * basicModeArchive_OK: with specific test context and state.
	 *		.text : OK
	 * 		type : button
	 * 		.class : Html.BUTTON
	 * 		class :  x-btn-text
	 */
	protected GuiTestObject button_basicModeArchive_OK(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("button_basicModeArchive_OK"), anchor, flags);
	}
	
	/**
	 * indexingProgressWindow: with default state.
	 *		.text : RegularExpression(.*Indexing.*Cancel)
	 * 		.className :  x-window x-window-plain x-window-dlg
	 * 		.class : Html.DIV
	 * 		.contentText : RegularExpression(.*Indexing.*)
	 */
	protected GuiTestObject html_indexingProgressWindow() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_indexingProgressWindow"));
	}
	/**
	 * indexingProgressWindow: with specific test context and state.
	 *		.text : RegularExpression(.*Indexing.*Cancel)
	 * 		.className :  x-window x-window-plain x-window-dlg
	 * 		.class : Html.DIV
	 * 		.contentText : RegularExpression(.*Indexing.*)
	 */
	protected GuiTestObject html_indexingProgressWindow(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_indexingProgressWindow"), anchor, flags);
	}
	
	/**
	 * selectedOnlyCheckBox: with default state.
	 *		.class : Html.IMG
	 */
	protected StatelessGuiSubitemTestObject image_selectedOnlyCheckBox() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_selectedOnlyCheckBox"));
	}
	/**
	 * selectedOnlyCheckBox: with specific test context and state.
	 *		.class : Html.IMG
	 */
	protected StatelessGuiSubitemTestObject image_selectedOnlyCheckBox(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_selectedOnlyCheckBox"), anchor, flags);
	}
	
	/**
	 * selectionsButton0: with default state.
	 *		id : selectionsButton0
	 * 		.id : selectionsButton0
	 * 		.class : Html.TABLE
	 */
	protected StatelessGuiSubitemTestObject table_selectionsButton0() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_selectionsButton0"));
	}
	/**
	 * selectionsButton0: with specific test context and state.
	 *		id : selectionsButton0
	 * 		.id : selectionsButton0
	 * 		.class : Html.TABLE
	 */
	protected StatelessGuiSubitemTestObject table_selectionsButton0(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_selectionsButton0"), anchor, flags);
	}
	
	

	protected TS_BasicModeHelper()
	{
		setScriptName("TestCases.TC_BasicMode.TS_BasicMode");
	}
	
}

