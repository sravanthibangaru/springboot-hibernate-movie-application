package com.example.movie.service;

import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.MovieJpaRepository;
import com.example.movie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.ArrayList;

@Service
public class MovieJpaService implements MovieRepository{

    @Autowired
    private MovieJpaRepository movieJpaRepository;

    @Override
     public ArrayList<Movie> getAllMovies(){
        List<Movie> movieList = movieJpaRepository.findAll();
        ArrayList<Movie> movies = new ArrayList<>(movieList);
        return movies;
     }

    @Override
    public Movie getMovieById(int movieId){
        try{
            Movie movie = movieJpaRepository.findById(movieId).get();
            return movie;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @Override
    public Movie addMovie(Movie movie){
        movieJpaRepository.save(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(int movieId, Movie movie){
        try{
            Movie existingMovie = movieJpaRepository.findById(movieId).get();
            if(movie.getMovieName() != null){
                existingMovie.setMovieName(movie.getMovieName());
            }
            if(movie.getLeadActor() != null){
                existingMovie.setLeadActor(movie.getLeadActor());
            }
            movieJpaRepository.save(existingMovie);
            return existingMovie;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public void deleteMovie(int movieId){
        try{
            movieJpaRepository.deleteById(movieId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }
    
}