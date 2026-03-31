import java.text.DecimalFormat;

public class Card {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private int cardId;
    private boolean active = false;
    private double balance = 0;
    private boolean checkedIn = false;
    private Location checkInLocation = null;

    public Card(int cardId) {
        this.cardId = cardId;
    }

    public void setActive(boolean status)   { active = status; }
    public boolean isActive()               { return active; }
    public boolean isCheckedIn()            { return checkedIn; }
    public double getBalance()              { return balance; }
    public Location getCheckInLocation()    { return checkInLocation; }

    public void withdrawBalance(double amount) {
        balance -= amount;
        if (amount < 0) {
            System.out.println("€" + -amount + " opgeladen. Nieuw saldo: €" + df.format(balance) + " (afgerond)");
        } else if (amount > 0) {
            System.out.println("€" + amount + " afgetrokken. Nieuw saldo: €" + df.format(balance) + " (afgerond)");
        }
    }

    public void depositBalance(double amount) { balance += amount; }

    public void checkIn(Location location) {
        checkInLocation = location;
        checkedIn = true;
        System.out.println("Ingechecked op " + checkInLocation.getName() + ". Saldo: €" + df.format(balance) + " (afgerond)");
    }

    public void checkOut(Location location) {
        checkedIn = false;
        checkInLocation = null;
        System.out.println("Uitgechecked op " + location.getName() + ". Saldo: €" + df.format(balance) + " (afgerond)");
    }

    public void printInfo() {
        System.out.println("=== Kaartinfo ===");
        System.out.println("Kaart ID:    " + cardId);
        System.out.println("Actief:      " + active);
        System.out.println("Saldo:       €" + balance + " (niet afgerond)");
        System.out.println("Ingechecked: " + checkedIn);
        System.out.println("Locatie:     " + (checkInLocation != null ? checkInLocation.getName() : "N.V.T."));
    }
}