package sample;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleMain {

    public static void main(String[] args) throws IOException {

        MyGymManager myGymManager = new MyGymManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gym Management system.....");

        // infinite loop for getting the users correct value...
        label:
        while(true){
            System.out.println("\n==============Select an option to continue the Program==============");
            System.out.println("\nAdd a New Member       :  1\nView Members           :  2 \nDelete Members         :  3\nSave/Write to a file   :  4 \nView Table             :  5\nExit the Program       :  6");
            System.out.println("--------------------------------------------------------------------");
            System.out.print("\nEnter the option  : ");
            String userInput = scanner.nextLine().toLowerCase();

            // getting the validations
            if ("1".equals(userInput)) {
                myGymManager.addMember(userInput);
            } else if ("2".equals(userInput)) {
                myGymManager.viewMember();
            } else if ("3".equals(userInput)) {
                myGymManager.deleteMember();
            } else if ("4".equals(userInput)) {
                myGymManager.saveFile();
            } else if ("5".equals(userInput)) {
                myGymManager.viewTable();
            } else if ("6".equals(userInput)) {
                System.out.println("\nProgram will quit......\nBye!");
                break label;
            } else {
                System.out.println("Entered value is not Valid. Please enter a valid input.");
            }
        }
    }
}