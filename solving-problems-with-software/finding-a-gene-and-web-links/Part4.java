
/**
 * Write a description of Part4 here.
 * 
 * @author iveej 
 * @version 11/08/2022
 */

import edu.duke.*;
import java.util.*;  

public class Part4 {
    
    public static void getURLS() {
        URLResource webpage = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        
        for(String line : webpage.lines()) {
            String lowerCasedLine = line.toLowerCase();
            int strPos = lowerCasedLine.indexOf("youtube.com");
            
            if(lowerCasedLine.contains("youtube.com")) {
                int startPos = lowerCasedLine.lastIndexOf("\"", strPos);
                int endPos = lowerCasedLine.indexOf("\"", strPos + 1);
                String youtubeLink = line.substring(startPos + 1, endPos);
                System.out.println(youtubeLink);
            }
            
        }
        
    }
    
    
}
