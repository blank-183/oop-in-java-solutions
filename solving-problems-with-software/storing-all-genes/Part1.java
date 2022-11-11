
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part1 {

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
    
    public StorageResource getAllGenes(String dna) {
        int currIndex = dna.indexOf("ATG");
        StorageResource genesList = new StorageResource();
        
        if(currIndex == -1) {
            System.out.println("No gene found.");
        }
        
        String dnaStr = dna.substring(currIndex);
        
        while (true) {
            String gene = findGene(dnaStr);
            
            if(gene.isEmpty()) {
                break;
            }
            
            genesList.add(gene);
            // System.out.println(findGene(dnaStr));
            currIndex = currIndex + gene.length();
            dnaStr = dna.substring(currIndex);
        }
        
        return genesList;
    }
    
    public double cgRatio(String dna) {
        int cgCount = 0;
        double ratio = 0.0;
        
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cgCount = 0;
            }
        }
        
        ratio =  (double) cgCount / dna.length();
        
        return ratio;
    }
    
    public int countCTG(String dna) {
        int ctgCount = 0;
        int currIndex = dna.indexOf("CTG");
        int startIndex = 0;
        
        while(currIndex != -1) {
            ctgCount += 1;
            startIndex = currIndex + 3;
            currIndex = dna.indexOf("CTG", startIndex);
        }
        
        return ctgCount;
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
        String testStr = "aaaATGbbbTAAxcvsdfATGxxTAATGTGTAGbbbcccbbATGaaabbbxxxcccTAGTGAxxx";
        
        StorageResource genes = getAllGenes(testStr);
        
        for(String gene : genes.data()) {
            System.out.println("Found gene: " + gene);
        }
        // printAllGenes(testStr);
    }
    
}
