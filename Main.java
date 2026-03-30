import java.util.Scanner;

void main() {
    Card card     = new Card();
    Scanner input = new Scanner(System.in);

    Locatie arnhem   = new Locatie("Arnhem", 10.0, 20.0);
    Locatie nijmegen = new Locatie("Nijmegen", 5.0, 5.0);

    NFCScanner scannerArnhem   = new NFCScanner(card, arnhem);
    NFCScanner scannerNijmegen = new NFCScanner(card, nijmegen);
    Laadpunt laadpunt = new Laadpunt(card);

    while (true) {
        System.out.println("\nOpties: inchecken | uitchecken | saldo | info | stop");
        String keuze = input.nextLine().trim().toLowerCase();

        switch (keuze) {
            case "inchecken", "uitchecken" -> {
                System.out.println("Bij welk station? (arnhem | nijmegen)");
                String station = input.nextLine().trim().toLowerCase();
                NFCScanner scanner = switch (station) {
                    case "arnhem"   -> scannerArnhem;
                    case "nijmegen" -> scannerNijmegen;
                    default         -> null;
                };
                if (scanner == null) { System.out.println("Onbekend station."); break; }
                if (keuze.equals("inchecken")) scanner.inchecken();
                else scanner.uitchecken();
            }
            case "saldo" -> {
                System.out.println("Huidig saldo: €" + card.getBalance());
                System.out.println("Hoeveel wil je opwaarderen?");
                double amount = Double.parseDouble(input.nextLine().replace(",", "."));
                laadpunt.laadSaldo(amount);
            }
            case "info" -> card.printInfo();
            case "stop" -> { System.out.println("Tot ziens!"); return; }
            default     -> System.out.println("Ongeldige optie.");
        }
    }
}