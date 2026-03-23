public class Card {
    private int cardID = 1;
    private String expiry = "01/01/2026";
    private boolean active = true;
    private double balance = 50.0;
    private int subscriptionType = 1;
    private boolean checkedIn = false;
    private String checkInLocation = null;

    // GETTERS
    public boolean getActive() { return this.active; }
    public boolean getCheckedIn() { return this.checkedIn; }
    public double getBalance() { return this.balance; }
    public int getCardID() { return this.cardID; }
    public String getCheckInLocation() { return this.checkInLocation; }

    // FUNCTIONS
    public void withdrawBalance(double amount) {
        balance -= amount;
    }

//    ONLY HARDCODED LOCATIONS NOW! Nijmegen - Arnhem = 7.30 Standard
    public double calculatePrice(String from, String to) {
        if (from.equals("Arnhem") && to.equals("Nijmegen")) return 7.30;
        if (from.equals("Nijmegen") && to.equals("Arnhem")) return 7.30;
        return 0.0;
    }

//    Sets checkedin status to true and gives feedback
    public void checkIn(String location) {
        this.checkInLocation = location;
        this.checkedIn = true;
        System.out.println("Ingechecked op " + location + ". Huidig saldo: €" + balance);
    }

//    Calculates price, sets checkedin status false, resets checkin location and gives feedback
    public void checkOut(String location) {
        double price = calculatePrice(this.checkInLocation, location);
        withdrawBalance(price);
        this.checkedIn = false;
        this.checkInLocation = null;
        System.out.println("Uitgechecked op " + location + ". €" + price + " afgeschreven. Huidig saldo: €" + balance);
    }

//    Prints cardID, Active status, balance, checkedIn Status, location
    public void printInfo() {
        System.out.println("=== Kaartinfo ===");
        System.out.println("Kaart ID:    " + cardID);
        System.out.println("Actief:      " + active);
        System.out.println("Saldo:       €" + balance);
        System.out.println("Ingechecked: " + checkedIn);
        System.out.println("Locatie:     " + (checkInLocation != null ? checkInLocation : "N/A"));
    }
}