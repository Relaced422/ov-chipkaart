public class NFCScanner {
    Card card = new Card();
    String scannerLocation = "Nijmegen";

    public void inchecken() {
        if (card.getActive()) {
            IO.println("(DEBUG) Kaart is actief");
            if (!card.getCheckedIn()) {
                IO.println("(DEBUG) Je bent nog niet ingechecked");
                if (card.getBalance() >= 5) {
                    IO.println("(DEBUG) Saldo goedgekeurd (>5)");
                    card.toggleCheckIn(scannerLocation);
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