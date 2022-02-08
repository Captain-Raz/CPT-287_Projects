
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
				String sample = "Glass, 01/18/2019, Drama/Fantasy, 01/12/2019, released";
				
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

		System.out.println(""); // Brad edit menu and add options
		System.out.println("d - Display all Movies");
		System.out.println("a - Add Movie Entry"); // Zach
		System.out.println("s - Output Showing Movies"); 
		System.out.println("c - Output Coming Movies"); // Ben
		System.out.println("e - Edit a Movie"); // Zach // edit release date and description
		System.out.println("z - Count Coming Movies"); // Ben
		System.out.println("m - Start Showing Movies in Theater"); // Ben
		System.out.println("q - Quit"); // Movies will be autosaved when 'q' is entered and file location given
		System.out.println("");
		
		System.out.print("Please input option: ");
		char userChoice = input.nextLine().trim().toLowerCase().charAt(0);

		return userChoice;

	}

	public static void saveMovies(boolean state) { // Zach

	}

}
