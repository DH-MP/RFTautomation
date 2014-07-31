package TC_856_AdvanceLoggingAndWatchDog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import resources.TC_856_AdvanceLoggingAndWatchDog.TS_1047_NetmailSearch_AdvanceLoggingHelper;
import utilities.HelperClass;

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
public class TS_1047_NetmailSearch_AdvanceLogging extends TS_1047_NetmailSearch_AdvanceLoggingHelper
{
	/**
	 * Script Name   : <b>TS_1047_NetmailSearch_AdvanceLogging</b>
	 * Generated     : <b>Nov 7, 2013 12:36:22 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/11/07
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		IVariablesManager vm =getVariablesManager();
		IParameter rqmIP = vm.getInputParameter("ip");
		IParameter rqmPort = vm.getInputParameter("port");
		String ip = rqmIP == null ? IP : rqmIP.getValue();
		String port = rqmPort == null? "8888" : rqmPort.getValue();
		String userName = "amy";
		
		HelperClass.CloseAllBrowsers();
		sleep(5);
		HelperClass.oneBrowserSetup();
		sleep(5);
		browser_htmlBrowser().loadUrl("http://"+ip+":"+port+"/remote?cmd=log&status=off");
		vpManual("DoneMessage", "Done", document_htmlDocument().getProperty(".text").toString().trim()).performTest();
		logInfo("Turning off dplog");
		browser_htmlBrowser().loadUrl("http://"+ip+":"+port+"/remote?cmd=log&status=on");
		vpManual("DoneMessage", "Done", document_htmlDocument().getProperty(".text").toString().trim()).performTest();
		logInfo("Turning on dplog");
		
		
		//Login
		Object[] ls = {userName, "blub", true, false};
		callScript("loginScript", ls);
		
		BufferedReader br = null;
		try {
		    String sCurrentLine;

		    br = new BufferedReader(new FileReader("\\\\"+ip+"\\Messaging Architects\\RemoteProvider\\dp.log"));
		    boolean foundLog = false;
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
		    java.util.Date now = new java.util.Date();
		    String expectedRegexp = ft.format(now).substring(0, ft.format(now).length()-1)+"\\d:"+"\\d\\d\\.\\d{0,4}\\s{3,5}.*Failure response: User or password is not correct!";
		    logInfo("Looking for a log that matches < "+ expectedRegexp +">");  
		    while ((sCurrentLine = br.readLine()) != null) 
		    {	
			    String bindFailMsg = sCurrentLine.trim();
			    if(bindFailMsg.matches(expectedRegexp)){
			    	foundLog = true;
			    }
			    if(!foundLog){
			    	java.util.Date past = new java.util.Date(now.getTime() - 60000);
			    	java.util.Date future = new java.util.Date(now.getTime() + 60000);
				    expectedRegexp = ft.format(past).substring(0, ft.format(now).length()-1)+"\\d:"+"\\d\\d\\.\\d{0,4}\\s{3,5}.*Failure response: User or password is not correct!";;
				    if(bindFailMsg.matches(expectedRegexp)){
				    	foundLog = true;
				    	break;
				    }
				    
				    expectedRegexp = ft.format(future).substring(0, ft.format(now).length()-1)+"\\d:"+"\\d\\d\\.\\d{0,4}\\s{3,5}.*Failure response: User or password is not correct!";;
				    if(bindFailMsg.matches(expectedRegexp)){
				    	foundLog = true;
				    	break;
				    }
				    
			    }

		    }
		    vpManual("FoundLDAPBindLog", true, foundLog).performTest();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (br != null){
		        	br.close();
		        }
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		}
	     
	    browser_htmlBrowser().loadUrl("http://"+ip+":"+port+"/remote?cmd=log&status=off");
		vpManual("DoneMessage", "Done", document_htmlDocument().getProperty(".text").toString().trim()).performTest();
		logInfo("Turning off loggin");
	}
}

