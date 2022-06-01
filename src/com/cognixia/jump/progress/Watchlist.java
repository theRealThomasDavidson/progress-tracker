package com.cognixia.jump.progress;

import java.util.ArrayList;

public class Watchlist {
	public String watchlistName; 
	public ArrayList<Movie> movies; 
	
	public Watchlist(String name){
		watchlistName = name; 
		movies = new ArrayList<Movie>();
	} 
	
	public void addMovie(Movie movie){
		movies.add(movie); 
	} 
	
	public void removeMovie(Movie movie){
		movies.remove(movie);
	}
	
} 