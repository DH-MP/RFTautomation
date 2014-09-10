
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		
		while(true){
			html__130().click(RIGHT, atPoint(13,8));
			sleep(2);
			html__ariaId_146().click(atPoint(47,67));
			button_okbutton2().click();
			sleep(1);
			unregisterAll();
		}
		
		
		
//		// HTML Browser
//		// Document: RFT - Outlook Web App: 
//
//					while(true){
//						BrowserTestObject b = (BrowserTestObject) utilities.HelperClass.findBrowser();
//						TestObject[] c = b.find(atDescendant(".tag", "DIV", "id", "_ariaId_40.subfolders"), false);
//						TestObject[] a = ((GuiTestObject)c[0]).getChildren();
//						((GuiTestObject) a[1]).hover();
//						((GuiTestObject) a[1]).click();
//						((GuiTestObject) a[1]).click(RIGHT);
//						sleep(1.2);
//						try{
//							((GuiTestObject) b.find(atDescendant(".tag", "DIV", ".className", "contextMenuDropShadow contextMenuPopup removeFocusOutline"), true)[3]).click(atPoint(31,14));
//							button_okbutton().click();
//						} catch(Exception e){
//							((GuiTestObject) b.find(atDescendant(".tag", "DIV", ".className", "contextMenuDropShadow contextMenuPopup removeFocusOutline"), true)[3]).click(atPoint(31,14));
//							button_okbutton().click();
//						}
//						unregisterAll();
//					}




	}
}

