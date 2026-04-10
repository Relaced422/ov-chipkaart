import java.util.Scanner;

void main() {
    int idOne   = (int) (Math.random() * 9000) + 1000;
    int idTwo   = (int) (Math.random() * 9000) + 1000;
    int idThree = (int) (Math.random() * 9000) + 1000;

    Card cardOne   = new Card(idOne);
    Card cardTwo   = new Card(idTwo);
    Card cardThree = new Card(idThree);
    Scanner input  = new Scanner(System.in);

    Location amsterdamCentraal = new Location("Amsterdam Centraal",  120.0,  490.0);
    Location rotterdamCentraal = new Location("Rotterdam Centraal",   78.0,  433.0);
    Location denHaagCentraal   = new Location("Den Haag Centraal",    62.0,  453.0);
    Location utrechtCentraal   = new Location("Utrecht Centraal",    143.0,  456.0);
    Location eindhoven         = new Location("Eindhoven",           158.0,  387.0);
    Location arnhem            = new Location("Arnhem",              190.0,  437.0);
    Location nijmegen          = new Location("Nijmegen",            184.0,  420.0);
    Location leidenCentraal    = new Location("Leiden Centraal",      82.0,  472.0);
    Location haarlem           = new Location("Haarlem",             104.0,  490.0);
    Location delft             = new Location("Delft",                72.0,  444.0);
    Location zwolle            = new Location("Zwolle",              204.0,  518.0);
    Location groningen         = new Location("Groningen",           258.0,  591.0);
    Location maastricht        = new Location("Maastricht",          172.0,  300.0);
    Location tilburg           = new Location("Tilburg",             138.0,  401.0);
    Location breda             = new Location("Breda",               106.0,  400.0);
    Location amersfoort        = new Location("Amersfoort",          162.0,  463.0);
    Location deventer          = new Location("Deventer",            213.0,  477.0);
    Location dordrecht         = new Location("Dordrecht",           96.0,   422.0);
    Location venlo             = new Location("Venlo",               235.0,  380.0);
    Location almereCentrum     = new Location("Almere Centrum",      156.0,  503.0);

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

        NfcScanner scannerAmsterdamCentraal = new NfcScanner(selectedCard, amsterdamCentraal);
        NfcScanner scannerRotterdamCentraal = new NfcScanner(selectedCard, rotterdamCentraal);
        NfcScanner scannerDenHaagCentraal   = new NfcScanner(selectedCard, denHaagCentraal);
        NfcScanner scannerUtrechtCentraal   = new NfcScanner(selectedCard, utrechtCentraal);
        NfcScanner scannerEindhoven         = new NfcScanner(selectedCard, eindhoven);
        NfcScanner scannerArnhem            = new NfcScanner(selectedCard, arnhem);
        NfcScanner scannerNijmegen          = new NfcScanner(selectedCard, nijmegen);
        NfcScanner scannerLeidenCentraal    = new NfcScanner(selectedCard, leidenCentraal);
        NfcScanner scannerHaarlem           = new NfcScanner(selectedCard, haarlem);
        NfcScanner scannerDelft             = new NfcScanner(selectedCard, delft);
        NfcScanner scannerZwolle            = new NfcScanner(selectedCard, zwolle);
        NfcScanner scannerGroningen         = new NfcScanner(selectedCard, groningen);
        NfcScanner scannerMaastricht        = new NfcScanner(selectedCard, maastricht);
        NfcScanner scannerTilburg           = new NfcScanner(selectedCard, tilburg);
        NfcScanner scannerBreda             = new NfcScanner(selectedCard, breda);
        NfcScanner scannerAmersfoort        = new NfcScanner(selectedCard, amersfoort);
        NfcScanner scannerDeventer          = new NfcScanner(selectedCard, deventer);
        NfcScanner scannerDordrecht         = new NfcScanner(selectedCard, dordrecht);
        NfcScanner scannerVenlo             = new NfcScanner(selectedCard, venlo);
        NfcScanner scannerAlmereCentrum     = new NfcScanner(selectedCard, almereCentrum);
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