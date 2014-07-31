package Transend;
import java.util.HashMap;
import java.util.Map;

import resources.Transend.TransendHelper;
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
public class Transend extends TransendHelper
{
	/**
	 * Script Name   : <b>Transend_EmailSettings</b>
	 * Generated     : <b>Apr 24, 2014 1:19:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/04/24
	 * @author Administrator
	 */
	
	//Source Sections
	private String sourceType = "Exchange Server 2013";	
	private String sourceAddress = "10.1.30.92";
	private String sourceUserEmail = "Cristina01@ex2010-03.com";
	private String sourcePassword = "123Password";
	private String selectedFolders = ".*"; //"Deleted Items, Drafts, Inbox.*, Journal, Junk E-Email, Notes, Outbox, Sent Items, tanya";
	private String sourceOutlookMSGDirectory = "";
	private String sourceUserName = "";
	
	private String targetType = "Netmail XML Archive";
	private String targetSharedDirectory = "\\\\10.10.23.225\\TransendData";
	private String targetUserName = "Cristina01";
	private String targetUserCN = "Cristina01@ex2010-03.com";
	
	private String category = "";
	private boolean emailSection = false, calendarSection = false, addressbookSection = false, taskSection = false; 
	public void testMain(Object[] args) 
	{
		if(args != null && args.length > 0){
			@SuppressWarnings("unchecked")
			Map<String, Object> data = (Map<String, Object>) args[0];
			category = (String) data.get("category");
			sourceType 		= data.get("sourceType") 	== null 	? sourceType 		: (String)data.get("sourceType") ;
			sourceAddress 	= data.get("sourceAddress") == null 	? sourceAddress 	: (String)data.get("sourceAddress");
			sourceUserEmail = data.get("sourceUserEmail") == null 	? sourceUserEmail 	: (String) data.get("sourceUserEmail");
			sourcePassword 	= data.get("sourcePassword") == null 	? sourcePassword 	: (String) data.get("sourcePassword");
			selectedFolders = data.get("selectedFolders") == null 	? selectedFolders 	: (String) data.get("selectedFolders");	
			sourceUserName = data.get("sourceUserName") == null 	? sourceUserName 	: (String) data.get("sourceUserName");
		
			sourceOutlookMSGDirectory = data.get("sourceOutlookMSGDirectory") == null 	? sourceOutlookMSGDirectory 	: (String) data.get("sourceOutlookMSGDirectory");	
			
			targetType = data.get("targetType") == null 						? targetType 			: (String) data.get("targetType");
			targetSharedDirectory = data.get("targetSharedDirectory") == null 	? targetSharedDirectory :(String) data.get("targetSharedDirectory");
			targetUserName = data.get("targetUserName") == null 				? targetUserName 		: (String) data.get("targetUserName");
			targetUserCN = data.get("targetUserCN") == null 					? targetUserCN 			: (String) data.get("targetUserCN");
			
		}
		
		switch (category) {
			case "email":
				emailSection = true;
				calendarSection = addressbookSection =  taskSection = false;
				break;
			case "calendar":
				calendarSection = true;
				
				emailSection = addressbookSection =  taskSection = false;
				break;
				
			case "addressBook":
				addressbookSection = true;
				calendarSection = emailSection =  taskSection = false;
				break;
				
			case "task":
				taskSection = true;
				calendarSection = addressbookSection =  emailSection = false;
				break;
				
			default:
				break;
		}
		
	//Go to section		
		clickSection();
		
	//Enable section
		if(!Boolean.parseBoolean(enableMigrationcheckBox().getProperty(".checked").toString())){
			enableMigrationcheckBox().click();	
			logInfo("enabled Email migration");
		}
		
	//SOURCE
		sourceFrom_Dropdownwindow().click();
		logInfo("Open dropdown");
		logInfo("Selected < "+sourceType+" >");
		if(sourceType.contentEquals("Exchange Server 2013")){
			sourceListlist().click(atName(sourceType));
			sourceExchange2013();	
		}
		
		if(sourceType.contentEquals("GroupWise (via GW API)") ){	
			tDropDownFormwindow().inputChars("g");
			if(emailSection){
				sourceListlist().click(atName("GroupWise <i>(via GW API) </i>"));
			}else{
				sourceListlist().click(atName("GroupWise"));
			}
			sourceGW();	
		}
		
		if(sourceType.contentEquals("Outlook MSG") ){	
			sourceListlist().click(atName("Outlook MSG"));
			sourceOutlookMSG();	
		}
		
		if(sourceType.contentEquals("Google Single User") ){	
			tDropDownFormwindow().inputChars("go");
			sourceListlist().click(atName("Google Single User"));
			if(emailSection){
				sourceGmailEmail();	
			}else{
				sourceGmailOtherSection();
			}
		}

		
		//Folders only in email section
		if(emailSection){
			selectFolders();
		}
		
	//TARGET
		targetTo_DropDownwindow().click();
		targetListlist().click(atName(targetType));
		logInfo("Selected targetType < "+targetType+" >");
		
		if(targetType.contentEquals("Netmail XML Archive")){
			targetNetmailXML();

		}
		

	}
	
