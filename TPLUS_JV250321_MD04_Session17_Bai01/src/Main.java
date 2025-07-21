
import presentation.MoviePresentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MoviePresentation moviePresentation = new MoviePresentation();

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("********** Movie Management **********");
            System.out.println("1. Quản lý phim");
            System.out.println("2. Quản lý lịch chiếu phim");
            System.out.println("3. Quản lý vé");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    moviePresentation.displayMovieMenu(scanner);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Nhập vào số nguyên 1-4");
            }
        } while (true);
    }
}
