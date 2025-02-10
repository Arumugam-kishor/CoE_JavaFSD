package warehouseInventoryManagementSystem;

public class Location implements Comparable<Location> {
    private int aisle;
    private int shelf;
    private int bin;

    public Location(int aisle, int shelf, int bin) {
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }

    @Override
    public int compareTo(Location other) {
        if (this.aisle != other.aisle) return Integer.compare(this.aisle, other.aisle);
        if (this.shelf != other.shelf) return Integer.compare(this.shelf, other.shelf);
        return Integer.compare(this.bin, other.bin);
    }

    public int getAisle() { return aisle; }
    public int getShelf() { return shelf; }
    public int getBin() { return bin; }

    @Override
    public String toString() {
        return String.format("Aisle: %d, Shelf: %d, Bin: %d", aisle, shelf, bin);
    }
}