package NetmailSearch_General;

import java.util.Arrays;

import resources.NetmailSearch_General.NetmailLoginHelper;
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
public class NetmailLogin extends NetmailLoginHelper
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
	private boolean failToLogin = false;
	
	private static NetmailLogin nl = null;
	
	public void testMain(Object[] args) 
	{
	}
	
	
	//Login using default value (may be override by RQM value)
	public static void login(){
		NetmailLogin nl = getInstance();
		nl.performLogin(nl.username, nl.password);
	}
	public static void login(boolean failToLogin){
		NetmailLogin nl = getInstance();
		nl.failToLogin = failToLogin;
		nl.performLogin(nl.username, nl.password);
	}
	public static void login(String username, String password){
		NetmailLogin nl = getInstance();
		if(username != null && password != null ){
			username = username.isEmpty() ? nl.username : username;
			password = password.isEmpty() ? nl.password : password;
		}
		nl.performLogin(username, password);
	}
	
	public static void login(String username, String password, boolean failToLogin){
		NetmailLogin nl = getInstance();
		if(username != null && password != null ){
			username = username.isEmpty() ? nl.username : username;
			password = password.isEmpty() ? nl.password : password;
		}
		nl.failToLogin = failToLogin;
		nl.performLogin(username, password);
	}
	
	private void performLogin(String username, String password){
		String url = URL;
		//Set up browsers;
		try{
			utilities.HelperClass.oneBrowserSetup();
			browser_htmlBrowser().waitForExistence(120, DISABLED);
			browser_htmlBrowser().activate();
			browser_htmlBrowser().deleteCookies();
			sleep(3);
			browser_htmlBrowser().loadUrl(url);

			sleep(6);
		}catch(Exception  e){
			utilities.HelperClass.CloseAllBrowsers();
			startApp("http://www.google.com");
			browser_htmlBrowser().waitForExistence(120, DISABLED);
			sleep(5);
			browser_htmlBrowser().deleteCookies();
			sleep(3);
			browser_htmlBrowser().loadUrl(url);
			sleep(20);
			logInfo("lost track of Browser:"+e.toString());
		}
		sleep(5);
		
		String msg;
		html_loginWindow().waitForExistence(120, DISABLED);

		button_languagebutton().click();
		TestObject[] languages = html_languageUL().find(atDescendant(".class", "Html.LI"));
		vpManual("NumOfLanguageOption", 5, languages.length).performTest();
		vpManual("FirstLanguageOption", "English", languages[0].getProperty(".text")).performTest();
		vpManual("SecondLanguageOption", "Français", languages[1].getProperty(".text")).performTest();
		vpManual("ThirdLanguageOption", "Français (Canadien)", languages[2].getProperty(".text")).performTest();
		vpManual("FourthLanguageOption", "Deutsch", languages[3].getProperty(".text")).performTest();
		vpManual("FourthLanguageOption", "Magyar", languages[4].getProperty(".text")).performTest();
		
		msg = "Logging in with the following information: username = < %s >, password = < %s >";
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
//			vpManual("AfterLogin_LoginWindow_isNotVisible", false, html_loginWindow().ensureObjectIsVisible()).performTest();
//			vpManual("AfterLogin_Username_Input_NotVisible", false, text_username().ensureObjectIsVisible()).performTest();
//			vpManual("AfterLogin_Password_Input_NotVisible", false, text_pwd().ensureObjectIsVisible()).performTest();
//			sleep(2);
		}
		unregisterAll();
	}
	
	
	
	
	private static NetmailLogin getInstance(){
		if(nl == null){
			nl = new NetmailLogin();
		}
		return nl;
	}
}

