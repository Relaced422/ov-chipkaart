public class Card {
    private int cardID = 1;
    private String expiry = "01/01/2026";
    private boolean active = true;
    private double balance = 50.0;
    private int subscriptionType = 1;
    private boolean checkedIn = false;
    private String previousLocation = "Arnhem";

    public boolean isActive() {
        return active;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public double getBalance() {
        return balance;
    }

    public int getCardID() {
        return cardID;
    }

    public double withdrawBalance(double amount) {
        balance -= amount;
        return balance;
    }

    public double calculatePrice(String location, String previousLocation) {
        double price = 0.0;
        if ((previousLocation == "Arnhem") && (location == "Nijmegen")) {
            price = 7.30;
        }
        return price;
    }

    public void toggleCheckIn(String location) {
        if (!checkedIn) {
            calculatePrice(location);
            checkedIn = true;
            IO.println("Je bent nu ingechecked!");
        } else {
            checkedIn = false;
            IO.println("Je bent nu uitgechecked!");
        }
    }
}