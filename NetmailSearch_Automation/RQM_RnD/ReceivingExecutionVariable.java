package RQM_RnD;
import resources.RQM_RnD.ReceivingExecutionVariableHelper;
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
public class ReceivingExecutionVariable extends ReceivingExecutionVariableHelper
{
	/**
	 * Script Name   : <b>ReceivingExecutionVariable</b>
	 * Generated     : <b>Jun 10, 2014 1:28:39 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/06/10
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		IVariablesManager vm =getVariablesManager();
		IParameter A = vm.getInputParameter("A");
		
		vpManual("ReceivingExecutionVariable", "blood", A.getValue()).performTest();
	}
}

