import java.util.Scanner;
import java.lang.Math;

void main() {
    //    Decide random numbers
    int rand  = (int) (Math.random() * 9000) + 1000;
    int rand1 = (int) (Math.random() * 9000) + 1000;
    int rand2 = (int) (Math.random() * 9000) + 1000;

    Card card  = new Card(rand);
    Card card1 = new Card(rand1);
    Card card2 = new Card(rand2);
    Scanner input = new Scanner(System.in);

    Locatie arnhem   = new Locatie("Arnhem", 10.0, 20.0);
    Locatie nijmegen = new Locatie("Nijmegen", 5.0, 5.0);
    Locatie tokyo    = new Locatie("Tokyo", 60.0, 300.0);

    // Card selection
    System.out.println("Kies een kaart:");
    System.out.println("1. Kaart " + rand);
    System.out.println("2. Kaart " + rand1);
    System.out.println("3. Kaart " + rand2);

    Card selectedCard = null;
    while (selectedCard == null) {
        String cardKeuze = input.nextLine().trim();
        selectedCard = switch (cardKeuze) {
            case "1" -> card;
            case "2" -> card1;
            case "3" -> card2;
            default  -> { System.out.println("Ongeldige keuze, kies 1, 2 of 3."); yield null; }
        };
    }

    NFCScanner scannerArnhem   = new NFCScanner(selectedCard, arnhem);
    NFCScanner scannerNijmegen = new NFCScanner(selectedCard, nijmegen);
    NFCScanner scannerTokyo    = new NFCScanner(selectedCard, tokyo);
    Laadpunt laadpunt = new Laadpunt(selectedCard);

    while (true) {
        System.out.println("\nOpties: inchecken | uitchecken | saldo | info | stop");
        String keuze = input.nextLine().trim().toLowerCase();

        switch (keuze) {
            case "inchecken", "uitchecken" -> {
                System.out.println("Bij welk station? (arnhem | nijmegen | tokyo)");
                String station = input.nextLine().trim().toLowerCase();
                NFCScanner scanner = switch (station) {
                    case "arnhem"   -> scannerArnhem;
                    case "nijmegen" -> scannerNijmegen;
                    case "tokyo"    -> scannerTokyo;
                    default         -> null;
                };
                if (scanner == null) { System.out.println("Onbekend station."); break; }
                if (keuze.equals("inchecken")) scanner.inchecken();
                else scanner.uitchecken();
            }
            case "saldo" -> {
                System.out.println("Huidig saldo: €" + selectedCard.getBalance());
                System.out.println("Oplaadmethode? (opladen | opladentot)");
                String opwaardeerKeuze = input.nextLine().trim();
                laadpunt.laadSaldo(opwaardeerKeuze);
            }
            case "info" -> selectedCard.printInfo();
            case "stop" -> { System.out.println("Tot ziens!"); return; }
            default     -> System.out.println("Ongeldige optie.");
        }
    }
}