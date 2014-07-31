
import resources.TEST2Helper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class TEST2 extends TEST2Helper
{
	/**
	 * Script Name   : <b>TEST2</b>
	 * Generated     : <b>Jul 30, 2014 2:40:37 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/30
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		
		// HTML Browser
		// Document: RFT - Outlook Web App: 
		for(int i = 546; i <1001; i++){
			BrowserTestObject b = (BrowserTestObject) utilities.HelperClass.findBrowser();
			TestObject[] a = b.find(atDescendant(".tag", "SPAN", "id", "_ariaId_40.folder"), false);
			((GuiTestObject) a[0]).hover();
			((GuiTestObject) a[0]).click(RIGHT, atPoint(12,9));
			sleep(1);
			((GuiTestObject) b.find(atDescendant(".tag", "DIV", ".className", "contextMenuDropShadow contextMenuPopup removeFocusOutline"), true)[0]).click(atPoint(31,14));
			b.inputKeys(i+"{ENTER}");
			System.out.println(i);
		}
		
	}
}