	private void clickSection(){
		if(emailSection){
			tCoolBarwindow().click(atPoint(120,36));
			logInfo("Click Email");
		}
		
		if(addressbookSection){
			tCoolBarwindow().click(atPoint(250,36));
			logInfo("Click Address");
		}
		
		if(calendarSection){
			tCoolBarwindow().click(atPoint(450,36));
			logInfo("Click Address");
		}
		
		if(taskSection){
			tCoolBarwindow().click(atPoint(650,36));
			logInfo("Click Address");
		}
	}
	
	private void selectFolders(){
		foldersbutton().click();
		logInfo("Click Folder Button");
		loadFromEMailSourcebutton().click();
		logInfo("Click Load Source Data");
		sleep(5);
		
		String[] checked = selectedFolders.split(",");
		TestObject[] lists = migrationOptionslist().getChildren();
		migrationOptionslist().click(atName(lists[0].getProperty(".name").toString())); 
		for(TestObject b : lists){
			boolean match = false;
			for(String c : checked){
				if(b.getProperty(".name").toString().matches(c.trim())){
					match = true;
				}
			}			
			if(!match){
				transendMigratorMigrationOptio().inputKeys(" {DOWN}");
			}else{
				transendMigratorMigrationOptio().inputKeys("{DOWN}");
			}
		}
		logInfo("Finish applying folder setting");
		folder_OKbutton().click(atPoint(22,10));	
	}
	
	private void sourceExchange2013(){
		serverDomainComOrIPAddresstext().click();
		transendMigratorCProgramDataTr().inputKeys("^a{BKSP}"+sourceAddress);
		logInfo("input address <"+sourceAddress+">");
		
		sourceEmailtext().click();
		sourceEmailtext().click(RIGHT);
		contextpopupMenu().click(atPath("Select All"));
		transendMigratorCProgramDataTr().inputKeys("{BKSP}");
		sourceEmailtext().doubleClick(atPoint(1,1));
		transendMigratorCProgramDataTr().inputChars(sourceUserEmail);
		
		
		logInfo("input email <"+sourceUserEmail+">");
		
		sourcePasswordtext().click();
		transendMigratorCProgramDataTr().inputChars(sourcePassword);
		logInfo("input password <"+sourcePassword+">");
	}
	
	private void targetNetmailXML(){
		outputDirectorytext().click();
		transendMigratorCProgramDataTr2().inputKeys("^a{DELETE}"+targetSharedDirectory);
		
		netmailXML_userNametext().click();
		netmailXML_userNametext().doubleClick();
		transendMigratorCProgramDataTr2().inputKeys(targetUserName);
		logInfo("Enter target user name < "+targetUserName+" >");
		
		netmailXML_userCNtext().click();
		netmailXML_userCNtext().doubleClick();
		transendMigratorCProgramDataTr2().inputKeys(targetUserCN);
		logInfo("Enter target CN < "+targetUserCN+" >");
		
		
		logInfo("Click Test login");
		testLogonbutton().click();
		sleep(2);
		
		if(!logonToTargetSystemWasSuccessf().exists()){
			logError("Target Location TestLogON Failed");
		}
		okbutton().click();	
	}
	
