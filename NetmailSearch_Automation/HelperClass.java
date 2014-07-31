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

/**** USE THE OTHER HELPER CLASS IN UTILITIES INSTEAD*****/
//TODO: Change all reference to this class to the one in utitilies and delete
public class HelperClass extends RationalTestScript {
	
	public static void oneBrowserSetup(){
		TestObject[] browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
		if(browsers.length>1){
			for(TestObject browser: browsers){
				((BrowserTestObject) browser).close();
			}
			startBrowser("Internet Explorer","http://www.google.com");
			sleep(2);
			browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
			((BrowserTestObject) browsers[0]).maximize();
		}else if(browsers.length==0){
			startBrowser("Internet Explorer","http://www.google.com");
			sleep(2);
			browsers = find(atDescendant(".class", "Html.HtmlBrowser"));
			((BrowserTestObject) browsers[0]).maximize();
		}
	}
	
	
	public static TestObject[] getActiveTabBody(){
		TestObject [] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", new RegularExpression("^(\\s)*x-tab-panel-body x-tab-panel-body-top(\\s)*$", false)), true);
		return tabPanelBody[0].find(atChild(".tag", "DIV", "class", new RegularExpression("^(\\s)*x-panel x-panel-noborder(\\s)*$", false)));
	}
	
	
	
}
