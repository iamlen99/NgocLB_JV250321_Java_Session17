package business;

import entity.Movie;

public interface MovieBusiness {
    void addMovie(Movie movie);

    boolean isExistId(int id);

    void updateMovie(Movie movie);

    Movie getMovieById(int id);

    void deleteMovie(int id);

    void displayAllMovies();
}
