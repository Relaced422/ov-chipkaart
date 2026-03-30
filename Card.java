import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Card {
    private int cardID = 1;
    private boolean active = true;
    private double balance = 50.0;
    private boolean checkedIn = false;
    private Locatie checkInLocation = null;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public boolean getActive() {
        return active;
    }

    public boolean getCheckedIn() {
        return checkedIn;
    }

    public double getBalance() {
        return balance;
    }

    public Locatie getCheckInLocation() {
        return checkInLocation;
    }

    public void withdrawBalance(double amount) {balance -= amount;}

public void checkIn(Locatie location) {
    checkInLocation = location;
    checkedIn = true;
    System.out.println("Ingechecked op " + checkInLocation.getNaam() + ". Saldo: €" + df.format(balance));
}

public void checkOut(Locatie location) {
    checkedIn = false;
    checkInLocation = null;
    System.out.println("Uitgechecked op " + location.getNaam() + ". Saldo: €" + df.format(balance));
}

public void printInfo() {
    System.out.println("=== Kaartinfo ===");
    System.out.println("Kaart ID:    " + cardID);
    System.out.println("Actief:      " + active);
    System.out.println("Saldo:       €" + balance + " (niet afgerond)");
    System.out.println("Ingechecked: " + checkedIn);
    System.out.println("Locatie:     " + (checkInLocation != null ? checkInLocation.getNaam() : "N/A"));
}
}