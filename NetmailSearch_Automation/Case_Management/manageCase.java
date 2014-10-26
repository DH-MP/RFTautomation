package Case_Management;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import resources.Case_Management.manageCaseHelper;
import utilities.HelperClass;
import NetmailSearch_General.adminLogin;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.sys.graphical.Highlighter;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class manageCase extends manageCaseHelper
{
	/**
	 * Script Name   : <b>newCase</b>
	 * Generated     : <b>Jul 19, 2013 10:08:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/07/19
	 * @author Administrator
	 */
	private String 	name 		= "", //"autoCreateNewCase",
					description = "", //"\"Looking at other people during a relationship is okay. It's like looking at the menu but not ordering anything when you are on a diet\" - bad dating tips",
					status 		= "", //"Opened",
					caseClass 	= "", //"Claim",
					openDate 	= "", //"",
					closeDate 	= "", //"07/22/25",
					caseType 	= "", //"General Liability",
					caseSubType = "", //"Workplace",
					locations 	= "", //"ALS RIF",
					users		= "",
					filterWord 	= "",
					
					oldName 		= "",
					oldDescription 	= "", 
					oldStatus 		= "", 
					oldCaseClass 	= "", 
					oldOpenDate 	= "", 
					oldCloseDate 	= "", 
					oldCaseType 	= "", 
					oldCaseSubType 	= "",
					oldLocations 	= ""; 
	
	private Boolean checkLoadAllCase = true,
					oldCheckLoadAllCase = true,
					edit = false,
					cancelOperation = false,
					testMode = false;
	public void testMain(Object[] args) 
	{	
		if(args.length>0){
			name 		= (String) args[0];
			description = (String) args[1];
			status 		= (String) args[2];
			caseClass 	= (String) args[3];
			openDate 	= (String) args[4];
			closeDate 	= (String) args[5];
			caseType 	= (String) args[6];
			caseSubType = (String) args[7];
			checkLoadAllCase = (Boolean) args[8];
			
			locations 	= (String) args[9];
			filterWord 	= (String) args[10];
			cancelOperation = (Boolean) args[11];
			
			if(args.length>12){
				edit = true;
				oldName 		= (String) args[12];
				oldDescription 	= (String) args[13];
				oldStatus 		= (String) args[14];
				oldCaseClass 	= (String) args[15];
				oldCloseDate 	= (String) args[16];
				oldCaseType 	= (String) args[17];
				oldCaseSubType 	= (String) args[18];
				oldCheckLoadAllCase = (Boolean) args[19];
				oldLocations 	= (String) args[20];
			}
		}	
		
		/******CASE INFO TAB ************************/
		validateDefaultFields_CaseInfo();
		
		if(!edit){
			//Validate no name error message.
			button_createbutton().click();
			logInfo("clicked Create Button");
			sleep(1);
			waitForloading();
			if( vpManual("ErrorMsg_Unigue_CaseID_Displayed", true, html_errorMessage().ensureObjectIsVisible()).performTest()){
				button_errorMessage_OKbutton().click();
				logInfo("clicked OK button on error message");
			}
		}
		
		setCaseInfoTab();
		
		/******LOCATION AND USER TAB *******************/	
		link_locationAndUsers().click();
		sleep(10);
		
		if(!edit){
			//Validate no location selection error message.
			button_createbutton().click();
			logInfo("Clicked create button");
			if( vpManual("ErrorMsg_NoLocation_Message_Display", true, html_errorMessage().ensureObjectIsVisible()).performTest()){
				button_errorMessage_OKbutton().click();
				logInfo("Clicked error message OK button");
			}
			validateDefaultFields_Location();
			
			if(testMode){
				testLocationsCheckBox();
			}
		}
		

		if(!locations.isEmpty()){
			//Reset locations Check boxes
			TestObject firstHalf = bodyParts()[0];
			TestObject allLocationCheckBox = firstHalf.find(atDescendant(".tag", "DIV", "class", new RegularExpression(".*x-grid3-hd-checker", false)),false)[0];
			((GuiTestObject)allLocationCheckBox).click();
			sleep(0.5);
			((GuiTestObject)allLocationCheckBox).click();
			sleep(0.5);
			
			//Check Locations
			String[] locationNames = locations.split("\\s");
			for(String location : locationNames){
				checkLocations(location);
			}
		}
		
		if(!edit && testMode){
			testSelectAll_Users_DisplaySelected();
			testFilter(filterWord);
		}
		
		if(!cancelOperation){
			GuiTestObject button = edit? button_savebutton() : button_createbutton();
			button.click();
			String buttonName = edit? "save button" : "create button";
			logInfo("Clicked "+buttonName);
			sleep(4);
	
			manageCase.clickOkButtonReviewCase();
		}else{
			button_cancelbutton().click();
			logInfo("Clicked cancel button");
		}
		sleep(5);
		
		
	}
	
	public manageCase newCase(){
		if(html_caseListWindow().exists()){
			button_newCasebutton2().click();
			waitForloading();
		}else{
			logError("Case List Window not visible", browser_htmlBrowser().getScreenSnapshot());
			throw new ObjectNotFoundException("html_caseListWindow");
		}
		
		/******CASE INFO TAB ***********************/
		validateDefaultFields_CaseInfo();
		
		//Validate no name error message.
		button_createbutton().click();
		logInfo("clicked Create Button");
		sleep(1);
		waitForloading();
		if( vpManual("ErrorMsg_Unigue_CaseID_Displayed", true, html_errorMessage().ensureObjectIsVisible()).performTest()){
			button_errorMessage_OKbutton().click();
			logInfo("clicked OK button on error message");
		}
		
		setCaseInfoTab(); //Set data in fields
		
		
		/******LOCATION AND USER TAB **************/	
		link_locationAndUsers().click();
		sleep(10);
		waitForloading();

		//Validate no location selection error message.
		button_createbutton().click();
		logInfo("Clicked create button");
		if( vpManual("ErrorMsg_NoLocation_Message_Display", true, html_errorMessage().ensureObjectIsVisible()).performTest()){
			button_errorMessage_OKbutton().click();
			logInfo("Clicked error message OK button");
		}
		
		validateDefaultFields_Location();
		
		if(testMode){
			testLocationsCheckBox();
		}
		
		//Main
		setLocationAndUsersTab();
		
		if(!cancelOperation){
			button_createbutton().click();
			logInfo("Clicked create button ");
			sleep(4);
			
			manageCase.clickOkButtonReviewCase();
			openCase(name);
		}else{
			button_cancelbutton().click();
			logInfo("Clicked cancel button");
		}
		sleep(5);
		return this;
	}
	
	//Case must be opened
	public manageCase editCase(){
		waitForloading();
		button_editCasebutton().click();
		sleep(1);
		return edit();

	}
	
	public manageCase editCase(String caseName){
			openCase(caseName);
			waitForloading();
			button_editCasebutton().click();
			sleep(1);
			waitForloading();
			return edit();
	}
	
	//from casemanager
	public manageCase editCaseCM(String caseName){
		clickCase(caseName);
		waitForloading();
		button_editCaseCM().click();
		sleep(1);
		waitForloading();
		return edit();
}
	
	private manageCase edit(){
		/******CASE INFO TAB ***********************/
		
		setCaseInfoTab(); //Set data in fields
		
		/******LOCATION AND USER TAB *********************/	
		link_locationAndUsers().click();
		sleep(10);
		
		if(testMode){
			testLocationsCheckBox();
		}
		
		setLocationAndUsersTab();
		
		if(!cancelOperation){
			button_savebutton().click();
			logInfo("Clicked save button ");
			sleep(4);
		}else{
			button_cancelbutton().click();
			logInfo("Clicked cancel button");
		}
		sleep(5);
		return this;
	}
	
	public manageCase openCase(String caseName){
		clickCase(caseName);
		button_openCasebutton().click();
		return this;
	}
	
	private manageCase clickCase(String caseName){
		html_caseListDIV().waitForExistence(1000, DISABLED);
		sleep(0.5);
		TestObject[] searchCase = html_caseListDIV().find(atDescendant(".class", "Html.DIV", ".text", caseName), false);
		((GuiTestObject)searchCase[0]).click();
		return this;
	}
	
	
	public static void deleteCase(String caseName){
		manageCase mc = new manageCase();
		mc.setTestMode(false);
		mc.setStatus("Closed");
		mc.delete(caseName);
	}
	private manageCase delete(String caseName){
		String info = "As super user, closing the case: %s ";
		logInfo(String.format(info, caseName));
		editCaseCM(caseName);
		waitForloading();
		clickOkButtonReviewCase();
		
		info = "As super user, DELETING the case: %s ";
		logInfo(String.format(info, caseName));
		html_caseListDIV().waitForExistence(1000, DISABLED);
		sleep(0.5);
		TestObject[] searchCase = html_caseListDIV().find(atDescendant(".class", "Html.DIV", ".text", caseName), false);
		((GuiTestObject)searchCase[0]).click();
		button_deleteCasebutton().click();
		button_deleteCaseYesbutton().click();
		clickOkButtonReviewCase();
		return this;
	}
	
	//When case already open, saving the STATE of the case
	public manageCase saveCurrentCase(){
		button_saveCasebutton().click();
		waitForloading();
		return this;
	}
	
	public void validateDefaultFields_CaseInfo(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		java.util.Date today = new java.util.Date();
		String todayString = dateFormat.format(today);
		Boolean[] defaultFields = null;
		if(edit){
			logInfo("Validating if edit case window is prepopulated with correct values");
			defaultFields	= new Boolean[]{text_id().getProperty(".value").toString().contentEquals(oldName),
											text_desc().getProperty(".value").toString().contentEquals(oldDescription),
											text_status().getProperty(".value").toString().contentEquals(oldStatus),
											text_class().getProperty(".value").toString().contentEquals(oldCaseClass),
											text_type().getProperty(".value").toString().contentEquals(oldCaseType),
											text_subType().getProperty(".value").toString().contentEquals(oldCaseSubType),
											text_closeDate().getProperty(".value").toString().contentEquals(oldCloseDate),
											Boolean.parseBoolean(checkBox_loadAllCaseData().getProperty(".checked").toString()) == oldCheckLoadAllCase.booleanValue(),
										};
			vpManual("EditCase_prepopulated_with_correct_value", false, Arrays.asList(defaultFields).contains(false)).performTest();		
		}else{
			logInfo("Validating if new case has correct default input value");
			defaultFields	= new Boolean[]{text_id().getProperty(".value").toString().isEmpty(),
											text_desc().getProperty(".value").toString().isEmpty(),
											text_status().getProperty(".value").toString().contentEquals("New"),
											text_class().getProperty(".value").toString().isEmpty(),
											text_openDate().getProperty(".value").toString().contentEquals(todayString),
											text_type().getProperty(".value").toString().isEmpty(),
											text_subType().getProperty(".value").toString().isEmpty(),
											text_closeDate().getProperty(".value").toString().isEmpty(),
											Boolean.parseBoolean(checkBox_loadAllCaseData().getProperty(".checked").toString())
										};
			vpManual("Default_field_value_are_valid_for_caseInfo", false, Arrays.asList(defaultFields).contains(false)).performTest();		
		}
		
	}
	
	private void validateDefaultFields_Location(){
		logInfo("Validating Location&User tab default value");
		TestObject firstHalf = bodyParts()[0];
		TestObject[] locations = firstHalf.find(atDescendant(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected$", false)),false);
		vpManual("No_Location_checked", 0 , locations.length).performTest();
		vpManual("Default_SelectAllUser_Checked", true, Boolean.parseBoolean(checkBox_allMenuon().getProperty(".checked").toString())).performTest();
	}
	

	
	private void checkLocations(String locationName){
		TestObject firstHalf = bodyParts()[0];
		TestObject location = firstHalf.find(atDescendant(".text", locationName, "class", new RegularExpression(".*x-grid3-row.*", false)),false)[0];
		GuiTestObject checkbox = (GuiTestObject)location.find(atDescendant(".tag", "DIV", "class", "x-grid3-row-checker"), false)[0];
		checkbox.hover();
		checkbox.click();
		logInfo("checked location < "+ locationName +" >");
	}
	
	private void testLocationsCheckBox(){
		TestObject firstHalf = bodyParts()[0];
		TestObject allLocationCheckBox = firstHalf.find(atDescendant(".tag", "DIV", "class", new RegularExpression(".*x-grid3-hd-checker", false)),false)[0];
		TestObject[] checkedLocations = null;
		
		((GuiTestObject)allLocationCheckBox).click();
		logInfo("Clicked all location header checkbox");
		sleep(1);
		TestObject[] locations = firstHalf.find(atDescendant(".tag","DIV", "class", "x-grid3-body"),false)[0].getChildren();
		checkedLocations= firstHalf.find(atDescendant(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected", false)),false);
		vpManual("Expected_all_locations_to_be_checked", locations.length, checkedLocations.length).performTest();
		
		((GuiTestObject)allLocationCheckBox).click();
		logInfo("Clicked all location header checkbox");
		sleep(1);
		checkedLocations = firstHalf.find(atDescendant(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected", false)),false);
		vpManual("Expected_0_locations_to_be_checked", 0, checkedLocations.length).performTest();
	}
	
	private void testSelectAll_Users_DisplaySelected(){
		TestObject[] checkedUsers = null;
		TestObject secondHalf = bodyParts()[1];
		TestObject userBody = secondHalf.find(atDescendant(".tag","DIV", "class", "x-grid3-body"),false)[0];
		
		//Default Value
		TestObject[] totalUsers = userBody.getChildren();
		checkedUsers = userBody.find(atChild(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected", false)),false);
		vpManual("ByDefault_AllUserChecked", totalUsers.length, checkedUsers.length).performTest();
		
		//Uncheck Select All users
		checkBox_allMenuon().click();
		logInfo("Uncheck select all user checkbox");
		sleep(0.5);
		checkedUsers = userBody.find(atChild(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected", false)),false);
		vpManual("NoUsersChecked", 0, checkedUsers.length).performTest();
		
		//Check users checkbox
		TestObject users = secondHalf.find(atDescendant(".tag", "DIV", "class", new RegularExpression(".*x-grid3-hd-checker", false)),false)[0];
		((GuiTestObject)users).click();
		logInfo("Check users checkbox");
		sleep(0.5);
		checkedUsers = userBody.find(atChild(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected", false)),false);
		vpManual("allUsersChecked", totalUsers.length, checkedUsers.length).performTest();
		
		//Uncheck users checkbox
		((GuiTestObject)users).click();
		logInfo("Uncheck users checkbox");
		sleep(0.5);
		checkedUsers = userBody.find(atChild(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected", false)),false);
		vpManual("NoUsersChecked", 0, checkedUsers.length).performTest();
		
		//Select all but last user
		logInfo("Selecting all but the last user");
		for(int i = 0; i < totalUsers.length-1; i++){
			((GuiTestObject)totalUsers[i]).click();
		}
		
		//check display all selected
		checkBox_displaySelected().click();
		logInfo("Checked display selected checkbox");
		sleep(2);
		checkedUsers = userBody.find(atChild(".tag", "DIV", "class", new RegularExpression(".*x-grid3-row.*x-grid3-row-selected", false)),false);
		vpManual("onlySelectedUserIsDisplayed", totalUsers.length-1, checkedUsers.length).performTest();
		
		//Uncheck display all selected
		checkBox_displaySelected().click();
		logInfo("Uncheck display selected checkbox");
		sleep(2);
		int pastTotal = totalUsers.length;
		totalUsers = userBody.getChildren();
		vpManual("AllUserDisplayed", pastTotal, totalUsers.length).performTest();
	}
	
	
	private boolean testFilter(String word){
		//Click filter input
		text_filter().click();
		logInfo("Clicked filter input field");
		
		//Enter Value
		browser_htmlBrowser().inputKeys(word+"{ENTER}");
		logInfo("Entered < "+ word +" >");
		sleep(2);
		
		//Verify results
		TestObject secondHalf = bodyParts()[1];
		TestObject userBody = secondHalf.find(atDescendant(".tag","DIV", "class", "x-grid3-body"),false)[0];
		TestObject[] users = userBody.getChildren();
		for(TestObject user: users){
			if( !user.getProperty(".text").toString().toLowerCase().trim().contains(word.toLowerCase().trim())){
				logError("user email does not contain the word< "+word+" >");
				browser_htmlBrowser().inputKeys("^a{DELETE}{ENTER}");
				return false;//If it gets to this point filter test failed
			}
		}
		logTestResult("Filter Test Passed", true);
		browser_htmlBrowser().inputKeys("^a{DELETE}{ENTER}");
		return true;//If it gets to this point filter test pass
	}
	
	private TestObject getActiveCaseBody(){
		TestObject parentBody = html_caseDetailWindow().find(atDescendant(".tag", "DIV", "class", "x-tab-panel-bwrap"), true)[0].find(atChild(".tag","DIV"), true)[0];
		return parentBody.find(atChild(".tag", "DIV", "class", new RegularExpression("\\s*x-panel x-panel-noborder$", false)), true)[0];
	}
	
	private TestObject[] bodyParts(){
		TestObject activeBody = getActiveCaseBody();
		TestObject[] bodyParts = activeBody.find(atDescendant(".tag", "DIV", "class", new RegularExpression("\\s*x-panel x-border-panel$", false)));
		return bodyParts;
	}
	
	
	private void setCaseInfoTab(){
		if(!name.isEmpty()){
			text_id().click();
			logInfo("Clicked name input field");
			browser_htmlBrowser().inputKeys("^a{DELETE}"+name);
			logInfo("Entered < "+name+" >");
		}
		if(!description.isEmpty()){
			text_desc().click();
			logInfo("Clicked description input field");
			browser_htmlBrowser().inputKeys("^a{DELETE}"+description);
			logInfo("entered < "+description+" >");
		}
		
		if(!status.isEmpty()){
			text_status().click();
			logInfo("Clicked status dropdown");
			TestObject value = html_visibleDropDownList().find(atDescendant(".text", status, "class", new RegularExpression("^x-combo-list-item.*", false)),false)[0];
			((GuiTestObject) value).click();
			logInfo("Selected < "+status+" >");
		}
		
		if(!caseClass.isEmpty()){
			text_class().click();
			logInfo("Clicked case class dropdown");
			TestObject value = html_visibleDropDownList().find(atDescendant(".text", caseClass, "class", new RegularExpression("^x-combo-list-item.*", false)),false)[0];
			((GuiTestObject) value).click();
			logInfo("Selected < "+caseClass+" >");

		}
		if(!openDate.isEmpty()){
			text_openDate().click();
			logInfo("Clicked open date input field");
			browser_htmlBrowser().inputKeys("^a{DELETE}"+openDate);
			logInfo("Entered < "+openDate+" >");
		}
		
		if(!caseType.isEmpty()){
			text_type().click();
			logInfo("Clicked case type dropdown");
			TestObject value = html_visibleDropDownList().find(atDescendant(".text", caseType, "class", new RegularExpression("^x-combo-list-item.*", false)),false)[0];
			((GuiTestObject) value).click();
			logInfo("Selected < "+caseType+" >");
		}
		
		if(!caseSubType.isEmpty()){
			text_subType().click();
			logInfo("Clicked sub type dropdown");
			TestObject value = html_visibleDropDownList().find(atDescendant(".text", caseSubType, "class", new RegularExpression("^x-combo-list-item.*", false)),false)[0];
			((GuiTestObject) value).click();
			logInfo("Selected < "+caseSubType+" >");
		}

		if(!closeDate.isEmpty()){
			text_closeDate().click();
			logInfo("Clicked close date input field");
			browser_htmlBrowser().inputKeys("^a{DELETE}"+closeDate);
			logInfo("entered < "+closeDate+" >");
		}
		
		if(checkLoadAllCase !=null){
			boolean checked = Boolean.parseBoolean(checkBox_loadAllCaseData().getProperty(".checked").toString());
			if( checkLoadAllCase && !checked){
				checkBox_loadAllCaseData().click();
				logInfo("Checked load all case");
			}else if (!checkLoadAllCase && checked){
				checkBox_loadAllCaseData().click();
				logInfo("Unchecked load all case");
			}
		}
	}
	
	private void setLocationAndUsersTab(){
		if(!locations.isEmpty()){
			//Reset locations Check boxes
			TestObject firstHalf = bodyParts()[0];
			TestObject allLocationCheckBox = firstHalf.find(atDescendant(".tag", "DIV", "class", new RegularExpression(".*x-grid3-hd-checker", false)),false)[0];
			((GuiTestObject)allLocationCheckBox).click();
			sleep(0.5);
			waitForloading();
			((GuiTestObject)allLocationCheckBox).click();
			sleep(0.5);
			waitForloading();
			
			//Check Locations
			String[] locationNames = locations.split(";");
			for(String location : locationNames){
				checkLocations(location);
				waitForloading();
			}
		}
		
		if(testMode){
			testSelectAll_Users_DisplaySelected();
			testFilter(filterWord);
		}

		if(!users.isEmpty()){
			//Uncheck Select All users
			if(checkBox_allMenuon().getProperty(".checked").toString().contentEquals("true")){
				checkBox_allMenuon().click();
			}
			logInfo("Uncheck select all user checkbox");
			sleep(1);
			waitForloading();
			
			String[] userNames = users.split(";");
			TestObject secondHalf = bodyParts()[1];
			TestObject userBody = secondHalf.find(atDescendant(".tag","DIV", "class", "x-grid3-body"),false)[0];
			for(String name : userNames){
				TestObject[] user = userBody.find(atDescendant(".text", name.trim(), "class", "x-grid3-row"));
				user = user.length==0 ? userBody.find(atDescendant(".text", name.trim())) : user;
				if(user.length == 1){
					((GuiTestObject) user[0]).click();
				}else{
					logError("cannot find user in location: "+name);
					return;
				}
			}
			
		}
		
	}
	
	
	
	
	public static void clickOkButtonReviewCase(){
		new manageCase().okButtonReviewCase().click();
	}
	
	private GuiTestObject okButtonReviewCase(){
		if(html_requireReviewCaseTXT().exists()){
			if(Integer.parseInt(html_requireReviewCaseTXT().getProperty(".screenTop").toString())>0){
				TestObject parent = html_requireReviewCaseTXT().getParent();
				while(!parent.getProperty(".class").toString().contentEquals("Html.BODY")){
					parent = parent.getParent();
				}
				TestObject okButton = parent.find(atDescendant(".class", "Html.BUTTON", ".text", "OK"), false)[0];
				return ((GuiTestObject)okButton);
			}
		}
		return null;
	}

	
	/***************************************************SETTERS*******************************************/
	public manageCase setName(String name) {
		this.name = name;
		return this;
	}


	public manageCase setDescription(String description) {
		this.description = description;
		return this;
	}


	public manageCase setStatus(String status) {
		this.status = status;
		return this;
	}


	public manageCase setCaseClass(String caseClass) {
		this.caseClass = caseClass;
		return this;
	}


	public manageCase setOpenDate(String openDate) {
		this.openDate = openDate;
		return this;
	}


	public manageCase setCloseDate(String closeDate) {
		this.closeDate = closeDate;
		return this;
	}


	public manageCase setCaseType(String caseType) {
		this.caseType = caseType;
		return this;
	}


	public manageCase setCaseSubType(String caseSubType) {
		this.caseSubType = caseSubType;
		return this;
	}


	public manageCase setLocations(String locations) {
		this.locations = locations;
		return this;
	}


	public manageCase setFilterWord(String filterWord) {
		this.filterWord = filterWord;
		return this;
	}


	public manageCase setOldName(String oldName) {
		this.oldName = oldName;
		return this;
	}


	public manageCase setOldDescription(String oldDescription) {
		this.oldDescription = oldDescription;
		return this;
	}


	public manageCase setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
		return this;
	}


	public manageCase setOldCaseClass(String oldCaseClass) {
		this.oldCaseClass = oldCaseClass;
		return this;
	}


	public manageCase setOldOpenDate(String oldOpenDate) {
		this.oldOpenDate = oldOpenDate;
		return this;
	}


	public manageCase setOldCloseDate(String oldCloseDate) {
		this.oldCloseDate = oldCloseDate;
		return this;
	}


	public manageCase setOldCaseType(String oldCaseType) {
		this.oldCaseType = oldCaseType;
		return this;
	}


	public manageCase setOldCaseSubType(String oldCaseSubType) {
		this.oldCaseSubType = oldCaseSubType;
		return this;
	}


	public manageCase setOldLocations(String oldLocations) {
		this.oldLocations = oldLocations;
		return this;
	}


	public manageCase setCheckLoadAllCase(Boolean checkLoadAllCase) {
		this.checkLoadAllCase = checkLoadAllCase;
		return this;
	}


	public manageCase setOldCheckLoadAllCase(Boolean oldCheckLoadAllCase) {
		this.oldCheckLoadAllCase = oldCheckLoadAllCase;
		return this;
	}


	public manageCase setEdit(Boolean edit) {
		this.edit = edit;
		return this;
	}


	public manageCase setCancelOperation(Boolean cancelOperation) {
		this.cancelOperation = cancelOperation;
		return this;
	}


	public manageCase setTestMode(Boolean testMode) {
		this.testMode = testMode;
		return this;
	}

	public void setUsers(String users) {
		this.users = users;
	}
}

