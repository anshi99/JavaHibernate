package com.nagarro.training.constants;

public class Constants {
	// CSV FILES Path
	public static final String FILES_PATH = "/csv/";
	
	// Format final String output
	public static final String FORMAT_OUTPUT = "%-15s %-40s %-10s %-10s %-10s %-10s %-10s %-5s";
	
	// Output Preference Options
	public static final String OUTPUT_PREFERENCES_OPTIONS = "Output Preference:\n1. By Price\n2. By Rating\n3. By Price & Rating\n";
	public static final int OUTPUT_BY_PRICE = 1;
	public static final int OUTPUT_BY_RATING = 2;
	public static final int OUTPUT_BY_PRICE_AND_RATING = 3;
	
	// CSV Column Position
	public static final int COLUMN_ID = 0;
	public static final int COLUMN_NAME = 1;
	public static final int COLUMN_COLOR = 2;
	public static final int COLUMN_GENDER = 3;
	public static final int COLUMN_SIZE = 4;
	public static final int COLUMN_PRICE = 5;
	public static final int COLUMN_RATING = 6;
	public static final int COLUMN_AVAILABILITY = 7;
	
	// Exceptions 
	public static final String UNKNOWN_ERROR = "Some Error Occurred.";
	public static final String INVALID_VALUE = "Invalid Value.";
	public static final String NO_TSHIRT_FOUND = "No TShirt Found!";
	public static final String NO_FILE_FOUND = "No File Found!";
	
}
