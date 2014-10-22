package TC_1333_FilterAndFolderStats;
import java.util.regex.Matcher;

import resources.TC_1333_FilterAndFolderStats.TS_1454_FilterAndFolderHelper;
import NetmailSearch_General.NetmailLogin;
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
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class TS_1454_FilterAndFolder extends TS_1454_FilterAndFolderHelper
{
	/**
	 * Script Name   : <b>TS_1454_FilterAndFolder</b>
	 * Generated     : <b>Oct 22, 2013 10:33:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/22
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		
		//Login
		NetmailLogin.login();
		//AdminLogin
		Object[] al = {dpString("caseName"), dpString("userType"), true};
		adminLogin.selectUserType( dpString("userType"));
		adminLogin.selectCase(dpString("caseName"));
		
		button_preferencesbutton().click();
		logInfo("clicked preference");
		sleep(0.5);
		link_pageSize().hover();
		logInfo("clicked hover page size");
		sleep(0.5);
		link_pageSize_200().click();
		logInfo("clicked clicked page size 200");
		sleep(2);
		waitForloading();
		
		TestObject[] location = findLocationDropDown(dpString("folderName"));
		expandFolder((GuiTestObject)location[0]);
		logInfo("expanded folder/location < "+dpString("folderName")+" >");
		sleep(2);
		Property p1 = new Property(".class", "Html.UL");
		TestObject[] locationSubTree = findAtChild(location[0],new Property[]{p1}, false);
		TestObject[] user = findUser(locationSubTree[0], dpString("userNameInFolder"));
		((GuiTestObject)user[0]).doubleClick();
		logInfo("Double clicked user < "+ dpString("userNameInFolder")+" >" );
		sleep(2);
		waitForloading();
		
		if(html_filterTree0Xcollapsed().exists()){
			html_filterTree0Xcollapsed().click();
			logInfo("clicked expand filter");	
		}
		
		logInfo("Verify filters");
		validatePercentageForAllFilters("opened,read");
	}
	
	
	public void validatePercentageForAllFilters(String filterList){
		String[] filterGroup = filterList.split(",");
		for(String topFilter : filterGroup){
			topFilter = topFilter.trim();
			int totalResults = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true).length;
			TestObject[] languageFilter = findFilterDropDown(topFilter);
			
			if(languageFilter.length>0){
				expand((GuiTestObject)languageFilter[0]);
				TestObject[] filters = findSubFilters(languageFilter[0]);
				for(int i = 0; i<filters.length; i++){
					
					//GetPercentage
					java.util.regex.Pattern percentageRegexp = java.util.regex.Pattern.compile("\\((.*)%\\)");
					String filterText = filters[i].getProperty(".text").toString();
					Matcher m = percentageRegexp.matcher(filterText);
					float percentage = 0;
					while(m.find()){
						percentage = Float.parseFloat(m.group(1)); 
					}
					
					//Check filter
					String filterName = filterText.substring(0, filterText.indexOf("("));
					findCheckBox(filters[i]).click();
					sleep(2);
					waitForloading();
					
					//Verify percentage
					float expectedMessageCount = totalResults*percentage/100;
					long  expectedMessageCount_long = Math.round(expectedMessageCount);
					int expectedMessageCount_int = (int)expectedMessageCount_long;
					TestObject[] results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
					int filterResults = results.length;
					vpManual("CorrectPercentageFor_"+filterName, expectedMessageCount_int, filterResults ).performTest();
					

					switch (topFilter) {
					case "opened": case "read":
						GuiTestObject propertyRow = topFilter.contentEquals("opened") ? link_msgPropertyOpened():link_msgPropertyRead();
						String expectedOpenedValue = filterName.toLowerCase().trim().contentEquals("yes")? "true" : "false";
						text_quickSearchField0().click();
						browser_htmlBrowser().inputKeys("for your eyes only -----> confidential{ENTER}"); //Shorten the verification with a quick search using a known term that has both yes and no results
						waitForloading();
						results = find(atDescendant(".class", "Html.TABLE", "class", "x-grid3-row-table"), true);
						for(TestObject result : results){
							
							((GuiTestObject)result).doubleClick();
							sleep(1);
							waitForloading();
							
							link_msgPropertyTab().click();
							sleep(1);
							waitForloading();
							String openValue = propertyRow.getParent().getParent().getChildren()[1].getProperty(".contentText").toString();
							vpManual("Filtered_"+topFilter+"_"+filterName, expectedOpenedValue, openValue).performTest();
							
							html_msgCloseButton().click();
							sleep(1);
						}
						text_quickSearchField0().click();
						browser_htmlBrowser().inputKeys("^a{DELETE}{ENTER}");
						waitForloading();
						break;
					default:
					}
					
					//Refind after load
					languageFilter = findFilterDropDown(topFilter);
					filters = findSubFilters(languageFilter[0]);
					
					//Uncheck filter
					findCheckBox(filters[i]).click();
					waitForloading();
	
					//Refind after load
					languageFilter = findFilterDropDown(topFilter);
					filters = findSubFilters(languageFilter[0]);
				}
			}
		}
	}
	
	
	public TestObject[] findFilterDropDown(String name){
		TestObject filterTree = browser_htmlBrowser().find(atDescendant(".tag", "DIV", ".id", "filterTree0"), true)[0];
		TestObject filterUL = filterTree.find(atDescendant(".tag", "UL"), false)[0];
		Property p1 = new Property(".class", "Html.LI");
		Property p2 = new Property(".text", new RegularExpression(name+".*", false));
		return findAtChild(filterUL.getChildren()[0], new Property[]{p1, p2}, false);
	}
	
	public TestObject[] findLocationDropDown(String name){
		Property p1 = new Property(".class", "Html.LI");
		Property p2 = new Property(".text", new RegularExpression(name+".*", false));
		return findAtChild(html_folderUL().getChildren()[0], new Property[]{p1, p2}, false);
	}
	
	public void expand(GuiTestObject object){
		Property p1 = new Property(".class", "Html.DIV");
		Property p2 = new Property("class", new RegularExpression(".*x-tree-node-expanded", false));
		TestObject[] expandedDiv = findAtChild(object, new Property[]{p1, p2}, false);
		if(expandedDiv.length == 0){
			object.doubleClick();
		}
	}
	
	public void expandFolder(GuiTestObject object){
		Property p1 = new Property(".class", "Html.DIV");
		Property p2 = new Property("class", new RegularExpression(".*x-tree-node-expanded", false));
		TestObject[] expandedDiv = findAtChild(object, new Property[]{p1, p2}, false);
		if(expandedDiv.length == 0){
			//Find collapse div
			p1 = new Property(".class", "Html.DIV");
			expandedDiv = findAtChild(object, new Property[]{p1}, false);
			
			//Click on arrow
			p1 = new Property(".class", "Html.IMG");
			TestObject[] arrow = findAtChild(expandedDiv[0], new Property[]{p1}, true);
			((GuiTestObject)arrow[0]).click();
		}
	}
	
	public GuiTestObject findCheckBox(TestObject object){
		Property p1 = new Property(".class", "Html.INPUT.checkbox");
		return (GuiTestObject) findAtDescendant(object, new Property[]{p1}, true)[0];
	}
	
	public TestObject[] findSubFilters(TestObject object){
		Property p1 = new Property(".class", "Html.LI");
		Property p2 = new Property("class", "x-tree-node");
		return findAtDescendant(object, new Property[]{p1,p2}, false);
	}
	
	public TestObject[] findUser(TestObject object, String name){
		Property p1 = new Property(".class", "Html.LI");
		Property p2 = new Property("class", "x-tree-node");
		Property p3 = new Property(".text", new RegularExpression(".*"+name+".*", false));
		return findAtDescendant(object, new Property[]{p1, p2, p3}, false);
	}
}

