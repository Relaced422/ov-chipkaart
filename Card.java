public class Card {

    // --- Fields ---
    private int cardID = 1;                  // Unique identifier for this card
    private String expiry = "01/01/2026";    // Expiry date in MM/DD/YYYY format
    private boolean active = true;           // Whether the card is currently active
    private double balance = 50.0;           // Current balance on the card in euros
    private int subscriptionType = 1;        // Subscription tier (e.g. 1 = standard)
    private boolean checkedIn = false;       // Whether the traveller is currently checked in
    private String checkInLocation = null;   // The location where the traveller checked in (null if not checked in)

    // Ordered list of stations on this line — add more stops here to extend the route
    public static final String[] STATIONS = {
            "Nijmegen",
            "Arnhem",
            "Utrecht Centraal",
            "Amsterdam Centraal"
    };

    // Price charged per stop passed through
    private static final double PRICE_PER_STOP = 3.65;

    // --- Getters ---
    public boolean getActive() { return this.active; }
    public boolean getCheckedIn() { return this.checkedIn; }
    public double getBalance() { return this.balance; }
    public int getCardID() { return this.cardID; }
    public String getCheckInLocation() { return this.checkInLocation; }

    // --- Methods ---

    /**
     * Deducts the given amount from the card balance.
     * Pass a negative value to add funds (used by Laadpunt).
     *
     * @param amount The amount in euros to deduct (negative = top up)
     */
    public void withdrawBalance(double amount) {
        balance -= amount;
    }

    /**
     * Calculates the travel price based on how many stops lie between
     * the departure and arrival stations.
     * Price = number of stops passed × PRICE_PER_STOP.
     * Returns 0.0 if either station is not found on the line.
     *
     * @param from  Departure station name
     * @param to    Arrival station name
     * @return      Total price in euros
     */
    public double calculatePrice(String from, String to) {
        int fromIndex = -1, toIndex = -1;

        for (int i = 0; i < STATIONS.length; i++) {
            if (STATIONS[i].equalsIgnoreCase(from)) fromIndex = i;
            if (STATIONS[i].equalsIgnoreCase(to))   toIndex   = i;
        }

        if (fromIndex == -1 || toIndex == -1) return 0.0;

        return Math.abs(toIndex - fromIndex) * PRICE_PER_STOP;
    }

    /**
     * Checks the traveller in at the given location.
     * Stores the check-in location and marks the card as checked in.
     *
     * @param location The name of the departure station
     */
    public void checkIn(String location) {
        this.checkInLocation = location;
        this.checkedIn = true;
        System.out.println("Ingechecked op " + location + ". Huidig saldo: €" + balance);
    }

    /**
     * Checks the traveller out at the given location.
     * Calculates the fare based on stops passed, deducts it from
     * the balance, and resets the check-in state.
     *
     * @param location The name of the arrival station
     */
    public void checkOut(String location) {
        double price = calculatePrice(this.checkInLocation, location);
        withdrawBalance(price);
        this.checkedIn = false;
        this.checkInLocation = null;
        System.out.println("Uitgechecked op " + location + ". €" + price + " afgeschreven. Huidig saldo: €" + balance);
    }

    /**
     * Prints a summary of the card's current state to the console.
     * Displays: card ID, active status, balance, check-in status, and current location.
     */
    public void printInfo() {
        System.out.println("=== Kaartinfo ===");
        System.out.println("Kaart ID:    " + cardID);
        System.out.println("Actief:      " + active);
        System.out.println("Saldo:       €" + balance);
        System.out.println("Ingechecked: " + checkedIn);
        System.out.println("Locatie:     " + (checkInLocation != null ? checkInLocation : "N/A"));
    }
}