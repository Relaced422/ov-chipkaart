import java.util.Scanner;

void main() {
    int idOne   = (int) (Math.random() * 9000) + 1000;
    int idTwo   = (int) (Math.random() * 9000) + 1000;
    int idThree = (int) (Math.random() * 9000) + 1000;

    Card cardOne   = new Card(idOne);
    Card cardTwo   = new Card(idTwo);
    Card cardThree = new Card(idThree);
    Scanner input  = new Scanner(System.in);

    Location amsterdamCentraal = new Location("Amsterdam Centraal", 4.90, 52.38);
    Location rotterdamCentraal = new Location("Rotterdam Centraal", 4.47, 51.92);
    Location denHaagCentraal   = new Location("Den Haag Centraal",  4.32, 52.08);
    Location utrechtCentraal   = new Location("Utrecht Centraal",   5.11, 52.09);
    Location eindhoven         = new Location("Eindhoven",          5.48, 51.44);
    Location arnhem            = new Location("Arnhem",             5.90, 51.98);
    Location nijmegen          = new Location("Nijmegen",           5.85, 51.84);
    Location leidenCentraal    = new Location("Leiden Centraal",    4.48, 52.17);
    Location haarlem           = new Location("Haarlem",            4.64, 52.38);
    Location delft             = new Location("Delft",              4.36, 52.01);
    Location zwolle            = new Location("Zwolle",             6.09, 52.50);
    Location groningen         = new Location("Groningen",          6.57, 53.21);
    Location maastricht        = new Location("Maastricht",         5.71, 50.85);
    Location tilburg           = new Location("Tilburg",            5.08, 51.56);
    Location breda             = new Location("Breda",              4.78, 51.60);
    Location amersfoort        = new Location("Amersfoort",         5.39, 52.15);
    Location deventer          = new Location("Deventer",           6.16, 52.25);
    Location dordrecht         = new Location("Dordrecht",          4.67, 51.81);
    Location venlo             = new Location("Venlo",              6.17, 51.37);
    Location almereCentrum     = new Location("Almere Centrum",     5.22, 52.37);

    while (true) {
        System.out.println("\nKies de kaart die je wilt scannen:");
        System.out.println("1. Kaart ID: " + idOne);
        System.out.println("2. Kaart ID: " + idTwo);
        System.out.println("3. Kaart ID: " + idThree);
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

        NfcScanner scannerAmsterdamCentraal = new NfcScanner(selectedCard, new Location("Amsterdam Centraal", 4.90, 52.38));
        NfcScanner scannerRotterdamCentraal = new NfcScanner(selectedCard, new Location("Rotterdam Centraal", 4.47, 51.92));
        NfcScanner scannerDenHaagCentraal   = new NfcScanner(selectedCard, new Location("Den Haag Centraal",  4.32, 52.08));
        NfcScanner scannerUtrechtCentraal   = new NfcScanner(selectedCard, new Location("Utrecht Centraal",   5.11, 52.09));
        NfcScanner scannerEindhoven         = new NfcScanner(selectedCard, new Location("Eindhoven",          5.48, 51.44));
        NfcScanner scannerArnhem            = new NfcScanner(selectedCard, new Location("Arnhem",             5.90, 51.98));
        NfcScanner scannerNijmegen          = new NfcScanner(selectedCard, new Location("Nijmegen",           5.85, 51.84));
        NfcScanner scannerLeidenCentraal    = new NfcScanner(selectedCard, new Location("Leiden Centraal",    4.48, 52.17));
        NfcScanner scannerHaarlem           = new NfcScanner(selectedCard, new Location("Haarlem",            4.64, 52.38));
        NfcScanner scannerDelft             = new NfcScanner(selectedCard, new Location("Delft",              4.36, 52.01));
        NfcScanner scannerZwolle            = new NfcScanner(selectedCard, new Location("Zwolle",             6.09, 52.50));
        NfcScanner scannerGroningen         = new NfcScanner(selectedCard, new Location("Groningen",          6.57, 53.21));
        NfcScanner scannerMaastricht        = new NfcScanner(selectedCard, new Location("Maastricht",         5.71, 50.85));
        NfcScanner scannerTilburg           = new NfcScanner(selectedCard, new Location("Tilburg",            5.08, 51.56));
        NfcScanner scannerBreda             = new NfcScanner(selectedCard, new Location("Breda",              4.78, 51.60));
        NfcScanner scannerAmersfoort        = new NfcScanner(selectedCard, new Location("Amersfoort",         5.39, 52.15));
        NfcScanner scannerDeventer          = new NfcScanner(selectedCard, new Location("Deventer",           6.16, 52.25));
        NfcScanner scannerDordrecht         = new NfcScanner(selectedCard, new Location("Dordrecht",          4.67, 51.81));
        NfcScanner scannerVenlo             = new NfcScanner(selectedCard, new Location("Venlo",              6.17, 51.37));
        NfcScanner scannerAlmereCentrum     = new NfcScanner(selectedCard, new Location("Almere Centrum",     5.22, 52.37));
        ChargingStation chargingStation = new ChargingStation(selectedCard);

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\nOpties: 1. inchecken | 2. uitchecken | 3. opwaarderen | 4. info | 0. stop");
            String choice = input.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1", "2" -> {
                    System.out.println("Bij welk station? (amsterdam-centraal | rotterdam-centraal | den-haag-centraal | utrecht-centraal | eindhoven | arnhem | nijmegen | leiden-centraal | haarlem | delft | zwolle | groningen | maastricht | tilburg | breda | amersfoort | deventer | dordrecht | venlo | almere-centrum)");
                    String station = input.nextLine().trim().toLowerCase();
                    NfcScanner scanner = switch (station) {
                        case "amsterdam-centraal" -> scannerAmsterdamCentraal;
                        case "rotterdam-centraal" -> scannerRotterdamCentraal;
                        case "den-haag-centraal"  -> scannerDenHaagCentraal;
                        case "utrecht-centraal"   -> scannerUtrechtCentraal;
                        case "eindhoven"          -> scannerEindhoven;
                        case "arnhem"             -> scannerArnhem;
                        case "nijmegen"           -> scannerNijmegen;
                        case "leiden-centraal"    -> scannerLeidenCentraal;
                        case "haarlem"            -> scannerHaarlem;
                        case "delft"              -> scannerDelft;
                        case "zwolle"             -> scannerZwolle;
                        case "groningen"          -> scannerGroningen;
                        case "maastricht"         -> scannerMaastricht;
                        case "tilburg"            -> scannerTilburg;
                        case "breda"              -> scannerBreda;
                        case "amersfoort"         -> scannerAmersfoort;
                        case "deventer"           -> scannerDeventer;
                        case "dordrecht"          -> scannerDordrecht;
                        case "venlo"              -> scannerVenlo;
                        case "almere-centrum"     -> scannerAlmereCentrum;
                        default                   -> null;
                    };
                    if (scanner == null) { System.out.println("Onbekend station."); break; }
                    if (choice.equals("1")) scanner.checkIn();
                    else scanner.checkOut();
                }
                case "3" -> {
                    System.out.println("Huidig saldo: €" + selectedCard.getBalance());
                    System.out.println("Oplaadmethode? (1. opladen | 2. opladentot)");
                    String chargeChoice = input.nextLine().trim();
                    chargingStation.chargeSaldo(chargeChoice);
                }
                case "4"  -> selectedCard.printInfo();
                case "0"  -> { System.out.println("Uitgelogd."); loggedIn = false; }
                default      -> System.out.println("Ongeldige optie, kies 1,2,3,4,0.");
            }
        }
    }
}