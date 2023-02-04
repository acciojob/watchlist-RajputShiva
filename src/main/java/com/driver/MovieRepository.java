package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String,Movie> movieDB=new HashMap<>();
    HashMap<String,Director>directorDB=new HashMap<>();
    HashMap<String, List<String >>pairDB=new HashMap<>();


    public String addMovie(Movie movie){
        String movieName=movie.getName();
        movieDB.put(movieName,movie);
        return "Movie added to the db";
    }



    public String addDirector(Director director){
        String directorName=director.getName();
        directorDB.put(directorName,director);
        return "Director add to the db";
    }



    public String addMovieDirectorPair(String movieName, String directorName){
        if(movieDB.containsKey(movieName)&& directorDB.containsKey(directorName)){
            if(pairDB.containsKey(directorName)){
                pairDB.get(directorName).add(movieName);
            }
            else{
                List<String>list=new ArrayList<>();
                list.add(movieName);
                pairDB.put(directorName,list);
            }
            return "Movie and Director paired successfully";
        }
        else{
            return "Movie or Director is not exist on database";
        }
    }



    public Movie getMovieByName(String name){
        return movieDB.get(name);
    }



    public Director getDirectorByName(String name){
        return directorDB.get(name);
    }



    public List<String> getMoviesByDirectorName(String name){
        return pairDB.get(name);
    }



    public List<String> findAllMovies(){
        List<String>movies=new ArrayList<>();
        for(String key: movieDB.keySet()){
            movies.add(key);
        }
        return movies;
    }


    public String deleteDirectorByName(String name){
        if(pairDB.containsKey(name)){
            List<String> list=pairDB.getOrDefault(name,new ArrayList<>());
            //remove movie of the director from movieDB
            for(String movie: list){
                if(movieDB.containsKey(movie)){
                    movieDB.remove(movie);
                }
            }
            //remove director from directorDB
            if(directorDB.containsKey(name)){
                directorDB.remove(name);
            }
            //removing movie director pair form pairDB
            pairDB.remove(name);

            return "Director and his movies removed from database";

        }
        return "Director not present in database";
    }


    public String deleteAllDirectors(){
        for(String directorName : pairDB.keySet()){
            List<String>movies=pairDB.get(directorName);
            //removing movies from movieDB
            for(String movie : movies){
                if(movieDB.containsKey(movie)){
                    movieDB.remove(movie);
                }
            }
            //removing director from director db
            if(directorDB.containsKey(directorName)){
                directorDB.remove(directorName);
            }
        }
        for(String director : directorDB.keySet()){
            directorDB.remove(director);
        }

        return "All directors and their movie is removed";
    }

}
