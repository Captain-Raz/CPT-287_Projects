
import java.util.*;

public class movieManager {

	static Scanner input = new Scanner(System.in);
	ArrayList<String[]> movies = new ArrayList<String[]>(); // Stores everything as strings

	public static void main(String[] args) {

		char userChoice = ' ';

		while (userChoice != 'q') {

			userChoice = menu();

			if (userChoice == 'a') { // Add movie entry

			} else if (userChoice == 'o') { // Output current movie entries to console

			} else if (userChoice == 'q') { // Quits and saves movies to file
				saveMovies();
			}

		}

	}

	public static char menu() { // Outputs menu options and returns char choice for main

		System.out.println("");
		System.out.println("a - Add Movie Entry");
		System.out.println("o - Output Movie Entries");
		System.out.println("q - Quit"); // Movies will be autosaved when 'q' is entered and instructions on where to
										// find the file will be printed
		System.out.println("");

		char userChoice = input.nextLine().strip().toLowerCase().charAt(0);

		return userChoice;

	}

	public static void saveMovies() { // Needs written

	}

}
