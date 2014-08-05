package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
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

public class HelperClass extends RationalTestScript {
	
	public static void oneBrowserSetup(){
		TestObject[] browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
		if(browsers.length>1){
			CloseAllBrowsers();
			startBrowser("Internet Explorer","http://www.google.com");
			logInfo("Open IE");
			sleep(2);
			
			TestObject browser = findBrowser();
			((BrowserTestObject) browser).waitForExistence(120, DISABLED);
			((BrowserTestObject) browser).deleteCookies();
			((BrowserTestObject) browser).maximize();
			logInfo("Maximize browser");
			
		}else if(browsers.length==0){
			startBrowser("Internet Explorer","http://www.google.com");
			sleep(2);
			
			TestObject browser = findBrowser();
			((BrowserTestObject) browser).deleteCookies();
			((BrowserTestObject) browser).maximize();
			logInfo("Maximize browser");
			sleep(3);
		}	
	}
	
	public static void CloseAllBrowsers(){
		TestObject[] browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
		for(TestObject browser : browsers){
			((BrowserTestObject) browser).close();
		}
	}
	
	public static TestObject findBrowser(){
		TestObject[] browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
		int counter = 0;
		while(browsers.length == 0){
			browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
			sleep(6);
			if(counter++>10){ //~ 60 seconds
				logError("Timeout: Cannot find browser");
				return null;
			}
		}
		
		return browsers[0];
	}
	
	public static TestObject[] getActiveTabBody(){
		TestObject [] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", new RegularExpression("^(\\s)*x-tab-panel-body x-tab-panel-body-top(\\s)*$", false)), true);
		return tabPanelBody[0].find(atChild(".tag", "DIV", "class", new RegularExpression("^(\\s)*x-panel x-panel-noborder(\\s)*$", false)));
	}

	
	public static GuiTestObject ieNotificationElement(String name){
		Property[] button = { 	new Property(".class", "Html.HtmlBrowser.NotificationBarControl"),
								new Property(".name", name),
				
		};
		TestObject[] ieElement = find(atDescendant(button));
		return  ieElement.length == 1? (GuiTestObject) ieElement[0] : null;
	}
	
	//TABS
	public static GuiTestObject findActiveTab(){
		//This method just find the tabs, not the body of the tab.
		TestObject[] tab = find(atDescendant(".tag", "LI", "class", "x-tab-strip-closable x-tab-strip-active"), true);
		while( tab.length != 1){
			tab = find(atDescendant(".tag", "LI", "class", "x-tab-strip-closable x-tab-strip-active"), true);
		}
		return (GuiTestObject)tab[0];
	}
	
	
	public static TestObject[] findAllTabs(){
		return find(atDescendant(".class","Html.DIV", "class", new RegularExpression("x-tab-panel-header.*", false)))[0].find(atDescendant(".tag", "LI", "class", new RegularExpression("x-tab-strip-closable.*",false)));
	}
	
	
	public static Object[] extractAndComparePDF(File expected, File actual, String mask){
		String expectedText ="";
		String actualText = "";
		Object[] result = new Object[3];
		try{	
			
			//Load the PDF files
			PDDocument expectedDoc = PDDocument.load(expected);
			PDDocument actualDoc = PDDocument.load(actual);
			
			PDFTextStripper stripper = new PDFTextStripper();
			expectedText = stripper.getText(expectedDoc).toString();
			actualText = stripper.getText(actualDoc).toString();
			
			if(mask!=null){
				expectedText = expectedText.replaceAll(mask, "HIDDEN");
				actualText = actualText.replaceAll(mask, "HIDDEN");
			}
			diff_match_patch dmp = new diff_match_patch();
			LinkedList<Diff> diffList = dmp.diff_main(expectedText, actualText,true);
			dmp.diff_cleanupSemantic(diffList);
			
			result[0]=expectedText;
			result[1]=actualText;
			if(expectedText.equals(actualText)){
				result[2]=true;
				return result;
			}else{
				result[2]=false;
				return result;
			}
		}
		catch (Exception e){
			RationalTestScript.logException(e);
		}
		
		
		return null;
	}
	
