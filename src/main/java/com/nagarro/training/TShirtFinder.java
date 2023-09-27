package com.nagarro.training;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.models.TShirt;
import com.nagarro.training.utils.CSVFileHandler;
import com.nagarro.training.utils.UserInput;

public class TShirtFinder {
	public static void main(String[] args) {
		// UserInput Object
		UserInput input = new UserInput();

		// Get input using UserInput object
		String color = input.inputColor();
		String size = input.inputSize();
		String gender = input.inputGender();
		int outPref = input.inputOutPref();

		// Create a CSVFileHandler class object
		CSVFileHandler csvFileHandler = new CSVFileHandler();
		// Set filters for the CSVFileHandler class setFilters() method
		csvFileHandler.setFilters(color, size, gender, outPref);
		
		// Create a new ThreadPoolExecutor with 3 threads
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		// Create a new ArrayList of TShirt
		ArrayList<TShirt> tShirts = new ArrayList<TShirt>();
		
		// Iterate through every file in folder
		for (File file : csvFileHandler.getCSVFiles()) {
			// Add all file read tasks in the ThreadPool
			executor.execute(() -> {
				// Add all the results in TShirt's ArrayList
				tShirts.addAll(csvFileHandler.readFile(file));
			});
		}

		
		
		// Close Executor after finishing the execution task
		executor.shutdown();
		// Wait till executor finishes it's task
		while (!executor.isTerminated()) {

		}
		
		// Sort the results with sortResults() method from CSVFileHandler Class
		csvFileHandler.sortResults(tShirts);
		
		// Check if TShirt ArrayList is not empty
		if (tShirts.size() != 0) {
			// Format the output
			System.out.printf(Constants.FORMAT_OUTPUT, 
								"ID", "Name", "Color", "Gender", "Size", 
								"Price", "Rating", "Availibility");
			
			// Print all results in TShirt ArrayList
			tShirts.forEach((tShirt) -> {
				System.out.println();
				// Format the result
				System.out.printf(Constants.FORMAT_OUTPUT, 
									tShirt.getID(), tShirt.getName(), tShirt.getColor(),
									tShirt.getGender(), tShirt.getSize(), tShirt.getPrice(),
									tShirt.getRating(), tShirt.getAvalibility());
			});
		} else {
			System.out.println(Constants.NO_TSHIRT_FOUND);
		}
	}
	
}