	private void sourceGW(){
		// Window: tm11.exe: RegularExpression(Transend Migrator.*)
		gWuserNametext().click();
		gWuserNametext().doubleClick();
		transendMigratorCProgramDataTr2().inputChars(sourceUserName);
		logInfo("Enter source user name < "+sourceUserName+" >");
		
		gWpasswordtext().click();
		gWpasswordtext().doubleClick();
		transendMigratorCProgramDataTr2().inputChars(sourcePassword);
		logInfo("Enter source password < "+sourcePassword+" >");
		
//		if(false){
//			useArchiveGWClient55OrGreaterc().click(atPoint(7,10));
//		}
	}
	
	private void sourceOutlookMSG(){
		outlookMSGDirectorytext().click();
		outlookMSGDirectorytext().click();
		outlookMSGDirectorytext().click(RIGHT);
		contextpopupMenu().click(atPath("Select All"));
		transendMigratorCProgramDataTr().inputChars(sourceOutlookMSGDirectory);
		logInfo("Input msg file directory < "+sourceOutlookMSGDirectory+" >");
	}
	
	private void sourceGmailEmail(){
		serverDomainComOrIPAddresstext().click();
		transendMigratorCProgramDataTr().inputKeys("^a{BKSP}imap.gmail.com;993");
		logInfo("input imap gmail address");
		
		sourceEmailtext().click();
		sourceEmailtext().click(RIGHT);
		contextpopupMenu().click(atPath("Select All"));
		transendMigratorCProgramDataTr().inputKeys("{BKSP}");
		sourceEmailtext().doubleClick(atPoint(1,1));
		transendMigratorCProgramDataTr().inputChars(sourceUserEmail);
		logInfo("Input source user email < "+sourceUserEmail+" > ");
				
		sourcePasswordtext().click();
		transendMigratorCProgramDataTr().inputChars(sourcePassword);
		logInfo("input password <"+sourcePassword+">");
	}
	
	
	private void sourceGmailOtherSection(){
	}
	
	
	public void startSingleMigration(){
		startSingleMigrationbutton().click();
		logInfo("Clicked startSingle Migration");
		
		// Transend Migrator
		while(yesbutton().exists()){
			logInfo("Clicking yes on prompt", transendMigratorwindow().getScreenSnapshot());
			yesbutton().click();
		}
		
		//Migration Completed Report
		migrationCompletedReportwindow().waitForExistence(1000, 3);
		logInfo("click ok on migration complete", migrationCompletedReportwindow().getScreenSnapshot());
		okMigrationCompletebutton().click(atPoint(24,9));
		
	}

	public void setData(){
		Object[] o = {};
		this.runMain(o);
	}
/*******************************Setters*********************************************/
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public void setSourceUserEmail(String sourceUserEmail) {
		this.sourceUserEmail = sourceUserEmail;
	}

	public void setSourcePassword(String sourcePassword) {
		this.sourcePassword = sourcePassword;
	}

	public void setSelectedFolders(String selectedFolders) {
		this.selectedFolders = selectedFolders;
	}

	public void setSourceOutlookMSGDirectory(String sourceOutlookMSGDirectory) {
		this.sourceOutlookMSGDirectory = sourceOutlookMSGDirectory;
	}

	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public void setTargetSharedDirectory(String targetSharedDirectory) {
		this.targetSharedDirectory = targetSharedDirectory;
	}

	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}

	public void setTargetUserCN(String targetUserCN) {
		this.targetUserCN = targetUserCN;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}

