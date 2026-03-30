public class Laadpunt {
    private Card card;

    public Laadpunt(Card card) {
        this.card = card;
    }

    public void laadSaldo(double amount) {
        if (amount <= 0) { System.out.println("Ongeldig bedrag."); return; }
        card.withdrawBalance(-amount);
        System.out.println("€" + amount + " opgeladen. Nieuw saldo: €" + card.getBalance());
    }
}