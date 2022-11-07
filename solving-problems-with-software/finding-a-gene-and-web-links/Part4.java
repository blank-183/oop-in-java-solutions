
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
            
            if(line.contains("youtube.com")) {
                int startPos = line.indexOf("href=") + ("href=").length();
                int endPos = line.indexOf("\"", startPos + 1) + 1;
                String youtubeLink = line.substring(startPos, endPos);
                System.out.println(youtubeLink);
            }
            
        }
        
    }
    
}
