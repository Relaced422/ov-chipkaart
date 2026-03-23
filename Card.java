public class Card {

    // --- Fields ---
    private int cardID = 1;                  // Unique identifier for this card
    private String expiry = "01/01/2026";    // Expiry date in MM/DD/YYYY format
    private boolean active = true;           // Whether the card is currently active
    private double balance = 50.0;           // Current balance on the card in euros
    private int subscriptionType = 1;        // Subscription tier (e.g. 1 = standard)
    private boolean checkedIn = false;       // Whether the traveller is currently checked in
    private String checkInLocation = null;   // The location where the traveller checked in (null if not checked in)

    // --- Getters ---
    public boolean getActive() { return this.active; }
    public boolean getCheckedIn() { return this.checkedIn; }
    public double getBalance() { return this.balance; }
    public int getCardID() { return this.cardID; }
    public String getCheckInLocation() { return this.checkInLocation; }

    // --- Methods ---

    /**
     * Deducts the given amount from the card balance.
     * Does not validate whether sufficient funds are available.
     *
     * @param amount The amount in euros to deduct
     */
    public void withdrawBalance(double amount) {
        balance -= amount;
    }

    /**
     * Returns the travel price between two hardcoded locations.
     * Currently only supports routes between Arnhem and Nijmegen.
     * Returns 0.0 for any unsupported route.
     *
     * TODO: Replace with dynamic pricing based on distance or a pricing table.
     *
     * @param from  Departure location
     * @param to    Destination location
     * @return      Price in euros, or 0.0 if the route is not recognised
     */
    public double calculatePrice(String from, String to) {
        if (from.equals("Arnhem") && to.equals("Nijmegen")) return 7.30;
        if (from.equals("Nijmegen") && to.equals("Arnhem")) return 7.30;
        return 0.0;
    }

    /**
     * Checks the traveller in at the given location.
     * Stores the check-in location and marks the card as checked in.
     * Does not validate card status or balance before checking in.
     *
     * @param location The name of the check-in location (e.g. "Arnhem")
     */
    public void checkIn(String location) {
        this.checkInLocation = location;
        this.checkedIn = true;
        System.out.println("Ingechecked op " + location + ". Huidig saldo: €" + balance);
    }

    /**
     * Checks the traveller out at the given location.
     * Calculates the fare based on the check-in and check-out locations,
     * deducts it from the balance, and resets the check-in state.
     *
     * @param location The name of the check-out location (e.g. "Nijmegen")
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