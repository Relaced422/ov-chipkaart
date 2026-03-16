public class NFCScanner {
    Card card = new Card();
    String ScannerLocation = "Nijmegen";

    public void inchecken() {
        if (card.isActive()) {
            IO.println("(DEBUG) Kaart is actief");
            if (!card.isCheckedIn()) {
                IO.println("(DEBUG) Je bent nog niet ingechecked");
                if (card.getBalance() >= 5) {
                    IO.println("(DEBUG) Saldo goedgekeurd (>5)");
                    card.toggleCheckIn(ScannerLocation);
                } else {
                    IO.println("Je hebt niet genoeg saldo! (minimum: 5)");
                }
            } else {
                IO.println("Je bent al ingecheckt!");
            }
        } else {
            IO.println("Kaart is ongeldig");
        }
    }
}
