package NetmailAdminUUI;
import java.util.HashMap;
import java.util.Map;

import resources.NetmailAdminUUI.WebAdminHelper;
import utilities.HelperClass;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.object.map.SpyMappedTestObject;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class WebAdmin extends WebAdminHelper
{
	/**
	 * Script Name   : <b>Proxy</b>
	 * Generated     : <b>Jul 8, 2014 1:51:56 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/08
	 * @author Administrator
	 */
	
	private String jobName = "";
	private String ip = IP;
	private String userName = webAdminUserName;
	private String password = webAdminPassword;
	
	public void testMain(Object[] args) 
	{
		while(!button_xbutton().ensureObjectIsVisible());
	}
	
	
	/**
	 * Method: loadWebadminUUI
	 * precondition: class variable ip is set
	 * Description: load IE to webadmin
	 * Example: 
	 */
	public void loadWebadminUUI(){
		try{
			utilities.HelperClass.oneBrowserSetup();
			browser_htmlBrowser().waitForExistence(120, DISABLED);
			browser_htmlBrowser().activate();
			browser_htmlBrowser().loadUrl("https://"+ip);
			logInfo("loading page "+"https://"+ip);
			sleep(6);
		}catch(Exception  e){
			utilities.HelperClass.CloseAllBrowsers();
			utilities.HelperClass.oneBrowserSetup();			
			browser_htmlBrowser().waitForExistence(120, DISABLED);
			browser_htmlBrowser().loadUrl("https://"+ip);
			sleep(20);
			logInfo("lost track of Browser:"+e.toString());
		}
		sleep(5);

		sleep(4);
		if(link_overridelink().exists()){
			link_overridelink().click();
		}
		text_username().waitForExistence(200, 5);
	}
	
	/**
	 * Method: login
	 * precondition: webadmin uui loaded, class variable are set (i.e userName, password)
	 * Description: login to webadmin
	 * Example: 
	 */
	public void login(){
		text_username().click();
		browser_htmlBrowser(document_netmail(), DEFAULT_FLAGS).inputChars(userName);
		text_password().click();
		browser_htmlBrowser(document_netmail(), DEFAULT_FLAGS).inputChars(password);
		button_logInbutton().doubleClick();
		link_archive().waitForExistence(300, 5);
		logInfo("logging in webadmin UUI: "+userName+"/"+password);
		
	}
	
	
	/**
	 * Method: navigateTree
	 * precondition: logged in
	 * Description: navigate the tree on the left
	 * Example:  navigateTree("Archive>Cluster.*>Agents>Archive>ok");
	 */
	public void navigateTree(String traversePath){
		String[] paths = traversePath.split(">");
		
		sleep(2);
		TestObject root =  html_framework().find(atDescendant(".tag", "DIV", "class", "treeDiv"), true)[0];
		for(String path: paths){
			RegularExpression pathRegexp = new RegularExpression(path, false);
			TestObject[] link = root.find(atDescendant(".tag", "A", ".contentText", pathRegexp), false);
			if(link.length == 1){
				((GuiTestObject)link[0]).click();
				logInfo("clicked "+path);
			}else if(link.length == 0 ){
				logError("CAN'T FIND < "+path+" > in tree" );
				
			}else if(link.length >1 ){
				logInfo("More than one path < "+path+" > in tree, clicking first one" );
				((GuiTestObject)link[0]).click();
			}
			sleep(6);
			root = link[0].getParent().getParent().find(atChild(".tag", "UL"), false)[0]; //Next contain UL
		}
	}
	
	/**
	 * Method: selectPageTab
	 * precondition: must be login, page has tabs
	 * Description: click the a tab by it's name
	 * Example: selectPageTab("Monitor") 
	 */
	public void selectPageTab(String tabName){
		TestObject tabPanel = html_admin().find(atDescendant(".tag", "DIV", "class", "tabs"), false)[0];
		TestObject tab = tabPanel.find(atDescendant(".tag", "LI", ".contentText", tabName), false)[0];
		((GuiTestObject)tab).click();
		logInfo("Clicked "+tabName+" tab");
	}
	
	
	/**
	 * Method: addProxiesAccess
	 * precondition: must be in the proxie admin page
	 * Description: Allow userA proxess access to user(s)
	 * Example: addProxiesAccess("userA", "userB, userC"); 
	 */
	public void addProxiesAccess(String userName, String accessedUserList){
		html_admin().click();
		text_idFilter().click();
		browser_htmlBrowser().inputKeys("^a");
		browser_htmlBrowser().inputKeys(userName);
		button_findbutton().click();
		button_findbutton().click();
		logInfo("clicked find button");
		sleep(10);
		list_idUserList().click(atText(userName));
		logInfo("selected "+userName+" on user list");
		button_selectButton().click();
		logInfo("click select button");
		for(String user : accessedUserList.split(",")){
			text_idFilter().click();
			browser_htmlBrowser().inputKeys("^a");
			browser_htmlBrowser().inputKeys(user);
			button_findbutton().click();
			sleep(10);
			logInfo("clicked find button");
			list_idUserList().click(atText(user));
			logInfo("click "+user+" in proxy list");
			button_addButton().click();
			logInfo("click add button");
		}
		
		button_savebutton().click();
		logInfo("Clicked Save button");
		button_htmlDialogButtonOK().waitForExistence(30, 2);
		button_htmlDialogButtonOK().click();
		logInfo("click dialog OK button");
		logInfo("< "+userName+" > now has proxy access to the following users < "+accessedUserList+">");
	}
	
	
	/**
	 * Method: removeProxiesAccess
	 * precondition: must be in the proxie admin page
	 * Description: remove userA proxess access to user(s)
	 * Example: removeProxiesAccess("userA", "userB, userC"); 
	 */
	public void removeProxiesAccess(String userName, String accessedUserList){
		html_admin().click();
		
		text_idFilter().click();
		browser_htmlBrowser().inputKeys("^a");
		browser_htmlBrowser().inputKeys(userName);
		button_findbutton().click();
		
		sleep(30);
		logInfo("clicked find button");
		list_idUserList().click(atText(userName));
		logInfo("selected "+userName+" on user list");
		button_selectButton().click();
		logInfo("click select button");
		
		for(String user : accessedUserList.split(",")){
			list_idProxyList().click(atText(user));
			logInfo("click "+user+" in proxy list");
			button_removebutton().click();
			logInfo("click remove button");
		}
		button_savebutton().click();
		logInfo("Clicked Save button");
		button_htmlDialogButtonOK().click();	
		logInfo("click dialog OK button");
		logInfo("< "+userName+" > no longer has proxy acccess to  < "+accessedUserList+">");
	}
	
	
	/**
	 * Method: archiveAccount
	 * precondition: on archive agent page, archive agent exists
	 * Description: using the default setting of the agent, archives one account to a location
	 * Example: archiveAccount("userA", "Location"); 
	 */
	public void archiveAccount(String accountName, String ArchiveLocation){
		
		button_settingUsersSelect().click();
		logInfo("clicked select button for user");
		//Remove all user in archive list (setup)
		for(TestObject options : list_userListB().getChildren()){
			((GuiTestObject)options).click();
			button_archiveUserList_Remove().click();
		}
		
		button_listUsersbutton().click();
		logInfo("click list user button");
		list_userListA().click(atText(accountName));
		logInfo("Selecting "+accountName+" on listA(left)");
		button_archiveUserList_AddButt().click();
		logInfo("click add button");
		button_oKbutton().click();
		logInfo("click ok button");
		
		
		
		button_savebutton().click();
		logInfo("click save button");
		while(!button_xbutton().ensureObjectIsVisible());
		button_xbutton().click();
		logInfo("click X button on successful save");
		
		selectPageTab("Criteria");
		sleep(3);
		
		list_cSelect_ArchiveLocationLi().click();
		logInfo("click archive select dropdown");
		list_cSelect_ArchiveLocationLi().click(atText(ArchiveLocation));
		logInfo("selected "+ArchiveLocation+" archive location");
		
		
		button_savebutton().click();
		logInfo("click save button");
		while(!button_xbutton().ensureObjectIsVisible());
		button_xbutton().click();
		logInfo("click X button on successful save");

		
		selectPageTab("Job Settings");
		sleep(5);
		
		button_runNowbutton().click();
		logInfo("click run button");
		button_htmlDialogButtonOK().click();
		logInfo("click ok button on dialog");
		
	}
	
	
	/**
	 * Method: indexAccount
	 * precondition: on index agent page, index agent exists
	 * Description: using the default setting of the agent, index one account in a location
	 * Example: indexAccount("userA", "Location"); 
	 */
	public void indexAccount(String accountName, String ArchiveLocation){
		
		//Select Location
		selectPageTab("Criteria");
		sleep(5);
		
		list_indexLocationList().click();
		logInfo("click index select dropdown list");
		list_indexLocationList().click(atText(ArchiveLocation));
		logInfo("selected "+ArchiveLocation+" index location");
		
		button_savebutton().click();
		logInfo("click save button");
		
		while(!button_xbutton().ensureObjectIsVisible());
		if(button_xbutton().ensureObjectIsVisible()){	
			button_xbutton().click();
		}else{
			sleep(10);
			button_xbutton().click();
		}
		logInfo("click X button on successful save");
		
		runJob(accountName);
		
	}
	
	public void runJob(String accountName){
		selectPageTab("Job Settings");
		sleep(3);
	
		//Select User
		if(button_settingUsersSelect().exists()){
			button_settingUsersSelect().click();
		}else{
			button_settingUsersSelect2().click();
		}
		logInfo("clicked user select button");
		
		for(TestObject options : list_userSelectionListB().getChildren()){
			((GuiTestObject)options).click();
			button_userSelectionRemove().click();
		}
		
		button_userSelectionLoad().click();
		logInfo("clicked load button");
		list_userSelectionListA().click(atText(accountName));
		logInfo("selecting "+accountName+" in index location");
		button_userSelectionAdd().click();
		logInfo("clicked add button");
		button_index_OK().click();
		logInfo("clicked OK button");
		
		button_savebutton().click();
		logInfo("clicked save button");
		while(!button_xbutton().ensureObjectIsVisible());
		button_xbutton().click();
		logInfo("clicked X button");
		
		//Run
		button_runNowbutton().click();
		logInfo("clicked RUN button");
		button_htmlDialogButtonOK().click();
		logInfo("clicked OK button on dialog");
	}
	
	public void runSyncAB(String exUser, String exPassword, String powerShellURL, String exEmail){
		boolean dirty = false;
		if(exUser != null){
			text_c_EXUSER().click();
			browser_htmlBrowser(document_netmail(),DEFAULT_FLAGS).inputChars(exUser);
			dirty = true;
		}
		if (exPassword !=null) {
			text_c_EXPASSWORD().click();
			browser_htmlBrowser(document_netmail(), DEFAULT_FLAGS).inputChars(exPassword);
			dirty = true;
		}
		if (powerShellURL != null) {
			text_c_EXPATH().click();
			browser_htmlBrowser(document_netmail(), DEFAULT_FLAGS).inputChars(powerShellURL);
			dirty = true;
		}
		if (exEmail != null) {
			text_c_EXUSEREMAIL().click(atPoint(193, 14));
			browser_htmlBrowser(document_netmail(), DEFAULT_FLAGS).inputChars(exEmail);
			dirty = true;
		}
		if(dirty){
			button_savebutton().click();
		}
		button_runNowbutton2().click();
		logInfo("clicked run syncAB button");
	}
	
	
	/**
	 * Method: waitForJob
	 * precondition: archive monitor page
	 * Description: waits until the agent dissapears from the page
	 * Example: waitForJob("AgentName"); 
	 */
	public void waitForJob(String name){
		this.jobName = name;
		boolean jobFinished = false;
		while(!jobFinished){
			jobFinished = !foundJob();
			if(jobFinished){
				sleep(4);
				jobFinished = !foundJob();
			}
			sleep(10);
		}
	}
	

	/**
	 * Method: pageActions
	 * precondition: button are loaded
	 * Description: the save cancel, rename clone, delete button on the bottom of the page
	 * Example: 
	 */
	public void clickActionBarButton(String buttonName){
		TestObject button = html_allButtons().find(atDescendant(".class", "Html.INPUT.button", ".value", buttonName))[0];
		((GuiTestObject) button).click();
		logInfo(String.format("clicked  %s button", buttonName));
	}
	
	/**
	 * Method: pageActions
	 * precondition: agent container page
	 * Description: the save cancel, rename clone, delete button on the bottom of the page
	 * Example: 
	 */
	public void createJob(String jobName, boolean createAgent){
		clickActionBarButton("Create");
		
		text_jobName().waitForExistence(10, 2);
		text_jobName().click();
		BrowserTestObject browser = (BrowserTestObject) text_jobName().getTopMappableParent();
		browser.inputChars(jobName);
		if(createAgent){
			button_jobCreate().click();
		}else{
			button_jobCancel().click();
		}
	}
	
	private boolean foundJob(){
		TestObject[] jobs = table_runningJob().find(atDescendant(".tag", "TR"), false);
		for(TestObject job : jobs){
			TestObject[] jobColumns = job.find(atChild(".tag", "TD"), false);
			if(jobColumns.length > 0 ){
				String jobTitle = (String) jobColumns[0].getProperty(".text");
				return jobTitle.contentEquals(jobName);
			}
		}
		return false;
	}
	
	

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

