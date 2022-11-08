
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        if(stopIndex == -1 || ((stopIndex - startIndex) % 3 != 0)) {
            return dna.length();
        }
        return stopIndex;
    }
    
    public String findGene(String dna) {
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
    
    public int countGenes(String dna) {
        int count = 0;
        int currIndex = dna.indexOf("ATG");
        
        if(currIndex == -1) {
            return 0;
        }
        
        String dnaStr = dna.substring(currIndex);
        
        while (true) {
            String gene = findGene(dnaStr);
            
            if(gene.isEmpty()) {
                break;
            }
            
            count += 1;
            currIndex = currIndex + gene.length();
            dnaStr = dna.substring(currIndex);
        }
        
        return count;
    }
    
    public void printAllGenes(String dna) {
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
    
    public void testFindStopCodon() {
        System.out.println(findStopCodon("aaaATGbbbTAA", 3, "TAA"));
        System.out.println(findStopCodon("aaaATGbbTAAATTGA", 3, "TGA"));
    }
    
    public void testFindGene() {
        // System.out.println(findGene("xxxTGATAA"));
        // System.out.println(findGene("aaATGxxxTGA"));
        // System.out.println(findGene("aaATGxxxaaTGAATAG"));
        // System.out.println(findGene("aaATGxxxaaTGAxxTAAG"));
        
        printAllGenes("aaaATGbbbTAAxcvsdfATGxxTAATGTGTAGbbbcccbbATGaaabbbxxxcccTAGTGAxxx");
    }
    
    public void testCountGenes() {
        System.out.println(countGenes("aaaATGbbbTAAxcvsdfATGxxTAATGTGTAGbbbcccbbATGaaabbbxxxcccTAGTGAxxx"));
        System.out.println(countGenes("aaaATGbbbTAA"));
        System.out.println(countGenes("aaaATGbbTGA"));
    }

}
