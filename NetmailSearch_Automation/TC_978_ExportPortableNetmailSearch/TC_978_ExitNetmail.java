package TC_978_ExportPortableNetmailSearch;
import resources.TC_978_ExportPortableNetmailSearch.TC_978_ExitNetmailHelper;
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
public class TC_978_ExitNetmail extends TC_978_ExitNetmailHelper
{
	/**
	 * Script Name   : <b>TC_978_ExitNetmail</b>
	 * Generated     : <b>Oct 24, 2013 3:16:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/24
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		table_optionsButton().click();
		link_userDD_Logout().click();
		button_yesbutton().click();
		yesbutton().click();
		sleep(150);
	}
}

