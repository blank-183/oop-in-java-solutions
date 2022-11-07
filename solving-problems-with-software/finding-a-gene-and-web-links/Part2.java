
/**
 * Write a description of Part2 here.
 * 
 * @author iveej
 * @version 11/07/2022
 */
public class Part2 {

    public static String findSimpleGene(String dna, String startCodon, String stopCodon) {
        if(dna.equals(dna.toUpperCase())) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }

        int startCodonPos = dna.indexOf(startCodon);
        if(startCodonPos == - 1) {
            return "";
        }
        
        int stopCodonPos = dna.indexOf(stopCodon, startCodonPos + 3);
        if(stopCodonPos == - 1) {
            return "";
        }
        
        String gene = dna.substring(startCodonPos, stopCodonPos + 3);
        if(gene.length() % 3 != 0 ) {
            return "";
        }
        
        return gene;
    }
    
    public static void testSimpleGene() {
        System.out.println("Test 1: " + findSimpleGene("CTAGTACCCTAA", "atg", "TAA")); // no atg
        System.out.println("Test 2: " + findSimpleGene("ACATGCTAGTACCC", "ATG", "taa")); // no taa
        System.out.println("Test 3: " + findSimpleGene("ACCTAGTACCCTCATA", "ATG", "TAA")); // no atg and taa
        System.out.println("Test 4: " + findSimpleGene("CCATGACATGCTAGTACCCCTAA", "atg", "TAA")); // correct
        System.out.println("Test 5: " + findSimpleGene("ACATGATGCTAGTACTAACC", "atg", "taa")); // w atg and taa
        System.out.println("Test 6: " + findSimpleGene("ATGGGTTAAGTC", "atg", "taa"));
        System.out.println("Test 7: " + findSimpleGene("gatgctataat", "atg", "taa"));
    }
    
}
