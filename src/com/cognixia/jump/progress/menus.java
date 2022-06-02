package com.cognixia.jump.progress;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class menus {

	private static ArrayList<Movie> allMovies;

	private static ArrayList<Watchlist> watchlists;

	private static Scanner sc;

	private static boolean started = false;

	public static void Start() {
		if (started)
			return;
		started = true;

		sc = new Scanner(System.in);

		FakeLoad();
	}

	public static void FakeLoad() {
		FakeLoadMovies();
		FakeLoadWatchlists();
	}

	public static void FakeLoadMovies() {
		allMovies = new ArrayList<Movie>();
		allMovies.add(new Movie("Bongbong Bingbing the Dragon", 120));
		allMovies.add(new Movie("Climbing a Wall Alone", 120));
		allMovies.add(new Movie("Gorgeous", 220));
		allMovies.add(new Movie("Lamanas the Destroyer", 120));
		allMovies.add(new Movie("Disrupting the Bank", 120));
		allMovies.add(new Movie("Fast 6", 120));
		allMovies.add(new Movie("Last Chance at a Loan", 120));
		allMovies.add(new Movie("Freezing Rickety Old House", 120));
		allMovies.add(new Movie("Starstar the Hero", 120));
		allMovies.add(new Movie("Starstar the Hero 2", 120));
		allMovies.add(new Movie("Starstar the Hero 3", 120));
		allMovies.add(new Movie("Starstar the Hero 4", 120));
		allMovies.add(new Movie("Pastry Pastries", 120));
	}

	public static void FakeLoadWatchlists() {
		watchlists = new ArrayList<Watchlist>();
		Watchlist wl1 = new Watchlist("Favorite Movies");
		wl1.addMovie(allMovies.get(0));
		wl1.addMovie(allMovies.get(2));
		wl1.addMovie(allMovies.get(3));
		wl1.addMovie(allMovies.get(6));
		wl1.addMovie(allMovies.get(10));
		watchlists.add(wl1);
		Watchlist wl2 = new Watchlist("The Starstar Playlist");
		wl2.addMovie(allMovies.get(8));
		wl2.addMovie(allMovies.get(9));
		wl2.addMovie(allMovies.get(10));
		wl2.addMovie(allMovies.get(11));
		watchlists.add(wl2);
	}

	public static void OpeningMenu() {
		DisplayMenuOption("Login", 1);
		DisplayMenuOption("Quit", 2);

		int input = getInputInt("", 1, 2);

		if (input == 1) {
			LoginMenu();
		}
		if (input == 2) {
			End();
		}
	}

	public static void LoginMenu() {
		String username = getInputString("Enter your username.");
		String password = getInputString("Enter your password.");

		boolean success = true;

		if (success == true) {
			MainMenu();
		}
		if (success == false) {
			OpeningMenu();
		}

	}

	public static void MainMenu() {
		System.out.println("\nMain Menu\n");

		DisplayMenuOption("Watch Movie", 1);
		DisplayMenuOption("View Movies", 2);
		DisplayMenuOption("Watch Lists", 3);
		DisplayMenuOption("Log Out", 4);
		DisplayMenuOption("Quit", 5);

		int input = getInputInt("", 1, 4);

		if (input == 1) {
			SelectMovieToWatch();
		}
		if (input == 2) {
			MoviesMenu();
		}
		if (input == 3) {
			WatchlistMenu();
		}
		if (input == 4) {
			OpeningMenu();
		}
		if (input == 5) {
			End();
		}
	}

	public static void MoviesMenu() {
		// print all movies
		System.out.println("All Movies:\n");

		for (int i = 0; i < allMovies.size(); i++) {
			Movie curMovie = allMovies.get(i);
			System.out.println(curMovie.name + "\t-\t-\t-\t-\tProgress = " + GetPercentMovieProgress(curMovie) + "%");
		}
		MainMenu();
	}
	public static void SelectMovieToWatch() {
		
		System.out.println("All Movies:\n");

		for (int i = 0; i < allMovies.size(); i++) {
			Movie curMovie = allMovies.get(i);
			System.out.println("(" + i + ") " + curMovie.name + "\t-\t-\t-\t-\tProgress = " + GetPercentMovieProgress(curMovie) + "%");
		}
		int input = getInputInt("", 1, allMovies.size() - 1);
		Movie selectedMovie = allMovies.get(input);
		WatchMovie(selectedMovie);
		MainMenu();
	}
	
	public static void WatchMovie(Movie movie) {
		
		System.out.println("Wow, what a great movie! Thanks for watching!\n");
		Random rand = new Random();
		int minutesWatched = rand.nextInt(movie.totalMinutes);
		movie.minutesWatched += minutesWatched;
		autosave();
		MainMenu();
	}
	
	public static void WatchlistMenu() {
		DisplayMenuOption("View Watchlist Progress", 1);
		DisplayMenuOption("View Watchlist Details", 2);

		DisplayMenuOption("Create Watchlist", 3);
		DisplayMenuOption("Edit Watchlist", 4);
		DisplayMenuOption("Delete Watchlist", 5);
		DisplayMenuOption("Back", 6);
		DisplayMenuOption("Quit", 7);

		int input = getInputInt("", 1, 6);

		if (input == 1) {
			DisplayWatchlists();
		}
		if (input == 2) {
			DisplayWatchlistsDetails();
		}
		if (input == 3) {
			CreateWatchlist();
		}
		if (input == 4) {
			EditWatchlist();
		}
		if (input == 5) {
			DeleteWatchlist();
		}
		if (input == 6) {
			MainMenu();
		}
		if (input == 7) {
			End();
		}
	}

	public static void DisplayWatchlists() {
		// Make sure to include the % progress!!
		System.out.println("\nAll Watchists:\n");

		for (int i = 0; i < watchlists.size(); i++) {
			Watchlist curList = watchlists.get(i);
			GetPercentWatchlistProgress(curList, true);
		}

		WatchlistMenu();
	}

	public static void DisplayWatchlistsInteractive(boolean delete) {
		if (watchlists.size() == 0) {
			System.out.println("\nNo watchlists here!\n");
			WatchlistMenu();
			return;
		}

		System.out.println("\nSelect Watchist:\n");

		for (int i = 0; i < watchlists.size(); i++) {
			Watchlist curList = watchlists.get(i);
			System.out.println("(" + (i + 1) + ") " + curList.watchlistName);
		}
		int input = getInputInt("Enter in a watchlist index:", 1, watchlists.size());

		if (delete) {
			System.out.println("Deleting the " + watchlists.get(input - 1).watchlistName + " watchlist.");
			watchlists.remove(input);
			WatchlistMenu();
			return;
		}
		DisplayMoviesInteractive(watchlists.get(input));
		
		autosave();
		WatchlistMenu();
	}

	public static void DisplayWatchlistsDetails() {
		// Make sure to include the % progress!!
		System.out.println("\nAll Watchists:\n");

		for (int i = 0; i < watchlists.size(); i++) {
			Watchlist curList = watchlists.get(i);
			System.out.println(curList.watchlistName);
			GetPercentWatchlistProgress(curList, true);

			for (int x = 0; x < curList.movies.size(); x++) {
				Movie curMovie = curList.movies.get(x);

				System.out.println(curMovie.name);
				System.out.println("Progress: " + GetPercentMovieProgress(curMovie) + "%");

			}

		}
		System.out.println();

		WatchlistMenu();
	}

	public static void DisplayMoviesInteractive(Watchlist watchlist) {
		System.out.println("\nAdd or remove watchlists.\n");

		System.out.println("(" + (1) + ") Save Watchlist");
		System.out.println("(" + (2) + ") Discard Watchlist Changes");
		for (int i = 0; i < allMovies.size(); i++) {
			Movie curMovie = allMovies.get(i);
			boolean hasMovie = false;
			for (int x = 0; x < watchlist.movies.size(); x++) {
				if (watchlist.movies.get(x).name == curMovie.name) {
					hasMovie = true;
					break;
				}
			}
			if (hasMovie == true) {
				System.out.println("(" + (i + 3) + ") Remove\t" + curMovie.name);

			}
			if (hasMovie == false) {
				System.out.println("(" + (i + 3) + ") Add  \t" + curMovie.name);
			}
		}
	}

	public static void CreateWatchlist() {

		System.out.println("\nCreating a Watchlist\n");

		String watchlist_name = getInputString("Enter the watchlist name.");
		Watchlist watchlist = new Watchlist(watchlist_name);

		DisplayMoviesInteractive(watchlist);

		int input = 3;
		while (input > 2) {

			input = getInputInt("Enter in a movie index:", 1, allMovies.size() + 2);
			if (input == 1) {
				if (watchlist.movies.size() == 0) {
					System.out.println("No movies on watchlist. Start over.\n\n");
					CreateWatchlist();
					return;

				}
				watchlists.add(watchlist);
				System.out.println("Adding the watchlist to your collection.");
				WatchlistMenu();
				return;
			}
			if (input == 2) {
				System.out.println("Discarding the watchlist.");
				WatchlistMenu();
				return;
			}

			boolean hasMovie = false;
			for (int x = 0; x < watchlist.movies.size(); x++) {
				if (watchlist.movies.get(x).name == allMovies.get(input - 3).name) {
					hasMovie = true;
					break;
				}
			}
			if (hasMovie == false) {
				watchlist.addMovie(allMovies.get(input - 3));
			}
			if (hasMovie == true) {
				watchlist.removeMovie(allMovies.get(input - 3));
			}
			DisplayMoviesInteractive(watchlist);
			autosave();
		}

	}

	public static void EditWatchlist() {

		System.out.println("\nEditing a Watchlist\n");

		String watchlist_name = getInputString("Enter the watchlist name.");
		Watchlist watchlist = new Watchlist(watchlist_name);

		DisplayMoviesInteractive(watchlist);

		int input = 3;
		while (input > 2) {

			input = getInputInt("Enter in a movie index:", 1, allMovies.size() + 2);
			if (input == 1) {
				if (watchlist.movies.size() == 0) {
					System.out.println("No movies on watchlist. Start over.\n\n");
					CreateWatchlist();
					return;

				}
				watchlists.add(watchlist);
				System.out.println("Adding the watchlist to your collection.");
				WatchlistMenu();
				return;
			}
			if (input == 2) {
				System.out.println("Discarding the watchlist.");
				WatchlistMenu();
				return;
			}

			boolean hasMovie = false;
			for (int x = 0; x < watchlist.movies.size(); x++) {
				if (watchlist.movies.get(x).name == allMovies.get(input - 3).name) {
					hasMovie = true;
					break;
				}
			}
			if (hasMovie == false) {
				watchlist.addMovie(allMovies.get(input - 3));
			}
			if (hasMovie == true) {
				watchlist.removeMovie(allMovies.get(input - 3));
			}
			DisplayMoviesInteractive(watchlist);
		}
		autosave();
	}

	public static void DeleteWatchlist() {
		DisplayWatchlistsInteractive(true);
		autosave();
	}

	public static void DisplayMenuOption(String s, int number) {
		System.out.println("(" + number + ") --> " + s);
	}

	public static void main(String[] args) {
		System.out.println("MOVIES");

		Start();
		OpeningMenu();
	}

	// Quitting
	public static void End() {
		System.out.println("Ending the program.");
		sc.close();
		System.exit(0);
	}

	// Gets an integer from the user. Will continue to prompt until a valid integer
	// is given
	public static int getInputInt(String message, int min, int max) {
		boolean foundInt = false;
		int returnValue = 0;
		while (foundInt == false) {
			String input = sc.nextLine();

			try {
				returnValue = Integer.parseInt(input);
				if (returnValue <= max && returnValue >= min) {

					foundInt = true;
				} else {
					System.out.println("Error. Please try again.\n" + message);
				}
			} catch (Exception e) {
				System.out.println("Error. Please try again.\n" + message);

				continue;
			}
		}
		return returnValue;
	}

	// Gets a string from the user
	public static String getInputString(String message) {
		System.out.println(message);
		boolean foundString = false;
		String returnValue = sc.nextLine();

		while (foundString == false) {

			if (returnValue.length() > 0) {
				foundString = true;
				break;
			} else {
				System.out.println("Invalid. Can't be blank.\n" + message);
				continue;
			}

		}
		return returnValue;
	}

	public static double GetPercentWatchlistProgress(Watchlist watchlist, boolean display) {
		System.out.println("\n" + watchlist.watchlistName + " Progress");

		double timeWatched = 0;
		double totalTime = 0;

		for (int i = 0; i < watchlist.movies.size(); i++) {
			Movie curMovie = watchlist.movies.get(i);
			timeWatched += curMovie.minutesWatched;
			totalTime += curMovie.totalMinutes;
		}
		int percentWatchedInt = (int)((timeWatched / totalTime) * 1000);
		double percentWatched = (double)(percentWatchedInt)/10;

		if (display) {
			String progressBar = "";
			for (int i = 0; i < 50; i++) {
				if (i < percentWatched / 2) {
					progressBar += "|";
				} else {
					progressBar += ".";
				}
			}
			System.out.println(progressBar + "\n" + percentWatched + "%\n\n");
		}
		
		if(percentWatched > 100) percentWatched = 100;
		return percentWatched;
	}

	public static double GetPercentMovieProgress(Movie movie) {

		double timeWatched = movie.minutesWatched;
		double totalTime = movie.totalMinutes;

		int percentWatchedInt = (int)((timeWatched / totalTime) * 1000);
		double percentWatched = (double)(percentWatchedInt)/10;
		
		if(percentWatched > 100) percentWatched = 100;
		return percentWatched;
	}
	/*
	public static double ProgressMovie(Movie movie) {

		
		double timeWatched = movie.minutesWatched + 1;
		double totalTime = movie.totalMinutes;

		double percentWatched = timeWatched / totalTime / 100;
		while(percentWatched < 100) {
			timeWatched = movie.minutesWatched + 1;
			percentWatched = timeWatched / totalTime / 100;
		//	Delay(100);
		}
		return percentWatched;
	}
	*/
	
	public static void autosave() {
		
	}
}
