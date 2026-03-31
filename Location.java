public class Location {
    private String name;
    private double x, y;

    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() { return name; }

    public double calculateDistance(Location other) {
        double a = this.x - other.x;
        double b = this.y - other.y;
        return Math.sqrt(a * a + b * b);
    }
}