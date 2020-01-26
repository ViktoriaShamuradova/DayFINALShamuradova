package by.javatr;


import by.javatr.controller.Controller;
import by.javatr.view.DataScanner;
import by.javatr.view.View;

public class Main {
    private static DataScanner scanner = DataScanner.getInstance();
    private static Controller controller = Controller.getInstance();
    private static View view = View.getInstance();

    public static void main(String[] args) {
        System.out.println(view.enterOrRegistration());
        int request = scanner.enterIntegerFromConsole();
        System.out.println(controller.executeEnterOrRegistration(request));

        boolean startMenu = true;
        while (startMenu) {
            int request2 = scanner.enterIntegerFromConsole();
            System.out.println(controller.executeMenu(request2));
            if (request2 == 0) {
                startMenu = false;
            }
        }
        int request3 = scanner.enterIntegerFromConsole();
        System.out.println(controller.executeEnterOrRegistration(request3));

    }

}
