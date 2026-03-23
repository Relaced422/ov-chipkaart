public class NFCScanner {
    private Card card;
    private String location;


//    Make scanner info public to other classes
    public NFCScanner(Card card, String location) {
        this.card = card;
        this.location = location;
    }

//    Checks active status, checkin status, confirms balance status =< 5 and then checks in
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

    //    Checks active status, checkin status and then checks out
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