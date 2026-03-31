import java.util.Scanner;

public class Laadpunt {
    private Card card;
    private Scanner scanner = new Scanner(System.in);

    public Laadpunt(Card card) {
        this.card = card;
    }

    public void laadSaldo(String opwaardeerKeuze) {
        switch (opwaardeerKeuze) {
            case "opladen" -> {
                System.out.println("Hoeveel wil je opwaarderen?");
                double amount = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (amount <= 0) { System.out.println("Ongeldig bedrag."); return; }
                card.depositBalance(amount);
                System.out.println(amount + " opgewaardeerd");
                System.out.println("nieuw saldo: " + card.getBalance());
                if ((card.getBalance() > 0) && (card.getActive() == false)) {
                    card.setActive(true);
                    System.out.println("Uw kaart is weer actief");
                }
            }
            case "opladentot" -> {
                System.out.print("Voer het gewenste saldo in: ");
                double targetBalance = scanner.nextDouble();
                if (targetBalance <= 0) { System.out.println("Ongeldig bedrag."); return; }
                if (targetBalance <= card.getBalance()) {
                    System.out.println("Huidig saldo is al hoger dan of gelijk aan het gewenste saldo.");
                    System.out.println(targetBalance + " is toegevoegd aan je balans");
                    return;
                }
                double toDeposit = targetBalance - card.getBalance();
                card.depositBalance(toDeposit);
                System.out.println(toDeposit + " opgewaardeerd");
                System.out.println("nieuw saldo: " + card.getBalance());
                if ((card.getBalance() > 0) && (card.getActive() == false)) {
                    card.setActive(true);
                    System.out.println("Uw kaart is weer actief");
                }
            }
            default -> System.out.println("Ongeldige keuze: " + opwaardeerKeuze);
        }
    }
}