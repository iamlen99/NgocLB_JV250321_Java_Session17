package dao.impl;

import dao.MovieDAO;
import entity.Movie;
import ulti.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    @Override
    public boolean save(Movie movie) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_movie(?, ?, ?, ?, ?)}");
            callSt.setString(1, movie.getTitle());
            callSt.setString(2, movie.getDirector());
            callSt.setInt(3, movie.getDuration());
            callSt.setDate(4, Date.valueOf(movie.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            callSt.setBoolean(5, movie.isStatus());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean isExistId(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_exist_id(?, ?)}");
            callSt.setInt(1, id);
           callSt.registerOutParameter(2, Types.INTEGER);
           callSt.execute();
           int count = callSt.getInt(2);
           return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_movie(?, ?, ?, ?, ?, ?)}");
            callSt.setInt(1, movie.getMovieId());
            callSt.setString(2, movie.getTitle());
            callSt.setString(3, movie.getDirector());
            callSt.setInt(4, movie.getDuration());
            callSt.setDate(5, Date.valueOf(movie.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            callSt.setBoolean(6, movie.isStatus());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Movie findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Movie movie = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_movie_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setMovieId(rs.getInt(1));
                movie.setTitle(rs.getString(2));
                movie.setDirector(rs.getString(3));
                movie.setDuration(rs.getInt(4));
                movie.setReleaseDate(LocalDate.parse(rs.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                movie.setStatus(rs.getBoolean(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return movie;
    }

    @Override
    public boolean deleteById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_movie_by_id(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Movie> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Movie> movies = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select * from movies";
            callSt = conn.prepareCall(sql);
            ResultSet rs = callSt.executeQuery();
            movies = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt(1));
                movie.setTitle(rs.getString(2));
                movie.setDirector(rs.getString(3));
                movie.setDuration(rs.getInt(4));
                movie.setReleaseDate(LocalDate.parse(rs.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                movie.setStatus(rs.getBoolean(6));
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return movies;
    }
}
