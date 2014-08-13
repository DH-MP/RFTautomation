package utilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private void addToMap(LinkedHashMap<String, TestObject> map, TestObject o){
		//Visibility Check: add only visible objects
		if(	!((TestObject)o).getProperty(".offsetHeight").toString().contentEquals("0") |
				!((TestObject)o).getProperty(".offsetWidth").toString().contentEquals("0")  |
				!((TestObject)o).getProperty(".offsetTop").toString().contentEquals("0")){
			
				String tag1 = ((TestObject)o).getProperty(".tag").toString().trim();			
				String contentText1 = ((TestObject)o).getProperty(".contentText").toString().trim();
				
				String value1 = "";
				try{
					value1 = ((TestObject)o).getProperty(".value").toString().trim();
					
					//Object(s) are not added
					String rftClass = o.getProperty(".class").toString();
					if(rftClass.contentEquals("Html.INPUT.checkbox")){	
						return;
					}
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
	


	private boolean removeFromMap(LinkedHashMap<String, TestObject> map, TestObject o ){
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

