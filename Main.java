import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Reservation> Reservations = new ArrayList<>();
    static ReservationSystem a = new ReservationSystem(Reservations);
    
    /**
     * Method Name: Main
     * Purpose: Call displayMenu
     * @param args      
     */
    public static void main(String[] args) {      
        displayMenu();
    }
    
    /**
     * Method Name: displayMenu
     * Purpose: Display the menu to the user and get user input to call desired method
     */
    public static void displayMenu() {
        Scanner sc = new Scanner(System.in);
        final int END = 7;
        int choice = 0;
        try {
            while (choice > 7 || choice < 1) {
                System.out.println("Welcome to the Hotel Reservation System! Please select and option.");
                System.out.println("1) Upload Reservations");
                System.out.println("2) New Reservation");
                System.out.println("3) Update Reservation");
                System.out.println("4) View All Existing Reservations");
                System.out.println("5) Find Reservation");
                System.out.println("6) Download Statistics");
                System.out.println("7) EXIT");
                choice = sc.nextInt();
                if (choice == END) {
                    return;
                }
                if (choice > 7 || choice < 1) {
                    System.err.println("Not a valid option, please try again.");
                }
            } 
        } 
        catch (InputMismatchException e) {
                System.err.println("Not a valid option, please try again.");
                displayMenu();
        }
        
        switch (choice) {
            case 1:
                a.uploadReservations(sc);
                System.out.println("\n");
                displayMenu();
                break;
            case 2:
                a.addReservation(sc);
                System.out.println("\n");
                displayMenu();
                break;
            case 3: 
                a.updateReservation(sc);
                System.out.println("\n");
                displayMenu();
                break;
            case 4: 
                a.viewReservations();
                System.out.println("\n");
                displayMenu();
                break;
            case 5:
                a.findReservation();
                System.out.println("\n");
                displayMenu();
                break;
            case 6:
                a.downloadReservations();
                System.out.println("\n");
                displayMenu();
                break;
        }
    }

}
