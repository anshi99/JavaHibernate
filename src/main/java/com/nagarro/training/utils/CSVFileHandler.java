package com.nagarro.training.utils;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.models.TShirt;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class CSVFileHandler {
	// Initialize all required variables
	private String color = null;
	private String size = null;
	private String gender = null;
	private int outPref = 1;

	// Method to set filters to the CSV File read operation
	public void setFilters(String color, String size, String gender, int outPref) {
		this.color = color;
		this.size = size;
		this.gender = gender;
		this.outPref = outPref;
	}

	// Method to get all CSV files in a folder
	public File[] getCSVFiles() {
		// Create a null files array object
		File[] files = null;

		try {
			// Get all the CSV Files from the folder
			files = new File(getClass().getResource(Constants.FILES_PATH).getPath()).listFiles((dir, name) -> {
				return name.toLowerCase().endsWith(".csv");
			});
		}catch(NullPointerException npe) {
			System.out.println(Constants.NO_FILE_FOUND);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// Return all the files
		return files;
	}


	// Method to read the CSV file data and return a ArrayList of TShirt
	public ArrayList<TShirt> readFile(File file) {
		// Create a new TShirt ArrayList
		ArrayList<TShirt> tShirts = new ArrayList<TShirt>();

		try {
			// Create a new FileReader Object with the file
			FileReader reader = new FileReader(file);

			// Parser for the CSV File to separate the columns with "|"
			CSVParser parser = new CSVParserBuilder().withSeparator('|').build();

			// Read CSV files
			CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();
			// Create an empty String array
			String[] strArr;

			// Iterate to the end of CSV Files
			while ((strArr = csvReader.readNext()) != null) {
				// Check if the current row matches the required criteria
				if (strArr[Constants.COLUMN_COLOR].toLowerCase().equals(color.toLowerCase())
						&& strArr[Constants.COLUMN_SIZE].equals(size)
						&& strArr[Constants.COLUMN_GENDER].equals(gender)) {
					// Add current row to a TShirt Object
					TShirt tShirt = new TShirt();
					tShirt.setID(strArr[Constants.COLUMN_ID]);
					tShirt.setName(strArr[Constants.COLUMN_NAME]);
					tShirt.setColor(strArr[Constants.COLUMN_COLOR]);
					tShirt.setGender(strArr[Constants.COLUMN_GENDER]);
					tShirt.setSize(strArr[Constants.COLUMN_SIZE]);
					tShirt.setPrice(Double.parseDouble(strArr[Constants.COLUMN_PRICE]));
					tShirt.setRating(Float.parseFloat(strArr[Constants.COLUMN_RATING]));
					tShirt.setAvailibility(strArr[Constants.COLUMN_AVAILABILITY]);
					// Add TShirt Object to the ArrayList
					tShirts.add(tShirt);
				}
			}
			// Close CSVReader object
			csvReader.close();
		} catch (CsvException csve) {
			System.out.println("Error Reading CSV File.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return TShirt ArrayList
		return tShirts;
	}

	// Method to sort the final result
	public void sortResults(ArrayList<TShirt> tShirts) {
		if (outPref == Constants.OUTPUT_BY_PRICE) {
			// Sort the result by lower price to higher price
			tShirts.sort(Comparator.comparing(TShirt::getPrice));
		} else if (outPref == Constants.OUTPUT_BY_RATING) {
			// Sort the result by higher rating to lower rating
			tShirts.sort(Comparator.comparing(TShirt::getRating).reversed());
		} else if (outPref == Constants.OUTPUT_BY_PRICE_AND_RATING) {
			// Sort the result first from higher rating to lower rating
			// then by the lower price to higher price
			tShirts.sort(Comparator.comparing(TShirt::getRating).reversed().thenComparing(TShirt::getPrice));
		}
	}
}
