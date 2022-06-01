package com.cognixia.jump.progress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognixia.jump.jdbc.dao.Department;


public class DAO implements UserDAOInterface{
	
	private Connection conn = ConnectionManager.getConnection();

	@Override
	public List<Movie> getAllMovies() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM movie");
			
			List<Movie> movieList = new ArrayList<Movie>();
			
			while(rs.next()) {
				int id = rs.getInt("movie_id");
				String name = rs.getString("movie_name");
				String rating = rs.getString("movie_rating");
				Date release = rs.getDate("movie_release_date");
				int duration = rs.getInt("movie_time_minutes");
				double cost = rs.getDouble("movie_cost");
				
				Movie tempMovie = new Movie( id, name, rating, release, duration,cost );
				movieList.add(tempMovie);
				
			}
			
			return movieList;
			
			
		} catch(SQLException e) {
			System.out.println("Could NOT retrieve list of departments from D/B :(");
		}
		
		return null;
	}

	@Override
	public Movie getMovie(int movieId) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM department WHERE dept_id = ?");
			pstmt.setInt(1, movieId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("movie_id");
				String name = rs.getString("movie_name");
				String rating = rs.getString("movie_rating");
				Date release = rs.getDate("movie_release_date");
				int duration = rs.getInt("movie_time_minutes");
				double cost = rs.getDouble("movie_cost");
			
				Movie movie = new Movie( id, name, rating, release, duration,cost );
				return dept;
			}
			
			
			
		}catch(SQLException e) {
			System.out.println("Could NOT locate the department record with id ==> " + deptId);
		}
		
		
		return null;
	}

	@Override
	public Movie getMovie(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMovie(int movie_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Watchlist> getAllWatchList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Watchlist getWatchlist(int watchListId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Watchlist getWatchlist(String watchListName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addWatchList(Watchlist watchlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletewatchList(int watchId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateWatchList(Watchlist watchlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserMovie> getMyMovies(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserMovie getUserMovie(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserMovie(UserMovie user_movie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserMovie(int userMovieId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserMoie(UserMovie userMovie) {
		// TODO Auto-generated method stub
		return false;
	}

}