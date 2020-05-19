import java.util.Scanner;

public class Menu {
    public String menu(Scanner scanner){
        System.out.printf("\nInput the action (add, remove, import, export, ask, exit): \n");
       return scanner.nextLine();
           }
}
