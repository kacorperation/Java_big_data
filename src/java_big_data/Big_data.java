package java_big_data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Big_data {
	/**
	 * 
	 * @param data
	 * @return the enumerated list of data
	 */
	public int[] enumerate(String[] data) {
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		//get unique elements of data
		String[] temp_list = Arrays.stream(data).distinct().toArray(String[]::new);
		
		//fill the dictionary
		for (int i=0;i<temp_list.length;i++) {
			dictionary.put(temp_list[i], i);
		}
		//enumerate
		int[] result = new int[data.length];
		for (int i = 0; i < temp_list.length ; i++) {
			result[i] = dictionary.get(data[i]);
		}
		return result;
	}
	/**
	 * 
	 * @param data
	 * @return the enumerated list of data
	 */
	public int[] enumerate(int[] data) {
		Map<Integer, Integer> dictionary = new HashMap<Integer, Integer>();
		//get unique elements of data
		int[] temp_list = Arrays.stream(data).distinct().toArray();
		
		//fill the dictionary
		for (int i=0;i<temp_list.length;i++) {
			dictionary.put(temp_list[i], i);
		}
		//enumerate
		int[] result = new int[data.length];
		for (int i = 0; i < temp_list.length ; i++) {
			result[i] = dictionary.get(data[i]);
		}
		return result;
	}
	
	public static String[][] csv_reader(String SAMPLE_CSV_FILE_PATH,
			int num_columns, int num_rows, String[] column_names) throws IOException{
		/**
		 * TODO: CSVs w/o header columns
		* Reads a csv file
		* @param the parameters used by the method
		* @return {String[num_columns, num_entries]} - the transposed String matrix of csv file
		* @throws what kind of exception does this method throw
		*/
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
	    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
	    		.withFirstRecordAsHeader()
	            .withIgnoreHeaderCase()
	            .withTrim());
	    Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	    String[][] output = new String [num_columns][num_rows];
	    int index = 0;
	    for (CSVRecord csvRecord : csvRecords) {
	    	for (int i=0; i < column_names.length; i++) {
	    		output[i][index] = csvRecord.get(column_names[i]);}
	    	index += 1;
	    }
	    csvParser.close();
		return output;
	}
	
	public static void csv_writer(String location, String name, String[] column_names, String[][] data) throws IOException {
		/**
		 Writes a csv file
		 @param {String} location - the folder directory in which the data will be saved
		 @param {String} name - name of the csv file that will be saved
		 @param {String[]} column_names - Header names
		 @param {String[][]} data - the data matrix that will be converted to csv
		 @return {csv} - a csv file at the designated location
		 */
		FileWriter fileWriter = null;
		final String NEW_LINE_SEPARATOR = "\n";
		 CSVPrinter csvFilePrinter = null;
		 String fileName = location + name;
	
		//Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
				
		//initialize FileWriter object
		fileWriter = new FileWriter(fileName);
		
		//initialize CSVPrinter object 
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
        
        //Create CSV file header
        List header = new ArrayList();
        for (int j=0; j < column_names.length; j++) {
        	header.add(column_names[j]);
        }
        csvFilePrinter.printRecord(header);
		
		//Write a new student object list to the CSV file
		for (int i = 0; i < data.length; i++) {
			List line = new ArrayList();
			for (int j=0; j < data[1].length; j++) {
				line.add(data[i][j]);
			}
            csvFilePrinter.printRecord(line);
		}
		csvFilePrinter.close();
	}
	
	public static void csv_writer(String location, String name, String[] column_names, String[] data) throws IOException {
		/**
		 Writes a csv file
		 @param {String} location - the folder directory in which the data will be saved
		 @param {String} name - name of the csv file that will be saved
		 @param {String[]} column_names - Header names
		 @param {String[][]} data - the data matrix that will be converted to csv
		 @return {csv} - a csv file at the designated location
		 */
		FileWriter fileWriter = null;
		final String NEW_LINE_SEPARATOR = "\n";
		 CSVPrinter csvFilePrinter = null;
		 String fileName = location + name;
	
		//Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
				
		//initialize FileWriter object
		fileWriter = new FileWriter(fileName);
		
		//initialize CSVPrinter object 
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
        
        //Create CSV file header
        List header = new ArrayList();
        for (int j=0; j < column_names.length; j++) {
        	header.add(column_names[j]);
        }
        csvFilePrinter.printRecord(header);
		
		//Write a new student object list to the CSV file
		for (int i = 0; i < data.length; i++) {
			List line = new ArrayList();
				line.add(data[i]);
            csvFilePrinter.printRecord(line);
		}
		csvFilePrinter.close();
	}
	
	public static void csv_writer(String location, String name, String[] column_names, int[][] data) throws IOException {
		/**
		 Writes a csv file
		 @param {String} location - the folder directory in which the data will be saved
		 @param {String} name - name of the csv file that will be saved
		 @param {String[]} column_names - Header names
		 @param {String[][]} data - the data matrix that will be converted to csv
		 @return {csv} - a csv file at the designated location
		 */
		FileWriter fileWriter = null;
		final String NEW_LINE_SEPARATOR = "\n";
		 CSVPrinter csvFilePrinter = null;
		 String fileName = location + name;
	
		//Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
				
		//initialize FileWriter object
		fileWriter = new FileWriter(fileName);
		
		//initialize CSVPrinter object 
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
        
        //Create CSV file header
        List header = new ArrayList();
        for (int j=0; j < column_names.length; j++) {
        	header.add(column_names[j]);
        }
        csvFilePrinter.printRecord(header);
		
		//Write a new student object list to the CSV file
		for (int i = 0; i < data[0].length; i++) {
			List line = new ArrayList();
			for (int j=0; j < data[1].length; j++) {
				line.add(data[i][j]);
			}
            csvFilePrinter.printRecord(line);
		}
		csvFilePrinter.close();
	}
	
	public static String[] find_uniques(String[] data) {
		return Arrays.stream(data).distinct().toArray(String[]::new);
	}
	
	public static int [] find_uniques(int[] data) {
		return Arrays.stream(data).distinct().toArray();
	}
}
