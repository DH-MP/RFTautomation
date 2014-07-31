package TC_483_LoginAsRegularUser;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Matcher;


import java.io.FileInputStream;
import java.security.MessageDigest;

import resources.TC_483_LoginAsRegularUser.testHelper;
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
public class test extends testHelper
{
	/**
	 * Script Name   : <b>test</b>
	 * Generated     : <b>Jun 5, 2013 3:09:59 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/05
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		
		
	}
	
	private GuiTestObject getTabBody(){
		TestObject [] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", new RegularExpression("^(\\s)*x-tab-panel-body x-tab-panel-body-top(\\s)*$", false)), true);
		return (GuiTestObject) tabPanelBody[0].find(atChild(".tag", "DIV", "class", new RegularExpression("^(\\s)*x-panel x-panel-noborder(\\s)*$", false)))[0];
	}
	
	
	private String getCheckSum(String filePath, String format){

		try {
			
		    MessageDigest md = MessageDigest.getInstance(format);
		    FileInputStream fis = new FileInputStream(filePath);
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
		 
		   return sb.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			return  null;
		}
		
		
		
	}
}

