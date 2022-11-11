
/**
 * Write a description of Part5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part5 {
    
    public void getURLS() {
       URLResource file = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
       for (String item : file.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
       	       int beg = itemLower.indexOf("\"",pos-9);
       	       int end = itemLower.indexOf("\"", pos+1);
       	       System.out.println(itemLower.substring(beg+1,end));
           }
   	}
    }
    
}
