public class Laadpunt {
    private Card card;

    public Laadpunt(Card card) {
        this.card = card;
    }

    public void laadSaldo(double amount) {
        if (amount <= 0) { System.out.println("Ongeldig bedrag."); return; }
        card.depositBalance(amount);
        System.out.println(amount + " opgewaardeerd");
        System.out.println("nieuw saldo: " + card.getBalance());
        if (card.getBalance() > 0) {
            card.setActive(true);
            System.out.println("Uw kaart is weer actief");
        }
    }
}