public class NFCScanner {
    private Card card;
    private Locatie locatie;

    public NFCScanner(Card card, Locatie locatie) {
        this.card = card;
        this.locatie = locatie;
    }

    public void inchecken() {
        if (!card.getActive())        { System.out.println("Kaart is niet actief.");                          return; }
        if (card.getCheckedIn())      { System.out.println("Je bent al ingechecked!");                     return; }
        if (card.getBalance() <= 5)   { System.out.println("Niet genoeg saldo. (minimum: €5)");            return; }
        card.checkIn(locatie);
    }

    public void uitchecken() {
        if (!card.getActive())        { System.out.println("Kaart is niet actief.");                          return; }
        if (!card.getCheckedIn())     { System.out.println("Je bent niet ingechecked.");                   return; }
        double afstand = card.getCheckInLocation().berekenAfstand(locatie);
        card.checkOut(locatie);
        System.out.println("Afstand: " + afstand + " km");
        double prijs = berekenPrijs(afstand);
        card.withdrawBalance(prijs);
        if (card.getBalance() < 0) {
            card.setActive(false);
            System.out.println();
            System.out.println("Je kaart heeft te weinig saldo!");
            System.out.println("Deze kaart is nu geblokkeerd tot u weer een positief saldo heeft.");
            System.out.println("Huidig saldo: " + card.getBalance());
        }
    }

    public double berekenPrijs(double afstand) {
        double prijs = afstand * 1.6;
                return prijs;
    }
}