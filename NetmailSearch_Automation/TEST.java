
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import resources.TESTHelper;
import utilities.NetmailFinder;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
import com.rational.test.ft.*;
import com.rational.test.ft.domain.java.eclipse.Finder;
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
		
		
		NetmailFinder a = new NetmailFinder();
		TestObject[] x = a.findAllTextObjects2(html_editConf());
		a.highlight(x);
		sleep(100);
		
		
		
		// HTML Browser
		// Document: RFT - Outlook Web App: 
//		int a = browser_htmlBrowser().find(atDescendant(".tag", "SPAN", "id", "_ariaId_40.folder"), false).length;
//		System.out.println(a);
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
//				for(int i = 1; i<100000; i++){
//					FileWriter fstream2 = new FileWriter("C:\\contactInfo\\user"+i+"@fakefake.com.xml");
//					BufferedWriter bw = new BufferedWriter(fstream2);
//					String xml = dpString("a");
//					bw.write(xml.replaceAll("mike", "user"+i));
//					bw.close();
//				}
//
//		} catch (Exception e) {
//			System.err.println("Error: " + e.getMessage());
//		}
//		
//		try {
//			
//			for(int i = 1; i<100000; i++){
//				FileWriter fstream2 = new FileWriter("\\\\10.1.30.64\\GVautomatoin\\audit\\accounts\\rft@rft.BASE2012.First Organization\\pabStub\\Contacts\\user"+i+"@fakefake.com.stb");
//				BufferedWriter bw = new BufferedWriter(fstream2);
//				String xml = dpString("b");
//				bw.write(xml.replaceAll("mikeNUMNUM", "user"+i));
//				bw.close();
//				System.out.println("wrote "+i);
//			}
//
//		} catch (Exception e) {
//			System.err.println("Error: " + e.getMessage());
//		}
//	
//		
		
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
	
	private class MyArrayList<T> extends ArrayList<T> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(T o){		
			if(	!((TestObject)o).getProperty(".offsetHeight").toString().contentEquals("0") |
					!((TestObject)o).getProperty(".offsetWidth").toString().contentEquals("0")  |
					!((TestObject)o).getProperty(".offsetTop").toString().contentEquals("0")){
				

					
					boolean foundDup = false;
					for(T addedO : this){
						TestObject addedTestO = (TestObject) addedO;
						String tag1 = ((TestObject)o).getProperty(".tag").toString().trim();
						String tag2 = addedTestO.getProperty(".tag").toString().trim();
						
						String contentText1 = ((TestObject)o).getProperty(".contentText").toString().trim();
						String contentText2 = addedTestO.getProperty(".contentText").toString().trim();
						
						
						String value1 = "";
						String value2 = "";
						try{
							value1 = ((TestObject)o).getProperty(".value").toString().trim();
							value2 = addedTestO.getProperty(".value").toString().trim();
						}catch(PropertyNotFoundException e){
							//do nothing property doesn't exists
						}
						
						String class1 = ((TestObject)o).getProperty("class").toString().trim();
						String class2 = addedTestO.getProperty("class").toString().trim();
						
						if(value1.contentEquals(value2) && tag1.contentEquals(tag2) && contentText1.contentEquals(contentText2) && class1.contentEquals(class2)){
							System.out.print("FOUND==");
							System.out.println(tag1+" "+contentText1);
							foundDup = true;
						}
					}
					if(!foundDup){
						return super.add((T) o);
					}
			}
			return false;	
		}
	}
	
	private void version1(){
		MyArrayList<TestObject> noChildren = new MyArrayList<>();
		MyArrayList<TestObject> hasChildren = new MyArrayList<TestObject>();
		
		RegularExpression onlyLetterAndSpaces = new RegularExpression("[\\p{L}\\s]+", false);
		
		
		TestObject[] objects;
		GuiTestObject container = html_editConf();
		
		//Not Mappable ".value" property are ussually object that can't contain childs
		objects = container.find(atDescendant(".value", onlyLetterAndSpaces), false);
		for(TestObject o : objects){
			noChildren.add(o);
		}
			
		//Mappable ".value" property are ussually object that can't contain childs
		objects = container.find(atDescendant(".value", onlyLetterAndSpaces), true);	
		for(TestObject o : objects){
			noChildren.add(o);
		}
			
		//Not Mappable
		objects = container.find(atChild(".contentText",  onlyLetterAndSpaces), false);
		for(TestObject o : objects){
			hasChildren.add(o);
		}
		
		//Mappable
		objects = container.find(atChild(".contentText",  onlyLetterAndSpaces), true);	
		for(TestObject o : objects){
			hasChildren.add(o);
		}

		//FILTER OUT PARENT objects that takes the child object text
		while(!hasChildren.isEmpty()){
			TestObject[] parents = new TestObject[hasChildren.size()];
			hasChildren.toArray(parents);
			for(TestObject object: parents){
				TestObject[] a = object.find(atChild(".contentText", onlyLetterAndSpaces), false);
				TestObject[] b =  object.find(atChild(".contentText", onlyLetterAndSpaces), true);
				
				MyArrayList<TestObject> childs = new MyArrayList<>();
				for(TestObject o : a){
					childs.add(o);
				}
				for(TestObject o : b){
					childs.add(o);
				}
				
				if(childs.size()>0){		
					//Object has children that contain text
					String contentText = object.getProperty(".contentText").toString();
					contentText = contentText.replaceAll("\\r", "");
					contentText = contentText.replaceAll("\\n", "");
					
					for(TestObject childOfObject : childs){
						//Make sure that all string in parent object is within child objects.
						String childContentText = childOfObject.getProperty(".contentText").toString();
						childContentText = childContentText.replaceAll("\\r", "");
						childContentText = childContentText.replaceAll("\\n", "");
						contentText = contentText.replace(childContentText, "");
					}
					
					if(!contentText.replaceAll(" ", "").isEmpty()){
						//If has text that child doesn't have then added to noChildren array.
						noChildren.add(object);
					}
					
					for(TestObject o : childs){
						hasChildren.add(o);
					}	
					
				}else{
					//Object has no children aka the LEAF and contains text
					noChildren.add(object);

				}
				hasChildren.remove(object);
			}
		}
		
		//lastFilter remove empty string objects
		TestObject[] finalArray = new TestObject[noChildren.size()];
		noChildren.toArray(finalArray);
		for(TestObject o : finalArray){
			String contentText = o.getProperty(".contentText").toString();
			contentText = contentText.replaceAll("\\r", "");
			contentText = contentText.replaceAll("\\n", "");
			if(contentText.replaceAll(" ", "").isEmpty()){
				try{
					//If content text is empty check .value
					((TestObject)o).getProperty(".value").toString().trim();
				}catch(PropertyNotFoundException e){
					boolean deleted = noChildren.remove(o);
					assert deleted == true;
				}
			}
		}
		
		System.out.println("B"+noChildren.size());
		
		int count = 0;
		for(TestObject o : noChildren){
			Highlighter r = utilities.HelperClass.getHighlighter(o);
			r.show();
			sleep(1);
			count++;
		}
		
		System.out.println("Highlighted "+count);
		
		sleep(100);
	}
}

