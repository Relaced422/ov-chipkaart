public class Laadpunt {
    private Card card;
    private boolean isOrderActive;
    private double depositAmount;

    //    Make laadpunt info public to other classes
    public Laadpunt(Card card) {
        this.card = card;
    }

//    Checks saldo > 0, sets active order true, withrdraws amount, gives feedback, then sets active order false
    public void laadSaldo(double amount) {
        if (amount <= 0) {
            System.out.println("Ongeldig bedrag.");
            return;
        }
        this.depositAmount = amount;
        this.isOrderActive = true;
        card.withdrawBalance(-amount); // negative withdrawal = top up
        System.out.println("€" + amount + " opgeladen. Nieuw saldo: €" + card.getBalance());
        this.isOrderActive = false;
    }
}