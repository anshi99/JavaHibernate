package com.nagarro.training.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

import com.nagarro.training.constants.Constants;

import java.io.IOException;

public class UserInput {
	BufferedReader br;
	// Initializing variables
	String color = "";
	String size = "";
	String gender = "";
	int outputPref = 0;

	// Constructor
	public UserInput() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	// Take Color Input
	public String inputColor() {
		System.out.print("Enter Color: ");

		try {
			// Get Color Input
			color = br.readLine();
			if (color.matches(".*\\d.*")) {
				System.out.println("Color Cannot contains a number.");
				inputColor();
			}
		} catch (IOException ioe) {
			// for I/O Exception
			System.out.println(Constants.UNKNOWN_ERROR);
			inputColor();
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
			inputColor();
		}

		// Return color variable
		return color;
	}

	// Take Size Input
	public String inputSize() {
		System.out.print("Enter Size (S, M, L, XL, XXL): ");

		try {
			// Get Size Input
			size = br.readLine().toUpperCase();

			// Check if entered size is a valid size
			if (!size.equals("S") && !size.equals("M") && !size.equals("L") && !size.equals("XL")
					&& !size.equals("XXL")) {
				System.out.println(Constants.INVALID_VALUE);
				inputSize();
			}
		} catch (IOException ioe) {
			// For I/O Exception
			System.out.println(Constants.UNKNOWN_ERROR);
			inputSize();
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
			inputSize();
		}

		// Return size
		return size;
	}

	// Take Gender Input
	public String inputGender() {
		System.out.print("Enter Gender (M/F/U): ");

		try {
			// Get Gender Input
			gender = br.readLine().toUpperCase();

			// Check if entered gender is a valid gender
			if (!gender.equals("M") && !gender.equals("F") && !gender.equals("U")) {
				System.out.println(Constants.INVALID_VALUE);
				inputGender();
			}
		} catch (IOException ioe) {
			// For I/O Exception
			System.out.println(Constants.UNKNOWN_ERROR);
			inputGender();
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
			inputGender();
		}

		// Return gender variable
		return gender;
	}

	// Take Output Preference Input
	public int inputOutPref() {
		System.out.println(Constants.OUTPUT_PREFERENCES_OPTIONS);
		System.out.print("Enter Your Output Preference: ");

		try {
			// Get outputPreference input
			outputPref = Integer.parseInt(br.readLine());

			// Check if entered value is a correct value
			if (outputPref != Constants.OUTPUT_BY_PRICE && outputPref != Constants.OUTPUT_BY_RATING
					&& outputPref != Constants.OUTPUT_BY_PRICE_AND_RATING) {
				System.out.println(Constants.INVALID_VALUE);
				inputOutPref();
			}
		} catch (InputMismatchException ime) {
			// Handle String Input Exception
			System.out.println(Constants.INVALID_VALUE);
			inputOutPref();
		} catch (IOException ioe) {
			// I/O Exception
			System.out.println(Constants.UNKNOWN_ERROR);
			inputOutPref();
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
			inputOutPref();
		}

		// Return output Preference variable
		return outputPref;
	}
}
