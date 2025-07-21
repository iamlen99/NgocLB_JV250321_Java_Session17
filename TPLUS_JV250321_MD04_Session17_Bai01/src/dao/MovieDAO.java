package dao;

import entity.Movie;

import java.util.List;

public interface MovieDAO {
    boolean save (Movie movie);

    boolean isExistId (int id);

    boolean update (Movie movie);

    Movie findById (int id);

    boolean deleteById (int id);

    List<Movie> findAll ();
}
