package utilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

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
	
	//This version uses linkhashmap which should be faster for this scenario
	//This version uses LINKEDhashmap which keep the order of object inserted and not based on key
	public TestObject[] findAllTextObjects(GuiTestObject container){
		
		LinkedHashMap<String, TestObject> leaf = new LinkedHashMap<String, TestObject>();
		LinkedHashMap<String, TestObject> parent = new LinkedHashMap<String, TestObject>();
		
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
		objects = container.find(atChild(".text",  onlyLetterAndSpaces), false);
		for(TestObject o : objects){
			addToMap(parent, o);
		}
		
		//Mappable
		objects = container.find(atChild(".text",  onlyLetterAndSpaces), true);	
		for(TestObject o : objects){
			addToMap(parent, o);
		}
	
		//FILTER OUT PARENT objects that takes the child object text
		while(!parent.isEmpty()){
			TestObject[] parents = new TestObject[parent.size()];
			parent.values().toArray(parents);
			for(TestObject object: parents){
				TestObject[] a = object.find(atChild(".text", onlyLetterAndSpaces), false);
				TestObject[] b =  object.find(atChild(".text", onlyLetterAndSpaces), true);

				//Skip visibility because some hidden objects that contains text.
				// These hidden objects are needed to determine if
				// parent contains text that child objects does not have
				LinkedHashMap<String, TestObject> childs = new LinkedHashMap<>();
				for(TestObject o : a){
					addToMapSkipVisibilityCheck(childs,o);
				}
				for(TestObject o : b){
					addToMapSkipVisibilityCheck(childs,o);
				}
				
				if(childs.size()>0){		
					//Object has children that contain text
					String contentText = object.getProperty(".text").toString();
					contentText = contentText.replaceAll("\\r|\\t|\\n", "");
					
					TestObject[] childObjects = new TestObject[childs.size()];
					childs.values().toArray(childObjects);
					for(TestObject childOfObject : childObjects){
						//Make sure that all string in parent object is within child objects.
						String childContentText = childOfObject.getProperty(".text").toString();
						childContentText = contentText.replaceAll("\\r|\\t|\\n", "");
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
			String contentText = o.getProperty(".text").toString();
			contentText = contentText.replaceAll("\\r|\\t|\\n", "");
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
	
	
	private Window window(int count){
		//http://stackoverflow.com/questions/21604762/drawing-over-screen-in-java
		
		final int count2 = count;
		Window w = new Window(null)
		{
		  @Override
		  public void paint(Graphics g)
		  {
		    final Font font = new Font("sansserif", Font.BOLD, 13);
		    g.setFont(font);
		    g.setColor(Color.BLACK);
		    final String message = "["+count2+"]";
		    g.drawString(message, getWidth()/2, (getHeight()/2) + (getHeight()/5));
		  }
		  @Override
		  public void update(Graphics g)
		  {
		    paint(g);
		  }
		};
		return w;
	}
	
	public void highlight(TestObject[] testObjects){
		int count = 1;
		for(TestObject o : testObjects){
			Rectangle h = ((GuiTestObject)o).getClippedScreenRectangle();
			Highlighter r = Highlighter.create(h);

			sleep(1);
			
			//Lettering
			Window w = window(count);
			w.setAlwaysOnTop(true);
			w.setBounds(h);
			w.setBackground(new Color(333, true));
			
			r.show();
			w.setVisible(true);	
			count++;
		}
	}
	
	private void addToMapSkipVisibilityCheck(LinkedHashMap<String, TestObject> map, TestObject o){	
		//Makes sure same object are not added
		String key = getKey(o);
		if(key !=null && !map.containsKey(key)){
			map.put(key, o);
		}
		
	}
	
	private void addToMap(LinkedHashMap<String, TestObject> map, TestObject o){
		//Visibility Check: add only visible objects
		Hashtable prop = o.getProperties();
		
		if(prop.containsKey(".offsetHeight")){
			if(	!prop.get(".offsetHeight").toString().contentEquals("0") |
					!prop.get(".offsetWidth").toString().contentEquals("0")  |
					!prop.get(".offsetTop").toString().contentEquals("0")){
					
				addToMapSkipVisibilityCheck(map, o);
			}
		}else{
			//This object does not have those property
			Rectangle rect = ((GuiTestObject)o).getScreenRectangle();
			if( rect.x != 0 | rect.y != 0 | rect.height != 0 | rect.width !=0){	
				addToMapSkipVisibilityCheck(map, o);
			}
		}
	}
	


	private boolean removeFromMap(LinkedHashMap<String, TestObject> map, TestObject o ){
		String key = getKey(o);
		if(key !=null && map.containsKey(key)){
			return map.remove(key) != null;
		}
		return false;
	}
	
	private String getKey(TestObject o){
		Hashtable prop = o.getProperties();		
		
		String rftClass1 = prop.get(".class").toString().trim();			
		String contentText1 = ((TestObject)o).getProperty(".text").toString().trim();
		
		String value1 = "";
		if(prop.containsKey(".value")){
			value1 = prop.get(".value").toString().trim();
		}	
		
		String class1 = "";
		if(prop.containsKey(".className")){
			class1 = prop.get(".className").toString().trim();
		}	
		
		//Special handling for INPUT objects that have SAME values
		String rftClass = prop.get(".class").toString();
		String allInput = "Html.INPUT.*";
		String name ="";
		String id ="";
		if(rftClass.matches(allInput)){	
			
			if(prop.containsKey("name")){
				name = prop.get("name").toString().trim();
				
				//Object(s) are not added
				if(prop.get(".class").toString().contentEquals("Html.INPUT.checkbox")){	
					return null;
				}
			}
		
			
			if(prop.containsKey(".id")){
				id = prop.get(".id").toString().trim();
			}	
		}
			
		String key = rftClass1+contentText1+value1+class1+name+id;
		return key;
	}
}

