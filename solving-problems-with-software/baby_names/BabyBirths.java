
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int newRank = getRank(year, name, gender);
        
        if(newRank != -1) {
            String newName = getName(newYear, newRank, gender);
            
            if(!newName.equals("NO NAME")) {
                System.out.println(name + " born in " + year + " would be " + newName + 
                                    " if she was born in " + newYear + ".");
            }
        }
    }
    
    public void testNewName() {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
    
    public int getRank(int year, String name, String gender) {
        String fileName = "testing/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        int rank = 0;
        boolean isNamePresent = false;
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals(gender)) {
                rank += 1;
                if(record.get(0).equals(name)) {
                    isNamePresent = true;
                    break;
                }
            }
        }
        
        if(!isNamePresent) {
            return -1;
        }
        
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        String fileName = "testing/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        int currRank = 0;
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals(gender)) {
                currRank += 1;
                if(currRank == rank) {
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void testGetName() {
        System.out.println(getName(2014, 6, "F"));
    }
    
    public void testRank() {
        System.out.println(getRank(2012, "Mason", "F"));
    }
    
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
}
