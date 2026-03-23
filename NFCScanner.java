import java.util.Scanner;

public class NFCScanner {

    // --- Fields ---
    private Card card;     // The OV-chipkaart being scanned
    private Scanner input; // Reads station input from the user at this terminal

    // --- Constructor ---

    /**
     * Creates a new NFCScanner terminal linked to a specific card.
     * Location is no longer fixed — the user is prompted at check-in/out.
     *
     * @param card The card to read and operate on
     */
    public NFCScanner(Card card) {
        this.card = card;
        this.input = new Scanner(System.in);
    }

    // --- Methods ---

    /**
     * Attempts to check the traveller in.
     * Prompts the user to choose a departure station from the route,
     * then performs three validations before checking in:
     *  1. The card must be active
     *  2. The traveller must not already be checked in
     *  3. The balance must be more than €5 (minimum required to board)
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

        String location = promptStation("Van welk station vertrek je?");
        if (location == null) return;

        card.checkIn(location);
    }

    /**
     * Attempts to check the traveller out.
     * Prompts the user to choose an arrival station from the route,
     * then performs two validations before checking out:
     *  1. The card must be active
     *  2. The traveller must currently be checked in
     *
     * On success, the fare is calculated based on stops passed and deducted.
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

        String location = promptStation("Bij welk station stap je uit?");
        if (location == null) return;

        card.checkOut(location);
    }

    /**
     * Displays the list of stations on the current line and prompts
     * the user to pick one by number.
     * Returns the chosen station name, or null if the input was invalid.
     *
     * @param prompt  The question shown to the user
     * @return        The selected station name, or null on invalid input
     */
    private String promptStation(String prompt) {
        System.out.println(prompt);

        // Print all stations with an index number
        for (int i = 0; i < Card.STATIONS.length; i++) {
            System.out.println((i + 1) + ". " + Card.STATIONS[i]);
        }

        try {
            int choice = Integer.parseInt(input.nextLine().trim()) - 1;
            if (choice < 0 || choice >= Card.STATIONS.length) {
                System.out.println("Ongeldige keuze.");
                return null;
            }
            return Card.STATIONS[choice];
        } catch (NumberFormatException e) {
            System.out.println("Voer een getal in.");
            return null;
        }
    }
}