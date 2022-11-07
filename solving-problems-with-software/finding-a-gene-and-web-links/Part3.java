
/**
 * Write a description of Part3 here.
 * 
 * @author iveej
 * @version 11/07/2022
 */
public class Part3 {

    public static boolean twoOccurences(String stringA, String stringB) {
        int index = 0;
        int strPos = stringB.indexOf(stringA);
        
        if(strPos == -1) {
            return false;
        }
        index = strPos + stringA.length();
        int count = 1;
        
        while(index < stringB.length()){
            strPos = stringB.indexOf(stringA, index);
            
            if(strPos == -1) {
                break;
            }
            
            index = strPos + stringA.length();
            count += 1;
        }
        
        if(count < 2) {
            return false;
        }
        
        System.out.println("count: " + count);
        System.out.println("string a: " + stringA + " string b: " + stringB);
        return true;
    }
    
    public static String lastPart(String stringA , String stringB) {
        int strPos = stringB.indexOf(stringA);
        
        if(strPos == -1) {
            return stringB;
        }
        
        return stringB.substring(strPos + stringA.length());
    }
    
    public static void testing() {
        System.out.println(twoOccurences("i", "ivee"));
        System.out.println(twoOccurences("e", "ivee"));
        System.out.println(twoOccurences("by", "A story by Abby Long"));
        System.out.println(twoOccurences("a", "banana"));
        System.out.println(twoOccurences("atg", "ctgtatgta"));
        
        System.out.println("LAST PART");
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
    
}
