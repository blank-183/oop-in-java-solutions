
/**
 * Write a description of Part1 here.
 * 
 * @author iveej
 * @version 11/07/2022
 */
public class Part1 {
    
    public static String findSimpleGene(String dna) {
        int startCodonPos = dna.indexOf("ATG");
        if(startCodonPos == - 1) {
            return "";
        }
        
        int stopCodonPos = dna.indexOf("TAA", startCodonPos + 3);
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
        System.out.println("Test 1: " + findSimpleGene("CTAGTACCCTAA")); // no atg
        System.out.println("Test 2: " + findSimpleGene("ACATGCTAGTACCC")); // no taa
        System.out.println("Test 3: " + findSimpleGene("ACCTAGTACCCTCATA")); // no atg and taa
        System.out.println("Test 4: " + findSimpleGene("CCATGACATGCTAGTACCCCTAA")); // correct
        System.out.println("Test 5: " + findSimpleGene("ACATGATGCTAGTACTAACC")); // w atg and taa
        
        System.out.println("Answer: " + findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC"));
    }
    
}
