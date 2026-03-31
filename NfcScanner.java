public class NfcScanner {
    private Card card;
    private Location location;

    public NfcScanner(Card card, Location location) {
        this.card = card;
        this.location = location;
    }

    public void checkIn() {
        if (!card.isActive())             { System.out.println("Kaart is niet actief.");                                    return; }
        if (card.isCheckedIn())           { System.out.println("Je bent al ingechecked!");                                  return; }
        if (card.getBalance() <= 5)       { System.out.println("Niet genoeg saldo. (minimum: €5)");                        return; }
        card.checkIn(location);
        card.withdrawBalance(5.0);
        System.out.println("Instaptarief betaald (€5)");
    }

    public void checkOut() {
        if (!card.isActive())             { System.out.println("Kaart is niet actief.");                                    return; }
        if (!card.isCheckedIn())          { System.out.println("Je bent niet ingechecked.");                                return; }
        double distance = card.getCheckInLocation().calculateDistance(location);
        card.checkOut(location);
        System.out.println("Afstand: " + distance + " km");
        double price = calculatePrice(distance);
        card.depositBalance(5.0);
        System.out.println("Instaptarief terug gekregen (€5)");
        card.withdrawBalance(price);
        if (card.getBalance() < 0) {
            card.setActive(false);
            System.out.println("\nJe kaart heeft te weinig saldo!");
            System.out.println("Deze kaart is nu geblokkeerd tot u weer een positief saldo heeft.");
            System.out.println("Huidig saldo: " + card.getBalance());
        }
    }

    public double calculatePrice(double distance) {
        return distance * 1.6;
    }
}