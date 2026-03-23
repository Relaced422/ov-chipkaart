import java.util.Scanner;

Card card = new Card();
Laadpunt laadpunt = new Laadpunt();
NFCScanner NFCScanner = new NFCScanner();
Scanner scanner = new Scanner(System.in);

void main() {
    while (true) {
        System.out.println("Wil je inchecken? (true = inchecken) (false = uitchecken) (show = huidige kaart info laten zien (DEBUG))");
        String input = scanner.nextLine();
        if (input.equals("true")) {
            NFCScanner.inchecken();
        } else if (input.equals("false")) {
            NFCScanner.uitchecken();
        } else if (input.equals("show")) {
            System.out.println("Ingechecked: " + card.getCheckedIn());
            System.out.println("Actief: " + card.getActive());
        } else if (input.equals("toggle")) {
            card.toggleCheckIn("Arnhem");
        } else {
            System.out.println("Je bent niet ingecheckt.");
        }
    }
}