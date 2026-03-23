public class Card {
    //    CARD INIT
    private int cardID = 1;
    private String expiry = "01/01/2026";
    private boolean active = true;
    private double balance = 50.0;
    private int subscriptionType = 1;
    private boolean checkedIn = false;
    private String previousLocation = "Arnhem";

    //    GETTERS
    public boolean getActive() {
        return this.active;
    }

    public boolean getCheckedIn() {
        return this.checkedIn;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getCardID() {
        return this.cardID;
    }

    //    SETTERS
    public void setCheckedIn(boolean newCheckInStatus) {
        this.checkedIn = newCheckInStatus;
    }

    //    FUNCTIONS
    public void withdrawBalance(double amount) {
        balance -= amount;
    }

    public double calculatePrice(String location, String previousLocation) {
        double price = 0.0;
        if (previousLocation.equals("Arnhem") && location.equals("Nijmegen")) {
            price = 7.30;
        }
        return price;
    }

    public void toggleCheckIn(String location) {
        if (!checkedIn) {
            double price = calculatePrice(location, previousLocation);
            withdrawBalance(price);
            checkedIn = true;
            IO.println("Je bent nu ingechecked! €" + price + " afgeschreven. Huidig saldo: €" + balance);
        } else {
            checkedIn = false;
            IO.println("Je bent nu uitgechecked!");
        }
    }
}