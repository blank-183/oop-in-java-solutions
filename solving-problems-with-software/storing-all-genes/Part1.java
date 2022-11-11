
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            
            if (diff % 3 ==0 ) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int startIndex) {
        int index = dna.indexOf("ATG", startIndex);

        if(index == -1) {
            return "";
        }
        
        int taaStopIndex = findStopCodon(dna, index, "TAA");
        int tagStopIndex = findStopCodon(dna, index, "TAG");
        int tgaStopIndex = findStopCodon(dna, index, "TGA");
        
        int minIndex = Math.min(taaStopIndex, Math.min(tagStopIndex, tgaStopIndex));
        
        if(minIndex == dna.length()) {
            return "";
        }
        
        return dna.substring(index, minIndex + 3);
    }

    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource genesList = new StorageResource();
        
        while (true) {
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()) {
                break;
            }
            genesList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) +  gene.length();
        }
        
        return genesList;
    }
    
    public double cgRatio(String dna) {
        int cgCount = 0;
        double ratio = 0.0;
        
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cgCount += 1;
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
    
    public void processGenes(StorageResource sr) {
        int lengthCount = 0;
        int cgCount = 0;
        int genesCount = 0;
        String longestGene = null;
        
        for(String gene : sr.data()) {
            if(gene.length() > 60) {
                System.out.println("Gene (above 60 chars): " + gene);
                lengthCount += 1;
            }
            
            if(cgRatio(gene) > 0.35) {
                System.out.println("Gene (above 0.35 cgRatio): " + gene);
                cgCount += 1;
            }
            
            if(longestGene == null || longestGene.length() < gene.length()) {
                longestGene = gene;
            }
            
            genesCount += 1;
        }
        System.out.println();
        System.out.println("Number of genes: " + genesCount);
        System.out.println("No. of above 60 of length: " + lengthCount);
        System.out.println("No. of above 0.35 of cgRatio: " + cgCount);
        System.out.println("The longest gene is: " + longestGene);
        System.out.println("The length of longest gene: " + longestGene.length());
    }
    
    public void testProcessGenes() {
        //String testStr = "aaaATGbbbTAAxcvsdfATGxxTAATGTGTAGbbbcccbbATGaaabbbxxxcccTAGTGAxxx";
        //StorageResource genes = getAllGenes(testStr);
        
        FileResource fr = new FileResource();
        String testStr = fr.asString().toUpperCase();
        StorageResource genes = getAllGenes(testStr);

        processGenes(genes);
        System.out.println("Number of CTG: " + countCTG(testStr));

    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        int atgIndex = dna.indexOf("ATG", startIndex);
          
        while (true) {
            String gene = findGene(dna, startIndex);
            
            if(gene.isEmpty()) {
                break;
            }
            
            System.out.println(findGene(dna, startIndex));
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    
    public void testFindStopCodon() {
        System.out.println(findStopCodon("aaaATGbbbTAA", 3, "TAA"));
        System.out.println(findStopCodon("aaaATGbbTAAATTGA", 3, "TGA"));
    }

    public void testFindGene() {
        // System.out.println(findGene("xxxTGATAA"));

        String testStr = "aaaATGbbbTAAxcvsdfATGxxTAATGTGTAGbbbcccbbATGaaabbbxxxcccTAGTGAxxx";
        
        StorageResource genes = getAllGenes(testStr);
        
        for(String gene : genes.data()) {
            System.out.println("Found gene: " + gene);
        }
        
        // printAllGenes(testStr);
    }
    
}
