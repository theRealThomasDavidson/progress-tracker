package com.cognixia.jump.progress;

public class Movie { 
      private int min;//total runtime in minutes
      private String name; //movie name
      private String genre; // genre of movie 
      private String rating; //Rating for movie

      public Movie(int min, String name, String genre, String rating) {
        super();
        this.min = min;
        this.name = name;
        this.genre = genre; 
        this.rating = rating; 
      }

      public int getMin() {
        return min;
      }

      public void setMin(int min) {
        this.min = min;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getGenre() {
        return genre;
      }

      public void setGenre(String genre) {
        this.genre = genre;
      } 


      public String getRating() {
        return rating;
      }

      public void setRating(String rating) {
        this.rating = rating;
      }
       
      
}
