// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.TestCases.TC_DiagnosticUI;
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
 * Script Name   : <b>DiagnosticUI_PlatformStatusWith</b><br>
 * Generated     : <b>2014/12/15 11:04:28 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows Server 2008 R2 x86 6.1 <br>
 * 
 * @since  December 15, 2014
 * @author Administrator
 */
public abstract class DiagnosticUI_PlatformStatusWithHelper extends utilities.MySuperHelper
{
	/**
	 * framework: with default state.
	 *		.id : framework
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_framework() 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_framework"));
	}
	/**
	 * framework: with specific test context and state.
	 *		.id : framework
	 * 		.class : Html.DIV
	 */
	protected GuiTestObject html_framework(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("html_framework"), anchor, flags);
	}
	
	/**
	 * firstStatusGreen: with default state.
	 *		.src : RegularExpression(https://.*\/images/status-green\.png)
	 * 		.class : Html.IMG
	 */
	protected StatelessGuiSubitemTestObject image_firstStatusGreen() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_firstStatusGreen"));
	}
	/**
	 * firstStatusGreen: with specific test context and state.
	 *		.src : RegularExpression(https://.*\/images/status-green\.png)
	 * 		.class : Html.IMG
	 */
	protected StatelessGuiSubitemTestObject image_firstStatusGreen(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_firstStatusGreen"), anchor, flags);
	}
	
	/**
	 * statusRed: with default state.
	 *		.alt : 
	 * 		.id : 
	 * 		.src : https://10.1.30.64/images/status-red.png
	 * 		.title : 
	 * 		.class : Html.IMG
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject image_statusRed() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_statusRed"));
	}
	/**
	 * statusRed: with specific test context and state.
	 *		.alt : 
	 * 		.id : 
	 * 		.src : https://10.1.30.64/images/status-red.png
	 * 		.title : 
	 * 		.class : Html.IMG
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject image_statusRed(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_statusRed"), anchor, flags);
	}
	
	/**
	 * statusYellow: with default state.
	 *		.alt : 
	 * 		.id : 
	 * 		.src : https://10.1.30.64/images/status-yellow.png
	 * 		.title : 
	 * 		.class : Html.IMG
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject image_statusYellow() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_statusYellow"));
	}
	/**
	 * statusYellow: with specific test context and state.
	 *		.alt : 
	 * 		.id : 
	 * 		.src : https://10.1.30.64/images/status-yellow.png
	 * 		.title : 
	 * 		.class : Html.IMG
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject image_statusYellow(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_statusYellow"), anchor, flags);
	}
	
	/**
	 * Locate and return the verification point statusGreen_statusGreen object in the SUT.
	 */
	protected IFtVerificationPoint statusGreen_statusGreenVP() 
	{
		return vp("statusGreen_statusGreen");
	}
	protected IFtVerificationPoint statusGreen_statusGreenVP(TestObject anchor)
	{
		return vp("statusGreen_statusGreen", anchor);
	}
	
	/**
	 * Locate and return the verification point statusRed_statusRed object in the SUT.
	 */
	protected IFtVerificationPoint statusRed_statusRedVP() 
	{
		return vp("statusRed_statusRed");
	}
	protected IFtVerificationPoint statusRed_statusRedVP(TestObject anchor)
	{
		return vp("statusRed_statusRed", anchor);
	}
	
	/**
	 * Locate and return the verification point statusYellow_statusYellow object in the SUT.
	 */
	protected IFtVerificationPoint statusYellow_statusYellowVP() 
	{
		return vp("statusYellow_statusYellow");
	}
	protected IFtVerificationPoint statusYellow_statusYellowVP(TestObject anchor)
	{
		return vp("statusYellow_statusYellow", anchor);
	}
	
	

	protected DiagnosticUI_PlatformStatusWithHelper()
	{
		setScriptName("TestCases.TC_DiagnosticUI.DiagnosticUI_PlatformStatusWith");
	}
	
}

