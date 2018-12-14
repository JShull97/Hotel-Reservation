public class Reservation {
    int ID;
    String fName;
    String lName;
    int numOfNights;
    double dailyRate;
    
    /**
    * Reservation constructor
    */
    public Reservation() {
        this.ID = 0;
        this.fName = "John";
        this.lName = "Doe";
        this.numOfNights = 0;
        this.dailyRate = 0;
    }
    
    /**
     * Reservation constructor
     * @param ID
     * @param lName
     * @param fName
     * @param numOfNights
     * @param dailyRate 
     */
    public Reservation(int ID, String lName, String fName, int numOfNights, double dailyRate) {
        this.ID = ID;
        this.lName = lName;
        this.fName = fName;
        this.numOfNights = numOfNights;
        this.dailyRate = dailyRate;
    }
    
    /**
     * Method Name: print
     * Purpose: Print out the reservation
     * @param temp 
     */
    public void print(Reservation temp) {
            System.out.print("ID: " + temp.ID); 
            System.out.print(", Last Name: " + temp.lName);
            System.out.print(", First Name: " + temp.fName);
            System.out.print(", Number of Nights: " + temp.numOfNights);
            System.out.print(", Daily Rate: " + temp.dailyRate + "\n"); 
        }
    
    /**
     * Method Name: printToFile
     * Purpose: Print the reservation to a file
     * @param temp
     * @return String to print to file
     */
    public String printToFile(Reservation temp) {
            return String.valueOf(temp.ID) + " " + temp.lName + " " + temp.fName + " " + String.valueOf(temp.numOfNights) + " " + String.valueOf(temp.dailyRate); 
        }
    
    /**
     * Method Name: getID
     * Purpose: get the ID of the reservation
     * @return Reservation ID 
     */
    public int getID() {
        return this.ID;
    }
}
