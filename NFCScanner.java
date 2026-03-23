public class NFCScanner {
    private Card card;
    private String location;

    public NFCScanner(Card card, String location) {
        this.card = card;
        this.location = location;
    }

    public void inchecken() {
        if (!card.getActive()) {
            System.out.println("Kaart is ongeldig.");
            return;
        }
        if (card.getCheckedIn()) {
            System.out.println("Je bent al ingechecked!");
            return;
        }
        if (card.getBalance() < 5) {
            System.out.println("Niet genoeg saldo om in te checken. (minimum: €5)");
            return;
        }
        card.checkIn(location);
    }

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