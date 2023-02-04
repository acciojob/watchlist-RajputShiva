package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    //1    Add a movie: POST /movies/add-movie
// access:   localhost:8080/movies/add-movie
    @PostMapping("movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response= movieService.addMovie(movie);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }



    //2    Add a director: POST /movies/add-director
// access:   localhost:8080/movies/add-director
    @PostMapping("movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response= movieService.addDirector(director);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }



    //3    Pair an existing movie and director: PUT /movies/add-movie-director-pair
// access:   localhost:8080/movies/add-movie-director-pair?movieName=ABCD&directorName=Amar
    @PutMapping("movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        String response=movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }



//4    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//    localhost:8080/movies/get-movie-by-name/ABCD

    @GetMapping("movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        Movie movie=movieService.getMovieByName(movieName);
        return new ResponseEntity(movie,HttpStatus.FOUND);
    }



    //5    Get Director by director name: GET /movies/get-director-by-name/{name}
//  access:  localhost:8080/movies/get-director-by-name/Amar
    @GetMapping("movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
        Director director=movieService.getDirectorByName(directorName);
        return new ResponseEntity(director,HttpStatus.FOUND);
    }



    //6    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
// access:   localhost:8080/movies/get-movies-by-director-name/Amar
    @GetMapping("movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> movies=movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity(movies, HttpStatus.FOUND);
    }




    //7    Get List of all movies added: GET /movies/get-all-movies
// access:   localhost:8080/movies/get-all-movies
    @GetMapping("movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String > movies=movieService.findAllMovies();
        return new ResponseEntity(movies,HttpStatus.FOUND);
    }




    //    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
// access:   localhost:8080/movies/delete-director-by-name?directorName=Babar
    @DeleteMapping("movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        String response=movieService.deleteDirectorByName(directorName);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }



    //    Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//  access:  localhost:8080/movies/delete-all-directors
    @DeleteMapping("movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response=movieService.deleteAllDirectors();
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }



}