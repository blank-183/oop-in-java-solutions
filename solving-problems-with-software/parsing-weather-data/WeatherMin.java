
/**
 * Write a description of WeatherMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherMin {

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowestTempRow = null;
        
        for(CSVRecord currentRow : parser) {
            if(lowestTempRow == null) {
                lowestTempRow = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestTempRow.get("TemperatureF"));
                
                if(currentTemp < lowestTemp) {
                    lowestTempRow = currentRow;
                }
            }
        }
        return lowestTempRow;
    }
    
    public String fileWithColdestTemperature() throws IOException {
        File lowestTempFile = null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord coldestTempFile = coldestHourInFile(fr.getCSVParser());
            
            if(lowestTempFile == null) {
                lowestTempFile = f;
            } else {
                CSVRecord currentColdest = coldestHourInFile(new FileResource(lowestTempFile).getCSVParser());
                double currentTemp = Double.parseDouble(coldestTempFile.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(currentColdest.get("TemperatureF"));
                
                if(currentTemp < lowestTemp) {
                    lowestTempFile = f;
                }
            }
        }
        return lowestTempFile.getCanonicalPath();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumRow = null;
        
        for(CSVRecord currentRow : parser) {
            lowestHumRow = getLargestOfTwo(currentRow, lowestHumRow);
        }
        return lowestHumRow;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumRow = null;
	DirectoryResource dr = new DirectoryResource();

	for (File f : dr.selectedFiles()) {
		FileResource fr = new FileResource(f);
		CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
		lowestHumRow = getLargestOfTwo(currentRow, lowestHumRow);
	}
	return lowestHumRow;
    }
    
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord lowestHumRow) {
        //If largestSoFar is nothing
        if (lowestHumRow == null) {
        	lowestHumRow = currentRow;
        } else {
        	try {
                        double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                        double lowestTemp = Double.parseDouble(lowestHumRow.get("Humidity"));
                        
                        if(currentTemp < lowestTemp) {
                            lowestHumRow = currentRow;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("not a number");
                    }
        }
        return lowestHumRow;
    }
    
    public void testHumidityInManyFiles () {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") + 
                            " at " + lowestHumidity.get("DateUTC"));
    }
    
    public void testLowestHumidityFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest temperature was " + coldest.get("TemperatureF") +
                            " at " + coldest.get("TimeEST"));
    }
    
    public void testFileWithColdestTemperature() throws IOException{
        String coldestDay = fileWithColdestTemperature();
        File f = new File(coldestDay);
        FileResource fr = new FileResource(coldestDay);
        CSVRecord coldestTemp = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("Coldest day was in file " + f.getName());
        System.out.println("Coldest temperature on that  day was " + coldestTemp.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        
        for(CSVRecord row : fr.getCSVParser()) {
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }
    
}
