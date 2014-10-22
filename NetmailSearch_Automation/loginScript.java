
import java.util.Arrays;

import resources.loginScriptHelper;
import utilities.HelperScript;

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
public class loginScript extends loginScriptHelper
{
	/**
	 * Script Name   : <b>loginScript</b>
	 * Generated     : <b>May 29, 2013 8:54:49 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/29
	 * @author Administrator
	 */
	
	//Overridden data flow: Argument Data > RQMData > default data
	
	private String password = adminPassword, username = adminUserName;
	private boolean failToLogin = false, skipLoginVerification = false;
	private boolean useRQM;
	
	public void testMain(Object[] args) 
	{
		String url = URL;
		//Argument data override RQM
		this.username = args.length >= 1 && ((String)args[0]) != null  && !((String)args[0]).isEmpty() ? (String) args[0] : this.username;
		this.password = args.length >= 2 && ((String)args[1]) != null  && !((String)args[1]).isEmpty() ?(String) args[1] : this.password;
		this.failToLogin = args.length >= 3 ?(Boolean)args[2] : this.failToLogin;
		this.skipLoginVerification = args.length >= 4 ?(Boolean)args[3] : this.skipLoginVerification;
		this.useRQM = args.length >=5 ?(Boolean)args[4] : this.useRQM;
		
		try{
			utilities.HelperClass.oneBrowserSetup();
			browser_htmlBrowser().waitForExistence(120, DISABLED);
			browser_htmlBrowser().activate();
			browser_htmlBrowser().loadUrl(url);
			sleep(6);
		}catch(Exception  e){
			utilities.HelperClass.CloseAllBrowsers();
			startApp("http://www.google.com");
			browser_htmlBrowser().waitForExistence(120, DISABLED);
			browser_htmlBrowser().loadUrl(url);
			sleep(20);
			logInfo("lost track of Browser:"+e.toString());
		}
		sleep(5);
		
		Boolean isIE = ((String)browser_htmlBrowser().getProperty(".processName")).matches("(?i).*iexplore.*");
		String msg;

		html_loginWindow().waitForExistence(120, DISABLED);
		html_body().click(atPoint(1,1));
		button_languagebutton().click();

		
		TestObject[] languages = html_languageUL().find(atDescendant(".class", "Html.LI"));
		vpManual("NumOfLanguageOption", 5, languages.length).performTest();
		vpManual("FirstLanguageOption", "English", languages[0].getProperty(".text")).performTest();
		vpManual("SecondLanguageOption", "Français", languages[1].getProperty(".text")).performTest();
		vpManual("ThirdLanguageOption", "Français (Canadien)", languages[2].getProperty(".text")).performTest();
		vpManual("FourthLanguageOption", "Deutsch", languages[3].getProperty(".text")).performTest();
		vpManual("FourthLanguageOption", "Magyar", languages[4].getProperty(".text")).performTest();
		
//		html_body().hover(atPoint(1,1));
//		if(!skipLoginVerification){
//			if(isIE){
//				if( !html_body().compare( loginWindow_IEVP() )){
//					logWarning("IE Login Image Verification Failed", html_body().getScreenSnapshot());
//				}else{
//					html_body().performTest( loginWindow_IEVP());
//				}
//			}else{
//				html_body().performTest( loginWindow_FirefoxVP() );
//			}
//		}
		
		msg = "Logging in with the following information: username = < %s > , password = < %s >";
		logInfo(String.format(msg, username, password));
		text_username().click();
		browser_htmlBrowser().inputChars(username);
		text_pwd().click();
		browser_htmlBrowser().inputChars(password);
		button_loginbutton().click();
		sleep(8);
		waitForloading();
		
		if(failToLogin){	
			html_wrongPasswordWindow().waitForExistence(10, DISABLED);
			vpManual("LoginFailedMSG", true, html_wrongPasswordWindow().ensureObjectIsVisible()).performTest();
			button_wrongPassword_OKbutton().click();
			vpManual("LoginWindow_Visible", true, html_loginWindow().ensureObjectIsVisible()).performTest();
		}else{
			waitForloading();
//			TestObject[] loginWindow = find(atDescendant(".id", "loginWindow", "style", new RegularExpression("visibility: visible", false)));
//			while(loginWindow.length>0){
//				sleep(3);
//				loginWindow = find(atDescendant(".id", "loginWindow", "style", new RegularExpression("visibility: visible", false)));
//			}
			vpManual("AfterLogin_LoginWindow_isNotVisible", false, html_loginWindow().ensureObjectIsVisible()).performTest();
			vpManual("AfterLogin_Username_Input_NotVisible", false, text_username().ensureObjectIsVisible()).performTest();
			vpManual("AfterLogin_Password_Input_NotVisible", false, text_pwd().ensureObjectIsVisible()).performTest();
			sleep(2);
		}
		unregisterAll();
	}
}

