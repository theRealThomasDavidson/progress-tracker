package com.cognixia.jump.progress;

import java.util.Scanner;

public class menus {
	private static Scanner sc;

	private static boolean started = false;

	public static void Start() {
		if (started)
			return;
		started = true;

		sc = new Scanner(System.in);
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
		System.out.println("\nMenu\n");

		DisplayMenuOption("Movies", 1);
		DisplayMenuOption("Watch Lists", 2);
		DisplayMenuOption("Log Out", 3);
		DisplayMenuOption("Quit", 4);

		int input = getInputInt("", 1, 4);

		if (input == 1) {
			MoviesMenu();
		}
		if (input == 2) {
			WatchlistMenu();
		}
		if (input == 3) {
			OpeningMenu();
		}
		if (input == 4) {
			End();
		}
	}

	public static void MoviesMenu() {
		// print all movies
		MainMenu();
	}

	public static void WatchlistMenu() {
		DisplayMenuOption("View Watchlists", 1);
		DisplayMenuOption("Create Watchlist", 2);
		DisplayMenuOption("Edit Watchlist", 3);
		DisplayMenuOption("Delete Watchlist", 4);
		DisplayMenuOption("Back", 5);
		DisplayMenuOption("Quit", 6);

		int input = getInputInt("", 1, 6);

		if (input == 1) {
			DisplayWatchlists();
		}
		if (input == 2) {
			CreateWatchlist();
		}
		if (input == 3) {
			EditWatchlist();
		}
		if (input == 4) {
			DeleteWatchlist();
		}
		if (input == 5) {
			MainMenu();
		}
		if (input == 6) {
			End();
		}
	}

	public static void DisplayWatchlists() {
		//Make sure to include the % progress!!
	}
	
	public static void CreateWatchlist() {

	}
	public static void EditWatchlist() {

	}
	public static void DeleteWatchlist() {

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

	/*--------------------------------------------------------------------------------------------
	  ------------------------------------ WATCHLIST METHOD --------------------------------------
	  --------------------------------------------------------------------------------------------
	
	 
	 
	 
	  public static double GetPercentProgress(Watchlist watchlist, boolean display) {
	 
		
		double timeWatched = 0;
		double totalTime = 0;
		
		for each movie in the watchlist
	 		timeWatched += movie time watched (minutes)
			totalTime += movie runtime total (minutes)
		end for loop
		
		double percentWatched = timeWatched / total time / 100, to nearest hundredth
		
		if(display){
			String progressBar = "";
			for(int i = 0; i < 100; i++){
				if(i < percentWatched + 0.5){
					progressBar += "|";
				}
				else{
					progressBar += ".";
				}
			}
			print ("Progress on the " + watchlist.watchlist_name + " watch list:\n\n + progressBar + "\n" + (percentWacthed) + "%\n\n";
		}
		
		
		return percentWatched;
		} end method bracket
		
		
		
		
	  --------------------------------------------------------------------------------------------
	  ----------------------------------- END WATCHLIST METHOD -----------------------------------
	  --------------------------------------------------------------------------------------------
	*/
}