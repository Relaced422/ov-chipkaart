public class NFCScanner {
    Card card = new Card();
    String scannerLocation = "Nijmegen";

    public void inchecken() {
        if (card.getActive()) {
            System.out.println("(DEBUG) Kaart is actief");
            if (!card.getCheckedIn()) {
                IO.println("(DEBUG) Je bent nog niet ingechecked");
                if (card.getBalance() >= 5) {
                    System.out.println("(DEBUG) Saldo goedgekeurd (>5)");
                    card.toggleCheckIn(scannerLocation);
                } else {
                    System.out.println("Je hebt niet genoeg saldo! (minimum: 5)");
                }
            } else {
                System.out.println("Je bent al ingecheckt!");
            }
        } else {
            System.out.println("Kaart is ongeldig");
        }
    }

    public void uitchecken() {
        if (card.getActive()) {
            System.out.println("(DEBUG) Kaart is actief");
            if (card.getCheckedIn()){
                System.out.println("(DEBUG) Kaart is ingechecked");
                card.toggleCheckIn("Arnhem");
            }
            else {
                System.out.println("(DEBUG) Kaart is niet ingechecked! Annuleren..");
            }
        } else {
            System.out.println("Kaart is ongeldig");
        }
    }
}