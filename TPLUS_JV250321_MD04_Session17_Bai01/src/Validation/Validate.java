package Validation;

import java.util.Scanner;

public class Validate {
    public static boolean isEmpty(String data) {
        return data == null || data.trim().isEmpty();
    }

    public static boolean isInteger (String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int inputValidInteger (Scanner scanner, String message) {
        System.out.print(message);
        do {
            String input = scanner.nextLine();
            if (isInteger(input)) {
                return Integer.parseInt(input);
            }
            System.err.println("Vui long nhap vao 1 so nguyen:");
        } while (true);
    }
}