	//Use the one in HelperScript class
	@Deprecated
	public static void extract(String workspace, String winrarPath, String fileLocation, String extractLocation, String password ){
		System.out.println("aiodjaoidja  "+password);
		password = password == null | password.isEmpty() ? " " : " "+password+" " ;
		try {
			Process b = Runtime.getRuntime().exec("cmd.exe /C start "+workspace+"\\assets\\winrar.bat \""+winrarPath+"\" "+fileLocation+password+extractLocation);
			logInfo("Extracting the file at < "+fileLocation+" > to < "+extractLocation+" >" );
			sleep(3);
			b.destroy();
		} catch (IOException e) {
			logError("ZIP Extraction FAILED:"+e.toString());
			e.printStackTrace();
			stop();
		}
	}
	
	public static String getCheckSum(File file, String format){

		try {
		    MessageDigest md = MessageDigest.getInstance(format);
		    FileInputStream fis = new FileInputStream(file);
		    byte[] dataBytes = new byte[1024];
		 
		    int nread = 0; 
		 
		    while ((nread = fis.read(dataBytes)) != -1) {
		      md.update(dataBytes, 0, nread);
		    };
		 
		    byte[] mdbytes = md.digest();
		 
		    //convert the byte to hex format
		    StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < mdbytes.length; i++) {
		    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    }
		 
		   fis.close();
		   return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}	
	}
	
	
	
	public static void startOrStopNetmailServices(boolean start, String ip, String workSpace){
		String netmailIP = "\\\\"+ip;
		String[] a;
		Process b;
		if(start){
			// start all services
			a = new String[] {
					"cmd.exe",
					"/C start " + workSpace + "\\assets\\startAllNetmailServices.bat "
							+ "\""+netmailIP };
			try {
				b = Runtime.getRuntime().exec(a);
				sleep(3);
				b.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//stop all services
			a = new String[] {
					"cmd.exe",
					"/C start " + workSpace + "\\assets\\stopAllNetmailServices.bat " + "\"" + netmailIP};
			
			try {
				b = Runtime.getRuntime().exec(a);
				sleep(3);
				b.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		sleep(50);
	}
	
	public static void startstopRemoteService(boolean start, String ip, String serviceName, String workSpace){
		String netmailIP = "\\\\"+ip;
		String[] a;
		Process b;
		if(start){
			// start all services
			a = new String[] {
					"cmd.exe",
					"/C start " + workSpace + "\\assets\\startRemoteService.bat "
							+ "\""+netmailIP+"\" \""+serviceName };
			try {
				b = Runtime.getRuntime().exec(a);
				sleep(3);
				b.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//stop all services
			a = new String[] {
					"cmd.exe",
					"/C start " + workSpace + "\\assets\\stopRemoteService.bat " + "\"" + netmailIP+"\" \""+serviceName};
			
			try {
				b = Runtime.getRuntime().exec(a);
				sleep(3);
				b.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		sleep(60);
	}
	
	public static void navigateLocation(String traversePath){
		//For netmail search
		TestObject activeBody = getActiveTabBody()[0];
		TestObject locationTree = activeBody.find(atDescendant(".tag", "DIV", "class", "x-tree-root-node"), false)[0];
		String[] paths = traversePath.split(">");
		TestObject root =  locationTree;
		TestObject[] link = null;
		for(String path: paths){
			RegularExpression pathRegexp = new RegularExpression(path, false);
			link = root.find(atDescendant(".tag", "A", ".contentText", pathRegexp), false);
			RegularExpression a = new RegularExpression(".*-plus.*", false);
			if(link.length == 1){
				//Click arrow
				TestObject[] arrow = link[0].getParent().find(atDescendant(".tag", "IMG", "class", a),false);
				if(arrow.length>0){
					((GuiTestObject)arrow[0]).click();
					logInfo("clicked "+path);
					sleep(6);
				}

			}else if(link.length == 0 ){
				logError("CAN'T FIND < "+path+" > in tree" );
				
			}else if(link.length >1 ){
				logInfo("More than one path < "+path+" > in tree, clicking first one" );
				((GuiTestObject)link[0].getParent().find(atDescendant(".tag", "IMG", "class", a),false)[0]).click();
			}
			root = link[0].getParent().getParent().getChildren()[1];
		}
		
		//last path click on
		if(link!=null){
			((GuiTestObject)link[0]).click();
		}
	}
}

