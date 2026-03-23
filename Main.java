import java.util.Scanner;

void main() {
//    CLASS INIT
    Card card = new Card();
    NFCScanner scannerNijmegen = new NFCScanner(card, "Nijmegen");
    NFCScanner scannerArnhem = new NFCScanner(card, "Arnhem");
    Laadpunt laadpunt = new Laadpunt(card);
    Scanner input = new Scanner(System.in);

    while (true) {
//        Switch case always active
        System.out.println("\nOpties: inchecken | uitchecken | saldo | info | stop");
        String keuze = input.nextLine().trim().toLowerCase();

//        Keuze menu
        switch (keuze) {
            case "inchecken"   -> scannerNijmegen.inchecken();
            case "uitchecken"  -> scannerArnhem.uitchecken();
            case "saldo"       -> {
                System.out.println("Huidig saldo: " + card.getBalance());
                System.out.println("Hoeveel wil je opwaarderen? (??,??)");
                double amount = Double.parseDouble(input.nextLine().replace(",", "."));
                laadpunt.laadSaldo(amount);
            }
            case "info"        -> card.printInfo();
            case "stop"        -> { System.out.println("Tot ziens!"); return; }
            default            -> System.out.println("Ongeldige optie.");
        }
    }
}