package utilities;
import java.util.Arrays;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.rational.test.ft.script.IParameter;
import com.rational.test.ft.script.IVariablesManager;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.datapool.DatapoolFactory;
import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
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
 * Description   : Super class for script helper
 * 
 * @author Administrator
 * @since  July 05, 2013
 */
public abstract class MySuperHelper extends RationalTestScript
{
	public  String IP = "10.1.30.64";
	public  String webAdminIP = "";
	public  String webAdminUUI = "";
	public  String URL = "";
	public  String adminUserName = "administrator";	
	public  String adminPassword = "Pa$$w0rd";
	public 	String remoteWorkSpace = "\\\\10.10.23.61\\Data\\NetmailSearch_v5.3.1";

	
	private IVariablesManager IVM;
	private TestObject[] browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
	
	public MySuperHelper(){	
		//RQM variable override default values IP, adminusername, adminpassword
		IVariablesManager vm = getVariablesManager();
		IParameter rqmIP = vm.getInputParameter("ip");
		IP = rqmIP == null ? IP : rqmIP.getValue();
		URL = "http://"+IP+":8888";
		webAdminIP = "http://"+IP+":89";
		webAdminUUI = "http://"+IP+":8989";
		
		IParameter rqmUserName = vm.getInputParameter("adminUserName");
		IParameter rqmPassword = vm.getInputParameter("adminPassword");
		
		this.adminUserName = rqmUserName != null ? rqmUserName.getValue() : this.adminUserName;
		this.adminPassword = rqmPassword != null ? rqmPassword.getValue() : this.adminPassword;
	}
	
	
	

	public boolean onUnhandledException(java.lang.Throwable e){
		((BrowserTestObject) HelperClass.findBrowser()).click();
		logError("An Unhandled Exception" + e, ((BrowserTestObject) HelperClass.findBrowser()).getScreenSnapshot());
		return false;
	}
	
//	public void onObjectNotFound(ITestObjectMethodState testObjectMethodState){
//		logError("Object Not Found Exception ", getRootTestObject().getScreenSnapshot());
//	}
	
	
	public void waitForloading(){
		Property[] p = new Property[4];
		RegularExpression loadingText = new RegularExpression("(Loading data, please wait...)|(loading the users)", false);
		p[0] = new Property("class", new RegularExpression(".*ext-el-mask-msg x-mask-loading.*", false));
		p[1] = new Property("style", new RegularExpression(".*VISIBILITY: visible;.*", false));
		p[2] = new Property(".text", loadingText);
		p[3] = new Property(".tag", "DIV");
		TestObject[] loading = find(atDescendant(p), true);
		while (loading.length>0){
			sleep(0.5);
			loading = find(atDescendant(p), true);
		}
		sleep(0.5);
		return;
	}
	
	public void onTerminate(){
		//logInfo("Called on terminate");
		unregisterAll();
		cleanup();
	}
	
	public TestObject[] findAtDescendant(TestObject testObject, Property[] properties, boolean savable){
		return testObject.find(atDescendant(properties), savable);
	}
	
	public TestObject[] findAtChild(TestObject testObject, Property[] properties, boolean savable){
		return testObject.find(atChild(properties), savable);
	}
	
	@Override
	public  IVariablesManager getVariablesManager(){
		if(IVM == null){
			IVM = getScriptCaller() == null ? super.getVariablesManager() : getScriptCaller().getVariablesManager();
		}
		return IVM;
	}
	
	
	protected void useParentDataPool() {
		//http://stackoverflow.com/questions/3323083/rational-functional-tester-how-can-i-get-scripts-called-from-a-parent-script-t
        if(this.getScriptCaller() != null) {
            IDatapool dp = this.getScriptCaller().getDatapool();
            IDatapoolIterator iterator = DatapoolFactory.get().open(dp, "");
            if(dp != null && iterator != null) {
                this.dpInitialization(dp, iterator);
            }                           
        }
    }
	
	
	
}

