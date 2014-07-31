package TestCases.TC_1186_ProxiesAdmin;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import resources.TestCases.TC_1186_ProxiesAdmin.TS_1318_ProxiesAdminHelper;
import utilities.HelperClass;
import utilities.HelperScript;
import NetmailAdminUUI.WebAdmin;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
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
public class TS_1318_ProxiesAdmin extends TS_1318_ProxiesAdminHelper
{
	/**
	 * Script Name   : <b>TS_1318_ProxiesAdmin</b>
	 * Generated     : <b>Jul 8, 2014 3:28:58 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/08
	 * @author Administrator
	 */
	
	private String ldapHost = "";
	private String exAdminDN =  "";
	private String exAdminPassword = "";
	private String userDN = "";
	private String userBName = "";
	private String workSpace = "\\\\10.10.23.61\\Data\\NetmailSearchGV\\NetmailSearch_Automation";
	public void testMain(Object[] args) 
	{
		String webAdminUserName = dpString("webAdminUserName");
		String webAdminPassword = dpString("webAdminPassword");
		String storageLocation = dpString("storageLocation");
		String archiveAgent = dpString("archiveAgent");
		
		String userAName = dpString("userAName");
		String userApassword = dpString("userApassword");	
		userBName = dpString("userBName"); //THE USER being accesed by proxy
		String domain = dpString("domain");

		ldapHost = dpString("ldapHost");
		exAdminDN =  dpString("exAdminDN");
		exAdminPassword = dpString("exAdminPassword");
		userDN = dpString("userDN");
		
		
		//Webadmin setup
		WebAdmin wa = new WebAdmin();
		wa.setIp(IP);
		wa.setUserName(webAdminUserName);
		wa.setPassword(webAdminPassword);
		
		//Login
		wa.loadWebadminUUI();
		wa.login();
		sleep(10);
		
		//Run SYNCAB
		wa.navigateTree("Cluster");
		wa.selectPageTab("Address Book Sync");
		wa.runSyncAB(null, null, null, null);//run current settings
		sleep(30);
		
		//Archive account
		wa.navigateTree("Archive>Cluster.*>Agents>Archive>"+archiveAgent);
		wa.archiveAccount(userBName+domain, storageLocation);
		wa.navigateTree("Archive");
		sleep(30);
		wa.waitForJob(archiveAgent);
		
		
		//Add Proxy access
		wa.navigateTree("Archive");
		sleep(2);
		wa.selectPageTab("Proxies Admin");
		sleep(2);
		wa.addProxiesAccess(userAName+domain, userBName+domain);
		
		//Restart NetmailSearch
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
		
		//Login NS and verify proxy access
		Object[] ls = {userAName, userApassword, false};
		callScript("loginScript", ls);
		HelperClass.navigateLocation(storageLocation+">"+userBName);

		//LDAP change account name
		String newName = renameUser(userDN, ldapHost, exAdminDN, exAdminPassword, null);
		
		//Run SYNCAB
		wa.loadWebadminUUI();
		wa.login();
		sleep(10);
		wa.navigateTree("Cluster");
		wa.selectPageTab("Address Book Sync");
		wa.runSyncAB(null, null, null, null);//run current settings
		sleep(30);
		
		//Re-Archive under new account name
		wa.navigateTree("Archive>Cluster.*>Agents>Archive>"+archiveAgent);
		wa.archiveAccount(newName+domain, storageLocation);
		wa.navigateTree("Archive");
		sleep(30);
		wa.waitForJob(archiveAgent);
		
		//Restart NetmailSearch
		HelperClass.startOrStopNetmailServices(false, IP, workSpace);
		HelperClass.startOrStopNetmailServices(true, IP, workSpace);
		
		//Check if still has proxy access
		callScript("loginScript", ls);
		HelperClass.navigateLocation(storageLocation+">"+newName);
		
		//Remove proxy access
		wa.loadWebadminUUI();
		wa.login();
		sleep(10);
		wa.navigateTree("Archive");
		sleep(2);
		wa.selectPageTab("Proxies Admin");
		sleep(2);
		wa.removeProxiesAccess(userAName+domain, userBName+domain);
		
	}
	
	@Override
	public void onTerminate(){
		if(!ldapHost.isEmpty())
			renameUser(userDN, ldapHost, exAdminDN, exAdminPassword, userBName);
	}
	
	
	private TestObject[] getActiveTabBody(){
		TestObject [] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", new RegularExpression("^(\\s)*x-tab-panel-body x-tab-panel-body-top(\\s)*$", false)), true);
		return tabPanelBody[0].find(atChild(".tag", "DIV", "class", new RegularExpression("^(\\s)*x-panel x-panel-noborder(\\s)*$", false)));
	}
	
	
	private String renameUser(String userCN, String ldapHost, String loginDN, String password, String newName){
		
		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int ldapVersion = LDAPConnection.LDAP_V3;
		LDAPConnection lc = new LDAPConnection();
		
		try {
			lc.connect(ldapHost, ldapPort);
			
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
			
			System.out.println(lc.isConnected());
			String currentName;
			if(newName == null){
				//Set some random anme
				currentName = lc.read(userCN).getAttributeSet().getAttribute("sAMAccountName").getStringValue();
				System.out.println(currentName);
				java.util.Date date= new java.util.Date();
				DateFormat df = new SimpleDateFormat("ddMMYYHHmmss");
				newName = "auto" + df.format(date);
			}
			System.out.println(newName);
			
			LDAPAttribute attribute = new LDAPAttribute( "sAMAccountName", newName);
		    lc.modify(userCN, new LDAPModification(LDAPModification.REPLACE, attribute));

		    
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return newName;
	}
}

