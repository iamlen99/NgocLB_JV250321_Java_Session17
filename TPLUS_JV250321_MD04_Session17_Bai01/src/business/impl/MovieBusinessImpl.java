package business.impl;

import business.MovieBusiness;
import dao.MovieDAO;
import dao.impl.MovieDAOImpl;
import entity.Movie;

import java.util.List;

public class MovieBusinessImpl implements MovieBusiness {
    public final MovieDAO movieDAO;

    public MovieBusinessImpl() {
        movieDAO = new MovieDAOImpl();
    }

    @Override
    public void addMovie(Movie movie) {
        if(movieDAO.save(movie)) {
            System.out.println("Them phim thanh cong");
        } else {
            System.out.println("Co loi trong qua trinh them phim");
        }
    }

    @Override
    public boolean isExistId(int id) {
        return movieDAO.isExistId(id);
    }

    @Override
    public void updateMovie(Movie movie) {
        if(movieDAO.update(movie)) {
            System.out.println("Cap nhat phim thanh cong");
        } else {
            System.out.println("Co loi trong qua trinh cap nhat phim");
        }
    }

    @Override
    public Movie getMovieById(int id) {
        return movieDAO.findById(id);
    }

    @Override
    public void deleteMovie(int id) {
        if (movieDAO.deleteById(id)) {
            System.out.println("Xoa phim thanh cong");
        } else {
            System.out.println("Co loi trong qua trinh xoa phim");
        }
    }

    @Override
    public void displayAllMovies() {
        movieDAO.findAll().forEach(System.out::println);
    }
}
