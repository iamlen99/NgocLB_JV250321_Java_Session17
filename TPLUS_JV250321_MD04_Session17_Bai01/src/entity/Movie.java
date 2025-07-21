package entity;

import java.time.LocalDate;

public class Movie {
    private int movieId;
    private String title;
    private String director;
    private int duration;
    private LocalDate releaseDate;
    private boolean status;

    public Movie() {
    }

    public Movie(int movieId, String title, String director, int duration, LocalDate releaseDate, boolean status) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Title: %s - Director: %s - Duration: %d - Release Date: %s - Status: %s", this.movieId
                , this.title, this.director, this.duration, this.releaseDate, this.status ? "Dang chieu" : "Da chieu");
    }
}
