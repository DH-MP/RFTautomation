package RQM_RnD;
import resources.RQM_RnD.outputVariableHelper;
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
public class outputVariable extends outputVariableHelper
{
	/**
	 * Script Name   : <b>outputVariable</b>
	 * Generated     : <b>Jun 11, 2014 11:01:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/06/11
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		IVariablesManager vm =getVariablesManager();
		IParameter A = vm.getInputParameter("dump");
		logInfo(A.getValue().toString());
		vpManual("ReceivingExecutionVariable", "blood", A.getValue().toString()).performTest();
	}
}

