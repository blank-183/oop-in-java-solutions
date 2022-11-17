
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
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
	System.out.println("The coldest temperature was " + coldest.get("TemperatureF") +
				   " at " + coldest.get("TimeEST"));
    }
    
}
