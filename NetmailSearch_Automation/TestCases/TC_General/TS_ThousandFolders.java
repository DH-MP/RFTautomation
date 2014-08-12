package TestCases.TC_General;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.io.FileUtils;

import resources.TestCases.TC_General.TS_ThousandFoldersHelper;
import utilities.HelperClass;
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
public class TS_ThousandFolders extends TS_ThousandFoldersHelper
{
	/**
	 * Script Name   : <b>TS_ThousandFolders</b>
	 * Generated     : <b>Aug 8, 2014 3:41:04 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/08
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		int numberOfFolders = 1000; //10,100,1000,10000
		setupFolders(numberOfFolders, false);
		
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("GVautomation");
		waitForloading();
		
		TestObject foldersUL = HelperClass.navigateLocation("GVautomation>rft");
		waitForloading();
		
		TestObject rftLI = foldersUL.getParent();
		TestObject[] folderPagination = rftLI.find(atDescendant(".tag", "TR", "class", "x-toolbar-right-row"), false);	
		
		
		TestObject firstPage = folderPagination[0].getChildren()[0];
		TestObject previousPage = folderPagination[0].getChildren()[1];
		TestObject currentPage = folderPagination[0].getChildren()[2];
		Integer totalNumberOfPage = Integer.parseInt(folderPagination[0].getChildren()[3].getProperty(".contentText").toString().replace("/", ""));	
		TestObject nextPage = folderPagination[0].getChildren()[4];
		TestObject lastPage = folderPagination[0].getChildren()[5];
	
		//Find last folder
		((GuiTestObject)lastPage).hover();
		((GuiTestObject)lastPage).click();
		waitForloading();
		HelperClass.navigateLocation("GVautomation>rft>"+(numberOfFolders-1));
		waitForloading();
		
		//Using previous button
		int page = totalNumberOfPage;
		while(page>1){
			page--;
			((GuiTestObject)previousPage).hover();
			((GuiTestObject)previousPage).click();
			sleep(2);
			waitForloading();
			int folderNum = ((200)*(page))-1;
			logInfo("looking for folder number "+folderNum+" in folder page "+page);
			HelperClass.navigateLocation("GVautomation>rft>"+folderNum);
			waitForloading();
		}
		
		
		//Find last folder
		((GuiTestObject)lastPage).hover();
		((GuiTestObject)lastPage).click();
		waitForloading();
		HelperClass.navigateLocation("GVautomation>rft>"+(numberOfFolders-1));
		waitForloading();
		 
		//Using input page number
		page = totalNumberOfPage;
		while(page>1){
			page--;
			((GuiTestObject)currentPage).hover();
			((GuiTestObject)currentPage).click();
			((GuiTestObject)currentPage).doubleClick();
			browser_htmlBrowser().inputKeys(""+page+"{ENTER}");
			sleep(2);
			waitForloading();
			int folderNum = ((200)*(page))-1;
			logInfo("looking for folder number "+folderNum+" in folder page "+page);
			HelperClass.navigateLocation("GVautomation>rft>"+folderNum);
			waitForloading();
		}
		
		//Using next button
		page = 1;
		while(page<5){
			page++;
			((GuiTestObject)nextPage).hover();
			((GuiTestObject)nextPage).click();
			sleep(2);
			waitForloading();
			int folderNum = ((200)*(page))-1;
			logInfo("looking for folder number "+folderNum+" in folder page "+page);
			HelperClass.navigateLocation("GVautomation>rft>"+folderNum);
			waitForloading();
		}
		
		tearDown();
		sleep(10);
		
		int nestedFolders = 10;
		setupFolders(nestedFolders, true);
		NetmailLogin.login();
		adminLogin.selectUserType("Super User");
		adminLogin.selectCase("GVautomation");
		waitForloading();
		
		html_splitBarAdjust().hover();
		html_splitBarAdjust().dragToScreenPoint(text_quickCaseComboBox().getScreenPoint());
		
		String path = "GVautomation>rft";
		for(int i = 1; i < nestedFolders; i++){
			path += ">"+i+"$";
		}
		HelperClass.navigateLocation(path);
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(2);
		
		waitForloading();
		sleep(100);
	}
	
	@Override
	public void onTerminate(){
		System.out.println("asdjaoidjao");
		tearDown();
	}
	
	private void tearDown(){
		String userFolderName = "rft@rft.BASE2012.First Organization";
		String userFolder = "\\\\"+IP+"\\GVautomatoin\\audit\\accounts\\"+userFolderName;
		try {		
				File stat_info = new File(userFolder+"\\stat_info.xml");	
				File backupStat = new File(userFolder+"\\BACKUPSTAT.xml");		
				if(backupStat.exists()){
					if(stat_info.exists()){
						stat_info.delete();
					}
					
					backupStat.renameTo(stat_info);
				}
	
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private void setupFolders(int numberOfFolders, boolean nested){
		String userFolderName = "rft@rft.BASE2012.First Organization";
		String userFolder = "\\\\"+IP+"\\GVautomatoin\\audit\\accounts\\"+userFolderName;
		try {		
				File stat_info = new File(userFolder+"\\stat_info.xml");	
				File backupStat = new File(userFolder+"\\BACKUPSTAT.xml");		
				if(backupStat.exists()){
					backupStat.delete();
				}
				
				stat_info.renameTo(backupStat);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
	    try {
			 // create an XMLOutputFactory
		    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		    // create XMLEventWriter
		    XMLEventWriter eventWriter = outputFactory
		        .createXMLEventWriter(new FileOutputStream(userFolder+"\\stat_info.xml"));
		    // create an EventFactory
		    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		    XMLEvent newLine = eventFactory.createCharacters("\n");
		    XMLEvent tab = eventFactory.createCharacters("\t");
		    
		    // create and write Start Tag
		    StartDocument startDocument = eventFactory.createStartDocument();
		    eventWriter.add(startDocument);
			    
		    
		    //CONTENT
		    eventWriter.add(newLine);
		    eventWriter.add(eventFactory.createStartElement("", "", "ACCOUNT")); 
		    eventWriter.add(newLine);

		    	eventWriter.add(tab);
		    	eventWriter.add(eventFactory.createStartElement("", "", "FOLDERS")); 
			    eventWriter.add(newLine);

				    eventWriter.add(tab);
				    eventWriter.add(tab);
			    	ArrayList<Attribute> a = new ArrayList<Attribute>();
			    	a.add(eventFactory.createAttribute("NAME", "ROOT"));
			    	a.add(eventFactory.createAttribute("ITEMCOUNT", "0"));
			    	a.add(eventFactory.createAttribute("TOTALCOUNT", "47"));
			    	eventWriter.add(eventFactory.createStartElement("", "", "FOLDER", a.iterator(), null)); 
				    eventWriter.add(newLine);


					if(nested){
					    createNestedFolders(eventWriter, 1, numberOfFolders);
					}else{
				    	for(int i = 1; i < numberOfFolders; i++){
						    eventWriter.add(tab);
						    eventWriter.add(tab);
						    eventWriter.add(tab);
					    	a = new ArrayList<Attribute>();
					    	a.add(eventFactory.createAttribute("NAME", ""+i));
					    	a.add(eventFactory.createAttribute("ITEMCOUNT", "0"));
					    	a.add(eventFactory.createAttribute("TOTALCOUNT", "0"));
					    	eventWriter.add(eventFactory.createStartElement("", "", "FOLDER", a.iterator(), null)); 
						    eventWriter.add(eventFactory.createEndElement("", "", "FOLDER"));
						    eventWriter.add(newLine);
				    	}
					}
  
					eventWriter.add(tab);	
				    eventWriter.add(tab);
				    eventWriter.add(eventFactory.createEndElement("", "", "FOLDER"));
				    eventWriter.add(newLine);
			    
				eventWriter.add(tab);
			    eventWriter.add(eventFactory.createEndElement("", "", "FOLDERS"));
			    eventWriter.add(newLine);
			    
		    eventWriter.add(eventFactory.createEndElement("", "", "ACCOUNT"));
		    eventWriter.add(newLine);
		    
		    
		    
		    
		    
		    eventWriter.add(eventFactory.createEndDocument());
			eventWriter.close();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	
	
	private void createNestedFolders(XMLEventWriter eventWriter, int folderName, int max){
		if(folderName >=max){
			return;
		}
		try{
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		    XMLEvent newLine = eventFactory.createCharacters("\n");
		    XMLEvent tab = eventFactory.createCharacters("\t");
			ArrayList<Attribute> a = new ArrayList<Attribute>();
		   
			eventWriter.add(tab);
		    eventWriter.add(tab);
		    eventWriter.add(tab);
	    	a = new ArrayList<Attribute>();
	    	a.add(eventFactory.createAttribute("NAME", ""+folderName));
	    	a.add(eventFactory.createAttribute("ITEMCOUNT", "0"));
	    	a.add(eventFactory.createAttribute("TOTALCOUNT", "0"));
		    eventWriter.add(newLine);
	    	
	    	eventWriter.add(eventFactory.createStartElement("", "", "FOLDER", a.iterator(), null)); 
	    	
	    	//Recursive
	    	createNestedFolders(eventWriter, ++folderName, max);
	    	
	    	
		    eventWriter.add(eventFactory.createEndElement("", "", "FOLDER"));
		    eventWriter.add(newLine);
		    
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

