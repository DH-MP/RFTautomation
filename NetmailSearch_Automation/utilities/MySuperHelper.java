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
	public static String browserVersion = "IE_9"; //IE_9, IE_11, Chrome_32, FF_17
	public static String IP = "10.1.30.64";
	public static String webAdminIP = "";
	public static String webAdminUUI = "";
	public static String URL = "";
	public static String adminUserName = "administrator";	
	public static String adminPassword = "Pa$$w0rd"; //"Pa$$w0rd";
	public static String webAdminUserName = "netmail";
	public static String webAdminPassword = "M3ss4g1ng";
	public static String remoteWorkSpace = "\\\\10.10.23.61\\Data\\NetmailSearch_v5.3.1";
	public static String version = "n/a";
	private IVariablesManager IVM;
	private TestObject[] browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
	
	
	public MySuperHelper(){	
	}
	
//	@Override
//	public IVariablesManager getVariablesManager(){
//		if(IVM == null){
//			IVM = getScriptCaller() == null ? super.getVariablesManager() : getScriptCaller().getVariablesManager();
//		}
//		return IVM;
//	}
	
	

	public boolean onUnhandledException(java.lang.Throwable e){
		logError("An Unhandled Exception" + e, getRootTestObject().getScreenSnapshot());
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
	
	@Override
	public void onInitialize(){
		//RQM variable override default values IP, adminusername, adminpassword
		logInfo("OnInitialize");
		IVariablesManager IVM = getVariablesManager();
		IParameter rqmIP = IVM.getInputParameter("ip");
		IParameter rqmVersion = IVM.getInputParameter("version");
		IParameter bVersion = IVM.getInputParameter("browserVersion");
		
		IP = rqmIP == null ? IP : rqmIP.getValue();
		version = rqmVersion == null ? version : rqmVersion.getValue();
		browserVersion = bVersion == null ? browserVersion : bVersion.getValue();
		URL = "http://"+IP+":8888";
		webAdminIP = "http://"+IP+":89";
		webAdminUUI = "http://"+IP+":8989";
		
		logInfo("IP:"+IP);
		logInfo("version:"+version);
		logInfo("browserVersion:"+browserVersion);
		logInfo("URL:"+URL);
		logInfo("webAdminIP:"+webAdminIP);
		logInfo("webAdminUUI:"+webAdminUUI);
		
		IParameter rqmUserName = IVM.getInputParameter("adminUserName");
		IParameter rqmPassword = IVM.getInputParameter("adminPassword");
		
		if(rqmUserName != null | rqmPassword !=null){
			logInfo("Overriding default credential with RQM execution variables");		
			logInfo("RQM_CRED: "+ rqmUserName.getValue()+"/"+rqmPassword.getValue());
			this.adminUserName = rqmUserName.getValue();
			this.adminPassword = rqmPassword.getValue();
		}
	}
	
	@Override
	public void onTerminate(){
		//logInfo("Called on terminate");
		unregisterAll();
		cleanup();
		
		logInfo("Version:"+version);
	}
	
	public TestObject[] findAtDescendant(TestObject testObject, Property[] properties, boolean savable){
		return testObject.find(atDescendant(properties), savable);
	}
	
	public TestObject[] findAtChild(TestObject testObject, Property[] properties, boolean savable){
		return testObject.find(atChild(properties), savable);
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

