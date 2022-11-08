
/**
 * @author iveej
 */
public class Part1 {

    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        if(stopIndex == -1 || ((stopIndex - startIndex) % 3 != 0)) {
            return dna.length();
        }
        return stopIndex;
    }
    
    public static String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        
        if(startIndex == -1) {
            return "";
        }
        
        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(taaStopIndex, Math.min(tagStopIndex, tgaStopIndex));
        
        if(minIndex == dna.length()) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public static void printAllGenes(String dna) {
        int currIndex = dna.indexOf("ATG");
        
        if(currIndex == -1) {
            System.out.println("No gene found.");
        }
        
        String dnaStr = dna.substring(currIndex);
        
        while (true) {
            String gene = findGene(dnaStr);
            
            if(gene.isEmpty()) {
                break;
            }
            
            System.out.println(findGene(dnaStr));
            currIndex = currIndex + gene.length();
            dnaStr = dna.substring(currIndex);
        }
    }
    
    public static void testFindStopCodon() {
        System.out.println(findStopCodon("aaaATGbbbTAA", 3, "TAA"));
        System.out.println(findStopCodon("aaaATGbbTAAATTGA", 3, "TGA"));
    }
    
    public static void testFindGene() {
        // System.out.println(findGene("xxxTGATAA"));
        // System.out.println(findGene("aaATGxxxTGA"));
        // System.out.println(findGene("aaATGxxxaaTGAATAG"));
        // System.out.println(findGene("aaATGxxxaaTGAxxTAAG"));
        
        printAllGenes("aaaATGbbbTAAxcvsdfATGxxTAATGTGTAGbbbcccbbATGaaabbbxxxcccTAGTGAxxx");
    }
}
