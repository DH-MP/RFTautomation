package Netmail_WebAdmin;
import java.util.HashMap;
import java.util.Map;

import resources.Netmail_WebAdmin.webAdminHelper;
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
public class webAdmin extends webAdminHelper
{
	public void login(String userName, String password){
		Map<String, Object> webAdminCredential = new HashMap<>();
		Object[] webAdminLogin = {webAdminCredential};
		webAdminCredential.put("userName", userName);
		webAdminCredential.put("password", password);
		callScript("Netmail_WebAdmin.login", webAdminLogin);
	}
	public void index (String indexLocation, String user, String indexName){
		Map<String, Object> index = new HashMap<>();
		index.put("indexLocation", indexLocation);
		index.put("user", user);
		index.put("indexName", indexName);
		Object[] o = {index};
		callScript("Netmail_WebAdmin.indexer", o);
	}
	
	
	public void createStorageLocation(String locationType, String name, String description, String storageDevice, String locationPath, String superUser){
		Map<String, Object> newStorageLocation = new HashMap<>();
		Object[] newStorageLocationObject = {newStorageLocation};
		newStorageLocation.put("locationType", locationType);
		newStorageLocation.put("name", name);
		newStorageLocation.put("description", description);
		newStorageLocation.put("storageDevice", storageDevice);
		newStorageLocation.put("locationPath", locationPath);
		newStorageLocation.put("superUser",superUser);
		callScript("Netmail_WebAdmin.newStorageLocation", newStorageLocationObject);
	}
	
	public void createStorage(String storageType, String name, String storagePath){
		Map<String, Object> newStorage = new HashMap<>();
		Object[] newStorageObject = {newStorage};
		newStorage.put("storageType", storageType);
		newStorage.put("name", name);
		newStorage.put("storagePath", storagePath);
		callScript("Netmail_WebAdmin.newStorage", newStorageObject);
	}
	
	public void waitForIndexing(String indexName){
		Map<String, Object> mp = new HashMap<>();
		Object[] o = {mp};
		mp.put("indexName", indexName);
		callScript("Netmail_WebAdmin.indexWatcher", o);

	}
}

