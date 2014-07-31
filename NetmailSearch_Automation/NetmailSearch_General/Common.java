package NetmailSearch_General;
import resources.NetmailSearch_General.CommonHelper;
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
public class Common extends CommonHelper
{
	/**
	 * Script Name   : <b>Common</b>
	 * Generated     : <b>Jul 29, 2014 10:41:51 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/29
	 * @author Administrator
	 */
	private static Common c;
	
	protected Common(){

	}
	
	public static void navigatePages(boolean firstPage, boolean previouspage, boolean nextPage, boolean lastPage){
		getInstance().clickPages(firstPage, previouspage, nextPage, lastPage);
	}
	public void clickPages(boolean firstPage, boolean previouspage, boolean nextPage, boolean lastPage ){
		if(firstPage){
			button_firstPage().click();
			logInfo("clicked first page button");

		}
		if (previouspage) {
			button_previousPage().click();
			logInfo("clicked previous page button");
		}
		if (nextPage) {
			button_nextPage().click();
			logInfo("clicked next page button");
		}
		
		if (lastPage) {
			button_lastPage().click();
			logInfo("clicked last page button");
		}
	}
	
	public static Common getInstance(){
		return new Common();
	}
}

