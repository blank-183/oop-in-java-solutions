
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public int howMany(String stringA, String stringB) {
        int count = 0;
        int startIndex = 0;
        int currIndex = stringB.indexOf(stringA);
        
        while(currIndex != -1) {
            startIndex = currIndex + stringA.length();
            count += 1;
            currIndex = stringB.indexOf(stringA, startIndex);
        }
        
        return count;
    }
    
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
        System.out.println(howMany("a", "banana"));
        System.out.println(howMany("AG", "AGAAGTGATGAAGTGAGAGGATTGAAG"));
        System.out.println(howMany("TGA", "AGAGATAATAGTAGAAATTT"));
    }
    
}
