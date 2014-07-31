package utilities;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import resources.utilities.HelperScriptHelper;
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
public class HelperScript extends HelperScriptHelper
{
	/**
	 * Script Name   : <b>extractZip</b>
	 * Generated     : <b>Sep 5, 2013 4:49:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/05
	 * @author Administrator
	 */
	

	
	Object[] parameters;
	public void testMain(Object[] args) 
	{
		parameters = args;
		try {
			Method method = this.getClass().getMethod(args[0].toString());
			method.invoke(this);
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void extractZip(){
		@SuppressWarnings("unchecked")
		HashMap<String, String> map = (HashMap<String, String>) parameters[1];
		
		String 	file = map.get("file"),
				password =map.get("password"), 
				extractLocation = map.get("extractLocation"), 
				fileLocation = map.get("fileLocation"), 
				workspace = map.get("workspace"), 
				winrarPath = map.get("winrarPath");
		
		if(password!=null && !password.isEmpty()){
			logInfo("extracting < "+file+"> to < "+ extractLocation +" > using incorrect password" );
			HelperClass.extract(workspace, winrarPath, fileLocation+"\\"+file, extractLocation, "WRONGPASSWORD");
			winRARClosebutton().waitForExistence(240, DISABLED);
			if(!winRARClosebutton().exists()){
				logError("wrong password passed for zip file passed");
			}
			winRARClosebutton().click();
			logInfo("clicked close on wrong password winrar message");
		}
		
		logInfo("extracting < "+file+" > to < "+ extractLocation +" > using password < "+password+" >" );
		HelperClass.extract(workspace, winrarPath, fileLocation+"\\"+file, extractLocation, password);
		sleep(10);
		while(winrarExtractingwindow().exists()){
			sleep(5);
		}		
		//Rare changes the winrar extract dissapears and comes back
		while(winrarExtractingwindow().exists()){
			sleep(5);
		}
		//Rare changes the winrar extract dissapears and comes back
		while(winrarExtractingwindow().exists()){
			sleep(5);
		}
		
		logInfo("ZIP Extraction Complete!");
	}
}

