public class NFCScanner {

    // --- Fields ---
    private Card card;      // The OV-chipkaart being scanned
    private String location; // The name of the station/stop where this scanner is located

    // --- Constructor ---

    /**
     * Creates a new NFCScanner tied to a specific card and location.
     * Both values are set at construction and do not change during the scanner's lifetime.
     *
     * @param card     The card to read and operate on
     * @param location The station or stop name where this scanner is placed (e.g. "Arnhem")
     */
    public NFCScanner(Card card, String location) {
        this.card = card;
        this.location = location;
    }

    // --- Methods ---

    /**
     * Attempts to check the traveller in at this scanner's location.
     * Performs three validations before checking in:
     *  1. The card must be active
     *  2. The traveller must not already be checked in
     *  3. The balance must be more than €5 (minimum required to board)
     *
     * If any validation fails, an error message is printed and the check-in is aborted.
     */
    public void inchecken() {
        if (!card.getActive()) {
            System.out.println("Kaart is ongeldig.");
            return;
        }
        if (card.getCheckedIn()) {
            System.out.println("Je bent al ingechecked!");
            return;
        }
        if (card.getBalance() <= 5) {
            System.out.println("Niet genoeg saldo om in te checken. (minimum: €5)");
            return;
        }
        card.checkIn(location);
    }

    /**
     * Attempts to check the traveller out at this scanner's location.
     * Performs two validations before checking out:
     *  1. The card must be active
     *  2. The traveller must currently be checked in
     *
     * If either validation fails, an error message is printed and the check-out is aborted.
     * On success, the fare is calculated and deducted from the card balance via checkOut().
     */
    public void uitchecken() {
        if (!card.getActive()) {
            System.out.println("Kaart is ongeldig.");
            return;
        }
        if (!card.getCheckedIn()) {
            System.out.println("Je bent niet ingechecked.");
            return;
        }
        card.checkOut(location);
    }
}