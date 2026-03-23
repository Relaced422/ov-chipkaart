public class Laadpunt {

    // --- Fields ---
    private Card card;              // The OV-chipkaart being topped up at this terminal
    private boolean isOrderActive;  // Whether a top-up transaction is currently in progress
    private double depositAmount;   // The amount being deposited in the current transaction (in euros)

    // --- Constructor ---

    /**
     * Creates a new Laadpunt (top-up terminal) linked to a specific card.
     *
     * @param card The card to add balance to
     */
    public Laadpunt(Card card) {
        this.card = card;
    }

    // --- Methods ---

    /**
     * Tops up the card balance by the given amount.
     * Uses a negative withdrawal internally to add funds (see Card.withdrawBalance).
     *
     * Validation:
     *  - Amount must be greater than €0; invalid amounts are rejected with an error message.
     *
     * Transaction flow:
     *  1. Store the deposit amount
     *  2. Mark the order as active
     *  3. Add funds to the card
     *  4. Confirm the top-up to the user
     *  5. Mark the order as complete
     *
     * @param amount The amount in euros to add to the card balance (must be > 0)
     */
    public void laadSaldo(double amount) {
        if (amount <= 0) {
            System.out.println("Ongeldig bedrag.");
            return;
        }
        this.depositAmount = amount;
        this.isOrderActive = true;
        card.withdrawBalance(-amount); // Passing a negative value to withdrawBalance adds to the balance
        System.out.println("€" + amount + " opgeladen. Nieuw saldo: €" + card.getBalance());
        this.isOrderActive = false;
    }
}