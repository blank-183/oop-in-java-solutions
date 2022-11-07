
/**
 * Write a description of Part3 here.
 * 
 * @author iveej
 * @version 11/07/2022
 */
public class Part3 {

    public static boolean twoOccurences(String stringa, String stringb) {
        int index = 0;
        int strPos = stringb.indexOf(stringa);
        
        if(strPos == -1) {
            return false;
        }
        int count = 1;
        
        while(strPos < stringb.length()){
            strPos = stringb.indexOf(stringa, strPos + stringa.length());
            count += 1;
        }
        
        if(count < 2) {
            return false;
        }
        
        System.out.println("count: " + count);
        return true;
    }
    
    public static testing()
    
}
