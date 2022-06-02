package com.cognixia.jump.progress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
			System.out.println("Could NOT retrieve list of movie from D/B :(");
		}
		
		return null;
	}

	@Override
	public Movie getMovie(int movieId) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM movie WHERE dept_id = ?");
			pstmt.setInt(1, movieId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("movie_id");
				String name = rs.getString("movie_name");
				String rating = rs.getString("movie_rating");
				Date release = rs.getDate("movie_release_date");
				int duration = rs.getInt("movie_time_minutes");
				double cost = rs.getDouble("movie_cost");
			
				Movie movie = new Movie(name, rating, release, duration,cost );
				return movie;
			}
			
			
			
		}catch(SQLException e) {
			System.out.println("Could NOT locate the movie record with id ==> " + movieId);
		}
		
		
		return null;
	}

	@Override
	public Movie getMovie(String name) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM movie WHERE movie_name = ?");
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("movie_id");
				String movie_name = rs.getString("movie_name");
				String rating = rs.getString("movie_rating");
				Date release = rs.getDate("movie_release_date");
				int duration = rs.getInt("movie_time_minutes");
				double cost = rs.getDouble("movie_cost");
			
				Movie movie = new Movie(id, movie_name, rating, release, duration,cost );
				return movie;
			}
			
			
			
		}catch(SQLException e) {
			System.out.println("Could NOT locate the movie record with name ==> " + name);
		}
		
		
		return null;
	}

	@Override
	public boolean addMovie(Movie movie) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO movie(movie_id, movie_name, movie_rating, movie_release_date, movie_time_minutes, movie_cost) VALUES(1, ?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, movie.getname());
			pstmt.setString(2, movie.getName());
			pstmt.setString(3, movie.getPhone());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		
		} catch(SQLException e) {
			System.out.println("Could NOT insert new movie record :(");
		}
		
		
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
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("select * from `user`;");
			
			ResultSet rs = pstmt.executeQuery();
			List<User> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("user_username");
				String firstName = rs.getString("user_firstName");
				String lastName = rs.getString("user_lastName");
				String email = rs.getString("user_email");
				String password = rs.getString("user_password");
				boolean isAdmin = rs.getString("isadmin") == "n";
				list.add(new User(id, firstName, lastName, email, password));
			}
			return list;
			
			
		
		} catch(SQLException e) {
			System.out.println("Could NOT get My Movies :(");
		}
		return null;
	}

	@Override
	public User getUser(int user_id) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("select * from `user` where user_id = ?;");
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("user_username");
				String firstName = rs.getString("user_firstName");
				String lastName = rs.getString("user_lastName");
				String email = rs.getString("user_email");
				String password = rs.getString("user_password");
				boolean isAdmin = rs.getString("isadmin") == "n";
				return new User(id, firstName, lastName, email, password);
			}		
		
		} catch(SQLException e) {
			System.out.println("Could NOT get My Movies :(");
		}
		return null;
	}

	@Override
	public User getUser(String username) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("select * from `user` where user_username = ?;");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String firstName = rs.getString("user_firstName");
				String lastName = rs.getString("user_lastName");
				String email = rs.getString("user_email");
				String password = rs.getString("user_password");
				boolean isAdmin = rs.getString("isadmin") == "n";
				return new User(id, firstName, lastName, email, password);
			}		
		
		} catch(SQLException e) {
			System.out.println("Could NOT get My Movies :(");
		}
		return null;
	}
	}

	@Override
	public boolean addUser(User user) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `user`"
					+ "(user_id, user_username, user_firstName, user_lastName, user_email) "
					+ "VALUES (?, ?, ?, ?);");
			
			pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setString(5, user.getEmail());
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Could NOT insert new user record :(");
		}
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("delete from `user` where user_id = ?;");
			
			pstmt.setInt(1, userId);
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Could NOT find user record :(");
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("update `user` set \r\n"
					+ "	user_username=?,\r\n"
					+ "    user_firstName=?,\r\n"
					+ "    user_lastName = ?,\r\n"
					+ "    user_password = ?,\r\n"
					+ "    user_email = ?\r\n"
					+ "where user_id = ?;\r\n");
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getEmail());
			pstmt.setInt(6, user.getId());
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Could NOT update user record :(");
		}
		return false;
	}

	@Override
	public List<UserMovie> getMyMovies(int user_id) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("select movie_name, \r\n"
					+ "         movie.movie_id,\r\n"
					+ "			user_movie.movie_progress_minutes as watch_time, \r\n"
					+ "            movie.movie_time_minutes as total_time,\r\n"
					+ "            movie.movie_rating as movie_rating,\r\n"
					+ "            user_movie.user_id as user_id,\r\n"
					+ "            user_movie.movie_starRating as rating from user_movie \r\n"
					+ "join `user` on `user`.user_id=user_movie.user_id\r\n"
					+ "join movie on movie.movie_id=user_movie.movie_id\r\n"
					+ "where `user`.user_id = ?;");
			
			pstmt.setInt(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<UserMovie> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("movie_id");
				int movie_id = rs.getInt("movie_id");
				String movie_name = rs.getString("movie_name");
				String rating = rs.getString("movie_rating");
				int duration = rs.getInt("total_time");
				int watch_time = rs.getInt("watch_time");
				list.add(new UserMovie(id, user_id, movie_id, movie_name, rating, duration, watch_time));
				
			}
			return list;
			
			
		
		} catch(SQLException e) {
			System.out.println("Could NOT get My Movies :(");
		}
		return null;
	}

	@Override
	public UserMovie getUserMovie(int user_movie_id, int user_id) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("select movie_name, \r\n"
					+ "         movie.movie_id,\r\n"
					+ "			concat(`user`.user_firstName, \" \", `user`.user_lastName) as 'name', \r\n"
					+ "			user_movie.movie_progress_minutes as watch_time, \r\n"
					+ "            movie.movie_time_minutes as total_time,\r\n"
					+ "            movie.movie_rating as movie_rating,\r\n"
					+ "            user_movie.movie_starRating as rating from user_movie \r\n"
					+ "join `user` on `user`.user_id=user_movie.user_id\r\n"
					+ "join movie on movie.movie_id=user_movie.movie_id\r\n"
					+ "where `user`.user_id = ? and user_movie.mtm_id=?;");
			
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, user_movie_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<UserMovie> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("movie_id");
				String movie_name = rs.getString("movie_name");
				int movie_id = rs.getInt("movie_id");
				String rating = rs.getString("movie_rating");
				int duration = rs.getInt("total_time");
				int watch_time = rs.getInt("watch_time");
				return new UserMovie(id, user_id, movie_id, movie_name, rating, duration, watch_time)
			}		
		
		} catch(SQLException e) {
			System.out.println("Could NOT get My Movies :(");
		}
		return null;
	}

	@Override
	public boolean addUserMovie(UserMovie user_movie) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("insert into `user_movie`"
					+ "(user_id, movie_id, movie_progress_minutes, movie_starRating) "
					+ "values(?, ?, ?, ?);");
			
			pstmt.setInt(1, user_movie.getUserId());
			pstmt.setInt(2, user_movie.getMovieId());
			pstmt.setInt(3, user_movie.getMovieProgress());
			pstmt.setString(4, user_movie.getMovieStarRating());
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Could NOT insert new movie record :(");
		}
		return false;
	}

	@Override
	public boolean deleteUserMovie(int userMovieId) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("delete from `user_movie` where mtm_id = ?;");
			
			pstmt.setInt(1, userMovieId);
			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Could NOT find this record :(");
		}
		return false;
	}

	@Override
	public boolean updateUserMovie(UserMovie user_movie) {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("update `user_movie` set \r\n"
					+ "	user_id=?,\r\n"
					+ "    movie_id=?,\r\n"
					+ "    movie_progress_minutes = ?,\r\n"
					+ "    movie_starRating = ?\r\n"
					+ "where mtm_id = ?;");
			
			pstmt.setInt(1, user_movie.getUserId());
			pstmt.setInt(2, user_movie.getMovieId());
			pstmt.setInt(3, user_movie.getMovieProgress());
			pstmt.setString(4, user_movie.getMovieStarRating());
			pstmt.setInt(5, user_movie.getUserMovieId());
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Could NOT update this user's movie record :(");
		}
		return false;
	}

}