package utilities;
import java.util.ArrayList;
import java.util.HashMap;

import resources.utilities.NetmailFinderHelper;
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
public class NetmailFinder extends NetmailFinderHelper
{
	/**
	 * Script Name   : <b>Finder</b>
	 * Generated     : <b>Aug 12, 2014 1:42:17 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/08/12
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	
	private class MyArrayList<T> extends ArrayList<T> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(T o){		
			if(	!((TestObject)o).getProperty(".offsetHeight").toString().contentEquals("0") |
					!((TestObject)o).getProperty(".offsetWidth").toString().contentEquals("0")  |
					!((TestObject)o).getProperty(".offsetTop").toString().contentEquals("0")){
				

					
					boolean foundDup = false;
					for(T addedO : this){
						TestObject addedTestO = (TestObject) addedO;
						String tag1 = ((TestObject)o).getProperty(".tag").toString().trim();
						String tag2 = addedTestO.getProperty(".tag").toString().trim();
						
						String contentText1 = ((TestObject)o).getProperty(".contentText").toString().trim();
						String contentText2 = addedTestO.getProperty(".contentText").toString().trim();
						
						
						String value1 = "";
						String value2 = "";
						try{
							value1 = ((TestObject)o).getProperty(".value").toString().trim();
							value2 = addedTestO.getProperty(".value").toString().trim();
						}catch(PropertyNotFoundException e){
							//do nothing property doesn't exists
						}
						
						String class1 = ((TestObject)o).getProperty("class").toString().trim();
						String class2 = addedTestO.getProperty("class").toString().trim();
						
						if(value1.contentEquals(value2) && tag1.contentEquals(tag2) && contentText1.contentEquals(contentText2) && class1.contentEquals(class2)){
//							System.out.print("FOUND==");
//							System.out.println(tag1+" "+contentText1);
							foundDup = true;
						}
					}
					if(!foundDup){
						return super.add((T) o);
					}
			}
			return false;	
		}
	}
	
	public TestObject[] findAllTextObjects(GuiTestObject container){
		MyArrayList<TestObject> noChildren = new MyArrayList<>();
		MyArrayList<TestObject> hasChildren = new MyArrayList<TestObject>();
		
		RegularExpression onlyLetterAndSpaces = new RegularExpression("[\\p{L}\\s]+", false);
		
		
		TestObject[] objects;
		
		//Not Mappable ".value" property are ussually object that can't contain childs
		objects = container.find(atDescendant(".value", onlyLetterAndSpaces), false);
		for(TestObject o : objects){
			noChildren.add(o);
		}
			
		//Mappable ".value" property are ussually object that can't contain childs
		objects = container.find(atDescendant(".value", onlyLetterAndSpaces), true);	
		for(TestObject o : objects){
			noChildren.add(o);
		}
			
		//Not Mappable
		objects = container.find(atChild(".contentText",  onlyLetterAndSpaces), false);
		for(TestObject o : objects){
			hasChildren.add(o);
		}
		
		//Mappable
		objects = container.find(atChild(".contentText",  onlyLetterAndSpaces), true);	
		for(TestObject o : objects){
			hasChildren.add(o);
		}

		//FILTER OUT PARENT objects that takes the child object text
		while(!hasChildren.isEmpty()){
			TestObject[] parents = new TestObject[hasChildren.size()];
			hasChildren.toArray(parents);
			for(TestObject object: parents){
				TestObject[] a = object.find(atChild(".contentText", onlyLetterAndSpaces), false);
				TestObject[] b =  object.find(atChild(".contentText", onlyLetterAndSpaces), true);
				
				MyArrayList<TestObject> childs = new MyArrayList<>();
				for(TestObject o : a){
					childs.add(o);
				}
				for(TestObject o : b){
					childs.add(o);
				}
				
				if(childs.size()>0){		
					//Object has children that contain text
					String contentText = object.getProperty(".contentText").toString();
					contentText = contentText.replaceAll("\\r", "");
					contentText = contentText.replaceAll("\\n", "");
					
					for(TestObject childOfObject : childs){
						//Make sure that all string in parent object is within child objects.
						String childContentText = childOfObject.getProperty(".contentText").toString();
						childContentText = childContentText.replaceAll("\\r", "");
						childContentText = childContentText.replaceAll("\\n", "");
						contentText = contentText.replace(childContentText, "");
					}
					
					if(!contentText.replaceAll(" ", "").isEmpty()){
						//If has text that child doesn't have then added to noChildren array.
						noChildren.add(object);
					}
					
					for(TestObject o : childs){
						hasChildren.add(o);
					}	
					
				}else{
					//Object has no children aka the LEAF and contains text
					noChildren.add(object);

				}
				hasChildren.remove(object);
			}
		}
		
		//lastFilter remove empty string objects
		TestObject[] finalArray = new TestObject[noChildren.size()];
		noChildren.toArray(finalArray);
		for(TestObject o : finalArray){
			String contentText = o.getProperty(".contentText").toString();
			contentText = contentText.replaceAll("\\r", "");
			contentText = contentText.replaceAll("\\n", "");
			if(contentText.replaceAll(" ", "").isEmpty()){
				try{
					//If content text is empty check .value
					((TestObject)o).getProperty(".value").toString().trim();
				}catch(PropertyNotFoundException e){
					boolean deleted = noChildren.remove(o);
					assert deleted == true;
				}
			}
		}
		System.out.println("B"+noChildren.size());
		
		finalArray = new TestObject[noChildren.size()];
		return noChildren.toArray(finalArray);
	}
	
	
	
	//This version uses hashmap which should be faster for this scenario
	public TestObject[] findAllTextObjects2(GuiTestObject container){
		
		HashMap<String, TestObject> leaf = new HashMap<String, TestObject>();
		HashMap<String, TestObject> parent = new HashMap<String, TestObject>();
		
		RegularExpression onlyLetterAndSpaces = new RegularExpression("[\\p{L}\\s]+", false);
		
		
		TestObject[] objects;
		
		//Not Mappable ".value" property are ussually object that can't contain childs
		objects = container.find(atDescendant(".value", onlyLetterAndSpaces), false);
		for(TestObject o : objects){
			addToMap(leaf, o);
		}
			
		//Mappable ".value" property are ussually object that can't contain childs
		objects = container.find(atDescendant(".value", onlyLetterAndSpaces), true);	
		for(TestObject o : objects){
			addToMap(leaf, o);
		}
			
		//Not Mappable
		objects = container.find(atChild(".contentText",  onlyLetterAndSpaces), false);
		for(TestObject o : objects){
			addToMap(parent, o);
		}
		
		//Mappable
		objects = container.find(atChild(".contentText",  onlyLetterAndSpaces), true);	
		for(TestObject o : objects){
			addToMap(parent, o);
		}

		//FILTER OUT PARENT objects that takes the child object text
		while(!parent.isEmpty()){
			TestObject[] parents = new TestObject[parent.size()];
			parent.values().toArray(parents);
			for(TestObject object: parents){
				TestObject[] a = object.find(atChild(".contentText", onlyLetterAndSpaces), false);
				TestObject[] b =  object.find(atChild(".contentText", onlyLetterAndSpaces), true);
				
				HashMap<String, TestObject> childs = new HashMap<>();
				for(TestObject o : a){
					addToMap(childs,o);
				}
				for(TestObject o : b){
					addToMap(childs,o);
				}
				
				if(childs.size()>0){		
					//Object has children that contain text
					String contentText = object.getProperty(".contentText").toString();
					contentText = contentText.replaceAll("\\r", "");
					contentText = contentText.replaceAll("\\n", "");
					
					TestObject[] childObjects = new TestObject[childs.size()];
					childs.values().toArray(childObjects);
					for(TestObject childOfObject : childObjects){
						//Make sure that all string in parent object is within child objects.
						String childContentText = childOfObject.getProperty(".contentText").toString();
						childContentText = childContentText.replaceAll("\\r", "");
						childContentText = childContentText.replaceAll("\\n", "");
						contentText = contentText.replace(childContentText, "");
					}
					
					if(!contentText.replaceAll(" ", "").isEmpty()){
						//If has text that child doesn't have then added to noChildren array.
						addToMap(leaf,object);
					}
					
					for(TestObject o : childObjects){
						addToMap(parent, o);
					}	
					
				}else{
					//Object has no children aka the LEAF and contains text
					addToMap(leaf, object);

				}
				System.out.println(removeFromMap(parent,object));
			}
		}
		
		//lastFilter remove empty string objects
		TestObject[] finalArray = new TestObject[leaf.size()];
		leaf.values().toArray(finalArray);
		for(TestObject o : finalArray){
			String contentText = o.getProperty(".contentText").toString();
			contentText = contentText.replaceAll("\\r", "");
			contentText = contentText.replaceAll("\\n", "");
			if(contentText.replaceAll(" ", "").isEmpty()){
				try{
					//If content text is empty check .value
					o.getProperty(".value").toString().trim();
				}catch(PropertyNotFoundException e){
					Boolean deleted = removeFromMap(leaf, o);
					assert deleted != false;
				}
			}
		}
		System.out.println("B"+leaf.size());
		
		finalArray = new TestObject[leaf.size()];
		return leaf.values().toArray(finalArray);
	}
	
	public void highlight(TestObject[] testObjects){
		int count = 0;
		for(TestObject o : testObjects){
			Highlighter r = utilities.HelperClass.getHighlighter(o);
			r.show();
			sleep(1);
			count++;
		}
	}
	
	
	public void addToMap(HashMap<String, TestObject> map, TestObject o){
		if(	!((TestObject)o).getProperty(".offsetHeight").toString().contentEquals("0") |
				!((TestObject)o).getProperty(".offsetWidth").toString().contentEquals("0")  |
				!((TestObject)o).getProperty(".offsetTop").toString().contentEquals("0")){
			
				String tag1 = ((TestObject)o).getProperty(".tag").toString().trim();			
				String contentText1 = ((TestObject)o).getProperty(".contentText").toString().trim();
				
				String value1 = "";
				try{
					value1 = ((TestObject)o).getProperty(".value").toString().trim();
				}catch(PropertyNotFoundException e){
					//do nothing property doesn't exists
				}		
				String class1 = ((TestObject)o).getProperty("class").toString().trim();
				String key = tag1+contentText1+value1+class1;
				if(!map.containsKey(key)){
					map.put(key, o);
				}
		}
	}
	
	private boolean removeFromMap(HashMap<String, TestObject> map, TestObject o ){
		String tag1 = ((TestObject)o).getProperty(".tag").toString().trim();			
		String contentText1 = ((TestObject)o).getProperty(".contentText").toString().trim();
		
		String value1 = "";
		try{
			value1 = ((TestObject)o).getProperty(".value").toString().trim();
		}catch(PropertyNotFoundException e){
			//do nothing property doesn't exists
		}		
		String class1 = ((TestObject)o).getProperty("class").toString().trim();
		String key = tag1+contentText1+value1+class1;
		if(map.containsKey(key)){
			return map.remove(key) != null;
		}
		return false;
	}
}

