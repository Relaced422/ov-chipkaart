import java.util.Scanner;

public class ChargingStation {
    private Card card;
    private Scanner scanner = new Scanner(System.in);

    public ChargingStation(Card card) {
        this.card = card;
    }

    public void chargeSaldo(String chargeChoice) {
        switch (chargeChoice) {
            case "1" -> {
                System.out.println("Hoeveel wil je opwaarderen?");
                double amount = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (amount <= 0) { System.out.println("Ongeldig bedrag."); return; }
                depositAndActivate(amount);
            }
            case "2" -> {
                System.out.print("Voer het gewenste saldo in: ");
                double targetBalance = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (targetBalance <= 0) { System.out.println("Ongeldig bedrag."); return; }
                if (targetBalance <= card.getBalance()) {
                    System.out.println("Huidig saldo is al hoger dan of gelijk aan het gewenste saldo.");
                    return;
                }
                depositAndActivate(targetBalance - card.getBalance());
            }
            default -> System.out.println("Ongeldige keuze: " + chargeChoice);
        }
    }

    private void depositAndActivate(double amount) {
        card.depositBalance(amount);
        System.out.println(amount + " opgewaardeerd");
        System.out.println("Nieuw saldo: " + card.getBalance());
        activeStatusUpdate();
    }

    private void activeStatusUpdate() {
        if (card.getBalance() > 0 && !card.isActive()) {
            card.setActive(true);
            System.out.println("Uw kaart is weer actief.");
        }
    }
//    Todo: Functie maken van opwaarderen en verwerken in bovenstaande switchcase
}