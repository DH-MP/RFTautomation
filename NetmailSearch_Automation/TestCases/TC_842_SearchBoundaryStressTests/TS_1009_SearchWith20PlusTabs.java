package TestCases.TC_842_SearchBoundaryStressTests;
import java.util.HashMap;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.TestCases.TC_842_SearchBoundaryStressTests.TS_1009_SearchWith20PlusTabsHelper;
import utilities.HelperScript;

import Case_Management.manageCase;
import NetmailSearch_AdvanceSearch.MessageWordListTab_SuperUser;
import NetmailSearch_AdvanceSearch.Message_SuperUser;

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
public class TS_1009_SearchWith20PlusTabs extends TS_1009_SearchWith20PlusTabsHelper
{
	/**
	 * Script Name   : <b>TS_985_Superuser</b>
	 * Generated     : <b>Jun 25, 2013 9:33:47 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/25
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		String caseName = "TS1009_20Plus";
		int numberOfTabs = 20;  
		
		//Login
		Object[] ls = {null,null, false};
		callScript("loginScript", ls);
		
		//Admin Login
		Object[] al = {null, "Super User"};
		callScript("adminLogin", al);
		
		//New Case
		manageCase mc = new manageCase();
		mc.setTestMode(false);
		mc.setName(caseName).setLocations("LargeUser;ALS").setTestMode(false).newCase();
		waitForloading();
		
		//Add tabs put default tab into account
		for(int i=0; i<numberOfTabs; i++){
			if(html_tabScrollRight().exists() ){
				html_tabScrollRight().click();
				sleep(1);
			}
			html_addTabLI().click();

			waitForloading();
			logInfo("Clicked + for new tab");
		}	
		
		mc.saveCurrentCase();
		sleep(20);
		browser_htmlBrowser().inputKeys("{F5}");
		sleep(20);
		callScript("loginScript", ls);
		
		Object[] al2 = {caseName, "Super User"};
		callScript("adminLogin", al2);
		sleep(5);
		waitForloading();
		waitForloading();
		
		vpManual("numOfTabSaved", numberOfTabs, findAllTabs().length ).performTest();
		
		//Test search
		TestMessageAndWordList();
			
		
		//Relogin and delete case
		callScript("loginScript", ls);
		callScript("adminLogin", al);
		mc.deleteCase("TS1009_20Plus");
		
	}
	
	private GuiTestObject findActiveTab(){
		TestObject[] tab = find(atDescendant(".tag", "LI", "class", "x-tab-strip-closable x-tab-strip-active"), true);
		while( tab.length != 1){
			tab = find(atDescendant(".tag", "LI", "class", "x-tab-strip-closable x-tab-strip-active"), true);
		}
		return (GuiTestObject)tab[0];
	}
	
	private TestObject[] getActiveTabBody(){
		TestObject [] tabPanelBody = find(atDescendant( ".tag", "DIV", "class", new RegularExpression("^(\\s)*x-tab-panel-body x-tab-panel-body-top(\\s)*$", false)), true);
		return tabPanelBody[0].find(atChild(".tag", "DIV", "class", new RegularExpression("^(\\s)*x-panel x-panel-noborder(\\s)*$", false)));
	}
	
	
	private TestObject[] findAllTabs(){
		return html_tabPanelUL().find(atDescendant(".tag", "LI", "class", new RegularExpression("x-tab-strip-closable.*",false)));
	}
	
	
	
	private void TestMessageAndWordList() {
		
		

		// Point to the datapool location that was created
		java.io.File dpFile = new java.io.File(
				(String) getOption(IOptionName.DATASTORE), "/TestCases/TC_842_SearchBoundaryStressTests/TS_1009_MessageAndWordSearch.rftdp");
		// Load the datapool using RFT IDataPoolFactory
		IDatapool dataPool = dpFactory().load(dpFile, true);

		// Open the datapool using RFT IDataPoolFactory
		IDatapoolIterator dataPoolIter = dpFactory().open(dataPool, null);

		// After it is opened, initilize the datapool to access the data
		dataPoolIter.dpInitialize(dataPool);
		dataPoolIter.dpReset();
		
		//Advance Search
		while(!dataPoolIter.dpDone()){
			HashMap<String, String> query = new HashMap<String, String> ();
			query.put("subject", dataPoolIter.dpString("subject")); 
			query.put("sender", dataPoolIter.dpString("sender")); 
			query.put("recipient", dataPoolIter.dpString("recipient"));
			query.put("body", dataPoolIter.dpString("body"));
			query.put("sendDate1", dataPoolIter.dpString("sendDate1")); 
			query.put("sendDate2",dataPoolIter.dpString("sendDate2"));
			query.put("rcvDate1", dataPoolIter.dpString("rcvDate1"));
			query.put("rcvDate2", dataPoolIter.dpString("rcvDate2"));  
			query.put("personal",dataPoolIter.dpString("personal")); 
			query.put("category",dataPoolIter.dpString("category"));
			
			HashMap<String, Boolean> booleanQuery = new HashMap<String, Boolean> ();
			booleanQuery.put("typeMail", dataPoolIter.dpBoolean("checkTypeMail")); 
			booleanQuery.put("typeAppointment", dataPoolIter.dpBoolean("checkTypeAppointment"));
			booleanQuery.put("typeTask", dataPoolIter.dpBoolean("checkTypeTask"));			
			booleanQuery.put("typeNote", dataPoolIter.dpBoolean("checkTypeNote"));
			booleanQuery.put("searchEmbedded",dataPoolIter.dpBoolean("checkSearchEmbedded"));
			booleanQuery.put("invalidRcvDateRange", dataPoolIter.dpBoolean("invalidRcvDateRange"));
			booleanQuery.put("invalidSentDateRange", dataPoolIter.dpBoolean("invalidSendDateRange"));
			booleanQuery.put("expectedResults", dataPoolIter.dpBoolean("expectedResults"));
			Object[] asmnu = {query, booleanQuery};
			callScript("NetmailSearch_AdvanceSearch.Message_SuperUser", asmnu);
			


			if(dataPoolIter.dpString("wordList")!=null && !dataPoolIter.dpString("wordList").isEmpty()){
				Message_SuperUser msu = new Message_SuperUser();
				msu.clearSearchResult();
				msu.openAdvanceSearchMessage();
				
				MessageWordListTab_SuperUser mwltsu = new MessageWordListTab_SuperUser();
				mwltsu.openWordListTab();
				mwltsu.inputWordList(dataPoolIter.dpString("wordList"), ";");
				mwltsu.search();
				waitForloading();
				mwltsu.validateSearchResult(";");
			}
			
			dataPoolIter.dpNext();
		}
	}
}




//
//package com.dpvt;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.io.Writer;
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.CookiePolicy;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.DecimalFormat;
//import java.text.MessageFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.TreeSet;
//import java.util.Vector;
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.TransformerFactoryConfigurationError;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import org.w3c.dom.DOMException;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import java.io.ByteArrayOutputStream;
//
///**
// * Essentially, HTTP POST a sequence of application/x-www-form-urlencoded data
// * to a URL and either record response (for manual validation) or assert it
// * still equals (from previously recorded output).
// * 
// * DPVTRequester.baseline("c:/out", "http://localhost:8888/remote",
// * "c:/requests.xml", "c:/output.xml") { DPVTRequester.compare("c:/out",
// * "c:/out2", "c:/output.xml") {
// * 
// * changes: - rename and make in default package for better naming calling
// * module. - pretty print json response output. - add param to write
// * ModuleResult output to file. - translate xml to list of
// * application/x-www-form-urlencoded. - call getfolderlist in background for
// * name to id translation. - compare against two generated output instead with
// * server.
// */
//
//public class DPVTRequesterBaseLine {
//
//	// private static String uid;
//	// private static String pwd;
//	// private static String language;
//	private static String baseLineFolder;
//	private static String requestFile;
//	private static String resultFile;
//
//	// Simple factory for creating a new blank XML result.
//	private static class XMLResult {
//		private Document doc;
//
//		XMLResult() throws ParserConfigurationException {
//			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
//					.newDocument();
//			doc.appendChild(doc.createElement("ModuleResult"));
//			doc.getDocumentElement().appendChild(
//					doc.createElement("ReturnValue"));
//			doc.getDocumentElement().appendChild(doc.createElement("Messages"));
//			((Element) doc.getDocumentElement().getFirstChild()).setAttribute(
//					"CODE", "0");
//		}
//
//		public Document getXML() {
//			return doc;
//		}
//
//		/**
//		 * Append message to the result.
//		 * 
//		 * @param message
//		 */
//		public void append(String message) {
//			doc.getDocumentElement().getFirstChild()
//					.appendChild(doc.createTextNode(message));
//		}
//
//		/**
//		 * Add a message node (within the second child) in doc, setting the code
//		 * attribute of first child to failure if kind is anything above info.
//		 */
//		public void log(String message, String kind) {
//
//			String intKind = "0";
//			if (kind.equals("Info")) {
//				intKind = "3";
//			} else if (kind.equals("Warning")) {
//				intKind = "1";
//			} else if (kind.equals("Error")) {
//				intKind = "-1";
//			} else if (kind.equals("Debug")) {
//				intKind = "2";
//			}
//
//			Element m = doc.createElement("Message");
//			m.setAttribute("KIND", intKind);
//			m.appendChild(doc.createTextNode(message));
//			doc.getDocumentElement().getChildNodes().item(1).appendChild(m);
//			if (!"Info".equals(kind)) {
//				((Element) doc.getDocumentElement().getFirstChild())
//						.setAttribute("CODE", "-1");
//			}
//		}
//	}
//
//	private static class FolderTextWithoutIdException extends Exception {
//		private static final long serialVersionUID = -6043441737663223872L;
//
//		public FolderTextWithoutIdException(String message) {
//			super(message);
//		}
//	}
//
//	private static void parseJson(String jsonString) throws Exception {
//		JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
//	}
//
//	/**
//	 * loads JSONObject from Reader, filtering out 'list[i].lastModified'. todo:
//	 * rename to clearly state that it does filtering
//	 * 
//	 * @throws IOException
//	 */
//	@SuppressWarnings({ "rawtypes", "unused" })
//	static Object readJSON(InputStream in) throws Exception {
//		Map<?, ?> obj = null;
//
//		// ***************
//		BufferedReader r = new BufferedReader(new InputStreamReader(in));
//		StringBuffer content = new StringBuffer();
//		String line;
//
//		while ((line = r.readLine()) != null) {
//			// writer.print(line);
//			// content.append(line);
//			System.out.println(line);
//			parseJson(line);
//		}
//		// ***************
//
//		try {
//			ObjectMapper m = new ObjectMapper();
//			obj = new ObjectMapper().readValue(in, Map.class);
//
//			List a = (List) obj.get("list");
//			if (a != null) {
//				for (Object item : a) {
//					try {
//						((Map<?, ?>) item).remove("lastModified");
//					} catch (ClassCastException e) {
//						// unexpected object, ignore the error and move on
//						System.err.println("Unexpected Obj");
//					}
//				}
//			}
//		} catch (JsonParseException e) {
//			// Debug(e.toString());
//			// e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// Debug(e.toString());
//			// e.printStackTrace();
//		} catch (IOException e) {
//			// user will then get null
//			// Debug(e.toString());
//			// e.printStackTrace();
//		}
//		return obj;
//	}
//
//	private static void writeJSON(Object value, Writer out)
//			throws JsonGenerationException, JsonMappingException, IOException {
//		ObjectMapper m = new ObjectMapper();
//		m.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
//		m.writeValue(out, value);
//	}
//
//	private static Document readXML(InputStream is) throws SAXException,
//			IOException, ParserConfigurationException {
//
//		Document document = null;
//		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
//				.newInstance();
//
//		try {
//			DocumentBuilder db = documentFactory.newDocumentBuilder();
//			document = db.parse(is);
//		} catch (Exception e) {
//			ByteArrayOutputStream data = new ByteArrayOutputStream();
//			PrintStream err = new PrintStream(data);
//			e.printStackTrace(err);
//			String stack = new String(data.toByteArray());
//			Debug("Error in readXML:" + e.getMessage() + "\r\n" + stack);
//			e.printStackTrace();
//		}
//
//		return document; // DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
//	}
//
//	// And still wondering why Document#toString was not there.
//	private static void writeXML(Document doc, BufferedOutputStream out)
//			throws TransformerFactoryConfigurationError, TransformerException {
//		Transformer t = TransformerFactory.newInstance().newTransformer();
//		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//		t.setOutputProperty(OutputKeys.METHOD, "xml");
//		t.setOutputProperty(OutputKeys.INDENT, "yes");
//		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//		// t.transform(new DOMSource(doc), new StreamResult(out));
//		OutputStreamWriter writer = null;
//		try {
//			t.transform(new DOMSource(doc), new StreamResult(
//					writer = new OutputStreamWriter(out, "UTF-8")));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				writer.flush();
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	// wow, java dont have builtin join, im in shock!
//	static String join(Iterable<? extends Object> collection, String separator) {
//		Iterator<? extends Object> it;
//		if (collection == null || (!(it = collection.iterator()).hasNext()))
//			return "";
//		StringBuilder s = new StringBuilder(String.valueOf(it.next()));
//		while (it.hasNext())
//			s.append(separator).append(it.next());
//		return s.toString();
//	}
//
//	private static String urlEncodeAttributes(NamedNodeMap nodeMap, String enc)
//			throws UnsupportedEncodingException, DOMException {
//		List<String> attrs = new ArrayList<String>();
//		for (int i = 0; i < nodeMap.getLength(); i++) {
//			Node n = nodeMap.item(i);
//			attrs.add(n.getNodeName() + "="
//					+ URLEncoder.encode(n.getNodeValue(), enc));
//		}
//		return join(attrs, "&"); // categoryList=%7B%7D&cmd=mplus.ediscovery.search&criteria=message&limit=2000&messageList=%5B%5D&nodeList=null&specific=&ssid=test&start=0&text=&type=message&wordlist=
//	}
//
//	private static HttpURLConnection request(URL url, String data)
//			throws IOException {
//		System.out.println(data);
//		HttpURLConnection cn = (HttpURLConnection) url.openConnection();
//		cn.setRequestMethod("POST");
//		cn.setDoOutput(true);
//		cn.setDoInput(true);
//
//		// cn.setRequestProperty("Content-Type",
//		// "application/x-www-form-urlencoded");
//		// cn.setRequestProperty("charset", "utf-8");
//		// cn.setUseCaches(false);
//		cn.connect();
//		DataOutputStream out = new DataOutputStream(cn.getOutputStream());
//		out.write(data.getBytes());
//		out.flush();
//		out.close();
//		return cn;
//	}
//
//	private static String getFolderId(List<String> paths, URL url,
//			Map<String, String> map) throws Exception,
//			FolderTextWithoutIdException {
//		List<String> p = new ArrayList<String>();
//		for (int i = 0; i < paths.size(); i++) {
//			p.add(getFolderId(paths.get(i), url, map));
//		}
//		return join(p, ",");
//	}
//
//	@SuppressWarnings("unchecked")
//	private static String getFolderId(String path, URL url,
//			Map<String, String> map) throws IOException,
//			FolderTextWithoutIdException, Exception {
//		if (path == null)
//			path = "";
//		else
//			path = path.trim().replaceFirst("^\\\\", "")
//					.replaceFirst("\\\\$", "");
//
//		if (map.containsKey(path)) {
//			return map.get(path);
//		} else {
//			int lio = path.lastIndexOf('\\');
//			String parentText;
//			String parentId;
//			if (lio > -1) {
//				parentText = path.substring(0, lio);
//				parentId = getFolderId(parentText, url, map);
//				if (parentId == null)
//					throw new FolderTextWithoutIdException(parentText);
//			} else {
//				parentText = "";
//				parentId = "~ROOT~";
//			}
//			String data = "cmd=mplus.ediscovery.getFolderList&start=0&limit=200&options=0&caseid=test&ssid=test&node="
//					+ parentId;
//			InputStream in = request(url, data).getInputStream();
//			Map<String, ?> o = (Map<String, ?>) readJSON(in);
//			ArrayList<Map<String, ?>> ol = (ArrayList<Map<String, ?>>) o
//					.get("nodes");
//			// be casefull here, we may throw NullPointerException, not very
//			// useful for debugging ...
//			for (Map<String, ?> e : ol) {
//				String text = (String) e.get("text");
//				String key = ("".equals(parentText)) ? text : parentText + "\\"
//						+ text;
//				map.put(key, (String) e.get("id"));
//			}
//			return map.get(path);
//		}
//	}
//
//	/**
//	 * String only arguments wrapper for baseline; saving XML result to file.
//	 * 
//	 * @param base
//	 *            File pathname to directory where responses will be written.
//	 * @param url
//	 *            URL spec of remote provider (usually http://localhost:8888)
//	 * @param requests
//	 *            File pathname to XML that contains the list of 'request'
//	 *            nodes.
//	 * @param result
//	 *            File pathname where "ModuleResult" is to be saved.
//	 * @throws Exception
//	 */
//	public static void baseline(String base, String url, String requests,
//			String result) throws Exception {
//		try {
//			Debug("baseLine baseDir=" + base + " url=" + url + " requests="
//					+ requests + ", result=" + result);
//			File baseDir = new File(base);
//			if (!baseDir.exists()) {
//				baseDir.mkdirs();
//			} else {
//				for (File in : baseDir.listFiles()) {
//					in.delete();
//				}
//
//			}
//			Debug("before doc");
//
//			GregorianCalendar date = (GregorianCalendar) GregorianCalendar
//					.getInstance();
//			date.add(date.DATE, 0);
//			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-SSS");
//			String name = format1.format(date.getTime());
//
//			Document doc = baseline(baseDir, new URL(url),
//					readXML(new FileInputStream(requests)));
//			writeXML(doc, new BufferedOutputStream(new FileOutputStream(result
//					+ name + "-" + (Thread.currentThread().getName()) + "("
//					+ System.currentTimeMillis() + ")" + ".xml")));
//
//		} catch (Exception e) {
//			ByteArrayOutputStream data = new ByteArrayOutputStream();
//			PrintStream err = new PrintStream(data);
//			e.printStackTrace(err);
//			String stack = new String(data.toByteArray());
//			Debug("exception in baseline:" + e.getMessage() + "\r\n" + stack);
//			e.printStackTrace();
//			throw (e);
//		}
//	}
//
//	/**
//	 * Make each 'requests' on 'url', recording each response in a separate file
//	 * in 'base' directory, saving log infos to 'result' XML.
//	 * 
//	 * @param base
//	 *            File pathname to directory where responses will be written.
//	 * @param url
//	 *            URL spec of remote provider (usually http://localhost:8888)
//	 * @param requests
//	 *            File pathname to XML that contains the list of 'request'
//	 *            nodes.
//	 * @return Document "ModuleResult" XML.
//	 * @throws ParserConfigurationException
//	 */
//	public static Document baseline(File base, URL url, Document requests)
//			throws ParserConfigurationException {
//		Debug("baseline(base=" + base + ", url=" + url + ", requests="
//				+ requests);
//		HashMap<String, String> foldersIdCache = new HashMap<String, String>();
//		NodeList nodes = requests.getElementsByTagName("request");
//		XMLResult result = new XMLResult();
//		String fileName = null;
//		;
//
//		long startTime = System.currentTimeMillis(), endTime;
//		int i = 0;
//
//		try {
//			for (; i < nodes.getLength(); i++) {
//
//				// TODO set username here from the argument
//
//				Element n = (Element) nodes.item(i);
//				// System.err.println(n.getAttribute("uid"));
//				// set uid
//				
//				username="cn=rft@BASE2012@First Organization,ou=PostOffices,ou=GWOpenServer,o=archive";
//				
//				n.setAttribute("uid", "rft");
//				System.err.println(n.getAttribute("uid"));
//				// System.err.println(n.getAttribute("nodeList"));
//
//				// set and convert nodeList
//				// assume that location is always the same
//				String nodeList = "{\"afn\":\"\",\"rp\":\"/\",\"loc\":\"1407272263379\"}";
//				
//				//Pattern p = Pattern.compile("\\d{5}");
//				//Matcher m = p.matcher(username);
//				//System.err.println(m.find());
//				// System.err.println(m.group());
//				//String userID = m.group();
//				nodeList = "{\"afn\":\"rft@rft.BASE2012.First Organization\",\"rp\":\"/\",\"loc\":\"1407272263379\"}";
//				// System.out.println(nodeList);
//				n.setAttribute("nodeList", toHex(nodeList));
//				// System.err.println(n.getAttribute("nodeList"));
//
//				// attributes set on document element apply to all requests
//				NamedNodeMap globalAttrs = requests.getDocumentElement()
//						.getAttributes();
//				for (int j = 0; j < globalAttrs.getLength(); j++) {
//					Node at = globalAttrs.item(j);
//					if ("".equals(n.getAttribute(at.getNodeName()))) {
//						n.setAttribute(at.getNodeName(), at.getNodeValue());
//					}
//				}
//
//				// nodeList gets translated from human format to id (requests
//				// are made in the background)
//				// if (!n.getAttribute("nodeList").equals("")
//				// && !n.getAttribute("nodeList").equals("name")) {
//				// result.log(
//				// Integer.toString(i)
//				// + ". translating nodeList to id: "
//				// + n.getAttribute("nodeList"), "Info");
//				// System.err.println(n.getAttribute("nodeList"));
//				// n.setAttribute("nodeList",
//				// translateNode(n.getAttribute("nodeList")));
//				// System.err.println(n.getAttribute("nodeList"));
//				// }
//
//				String data = urlEncodeAttributes(n.getAttributes(),
//						requests.getInputEncoding());
//				result.log(Integer.toString(i) + ". " + data, "Info");
//
//				InputStream in = request(url, data).getInputStream();
//				Object o = readJSON(in);
//
//				if (!n.getAttribute("name").equals("")) {
//					fileName = n.getAttribute("name");
//				} else {
//					throw new Exception("Query without name");
//
//				}
//			}
//		} catch (Exception e) {
//			result.log(e.toString(), "Error");
//		} finally {
//			endTime = System.currentTimeMillis();
//		}
//		appendResult(endTime - startTime);
//
//		result.append(MessageFormat.format(
//				"{0}/{1} requests on <{2}> for <{3}> in {4}ms.", i,
//				nodes.getLength(), url.toString(), base.toString(), endTime
//						- startTime));
//		return result.getXML();
//	}
//
//	public static String toHex(String ascii) {
//		String str = "";
//		for (int i = 0; i < ascii.length(); i++) {
//			char temp = ascii.charAt(i);
//			str += Integer.toHexString((int) temp);
//		}
//		return str;
//	}
//
//	// private static String getUsername() {
//	// Pattern p = Pattern.compile("\\w*.xml");
//	// Matcher m = p.matcher(globalRequest);
//	// if (m.find()) {
//	// return m.group();
//	// }
//	// return null;
//	// }
//
//	private static void appendResult(long time) {
//		Pattern p = Pattern.compile("GWA\\d{5}");
//		Matcher m = p.matcher(username);
//		String uid = "";
//		if (m.find())
//			uid = m.group();
//		else
//			uid = Long.toString(System.currentTimeMillis());
//
//		System.out.println("*****USER\n" + uid);
//
//		PrintWriter out = null;
//		boolean newDir = new File(folderName).mkdir();
//		if (!newDir) {
//			System.err.println("no new folder");
//		}
//		try {
//			out = new PrintWriter(new BufferedWriter(new FileWriter(folderName
//					+ "jsonStoreResult.txt", true)));
//			out.println("{testUser:'" + uid + "',runTime:" + time + "},");
//			out.println(baselineData);
//		} catch (IOException e) {
//			// do nothing
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//	}
//
//	public static String translateNode(String nodeList) {
//		StringBuilder output = new StringBuilder();
//		for (int i = 0; i < nodeList.length(); i += 2) {
//			String str = nodeList.substring(i, i + 2);
//			output.append((char) Integer.parseInt(str, 16));
//		}
//		// System.err.println(output.toString());
//
//		return output.toString();
//	}
//
//	public static void connect(String url, String... args)
//			throws MalformedURLException, IOException {
//
//		CookieHandler.setDefault(new CookieManager(null,
//				CookiePolicy.ACCEPT_ALL));
//
//		if (args != null && args.length > 0) {
//			// uid = (args[0] != null && !args[0].isEmpty())? args[0] : "admin";
//			// pwd = (args[1] != null && !args[1].isEmpty())? args[1] :
//			// "password";
//			// language = (args[2] != null && !args[2].isEmpty())? args[2] :
//			// "en";
//			baseLineFolder = (args[0] != null && !args[0].isEmpty()) ? args[0]
//					: "C:\\temp\\";
//			requestFile = (args[1] != null && !args[1].isEmpty()) ? args[1]
//					: "c:/temp/request.xml";
//			resultFile = (args[2] != null && !args[2].isEmpty()) ? args[2]
//					: "c:/temp/result.xml";
//		} else {
//			// uid = "admin";
//			// pwd = "password";
//			// language = "en";
//			baseLineFolder = "c:/temp";
//			requestFile = "c:/temp/request.xml";
//			resultFile = "c:/temp/result.xml";
//		}
//		// manual login
//		// String data = "cmd=mplus.ediscovery.login&caseid=test&ssid=test&uid="
//		// + uid + "&pwd=" + pwd + "&language=" + language;
//		// InputStream in = request(new URL(url), data).getInputStream();
//
//	}
//
//	public static void Debug(String txt) {
//		Date now = new Date();
//		try {
//			FileWriter writer = new FileWriter("C:\\dpvt.txt", true);
//			writer.write(now + " DPVTRequesterBaseLine.java " + txt + "\r\n");
//			writer.close();
//		} catch (Exception e) {
//
//		}
//	}
//
//	public void service(String url, String... args) {
//		DelOldDebug();
//		Debug("service");
//		try {
//			Debug("before connect");
//			connect(url, args);
//			Debug("before baseline");
//			baseline(args[0], url, args[1], args[2]);
//			Debug("after baseline");
//		} catch (IOException e) {
//			System.out.println("Connection problems: " + e.getMessage());
//			writeErrorToResultXml(url, e);
//		} catch (Exception e) {
//			System.out.println("Problem while running baseline: "
//					+ e.getMessage());
//			e.printStackTrace();
//			writeOtherErrorToResultXml(e);
//		}
//	}
//
//	private void DelOldDebug() {
//		File f = new File("C:\\dpvt.txt");
//		if (f.exists()) {
//			f.delete();
//		}
//	}
//
//	private void writeErrorToResultXml(String url, IOException e) {
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
//				+ "<ModuleResult>\n"
//				+ "<ReturnValue CODE=\"-1\">"
//				+ "Connection problem to "
//				+ url
//				+ " :"
//				+ e.getMessage()
//				+ "</ReturnValue>\n" + "</ModuleResult>";
//		try {
//			FileWriter writer = new FileWriter(resultFile);
//			writer.write(xml);
//			writer.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	private void writeOtherErrorToResultXml(Exception e) {
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
//				+ "<ModuleResult>\n"
//				+ "<ReturnValue CODE=\"-1\">"
//				+ "Problem with baseline:"
//				+ e.getMessage()
//				+ "</ReturnValue>\n" + "</ModuleResult>";
//		try {
//			FileWriter writer = new FileWriter(resultFile
//					+ System.currentTimeMillis());
//			writer.write(xml);
//			writer.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	private static String folderName = "";
//	private static String globalRequest = "";
//	private static String baselineData = "";
//	private static String username = "";
//
//	public static void main(String[] args){
//		String baseLineFolder = args[0];// "c:/temp";
//		String requestFile = args[1];// "c:/temp/request.xml";
//		String resultFile = args[2];// "c:/temp/result.xml";
//		String url = args[3];// "http://localhost:8888/remote"
//		globalRequest = args[1];
//		folderName = args[4];
//		baselineData = args[5]; // C:\\loadtest\\dpvtresults\\baseline.txt
//		username = args[6]; //
//
//		String params[] = { baseLineFolder, requestFile, resultFile };
//
//		DPVTRequesterBaseLine baseline = new DPVTRequesterBaseLine();
//		baseline.service(url, params);
//	}
//}
///*
// * package com.dpvt;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.io.Writer;
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.CookiePolicy;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.DecimalFormat;
//import java.text.MessageFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.TreeSet;
//import java.util.Vector;
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.TransformerFactoryConfigurationError;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import org.w3c.dom.DOMException;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import java.io.ByteArrayOutputStream;
//
///**
// * Essentially, HTTP POST a sequence of application/x-www-form-urlencoded data
// * to a URL and either record response (for manual validation) or assert it
// * still equals (from previously recorded output).
// * 
// * DPVTRequester.baseline("c:/out", "http://localhost:8888/remote",
// * "c:/requests.xml", "c:/output.xml") { DPVTRequester.compare("c:/out",
// * "c:/out2", "c:/output.xml") {
// * 
// * changes: - rename and make in default package for better naming calling
// * module. - pretty print json response output. - add param to write
// * ModuleResult output to file. - translate xml to list of
// * application/x-www-form-urlencoded. - call getfolderlist in background for
// * name to id translation. - compare against two generated output instead with
// * server.
// */
//
//public class DPVTRequesterBaseLine {
//
//	// private static String uid;
//	// private static String pwd;
//	// private static String language;
//	private static String baseLineFolder;
//	private static String requestFile;
//	private static String resultFile;
//
//	// Simple factory for creating a new blank XML result.
//	private static class XMLResult {
//		private Document doc;
//
//		XMLResult() throws ParserConfigurationException {
//			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
//					.newDocument();
//			doc.appendChild(doc.createElement("ModuleResult"));
//			doc.getDocumentElement().appendChild(
//					doc.createElement("ReturnValue"));
//			doc.getDocumentElement().appendChild(doc.createElement("Messages"));
//			((Element) doc.getDocumentElement().getFirstChild()).setAttribute(
//					"CODE", "0");
//		}
//
//		public Document getXML() {
//			return doc;
//		}
//
//		/**
//		 * Append message to the result.
//		 * 
//		 * @param message
//		 */
//		public void append(String message) {
//			doc.getDocumentElement().getFirstChild()
//					.appendChild(doc.createTextNode(message));
//		}
//
//		/**
//		 * Add a message node (within the second child) in doc, setting the code
//		 * attribute of first child to failure if kind is anything above info.
//		 */
//		public void log(String message, String kind) {
//
//			String intKind = "0";
//			if (kind.equals("Info")) {
//				intKind = "3";
//			} else if (kind.equals("Warning")) {
//				intKind = "1";
//			} else if (kind.equals("Error")) {
//				intKind = "-1";
//			} else if (kind.equals("Debug")) {
//				intKind = "2";
//			}
//
//			Element m = doc.createElement("Message");
//			m.setAttribute("KIND", intKind);
//			m.appendChild(doc.createTextNode(message));
//			doc.getDocumentElement().getChildNodes().item(1).appendChild(m);
//			if (!"Info".equals(kind)) {
//				((Element) doc.getDocumentElement().getFirstChild())
//						.setAttribute("CODE", "-1");
//			}
//		}
//	}
//
//	private static class FolderTextWithoutIdException extends Exception {
//		private static final long serialVersionUID = -6043441737663223872L;
//
//		public FolderTextWithoutIdException(String message) {
//			super(message);
//		}
//	}
//
//	private static void parseJson(String jsonString) throws Exception {
//		JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
//	}
//
//	/**
//	 * loads JSONObject from Reader, filtering out 'list[i].lastModified'. todo:
//	 * rename to clearly state that it does filtering
//	 * 
//	 * @throws IOException
//	 */
//	@SuppressWarnings({ "rawtypes", "unused" })
//	static Object readJSON(InputStream in) throws Exception {
//		Map<?, ?> obj = null;
//
//		// ***************
//		BufferedReader r = new BufferedReader(new InputStreamReader(in));
//		StringBuffer content = new StringBuffer();
//		String line;
//
//		while ((line = r.readLine()) != null) {
//			// writer.print(line);
//			// content.append(line);
//			System.out.println(line);
//			parseJson(line);
//		}
//		// ***************
//
//		try {
//			ObjectMapper m = new ObjectMapper();
//			obj = new ObjectMapper().readValue(in, Map.class);
//
//			List a = (List) obj.get("list");
//			if (a != null) {
//				for (Object item : a) {
//					try {
//						((Map<?, ?>) item).remove("lastModified");
//					} catch (ClassCastException e) {
//						// unexpected object, ignore the error and move on
//						System.err.println("Unexpected Obj");
//					}
//				}
//			}
//		} catch (JsonParseException e) {
//			// Debug(e.toString());
//			// e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// Debug(e.toString());
//			// e.printStackTrace();
//		} catch (IOException e) {
//			// user will then get null
//			// Debug(e.toString());
//			// e.printStackTrace();
//		}
//		return obj;
//	}
//
//	private static void writeJSON(Object value, Writer out)
//			throws JsonGenerationException, JsonMappingException, IOException {
//		ObjectMapper m = new ObjectMapper();
//		m.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
//		m.writeValue(out, value);
//	}
//
//	private static Document readXML(InputStream is) throws SAXException,
//			IOException, ParserConfigurationException {
//
//		Document document = null;
//		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
//				.newInstance();
//
//		try {
//			DocumentBuilder db = documentFactory.newDocumentBuilder();
//			document = db.parse(is);
//		} catch (Exception e) {
//			ByteArrayOutputStream data = new ByteArrayOutputStream();
//			PrintStream err = new PrintStream(data);
//			e.printStackTrace(err);
//			String stack = new String(data.toByteArray());
//			Debug("Error in readXML:" + e.getMessage() + "\r\n" + stack);
//			e.printStackTrace();
//		}
//
//		return document; // DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
//	}
//
//	// And still wondering why Document#toString was not there.
//	private static void writeXML(Document doc, BufferedOutputStream out)
//			throws TransformerFactoryConfigurationError, TransformerException {
//		Transformer t = TransformerFactory.newInstance().newTransformer();
//		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//		t.setOutputProperty(OutputKeys.METHOD, "xml");
//		t.setOutputProperty(OutputKeys.INDENT, "yes");
//		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//		// t.transform(new DOMSource(doc), new StreamResult(out));
//		OutputStreamWriter writer = null;
//		try {
//			t.transform(new DOMSource(doc), new StreamResult(
//					writer = new OutputStreamWriter(out, "UTF-8")));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				writer.flush();
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	// wow, java dont have builtin join, im in shock!
//	static String join(Iterable<? extends Object> collection, String separator) {
//		Iterator<? extends Object> it;
//		if (collection == null || (!(it = collection.iterator()).hasNext()))
//			return "";
//		StringBuilder s = new StringBuilder(String.valueOf(it.next()));
//		while (it.hasNext())
//			s.append(separator).append(it.next());
//		return s.toString();
//	}
//
//	private static String urlEncodeAttributes(NamedNodeMap nodeMap, String enc)
//			throws UnsupportedEncodingException, DOMException {
//		List<String> attrs = new ArrayList<String>();
//		for (int i = 0; i < nodeMap.getLength(); i++) {
//			Node n = nodeMap.item(i);
//			attrs.add(n.getNodeName() + "="
//					+ URLEncoder.encode(n.getNodeValue(), enc));
//		}
//		return join(attrs, "&"); // categoryList=%7B%7D&cmd=mplus.ediscovery.search&criteria=message&limit=2000&messageList=%5B%5D&nodeList=null&specific=&ssid=test&start=0&text=&type=message&wordlist=
//	}
//
//	private static HttpURLConnection request(URL url, String data)
//			throws IOException {
//		System.out.println(data);
//		HttpURLConnection cn = (HttpURLConnection) url.openConnection();
//		cn.setRequestMethod("POST");
//		cn.setDoOutput(true);
//		cn.setDoInput(true);
//
//		// cn.setRequestProperty("Content-Type",
//		// "application/x-www-form-urlencoded");
//		// cn.setRequestProperty("charset", "utf-8");
//		// cn.setUseCaches(false);
//		cn.connect();
//		DataOutputStream out = new DataOutputStream(cn.getOutputStream());
//		out.write(data.getBytes());
//		out.flush();
//		out.close();
//		return cn;
//	}
//
//	private static String getFolderId(List<String> paths, URL url,
//			Map<String, String> map) throws Exception,
//			FolderTextWithoutIdException {
//		List<String> p = new ArrayList<String>();
//		for (int i = 0; i < paths.size(); i++) {
//			p.add(getFolderId(paths.get(i), url, map));
//		}
//		return join(p, ",");
//	}
//
//	@SuppressWarnings("unchecked")
//	private static String getFolderId(String path, URL url,
//			Map<String, String> map) throws IOException,
//			FolderTextWithoutIdException, Exception {
//		if (path == null)
//			path = "";
//		else
//			path = path.trim().replaceFirst("^\\\\", "")
//					.replaceFirst("\\\\$", "");
//
//		if (map.containsKey(path)) {
//			return map.get(path);
//		} else {
//			int lio = path.lastIndexOf('\\');
//			String parentText;
//			String parentId;
//			if (lio > -1) {
//				parentText = path.substring(0, lio);
//				parentId = getFolderId(parentText, url, map);
//				if (parentId == null)
//					throw new FolderTextWithoutIdException(parentText);
//			} else {
//				parentText = "";
//				parentId = "~ROOT~";
//			}
//			String data = "cmd=mplus.ediscovery.getFolderList&start=0&limit=200&options=0&caseid=test&ssid=test&node="
//					+ parentId;
//			InputStream in = request(url, data).getInputStream();
//			Map<String, ?> o = (Map<String, ?>) readJSON(in);
//			ArrayList<Map<String, ?>> ol = (ArrayList<Map<String, ?>>) o
//					.get("nodes");
//			// be casefull here, we may throw NullPointerException, not very
//			// useful for debugging ...
//			for (Map<String, ?> e : ol) {
//				String text = (String) e.get("text");
//				String key = ("".equals(parentText)) ? text : parentText + "\\"
//						+ text;
//				map.put(key, (String) e.get("id"));
//			}
//			return map.get(path);
//		}
//	}
//
//	/**
//	 * String only arguments wrapper for baseline; saving XML result to file.
//	 * 
//	 * @param base
//	 *            File pathname to directory where responses will be written.
//	 * @param url
//	 *            URL spec of remote provider (usually http://localhost:8888)
//	 * @param requests
//	 *            File pathname to XML that contains the list of 'request'
//	 *            nodes.
//	 * @param result
//	 *            File pathname where "ModuleResult" is to be saved.
//	 * @throws Exception
//	 */
//	public static void baseline(String base, String url, String requests,
//			String result) throws Exception {
//		try {
//			Debug("baseLine baseDir=" + base + " url=" + url + " requests="
//					+ requests + ", result=" + result);
//			File baseDir = new File(base);
//			if (!baseDir.exists()) {
//				baseDir.mkdirs();
//			} else {
//				for (File in : baseDir.listFiles()) {
//					in.delete();
//				}
//
//			}
//			Debug("before doc");
//
//			GregorianCalendar date = (GregorianCalendar) GregorianCalendar
//					.getInstance();
//			date.add(date.DATE, 0);
//			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-SSS");
//			String name = format1.format(date.getTime());
//
//			Document doc = baseline(baseDir, new URL(url),
//					readXML(new FileInputStream(requests)));
//			writeXML(doc, new BufferedOutputStream(new FileOutputStream(result
//					+ name + "-" + (Thread.currentThread().getName()) + "("
//					+ System.currentTimeMillis() + ")" + ".xml")));
//
//		} catch (Exception e) {
//			ByteArrayOutputStream data = new ByteArrayOutputStream();
//			PrintStream err = new PrintStream(data);
//			e.printStackTrace(err);
//			String stack = new String(data.toByteArray());
//			Debug("exception in baseline:" + e.getMessage() + "\r\n" + stack);
//			e.printStackTrace();
//			throw (e);
//		}
//	}
//
//	/**
//	 * Make each 'requests' on 'url', recording each response in a separate file
//	 * in 'base' directory, saving log infos to 'result' XML.
//	 * 
//	 * @param base
//	 *            File pathname to directory where responses will be written.
//	 * @param url
//	 *            URL spec of remote provider (usually http://localhost:8888)
//	 * @param requests
//	 *            File pathname to XML that contains the list of 'request'
//	 *            nodes.
//	 * @return Document "ModuleResult" XML.
//	 * @throws ParserConfigurationException
//	 */
//	public static Document baseline(File base, URL url, Document requests)
//			throws ParserConfigurationException {
//		Debug("baseline(base=" + base + ", url=" + url + ", requests="
//				+ requests);
//		HashMap<String, String> foldersIdCache = new HashMap<String, String>();
//		NodeList nodes = requests.getElementsByTagName("request");
//		XMLResult result = new XMLResult();
//		String fileName = null;
//		;
//
//		long startTime = System.currentTimeMillis(), endTime;
//		int i = 0;
//
//		try {
//			for (; i < nodes.getLength(); i++) {
//
//				// TODO set username here from the argument
//
//				Element n = (Element) nodes.item(i);
//				// System.err.println(n.getAttribute("uid"));
//				// set uid
//				n.setAttribute("uid", username);
//				System.err.println(n.getAttribute("uid"));
//				// System.err.println(n.getAttribute("nodeList"));
//
//				// set and convert nodeList
//				// assume that location is always the same
//				String nodeList = "{\"afn\":\"\",\"rp\":\"/\",\"loc\":\"1257274138906\"}";
//				Pattern p = Pattern.compile("\\d{5}");
//				Matcher m = p.matcher(username);
//				System.err.println(m.find());
//				// System.err.println(m.group());
//				String userID = m.group();
//				nodeList = "{\"afn\":\"GWA"
//						+ userID
//						+ " MA"
//						+ userID
//						+ "@GWA"
//						+ userID
//						+ ".PO02.DOM02\",\"rp\":\"/\",\"loc\":\"1257274138906\"}";
//				// System.out.println(nodeList);
//				n.setAttribute("nodeList", toHex(nodeList));
//				// System.err.println(n.getAttribute("nodeList"));
//
//				// attributes set on document element apply to all requests
//				NamedNodeMap globalAttrs = requests.getDocumentElement()
//						.getAttributes();
//				for (int j = 0; j < globalAttrs.getLength(); j++) {
//					Node at = globalAttrs.item(j);
//					if ("".equals(n.getAttribute(at.getNodeName()))) {
//						n.setAttribute(at.getNodeName(), at.getNodeValue());
//					}
//				}
//
//				// nodeList gets translated from human format to id (requests
//				// are made in the background)
//				// if (!n.getAttribute("nodeList").equals("")
//				// && !n.getAttribute("nodeList").equals("name")) {
//				// result.log(
//				// Integer.toString(i)
//				// + ". translating nodeList to id: "
//				// + n.getAttribute("nodeList"), "Info");
//				// System.err.println(n.getAttribute("nodeList"));
//				// n.setAttribute("nodeList",
//				// translateNode(n.getAttribute("nodeList")));
//				// System.err.println(n.getAttribute("nodeList"));
//				// }
//
//				String data = urlEncodeAttributes(n.getAttributes(),
//						requests.getInputEncoding());
//				result.log(Integer.toString(i) + ". " + data, "Info");
//
//				InputStream in = request(url, data).getInputStream();
//				Object o = readJSON(in);
//
//				if (!n.getAttribute("name").equals("")) {
//					fileName = n.getAttribute("name");
//				} else {
//					throw new Exception("Query without name");
//
//				}
//			}
//		} catch (Exception e) {
//			result.log(e.toString(), "Error");
//		} finally {
//			endTime = System.currentTimeMillis();
//		}
//		appendResult(endTime - startTime);
//
//		result.append(MessageFormat.format(
//				"{0}/{1} requests on <{2}> for <{3}> in {4}ms.", i,
//				nodes.getLength(), url.toString(), base.toString(), endTime
//						- startTime));
//		return result.getXML();
//	}
//
//	public static String toHex(String ascii) {
//		String str = "";
//		for (int i = 0; i < ascii.length(); i++) {
//			char temp = ascii.charAt(i);
//			str += Integer.toHexString((int) temp);
//		}
//		return str;
//	}
//
//	// private static String getUsername() {
//	// Pattern p = Pattern.compile("\\w*.xml");
//	// Matcher m = p.matcher(globalRequest);
//	// if (m.find()) {
//	// return m.group();
//	// }
//	// return null;
//	// }
//
//	private static void appendResult(long time) {
//		Pattern p = Pattern.compile("GWA\\d{5}");
//		Matcher m = p.matcher(username);
//		String uid = "";
//		if (m.find())
//			uid = m.group();
//		else
//			uid = Long.toString(System.currentTimeMillis());
//
//		System.out.println("*****USER\n" + uid);
//
//		PrintWriter out = null;
//		boolean newDir = new File(folderName).mkdir();
//		if (!newDir) {
//			System.err.println("no new folder");
//		}
//		try {
//			out = new PrintWriter(new BufferedWriter(new FileWriter(folderName
//					+ "jsonStoreResult.txt", true)));
//			out.println("{testUser:'" + uid + "',runTime:" + time + "},");
//			out.println(baselineData);
//		} catch (IOException e) {
//			// do nothing
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//	}
//
//	public static String translateNode(String nodeList) {
//		StringBuilder output = new StringBuilder();
//		for (int i = 0; i < nodeList.length(); i += 2) {
//			String str = nodeList.substring(i, i + 2);
//			output.append((char) Integer.parseInt(str, 16));
//		}
//		// System.err.println(output.toString());
//
//		return output.toString();
//	}
//
//	public static void connect(String url, String... args)
//			throws MalformedURLException, IOException {
//
//		CookieHandler.setDefault(new CookieManager(null,
//				CookiePolicy.ACCEPT_ALL));
//
//		if (args != null && args.length > 0) {
//			// uid = (args[0] != null && !args[0].isEmpty())? args[0] : "admin";
//			// pwd = (args[1] != null && !args[1].isEmpty())? args[1] :
//			// "password";
//			// language = (args[2] != null && !args[2].isEmpty())? args[2] :
//			// "en";
//			baseLineFolder = (args[0] != null && !args[0].isEmpty()) ? args[0]
//					: "C:\\temp\\";
//			requestFile = (args[1] != null && !args[1].isEmpty()) ? args[1]
//					: "c:/temp/request.xml";
//			resultFile = (args[2] != null && !args[2].isEmpty()) ? args[2]
//					: "c:/temp/result.xml";
//		} else {
//			// uid = "admin";
//			// pwd = "password";
//			// language = "en";
//			baseLineFolder = "c:/temp";
//			requestFile = "c:/temp/request.xml";
//			resultFile = "c:/temp/result.xml";
//		}
//		// manual login
//		// String data = "cmd=mplus.ediscovery.login&caseid=test&ssid=test&uid="
//		// + uid + "&pwd=" + pwd + "&language=" + language;
//		// InputStream in = request(new URL(url), data).getInputStream();
//
//	}
//
//	public static void Debug(String txt) {
//		Date now = new Date();
//		try {
//			FileWriter writer = new FileWriter("C:\\dpvt.txt", true);
//			writer.write(now + " DPVTRequesterBaseLine.java " + txt + "\r\n");
//			writer.close();
//		} catch (Exception e) {
//
//		}
//	}
//
//	public void service(String url, String... args) {
//		DelOldDebug();
//		Debug("service");
//		try {
//			Debug("before connect");
//			connect(url, args);
//			Debug("before baseline");
//			baseline(args[0], url, args[1], args[2]);
//			Debug("after baseline");
//		} catch (IOException e) {
//			System.out.println("Connection problems: " + e.getMessage());
//			writeErrorToResultXml(url, e);
//		} catch (Exception e) {
//			System.out.println("Problem while running baseline: "
//					+ e.getMessage());
//			e.printStackTrace();
//			writeOtherErrorToResultXml(e);
//		}
//	}
//
//	private void DelOldDebug() {
//		File f = new File("C:\\dpvt.txt");
//		if (f.exists()) {
//			f.delete();
//		}
//	}
//
//	private void writeErrorToResultXml(String url, IOException e) {
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
//				+ "<ModuleResult>\n"
//				+ "<ReturnValue CODE=\"-1\">"
//				+ "Connection problem to "
//				+ url
//				+ " :"
//				+ e.getMessage()
//				+ "</ReturnValue>\n" + "</ModuleResult>";
//		try {
//			FileWriter writer = new FileWriter(resultFile);
//			writer.write(xml);
//			writer.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	private void writeOtherErrorToResultXml(Exception e) {
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
//				+ "<ModuleResult>\n"
//				+ "<ReturnValue CODE=\"-1\">"
//				+ "Problem with baseline:"
//				+ e.getMessage()
//				+ "</ReturnValue>\n" + "</ModuleResult>";
//		try {
//			FileWriter writer = new FileWriter(resultFile
//					+ System.currentTimeMillis());
//			writer.write(xml);
//			writer.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	private static String folderName = "";
//	private static String globalRequest = "";
//	private static String baselineData = "";
//	private static String username = "";
//
//	public static void main(String[] args) throws InterruptedException,
//			BrokenBarrierException {
//		String baseLineFolder = args[0];// "c:/temp";
//		String requestFile = args[1];// "c:/temp/request.xml";
//		String resultFile = args[2];// "c:/temp/result.xml";
//		String url = args[3];// "http://localhost:8888/remote"
//		globalRequest = args[1];
//		folderName = args[4];
//		baselineData = args[5]; // C:\\loadtest\\dpvtresults\\baseline.txt
//		username = args[6]; //
//
//		String params[] = { baseLineFolder, requestFile, resultFile };
//
//		DPVTRequesterBaseLine baseline = new DPVTRequesterBaseLine();
//		baseline.service(url, params);
//	}
//}


/// ORIGINAL
//package com.dpvt;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.io.Writer;
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.CookiePolicy;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.DecimalFormat;
//import java.text.MessageFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.TreeSet;
//import java.util.Vector;
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.TransformerFactoryConfigurationError;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import org.w3c.dom.DOMException;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import java.io.ByteArrayOutputStream;
//
///**
// * Essentially, HTTP POST a sequence of application/x-www-form-urlencoded data
// * to a URL and either record response (for manual validation) or assert it
// * still equals (from previously recorded output).
// * 
// * DPVTRequester.baseline("c:/out", "http://localhost:8888/remote",
// * "c:/requests.xml", "c:/output.xml") { DPVTRequester.compare("c:/out",
// * "c:/out2", "c:/output.xml") {
// * 
// * changes: - rename and make in default package for better naming calling
// * module. - pretty print json response output. - add param to write
// * ModuleResult output to file. - translate xml to list of
// * application/x-www-form-urlencoded. - call getfolderlist in background for
// * name to id translation. - compare against two generated output instead with
// * server.
// */
//
//public class DPVTRequesterBaseLine {
//
//	// private static String uid;
//	// private static String pwd;
//	// private static String language;
//	private static String baseLineFolder;
//	private static String requestFile;
//	private static String resultFile;
//
//	// Simple factory for creating a new blank XML result.
//	private static class XMLResult {
//		private Document doc;
//
//		XMLResult() throws ParserConfigurationException {
//			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
//					.newDocument();
//			doc.appendChild(doc.createElement("ModuleResult"));
//			doc.getDocumentElement().appendChild(
//					doc.createElement("ReturnValue"));
//			doc.getDocumentElement().appendChild(doc.createElement("Messages"));
//			((Element) doc.getDocumentElement().getFirstChild()).setAttribute(
//					"CODE", "0");
//		}
//
//		public Document getXML() {
//			return doc;
//		}
//
//		/**
//		 * Append message to the result.
//		 * 
//		 * @param message
//		 */
//		public void append(String message) {
//			doc.getDocumentElement().getFirstChild()
//					.appendChild(doc.createTextNode(message));
//		}
//
//		/**
//		 * Add a message node (within the second child) in doc, setting the code
//		 * attribute of first child to failure if kind is anything above info.
//		 */
//		public void log(String message, String kind) {
//
//			String intKind = "0";
//			if (kind.equals("Info")) {
//				intKind = "3";
//			} else if (kind.equals("Warning")) {
//				intKind = "1";
//			} else if (kind.equals("Error")) {
//				intKind = "-1";
//			} else if (kind.equals("Debug")) {
//				intKind = "2";
//			}
//
//			Element m = doc.createElement("Message");
//			m.setAttribute("KIND", intKind);
//			m.appendChild(doc.createTextNode(message));
//			doc.getDocumentElement().getChildNodes().item(1).appendChild(m);
//			if (!"Info".equals(kind)) {
//				((Element) doc.getDocumentElement().getFirstChild())
//						.setAttribute("CODE", "-1");
//			}
//		}
//	}
//
//	private static class FolderTextWithoutIdException extends Exception {
//		private static final long serialVersionUID = -6043441737663223872L;
//
//		public FolderTextWithoutIdException(String message) {
//			super(message);
//		}
//	}
//
//	private static void parseJson(String jsonString) throws Exception {
//		JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
//	}
//
//	/**
//	 * loads JSONObject from Reader, filtering out 'list[i].lastModified'. todo:
//	 * rename to clearly state that it does filtering
//	 * 
//	 * @throws IOException
//	 */
//	@SuppressWarnings({ "rawtypes", "unused" })
//	static Object readJSON(InputStream in) throws Exception {
//		Map<?, ?> obj = null;
//
//		// ***************
//		BufferedReader r = new BufferedReader(new InputStreamReader(in));
//		StringBuffer content = new StringBuffer();
//		String line;
//
//		while ((line = r.readLine()) != null) {
//			// writer.print(line);
//			// content.append(line);
//			System.out.println(line);
//			parseJson(line);
//		}
//		// ***************
//
//		try {
//			ObjectMapper m = new ObjectMapper();
//			obj = new ObjectMapper().readValue(in, Map.class);
//
//			List a = (List) obj.get("list");
//			if (a != null) {
//				for (Object item : a) {
//					try {
//						((Map<?, ?>) item).remove("lastModified");
//					} catch (ClassCastException e) {
//						// unexpected object, ignore the error and move on
//						System.err.println("Unexpected Obj");
//					}
//				}
//			}
//		} catch (JsonParseException e) {
//			// Debug(e.toString());
//			// e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// Debug(e.toString());
//			// e.printStackTrace();
//		} catch (IOException e) {
//			// user will then get null
//			// Debug(e.toString());
//			// e.printStackTrace();
//		}
//		return obj;
//	}
//
//	private static void writeJSON(Object value, Writer out)
//			throws JsonGenerationException, JsonMappingException, IOException {
//		ObjectMapper m = new ObjectMapper();
//		m.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
//		m.writeValue(out, value);
//	}
//
//	private static Document readXML(InputStream is) throws SAXException,
//			IOException, ParserConfigurationException {
//
//		Document document = null;
//		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
//				.newInstance();
//
//		try {
//			DocumentBuilder db = documentFactory.newDocumentBuilder();
//			document = db.parse(is);
//		} catch (Exception e) {
//			ByteArrayOutputStream data = new ByteArrayOutputStream();
//			PrintStream err = new PrintStream(data);
//			e.printStackTrace(err);
//			String stack = new String(data.toByteArray());
//			Debug("Error in readXML:" + e.getMessage() + "\r\n" + stack);
//			e.printStackTrace();
//		}
//
//		return document; // DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
//	}
//
//	// And still wondering why Document#toString was not there.
//	private static void writeXML(Document doc, BufferedOutputStream out)
//			throws TransformerFactoryConfigurationError, TransformerException {
//		Transformer t = TransformerFactory.newInstance().newTransformer();
//		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//		t.setOutputProperty(OutputKeys.METHOD, "xml");
//		t.setOutputProperty(OutputKeys.INDENT, "yes");
//		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//		// t.transform(new DOMSource(doc), new StreamResult(out));
//		OutputStreamWriter writer = null;
//		try {
//			t.transform(new DOMSource(doc), new StreamResult(
//					writer = new OutputStreamWriter(out, "UTF-8")));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				writer.flush();
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	// wow, java dont have builtin join, im in shock!
//	static String join(Iterable<? extends Object> collection, String separator) {
//		Iterator<? extends Object> it;
//		if (collection == null || (!(it = collection.iterator()).hasNext()))
//			return "";
//		StringBuilder s = new StringBuilder(String.valueOf(it.next()));
//		while (it.hasNext())
//			s.append(separator).append(it.next());
//		return s.toString();
//	}
//
//	private static String urlEncodeAttributes(NamedNodeMap nodeMap, String enc)
//			throws UnsupportedEncodingException, DOMException {
//		List<String> attrs = new ArrayList<String>();
//		for (int i = 0; i < nodeMap.getLength(); i++) {
//			Node n = nodeMap.item(i);
//			attrs.add(n.getNodeName() + "="
//					+ URLEncoder.encode(n.getNodeValue(), enc));
//		}
//		return join(attrs, "&"); // categoryList=%7B%7D&cmd=mplus.ediscovery.search&criteria=message&limit=2000&messageList=%5B%5D&nodeList=null&specific=&ssid=test&start=0&text=&type=message&wordlist=
//	}
//
//	private static HttpURLConnection request(URL url, String data)
//			throws IOException {
//		System.out.println(data);
//		HttpURLConnection cn = (HttpURLConnection) url.openConnection();
//		cn.setRequestMethod("POST");
//		cn.setDoOutput(true);
//		cn.setDoInput(true);
//
//		// cn.setRequestProperty("Content-Type",
//		// "application/x-www-form-urlencoded");
//		// cn.setRequestProperty("charset", "utf-8");
//		// cn.setUseCaches(false);
//		cn.connect();
//		DataOutputStream out = new DataOutputStream(cn.getOutputStream());
//		out.write(data.getBytes());
//		out.flush();
//		out.close();
//		return cn;
//	}
//
//	private static String getFolderId(List<String> paths, URL url,
//			Map<String, String> map) throws Exception,
//			FolderTextWithoutIdException {
//		List<String> p = new ArrayList<String>();
//		for (int i = 0; i < paths.size(); i++) {
//			p.add(getFolderId(paths.get(i), url, map));
//		}
//		return join(p, ",");
//	}
//
//	@SuppressWarnings("unchecked")
//	private static String getFolderId(String path, URL url,
//			Map<String, String> map) throws IOException,
//			FolderTextWithoutIdException, Exception {
//		if (path == null)
//			path = "";
//		else
//			path = path.trim().replaceFirst("^\\\\", "")
//					.replaceFirst("\\\\$", "");
//
//		if (map.containsKey(path)) {
//			return map.get(path);
//		} else {
//			int lio = path.lastIndexOf('\\');
//			String parentText;
//			String parentId;
//			if (lio > -1) {
//				parentText = path.substring(0, lio);
//				parentId = getFolderId(parentText, url, map);
//				if (parentId == null)
//					throw new FolderTextWithoutIdException(parentText);
//			} else {
//				parentText = "";
//				parentId = "~ROOT~";
//			}
//			String data = "cmd=mplus.ediscovery.getFolderList&start=0&limit=200&options=0&caseid=test&ssid=test&node="
//					+ parentId;
//			InputStream in = request(url, data).getInputStream();
//			Map<String, ?> o = (Map<String, ?>) readJSON(in);
//			ArrayList<Map<String, ?>> ol = (ArrayList<Map<String, ?>>) o
//					.get("nodes");
//			// be casefull here, we may throw NullPointerException, not very
//			// useful for debugging ...
//			for (Map<String, ?> e : ol) {
//				String text = (String) e.get("text");
//				String key = ("".equals(parentText)) ? text : parentText + "\\"
//						+ text;
//				map.put(key, (String) e.get("id"));
//			}
//			return map.get(path);
//		}
//	}
//
//	/**
//	 * String only arguments wrapper for baseline; saving XML result to file.
//	 * 
//	 * @param base
//	 *            File pathname to directory where responses will be written.
//	 * @param url
//	 *            URL spec of remote provider (usually http://localhost:8888)
//	 * @param requests
//	 *            File pathname to XML that contains the list of 'request'
//	 *            nodes.
//	 * @param result
//	 *            File pathname where "ModuleResult" is to be saved.
//	 * @throws Exception
//	 */
//	public static void baseline(String base, String url, String requests,
//			String result) throws Exception {
//		try {
//			Debug("baseLine baseDir=" + base + " url=" + url + " requests="
//					+ requests + ", result=" + result);
//			File baseDir = new File(base);
//			if (!baseDir.exists()) {
//				baseDir.mkdirs();
//			} else {
//				for (File in : baseDir.listFiles()) {
//					in.delete();
//				}
//
//			}
//			Debug("before doc");
//
//			GregorianCalendar date = (GregorianCalendar) GregorianCalendar
//					.getInstance();
//			date.add(date.DATE, 0);
//			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-SSS");
//			String name = format1.format(date.getTime());
//
//			Document doc = baseline(baseDir, new URL(url),
//					readXML(new FileInputStream(requests)));
//			writeXML(doc, new BufferedOutputStream(new FileOutputStream(result
//					+ name + "-" + (Thread.currentThread().getName()) + "("
//					+ System.currentTimeMillis() + ")" + ".xml")));
//
//		} catch (Exception e) {
//			ByteArrayOutputStream data = new ByteArrayOutputStream();
//			PrintStream err = new PrintStream(data);
//			e.printStackTrace(err);
//			String stack = new String(data.toByteArray());
//			Debug("exception in baseline:" + e.getMessage() + "\r\n" + stack);
//			e.printStackTrace();
//			throw (e);
//		}
//	}
//
//	/**
//	 * Make each 'requests' on 'url', recording each response in a separate file
//	 * in 'base' directory, saving log infos to 'result' XML.
//	 * 
//	 * @param base
//	 *            File pathname to directory where responses will be written.
//	 * @param url
//	 *            URL spec of remote provider (usually http://localhost:8888)
//	 * @param requests
//	 *            File pathname to XML that contains the list of 'request'
//	 *            nodes.
//	 * @return Document "ModuleResult" XML.
//	 * @throws ParserConfigurationException
//	 */
//	public static Document baseline(File base, URL url, Document requests)
//			throws ParserConfigurationException {
//		Debug("baseline(base=" + base + ", url=" + url + ", requests="
//				+ requests);
//		HashMap<String, String> foldersIdCache = new HashMap<String, String>();
//		NodeList nodes = requests.getElementsByTagName("request");
//		XMLResult result = new XMLResult();
//		String fileName = null;
//		;
//
//		long startTime = System.currentTimeMillis(), endTime;
//		int i = 0;
//
//		try {
//			for (; i < nodes.getLength(); i++) {
//
//				// TODO set username here from the argument
//
//				Element n = (Element) nodes.item(i);
//				// System.err.println(n.getAttribute("uid"));
//				// set uid
//				n.setAttribute("uid", username);
//				System.err.println(n.getAttribute("uid"));
//				// System.err.println(n.getAttribute("nodeList"));
//
//				// set and convert nodeList
//				// assume that location is always the same
//				String nodeList = "{\"afn\":\"\",\"rp\":\"/\",\"loc\":\"1257274138906\"}";
//				Pattern p = Pattern.compile("\\d{5}");
//				Matcher m = p.matcher(username);
//				System.err.println(m.find());
//				// System.err.println(m.group());
//				String userID = m.group();
//				nodeList = "{\"afn\":\"GWA"
//						+ userID
//						+ " MA"
//						+ userID
//						+ "@GWA"
//						+ userID
//						+ ".PO02.DOM02\",\"rp\":\"/\",\"loc\":\"1257274138906\"}";
//				// System.out.println(nodeList);
//				n.setAttribute("nodeList", toHex(nodeList));
//				// System.err.println(n.getAttribute("nodeList"));
//
//				// attributes set on document element apply to all requests
//				NamedNodeMap globalAttrs = requests.getDocumentElement()
//						.getAttributes();
//				for (int j = 0; j < globalAttrs.getLength(); j++) {
//					Node at = globalAttrs.item(j);
//					if ("".equals(n.getAttribute(at.getNodeName()))) {
//						n.setAttribute(at.getNodeName(), at.getNodeValue());
//					}
//				}
//
//				// nodeList gets translated from human format to id (requests
//				// are made in the background)
//				// if (!n.getAttribute("nodeList").equals("")
//				// && !n.getAttribute("nodeList").equals("name")) {
//				// result.log(
//				// Integer.toString(i)
//				// + ". translating nodeList to id: "
//				// + n.getAttribute("nodeList"), "Info");
//				// System.err.println(n.getAttribute("nodeList"));
//				// n.setAttribute("nodeList",
//				// translateNode(n.getAttribute("nodeList")));
//				// System.err.println(n.getAttribute("nodeList"));
//				// }
//
//				String data = urlEncodeAttributes(n.getAttributes(),
//						requests.getInputEncoding());
//				result.log(Integer.toString(i) + ". " + data, "Info");
//
//				InputStream in = request(url, data).getInputStream();
//				Object o = readJSON(in);
//
//				if (!n.getAttribute("name").equals("")) {
//					fileName = n.getAttribute("name");
//				} else {
//					throw new Exception("Query without name");
//
//				}
//			}
//		} catch (Exception e) {
//			result.log(e.toString(), "Error");
//		} finally {
//			endTime = System.currentTimeMillis();
//		}
//		appendResult(endTime - startTime);
//
//		result.append(MessageFormat.format(
//				"{0}/{1} requests on <{2}> for <{3}> in {4}ms.", i,
//				nodes.getLength(), url.toString(), base.toString(), endTime
//						- startTime));
//		return result.getXML();
//	}
//
//	public static String toHex(String ascii) {
//		String str = "";
//		for (int i = 0; i < ascii.length(); i++) {
//			char temp = ascii.charAt(i);
//			str += Integer.toHexString((int) temp);
//		}
//		return str;
//	}
//
//	// private static String getUsername() {
//	// Pattern p = Pattern.compile("\\w*.xml");
//	// Matcher m = p.matcher(globalRequest);
//	// if (m.find()) {
//	// return m.group();
//	// }
//	// return null;
//	// }
//
//	private static void appendResult(long time) {
//		Pattern p = Pattern.compile("GWA\\d{5}");
//		Matcher m = p.matcher(username);
//		String uid = "";
//		if (m.find())
//			uid = m.group();
//		else
//			uid = Long.toString(System.currentTimeMillis());
//
//		System.out.println("*****USER\n" + uid);
//
//		PrintWriter out = null;
//		boolean newDir = new File(folderName).mkdir();
//		if (!newDir) {
//			System.err.println("no new folder");
//		}
//		try {
//			out = new PrintWriter(new BufferedWriter(new FileWriter(folderName
//					+ "jsonStoreResult.txt", true)));
//			out.println("{testUser:'" + uid + "',runTime:" + time + "},");
//			out.println(baselineData);
//		} catch (IOException e) {
//			// do nothing
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//	}
//
//	public static String translateNode(String nodeList) {
//		StringBuilder output = new StringBuilder();
//		for (int i = 0; i < nodeList.length(); i += 2) {
//			String str = nodeList.substring(i, i + 2);
//			output.append((char) Integer.parseInt(str, 16));
//		}
//		// System.err.println(output.toString());
//
//		return output.toString();
//	}
//
//	public static void connect(String url, String... args)
//			throws MalformedURLException, IOException {
//
//		CookieHandler.setDefault(new CookieManager(null,
//				CookiePolicy.ACCEPT_ALL));
//
//		if (args != null && args.length > 0) {
//			// uid = (args[0] != null && !args[0].isEmpty())? args[0] : "admin";
//			// pwd = (args[1] != null && !args[1].isEmpty())? args[1] :
//			// "password";
//			// language = (args[2] != null && !args[2].isEmpty())? args[2] :
//			// "en";
//			baseLineFolder = (args[0] != null && !args[0].isEmpty()) ? args[0]
//					: "C:\\temp\\";
//			requestFile = (args[1] != null && !args[1].isEmpty()) ? args[1]
//					: "c:/temp/request.xml";
//			resultFile = (args[2] != null && !args[2].isEmpty()) ? args[2]
//					: "c:/temp/result.xml";
//		} else {
//			// uid = "admin";
//			// pwd = "password";
//			// language = "en";
//			baseLineFolder = "c:/temp";
//			requestFile = "c:/temp/request.xml";
//			resultFile = "c:/temp/result.xml";
//		}
//		// manual login
//		// String data = "cmd=mplus.ediscovery.login&caseid=test&ssid=test&uid="
//		// + uid + "&pwd=" + pwd + "&language=" + language;
//		// InputStream in = request(new URL(url), data).getInputStream();
//
//	}
//
//	public static void Debug(String txt) {
//		Date now = new Date();
//		try {
//			FileWriter writer = new FileWriter("C:\\dpvt.txt", true);
//			writer.write(now + " DPVTRequesterBaseLine.java " + txt + "\r\n");
//			writer.close();
//		} catch (Exception e) {
//
//		}
//	}
//
//	public void service(String url, String... args) {
//		DelOldDebug();
//		Debug("service");
//		try {
//			Debug("before connect");
//			connect(url, args);
//			Debug("before baseline");
//			baseline(args[0], url, args[1], args[2]);
//			Debug("after baseline");
//		} catch (IOException e) {
//			System.out.println("Connection problems: " + e.getMessage());
//			writeErrorToResultXml(url, e);
//		} catch (Exception e) {
//			System.out.println("Problem while running baseline: "
//					+ e.getMessage());
//			e.printStackTrace();
//			writeOtherErrorToResultXml(e);
//		}
//	}
//
//	private void DelOldDebug() {
//		File f = new File("C:\\dpvt.txt");
//		if (f.exists()) {
//			f.delete();
//		}
//	}
//
//	private void writeErrorToResultXml(String url, IOException e) {
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
//				+ "<ModuleResult>\n"
//				+ "<ReturnValue CODE=\"-1\">"
//				+ "Connection problem to "
//				+ url
//				+ " :"
//				+ e.getMessage()
//				+ "</ReturnValue>\n" + "</ModuleResult>";
//		try {
//			FileWriter writer = new FileWriter(resultFile);
//			writer.write(xml);
//			writer.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	private void writeOtherErrorToResultXml(Exception e) {
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
//				+ "<ModuleResult>\n"
//				+ "<ReturnValue CODE=\"-1\">"
//				+ "Problem with baseline:"
//				+ e.getMessage()
//				+ "</ReturnValue>\n" + "</ModuleResult>";
//		try {
//			FileWriter writer = new FileWriter(resultFile
//					+ System.currentTimeMillis());
//			writer.write(xml);
//			writer.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	private static String folderName = "";
//	private static String globalRequest = "";
//	private static String baselineData = "";
//	private static String username = "";
//
//	public static void main(String[] args) throws InterruptedException,
//			BrokenBarrierException {
//		String baseLineFolder = args[0];// "c:/temp";
//		String requestFile = args[1];// "c:/temp/request.xml";
//		String resultFile = args[2];// "c:/temp/result.xml";
//		String url = args[3];// "http://localhost:8888/remote"
//		globalRequest = args[1];
//		folderName = args[4];
//		baselineData = args[5]; // C:\\loadtest\\dpvtresults\\baseline.txt
//		username = args[6]; //
//
//		String params[] = { baseLineFolder, requestFile, resultFile };
//
//		DPVTRequesterBaseLine baseline = new DPVTRequesterBaseLine();
//		baseline.service(url, params);
//	}
//}  
