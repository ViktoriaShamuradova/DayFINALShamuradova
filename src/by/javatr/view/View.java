package by.javatr.view;

import by.javatr.bean.User;

public class View {
    private View() {
        scanner = DataScanner.getInstance();
    }

    private static final View instance = new View();

    public static View getInstance() {
        return instance;
    }

    private DataScanner scanner;

    public String enterOrRegistration() {
        return ("Please, choose an action. press: \n"
                + "1 Enter \n"
                + "2 Registration \n"
                + " press 0 for exit \n"
                + " AFTER CHOOSING AN OPTION PLEASE PRESS ENTER");
    }

    public String menu(User user) {
        if (user.getType().equals("admin")) {
            return "Please, choose an action. press: \n"
                    + "1 add a new book in library \n"
                    + "2 all books in library \n"
                    + "3 delete a book from library \n"
                    + "4 search a book in library by name\n"
                    + "5 search a book in library by id\n"
                    + "6 replace the book in library \n"
                    + "7 registration a new admin \n" //регистрация нового пользователя
                    + "8 see all clients \n"
                    + "9 see all admins \n"
                    + "10 delete admin by login \n"
                    + "11 view all issued books \n"
                    + " press 0 for exit \n"
                    + " AFTER CHOOSING AN OPTION PLEASE PRESS ENTER";
        } else { //if(user.getType().equals("user"))
            return "Please, choose an action. press: \n"

                    + "1 take a book for reading \n"
                    + "2 return a book in library  \n"
                    + "3 search a book in library \n"
                    + "4 show bibliography \n"
                    + " press 0 for exit \n"
                    + " AFTER CHOOSING AN OPTION PLEASE PRESS ENTER";
        }
    }

    public String[] enterDataBook() {
        System.out.println("Please, enter name of book");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Please, enter a books rating from 0 to 10");
        float rating = scanner.enterFloatFromConsole();
        String ratingStr = Float.toString(rating);
        System.out.println("Please, enter is BestSeller");
        boolean isBestSeller = scanner.enterBooleanFromConsole();
        String isBestSellerStr = Boolean.toString(isBestSeller);
        return new String[]{name, ratingStr, isBestSellerStr};
    }

    public String enterAdminLogin() {
        System.out.println("Please, enter admin's login");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public String enterNameBook() {
        System.out.println("Please, enter a name of book");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public int enterIdBook() {
        System.out.println("Please, id book");
        scanner.nextLine();
        return scanner.enterIntegerFromConsole();
    }
    public int enterIdBookTakeForReading() {
        System.out.println("Please, id book");
        return scanner.enterIntegerFromConsole();
    }
    public String enterLogin() {
        System.out.println("Please enter login and password");
        System.out.println("Login");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public String[] enterNewPassword() {
        String[] passwords = new String[2];
        System.out.println("Please enter password");
        String password1 = scanner.nextLine();
        System.out.println("Please enter password again");
        String password2 = scanner.nextLine();
        passwords[0] = password1;
        passwords[1] = password2;
        return passwords;
    }
    public String[] enterSignIn(){
        String[] loginAndPassword = new String[2];
        System.out.println("Please, enter login");
        scanner.nextLine();
        loginAndPassword[0] = scanner.nextLine();
        System.out.println("Password");
        loginAndPassword[1] = scanner.nextLine();
        return loginAndPassword;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
