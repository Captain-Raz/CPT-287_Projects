
import java.io.*;
import java.net.*;
import java.util.*;

public class movieManager {

	private static URL path = ClassLoader.getSystemResource("movies.txt");

	static Scanner input = new Scanner(System.in);
	static ArrayList<Movie> showingMovies = new ArrayList<Movie>(); // Stores everything as strings
	static ArrayList<Movie> comingMovies = new ArrayList<Movie>(); // Stores everything as strings // Ben
	private static String skipName;

	public static void main(String[] args) throws IOException, URISyntaxException { // Start main
		
		try { // Author: Zach
			populateArrays();
		} catch (IOException e) {
			System.out.println("An ERROR has occured. Cannot read file. No movie entries have been imported.");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			System.out.println("An ERROR has occured. Cannot read file. No movie entries have been imported.");
			e.printStackTrace();
		}

		char userChoice = ' ';
		Movie movieEntry = new Movie();
		boolean skip = false;
		String name;
		String descriptions;

		while (userChoice != 'q') { // Start while loop
			
			if (!skip) {  // Author: Zach
				userChoice = menu();
			}

			if (userChoice == 'a') { // Add movie entry  // Author: Zach

				System.out.println(""); // Getting name
				System.out.print("Enter Move Name: ");
				name = input.nextLine().trim();

				if (exists(name).equals("coming")) {
					System.out.println(""); // Getting Descriptions
					System.out.println("Movie exists in the Coming Movie List.");
					System.out.print("Edit Movie Listing? (y/n) ");
					userChoice = input.nextLine().trim().toLowerCase().charAt(0);

					if (userChoice == 'y') {
						userChoice = 'e';
						skip = true;
						skipName = name;
					} else {
						userChoice = ' ';
					}
				} else if (exists(name).equals("showing")) {
					System.out.println(""); // Getting Descriptions
					System.out.println("Movie exists in the Showing Movie List.");
					System.out.println("Cannot Edit Movies in Showing Movie List.");
					userChoice = ' ';
				} else {

					System.out.println(""); // Getting Descriptions
					System.out.println("Please enter movie descriptors separated by a space (e.g. Mystery Thriller)");
					System.out.print("Enter Movie Descriptors: ");
					descriptions = String.join("/", input.nextLine().split(" "));

					System.out.println(""); // Getting release Date
					System.out.print("Enter release date (e.g. 1/1/2000): ");
					String[] releaseDate = input.nextLine().split("/");

					System.out.println(""); // Getting received Date
					System.out.print("Enter received date (e.g. 1/1/2000): ");
					String[] receiveDate = input.nextLine().split("/");

					if (releaseDate.length == 3 && receiveDate.length == 3 && exists(name) == "") {
						Date released = new Date(releaseDate);
						Date received = new Date(receiveDate);
						if (Date.compareDates(released, received)) {

							movieEntry.setName(name); // Setting all fields of Movie object
							movieEntry.setDescription(descriptions);
							movieEntry.setReleaseDate(released);
							movieEntry.setReceiveDate(received);
							movieEntry.setEnumStatus();
							movieEntry.addChange("added");

							boolean added = false;

							if (comingMovies.size() != 0) { // Adding movie to comingMovies arrayList
								for (Movie movie : comingMovies) { // already entered date > current entry date
									if (Date.compareDates(movie.getReleaseDate(), movieEntry.getReleaseDate())) {
										comingMovies.add(comingMovies.indexOf(movie), movieEntry);
										added = true;
										break;
									}
								}
								if (!added) {
									comingMovies.add(movieEntry);
								}
							} else {
								comingMovies.add(movieEntry);
							}
						} else {
							System.out.println("Received Date occured after Release Date. Movie not entered.");
						}
					} else {
						if (releaseDate.length != 3) {
							System.out.println("Invalid Release Date. Movie not entered.");
						} else if (receiveDate.length != 3) {
							System.out.println("Invalid Received Date. Movie not entered.");
						} else {
							System.out.println("Movie already exists. Movie not entered.");
						}
					}
				}
			} else if (userChoice == 's') { // Output Showing Movies // Author: Zach
				for (Movie coming : showingMovies) {
					System.out.println(coming.formatMovie());
				}
			} else if (userChoice == 'c') { // Output Coming Movies // Author: Zach
				for (Movie coming : comingMovies) {
					System.out.println(coming.formatMovie());
				}
			} else if (userChoice == 'e') { // Edit Movie // Author: Zach
				name = skipName;
				if (!skip) {
					System.out.println("");
					System.out.print("Enter Name of Movie to Edit: ");
					name = input.nextLine();
				}
				skip = false;
				System.out.println("");
				System.out.print("Edit Description (d) or Edit Release Date (r): ");
				userChoice = input.nextLine().trim().toLowerCase().charAt(0);

				if (userChoice == 'd') {
					editDescription(name);
				} else if (userChoice == 'r') {
					editReleaseDate(name);
				} else {
					System.out.println("");
					System.out.println("That option was not recognize. No edits were made.");
				}
				skipName = "";
			} else if (userChoice == 'z') { // Count Coming Movies // Author: Ben
				System.out.println(comingMovies.size() + " Movies Coming Soon");

			} else if (userChoice == 'q') { // Quits and saves movies to file // Author: Zach
				System.out.println("");
				System.out.println("Saving Movies...");
				saveMovies();
			} else {
				System.out.println("That option was not recognized.");
			}
		} // End while loop
	} // End main
	
