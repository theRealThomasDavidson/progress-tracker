package com.cognixia.jump.progress;

import java.util.List;

public interface UserDAOInterface{

	public List<Movie> getAllMovies();
	public Movie getMovie(int movieId);
	public Movie getMovie(String name);
	public boolean addMovie(Movie movie);
	public boolean updateMovie(Movie movie);
	public boolean deleteMovie(int movie_id);
	
	public List<Watchlist> getAllWatchList(int userid);
	public Watchlist getWatchlist(int watchListId);
	public Watchlist getWatchlist(String watchListName);
	public boolean addWatchList(Watchlist watchlist);
	public boolean deletewatchList(int watchId);
	public boolean updateWatchList(Watchlist watchlist);
	
	public List<User> getAllUsers();
	public User getUser(int user_id);
	public User getUser(String username);
	public boolean addUser(User user);
	public boolean deleteUser(int userId);
	public boolean updateUser(User user);
	
	public List<UserMovie> getMyMovies(int userId);
	public UserMovie getUserMovie(int user_movie_id, int user_id);
	public boolean addUserMovie(UserMovie user_movie);
	public boolean deleteUserMovie(int userMovieId);
	public boolean updateUserMoie(UserMovie userMovie);
}