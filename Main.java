import java.util.Scanner;

void main() {

    // --- Initialisation ---

    // One card, one shared scanner terminal, one top-up point
    Card card         = new Card();
    NFCScanner nfc    = new NFCScanner(card);
    Laadpunt laadpunt = new Laadpunt(card);
    Scanner input     = new Scanner(System.in);

    // --- Main loop ---

    // Keeps the menu running until the user explicitly types "stop"
    while (true) {
        System.out.println("\nOpties: inchecken | uitchecken | saldo | info | stop");
        String keuze = input.nextLine().trim().toLowerCase();

        switch (keuze) {
            // Prompts user for departure station, then checks in
            case "inchecken"  -> nfc.inchecken();

            // Prompts user for arrival station, then checks out and bills by stops passed
            case "uitchecken" -> nfc.uitchecken();

            // Show current balance, then prompt the user to top up
            case "saldo"      -> {
                System.out.println("Huidig saldo: " + card.getBalance());
                System.out.println("Hoeveel wil je opwaarderen? (??,??)");
                // Replace comma with period to support Dutch decimal notation (e.g. "7,30" → 7.30)
                double amount = Double.parseDouble(input.nextLine().replace(",", "."));
                laadpunt.laadSaldo(amount);
            }

            // Print a full summary of the card's current state
            case "info"       -> card.printInfo();

            // Exit the program cleanly
            case "stop"       -> { System.out.println("Tot ziens!"); return; }

            // Catch any input that doesn't match a known option
            default           -> System.out.println("Ongeldige optie.");
        }
    }
}