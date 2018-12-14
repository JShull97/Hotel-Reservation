import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem extends Reservation {
    List<Reservation> Reservations;
    String fileName;
    int id;
    
    /**
    * ReservationSystem constructor
     * @param Reservations
    */
    public ReservationSystem(List<Reservation> Reservations) {
        this.Reservations = Reservations;
        this.id = 1;
    }
    
     /**
     * Method Name: addReservation
     * Purpose: get user input to add a new reservation to the array of Reservations
     * @param sc 
     */
    public void addReservation(Scanner sc) {
        String LName;
        String FName;
        int nights;
        double rate;
        try {
            System.out.println("Enter last name");
            sc.nextLine();
            LName = sc.nextLine();
            System.out.println("Enter first name");
            FName = sc.nextLine();
            System.out.println("Enter number of nights");
            nights = sc.nextInt();
            System.out.println("Enter the daily rate");
            rate = sc.nextDouble();
            try {
                addReservation(LName, FName, nights, rate);
            }
            catch (NullPointerException e) {
                addReservation(LName, FName, nights, rate);
            }  
        }
        catch (InputMismatchException e) {
            System.err.println("Not valid input, try again");
            addReservation(sc);
        }
            
    }
    
    /**
     * Method Name: addReservation
     * Purpose: Make a new reservation and add it to the array of reservations
     * @param lName
     * @param fName
     * @param numOfNights
     * @param dailyRate 
     */
    public void addReservation(String lName, String fName, int numOfNights, double dailyRate) {
        int IDNum = this.id;
        Reservation temp = new Reservation(IDNum, lName, fName, numOfNights, dailyRate);
        Reservations.add(temp);
        nextID();
    }
    
    /**
     * Method Name: nextID
     * Purpose: Take the most recent inputted ID and increase it by one
     * @param IDNum 
     */
    public void nextID() {
        this.id++;
    }
    
    /**
     * Method Name: uploadReservations
     * Purpose: Prompt user for file path and load records into Reservations array
     * @param sc
     */
    public void uploadReservations(Scanner sc) {
        try {
            String line = null;
            try {
                System.out.println("Enter file name for uploaded reservations   EX.'C:\\Users\\username\\Desktop\\test.txt'");
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.err.println("Not valid input, try again");
                uploadReservations(sc);
            }
            String filePath = sc.nextLine();
            this.fileName = filePath;
            BufferedReader in = new BufferedReader(
                                new FileReader(filePath));           
            while ((line = in.readLine()) != null) {
                int i;
                String[] pieces = new String[5];
                for (i = 0; i < 4; i++) {
                    pieces = line.split("\\s+");
                }
                String LName = pieces[i - 3];
                String FName = pieces[i - 2];
                int nights = parseInt(pieces[i - 1]);
                double rate = parseDouble(pieces[i]);
                addReservation(LName, FName, nights, rate);
            }
            System.out.println("File Uploaded");
        }
        catch (IOException e) {
            System.err.println("IOException thrown");
        }       
    }
    
    /**
     * Method Name: updateReservation
     * Purpose: Get the ID of desired reservation and call findReservation
     * @param sc 
     */
    public void updateReservation(Scanner sc) {
        try {
            System.out.println("Enter the ID of the reservation you would like to update");
            sc.nextLine();
        }
        catch (InputMismatchException e) {
            System.err.println("Not valid input, try again");
        }
        int searchID = sc.nextInt();
        findReservation(searchID, sc);        
        System.out.println("Reservation Updated\n");      
    }
    
    /**
     * Method Name: findReservation
     * Purpose: Loop through Reservation array to find the desired entry and update it
     * @param searchID
     * @param sc 
     */
    public void findReservation(int searchID, Scanner sc) {
        int i = 0;
        for (Reservation Reservation : Reservations) {
            int n = Reservation.getID();
            if (n == searchID) {
                try {
                    System.out.println("Update Last Name: ");
                    sc.nextLine();
                    Reservation.lName = sc.nextLine();
                    System.out.println("Update First Name: ");
                    Reservation.fName = sc.nextLine();
                    System.out.println("Update Number of nights: ");
                    Reservation.numOfNights = sc.nextInt();
                    System.out.println("Update Daily Rate: ");
                    Reservation.dailyRate = sc.nextDouble();
                }
                catch (InputMismatchException e) {
                    System.err.println("Not valid input, try again");
                }
            }
            i++;
            if (i > Reservations.size()) {
                System.err.println("Entry not found");
            }
        }
    }
    
    /**
     * Method Name: findReservation
     * Purpose: Get the name of the reservation holder and find that reservation in the array
     */
    public void findReservation() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the last name of the reservation holder ");
            String LName = sc.nextLine();
            System.out.println("Enter the first name of the reservation holder ");
            String FName = sc.nextLine();
            for (Reservation Reservation : Reservations) {
                if (Reservation.lName.equalsIgnoreCase(LName) && Reservation.fName.equalsIgnoreCase(FName)) {
                    print(Reservation);
                    return;
                }
            }
        }
        catch (InputMismatchException e) {
            System.err.println("Not valid input, try again");
        }
        System.err.println("No Reservation found under that name");
    }
    
    /**
     * Method Name: downloadReservations
     * Purpose: Download the Reservation array to a file along with other statistics
     */
    public void downloadReservations() {  
        createStatsFile();
        try {
            File reservationFile = new File(this.fileName);
            reservationFile.createNewFile();
            PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(reservationFile, true))
            , true);
            for (int i = 0; i <= Reservations.size() - 1; i++) {
                String record = printToFile(Reservations.get(i));
                out.print(record);
                out.print(" Total Cost: $");
                out.printf("%.2f", getTotalCost(Reservations.get(i)));
                out.println("");
            }
            out.println("Longest Stay: " + getLongestStay() + " nights");       
            System.out.println("File Downloaded");
            out.close();
        }
        catch (IOException e) {
            System.err.println("File not found. Try again");
            downloadReservations();
        }
    }
    
    /**
     * Method Name: createStatsFile
     * Purpose: Create the statistics file
     */
    public void createStatsFile() {
        int index = this.fileName.length() - 4;
        this.fileName = this.fileName.substring(0, index);
        this.fileName += ("_stats.txt");
        File reservationFile = new File(this.fileName);
        try {
            reservationFile.createNewFile();
        }
        catch (IOException e) {
            System.err.println("File Not Created");
            downloadReservations();
        }
    }
        
    /**
     * Method Name:getTotalCost
     * Purpose: get the total cost for each Reservation
     * @param temp
     * @return Total Cost
     */
    public double getTotalCost(Reservation temp) {
        return temp.dailyRate * temp.numOfNights;
    }
    
    /**
     * Method Name: getLongestSTay
     * Purpose: Find the Reservation with the largest numOfNights
     * @return longestStay
     */
    public int getLongestStay() {
        int longestStay = 0;
        for (Reservation Reservation : Reservations) {
            if (Reservation.numOfNights > longestStay) {
                longestStay = Reservation.numOfNights;
            }
        }
        return longestStay;
    }
    
    /**
     * Method Name: viewReservations
     * Purpose: Loop through Reservation array and call the print method for each entry
     */
    public void viewReservations() {
        for (int i = 0; i <= Reservations.size() - 1; i++) {
            print(Reservations.get(i));
        }
    }
    
    /**
     * Method Name: getID
     * Purpose: Returns the ID number of the Reservation
     * @return ID
     */
    @Override
    public int getID() {
        return this.ID;
    }
    
}
