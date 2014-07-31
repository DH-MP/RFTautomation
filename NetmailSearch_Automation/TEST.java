
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import resources.TESTHelper;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
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
public class TEST extends TESTHelper
{
	/**
	 * Script Name   : <b>TEST</b>
	 * Generated     : <b>Jul 2, 2014 11:36:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/02
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		
		
		// HTML Browser
		// Document: RFT - Outlook Web App: 
		int a = browser_htmlBrowser().find(atDescendant(".tag", "SPAN", "id", "_ariaId_40.folder"), false).length;
		System.out.println(a);
		//		for(int i = 11; i<1001; i++){
//			html__ariaId_40_2().click(RIGHT);
//			html__ariaId_143().click(atPoint(35,17));
//			browser_htmlBrowser(document_rftOutlookWebApp(),LOADED).inputKeys(i+"{ENTER}");
//			sleep(0.5);
//		}
		
		
		
		
//		startApp("http://www.google.com");
//		
//		
//		// HTML Browser
//		// Document: Netmail Search: 
//		table_htmlTable_1_2().click(atCell(atRow(atIndex(0)), 
//                                     atColumn(atIndex(1))));
//		table_htmlTable_7().click(SHIFT_LEFT, atCell(
//                                        atRow(atIndex(0)), 
//                                        atColumn(atIndex(1))));
		
//		// HTML Browser
//		// Document: Netmail Search: 
//		table_htmlTable_1().click(SHIFT_LEFT, atCell(
//                                        atRow(atIndex(0)), 
//                                        atColumn(atIndex(0))));
//		table_htmlTable_2().click(SHIFT_LEFT, atCell(
//                                        atRow(atIndex(0)), 
//                                        atColumn(atIndex(0))));
//		
//		for(File a : File.listRoots()){
//		System.out.println(a.getName());
//		}
//		try {
//			System.out.println(InetAddress.getLocalHost().getHostAddress());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		File sharedFolder = new File("C:\CSEE");
//		
//		System.out.println(sharedFolder.mkdirs());
		
		
//		try {
//
//			FileWriter fstream2 = new FileWriter("C:\\loadTest.xml");
//			BufferedWriter bw = new BufferedWriter(fstream2);
//				bw.write("<tests ssid=\"20usrreq\">");
//				bw.newLine();
//				for(int i = 1; i<401; i++){
//					String format = "<test name=\"user%s\" loc=\"Load\" user=\"Sales Sales%s@Sales%s.po2.gw2012\"></test>";
//					String result = String.format(format, i, i ,i);
//					bw.write(result);
//					bw.newLine();
//				}
//				bw.write("<tests/>");
//				
//			bw.close();
//
//
//
//		} catch (Exception e) {
//			System.err.println("Error: " + e.getMessage());
//		}
		
		
//		int ldapPort = LDAPConnection.DEFAULT_PORT;
//		int ldapVersion = LDAPConnection.LDAP_V3;
//		String ldapHost = "10.10.23.220";
//		String loginDN =  "CN=administrator,CN=users,DC=auto2k10,DC=com";
//		String password = "Pa$$w0rd";
//		LDAPConnection lc = new LDAPConnection();
//		ArrayList modList = new ArrayList();
//		
//		try {
//			lc.connect(ldapHost, ldapPort);
//			
//			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
//			
//			System.out.println(lc.isConnected());
////			LDAPSearchResults searchResults = lc.search("CN=users,DC=auto2k10,DC=com", LDAPConnection.SCOPE_ONE, "cn=RFT", null, false);
////			LDAPEntry userRFT = searchResults.next();
//			
//			LDAPAttribute attribute = new LDAPAttribute( "sAMAccountName", "rftAD");
//		    lc.modify("CN=RFT,CN=users,DC=auto2k10,DC=com", new LDAPModification(LDAPModification.REPLACE, attribute));
//		    System.out.println(lc.read("CN=RFT,CN=users,DC=auto2k10,DC=com").getAttributeSet());
//		    System.out.println(lc.toString());
//		    
//		    
//		} catch (LDAPException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		logInfo("done");
	}
}

