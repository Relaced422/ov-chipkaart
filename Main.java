import java.util.Scanner;

void main() {
    int idOne   = (int) (Math.random() * 9000) + 1000;
    int idTwo   = (int) (Math.random() * 9000) + 1000;
    int idThree = (int) (Math.random() * 9000) + 1000;

    Card cardOne   = new Card(idOne);
    Card cardTwo   = new Card(idTwo);
    Card cardThree = new Card(idThree);
    Scanner input  = new Scanner(System.in);

    Location arnhem   = new Location("Arnhem",   10.0, 20.0);
    Location nijmegen = new Location("Nijmegen",  5.0,  5.0);
    Location tokyo    = new Location("Tokyo",    60.0, 300.0);

    while (true) {
        System.out.println("\nKies een kaart:");
        System.out.println("1. Kaart " + idOne);
        System.out.println("2. Kaart " + idTwo);
        System.out.println("3. Kaart " + idThree);
        System.out.println("0. Afsluiten");

        Card selectedCard = null;
        while (selectedCard == null) {
            String cardChoice = input.nextLine().trim();
            if (cardChoice.equals("0")) { System.out.println("Tot ziens!"); return; }
            selectedCard = switch (cardChoice) {
                case "1" -> cardOne;
                case "2" -> cardTwo;
                case "3" -> cardThree;
                default  -> { System.out.println("Ongeldige keuze, kies 1, 2 of 3."); yield null; }
            };
        }

        NfcScanner scannerArnhem   = new NfcScanner(selectedCard, arnhem);
        NfcScanner scannerNijmegen = new NfcScanner(selectedCard, nijmegen);
        NfcScanner scannerTokyo    = new NfcScanner(selectedCard, tokyo);
        ChargingStation chargingStation = new ChargingStation(selectedCard);

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\nOpties: inchecken | uitchecken | saldo | info | stop");
            String choice = input.nextLine().trim().toLowerCase();

            switch (choice) {
                case "inchecken", "uitchecken" -> {
                    System.out.println("Bij welk station? (arnhem | nijmegen | tokyo)");
                    String station = input.nextLine().trim().toLowerCase();
                    NfcScanner scanner = switch (station) {
                        case "arnhem"   -> scannerArnhem;
                        case "nijmegen" -> scannerNijmegen;
                        case "tokyo"    -> scannerTokyo;
                        default         -> null;
                    };
                    if (scanner == null) { System.out.println("Onbekend station."); break; }
                    if (choice.equals("inchecken")) scanner.checkIn();
                    else scanner.checkOut();
                }
                case "saldo" -> {
                    System.out.println("Huidig saldo: €" + selectedCard.getBalance());
                    System.out.println("Oplaadmethode? (opladen | opladentot)");
                    String chargeChoice = input.nextLine().trim();
                    chargingStation.chargeSaldo(chargeChoice);
                }
                case "info"  -> selectedCard.printInfo();
                case "stop"  -> { System.out.println("Uitgelogd."); loggedIn = false; }
                default      -> System.out.println("Ongeldige optie.");
            }
        }
    }
}