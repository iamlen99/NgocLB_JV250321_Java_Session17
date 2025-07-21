package presentation;

import Validation.Validate;
import business.MovieBusiness;
import business.impl.MovieBusinessImpl;
import entity.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MoviePresentation {
    public final MovieBusiness movieBusiness;

    public MoviePresentation() {
        movieBusiness = new MovieBusinessImpl();
    }

    public void displayMovieMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println("1. Them phim moi");
            System.out.println("2. Cap nhat thong tin phim");
            System.out.println("3. Xoa phim");
            System.out.println("4. Hien thi danh sach phim");
            System.out.println("5. Thoat");

            int choice = Validate.inputValidInteger(scanner, "Lua chon cua ban: ");

            switch (choice) {
                case 1:
                    Movie movie = inputData(scanner);
                    movieBusiness.addMovie(movie);
                    break;

                case 2:
                    updateMovie(scanner);
                    break;

                case 3:
                    deleteMovie(scanner);
                    break;

                case 4:
                    movieBusiness.displayAllMovies();
                    break;

                case 5:
                    isExit = true;
                    break;

                default:
                    System.out.println("Vui long chon tu 1-5");
            }
        } while (!isExit);
    }

    public Movie inputData(Scanner scanner) {
        Movie movie = new Movie();
        movie.setTitle(inputTitle(scanner, "Nhap tieu de phim:"));
        movie.setDirector(inputDirector(scanner, "Nhap dao dien cua phim:"));
        movie.setDuration(inputDuration(scanner, "Nhap thoi gian phim (phut):"));
        movie.setReleaseDate(inputReleaseDate(scanner, "Nhap ngay phat hanh (dd/MM/yyyy):"));
        movie.setStatus(inputStatus(scanner, "Nhap trang thai phim (true/false):"));
        return movie;
    }

    public String inputTitle(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String title = scanner.nextLine();
            if (!Validate.isEmpty(title)) {
                return title;
            }
            System.err.println("Tieu de phim khong duoc de trong, vui long nhap tieu de phim:");
        } while (true);
    }

    public String inputDirector(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String director = scanner.nextLine();
            if (!Validate.isEmpty(director)) {
                return director;
            }
            System.err.println("Dao dien phim khong duoc de trong, vui long nhap dao dien phim:");
        } while (true);
    }

    public int inputDuration(Scanner scanner, String message) {
        System.out.println(message);
        do {
            try {
                int duration = Integer.parseInt(scanner.nextLine());
                if (duration > 0) {
                    return duration;
                }
                System.err.println("Thoi gian phim phai la so nguyen duong, vui long nhap lai:");
            } catch (NumberFormatException e) {
                System.err.println("Vui long nhap thoi gian phim la so nguyen:");
            }
        } while (true);
    }

    public LocalDate inputReleaseDate(Scanner scanner, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(message);
        do {
            try {
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Dinh dang ngay khong hop le, vui long nhap lai:");
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String status = scanner.nextLine();
            if (status.equalsIgnoreCase("true")) {
                return true;
            } else if (status.equalsIgnoreCase("false")) {
                return false;
            }
            System.out.println("Vui long nhap trang thai phim la \"true\" hoac \"false\" ");

        } while (true);
    }

    public void updateMovie(Scanner scanner) {
        int updatedId = Validate.inputValidInteger(scanner, "Nhap id cua phim can cap nhat: ");
        Movie movieToUpdate = movieBusiness.getMovieById(updatedId);
        if (movieToUpdate != null) {
            boolean isExit = false;
            do {
                System.out.println("1. Cap nhat tieu de phim");
                System.out.println("2. Cap nhat dao dien phim");
                System.out.println("3. Cap nhat thoi gian phim");
                System.out.println("4. Cap nhat ngay phat hanh");
                System.out.println("5. Cap nhat trang thai phim");
                System.out.println("6. Thoat");
                int choice = Validate.inputValidInteger(scanner, "Lua chon cua ban: ");

                switch (choice) {
                    case 1:
                        String newTitle = inputTitle(scanner, "Nhap tieu de moi:");
                        movieToUpdate.setTitle(newTitle);
                        break;

                    case 2:
                        String newDirector = inputDirector(scanner, "Nhap dao dien moi:");
                        movieToUpdate.setDirector(newDirector);
                        break;

                    case 3:
                        int newDuration = inputDuration(scanner, "Nhap thoi gian phim moi:");
                        movieToUpdate.setDuration(newDuration);
                        break;

                    case 4:
                        LocalDate newReleaseDate = inputReleaseDate(scanner, "Nhap ngay phat hanh moi:");
                        movieToUpdate.setReleaseDate(newReleaseDate);
                        break;

                    case 5:
                        movieToUpdate.setStatus(!movieToUpdate.isStatus());
                        break;

                    case 6:
                        isExit = true;
                        break;

                    default:
                        System.out.println("Vui long chon tu 1-6");
                }
            } while (!isExit);

            movieBusiness.updateMovie(movieToUpdate);
        } else {
            System.err.println("Id ban nhap khong ton tai");
        }

    }

    public void deleteMovie(Scanner scanner) {
        int deleteId = Validate.inputValidInteger(scanner, "Nhap id cua phim can xoa:");
        if (movieBusiness.isExistId(deleteId)) {
            movieBusiness.deleteMovie(deleteId);
        } else {
            System.err.println("Id ban nhap khong ton tai");
        }
    }
}
