package TC_1328_FolderStructure;
import resources.TC_1328_FolderStructure.TS_1444_1445_Normal_And_Super_UserHelper;
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
public class TS_1444_1445_Normal_And_Super_User extends TS_1444_1445_Normal_And_Super_UserHelper
{
	/**
	 * Script Name   : <b>TS_1444_1445_Normal_And_Super_User</b>
	 * Generated     : <b>Sep 24, 2013 4:33:20 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/24
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		Boolean admin = dpBoolean("isAdmin");
		
		//Login
		Object[] ls = {dpString("username"), dpString("password"), false, true};
		callScript("loginScript", ls);
		
		if(admin){
			//Admin login
			Object[] al = {dpString("caseName"), dpString("userType")};
			callScript("adminLogin", al);
			waitForloading();
			waitForloading();
		}
		

		TestObject folderUL = admin ? html_folderUL_Admin() : html_folderUL_Normal();
		TestObject rootNode = folderUL.getChildren()[0];
		
		logInfo("finding: < "+dpString("firstNodeName")+" >");
		TestObject firstNode = findNode(dpString("firstNodeName"), rootNode);
		vpManual("ExpectedFirstNodeToExists", dpBoolean("firstNodeExists"), firstNode !=null).performTest();
		
		TestObject firstNode_secondLevelNode = null;
		if(!dpString("secondLevelNodeName").isEmpty()){
			expandNode(firstNode);
			sleep(2);
			logInfo("finding <"+dpString("secondLevelNodeName")+" >");
			firstNode_secondLevelNode = findNode(dpString("secondLevelNodeName"), findUL(firstNode));
			String msg = dpBoolean("secondNodeExists") ? "ExpectedSecondNodeToExists" : "ExpectedSecondNodeNOTEXISTS";
			vpManual(msg, dpBoolean("secondNodeExists"), firstNode_secondLevelNode !=null).performTest();
		}
		
		TestObject firstNode_secondLevelNode_thirdLevelNode = null;
		if(!dpString("thirdLevelNodeName").isEmpty()){
			expandNode(firstNode_secondLevelNode);
			sleep(2);
			logInfo("finding < "+dpString("thirdLevelNodeName")+" >");
			firstNode_secondLevelNode_thirdLevelNode = findNode(dpString("thirdLevelNodeName"), findUL(firstNode_secondLevelNode));
			
			String msg = dpBoolean("thirdNodeExists") ? "ExpectedThirdNodeToExists" : "ExpectedThirdNodeNOTEXISTS";
			vpManual(msg, dpBoolean("thirdNodeExists"), firstNode_secondLevelNode_thirdLevelNode !=null).performTest();
		}
		
		
	}
	
	private void expandNode(TestObject node){
		TestObject[] expander = node.find(atChild(".class", "Html.DIV"), false)[0].find(atChild(".class", "Html.IMG", "class", new RegularExpression("x-tree-ec-icon.*", false)));
		((GuiTestObject)expander[0]).click();
		sleep(1);
	}
	
	public TestObject findNode(String nodeName, TestObject parentElement){
		TestObject[] node = parentElement.find(atChild(".class", "Html.LI", ".text", new RegularExpression(nodeName+".*", false)), false);
		if(node.length == 1){
			return node[0];
		}else{
			return null;
		}
	}
	
	public TestObject findUL(TestObject node){
		TestObject[] ul = node.find(atChild(".class", "Html.UL"), false);
		if(ul.length == 1){
			return ul[0];
		}else{
			return null;
		}
	}
	
}