	/**
	 * Author: Zach
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	private static void editReleaseDate(String name) { // Start editReleaseDate

		for (Movie movie : comingMovies) {
			if (movie.getName().equals(name)) {
				System.out.println("");
				System.out.print("Current Release Date is: " + movie.formGetReleaseDate());

				System.out.println("");
				System.out.print("Enter a new Release Date (e.g. 1/1/2000): ");
				String[] newReleaseDate = input.nextLine().split("/");

				if (newReleaseDate.length == 3) {
					movie.setReleaseDate(new Date(newReleaseDate));
					movie.addChange("releaseDate");
				} else {
					System.out.println("");
					System.out.print("A New Release Date was not entered. No edits made.");
				}

				break;
			}
		}
	} // End editReleaseDate
	
	/**
	 * Author: Zach
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	private static void editDescription(String name) { // Start editDescription

		for (Movie movie : comingMovies) {
			if (movie.getName().equals(name)) {
				System.out.println("");
				System.out.print("Current Movie Description is: " + movie.getDescription());

				System.out.println("");
				System.out.println("Please enter movie descriptors separated by a space (e.g. Mystery Thriller)");
				System.out.print("Enter a new Descriptors: ");
				String newDesc = String.join("/", input.nextLine().split(" "));

				if (newDesc != "") {
					movie.setDescription(newDesc);
					movie.addChange("description");
				} else {
					System.out.println("");
					System.out.print("No Descriptors were entered. No edits made.");
				}

				break;
			}
		}
	} // End editDescription
	
	/**
	 * Author: Zach
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	private static String exists(String name) { // Checks if movie is already in a list

		for (Movie movie : comingMovies) {
			if (movie.getName().equals(name)) {
				return "coming";
			}
		}
		for (Movie movie : showingMovies) {
			if (movie.getName().equals(name)) {
				return "showing";
			}
		}
		return "";
	} // End exits

	public static char menu() { // Outputs menu options and returns char choice for main

		System.out.println("");
		System.out.println("d - Display all Movies");
		System.out.println("a - Add Movie Entry");
		System.out.println("s - Output Showing Movies");
		System.out.println("c - Output Coming Movies");
		System.out.println("e - Edit a Movie");
		System.out.println("z - Count Coming Movies");
		System.out.println("m - Start Showing Movies in Theater");
		System.out.println("q - Quit");
		System.out.println("");

		System.out.print("Please input option: ");
		char userChoice = input.nextLine().trim().toLowerCase().charAt(0);

		return userChoice;

	} // End menu
	
	/**
	 * Author: Zach
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	public static void populateArrays() throws IOException, URISyntaxException {

		String line;

		File f = new File(path.toURI());
		BufferedReader reader = new BufferedReader(new FileReader(f));

		while ((line = reader.readLine()) != null) {

			if (line.equals("Coming:") || line.equals("Showing:") || line.equals("") || !line.contains(",")) {
				continue;
			}
			System.out.println(line);
			Movie entry = new Movie(line.split(", "));
			System.out.println(line);

			// System.out.println(entry.formatMovie());
			if (entry.getEnumStatus().equals("RECEIVED")) {
				comingMovies.add(entry);
			} else {
				showingMovies.add(entry);
			}
		}
		reader.close();
	} // End populateArrays
	
	/**
	 * Author: Zach
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	public static void saveMovies() throws IOException, URISyntaxException {

		File f = new File(path.toURI());
		BufferedWriter writer = new BufferedWriter(new FileWriter(f, false));

		writer.write("Showing:\n");

		for (Movie movie : showingMovies) {
			writer.write(movie.formatMovie());
			writer.write("\n");
		}

		writer.write("\nComing:\n");

		for (Movie movie : comingMovies) {
			writer.write(movie.formatMovie());
			writer.write("\n");
		}
		
		writer.write("\nEdits:\n");
		writer.write("add - Added movie to Coming Movie List\n");
		writer.write("description - Changed Movie's Description\n");
		writer.write("releaseDate - Changed a Movie's Release Date\n");
		writer.write("showing - Moved Movie from 'Coming Movie List' to 'Showing Movie List'\n");
		writer.write("*** edits only appear on the entries that were edited.");

		writer.close();
		
		System.out.println("");
		System.out.println("File saved to /Movie_Manager/bin/movies.txt");
		System.out.println("");
	}
}
