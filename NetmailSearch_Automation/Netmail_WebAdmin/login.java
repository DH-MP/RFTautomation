package Netmail_WebAdmin;
import java.util.Map;

import resources.Netmail_WebAdmin.loginHelper;
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
public class login extends loginHelper
{
	/**
	 * Script Name   : <b>login</b>
	 * Generated     : <b>May 21, 2014 9:23:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/05/21
	 * @author Administrator
	 */
	private String userName = "netmail";
	private String password = "mm3ss4g1ng";
	public void testMain(Object[] args) 
	{
		if(args != null && args.length > 0){
			Map<String, Object> data = (Map<String, Object>) args[0];
			userName = (String) data.get("userName");
			password = (String) data.get("password");
			
			text_userName().click(atPoint(51,8));
			browser_htmlBrowser().inputKeys(userName);
			text_password().click(atPoint(29,14));
			browser_htmlBrowser().inputKeys(password+"{ENTER}{ENTER}");
		}
		
	}
}

