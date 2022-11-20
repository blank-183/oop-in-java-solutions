
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int totalBirths = 0;
        int rankMonitor = 1;
        double currentRank = getRank(year, name, gender);
        
        if(currentRank == -1) {
            return -1;
        }
        
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals(gender)) {
                if(rankMonitor < currentRank) {
                    totalBirths += Integer.parseInt(record.get(2));
                    rankMonitor += 1;
                }
                
                if(rankMonitor == currentRank) {
                    break;
                }
            }
        }
        
        return totalBirths;
    }
    
    public void testTotalBirthsRankedHigher() {
        System.out.println("Total births: " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double total = 0.0;
        int count = 0;
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            double currentRank = getYearRank(fr, name, gender);
            
            if(total == 0.0 && currentRank != -1) {
                total += currentRank;
                count += 1;
            } else {
                if(getYearRank(fr, name, gender) != -1) {
                    total += currentRank;
                    count += 1;
                }
            }
        }
        
        if(total == 0.0) {
            return -1;
        }
        
        return total / count;
    }
    
    public void testAverage() {
        System.out.println(getAverageRank("Robert", "M"));
    }
    
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
    
    public int extractInt(String str) {
        str = str.replaceAll("[^0-9]", " "); // regular expression
        str = str.replaceAll(" +", " ");
        str = str.trim();
        
        if (str.equals(""))
            return -1;
 
        return Integer.parseInt(str);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        Integer highestRank = null;
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentYear = extractInt(f.getName());
            int currentRank = getYearRank(fr, name, gender);
            
            if(highestRank == null && currentRank != -1) {
                highestRank = currentYear;
                System.out.println("Ranks: " + currentRank + " year: " + currentYear);
            } else if(currentRank != -1) {
                int currentHighestRank = getRank(highestRank, name, gender);
                
                if(currentRank != -1 && currentRank < currentHighestRank) {
                    highestRank = currentYear;
                }
                
                System.out.println("Ranks: " + currentRank + " year: " + currentYear);
            }
        }
        if(highestRank == null) {
            return -1;
        }
        
        return highestRank;
    }
    
    public void testYearHighestRank() {
        System.out.println(yearOfHighestRank("Genevieve", "F"));
    }
    
    public int getYearRank(FileResource fr, String name, String gender) {
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
    
    public void testNewName() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int getRank(int year, String name, String gender) {
        String fileName = "data/yob" + year + ".csv";
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
        String fileName = "data/yob" + year + ".csv";
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
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public void printNames (String gender) {
        FileResource fr = new FileResource();
        int currRank = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                if(currRank > 300 && currRank < 460) {
                    System.out.println("Rank: " + currRank + " Name " + rec.get(0) +
                               " Gender " + rec.get(1) +
                               " Num Born " + rec.get(2));
                    
                }
                currRank += 1;
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames += 1;
            }
            else {
                totalGirls += numBorn;
                totalGirlsNames += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        
        System.out.println("Total boys names: " + totalBoysNames);
        System.out.println("Total girls names: " + totalGirlsNames);
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob1905.csv");
        totalBirths(fr);
    }
}
