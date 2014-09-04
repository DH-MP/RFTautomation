package Netmail_WebAdmin;
import java.util.Map;

import resources.Netmail_WebAdmin.indexWatcherHelper;
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
public class indexWatcher extends indexWatcherHelper
{
	/**
	 * Script Name   : <b>indexWatcher</b>
	 * Generated     : <b>Jun 6, 2014 3:37:48 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/06/06
	 * @author Administrator
	 */
	
	private String indexName = "indexinator";
	public void testMain(Object[] args) 
	{
		if(args != null && args.length > 0){
			Map<String, Object> data = (Map<String, Object>) args[0];
			indexName = (String) data.get("indexName");
		}
		
		link_nodes().click();
		sleep(10); //allow for the index to start before checking
		
		
		boolean jobFinished = false;
		while(!jobFinished){
			jobFinished = !foundJob();
			if(jobFinished){
				sleep(4);
				jobFinished = !foundJob();
			}
			sleep(10);
		}
		
		SpyMappedTestObject indexInstance = this.getMappedTestObject("html_indexInstance");
		indexInstance.setProperty(".text", indexName, 100);
		html_indexInstance().click();
		logInfo("click index");
		sleep(2);
		
		link_reportTab().click();
		
		TestObject[] rows = table_tableContent().find(atDescendant(".tag", "TR"), false);
		TestObject[] columns = rows[2].getChildren();
		vpManual("NoIndexingError", "0( 0.00% )", columns[6].getProperty(".contentText").toString().trim().replaceAll("\n|\t|\r", "")).performTest();
	}
	
	private boolean foundJob(){
		TestObject[] jobs = table_runningJob().find(atDescendant(".tag", "TR"), false);
		for(TestObject job : jobs){
			TestObject[] jobColumns = job.find(atChild(".tag", "TD"), false);
			if(jobColumns.length > 0 ){
				String jobTitle = (String) jobColumns[0].getProperty(".text");
				System.out.println(jobTitle);
				return jobTitle.contentEquals(indexName);
			}
		}
		return false;
	}
}

