
import java.io.*;
import java.util.*;

public class movieManager {

	static Scanner input = new Scanner(System.in);
	ArrayList<String> showingMovies = new ArrayList<String>(); // Stores everything as strings
	ArrayList<String> comingMovies = new ArrayList<String>(); // Stores everything as strings // Ben

	public static void main(String[] args) {
		
		char userChoice = ' ';
		Movie movieEntry = new Movie();
		String name;
		String descriptions;
		String[] descriptors;

		while (userChoice != 'q') {

			userChoice = menu();

			if (userChoice == 'a') { // Add movie entry
				
				System.out.println("");
				System.out.print("Enter Move Name: ");
				name = input.nextLine().trim();
				
				System.out.println("");
				System.out.print("Enter Movie Descriptors (e.g. Mystery Thriller): ");
				descriptors = input.nextLine().trim().split(" ");
				descriptions = String.join("/", descriptors);
				
				System.out.println("");
				System.out.println("");
				
			} else if (userChoice == 's') { // Output Showing Movies
				saveMovies(false);
			} else if (userChoice == 'c') { // Output Coming Movies
				saveMovies(false);
			} else if (userChoice == 'e') { // Edit Movie
				saveMovies(false);
			} else if (userChoice == 'z') { // Count Coming Movies
				saveMovies(false);
			} else if (userChoice == 'q') { // Quits and saves movies to file
				saveMovies(true);
			} else {
				System.out.println("That option was not recognized.");
			}

		}

	}

	public static char menu() { // Outputs menu options and returns char choice for main

		System.out.println("");
		System.out.println("a - Add Movie Entry");
		System.out.println("s - Output Showing Movies");
		System.out.println("c - Output Coming Movies");
		System.out.println("e - Edit Coming Movies");
		System.out.println("z - Count Coming Movies");
		System.out.println("q - Quit"); // Movies will be autosaved when 'q' is entered and file location given
		System.out.println("");
		
		System.out.print("Please input option: ");
		char userChoice = input.nextLine().trim().toLowerCase().charAt(0);

		return userChoice;

	}

	public static void saveMovies(boolean state) { // Needs written

	}

}
