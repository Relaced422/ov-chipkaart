import java.util.Scanner;

Card card = new Card();
Laadpunt laadpunt = new Laadpunt();
NFCScanner NFCScanner = new NFCScanner();
Scanner scanner = new Scanner(System.in);

void main() {
    System.out.println("Wil je inchecken? (true)");
    String input = scanner.nextLine();
    if (input.equals("true")) {
        NFCScanner.inchecken();
    } else {
        System.out.println("Je bent niet ingecheckt.");
    }
}