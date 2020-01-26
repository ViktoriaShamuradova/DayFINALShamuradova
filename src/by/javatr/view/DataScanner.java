package by.javatr.view;

import java.util.Scanner;

public class DataScanner {
    private DataScanner() {
        sc = new Scanner(System.in);
    }

    private static final DataScanner instance = new DataScanner();

    public static DataScanner getInstance() {
        return instance;
    }

    private Scanner sc;

    public int enterIntegerFromConsole() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
        return sc.nextInt();
    }

    public String nextLine() {
        return sc.nextLine();
    }

    public float enterFloatFromConsole() {
        while (!sc.hasNextFloat()) {
            sc.next();
        }
        return sc.nextFloat();
    }

    public boolean enterBooleanFromConsole() {
        while (!sc.hasNextBoolean()) {
            sc.next();
        }
        return sc.nextBoolean();
    }
}
